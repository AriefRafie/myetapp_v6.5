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
import ekptg.helpers.DB;

/**
 * @author aida
 *
 */
public class FrmPrmhnnSek8KeputusanPermohonanData {
	private static Vector listKeputusan = new Vector();
	
	public static Vector getDataKeputusan(){
		return listKeputusan;
	}
	
	public static void setDataKeputusan(String id) throws Exception {
		Db db = null;
		listKeputusan.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("kp.id_KeputusanPermohonan");
			r.add("kp.tarikh_Hantar_BorangB");
			r.add("kp.tarikh_Terima_BorangC");
			r.add("kp.tarikh_Hantar_Nilaian");
			r.add("kp.tarikh_Terima_Nilaian");
			r.add("kp.catatan");
			r.add("kp.id_Permohonan");
			r.add("kp.keputusan_Permohonan");
			r.add("p.flag_Jenis_BorangC");
			r.add("p.jumlah_Hta_TarikhMohon");
			r.add("p.jumlah_Ha_TarikhMohon");
			r.add("p.jumlah_Harta_Tarikhmohon");
			r.add("p.id_Permohonan");
			
			r.add("kp.id_Permohonan",r.unquote("p.id_Permohonan"));
			
			r.add("p.id_Permohonan",id);
			
			sql = r.getSQLSelect("Tblppkkeputusanpermohonan kp, Tblppkpermohonan p");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("id_KeputusanPermohonan", rs.getString("id_KeputusanPermohonan")==null?"":rs.getString("id_KeputusanPermohonan"));
				h.put("tarikh_Hantar_BorangB", rs.getString("tarikh_Hantar_BorangB")==null?"":sdf.format(rs.getDate("tarikh_Hantar_BorangB")));
				h.put("tarikh_Terima_BorangC", rs.getString("tarikh_Terima_BorangC")==null?"":sdf.format(rs.getDate("tarikh_Terima_BorangC")));
				h.put("tarikh_Hantar_Nilaian", rs.getString("tarikh_Hantar_Nilaian")==null?"":sdf.format(rs.getDate("tarikh_Hantar_Nilaian")));
				h.put("tarikh_Terima_Nilaian", rs.getString("tarikh_Terima_Nilaian")==null?"":sdf.format(rs.getDate("tarikh_Terima_Nilaian")));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("keputusan_Permohonan", rs.getString("keputusan_Permohonan")==null?"":rs.getString("keputusan_Permohonan"));
				h.put("flag_Jenis_BorangC", rs.getString("flag_Jenis_BorangC")==null?"":rs.getString("flag_Jenis_BorangC"));
				h.put("jumlah_Hta_TarikhMohon", rs.getString("jumlah_Hta_TarikhMohon")==null?"":rs.getString("jumlah_Hta_TarikhMohon"));
				h.put("jumlah_Ha_TarikhMohon", rs.getString("jumlah_Ha_TarikhMohon")==null?"":rs.getString("jumlah_Ha_TarikhMohon"));
				h.put("jumlah_Harta_Tarikhmohon", rs.getString("jumlah_Harta_Tarikhmohon")==null?"":rs.getString("jumlah_Harta_Tarikhmohon"));
				listKeputusan.addElement(h);
				
			}
		}finally {
			if(db != null)db.close();			
		}
	}	
		
		private static Vector semakId = new Vector();
		
		public static Vector getSemakId(){
			return semakId;
		}
		
		public static void semakId(String id) throws Exception {
			Db db = null;
			semakId.clear();
			String sql = "Select count(id_permohonan) as cnt from tblppkkeputusanpermohonan where id_permohonan = '"+ id +"'";
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();			
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("cntid", rs.getString("cnt")==null?"":rs.getString("cnt"));
					semakId.addElement(h);
				}
			}finally {
				if(db != null)db.close();			
			}
	}
	
		
private static Vector semakMahkamah = new Vector();
		
		public static Vector getSemakMahkamah(){
			return semakMahkamah;
		}
		
		public static void semakAlamatMahkamah(int idDaerah, int idNegeri) throws Exception {
			Db db = null;
			semakMahkamah.clear();
			String sql = "Select nama_pejabat,alamat1,alamat2,alamat3,poskod,no_tel,no_fax from tblrujpejabat where jenis_pejabat = 8 and id_daerah = "+ idDaerah +" and id_negeri = "+ idNegeri +"";
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();		
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("namapejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
					h.put("alamat1pejabat", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
					h.put("alamat2pejabat", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
					h.put("alamat3pejabat", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
					h.put("poskodpejabat", rs.getString("poskod")==null?"":rs.getString("poskod"));
					h.put("notel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
					h.put("nofax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
					semakMahkamah.addElement(h);
				}
			}finally {
				if(db != null)db.close();			
			}
	}
		
		public static void insertDataMahkamah(Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");    	
		    	String idDaerah=(String)data.get("idDaerah");
		    	String idNegeri=(String)data.get("idNegeri");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	
		    	int id_Daerah = Integer.parseInt(idDaerah);
		    	int id_Negeri = Integer.parseInt(idNegeri);
		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	
		    	
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_keputusanpermohonan", idKeputusanPermohonan);
				r.add("id_negerimahkamah", id_Daerah);
				r.add("id_daerah_mahkamah", id_Negeri);
				r.add("id_permohonan", id_Permohonan);
				sql = r.getSQLInsert("tblppkkeputusanpermohonan");
				stmt.executeUpdate(sql);
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		
		public static void updateDataMahkamah(Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	String idDaerah=(String)data.get("idDaerah");
		    	String idNegeri=(String)data.get("idNegeri");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	
		    	int id_Daerah = Integer.parseInt(idDaerah);
		    	int id_Negeri = Integer.parseInt(idNegeri);
		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	
		    	
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_negerimahkamah", id_Daerah);
				r.add("id_daerah_mahkamah", id_Negeri);
				r.update("id_permohonan", id_Permohonan);
				sql = r.getSQLUpdate("tblppkkeputusanpermohonan");
				stmt.executeUpdate(sql);
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		public static void insertBorang(Hashtable data) throws Exception
		  {
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try
		    {
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");    	
		    	String tarikhHantarBorangB=(String)data.get("tarikhHantarBorangB");
		    	String tarikhTerimaBorangC=(String)data.get("tarikhTerimaBorangC");
		    	String tarikhHantarNilaian=(String)data.get("tarikhHantarNilaian");
		    	String tarikhTerimaNilaian=(String)data.get("tarikhTerimaNilaian");
		    	String keputusanBorangC=(String)data.get("keputusanBorangC");
		    	String penentuanBidangKuasa=(String)data.get("penentuanBidangKuasa");
		    	String penentuanBidangKuasaTeruskan=(String)data.get("penentuanBidangKuasaTeruskan");
		    	String catatan=(String)data.get("catatan");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	
		    	String ntarikhHantarBorangB = "to_date('" + tarikhHantarBorangB + "','dd/MM/yyyy')";
		    	String ntarikhTerimaBorangC = "to_date('" + tarikhTerimaBorangC + "','dd/MM/yyyy')";
		    	String ntarikhHantarNilaian = "to_date('" + tarikhHantarNilaian + "','dd/MM/yyyy')";
		    	String ntarikhTerimaNilaian = "to_date('" + tarikhTerimaNilaian + "','dd/MM/yyyy')";
		    	
		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_keputusanpermohonan", idKeputusanPermohonan);
				r.add("id_permohonan", id_Permohonan);
				r.add("tarikh_hantar_borangB", r.unquote(ntarikhHantarBorangB));
				r.add("tarikh_terima_borangC", r.unquote(ntarikhTerimaBorangC));
				r.add("tarikh_hantar_nilaian", r.unquote(ntarikhHantarNilaian));
				r.add("tarikh_terima_nilaian", r.unquote(ntarikhTerimaNilaian));
				r.add("jenis_borangC", keputusanBorangC);
				if (penentuanBidangKuasa == "1"){
		    		penentuanBidangKuasa = penentuanBidangKuasaTeruskan;
		    		r.add("keputusan_permohonan", penentuanBidangKuasa);
		    	}else{
		    		if (penentuanBidangKuasa != "53" || penentuanBidangKuasa != "23" || penentuanBidangKuasa != "63"){
		    			r.add("keputusan_permohonan", penentuanBidangKuasa);
		    		}else{
		    			r.add("keputusan_permohonan", "50");
		    		}
		    	}
				r.add("catatan", catatan);
				sql = r.getSQLInsert("tblppkkeputusanpermohonan");
				System.out.println("masuk ker--->"+sql);
				stmt.executeUpdate(sql);
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		public static void updateBorang(Hashtable data) throws Exception
		  {
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try
		    {
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");    	
		    	String tarikhHantarBorangB=(String)data.get("tarikhHantarBorangB");
		    	String tarikhTerimaBorangC=(String)data.get("tarikhTerimaBorangC");
		    	String tarikhHantarNilaian=(String)data.get("tarikhHantarNilaian");
		    	String tarikhTerimaNilaian=(String)data.get("tarikhTerimaNilaian");
		    	String keputusanBorangC=(String)data.get("keputusanBorangC");
		    	String penentuanBidangKuasa=(String)data.get("penentuanBidangKuasa");
		    	String penentuanBidangKuasaTeruskan=(String)data.get("penentuanBidangKuasaTeruskan");
		    	String catatan=(String)data.get("catatan");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	
		    	String ntarikhHantarBorangB = "to_date('" + tarikhHantarBorangB + "','dd/MM/yyyy')";
		    	String ntarikhTerimaBorangC = "to_date('" + tarikhTerimaBorangC + "','dd/MM/yyyy')";
		    	String ntarikhHantarNilaian = "to_date('" + tarikhHantarNilaian + "','dd/MM/yyyy')";
		    	String ntarikhTerimaNilaian = "to_date('" + tarikhTerimaNilaian + "','dd/MM/yyyy')";
		    	
		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("id_permohonan", id_Permohonan);
				r.add("tarikh_hantar_borangB", r.unquote(ntarikhHantarBorangB));
				r.add("tarikh_terima_borangC", r.unquote(ntarikhTerimaBorangC));
				r.add("tarikh_hantar_nilaian", r.unquote(ntarikhHantarNilaian));
				r.add("tarikh_terima_nilaian", r.unquote(ntarikhTerimaNilaian));
				r.add("jenis_borangC", keputusanBorangC);
				if (penentuanBidangKuasa == "1"){
		    		penentuanBidangKuasa = penentuanBidangKuasaTeruskan;
		    		r.add("keputusan_permohonan", penentuanBidangKuasa);
		    	}else{
		    		r.add("keputusan_permohonan", penentuanBidangKuasa);
		    	}
				r.add("catatan", catatan);
				sql = r.getSQLUpdate("tblppkkeputusanpermohonan");
				System.out.println("sql update-->"+sql);
				stmt.executeUpdate(sql);
		    	} finally {
		    		if (db != null) db.close();  
		    	}
		  	}
	
}
