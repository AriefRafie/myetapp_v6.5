/**
 * 
 */
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

/**
 * @author anis
 *
 */
public class FrmSenaraiFailKeputusanPermohonanData {
	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8KptsanBicaraData.class);
	
	private static Vector listKeputusan = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	public static void setListKeputusan() throws Exception {
		Db db = null;
		listKeputusan.clear();
	      String sql = "Select f.id_fail,f.no_fail,f.tarikh_daftar_fail,a.id_permohonan,a.tarikh_Mohon,a.tarikh_Masuk," +
    		"s.keterangan,p.id_simati,p.nama_simati,p.tempat_mati,pp.nama_pemohon,kp.keputusan_Permohonan," +
    		"(select ss.keterangan from Tblrujstatus ss where ss.id_status = kp.keputusan_Permohonan) as status_keputusan "+
    		"from Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pp, Tblppkkeputusanpermohonan kp "+
    		"where a.id_status = s.id_status(+) and a.id_fail = f.id_fail(+) and a.id_permohonan = pp.id_permohonan(+) " +
    		"and p.id_permohonan = a.id_permohonan AND a.id_Permohonan = kp.id_Permohonan(+) and a.id_status = 14 ";
		/*String sql = "SELECT f.id_Fail, f.no_Fail, p.id_Permohonan, p.tarikh_Mohon, kp.id_Keputusanpermohonan," +
				"kp.keputusan_Permohonan, p.id_Status, s.keterangan, p.tarikh_Masuk, f.tarikh_daftar_fail," +
				"(select ss.keterangan from Tblrujstatus ss where ss.id_status = kp.keputusan_Permohonan) as status_keputusan " +
				"FROM Tblppkpermohonan p, Tblpfdfail f, Tblppkkeputusanpermohonan kp, Tblrujstatus s WHERE " +
				"p.id_Status = s.id_Status  AND p.id_Fail = f.id_Fail(+)  AND p.id_Permohonan = kp.id_Permohonan(+) " +
				"and p.id_status=14 order by id_permohonan desc";*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("-------Read Here----");
		try{
			System.out.println("-------Read Here----");
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("f.id_Fail");
			r.add("f.no_Fail");
			r.add("p.id_Permohonan");
			r.add("p.tarikh_Mohon");
			r.add("kp.id_Keputusanpermohonan");
			r.add("kp.keputusan_Permohonan");
			r.add("a.id_Status");
			//r.add("p.id_Status");
			//r.add("p.id_Status");
			r.add("s.keterangan");
			r.add("p.tarikh_Masuk");
			r.add("f.tarikh_daftar_fail");
			r.add("p.nama_simati");
			r.add("p.tempat_mati");
		//	r.add("p.id_Status",r.unquote("s.id_Status"));
			r.add("p.id_Fail",r.unquote("f.id_Fail(+)"));
			r.add("p.id_Permohonan",r.unquote("kp.id_Permohonan(+)"));
			
			System.out.println("list senarai pemohonan"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("tempatmati", rs.getString("tempat_mati")==null?"":rs.getString("tempat_mati"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("id_Status", rs.getString("id_Status")==null?"":rs.getString("id_Status"));
				h.put("id_KeputusanPermohonan", rs.getString("id_KeputusanPermohonan")==null?"":rs.getString("id_KeputusanPermohonan"));
				h.put("keputusan_Permohonan", rs.getString("keputusan_Permohonan")==null?"":rs.getString("keputusan_Permohonan"));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("tarikhMasuk",rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar",rs.getString("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("statuskeputusan",rs.getString(11)==null?"":rs.getString(11));
				h.put("namasimati",rs.getString("nama_simati")==null?"":rs.getString("nama_simati"));
				listKeputusan.addElement(h);
				bil++;	
			}
			}finally {
				if(db != null) db.close();
			}	
		}
		
	public static Vector getListKeputusan(){
		System.out.println("-------Read Here----");
		return listKeputusan;
	}
	
	private static Vector list = new Vector();
	 public static Vector getData(){
		 System.out.println("-------Read Here----");
		return list;	 
	  }
	 
	public static void setData(String id) throws Exception{
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("-------Read Here----");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("f.id_Fail");
			r.add("f.no_Fail");
			r.add("d.id_Daerah");
			r.add("p.id_Permohonan");
			r.add("p.tarikh_Mohon");
			r.add("s.no_Kp_Baru");
			r.add("s.no_Kp_Lama");
			r.add("s.jenis_Kp");
			r.add("s.n0_Kp_Lain");
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
			r.add("p.jumlah_hta_tarikhmohon");
			r.add("p.jumlah_hta_tarikhmati");
			r.add("p.jumlah_ha_tarikhmohon");
			r.add("p.jumlah_ha_tarikhmati");
			r.add("p.jumlah_harta_tarikhmohon");
			r.add("p.jumlah_harta_tarikhmati");
		    r.add("pk.tarikh_hantar_borangB");
		    r.add("pk.tarikh_terima_borangC");
		    r.add("pk.tarikh_hantar_nilaian");
		    r.add("pk.tarikh_terima_nilaian");
		    r.add("pk.jenis_borangC");
		    r.add("pk.keputusan_permohonan");
		    r.add("pk.catatan");
		    
		    r.add("pk.FLAG_SEBABPINDAHMAHKAMAH"); // razman add
		    
			r.add("f.id_Negeri",r.unquote("n.id_Negeri(+)"));
			r.add("p.id_Daerahmhn",r.unquote("d.id_Daerah(+)"));
			r.add("p.id_Fail",r.unquote("f.id_Fail"));
			r.add("pm.id_Permohonan",r.unquote("p.id_Permohonan"));
			r.add("s.id_Permohonan",r.unquote("p.id_Permohonan"));
			r.add("st.id_Status",r.unquote("p.id_Status"));
			r.add("d.id_daerah",r.unquote("u.id_daerah(+)"));
			r.add("p.id_Permohonan",r.unquote("pk.id_Permohonan(+)"));
			
			r.add("p.id_Permohonan",id);
			System.out.println("-------Read Here----");
			sql = r.getSQLSelect("Tblpfdfail f, Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, Tblppkpemohon pm, Tblrujstatus st, Tblrujpejabatjkptg u, Tblppkkeputusanpermohonan pk");
			ResultSet rs = stmt.executeQuery(sql);
		    Hashtable h;
			
		      while (rs.next()) {
		    	  System.out.println("-------Read Here----");
		    	  h = new Hashtable();
		    	  
		    	  
		    	  h.put("tujuanPindah", rs.getString("FLAG_SEBABPINDAHMAHKAMAH")==null?"":rs.getString("FLAG_SEBABPINDAHMAHKAMAH")); //razman add
		    	  
		    	  h.put("idFail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
		    	  h.put("idDaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
		    	  h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
		    	  h.put("tarikhMohon", sdf.format(rs.getDate("tarikh_Mohon"))==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
		    	  h.put("noKpBaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
		    	  h.put("noKpBaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
		    	  h.put("noKpBaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
		    	  h.put("noKpLama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
		    	  h.put("jenisKp", rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
		    	  h.put("noKpLain", rs.getString("n0_Kp_Lain")==null?"":rs.getString("n0_Kp_Lain"));
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
		    	  h.put("jumHtaTarikhMohon", rs.getDouble("jumlah_hta_tarikhmohon"));
		    	  h.put("jumHtaTarikhMati", rs.getDouble("jumlah_hta_tarikhmati"));
		    	  h.put("jumHaTarikhMohon", rs.getDouble("jumlah_ha_tarikhmohon"));
		    	  h.put("jumHaTarikhMati", rs.getDouble("jumlah_ha_tarikhmati"));
		    	  h.put("jumHartaTarikhMohon", rs.getDouble("jumlah_harta_tarikhmohon"));
		    	  h.put("jumHartaTarikhMati", rs.getDouble("jumlah_harta_tarikhmati"));
		    	  h.put("tarikhborangB", rs.getString("tarikh_hantar_borangB")==null?"":sdf.format(rs.getDate("tarikh_hantar_borangB")));
		    	  h.put("tarikhborangC", rs.getString("tarikh_terima_borangC")==null?"":sdf.format(rs.getDate("tarikh_terima_borangC")));
		    	  h.put("tarikhhantarnilaian",rs.getString("tarikh_hantar_nilaian")==null?"":sdf.format(rs.getDate("tarikh_hantar_nilaian")));
		    	  h.put("tarikhterimanilaian", rs.getString("tarikh_terima_nilaian")==null?"":sdf.format(rs.getDate("tarikh_terima_nilaian")));
		    	  h.put("jenisborangC", rs.getString("jenis_borangC")==null?"":rs.getString("jenis_borangC"));
		    	  h.put("keputusanpermohonan", rs.getString("keputusan_permohonan")==null?"":rs.getString("keputusan_permohonan"));
		    	  h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
		    	  list.addElement(h);
		}      
	}
	finally {
		if(db != null)db.close();
		}
	}

	public static void  setCarianFail(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc,String statusFail)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    System.out.println("-------Read Here----");
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String chkDataFail = noFail.trim();
	      String chkDataPemohon = namaPemohon.trim();
	      String chkDataSimati = namaSimati.trim();
	      String chkDataIcSimati = icSimati.trim();
	      String chkDataJenisKp = JenisIc;
	      sql = "Select f.id_fail,f.no_fail,f.tarikh_daftar_fail,a.id_permohonan,a.tarikh_Mohon,a.tarikh_Masuk," +
	      		"s.keterangan,p.id_simati,p.nama_simati,pp.nama_pemohon,kp.keputusan_Permohonan," +
	      		"(select ss.keterangan from Tblrujstatus ss where ss.id_status = kp.keputusan_Permohonan) as status_keputusan "+
	      		"from Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pp, Tblppkkeputusanpermohonan kp "+
	      		"where a.id_status = s.id_status(+) and a.id_fail = f.id_fail(+) and a.id_permohonan = pp.id_permohonan(+) " +
	      		"and p.id_permohonan = a.id_permohonan AND a.id_Permohonan = kp.id_Permohonan(+) and a.id_status = 14 ";
	      
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
						sql = sql + " AND UPPER(P.N0_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
		    	}
			}  
	      

			
	      sql = sql + " ORDER BY F.ID_FAIL DESC";
	      System.out.println("-------Read Here----");
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      System.out.println("-------Read Here----");
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  	h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("tarikh_Mohon", rs.getString("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_simati")==null?"":rs.getString("nama_simati"));
				h.put("statuskeputusan",rs.getString(12)==null?"":rs.getString(12));
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
				h.put("statuskeputusan", "");
	    	  list.addElement(h);
	      }
	    } finally {
	      if (db != null) db.close();
	    }
	}
	 public static Vector getList(){
		  return list;
	  }
	
	 private static Vector listNegeri = new Vector();
		public static void setListNegeri() throws Exception {
			Db db = null;
			listNegeri.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("-------Read Here----");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("n.id_negeri");
				r.add("n.nama_negeri");
				
				r.add("p.id_negeri",r.unquote("n.id_negeri"));
				
				sql = r.getSQLSelect("tblrujpejabat p, tblrujnegeri n");
				sql = sql + " and p.id_jenispejabat = 8 group by n.id_negeri, n.nama_negeri";
				System.out.println("setListNegeri = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("idNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("namaNegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					listNegeri.addElement(h);
				}
			}finally {
				if(db != null) db.close();
			}
			}
		public static Vector getListNegeri(){
			return listNegeri;
		}
		
		private static Vector listDaerah = new Vector();
		public static void setListDaerah(int idnegeri) throws Exception {
			Db db = null;
			listDaerah.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("d.id_daerah");
				r.add("d.nama_daerah");
				
				r.add("p.id_negeri",r.unquote("n.id_negeri"));
				r.add("p.id_daerah",r.unquote("d.id_daerah"));
				
				sql = r.getSQLSelect("tblrujpejabat p, tblrujnegeri n, tblrujdaerah d");
				sql = sql + " and p.jenis_pejabat = 8 and p.id_negeri = "+ idnegeri +" group by d.id_daerah, d.nama_daerah";
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("idDaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("namaDaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					listDaerah.addElement(h);
				}
			}finally {
				if(db != null) db.close();
			}
			}
		public static Vector getListDaerah(){
			return listDaerah;
		}
}
