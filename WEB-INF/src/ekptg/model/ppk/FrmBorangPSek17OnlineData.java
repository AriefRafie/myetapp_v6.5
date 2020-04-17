package ekptg.model.ppk;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;

//Updated on 17/8/2010
public class FrmBorangPSek17OnlineData {
	private static Logger myLogger = Logger.getLogger(FrmBorangPSek17OnlineData.class);
	private Vector listSimati = new Vector();
	private Vector listPeguam = new Vector();
	private Vector listKeputusan = new Vector();
	private Vector listWaris = new Vector();
	private Vector listHta = new Vector();
	private Vector listHtath = new Vector();
	private Vector listHa = new Vector();
	private Vector listRujJenisHa = new Vector();
	private  Vector listPenting = new Vector();
	private  Vector listPentingbyIDOB = new Vector();
	private  Vector listSaksi = new Vector();
	private  Vector listPenghutang= new Vector();
	private  Vector listPemiutang= new Vector();
	private Vector listWarisParent = new Vector();
	private Vector listWarisLapisan = new Vector();
	private Vector listWarisUpdate = new Vector();
	private  Vector listDaerah = new Vector();
	private  Vector listLuas = new Vector();
	private  Vector listHTA = new Vector();
	private  Vector listHTAX = new Vector();
	private  Vector listHTAbyIdHtaam= new Vector();
	private  Vector listHTAXbyIdHtaam= new Vector();
	private Vector listWarisOB = new Vector();
	private Vector listWarisLapisanIdMati = new Vector();
	private Vector listWarisLapisanIdMatiDelete = new Vector();
	private Vector listCheckPeguam = new Vector();
	private Vector listPenghutangbyIDOB = new Vector();
	private Vector listPAPTHTA = new Vector();	
	public Vector getDataPenting(){
		return listPenting;
	}
	public Vector getDataPeguam(){
		return listPeguam;
	}
	public Vector getDataSimati(){
		return listSimati;
	}
	public Vector getDataHtath(){
		return listHtath;
	}
	public Vector getDataHTA(){
		return listHTA;
	}
	public Vector getDataSaksi(){
		return listSaksi;
	}
	public Vector getDataHTAXbyIdHtaam(){
		return listHTAXbyIdHtaam;
	}
	public Vector getDataHTAbyIdHtaam(){
		return listHTAbyIdHtaam;
	}
	public Vector getDataPentingbyIDOB(){
		return listPentingbyIDOB;
	}
	public Vector getDataPenghutang(){
		return listPenghutang;
	}
	public Vector getDataPenghutangbyIDOB(){
		return listPenghutangbyIDOB;
	}
	
	public static Vector chkSek17Data(String idPermohonan) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      sql = "Select count(id_permohonan) as cntdata from tblppkpermohonan and id_status = 21 where id_permohonan = '"+ idPermohonan +"'";
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector listBke = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("cntdata", rs.getString("cntdata")==null?"":rs.getString("cntdata"));
	        listBke.addElement(h);
	      }
	      return listBke;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getDetailsPemohonSek8(String kpbaru, String kplama, String kplain) throws Exception{
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector detailPemohon = new Vector();
		try {
			db = new Db();
			String kpBaru1 = kpbaru.trim();
			String kpLama1 = kplama.trim();
			String kpLain1 = kplain.trim();
			Statement stmt = db.getStatement();
			String sql="SELECT * FROM (SELECT A4.NO_KP_BARU, A4.NO_KP_LAMA, A4.JENIS_KP, A4.NO_KP_LAIN, A4.NAMA_PEMOHON, A5.NO_FAIL,A3." +
					   "NO_PERMOHONAN_ONLINE, A3.FLAG_HUBUNGAN_PEMOHON, A3.ID_HUBUNGANPEMOHON, A3.TARIKH_MOHON, A3.ID_PERMOHONAN," +
					   "A3.ID_NEGERIMHN, A3.ID_DAERAHMHN, A6.NAMA_NEGERI, A7.NAMA_DAERAH,A2.ID_PERMOHONANSIMATI,A1.ID_SIMATI, A4.ID_PEMOHON " +
					   "FROM TBLPPKSIMATI A1, TBLPPKPERMOHONANSIMATI A2, TBLPPKPERMOHONAN A3, TBLPPKPEMOHON A4, TBLPFDFAIL A5, " +
					   "TBLRUJNEGERI A6,TBLRUJDAERAH A7 WHERE A1.ID_SIMATI = A2.ID_SIMATI AND A2.ID_PERMOHONAN = A3.ID_PERMOHONAN " +
					   "AND A3.ID_PEMOHON = A4.ID_PEMOHON AND A3.ID_FAIL = A5.ID_FAIL AND A3.ID_NEGERIMHN = A6.ID_NEGERI(+) " +
					   "AND A3.ID_DAERAHMHN = A7.ID_DAERAH(+) "; //and pp.id_status = 41 and rownum < 2
			
			String sqlwhere = "";
			
			if (kpBaru1 != "") {
				if (!kpBaru1.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(A1.NO_KP_BARU) LIKE '%" + kpBaru1.toUpperCase() + "%'";
				}
			}
			if (kpLama1 != "") {
				if (!kpLama1.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(A1.NO_KP_LAMA) LIKE '%" + kpLama1.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(A1.NO_KP_LAMA) LIKE '%" + kpLama1.toUpperCase() + "%'";
					}
					
				}
			}
			if (kpLain1 != "") {
				if (!kpLain1.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(A1.NO_KP_LAIN) LIKE '%" + kpLain1.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(A1.NO_KP_LAIN) LIKE '%" + kpLain1.toUpperCase() + "%'";
					}
				}
			}	
			if (sqlwhere != "") {
				sql = sql + " AND ( "+sqlwhere+" ) ORDER BY A3.ID_PERMOHONAN DESC ) WHERE ROWNUM < 2";
			} 
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
				h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
				h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
				h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
				h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
				h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
				h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
				h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
				h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("idNegerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
				h.put("idDaerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
				h.put("idpemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
				detailPemohon.addElement(h);
			}
			return detailPemohon;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public static Vector getDetailsPemohon(String kpbaru, String kplama, String kplain, String nofail) throws Exception{
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector detailPemohon = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("P.NO_KP_BARU");
			r.add("P.NO_KP_LAMA");
			r.add("P.JENIS_KP");
			r.add("P.NO_KP_LAIN ");
			r.add("P.NAMA_PEMOHON");
			r.add("PP.NO_PERMOHONAN_ONLINE");
			r.add("PP.FLAG_HUBUNGAN_PEMOHON");
			r.add("PP.ID_HUBUNGANPEMOHON");
			r.add("PP.TARIKH_MOHON_ONLINE");
			r.add("PP.ID_PERMOHONAN");
			r.add("F.NO_FAIL");
			r.add("M1.ID_PERMOHONANSIMATI");
			r.add("M1.ID_SIMATI");
			r.add("PP.NO_SUBJAKET");
			
				
			r.add("P.ID_PERMOHONAN",r.unquote("PP.ID_PERMOHONAN"));
			r.add("PP.ID_FAIL",r.unquote("F.ID_FAIL"));
			r.add("PP.ID_PERMOHONAN",r.unquote("M1.ID_PERMOHONAN"));
			r.add("M1.ID_SIMATI",r.unquote("C.ID_SIMATI"));
			
			
			String kpBaru = kpbaru.trim();
			String kpLama = kplama.trim();
			String kpLain = kplain.trim();
			String failno = nofail.trim();
			sql = r.getSQLSelect("Tblppkpermohonan PP, Tblppksimati c, Tblppkpemohon P, Tblpfdfail F, Tblppkpermohonansimati M1");
			String sqlwhere = "";
			
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(c.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(c.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(c.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
					}
					
				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(c.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(c.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
					}
				}
			}
			if (failno != "") {
				if (!failno.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(pp.NO_PERMOHONAN_ONLINE) LIKE '%" + failno.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(pp.NO_PERMOHONAN_ONLINE) LIKE '%" + failno.toUpperCase() + "%'";
					}
				}
			}
			if (sqlwhere != "") {
				sql = sql + " AND ( "+sqlwhere+" ) AND PP.SEKSYEN = 17 AND ROWNUM < 2";
			} 			
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
				h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
				h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
				h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
				h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
				h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
				h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
				h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
				h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nosubjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
				detailPemohon.addElement(h);
			}
			return detailPemohon;
		}finally {
			if(db != null) db.close();
		}
		}
	

	public static Vector getDetailsPemohonOnline(String kpbaru, String kplama, String kplain) throws Exception{
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector detailPemohon = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("P.NO_KP_BARU");
			r.add("P.NO_KP_LAMA");
			r.add("P.JENIS_KP");
			r.add("P.NO_KP_LAIN ");
			r.add("P.NAMA_PEMOHON");
			r.add("PP.NO_PERMOHONAN_ONLINE");
			r.add("PP.FLAG_HUBUNGAN_PEMOHON");
			r.add("PP.ID_HUBUNGANPEMOHON");
			r.add("PP.TARIKH_MOHON_ONLINE");
			r.add("PP.ID_PERMOHONAN");
			r.add("F.NO_FAIL");
			r.add("D.NAMA_DAERAH");
			r.add("N.NAMA_NEGERI");
			r.add("M1.ID_PERMOHONANSIMATI");
			r.add("M1.ID_SIMATI");
			r.add("PP.NO_SUBJAKET");
			
				
			r.add("P.ID_PERMOHONAN",r.unquote("PP.ID_PERMOHONAN"));
			r.add("PP.ID_FAIL",r.unquote("F.ID_FAIL"));
			r.add("PP.ID_DAERAHMHN",r.unquote("D.ID_DAERAH"));
			r.add("D.ID_NEGERI",r.unquote("N.ID_NEGERI"));
			r.add("PP.ID_PERMOHONAN",r.unquote("M1.ID_PERMOHONAN"));
			r.add("M1.ID_SIMATI",r.unquote("C.ID_SIMATI"));
			
			
			String kpBaru = kpbaru.trim();
			String kpLama = kplama.trim();
			String kpLain = kplain.trim();

			sql = r.getSQLSelect("Tblppkpermohonan PP, Tblppksimati c, Tblppkpemohon P, Tblpfdfail F, Tblrujnegeri n, Tblrujdaerah d, Tblppkpermohonansimati M1");
			String sqlwhere = "";
			
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(c.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(c.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(c.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
					}
					
				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(c.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(c.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
					}
				}
			}
			if (sqlwhere != "") {
				sql = sql + " AND ( "+sqlwhere+" ) AND PP.SEKSYEN = 17 AND ROWNUM < 2";
			} 			
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
				h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
				h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
				h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
				h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
				h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
				h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
				h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
				h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nosubjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
				detailPemohon.addElement(h);
			}
			return detailPemohon;
		}finally {
			if(db != null) db.close();
		}
		}
	
	//private static Vector resultPemohon = new Vector();
	
	public static int checkPemohon(String kpbaru, String kplama, String kplain) throws Exception {
		Db db = null;
		//resultPemohon.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int cnt = 0;
		try {
			db = new Db();
			String kpBaru = kpbaru.trim();
			String kpLama = kplama.trim();
			String kpLain = kplain.trim();
			String sql = "Select count(a.ID_SIMATI) as cntId from TBLPPKSIMATI a, TBLPPKPERMOHONAN b, TBLPPKPERMOHONANSIMATI m1 where " +
					     "a.id_simati = m1.id_simati and m1.id_permohonan = b.id_permohonan and b.seksyen = 8"; //and b.id_status = 41
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + " AND UPPER(a.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					sql = sql + " AND UPPER(a.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					sql = sql + " AND UPPER(a.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
				}
			}
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			myLogger.info("CEK SQL :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			//Hashtable h;
			
			if (rs.next()){
				//h = new Hashtable();
				//h.put("idCnt", rs.getString("cntId")==null?"":rs.getString("cntId"));
				//resultPemohon.addElement(h);
				cnt = 1;
			} else {
				cnt = 0;
			}
		}
		catch(Exception e){
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		finally {
			if(db != null) db.close();
		}
		return cnt;
		}
	//azam removed
//	public static Vector getListPemohon(){
//		//return resultPemohon;
//	}
	
	public static void insertOnlinePermohonan(Hashtable data) throws Exception {
		Db db = null;
			String sql = "";
			String sql1 = "";
			String sql2 = "";
			String sql3 = "";
			String sql4 = "";
			String sql5 = "";
			String sql6 = "";
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			int getYear = calendar.get(java.util.Calendar.YEAR);
			try
			{
				long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
				long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
				long idPermohonan = DB.getNextID("TBLPPKPERMOHONAN_SEQ");
				long idob = DB.getNextID("TBLPPKOB_SEQ");
				long idpermohonansimati = DB.getNextID("TBLPPKPERMOHONANSIMATI_SEQ");
				//String noFail = String.format("%06d",File.getSeqNo(2,01,18,0,0,false,false));
				String userid = (String)data.get("iduser");
				int idusernegeri = (Integer)data.get("useridnegeri");
				String noFail = String.format("%06d",File.getSeqNo(2,02,382,0,idusernegeri,false,false));
				String X = "JKPTG/PK/02/"+getYear+"/"+noFail;
				String NoKPBaru = (String)data.get("noKPBaru");
				String NoKPLama = (String)data.get("noKpLama");
				String JenisKPLain = (String)data.get("jenisKPLain");
				String NoKpLain = (String)data.get("noKpLain");
				String idsimati = (String)data.get("idsimati");
				String flagHubungan = (String)data.get("flagHubungan");
//				int idPermohonanLama = Integer.parseInt((String)data.get("idPermohonanLama"));
				String idPermohonanLama = (String)data.get("idPermohonanLama");
//				int idpemohonlama = Integer.parseInt((String)data.get("idpemohonlama"));
				String idpemohonlama = (String)data.get("idpemohonlama");
				String idHubungan = (String)data.get("idHubungan");
				String idNegerimhn = (String)data.get("idNegerimhn");
				String idDaerahmhn = (String)data.get("idDaerahmhn");
				String nokpbaru3 = (String)data.get("nokpbaru3");
				String socOB = (String)data.get("SocOB");
				String socWaris = (String)data.get("SocWaris");
				String sorWaris = (String)data.get("SorWaris");
				String NamaPemohon = ((String)data.get("namaPemohon")).toUpperCase();
				int wujudWaris = (Integer)data.get("wujudWaris");
				int wujudPemohon = (Integer)data.get("wujudPemohon");
				String idExistPemohon = (String)data.get("idExistPemohon");
				
				String icYEAR = "";
				String icMONTH = "";
				String icDAY = "";
				
				db = new Db();
				Statement stmtA = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_fail",idFail);
				r.add("id_seksyen",2);
				r.add("id_urusan",382);
				r.add("tarikh_daftar_fail",r.unquote("sysdate"));
				r.add("id_negeri",idNegerimhn);
				r.add("id_suburusan",60);
				r.add("flag_fail",1);
				r.add("id_status",7); // active
				r.add("id_masuk",userid);
				r.add("tarikh_masuk",r.unquote("sysdate")); 
				sql = r.getSQLInsert("tblpfdfail");
				stmtA.executeUpdate(sql);
				
				Statement stmt = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				r2.add("ID_PERMOHONAN",idPermohonan);
				r2.add("ID_FAIL",idFail);
				r2.add("FLAG_JENIS_PERMOHONAN",0);
				r2.add("NO_PERMOHONAN_ONLINE",X);
				r2.add("ID_STATUS",160); //status permohonan online sek17
				r2.add("SEKSYEN",17);
				r2.add("TARIKH_MOHON",r.unquote("sysdate"));
				r2.add("TARIKH_MOHON_ONLINE",r.unquote("sysdate"));
				r2.add("FLAG_HUBUNGAN_PEMOHON",sorWaris);
				r2.add("ID_PERMOHONANTERDAHULU",idPermohonanLama);
				r2.add("ID_MASUK",userid);
				r2.add("TARIKH_MASUK",r.unquote("sysdate"));
				if (sorWaris.equals("1")){
					r2.add("ID_HUBUNGANPEMOHON",sorWaris);
				}else if (sorWaris.equals("2")){
					r2.add("ID_HUBUNGANPEMOHON",socOB);
				}
				r2.add("ID_PEMOHON",Integer.parseInt(idExistPemohon));
				r2.add("ID_KEMASKINI",userid);
				r2.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql4 = r2.getSQLInsert("TBLPPKPERMOHONAN");
				stmt.executeUpdate(sql4);
				
				if (wujudPemohon==0){ //0 - klu pemohon tidak ada dlm db
					Statement stmtB = db.getStatement();
					SQLRenderer r1 = new SQLRenderer();
					r1.add("id_pemohon",idPemohon);
					r1.add("no_kp_baru",NoKPBaru);
					r1.add("no_kp_lama",NoKPLama);
					r1.add("jenis_kp",JenisKPLain);
					r1.add("no_kp_lain",NoKpLain);
					r1.add("nama_pemohon",NamaPemohon);
					if (sorWaris.equals("1")){
						r1.add("ID_TARAFKPTG",1);
						r1.add("ID_SAUDARA",socWaris);
					}else if (sorWaris.equals("2")){
						r1.add("ID_TARAFKPTG",2);
						r1.add("ID_SAUDARA",socOB);
					}
					if (!NoKPBaru.equals("")){
						icYEAR = NoKPBaru.substring(0,2);
				 		icMONTH = NoKPBaru.substring(2,4);
				 		icDAY = NoKPBaru.substring(4,6);
				 		
				 		String yearx = "19"+icYEAR ;
					    
				    	int y = Integer.parseInt(yearx);
				    	int m = Integer.parseInt(icMONTH) - 1;
				    	int d = Integer.parseInt(icDAY);
					
				    	Calendar cal = new GregorianCalendar(y, m, d);
				    	Calendar now = new GregorianCalendar();
	
				    	int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
				    		if((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
				    				|| (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)
				    						&& cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH)))
				    		{
				        res--;
				    		}
				    		r1.add("umur",res);
						}
					
					if (!nokpbaru3.equals("")){
						String lastDigit = nokpbaru3.substring(3,4);
						int digitValue = Integer.parseInt(lastDigit);
						String gender = "";
						if (digitValue==0||digitValue==2||digitValue==4||digitValue==6||digitValue==8) {
							gender = "2"; //female
						}else{
							gender = "1"; //male
						}
						r1.add("jantina",gender);
					}else{
						r1.add("jantina",0);
					}
					r1.add("ID_PERMOHONAN",idPermohonan);
					r1.add("ID_MASUK",userid);
					r1.add("TARIKH_MASUK",r.unquote("sysdate"));
					r1.add("ID_KEMASKINI",userid);
					r1.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r1.getSQLInsert("TBLPPKPEMOHON");
					stmtB.executeUpdate(sql1);
					
					Statement stmtH = db.getStatement();
					/*UPDATE TABLEA
					SET (b, c, d) = (SELECT b1, c1, d1 from TABLEB WHERE TABLEB.a1 = TABLEA.a and TABLEB.e1 > 40)
					WHERE EXISTS (SELECT 1 from TABLEB WHERE TABLEB.a1 = TABLEA.a and TABLEB.e1 > 40)*/
					sql5 ="update TBLPPKPEMOHON set (NAMA_PEMOHON,NO_KP_BARU,NO_KP_LAMA,JENIS_KP,NO_KP_LAIN,UMUR,JANTINA,JENIS_AGAMA," +
						  "ALAMAT_1,ALAMAT_2,ALAMAT_3,BANDAR,POSKOD,NO_HP,NO_TEL,EMEL,NO_FAX,CATATAN,ID_TARAFKPTG," +
						  "ID_SAUDARA,ID_NEGERI,STATUS_PEGUAM,STATUS_PEMOHON) = (select NAMA_PEMOHON,NO_KP_BARU,NO_KP_LAMA,JENIS_KP," +
						  "NO_KP_LAIN,UMUR,JANTINA,JENIS_AGAMA, ALAMAT_1,ALAMAT_2,ALAMAT_3,BANDAR,POSKOD,NO_HP,NO_TEL,EMEL,NO_FAX," +
						  "CATATAN,ID_TARAFKPTG,ID_SAUDARA,ID_NEGERI,STATUS_PEGUAM,STATUS_PEMOHON from Tblppkpemohon " +
						  "where id_permohonan = "+ idPermohonanLama +") " +
						  "where id_permohonan = "+ idPermohonan +""; 
					stmtH.executeUpdate(sql5);
					
				}
				
				Statement stmtF = db.getStatement();
				SQLRenderer r5 = new SQLRenderer();
				r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
				r5.add("ID_PERMOHONAN",idPermohonan);
				r5.add("ID_SUBURUSANSTATUS",464); //436 status utk permohonan online
				r5.add("AKTIF",1);
				r5.add("ID_MASUK",userid);
				r5.add("TARIKH_MASUK",r.unquote("sysdate"));
				r5.add("ID_KEMASKINI",userid);
				r5.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				r5.add("ID_FAIL",idFail);
				sql2 = r5.getSQLInsert("tblrujsuburusanstatusfail");
				stmtF.executeUpdate(sql2);	
				
				Statement stmtT = db.getStatement();
				SQLRenderer r3 = new SQLRenderer();
				r3.add("id_permohonansimati",idpermohonansimati);
				r3.add("id_permohonan",idPermohonan);
				r3.add("ID_SIMATI",Integer.parseInt(idsimati));
				r3.add("ID_MASUK",userid);
				r3.add("TARIKH_MASUK",r.unquote("sysdate"));
				r3.add("ID_KEMASKINI",userid);
				r3.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql3 = r3.getSQLInsert("TBLPPKPERMOHONANSIMATI");
				stmtT.executeUpdate(sql3);
				
				Statement stmtHL = db.getStatement();
				/*UPDATE TABLEA
				SET (b, c, d) = (SELECT b1, c1, d1 from TABLEB WHERE TABLEB.a1 = TABLEA.a and TABLEB.e1 > 40)
				WHERE EXISTS (SELECT 1 from TABLEB WHERE TABLEB.a1 = TABLEA.a and TABLEB.e1 > 40)*/
				String sql15 ="update TBLPPKPERMOHONAN set NO_SUBJAKET = (select (NO_SUBJAKET)+1 FROM TBLPPKPERMOHONAN " +
					  "where id_permohonan = "+ idPermohonanLama +")" +
					  "where id_permohonan = "+ idPermohonan +""; 
				stmtHL.executeUpdate(sql15);
				
				if (wujudWaris==0){
				String idtaraf = "";
				if (sorWaris.equals("1")){
					idtaraf = socWaris;
				}else if (sorWaris.equals("2")){
					idtaraf = socOB;
				}
				
				Statement stmtCh = db.getStatement();
				String sql7="insert into tblppkob (" +
							"ID_OB," +
							"ID_SIMATI," +
							"NAMA_OB," +
							"NO_KP_BARU," +
							"NO_KP_LAMA," +
							"JENIS_KP," +
							"NO_KP_LAIN," +
							"JANTINA," +
							"ID_TARAFKPTG," +
							"ID_NEGERI," +
							"ID_SAUDARA," +
							"STATUS_OB," +
							"LAPIS," +
							"ID_PEMOHON," +
							"ID_PERMOHONANSIMATI," +
							"ID_MASUK," +
							"TARIKH_MASUK,UMUR,STATUS_HIDUP) values ("+ idob +"," +
							""+Integer.parseInt(idsimati)+"," +
							"'"+ NamaPemohon +"','"+NoKPBaru+"','"+NoKPLama+"','"+JenisKPLain+"','"+NoKpLain+"',(select jantina from users_online where no_kp_baru = '"+ NoKPBaru +"')," +
							"'"+ sorWaris +"',"+idusernegeri+",'"+ idtaraf +"',0,1,"+ idPemohon +"," +
							""+idpermohonansimati+","+userid+",sysdate,(select umur from tblppkpemohon where id_pemohon = "+ idPemohon +"),0)";
							stmtCh.executeUpdate(sql7);
				}
			
			}finally {
				if(db != null) db.close();
			}
	}
	
	public static String generateNoPermohonanOnlineSeksyen17(String idsimati,String usid,String idfailSeksyen8,String idPemohon) throws Exception {
		Db db = null;
			String sql = "";
			String sql1 = "";
			String sql2 = "";
			String sql3 = "";
			String sql4 = "";
			String sql5 = "";
			String sql6 = "";
			
			String output="";
			
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			int getYear = calendar.get(java.util.Calendar.YEAR);
			try
			{
				long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
//				long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
				long idPermohonan = DB.getNextID("TBLPPKPERMOHONAN_SEQ");
				long idob = DB.getNextID("TBLPPKOB_SEQ");
				long idpermohonansimati = DB.getNextID("TBLPPKPERMOHONANSIMATI_SEQ");

				String noFail = String.format("%06d",File.getSeqNo(2,02,382,0,0,false,false));
				String X = "JKPTG/PK/02/"+getYear+"/"+noFail;
				
				String icYEAR = "";
				String icMONTH = "";
				String icDAY = "";
				
				db = new Db();
				Statement stmtA = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_fail",idFail);
				r.add("id_seksyen",2);
				r.add("id_urusan",382);
				r.add("tarikh_daftar_fail",r.unquote("sysdate"));
//				r.add("id_negeri",idNegerimhn);
				r.add("id_suburusan",60);
				r.add("flag_fail",1);
				r.add("id_status",7); // active
				r.add("id_masuk",usid);
				r.add("tarikh_masuk",r.unquote("sysdate")); 
				sql = r.getSQLInsert("tblpfdfail");
				stmtA.executeUpdate(sql);
				
				Statement stmt = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				r2.add("ID_PERMOHONAN",idPermohonan);
				r2.add("ID_FAIL",idFail);
				r2.add("FLAG_JENIS_PERMOHONAN",0);
				r2.add("NO_PERMOHONAN_ONLINE",X);
				r2.add("ID_STATUS",160); //status permohonan online sek17
				r2.add("SEKSYEN",17);
				r2.add("TARIKH_MOHON",r.unquote("sysdate"));
				r2.add("TARIKH_MOHON_ONLINE",r.unquote("sysdate"));
				r2.add("ID_PERMOHONANTERDAHULU",idfailSeksyen8);
				r2.add("ID_PEMOHON",idPemohon);
				r2.add("ID_MASUK",usid);
				r2.add("TARIKH_MASUK",r.unquote("sysdate"));
				r2.add("ID_KEMASKINI",usid);
				r2.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql4 = r2.getSQLInsert("TBLPPKPERMOHONAN");
				myLogger.info("insert TBLPPKPERMOHONAN :: "+sql4);
				stmt.executeUpdate(sql4);
				
				output = ""+idPermohonan;
				
				Statement stmtF = db.getStatement();
				SQLRenderer r5 = new SQLRenderer();
				r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
				r5.add("ID_PERMOHONAN",idPermohonan);
				r5.add("ID_SUBURUSANSTATUS",464); //436 status utk permohonan online
				r5.add("AKTIF",1);
				r5.add("ID_MASUK",usid);
				r5.add("TARIKH_MASUK",r.unquote("sysdate"));
				r5.add("ID_KEMASKINI",usid);
				r5.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				r5.add("ID_FAIL",idFail);
				sql2 = r5.getSQLInsert("tblrujsuburusanstatusfail");
				stmtF.executeUpdate(sql2);
				
				Statement stmtT = db.getStatement();
				SQLRenderer r3 = new SQLRenderer();
				r3.add("id_permohonansimati",idpermohonansimati);
				r3.add("id_permohonan",idPermohonan);
//				r3.add("ID_SIMATI",Integer.parseInt(idsimati));
				r3.add("ID_SIMATI",idsimati);
				r3.add("ID_MASUK",usid);
				r3.add("TARIKH_MASUK",r.unquote("sysdate"));
				r3.add("ID_KEMASKINI",usid);
				r3.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql3 = r3.getSQLInsert("TBLPPKPERMOHONANSIMATI");
				stmtT.executeUpdate(sql3);
				myLogger.info("update TBLPPKPERMOHONANSIMATI :: "+sql3);
				
				Statement stmtHL = db.getStatement();
				String sql15 ="update TBLPPKPERMOHONAN set NO_SUBJAKET = (select (NO_SUBJAKET)+1 FROM TBLPPKPERMOHONAN " +
					  "where id_fail = "+ idfailSeksyen8 +")" +
					  "where id_permohonan = "+ idPermohonan +""; 
				stmtHL.executeUpdate(sql15);
				myLogger.info("update tblppkpermohonan :: "+sql15);
				
			}finally {
				if(db != null) db.close();
			}
			
		  return output;	
	}	
	
	public static int cntWujudWarisDariPemohon(String nokpbaru, String nokplama, String nokplain, String nokpbarusimati, String nokplamasimati, String nokplainsimati) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      String kpBaru = nokpbaru.trim();
		  String kpLama = nokplama.trim();
		  String kpLain = nokplain.trim();
		  String kpBarusimati = nokpbarusimati.trim();
		  String kpLamasimati = nokplamasimati.trim();
		  String kpLainsimati = nokplainsimati.trim();

	      sql = "Select count(a.id_ob) as cntWaris from tblppkob a, tblppkpermohonansimati b, tblppksimati m " +
	      		"where a.id_permohonansimati=b.id_permohonansimati and b.id_simati = m.id_simati ";
	      
	      String sqlwhere = "";
	      String sqlwhere1 = "";
	    //KP BARU SIMATI
	      if (kpBarusimati != "") {
				if (!kpBarusimati.trim().equals("")) {
					sqlwhere1 = sqlwhere1 + " UPPER(M.NO_KP_BARU) LIKE '%" + kpBarusimati.toUpperCase() + "%' ";
				}
			}
	    //KP LAMA SIMATI
	      if (kpLamasimati != "") {
				if (!kpLamasimati.trim().equals("")) {
					if (sqlwhere1 != "") {
						sqlwhere1 = sqlwhere1 + " OR UPPER(M.NO_KP_LAMA) LIKE '%" + kpLamasimati.toUpperCase() + "%' ";
					} else {
						sqlwhere1 = sqlwhere1 + " UPPER(M.NO_KP_LAMA) LIKE '%" + kpLamasimati.toUpperCase() + "%'";
					}
				}
			}
	    //KP LAIN SIMATI
	      if (kpLainsimati != "") {
				if (!kpLainsimati.trim().equals("")) {
					if (sqlwhere1 != "") {
						sqlwhere1 = sqlwhere1 + " OR UPPER(M.NO_KP_LAIN) LIKE '%" + kpLainsimati.toUpperCase() + "%'";
					} else {
						sqlwhere1 = sqlwhere1 + " UPPER(M.NO_KP_LAIN) LIKE '%" + kpLainsimati.toUpperCase() + "%'";
					}
				}
			}
	      
			//KP BARU PEMOHON
		      if (kpBaru != "") {
					if (!kpBaru.trim().equals("")) {
						sql = sql + "and UPPER(a.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%' AND ("+sqlwhere1+") ";
					}
				}
		    //KP LAMA PEMOHON
		      if (kpLama != "") {
					if (!kpLama.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(a.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%' AND ("+sqlwhere1+")";
						} else {
							sqlwhere = sqlwhere + " UPPER(a.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%' AND ("+sqlwhere1+")";
						}
					}
				}
		    //KP LAIN PEMOHON
		      if (kpLain != "") {
					if (!kpLain.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(a.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%' AND ("+sqlwhere1+")";
						} else {
							sqlwhere = sqlwhere + " UPPER(a.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%' AND ("+sqlwhere1+")";
						}
					}
				}
		      
		    
		      
		    if (sqlwhere != "") {
					sql = sql + " AND ( "+sqlwhere+" ) ";
			}
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      if (rs.next()) {
				return rs.getString("cntWaris")==null?0:Integer.parseInt(rs.getString("cntWaris"));
			} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getSek17PemohonInfo(String kpbaru, String kplama, String kplain, String nofail) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector v = new Vector();
		try {
			db = new Db();
			String kpBaru = kpbaru.trim();
			String kpLama = kplama.trim();
			String kpLain = kplain.trim();
			String failno = nofail.trim();
			String sql = "Select b.id_status as cntId from TBLPPKSIMATI a, TBLPPKPERMOHONAN b, TBLPPKPERMOHONANSIMATI m1 where " +
					     "a.id_simati = m1.id_simati and m1.id_permohonan = b.id_permohonan";
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + " AND UPPER(a.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					sql = sql + " AND UPPER(a.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					sql = sql + " AND UPPER(a.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
				}
			}
			if (failno != "") {
				if (!failno.trim().equals("")) {
					sql = sql + " AND UPPER(b.NO_PERMOHONAN_ONLINE) LIKE '%" + failno.toUpperCase() + "%'";
				}
			}
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = sql + " and rownum < 2 order by b.id_permohonan desc";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("idCnt", rs.getString("cntId")==null?"":rs.getString("cntId"));
				v.addElement(h);
			}
			return v;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public void setDataPenting(String id) throws Exception {
		Db db = null;
		listPenting.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.catatan");
			r.add("p.seksyen");
			
			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.jenis_pemiutang");
			r.add("ob.id_permohonansimati");
	
			r.add("ob.id_Simati",r.unquote("m1.id_Simati"));
			r.add("m1.id_Simati",r.unquote("m.id_Simati(+)"));
			r.add("m1.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
	
			r.add("m1.id_permohonan",id);
			
			sql = r.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati M1");
			sql = sql + " order by ob.id_permohonansimati desc";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;
			
			while(rs.next()) {		
				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob")==null?"":rs.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob")==null?"":rs.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob")==null?"":rs.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
				h.put("nokplama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
				h.put("noberanak", rs.getString("no_Surat_Beranak")==null?"":rs.getString("no_Surat_Beranak"));
				h.put("dob", rs.getString("tarikh_Lahir")==null?"":rs.getString("tarikh_Lahir"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina")==null?"":rs.getString("jantina"));
				h.put("umur", rs.getString("umur")==null?"":rs.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup")==null?"":rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg")==null?"":rs.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara")==null?"":rs.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama")==null?"":rs.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
				h.put("butiranhutang", rs.getString("butiran_Hutang")==null?"":rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang")==null?"":rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun")==null?"":rs.getString("no_Akaun"));
				h.put("pemiutang", rs.getString("jenis_pemiutang")==null?"":rs.getString("jenis_pemiutang"));
				h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
				listPenting.addElement(h);
				count++;
			}
		}
		finally {
			if(db != null) db.close();
		}
	}
	
	public static Vector setDataPentingData(String idpermohonansimati) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("distinct(ob.id_Simati)");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.catatan");
			
			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.jenis_pemiutang");
			r.add("ob.id_permohonansimati");
	
			r.add("ob.id_Simati",r.unquote("m1.id_Simati"));
			r.add("m1.id_Simati",r.unquote("m.id_Simati"));
	
			r.add("m.id_simati",idpermohonansimati);
			r.add("ob.id_tarafkptg",2);
			
			sql = r.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonansimati M1");
			sql = sql + " order by ob.id_permohonansimati desc";
			ResultSet rs = stmt.executeQuery(sql);
			Vector listPenting = new Vector();
			Hashtable h;
			Integer count = 0;
			
			while(rs.next()) {		
				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob")==null?"":rs.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob")==null?"":rs.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob")==null?"":rs.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
				h.put("nokplama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
				h.put("noberanak", rs.getString("no_Surat_Beranak")==null?"":rs.getString("no_Surat_Beranak"));
				h.put("dob", rs.getString("tarikh_Lahir")==null?"":rs.getString("tarikh_Lahir"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina")==null?"":rs.getString("jantina"));
				h.put("umur", rs.getString("umur")==null?"":rs.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup")==null?"":rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg")==null?"":rs.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara")==null?"":rs.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama")==null?"":rs.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
				h.put("butiranhutang", rs.getString("butiran_Hutang")==null?"":rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang")==null?"":rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun")==null?"":rs.getString("no_Akaun"));
				h.put("pemiutang", rs.getString("jenis_pemiutang")==null?"":rs.getString("jenis_pemiutang"));
				h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
				listPenting.addElement(h);
				count++;
			}
			return listPenting;
		}
		finally {
			if(db != null) db.close();
		}
	}
	
	public void setDataPeguam(String id2) throws Exception {
		Db db = null;
		listPeguam.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("pm.id_Pemohon");
		r.add("pg.id_Peguam");
		r.add("pg.nama_firma");
		r.add("pg.alamat1");
		r.add("pg.alamat2");
		r.add("pg.alamat3");
		r.add("pg.bandar");
		r.add("pg.poskod");
		r.add("pg.id_Negeri");
		r.add("pg.id_bandar");
		r.add("pg.no_Tel");
		r.add("pg.no_Fax");
		r.add("pg.no_Syarikat");
		r.add("pg.no_Rujukan_Firma");
		r.add("pg.nama_Firma");
		r.add("pg.emel");
	
		r.add("pg.id_peguam",r.unquote("pm.id_peguam"));
		r.add("pm.id_Pemohon",id2);
		
		sql = r.getSQLSelect("Tblppkpeguam pg, tblppkpeguampemohon pm");
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		
		while(rs.next()) {
			h = new Hashtable();
			h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
			h.put("idPeguam", rs.getString("id_Peguam")==null?"":rs.getString("id_Peguam"));
			h.put("namaPeguam", rs.getString("nama_firma")==null?"":rs.getString("nama_firma"));
			h.put("noRujukanFirma", rs.getString("no_Rujukan_Firma")==null?"":rs.getString("no_Rujukan_Firma"));
			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
			h.put("noTel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
			h.put("noFax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));
			h.put("noSyarikat", rs.getString("no_Syarikat")==null?"":rs.getString("no_Syarikat"));
			h.put("namaFirma", rs.getString("nama_Firma")==null?"":rs.getString("nama_Firma"));
			h.put("emel", rs.getString("emel")==null?"":rs.getString("emel"));
			h.put("idbandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
			listPeguam.addElement(h);
			//count++;
		}
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public static Vector setDataPeguamData(String id2) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("pm.id_Pemohon");
		r.add("pg.id_Peguam");
		r.add("pg.nama_firma");
		r.add("pg.alamat1");
		r.add("pg.alamat2");
		r.add("pg.alamat3");
		r.add("pg.bandar");
		r.add("pg.poskod");
		r.add("pg.id_Negeri");
		r.add("pg.id_bandar");
		r.add("pg.no_Tel");
		r.add("pg.no_Fax");
		r.add("pg.no_Syarikat");
		r.add("pg.no_Rujukan_Firma");
		r.add("pg.nama_Firma");
		r.add("pg.emel");
	
		r.add("pg.id_peguam",r.unquote("pm.id_peguam"));
		r.add("pg.id_Peguam",id2);
		
		sql = r.getSQLSelect("Tblppkpeguam pg, tblppkpeguampemohon pm");
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		Vector dataPeguam = new Vector();
		while(rs.next()) {
			h = new Hashtable();
			h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
			h.put("idPeguam", rs.getString("id_Peguam")==null?"":rs.getString("id_Peguam"));
			h.put("namaPeguam", rs.getString("nama_firma")==null?"":rs.getString("nama_firma"));
			h.put("noRujukanFirma", rs.getString("no_Rujukan_Firma")==null?"":rs.getString("no_Rujukan_Firma"));
			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
			h.put("noTel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
			h.put("noFax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));
			h.put("noSyarikat", rs.getString("no_Syarikat")==null?"":rs.getString("no_Syarikat"));
			h.put("namaFirma", rs.getString("nama_Firma")==null?"":rs.getString("nama_Firma"));
			h.put("emel", rs.getString("emel")==null?"":rs.getString("emel"));
			h.put("idbandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
			dataPeguam.addElement(h);
			//count++;
		}
			return dataPeguam;
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public void setDataSimati(String id) throws Exception {
		Db db = null;
		listSimati.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("p.id_Permohonan");
		r.add("s.id_Simati");
		r.add("s.no_Kp_Baru");
		r.add("s.no_Kp_Lama");
		r.add("s.jenis_Kp");
		r.add("s.no_Kp_Lain");
		r.add("s.nama_Simati");
		r.add("s.tarikh_Mati");
		r.add("s.nama_Lain");
		r.add("s.jantina");
		r.add("s.jenis_Warga");
		r.add("s.jenis_Agama");
		r.add("s.umur");
		r.add("s.id_Buktimati");
		r.add("s.tempat_Mati");
		r.add("s.no_Sijil_Mati");
		r.add("s.waktu_Kematian");
		r.add("s.jenis_Waktu_Mati");
		
		r.add("s.id_Buktimati");
		
		r.add("s.sebab_Mati");
		r.add("s.alamat_1");
		r.add("s.alamat_2");
		r.add("s.alamat_3");
		r.add("s.poskod");
		r.add("s.bandar");
		r.add("s.id_Negeri");
		r.add("s.catatan");
	 //   r.add("m.keterangan");
	  //  r.add("n.nama_Negeri");
	    r.add("po.id_Pemohon");
	   // r.add("m.kod");
	  //  r.add("n.kod_Negeri");
		
		r.add("po.id_pemohon",r.unquote("p.id_pemohon"));
		r.add("p.id_Permohonan",r.unquote("m1.id_Permohonan"));
		r.add("m1.id_simati",r.unquote("s.id_simati"));

		r.add("p.id_Permohonan",id);
		
		sql = r.getSQLSelect("Tblppksimati s, Tblppkpermohonan p, Tblppkpemohon po, Tblppkpermohonansimati m1");
		ResultSet rs = stmt.executeQuery(sql);
		myLogger.info("SQL 17 :: "+sql);
		Hashtable h;
		
		while(rs.next()) {
			h = new Hashtable();
			h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			h.put("noKpBaru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
			h.put("noKpBaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
			h.put("noKpBaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
			h.put("noKpBaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
			h.put("noKpLama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
			h.put("jenisKp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
			h.put("noKpLain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
			h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
			h.put("namaLain", rs.getString("nama_Lain")==null?"":rs.getString("nama_Lain"));
			h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
			h.put("masamati", rs.getString("waktu_Kematian")==null?"":rs.getString("waktu_Kematian"));
			
			Calendar cal = new GregorianCalendar();			
			String dateStart = rs.getString("tarikh_Mati");			
//			DateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
////	  		DateFormat ds = new SimpleDateFormat("E-MMM-dd hh:mm:ss");
//	  					
//			Date start_parsed = ds.parse(dateStart);
//			myLogger.info("start_parsed >> "+start_parsed);
//			
////	  		h.put("tarikh_kemaskini", rs.getString("tarikh_kemaskini")==null?"":sdf.format(rs.getDate("tarikh_kemaskini")));
//	  		
//	  		Calendar newStart = Calendar.getInstance();
//	  		newStart.setTime(start_parsed);
//	  		
//	        int s_day = newStart.get(Calendar.DATE);
//	  	    int s_month =  newStart.get(Calendar.MONTH);
//	  	    int s_year =  newStart.get(Calendar.YEAR);
//	  	  
//	  	    String st="";
//			
//			if(s_month<10)
//			{
//			 if(s_day<10)
//				{
//				 st="0"+(s_day)+"/"+0+(s_month+1)+"/"+s_year;
//				}
//			 else
//			 {
//				
//				 st=s_day+"/"+0+(s_month+1)+"/"+s_year;
//			 }
//			}
//			else
//			{
//				if(s_day<10)
//				{
//					
//					 st="0"+(s_day)+"/"+0+(s_month+1)+"/"+s_year;
//				}
//			 else
//			 {	
//				 
//				 st=s_day+"/"+(s_month+1)+"/"+s_year;
//
//			 }
//			}
//			h.put("tarikhMati",st);
			h.put("tarikhMatidb",rs.getString("tarikh_Mati")==null?"":rs.getString("tarikh_Mati"));
			h.put("jantina", rs.getString("jantina")==null?"":rs.getString("jantina"));
			h.put("jenisWarga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
			h.put("jenisAgama", rs.getString("jenis_Agama")==null?"":rs.getString("jenis_Agama"));
			h.put("umur", rs.getString("umur")==null?"":rs.getString("umur"));
			h.put("idBuktimati", rs.getString("id_Buktimati")==null?"":rs.getString("id_Buktimati"));
			h.put("tempatMati", rs.getString("tempat_Mati")==null?"":rs.getString("tempat_Mati"));
			h.put("noSijilMati", rs.getString("no_Sijil_Mati")==null?"":rs.getString("no_Sijil_Mati"));
			h.put("masaMati", rs.getString("waktu_Kematian")==null?"":rs.getString("waktu_Kematian"));
			h.put("jeniswaktumati", rs.getString("jenis_Waktu_Mati")==null?"":rs.getString("jenis_Waktu_Mati"));
			h.put("sebabMati", rs.getString("sebab_Mati")==null?"":rs.getString("sebab_Mati"));
			h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
			h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
			h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
			h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
			h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			listSimati.addElement(h);
		}
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public void setDataHTA(String idpermohonansimati) throws Exception {
		Db db = null;
		listHTA.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("h.id_Hta");
		r.add("h.no_Hakmilik");
		r.add("h.id_Simati");
		r.add("h.no_Pt");
		r.add("h.nilai_Hta_Tarikhmohon");
		r.add("h.nilai_Hta_Tarikhmati");
		r.add("h.id_Kategori");
		r.add("h.id_Jenishm");
		r.add("h.id_Jenispb");
		r.add("h.id_Negeri");
		r.add("h.id_Daerah");
		r.add("h.id_Luas");
		r.add("h.id_Mukim");
		r.add("h.luas_Hmp");
		r.add("h.luas");
		r.add("h.no_Cagaran");
		r.add("h.no_Pajakan");
		r.add("h.jenis_Tnh");
		r.add("h.ba_Simati");
		r.add("h.bb_Simati");
		r.add("h.jenis_Hta");
		r.add("h.tanggungan");
		r.add("h.no_Perserahan");
		r.add("h.catatan");
		r.add("h.status_Pemilikan");
		r.add("h.id_permohonansimati");

		r.add("h.id_simati",r.unquote("m1.id_simati"));
		r.add("m1.id_simati",r.unquote("s.id_simati(+)"));
		r.add("m1.id_permohonan",r.unquote("p.id_permohonan(+)"));
		r.add("h.id_simati",r.unquote("s.id_simati"));
		
		r.add("h.id_permohonansimati",idpermohonansimati);
		String status="Y";
		String baru="T";
		r.add("h.jenis_Hta",status);
		r.add("h.flag_pa",baru);
		r.add("h.flag_pt",baru);
		r.add("h.flag_selesai",baru);
		sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s, Tblppkpermohonansimati M1, Tblppkpermohonan p");
		
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		
		while(rs.next()) {
			h = new Hashtable();
			h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
			h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));			
			h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
			h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getString("nilai_Hta_Tarikhmohon"));
			h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getString("nilai_Hta_Tarikhmati"));
			h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
			h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
			h.put("pemilikan", rs.getString("id_Jenispb")==null?"":rs.getString("id_Jenispb"));
			h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
			h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
			h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
			h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
			h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
			h.put("nocagaran", rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
			h.put("nopajakan", rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
			h.put("jenistanah", rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
			h.put("basimati", rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
			h.put("bbsimati", rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
			h.put("jenishta", rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
			h.put("tanggungan", rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
			h.put("jenisluas", rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
			h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("noperserahan", rs.getString("no_Perserahan")==null?"":rs.getString("no_Perserahan"));
			h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
			listHTA.addElement(h);
		}
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public static Vector getDataHTA(String idpermohonansimati) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector listHTA = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("h.id_Hta");
		r.add("h.no_Hakmilik");
		r.add("h.id_Simati");
		r.add("h.no_Pt");
		r.add("h.nilai_Hta_Tarikhmohon");
		r.add("h.nilai_Hta_Tarikhmati");
		r.add("h.id_Kategori");
		r.add("h.id_Jenishm");
		r.add("h.id_Jenispb");
		r.add("h.id_Negeri");
		r.add("h.id_Daerah");
		r.add("h.id_Luas");
		r.add("h.id_Mukim");
		r.add("h.luas_Hmp");
		r.add("h.luas");
		r.add("h.no_Cagaran");
		r.add("h.no_Pajakan");
		r.add("h.jenis_Tnh");
		r.add("h.ba_Simati");
		r.add("h.bb_Simati");
		r.add("h.jenis_Hta");
		r.add("h.tanggungan");
		r.add("h.no_Perserahan");
		r.add("h.catatan");
		r.add("h.status_Pemilikan");
		r.add("n.nama_negeri");
		r.add("d.nama_daerah");
		r.add("m.nama_mukim");
		r.add("h.id_permohonansimati");
			
		String status="Y";
		
		r.add("h.id_simati",r.unquote("m1.id_simati"));
		r.add("h.id_Negeri",r.unquote("n.id_Negeri"));
		r.add("h.id_Daerah",r.unquote("d.id_Daerah"));
		r.add("h.id_Mukim",r.unquote("m.id_Mukim"));
		r.add("m1.id_simati",r.unquote("s.id_simati(+)"));
		r.add("m1.id_permohonan",r.unquote("p.id_permohonan(+)"));
		r.add("m1.id_permohonansimati",r.unquote("h.id_permohonansimati"));
		r.add("h.jenis_Hta",status);
		r.add("m1.id_permohonansimati",idpermohonansimati);
		
		r.add("h.flag_pa","T");
		r.add("h.flag_pt","T");
		r.add("h.flag_selesai","T");
		
		sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s, tblrujnegeri n, tblrujdaerah d, tblrujmukim m, Tblppkpermohonansimati M1, Tblppkpermohonan p");
		sql = sql + " order by h.id_hta desc";
		
		System.out.println("harta baru :: "+sql);
		
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		
		while(rs.next()) {
			h = new Hashtable();
			h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
			h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));			
			h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
			h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getString("nilai_Hta_Tarikhmohon"));
			h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getString("nilai_Hta_Tarikhmati"));
			h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
			h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
			h.put("pemilikan", rs.getString("id_Jenispb")==null?"":rs.getString("id_Jenispb"));
			h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
			h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
			h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
			h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
			h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
			h.put("nocagaran", rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
			h.put("nopajakan", rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
			h.put("jenistanah", rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
			h.put("basimati", rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
			h.put("bbsimati", rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
			h.put("jenishta", rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
			h.put("tanggungan", rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
			h.put("jenisluas", rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
			h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("noperserahan", rs.getString("no_Perserahan")==null?"":rs.getString("no_Perserahan"));
			h.put("namanegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
			h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			h.put("namamukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
			h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
			listHTA.addElement(h);
		}
		return listHTA;
		}
		finally {
			if(db != null) db.close();
		}
 }

		public void setDataHtath(String id) throws Exception{
			Db db = null;
			listHtath.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("hta.id_Hta");
				r.add("hta.id_Negeri");
				r.add("hta.id_Daerah");
				r.add("hta.id_Mukim");
				r.add("hta.no_Perjanjian");
				r.add("hta.no_Pt");
				r.add("hta.ba_Simati");
				r.add("hta.bb_Simati");
				r.add("n.nama_Negeri");
				r.add("d.nama_Daerah");
				r.add("m.nama_Mukim");
				r.add("sm.id_Simati");
				r.add("sm.id_Permohonan");
				r.add("p.id_Permohonan");
				
				r.add("hta.id_Negeri",r.unquote("n.id_Negeri(+)"));
				r.add("hta.id_Daerah",r.unquote("d.id_Daerah(+)"));
				r.add("hta.id_Mukim",r.unquote("m.id_Mukim(+)"));
				r.add("hta.id_Simati",r.unquote("sm.id_Simati(+)"));
				r.add("sm.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
				
				r.add("p.id_Permohonan",id);
				
				
				sql = r.getSQLSelect("Tblppkpermohonan p, Tblppkhta hta, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblppksimati sm");
				ResultSet rs = stmt.executeQuery(sql);
				//Vector list = new Vector(;
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_Permohonan", rs.getString("id_Permohonan"));
					h.put("id_Hta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
					h.put("id_Negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
					h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
					h.put("id_Daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
					h.put("id_Mukim",rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
					h.put("no_Perjanjian",rs.getString("no_Perjanjian")==null?"":rs.getString("no_Perjanjian"));
					h.put("no_Pt",rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
					h.put("ba_Simati",rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
					h.put("bb_Simati",rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
					h.put("nama_Negeri",rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
					h.put("nama_Daerah",rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
					h.put("nama_Mukim",rs.getString("nama_Mukim")==null?"":rs.getString("nama_Mukim"));
					
					listHtath.addElement(h);
					bil++;	
				}
				//return list;
			}finally {
				if(db != null) db.close();
			}
			}
	
		public void setDataSaksi(String simati) throws Exception {
			Db db = null;
			listSaksi.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.catatan");
			r.add("ob.id_Simati",r.unquote("m.id_Simati"));
			r.add("ob.id_simati",simati);
			int k=14;
			r.add("ob.id_Tarafkptg",k);
			sql = r.getSQLSelect("Tblppkob ob, Tblppksimati m");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;
			
			while(rs.next()) {		
				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob")==null?"":rs.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob")==null?"":rs.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob")==null?"":rs.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
				h.put("nokplama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
				h.put("noberanak", rs.getString("no_Surat_Beranak")==null?"":rs.getString("no_Surat_Beranak"));
				h.put("dob", rs.getString("tarikh_Lahir")==null?"":rs.getString("tarikh_Lahir"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina")==null?"":rs.getString("jantina"));
				h.put("umur", rs.getString("umur")==null?"":rs.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup")==null?"":rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg")==null?"":rs.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara")==null?"":rs.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama")==null?"":rs.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
				h.put("warga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
				listSaksi.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
		}
		
		public void setDataHTAXbyIdHtaam(String idhtaam) throws Exception {
			Db db = null;
			listHTAXbyIdHtaam.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			r.add("h.id_Hta");
			r.add("h.no_Hakmilik");
			r.add("h.id_Simati");
			r.add("h.no_Pt");
			r.add("h.nilai_Hta_Tarikhmohon");
			r.add("h.nilai_Hta_Tarikhmati");
			r.add("h.id_Kategori");
			r.add("h.id_Jenishm");
			r.add("h.id_Jenispb");
			r.add("h.id_Negeri");
			r.add("h.id_Daerah");
			r.add("h.id_Luas");
			r.add("h.id_Mukim");
			r.add("h.luas_Hmp");
			r.add("h.luas");
			r.add("h.no_Cagaran");
			r.add("h.no_Pajakan");
			r.add("h.jenis_Tnh");
			r.add("h.ba_Simati");
			r.add("h.bb_Simati");
			r.add("h.jenis_Hta");
			r.add("h.tanggungan");
			r.add("h.no_Perserahan");
			r.add("h.catatan");
			r.add("h.status_Pemilikan");
			
			r.add("h.nama_Pemaju");
			r.add("h.alamat_Pemaju1");
			r.add("h.alamat_Pemaju2");
			r.add("h.alamat_Pemaju3");
			r.add("h.poskod_Pemaju");
			r.add("h.bandar_Pemaju");
			r.add("h.id_Negeripemaju");
			r.add("h.alamat_Hta1");
			r.add("h.alamat_Hta2");
			r.add("h.alamat_Hta3");
			r.add("h.poskod_Hta");
			r.add("h.bandar_Hta");
			r.add("h.no_Perjanjian");
			r.add("h.tarikh_Perjanjian");
			r.add("h.nama_Rancangan");
			r.add("h.no_Roh");
			r.add("h.no_Lot_Id");
			r.add("h.flag_Kategori_Hta");
			r.add("h.jenis_kepentingan");
			String status="T";
			r.add("h.id_simati",r.unquote("s.id_simati"));
			r.add("h.jenis_Hta",status);
			r.add("h.id_Hta",idhtaam);
			sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s");
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("status_Pemilikan")==null?"":rs.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas")==null?"":rs.getDouble("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("noperserahan", rs.getString("no_Perserahan")==null?"":rs.getString("no_Perserahan"));
				h.put("flag", rs.getString("flag_Kategori_Hta")==null?"":rs.getString("flag_Kategori_Hta"));
				h.put("namapemaju", rs.getString("nama_Pemaju")==null?"":rs.getString("nama_Pemaju"));
				h.put("alamatpemaju1", rs.getString("alamat_Pemaju1")==null?"":rs.getString("alamat_Pemaju1"));
				h.put("alamatpemaju2", rs.getString("alamat_Pemaju2")==null?"":rs.getString("alamat_Pemaju2"));
				h.put("alamatpemaju3", rs.getString("alamat_Pemaju3")==null?"":rs.getString("alamat_Pemaju3"));
				h.put("poskodpemaju", rs.getString("poskod_Pemaju")==null?"":rs.getString("poskod_Pemaju"));
				h.put("bandarpemaju", rs.getString("bandar_Pemaju")==null?"":rs.getString("bandar_Pemaju"));
				h.put("negeripemaju", rs.getString("id_Negeripemaju")==null?"":rs.getString("id_Negeripemaju"));
				h.put("alamathta1", rs.getString("alamat_Hta1")==null?"":rs.getString("alamat_Hta1"));
				h.put("alamathta2", rs.getString("alamat_Hta2")==null?"":rs.getString("alamat_Hta2"));
				h.put("alamathta3", rs.getString("alamat_Hta3")==null?"":rs.getString("alamat_Hta3"));
				h.put("poskodhta", rs.getString("poskod_Hta")==null?"":rs.getString("poskod_Hta"));
				h.put("bandarhta", rs.getString("bandar_Hta")==null?"":rs.getString("bandar_Hta"));
				h.put("noperjanjian", rs.getString("no_Perjanjian")==null?"":rs.getString("no_Perjanjian"));
				h.put("tarikhperjanjian", rs.getString("tarikh_Perjanjian")==null?"":sdf.format(rs.getDate("tarikh_Perjanjian")));
				h.put("namarancangan", rs.getString("nama_Rancangan")==null?"":rs.getString("nama_Rancangan"));
				h.put("noroh", rs.getString("no_Roh")==null?"":rs.getString("no_Roh"));
				h.put("nolot", rs.getString("no_Lot_Id")==null?"":rs.getString("no_Lot_Id"));
				h.put("jeniskepentingan", rs.getString("jenis_kepentingan")==null?"":rs.getString("jenis_kepentingan"));
				listHTAXbyIdHtaam.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
		}
		
		public void setDataHTAbyIdHtaam(String idhtaam) throws Exception {
			Db db = null;
			listHTAbyIdHtaam.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			r.add("h.id_Hta");
			r.add("h.no_Hakmilik");
			r.add("h.id_Simati");
			r.add("h.no_Pt");
			r.add("h.nilai_Hta_Tarikhmohon");
			r.add("h.nilai_Hta_Tarikhmati");
			r.add("h.id_Kategori");
			r.add("h.id_Jenishm");
			r.add("h.id_Jenispb");
			r.add("h.id_Negeri");
			r.add("h.id_Daerah");
			r.add("h.id_Luas");
			r.add("h.id_Mukim");
			r.add("h.luas_Hmp");
			r.add("h.luas");
			r.add("h.no_Cagaran");
			r.add("h.no_Pajakan");
			r.add("h.jenis_Tnh");
			r.add("h.ba_Simati");
			r.add("h.bb_Simati");
			r.add("h.jenis_Hta");
			r.add("h.tanggungan");
			r.add("h.no_Perserahan");
			r.add("h.catatan");
			r.add("h.status_Pemilikan");
			r.add("h.id_permohonansimati");
				
			String status="Y";
			
			r.add("h.id_simati",r.unquote("s.id_simati"));
			r.add("h.jenis_Hta",status);
			r.add("h.id_Hta",idhtaam);
			sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s");
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("status_Pemilikan")==null?"":rs.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("noperserahan", rs.getString("no_Perserahan")==null?"":rs.getString("no_Perserahan"));
				h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
				listHTAbyIdHtaam.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
		}

		public void setDataPentingbyIDOB(String id, String idpermohonansimati) throws Exception {
			Db db = null;
			listPentingbyIDOB.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");			
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");				
			r.add("ob.catatan");
			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.no_tel");
			r.add("ob.no_hp");
			r.add("n.nama_negeri");
			r.add("ob.jenis_pemiutang");
			r.add("ob.id_permohonansimati");
			
			r.add("ob.id_Simati",r.unquote("m1.id_Simati"));
			r.add("m1.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
			r.add("m1.id_Permohonan",r.unquote("m.id_simati(+)"));
			r.add("ob.id_Negeri",r.unquote("n.id_negeri(+)"));
			
			r.add("ob.id_Ob",id);
			r.add("ob.id_permohonansimati",idpermohonansimati);
			
			sql = r.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati M1, Tblrujnegeri n");
			sql = sql + " group by ob.id_Simati, ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, " +
					"ob.no_Surat_Beranak, ob.tarikh_Lahir, ob.jantina, ob.umur, ob.alamat_1, ob.alamat_2, ob.alamat_3, ob.bandar, " +
					"ob.poskod, ob.no_Tel, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri, ob.id_Saudara, ob.jenis_Agama, " +
					"ob.jenis_Warga, ob.catatan, ob.butiran_Hutang, ob.nilai_Hutang, ob.no_Akaun, ob.no_tel, ob.no_hp, n.nama_negeri, " +
					"ob.jenis_pemiutang, ob.id_permohonansimati";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;
			
			while(rs.next()) {		
				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob")==null?"":rs.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob")==null?"":rs.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob")==null?"":rs.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
				h.put("nokplama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
				h.put("noberanak", rs.getString("no_Surat_Beranak")==null?"":rs.getString("no_Surat_Beranak"));
				h.put("dob",rs.getDate("tarikh_Lahir")==null?"":sdf.format(rs.getDate("tarikh_Lahir")));			
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina")==null?"":rs.getString("jantina"));
				h.put("umur", rs.getString("umur")==null?"":rs.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup")==null?"":rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg")==null?"":rs.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara")==null?"":rs.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama")==null?"":rs.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
				h.put("butiranhutang", rs.getString("butiran_Hutang")==null?"":rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang")==null?"":rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun")==null?"":rs.getString("no_Akaun"));
				h.put("negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				h.put("notel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
				h.put("nohp", rs.getString("no_hp")==null?"":rs.getString("no_hp"));
				h.put("pemiutang", rs.getString("jenis_pemiutang")==null?"":rs.getString("jenis_pemiutang"));
				h.put("id_permohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
				listPentingbyIDOB.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
		}
		
		public void setDataPenghutang(String id) throws Exception {
			Db db = null;
			listPenghutang.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Penghutang");	
			r.add("ob.id_Simati");
			r.add("ob.id_Penghutang");
			r.add("ob.nama_Penghutang");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");				
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.id_Negeri");					
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");					
			r.add("ob.jenis_Penghutang");					
			r.add("ob.butiran_Hutang");
			r.add("ob.jumlah_Hutang");
			r.add("ob.no_Akaun");	
			r.add("p.seksyen");	
			r.add("ob.id_permohonansimati");	
			
			r.add("ob.id_simati",r.unquote("m1.id_simati"));
			r.add("m1.id_simati",r.unquote("m.id_simati(+)"));
			r.add("m1.id_permohonan",r.unquote("p.id_permohonan(+)"));
			
			r.add("m1.id_permohonansimati",id);
			
			sql = r.getSQLSelect("Tblppkpenghutang ob, Tblppksimati m, Tblppkpermohonansimati M1, Tblppkpermohonan p");
			sql = sql + " order by ob.id_Penghutang desc";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;
			
			while(rs.next()) {		
				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Penghutang")==null?"":rs.getString("id_Penghutang"));
				h.put("nama_Ob", rs.getString("nama_Penghutang")==null?"":rs.getString("nama_Penghutang"));
				h.put("jenishutang", rs.getString("jenis_Penghutang")==null?"":rs.getString("jenis_Penghutang"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
				h.put("nokplama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				h.put("agama", rs.getString("jenis_Agama")==null?"":rs.getString("jenis_Agama"));
				h.put("warga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
				h.put("butiranhutang", rs.getString("butiran_Hutang")==null?"":rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("jumlah_Hutang")==null?"":Utils.format2Decimal(rs.getDouble("jumlah_Hutang")));
				h.put("noakaun", rs.getString("no_Akaun")==null?"":rs.getString("no_Akaun"));
				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
				h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
				listPenghutang.addElement(h);
				count++;
			}
			}
			finally {
				if(db != null) db.close();
			}
		}

		public void setDataPenghutangbyIDOB(String id, String idPemohonansimati) throws Exception {
			Db db = null;
			listPenghutangbyIDOB.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Penghutang");	
			r.add("ob.id_Simati");
			r.add("ob.id_Penghutang");
			r.add("ob.nama_Penghutang");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");				
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.id_Negeri");					
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");					
			r.add("ob.jenis_Penghutang");					
			r.add("ob.butiran_Hutang");
			r.add("ob.jumlah_Hutang");
			r.add("ob.no_Akaun");	
			r.add("ob.id_permohonansimati");
			r.add("ob.id_bandar");
			
			r.add("ob.id_Simati",r.unquote("m.id_Simati"));
			r.add("ob.id_Penghutang",id);
			
			sql = r.getSQLSelect("Tblppkpenghutang ob, Tblppksimati m");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;
			
			while(rs.next()) {		
				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Penghutang")==null?"":rs.getString("id_Penghutang"));
				h.put("nama_Ob", rs.getString("nama_Penghutang")==null?"":rs.getString("nama_Penghutang"));
				h.put("jenishutang", rs.getString("jenis_Penghutang")==null?"":rs.getString("jenis_Penghutang"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
				h.put("nokplama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				h.put("agama", rs.getString("jenis_Agama")==null?"":rs.getString("jenis_Agama"));
				h.put("warga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
				h.put("butiranhutang", rs.getString("butiran_Hutang")==null?"":rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("jumlah_Hutang")==null?"":rs.getDouble("jumlah_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun")==null?"":rs.getString("no_Akaun"));
				h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
				h.put("idbandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
				listPenghutangbyIDOB.addElement(h);
				count++;
			}
			}
			finally {
				if(db != null) db.close();
			}
		}

		public static Vector semakDataSimati(String id) throws Exception{
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector listDataSimati = new Vector();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("P.NO_KP_BARU");
				r.add("P.NO_KP_LAMA");
				r.add("P.JENIS_KP");
				r.add("P.NO_KP_LAIN ");
				r.add("P.NAMA_PEMOHON");
				r.add("P.ID_PEMOHON");
				r.add("PP.NO_PERMOHONAN_ONLINE");
				r.add("PP.FLAG_HUBUNGAN_PEMOHON");
				r.add("PP.ID_HUBUNGANPEMOHON");
				r.add("PP.TARIKH_MOHON_ONLINE");
				r.add("PP.ID_PERMOHONAN");
				r.add("M.ID_SIMATI");
				r.add("M.NAMA_SIMATI");
				r.add("M.NAMA_LAIN");
				r.add("M.NO_KP_BARU");
				r.add("M.NO_KP_LAMA");
				r.add("M.JENIS_KP");
				r.add("M.NO_KP_LAIN");
				r.add("M.UMUR");
				r.add("M.JANTINA");
				r.add("M.NO_SIJIL_MATI");
				r.add("M.TEMPAT_MATI");
				r.add("M.ALAMAT_1");
				r.add("M.ALAMAT_2");
				r.add("M.ALAMAT_3");
				r.add("M.BANDAR");
				r.add("M.POSKOD");
				r.add("M.TARIKH_MATI");
				r.add("M.WAKTU_KEMATIAN");
				r.add("M.JENIS_WAKTU_MATI");
				r.add("M.SEBAB_MATI");
				r.add("M.CATATAN");
				r.add("M.ID_NEGERI");
				r.add("M.ID_BUKTIMATI");
				r.add("M.JENIS_AGAMA");
				r.add("M.JENIS_WARGA");
				r.add("M.TARIKH_KEMASKINI");
				r.add("M1.ID_PERMOHONANSIMATI");
				r.add("PP.ID_NEGERIMHN");
				r.add("PP.ID_DAERAHMHN");
				r.add("P.ID_TARAFKPTG");
				r.add("P.ID_SAUDARA");
				r.add("PP.ID_STATUS");
				r.add("M.ID_BANDAR");
				r.add("PP.ID_PERMOHONANTERDAHULU");
				r.add("PP.ID_FAIL");
				r.add("PP.BATAL_KUASA_PENTADBIR");
				r.add("PP.LANTIK_PENTADBIR");
				r.add("PP.BATAL_P_AMANAH");
				r.add("PP.LANTIK_P_AMANAH");
				r.add("PP.HARTA_TINGGAL");
				
				r.add("P.ID_PEMOHON",r.unquote("PP.ID_PEMOHON"));
				r.add("PP.ID_PERMOHONAN",r.unquote("M1.ID_PERMOHONAN"));
				r.add("M1.ID_SIMATI",r.unquote("M.ID_SIMATI(+)"));
				
				r.add("PP.ID_PERMOHONAN",id);
				//r.add("PP.SEKSYEN",17);
				
				
				sql = r.getSQLSelect("Tblppkpermohonan PP, Tblppkpemohon P, Tblppksimati M, Tblppkpermohonansimati M1");
				System.out.println("SQL test :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("idsimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("namasimati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
					h.put("namalainsimati", rs.getString("NAMA_LAIN")==null?"":rs.getString("NAMA_LAIN"));
					h.put("nokpbarusimati1", rs.getString(15)==null?"":rs.getString(15).substring(0,6));
					h.put("nokpbarusimati2", rs.getString(15)==null?"":rs.getString(15).substring(6,8));
					h.put("nokpbarusimati3", rs.getString(15)==null?"":rs.getString(15).substring(8,12));
					h.put("nokplamasimati", rs.getString(16)==null?"":rs.getString(16));
					h.put("jeniskpsimati", rs.getString(17)==null?"":rs.getString(17));
					h.put("nokplainsimati", rs.getString(18)==null?"":rs.getString(18));
					h.put("umursimati", rs.getString("UMUR")==null?"":rs.getString("UMUR"));
					h.put("jantinasimati", rs.getString("JANTINA")==null?"":rs.getString("JANTINA"));
					h.put("nosijilsimati", rs.getString("NO_SIJIL_MATI")==null?"":rs.getString("NO_SIJIL_MATI"));
					h.put("tmptmatisimati", rs.getString("TEMPAT_MATI")==null?"":rs.getString("TEMPAT_MATI"));
					h.put("alamat1simati", rs.getString("ALAMAT_1")==null?"":rs.getString("ALAMAT_1"));
					h.put("alamat2simati", rs.getString("ALAMAT_2")==null?"":rs.getString("ALAMAT_2"));
					h.put("alamat3simati", rs.getString("ALAMAT_3")==null?"":rs.getString("ALAMAT_3"));
					h.put("bandarsimati", rs.getString("BANDAR")==null?"":rs.getString("BANDAR"));
					h.put("poskodsimati", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
					h.put("tarikhmati", rs.getString("TARIKH_MATI")==null?"":sdf.format(rs.getDate("TARIKH_MATI")));
					h.put("waktukematian", rs.getString("WAKTU_KEMATIAN")==null?"":rs.getString("WAKTU_KEMATIAN"));
					h.put("jeniswaktumati", rs.getString("JENIS_WAKTU_MATI")==null?"":rs.getString("JENIS_WAKTU_MATI"));
					h.put("sebabmati", rs.getString("SEBAB_MATI")==null?"":rs.getString("SEBAB_MATI"));
					h.put("catatan", rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
					h.put("idNegeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("idBuktiMati", rs.getString("ID_BUKTIMATI")==null?"":rs.getString("ID_BUKTIMATI"));
					h.put("jenisAgama", rs.getString("JENIS_AGAMA")==null?"":rs.getString("JENIS_AGAMA"));
					h.put("jenisWarga", rs.getString("JENIS_WARGA")==null?"":rs.getString("JENIS_WARGA"));
					h.put("tarikhKkini", rs.getString("TARIKH_KEMASKINI")==null?"":rs.getString("TARIKH_KEMASKINI"));
					h.put("idPemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
					h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("flaghubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
					h.put("idpermohonansimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
					h.put("idnegerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
					h.put("iddaerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
					h.put("idtarafkptg", rs.getString("ID_TARAFKPTG")==null?"":rs.getString("ID_TARAFKPTG"));
					h.put("idsaudara", rs.getString("ID_SAUDARA")==null?"":rs.getString("ID_SAUDARA"));
					h.put("nopermohonanonline", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
					h.put("idstatus", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idhubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
					h.put("idBandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
					h.put("idpermohonanterdahulu", rs.getString("ID_PERMOHONANTERDAHULU")==null?"":rs.getString("ID_PERMOHONANTERDAHULU"));
					h.put("idfail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("batalkuasatadbir", rs.getString("BATAL_KUASA_PENTADBIR")==null?"":rs.getString("BATAL_KUASA_PENTADBIR"));
					h.put("lantiktadbir", rs.getString("LANTIK_PENTADBIR")==null?"":rs.getString("LANTIK_PENTADBIR"));
					h.put("batalamanah", rs.getString("BATAL_P_AMANAH")==null?"":rs.getString("BATAL_P_AMANAH"));
					h.put("lantikamanah", rs.getString("LANTIK_P_AMANAH")==null?"":rs.getString("LANTIK_P_AMANAH"));
					h.put("hartatinggal", rs.getString("HARTA_TINGGAL")==null?"":rs.getString("HARTA_TINGGAL"));
					listDataSimati.addElement(h);
				}
				return listDataSimati;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public static Vector getDataHTAX(String idpermohonansimati) throws Exception {
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector listHTAX = new Vector();
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
				r.add("h.id_Hta");
				r.add("h.no_Hakmilik");
				r.add("h.id_Simati");
				r.add("h.no_Pt");
				r.add("h.nilai_Hta_Tarikhmohon");
				r.add("h.nilai_Hta_Tarikhmati");
				r.add("h.id_Kategori");
				r.add("h.id_Jenishm");
				r.add("h.id_Jenispb");
				r.add("h.id_Negeri");
				r.add("h.id_Daerah");
				r.add("h.id_Luas");
				r.add("h.id_Mukim");
				r.add("h.luas_Hmp");
				r.add("h.luas");
				r.add("h.no_Cagaran");
				r.add("h.no_Pajakan");
				r.add("h.jenis_Tnh");
				r.add("h.ba_Simati");
				r.add("h.bb_Simati");
				r.add("h.jenis_Hta");
				r.add("h.tanggungan");
				r.add("h.no_Perserahan");
				r.add("h.catatan");
				r.add("h.status_Pemilikan");
				r.add("h.no_Perjanjian");
				r.add("h.no_Roh");
				r.add("n.nama_negeri");
				r.add("d.nama_daerah");
				r.add("m.nama_mukim");
				r.add("h.id_permohonansimati");
				r.add("h.flag_kategori_hta");
				r.add("h.alamat_hta1");
				r.add("h.alamat_hta2");
				r.add("h.alamat_hta3");
				r.add("h.no_roh");
				r.add("h.no_lot_id");
				r.add("b.keterangan");
				r.add("h.poskod_hta");
				
				r.add("h.id_simati",r.unquote("m1.id_simati"));
				r.add("h.id_Negeri",r.unquote("n.id_negeri"));
				r.add("h.id_Daerah",r.unquote("d.id_daerah"));
				r.add("h.id_Mukim",r.unquote("m.id_mukim"));
				r.add("m1.id_simati",r.unquote("s.id_simati(+)"));
				r.add("m1.id_permohonan",r.unquote("p.id_permohonan(+)"));
				r.add("m1.id_permohonansimati",r.unquote("h.id_permohonansimati"));
				r.add("h.id_bandarhta",r.unquote("b.id_bandar(+)"));
				
				String status="T";
				r.add("h.jenis_Hta",status);
				r.add("M1.id_permohonansimati",idpermohonansimati);
				r.add("h.flag_pa","T");
				r.add("h.flag_pt","T");
				r.add("h.flag_selesai","T");
				
				sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblppkpermohonansimati M1, Tblppkpermohonan p, Tblrujbandar b");
				sql = sql + " order by h.id_Hta desc";
				System.out.println("htaamx baru-->>"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));			
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getString("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getString("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb")==null?"":rs.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("noperserahan", rs.getString("no_Perserahan")==null?"":rs.getString("no_Perserahan"));
				h.put("noperjanjian", rs.getString("no_Perjanjian")==null?"":rs.getString("no_Perjanjian"));
				h.put("noroh", rs.getString("no_Roh")==null?"":rs.getString("no_Roh"));
				h.put("namanegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
				h.put("namamukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
				h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
				h.put("alamathta1", rs.getString("alamat_hta1")==null?"":rs.getString("alamat_hta1"));
				h.put("alamathta2", rs.getString("alamat_hta2")==null?"":rs.getString("alamat_hta2"));
				h.put("alamathta3", rs.getString("alamat_hta3")==null?"":rs.getString("alamat_hta3"));
				h.put("noroh", rs.getString("no_roh")==null?"":rs.getString("no_roh"));
				h.put("nolotid", rs.getString("no_lot_id")==null?"":rs.getString("no_lot_id"));
				h.put("flaghta", rs.getString("flag_kategori_hta")==null?"":rs.getString("flag_kategori_hta"));
				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("poskodhta", rs.getString("poskod_hta")==null?"":rs.getString("poskod_hta"));
				listHTAX.addElement(h);
			}
			return listHTAX;
			}
			finally {
				if(db != null) db.close();
			}
		}
		
		public static Vector getDataHa(String id) throws Exception{
			Db db = null;
			Vector listDataHa = new Vector();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "SELECT h.id_Ha, h.bil, h.id_Simati, h.id_Jenisha, h.no_Daftar, h.nilai_ha_tarikhmohon, h.nilai_ha_tarikhmati, h.no_sijil, h.ba_simati, h.bb_simati, j.kod, j.keterangan, h.id_permohonansimati,(select count(*) from tblppkpilihanha where id_ha = h.id_ha and id_permohonansimati = '"+id+"')as pilih FROM Tblppkha h, Tblppkrujjenisha j, Tblppkpermohonansimati M1, Tblppkpermohonan p  WHERE h.id_simati = m1.id_simati  AND m1.id_permohonan = p.id_permohonan(+)  AND h.id_Jenisha = j.id_jenisha  AND m1.id_permohonansimati = h.id_permohonansimati  AND m1.id_permohonansimati = '"+id+"'";
				
				/*r.add("h.id_Ha");
				r.add("h.bil");
				r.add("h.id_Simati");
				r.add("h.id_Jenisha");
				r.add("h.no_Daftar");
				r.add("h.nilai_ha_tarikhmohon");
				r.add("h.nilai_ha_tarikhmati");
				r.add("h.no_sijil");
				r.add("h.ba_simati");
				r.add("h.bb_simati");
				r.add("j.kod");
				r.add("j.keterangan");
				r.add("h.id_permohonansimati");
				
				r.add("h.id_simati",r.unquote("m1.id_simati"));
				r.add("m1.id_permohonan",r.unquote("p.id_permohonan(+)"));
				r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));
				r.add("m1.id_permohonansimati",r.unquote("h.id_permohonansimati"));
				r.add("m1.id_permohonansimati",id);
				
				sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j, Tblppkpermohonansimati M1, Tblppkpermohonan p ");*/
				System.out.println("sqlhalama-->>"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				int bil = 1;
				while (rs.next()){
					Hashtable h = new Hashtable();
					h.put("bil", bil);
					h.put("id_Ha", rs.getString("id_Ha")==null?"":rs.getString("id_Ha"));
					h.put("id_Jenisha", rs.getString("id_Jenisha")==null?"":rs.getString("id_Jenisha"));
					h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
					h.put("nosijil", rs.getString("no_sijil")==null?"":rs.getString("no_sijil"));
					h.put("noDaftar", rs.getString("no_Daftar")==null?"":rs.getString("no_Daftar"));
					h.put("Kod", rs.getString("kod")==null?"":rs.getString("kod"));
					h.put("Keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("basimati", rs.getString("ba_simati")==null?"":rs.getString("ba_simati"));
					h.put("bbsimati", rs.getString("bb_simati")==null?"":rs.getString("bb_simati"));
					h.put("nilai_tarikhmohon", rs.getString("nilai_ha_tarikhmohon")==null?"":rs.getDouble("nilai_ha_tarikhmohon"));
					h.put("nilai_tarikhmati", rs.getString("nilai_ha_tarikhmati")==null?"":rs.getDouble("nilai_ha_tarikhmati"));
					h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
					h.put("pilih",rs.getString("pilih")==null?"0":rs.getString("pilih"));
					listDataHa.addElement(h);
					bil++;	
				}
				return listDataHa;
			}finally {
				if(db != null) db.close();
			}
			}
		public static Vector getDataHaBaru(String id) throws Exception{
			Db db = null;
			Vector listDataHaBaru = new Vector();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("h.id_Ha");
				r.add("h.bil");
				r.add("h.id_Simati");
				r.add("h.id_Jenisha");
				r.add("h.no_Daftar");
				r.add("h.nilai_ha_tarikhmohon");
				r.add("h.nilai_ha_tarikhmati");
				r.add("h.no_sijil");
				r.add("h.ba_simati");
				r.add("h.bb_simati");
				r.add("j.kod");
				r.add("j.keterangan");
				r.add("h.id_permohonansimati");
				
				r.add("h.id_simati",r.unquote("m1.id_simati"));
				r.add("m1.id_permohonan",r.unquote("p.id_permohonan(+)"));
				r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));
				r.add("m1.id_permohonansimati",r.unquote("h.id_permohonansimati"));
				r.add("m1.id_permohonansimati",id);
				
				sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j, Tblppkpermohonansimati M1, Tblppkpermohonan p ");
				System.out.println("sqlhabaru-->>"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				int bil = 1;
				while (rs.next()){
					Hashtable h = new Hashtable();
					h.put("bil", bil);
					h.put("id_Ha", rs.getString("id_Ha")==null?"":rs.getString("id_Ha"));
					h.put("id_Jenisha", rs.getString("id_Jenisha")==null?"":rs.getString("id_Jenisha"));
					h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
					h.put("nosijil", rs.getString("no_sijil")==null?"":rs.getString("no_sijil"));
					h.put("noDaftar", rs.getString("no_Daftar")==null?"":rs.getString("no_Daftar"));
					h.put("Kod", rs.getString("kod")==null?"":rs.getString("kod"));
					h.put("Keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("basimati", rs.getString("ba_simati")==null?"":rs.getString("ba_simati"));
					h.put("bbsimati", rs.getString("bb_simati")==null?"":rs.getString("bb_simati"));
					h.put("nilai_tarikhmohon", rs.getString("nilai_ha_tarikhmohon")==null?"":rs.getDouble("nilai_ha_tarikhmohon"));
					h.put("nilai_tarikhmati", rs.getString("nilai_ha_tarikhmati")==null?"":rs.getDouble("nilai_ha_tarikhmati"));
					h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
					listDataHaBaru.addElement(h);
					bil++;	
				}
				return listDataHaBaru;
			}finally {
				if(db != null) db.close();
			}
			}
		public static int getUserDetail(int userid) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "Select id_negeri from users_online where user_id = "+ userid +"";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      if (rs.next()) {
					return rs.getString("id_negeri")==null?0:Integer.parseInt(rs.getString("id_negeri"));
				} else return 0;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	 }
		
		public void updateDataPemohon(Hashtable data) throws Exception {
			Db db = null;
				String sql1 = "";
				String sql2 = "";
				String sql3 = "";
				try
				{
					long idob = DB.getNextID("TBLPPKOB_SEQ");
					String namaPemohonPemohon = ((String)data.get("namaPemohonPemohon")).toUpperCase();
					String idPermohonan = (String)data.get("IdPermohonan");
					String idPemohon = (String)data.get("idPemohon");
					String noKPLamaPemohon = ((String)data.get("noKPLamaPemohon")).toUpperCase();
					String jenisKP = ((String)data.get("jenisKP")).toUpperCase();
					String noKpLain = (String)data.get("noKpLain");
					String jenisTaraf = (String)data.get("jenisTaraf");
					String talianSaudara = (String)data.get("talianSaudara");
					String jantina = (String)data.get("jantina");
					String agama = (String)data.get("agama");
					String warga = (String)data.get("warga");
					String umurPemohon = (String)data.get("umurPemohon");
					String alamatTerakhir1Pemohon = ((String)data.get("alamatTerakhir1Pemohon")).toUpperCase();
					String alamatTerakhir2Pemohon = ((String)data.get("alamatTerakhir2Pemohon")).toUpperCase();
					String alamatTerakhir3Pemohon = ((String)data.get("alamatTerakhir3Pemohon")).toUpperCase();
					String poskodPemohon = (String)data.get("poskodPemohon");
					String bandarPemohon = ((String)data.get("bandarPemohon")).toUpperCase();
					int idnegeri = (Integer)data.get("negeri");
					String noTelefonPemohon = (String)data.get("noTelefonPemohon");
					String noTelefonBimbitPemohon = (String)data.get("noTelefonBimbitPemohon");
					String noFaksPemohon = (String)data.get("noFaksPemohon");
					String emelPemohon = (String)data.get("emelPemohon");
					String catatanPemohon = ((String)data.get("catatanPemohon")).toUpperCase();
					String peguam = (String)data.get("peguam");
					String wujudWaris = (String)data.get("wujudWaris");
					String kp_Waris = (String)data.get("kp_Waris");
					String iduser = (String)data.get("iduser");
					String chcAlamat = (String)data.get("chcAlamat");
					String alamatSurat1Pemohon = ((String)data.get("alamatSurat1Pemohon")).toUpperCase();
					String alamatSurat2Pemohon = ((String)data.get("alamatSurat2Pemohon")).toUpperCase();
					String alamatSurat3Pemohon = ((String)data.get("alamatSurat3Pemohon")).toUpperCase();
					String poskodSuratPemohon = (String)data.get("poskodSuratPemohon");
					int negeriSurat = (Integer)data.get("negeriSurat");
					int bandar = (Integer)data.get("id_bandar");
					int bandarsurat = (Integer)data.get("id_bandarsurat");

					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r1 = new SQLRenderer();
					r1.update("id_pemohon",idPemohon);
					r1.add("NO_KP_LAMA",noKPLamaPemohon);
					r1.add("JENIS_KP",jenisKP);
					r1.add("NO_KP_LAIN",noKpLain);
					r1.add("NAMA_PEMOHON",namaPemohonPemohon);
					r1.add("UMUR",umurPemohon);
					r1.add("JANTINA",jantina);
					r1.add("JENIS_AGAMA",agama);
					r1.add("JENIS_WARGA",warga);
					r1.add("ALAMAT_1", alamatTerakhir1Pemohon);
					r1.add("ALAMAT_2", alamatTerakhir2Pemohon);
					r1.add("ALAMAT_3", alamatTerakhir3Pemohon);
					r1.add("POSKOD", poskodPemohon);
					r1.add("NO_HP",noTelefonBimbitPemohon);
					r1.add("NO_TEL",noTelefonPemohon);
					r1.add("EMEL",emelPemohon);
					r1.add("NO_FAX",noFaksPemohon);
					r1.add("CATATAN",catatanPemohon);
					r1.add("ID_TARAFKPTG",jenisTaraf);
					r1.add("ID_SAUDARA", talianSaudara);
					r1.add("ID_BANDAR", bandar);
					r1.add("STATUS_PEGUAM", peguam);
					r1.add("ID_NEGERI", idnegeri);
					if (chcAlamat.equals("1")){
						r1.add("ALAMAT1_SURAT", alamatTerakhir1Pemohon);
						r1.add("ALAMAT2_SURAT", alamatTerakhir2Pemohon);
						r1.add("ALAMAT3_SURAT", alamatTerakhir3Pemohon);
						r1.add("ID_BANDARSURAT", bandar);
						r1.add("POSKOD_SURAT", poskodPemohon);
						r1.add("ID_NEGERISURAT", idnegeri);
					}else{
						r1.add("ALAMAT1_SURAT", alamatSurat1Pemohon);
						r1.add("ALAMAT2_SURAT", alamatSurat2Pemohon);
						r1.add("ALAMAT3_SURAT", alamatSurat3Pemohon);
						r1.add("ID_BANDARSURAT", bandarsurat);
						r1.add("POSKOD_SURAT", poskodSuratPemohon);
						r1.add("ID_NEGERISURAT", negeriSurat);
					}
					sql1 = r1.getSQLUpdate("tblppkpemohon");
					stmt.executeUpdate(sql1);
	
					
					Statement stmta = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.update("id_permohonan",idPermohonan);
					if (jenisTaraf.equals("1")){
						r2.add("flag_hubungan_pemohon",jenisTaraf);
					}else{
						r2.add("flag_hubungan_pemohon",2);
					}
					sql2 = r2.getSQLUpdate("tblppkpermohonan");
					stmta.executeUpdate(sql2);
					
						if (!wujudWaris.equals("0")){
							if (jenisTaraf.equals("1")){ // waris

								Statement stmtB = db.getStatement();
								sql3 = "update tblppkob set nama_ob='"+ namaPemohonPemohon +"',alamat_1='"+ alamatTerakhir1Pemohon +"', alamat_2='"+ alamatTerakhir2Pemohon +"', alamat_3='"+ alamatTerakhir3Pemohon +"', " +
										"poskod='"+ poskodPemohon +"',id_negeri="+ idnegeri +",id_bandar="+bandar+",no_hp='"+ noTelefonBimbitPemohon +"', " +
										"alamat1_surat='"+alamatSurat1Pemohon+"',alamat2_surat='"+alamatSurat2Pemohon+"',alamat3_surat='"+alamatSurat3Pemohon+"'," +
										"poskod_surat='"+poskodSuratPemohon+"',id_negerisurat = '"+negeriSurat+"',id_bandarsurat="+bandarsurat+"," +
										"no_tel='"+ noTelefonPemohon +"',id_tarafkptg="+jenisTaraf+",id_saudara="+talianSaudara+", status_ob=0 " +
										"where id_pemohon = '"+idPemohon+"'";
								stmtB.executeUpdate(sql3);
							}else{

								Statement stmtD = db.getStatement();
								String sql5 = "update tblppkob set nama_ob='"+ namaPemohonPemohon +"',alamat_1='"+ alamatTerakhir1Pemohon +"', alamat_2='"+ alamatTerakhir2Pemohon +"', alamat_3='"+ alamatTerakhir3Pemohon +"', " +
										"poskod='"+ poskodPemohon +"',id_negeri="+ idnegeri +",id_bandar="+bandar+",no_hp='"+ noTelefonBimbitPemohon +"', " +
										"alamat1_surat='"+alamatSurat1Pemohon+"',alamat2_surat='"+alamatSurat2Pemohon+"',alamat3_surat='"+alamatSurat3Pemohon+"'," +
										"poskod_surat='"+poskodSuratPemohon+"',id_negerisurat = '"+negeriSurat+"',id_bandarsurat="+bandarsurat+"," +
										"no_tel='"+ noTelefonPemohon +"',id_tarafkptg="+jenisTaraf+",id_saudara="+talianSaudara+",status_ob=1,nilai_hutang=0," +
										"jenis_pemiutang=1 where id_pemohon = '"+idPemohon+"')";
								stmtD.executeUpdate(sql5);	
							}
						}
						
				}finally {
					if(db != null) db.close();
				}
			}
		
		public static Vector getDetailsPemohonNew(String kpbaru, String kplama, String kplain) throws Exception{
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector detailPemohon = new Vector();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("P.NO_KP_BARU");
				r.add("P.NO_KP_LAMA");
				r.add("P.JENIS_KP");
				r.add("P.NO_KP_LAIN ");
				r.add("P.NAMA_PEMOHON");
				r.add("PP.NO_PERMOHONAN_ONLINE");
				r.add("PP.FLAG_HUBUNGAN_PEMOHON");
				r.add("PP.ID_HUBUNGANPEMOHON");
				r.add("PP.TARIKH_MOHON_ONLINE");
				r.add("PP.ID_PERMOHONAN");
				r.add("F.NO_FAIL");
				r.add("D.NAMA_DAERAH");
				r.add("N.NAMA_NEGERI");
				r.add("M1.ID_PERMOHONANSIMATI");
				r.add("M1.ID_SIMATI");
				r.add("PP.NO_SUBJAKET");
				
					
				r.add("P.ID_PERMOHONAN",r.unquote("PP.ID_PERMOHONAN"));
				r.add("PP.ID_FAIL",r.unquote("F.ID_FAIL"));
				r.add("PP.ID_DAERAHMHN",r.unquote("D.ID_DAERAH"));
				r.add("D.ID_NEGERI",r.unquote("N.ID_NEGERI"));
				r.add("PP.ID_PERMOHONAN",r.unquote("M1.ID_PERMOHONAN"));
				r.add("M1.ID_SIMATI",r.unquote("C.ID_SIMATI"));
				
				
				String kpBaru = kpbaru.trim();
				String kpLama = kplama.trim();
				String kpLain = kplain.trim();
				sql = r.getSQLSelect("Tblppkpermohonan PP, Tblppksimati c, Tblppkpemohon P, Tblpfdfail F, Tblrujnegeri n, Tblrujdaerah d, Tblppkpermohonansimati M1");
				String sqlwhere = "";
				
				if (kpBaru != "") {
					if (!kpBaru.trim().equals("")) {
						sqlwhere = sqlwhere + " UPPER(c.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%'";
					}
				}
				if (kpLama != "") {
					if (!kpLama.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(c.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
						} else {
							sqlwhere = sqlwhere + " UPPER(c.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
						}
						
					}
				}
				if (kpLain != "") {
					if (!kpLain.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(c.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
						} else {
							sqlwhere = sqlwhere + " UPPER(c.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
						}
					}
				}	
				if (sqlwhere != "") {
					sql = sql + " AND ( "+sqlwhere+" ) AND PP.SEKSYEN = 17 and PP.ID_STATUS = 160";
				} 			
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
					h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
					h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
					h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
					h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
					h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
					h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
					h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("namaDaerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
					h.put("namaNegeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
					h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
					h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
					h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
					h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("nosubjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
					detailPemohon.addElement(h);
				}
				return detailPemohon;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public static Vector semakDataPemohon(String id) throws Exception{
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector listDataPemohon = new Vector();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("P.NO_KP_BARU");
				r.add("P.NO_KP_LAMA");
				r.add("P.JENIS_KP");
				r.add("P.NO_KP_LAIN ");
				r.add("P.NAMA_PEMOHON");
				r.add("P.ID_PEMOHON");
				r.add("P.UMUR");
				r.add("P.JANTINA");
				r.add("P.JENIS_AGAMA");
				r.add("P.JENIS_WARGA");
				r.add("P.ALAMAT_1");
				r.add("P.ALAMAT_2");
				r.add("P.ALAMAT_3");
				r.add("P.BANDAR");
				r.add("P.POSKOD");
				r.add("P.NO_HP");
				r.add("P.NO_TEL");
				r.add("P.EMEL");
				r.add("P.NO_FAX");
				r.add("P.CATATAN");
				r.add("P.ID_TARAFKPTG");
				r.add("P.ID_SAUDARA");
				r.add("P.ID_NEGERI");
				r.add("P.STATUS_PEGUAM");
				r.add("P.ALAMAT1_SURAT");
				r.add("P.ALAMAT2_SURAT");
				r.add("P.ALAMAT3_SURAT");
				r.add("P.BANDAR_SURAT");
				r.add("P.POSKOD_SURAT");
				r.add("P.ID_NEGERISURAT");
				r.add("P.NO_TEL_SURAT");
				r.add("P.NO_HP_SURAT");
				r.add("P.ID_BANDARSURAT");
				r.add("P.ID_BANDAR");
				
				r.add("PP.NO_PERMOHONAN_ONLINE");
				r.add("PP.FLAG_HUBUNGAN_PEMOHON");
				r.add("PP.ID_HUBUNGANPEMOHON");
				r.add("PP.TARIKH_MOHON_ONLINE");
				r.add("PP.ID_PERMOHONAN");
				r.add("PP.ID_NEGERIMHN");
				r.add("PP.ID_DAERAHMHN");
				r.add("M1.ID_PERMOHONANSIMATI");
				r.add("PP.ID_STATUS");
					
				r.add("P.ID_PEMOHON",r.unquote("PP.ID_PEMOHON"));
				r.add("PP.ID_PERMOHONAN",r.unquote("M1.ID_PERMOHONAN"));
				
				r.add("PP.ID_PERMOHONAN",id);
				r.add("PP.SEKSYEN",17);
				
				sql = r.getSQLSelect("Tblppkpermohonan PP, Tblppkpemohon P, Tblppkpermohonansimati M1");
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
					h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
					h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
					h.put("idPemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
					h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
					h.put("noKpBaruPemohon1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
					h.put("noKpBaruPemohon2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
					h.put("noKpBaruPemohon3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
					h.put("umurPemohon", rs.getString("UMUR")==null?"":rs.getString("UMUR"));
					h.put("jantinaPemohon", rs.getString("JANTINA")==null?"":rs.getString("JANTINA"));
					h.put("jenisagama", rs.getString("JENIS_AGAMA")==null?"":rs.getString("JENIS_AGAMA"));
					h.put("jeniswarga", rs.getString("JENIS_WARGA")==null?"":rs.getString("JENIS_WARGA"));
					h.put("alamat1", rs.getString("ALAMAT_1")==null?"":rs.getString("ALAMAT_1"));
					h.put("alamat2", rs.getString("ALAMAT_2")==null?"":rs.getString("ALAMAT_2"));
					h.put("alamat3", rs.getString("ALAMAT_3")==null?"":rs.getString("ALAMAT_3"));
					h.put("bandarpemohon", rs.getString("BANDAR")==null?"":rs.getString("BANDAR"));
					h.put("poskodpemohon", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
					h.put("hppemohon", rs.getString("NO_HP")==null?"":rs.getString("NO_HP"));
					h.put("telpemohon", rs.getString("NO_TEL")==null?"":rs.getString("NO_TEL"));
					h.put("emelpemohon", rs.getString("EMEL")==null?"":rs.getString("EMEL"));
					h.put("faxpemohon", rs.getString("NO_FAX")==null?"":rs.getString("NO_FAX"));
					h.put("catatan", rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
					h.put("tarafkptg", rs.getString("ID_TARAFKPTG")==null?"":rs.getString("ID_TARAFKPTG"));
					h.put("saudara", rs.getString("ID_SAUDARA")==null?"":rs.getString("ID_SAUDARA"));
					h.put("idnegeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("statuspeguam", rs.getString("STATUS_PEGUAM")==null?"":rs.getString("STATUS_PEGUAM"));
					h.put("flaghubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
					h.put("idhubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
					h.put("nokp", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,2));
					h.put("idnegerimhn", rs.getString("ID_NEGERIMHN")==null?"":rs.getString("ID_NEGERIMHN"));
					h.put("iddaerahmhn", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
					h.put("idpermohonansimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
					h.put("alamat1surat", rs.getString("ALAMAT1_SURAT")==null?"":rs.getString("ALAMAT1_SURAT"));
					h.put("alamat2surat", rs.getString("ALAMAT2_SURAT")==null?"":rs.getString("ALAMAT2_SURAT"));
					h.put("alamat3surat", rs.getString("ALAMAT3_SURAT")==null?"":rs.getString("ALAMAT3_SURAT"));
					h.put("bandarsurat", rs.getString("BANDAR_SURAT")==null?"":rs.getString("BANDAR_SURAT"));
					h.put("poskodsurat", rs.getString("POSKOD_SURAT")==null?"":rs.getString("POSKOD_SURAT"));
					h.put("idnegerisurat", rs.getString("ID_NEGERISURAT")==null?"":rs.getString("ID_NEGERISURAT"));
					h.put("notelsurat", rs.getString("NO_TEL_SURAT")==null?"":rs.getString("NO_TEL_SURAT"));
					h.put("nohpsurat", rs.getString("NO_HP_SURAT")==null?"":rs.getString("NO_HP_SURAT"));
					h.put("iddaerahtetap", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
					h.put("iddaerahsurat", rs.getString("ID_BANDARSURAT")==null?"":rs.getString("ID_BANDARSURAT"));
					listDataPemohon.addElement(h);
				}
				return listDataPemohon;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public static String getNoFail(String kpbarusimati, String kplamasimati, String kplainsimati, 
				String userid,String NoFail) throws Exception {
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector resultSimati = new Vector();
			try {
				db = new Db();
				String kpBaru = kpbarusimati.trim();
				String kpLama = kplamasimati.trim();
				String kpLain = kplainsimati.trim();
				//String sql = "Select * from (Select PP.NO_PERMOHONAN_ONLINE as cntId from TBLPPKSIMATI P, TBLPPKPERMOHONAN PP, TBLPPKPERMOHONANSIMATI M where " +
				String sql = "Select * from (Select PP.ID_FAIL as cntId from TBLPPKSIMATI P,TBLPFDFAIL FF, TBLPPKPERMOHONAN PP, TBLPPKPERMOHONANSIMATI M where " +
						"PP.ID_PERMOHONAN = M.ID_PERMOHONAN AND PP.ID_FAIL = FF.ID_FAIL AND M.ID_SIMATI = P.ID_SIMATI";
				
				String sqlwhere = "";
				if (NoFail != "") {
					if (!NoFail.trim().equals("")) {
						sqlwhere = sqlwhere + " UPPER(FF.NO_FAIL) LIKE '%" + NoFail.trim().toUpperCase() + "%'";
					}
				}
				
				if (kpBaru != "") {
					if (!kpBaru.trim().equals("")) {
						if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_BARU) LIKE '%" + kpBaru.trim().toUpperCase() + "%'";
						}
						else
						{
					   sqlwhere = sqlwhere + " UPPER(P.NO_KP_BARU) LIKE '%" + kpBaru.trim().toUpperCase() + "%'";	
						}
					}
				}
				if (kpLama != "") {
					if (!kpLama.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAMA) LIKE '%" + kpLama.trim().toUpperCase() + "%'";
						} else {
							sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAMA) LIKE '%" + kpLama.trim().toUpperCase() + "%'";
						}
						
					}
				}
				if (kpLain != "") {
					if (!kpLain.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAIN) LIKE '%" + kpLain.trim().toUpperCase() + "%'";
						} else {
							sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAIN) LIKE '%" + kpLain.trim().toUpperCase() + "%'";
						}
					}
				}
				
				if (sqlwhere != "") {
					sql = sql + " AND ( "+sqlwhere+" ) order by pp.id_permohonan desc ) where rownum < 2";
				} 
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("getNoFail :: "+sql);
				
				Hashtable h;

				if (rs.next()) {	
					return rs.getString("cntId")==null?"":rs.getString("cntId");
				} else return "";
				
				//return resultSimati;
			}finally {
				if(db != null) db.close();
			}
			}
		
		
		public static String get_id_permohonan(String kpbarusimati, String kplamasimati, String kplainsimati, 
				String userid,String NoFail) throws Exception {
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector resultSimati = new Vector();
			try {
				db = new Db();
				String kpBaru = kpbarusimati.trim();
				String kpLama = kplamasimati.trim();
				String kpLain = kplainsimati.trim();
				//String sql = "Select * from (Select PP.NO_PERMOHONAN_ONLINE as cntId from TBLPPKSIMATI P, TBLPPKPERMOHONAN PP, TBLPPKPERMOHONANSIMATI M where " +
				String sql = "Select * from (Select PP.ID_PERMOHONAN as cntId from TBLPPKSIMATI P, TBLPPKPERMOHONAN PP,TBLPFDFAIL FF, TBLPPKPERMOHONANSIMATI M where " +
						"PP.ID_PERMOHONAN = M.ID_PERMOHONAN AND PP.ID_FAIL = FF.ID_FAIL AND M.ID_SIMATI = P.ID_SIMATI";
				
				String sqlwhere = "";
				
				if (NoFail != "") {
					if (!NoFail.trim().equals("")) {
						sqlwhere = sqlwhere + " UPPER(FF.NO_FAIL) LIKE '%" + NoFail.trim().toUpperCase() + "%'";
					}
				}
				
				if (kpBaru != "") {
					if (!kpBaru.trim().equals("")) {
						if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_BARU) LIKE '%" + kpBaru.trim().toUpperCase() + "%'";
						}else
						{
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_BARU) LIKE '%" + kpBaru.trim().toUpperCase() + "%'";	
						}
					}
				}
				if (kpLama != "") {
					if (!kpLama.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAMA) LIKE '%" + kpLama.trim().toUpperCase() + "%'";
						} else {
							sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAMA) LIKE '%" + kpLama.trim().toUpperCase() + "%'";
						}
						
					}
				}
				if (kpLain != "") {
					if (!kpLain.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAIN) LIKE '%" + kpLain.trim().toUpperCase() + "%'";
						} else {
							sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAIN) LIKE '%" + kpLain.trim().toUpperCase() + "%'";
						}
					}
				}
				
				if (sqlwhere != "") {
					sql = sql + " AND ( "+sqlwhere+" ) order by pp.id_permohonan desc ) where rownum < 2";
				} 
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("getNoFail :: "+sql);
				
				Hashtable h;

				if (rs.next()) {	
					return rs.getString("cntId")==null?"":rs.getString("cntId");
				} else return "";
				
				//return resultSimati;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public static Vector getDetailsPemohonRecordLama(String kpbaru, String kplama, String kplain, String nofail,String kpbarupemohon, String kplamapemohon, String kplainpemohon) throws Exception{
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector detailPemohon = new Vector();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				String kpBaru = kpbaru.trim();
				String kpLama = kplama.trim();
				String kpLain = kplain.trim();
				String failno = nofail.trim();
				String kpBarupemohon = kpbarupemohon.trim();
				String kpLamapemohon = kplamapemohon.trim();
				String kpLainpemohon = kplainpemohon.trim();
				sql = "SELECT * FROM (SELECT PP.SEKSYEN,PP.ID_STATUS,P.NO_KP_BARU, P.NO_KP_LAMA, P.JENIS_KP, P.NO_KP_LAIN , " +
						"P.NAMA_PEMOHON, PP.NO_PERMOHONAN_ONLINE, PP.FLAG_HUBUNGAN_PEMOHON,PP.ID_HUBUNGANPEMOHON, PP.TARIKH_MOHON_ONLINE, " +
						"PP.ID_PERMOHONAN, F.NO_FAIL, M1.ID_PERMOHONANSIMATI, M1.ID_SIMATI, (SELECT PP2.NO_SUBJAKET FROM " +
						"TBLPPKPERMOHONAN PP2 WHERE PP2.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NOSUBJAKET," +
						"(SELECT F1.NO_FAIL FROM TBLPFDFAIL F1, TBLPPKPERMOHONAN PP1 WHERE F1.ID_FAIL = PP1.ID_FAIL AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NOFAILLAMA,(SELECT N.NAMA_NEGERI FROM TBLPPKPERMOHONAN PP1, " +
						"TBLRUJNEGERI N WHERE N.ID_NEGERI = PP1.ID_NEGERIMHN AND PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NAMANEGERI," +
						"(SELECT D.NAMA_DAERAH FROM TBLPPKPERMOHONAN PP1, TBLRUJDAERAH D WHERE D.ID_DAERAH = PP1.ID_DAERAHMHN AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NAMADAERAH, PP.HARTA_TINGGAL,PP.BATAL_KUASA_PENTADBIR,PP.BATAL_P_AMANAH," +
						"PP.LANTIK_P_AMANAH,PP.LANTIK_PENTADBIR,PP.PERINTAH_BARU,PP.PERINTAH_LAMA,P.ID_PEMOHON, " +
						"(SELECT F1.ID_FAIL FROM TBLPFDFAIL F1, TBLPPKPERMOHONAN PP1 WHERE F1.ID_FAIL = PP1.ID_FAIL AND PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS IDFAILLAMA "+
						"FROM Tblppkpermohonan PP, Tblppksimati c, " +
						"Tblppkpemohon P, Tblpfdfail F, Tblppkpermohonansimati M1 WHERE P.ID_PEMOHON = PP.ID_PEMOHON  " +
						"AND PP.ID_FAIL = F.ID_FAIL  AND PP.ID_PERMOHONAN = M1.ID_PERMOHONAN  AND M1.ID_SIMATI = C.ID_SIMATI ";

				String sqlwhere = "";
				String sqlwhere1 = "";
				
				if (kpBarupemohon != "") {
					if (!kpBarupemohon.trim().equals("")) {
						sqlwhere1 = sqlwhere1 + " UPPER(P.NO_KP_BARU) LIKE '%" + kpBarupemohon.toUpperCase() + "%'";
					}
				}
				if (kpLamapemohon != "") {
					if (!kpLamapemohon.trim().equals("")) {
						if (sqlwhere1 != "") {
							sqlwhere1 = sqlwhere1 + " OR UPPER(P.NO_KP_LAMA) LIKE '%" + kpLamapemohon.toUpperCase() + "%'";
						} else {
							sqlwhere1 = sqlwhere1 + " UPPER(P.NO_KP_LAMA) LIKE '%" + kpLamapemohon.toUpperCase() + "%'";
						}
						
					}
				}
				if (kpLainpemohon != "") {
					if (!kpLainpemohon.trim().equals("")) {
						if (sqlwhere1 != "") {
							sqlwhere1 = sqlwhere1 + " OR UPPER(P.NO_KP_LAIN) LIKE '%" + kpLainpemohon.toUpperCase() + "%'";
						} else {
							sqlwhere1 = sqlwhere1 + " UPPER(P.NO_KP_LAIN) LIKE '%" + kpLainpemohon.toUpperCase() + "%'";
						}
					}
				}
				
				if (kpBaru != "") {
					if (!kpBaru.trim().equals("")) {
						sqlwhere = sqlwhere + " UPPER(c.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%' or ("+sqlwhere1+")";
					}
				}
				if (kpLama != "") {
					if (!kpLama.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(c.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%' or ("+sqlwhere1+")";
						} else {
							sqlwhere = sqlwhere + " UPPER(c.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%' or ("+sqlwhere1+")";
						}
						
					}
				}
				if (kpLain != "") {
					if (!kpLain.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(c.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%' or ("+sqlwhere1+")";
						} else {
							sqlwhere = sqlwhere + " UPPER(c.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%' or ("+sqlwhere1+")";
						}
					}
				}
				/* if (failno != "") {
					if (!failno.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(pp.NO_PERMOHONAN_ONLINE) LIKE '%" + failno.toUpperCase() + "%'";
						} else {
							sqlwhere = sqlwhere + " UPPER(pp.NO_PERMOHONAN_ONLINE) LIKE '%" + failno.toUpperCase() + "%'";
						}
					}
				} */
				if (sqlwhere != "") {
					sql = sql + " and ( "+sqlwhere+" ) ORDER BY PP.ID_PERMOHONAN DESC ) WHERE ROWNUM < 2";
				} 			
				
				myLogger.debug(sql);
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("sql dr 8 ke 17 : "+sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("id_faillama",rs.getString("IDFAILLAMA")==null?"":rs.getString("IDFAILLAMA"));
					h.put("id_pemohon",rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
					h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
					h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
					h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
					h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
					h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
					h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
					h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
					h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
					h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
					h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
					h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("nosubjaket", rs.getString("NOSUBJAKET")==null?"":rs.getString("NOSUBJAKET"));
					h.put("nofaillama", rs.getString("NOFAILLAMA")==null?"":rs.getString("NOFAILLAMA"));
					h.put("namanegeri", rs.getString("NAMANEGERI")==null?"":rs.getString("NAMANEGERI"));
					h.put("namadaerah", rs.getString("NAMADAERAH")==null?"":rs.getString("NAMADAERAH"));
					h.put("hartatinggal", rs.getString("HARTA_TINGGAL")==null?"":rs.getString("HARTA_TINGGAL"));
					h.put("batalpentadbir", rs.getString("BATAL_KUASA_PENTADBIR")==null?"":rs.getString("BATAL_KUASA_PENTADBIR"));
					h.put("batalamanah", rs.getString("BATAL_P_AMANAH")==null?"":rs.getString("BATAL_P_AMANAH"));
					h.put("lantikamanah", rs.getString("LANTIK_P_AMANAH")==null?"":rs.getString("LANTIK_P_AMANAH"));
					h.put("lantikpentadbir", rs.getString("LANTIK_PENTADBIR")==null?"":rs.getString("LANTIK_PENTADBIR"));
					h.put("perintah_baru", rs.getString("PERINTAH_BARU")==null?"":rs.getString("PERINTAH_BARU"));
					h.put("perintah_lama", rs.getString("PERINTAH_LAMA")==null?"":rs.getString("PERINTAH_LAMA"));
					detailPemohon.addElement(h);
				}
				return detailPemohon;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public static Vector getDetailsPemohonRecordLama_guna_idpermohonanbaru(String kpbaru, String kplama, String kplain, String id_permohonan) throws Exception{
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector detailPemohon = new Vector();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				sql = "SELECT * FROM (SELECT PP.SEKSYEN,PP.ID_STATUS,P.NO_KP_BARU, P.NO_KP_LAMA, P.JENIS_KP, P.NO_KP_LAIN , " +
						"P.NAMA_PEMOHON, PP.NO_PERMOHONAN_ONLINE, PP.FLAG_HUBUNGAN_PEMOHON,PP.ID_HUBUNGANPEMOHON, PP.TARIKH_MOHON_ONLINE, " +
						"PP.ID_PERMOHONAN,F.ID_FAIL, F.NO_FAIL, M1.ID_PERMOHONANSIMATI, M1.ID_SIMATI, (SELECT PP2.NO_SUBJAKET FROM " +
						"TBLPPKPERMOHONAN PP2 WHERE PP2.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS NOSUBJAKET," +
						"(SELECT F1.NO_FAIL FROM TBLPFDFAIL F1, TBLPPKPERMOHONAN PP1 WHERE F1.ID_FAIL = PP1.ID_FAIL AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS NOFAILLAMA, (SELECT f1.id_fail FROM tblpfdfail f1, tblppkpermohonan pp1 WHERE f1.id_fail = pp1.id_fail AND pp1.id_permohonan = pp.id_permohonanterdahulu) AS IDFAILLAMA,(SELECT N.NAMA_NEGERI FROM TBLPPKPERMOHONAN PP1, " +
						"TBLRUJNEGERI N WHERE N.ID_NEGERI = PP1.ID_NEGERIMHN AND PP1.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS NAMANEGERI," +
						"(SELECT D.NAMA_DAERAH FROM TBLPPKPERMOHONAN PP1, TBLRUJDAERAH D WHERE D.ID_DAERAH = PP1.ID_DAERAHMHN AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS NAMADAERAH, PP.HARTA_TINGGAL,PP.BATAL_KUASA_PENTADBIR,PP.BATAL_P_AMANAH," +
						"PP.LANTIK_P_AMANAH,PP.LANTIK_PENTADBIR," +
						"(SELECT PP2.PERINTAH_LAMA FROM " +
						"TBLPPKPERMOHONAN PP2 WHERE PP2.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS PERINTAH_LAMA," +
						"(SELECT PP2.PERINTAH_BARU FROM " +
						"TBLPPKPERMOHONAN PP2 WHERE PP2.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS PERINTAH_BARU " +
						"FROM Tblppkpermohonan PP, Tblppksimati c, " +
						"Tblppkpemohon P, Tblpfdfail F, Tblppkpermohonansimati M1 WHERE P.ID_PEMOHON = PP.ID_PEMOHON " +
						"AND PP.ID_FAIL = F.ID_FAIL  AND PP.ID_PERMOHONAN = M1.ID_PERMOHONAN  AND M1.ID_SIMATI = C.ID_SIMATI " +
						"" +
						"";
				
				String sqlwhere = "pp.ID_PERMOHONAN = '" + id_permohonan + "'";
				
				if (sqlwhere != "") {
					sql = sql + " AND ( "+sqlwhere+" ) ORDER BY PP.ID_PERMOHONAN DESC )";
				} 	
				myLogger.info("REKOD LAMA ONLINE XX :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
					h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
					h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
					h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
					h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
					h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
					h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
					h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("idfail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("id_faillama", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					
					h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
					h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
					h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
					h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("nosubjaket", rs.getString("NOSUBJAKET")==null?"":rs.getString("NOSUBJAKET"));
					h.put("nofaillama", rs.getString("NOFAILLAMA")==null?"":rs.getString("NOFAILLAMA"));
					h.put("idfaillama", rs.getString("IDFAILLAMA")==null?"":rs.getString("IDFAILLAMA"));				
					h.put("namanegeri", rs.getString("NAMANEGERI")==null?"":rs.getString("NAMANEGERI"));
					h.put("namadaerah", rs.getString("NAMADAERAH")==null?"":rs.getString("NAMADAERAH"));
					h.put("hartatinggal", rs.getString("HARTA_TINGGAL")==null?"":rs.getString("HARTA_TINGGAL"));
					h.put("batalpentadbir", rs.getString("BATAL_KUASA_PENTADBIR")==null?"":rs.getString("BATAL_KUASA_PENTADBIR"));
					h.put("batalamanah", rs.getString("BATAL_P_AMANAH")==null?"":rs.getString("BATAL_P_AMANAH"));
					h.put("lantikamanah", rs.getString("LANTIK_P_AMANAH")==null?"":rs.getString("LANTIK_P_AMANAH"));
					h.put("lantikpentadbir", rs.getString("LANTIK_PENTADBIR")==null?"":rs.getString("LANTIK_PENTADBIR"));
					h.put("idstatus", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("perintah_lama", rs.getString("PERINTAH_LAMA")==null?"":rs.getString("PERINTAH_LAMA"));
					h.put("perintah_baru", rs.getString("PERINTAH_BARU")==null?"":rs.getString("PERINTAH_BARU"));
					detailPemohon.addElement(h);
				}
				return detailPemohon;
			}finally {
				if(db != null) db.close();
			}
			}
		
		
		public static Vector getDetailsPemohonRecordLama_guna_idpermohonanlama(String kpbaru, String kplama, String kplain, String id_permohonan) throws Exception{
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector detailPemohon = new Vector();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				sql = "SELECT * FROM (SELECT PP.SEKSYEN,PP.ID_STATUS,P.NO_KP_BARU, P.NO_KP_LAMA, P.JENIS_KP, P.NO_KP_LAIN , " +
						"P.NAMA_PEMOHON, PP.NO_PERMOHONAN_ONLINE, PP.FLAG_HUBUNGAN_PEMOHON,PP.ID_HUBUNGANPEMOHON, PP.TARIKH_MOHON_ONLINE, " +
						"PP.ID_PERMOHONAN,F.ID_FAIL, F.NO_FAIL, M1.ID_PERMOHONANSIMATI, M1.ID_SIMATI, (SELECT PP2.NO_SUBJAKET FROM " +
						"TBLPPKPERMOHONAN PP2 WHERE PP2.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NOSUBJAKET," +
						"(SELECT F1.NO_FAIL FROM TBLPFDFAIL F1, TBLPPKPERMOHONAN PP1 WHERE F1.ID_FAIL = PP1.ID_FAIL AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NOFAILLAMA, (SELECT f1.id_fail FROM tblpfdfail f1, tblppkpermohonan pp1 WHERE f1.id_fail = pp1.id_fail AND pp1.id_permohonan = pp.id_permohonan) AS IDFAILLAMA,(SELECT N.NAMA_NEGERI FROM TBLPPKPERMOHONAN PP1, " +
						"TBLRUJNEGERI N WHERE N.ID_NEGERI = PP1.ID_NEGERIMHN AND PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NAMANEGERI," +
						"(SELECT D.NAMA_DAERAH FROM TBLPPKPERMOHONAN PP1, TBLRUJDAERAH D WHERE D.ID_DAERAH = PP1.ID_DAERAHMHN AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NAMADAERAH, PP.HARTA_TINGGAL,PP.BATAL_KUASA_PENTADBIR,PP.BATAL_P_AMANAH," +
						"PP.LANTIK_P_AMANAH,PP.LANTIK_PENTADBIR,PP.PERINTAH_LAMA,PP.PERINTAH_BARU FROM Tblppkpermohonan PP, Tblppksimati c, " +
						"Tblppkpemohon P, Tblpfdfail F, Tblppkpermohonansimati M1 WHERE P.ID_PEMOHON = PP.ID_PEMOHON " +
						"AND PP.ID_FAIL = F.ID_FAIL  AND PP.ID_PERMOHONAN = M1.ID_PERMOHONAN  AND M1.ID_SIMATI = C.ID_SIMATI " +
						"" +
						"";
				
				String sqlwhere = "pp.ID_PERMOHONAN = '" + id_permohonan + "'";
				
				if (sqlwhere != "") {
					sql = sql + " AND ( "+sqlwhere+" ) ORDER BY PP.ID_PERMOHONAN DESC )";
				} 	
				myLogger.info("REKOD LAMA ONLINE XX :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
					h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
					h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
					h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
					h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
					h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
					h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
					h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("idfail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("id_faillama", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					
					h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
					h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
					h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
					h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("nosubjaket", rs.getString("NOSUBJAKET")==null?"":rs.getString("NOSUBJAKET"));
					h.put("nofaillama", rs.getString("NOFAILLAMA")==null?"":rs.getString("NOFAILLAMA"));
					h.put("idfaillama", rs.getString("IDFAILLAMA")==null?"":rs.getString("IDFAILLAMA"));				
					h.put("namanegeri", rs.getString("NAMANEGERI")==null?"":rs.getString("NAMANEGERI"));
					h.put("namadaerah", rs.getString("NAMADAERAH")==null?"":rs.getString("NAMADAERAH"));
					h.put("hartatinggal", rs.getString("HARTA_TINGGAL")==null?"":rs.getString("HARTA_TINGGAL"));
					h.put("batalpentadbir", rs.getString("BATAL_KUASA_PENTADBIR")==null?"":rs.getString("BATAL_KUASA_PENTADBIR"));
					h.put("batalamanah", rs.getString("BATAL_P_AMANAH")==null?"":rs.getString("BATAL_P_AMANAH"));
					h.put("lantikamanah", rs.getString("LANTIK_P_AMANAH")==null?"":rs.getString("LANTIK_P_AMANAH"));
					h.put("lantikpentadbir", rs.getString("LANTIK_PENTADBIR")==null?"":rs.getString("LANTIK_PENTADBIR"));
					h.put("idstatus", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("perintah_lama", rs.getString("PERINTAH_LAMA")==null?"":rs.getString("PERINTAH_LAMA"));
					h.put("perintah_baru", rs.getString("PERINTAH_BARU")==null?"":rs.getString("PERINTAH_BARU"));
					detailPemohon.addElement(h);
				}
				return detailPemohon;
			}finally {
				if(db != null) db.close();
			}
			}
		
		
	     	
		
		
		
		
		public static Vector getDetailsPemohonRecordLama2_baru(String kpbaru, String kplama, String kplain, String nofail) throws Exception{
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector detailPemohon = new Vector();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				String kpBaru = kpbaru.trim();
				String kpLama = kplama.trim();
				String kpLain = kplain.trim();
				String failno = nofail.trim();
				sql = "SELECT * FROM (SELECT PP.SEKSYEN,PP.ID_STATUS,P.NO_KP_BARU, P.NO_KP_LAMA, P.JENIS_KP, P.NO_KP_LAIN , " +
						"P.NAMA_PEMOHON, PP.NO_PERMOHONAN_ONLINE, PP.FLAG_HUBUNGAN_PEMOHON,PP.ID_HUBUNGANPEMOHON, PP.TARIKH_MOHON_ONLINE, " +
						"PP.ID_PERMOHONAN,F.ID_FAIL, F.NO_FAIL, M1.ID_PERMOHONANSIMATI, M1.ID_SIMATI, (SELECT PP2.NO_SUBJAKET FROM " +
						"TBLPPKPERMOHONAN PP2 WHERE PP2.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS NOSUBJAKET," +
						"(SELECT F1.NO_FAIL FROM TBLPFDFAIL F1, TBLPPKPERMOHONAN PP1 WHERE F1.ID_FAIL = PP1.ID_FAIL AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS NOFAILLAMA, " +
						"(SELECT f1.id_fail FROM tblpfdfail f1, tblppkpermohonan pp1 WHERE f1.id_fail = pp1.id_fail AND pp1.id_permohonan = pp.id_permohonanterdahulu) AS IDFAILLAMA,(SELECT N.NAMA_NEGERI FROM TBLPPKPERMOHONAN PP1, " +
						"TBLRUJNEGERI N WHERE N.ID_NEGERI = PP1.ID_NEGERIMHN AND PP1.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS NAMANEGERI," +
						"(SELECT D.NAMA_DAERAH FROM TBLPPKPERMOHONAN PP1, TBLRUJDAERAH D WHERE D.ID_DAERAH = PP1.ID_DAERAHMHN AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONAN) AS NAMADAERAH, PP.HARTA_TINGGAL,PP.BATAL_KUASA_PENTADBIR,PP.BATAL_P_AMANAH," +
						"PP.LANTIK_P_AMANAH,PP.LANTIK_PENTADBIR,PP.PERINTAH_LAMA,PP.PERINTAH_BARU FROM Tblppkpermohonan PP, Tblppksimati c, " +
						"Tblppkpemohon P, Tblpfdfail F, Tblppkpermohonansimati M1 WHERE P.ID_PEMOHON = PP.ID_PEMOHON " +
						"AND PP.ID_FAIL = F.ID_FAIL  AND PP.ID_PERMOHONAN = M1.ID_PERMOHONAN  AND M1.ID_SIMATI = C.ID_SIMATI " +
						"" +
						"AND PP.NO_SUBJAKET = (select max(px.no_subjaket) from tblpfdfail fx,tblppkpermohonan px,tblppksimati sx,tblppkpermohonansimati psx where fx.id_fail = px.id_fail and px.id_permohonan = psx.id_permohonan " +
						" and psx.id_simati = sx.id_simati and px.id_permohonan = '" + failno.toUpperCase() + "')";
				
				String sqlwhere = "pp.ID_PERMOHONAN = '" + failno.toUpperCase() + "'";
				
				if (sqlwhere != "") {
					sql = sql + " AND ( "+sqlwhere+" ) ORDER BY PP.ID_PERMOHONAN DESC )";
				} 	
				myLogger.info("REKOD LAMA BARU ONLINE :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
					h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
					h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
					h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
					h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
					h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
					h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
					h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("idfail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("id_faillama", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					
					h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
					h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
					h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
					h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("nosubjaket", rs.getString("NOSUBJAKET")==null?"":rs.getString("NOSUBJAKET"));
					h.put("nofaillama", rs.getString("NOFAILLAMA")==null?"":rs.getString("NOFAILLAMA"));
					h.put("idfaillama", rs.getString("IDFAILLAMA")==null?"":rs.getString("IDFAILLAMA"));				
					h.put("namanegeri", rs.getString("NAMANEGERI")==null?"":rs.getString("NAMANEGERI"));
					h.put("namadaerah", rs.getString("NAMADAERAH")==null?"":rs.getString("NAMADAERAH"));
					h.put("hartatinggal", rs.getString("HARTA_TINGGAL")==null?"":rs.getString("HARTA_TINGGAL"));
					h.put("batalpentadbir", rs.getString("BATAL_KUASA_PENTADBIR")==null?"":rs.getString("BATAL_KUASA_PENTADBIR"));
					h.put("batalamanah", rs.getString("BATAL_P_AMANAH")==null?"":rs.getString("BATAL_P_AMANAH"));
					h.put("lantikamanah", rs.getString("LANTIK_P_AMANAH")==null?"":rs.getString("LANTIK_P_AMANAH"));
					h.put("lantikpentadbir", rs.getString("LANTIK_PENTADBIR")==null?"":rs.getString("LANTIK_PENTADBIR"));
					h.put("idstatus", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("perintah_lama", rs.getString("PERINTAH_LAMA")==null?"":rs.getString("PERINTAH_LAMA"));
					h.put("perintah_baru", rs.getString("PERINTAH_BARU")==null?"":rs.getString("PERINTAH_BARU"));
					detailPemohon.addElement(h);
				}
				return detailPemohon;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public static String getNoFailtest(String kpbarusimati, String kplamasimati, String kplainsimati, int userid) throws Exception {
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector resultSimati = new Vector();
			try {
				db = new Db();
				String kpBaru = kpbarusimati.trim();
				String kpLama = kplamasimati.trim();
				String kpLain = kplainsimati.trim();
				String sql = "Select * from (Select PP.NO_PERMOHONAN_ONLINE as cntId from TBLPPKSIMATI P, TBLPPKPERMOHONAN PP, TBLPPKPERMOHONANSIMATI M where " +
						"PP.ID_PERMOHONAN = M.ID_PERMOHONAN AND M.ID_SIMATI = P.ID_SIMATI";
				
				String sqlwhere = "";
				
				if (kpBaru != "") {
					if (!kpBaru.trim().equals("")) {
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%'";
					}
				}
				if (kpLama != "") {
					if (!kpLama.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
						} else {
							sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
						}
						
					}
				}
				if (kpLain != "") {
					if (!kpLain.trim().equals("")) {
						if (sqlwhere != "") {
							sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
						} else {
							sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
						}
					}
				}
				
				if (sqlwhere != "") {
					sql = sql + " AND ( "+sqlwhere+" ) order by pp.id_permohonan desc ) where rownum < 2";
				} 
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;

				/*while (rs.next()){
					h = new Hashtable();
					h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
					h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
					h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
					h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
					h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
					h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
					h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
					h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
					h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
					h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
					h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("nosubjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
					h.put("nofaillama", rs.getString("NOFAILLAMA")==null?"":rs.getString("NOFAILLAMA"));
					h.put("namanegeri", rs.getString("NAMANEGERI")==null?"":rs.getString("NAMANEGERI"));
					h.put("namadaerah", rs.getString("NAMADAERAH")==null?"":rs.getString("NAMADAERAH"));
					detailPemohon.addElement(h);
				}
				return detailPemohon;*/
				
				if (rs.next()) {	
					return rs.getString("cntId")==null?"":rs.getString("cntId");
				} else return "";
				
				//return resultSimati;
			}finally {
				if(db != null) db.close();
			}
			}
		
		public void simpandatasemak(Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	String iduser=(String)data.get("iduser");
		    	String idpermohonan=(String)data.get("idPermohonan");
		    	/*String cb1= ((String)data.get("cb1")).toUpperCase();
		    	String cb3= ((String)data.get("cb3")).toUpperCase();
		    	String cb4= ((String)data.get("cb4")).toUpperCase();
		    	String cb6= ((String)data.get("cb6")).toUpperCase();
		    	String cb7= ((String)data.get("cb7")).toUpperCase();*/
		    	//added by elly 271010
		    	String perintahdahulu= (String)data.get("perintahdahulu");
		    	String perintahminta= (String)data.get("perintahminta"); 
		    			    	
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			    /*  sql = "update TBLPPKPERMOHONAN set batal_kuasa_pentadbir='"+ cb6 +"',lantik_pentadbir='"+ cb7 +"'," +
			      		" batal_p_amanah='"+ cb3 +"', lantik_p_amanah='"+ cb4 +"', harta_tinggal='"+ cb1 +"'," +
			      		" perintah_lama='"+ perintahdahulu +"', perintah_baru='"+ perintahminta +"'," +
			      		"tarikh_kemaskini = sysdate, id_kemaskini = "+ iduser +" where id_permohonan = "+ Integer.parseInt(idpermohonan) +"";*/
			      
			      sql = "update TBLPPKPERMOHONAN set  perintah_lama='"+ perintahdahulu +"', perintah_baru='"+ perintahminta +"'," +
		      		"tarikh_kemaskini = sysdate, id_kemaskini = "+ iduser +" where id_permohonan = "+ idpermohonan +"";
			     System.out.println("update simpansemak : "+sql);
			     stmt.executeUpdate(sql);			      
			    } 
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    	finally {
			      if (db != null) db.close();
			    }
			    
		  }
		
		public static Vector getDetailsPemohonRecord(String idPermohonan) throws Exception{
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector detailPemohon = new Vector();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = "SELECT * FROM (SELECT P.ID_PEMOHON, PP.SEKSYEN,PP.ID_STATUS,P.NO_KP_BARU, P.NO_KP_LAMA, P.JENIS_KP, P.NO_KP_LAIN , " +
						"P.NAMA_PEMOHON, PP.NO_PERMOHONAN_ONLINE, PP.FLAG_HUBUNGAN_PEMOHON,PP.ID_HUBUNGANPEMOHON, PP.TARIKH_MOHON_ONLINE, " +
						"PP.ID_PERMOHONAN, F.ID_FAIL, F.NO_FAIL, M1.ID_PERMOHONANSIMATI, M1.ID_SIMATI, (SELECT PP2.NO_SUBJAKET FROM " +
						"TBLPPKPERMOHONAN PP2 WHERE PP2.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NOSUBJAKET," +
						"(SELECT F1.NO_FAIL FROM TBLPFDFAIL F1, TBLPPKPERMOHONAN PP1 WHERE F1.ID_FAIL = PP1.ID_FAIL AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NOFAILLAMA,(SELECT N.NAMA_NEGERI FROM TBLPPKPERMOHONAN PP1, " +
						"TBLRUJNEGERI N WHERE N.ID_NEGERI = PP1.ID_NEGERIMHN AND PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NAMANEGERI," +
						"(SELECT D.NAMA_DAERAH FROM TBLPPKPERMOHONAN PP1, TBLRUJDAERAH D WHERE D.ID_DAERAH = PP1.ID_DAERAHMHN AND " +
						"PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NAMADAERAH, PP.HARTA_TINGGAL,PP.BATAL_KUASA_PENTADBIR," +
						"PP.BATAL_P_AMANAH,PP.LANTIK_P_AMANAH,PP.LANTIK_PENTADBIR,PP.PERINTAH_BARU,PP.PERINTAH_LAMA FROM Tblppkpermohonan PP, Tblppksimati c, " +
						"Tblppkpemohon P, Tblpfdfail F, Tblppkpermohonansimati M1 WHERE P.ID_PEMOHON = PP.ID_PEMOHON  " +
						"AND PP.ID_FAIL = F.ID_FAIL  AND PP.ID_PERMOHONAN = M1.ID_PERMOHONAN  AND M1.ID_SIMATI = C.ID_SIMATI " +
						" AND PP.ID_PERMOHONAN = "+ idPermohonan +" ORDER BY PP.ID_PERMOHONAN DESC ) WHERE ROWNUM < 2";
				//System.out.println("sql 3333 :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("id_pemohon",rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
					h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
					h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
					h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
					h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
					h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
					h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
					h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
					h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
					h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idfail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("id_faillama", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
					h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
					h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
					h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
					h.put("nosubjaket", rs.getString("NOSUBJAKET")==null?"":rs.getString("NOSUBJAKET"));
					h.put("nofaillama", rs.getString("NOFAILLAMA")==null?"":rs.getString("NOFAILLAMA"));
					h.put("namanegeri", rs.getString("NAMANEGERI")==null?"":rs.getString("NAMANEGERI"));
					h.put("namadaerah", rs.getString("NAMADAERAH")==null?"":rs.getString("NAMADAERAH"));
					h.put("hartatinggal", rs.getString("HARTA_TINGGAL")==null?"":rs.getString("HARTA_TINGGAL"));
					h.put("batalpentadbir", rs.getString("BATAL_KUASA_PENTADBIR")==null?"":rs.getString("BATAL_KUASA_PENTADBIR"));
					h.put("batalamanah", rs.getString("BATAL_P_AMANAH")==null?"":rs.getString("BATAL_P_AMANAH"));
					h.put("lantikamanah", rs.getString("LANTIK_P_AMANAH")==null?"":rs.getString("LANTIK_P_AMANAH"));
					h.put("lantikpentadbir", rs.getString("LANTIK_PENTADBIR")==null?"":rs.getString("LANTIK_PENTADBIR"));
					h.put("idstatus", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("perintah_lama", rs.getString("PERINTAH_LAMA")==null?"":rs.getString("PERINTAH_LAMA"));
					h.put("perintah_baru", rs.getString("PERINTAH_BARU")==null?"":rs.getString("PERINTAH_BARU"));
					detailPemohon.addElement(h);
				}
				
			}
			catch(Exception e){
				Log.error(e.getMessage());
				e.printStackTrace();
			}
			finally {
				if(db != null) db.close();
			}
			return detailPemohon;
			}
		
		public static int getPengesahan(String idpermohonansimati) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "Select count(*) as cntid from tblppkhta where id_permohonansimati = '"+ idpermohonansimati +"' ";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      if (rs.next()) {
					return rs.getString("cntid")==null?0:Integer.parseInt(rs.getString("cntid"));
				} else return 0;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	 }
		
		public static Vector getDaerahNegeri(String idpermohonanterdahulu) throws Exception {
		    Db db = null;
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();    
		      
		      //String sql = "Select a.id_daerahmhn, a.id_negerimhn, b.id_permohonansimati from tblppkpermohonan a, tblppkpermohonansimati b where a.id_permohonan = b.id_permohonan and b.id_simati = '"+ idpermohonanterdahulu +"' ";
		      String sql = "Select DISTINCT a.id_daerahmhn, a.id_negerimhn from tblppkpermohonan a, tblppkpermohonansimati b " +
		      		"where a.id_permohonan = b.id_permohonan " +
		      		"AND a.id_negerimhn IS NOT NULL and b.id_simati = '"+ idpermohonanterdahulu +"' ";
		      myLogger.info("SQL GETDAERAH :: "+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v1 = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("iddaerahmhn", rs.getString("id_daerahmhn")==null?"":rs.getString("id_daerahmhn"));
		        h.put("idnegerimhn", rs.getString("id_negerimhn")==null?"":rs.getString("id_negerimhn"));
		        //h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
		        v1.addElement(h);
		      }
		      return v1;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
		
		public static Vector getListnegeri(int idnegeri) throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Negeri");
		      r.add("nama_Negeri");
		      r.add("kod_Negeri");
		      sql = r.getSQLSelect("Tblrujnegeri");
		      sql = sql + " where id_negeri = "+ idnegeri +" order by kod_Negeri";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("idnegeri", rs.getInt("id_Negeri"));
		        if(rs.getString("nama_Negeri") == null){
		        	h.put("namanegeri", "");
		        }else{
		        	h.put("namanegeri", rs.getString("nama_Negeri"));
		        }
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
		
		public void insertDaerahMohon(int idnegeri, int iddaerah, String idpermohonan, long userid, long idFail) throws Exception {
			Db db = null;
				String sql = "";
				java.util.Calendar calendar = java.util.Calendar.getInstance();
				int getYear = calendar.get(java.util.Calendar.YEAR);
				try
				{
									
					db = new Db();
					Statement stmtG = db.getStatement();
					String sql8 = "Update tblrujsuburusanstatusfail set AKTIF = '0' where ID_PERMOHONAN = "+ idpermohonan +" and ID_FAIL = '"+ idFail +"' and AKTIF ='1'";
					stmtG.executeUpdate(sql8);
					
					db = new Db();
					Statement stmtF = db.getStatement();
					SQLRenderer r5 = new SQLRenderer();
					r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
					r5.add("ID_PERMOHONAN",idpermohonan);
					r5.add("ID_SUBURUSANSTATUS",615); //436 status utk permohonan online
					r5.add("AKTIF",1);
					r5.add("ID_FAIL",idFail);
					r5.add("ID_MASUK",userid);
					r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
					String sql2 = r5.getSQLInsert("tblrujsuburusanstatusfail");
					stmtF.executeUpdate(sql2);
					
					String noFail_online = String.format("%06d",File.getSeqNo(db,2,01,18,0,0,false,false,0,0));
					String X = "JKPTG/PK/02/"+getYear+"/"+noFail_online;
					
					db = new Db();
					Statement stmtT = db.getStatement();
					sql = "Update Tblppkpermohonan set NO_PERMOHONAN_ONLINE = '"+X+"',TARIKH_MOHON_ONLINE = sysdate, id_status = 171, id_negerimhn = "+ idnegeri +",id_daerahmhn = "+ iddaerah +",ID_MASUK = "+ userid +", TARIKH_MASUK = sysdate,  ID_KEMASKINI = "+ userid +", TARIKH_KEMASKINI = sysdate where id_permohonan = "+ idpermohonan +"";
					stmtT.executeUpdate(sql);
				}finally {
					if(db != null) db.close();
				}
		}		
				public static Vector setDataHa(String idpermohonansimati) throws Exception{
					Db db = null;
					String sql = "";
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					try {
						db = new Db();
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						r.add("h.id_Ha");
						r.add("h.bil");
						r.add("h.id_Simati");
						r.add("h.id_Jenisha");
						r.add("h.no_Daftar");
						r.add("h.nilai_ha_tarikhmohon");
						r.add("h.nilai_ha_tarikhmati");
						r.add("h.no_sijil");
						r.add("j.kod");
						r.add("j.keterangan");
						r.add("h.alamat_ha1");
						r.add("h.alamat_ha2");
						r.add("h.alamat_ha3");
						r.add("h.poskod");
						r.add("h.poskod");
						r.add("h.id_permohonansimati");
						r.add("h.id_Jenisha",r.unquote("j.id_jenisha"));			
						r.add("h.id_Simati",r.unquote("ms.id_Simati"));
						r.add("ms.id_Permohonansimati",idpermohonansimati);

						sql = r.getSQLSelect("Tblppkha h, Tblppkrujjenisha j, Tblppkpermohonansimati ms");
						sql = sql + "order by h.id_Ha desc ";
						
						ResultSet rs = stmt.executeQuery(sql);
						Hashtable h;
						int bil = 1;
						Vector listDataHa = new Vector();
						while (rs.next()){
							h = new Hashtable();
							h.put("bil", bil);
							h.put("id_Ha", rs.getString("id_Ha")==null?"":rs.getString("id_Ha"));
							h.put("id_Jenisha", rs.getString("id_Jenisha")==null?"":rs.getString("id_Jenisha"));
							h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
							h.put("nosijil", rs.getString("no_sijil")==null?"":rs.getString("no_sijil"));
							h.put("noDaftar", rs.getString("no_Daftar")==null?"":rs.getString("no_Daftar"));
							h.put("Kod", rs.getString("kod")==null?"":rs.getString("kod"));
							h.put("alamat1", rs.getString("alamat_ha1")==null?"":rs.getString("alamat_ha1"));
							h.put("alamat2", rs.getString("alamat_ha2")==null?"":rs.getString("alamat_ha2"));
							h.put("alamat3", rs.getString("alamat_ha3")==null?"":rs.getString("alamat_ha3"));
							h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
							h.put("Keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
							h.put("nilai_tarikhmohon", rs.getString("nilai_ha_tarikhmohon")==null?"":rs.getDouble("nilai_ha_tarikhmohon"));
							h.put("nilai_tarikhmati", rs.getString("nilai_ha_tarikhmati")==null?"":rs.getDouble("nilai_ha_tarikhmati"));
							h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getDouble("id_permohonansimati"));
							listDataHa.addElement(h);
							bil++;	
						}
						return listDataHa;
					}finally {
						if(db != null) db.close();
					}
					}
				
				public void setDataPentingbyIDOB(String id) throws Exception {
					Db db = null;
					listPentingbyIDOB.clear();
					String sql = "";
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					try{
						db = new Db();
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
					r.add("ob.id_Simati");
					r.add("ob.id_Ob");
					r.add("ob.nama_Ob");
					r.add("ob.no_Kp_Baru");
					r.add("ob.no_Kp_Lama");
					r.add("ob.jenis_Kp");
					r.add("ob.no_Kp_Lain");
					r.add("ob.no_Surat_Beranak");
					r.add("ob.tarikh_Lahir");
					r.add("ob.jantina");
					r.add("ob.umur");
					r.add("ob.alamat_1");
					r.add("ob.alamat_2");
					r.add("ob.alamat_3");
					r.add("ob.bandar");
					r.add("ob.poskod");
					r.add("ob.no_Tel");
					r.add("ob.status_Hidup");
					r.add("ob.status_Ob");
					r.add("ob.id_Tarafkptg");
					r.add("ob.id_Negeri");
					r.add("ob.id_Saudara");			
					r.add("ob.jenis_Agama");
					r.add("ob.jenis_Warga");				
					r.add("ob.catatan");
					r.add("ob.butiran_Hutang");
					r.add("ob.nilai_Hutang");
					r.add("ob.no_Akaun");
					r.add("ob.no_tel");
					r.add("ob.no_hp");
					r.add("n.nama_negeri");
					r.add("ob.jenis_pemiutang");
					r.add("ob.id_bandar");
					
					r.add("ob.id_permohonansimati",r.unquote("m1.id_permohonansimati"));
					r.add("m1.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
					r.add("m1.id_Permohonan",r.unquote("m.id_simati(+)"));
					r.add("ob.id_Negeri",r.unquote("n.id_negeri(+)"));
					
					r.add("ob.id_Ob",id);
					
					sql = r.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati M1, Tblrujnegeri n");
					sql = sql + " group by ob.id_Simati, ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, " +
					"ob.no_Surat_Beranak, ob.tarikh_Lahir, ob.jantina, ob.umur, ob.alamat_1, ob.alamat_2, ob.alamat_3, ob.bandar, " +
					"ob.poskod, ob.no_Tel, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri, ob.id_Saudara, ob.jenis_Agama, " +
					"ob.jenis_Warga, ob.catatan, ob.butiran_Hutang, ob.nilai_Hutang, ob.no_Akaun, ob.no_tel, ob.no_hp, n.nama_negeri, " +
					"ob.jenis_pemiutang, ob.id_bandar";
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;
					Integer count = 0;
					
					while(rs.next()) {		
						h = new Hashtable();
						h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
						h.put("idOb", rs.getString("id_Ob")==null?"":rs.getString("id_Ob"));
						h.put("nama_Ob", rs.getString("nama_Ob")==null?"":rs.getString("nama_Ob"));
						h.put("status_Ob", rs.getString("status_Ob")==null?"":rs.getString("status_Ob"));
						h.put("nokpbaru", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
						h.put("nokpbaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
						h.put("nokpbaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
						h.put("nokpbaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
						h.put("nokplama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
						h.put("jeniskp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
						h.put("nokplain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
						h.put("noberanak", rs.getString("no_Surat_Beranak")==null?"":rs.getString("no_Surat_Beranak"));
						h.put("dob",rs.getDate("tarikh_Lahir")==null?"":sdf.format(rs.getDate("tarikh_Lahir")));			
						h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
						h.put("noTel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
						h.put("jantina", rs.getString("jantina")==null?"":rs.getString("jantina"));
						h.put("umur", rs.getString("umur")==null?"":rs.getString("umur"));
						h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
						h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
						h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
						h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
						h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
						h.put("statushidup", rs.getString("status_Hidup")==null?"":rs.getString("status_Hidup"));
						h.put("taraf", rs.getString("id_Tarafkptg")==null?"":rs.getString("id_Tarafkptg"));
						h.put("saudara", rs.getString("id_Saudara")==null?"":rs.getString("id_Saudara"));
						h.put("agama", rs.getString("jenis_Agama")==null?"":rs.getString("jenis_Agama"));
						h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
						h.put("warga", rs.getString("jenis_Warga")==null?"":rs.getString("jenis_Warga"));
						h.put("butiranhutang", rs.getString("butiran_Hutang")==null?"":rs.getString("butiran_Hutang"));
						h.put("nilaihutang", rs.getString("nilai_Hutang")==null?"":rs.getDouble("nilai_Hutang"));
						h.put("noakaun", rs.getString("no_Akaun")==null?"":rs.getString("no_Akaun"));
						h.put("negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
						h.put("notel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
						h.put("nohp", rs.getString("no_hp")==null?"":rs.getString("no_hp"));
						h.put("pemiutang", rs.getString("jenis_pemiutang")==null?"":rs.getString("jenis_pemiutang"));
						h.put("idbandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
						listPentingbyIDOB.addElement(h);
						
					}
					
					}
					finally {
						if(db != null) db.close();
					}
					
				}
		
				public static int checkNewPemohon(String nokpbaru, String nokplama, String nokplain) throws Exception {
					Db db = null;
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					try {
						db = new Db();

						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						String kpBaru = nokpbaru.trim();
						String kpLama = nokplama.trim();
						String kpLain = nokplain.trim();
						String sql = "Select count(*) as cntId from tblppkpemohon a ";
						String sqlwhere="";
						//KP BARU PEMOHON
					      if (kpBaru != "") {
								if (!kpBaru.trim().equals("")) {
									sqlwhere = sqlwhere + " UPPER(a.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%' ";
								}
							}
					    //KP LAMA PEMOHON
					      if (kpLama != "") {
								if (!kpLama.trim().equals("")) {
									if (sqlwhere != "") {
										sqlwhere = sqlwhere + " OR UPPER(a.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%' ";
									} else {
										sqlwhere = sqlwhere + " UPPER(a.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%' ";
									}
								}
							}
					    //KP LAIN PEMOHON
					      if (kpLain != "") {
								if (!kpLain.trim().equals("")) {
									if (sqlwhere != "") {
										sqlwhere = sqlwhere + " OR UPPER(a.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%' ";
									} else {
										sqlwhere = sqlwhere + " UPPER(a.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%' ";
									}
								}
							}
					        
					    if (sqlwhere != "") {
								sql = sql + "WHERE ( "+sqlwhere+" ) ";
						}
					    
						ResultSet rs = stmt.executeQuery(sql);
						Hashtable h;
						if (rs.next()) {
							return rs.getString("cntId")==null?0:Integer.parseInt(rs.getString("cntId"));
						} else return 0;
						
					}finally {
						if(db != null) db.close();
					}
					}
				
				public static String getIdPemohon(String kpbaru, String kplama, String kplain) throws Exception {
					Db db = null;
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Vector resultSimati = new Vector();
					try {
						db = new Db();
						String kpBaru = kpbaru.trim();
						String kpLama = kplama.trim();
						String kpLain = kplain.trim();
						String sql = "Select * from (Select (ID_PEMOHON) as cntId FROM TBLPPKPEMOHON ";
						
						String sqlwhere = "";
						
						if (kpBaru != "") {
							if (!kpBaru.trim().equals("")) {
								sqlwhere = sqlwhere + " UPPER(NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%'";
							}
						}
						if (kpLama != "") {
							if (!kpLama.trim().equals("")) {
								if (sqlwhere != "") {
									sqlwhere = sqlwhere + " OR UPPER(NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
								} else {
									sqlwhere = sqlwhere + " UPPER(NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
								}
								
							}
						}
						if (kpLain != "") {
							if (!kpLain.trim().equals("")) {
								if (sqlwhere != "") {
									sqlwhere = sqlwhere + " OR UPPER(NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
								} else {
									sqlwhere = sqlwhere + " UPPER(NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
								}
							}
						}
						
						if (sqlwhere != "") {
							sql = sql + " WHERE ( "+sqlwhere+" )) where rownum < 2";
						} 
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						ResultSet rs = stmt.executeQuery(sql);
						Hashtable h;

						if (rs.next()) {	
							return rs.getString("cntId")==null?"":rs.getString("cntId");
						} else return "";
						
						//return resultSimati;
					}finally {
						if(db != null) db.close();
					}
					}
	
	public static Vector getDetailsPemohonDetails(String idPermohonan) throws Exception{
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector detailPemohon = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT * FROM (SELECT P.ID_PEMOHON,PP.SEKSYEN,PP.ID_STATUS,P.NO_KP_BARU, P.NO_KP_LAMA, P.JENIS_KP, P.NO_KP_LAIN , " +
					"P.NAMA_PEMOHON, PP.NO_PERMOHONAN_ONLINE, PP.FLAG_HUBUNGAN_PEMOHON,PP.ID_HUBUNGANPEMOHON, PP.TARIKH_MOHON_ONLINE, " +
					"PP.ID_PERMOHONAN, F.NO_FAIL, F.ID_FAIL, M1.ID_PERMOHONANSIMATI, M1.ID_SIMATI, (SELECT PP2.NO_SUBJAKET FROM " +
					"TBLPPKPERMOHONAN PP2 WHERE PP2.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NOSUBJAKET," +
					"(SELECT F1.NO_FAIL FROM TBLPFDFAIL F1, TBLPPKPERMOHONAN PP1 WHERE F1.ID_FAIL = PP1.ID_FAIL AND " +
					" F1.ID_FAIL = PP.ID_PERMOHONANTERDAHULU) AS NOFAILLAMA, " +
					"(SELECT f1.id_fail FROM tblpfdfail f1, tblppkpermohonan pp1 WHERE f1.id_fail = pp1.id_fail AND pp1.id_permohonan = pp.id_permohonanterdahulu)AS IDFAILLAMA, " +
					"(SELECT ps1.id_permohonansimati "+
                    "FROM tblpfdfail f1, tblppkpermohonan pp1,tblppkpermohonansimati ps1 "+
                    "WHERE f1.id_fail = pp1.id_fail "+
                    "AND pp1.id_permohonan = ps1.id_permohonan "+
                    "AND f1.id_fail = pp.id_permohonanterdahulu) AS idpermohonansimatilama, "+
					"(SELECT N.NAMA_NEGERI FROM TBLPPKPERMOHONAN PP1, " +
					"TBLRUJNEGERI N WHERE N.ID_NEGERI = PP1.ID_NEGERIMHN AND PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NAMANEGERI," +
					"(SELECT D.NAMA_DAERAH FROM TBLPPKPERMOHONAN PP1, TBLRUJDAERAH D WHERE D.ID_DAERAH = PP1.ID_DAERAHMHN AND " +
					"PP1.ID_PERMOHONAN = PP.ID_PERMOHONANTERDAHULU) AS NAMADAERAH, PP.HARTA_TINGGAL,PP.BATAL_KUASA_PENTADBIR,PP.BATAL_P_AMANAH," +
					"PP.LANTIK_P_AMANAH,PP.LANTIK_PENTADBIR,C.NO_KP_BARU AS NO_KP_BARU_SM, C.NO_KP_LAIN AS NO_KP_LAIN_SM, C.NO_KP_LAMA AS NO_KP_LAMA_SM,C.NAMA_SIMATI,S.KETERANGAN," +
					"PP.TARIKH_MOHON FROM Tblppkpermohonan PP, Tblppksimati c, " +
					"Tblppkpemohon P, Tblpfdfail F, Tblppkpermohonansimati M1, Tblrujstatus s WHERE P.ID_PEMOHON = PP.ID_PEMOHON " +
					"AND PP.ID_FAIL = F.ID_FAIL  AND PP.ID_PERMOHONAN = M1.ID_PERMOHONAN  AND M1.ID_SIMATI = C.ID_SIMATI and " +
					"PP.ID_STATUS = S.ID_STATUS";

			String sqlwhere = "UPPER(pp.ID_PERMOHONAN) = '"+ idPermohonan +"'";
			
			if (sqlwhere != "") {
				sql = sql + " AND ( "+sqlwhere+" ) ORDER BY PP.ID_PERMOHONAN DESC ) WHERE ROWNUM < 2";
			} 			
			
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("SQL idfaillama :: "+sql);
			
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("idPemohon",rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
				h.put("idPermohonan",rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("noKpBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("noKpBaru1", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(0,6));
				h.put("noKpBaru2", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(6,8));
				h.put("noKpBaru3", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU").substring(8,12));
				h.put("noKpLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
				h.put("jenisKp", rs.getString("JENIS_KP")==null?"":rs.getString("JENIS_KP"));
				h.put("noKpLain", rs.getString("NO_KP_LAIN")==null?"":rs.getString("NO_KP_LAIN"));
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("noKpBaruPemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("idfail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
				h.put("flagHubungan", rs.getString("FLAG_HUBUNGAN_PEMOHON")==null?"":rs.getString("FLAG_HUBUNGAN_PEMOHON"));
				h.put("idHubungan", rs.getString("ID_HUBUNGANPEMOHON")==null?"":rs.getString("ID_HUBUNGANPEMOHON"));
				h.put("idSimati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nosubjaket", rs.getString("NOSUBJAKET")==null?"":rs.getString("NOSUBJAKET"));
				h.put("nofaillama", rs.getString("NOFAILLAMA")==null?"":rs.getString("NOFAILLAMA"));
				h.put("idfaillama", rs.getString("IDFAILLAMA")==null?"":rs.getString("IDFAILLAMA"));
				h.put("namanegeri", rs.getString("NAMANEGERI")==null?"":rs.getString("NAMANEGERI"));
				h.put("namadaerah", rs.getString("NAMADAERAH")==null?"":rs.getString("NAMADAERAH"));
				h.put("hartatinggal", rs.getString("HARTA_TINGGAL")==null?"":rs.getString("HARTA_TINGGAL"));
				h.put("batalpentadbir", rs.getString("BATAL_KUASA_PENTADBIR")==null?"":rs.getString("BATAL_KUASA_PENTADBIR"));
				h.put("batalamanah", rs.getString("BATAL_P_AMANAH")==null?"":rs.getString("BATAL_P_AMANAH"));
				h.put("lantikamanah", rs.getString("LANTIK_P_AMANAH")==null?"":rs.getString("LANTIK_P_AMANAH"));
				h.put("lantikpentadbir", rs.getString("LANTIK_PENTADBIR")==null?"":rs.getString("LANTIK_PENTADBIR"));
				h.put("idstatus", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
				//h.put("tarikhmohon", rs.getString("TARIKH_MOHON")==null?"":rs.getString("TARIKH_MOHON"));
				//CetakPengesahanView
				h.put("tarikhmohon", rs.getString("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("namasimati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("no_kp_baru_sm", rs.getString("NO_KP_BARU_SM")==null?"":rs.getString("NO_KP_BARU_SM"));
				h.put("no_kp_lain_sm", rs.getString("NO_KP_LAIN_SM")==null?"":rs.getString("NO_KP_LAIN_SM"));
				h.put("no_kp_lama_sm", rs.getString("NO_KP_LAMA_SM")==null?"":rs.getString("NO_KP_LAMA_SM"));
				h.put("idpermohonansimatilama", rs.getString("idpermohonansimatilama")==null?"":rs.getString("idpermohonansimatilama"));

				
				detailPemohon.addElement(h);
			}
			return detailPemohon;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public static Vector getPAPTHTA(String idPermohonanSimati) throws Exception{
		
		Db db = null;
		String sql = "";
		Vector listPAPTHTA = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT A.FLAG_PA,A.FLAG_PT FROM TBLPPKHTA A, TBLPPKPERMOHONANSIMATI B, TBLPPKSIMATI C"+ 
					" WHERE A.ID_SIMATI = C.ID_SIMATI"+
					" AND B.ID_SIMATI = C.ID_SIMATI"+
					" AND A.JENIS_HTA = Y"+
					" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("FLAG_PA", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("FLAG_PT", rs.getString("FLAG_PT")==null?"":rs.getString("FLAG_PT"));			
				listPAPTHTA.addElement(h);
			}return listPAPTHTA;	
		}

	finally {
		if(db != null) db.close();
	}
	}
	public static Vector setDataHTAdulu(String idsimati,String bkp,String lp,String bpa,String lpa) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector listHTAdulu = new Vector();
		System.out.println("bkp---"+bkp);
		System.out.println("lp---"+lp);
		System.out.println("bpa---"+bpa);
		System.out.println("lpa---"+lpa);
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT MS.ID_PERMOHONANSIMATI,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, " +
				"H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, " +
				"H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, " +
				"H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,  H.NO_PERSERAHAN, " +
				"H.CATATAN, H.STATUS_PEMILIKAN, H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI, " +
				"N.NAMA_NEGERI, D.NAMA_DAERAH, MU.NAMA_MUKIM,(Select count(*) from tblppkpilihanhta where " +
				"id_permohonansimati = '"+idsimati+"' and id_hta = H.ID_HTA) as PILIH "+
				"FROM TBLPPKHTA H, TBLPPKSIMATI S, " +
				"TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, " +
				"TBLPPKPERMOHONANSIMATI MS1, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJMUKIM MU " +
				"WHERE H.ID_SIMATI = S.ID_SIMATI   " +
				"AND H.ID_SIMATI = MS.ID_SIMATI  " +
				"AND MS.ID_PERMOHONANSIMATI <> H.ID_PERMOHONANSIMATI " +
				"AND MS.ID_PERMOHONANSIMATI = '"+idsimati+"'   " +
				"AND H.JENIS_HTA = 'Y' " +
				"AND H.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   " +
				"AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  " +
				"AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  " +
				"AND P1.NO_SUBJAKET < P.NO_SUBJAKET  " +
				"AND S.ID_SIMATI = MS.ID_SIMATI AND N.ID_NEGERI = H.ID_NEGERI AND D.ID_DAERAH = H.ID_DAERAH " +
				"AND MU.ID_MUKIM = H.ID_MUKIM ";

				if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("T") && lpa.equals("T")))
				{
				sql += " AND H.FLAG_PT = 'Y'";
				}
				else if((bkp.equals("T") && lp.equals("T")) && (bpa.equals("Y") || lpa.equals("Y")))
				{
				sql += " AND H.FLAG_PA = 'Y'";             
				}
				else if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("Y") || lpa.equals("Y")))
				{
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";	
				}
				else
				{	
				sql += "";
				}
								
				sql += " ORDER BY H.ID_HTA DESC";		
			
			System.out.println("HTAAM dahulu :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));			
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb")==null?"":rs.getString("id_Jenispb"));
				h.put("negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("noperserahan", rs.getString("no_Perserahan")==null?"":rs.getString("no_Perserahan"));			
				h.put("flag_pt", rs.getString("FLAG_PT")==null?"":rs.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("namanegeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("namadaerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("namamukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
				h.put("pilih", rs.getString("PILIH")==null?"":rs.getString("PILIH"));
				listHTAdulu.addElement(h);
			}
			return listHTAdulu;
			}
			finally {
				if(db != null) db.close();
			}
		}
	
	public static void addPilihanHta(String userid,String idpermohonansimati, String idhta) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
    	   long idInsert = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
    	   db = new Db();
       	   Statement stmt = db.getStatement();
		   SQLRenderer r = new SQLRenderer();  
		   r.add("id_pilihanhta",idInsert);
		   r.add("id_permohonansimati",Integer.parseInt(idpermohonansimati));
		   r.add("id_hta",Integer.parseInt(idhta));
		   r.add("id_masuk",Integer.parseInt(userid));
		   r.add("tarikh_masuk",r.unquote("sysdate"));
		   r.add("id_kemaskini",Integer.parseInt(userid));
		   r.add("tarikh_kemaskini",r.unquote("sysdate"));
	       sql = r.getSQLInsert("tblppkpilihanhta");
	       System.out.println("tblppkpilihanhta-->>"+sql);
		   stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static void addPilihanHa(String userid,String idpermohonansimati, String idha) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
    	   long idInsert = DB.getNextID("TBLPPKPILIHANHA_SEQ");
    	   db = new Db();
       	   Statement stmt = db.getStatement();
		   SQLRenderer r = new SQLRenderer();  
		   r.add("id_pilihanha",idInsert);
		   r.add("id_permohonansimati",Integer.parseInt(idpermohonansimati));
		   r.add("id_ha",Integer.parseInt(idha));
		   r.add("id_masuk",Integer.parseInt(userid));
		   r.add("tarikh_masuk",r.unquote("sysdate"));
		   r.add("id_kemaskini",Integer.parseInt(userid));
		   r.add("tarikh_kemaskini",r.unquote("sysdate"));
	       sql = r.getSQLInsert("tblppkpilihanha");
	       System.out.println("tblppkpilihanhta-->>"+sql);
		   stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public static void deletePilihanHta(String idpermohonansimati) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
    	   db = new Db();
    	   Statement stmta = db.getStatement();
	       String sql1 = "Delete from tblppkpilihanhta where id_permohonansimati = '"+ idpermohonansimati +"'";
		   System.out.println("Delete tblppkpilihanhta-->>"+sql1);
	       stmta.executeUpdate(sql1);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static void deletePilihanHa(String idpermohonansimati) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
    	   db = new Db();
    	   Statement stmta = db.getStatement();
	       String sql1 = "Delete from tblppkpilihanha where id_permohonansimati = '"+ idpermohonansimati +"'";
		   System.out.println("Delete tblppkpilihanhta-->>"+sql1);
	       stmta.executeUpdate(sql1);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector listPilihanHta(String idpermohonansimati) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_hta");
	      sql = r.getSQLSelect("tblppkpilihanhta");
	      sql = sql + " where id_permohonansimati = '"+ idpermohonansimati +"'";
	      System.out.println("-->>"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("idhta", rs.getString("id_hta")==null?"":rs.getString("id_hta"));
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector setDataHTATHdulu(String idsimati,String bkp,String lp,String bpa,String lpa) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector listHTAdulu = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, " +
				"H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, " +
				"H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, " +
				"H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,  H.NO_PERSERAHAN, " +
				"H.CATATAN, H.STATUS_PEMILIKAN, H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI, " +
				"N.NAMA_NEGERI, D.NAMA_DAERAH, MU.NAMA_MUKIM,(Select count(*) from tblppkpilihanhta where " +
				"id_permohonansimati = '"+idsimati+"' and id_hta = H.ID_HTA) as PILIH "+
				"FROM TBLPPKHTA H, TBLPPKSIMATI S, " +
				"TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, " +
				"TBLPPKPERMOHONANSIMATI MS1, TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJMUKIM MU " +
				"WHERE H.ID_SIMATI = S.ID_SIMATI   " +
				"AND H.ID_SIMATI = MS.ID_SIMATI  " +
				"AND MS.ID_PERMOHONANSIMATI <> H.ID_PERMOHONANSIMATI " +
				"AND MS.ID_PERMOHONANSIMATI = '"+idsimati+"'   " +
				"AND H.JENIS_HTA = 'T' " +
				"AND H.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   " +
				"AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  " +
				"AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  " +
				"AND P1.NO_SUBJAKET < P.NO_SUBJAKET  " +
				"AND S.ID_SIMATI = MS.ID_SIMATI AND N.ID_NEGERI = H.ID_NEGERI AND D.ID_DAERAH = H.ID_DAERAH " +
				"AND MU.ID_MUKIM = H.ID_MUKIM ";

				if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("T") && lpa.equals("T")))
				{
				sql += " AND H.FLAG_PT = 'Y'";
				}
				else if((bkp.equals("T") && lp.equals("T")) && (bpa.equals("Y") || lpa.equals("Y")))
				{
				sql += " AND H.FLAG_PA = 'Y'";             
				}
				else if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("Y") || lpa.equals("Y")))
				{
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";	
				}
				else
				{	
				sql += "";
				}
								
				sql += " ORDER BY H.ID_HTA DESC";		
			
			System.out.println("HTAAMX :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));			
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb")==null?"":rs.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("noperserahan", rs.getString("no_Perserahan")==null?"":rs.getString("no_Perserahan"));			
				h.put("flag_pt", rs.getString("FLAG_PT")==null?"":rs.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("namanegeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("namadaerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("namamukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
				h.put("pilih", rs.getString("PILIH")==null?"":rs.getString("PILIH"));
				listHTAdulu.addElement(h);
			}
			return listHTAdulu;
			}
			finally {
				if(db != null) db.close();
			}
		}
	
	public static Vector setDatalistHTAbaru(String idpermohonanterdahulu) throws Exception {
	    Db db = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();    
	      String sql = "Select a.id_daerahmhn, a.id_negerimhn, b.id_permohonansimati from tblppkpermohonan a, tblppkpermohonansimati b where a.id_permohonan = b.id_permohonan and a.id_permohonan = "+ idpermohonanterdahulu +" ";
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v1 = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("iddaerahmhn", rs.getString("id_daerahmhn")==null?"":rs.getString("id_daerahmhn"));
	        h.put("idnegerimhn", rs.getString("id_negerimhn")==null?"":rs.getString("id_negerimhn"));
	        h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
	        v1.addElement(h);
	      }
	      return v1;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector setDataHTABaru(String idpermohonansimati) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector datalist = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("h.id_Hta");
		r.add("h.no_Hakmilik");
		r.add("h.id_Simati");
		r.add("h.no_Pt");
		r.add("h.nilai_Hta_Tarikhmohon");
		r.add("h.nilai_Hta_Tarikhmati");
		r.add("h.id_Kategori");
		r.add("h.id_Jenishm");
		r.add("h.id_Jenispb");
		r.add("h.id_Negeri");
		r.add("h.id_Daerah");
		r.add("h.id_Luas");
		r.add("h.id_Mukim");
		r.add("h.luas_Hmp");
		r.add("h.luas");
		r.add("h.no_Cagaran");
		r.add("h.no_Pajakan");
		r.add("h.jenis_Tnh");
		r.add("h.ba_Simati");
		r.add("h.bb_Simati");
		r.add("h.jenis_Hta");
		r.add("h.tanggungan");
		r.add("h.no_Perserahan");
		r.add("h.catatan");
		r.add("h.status_Pemilikan");
		r.add("h.id_permohonansimati");

		r.add("h.id_simati",r.unquote("m1.id_simati"));
		r.add("m1.id_simati",r.unquote("s.id_simati(+)"));
		r.add("m1.id_permohonan",r.unquote("p.id_permohonan(+)"));
		r.add("h.id_simati",r.unquote("s.id_simati"));
		
		r.add("h.id_permohonansimati",idpermohonansimati);
		String status="Y";
		String baru="T";
		r.add("h.jenis_Hta",status);
		r.add("h.flag_pa",baru);
		r.add("h.flag_pt",baru);
		r.add("h.flag_selesai",baru);
		sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s, Tblppkpermohonansimati M1, Tblppkpermohonan p,");
		
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		
		while(rs.next()) {
			h = new Hashtable();
			h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
			h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));			
			h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
			h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getString("nilai_Hta_Tarikhmohon"));
			h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getString("nilai_Hta_Tarikhmati"));
			h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
			h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
			h.put("pemilikan", rs.getString("id_Jenispb")==null?"":rs.getString("id_Jenispb"));
			h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
			h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
			h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
			h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
			h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
			h.put("nocagaran", rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
			h.put("nopajakan", rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
			h.put("jenistanah", rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
			h.put("basimati", rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
			h.put("bbsimati", rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
			h.put("jenishta", rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
			h.put("tanggungan", rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
			h.put("jenisluas", rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
			h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("noperserahan", rs.getString("no_Perserahan")==null?"":rs.getString("no_Perserahan"));
			h.put("idpermohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
			datalist.addElement(h);
		}
		return datalist;
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	
	public static String getIdPermohonan(String NoPermohonanOnline) throws Exception {
		
		String output="0";
		String sql = "";
		Db db = null;
		try {
			db = new Db(); 
			sql = "Select id_permohonan from tblppkpermohonan where " +
					"no_permohonan_online='"+NoPermohonanOnline+"'";
			System.out.println("sql idpermohonan " +sql);
			ResultSet rs = db.getStatement().executeQuery(sql); 
			if (rs.next()){	
				output = rs.getString("id_permohonan");
			}
		} catch (Exception e) {
			throw new Exception ("error getIdPermohonan :"+e.getMessage());
		}finally {
			if (db != null)db.close();
		}
		return output;
	}
	
	public static String getIdPermohonanSimati(String IdPermohonan) throws Exception {
		
		String output="0";
		String sql = "";
		Db db = null;
		try {
			db = new Db(); 
			sql = "Select id_permohonansimati from tblppkpermohonansimati where " +
					"id_permohonan='"+IdPermohonan+"'";
			myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql); 
			if (rs.next()){	
				output = rs.getString("id_permohonansimati");
			}
		} catch (Exception e) {
			throw new Exception ("error getid_permohonansimati :"+e.getMessage());
		}finally {
			if (db != null)db.close();
		}
		return output;
	}
}


