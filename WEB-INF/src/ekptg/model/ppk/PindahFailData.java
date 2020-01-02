package ekptg.model.ppk;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;

public class PindahFailData {
	static Logger myLogger = Logger.getLogger(PindahFailData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");

	private static Vector listTunggu = new Vector();
	private static Vector listTunggu17 = new Vector();
	private static Vector listTunggubyNeg = new Vector();
	private static Vector listTunggubyNeg17 = new Vector();
	private static Vector listTunggubyNegFailLama = new Vector();
	private static Vector listTunggubyNegFailLama17 = new Vector();
	private Vector listFailPindah = new Vector();
	private Vector statusID = new Vector();
	private static Vector listBatal = new Vector();
	private static Vector listFailPindahBatal = new Vector();
	private static Vector listLokasiUserID = new Vector();
	private static Vector NoFailBaru = new Vector();
	private static Vector NoFailBaruCreate = new Vector();


	public static Vector getDataNoFailBaru(){
		return NoFailBaru;
	}
	
	public static Vector getDataNoFailBaruCreate(){
		return NoFailBaruCreate;
	}

	public Vector listTunggubyNegFailLama(){
		return listTunggubyNegFailLama;
	}

	public Vector listTunggubyNegFailLama17(){
		return listTunggubyNegFailLama17;
	}

	public Vector getFailPindah(){
		return listFailPindah;
	}

	public Vector getFailPindahBatal(){
		return listFailPindahBatal;
	}
	public static Vector getListTunggu(){
		return listTunggu;
	}

	public static Vector listTunggubyNeg(){
		return listTunggubyNeg;
	}

	public static Vector listTunggubyNeg17(){
		return listTunggubyNeg17;
	}

	public static Vector listTunggu(){
		return listTunggu;
	}

	public static Vector listTunggu17(){
		return listTunggu17;
	}

	public Vector getListStatusID(){
		return statusID;
	}

	public static Vector getListBatal(){
		return listBatal;
	}

	public void setList(String ekptg_user_id) throws Exception {
		Db db = null;
		listTunggu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      db = new Db();
		      Statement stmt = db.getStatement();

		      //SYARAT
		      sql = "SELECT f.id_fail, f.no_fail, f.tarikh_daftar_fail, a.id_permohonan, a.no_permohonan, ";
		      sql += "a.tarikh_mohon, a.tarikh_masuk, s.keterangan, p.id_simati, ";
		      sql += "p.nama_simati, pp.nama_pemohon, kp.keputusan_permohonan, ";
		      sql += "bke.id_negeri, neg.nama_negeri, bke.id_daerah, da.nama_daerah,bke.id_bke, a.id_status ";
		      sql += "FROM tblppkpermohonan a,tblpfdfail f,tblrujstatus s,tblppksimati p,tblppkpemohon pp,tblppkkeputusanpermohonan kp,tblppkpermohonansimati psm,tblppkbke bke,tblrujnegeri neg,tblrujdaerah da,users ur ";
		      sql += "WHERE a.id_status = s.id_status(+) AND a.id_fail = f.id_fail ";
		      sql += "AND a.id_pemohon = pp.id_pemohon(+) AND p.id_simati = psm.id_simati AND a.id_permohonan = psm.id_permohonan ";
		      sql += "AND a.id_permohonan = kp.id_permohonan(+) AND bke.id_permohonan = a.id_permohonan ";
		      sql += "AND bke.id_negeri = neg.id_negeri AND da.id_daerah = bke.id_daerah ";
		      sql += "AND a.id_permohonan = bke.id_permohonan AND ur.user_id = a.id_masuk ";
		      sql += "AND a.id_status = 56 AND ur.user_id = " +ekptg_user_id+ "  ";

		      //sorting
		      sql +=" ORDER BY f.id_fail DESC, a.tarikh_kemaskini DESC";

		      ResultSet rs = stmt.executeQuery(sql);
		      myLogger.info("SQL FAIL PINDAH 56 = "+sql);

		      Hashtable h;
		      int bil = 1;

			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("keputusan_permohonan", rs.getString("KEPUTUSAN_PERMOHONAN")==null?"":rs.getString("KEPUTUSAN_PERMOHONAN"));
				h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("id_bke", rs.getString("ID_BKE")==null?"":rs.getString("ID_BKE"));
				h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));

				listTunggu.addElement(h);
				bil++;
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}


	public void setListMaklumatMenunggu(String ekptg_user_id,String id_negeri,String id_daerah) throws Exception {
		Db db = null;
		listTunggu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT DISTINCT A.TARIKH_KEMASKINI,A.ID_STATUS,C.ID_FAIL,C.NO_FAIL ,C.TARIKH_DAFTAR_FAIL,A.ID_PERMOHONAN, A.TARIKH_MOHON,A.TARIKH_MASUK, "+
		      "E.ID_SIMATI,upper(E.NAMA_SIMATI) as NAMA_SIMATI, upper(F.NAMA_PEMOHON) as NAMA_PEMOHON,"+
		      "G.NAMA_NEGERI,G.ID_NEGERI, H.NAMA_DAERAH,H.ID_DAERAH, O.KETERANGAN, Q.USER_LOGIN, "+
		      "A.ID_NEGERIMHN, A.ID_DAERAHMHN, R.NAMA_NEGERI, S.NAMA_DAERAH, N.ID_SUBURUSAN, A.SEKSYEN "+
		      "from TBLPPKPERMOHONAN A, TBLPPKBKE B,  TBLPFDFAIL C, TBLPPKPERMOHONANSIMATI D, TBLPPKSIMATI E, "+
		      "TBLPPKPEMOHON F,  TBLRUJNEGERI G,  TBLRUJDAERAH H,  TBLRUJPEJABATURUSAN I, TBLRUJPEJABATJKPTG J, "+
		      "TBLRUJSUBURUSANSTATUSFAIL M, TBLRUJSUBURUSANSTATUS N, TBLRUJSTATUS O, USERS_INTERNAL P, USERS Q, "+
		      "TBLRUJNEGERI R, TBLRUJDAERAH S "+
		      "WHERE A.ID_PERMOHONAN =  B.ID_PERMOHONAN "+
		      "AND A.ID_FAIL  =  C.ID_FAIL "+
		      "AND D.ID_PERMOHONAN  = A.ID_PERMOHONAN "+
		      "AND D.ID_SIMATI = E.ID_SIMATI "+
		      "AND A.ID_PEMOHON = F.ID_PEMOHON "+
		      "AND B.ID_NEGERI = G.ID_NEGERI "+
		      "AND B.ID_DAERAH = H.ID_DAERAH "+
		      //"and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
		      //"AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
		      "AND I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "+
		      "AND M.ID_PERMOHONAN = A.ID_PERMOHONAN "+
		      "AND M.AKTIF = 1 "+
		      "AND M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "+
		      "AND N.ID_STATUS = O.ID_STATUS "+
		      "AND J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "+
		      "AND P.USER_ID  =  Q.USER_ID "+
		      "AND A.ID_NEGERIMHN = R.ID_NEGERI "+
		      "AND A.ID_DAERAHMHN = S.ID_DAERAH "+
		      "AND Q.USER_ID = '"+ekptg_user_id+"' "+
		      "AND G.ID_NEGERI = P.ID_NEGERI "+
		      "AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL) "+
		      "AND A.ID_STATUS = 56 ";

		      //sorting
//		      sql +=" ORDER BY c.id_fail DESC, a.tarikh_kemaskini DESC";
		      sql +=" ORDER BY A.TARIKH_KEMASKINI ASC";
		      myLogger.info("SQL LIST PERTAMA = "+sql);

		      ResultSet rs = stmt.executeQuery(sql);

		      Hashtable h;
		      int bil = 1;

			while (rs.next()){
//				myLogger.info("simati:"+rs.getString("NAMA_SIMATI"));
//				myLogger.info("simati:"+rs.getString("NAMA_PEMOHON"));
//				myLogger.info("negeri:"+rs.getString("NAMA_NEGERI"));
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
				h.put("id_daerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
				h.put("nama_negerimhn", rs.getString(20)==null?"":rs.getString(20));
				h.put("nama_daerahmhn", rs.getString(21)==null?"":rs.getString(21));
				h.put("id_suburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));

				listTunggu.addElement(h);
				bil++;
			}
			//return list;
		} catch (Exception e) {e.printStackTrace();} finally {
			if(db != null) db.close();
		}
	}



	public void setListMaklumatMenunggu17(String ekptg_user_id,String id_negeri,String id_daerah) throws Exception {
		Db db = null;
		listTunggu17.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "select DISTINCT a.tarikh_kemaskini,a.id_status,c.id_fail,c.no_fail ,c.tarikh_daftar_fail,a.id_permohonan, a.tarikh_mohon,a.tarikh_masuk, "+
		      "e.id_simati,upper(e.nama_simati) as NAMA_SIMATI, upper(f.nama_pemohon) as NAMA_PEMOHON,"+
		      "g.nama_negeri,g.id_negeri, h.nama_daerah,h.id_daerah, O.KETERANGAN, Q.USER_LOGIN, "+
		      "a.id_negerimhn, a.id_daerahmhn, r.nama_negeri, s.nama_daerah "+
		      "from tblppkpermohonan a, tblppkbke b,  tblpfdfail c, tblppkpermohonansimati d, tblppksimati e, "+
		      "tblppkpemohon f,  tblrujnegeri g,  tblrujdaerah h,  tblrujpejabaturusan i, tblrujpejabatjkptg j, "+
		      "tblrujsuburusanstatusfail m, tblrujsuburusanstatus n, tblrujstatus o, users_internal p, users q, "+
		      "tblrujnegeri r, tblrujdaerah s "+
		      "where A.ID_PERMOHONAN =  B.ID_PERMOHONAN "+
		      "and A.ID_FAIL  =  c.id_fail "+
		      "and D.ID_PERMOHONAN  = A.ID_PERMOHONAN "+
		      "and D.ID_SIMATI = E.ID_SIMATI "+
		      "and A.ID_PEMOHON = F.ID_PEMOHON "+
		      "and B.ID_NEGERI = G.ID_NEGERI "+
		      "and B.ID_DAERAH = h.ID_DAERAH "+
		      //"and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
		      //"AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
		      "and I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "+
		      "and M.ID_PERMOHONAN = A.ID_PERMOHONAN "+
		      "and M.AKTIF = 1 "+
		      "and M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "+
		      "and N.ID_STATUS = O.ID_STATUS "+
		      "and J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "+
		      "and P.USER_ID  =  Q.USER_ID "+
		      "AND a.id_negerimhn = r.id_negeri "+
		      "AND a.id_daerahmhn = s.id_daerah "+
		      "and Q.USER_ID = '"+ekptg_user_id+"' "+
		      "and g.id_negeri = p.id_negeri "+
		      "and A.ID_STATUS = 303 ";

		      //sorting
//		      sql +=" ORDER BY c.id_fail DESC, a.tarikh_kemaskini DESC";
		      sql +=" ORDER BY a.tarikh_kemaskini ASC";
		      myLogger.info("SQL LIST PERTAMA = "+sql);

		      ResultSet rs = stmt.executeQuery(sql);


		      Hashtable h;
		      int bil = 1;

			while (rs.next()){
				myLogger.info("simati:"+rs.getString("NAMA_SIMATI"));
				myLogger.info("simati:"+rs.getString("NAMA_PEMOHON"));
				myLogger.info("negeri:"+rs.getString("NAMA_NEGERI"));
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
				h.put("id_daerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
				h.put("nama_negerimhn", rs.getString(20)==null?"":rs.getString(20));
				h.put("nama_daerahmhn", rs.getString(21)==null?"":rs.getString(21));

				listTunggu17.addElement(h);
				bil++;
			}
			//return list;
		} catch (Exception e) {e.printStackTrace();} finally {
			if(db != null) db.close();
		}
		}

	public void setListbyIdnegeri(String ekptg_user_id,String id_negeri,String id_daerah) throws Exception {
		Db db = null;
		listTunggubyNeg.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      db = new Db();
		      Statement stmt = db.getStatement();

		      sql = "select distinct c.id_fail,c.no_fail ,c.tarikh_daftar_fail,a.id_permohonan, a.tarikh_mohon,a.tarikh_masuk, "+
		      "e.id_simati,upper(e.nama_simati) as NAMA_SIMATI, upper(f.nama_pemohon) as NAMA_PEMOHON,"+
		      "g.nama_negeri,g.id_negeri, h.nama_daerah,h.id_daerah, O.KETERANGAN, Q.USER_LOGIN, "+
		      "a.id_negerimhn, a.id_daerahmhn, r.nama_negeri, s.nama_daerah, a.tarikh_kemaskini, n.id_suburusan, a.seksyen "+
		      "from tblppkpermohonan a, tblppkbke b,  tblpfdfail c, tblppkpermohonansimati d, tblppksimati e, "+
		      "tblppkpemohon f,  tblrujnegeri g,  tblrujdaerah h,  tblrujpejabaturusan i, tblrujpejabatjkptg j, "+
		      "tblrujsuburusanstatusfail m, tblrujsuburusanstatus n, tblrujstatus o, users_internal p, users q, "+
		      "tblrujnegeri r, tblrujdaerah s "+
		      "where A.ID_PERMOHONAN =  B.ID_PERMOHONAN "+
		      "and A.ID_FAIL  =  c.id_fail "+
		      "and D.ID_PERMOHONAN  = A.ID_PERMOHONAN "+
		      "and D.ID_SIMATI = E.ID_SIMATI "+
		      "and A.ID_PEMOHON = F.ID_PEMOHON "+
		      "and B.ID_NEGERI = G.ID_NEGERI "+
		      "and B.ID_DAERAH = h.ID_DAERAH "+
		      "and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
		      "AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
		      "and I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "+
		      "and M.ID_PERMOHONAN = A.ID_PERMOHONAN "+
		      "and M.AKTIF = 1 "+
		      "and M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "+
		      "and N.ID_STATUS = O.ID_STATUS "+
		      "and J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "+
		      "and P.USER_ID  =  Q.USER_ID "+
		      "AND a.id_negerimhn = r.id_negeri "+
		      "AND a.id_daerahmhn = s.id_daerah "+
		      "AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL) "+
		      "and Q.USER_ID = '"+ekptg_user_id+"' "+
		      "and N.ID_STATUS = 56 ";

		      //sorting
		      //sql +=" ORDER BY c.id_fail DESC, a.tarikh_kemaskini DESC";
		      sql +=" ORDER BY a.tarikh_kemaskini ASC";
		      myLogger.info("SQL LIST KEDUA = "+sql);

		      ResultSet rs = stmt.executeQuery(sql);


		      Hashtable h;
		      int bil = 1;

			while (rs.next()){
				myLogger.info("simati:"+rs.getString("NAMA_SIMATI"));
				myLogger.info("simati:"+rs.getString("NAMA_PEMOHON"));
				myLogger.info("negeri:"+rs.getString("NAMA_NEGERI"));
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
				h.put("id_daerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
				h.put("nama_negerimhn", rs.getString(18)==null?"":rs.getString(18));
				h.put("nama_daerahmhn", rs.getString(19)==null?"":rs.getString(19));
				h.put("id_suburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));

				listTunggubyNeg.addElement(h);
				bil++;
			}
			//return list;
		} catch (Exception e) {e.printStackTrace();} finally {
			if(db != null) db.close();
		}
		}


	public void setListbyIdnegeri17(String ekptg_user_id,String id_negeri,String id_daerah) throws Exception {
		Db db = null;
		listTunggubyNeg17.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      db = new Db();
		      Statement stmt = db.getStatement();

		      sql = "select distinct c.id_fail,c.no_fail ,c.tarikh_daftar_fail,a.id_permohonan, a.tarikh_mohon,a.tarikh_masuk, "+
		      "e.id_simati,upper(e.nama_simati) as NAMA_SIMATI, upper(f.nama_pemohon) as NAMA_PEMOHON,"+
		      "g.nama_negeri,g.id_negeri, h.nama_daerah,h.id_daerah, O.KETERANGAN, Q.USER_LOGIN, "+
		      "a.id_negerimhn, a.id_daerahmhn, r.nama_negeri, s.nama_daerah, a.tarikh_kemaskini "+
		      "from tblppkpermohonan a, tblppkbke b,  tblpfdfail c, tblppkpermohonansimati d, tblppksimati e, "+
		      "tblppkpemohon f,  tblrujnegeri g,  tblrujdaerah h,  tblrujpejabaturusan i, tblrujpejabatjkptg j, "+
		      "tblrujsuburusanstatusfail m, tblrujsuburusanstatus n, tblrujstatus o, users_internal p, users q, "+
		      "tblrujnegeri r, tblrujdaerah s "+
		      "where A.ID_PERMOHONAN =  B.ID_PERMOHONAN "+
		      "and A.ID_FAIL  =  c.id_fail "+
		      "and D.ID_PERMOHONAN  = A.ID_PERMOHONAN "+
		      "and D.ID_SIMATI = E.ID_SIMATI "+
		      "and A.ID_PEMOHON = F.ID_PEMOHON "+
		      "and B.ID_NEGERI = G.ID_NEGERI "+
		      "and B.ID_DAERAH = h.ID_DAERAH "+
		      "and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
		      "AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
		      "and I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "+
		      "and M.ID_PERMOHONAN = A.ID_PERMOHONAN "+
		      "and M.AKTIF = 1 "+
		      "and M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "+
		      "and N.ID_STATUS = O.ID_STATUS "+
		      "and J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "+
		      "and P.USER_ID  =  Q.USER_ID "+
		      "AND a.id_negerimhn = r.id_negeri "+
		      "AND a.id_daerahmhn = s.id_daerah "+
		      "and Q.USER_ID = '"+ekptg_user_id+"' "+
		      "and N.ID_STATUS = 56 AND c.id_suburusan = 60 ";

		      sql +=" ORDER BY a.tarikh_kemaskini ASC";


		      ResultSet rs = stmt.executeQuery(sql);

		      myLogger.info("SQL LIST KEDUA SEKSYEN 17 = "+sql);

		      Hashtable h;
		      int bil = 1;

			while (rs.next()){
				myLogger.info(bil);
				myLogger.info("simati:"+rs.getString("NAMA_SIMATI"));
				myLogger.info("simati:"+rs.getString("NAMA_PEMOHON"));
				myLogger.info("negeri:"+rs.getString("NAMA_NEGERI"));
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
				h.put("id_daerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
				h.put("nama_negerimhn", rs.getString(18)==null?"":rs.getString(18));
				h.put("nama_daerahmhn", rs.getString(19)==null?"":rs.getString(19));

				listTunggubyNeg17.addElement(h);
				bil++;
			}
			//return list;
		} catch (Exception e) {e.printStackTrace();} finally {
			if(db != null) db.close();
		}
		}



	public static void setListBatal(String ekptg_user_id) throws Exception {
		Db db = null;
		listBatal.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      //db = new Db();
		      Statement stmt = db.getStatement();

		      //SYARAT
		      sql = "SELECT f.id_fail, f.no_fail, f.tarikh_daftar_fail, a.id_permohonan, a.no_permohonan, ";
		      sql += "a.tarikh_mohon, a.tarikh_masuk, s.keterangan, p.id_simati, ";
		      sql += "p.nama_simati, pp.nama_pemohon, kp.keputusan_permohonan, ";
		      sql += "bke.id_negeri, neg.nama_negeri, bke.id_daerah, da.nama_daerah,bke.id_bke ";
		      sql += "FROM tblppkpermohonan a,tblpfdfail f,tblrujstatus s,tblppksimati p,tblppkpemohon pp,tblppkkeputusanpermohonan kp,tblppkpermohonansimati psm,tblppkbke bke,tblrujnegeri neg,tblrujdaerah da,users ur ";
		      sql += "WHERE a.id_status = s.id_status(+) AND a.id_fail = f.id_fail ";
		      sql += "AND a.id_pemohon = pp.id_pemohon(+) AND p.id_simati = psm.id_simati AND a.id_permohonan = psm.id_permohonan ";
		      sql += "AND a.id_permohonan = kp.id_permohonan(+) AND bke.id_permohonan = a.id_permohonan ";
		      sql += "AND bke.id_negeri = neg.id_negeri AND da.id_daerah = bke.id_daerah ";
		      sql += "AND a.id_permohonan = bke.id_permohonan AND ur.user_id = a.id_masuk ";
		      sql += "AND a.id_status = 169 AND ur.user_id = "+ ekptg_user_id +" ";

		      //sorting
		      sql +=" ORDER BY f.id_fail DESC, a.tarikh_kemaskini DESC";

		      ResultSet rs = stmt.executeQuery(sql);

		      Hashtable h;
		      int bil = 1;

			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("keputusan_permohonan", rs.getString("KEPUTUSAN_PERMOHONAN")==null?"":rs.getString("KEPUTUSAN_PERMOHONAN"));
				h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("id_bke", rs.getString("ID_BKE")==null?"":rs.getString("ID_BKE"));

				listBatal.addElement(h);
				bil++;
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
	}


	public void setFailPindah(String id_permohonan, String id_fail) throws Exception {
		Db db = null;
		listFailPindah.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("F.ID_FAIL");
			r.add("F.NO_FAIL");
			r.add("F.TARIKH_DAFTAR_FAIL");
			r.add("A.ID_PERMOHONAN");
			r.add("A.TARIKH_MOHON");
			r.add("A.TARIKH_MASUK");
			r.add("S.KETERANGAN");
			r.add("P.ID_SIMATI");
			r.add("P.NAMA_SIMATI");
			r.add("PP.NAMA_PEMOHON");
			r.add("KP.KEPUTUSAN_PERMOHONAN");
			r.add("BKE.ID_NEGERI");
			r.add("NEG.NAMA_NEGERI");
			r.add("BKE.ID_DAERAH");
			r.add("DA.NAMA_DAERAH");
			r.add("F.ID_NEGERI");
			r.add("BKE.ID_BKE");
			r.add("NEG.KOD_NEGERI");
			r.add("DA.KOD_DAERAH");
			r.add("A.ID_NEGERIMHN");
			r.add("A.ID_DAERAHMHN");
			r.add("R.NAMA_NEGERI");
			r.add("S.NAMA_DAERAH");


			r.add("A.ID_STATUS",r.unquote("S.ID_STATUS(+)"));
			r.add("A.ID_FAIL",r.unquote("F.ID_FAIL"));
			r.add("A.ID_PEMOHON",r.unquote("PP.ID_PEMOHON(+)"));
			r.add("P.ID_SIMATI",r.unquote("PSM.ID_SIMATI"));
			r.add("A.ID_PERMOHONAN",r.unquote("PSM.ID_PERMOHONAN"));
			r.add("A.ID_PERMOHONAN",r.unquote("KP.ID_PERMOHONAN(+)"));
			r.add("BKE.ID_PERMOHONAN",r.unquote("A.ID_PERMOHONAN"));
			r.add("BKE.ID_NEGERI",r.unquote("NEG.ID_NEGERI"));
			r.add("BKE.ID_DAERAH",r.unquote("DA.ID_DAERAH"));
			r.add("A.ID_NEGERIMHN",r.unquote("R.ID_NEGERI"));
			r.add("A.ID_DAERAHMHN",r.unquote("S.ID_DAERAH"));

			r.add("BKE.ID_PERMOHONAN",id_permohonan);

			sql = r.getSQLSelect("TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,TBLPPKPEMOHON PP,TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERMOHONANSIMATI PSM, TBLPPKBKE BKE, TBLRUJNEGERI NEG, TBLRUJDAERAH DA, TBLRUJNEGERI R, TBLRUJDAERAH S ");
			sql = sql + " AND A.ID_STATUS = 56  ";
			myLogger.info("SQL TBLPPKBKE = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("keputusan_permohonan", rs.getString("KEPUTUSAN_PERMOHONAN")==null?"":rs.getString("KEPUTUSAN_PERMOHONAN"));
				h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("id_projek_negeri", rs.getString(16)==null?"":rs.getString(16));
				h.put("id_bke", rs.getString("ID_BKE")==null?"":rs.getString("ID_BKE"));
				h.put("kod_negeri", rs.getString("KOD_NEGERI")==null?"":rs.getString("KOD_NEGERI"));
				h.put("kod_daerah", rs.getString("KOD_DAERAH")==null?"":rs.getString("KOD_DAERAH"));
				h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
				h.put("id_daerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
				h.put("nama_negerimhn", rs.getString(22)==null?"":rs.getString(22));
				h.put("nama_daerahmhn", rs.getString(23)==null?"":rs.getString(23));

				listFailPindah.addElement(h);
				bil++;
			}

		}finally {
			if(db != null) db.close();
		}
	}

	 public void setListStatusID(String id_permohonan) throws Exception {
		    Db db = null;
		    statusID.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("fs.id_suburusanstatusfail");
		      r.add("p.id_Permohonan");
		      r.add("fs.aktif");
		      r.add("p.id_status");

		      r.add("fs.id_permohonan",r.unquote("p.id_permohonan"));
		      r.add("fs.aktif",r.unquote("1"));

		      r.add("p.id_Permohonan",id_permohonan);

		      sql = r.getSQLSelect("Tblppkpermohonan p, Tblrujsuburusanstatusfail fs");
		      myLogger.info("SQL status id = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();

		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		    	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail")==null?"":rs.getString("id_suburusanstatusfail"));
		    	h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
		    	h.put("aktif", rs.getString("aktif")==null?"":rs.getString("aktif"));
		    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));

		    	statusID.addElement(h);
		      }

		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }


	public static Vector getNewNoFail(int id_fail) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      r.add("id_fail");
	      r.add("no_fail");

	      //r.add("a.id_perbicaraan",r.unquote("b.id_perbicaraan"));

	      r.add("id_fail",id_fail);

	      sql = r.getSQLSelect("Tblpfdfail");
	      myLogger.info("SQL ID FAIL BARU = "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();

	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	    	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));

	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }


	public static void edit_status_oldFail_Tblppkpermohonan(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";

	    try
	    {
	    	String ekptg_user_id = (String)data.get("ekptg_user_id");
	    	String id_permohonan = (String)data.get("id_permohonan");
	    	//int id_permohonan = (Integer)data.get("id_permohonan");
	    	//int idsuburusanstatusfail = (Integer)data.get("idsuburusanstatusfail");
			int id_status = 169; /* STATUS: BATAL (PINDAH PTG/KPTG) */

		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", id_permohonan);

		    r.add("id_status", 169);
		    r.add("id_kemaskini",ekptg_user_id);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));

		    sql = r.getSQLUpdate("tblppkpermohonan");
		    myLogger.info("SQL EDIT STATUS TBLPPKPERMOHONAN FAIL PINDAH LAMA = "+sql);
		    stmt.executeUpdate(sql);

	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}
    	}

	public static void edit_status_oldFail_Tblppkpermohonan17(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";

	    try
	    {
	    	String ekptg_user_id = (String)data.get("ekptg_user_id");
	    	String id_permohonan = (String)data.get("id_permohonan");
	    	//int id_permohonan = (Integer)data.get("id_permohonan");
	    	//int idsuburusanstatusfail = (Integer)data.get("idsuburusanstatusfail");
			//int id_status = 169; /* STATUS: BATAL (PINDAH PTG/KPTG) */
			//int seksyen = 17;

		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", id_permohonan);

		    r.add("id_status", 169);
		    r.add("seksyen", 17);
		    r.add("id_kemaskini",ekptg_user_id);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));

		    sql = r.getSQLUpdate("tblppkpermohonan");
		    myLogger.info("SQL EDIT STATUS TBLPPKPERMOHONAN FAIL PINDAH LAMA SEKSYEN 17 = "+sql);
		    stmt.executeUpdate(sql);

	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}
    	}



	public static void edit_status_oldFail_Tblrujsuburusanstatusfail(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";

	    try
	    {
	    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");

	    	//int id_permohonan = (Integer)data.get("id_permohonan");
	    	String idsuburusanstatusfail = (String)data.get("idsuburusanstatusfail");
	    	String id_permohonan = (String)data.get("id_permohonan");
			String ekptg_user_id = (String)data.get("ekptg_user_id");
			String id_fail = (String)data.get("id_fail");

			db = new Db();


			//** update TBLRUJSUBURUSANSTATUSFAIL

			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();

			  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

			  r2.add("aktif",0);
			  r2.add("id_kemaskini",ekptg_user_id);
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));

			  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
			  stmt2.executeUpdate(sql2);
			  myLogger.info("SQL UPDATE TBLRUJSUBURUSANSTATUSFAIL===PINDAH FAIL --> "+sql2);


		  //** insert TBLRUJSUBURUSANSTATUSFAIL
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_suburusanstatusfail",id_suburusanstatusfail);
			r.add("id_permohonan",id_permohonan);
			r.add("id_suburusanstatus",533);
			r.add("aktif",1);
			r.add("id_fail",id_fail);
			r.add("id_masuk",ekptg_user_id);
			r.add("tarikh_masuk",r.unquote("sysdate"));
			r.add("id_kemaskini",ekptg_user_id);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));

			sql = r.getSQLInsert("tblrujsuburusanstatusfail");
			myLogger.info("SQL :: PINDAH FAIL LAMA = "+sql);
			stmt.executeUpdate(sql);


	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }


	public static void edit_status_oldFail_Tblrujsuburusanstatusfail17(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";

	    try
	    {
	    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");

	    	//int id_permohonan = (Integer)data.get("id_permohonan");
	    	String idsuburusanstatusfail = (String)data.get("idsuburusanstatusfail");
	    	String id_permohonan = (String)data.get("id_permohonan");
			String ekptg_user_id = (String)data.get("ekptg_user_id");
			String id_fail = (String)data.get("id_fail");

			db = new Db();


			//** update TBLRUJSUBURUSANSTATUSFAIL

			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();

			  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

			  r2.add("aktif",0);
			  r2.add("id_kemaskini",ekptg_user_id);
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));

			  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
			  stmt2.executeUpdate(sql2);
			  myLogger.info("SQL UPDATE TBLRUJSUBURUSANSTATUSFAIL PINDAH FAIL :: SEKSYEN 17 --> "+sql2);


		  //** insert TBLRUJSUBURUSANSTATUSFAIL
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_suburusanstatusfail",id_suburusanstatusfail);
			r.add("id_permohonan",id_permohonan);
			r.add("id_suburusanstatus",534);
			r.add("aktif",1);
			r.add("id_fail",id_fail);
			r.add("id_masuk",ekptg_user_id);
			r.add("tarikh_masuk",r.unquote("sysdate"));
			r.add("id_kemaskini",ekptg_user_id);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));

			sql = r.getSQLInsert("tblrujsuburusanstatusfail");
			myLogger.info("SQL :: PINDAH FAIL LAMA SEKSYEN 17 = "+sql);
			stmt.executeUpdate(sql);


	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }


	public static Vector getTblppkpermohonan(String id_permohonan) throws Exception {
	    Db db = null;

	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {

	      db = new Db();
	      Statement stmt = db.getStatement();

	      //SYARAT
	      sql = "SELECT A.ID_PERMOHONAN, A.ID_FAIL, A.NO_PERMOHONAN, A.NO_PERSERAHAN, A.TARIKH_MOHON, ";
	      sql += "A.BIL_BICARA, A.JUMLAH_HTA_TARIKHMOHON, A.JUMLAH_HTA_TARIKHMATI, A.JUMLAH_HA_TARIKHMOHON, ";
	      sql += "A.JUMLAH_HA_TARIKHMATI, A.JUMLAH_HARTA_TARIKHMOHON, A.JUMLAH_HARTA_TARIKHMATI, A.BIDANG_KUASA, ";
	      sql += "A.FLAG_JENIS_BORANGC, A.KEPUTUSAN, A.CATATAN, A.ID_BUKTIMATI, A.ID_TARAFKPTG, A.ID_NEGERIMHN, ";
	      sql += "A.ID_STATUS, A.SEKSYEN, A.ID_UNITPSKAWAL, A.ID_NEGERIAWAL, A.ID_DAERAHAWAL, A.ID_PEJAWAL, A.NO_FAIL_AWAL, ";
	      sql += "A.BATAL_KUASA_PENTADBIR, A.LANTIK_PENTADBIR, A.BATAL_P_AMANAH, A.LANTIK_P_AMANAH, A.HARTA_TINGGAL, A.NAMA_PEMOHON_AWAL, ";
	      sql += "A.FLAG_STATUS_PEGUAM, A.JENIS_FAIL, A.NILAI_TERDAHULU, A.FLAG_JENIS_PERMOHONAN, A.NO_PERMOHONAN_ONLINE, ";
	      sql += "A.ID_MASUK, A.TARIKH_MASUK, A.ID_KEMASKINI, A.TARIKH_KEMASKINI ";
	      sql += "FROM TBLPPKPERMOHONAN A ";
	      sql += "WHERE A.ID_PERMOHONAN = " + id_permohonan + " ";

	      ResultSet rs = stmt.executeQuery(sql);
	      myLogger.info("SQL TBLPPKPERMOHONAN = "+sql);
	      int bil = 1;
	      Vector v = new Vector();

	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	    	h.put("id_fail", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
	    	h.put("no_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
	    	h.put("no_permohonan", rs.getString("NO_PERMOHONAN")==null?"":rs.getString("NO_PERMOHONAN"));
	    	h.put("no_perserahan", rs.getString("NO_PERSERAHAN")==null?"":rs.getString("NO_PERSERAHAN"));
	    	h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
	    	h.put("bil_bicara", rs.getString("BIL_BICARA")==null?"":rs.getString("BIL_BICARA"));
	    	h.put("jumlah_hta_tarikhmohon", rs.getString("JUMLAH_HTA_TARIKHMOHON")==null?"":rs.getString("JUMLAH_HTA_TARIKHMOHON"));
	    	h.put("jumlah_hta_tarikhmati", rs.getString("JUMLAH_HTA_TARIKHMATI")==null?"":rs.getString("JUMLAH_HTA_TARIKHMATI"));
	    	h.put("jumlah_ha_tarikhmohon", rs.getString("JUMLAH_HA_TARIKHMOHON")==null?"":rs.getString("JUMLAH_HA_TARIKHMOHON"));
	    	h.put("jumlah_ha_tarikhmati", rs.getString("JUMLAH_HA_TARIKHMATI")==null?"":rs.getString("JUMLAH_HA_TARIKHMATI"));
	    	h.put("jumlah_harta_tarikhmohon", rs.getString("JUMLAH_HARTA_TARIKHMOHON")==null?"":rs.getString("JUMLAH_HARTA_TARIKHMOHON"));
	    	h.put("jumlah_harta_tarikhmati", rs.getString("JUMLAH_HARTA_TARIKHMATI")==null?"":rs.getString("JUMLAH_HARTA_TARIKHMATI"));
	    	h.put("bidang_kuasa", rs.getString("BIDANG_KUASA")==null?"":rs.getString("BIDANG_KUASA"));
	    	h.put("flag_jenis_borangc", rs.getString("FLAG_JENIS_BORANGC")==null?"":rs.getString("FLAG_JENIS_BORANGC"));
	    	h.put("keputusan", rs.getString("KEPUTUSAN")==null?"":rs.getString("KEPUTUSAN"));
	    	h.put("catatan", rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
	    	h.put("id_buktimati", rs.getString("ID_BUKTIMATI")==null?"":rs.getString("ID_BUKTIMATI"));
	    	h.put("id_tarafkptg", rs.getString("ID_TARAFKPTG")==null?"":rs.getString("ID_TARAFKPTG"));
	    	h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
	    	h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
	    	h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
	    	h.put("id_unitpskawal", rs.getString("ID_UNITPSKAWAL")==null?"":rs.getString("ID_UNITPSKAWAL"));
	    	h.put("id_negeriawal", rs.getString("ID_NEGERIAWAL")==null?"":rs.getString("ID_NEGERIAWAL"));
	    	h.put("id_daerahawal", rs.getString("ID_DAERAHAWAL")==null?"":rs.getString("ID_DAERAHAWAL"));
	    	h.put("id_pejawal", rs.getString("ID_PEJAWAL")==null?"":rs.getString("ID_PEJAWAL"));
	    	h.put("no_fail_awal", rs.getString("NO_FAIL_AWAL")==null?"":rs.getString("NO_FAIL_AWAL"));
	    	h.put("batal_kuasa_pentadbir", rs.getString("BATAL_KUASA_PENTADBIR")==null?"":rs.getString("BATAL_KUASA_PENTADBIR"));
	    	h.put("lantik_pentadbir", rs.getString("LANTIK_PENTADBIR")==null?"":rs.getString("LANTIK_PENTADBIR"));
	    	h.put("batal_p_amanah", rs.getString("BATAL_P_AMANAH")==null?"":rs.getString("BATAL_P_AMANAH"));
	    	h.put("lantik_p_amanah", rs.getString("LANTIK_P_AMANAH")==null?"":rs.getString("LANTIK_P_AMANAH"));
	    	h.put("harta_tinggal", rs.getString("HARTA_TINGGAL")==null?"":rs.getString("HARTA_TINGGAL"));
	    	h.put("nama_pemohon_awal", rs.getString("NAMA_PEMOHON_AWAL")==null?"":rs.getString("NAMA_PEMOHON_AWAL"));
	    	h.put("flag_status_peguam", rs.getString("FLAG_STATUS_PEGUAM")==null?"":rs.getString("FLAG_STATUS_PEGUAM"));
	    	h.put("jenis_fail", rs.getString("JENIS_FAIL")==null?"":rs.getString("JENIS_FAIL"));
	    	h.put("nilai_terdahulu", rs.getString("NILAI_TERDAHULU")==null?"":rs.getString("NILAI_TERDAHULU"));
	    	h.put("flag_jenis_permohonan", rs.getString("FLAG_JENIS_PERMOHONAN")==null?"":rs.getString("FLAG_JENIS_PERMOHONAN"));
	    	h.put("no_permohonan_online", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));

	    	v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

//	public  void addPermohonan(Hashtable data) throws Exception {
//		Db db = null;
//		String sql = "";
//		String sql1 = "";
//		String sql2 = "";
//		String sql3 = "";
//		String sql4 = "";
//		String sql5 = "";
//		String sql8 = "";
//		String sqlbayaran = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			try
//			{
//				long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
//				long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
//				long idsimati = DB.getNextID("TBLPPKSIMATI_SEQ");
//				long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
//
//				long idPermohonan = Long.parseLong((String)data.get("IdPermohonan"));
//
//
//				myLogger.info("IDPPPPP"+idPermohonan);
//				long idSubUrusanStatus = DB.getNextID("TBLRUJSUBURUSANSTATUS_SEQ");
//
//				int UserIdPejabat = Integer.parseInt((String)data.get("userIdPejabat"));
//				String userIdNeg = (String)data.get("userIdNeg");
//				String userId = (String)data.get("userId");
//				String NegId = (String)data.get("negId");
//				String id_Fail = (String)data.get("id_Fail");
//
//
//
//				String no_daerah = (String)data.get("no_daerah");
//				myLogger.info("IDPPPPP Daerah!!!!"+no_daerah);
//
//				int id_d = Integer.parseInt(no_daerah);
//
//				Vector vd = new Vector();
//				vd=getListnegeriByDaerah(id_d);
//				int idneg = 0;
//
//				Hashtable l = (Hashtable)vd.get(0);
//				idneg = Integer.parseInt(l.get("id_Negeri").toString());
//
//
//
//
//				String negeri = (String)data.get("negeri");
//				String no_kpbaru_pemohon = (String)data.get("no_kpbaru_pemohon");
//				String no_kplama_pemohon = (String)data.get("no_kplama_pemohon");
//				String nama_simati = (String)data.get("nama_simati");
//				String tarikh_masuk = (String)data.get("tarikh_masuk");
//				String no_kpbaru_simati = (String)data.get("no_kp_baru");
//				String no_kplama_simati = (String)data.get("no_kplama_simati");
//				String sel_jeniskp_simati = (String)data.get("sel_jeniskp_simati");
//				String no_kplain_simati = (String)data.get("no_kplain_simati");
//
//			    String userIdKodDaerah = (String)data.get("userIdKodDaerah");
//
//			    myLogger.info("USER KOD DAERAH!!!::"+userIdKodDaerah);
//
//
//				String userIdKodNegeri = (String)data.get("userIdKodNegeri");
//				String tarikh_simati = (String)data.get("tarikh_simati");
//				String sel_jeniskp_pemohon = (String)data.get("sel_jeniskp_pemohon");
//				String no_kplain_pemohon = (String)data.get("no_kplain_pemohon");
//				String nama_pemohon = (String)data.get("nama_pemohon");
//				String alamat1 = (String)data.get("alamat1");
//				String alamat2 = (String)data.get("alamat2");
//				String alamat3 = (String)data.get("alamat3");
//				String bandar = (String)data.get("bandar");
//				String poskod = (String)data.get("poskod");
//
//				String buktimati = (String)data.get("buktimati");
//				String sijilmati = (String)data.get("sijilmati");
//
//				String txtUmurSimati = (String)data.get("txtUmurSimati");
//				String socJantinaSimati = (String)data.get("socJantinaSimati");
//				String txtUmurPemohon = (String)data.get("txtUmurPemohon");
//				String socJantinaPemohon = (String)data.get("socJantinaPemohon");
//
//
//				String txtbox = (String)data.get("txtbox");
//				String tarikhresit = (String)data.get("tarikhresit");
//
//
//
//				myLogger.info("sijilmatimodel::"+sijilmati);
//
//
//
//				java.util.Calendar calendar = java.util.Calendar.getInstance();
//				int getYear = calendar.get(java.util.Calendar.YEAR);
//
//				String X = String.format("%04d",File.getSeqNo(2,382,0,Integer.parseInt(userIdNeg),Integer.parseInt(no_daerah),false,false,getYear));
//
//				if (no_daerah.length() < 1){
//					no_daerah = "0"+no_daerah;
//				}else{
//					no_daerah = no_daerah;
//				}
//				if (userIdNeg.length() < 1){
//					userIdNeg = "0"+userIdNeg;
//				}else{
//					userIdNeg = userIdNeg;
//				}
//				if (negeri.equals("")){
//					negeri = "0";
//				}
//
//				 myLogger.info("USER KOD DAERAH MOHON!!!::"+no_daerah);
//
//				int nod=Integer.parseInt(no_daerah);
//
//				//FrmPrmhnnSek8InternalData.setDaerahbyID(nod);
//
//
//				db = new Db();
//				Statement stmt12 = db.getStatement();
//				SQLRenderer r12 = new SQLRenderer();
//
//
//				r12.add("d.id_negeri");
//				r12.add("d.id_daerah");
//				r12.add("d.nama_daerah");
//				r12.add("d.kod_daerah");
//				r12.add("n.kod_negeri");
//
//				r12.add("d.id_negeri",r12.unquote("n.id_Negeri"));
//				r12.add("d.id_daerah",nod);
//
//
//				String sql12 = r12.getSQLSelect("Tblrujdaerah d, Tblrujnegeri n");
//				ResultSet rs12 = stmt12.executeQuery(sql12);
//				//Vector list = new Vector(;
//				Hashtable h;
//				//int bil = 1;
//				String kod="";
//				String kodn="";
//				while (rs12.next()){
//					h = new Hashtable();
//					//h.put("bil", bil);
//					//h.put("id_Permohonan", rs.getString("id_Permohonan"));
//					//h.put("id_Hta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
//					h.put("id_negeri",rs12.getString("id_negeri")==null?"":rs12.getString("id_negeri"));
//					h.put("id_daerah",rs12.getString("id_daerah")==null?"":rs12.getString("id_daerah"));
//					h.put("nama_daerah",rs12.getString("nama_daerah")==null?"":rs12.getString("nama_daerah"));
//					h.put("kod_daerah",rs12.getString("kod_daerah")==null?"":rs12.getString("kod_daerah"));
//					h.put("kod_negeri",rs12.getString("kod_negeri")==null?"":rs12.getString("kod_negeri"));
//
//
//					//bil++;
//					kod=rs12.getString("kod_daerah");
//					kodn=rs12.getString("kod_negeri");
//				}
//
//
//			//	String getNoFile = "JKPTG/PK/"+ userIdKodNegeri + "/"+ userIdKodDaerah + "/"+X+"/"+getYear;
//				String getNoFile = "JKPTG/PK/"+ kodn + "/"+ kod + "/"+X+"/"+getYear;
//
//				myLogger.info("getNoFile:"+getNoFile);
//
//				tarikh_masuk = (String)data.get("tarikh_masuk");
//				tarikh_simati = (String)data.get("tarikh_simati");
//				String tarikh_mohon = "to_date('" + tarikh_masuk + "','dd/MM/yyyy')";
//		        String tarikh_mati = "to_date('" + tarikh_simati + "','dd/MM/yyyy')";
//		        int idNeg = Integer.parseInt(NegId);
//
//
//				db = new Db();
//				Statement stmtA = db.getStatement();
//				SQLRenderer r = new SQLRenderer();
//				r.add("id_fail",idFail);
//				r.add("id_seksyen",2);
//				r.add("id_urusan",382);
//				r.add("tarikh_daftar_fail",r.unquote("sysdate"));
//				r.add("tarikh_masuk",r.unquote("sysdate"));
//				r.add("tajuk_fail",getNoFile);
//				r.add("no_fail",getNoFile);
//				r.add("id_negeri",Integer.parseInt(userIdNeg));
//				r.add("id_suburusan",59);
//				r.add("flag_fail",1);
//				r.add("flag_jenis_fail",1);
//				r.add("id_masuk",userId);
//				sql = r.getSQLInsert("tblpfdfail");
//				stmtA.executeUpdate(sql);
//
//				 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//				    Date date = new Date();
//				    String currentDate = dateFormat.format(date);
//
//
//				db = new Db();
//				Statement stmt = db.getStatement();
//				SQLRenderer r1 = new SQLRenderer();
//				r1.add("id_simati",idsimati);
//				r1.add("nama_simati",nama_simati);
//				r1.add("no_kp_baru",no_kpbaru_simati);
//				r1.add("no_kp_lama",no_kplama_simati);
//				r1.add("jenis_kp",sel_jeniskp_simati);
//				r1.add("no_kp_lain",no_kplain_simati);
//
//				r1.add("no_Sijil_Mati",sijilmati);
//				r1.add("id_Buktimati",buktimati);
//
//
//
//				r1.add("tarikh_mati",r.unquote(tarikh_mati));
//		       // r1.add("id_permohonan",idPermohonan);
//				r1.add("id_masuk",userId);
//				r1.add("umur",txtUmurSimati);
//				r1.add("jantina",socJantinaSimati);
//
//				r1.add("tarikh_Masuk",r.unquote("sysdate"));
//				sql1 = r1.getSQLInsert("tblppksimati");
//				stmt.executeUpdate(sql1);
//
//
//
//
//				db = new Db();
//				Statement stmtc = db.getStatement();
//				SQLRenderer r2 = new SQLRenderer();
//				r2.add("id_pemohon",idPemohon);
//				r2.add("no_kp_baru",no_kpbaru_pemohon);
//				r2.add("no_kp_lama",no_kplama_pemohon);
//				r2.add("jenis_kp",sel_jeniskp_pemohon);
//				r2.add("no_kp_lain",no_kplain_pemohon);
//				r2.add("nama_pemohon",nama_pemohon);
//				/*
//				r2.add("alamat_1",alamat1);
//				r2.add("alamat_2",alamat2);
//				r2.add("alamat_3",alamat3);
//				r2.add("poskod",poskod);
//				r2.add("bandar",bandar);
//				r2.add("id_negeri",negeri);
//				*/
//
//
//				r2.add("alamat1_surat",alamat1);
//				r2.add("alamat2_surat",alamat2);
//				r2.add("alamat3_surat",alamat3);
//				r2.add("poskod_surat",poskod);
//				r2.add("bandar_surat",bandar);
//				r2.add("id_negerisurat",negeri);
//
//
//				r2.add("umur",txtUmurPemohon);
//				r2.add("jantina",socJantinaPemohon);
//				r2.add("id_permohonan",idPermohonan);
//				r2.add("id_masuk",userId);
//				r2.add("tarikh_Masuk",r.unquote("sysdate"));
//				sql2 = r2.getSQLInsert("tblppkpemohon");
//				stmtc.executeUpdate(sql2);
//
//				db = new Db();
//				Statement stmtd = db.getStatement();
//				SQLRenderer r3 = new SQLRenderer();
//				r3.add("id_permohonan",idPermohonan);
//				r3.add("id_daerahmhn",no_daerah);
//				r3.add("id_negerimhn",idneg);
//
//
//				r3.add("id_status",8);
//				r3.add("flag_Jenis_Permohonan",1);
//				r3.add("id_fail",idFail);
//				r3.add("seksyen",8);
//				r3.add("no_subjaket",0);
//				r3.add("tarikh_masuk",r.unquote("sysdate"));
//				r3.add("tarikh_mohon",r.unquote(tarikh_mohon));
//				r3.add("id_unitpskawal",UserIdPejabat);
//				r3.add("id_masuk",userId);
//				sql3 = r3.getSQLInsert("tblppkpermohonan");
//				stmtd.executeUpdate(sql3);
//
//
//
//
//
//
//
//			    //baru
//
//			    db = new Db();
//				Statement stmt8 = db.getStatement();
//				SQLRenderer r8 = new SQLRenderer();
//				r8.add("id_Simati",idsimati);
//				r8.add("id_Permohonan",idPermohonan);
//				r8.add("id_Masuk",userId);
//				r8.add("tarikh_Masuk",r.unquote("sysdate"));
//				sql8 = r8.getSQLInsert("tblppkpermohonansimati");
//				stmt8.executeUpdate(sql8);
//
//
//				db = new Db();
//				Statement stmtF = db.getStatement();
//				SQLRenderer r5 = new SQLRenderer();
//				r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
//				r5.add("ID_PERMOHONAN",idPermohonan);
//				r5.add("ID_SUBURUSANSTATUS",340);
//				r5.add("ID_FAIL",idFail);
//				r5.add("AKTIF",1);
//				r5.add("ID_MASUK",userId);
//				r5.add("TARIKH_MASUK",r.unquote("sysdate"));
//				r5.add("ID_KEMASKINI",userId);
//				r5.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
//				sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
//				stmtF.executeUpdate(sql5);
//
//
//
//				  db = new Db();
//			      Statement stmtL = db.getStatement();
//			      SQLRenderer r9 = new SQLRenderer();
//			      r9.add("id_bayaran", idBayaran);
//			      r9.add("id_permohonan", idPermohonan);
//			      r9.add("id_jenisbayaran",2);
//			      r9.add("no_resit",txtbox);
//			      r9.add("tarikh_bayaran",tarikhresit);
//			      r9.add("jumlah_bayaran",10);
//			      //r1.add("id_masuk",6);
//
//			      r9.add("ID_MASUK",userId);
//			      r9.add("ID_KEMASKINI",userId);
//				  r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
//			      r9.add("tarikh_masuk",r9.unquote("sysdate"));
//			      sqlbayaran = r9.getSQLInsert("tblppkbayaran");
//			      myLogger.info("sqlbayaran-->"+sqlbayaran);
//			      stmtL.executeUpdate(sqlbayaran);
//
//			}finally {
//				if(db != null) db.close();
//			}
//	}

	 public  Vector getListnegeriByDaerah(int id) throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("n.id_Negeri");
		      r.add("n.nama_Negeri");
		      r.add("n.kod_Negeri");
		      r.add("n.id_Negeri",r.unquote("d.id_Negeri"));

		      r.add("d.id_Daerah",id);


		      sql = r.getSQLSelect("Tblrujnegeri n, Tblrujdaerah d");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Negeri", rs.getInt("id_Negeri"));


		        if(rs.getString("nama_Negeri") == null){
		        	h.put("nama_Negeri", "");
		        }else{
		        	h.put("nama_Negeri", rs.getString("nama_Negeri"));
		        }
		        if(rs.getString("kod_Negeri") == null){
		        	h.put("kod_Negeri", "");
		        }else{
		        	h.put("kod_Negeri", rs.getString("kod_Negeri"));
		        }
		       /*
		        if(rs.getString("description") == null){
		        	h.put("description", "");
		        }else{
		        	h.put("description", rs.getString("description"));
		        }
		       */
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

	public static void addTblppkpermohonan(Hashtable data) throws Exception {

		    Db db = null;
		    String sql = "";

		    try
		    {

		    	long idPermohonan = DB.getNextID("TBLPPKPERMOHONAN_SEQ");
		    	String ekptg_user_id = (String)data.get("ekptg_user_id");

		    	  String txtNoFail = (String)data.get("txtNoFail");
		    	  String no_permohonan = (String)data.get("no_permohonan");
		    	  String no_perserahan = (String)data.get("no_perserahan");
				  String tarikh_mohon = (String)data.get("tarikh_mohon");
				  int bil_bicara = (Integer)data.get("bil_bicara");
				  int jumlah_hta_tarikhmohon = (Integer)data.get("jumlah_hta_tarikhmohon");
				  int jumlah_hta_tarikhmati = (Integer)data.get("jumlah_hta_tarikhmati");
				  int jumlah_ha_tarikhmohon = (Integer)data.get("jumlah_ha_tarikhmohon");
				  int jumlah_ha_tarikhmati = (Integer)data.get("jumlah_ha_tarikhmati");
				  int jumlah_harta_tarikhmohon = (Integer)data.get("jumlah_harta_tarikhmohon");
				  int jumlah_harta_tarikhmati = (Integer)data.get("jumlah_harta_tarikhmati");
				  String bidang_kuasa = (String)data.get("bidang_kuasa");
				  String flag_jenis_borangc = (String)data.get("flag_jenis_borangc");
				  String keputusan = (String)data.get("keputusan");
				  String catatan = (String)data.get("catatan");
				  int id_buktimati = (Integer)data.get("id_buktimati");
				  int id_tarafkptg = (Integer)data.get("id_tarafkptg");
				  int id_negerimhn = (Integer)data.get("id_negerimhn");
				  int id_daerahmhn = (Integer)data.get("id_daerahmhn");
				  int id_status = (Integer)data.get("id_status");
				  String seksyen = (String)data.get("seksyen");
				  int id_unitpskawal = (Integer)data.get("id_unitpskawal");
				  int id_negeriawal = (Integer)data.get("id_negeriawal");
				  int id_daerahawal = (Integer)data.get("id_daerahawal");
				  int id_pejawal = (Integer)data.get("id_pejawal");
				  String no_fail_awal = (String)data.get("no_fail_awal");
				  String batal_kuasa_pentadbir = (String)data.get("batal_kuasa_pentadbir");
				  String lantik_pentadbir = (String)data.get("lantik_pentadbir");
				  String batal_p_amanah = (String)data.get("batal_p_amanah");
				  String lantik_p_amanah = (String)data.get("lantik_p_amanah");
				  String harta_tinggal = (String)data.get("harta_tinggal");
				  String nama_pemohon_awal = (String)data.get("nama_pemohon_awal");
				  String flag_status_peguam = (String)data.get("flag_status_peguam");
				  String jenis_fail = (String)data.get("jenis_fail");
				  int nilai_terdahulu = (Integer)data.get("nilai_terdahulu");
				  String flag_jenis_permohonan = (String)data.get("flag_jenis_permohonan");
				  String no_permohonan_online = (String)data.get("no_permohonan_online");

				db = new Db();
				Statement stmtA = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.add("id_permohonan",idPermohonan);
				r.add("id_fail","");
				r.add("no_permohonan",no_permohonan);
				r.add("tarikh_mohon",tarikh_mohon);
				r.add("bil_bicara",bil_bicara);
				r.add("jumlah_hta_tarikhmohon",jumlah_hta_tarikhmohon);
				r.add("jumlah_hta_tarikhmati",jumlah_hta_tarikhmati);
				r.add("jumlah_ha_tarikhmohon",jumlah_ha_tarikhmohon);
				r.add("jumlah_ha_tarikhmati",jumlah_ha_tarikhmati);
				r.add("jumlah_harta_tarikhmohon",jumlah_harta_tarikhmohon);
				r.add("jumlah_harta_tarikhmati",jumlah_harta_tarikhmati);
				r.add("bidang_kuasa",bidang_kuasa);
				r.add("flag_jenis_borangc",flag_jenis_borangc);
				r.add("keputusan",keputusan);
				r.add("catatan",catatan);
				r.add("id_buktimati",id_buktimati);
				r.add("id_tarafkptg",id_tarafkptg);
				r.add("id_negerimhn",id_negerimhn);
				r.add("id_daerahmhn",id_daerahmhn);
				r.add("id_status",id_status);
				r.add("seksyen",seksyen);
				r.add("id_unitpskawal",id_unitpskawal);
				r.add("id_negeriawal",id_negeriawal);
				r.add("id_daerahawal",id_daerahawal);
				r.add("id_pejawal",id_pejawal);
				r.add("no_fail_awal",txtNoFail);						//* cater old no fail
				r.add("batal_kuasa_pentadbir",batal_kuasa_pentadbir);
				r.add("lantik_pentadbir",lantik_pentadbir);
				r.add("batal_p_amanah",batal_p_amanah);
				r.add("lantik_p_amanah",lantik_p_amanah);
				r.add("harta_tinggal",harta_tinggal);
				r.add("nama_pemohon_awal",nama_pemohon_awal);
				r.add("flag_status_peguam",flag_status_peguam);
				r.add("jenis_fail",jenis_fail);
				r.add("nilai_terdahulu",nilai_terdahulu);
				r.add("flag_jenis_permohonan",flag_jenis_permohonan);
				r.add("no_permohonan_online",no_permohonan_online);
				r.add("id_masuk",ekptg_user_id);
				r.add("tarikh_masuk",r.unquote("sysdate"));

				sql = r.getSQLInsert("tblppkpermohonan");
				stmtA.executeUpdate(sql);
				myLogger.info("SQL GENERATE NEW FILE = "+ sql);

		    }
		    finally {
		      if (db != null) db.close();
		    }
		}


//	public static Vector getTblppkpermohonansimati(String id_permohonan) throws Exception {
//	    Db db = null;
//
//	    String sql = "";
//	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//	    try {
//
//	      db = new Db();
//	      Statement stmt = db.getStatement();
//
//	      //SYARAT
//	      sql = "SELECT A.ID_PERMOHONANSIMATI, A.ID_PERMOHONAN, A.ID_SIMATI ";
//	      sql += "FROM TBLPPKPERMOHONANSIMATI A ";
//	      sql += "WHERE A.ID_PERMOHONAN = " + id_permohonan + " ";
//
//	      ResultSet rs = stmt.executeQuery(sql);
//	      myLogger.info("SQL TBLPPKPERMOHONANSIMATI = "+sql);
//	      int bil = 1;
//	      Vector v = new Vector();
//
//	      while (rs.next()) {
//	        Hashtable h = new Hashtable();
//	    	h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
//	    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
//	    	h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
//
//	    	v.addElement(h);
//	      }
//	      return v;
//	    }
//	    finally {
//	      if (db != null) db.close();
//	    }
//	  }


	public boolean checkStatusIDtunggu(String id_status) throws Exception{
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_STATUS = 153 ";

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


	 public static Hashtable getListStatusID(String id_permohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("fs.id_suburusanstatusfail");
		      r.add("p.id_Permohonan");
		      r.add("fs.aktif");
		      r.add("p.id_status");

		      r.add("fs.id_permohonan",r.unquote("p.id_permohonan"));
		      r.add("fs.aktif",r.unquote("1"));

		      r.add("p.id_Permohonan",id_permohonan);

		      sql = r.getSQLSelect("Tblppkpermohonan p, Tblrujsuburusanstatusfail fs");
		      myLogger.info("SQL status id = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
			    	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail")==null?"":rs.getString("id_suburusanstatusfail"));
			    	h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			    	h.put("aktif", rs.getString("aktif")==null?"":rs.getString("aktif"));
			    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		      }
		      return h;

		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		public boolean checkStatusIDbatal(String id_status) throws Exception{
			Db db = null;
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = " SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_STATUS = 169 ";

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

//		public static Vector getTblpfdfail(String id_fail) throws Exception {
//		    Db db = null;
//		    String sql = "";
//		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		    try {
//
//		      db = new Db();
//		      Statement stmt = db.getStatement();
//
//		      //SYARAT
//		      sql =  "SELECT A.ID_TARAFKESELAMATAN, A.ID_SEKSYEN, A.ID_URUSAN, A.ID_SUBURUSAN, A.TARIKH_DAFTAR_FAIL, A.TAJUK_FAIL, A.NO_FAIL, A.NO_FAIL_ROOT, ";
//		      sql += "A.ID_LOKASIFAIL, A.ID_NEGERI, A.ID_KEMENTERIAN, A.ID_FAHARASAT, A.FLAG_FAIL, A.ID_STATUS, A.CATATAN, A.TARIKH_TUKAR_TARAF, A.NO_PERSERAHAN, A.FLAG_JENIS_FAIL ";
//		      sql += "FROM TBLPFDFAIL A ";
//		      sql += "WHERE A.ID_FAIL = " + id_fail + " ";
//
//		      ResultSet rs = stmt.executeQuery(sql);
//		      myLogger.info("SQL TBLPFDFAIL = "+sql);
//		      int bil = 1;
//		      Vector v = new Vector();
//
//		      while (rs.next()) {
//		        Hashtable h = new Hashtable();
//		    	h.put("id_tarafkeselamatan", rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
//		    	h.put("id_seksyen", rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
//		    	h.put("id_urusan", rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
//		    	h.put("id_suburusan", rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
//		    	h.put("tarikh_daftar_fail", rs.getString("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
//		    	h.put("tajuk_fail", rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
//		    	h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
//		    	h.put("no_fail_root", rs.getString("NO_FAIL_ROOT")==null?"":rs.getString("NO_FAIL_ROOT"));
//		    	h.put("id_lokasifail", rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
//		    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
//		    	h.put("id_kementerian", rs.getString("ID_KEMENTERIAN")==null?"":rs.getString("ID_KEMENTERIAN"));
//		    	h.put("id_faharasat", rs.getString("ID_FAHARASAT")==null?"":rs.getString("ID_FAHARASAT"));
//		    	h.put("flag_fail", rs.getString("FLAG_FAIL")==null?"":rs.getString("FLAG_FAIL"));
//		    	h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
//		    	h.put("catatan", rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
//		    	h.put("tarikh_tukar_taraf", rs.getString("TARIKH_TUKAR_TARAF")==null?"":Format.format(rs.getDate("TARIKH_TUKAR_TARAF")));
//		    	h.put("no_perserahan", rs.getString("NO_PERSERAHAN")==null?"":rs.getString("NO_PERSERAHAN"));
//		    	h.put("flag_jenis_fail", rs.getString("FLAG_JENIS_FAIL")==null?"":rs.getString("FLAG_JENIS_FAIL"));
//
//		    	v.addElement(h);
//		      }
//		      return v;
//		    }
//		    finally {
//		      if (db != null) db.close();
//		    }
//		  }
//
//
//		public static Vector getTblppkbayaran(String id_permohonan) throws Exception {
//		    Db db = null;
//		    String sql = "";
//		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		    try {
//
//		      db = new Db();
//		      Statement stmt = db.getStatement();
//
//		      //SYARAT
//		      sql =  "SELECT A.ID_BAYARAN, A.ID_PERMOHONAN, A.ID_JENISBAYARAN, A.NO_RESIT, A.TARIKH_BAYARAN, A.JUMLAH_BAYARAN, ";
//		      sql += "FROM TBLPPKBAYARAN A ";
//		      sql += "WHERE A.ID_PERMOHONAN = " + id_permohonan + " ";
//
//
//		      ResultSet rs = stmt.executeQuery(sql);
//		      myLogger.info("SQL TBLPPKBAYARAN = "+sql);
//		      int bil = 1;
//		      Vector v = new Vector();
//
//		      while (rs.next()) {
//		        Hashtable h = new Hashtable();
//		    	h.put("id_bayaran", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
//		    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
//		    	h.put("id_jenisbayaran", rs.getString("ID_JENISBAYARAN")==null?"":rs.getString("ID_JENISBAYARAN"));
//		    	h.put("no_resit", rs.getString("NO_RESIT")==null?"":rs.getString("NO_RESIT"));
//		    	h.put("tarikh_bayaran", rs.getString("TARIKH_BAYARAN")==null?"":Format.format(rs.getDate("TARIKH_BAYARAN")));
//		    	h.put("jumlah_bayaran", rs.getString("JUMLAH_BAYARAN")==null?"":rs.getString("JUMLAH_BAYARAN"));
//
//		    	v.addElement(h);
//		      }
//		      return v;
//		    }
//		    finally {
//		      if (db != null) db.close();
//		    }
//		  }
//
//
//		public static Vector getTblppkha(String id_permohonansimati) throws Exception {
//		    Db db = null;
//		    String sql = "";
//		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		    try {
//
//		      db = new Db();
//		      Statement stmt = db.getStatement();
//
//		      //SYARAT
//		      sql =  "SELECT A.ID_HA, A.BIL, A.ID_SIMATI, A.ID_JENISHA, A.ID_NEGERI, A.ID_DAERAH, A.JENAMA, A.NO_DAFTAR, A.NO_SIJIL, A.BIL_UNIT, A.TARIKH_HARTA, ALAMAT_HA1, ALAMAT_HA2, ALAMAT_HA3, POSKOD,  ";
//		      sql += "A.NILAI_HA_TARIKHMOHON, A.NILAI_HA_TARIKHMATI, A.BA_SIMATI, A.BB_SIMATI, A.CATATAN, A.NILAI_SEUNIT, A.ID_PERMOHONANSIMATI  ";
//		      sql += "FROM TBLPPKHA A ";
//		      sql += "WHERE A.ID_PERMOHONANSIMATI = " + id_permohonansimati + " ";
//
//		      ResultSet rs = stmt.executeQuery(sql);
//		      myLogger.info("SQL TBLPPKHA = "+sql);
//		      int bil = 1;
//		      Vector v = new Vector();
//
//		      while (rs.next()) {
//		        Hashtable h = new Hashtable();
//		    	h.put("id_ha", rs.getString("ID_HA")==null?"":rs.getString("ID_HA"));
//		    	h.put("bil", rs.getString("BIL")==null?"":rs.getString("BIL"));
//		    	h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
//		    	h.put("id_jenisha", rs.getString("ID_JENISHA")==null?"":rs.getString("ID_JENISHA"));
//		    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
//		    	h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":Format.format(rs.getDate("ID_DAERAH")));
//		    	h.put("jenama", rs.getString("JENAMA")==null?"":rs.getString("JENAMA"));
//		    	h.put("no_daftar", rs.getString("NO_DAFTAR")==null?"":rs.getString("NO_DAFTAR"));
//		    	h.put("no_sijil", rs.getString("NO_SIJIL")==null?"":rs.getString("NO_SIJIL"));
//		    	h.put("bil_unit", rs.getString("BIL_UNIT")==null?"":rs.getString("BIL_UNIT"));
//		    	h.put("tarikh_harta", rs.getString("TARIKH_HARTA")==null?"":Format.format(rs.getDate("TARIKH_HARTA")));
//		    	h.put("alamat_ha1", rs.getString("ALAMAT_HA1")==null?"":rs.getString("ALAMAT_HA1"));
//		    	h.put("alamat_ha1", rs.getString("ALAMAT_HA2")==null?"":rs.getString("ALAMAT_HA2"));
//		    	h.put("alamat_ha3", rs.getString("ALAMAT_HA3")==null?"":rs.getString("ALAMAT_HA3"));
//		    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
//		    	h.put("nilai_ha_tarikhmohon", rs.getString("NILAI_HA_TARIKHMOHON")==null?"":Format.format(rs.getDate("NILAI_HA_TARIKHMOHON")));
//		    	h.put("nilai_ha_tarikhmati", rs.getString("NILAI_HA_TARIKHMATI")==null?"":Format.format(rs.getDate("NILAI_HA_TARIKHMATI")));
//		    	h.put("ba_simati", rs.getString("BA_SIMATI")==null?"":rs.getString("BA_SIMATI"));
//		    	h.put("bb_simati", rs.getString("BB_SIMATI")==null?"":rs.getString("BB_SIMATI"));
//		    	h.put("catatan", rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
//		    	h.put("nilai_seunit", rs.getString("NILAI_SEUNIT")==null?"":rs.getString("NILAI_SEUNIT"));
//		    	h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
//
//		    	v.addElement(h);
//		      }
//		      return v;
//		    }
//		    finally {
//		      if (db != null) db.close();
//		    }
//		  }

		private static Vector listSemak = new Vector();

		public static Vector getListSemak(){
			return listSemak;
		}

		public static void setListSemak(String id_permohonan, String usid)throws Exception {
			Db db = null;
			listSemak.clear();
			String sql = "";

			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql =  "SELECT distinct pm.id_negeri, n.id_Negeri, n.nama_Negeri,f.id_Fail, f.no_Fail, d.id_Daerah, p.id_Permohonan, ";
				sql += "p.tarikh_Mohon, s.no_Kp_Baru, s.no_Kp_Lama, s.jenis_Kp, s.no_Kp_Lain, s.id_Simati, ";
				sql += "s.nama_Simati, s.tarikh_Mati, pm.id_Pemohon, pm.nama_Pemohon, pm.no_kp_baru, ";
				sql += "pm.no_kP_lama, pm.jenis_Kp, pm.no_Kp_lain, pm.alamat_1, pm.alamat_2, pm.alamat_3, ";
				sql += "pm.poskod, pm.bandar, d.nama_Daerah, p.seksyen, st.keterangan, ";
				sql += "p.id_Status, u.nama_pejabat, mosi.id_Permohonansimati, s.umur, s.jantina, ";
				sql += "pm.umur, pm.jantina,u.id_pejabatjkptg, p.no_subjaket, sstf.id_suburusanstatusfail, mosi.id_permohonansimati ";
				sql += "FROM Tblpfdfail f,Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, ";
				sql += "Tblppkpemohon pm, Tblrujstatus st, tblrujsuburusanstatusfail sstf, tblrujsuburusanstatus sst, ";
				sql += "tblrujpejabatjkptg u, Tblppkpermohonansimati mosi, Users_Internal ur ";
				sql += "WHERE f.id_Negeri = n.id_Negeri(+) ";
				sql += "AND sstf.id_permohonan = p.id_permohonan ";
				sql += "AND sstf.id_suburusanstatus = sst.id_suburusanstatus ";
				sql += "AND sst.id_status = st.id_status ";
				sql += "AND p.id_Daerahmhn = d.id_Daerah(+) ";
				sql += "AND ur.user_id = "+usid ;
				sql += " And ur.ID_PEJABATJKPTG = u.ID_PEJABATJKPTG ";
				sql += "AND p.id_Fail = f.id_Fail ";
				sql += "AND pm.id_Pemohon = p.id_Pemohon ";
				sql += "AND s.id_Simati = mosi.id_Simati ";
				sql += "AND p.id_Permohonan = mosi.id_Permohonan ";
				//sql += "AND st.id_Status = p.id_Status ";
				sql += "AND d.id_daerah = p.id_daerahmhn ";
				sql += "AND sstf.aktif = 1 ";
				sql += " AND p.id_Permohonan = " +id_permohonan ;

				//myLogger.info("[checklist]:"+sql);
				ResultSet rs = stmt.executeQuery(sql);
			    Hashtable h;

			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
			    	  h.put("idFail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
			    	  h.put("noFail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
			    	  h.put("idDaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
			    	  h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			    	  h.put("tarikhMohon", Format.format(rs.getDate("tarikh_Mohon"))==null?"":Format.format(rs.getDate("tarikh_Mohon")));
			    	  h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			    	  h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
			    	  h.put("tarikhMati",  Format.format(rs.getDate("tarikh_Mati"))==null?"": Format.format(rs.getDate("tarikh_Mati")));
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
			    	  h.put("namaPejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    	  h.put("id_pejabatjkptg", rs.getString("id_pejabatjkptg"));
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
			    	  h.put("id_permohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
			    	  h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));

			    	  listSemak.addElement(h);
			      	}
				}
				finally{
					if(db != null)db.close();
				}
		}//close semak



		//* generate idFail Seksyen 8
		public static Hashtable generateIdFailp_backup(String ekptg_user_id,String id_fail,String id_permohonan,String kod_negeri,String kod_daerah,String txdTarikhPindah,String id_permohonansimati,String id_negeri,String id_daerah,String no_fail)
		throws Exception{
		    Db db = null;
		    Connection conn = null;
		    String getNoFile;

		    String sqlSelect = "";
		    String sqlInsert = "";
		    long idFailBaru;
		    long idPermohonanBaru;
		    Hashtable output = null;
		    try {
		    	  output = new Hashtable();
			      db = new Db();
		          conn = db.getConnection();
		          conn.setAutoCommit(false);
			      Statement stmt = db.getStatement();

			    idFailBaru = DB.getNextID(db,"TBLPFDFAIL_SEQ");
			    idPermohonanBaru = DB.getNextID(db,"TBLPPKPERMOHONAN_SEQ");
			    long idSemakanHantarBaru = DB.getNextID(db,"TBLSEMAKANHANTAR_SEQ");
			    long idPermohonanSimatiBaru = DB.getNextID(db,"TBLPPKPERMOHONANSIMATI_SEQ");
			    long idPemohonBaru =  DB.getNextID(db,"TBLPPKPEMOHON_SEQ");
			    long idHtaBaru = DB.getNextID(db,"TBLPPKHTA_SEQ");
			    long idHaBaru = DB.getNextID(db,"TBLPPKHA_SEQ");
			    long idPeguamPemohonBaru = DB.getNextID(db,"TBLPPKPEGUAMPEMOHON_SEQ");
			    long idKeputusanPermohonanBaru = DB.getNextID(db,"TBLPPKKEPUTUSANPERMOHONAN_SEQ");

			    /*
			    TBLPFDFAIL
				TBLPPKPERMOHONAN
				TBLPPKPEMOHON
				TBLPPKPEGUAM
				TBLPPKPEGUAMPEMOHON
				TBLPPKPERMOHONANSIMATI
				TBLPPKSIMATI
				TBLRUJSUBURUSANSTATUSFAIL
				TBLPPKBAYARAN
				TBLSEMAKANHANTAR
				TBLPPKHUBUNGANOBPERMOHONAN
				TBLPPKHUBUNGANOB
				TBLPPKOB
				TBLPPKOBPERMOHONAN
				TBLPPKPENGHUTANG
				TBLPPKHTA
				TBLPPKHTAPERMOHONAN
				TBLPPKHA
				TBLPPKHAPERMOHONAN
				TBLPPKKEPUTUSANPERMOHONAN  
			    */
			    
			    //TBLPFDFAIL	1

			    java.util.Calendar calendar = java.util.Calendar.getInstance();
				int getYear = calendar.get(java.util.Calendar.YEAR);

			    String X = String.format("%04d",File.getSeqNo(db,2,382,0,Integer.parseInt(id_negeri),Integer.parseInt(id_daerah),false,false,getYear,0));
			    getNoFile = "JKPTG/PK/"+ kod_negeri + "/"+ kod_daerah + "/"+X+"/"+getYear;

			    sqlInsert = "INSERT INTO TBLPFDFAIL ( "+
						   "ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN,  "+
						   "ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL,  "+
						   "TAJUK_FAIL, NO_FAIL, NO_FAIL_ROOT,  "+
						   "ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN,  "+
						   "ID_FAHARASAT, FLAG_FAIL, ID_STATUS,  "+
						   "CATATAN, ID_MASUK, TARIKH_MASUK,  "+
						   "ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF,  "+
						   "ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL ) ";

			    sqlSelect = "SELECT '"+idFailBaru+"' as ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN,"+
				   "ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, "+
				   "'"+getNoFile+"' as TAJUK_FAIL, '"+getNoFile+"' as NO_FAIL, NO_FAIL_ROOT, "+
				   "ID_LOKASIFAIL, '"+id_negeri+"' as ID_NEGERI, ID_KEMENTERIAN, "+
				   "ID_FAHARASAT, FLAG_FAIL, ID_STATUS, "+
				   "CATATAN, '"+ekptg_user_id+"' as ID_MASUK,sysdate as TARIKH_MASUK, "+
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, " +
				   "TARIKH_TUKAR_TARAF, "+
				   "ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL "+
				   "FROM TBLPFDFAIL where id_fail='"+id_fail+"' ";

			    myLogger.info("TBLPFDFAIL :: "+sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKPERMOHONAN	2

			    txdTarikhPindah = txdTarikhPindah ;
			    //Integer id_permohonan_baru = getidPermohonanBaru ;

			    //txdTarikhPindah = (String)data.get("txdTarikhPindah");
				String TR = "to_date('" + txdTarikhPindah + "','dd/MM/yyyy')";

				myLogger.info("ID NEGERI === "+id_negeri);
				myLogger.info("ID DAERAH === "+id_daerah);
				myLogger.info("NO FAIL LAMA === "+no_fail);

			    sqlInsert = "INSERT INTO TBLPPKPERMOHONAN ( "+
				   "ID_PERMOHONAN, ID_FAIL, NO_PERMOHONAN,  "+
				   "NO_PERSERAHAN, TARIKH_MOHON, BIL_BICARA,  "+
				   "JUMLAH_HTA_TARIKHMOHON, JUMLAH_HTA_TARIKHMATI, JUMLAH_HA_TARIKHMOHON,  "+
				   "JUMLAH_HARTA_TARIKHMATI, BIDANG_KUASA, FLAG_JENIS_BORANGC, "+
				   "KEPUTUSAN, CATATAN, ID_BUKTIMATI, ID_TARAFKPTG, "+
				   "ID_NEGERIMHN, ID_DAERAHMHN, ID_STATUS,  "+
				   "SEKSYEN, ID_UNITPSKAWAL, ID_NEGERIAWAL, "+
				   "ID_DAERAHAWAL, ID_PEJAWAL, NO_FAIL_AWAL, "+
				   "BATAL_KUASA_PENTADBIR, LANTIK_PENTADBIR, BATAL_P_AMANAH, "+
				   "LANTIK_P_AMANAH, HARTA_TINGGAL, NAMA_PEMOHON_AWAL, "+
				   "FLAG_JENIS_PERMOHONAN, NO_PERMOHONAN_ONLINE," +
				   "ID_MASUK,TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, "+
				   "FLAG_HUBUNGAN_PEMOHON, ID_HUBUNGANPEMOHON, TARIKH_MOHON_ONLINE, ID_DB, "+
				   "NO_SUBJAKET, ID_NEGERITERTINGGI, ID_DAERAHTERTINGGI,  " +
				   "ID_PERMOHONANTERDAHULU, ID_PEMOHON, PERINTAH_LAMA, PERINTAH_BARU, CATATAN_PERINTAH, FLAG_PERMOHONAN, "+
				   "LAIN_TUJUAN, TARIKH_RAYUAN, ID_ARB, JUM_HARTA_TAMBAHAN_TKHMOHON, JUM_HARTA_TAMBAHAN_TKHMATI, " +
				   "JUM_HTA_TAMBAHAN_TKHMOHON, JUM_HTA_TAMBAHAN_TKHMATI, JUM_HA_TAMBAHAN_TKHMOHON, JUM_HA_TAMBAHAN_TKHMATI ) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as ID_PERMOHONAN, '"+idFailBaru+"' as ID_FAIL, NO_PERMOHONAN,"+
				   "NO_PERSERAHAN, "+TR+" as TARIKH_MOHON, BIL_BICARA, "+
				   "JUMLAH_HTA_TARIKHMOHON, JUMLAH_HTA_TARIKHMATI, JUMLAH_HA_TARIKHMOHON, "+
				   "JUMLAH_HARTA_TARIKHMATI, BIDANG_KUASA, FLAG_JENIS_BORANGC,  "+
				   "KEPUTUSAN, CATATAN, ID_BUKTIMATI, ID_TARAFKPTG, "+
				   "'"+id_negeri+"' as ID_NEGERIMHN,'"+id_daerah+"' as ID_DAERAHMHN, ID_STATUS,  "+
				   "SEKSYEN, ID_UNITPSKAWAL, ID_NEGERIAWAL, "+
				   "ID_DAERAHAWAL, ID_PEJAWAL, '"+no_fail+"' as NO_FAIL_AWAL, "+
				   "BATAL_KUASA_PENTADBIR, LANTIK_PENTADBIR, BATAL_P_AMANAH, "+
				   "LANTIK_P_AMANAH, HARTA_TINGGAL, NAMA_PEMOHON_AWAL, "+
				   "FLAG_JENIS_PERMOHONAN, NO_PERMOHONAN_ONLINE, " +
				   "'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, "+
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, " +
				   "FLAG_HUBUNGAN_PEMOHON, ID_HUBUNGANPEMOHON, TARIKH_MOHON_ONLINE, ID_DB, " +
				   "NO_SUBJAKET, ID_NEGERITERTINGGI, ID_DAERAHTERTINGGI,  " +
				   "ID_PERMOHONANTERDAHULU, ID_PEMOHON, PERINTAH_LAMA, PERINTAH_BARU, CATATAN_PERINTAH, FLAG_PERMOHONAN," +
				   "LAIN_TUJUAN, TARIKH_RAYUAN, ID_ARB, JUM_HARTA_TAMBAHAN_TKHMOHON, JUM_HARTA_TAMBAHAN_TKHMATI, "+
				   "JUM_HTA_TAMBAHAN_TKHMOHON, JUM_HTA_TAMBAHAN_TKHMATI, JUM_HA_TAMBAHAN_TKHMOHON, JUM_HA_TAMBAHAN_TKHMATI "+
				   "FROM TBLPPKPERMOHONAN where id_permohonan='"+id_permohonan+"' ";

			    myLogger.info(sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLSEMAKANHANTAR	3

			    sqlInsert = "INSERT INTO TBLSEMAKANHANTAR ( "+
				   "ID_SEMAKANSENARAI, ID_PERMOHONAN,  "+
				   "PEMOHON, PENTADBIR, STATUS, CATATAN, TARIKH_PELBAGAI, " +
				   "ID_DOKUMEN, FLAG_ADA, ID_MASUK, TARIKH_MASUK, " +
				   "ID_KEMASKINI, TARIKH_KEMASKINI ) ";

			    sqlSelect = "SELECT ID_SEMAKANSENARAI, '"+idPermohonanBaru+"' as ID_PERMOHONAN,"+
				   "PEMOHON, PENTADBIR, STATUS,CATATAN, TARIKH_PELBAGAI, " +
				   "ID_DOKUMEN, FLAG_ADA, '"+ekptg_user_id+"' as ID_MASUK,sysdate as TARIKH_MASUK, "+
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI " +
				   "FROM TBLSEMAKANHANTAR where id_permohonan='"+id_permohonan+"' ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKPERMOHONANSIMATI	4

			    sqlInsert = "INSERT INTO TBLPPKPERMOHONANSIMATI ( "+
				   "ID_PERMOHONANSIMATI,ID_PERMOHONAN, ID_SIMATI, "+
				   "ID_MASUK, TARIKH_MASUK, " +
				   "ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB) ";

			    sqlSelect = "SELECT "+idPermohonanSimatiBaru+" as ID_PERMOHONANSIMATI," +
			      " '"+idPermohonanBaru+"' as ID_PERMOHONAN, ID_SIMATI, " +
			       "'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, "+
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, ID_DB "+
				   "FROM TBLPPKPERMOHONANSIMATI where id_permohonan='"+id_permohonan+"'";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLRUJSUBURUSANSTATUSFAIL	5

			    sqlInsert = "INSERT INTO TBLRUJSUBURUSANSTATUSFAIL ( "+
				   "ID_PERMOHONAN, ID_SUBURUSANSTATUS,  "+
				   "URL, AKTIF,ID_MASUK, TARIKH_MASUK,  "+
				   "ID_KEMASKINI, TARIKH_KEMASKINI, ID_FAIL) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as ID_PERMOHONAN, ID_SUBURUSANSTATUS, " +
				   "URL, '0' as AKTIF, "+
				   "'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, " +
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, '"+idFailBaru+"' as ID_FAIL "+
				   "FROM TBLRUJSUBURUSANSTATUSFAIL where id_permohonan='"+id_permohonan+"'";


			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKBAYARAN	6

			    sqlInsert = "INSERT INTO TBLPPKBAYARAN ( "+
				   "ID_PERMOHONAN, ID_JENISBAYARAN,  "+
				   "NO_RESIT, TARIKH_BAYARAN, JUMLAH_BAYARAN," +
				   "ID_MASUK, TARIKH_MASUK, "+
				   "ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB ) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as ID_PERMOHONAN, ID_JENISBAYARAN, " +
				   "NO_RESIT, TARIKH_BAYARAN, JUMLAH_BAYARAN, "+
				   "'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, " +
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, ID_DB "+
				   "FROM TBLPPKBAYARAN where id_permohonan='"+id_permohonan+"'";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			   //TBLPPKPEMOHON	7

			    sqlInsert = " INSERT INTO TBLPPKPEMOHON ( "+
			    			" ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, "+
			    			" NO_KP_LAIN, UMUR, JANTINA, JENIS_AGAMA, JENIS_WARGA, "+
			    			" ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, ID_BANDAR, "+
			    			" POSKOD, NO_HP, NO_TEL, EMEL, NO_FAX, "+
			    			" CATATAN, ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI, STATUS_PEGUAM, "+
			    			" STATUS_PEMOHON, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, "+
			    			" POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, "+
			    			" ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, "+
			    			" ID_PEMOHONLAMA, ID_ARB )";


			    sqlSelect = " SELECT '"+idPemohonBaru+"' AS ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU, "+
				   "  NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,UMUR, JANTINA,  "+
				   "  JENIS_AGAMA, JENIS_WARGA, ALAMAT_1, ALAMAT_2, ALAMAT_3,"+
				   "  BANDAR, ID_BANDAR, POSKOD, NO_HP, NO_TEL, EMEL, NO_FAX, CATATAN,"+
				   "  ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI, STATUS_PEGUAM, STATUS_PEMOHON, "+
				   "  ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, POSKOD_SURAT,"+
				   "  NO_HP_SURAT, NO_TEL_SURAT ,'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, " +
				   "  '"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, ID_DB, "+
				   "  BANDAR_SURAT, ID_NEGERISURAT, ID_PEMOHONLAMA, ID_ARB "+
				   "  FROM TBLPPKPEMOHON WHERE ID_PEMOHON IN ( "+
				   "  SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN ='"+id_permohonan+"' ) ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKOB	8

			    sqlInsert = "INSERT INTO TBLPPKOB ( "+
				   "ID_SIMATI, NAMA_OB,  "+
				   "NO_KP_BARU, NO_KP_LAMA, JENIS_KP,NO_KP_LAIN, " +
				   "NO_SURAT_BERANAK, TARIKH_LAHIR,JANTINA, UMUR, ALAMAT_1, " +
				   "ALAMAT_2, ALAMAT_3, BANDAR,ID_BANDAR, POSKOD, NO_HP, NO_TEL, " +
				   "CATATAN, STATUS_HIDUP,ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, " +
				   "ID_JENISPB, JENIS_AGAMA, JENIS_WARGA,ID_BANK, NO_AKAUN, " +
				   "TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB,  "+
				   "NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, " +
				   "JENIS_PEMIUTANG,ID_PEMOHON, ID_PERMOHONANSIMATI, ALAMAT1_SURAT, " +
				   "ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, " +
				   "NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
				   "TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT,ID_PERAYU ) ";


			    sqlSelect = "SELECT ID_SIMATI, NAMA_OB, "+
				   "NO_KP_BARU, NO_KP_LAMA, JENIS_KP,NO_KP_LAIN, " +
				   "NO_SURAT_BERANAK, TARIKH_LAHIR,JANTINA, UMUR, ALAMAT_1, " +
				   "ALAMAT_2, ALAMAT_3, BANDAR,ID_BANDAR, POSKOD, NO_HP, NO_TEL, " +
				   "CATATAN, STATUS_HIDUP,ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, " +
				   "ID_JENISPB, JENIS_AGAMA, JENIS_WARGA,ID_BANK, NO_AKAUN, " +
				   "TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB, "+
				   "NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, " +
				   "JENIS_PEMIUTANG,ID_PEMOHON,'"+idPermohonanSimatiBaru+"' as ID_PERMOHONANSIMATI, ALAMAT1_SURAT, " +
				   "ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, " +
				   "NO_HP_SURAT, NO_TEL_SURAT, '"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, '"+ekptg_user_id+"'  as ID_KEMASKINI, "+
				   "sysdate as TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT,ID_PERAYU "+
				   "FROM TBLPPKOB WHERE ID_PERMOHONANSIMATI IN ( "+
				   "SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKHUBUNGANOB	9

			    sqlInsert = "INSERT INTO TBLPPKHUBUNGANOB ( "+
				   "ID_OB, ID_PARENTOB, ID_SAUDARA, ID_MASUK, TARIKH_MASUK,  "+
				   "ID_KEMASKINI, TARIKH_KEMASKINI ) ";


			    sqlSelect = "SELECT ID_OB, ID_PARENTOB, ID_SAUDARA, '"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, "+
			    	"'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI "+
			    	"from TBLPPKHUBUNGANOB where ID_OB in (select ID_PERMOHONANSIMATI from TBLPPKPERMOHONANSIMATI where ID_PERMOHONAN = '"+id_permohonan+"') ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);



			    //TBLPPKPENGHUTANG 10
				//Azam change here...id permohonan simati
			    sqlInsert = "INSERT INTO TBLPPKPENGHUTANG ( "+
			    	"ID_SIMATI, NAMA_PENGHUTANG, NO_KP_BARU, NO_KP_LAMA,  "+
			    	"NO_KP_LAIN, ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, POSKOD, NO_AKAUN, NAMA_BANK, "+
			    	"JUMLAH_HUTANG, BUTIRAN_HUTANG, ID_NEGERI, JENIS_PENGHUTANG, JENIS_AGAMA, JENIS_WARGA,  "+
			    	"JENIS_KP, ID_PERMOHONANSIMATI, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI ) ";


			    sqlSelect = "SELECT id_simati, nama_penghutang, no_kp_baru, no_kp_lama, " +
			       "no_kp_lain, alamat_1, alamat_2, alamat_3, bandar, poskod, no_akaun, nama_bank, " +
			       "jumlah_hutang, butiran_hutang, id_negeri, jenis_penghutang,jenis_agama, jenis_warga, " +
			       "jenis_kp," +
			       //"id_permohonansimati, " +
			       "'"+idPermohonanSimatiBaru+"', " +
			       "'"+ekptg_user_id+"' as id_masuk, "+
			       "sysdate as tarikh_masuk, '"+ekptg_user_id+"' as id_kemaskini, sysdate as  tarikh_kemaskini "+
			       "FROM tblppkpenghutang where ID_PERMOHONANSIMATI IN "+
			       "(SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') ";

				    myLogger.info(sqlInsert+sqlSelect);
					stmt.executeUpdate(sqlInsert+sqlSelect);
					

			    //TBLPPKHTA 11
				//Azam change on 30/3/2010
				//id_permohonansimati menggunakan id yg baru
			    sqlInsert = "INSERT INTO TBLPPKHTA ( "+
		    	   "no_hakmilik, id_simati, no_pt, nilai_hta_tarikhmohon, "+
			       "nilai_hta_tarikhmati, id_kategori,id_jenishm, id_jenispb, id_negeri, "+
			       "id_daerah, id_mukim, id_luas,luas, luas_hmp, no_pajakan,  "+
			       "no_cagaran, jenis_tnh, alamat_hta1, alamat_hta2, alamat_hta3, bandar_hta, "+
			       "poskod_hta, tarikh_perjanjian, nama_pemaju, alamat_pemaju1, "+
			       "alamat_pemaju2, alamat_pemaju3, bandar_pemaju, poskod_pemaju, "+
			       " id_negeripemaju, catatan, ba_simati,bb_simati, no_bangunan, "+
			       "no_tingkat, no_petak, no_perjanjian, maklumat_tanah, "+
			       "no_strata, jenis_hta, status_pemilikan, tanggungan, no_perserahan, "+
			       "nama_rancangan, no_roh, no_lot_id, flag_kategori_hta, jenis_kepentingan, "+
			       "id_permohonansimati, id_masuk, "+
			       "tarikh_masuk, id_kemaskini, tarikh_kemaskini ) ";

			    sqlSelect = "SELECT no_hakmilik, id_simati, no_pt, nilai_hta_tarikhmohon, "+
			       "nilai_hta_tarikhmati, id_kategori,id_jenishm, id_jenispb, id_negeri, "+
			       "id_daerah, id_mukim, id_luas,luas, luas_hmp, no_pajakan,  "+
			       "no_cagaran, jenis_tnh, alamat_hta1, alamat_hta2, alamat_hta3, bandar_hta, "+
			       "poskod_hta, tarikh_perjanjian, nama_pemaju, alamat_pemaju1, "+
			       "alamat_pemaju2, alamat_pemaju3, bandar_pemaju, poskod_pemaju, "+
			       " id_negeripemaju, catatan, ba_simati,bb_simati, no_bangunan, "+
			       "no_tingkat, no_petak, no_perjanjian, maklumat_tanah, "+
			       "no_strata, jenis_hta, status_pemilikan, tanggungan, no_perserahan, "+
			       "nama_rancangan, no_roh, no_lot_id, flag_kategori_hta, jenis_kepentingan, "+
			       //"id_permohonansimati, " +
			       "'"+idPermohonanSimatiBaru+"',"+
			       "'"+ekptg_user_id+"' as id_masuk, "+
			       "sysdate as tarikh_masuk, '"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini "+
			       "from TBLPPKHTA a  WHERE ID_PERMOHONANSIMATI IN (  "+
			       "select ID_PERMOHONANSIMATI from TBLPPKPERMOHONANSIMATI where id_permohonan = '"+id_permohonan+"' ) ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);



			    //TBLPPKHA 		12

			    sqlInsert = "INSERT INTO TBLPPKHA ( "+
		    		"bil, id_simati, id_jenisha, "+
		    		"id_negeri, id_daerah, jenama, "+
		    		"no_daftar, bil_unit, no_sijil, "+
		    		"tarikh_harta, alamat_ha1, alamat_ha2, "+
		    		"poskod, alamat_ha3, nilai_ha_tarikhmohon, "+
		    		"nilai_ha_tarikhmati, ba_simati, bb_simati, "+
		    		"catatan, id_masuk, tarikh_masuk, "+
		    	    "id_permohonansimati, nilai_seunit,"+
		    		"id_kemaskini, tarikh_kemaskini ) ";

			    sqlSelect = "SELECT bil, id_simati, id_jenisha, " +
			       "id_negeri, id_daerah, jenama, "+
			       "no_daftar, bil_unit, no_sijil, "+
			       "tarikh_harta, alamat_ha1, alamat_ha2, "+
			       "poskod, alamat_ha3, nilai_ha_tarikhmohon, "+
			       "nilai_ha_tarikhmati, ba_simati, bb_simati, "+
			       "catatan, '"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk, "+
			       //azam change here
			       //"'"+id_permohonansimati+"' as id_permohonansimati, " +
			       "'"+idPermohonanSimatiBaru+"' as id_permohonansimati, " +
			       "nilai_seunit, " +
			       "'"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini "+
			       "FROM TBLPPKHA where ID_PERMOHONANSIMATI IN (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI " +
			       "WHERE ID_PERMOHONAN = '"+id_permohonan+"' ) ";

			    myLogger.info("TBLPPKHA :: "+sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKPEGUAMPEMOHON	13

			    sqlInsert = "INSERT INTO TBLPPKPEGUAMPEMOHON ( "+
		    		"id_peguam, id_pemohon, "+
		    		"id_masuk, tarikh_masuk, "+
		    		"id_kemaskini, tarikh_kemaskini ) ";


			    sqlSelect = "SELECT id_peguam, id_pemohon, "+
			       "'"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk, "+
			       "'"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini "+
			       "FROM TBLPPKPEGUAMPEMOHON WHERE ID_PEMOHON = '"+idPemohonBaru+"' ";


			    myLogger.info("TBLPPKPEGUAMPEMOHON :: "+sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKKEPUTUSANPERMOHONAN		14

			    sqlInsert = "INSERT INTO TBLPPKKEPUTUSANPERMOHONAN ( "+
		    		"id_permohonan, "+
		    		"catatan, tarikh_hantar_borangb, "+
		    		"tarikh_terima_borangc, keputusan_permohonan, "+
		    		"tarikh_hantar_nilaian, tarikh_terima_nilaian, "+
		    		"id_negerimahkamah, id_daerah_mahkamah, "+
		    		"jenis_borangc, "+
		    		"id_masuk, tarikh_masuk, "+
		    		"id_kemaskini, tarikh_kemaskini ) ";


			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as id_permohonan, "+
			       "catatan, tarikh_hantar_borangb, "+
			       "tarikh_terima_borangc, keputusan_permohonan, "+
			       "tarikh_hantar_nilaian, tarikh_terima_nilaian, "+
			       "id_negerimahkamah, id_daerah_mahkamah, "+
			       "jenis_borangc, "+
			       "'"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk, "+
			       "'"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini "+
			       "FROM TBLPPKKEPUTUSANPERMOHONAN where id_permohonan = '"+id_permohonan+"' ";


			    myLogger.info("TBLPPKKEPUTUSANPERMOHONAN :: "+sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);


			  //TBLPPKFAILPINDAH		15

			    sqlInsert = "INSERT INTO TBLPPKFAILPINDAH ( "+
			    	" id_faillama, id_failbaru, id_permohonansimati, "+
			    	" id_masuk, tarikh_masuk, id_kemaskini, tarikh_kemaskini ) "+
			    	" VALUES ( '"+id_fail+"', '"+idFailBaru+"', '"+id_permohonansimati+"', '"+ekptg_user_id+"', sysdate , '"+ekptg_user_id+"', sysdate ) ";



			    myLogger.info("TBLPPKFAILPINDAH :: "+sqlInsert);
			    stmt.executeUpdate(sqlInsert);

			    conn.commit();
		    }
		    catch (SQLException se) {
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pindah Fail:"+se.getMessage());
		    }
		    finally {
		      if (db != null) db.close();
		    }
			myLogger.info("SEKSYEN 8 <<<<< ::"+getNoFile + "|" + idPermohonanBaru + "|" + idFailBaru);
			//return getNoFile + "|" + idPermohonanBaru + "|" + idFailBaru ;
			output.put("getNoFile", getNoFile);
			output.put("idPermohonanBaru", idPermohonanBaru);
			output.put("idFailBaru", idFailBaru);
			return output;
		}


		public static Hashtable generateIdFail(String ekptg_user_id,String id_fail,String id_permohonan,String kod_negeri,String kod_daerah,String txdTarikhPindah,String id_permohonansimati,String id_negeri,String id_daerah,String no_fail)
		throws Exception{
		    Db db = null;
		    Connection conn = null;
		    String getNoFile;

		    String sqlSelect = "";
		    String sqlInsert = "";
		    long idFailBaru;
		    long idPermohonanBaru;
		    Hashtable output = null;
		    try {
		    	  output = new Hashtable();
			      db = new Db();
		          conn = db.getConnection();
		          conn.setAutoCommit(false);
			      Statement stmt = db.getStatement();

			    idFailBaru = DB.getNextID(db,"TBLPFDFAIL_SEQ");
			    idPermohonanBaru = DB.getNextID(db,"TBLPPKPERMOHONAN_SEQ");
			    long idSemakanHantarBaru = DB.getNextID(db,"TBLSEMAKANHANTAR_SEQ");
			    long idPermohonanSimatiBaru = DB.getNextID(db,"TBLPPKPERMOHONANSIMATI_SEQ");
			    long idPemohonBaru =  DB.getNextID(db,"TBLPPKPEMOHON_SEQ");
			    long idHtaBaru = DB.getNextID(db,"TBLPPKHTA_SEQ");
			    long idHaBaru = DB.getNextID(db,"TBLPPKHA_SEQ");
			    long idPeguamPemohonBaru = DB.getNextID(db,"TBLPPKPEGUAMPEMOHON_SEQ");
			    long idKeputusanPermohonanBaru = DB.getNextID(db,"TBLPPKKEPUTUSANPERMOHONAN_SEQ");

			    /*
			    TBLPFDFAIL
				TBLPPKPERMOHONAN
				TBLPPKPEMOHON
				TBLPPKPEGUAM
				TBLPPKPEGUAMPEMOHON
				TBLPPKPERMOHONANSIMATI
				TBLPPKSIMATI
				TBLRUJSUBURUSANSTATUSFAIL
				TBLPPKBAYARAN
				TBLSEMAKANHANTAR
				TBLPPKHUBUNGANOBPERMOHONAN
				TBLPPKHUBUNGANOB
				TBLPPKOB
				TBLPPKOBPERMOHONAN
				TBLPPKPENGHUTANG
				TBLPPKHTA
				TBLPPKHTAPERMOHONAN
				TBLPPKHA
				TBLPPKHAPERMOHONAN
				TBLPPKKEPUTUSANPERMOHONAN  
			    */
			    
			    //TBLPFDFAIL	1

			    java.util.Calendar calendar = java.util.Calendar.getInstance();
				int getYear = calendar.get(java.util.Calendar.YEAR);

			    String X = String.format("%04d",File.getSeqNo(db,2,382,0,Integer.parseInt(id_negeri),Integer.parseInt(id_daerah),false,false,getYear,0));
			    getNoFile = "JKPTG/PK/"+ kod_negeri + "/"+ kod_daerah + "/"+X+"/"+getYear;

			    sqlInsert = "INSERT INTO TBLPFDFAIL ( "+
						   "ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN,  "+
						   "ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL,  "+
						   "TAJUK_FAIL, NO_FAIL, NO_FAIL_ROOT,  "+
						   "ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN,  "+
						   "ID_FAHARASAT, FLAG_FAIL, ID_STATUS,  "+
						   "CATATAN, ID_MASUK, TARIKH_MASUK,  "+
						   "ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF,  "+
						   "ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL ) ";

			    sqlSelect = "SELECT '"+idFailBaru+"' as ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN,"+
				   "ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, "+
				   "'"+getNoFile+"' as TAJUK_FAIL, '"+getNoFile+"' as NO_FAIL, NO_FAIL_ROOT, "+
				   "ID_LOKASIFAIL, '"+id_negeri+"' as ID_NEGERI, ID_KEMENTERIAN, "+
				   "ID_FAHARASAT, FLAG_FAIL, ID_STATUS, "+
				   "CATATAN, '"+ekptg_user_id+"' as ID_MASUK,sysdate as TARIKH_MASUK, "+
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, " +
				   "TARIKH_TUKAR_TARAF, "+
				   "ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL "+
				   "FROM TBLPFDFAIL where id_fail='"+id_fail+"' ";

			    myLogger.info("TBLPFDFAIL :: "+sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKPERMOHONAN	2

			    txdTarikhPindah = txdTarikhPindah ;
			    //Integer id_permohonan_baru = getidPermohonanBaru ;

			    //txdTarikhPindah = (String)data.get("txdTarikhPindah");
				String TR = "to_date('" + txdTarikhPindah + "','dd/MM/yyyy')";

				myLogger.info("ID NEGERI === "+id_negeri);
				myLogger.info("ID DAERAH === "+id_daerah);
				myLogger.info("NO FAIL LAMA === "+no_fail);

			    sqlInsert = "INSERT INTO TBLPPKPERMOHONAN ( "+
				   "ID_PERMOHONAN, ID_FAIL, NO_PERMOHONAN,  "+
				   "NO_PERSERAHAN, TARIKH_MOHON, BIL_BICARA,  "+
				   "JUMLAH_HTA_TARIKHMOHON, JUMLAH_HTA_TARIKHMATI, JUMLAH_HA_TARIKHMOHON,  "+
				   "JUMLAH_HARTA_TARIKHMATI, BIDANG_KUASA, FLAG_JENIS_BORANGC, "+
				   "KEPUTUSAN, CATATAN, ID_BUKTIMATI, ID_TARAFKPTG, "+
				   "ID_NEGERIMHN, ID_DAERAHMHN, ID_STATUS,  "+
				   "SEKSYEN, ID_UNITPSKAWAL, ID_NEGERIAWAL, "+
				   "ID_DAERAHAWAL, ID_PEJAWAL, NO_FAIL_AWAL, "+
				   "BATAL_KUASA_PENTADBIR, LANTIK_PENTADBIR, BATAL_P_AMANAH, "+
				   "LANTIK_P_AMANAH, HARTA_TINGGAL, NAMA_PEMOHON_AWAL, "+
				   "FLAG_JENIS_PERMOHONAN, NO_PERMOHONAN_ONLINE," +
				   "ID_MASUK,TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, "+
				   "FLAG_HUBUNGAN_PEMOHON, ID_HUBUNGANPEMOHON, TARIKH_MOHON_ONLINE, ID_DB, "+
				   "NO_SUBJAKET, ID_NEGERITERTINGGI, ID_DAERAHTERTINGGI,  " +
				   "ID_PERMOHONANTERDAHULU, ID_PEMOHON, PERINTAH_LAMA, PERINTAH_BARU, CATATAN_PERINTAH, FLAG_PERMOHONAN, "+
				   "LAIN_TUJUAN, TARIKH_RAYUAN, ID_ARB, JUM_HARTA_TAMBAHAN_TKHMOHON, JUM_HARTA_TAMBAHAN_TKHMATI, " +
				   //BARU TAMBAH
				   "JUM_HTA_TAMBAHAN_TKHMOHON, JUM_HTA_TAMBAHAN_TKHMATI, JUM_HA_TAMBAHAN_TKHMOHON, JUM_HA_TAMBAHAN_TKHMATI," +
				   "USER_ID_KEBENARAN_EDIT,FLAG_KEBENARAN_EDIT,CATATAN_KEBENARAN_EDIT,CATATAN_MAKLUMAT_TAMBAHAN ) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as ID_PERMOHONAN, '"+idFailBaru+"' as ID_FAIL, NO_PERMOHONAN,"+
				   "NO_PERSERAHAN, "+TR+" as TARIKH_MOHON, BIL_BICARA, "+
				   "JUMLAH_HTA_TARIKHMOHON, JUMLAH_HTA_TARIKHMATI, JUMLAH_HA_TARIKHMOHON, "+
				   "JUMLAH_HARTA_TARIKHMATI, BIDANG_KUASA, FLAG_JENIS_BORANGC,  "+
				   "KEPUTUSAN, CATATAN, ID_BUKTIMATI, ID_TARAFKPTG, "+
				   "'"+id_negeri+"' as ID_NEGERIMHN,'"+id_daerah+"' as ID_DAERAHMHN, ID_STATUS,  "+
				   "SEKSYEN, ID_UNITPSKAWAL, ID_NEGERIAWAL, "+
				   "ID_DAERAHAWAL, ID_PEJAWAL, '"+no_fail+"' as NO_FAIL_AWAL, "+
				   "BATAL_KUASA_PENTADBIR, LANTIK_PENTADBIR, BATAL_P_AMANAH, "+
				   "LANTIK_P_AMANAH, HARTA_TINGGAL, NAMA_PEMOHON_AWAL, "+
				   "FLAG_JENIS_PERMOHONAN, NO_PERMOHONAN_ONLINE, " +
				   "'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, "+
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, " +
				   "FLAG_HUBUNGAN_PEMOHON, ID_HUBUNGANPEMOHON, TARIKH_MOHON_ONLINE, ID_DB, " +
				   "NO_SUBJAKET, ID_NEGERITERTINGGI, ID_DAERAHTERTINGGI,  " +
				   "ID_PERMOHONANTERDAHULU, ID_PEMOHON, PERINTAH_LAMA, PERINTAH_BARU, CATATAN_PERINTAH, FLAG_PERMOHONAN," +
				   "LAIN_TUJUAN, TARIKH_RAYUAN, ID_ARB, JUM_HARTA_TAMBAHAN_TKHMOHON, JUM_HARTA_TAMBAHAN_TKHMATI, "+
				   "JUM_HTA_TAMBAHAN_TKHMOHON, JUM_HTA_TAMBAHAN_TKHMATI, JUM_HA_TAMBAHAN_TKHMOHON, JUM_HA_TAMBAHAN_TKHMATI, "+
				   //BARU TAMBAH
				   "USER_ID_KEBENARAN_EDIT,FLAG_KEBENARAN_EDIT,CATATAN_KEBENARAN_EDIT,CATATAN_MAKLUMAT_TAMBAHAN "+
				   "FROM TBLPPKPERMOHONAN where id_permohonan='"+id_permohonan+"' ";

			    myLogger.info(sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLSEMAKANHANTAR	3

			    sqlInsert = "INSERT INTO TBLSEMAKANHANTAR ( "+
				   "ID_SEMAKANSENARAI, ID_PERMOHONAN,  "+
				   "PEMOHON, PENTADBIR, STATUS, CATATAN, TARIKH_PELBAGAI, " +
				   "ID_DOKUMEN, FLAG_ADA, ID_MASUK, TARIKH_MASUK, " +
				   "ID_KEMASKINI, TARIKH_KEMASKINI ) ";

			    sqlSelect = "SELECT ID_SEMAKANSENARAI, '"+idPermohonanBaru+"' as ID_PERMOHONAN,"+
				   "PEMOHON, PENTADBIR, STATUS,CATATAN, TARIKH_PELBAGAI, " +
				   "ID_DOKUMEN, FLAG_ADA, '"+ekptg_user_id+"' as ID_MASUK,sysdate as TARIKH_MASUK, "+
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI " +
				   "FROM TBLSEMAKANHANTAR where id_permohonan='"+id_permohonan+"' ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKPERMOHONANSIMATI	4

			    sqlInsert = "INSERT INTO TBLPPKPERMOHONANSIMATI ( "+
				   "ID_PERMOHONANSIMATI,ID_PERMOHONAN, ID_SIMATI, "+
				   "ID_MASUK, TARIKH_MASUK, " +
				   "ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB) ";

			    sqlSelect = "SELECT "+idPermohonanSimatiBaru+" as ID_PERMOHONANSIMATI," +
			      " '"+idPermohonanBaru+"' as ID_PERMOHONAN, ID_SIMATI, " +
			       "'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, "+
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, ID_DB "+
				   "FROM TBLPPKPERMOHONANSIMATI where id_permohonan='"+id_permohonan+"'";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLRUJSUBURUSANSTATUSFAIL	5

			    sqlInsert = "INSERT INTO TBLRUJSUBURUSANSTATUSFAIL ( "+
				   "ID_PERMOHONAN, ID_SUBURUSANSTATUS,  "+
				   "URL, AKTIF,ID_MASUK, TARIKH_MASUK,  "+
				   "ID_KEMASKINI, TARIKH_KEMASKINI, ID_FAIL) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as ID_PERMOHONAN, ID_SUBURUSANSTATUS, " +
				   "URL, '0' as AKTIF, "+
				   "'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, " +
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, '"+idFailBaru+"' as ID_FAIL "+
				   "FROM TBLRUJSUBURUSANSTATUSFAIL where id_permohonan='"+id_permohonan+"'";


			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKBAYARAN	6

			    sqlInsert = "INSERT INTO TBLPPKBAYARAN ( "+
				   "ID_PERMOHONAN, ID_JENISBAYARAN,  "+
				   "NO_RESIT, TARIKH_BAYARAN, JUMLAH_BAYARAN," +
				   "ID_MASUK, TARIKH_MASUK, "+
				   "ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB ) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as ID_PERMOHONAN, ID_JENISBAYARAN, " +
				   "NO_RESIT, TARIKH_BAYARAN, JUMLAH_BAYARAN, "+
				   "'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, " +
				   "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, ID_DB "+
				   "FROM TBLPPKBAYARAN where id_permohonan='"+id_permohonan+"'";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			   //TBLPPKPEMOHON	7

			    sqlInsert = " INSERT INTO TBLPPKPEMOHON ( "+
			    			" ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, "+
			    			" NO_KP_LAIN, UMUR, JANTINA, JENIS_AGAMA, JENIS_WARGA, "+
			    			" ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, ID_BANDAR, "+
			    			" POSKOD, NO_HP, NO_TEL, EMEL, NO_FAX, "+
			    			" CATATAN, ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI, STATUS_PEGUAM, "+
			    			" STATUS_PEMOHON, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, "+
			    			" POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, "+
			    			" ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, "+
			    			" ID_PEMOHONLAMA, ID_ARB )";


			    sqlSelect = " SELECT '"+idPemohonBaru+"' AS ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU, "+
				   "  NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,UMUR, JANTINA,  "+
				   "  JENIS_AGAMA, JENIS_WARGA, ALAMAT_1, ALAMAT_2, ALAMAT_3,"+
				   "  BANDAR, ID_BANDAR, POSKOD, NO_HP, NO_TEL, EMEL, NO_FAX, CATATAN,"+
				   "  ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI, STATUS_PEGUAM, STATUS_PEMOHON, "+
				   "  ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, POSKOD_SURAT,"+
				   "  NO_HP_SURAT, NO_TEL_SURAT ,'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, " +
				   "  '"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, ID_DB, "+
				   "  BANDAR_SURAT, ID_NEGERISURAT, ID_PEMOHONLAMA, ID_ARB "+
				   "  FROM TBLPPKPEMOHON WHERE ID_PEMOHON IN ( "+
				   "  SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN ='"+id_permohonan+"' ) ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKOB	8

			    sqlInsert = "INSERT INTO TBLPPKOB ( "+
				   "ID_SIMATI, NAMA_OB,  "+
				   "NO_KP_BARU, NO_KP_LAMA, JENIS_KP,NO_KP_LAIN, " +
				   "NO_SURAT_BERANAK, TARIKH_LAHIR,JANTINA, UMUR, ALAMAT_1, " +
				   "ALAMAT_2, ALAMAT_3, BANDAR,ID_BANDAR, POSKOD, NO_HP, NO_TEL, " +
				   "CATATAN, STATUS_HIDUP,ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, " +
				   "ID_JENISPB, JENIS_AGAMA, JENIS_WARGA,ID_BANK, NO_AKAUN, " +
				   "TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB,  "+
				   "NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, " +
				   "JENIS_PEMIUTANG,ID_PEMOHON, ID_PERMOHONANSIMATI, ALAMAT1_SURAT, " +
				   "ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, " +
				   "NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
				   "TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT,ID_PERAYU," +
				   "ID_OBLAMA,ID_ARB,NO_FAX,FLAG_DAFTAR ) ";

			    sqlSelect = "SELECT ID_SIMATI, NAMA_OB, "+
				   "NO_KP_BARU, NO_KP_LAMA, JENIS_KP,NO_KP_LAIN, " +
				   "NO_SURAT_BERANAK, TARIKH_LAHIR,JANTINA, UMUR, ALAMAT_1, " +
				   "ALAMAT_2, ALAMAT_3, BANDAR,ID_BANDAR, POSKOD, NO_HP, NO_TEL, " +
				   "CATATAN, STATUS_HIDUP,ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, " +
				   "ID_JENISPB, JENIS_AGAMA, JENIS_WARGA,ID_BANK, NO_AKAUN, " +
				   "TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB, "+
				   "NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, " +
				   "JENIS_PEMIUTANG,ID_PEMOHON,'"+idPermohonanSimatiBaru+"' as ID_PERMOHONANSIMATI, ALAMAT1_SURAT, " +
				   "ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, " +
				   "NO_HP_SURAT, NO_TEL_SURAT, '"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, '"+ekptg_user_id+"'  as ID_KEMASKINI, "+
				   "sysdate as TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT,ID_PERAYU," +
				   "ID_OBLAMA,ID_ARB,NO_FAX,FLAG_DAFTAR "+
				   "FROM TBLPPKOB WHERE ID_PERMOHONANSIMATI IN ( "+
				   "SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
				
				//TBLPPKOBPERMOHONAN
				/*
				 sqlInsert = "INSERT INTO TBLPPKOBPERMOHONAN ( "+
				   "ID_OB,ID_SIMATI, NAMA_OB,  "+
				   "NO_KP_BARU, NO_KP_LAMA, JENIS_KP,NO_KP_LAIN, " +
				   "NO_SURAT_BERANAK, TARIKH_LAHIR,JANTINA, UMUR, ALAMAT_1, " +
				   "ALAMAT_2, ALAMAT_3, BANDAR,ID_BANDAR, POSKOD, NO_HP, NO_TEL, " +
				   "CATATAN, STATUS_HIDUP,ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, " +
				   "ID_JENISPB, JENIS_AGAMA, JENIS_WARGA,ID_BANK, NO_AKAUN, " +
				   "TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB,  "+
				   "NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, " +
				   "JENIS_PEMIUTANG,ID_PEMOHON, ID_PERMOHONANSIMATI, ALAMAT1_SURAT, " +
				   "ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, " +
				   "NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
				   "TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT,ID_PERAYU," +
				   "ID_OBLAMA,ID_ARB,NO_FAX,FLAG_DAFTAR ) ";

			    sqlSelect = "SELECT ID_SIMATI, NAMA_OB, "+
				   "NO_KP_BARU, NO_KP_LAMA, JENIS_KP,NO_KP_LAIN, " +
				   "NO_SURAT_BERANAK, TARIKH_LAHIR,JANTINA, UMUR, ALAMAT_1, " +
				   "ALAMAT_2, ALAMAT_3, BANDAR,ID_BANDAR, POSKOD, NO_HP, NO_TEL, " +
				   "CATATAN, STATUS_HIDUP,ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, " +
				   "ID_JENISPB, JENIS_AGAMA, JENIS_WARGA,ID_BANK, NO_AKAUN, " +
				   "TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB, "+
				   "NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, " +
				   "JENIS_PEMIUTANG,ID_PEMOHON,'"+idPermohonanSimatiBaru+"' as ID_PERMOHONANSIMATI, ALAMAT1_SURAT, " +
				   "ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, " +
				   "NO_HP_SURAT, NO_TEL_SURAT, '"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, '"+ekptg_user_id+"'  as ID_KEMASKINI, "+
				   "sysdate as TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT,ID_PERAYU," +
				   "ID_OBLAMA,ID_ARB,NO_FAX,FLAG_DAFTAR "+
				   "FROM TBLPPKOBPERMOHONAN WHERE ID_PERMOHONANSIMATI IN ( "+
				   "SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
				*/

			    //TBLPPKHUBUNGANOB	9

			    sqlInsert = "INSERT INTO TBLPPKHUBUNGANOB ( "+
				   "ID_OB, ID_PARENTOB, ID_SAUDARA, ID_MASUK, TARIKH_MASUK,  "+
				   "ID_KEMASKINI, TARIKH_KEMASKINI ) ";

			    sqlSelect = "SELECT ID_OB, ID_PARENTOB, ID_SAUDARA, '"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, "+
			    	"'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI "+
			    	"from TBLPPKHUBUNGANOB where ID_OB in (select ID_PERMOHONANSIMATI from TBLPPKPERMOHONANSIMATI where ID_PERMOHONAN = '"+id_permohonan+"') ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
				
				
				//TBLPPKHUBUNGANOBPERMOHONAN
				/*
				sqlInsert = " INSERT INTO TBLPPKHUBUNGANOBPERMOHONAN ( "+
						   " ID_HUBUNGANOBPERMOHONAN, ID_PERMOHONANSIMATI, ID_HUBUNGANOB, "+
						   " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
						   " TARIKH_KEMASKINI, ID_DB, ID_OB, "+
						   " ID_PARENTOB, ID_SAUDARA) ";				
				sqlSelect = " SELECT "+
				   " ID_HUBUNGANOBPERMOHONAN, ID_PERMOHONANSIMATI, ID_HUBUNGANOB, "+
				   " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
				   " TARIKH_KEMASKINI, ID_DB, ID_OB, "+
				   " ID_PARENTOB, ID_SAUDARA FROM  TBLPPKHUBUNGANOBPERMOHONAN ";				
				myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
				*/



			    //TBLPPKPENGHUTANG 10
				//Azam change here...id permohonan simati
			    sqlInsert = "INSERT INTO TBLPPKPENGHUTANG ( "+
			    	"ID_SIMATI, NAMA_PENGHUTANG, NO_KP_BARU, NO_KP_LAMA,  "+
			    	"NO_KP_LAIN, ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, POSKOD, NO_AKAUN, NAMA_BANK, "+
			    	"JUMLAH_HUTANG, BUTIRAN_HUTANG, ID_NEGERI, JENIS_PENGHUTANG, JENIS_AGAMA, JENIS_WARGA,  "+
			    	"JENIS_KP, ID_PERMOHONANSIMATI, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI,ID_BANDAR ) ";


			    sqlSelect = "SELECT id_simati, nama_penghutang, no_kp_baru, no_kp_lama, " +
			       "no_kp_lain, alamat_1, alamat_2, alamat_3, bandar, poskod, no_akaun, nama_bank, " +
			       "jumlah_hutang, butiran_hutang, id_negeri, jenis_penghutang,jenis_agama, jenis_warga, " +
			       "jenis_kp," +
			       //"id_permohonansimati, " +
			       "'"+idPermohonanSimatiBaru+"', " +
			       "'"+ekptg_user_id+"' as id_masuk, "+
			       "sysdate as tarikh_masuk, '"+ekptg_user_id+"' as id_kemaskini, sysdate as  tarikh_kemaskini,ID_BANDAR "+
			       "FROM tblppkpenghutang where ID_PERMOHONANSIMATI IN "+
			       "(SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') ";

				    myLogger.info(sqlInsert+sqlSelect);
					stmt.executeUpdate(sqlInsert+sqlSelect);
					

			    //TBLPPKHTA 11
				//Azam change on 30/3/2010
				//id_permohonansimati menggunakan id yg baru
			    sqlInsert = "INSERT INTO TBLPPKHTA ( "+
		    	   "no_hakmilik, id_simati, no_pt, nilai_hta_tarikhmohon, "+
			       "nilai_hta_tarikhmati, id_kategori,id_jenishm, id_jenispb, id_negeri, "+
			       "id_daerah, id_mukim, id_luas,luas, luas_hmp, no_pajakan,  "+
			       "no_cagaran, jenis_tnh, alamat_hta1, alamat_hta2, alamat_hta3, bandar_hta, "+
			       "poskod_hta, tarikh_perjanjian, nama_pemaju, alamat_pemaju1, "+
			       "alamat_pemaju2, alamat_pemaju3, bandar_pemaju, poskod_pemaju, "+
			       " id_negeripemaju, catatan, ba_simati,bb_simati, no_bangunan, "+
			       "no_tingkat, no_petak, no_perjanjian, maklumat_tanah, "+
			       "no_strata, jenis_hta, status_pemilikan, tanggungan, no_perserahan, "+
			       "nama_rancangan, no_roh, no_lot_id, flag_kategori_hta, jenis_kepentingan, "+
			       "id_permohonansimati, id_masuk, "+
			       "tarikh_masuk, id_kemaskini, tarikh_kemaskini," +
			       "ID_BANDARPEMAJU,ID_BANDARHTA,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,ID_HAKMILIK,FLAG_DAFTAR ) ";

			    sqlSelect = "SELECT no_hakmilik, id_simati, no_pt, nilai_hta_tarikhmohon, "+
			       "nilai_hta_tarikhmati, id_kategori,id_jenishm, id_jenispb, id_negeri, "+
			       "id_daerah, id_mukim, id_luas,luas, luas_hmp, no_pajakan,  "+
			       "no_cagaran, jenis_tnh, alamat_hta1, alamat_hta2, alamat_hta3, bandar_hta, "+
			       "poskod_hta, tarikh_perjanjian, nama_pemaju, alamat_pemaju1, "+
			       "alamat_pemaju2, alamat_pemaju3, bandar_pemaju, poskod_pemaju, "+
			       " id_negeripemaju, catatan, ba_simati,bb_simati, no_bangunan, "+
			       "no_tingkat, no_petak, no_perjanjian, maklumat_tanah, "+
			       "no_strata, jenis_hta, status_pemilikan, tanggungan, no_perserahan, "+
			       "nama_rancangan, no_roh, no_lot_id, flag_kategori_hta, jenis_kepentingan, "+
			       //"id_permohonansimati, " +
			       "'"+idPermohonanSimatiBaru+"',"+
			       "'"+ekptg_user_id+"' as id_masuk, "+
			       "sysdate as tarikh_masuk, '"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini," +
			       		"ID_BANDARPEMAJU,ID_BANDARHTA,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,ID_HAKMILIK,FLAG_DAFTAR  "+
			       "from TBLPPKHTA a  WHERE ID_PERMOHONANSIMATI IN (  "+
			       "select ID_PERMOHONANSIMATI from TBLPPKPERMOHONANSIMATI where id_permohonan = '"+id_permohonan+"' ) ";

			    myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);



			    //TBLPPKHA 		12

			    sqlInsert = "INSERT INTO TBLPPKHA ( "+
		    		"bil, id_simati, id_jenisha, "+
		    		"id_negeri, id_daerah, jenama, "+
		    		"no_daftar, bil_unit, no_sijil, "+
		    		"tarikh_harta, alamat_ha1, alamat_ha2, "+
		    		"poskod, alamat_ha3, nilai_ha_tarikhmohon, "+
		    		"nilai_ha_tarikhmati, ba_simati, bb_simati, "+
		    		"catatan, id_masuk, tarikh_masuk, "+
		    	    "id_permohonansimati, nilai_seunit,"+
		    		"id_kemaskini, tarikh_kemaskini," +
		    		"FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,NAMA_SAHAM,BUTIRAN,ID_HA_LAMA,FLAG_DAFTAR) ";

			    sqlSelect = "SELECT bil, id_simati, id_jenisha, " +
			       "id_negeri, id_daerah, jenama, "+
			       "no_daftar, bil_unit, no_sijil, "+
			       "tarikh_harta, alamat_ha1, alamat_ha2, "+
			       "poskod, alamat_ha3, nilai_ha_tarikhmohon, "+
			       "nilai_ha_tarikhmati, ba_simati, bb_simati, "+
			       "catatan, '"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk, "+
			       //azam change here
			       //"'"+id_permohonansimati+"' as id_permohonansimati, " +
			       "'"+idPermohonanSimatiBaru+"' as id_permohonansimati, " +
			       "nilai_seunit, " +
			       "'"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini," +
			       		"FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,NAMA_SAHAM,BUTIRAN,ID_HA_LAMA,FLAG_DAFTAR "+
			       "FROM TBLPPKHA where ID_PERMOHONANSIMATI IN (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI " +
			       "WHERE ID_PERMOHONAN = '"+id_permohonan+"' ) ";

			    myLogger.info("TBLPPKHA :: "+sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKPEGUAMPEMOHON	13

			    sqlInsert = "INSERT INTO TBLPPKPEGUAMPEMOHON ( "+
		    		"id_peguam, id_pemohon, "+
		    		"id_masuk, tarikh_masuk, "+
		    		"id_kemaskini, tarikh_kemaskini ) ";
			    
			    sqlSelect = "SELECT id_peguam, id_pemohon, "+
			       "'"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk, "+
			       "'"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini "+
			       "FROM TBLPPKPEGUAMPEMOHON WHERE ID_PEMOHON = '"+idPemohonBaru+"' ";
			    
			    myLogger.info("TBLPPKPEGUAMPEMOHON :: "+sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);


			    //TBLPPKKEPUTUSANPERMOHONAN		14

			    sqlInsert = "INSERT INTO TBLPPKKEPUTUSANPERMOHONAN ( "+
		    		"id_permohonan, "+
		    		"catatan, tarikh_hantar_borangb, "+
		    		"tarikh_terima_borangc, keputusan_permohonan, "+
		    		"tarikh_hantar_nilaian, tarikh_terima_nilaian, "+
		    		"id_negerimahkamah, id_daerah_mahkamah, "+
		    		"jenis_borangc, "+
		    		"id_masuk, tarikh_masuk, "+
		    		"id_kemaskini, tarikh_kemaskini ) ";


			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as id_permohonan, "+
			       "catatan, tarikh_hantar_borangb, "+
			       "tarikh_terima_borangc, keputusan_permohonan, "+
			       "tarikh_hantar_nilaian, tarikh_terima_nilaian, "+
			       "id_negerimahkamah, id_daerah_mahkamah, "+
			       "jenis_borangc, "+
			       "'"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk, "+
			       "'"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini "+
			       "FROM TBLPPKKEPUTUSANPERMOHONAN where id_permohonan = '"+id_permohonan+"' ";


			    myLogger.info("TBLPPKKEPUTUSANPERMOHONAN :: "+sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);


			  //TBLPPKFAILPINDAH		15

			    sqlInsert = "INSERT INTO TBLPPKFAILPINDAH ( "+
			    	" id_faillama, id_failbaru, id_permohonansimati, "+
			    	" id_masuk, tarikh_masuk, id_kemaskini, tarikh_kemaskini ) "+
			    	" VALUES ( '"+id_fail+"', '"+idFailBaru+"', '"+id_permohonansimati+"', '"+ekptg_user_id+"', sysdate , '"+ekptg_user_id+"', sysdate ) ";



			    myLogger.info("TBLPPKFAILPINDAH :: "+sqlInsert);
			    stmt.executeUpdate(sqlInsert);

			    conn.commit();
		    }
		    catch (SQLException se) {
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pindah Fail:"+se.getMessage());
		    }
		    finally {
		      if (db != null) db.close();
		    }
			myLogger.info("SEKSYEN 8 <<<<< ::"+getNoFile + "|" + idPermohonanBaru + "|" + idFailBaru);
			//return getNoFile + "|" + idPermohonanBaru + "|" + idFailBaru ;
			output.put("getNoFile", getNoFile);
			output.put("idPermohonanBaru", idPermohonanBaru);
			output.put("idFailBaru", idFailBaru);
			return output;
		}
		
		//* generate idFail Seksyen 17
		public static Hashtable generateIdFailS17( String ekptg_user_id, String id_fail, String id_permohonan, String kod_negeri,
				String kod_daerah, String txdTarikhPindah, String id_permohonansimati, String id_negeri,
				String id_daerah, String no_fail, String no_subjaket ) throws Exception	{

		    Db db = null;
		    Connection conn = null;
		    String getNoFile;
		    String sqlSelect = "";
		    String sqlInsert = "";
		    long idFailBaru;
		    long idPermohonanBaru;
		    Hashtable output = null;

		    try {
		    	output = new Hashtable();
			    db = new Db();
		        conn = db.getConnection();
		        conn.setAutoCommit(false);
			    Statement stmt = db.getStatement();

			    idFailBaru = DB.getNextID(db,"TBLPFDFAIL_SEQ");
			    idPermohonanBaru = DB.getNextID(db,"TBLPPKPERMOHONAN_SEQ");
			    long idSemakanHantarBaru = DB.getNextID(db,"TBLSEMAKANHANTAR_SEQ");
			    long idPermohonanSimatiBaru = DB.getNextID(db,"TBLPPKPERMOHONANSIMATI_SEQ");
			    long idPemohonBaru =  DB.getNextID(db,"TBLPPKPEMOHON_SEQ");
			    long idHtaBaru = DB.getNextID(db,"TBLPPKHTA_SEQ");
			    long idHaBaru = DB.getNextID(db,"TBLPPKHA_SEQ");
			    long idPeguamPemohonBaru = DB.getNextID(db,"TBLPPKPEGUAMPEMOHON_SEQ");
			    long idKeputusanPermohonanBaru = DB.getNextID(db,"TBLPPKKEPUTUSANPERMOHONAN_SEQ");

			    //TBLPFDFAIL	1
			    java.util.Calendar calendar = java.util.Calendar.getInstance();
				int getYear = calendar.get(java.util.Calendar.YEAR);

				//String s = "";
				String X = String.format("%04d", File.getSeqNo(db,2, 382, 0, Integer
						.parseInt(id_negeri), Integer.parseInt(id_daerah), false,
						false, getYear,0));

				//getNoFile = s + "/S17-" + no_subjaket;

				getNoFile = "JKPTG/PK/"+ kod_negeri + "/"+ kod_daerah + "/"+X+"/"+getYear + "/S17-" + no_subjaket;
				//myLogger.info("getNoFile:" + getNoFile);

			    sqlInsert = "INSERT INTO TBLPFDFAIL ( "+
			    	"ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN,  "+
					"ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL,  "+
					"TAJUK_FAIL, NO_FAIL, NO_FAIL_ROOT,  "+
					"ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN,  "+
					"ID_FAHARASAT, FLAG_FAIL, ID_STATUS,  "+
					"CATATAN, ID_MASUK, TARIKH_MASUK,  "+
					"ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF,  "+
					"ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL ) ";

			    sqlSelect = "SELECT '"+idFailBaru+"' as ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN,"+
					"ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, "+
					"'"+getNoFile+"' as TAJUK_FAIL, '"+getNoFile+"' as NO_FAIL, NO_FAIL_ROOT, "+
					"ID_LOKASIFAIL, '"+id_negeri+"' as ID_NEGERI, ID_KEMENTERIAN, "+
					"ID_FAHARASAT, FLAG_FAIL, ID_STATUS, "+
					"CATATAN, '"+ekptg_user_id+"' as ID_MASUK,sysdate as TARIKH_MASUK, "+
					"'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, " +
					"TARIKH_TUKAR_TARAF, "+
					"ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL "+
					"FROM TBLPFDFAIL where id_fail='"+id_fail+"' ";
			    //myLogger.info("TBLPFDFAIL :: "+sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKPERMOHONAN	2
			    txdTarikhPindah = txdTarikhPindah ;
				String TR = "to_date('" + txdTarikhPindah + "','dd/MM/yyyy')";
//				myLogger.info("ID NEGERI SEKSYEN 17 === "+id_negeri);
//				myLogger.info("ID DAERAH SEKSYEN 17 === "+id_daerah);
//				myLogger.info("NO FAIL LAMA SEKSYEN 17 === "+no_fail);
//				myLogger.info("NO SUBJAKET === "+no_subjaket);

			    sqlInsert = "INSERT INTO TBLPPKPERMOHONAN ( "+
			    	"ID_PERMOHONAN, ID_FAIL, NO_PERMOHONAN,  "+
			    	"NO_PERSERAHAN, TARIKH_MOHON, BIL_BICARA,  "+
			    	"JUMLAH_HTA_TARIKHMOHON, JUMLAH_HTA_TARIKHMATI, JUMLAH_HA_TARIKHMOHON,  "+
			    	"JUMLAH_HARTA_TARIKHMATI, BIDANG_KUASA, FLAG_JENIS_BORANGC, "+
			    	"KEPUTUSAN, CATATAN, ID_BUKTIMATI, ID_TARAFKPTG, "+
			    	"ID_NEGERIMHN, ID_DAERAHMHN, ID_STATUS,  "+
			    	"SEKSYEN, ID_UNITPSKAWAL, ID_NEGERIAWAL, "+
			    	"ID_DAERAHAWAL, ID_PEJAWAL, NO_FAIL_AWAL, "+
			    	"BATAL_KUASA_PENTADBIR, LANTIK_PENTADBIR, BATAL_P_AMANAH, "+
			    	"LANTIK_P_AMANAH, HARTA_TINGGAL, NAMA_PEMOHON_AWAL, "+
			    	"FLAG_JENIS_PERMOHONAN, NO_PERMOHONAN_ONLINE," +
			    	"ID_MASUK,TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, "+
			    	"FLAG_HUBUNGAN_PEMOHON, ID_HUBUNGANPEMOHON, TARIKH_MOHON_ONLINE, ID_DB, "+
			    	"NO_SUBJAKET, ID_NEGERITERTINGGI, ID_DAERAHTERTINGGI,  " +
			    	"ID_PERMOHONANTERDAHULU, ID_PEMOHON, PERINTAH_LAMA, PERINTAH_BARU, CATATAN_PERINTAH, FLAG_PERMOHONAN, "+
			    	"LAIN_TUJUAN, TARIKH_RAYUAN, ID_ARB, JUM_HARTA_TAMBAHAN_TKHMOHON, JUM_HARTA_TAMBAHAN_TKHMATI, " +
			    	"JUM_HTA_TAMBAHAN_TKHMOHON, JUM_HTA_TAMBAHAN_TKHMATI, JUM_HA_TAMBAHAN_TKHMOHON, JUM_HA_TAMBAHAN_TKHMATI," +
			    	"USER_ID_KEBENARAN_EDIT,FLAG_KEBENARAN_EDIT,CATATAN_KEBENARAN_EDIT,CATATAN_MAKLUMAT_TAMBAHAN ) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as ID_PERMOHONAN, '"+idFailBaru+"' as ID_FAIL, NO_PERMOHONAN,"+
					"NO_PERSERAHAN, "+TR+" as TARIKH_MOHON, BIL_BICARA, "+
				    "JUMLAH_HTA_TARIKHMOHON, JUMLAH_HTA_TARIKHMATI, JUMLAH_HA_TARIKHMOHON, "+
				    "JUMLAH_HARTA_TARIKHMATI, BIDANG_KUASA, FLAG_JENIS_BORANGC,  "+
				    "KEPUTUSAN, CATATAN, ID_BUKTIMATI, ID_TARAFKPTG, "+
				    "'"+id_negeri+"' as ID_NEGERIMHN,'"+id_daerah+"' as ID_DAERAHMHN, ID_STATUS,  "+
				    "SEKSYEN, ID_UNITPSKAWAL, ID_NEGERIAWAL, "+
				    "ID_DAERAHAWAL, ID_PEJAWAL, '"+no_fail+"' as NO_FAIL_AWAL, "+
				    "BATAL_KUASA_PENTADBIR, LANTIK_PENTADBIR, BATAL_P_AMANAH, "+
				    "LANTIK_P_AMANAH, HARTA_TINGGAL, NAMA_PEMOHON_AWAL, "+
				    "FLAG_JENIS_PERMOHONAN, NO_PERMOHONAN_ONLINE, " +
				    "'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, "+
				    "'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, " +
				    "FLAG_HUBUNGAN_PEMOHON, ID_HUBUNGANPEMOHON, TARIKH_MOHON_ONLINE, ID_DB, " +
				    "NO_SUBJAKET, ID_NEGERITERTINGGI, ID_DAERAHTERTINGGI,  " +
				    "ID_PERMOHONANTERDAHULU, ID_PEMOHON, PERINTAH_LAMA, PERINTAH_BARU, CATATAN_PERINTAH, FLAG_PERMOHONAN," +
				    "LAIN_TUJUAN, TARIKH_RAYUAN, ID_ARB, JUM_HARTA_TAMBAHAN_TKHMOHON, JUM_HARTA_TAMBAHAN_TKHMATI, "+
				    "JUM_HTA_TAMBAHAN_TKHMOHON, JUM_HTA_TAMBAHAN_TKHMATI, JUM_HA_TAMBAHAN_TKHMOHON, JUM_HA_TAMBAHAN_TKHMATI," +
				    "USER_ID_KEBENARAN_EDIT,FLAG_KEBENARAN_EDIT,CATATAN_KEBENARAN_EDIT,CATATAN_MAKLUMAT_TAMBAHAN  "+
				    "FROM TBLPPKPERMOHONAN where id_permohonan='"+id_permohonan+"' ";
			    //myLogger.info(sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLSEMAKANHANTAR	3
			    sqlInsert = "INSERT INTO TBLSEMAKANHANTAR ( "+
			    	"ID_SEMAKANSENARAI, ID_PERMOHONAN,  "+
			    	"PEMOHON, PENTADBIR, STATUS, CATATAN, TARIKH_PELBAGAI, " +
			    	"ID_DOKUMEN, FLAG_ADA, ID_MASUK, TARIKH_MASUK, " +
			    	"ID_KEMASKINI, TARIKH_KEMASKINI ) ";

			    sqlSelect = "SELECT ID_SEMAKANSENARAI, '"+idPermohonanBaru+"' as ID_PERMOHONAN,"+
			    	"PEMOHON, PENTADBIR, STATUS,CATATAN, TARIKH_PELBAGAI, " +
			    	"ID_DOKUMEN, FLAG_ADA, '"+ekptg_user_id+"' as ID_MASUK,sysdate as TARIKH_MASUK, "+
			    	"'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI " +
			    	"FROM TBLSEMAKANHANTAR where id_permohonan='"+id_permohonan+"' ";
			    //myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKPERMOHONANSIMATI	4
			    sqlInsert = "INSERT INTO TBLPPKPERMOHONANSIMATI ( "+
			    	"ID_PERMOHONANSIMATI,ID_PERMOHONAN, ID_SIMATI, "+
			    	"ID_MASUK, TARIKH_MASUK, " +
			    	"ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB) ";

			    sqlSelect = "SELECT "+idPermohonanSimatiBaru+" as ID_PERMOHONANSIMATI," +
			    	" '"+idPermohonanBaru+"' as ID_PERMOHONAN, ID_SIMATI, " +
			    	"'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, "+
			    	"'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, ID_DB "+
			    	"FROM TBLPPKPERMOHONANSIMATI where id_permohonan='"+id_permohonan+"'";
			    //myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLRUJSUBURUSANSTATUSFAIL	5
			    sqlInsert = "INSERT INTO TBLRUJSUBURUSANSTATUSFAIL ( "+
			    	"ID_PERMOHONAN, ID_SUBURUSANSTATUS,  "+
			    	"URL, AKTIF,ID_MASUK, TARIKH_MASUK,  "+
			    	"ID_KEMASKINI, TARIKH_KEMASKINI, ID_FAIL) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as ID_PERMOHONAN, ID_SUBURUSANSTATUS, " +
					"URL, '0' as AKTIF, "+
					"'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, " +
					"'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, '"+idFailBaru+"' as ID_FAIL "+
					"FROM TBLRUJSUBURUSANSTATUSFAIL where id_permohonan='"+id_permohonan+"'";
			    //myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKBAYARAN	6
			    sqlInsert = "INSERT INTO TBLPPKBAYARAN ( "+
			    	"ID_PERMOHONAN, ID_JENISBAYARAN,  "+
			    	"NO_RESIT, TARIKH_BAYARAN, JUMLAH_BAYARAN," +
			    	"ID_MASUK, TARIKH_MASUK, "+
			    	"ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB ) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as ID_PERMOHONAN, ID_JENISBAYARAN, " +
			    	"NO_RESIT, TARIKH_BAYARAN, JUMLAH_BAYARAN, "+
			    	"'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, " +
			    	"'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, ID_DB "+
			    	"FROM TBLPPKBAYARAN where id_permohonan='"+id_permohonan+"'";
			    //myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			   //TBLPPKPEMOHON	7
			    sqlInsert = " INSERT INTO TBLPPKPEMOHON ( "+
    				" ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, "+
    				" NO_KP_LAIN, UMUR, JANTINA, JENIS_AGAMA, JENIS_WARGA, "+
    				" ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, ID_BANDAR, "+
    				" POSKOD, NO_HP, NO_TEL, EMEL, NO_FAX, "+
    				" CATATAN, ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI, STATUS_PEGUAM, "+
    				" STATUS_PEMOHON, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, "+
    				" POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, "+
    				" ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, "+
    				" ID_PEMOHONLAMA, ID_ARB )";

			    sqlSelect = " SELECT '"+idPemohonBaru+"' AS ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU, "+
					"  NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,UMUR, JANTINA,  "+
					"  JENIS_AGAMA, JENIS_WARGA, ALAMAT_1, ALAMAT_2, ALAMAT_3,"+
					"  BANDAR, ID_BANDAR, POSKOD, NO_HP, NO_TEL, EMEL, NO_FAX, CATATAN,"+
					"  ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI, STATUS_PEGUAM, STATUS_PEMOHON, "+
					"  ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, POSKOD_SURAT,"+
					"  NO_HP_SURAT, NO_TEL_SURAT ,'"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, " +
					"  '"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI, ID_DB, "+
					"  BANDAR_SURAT, ID_NEGERISURAT, ID_PEMOHONLAMA, ID_ARB "+
					"  FROM TBLPPKPEMOHON WHERE ID_PEMOHON IN ( "+
					"  SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN ='"+id_permohonan+"' ) ";
			    //myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKOB	8
			    sqlInsert = "INSERT INTO TBLPPKOB ( "+
					"ID_SIMATI, NAMA_OB,  "+
					"NO_KP_BARU, NO_KP_LAMA, JENIS_KP,NO_KP_LAIN, " +
					"NO_SURAT_BERANAK, TARIKH_LAHIR,JANTINA, UMUR, ALAMAT_1, " +
					"ALAMAT_2, ALAMAT_3, BANDAR,ID_BANDAR, POSKOD, NO_HP, NO_TEL, " +
					"CATATAN, STATUS_HIDUP,ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, " +
					"ID_JENISPB, JENIS_AGAMA, JENIS_WARGA,ID_BANK, NO_AKAUN, " +
					"TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB,  "+
					"NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, " +
					"JENIS_PEMIUTANG,ID_PEMOHON, ID_PERMOHONANSIMATI, ALAMAT1_SURAT, " +
					"ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, " +
					"NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
					"TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT,ID_PERAYU," +
					"ID_OBLAMA,ID_ARB,NO_FAX,FLAG_DAFTAR ) ";

			    sqlSelect = "SELECT ID_SIMATI, NAMA_OB, "+
					"NO_KP_BARU, NO_KP_LAMA, JENIS_KP,NO_KP_LAIN, " +
					"NO_SURAT_BERANAK, TARIKH_LAHIR,JANTINA, UMUR, ALAMAT_1, " +
					"ALAMAT_2, ALAMAT_3, BANDAR,ID_BANDAR, POSKOD, NO_HP, NO_TEL, " +
					"CATATAN, STATUS_HIDUP,ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, " +
					"ID_JENISPB, JENIS_AGAMA, JENIS_WARGA,ID_BANK, NO_AKAUN, " +
					"TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB, "+
					"NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, " +
					"JENIS_PEMIUTANG,ID_PEMOHON,'"+idPermohonanSimatiBaru+"' as ID_PERMOHONANSIMATI, ALAMAT1_SURAT, " +
					"ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, " +
					"NO_HP_SURAT, NO_TEL_SURAT, '"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, '"+ekptg_user_id+"'  as ID_KEMASKINI, "+
					"sysdate as TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT,ID_PERAYU," +
					"ID_OBLAMA,ID_ARB,NO_FAX,FLAG_DAFTAR  "+
					"FROM TBLPPKOB WHERE ID_PERMOHONANSIMATI IN ( "+
					"SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') ";
			    //myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKHUBUNGANOB	9
			    sqlInsert = "INSERT INTO TBLPPKHUBUNGANOB ( "+
					"ID_OB, ID_PARENTOB, ID_SAUDARA, ID_MASUK, TARIKH_MASUK,  "+
					"ID_KEMASKINI, TARIKH_KEMASKINI ) ";

			    sqlSelect = "SELECT ID_OB, ID_PARENTOB, ID_SAUDARA, '"+ekptg_user_id+"' as ID_MASUK, sysdate as TARIKH_MASUK, "+
			    	"'"+ekptg_user_id+"' as ID_KEMASKINI, sysdate as TARIKH_KEMASKINI "+
			    	"from TBLPPKHUBUNGANOB where ID_OB in (select ID_PERMOHONANSIMATI from TBLPPKPERMOHONANSIMATI where ID_PERMOHONAN = '"+id_permohonan+"') ";
			    //myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKPENGHUTANG 10
			    sqlInsert = "INSERT INTO TBLPPKPENGHUTANG ( "+
			    	"ID_SIMATI, NAMA_PENGHUTANG, NO_KP_BARU, NO_KP_LAMA,  "+
			    	"NO_KP_LAIN, ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, POSKOD, NO_AKAUN, NAMA_BANK, "+
			    	"JUMLAH_HUTANG, BUTIRAN_HUTANG, ID_NEGERI, JENIS_PENGHUTANG, JENIS_AGAMA, JENIS_WARGA,  "+
			    	"JENIS_KP, ID_PERMOHONANSIMATI, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI ) ";

			    sqlSelect = "SELECT id_simati, nama_penghutang, no_kp_baru, no_kp_lama, " +
			    	"no_kp_lain, alamat_1, alamat_2, alamat_3, bandar, poskod, no_akaun, nama_bank, " +
			    	"jumlah_hutang, butiran_hutang, id_negeri, jenis_penghutang,jenis_agama, jenis_warga, " +
			    	"jenis_kp,id_permohonansimati, '"+ekptg_user_id+"' as id_masuk, "+
			    	"sysdate as tarikh_masuk, '"+ekptg_user_id+"' as id_kemaskini, sysdate as  tarikh_kemaskini "+
			    	"FROM tblppkpenghutang where ID_PERMOHONANSIMATI IN "+
			    	"(SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') ";
			    //myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKHTA 11
			    sqlInsert = "INSERT INTO TBLPPKHTA ( "+
		    		"no_hakmilik, id_simati, no_pt, nilai_hta_tarikhmohon, "+
		    		"nilai_hta_tarikhmati, id_kategori,id_jenishm, id_jenispb, id_negeri, "+
		    		"id_daerah, id_mukim, id_luas,luas, luas_hmp, no_pajakan,  "+
		    		"no_cagaran, jenis_tnh, alamat_hta1, alamat_hta2, alamat_hta3, bandar_hta, "+
		    		"poskod_hta, tarikh_perjanjian, nama_pemaju, alamat_pemaju1, "+
		    		"alamat_pemaju2, alamat_pemaju3, bandar_pemaju, poskod_pemaju, "+
		    		" id_negeripemaju, catatan, ba_simati,bb_simati, no_bangunan, "+
		    		"no_tingkat, no_petak, no_perjanjian, maklumat_tanah, "+
		    		"no_strata, jenis_hta, status_pemilikan, tanggungan, no_perserahan, "+
		    		"nama_rancangan, no_roh, no_lot_id, flag_kategori_hta, jenis_kepentingan, "+
		    		"id_permohonansimati, id_masuk, "+
		    		"tarikh_masuk, id_kemaskini, tarikh_kemaskini," +
				    "ID_BANDARPEMAJU,ID_BANDARHTA,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,ID_HAKMILIK,FLAG_DAFTAR ) " +
		    		"";

			    sqlSelect = "SELECT no_hakmilik, id_simati, no_pt, nilai_hta_tarikhmohon, "+
			       "nilai_hta_tarikhmati, id_kategori,id_jenishm, id_jenispb, id_negeri, "+
			       "id_daerah, id_mukim, id_luas,luas, luas_hmp, no_pajakan,  "+
			       "no_cagaran, jenis_tnh, alamat_hta1, alamat_hta2, alamat_hta3, bandar_hta, "+
			       "poskod_hta, tarikh_perjanjian, nama_pemaju, alamat_pemaju1, "+
			       "alamat_pemaju2, alamat_pemaju3, bandar_pemaju, poskod_pemaju, "+
			       " id_negeripemaju, catatan, ba_simati,bb_simati, no_bangunan, "+
			       "no_tingkat, no_petak, no_perjanjian, maklumat_tanah, "+
			       "no_strata, jenis_hta, status_pemilikan, tanggungan, no_perserahan, "+
			       "nama_rancangan, no_roh, no_lot_id, flag_kategori_hta, jenis_kepentingan, "+
			       "id_permohonansimati, '"+ekptg_user_id+"' as id_masuk, "+
			       "sysdate as tarikh_masuk, '"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini," +
			       "ID_BANDARPEMAJU,ID_BANDARHTA,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,ID_HAKMILIK,FLAG_DAFTAR "+
			       "from TBLPPKHTA a  WHERE ID_PERMOHONANSIMATI IN (  "+
			       "select ID_PERMOHONANSIMATI from TBLPPKPERMOHONANSIMATI where id_permohonan = '"+id_permohonan+"' ) ";
			    //myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKHA 		12
			    sqlInsert = "INSERT INTO TBLPPKHA ( "+
			    	"bil, id_simati, id_jenisha, "+
		    		"id_negeri, id_daerah, jenama, "+
		    		"no_daftar, bil_unit, no_sijil, "+
		    		"tarikh_harta, alamat_ha1, alamat_ha2, "+
		    		"poskod, alamat_ha3, nilai_ha_tarikhmohon, "+
		    		"nilai_ha_tarikhmati, ba_simati, bb_simati, "+
		    		"catatan, id_masuk, tarikh_masuk, "+
		    	    "id_permohonansimati, nilai_seunit,"+
		    		"id_kemaskini, tarikh_kemaskini," +
		    		//Kemaskini pada 26/12/2013 oleh Mohamad Rosli, Buang column ID_BANDARPEMAJU,ID_BANDARHTA
		    		//"ID_BANDARPEMAJU,ID_BANDARHA,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,FLAG_DAFTAR) ";
			    	"FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,FLAG_DAFTAR) ";

			    sqlSelect = "SELECT bil, id_simati, id_jenisha, " +
			    	"id_negeri, id_daerah, jenama, "+
			    	"no_daftar, bil_unit, no_sijil, "+
			    	"tarikh_harta, alamat_ha1, alamat_ha2, "+
			    	"poskod, alamat_ha3, nilai_ha_tarikhmohon, "+
			    	"nilai_ha_tarikhmati, ba_simati, bb_simati, "+
			    	"catatan, '"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk, "+
			    	"'"+id_permohonansimati+"' as id_permohonansimati, nilai_seunit, " +
			    	"'"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini," +
			    	//Kemaskini pada 26/12/2013 oleh Mohamad Rosli, Buang column ID_BANDARPEMAJU,ID_BANDARHTA
			    	//"ID_BANDARPEMAJU,ID_BANDARHA,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,FLAG_DAFTAR "+
			    	"FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,FLAG_DAFTAR "+
			    	"FROM TBLPPKHA where ID_PERMOHONANSIMATI IN (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI " +
			    	"WHERE ID_PERMOHONAN = '"+id_permohonan+"' ) ";
			    //myLogger.info("TBLPPKHA :: "+sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKPEGUAMPEMOHON	13
			    sqlInsert = "INSERT INTO TBLPPKPEGUAMPEMOHON ( "+
		    		"id_peguam, id_pemohon, "+
		    		"id_masuk, tarikh_masuk, "+
		    		"id_kemaskini, tarikh_kemaskini ) ";

			    sqlSelect = "SELECT id_peguam, id_pemohon, "+
			    	"'"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk, "+
			    	"'"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini "+
			    	"FROM TBLPPKPEGUAMPEMOHON WHERE ID_PEMOHON = '"+idPemohonBaru+"' ";
			    //myLogger.info("TBLPPKPEGUAMPEMOHON :: "+sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);

			    //TBLPPKKEPUTUSANPERMOHONAN		14
			    sqlInsert = "INSERT INTO TBLPPKKEPUTUSANPERMOHONAN ( "+
		    		"id_permohonan, "+
		    		"catatan, tarikh_hantar_borangb, "+
		    		"tarikh_terima_borangc, keputusan_permohonan, "+
		    		"tarikh_hantar_nilaian, tarikh_terima_nilaian, "+
		    		"id_negerimahkamah, id_daerah_mahkamah, "+
		    		"jenis_borangc, "+
		    		"id_masuk, tarikh_masuk, "+
		    		"id_kemaskini, tarikh_kemaskini ) ";

			    sqlSelect = "SELECT '"+idPermohonanBaru+"' as id_permohonan, "+
			    	"catatan, tarikh_hantar_borangb, "+
			        "tarikh_terima_borangc, keputusan_permohonan, "+
			        "tarikh_hantar_nilaian, tarikh_terima_nilaian, "+
			        "id_negerimahkamah, id_daerah_mahkamah, "+
			        "jenis_borangc, "+
			        "'"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk, "+
			        "'"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini "+
			        "FROM TBLPPKKEPUTUSANPERMOHONAN where id_permohonan = '"+id_permohonan+"' ";
			    //myLogger.info("TBLPPKKEPUTUSANPERMOHONAN :: "+sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);

			  //TBLPPKFAILPINDAH		15
//			    sqlInsert = "INSERT INTO TBLPPKFAILPINDAH ( "+
//			    	" id_faillama, id_failbaru, id_permohonansimati, "+
//			    	" id_masuk, tarikh_masuk, id_kemaskini, tarikh_kemaskini ) "+
//			    	" VALUES ( '"+id_fail+"', '"+idFailBaru+"', '"+id_permohonansimati+"', '"+ekptg_user_id+"', sysdate , '"+ekptg_user_id+"', sysdate ) ";
//
//			    myLogger.info("TBLPPKFAILPINDAH :: "+sqlInsert);
//			    stmt.executeUpdate(sqlInsert);

			    String history_nofail = no_fail.replace("/S17-" + no_subjaket,"");

			    sqlInsert =  " INSERT INTO TBLPPKFAILPINDAH ( "+
	    		"ID_FAILLAMA, ID_FAILBARU, ID_PERMOHONANSIMATI, "+
	    		"ID_MASUK, TARIKH_MASUK,ID_KEMASKINI, TARIKH_KEMASKINI ) ";

			    sqlSelect = " select id_fail as id_faillama , '"+idFailBaru+"' as id_failbaru, "+
			       " ( select id_permohonansimati from tblppkpermohonansimati where id_permohonan "+
			       " in (select id_permohonan from tblppkpermohonan where id_fail=a.id_fail) "+
			       " ) as id_permohonansimati, '"+ekptg_user_id+"' as id_masuk, sysdate as tarikh_masuk,  "+
			       "  '"+ekptg_user_id+"' as id_kemaskini, sysdate as tarikh_kemaskini "+
			       " from tblpfdfail a where no_fail like '"+history_nofail+"%' ";
			    //myLogger.info("TBLPPKFAILPINDAH >>>>>>> :: "+sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);

			    conn.commit();
		    }
		    catch (SQLException se) {
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pindah Fail:"+se.getMessage());
		    }
		    finally {
		      if (db != null) db.close();
		    }
			//myLogger.info("xx SEKSYEN 17 <<<<<<<<<<<< ::"+getNoFile + "|" + idPermohonanBaru + "|" + idFailBaru);
			//return getNoFile + "|" + idPermohonanBaru + "|" + idFailBaru ;
			output.put("getNoFile", getNoFile);
			output.put("idPermohonanBaru", idPermohonanBaru);
			output.put("idFailBaru", idFailBaru);
			return output;

		}












		public void setListbyIdnegeriFailLama(String ekptg_user_id,String id_negeri,String id_daerah) throws Exception {
			Db db = null;
			listTunggubyNegFailLama.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {

			      db = new Db();
			      Statement stmt = db.getStatement();

			      sql = "select c.id_fail,c.no_fail ,c.tarikh_daftar_fail,a.id_permohonan, a.tarikh_mohon,a.tarikh_masuk, "+
			      "e.id_simati,upper(e.nama_simati) as NAMA_SIMATI, upper(f.nama_pemohon) as NAMA_PEMOHON,"+
			      "g.nama_negeri,g.id_negeri, h.nama_daerah,h.id_daerah, O.KETERANGAN, Q.USER_LOGIN, a.id_negerimhn, " +
			      "a.id_daerahmhn, r.nama_negeri, s.nama_daerah, a.id_status, n.id_suburusan, a.seksyen "+
			      "from tblppkpermohonan a, tblppkbke b,  tblpfdfail c, tblppkpermohonansimati d, tblppksimati e, "+
			      "tblppkpemohon f,  tblrujnegeri g,  tblrujdaerah h,  tblrujpejabaturusan i, tblrujpejabatjkptg j, "+
			      "tblrujsuburusanstatusfail m, tblrujsuburusanstatus n, tblrujstatus o, users_internal p, users q, tblrujnegeri r, tblrujdaerah s "+
			      "where A.ID_PERMOHONAN =  B.ID_PERMOHONAN "+
			      "and A.ID_FAIL  =  c.id_fail "+
			      "and D.ID_PERMOHONAN  = A.ID_PERMOHONAN "+
			      "and D.ID_SIMATI = E.ID_SIMATI "+
			      "and A.ID_PEMOHON = F.ID_PEMOHON "+
			      "and B.ID_NEGERI = G.ID_NEGERI "+
			      "and B.ID_DAERAH = h.ID_DAERAH "+
			      "and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
			      "AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
			      "and I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "+
			      "and M.ID_PERMOHONAN = A.ID_PERMOHONAN "+
			      "and M.AKTIF = 1 "+
			      "and M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "+
			      "and N.ID_STATUS = O.ID_STATUS "+
			      "and J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "+
			      "and P.USER_ID  =  Q.USER_ID "+
			      "and A.ID_NEGERIMHN = R.ID_NEGERI "+
			      "AND A.ID_DAERAHMHN = S.ID_DAERAH "+
			      "AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL) "+
			      "and Q.USER_ID = '"+ekptg_user_id+"' "+
			      "and N.ID_STATUS = 169 ";

			      //sorting
			      //sql +=" ORDER BY c.id_fail DESC, a.tarikh_kemaskini DESC";
			      sql +=" ORDER BY C.id_fail ASC";
			      myLogger.info("SQL LIST KETIGA = "+sql);

			      ResultSet rs = stmt.executeQuery(sql);


			      Hashtable h;
			      int bil = 1;

				while (rs.next()){
					myLogger.info("simati:"+rs.getString("NAMA_SIMATI"));
					myLogger.info("simati:"+rs.getString("NAMA_PEMOHON"));
					myLogger.info("negeri:"+rs.getString("NAMA_NEGERI"));
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
					h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
					h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
					h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
					h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
					h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
					h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
					h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
					h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
					h.put("id_daerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
					h.put("nama_negerimhn", rs.getString(18)==null?"":rs.getString(18));
					h.put("nama_daerahmhn", rs.getString(19)==null?"":rs.getString(19));
					h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("id_suburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
					h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));

					listTunggubyNegFailLama.addElement(h);
					bil++;
				}
				//return list;
			} catch (Exception e) {e.printStackTrace();} finally {
				if(db != null) db.close();
			}
			}


		public void setListbyIdnegeriFailLama17(String ekptg_user_id,String id_negeri,String id_daerah) throws Exception {
			Db db = null;
			listTunggubyNegFailLama17.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {

			      db = new Db();
			      Statement stmt = db.getStatement();

			      sql = "select c.id_fail,c.no_fail ,c.tarikh_daftar_fail,a.id_permohonan, a.tarikh_mohon,a.tarikh_masuk, "+
			      "e.id_simati,upper(e.nama_simati) as NAMA_SIMATI, upper(f.nama_pemohon) as NAMA_PEMOHON,"+
			      "g.nama_negeri,g.id_negeri, h.nama_daerah,h.id_daerah, O.KETERANGAN, Q.USER_LOGIN, a.id_negerimhn, " +
			      "a.id_daerahmhn, r.nama_negeri, s.nama_daerah, a.id_status "+
			      "from tblppkpermohonan a, tblppkbke b,  tblpfdfail c, tblppkpermohonansimati d, tblppksimati e, "+
			      "tblppkpemohon f,  tblrujnegeri g,  tblrujdaerah h,  tblrujpejabaturusan i, tblrujpejabatjkptg j, "+
			      "tblrujsuburusanstatusfail m, tblrujsuburusanstatus n, tblrujstatus o, users_internal p, users q, tblrujnegeri r, tblrujdaerah s "+
			      "where A.ID_PERMOHONAN =  B.ID_PERMOHONAN "+
			      "and A.ID_FAIL  =  c.id_fail "+
			      "and D.ID_PERMOHONAN  = A.ID_PERMOHONAN "+
			      "and D.ID_SIMATI = E.ID_SIMATI "+
			      "and A.ID_PEMOHON = F.ID_PEMOHON "+
			      "and B.ID_NEGERI = G.ID_NEGERI "+
			      "and B.ID_DAERAH = h.ID_DAERAH "+
			      "and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
			      "AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
			      "and I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "+
			      "and M.ID_PERMOHONAN = A.ID_PERMOHONAN "+
			      "and M.AKTIF = 1 "+
			      "and M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "+
			      "and N.ID_STATUS = O.ID_STATUS "+
			      "and J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "+
			      "and P.USER_ID  =  Q.USER_ID "+
			      "and A.ID_NEGERIMHN = R.ID_NEGERI "+
			      "AND A.ID_DAERAHMHN = S.ID_DAERAH "+
			      "and Q.USER_ID = '"+ekptg_user_id+"' "+
			      "and N.ID_STATUS = 169 AND c.id_suburusan = 60 ";

			      //sorting
			      //sql +=" ORDER BY c.id_fail DESC, a.tarikh_kemaskini DESC";
			      sql +=" ORDER BY C.id_fail ASC";
			      myLogger.info("SQL LIST KETIGA = "+sql);

			      ResultSet rs = stmt.executeQuery(sql);


			      Hashtable h;
			      int bil = 1;

				while (rs.next()){
					myLogger.info("simati:"+rs.getString("NAMA_SIMATI"));
					myLogger.info("simati:"+rs.getString("NAMA_PEMOHON"));
					myLogger.info("negeri:"+rs.getString("NAMA_NEGERI"));
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
					h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
					h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
					h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
					h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
					h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
					h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
					h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
					h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
					h.put("id_daerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
					h.put("nama_negerimhn", rs.getString(18)==null?"":rs.getString(18));
					h.put("nama_daerahmhn", rs.getString(19)==null?"":rs.getString(19));
					h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));

					listTunggubyNegFailLama17.addElement(h);
					bil++;
				}
				//return list;
			} catch (Exception e) {e.printStackTrace();} finally {
				if(db != null) db.close();
			}
			}


	public static void update_status_NewFail_Tblppkpermohonan(String id_fail) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "update tblppkpermohonan set id_status = 8 "+
		      		"where id_fail='"+id_fail+"' ";
//				"where id_permohonan = ( "+
//				"select id_permohonan from tblppkpermohonan where id_fail = (select id_fail from tblpfdfail where no_fail = '"+no_fail+"') "+
//				")";
		      myLogger.info(sql);
		      stmt.executeQuery(sql);

		} catch (Exception e) {e.printStackTrace();} finally {
			if(db != null) db.close();
		}

	}

	public static void update_status_NewFail_Tblppkpermohonan17(String id_fail) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "update tblppkpermohonan set id_status = 8,seksyen = 17 "+
					"where id_fail='"+id_fail+"' ";
		      	//Azam change to use id_fail instead of no_fail
		      	/*"where id_permohonan = ( "+
				"select id_permohonan from tblppkpermohonan where id_fail = (select id_fail from tblpfdfail where no_fail = '"+no_fail+"') "+
				")";
				*/
		      //myLogger.info("NO FAIL  :: "+no_fail);
		      myLogger.info("SQL UPDATE STATUS TBLPPKPERMOHONSN :: "+sql);
		      stmt.executeQuery(sql);

		} catch (Exception e) {e.printStackTrace();} finally {
			if(db != null) db.close();
		}

	}

	public void setFailPindahBatal(String id_permohonan, String id_fail) throws Exception {
		Db db = null;
		listFailPindahBatal.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();


			r.add("F.ID_FAIL");
			r.add("F.NO_FAIL");
			r.add("F.TARIKH_DAFTAR_FAIL");
			r.add("A.ID_PERMOHONAN");
			r.add("A.TARIKH_MOHON");
			r.add("A.TARIKH_MASUK");
			r.add("S.KETERANGAN");
			r.add("P.ID_SIMATI");
			r.add("P.NAMA_SIMATI");
			r.add("PP.NAMA_PEMOHON");
			r.add("KP.KEPUTUSAN_PERMOHONAN");
			r.add("BKE.ID_NEGERI");
			r.add("NEG.NAMA_NEGERI");
			r.add("BKE.ID_DAERAH");
			r.add("DA.NAMA_DAERAH");
			r.add("F.ID_NEGERI");
			r.add("BKE.ID_BKE");
			r.add("NEG.KOD_NEGERI");
			r.add("DA.KOD_DAERAH");
			r.add("A.ID_NEGERIMHN");
			r.add("A.ID_DAERAHMHN");
			r.add("R.NAMA_NEGERI");
			r.add("S.NAMA_DAERAH");
			r.add("A.NO_FAIL_AWAL");

			r.add("A.ID_STATUS",r.unquote("S.ID_STATUS(+)"));
			r.add("A.ID_FAIL",r.unquote("F.ID_FAIL"));
			r.add("A.ID_PEMOHON",r.unquote("PP.ID_PEMOHON(+)"));
			r.add("P.ID_SIMATI",r.unquote("PSM.ID_SIMATI"));
			r.add("A.ID_PERMOHONAN",r.unquote("PSM.ID_PERMOHONAN"));
			r.add("A.ID_PERMOHONAN",r.unquote("KP.ID_PERMOHONAN(+)"));
			r.add("BKE.ID_PERMOHONAN",r.unquote("A.ID_PERMOHONAN"));
			r.add("BKE.ID_NEGERI",r.unquote("NEG.ID_NEGERI"));
			r.add("BKE.ID_DAERAH",r.unquote("DA.ID_DAERAH"));
			r.add("A.ID_NEGERIMHN",r.unquote("R.ID_NEGERI"));
			r.add("A.ID_DAERAHMHN",r.unquote("S.ID_DAERAH"));

			r.add("BKE.ID_PERMOHONAN",id_permohonan);

			sql = r.getSQLSelect("TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,TBLPPKPEMOHON PP,TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERMOHONANSIMATI PSM, TBLPPKBKE BKE, TBLRUJNEGERI NEG, TBLRUJDAERAH DA, TBLRUJNEGERI R, TBLRUJDAERAH S ");
			sql = sql + " AND A.ID_STATUS = 169 ";
			myLogger.info("SQL FAIL PINDAH BATAL = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("keputusan_permohonan", rs.getString("KEPUTUSAN_PERMOHONAN")==null?"":rs.getString("KEPUTUSAN_PERMOHONAN"));
				h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("id_projek_negeri", rs.getString(16)==null?"":rs.getString(16));
				h.put("id_bke", rs.getString("ID_BKE")==null?"":rs.getString("ID_BKE"));
				h.put("kod_negeri", rs.getString("KOD_NEGERI")==null?"":rs.getString("KOD_NEGERI"));
				h.put("kod_daerah", rs.getString("KOD_DAERAH")==null?"":rs.getString("KOD_DAERAH"));
				h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
				h.put("id_daerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
				h.put("nama_negerimhn", rs.getString(22)==null?"":rs.getString(22));
				h.put("nama_daerahmhn", rs.getString(23)==null?"":rs.getString(23));
				h.put("no_fail_awal", rs.getString("NO_FAIL_AWAL")==null?"":rs.getString("NO_FAIL_AWAL"));

				listFailPindahBatal.addElement(h);
				bil++;
			}

		}finally {
			if(db != null) db.close();
		}
	}

	public static void insert_idsuburusanstatusfail_NewFail(String ekptg_user_id,String id_permohonan_baru,String id_file_baru) throws Exception {
	    Db db = null;
	    String sql = "";

	    try
	    {
	    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");

		    db = new Db();

		    //* INSERT TBLRUJSUBURUSANSTATUSFAIL
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();

		    r.add("id_suburusanstatusfail", id_suburusanstatusfail);
		    r.add("id_permohonan", id_permohonan_baru);
		    r.add("id_suburusanstatus",340);
		    r.add("aktif",1);
		    r.add("id_fail",id_file_baru);
		    r.add("id_masuk",ekptg_user_id);
			r.add("tarikh_masuk",r.unquote("sysdate"));

		    sql = r.getSQLInsert("tblrujsuburusanstatusfail");
		    myLogger.info("SQL INSERT ID SUBURUSANSTATUSFAIL = "+sql);
		    stmt.executeUpdate(sql);

	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}
    	}

	public static void insert_idsuburusanstatusfail_NewFail17(String ekptg_user_id,String id_permohonan_baru,String id_file_baru) throws Exception {
	    Db db = null;
	    String sql = "";

	    try
	    {
	    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");

		    db = new Db();

		    //* INSERT TBLRUJSUBURUSANSTATUSFAIL
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();

		    r.add("id_suburusanstatusfail", id_suburusanstatusfail);
		    r.add("id_permohonan", id_permohonan_baru);
		    r.add("id_suburusanstatus",430);
		    r.add("aktif",1);
		    r.add("id_fail",id_file_baru);
		    r.add("id_masuk",ekptg_user_id);
			r.add("tarikh_masuk",r.unquote("sysdate"));

		    sql = r.getSQLInsert("tblrujsuburusanstatusfail");
		    myLogger.info("SQL INSERT ID SUBURUSANSTATUSFAIL SEKSYEN 17 = "+sql);
		    stmt.executeUpdate(sql);

	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}
    	}


	public static Vector getLokasiUserID(){
		return listLokasiUserID;
	}

	public static void setLokasiUserID(String usid)throws Exception {
			Db db = null;
			listLokasiUserID.clear();
			String sql = "";

			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql =  "SELECT id_negeri, id_daerah FROM users_internal WHERE user_id = " +usid;


				ResultSet rs = stmt.executeQuery(sql);
			    Hashtable h;

			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("id_negeri", rs.getString("id_negeri"));
			    	  h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));

			    	  listLokasiUserID.addElement(h);
			      	}
				}
				finally{
					if(db != null)db.close();
				}
		}

	public static Vector setNoFailAwal(String id_permohonan, String id_fail) throws Exception {
		Db db = null;
		listFailPindahBatal.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();


			r.add("F.ID_FAIL");
			r.add("F.NO_FAIL");
			r.add("F.TARIKH_DAFTAR_FAIL");
			r.add("A.ID_PERMOHONAN");
			r.add("A.TARIKH_MOHON");
			r.add("A.TARIKH_MASUK");
			r.add("S.KETERANGAN");
			r.add("P.ID_SIMATI");
			r.add("P.NAMA_SIMATI");
			r.add("PP.NAMA_PEMOHON");
			r.add("KP.KEPUTUSAN_PERMOHONAN");
			r.add("BKE.ID_NEGERI");
			r.add("NEG.NAMA_NEGERI");
			r.add("BKE.ID_DAERAH");
			r.add("DA.NAMA_DAERAH");
			r.add("F.ID_NEGERI");
			r.add("BKE.ID_BKE");
			r.add("NEG.KOD_NEGERI");
			r.add("DA.KOD_DAERAH");
			r.add("A.ID_NEGERIMHN");
			r.add("A.ID_DAERAHMHN");
			r.add("R.NAMA_NEGERI");
			r.add("S.NAMA_DAERAH");
			r.add("A.NO_FAIL_AWAL");

			r.add("A.ID_STATUS",r.unquote("S.ID_STATUS(+)"));
			r.add("A.ID_FAIL",r.unquote("F.ID_FAIL"));
			r.add("A.ID_PEMOHON",r.unquote("PP.ID_PEMOHON(+)"));
			r.add("P.ID_SIMATI",r.unquote("PSM.ID_SIMATI"));
			r.add("A.ID_PERMOHONAN",r.unquote("PSM.ID_PERMOHONAN"));
			r.add("A.ID_PERMOHONAN",r.unquote("KP.ID_PERMOHONAN(+)"));
			r.add("BKE.ID_PERMOHONAN",r.unquote("A.ID_PERMOHONAN"));
			r.add("BKE.ID_NEGERI",r.unquote("NEG.ID_NEGERI"));
			r.add("BKE.ID_DAERAH",r.unquote("DA.ID_DAERAH"));
			r.add("A.ID_NEGERIMHN",r.unquote("R.ID_NEGERI"));
			r.add("A.ID_DAERAHMHN",r.unquote("S.ID_DAERAH"));

			r.add("BKE.ID_PERMOHONAN",id_permohonan);

			sql = r.getSQLSelect("TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,TBLPPKPEMOHON PP,TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERMOHONANSIMATI PSM, TBLPPKBKE BKE, TBLRUJNEGERI NEG, TBLRUJDAERAH DA, TBLRUJNEGERI R, TBLRUJDAERAH S ");
			sql = sql + " AND A.ID_STATUS = 169 ";
			myLogger.info("SQL FAIL PINDAH BATAL = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikh_daftar_fail", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("id_permohonan",rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("keputusan_permohonan", rs.getString("KEPUTUSAN_PERMOHONAN")==null?"":rs.getString("KEPUTUSAN_PERMOHONAN"));
				h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("id_projek_negeri", rs.getString(16)==null?"":rs.getString(16));
				h.put("id_bke", rs.getString("ID_BKE")==null?"":rs.getString("ID_BKE"));
				h.put("kod_negeri", rs.getString("KOD_NEGERI")==null?"":rs.getString("KOD_NEGERI"));
				h.put("kod_daerah", rs.getString("KOD_DAERAH")==null?"":rs.getString("KOD_DAERAH"));
				h.put("id_negerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
				h.put("id_daerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
				h.put("nama_negerimhn", rs.getString(22)==null?"":rs.getString(22));
				h.put("nama_daerahmhn", rs.getString(23)==null?"":rs.getString(23));
				h.put("no_fail_awal", rs.getString("NO_FAIL_AWAL")==null?"":rs.getString("NO_FAIL_AWAL"));

				listFailPindahBatal.addElement(h);
				bil++;
			}

		}finally {
			if(db != null) db.close();
		}
		return listFailPindahBatal;
	}



	public static void setDataNoFailBaru(String no_fail) throws Exception {
		Db db = null;
		NoFailBaru.clear();
		String sql = "select a.id_status,a.seksyen,sm.id_simati,a.id_fail,(select no_fail from tblpfdfail where id_fail = a.id_fail) "+
					 "as no_fail_baru,a.id_permohonan,a.no_fail_awal from "+
					 "tblppkpermohonan a,tblppkpermohonansimati sm where a.id_permohonan = sm.id_permohonan and a.no_fail_awal = '"+ no_fail +"' ";
		myLogger.info("------NoFailBaru"+sql);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()){
				h = new Hashtable();
				h.put("id_status_baru", rs.getString("id_status")==null?"":rs.getString("id_status"));
				h.put("seksyen_baru", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
				h.put("id_simati_baru", rs.getString("id_simati")==null?"":rs.getString("id_simati"));
				h.put("id_permohonan_baru", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
				h.put("id_fail_baru", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
				h.put("no_fail_baru", rs.getString("no_fail_baru")==null?"":rs.getString("no_fail_baru"));
				h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
				h.put("no_fail_awal", rs.getString("no_fail_awal")==null?"":rs.getString("no_fail_awal"));

				NoFailBaru.addElement(h);
				bil++;
			}
		}finally {
			if(db != null) db.close();
		}
	}
	
	public static void setDataNoFailBaruCreate(String no_fail) throws Exception {
		Db db = null;
		NoFailBaruCreate.clear();
		String sql = "select a.id_status,a.seksyen,sm.id_simati,a.id_fail," +
				" "+
					 "f.no_fail as no_fail_baru,a.id_permohonan,a.no_fail_awal from "+
					 "tblppkpermohonan a,tblpfdfail f,tblppkpermohonansimati sm " +
					 " where f.id_fail = a.id_fail and a.id_permohonan = sm.id_permohonan and f.no_fail = '"+ no_fail +"' ";
		myLogger.info("------NoFailBaruCreate"+sql);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()){
				h = new Hashtable();
				h.put("id_status_baru", rs.getString("id_status")==null?"":rs.getString("id_status"));
				h.put("seksyen_baru", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
				h.put("id_simati_baru", rs.getString("id_simati")==null?"":rs.getString("id_simati"));
				h.put("id_permohonan_baru", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
				h.put("id_fail_baru", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
				h.put("no_fail_baru", rs.getString("no_fail_baru")==null?"":rs.getString("no_fail_baru"));
				h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
				h.put("no_fail_awal", rs.getString("no_fail_awal")==null?"":rs.getString("no_fail_awal"));

				NoFailBaruCreate.addElement(h);
				bil++;
			}
		}finally {
			if(db != null) db.close();
		}
	}


}
