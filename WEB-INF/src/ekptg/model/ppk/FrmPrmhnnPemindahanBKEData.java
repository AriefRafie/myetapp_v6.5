package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmPrmhnnPemindahanBKEData {
	
	static Logger myLogger = Logger.getLogger(FrmPrmhnnPemindahanBKEData.class);	

	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	public static Vector setList(int usernegeri, String userdaerah) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector list = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("f.id_Fail");
			r.add("f.no_Fail");
			r.add("a.id_Permohonan");
			r.add("a.tarikh_Mohon");
			r.add("a.tarikh_Masuk");
			r.add("f.tarikh_daftar_fail");
			r.add("s.keterangan");
			r.add("p.id_Simati");
			r.add("p.nama_Simati");
			r.add("a.seksyen");
			
			r.add("a.id_Status",r.unquote("s.id_Status(+)"));
			r.add("a.id_Fail",r.unquote("f.id_Fail(+)"));
			r.add("a.id_Permohonan",r.unquote("M1.id_Permohonan"));
			r.add("M1.id_simati",r.unquote("p.id_simati"));
			
			//r.add("a.id_negerimhn",usernegeri);
			//r.add("a.id_daerahmhn",userdaerah);
			
			sql = r.getSQLSelect("Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpermohonansimati M1, Tblrujdaerah d");
			sql = sql + " and a.ID_DAERAHMHN = d.ID_DAERAH and  a.FLAG_JENIS_PERMOHONAN = 1 and a.id_negeritertinggi is null and a.id_daerahtertinggi is null and a.id_status in (8,14,56,61,153) and d.id_negeri = "+ usernegeri +" ORDER BY F.ID_FAIL DESC";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
				list.addElement(h);
				bil++;	
			}
			return list;
		}finally {
			if(db != null) db.close();
			
		}
		}

	private static Vector list = new Vector();
	public static void  setCarianFail(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc, int usernegeri)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String chkDataFail = noFail.trim();
	      String chkDataPemohon = namaPemohon.trim();
	      String chkDataSimati = namaSimati.trim();
	      String chkDataIcSimati = icSimati.trim();
	      String chkDataJenisKp = JenisIc;
	      sql = "Select f.id_fail,f.no_fail,f.tarikh_daftar_fail,a.id_permohonan,a.tarikh_Mohon,a.tarikh_Masuk,s.keterangan,p.id_simati,p.nama_simati,pp.nama_pemohon,a.seksyen "+
	      	"from Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pp, Tblppkpermohonansimati M1, Tblrujdaerah d "+
	      	"where a.id_status = s.id_status(+) and a.id_fail = f.id_fail(+) " +
	      	//"and a.id_permohonan = pp.id_permohonan(+) and " +
	      	"and a.id_pemohon = pp.id_pemohon(+) and " +
	      	"M1.id_permohonan = a.id_permohonan and M1.id_simati = p.id_simati ";
	      
	      //NO FAIL
	      if (noFail != "") {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
				}
			}
	    //NAMA PEMOHON
	      if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
				}
			}
	    //NAMA SIMATI
	      if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(P.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
				}
			}
	    //IC SIMATI
	      if (icSimati != "") {
			   if (!icSimati.trim().equals("")) {
					if (chkDataJenisKp.equals("1")){
						sql = sql + " AND UPPER(P.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("2")){
						sql = sql + " AND UPPER(P.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("3")){
						sql = sql + " AND UPPER(P.NO_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
		    	}
			}  
	      sql = sql + "  and a.ID_DAERAHMHN = d.ID_DAERAH " +
	      		"and a.FLAG_JENIS_PERMOHONAN = 1  and a.id_status in (8,14,56,61,153) " +
	      		"and d.id_negeri = "+ usernegeri +" ORDER BY F.ID_FAIL DESC";
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("tarikh_Mohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
				list.addElement(h);
	    	  bil++;
	    	  count++;  
	      }
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil","");
				h.put("id_Permohonan", "");
				h.put("id_Fail", "");
				h.put("no_Fail", "");
				h.put("tarikh_Mohon","");
				h.put("tarikhMasuk","");
				h.put("keterangan","");
				h.put("id_simati", "");
				h.put("namasimati", "");
				h.put("seksyen", "");
				list.addElement(h);
	      }
	    } finally {
	      if (db != null) db.close();
	    }
	}
	 public static Vector getList(){
		  return list;
	  }
	
	
	public static Vector getNegeriDaerahMohon(String idPermohonan) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("n.id_negeri");
	      r.add("n.nama_negeri");
	      r.add("d.nama_daerah");
	      r.add("d.id_daerah");
	      
	      r.add("p.id_permohonan",r.unquote("M1.id_permohonan"));
	      r.add("M1.id_simati",r.unquote("m.id_simati"));
	      r.add("p.id_negerimhn",r.unquote("n.id_negeri"));
	      r.add("p.id_daerahmhn",r.unquote("d.id_daerah"));
	      
	      r.add("p.id_permohonan",idPermohonan);
	     
	      sql = r.getSQLSelect("Tblppkpermohonan p, Tblppkpermohonansimati M1, Tblppksimati m, Tblrujnegeri n, Tblrujdaerah d");
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("namanegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
	        h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
	        h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
	        h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getBkeData(String idPermohonan) throws Exception {
	    Db db = null;
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("t.ID_BKE");
	      r.add("t.ID_PERMOHONAN");
	      r.add("t.ID_NEGERI");
	      r.add("t.ID_DAERAH");
	      r.add("t.TARIKH_MOHON");
	      r.add("t.ID_UNITPSK");
	      r.add("t.ID_NEGERIUNITPSK");
	      r.add("t.JENIS_KELUAR_PERINTAH");
	      r.add("t.ALASAN_1");
	      r.add("t.ALASAN_2");
	      r.add("t.ALASAN_3");
	      r.add("t.ALASAN_4");
	      r.add("t.ALASAN_5");
	      r.add("t.ALASAN_6");
	      r.add("t.ALASAN_7");
	      r.add("t.ALASAN_LAIN");
	      r.add("t.KEPUTUSAN_PEGAWAI");
	      r.add("t.ID_UNITPSK");
	      r.add("t.KEPUTUSAN_KPTG_PTG");
	      r.add("t.CATATAN_PEGAWAI");
	      r.add("t.CATATAN");
	      r.add("t.TARIKH_LULUS");
	      r.add("p.ID_FAIL");
	      
	      r.add("p.id_permohonan",r.unquote("M1.id_permohonan"));
	      r.add("M1.id_permohonan",r.unquote("t.id_permohonan"));
	      
	      r.add("p.id_permohonan",idPermohonan);
	     
	      sql = r.getSQLSelect("Tblppkpermohonan p, Tblppkbke t, Tblppkpermohonansimati m1");
	      //System.out.println("xxxxxxxx-->>"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector listBke = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("idbke", rs.getString("ID_BKE")==null?"":rs.getString("ID_BKE"));
	        h.put("idnegeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
	        h.put("iddaerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
	        h.put("tarikhmohon", sdf.format(rs.getDate("TARIKH_MOHON"))==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
	        h.put("idunitpsk", rs.getString("ID_UNITPSK")==null?"":rs.getString("ID_UNITPSK"));
	        h.put("idnegeriunitpsk", rs.getString("ID_NEGERIUNITPSK")==null?"":rs.getString("ID_NEGERIUNITPSK"));
	        h.put("jenisperintah", rs.getString("JENIS_KELUAR_PERINTAH")==null?"":rs.getString("JENIS_KELUAR_PERINTAH"));
	        h.put("alasan1", rs.getString("ALASAN_1")==null?"":rs.getString("ALASAN_1"));
	        h.put("alasan2", rs.getString("ALASAN_2")==null?"":rs.getString("ALASAN_2"));
	        h.put("alasan3", rs.getString("ALASAN_3")==null?"":rs.getString("ALASAN_3"));
	        h.put("alasan4", rs.getString("ALASAN_4")==null?"":rs.getString("ALASAN_4"));
	        h.put("alasan5", rs.getString("ALASAN_5")==null?"":rs.getString("ALASAN_5"));
	        h.put("alasan6", rs.getString("ALASAN_6")==null?"":rs.getString("ALASAN_6"));
	        h.put("alasan7", rs.getString("ALASAN_7")==null?"":rs.getString("ALASAN_7"));
	        h.put("alasanlain", rs.getString("ALASAN_LAIN")==null?"":rs.getString("ALASAN_LAIN"));
	        h.put("keputusanpegawai", rs.getString("KEPUTUSAN_PEGAWAI")==null?"":rs.getString("KEPUTUSAN_PEGAWAI"));
	        h.put("keputusankptg", rs.getString("KEPUTUSAN_KPTG_PTG")==null?"":rs.getString("KEPUTUSAN_KPTG_PTG"));
	        h.put("catatanpegawai", rs.getString("CATATAN_PEGAWAI")==null?"":rs.getString("CATATAN_PEGAWAI"));
	        if (rs.getString("TARIKH_LULUS")!=null){
	        	h.put("tarikhlulus", sdf.format(rs.getDate("TARIKH_LULUS"))==null?"":sdf.format(rs.getDate("TARIKH_LULUS")));	
	        }
	        h.put("catatan", rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));

	        listBke.addElement(h);
	      }
	      return listBke;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static int chkBkeData(String idPermohonan) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      sql = "Select count(id_permohonan) as cntdata from tblppkbke where id_permohonan = '"+ idPermohonan +"'";
	      //System.out.println("chkBkeData-->>"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector listBke = new Vector();
	      if (rs.next()) {
	    		return rs.getString("cntdata")==null?0:Integer.parseInt(rs.getString("cntdata"));
	    	} else return 0;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	 
	 public static Vector getMaklumatPermohonan(String id) throws Exception{
			Db db = null;
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Vector listData = new Vector();
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				/*SQLRenderer r = new SQLRenderer();
				
				r.add("f.id_Fail");
				r.add("f.no_Fail");
				r.add("d.id_Daerah");
				r.add("p.id_Permohonan");
				r.add("p.tarikh_Mohon");
				r.add("s.no_Kp_Baru");
				r.add("s.no_Kp_Lama");
				r.add("s.jenis_Kp");
				r.add("s.no_Kp_Lain");
				r.add("s.id_Simati");
				r.add("s.nama_Simati");
				r.add("s.tarikh_Mati");
				r.add("pm.id_Pemohon");
				r.add("pm.nama_Pemohon");
				r.add("pm.no_kp_baru");
				r.add("pm.no_kP_lama");
				r.add("pm.jenis_Kp");
				r.add("pm.no_Kp_lain");
				
				r.add("pm.alamat_1");
				r.add("pm.alamat_2");
				r.add("pm.alamat_3");
				r.add("pm.poskod");
				r.add("pm.bandar");
				r.add("n.id_Negeri");
				r.add("n.nama_Negeri");
				r.add("d.nama_Daerah");
				r.add("p.seksyen");
				r.add("st.keterangan");
				r.add("p.id_Status");
				r.add("u.nama_pejabat");
				r.add("pm.id_negeri");
				
				r.add("sub.id_Suburusanstatus");			
				r.add("sub.id_Suburusanstatusfail");
				r.add("sub.aktif");
				r.add("mosi.id_Permohonansimati");
				
				r.add("s.umur");//36
				r.add("s.jantina");//37
				r.add("pm.umur");//38
				r.add("pm.jantina");//39

				r.add("d.id_negeri",r.unquote("n.kod_Negeri(+)"));
				r.add("p.id_Daerahmhn",r.unquote("d.id_Daerah(+)"));
				r.add("p.id_Fail",r.unquote("f.id_Fail"));
				r.add("pm.id_Permohonan",r.unquote("p.id_Permohonan"));
				r.add("s.id_Simati",r.unquote("mosi.id_Simati"));
				r.add("p.id_Permohonan",r.unquote("mosi.id_Permohonan"));
				r.add("p.id_Permohonan",r.unquote("sub.id_Permohonan"));

				r.add("st.id_Status",r.unquote("p.id_Status"));
				r.add("d.id_daerah",r.unquote("u.id_daerah(+)"));
				//r.add("sub.aktif",1);
				r.add("p.id_Permohonan",id);
				
				sql = r.getSQLSelect("Tblpfdfail f, Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, Tblppkpemohon pm, Tblrujstatus st, tblrujpejabatjkptg u, Tblrujsuburusanstatusfail sub, Tblppkpermohonansimati mosi");*/
				sql = "SELECT f.id_Fail, f.no_Fail, d.id_Daerah, p.id_Permohonan, p.tarikh_Mohon, s.no_Kp_Baru, s.no_Kp_Lama, s.jenis_Kp, s.no_Kp_Lain," +
						"s.id_Simati, s.nama_Simati, s.tarikh_Mati, pm.id_Pemohon, pm.nama_Pemohon, pm.no_kp_baru, pm.no_kP_lama, pm.jenis_Kp, pm.no_Kp_lain," +
						"u.nama_pejabat, pm.alamat_1, pm.alamat_2, pm.alamat_3, pm.poskod, pm.bandar, n.id_Negeri, n.nama_Negeri, d.nama_Daerah, p.seksyen, st.keterangan, " +
						"p.id_Status, u.nama_pejabat, pm.id_negeri, sub.id_Suburusanstatus, sub.id_Suburusanstatusfail, sub.aktif, mosi.id_Permohonansimati, " +
						"s.umur, s.jantina, pm.umur, pm.jantina,(select nama_pejabat from tblRujPejabatJKPTG  where id_pejabatjkptg in " +
						"(select distinct id_pejabatjkptg from TBLRUJPEJABATURUSAN where id_daerahurus = p.id_daerahmhn and id_jenispejabat=22 and id_seksyen=2)) as namapejabat," +
						"(select alamat1 from tblRujPejabatJKPTG  where id_pejabatjkptg in (select distinct id_pejabatjkptg from TBLRUJPEJABATURUSAN where " +
						"id_daerahurus = p.id_daerahmhn and id_jenispejabat=22 and id_seksyen=2)) as alamat1 " +
						"FROM Tblpfdfail f, Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, Tblppkpemohon pm, Tblrujstatus st, " +
						"tblrujpejabatjkptg u, Tblrujsuburusanstatusfail sub, Tblppkpermohonansimati mosi " +
						"WHERE d.id_negeri = n.kod_Negeri(+)  AND p.id_Daerahmhn = d.id_Daerah(+)  AND p.id_Fail = f.id_Fail  AND " +
						"pm.id_pemohon = p.id_pemohon AND s.id_Simati = mosi.id_Simati  AND p.id_Permohonan = mosi.id_Permohonan  " +
						"AND p.id_Permohonan = sub.id_Permohonan AND st.id_Status = p.id_Status  " +
						"and u.id_pejabatjkptg in (select id_pejabatjkptg from TBLRUJPEJABATURUSAN where id_daerahurus = p.id_daerahmhn and id_jenispejabat=22 and id_seksyen=2) " +
						"AND p.id_Permohonan = '"+ id +"'";
				sql += " AND U.ID_SEKSYEN = '2' AND U.ID_JENISPEJABAT = '22' AND SUB.AKTIF = '1'";
				myLogger.info("sql bke-->>"+sql);
				ResultSet rs = stmt.executeQuery(sql);
			    Hashtable h;
				
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("id_Permohonansimati", rs.getString("id_Permohonansimati")==null?"":rs.getString("id_Permohonansimati"));
			    	  h.put("id_Suburusanstatus", rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
			    	  h.put("id_Suburusanstatusfail", rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));
			    	  h.put("idFail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
			    	  h.put("noFail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
			    	  h.put("idDaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
			    	  h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			    	  h.put("tarikhMohon", sdf.format(rs.getDate("tarikh_Mohon"))==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
			    	  h.put("noKpBaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
			    	  h.put("noKpBaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
			    	  h.put("noKpBaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
			    	  h.put("noKpLama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
			    	  h.put("jenisKp", rs.getString(8)==null?"":rs.getString(8));
			    	  h.put("noKpLain", rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
			    	  h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			    	  h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
			    	  h.put("tarikhMati",  sdf.format(rs.getDate("tarikh_Mati"))==null?"": sdf.format(rs.getDate("tarikh_Mati")));
			    	  h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
			    	  h.put("namaPemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
			    	  h.put("noKpBaruPemohon1", rs.getString(15)==null?"":rs.getString(15).substring(0,6));
			    	  h.put("noKpBaruPemohon2", rs.getString(15)==null?"":rs.getString(15).substring(6,8));
			    	  h.put("noKpBaruPemohon3", rs.getString(15)==null?"":rs.getString(15).substring(8,12));
			    	  h.put("noKpLamaPemohon", rs.getString(16)==null?"":rs.getString(16));
			    	  h.put("jenisKpPemohon", rs.getString(17)==null?"":rs.getString(17));
			    	  h.put("noKpLainPemohon", rs.getString(18)==null?"":rs.getString(18));
			    	  h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
			    	  h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
			    	  h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
			    	  h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
			    	  h.put("namanegeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
			    	  h.put("namadaerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
			    	  h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
			    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	  h.put("id_Status", rs.getString("id_Status")==null?"":rs.getString("id_Status"));
			    	  h.put("namaPejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    	  h.put("pmidnegeri", rs.getString(31)==null?"":rs.getString(31));
			    	  h.put("id_Suburusanstatus", rs.getString("id_Suburusanstatus")==null?"":rs.getString("id_Suburusanstatus"));
			    	  h.put("id_Suburusanstatusfail", rs.getString("id_Suburusanstatusfail")==null?"":rs.getString("id_Suburusanstatusfail"));
			    	  h.put("umursimati", rs.getString(36)==null?"":rs.getString(36));
			    	  h.put("jantinasimati", rs.getString(37)==null?"":rs.getString(37));
			    	  h.put("umurpemohon", rs.getString(38)==null?"":rs.getString(38));
			    	  h.put("jantinapemohon", rs.getString(39)==null?"":rs.getString(39));
			    	  h.put("nama_pejabat", rs.getString("namapejabat")==null?"":rs.getString("namapejabat"));
			    	  h.put("alamat_1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			    	  listData.addElement(h);
			}   
			      return listData;
		}
		finally {
			if(db != null)db.close();
			}
	}
	 
	 public static void insertPermohonanBke(String cbAlasan, String idPermohonan,String alasanLain, String idmasuk) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    try
	    {
	      long idsemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql2 = "Insert into tblsemakanhantar (ID_SEMAKANHANTAR, ID_SEMAKANSENARAI, ID_PERMOHONAN, CATATAN, ID_MASUK, TARIKH_MASUK) values ("+idsemakanhantar+","+cbAlasan+","+idPermohonan+"," +
	      		"'"+alasanLain+"',"+idmasuk+",sysdate)";
	      stmt.executeUpdate(sql2);
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	 
	 public static void deletePermohonanBke(String idPermohonan,String idmasuk) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    try
	    {
	      long idsemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	      
	      db = new Db();
	      Statement stmtC = db.getStatement();
	      sql = "Delete from tblsemakanhantar where ID_PERMOHONAN = "+idPermohonan+" and ID_SEMAKANSENARAI between 83 and 89";
	      stmtC.executeUpdate(sql);

	    } finally {
	      if (db != null) db.close();
	    }
	  }
	 
	 public static void insertNewBke(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    String sql3 = "";
	    try
	    {
	    	
	      long idBke = DB.getNextID("TBLPPKBKE_SEQ");
	      String userId = (String)data.get("iduser");
	      String idPermohonan = (String)data.get("IdPermohonan");
	      String idnegeri = (String)data.get("socnegeri");
	      String iddaerah = (String)data.get("socdaerah");
	      String tarikhsurat = (String)data.get("tarikhsurat");
	      String alasanlain = (String)data.get("alasanlain");
	      String alasan1 = "";
	      String alasan2 = "";
	      String alasan3 = "";
	      String alasan4 = "";
	      String alasan5 = "";
	      String alasan6 = "";
	      String alasan7 = "";
	      
	      if ((String)data.get("alasan1")!=null){
	    	  alasan1 = (String)data.get("alasan1");
	      }else{
	    	  alasan1 = "";
	      }
	      
	      if ((String)data.get("alasan2")!=null){
	    	  alasan2 = (String)data.get("alasan2");
	      }else{
	    	  alasan2 = "";
	      }
	      
	      if ((String)data.get("alasan3")!=null){
	    	  alasan3 = (String)data.get("alasan3");
	      }else{
	    	  alasan3 = "";
	      }
	    	
	      if ((String)data.get("alasan4")!=null){
	    	  alasan4 = (String)data.get("alasan4");
	      }else{
	    	  alasan4 = "";
	      }
	      
	      if ((String)data.get("alasan5")!=null){
	    	  alasan5 = (String)data.get("alasan5");
	      }else{
	    	  alasan5 = "";
	      }
	      
	      if ((String)data.get("alasan6")!=null){
	    	  alasan6 = (String)data.get("alasan6");
	      }else{
	    	  alasan6 = "";
	      }
	      
	      if ((String)data.get("alasan7")!= null){
	    	  alasan7 = (String)data.get("alasan7");
	      }else{
	    	  alasan7 = "";
	      }
	      if ((String)data.get("alasanlain")!= null){
	    	  alasanlain = (String)data.get("alasanlain");
	      }else{
	    	  alasanlain = "";
	      }
	      
	      String tarikh_surat = "to_date('" + tarikhsurat + "','dd/MM/yyyy')";
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "Insert into TBLPPKBKE (ID_BKE, ID_PERMOHONAN, ID_NEGERI, ID_DAERAH, TARIKH_MOHON, ALASAN_1, ALASAN_2, ALASAN_3," +
	      		"ALASAN_4, ALASAN_5, ALASAN_6, ALASAN_7, ALASAN_LAIN, ID_MASUK, TARIKH_MASUK,ID_KEMASKINI, TARIKH_KEMASKINI) values ("+idBke+","+idPermohonan+","+idnegeri+"," +
	      		""+iddaerah+","+tarikh_surat+",'"+alasan1+"','"+alasan2+"','"+alasan3+"','"+alasan4+"','"+alasan5+"'," +
	      		"'"+alasan6+"','"+alasan7+"','"+alasanlain+"',"+userId+",sysdate,"+userId+",sysdate)";
	      stmt.executeUpdate(sql);
	    
	      	db = new Db();
			Statement stmtF = db.getStatement();
			sql1 = "Update tblrujsuburusanstatusfail set AKTIF = 0, ID_KEMASKINI = "+userId+",TARIKH_KEMASKINI = sysdate where id_permohonan = "+ idPermohonan +"";
			stmtF.executeUpdate(sql1);
			
			db = new Db();
			Statement stmtA = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = "insert into tblrujsuburusanstatusfail (ID_SUBURUSANSTATUSFAIL,ID_PERMOHONAN,ID_SUBURUSANSTATUS,AKTIF,ID_MASUK,TARIKH_MASUK,ID_KEMASKINI,TARIKH_KEMASKINI," +
				   "ID_FAIL) values ("+DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ")+","+idPermohonan+",408,1,"+userId+"," +
				   "sysdate,"+userId+",sysdate,(Select id_fail from tblppkpermohonan where id_permohonan="+idPermohonan+"))";
			stmtA.executeUpdate(sql2);
			
			db = new Db();
			Statement stmtB = db.getStatement();
			sql3 = "Update tblppkpermohonan set id_status = 61 where id_permohonan = "+ idPermohonan +"";
			stmtB.executeUpdate(sql3);
	    
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	 
	 public static void updateBke(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    String sql3 = "";
	    try
	    {
	    	
	      long idBke = DB.getNextID("TBLPPKBKE_SEQ");
	      String userId = (String)data.get("iduser");
	      String idPermohonan = (String)data.get("IdPermohonan");
	      String idnegeri = (String)data.get("socnegeri");
	      String iddaerah = (String)data.get("socdaerah");
	      String tarikhsurat = (String)data.get("tarikhsurat");
	      String alasanlain = (String)data.get("alasanlain");
	      String alasan1 = "";
	      String alasan2 = "";
	      String alasan3 = "";
	      String alasan4 = "";
	      String alasan5 = "";
	      String alasan6 = "";
	      String alasan7 = "";
	      
	      if ((String)data.get("alasan1")!=null){
	    	  alasan1 = (String)data.get("alasan1");
	      }else{
	    	  alasan1 = "";
	      }
	      
	      if ((String)data.get("alasan2")!=null){
	    	  alasan2 = (String)data.get("alasan2");
	      }else{
	    	  alasan2 = "";
	      }
	      
	      if ((String)data.get("alasan3")!=null){
	    	  alasan3 = (String)data.get("alasan3");
	      }else{
	    	  alasan3 = "";
	      }
	    	
	      if ((String)data.get("alasan4")!=null){
	    	  alasan4 = (String)data.get("alasan4");
	      }else{
	    	  alasan4 = "";
	      }
	      
	      if ((String)data.get("alasan5")!=null){
	    	  alasan5 = (String)data.get("alasan5");
	      }else{
	    	  alasan5 = "";
	      }
	      
	      if ((String)data.get("alasan6")!=null){
	    	  alasan6 = (String)data.get("alasan6");
	      }else{
	    	  alasan6 = "";
	      }
	      
	      if ((String)data.get("alasan7")!= null){
	    	  alasan7 = (String)data.get("alasan7");
	      }else{
	    	  alasan7 = "";
	      }
	      
	      String tarikh_surat = "to_date('" + tarikhsurat + "','dd/MM/yyyy')";
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "update TBLPPKBKE set ID_NEGERI = "+idnegeri+",ID_DAERAH = "+iddaerah+", TARIKH_MOHON="+tarikh_surat+", ALASAN_1='"+alasan1+"'," +
	      		"ALASAN_2='"+alasan2+"', ALASAN_3='"+alasan3+"',ALASAN_4='"+alasan4+"',ALASAN_5='"+alasan5+"',ALASAN_6='"+alasan6+"'," +
	      		"ALASAN_7='"+alasan7+"', ALASAN_LAIN='"+alasanlain+"', ID_KEMASKINI="+userId+", TARIKH_KEMASKINI=sysdate where id_permohonan = "+ idPermohonan +"";
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	 
	 public static Vector getDaerahbyNegeri(String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "select n.id_negeri,n.nama_negeri, d.id_daerah,d.kod_daerah, d.nama_daerah " +
		      		" from tblppkbke b, tblrujnegeri n, tblrujdaerah d, tblrujpejabaturusan s where " +
		      		"b.id_negeri = n.id_negeri and b.id_daerah = d.id_daerah and b.id_permohonan = '"+ idPermohonan +"' " +
		      				" group by n.id_negeri,n.nama_negeri, d.id_daerah,d.kod_daerah, d.nama_daerah";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("idnegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		        h.put("namanegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
		        h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
		        h.put("kod", rs.getString("kod_daerah")==null?"":rs.getString("kod_daerah"));
		        h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	 }
	 
	 public static Vector getNamaPegawai(int userid) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "select j.id_unitpsk, j.id_negeri, j.nama_pegawai, j.jawatan from users_internal u, tblppkrujunit j " +
		      		"where u.id_negeri = j.id_negeri and u.user_id = "+ userid +" " +
		      		"group by j.id_unitpsk, j.id_negeri, j.nama_pegawai, j.jawatan";
		
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("idunitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
		        h.put("idnegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		        h.put("namapegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
		        h.put("jawatan", rs.getString("jawatan")==null?"":rs.getString("jawatan"));
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	 }
	 
	 public static void updateBkeKeputusan(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    String sql3 = "";
	    try
	    {
	    	
	      String userId = (String)data.get("iduser");
	      String idPermohonan = (String)data.get("IdPermohonan");
	      String keputusan = (String)data.get("keputusan");
	      String pegawai = (String)data.get("pegawai");
	      int pengendali = Integer.parseInt((String)data.get("pengendali"));
	      String catatan = (String)data.get("catatan");
	      int idnegeripsk = (Integer)data.get("idnegeripsk");
	      
	      int idstatus_fail = 0;
	      int idstatus_permohonan = 0;
	      
	      if (keputusan.equals("L")){
	    	  idstatus_fail = 455;
	    	  idstatus_permohonan = 153;
	      }else{
	    	  idstatus_fail = 340;
	    	  idstatus_permohonan = 8;
	      }
	    	  
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "update TBLPPKBKE set KEPUTUSAN_PEGAWAI = '"+ keputusan +"',JENIS_KELUAR_PERINTAH = '"+ pegawai +"'," +
	      		"ID_UNITPSK = "+ pengendali +" , ID_NEGERIUNITPSK = "+ idnegeripsk +", CATATAN_PEGAWAI = '"+ catatan +"' ,ID_KEMASKINI="+userId+", " +
	      	    "TARIKH_KEMASKINI=sysdate where id_permohonan = '"+ idPermohonan +"'";
	      stmt.executeUpdate(sql);
	      
	      	db = new Db();
			Statement stmtF = db.getStatement();
			sql1 = "Update tblrujsuburusanstatusfail set AKTIF = 0, ID_KEMASKINI = "+userId+", TARIKH_KEMASKINI = sysdate where " +
					"ID_SUBURUSANSTATUSFAIL in (select ID_SUBURUSANSTATUSFAIL from tblrujsuburusanstatusfail where AKTIF = 1 and id_permohonan = '"+ idPermohonan +"')";
			stmtF.executeUpdate(sql1);
			
			db = new Db();
			Statement stmtA = db.getStatement();
			sql2 = "INSERT INTO tblrujsuburusanstatusfail (ID_SUBURUSANSTATUSFAIL, ID_PERMOHONAN, ID_SUBURUSANSTATUS, AKTIF, " +
					"ID_FAIL, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI)  VALUES " +
					"("+DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ")+",'"+idPermohonan+"',"+idstatus_fail+",1," +
					"(SELECT ID_FAIL FROM tblppkpermohonan where id_permohonan = '"+ idPermohonan +"'),"+userId+"," +
					"sysdate ,"+userId+",sysdate)";
			stmtA.executeUpdate(sql2);
			
			db = new Db();
			Statement stmtB = db.getStatement();
			sql3 = "Update tblppkpermohonan set id_status = "+ idstatus_permohonan +" where id_permohonan = '"+ idPermohonan +"'";
			stmtB.executeUpdate(sql3);
	      
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	 
	 public static int cntKeputusan(String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "Select count(id_unitpsk) as cntData from tblppkbke where id_permohonan = '"+ idPermohonan +"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      if (rs.next()) {
		    		return rs.getString("cntData")==null?0:Integer.parseInt(rs.getString("cntData"));
		    	} else return 0;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	 }
	 
	 public static void updateKptgKeputusan(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    String sql3 = "";
	    try
	    {
	      String userId = (String)data.get("iduser");
	      String idPermohonan = (String)data.get("IdPermohonan");
	      String tarikhlulus = (String)data.get("tarikhlulus");
	      String keputusan = (String)data.get("keputusan");
	      String catatankptg = (String)data.get("catatankptg");
	      int insertfail = (Integer)data.get("insertfail");
	      
	      
	      int idstatus_fail = 0;
	      int idstatus_permohonan = 0;
	      
	      if (keputusan.equals("L")){
	    	  idstatus_fail = 407;
	    	  idstatus_permohonan = 56;
	      }else{
	    	  idstatus_fail = 340;
	    	  idstatus_permohonan = 8;
	      }
	      
	      String tarikh_lulus = "to_date('" + tarikhlulus + "','dd/MM/yyyy')";
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "update TBLPPKBKE set KEPUTUSAN_KPTG_PTG = '"+ keputusan +"',TARIKH_LULUS = "+ tarikh_lulus +"," +
	      		"CATATAN = '"+ catatankptg +"', ID_KEMASKINI = "+userId+", TARIKH_KEMASKINI = sysdate " +
	      		"where id_permohonan = '"+ idPermohonan +"'";
	      stmt.executeUpdate(sql);
	      
	      if (insertfail==1){
	    	  db = new Db();
				Statement stmtF = db.getStatement();
				sql1 = "Update tblrujsuburusanstatusfail set AKTIF = 0, ID_KEMASKINI = "+userId+", TARIKH_KEMASKINI = sysdate where " +
						"ID_SUBURUSANSTATUSFAIL in (select ID_SUBURUSANSTATUSFAIL from tblrujsuburusanstatusfail where AKTIF = 1 and id_permohonan = '"+ idPermohonan +"' and ID_SUBURUSANSTATUS <> "+ idstatus_fail +")";
				stmtF.executeUpdate(sql1);
				
				db = new Db();
				Statement stmtA = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql2 = "insert into tblrujsuburusanstatusfail (ID_SUBURUSANSTATUSFAIL,ID_PERMOHONAN,ID_SUBURUSANSTATUS,AKTIF,ID_MASUK,TARIKH_MASUK," +
				"ID_KEMASKINI,TARIKH_KEMASKINI,ID_FAIL) values ("+DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ")+",'"+idPermohonan+"',"+idstatus_fail+",1,"+userId+",sysdate,"+userId+",sysdate,(select id_fail from tblppkpermohonan where id_permohonan = '"+ idPermohonan +"'))";
				stmtA.executeUpdate(sql2);
				
				db = new Db();
				Statement stmtB = db.getStatement();
				sql3 = "Update tblppkpermohonan set id_status = "+ idstatus_permohonan +" where id_permohonan = '"+ idPermohonan +"'";
				stmtB.executeUpdate(sql3);
	      }
	      	
			
			
			
			
	      
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	 
	 public static Vector cntDataPersamaan(String idPermohonan, String keputusan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "Select count(KEPUTUSAN_KPTG_PTG) as cntData from tblppkbke where id_permohonan = '"+ idPermohonan +"' and KEPUTUSAN_KPTG_PTG = '"+ keputusan +"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("cntpersamaan", rs.getString("cntData")==null?"":rs.getString("cntData"));
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	 }
	 
	 public static Vector cntKeputusanKptg(String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "Select count(KEPUTUSAN_KPTG_PTG) as cntKptg from tblppkbke where id_permohonan = '"+ idPermohonan +"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("cntkptg", rs.getString("cntKptg")==null?"":rs.getString("cntKptg"));
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	 }
	 
	 public static Vector getUserDetail(String userid) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "Select id_negeri,id_daerah from users_internal where user_id = "+ userid +"";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("idnegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		        h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	 }
	 
	 public static Vector getListDaerahbyNegeri(int idnegeri, String idpermohonan) throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("d.id_Daerah");
		      r.add("d.nama_Daerah");
		      r.add("d.kod_Daerah");
		      r.add("d.id_Negeri");
		      
		      r.add("d.id_daerah",r.unquote("p.id_daerahurus"));
		      r.add("d.id_negeri",idnegeri);
		      
		      //sql = r.getSQLSelect("Tblrujdaerah d , tblrujpejabaturusan p");
		      //sql = sql + " group by d.id_daerah,d.nama_daerah,d.kod_daerah,d.id_negeri order by d.id_daerah";
		      sql += "SELECT distinct a4.id_Daerah, a4.kod_Daerah, a4.nama_Daerah, a3.id_negeri " +
		      		  "FROM tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5" +
		      		  ",tblppkpermohonan a6,tblppkpermohonansimati a7,tblppkhta a8 " +
		      		  " WHERE a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG " +
		      		  " AND a1.id_negeriurus = a3.id_negeri AND a1.id_daerahurus = a4.id_daerah " +
		      		  " AND a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
		      		  " AND a6.id_permohonan = a7.id_permohonan " +
		      		  " AND a7.id_simati = a8.id_simati " +
		      		  " AND a8.id_negeri = a1.id_negeriurus AND a6.id_permohonan = '"+ idpermohonan +"' " +
		      		  " AND a3.id_negeri = "+ idnegeri +" " +
		      		  " AND a1.ID_JENISPEJABAT = 22 AND a1.id_seksyen = 2 ";
		      sql += " UNION SELECT distinct a4.id_Daerah, a4.kod_Daerah, a4.nama_Daerah, a3.id_negeri " +
      		  "FROM tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5" +
      		  ",tblppkpermohonan a6,tblppkpermohonansimati a7,tblppkha a8 " +
      		  " WHERE a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG " +
      		  " AND a1.id_negeriurus = a3.id_negeri AND a1.id_daerahurus = a4.id_daerah " +
      		  " AND a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
      		  " AND a6.id_permohonan = a7.id_permohonan " +
      		  " AND a7.id_simati = a8.id_simati " +
      		  " AND a8.id_negeri = a1.id_negeriurus AND a6.id_permohonan = '"+ idpermohonan +"' " +
      		  " AND a3.id_negeri = "+ idnegeri +" " +
      		  " AND a1.ID_JENISPEJABAT = 22 AND a1.id_seksyen = 2 ";
// Kemaskini Oleh Mohamad Rosli pada 30/03/2011, senarai semua daerah selain daerah pohon
//		      		  "AND A1.ID_PEJABATJKPTG NOT IN (SELECT ID_PEJABATJKPTG FROM tblrujpejabaturusan WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN " +
//		      		  "AND A6.ID_PERMOHONAN = '"+ idpermohonan +"') " +
		      	
		      	sql += " group by a4.id_Daerah, a4.kod_Daerah, a4.nama_Daerah, a3.id_negeri";
		      	
		      	myLogger.info("GET DAERAH ::: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id", rs.getInt("id_Daerah"));
		        if(rs.getString("nama_Daerah") == null){
		        	h.put("nama", "");
		        }else{
		        	h.put("nama", rs.getString("nama_Daerah"));
		        }
		        if(rs.getString("kod_Daerah") == null){
		        	h.put("kod", "");
		        }else{
		        	h.put("kod", rs.getString("kod_Daerah"));
		        }
		        h.put("id_negeri", rs.getInt("id_Negeri"));
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Vector getListnegeri() throws Exception {
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
		      
		      r.add("n.id_negeri",r.unquote("p.id_negeri"));
		      
		      sql = r.getSQLSelect("Tblrujnegeri n, tblrujpejabaturusan p");
		      sql = sql + " group by n.id_Negeri,n.nama_Negeri,n.kod_Negeri";
		      System.out.println("getListnegeri-->>>"+sql);
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
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 
	 public static Vector getListnegeribyhta(String idpermohonan) throws Exception {
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
		      
		      r.add("n.id_negeri",r.unquote("p.id_negeri"));
		      
		      //sql = r.getSQLSelect("Tblrujnegeri n, tblrujpejabaturusan p");
		      //sql = sql + " group by n.id_Negeri,n.nama_Negeri,n.kod_Negeri";
		      /*sql = "select a3.id_negeri, a3.kod_negeri, a3.NAMA_NEGERI " +
		      		" from tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5,tblppkpermohonan a6," +
		      		"tblppkpermohonansimati a7,tblppkhta a8 where a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG and " +
		      		"a1.id_negeriurus = a3.id_negeri and a1.id_daerahurus = a4.id_daerah and a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
		      		"and a6.id_permohonan = a7.id_permohonan and a7.id_permohonansimati = a8.id_permohonansimati " +
		      		"and a8.id_negeri = a1.id_negeriurus and a6.id_permohonan = "+ idpermohonan +" and a1.ID_JENISPEJABAT = 22 and a1.id_seksyen = 2 " +
		      		"AND A1.ID_PEJABATJKPTG NOT IN (SELECT ID_PEJABATJKPTG FROM tblrujpejabaturusan WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN " +
		      		"AND A6.ID_PERMOHONAN = '"+ idpermohonan +"') " +
		      		"group by a3.id_negeri, a3.kod_negeri, a3.NAMA_NEGERI";*/
		      
		      

		      sql = " SELECT DISTINCT  A3.ID_NEGERI, A3.KOD_NEGERI, A3.NAMA_NEGERI "+
		          " FROM TBLRUJPEJABATURUSAN A1,TBLRUJNEGERI A3,TBLRUJDAERAH A4,TBLRUJPEJABATJKPTG A5, "+
		    	  " TBLPPKPERMOHONAN A6,TBLPPKPERMOHONANSIMATI A7,TBLPPKHTA A8 "+
		    	  " WHERE A1.ID_PEJABATJKPTG = A5.ID_PEJABATJKPTG AND A1.ID_NEGERIURUS = A3.ID_NEGERI "+
		    	  " AND A1.ID_DAERAHURUS = A4.ID_DAERAH AND A6.ID_DAERAHMHN NOT IN (A1.ID_DAERAHURUS) "+
		    	  " AND A6.ID_PERMOHONAN = A7.ID_PERMOHONAN AND A7.ID_SIMATI = A8.ID_SIMATI "+
		    	  " AND A8.ID_NEGERI = A1.ID_NEGERIURUS AND A6.ID_PERMOHONAN = '"+ idpermohonan +"' "+
		    	  " AND A1.ID_JENISPEJABAT = 22 AND A1.ID_SEKSYEN = 2 "+
		    	  " AND A1.ID_PEJABATJKPTG NOT IN ( SELECT ID_PEJABATJKPTG "+
		    	  " FROM TBLRUJPEJABATURUSAN WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN "+
		    	  " AND A6.ID_PERMOHONAN = '"+ idpermohonan +"') "+
		    	  " UNION "+
		    	  " SELECT  DISTINCT A3.ID_NEGERI, A3.KOD_NEGERI, A3.NAMA_NEGERI "+
		    	  " FROM TBLRUJPEJABATURUSAN A1, TBLRUJNEGERI A3, TBLRUJDAERAH A4, "+
		    	  " TBLRUJPEJABATJKPTG A5, TBLPPKPERMOHONAN A6, TBLPPKPERMOHONANSIMATI A7, TBLPPKHA A8 "+
		    	  " WHERE A1.ID_PEJABATJKPTG = A5.ID_PEJABATJKPTG AND A1.ID_NEGERIURUS = A3.ID_NEGERI "+
		    	  " AND A1.ID_DAERAHURUS = A4.ID_DAERAH  AND A6.ID_DAERAHMHN NOT IN (A1.ID_DAERAHURUS) "+
		    	  " AND A6.ID_PERMOHONAN = A7.ID_PERMOHONAN AND A7.ID_SIMATI = A8.ID_SIMATI "+
		    	  " AND A8.ID_NEGERI = A1.ID_NEGERIURUS AND A6.ID_PERMOHONAN = '"+ idpermohonan +"' "+
		    	  " AND A1.ID_JENISPEJABAT = 22  AND A1.ID_SEKSYEN = 2 "+
		    	  " AND A1.ID_PEJABATJKPTG NOT IN ( SELECT ID_PEJABATJKPTG "+
		    	  " FROM TBLRUJPEJABATURUSAN WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN "+
		    	  " AND A6.ID_PERMOHONAN = '"+ idpermohonan +"') "+
		    	  " GROUP BY A3.ID_NEGERI, A3.KOD_NEGERI, A3.NAMA_NEGERI ";

		      
		      
		      myLogger.info("BKE getListnegeribyhta  "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Negeri", rs.getInt("id_negeri"));
		        if(rs.getString("nama_Negeri") == null){
		        	h.put("NAMA_NEGERI", "");
		        }else{
		        	h.put("nama_Negeri", rs.getString("NAMA_NEGERI"));
		        }
		        if(rs.getString("kod_negeri") == null){
		        	h.put("kod_Negeri", "");
		        }else{
		        	h.put("kod_Negeri", rs.getString("kod_negeri"));
		        }
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	 
		public static int getListnegeribyhtacount(String idpermohonan) throws Exception {
		    Db db = null;
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();    
		      String  sql = "select ";
		      
		      sql += "((select count(a3.id_negeri) as CntId " +
	      		" from tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5,tblppkpermohonan a6," +
	      		"tblppkpermohonansimati a7,tblppkhta a8 where a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG and " +
	      		"a1.id_negeriurus = a3.id_negeri and a1.id_daerahurus = a4.id_daerah and a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
	      		"and a6.id_permohonan = a7.id_permohonan and a7.id_simati = a8.id_simati " +
	      		"and a8.id_negeri = a1.id_negeriurus and a6.id_permohonan = '"+ idpermohonan +"' and a1.ID_JENISPEJABAT = 22 and a1.id_seksyen = 2 " +
	      		"AND A1.ID_PEJABATJKPTG NOT IN (SELECT ID_PEJABATJKPTG FROM tblrujpejabaturusan WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN " +
	      		"AND A6.ID_PERMOHONAN = '"+ idpermohonan +"')) " ;
		      
		      sql += "+ (select count(a3.id_negeri) as CntId " +
	      		" from tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5,tblppkpermohonan a6," +
	      		"tblppkpermohonansimati a7,tblppkha a8 where a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG and " +
	      		"a1.id_negeriurus = a3.id_negeri and a1.id_daerahurus = a4.id_daerah and a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
	      		"and a6.id_permohonan = a7.id_permohonan and a7.id_simati = a8.id_simati " +
	      		"and a8.id_negeri = a1.id_negeriurus and a6.id_permohonan = '"+ idpermohonan +"' and a1.ID_JENISPEJABAT = 22 and a1.id_seksyen = 2 " +
	      		"AND A1.ID_PEJABATJKPTG NOT IN (SELECT ID_PEJABATJKPTG FROM tblrujpejabaturusan WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN " +
	      		"AND A6.ID_PERMOHONAN = '"+ idpermohonan +"'))) " ;
		      
		      sql += " as  CntId from dual ";
		      
		      myLogger.info("GETCOUNT :"+sql);
		      
	      		//"group by a3.id_negeri, a3.kod_negeri, a3.NAMA_NEGERI";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v1 = new Vector();
		      if (rs.next()) {
		    		return rs.getString("CntId")==null?0:Integer.parseInt(rs.getString("CntId"));
		    	} else return 0;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
		
		public static Vector getDaerahbyNegerihta (String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "select a3.id_negeri, a3.kod_negeri, a3.nama_negeri, a4.id_daerah, a4.kod_daerah, a4.nama_daerah " +
	      		" from tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5,tblppkpermohonan a6," +
	      		"tblppkpermohonansimati a7,tblppkhta a8 where a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG and " +
	      		"a1.id_negeriurus = a3.id_negeri and a1.id_daerahurus = a4.id_daerah and a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
	      		"and a6.id_permohonan = a7.id_permohonan and a7.id_permohonansimati = a8.id_permohonansimati " +
	      		"and a8.id_negeri = a1.id_negeriurus and a6.id_permohonan = '"+ idPermohonan +"' and a1.ID_JENISPEJABAT = 22 and a1.id_seksyen = 2 " +
	      		"AND A1.ID_PEJABATJKPTG NOT IN (SELECT ID_PEJABATJKPTG FROM tblrujpejabaturusan WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN " +
	      		"AND A6.ID_PERMOHONAN = '"+ idPermohonan +"') " +
	      		"group by a3.id_negeri, a3.kod_negeri, a3.nama_negeri, a4.id_daerah, a4.kod_daerah, a4.nama_daerah";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("idnegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		        h.put("namanegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
		        h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
		        h.put("kod", rs.getString("kod_daerah")==null?"":rs.getString("kod_daerah"));
		        h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	 }
		
		public static Vector getListDaerahbyNegerihta(int idnegeri,String idpermohonan) throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		    	SQLRenderer r = new SQLRenderer();
			    r.add("id_Daerah");
			    r.add("nama_Daerah");
			    r.add("kod_Daerah");
			    r.add("id_Negeri");		      		      
			    r.add("id_Negeri",idnegeri);
		     
			    sql += "select distinct a4.id_Daerah, a4.kod_Daerah, a4.nama_Daerah, a3.id_negeri " +
		      	"from tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5,tblppkpermohonan a6," +
		      	"tblppkpermohonansimati a7,tblppkhta a8 where a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG and " +
		      	"a1.id_negeriurus = a3.id_negeri and a1.id_daerahurus = a4.id_daerah and a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
		      	"and a6.id_permohonan = a7.id_permohonan and a7.id_simati = a8.id_simati and " +
		      	"a8.id_negeri = a1.id_negeriurus and a6.id_permohonan = '"+ idpermohonan +"' and a3.id_negeri = "+ idnegeri +" " +
		      	"and a1.ID_JENISPEJABAT = 22 and a1.id_seksyen = 2 " +
		      	"AND A1.ID_PEJABATJKPTG NOT IN (SELECT ID_PEJABATJKPTG FROM tblrujpejabaturusan WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN " +
		      	"AND A6.ID_PERMOHONAN = '"+ idpermohonan +"') ";
			    sql += "UNION distinct select a4.id_Daerah, a4.kod_Daerah, a4.nama_Daerah, a3.id_negeri " +
		      	"from tblrujpejabaturusan a1,tblrujnegeri a3, tblrujdaerah a4,TBLRUJPEJABATJKPTG a5,tblppkpermohonan a6," +
		      	"tblppkpermohonansimati a7,tblppkha a8 where a1.ID_PEJABATJKPTG = a5.ID_PEJABATJKPTG and " +
		      	"a1.id_negeriurus = a3.id_negeri and a1.id_daerahurus = a4.id_daerah and a6.ID_daerahMHN NOT IN (a1.id_daerahurus) " +
		      	"and a6.id_permohonan = a7.id_permohonan and a7.id_simati = a8.id_simati and " +
		      	"a8.id_negeri = a1.id_negeriurus and a6.id_permohonan = '"+ idpermohonan +"' and a3.id_negeri = "+ idnegeri +" " +
		      	"and a1.ID_JENISPEJABAT = 22 and a1.id_seksyen = 2 " +
		      	"AND A1.ID_PEJABATJKPTG NOT IN (SELECT ID_PEJABATJKPTG FROM tblrujpejabaturusan WHERE ID_DAERAHURUS = A6.ID_DAERAHMHN " +
		      	"AND A6.ID_PERMOHONAN = '"+ idpermohonan +"') ";
		      	sql += "group by a4.id_Daerah, a4.kod_Daerah, a4.nama_Daerah, a3.id_negeri";
		      
		      	myLogger.info("sql getDaerah"+sql);
			    ResultSet rs = stmt.executeQuery(sql);
			    Vector v = new Vector();
			    while (rs.next()) {
			    	Hashtable h = new Hashtable();
			    	h.put("id", rs.getInt("id_Daerah"));	       
			    	if(rs.getString("nama_Daerah") == null){
			    		h.put("nama", "");
			    	}else{
			    		  h.put("nama", rs.getString("nama_Daerah"));
			    	}
			    	if(rs.getString("kod_Daerah") == null){
			    		h.put("kod", "");
			    	}else{
			    		h.put("kod", rs.getString("kod_Daerah"));
			    	}
			       
			    	v.addElement(h);
			    
			    }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
}
