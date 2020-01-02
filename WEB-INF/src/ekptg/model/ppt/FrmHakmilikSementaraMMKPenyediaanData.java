package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraMMKPenyediaanData {
	
	Vector butiran = null;
	Vector semakPrmhnn = null;
	Vector butiranLot = null;
	Vector sewaLot = null;
	Vector noLot = null;
	Vector paparMMK = null;
	Vector status = null;
	Vector id_Permohonan = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public void setButiranAsas(String id_fail, String id_permohonan) throws Exception{
		
		Db db = null;
		String sql1 = "";
		
		try{
			butiran = new Vector();
			db = new Db();
			
			
			sql1 = "SELECT DISTINCT C.NAMA_KEMENTERIAN, D.NAMA_AGENSI,A.TUJUAN,F.NAMA_NEGERI,G.NAMA_DAERAH,H.NAMA_MUKIM"+

				  " FROM TBLPPTPERMOHONAN A,"+ 
				  " TBLPFDFAIL B,"+
				  " TBLRUJKEMENTERIAN C,"+
				  " TBLRUJAGENSI D,"+
				  " TBLPPTHAKMILIK E," +
				  " TBLRUJNEGERI F," +
				  " TBLRUJDAERAH G," +
				  " TBLRUJMUKIM H" +
     
				  " WHERE B.ID_FAIL = A.ID_FAIL"+
				  " AND C.ID_KEMENTERIAN = B.ID_KEMENTERIAN"+
				  " AND D.ID_AGENSI = A.ID_AGENSI"+
				  " AND A.ID_PERMOHONAN = E.ID_PERMOHONAN"+
				  " AND F.ID_NEGERI = E.ID_NEGERI"+
				  " AND G.ID_DAERAH = E.ID_DAERAH"+
				  " AND H.ID_MUKIM = E.ID_MUKIM"+
				  " AND A.ID_PERMOHONAN = '"+id_permohonan+"'"+
				  " AND A.ID_FAIL = '"+id_fail+"'";
			
			Statement stmt1 = db.getStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
		
			
			Hashtable h1;
			Hashtable h2;
			String noLot = "";
			int luasSewa = 0;
			while(rs1.next()) {
				h1 = new Hashtable();
				h1.put("pemohon",rs1.getString("NAMA_AGENSI")!= null?rs1.getString("NAMA_AGENSI"):rs1.getString("NAMA_KEMENTERIAN"));
				h1.put("tujuan", rs1.getString("TUJUAN")== null?"":rs1.getString("TUJUAN"));
				h1.put("lokasi",rs1.getString("NAMA_NEGERI")== null?"":rs1.getString("NAMA_MUKIM")+", "+rs1.getString("NAMA_DAERAH")+", "+rs1.getString("NAMA_NEGERI"));
				butiran.addElement(h1);
			}
			
			
		} finally {
	      if (db != null) db.close();
	    }//close finally
	   
		
		
	}
	
	public void setButiranLot(String id_fail, String id_permohonan) throws Exception{
		
		Db db = null;
		
		String sql2 = "";
	
		try{
			butiranLot = new Vector();
			db = new Db();
			
			
			
			
			sql2 = "SELECT COUNT(NO_LOT) AS BIL_LOT FROM TBLPPTHAKMILIK WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
			
			Statement stmt2 = db.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);
			
			Hashtable h2;
			
			while(rs2.next()){
				h2 = new Hashtable();
				h2.put("bilLot",rs2.getString("BIL_LOT")== null?"":rs2.getString("BIL_LOT"));
				butiranLot.addElement(h2);
			}
			
		} finally {
	      if (db != null) db.close();
	    }//close finally
	   
		
		
	}
	public void setSewaLot(String id_fail, String id_permohonan) throws Exception{
		
		Db db = null;
		String sql3 = "";
		try{
			sewaLot = new Vector();
			db = new Db();
			
			
			
			
			sql3 = "SELECT SUM(LUAS_SEWA) AS SUM_SEWA FROM TBLPPTBORANGQ WHERE ID_PERMOHONAN ='"+id_permohonan+"'";
			
			Statement stmt3 = db.getStatement();
			ResultSet rs3 = stmt3.executeQuery(sql3);
			

			Hashtable h1;
			String noLot = "";
			while(rs3.next()) {
				h1 = new Hashtable();
				h1.put("sewaLot", rs3.getString("SUM_SEWA"));
				sewaLot.addElement(h1);
			}
			
			
		} finally {
	      if (db != null) db.close();
	    }//close finally
	   
		
		
	}
	public void setNoLot(String id_fail, String id_permohonan) throws Exception{
		
		Db db = null;
		String sql3 = "";
		
		try{
			noLot = new Vector();
			db = new Db();
			
		
			
			sql3 = "SELECT A.NO_LOT,B.KETERANGAN FROM TBLPPTHAKMILIK A, TBLRUJLOT B WHERE B.ID_LOT = A.ID_LOT AND A.ID_PERMOHONAN = '"+id_permohonan+"'";
			
			Statement stmt3 = db.getStatement();
			ResultSet rs3 = stmt3.executeQuery(sql3);
			
			
			Hashtable h1;
			int count = 0;
			while(rs3.next()) {
				h1 = new Hashtable();
				h1.put("noLot",rs3.getString("KETERANGAN")+" " +rs3.getString("NO_LOT"));
				noLot.addElement(h1);
				count++;
			}
			
		} finally {
	      if (db != null) db.close();
	    }//close finally
	   
		
		
	}
	public void setSemakPermohonan(String id_fail, String id_permohonan) throws Exception{
		
		Db db = null;
		String sql =""; 

		try{
			semakPrmhnn = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			boolean empty = rs.wasNull();
			
			if (empty == true){
				h = new Hashtable();
				System.out.println("sqlSemak--"+sql);
				if(rs.getString("ID_HAKMILIK")== null || rs.getString("ID_HAKMILIK")== " "){
					h.put("error","1");
				}
				semakPrmhnn.addElement(h);
				
			}

			
		}finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}
	
	public static String add(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 String output = "";
		    try
		    {
		    	long idMMK = DB.getNextID("TBLPPTMMK_SEQ");
		    	String idPermohonan = (String)data.get("idPermohonan");
		    	String tujuan = (String)data.get("tujuan");
		    	String latarBelakang = (String)data.get("latarBelakang");
		    	String asasPertimbangan = (String)data.get("asasPertimbangan");
		    	String kesimpulan = (String)data.get("kesimpulan");
		    	String syor = (String)data.get("syor");
		    	String kedudukanLaporanTanah = (String)data.get("kedudukanLaporanTanah");
		    	String pengesahanPeruntukan = (String)data.get("pengesahanPeruntukan");
		    	String pandangan = (String)data.get("pandangan");
		    	String implikasi = (String)data.get("implikasi");
		    	String perihalTanah = (String)data.get("perihalTanah");
		    	String perihalPohon = (String)data.get("perihalPohon");
		    	String anggaranKos = (String)data.get("anggaranKos");
		    	String perakuanPentadbir = (String)data.get("perakuanPentadbir");
		    	String nilaianTanah = (String)data.get("nilaianTanah");
		    	String pengecualianUpahUkur = (String)data.get("pengecualianUpahUkur");
		    	String halLain = (String)data.get("halLain");
		    	String jawatankuasaTanah = (String)data.get("jawatankuasaTanah");
		    	String namaTuanTanah = (String)data.get("namaTuanTanah");
		    	String tajuk = (String)data.get("tajuk");
		    	String ulasan = (String)data.get("ulasan");
		    	String ulasanJT = (String)data.get("ulasanJT");
		    	String ulasanPengarah = (String)data.get("ulasanPengarah");
		    	String keputusan = (String)data.get("keputusan");
		    	String butirAsas = (String)data.get("butirAsas");
		    	String keadaanTanah = (String)data.get("keadaanTanah");
		    	String butirTanah = (String)data.get("butirTanah");
		    	String kemudahanAsas = (String)data.get("kemudahanAsas");
		    	String perakuanSetiausaha = (String)data.get("perakuanSetiausaha");
		    	String penutup = (String)data.get("penutup");
		    	String idMasuk = (String)data.get("id_Masuk");
		    	
		    	db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.add("id_Mmk",idMMK);
			    r.add("id_Permohonan",idPermohonan);
			    r.add("tujuan",tujuan);
			    r.add("latarbelakang",latarBelakang);
			    r.add("asas_Pertimbangan",asasPertimbangan);
			    r.add("kesimpulan",kesimpulan);
			    r.add("syor",syor);
			    r.add("kedudukan_Laporan_Tnh",kedudukanLaporanTanah);
			    r.add("pengesahan_Peruntukan",pengesahanPeruntukan);
			    r.add("pandangan",pandangan);
			    r.add("implikasi",implikasi);
			    r.add("perihal_Tanah",perihalTanah);
			    r.add("perihal_Pohon",perihalPohon);
			    r.add("anggaran_Kos",anggaranKos);
			    r.add("perakuan_Pentadbir_Tnh",perakuanPentadbir);
			    r.add("nilai_Atas_Tanah",nilaianTanah);
			    r.add("pengecualian_Upah_Ukur",pengecualianUpahUkur);
			    r.add("hal_Lain",halLain);
			    r.add("jawatankuasa_Tanah",jawatankuasaTanah);
			    r.add("nama_Tuan_Tanah",namaTuanTanah);
			    r.add("tajuk",tajuk);
			    r.add("ulasan",ulasan);
			    r.add("ulasan_Jabteknikal",ulasanJT);
			    r.add("ulasan_Pengarah",ulasanPengarah);
			    r.add("keputusan",keputusan);
			    r.add("butir_Asas",butirAsas);
			    r.add("keadaan_Tanah",keadaanTanah);
			    r.add("butir_Tanah",butirTanah);
			    r.add("kemudahan_Asas",kemudahanAsas);
			    r.add("perakuan_Setiausaha",perakuanSetiausaha);
			    r.add("penutup",penutup);
			    r.add("id_Masuk",idMasuk);
			    r.add("tarikh_Masuk",r.unquote("sysdate"));
			    sql = r.getSQLInsert("tblpptmmk");
			    stmt.executeUpdate(sql);
			    
			    output = ""+idMMK;
		    	
		    }finally {
			      if (db != null) db.close();
		    }
		    
		    return output;
	}
	public static void update(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 
		    try
		    {
		    	String idMMK = (String)data.get("idMMK");
		    	String tujuan = (String)data.get("tujuan");
		    	String latarBelakang = (String)data.get("latarBelakang");
		    	String asasPertimbangan = (String)data.get("asasPertimbangan");
		    	String kesimpulan = (String)data.get("kesimpulan");
		    	String syor = (String)data.get("syor");
		    	String kedudukanLaporanTanah = (String)data.get("kedudukanLaporanTanah");
		    	String pengesahanPeruntukan = (String)data.get("pengesahanPeruntukan");
		    	String pandangan = (String)data.get("pandangan");
		    	String implikasi = (String)data.get("implikasi");
		    	String perihalTanah = (String)data.get("perihalTanah");
		    	String perihalPohon = (String)data.get("perihalPohon");
		    	String anggaranKos = (String)data.get("anggaranKos");
		    	String perakuanPentadbir = (String)data.get("perakuanPentadbir");
		    	String nilaianTanah = (String)data.get("nilaianTanah");
		    	String pengecualianUpahUkur = (String)data.get("pengecualianUpahUkur");
		    	String halLain = (String)data.get("halLain");
		    	String jawatankuasaTanah = (String)data.get("jawatankuasaTanah");
		    	String namaTuanTanah = (String)data.get("namaTuanTanah");
		    	String tajuk = (String)data.get("tajuk");
		    	String ulasan = (String)data.get("ulasan");
		    	String ulasanJT = (String)data.get("ulasanJT");
		    	String ulasanPengarah = (String)data.get("ulasanPengarah");
		    	String keputusan = (String)data.get("keputusan");
		    	String butirAsas = (String)data.get("butirAsas");
		    	String keadaanTanah = (String)data.get("keadaanTanah");
		    	String butirTanah = (String)data.get("butirTanah");
		    	String kemudahanAsas = (String)data.get("kemudahanAsas");
		    	String perakuanSetiausaha = (String)data.get("perakuanSetiausaha");
		    	String penutup = (String)data.get("penutup");
		    	String idKemaskini = (String)data.get("id_Kemaskini");
		    	
		    	db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_Mmk",idMMK);
			    r.add("tujuan",tujuan);
			    r.add("latarbelakang",latarBelakang);
			    r.add("asas_Pertimbangan",asasPertimbangan);
			    r.add("kesimpulan",kesimpulan);
			    r.add("syor",syor);
			    r.add("kedudukan_Laporan_Tanah",kedudukanLaporanTanah);
			    r.add("pengesahan_Peruntukan",pengesahanPeruntukan);
			    r.add("pandangan",pandangan);
			    r.add("implikasi",implikasi);
			    r.add("perihal_Tanah",perihalTanah);
			    r.add("perihal_Pohon",perihalPohon);
			    r.add("anggaran_Kos",anggaranKos);
			    r.add("perakuan_Pentadbir_Tnh",perakuanPentadbir);
			    r.add("nilai_Atas_Tanah",nilaianTanah);
			    r.add("pengecualian_Upah_Ukur",pengecualianUpahUkur);
			    r.add("hal_Lain",halLain);
			    r.add("jawatankuasa_Tanah",jawatankuasaTanah);
			    r.add("nama_Tuan_Tanah",namaTuanTanah);
			    r.add("tajuk",tajuk);
			    r.add("ulasan",ulasan);
			    r.add("ulasan_Jabteknikal",ulasanJT);
			    r.add("ulasan_Pengarah",ulasanPengarah);
			    r.add("keputusan",keputusan);
			    r.add("butir_Asas",butirAsas);
			    r.add("keadaan_Tanah",keadaanTanah);
			    r.add("butir_Tanah",butirTanah);
			    r.add("kemudahan_Asas",kemudahanAsas);
			    r.add("perakuan_Setiausaha",perakuanSetiausaha);
			    r.add("penutup",penutup);
			    r.add("id_Kemaskini",idKemaskini);
			    r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			    sql = r.getSQLUpdate("tblpptmmk");
			    stmt.executeUpdate(sql);
	
		    	
		    }finally {
			      if (db != null) db.close();
		    }
	}
	public static void addSemak(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 String output = "";
		    try
		    {
		    	String idMMK = (String)data.get("idMMK");
		    	String user_Id = (String)data.get("user_Id");
		    	String tarikh_Semak = (String)data.get("tarikh_Semak");
		    	String tkhSemak = "to_date('" + tarikh_Semak + "','dd/MM/yyyy')";
		    	String status_Semakan = (String)data.get("status_Semakan");
		    	String ulasan = (String)data.get("ulasan");
		    	String id_Kemaskini = (String)data.get("id_Kemaskini");
		    	
		    	db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_Mmk",idMMK);
			    r.add("user_Id",user_Id);
			    r.add("tarikh_Semak",r.unquote(tkhSemak));
			    r.add("status_Semakan",status_Semakan);
			    r.add("ulasan",ulasan);
			    r.add("id_Kemaskini",id_Kemaskini);
			    r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			    sql = r.getSQLUpdate("tblpptmmk");
			    stmt.executeUpdate(sql);
			    
		    	
		    }finally {
			      if (db != null) db.close();
		    }
		    
	}
	public static void updateSemak(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 
		    try
		    {
		    	String idMMK = (String)data.get("idMMK");
		    	String user_Id = (String)data.get("user_Id");
		    	String tarikh_Semak = (String)data.get("tarikh_Semak");
		    	String tkhSemak = "to_date('" + tarikh_Semak + "','dd/MM/yyyy')";
		    	String status_Semakan = (String)data.get("status_Semakan");
		    	String ulasan = (String)data.get("ulasan");
		    	String id_Kemaskini = (String)data.get("id_Kemaskini");
		    	
		        db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_Mmk",idMMK);
			    r.add("user_Id",user_Id);
			    r.add("tarikh_Semak",r.unquote(tkhSemak));
			    r.add("status_Semakan",status_Semakan);
			    r.add("ulasan",ulasan);
			    r.add("id_Kemaskini",id_Kemaskini);
			    r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			    sql = r.getSQLUpdate("tblpptmmk");
			    stmt.executeUpdate(sql);
	
		    	
		    }finally {
			      if (db != null) db.close();
		    }
	}
	public static String addKeputusan(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql1 = "";
		 String sql2 = "";
		 String output = "";
		    try
		    {
		    	long id_MMKKptsn = DB.getNextID("TBLPPTMMKKEPUTUSAN_SEQ");
		    	String idMMK = (String)data.get("idMMK");
		    	String no_rujukan = (String)data.get("no_rujukan");
		    	String tarikh_hantar = (String)data.get("tarikh_hantar");
		    	String tkhHantar = "to_date('" + tarikh_hantar + "','dd/MM/yyyy')";
		    	String tarikh_terima = (String)data.get("tarikh_terima");
		    	String tkhTerima = "to_date('" + tarikh_terima + "','dd/MM/yyyy')";
		    	String tarikh_keputusan = (String)data.get("tarikh_keputusan");
		    	String tkhKpstn = "to_date('" + tarikh_keputusan + "','dd/MM/yyyy')";
		    	String tarikh_Mmk = (String)data.get("tarikh_Mmk");
		    	String tkhMMK = "to_date('" + tarikh_Mmk + "','dd/MM/yyyy')";
		    	String status_keputusan = (String)data.get("status_keputusan");
		    	String ulasan = (String)data.get("ulasan");
		    	String id_Masuk = (String)data.get("id_Masuk");
		    	
		    	db = new Db();
			    Statement stmt1 = db.getStatement();
			    SQLRenderer r1 = new SQLRenderer();
			    r1.add("id_Mmkkeputusan",id_MMKKptsn);
			    r1.add("id_Mmk",idMMK);
			    r1.add("tarikh_Hantar",r1.unquote(tkhHantar));
			    r1.add("tarikh_Terima",r1.unquote(tkhTerima));
			    r1.add("tarikh_Keputusan",r1.unquote(tkhKpstn));
			    r1.add("status_Keputusan",status_keputusan);
			    r1.add("ulasan",ulasan);
			    r1.add("id_Masuk",id_Masuk);
			    r1.add("tarikh_Masuk",r1.unquote("sysdate"));
			    sql1 = r1.getSQLInsert("tblpptmmkkeputusan");
			    stmt1.executeUpdate(sql1);
			    
			    Statement stmt2 = db.getStatement();
			    SQLRenderer r2 = new SQLRenderer();
			    r2.update("id_Mmk",idMMK);
			    r2.add("no_Rujmmk",no_rujukan);
			    r2.add("tarikh_Mmk",r2.unquote(tkhMMK));
			    r2.add("id_Kemaskini",id_Masuk);
			    r2.add("tarikh_Kemaskini",r2.unquote("sysdate"));
			    sql2 = r2.getSQLUpdate("tblpptmmk");
			    stmt2.executeUpdate(sql2);
			    
			    
			    output = ""+ id_MMKKptsn;
		    	
		    }finally {
			      if (db != null) db.close();
		    }
		    return output;
		    
	}
	public static void updateKeputusan(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 String sql2 = "";
		 
		    try
		    {
		    	String id_MMKKptsn = (String)data.get("idMMKKptsn");
		    	String idMMK = (String)data.get("idMMK");
		    	String no_rujukan = (String)data.get("no_rujukan");
		    	String tarikh_hantar = (String)data.get("tarikh_hantar");
		    	String tkhHantar = "to_date('" + tarikh_hantar + "','dd/MM/yyyy')";
		    	String tarikh_terima = (String)data.get("tarikh_terima");
		    	String tkhTerima = "to_date('" + tarikh_terima + "','dd/MM/yyyy')";
		    	String tarikh_keputusan = (String)data.get("tarikh_keputusan");
		    	String tkhKpstn = "to_date('" + tarikh_keputusan + "','dd/MM/yyyy')";
		    	String tarikh_Mmk = (String)data.get("tarikh_Mmk");
		    	String tkhMMK = "to_date('" + tarikh_Mmk + "','dd/MM/yyyy')";
		    	String status_keputusan = (String)data.get("status_keputusan");
		    	String ulasan = (String)data.get("ulasan");
		    	String id_Kemaskini = (String)data.get("id_Kemaskini");
		    	
		    	db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_Mmkkeputusan",id_MMKKptsn);
			    r.add("tarikh_Hantar",r.unquote(tkhHantar));
			    r.add("tarikh_Terima",r.unquote(tkhTerima));
			    r.add("tarikh_Keputusan",r.unquote(tkhKpstn));
			    r.add("status_Keputusan",status_keputusan);
			    r.add("ulasan",ulasan);
			    r.add("id_Kemaskini",id_Kemaskini);
			    r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			    sql = r.getSQLUpdate("tblpptmmkkeputusan");
			    stmt.executeUpdate(sql);
			    
			    Statement stmt2 = db.getStatement();
			    SQLRenderer r2 = new SQLRenderer();
			    r2.update("id_Mmk",idMMK);
			    r2.add("no_Rujmmk",no_rujukan);
			    r2.add("tarikh_Mmk",r2.unquote(tkhMMK));
			    r2.add("id_Kemaskini",id_Kemaskini);
			    r2.add("tarikh_Kemaskini",r2.unquote("sysdate"));
			    sql2 = r2.getSQLUpdate("tblpptmmk");
			    stmt2.executeUpdate(sql2);
		    	
		    }finally {
			      if (db != null) db.close();
		    }
	}
	public void setPaparMMK(String idPermohonan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparMMK = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_MMK,A.TUJUAN,A.LATARBELAKANG,A.PERIHAL_TANAH,A.NILAI_ATAS_TANAH," +
				  " A.SYOR,A.ULASAN_PENGARAH,A.USER_ID,A.TARIKH_SEMAK,A.STATUS_SEMAKAN,A.ULASAN,A.KEPUTUSAN, " +
				  " A.ASAS_PERTIMBANGAN,A.KESIMPULAN,A.KEDUDUKAN_LAPORAN_TNH,A.PENGESAHAN_PERUNTUKAN,A.PANDANGAN,A.IMPLIKASI, " +
				  " A.PERIHAL_POHON,A.ANGGARAN_KOS,A.PERAKUAN_PENTADBIR_TNH,A.PENGECUALIAN_UPAH_UKUR,A.HAL_LAIN,A.JAWATANKUASA_TANAH, " +
				  " A.NAMA_TUAN_TANAH,A.TAJUK,A.ULASAN_JABTEKNIKAL,A.BUTIR_ASAS,A.KEADAAN_TANAH,A.BUTIR_TANAH,A.KEMUDAHAN_ASAS,A.PERAKUAN_SETIAUSAHA,A.PENUTUP, " +
				  " A.NO_RUJMMK,B.ID_MMKKEPUTUSAN, B.TARIKH_TERIMA, B.TARIKH_KEPUTUSAN, B.TARIKH_HANTAR, B.STATUS_KEPUTUSAN," +
				  " B.ULASAN AS ULASAN_KEPUTUSAN, A.TARIKH_MMK,C.USER_NAME" +
				  " FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN B, USERS C WHERE C.USER_ID(+) = A.USER_ID AND A.ID_MMK = B.ID_MMK(+) AND A.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			 ResultSet rs = stmt.executeQuery(sql); 
		     Hashtable h;
		     
		     while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("ID_MMK",rs.getString("ID_MMK")==null?"":rs.getString("ID_MMK"));
		    	  h.put("TUJUAN",rs.getString("TUJUAN")==null?"":rs.getString("TUJUAN"));
		    	  h.put("LATARBELAKANG",rs.getString("LATARBELAKANG")==null?"":rs.getString("LATARBELAKANG"));
		    	  h.put("PERIHAL_TANAH",rs.getString("PERIHAL_TANAH")==null?"":rs.getString("PERIHAL_TANAH"));
		    	  h.put("NILAI_ATAS_TANAH",rs.getString("NILAI_ATAS_TANAH")==null?"":rs.getString("NILAI_ATAS_TANAH"));
		    	  h.put("SYOR",rs.getString("SYOR")==null?"":rs.getString("SYOR"));
		    	  h.put("ULASAN_PENGARAH",rs.getString("ULASAN_PENGARAH")==null?"":rs.getString("ULASAN_PENGARAH"));
		    	  h.put("KEPUTUSAN",rs.getString("KEPUTUSAN")==null?"":rs.getString("KEPUTUSAN").toUpperCase());
		    	  h.put("ASAS_PERTIMBANGAN",rs.getString("ASAS_PERTIMBANGAN")==null?"":rs.getString("ASAS_PERTIMBANGAN").toUpperCase());
		    	  h.put("KESIMPULAN",rs.getString("KESIMPULAN")==null?"":rs.getString("KESIMPULAN").toUpperCase());
		    	  h.put("KEDUDUKAN_LAPORAN_TNH",rs.getString("KEDUDUKAN_LAPORAN_TNH")==null?"":rs.getString("KEDUDUKAN_LAPORAN_TNH").toUpperCase());
		    	  h.put("PENGESAHAN_PERUNTUKAN",rs.getString("PENGESAHAN_PERUNTUKAN")==null?"":rs.getString("PENGESAHAN_PERUNTUKAN").toUpperCase());
		    	  h.put("PANDANGAN",rs.getString("PANDANGAN")==null?"":rs.getString("PANDANGAN").toUpperCase());
		    	  h.put("IMPLIKASI",rs.getString("IMPLIKASI")==null?"":rs.getString("IMPLIKASI").toUpperCase());
		    	  h.put("PERIHAL_POHON",rs.getString("PERIHAL_POHON")==null?"":rs.getString("PERIHAL_POHON").toUpperCase());
		    	  h.put("ANGGARAN_KOS",rs.getString("ANGGARAN_KOS")==null?"":rs.getString("ANGGARAN_KOS").toUpperCase());
		    	  h.put("PERAKUAN_PENTADBIR_TNH",rs.getString("PERAKUAN_PENTADBIR_TNH")==null?"":rs.getString("PERAKUAN_PENTADBIR_TNH").toUpperCase());
		    	  h.put("PENGECUALIAN_UPAH_UKUR",rs.getString("PENGECUALIAN_UPAH_UKUR")==null?"":rs.getString("PENGECUALIAN_UPAH_UKUR").toUpperCase());
		    	  h.put("HAL_LAIN",rs.getString("HAL_LAIN")==null?"":rs.getString("HAL_LAIN").toUpperCase());
		    	  h.put("JAWATANKUASA_TANAH",rs.getString("JAWATANKUASA_TANAH")==null?"":rs.getString("JAWATANKUASA_TANAH").toUpperCase());
		    	  h.put("NAMA_TUAN_TANAH",rs.getString("NAMA_TUAN_TANAH")==null?"":rs.getString("NAMA_TUAN_TANAH").toUpperCase());
		    	  h.put("TAJUK",rs.getString("TAJUK")==null?"":rs.getString("TAJUK").toUpperCase());
		    	  h.put("ULASAN_JABTEKNIKAL",rs.getString("ULASAN_JABTEKNIKAL")==null?"":rs.getString("ULASAN_JABTEKNIKAL").toUpperCase());
		    	  h.put("BUTIR_ASAS",rs.getString("BUTIR_ASAS")==null?"":rs.getString("BUTIR_ASAS").toUpperCase());
		    	  h.put("KEADAAN_TANAH",rs.getString("KEADAAN_TANAH")==null?"":rs.getString("KEADAAN_TANAH").toUpperCase());
		    	  h.put("BUTIR_TANAH",rs.getString("BUTIR_TANAH")==null?"":rs.getString("BUTIR_TANAH").toUpperCase());
		    	  h.put("KEMUDAHAN_ASAS",rs.getString("KEMUDAHAN_ASAS")==null?"":rs.getString("KEMUDAHAN_ASAS").toUpperCase());
		    	  h.put("PERAKUAN_SETIAUSAHA",rs.getString("PERAKUAN_SETIAUSAHA")==null?"":rs.getString("PERAKUAN_SETIAUSAHA").toUpperCase());
		    	  h.put("PENUTUP",rs.getString("PENUTUP")==null?"":rs.getString("PENUTUP").toUpperCase());
		    	  h.put("USER_ID",rs.getString("USER_ID")==null?"":rs.getString("USER_ID"));
		    	  h.put("TARIKH_SEMAK",rs.getString("TARIKH_SEMAK")==null?"":sdf.format(rs.getDate("TARIKH_SEMAK")));
		    	  h.put("STATUS_SEMAKAN",rs.getString("STATUS_SEMAKAN")==null?"":rs.getString("STATUS_SEMAKAN"));
		    	  h.put("ULASAN",rs.getString("ULASAN")==null?"":rs.getString("ULASAN").toUpperCase());
		    	  h.put("NO_RUJMMK",rs.getString("NO_RUJMMK")==null?"":rs.getString("NO_RUJMMK"));
		    	  h.put("ID_MMKKEPUTUSAN",rs.getString("ID_MMKKEPUTUSAN")==null?"":rs.getString("ID_MMKKEPUTUSAN"));
		    	  h.put("TARIKH_TERIMA",rs.getString("TARIKH_TERIMA")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA")));
		    	  h.put("TARIKH_KEPUTUSAN",rs.getString("TARIKH_KEPUTUSAN")==null?"":sdf.format(rs.getDate("TARIKH_KEPUTUSAN")));
		    	  h.put("TARIKH_HANTAR",rs.getString("TARIKH_HANTAR")==null?"":sdf.format(rs.getDate("TARIKH_HANTAR")));
		    	  h.put("TARIKH_MMK",rs.getString("TARIKH_MMK")==null?"":sdf.format(rs.getDate("TARIKH_MMK")));
		    	  h.put("STATUS_KEPUTUSAN",rs.getString("STATUS_KEPUTUSAN")==null?"":rs.getString("STATUS_KEPUTUSAN"));
		    	  h.put("ULASAN_KEPUTUSAN",rs.getString("ULASAN_KEPUTUSAN")==null?"":rs.getString("ULASAN_KEPUTUSAN").toUpperCase());
		    	  h.put("USER_NAME",rs.getString("USER_NAME")==null?"":rs.getString("USER_NAME"));

		    	  paparMMK.addElement(h);
		     }
			
			
			
		}finally {
		      if (db != null) db.close();
	    }
		
		
		
	}
	public static void editStatusPenyediaan(Hashtable data) throws Exception {
		Db db = null;
		String sql3 = "";
		
			try
			{						
				String id_permohonan = (String)data.get("id_permohonan");
				String id_status = "132" ;			        				
				  
				//**UNTUK UPDATE ID_STATUS = 11 [Permohonan Cawangan]
				  db = new Db();					  
				  Statement stmt3 = db.getStatement();
				  SQLRenderer r3 = new SQLRenderer();
				
				  r3.update("id_permohonan", id_permohonan);
				  r3.add("id_status", id_status);
				  
				  sql3 = r3.getSQLUpdate("tblpptpermohonan");
				  stmt3.executeUpdate(sql3);
								
			}finally {
				if(db != null) db.close();
			}			
	}
	
	public static void editStatusSemak(Hashtable data) throws Exception {
		Db db = null;
		String sql3 = "";
		
			try
			{						
				String id_permohonan = (String)data.get("id_permohonan");
				String id_status = "133" ;			        				
				  
				//**UNTUK UPDATE ID_STATUS = 11 [Permohonan Cawangan]
				  db = new Db();					  
				  Statement stmt3 = db.getStatement();
				  SQLRenderer r3 = new SQLRenderer();
				
				  r3.update("id_permohonan", id_permohonan);
				  r3.add("id_status", id_status);
				  
				  sql3 = r3.getSQLUpdate("tblpptpermohonan");
				  stmt3.executeUpdate(sql3);
								
			}finally {
				if(db != null) db.close();
			}			
	}
	public static void editStatusKeputusan(Hashtable data) throws Exception {
		Db db = null;
		String sql3 = "";
		
			try
			{						
				String id_permohonan = (String)data.get("id_permohonan");
				String id_status = "134" ;			        				
				  
				//**UNTUK UPDATE ID_STATUS = 11 [Permohonan Cawangan]
				  db = new Db();					  
				  Statement stmt3 = db.getStatement();
				  SQLRenderer r3 = new SQLRenderer();
				
				  r3.update("id_permohonan", id_permohonan);
				  r3.add("id_status", id_status);
				  
				  sql3 = r3.getSQLUpdate("tblpptpermohonan");
				  stmt3.executeUpdate(sql3);
								
			}finally {
				if(db != null) db.close();
			}			
	}
	public void check_status(String idPermohonan) throws Exception {
		 
		 Db db = null;
		 String sql = "";
		 
		 try{
			 
			 status = new Vector();
			 db = new Db();
			 Statement stmt = db.getStatement();
			 
			 sql = "SELECT A.ID_STATUS FROM TBLPPTPERMOHONAN A WHERE A.ID_PERMOHONAN ='" +idPermohonan+"'";
			 
			 ResultSet rs = stmt.executeQuery(sql); 
		     Hashtable h;
		     
		     while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("status",rs.getString("ID_STATUS")== null?"":rs.getString("ID_STATUS"));
		    	  status.addElement(h);
		     }
			 
		 }
		 finally {
				if(db != null) db.close();
			}

		 
	 }
	public void check_idPermohonan(String idPermohonan) throws Exception {
		 
		 Db db = null;
		 String sql = "";
		 
		 try{
			 
			 id_Permohonan = new Vector();
			 db = new Db();
			 Statement stmt = db.getStatement();
			 
			 sql = "SELECT A.ID_PERMOHONAN FROM TBLPPTMMK A WHERE A.ID_PERMOHONAN ='" +idPermohonan+"'";
			 
			 ResultSet rs = stmt.executeQuery(sql); 
		     Hashtable h;
		     
		     while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("idPermohonan",rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));
		    	  id_Permohonan.addElement(h);
		     }
			 
		 }
		 finally {
				if(db != null) db.close();
			}

		 
	 }
	 
	 public Vector getIdPermohonan(){
		 return id_Permohonan;
	 }
	 public Vector getStatus(){
		 return status;
	 }
	 
	public Vector getPaparMMK(){
		 return paparMMK;
	}
	
	public Vector getButiranAsas(){
		
		return butiran;
	}
	public Vector getButiranLot(){
			
			return butiranLot;
		}
	public Vector getSewaLot(){
		
		return sewaLot;
	}
	public Vector getNoLot(){
		
		return noLot;
}
	public Vector getSemakPermohonan(){
		
		return semakPrmhnn;
	}

}
