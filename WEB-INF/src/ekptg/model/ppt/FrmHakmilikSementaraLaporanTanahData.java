package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraLaporanTanahData {
	
	Vector maklumatHakmilik = null;
	Vector id_Permohonan = null;
	Vector maklumatLaporan = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");

	
	public void setMaklumatHakmilik(String idHakmilik)throws Exception{
		Db db = null;
		String sql = "";
		
		try{
			maklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_HAKMILIK,A.NO_LOT, A.LUAS_LOT, E.KETERANGAN AS UNIT_LUAS_LOT,B.NAMA_MUKIM,C.LUAS_SEWA,F.KETERANGAN AS UNIT_LUAS_SEWA,D.KETERANGAN,A.NO_PELAN, A.NO_SYIT"+


				  " FROM TBLPPTHAKMILIK A,"+
				  " TBLRUJMUKIM B,"+
				  " TBLPPTBORANGQ C,"+
				  " TBLRUJKATEGORI D," +
				  " TBLRUJLUAS E," +
				  " TBLRUJLUAS F"+


				  " WHERE B.ID_MUKIM = A.ID_MUKIM"+ 
				  " AND A.ID_HAKMILIK = C.ID_HAKMILIK"+
				  " AND D.ID_KATEGORI(+) = A.ID_KATEGORITANAH"+
				  " AND E.ID_LUAS = A.ID_UNITLUASLOT"+
				  " AND F.ID_LUAS = C.ID_UNITLUAS"+
				  " AND A.ID_HAKMILIK = '"+idHakmilik+"'";
			 
			
			 ResultSet rs = stmt.executeQuery(sql);  
		     Hashtable h;
		     
		     while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
		    	  h.put("LUAS_LOT",rs.getString("LUAS_LOT")==null?"":rs.getString("LUAS_LOT"));
		    	  h.put("UNIT_LUAS_LOT",rs.getString("UNIT_LUAS_LOT")==null?"":rs.getString("UNIT_LUAS_LOT"));
		    	  h.put("NAMA_MUKIM",rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
		    	  h.put("LUAS_SEWA",rs.getString("LUAS_SEWA")==null?"":rs.getString("LUAS_SEWA"));
		    	  h.put("UNIT_LUAS_SEWA",rs.getString("UNIT_LUAS_SEWA")==null?"":rs.getString("UNIT_LUAS_SEWA"));
		    	  h.put("KATEGORI_TANAH",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN").toUpperCase());
		    	  h.put("NO_PELAN",rs.getString("NO_PELAN")==null?"":rs.getString("NO_PELAN"));
		    	  h.put("NO_SYIT",rs.getString("NO_SYIT")==null?"":rs.getString("NO_SYIT"));
		    	  maklumatHakmilik.addElement(h);
		     }
			
			
			
		}finally {
	      if (db != null) db.close();
	    }

	}
	
	public Vector getList(){
		return maklumatHakmilik;
	}
	
	public static String addLaporanTanah(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 String id = "";
		 
		    try
		    {
		    	long idTanahUmum = DB.getNextID("TBLPPTTANAHUMUM_SEQ");
		    	String idHakmilik = (String)data.get("idHakmilik");
		    	String idPermohonan = (String)data.get("idPermohonan");
		    	String tkhMulaChart = (String)data.get("tkhMulaChart");
		    	String tarikhMulaChart = "to_date('" + tkhMulaChart + "','dd/MM/yyyy')";
		    	String tkhAkhirChart = (String)data.get("tkhAkhirChart");
		    	String tarikhAkhirChart= "to_date('" + tkhAkhirChart + "','dd/MM/yyyy')";
		    	String tkhLawatTapak = (String)data.get("tkhLawatTapak");
		    	String tarikhLawatTapak= "to_date('" + tkhLawatTapak + "','dd/MM/yyyy')";
		    	String id_Masuk = (String)data.get("id_Masuk");
		    	String bilSyit = (String)data.get("bilSyit");
		    	String lokasiTanah = (String)data.get("lokasiTanah");
		    	String flag_Jenis_Tanah = (String)data.get("flag_Jenis_Tanah");
		    	String flag_Luar_Simpanan = (String)data.get("flag_Luar_Simpanan");
		    	String flag_Dlm_Simpanan = (String)data.get("flag_Dlm_Simpanan");
		    	String flag_Luar_Majlis = (String)data.get("flag_Luar_Majlis");
		    	String flag_Dlm_Majlis = (String)data.get("flag_Dlm_Majlis");
		    	String rupabumi_Seluruh_Lot = (String)data.get("rupabumi_Seluruh_Lot");
		    	String rupabumi_Kwsn_Terlibat = (String)data.get("rupabumi_Kwsn_Terlibat");
		    	String lot_Seluruh_Lot = (String)data.get("lot_Seluruh_Lot");
		    	String lot_Jenis_Tanaman = (String)data.get("lot_Jenis_Tanaman");
		    	String lot_Keadaan_Tanaman = (String)data.get("lot_Keadaan_Tanaman");
		    	String lot_Kwsn_Terlibat = (String)data.get("lot_Kwsn_Terlibat");
		    	String nama_Pelapor = (String)data.get("nama_Pelapor");
		    	String tkhLapor = (String)data.get("tkhLapor");
		    	String tarikhLaporan= "to_date('" + tkhLapor + "','dd/MM/yyyy')";
		    
		    	 db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.add("id_Tanahumum",idTanahUmum);
			     r.add("id_Hakmilik",idHakmilik);
			     r.add("id_permohonan", idPermohonan);
			     r.add("tarikh_Mula_Chart", r.unquote(tarikhMulaChart));
			     r.add("tarikh_Akhir_Chart", r.unquote(tarikhAkhirChart));
			     r.add("tarikh_Lawatan", r.unquote(tarikhLawatTapak));
			     r.add("nama_Pelapor",nama_Pelapor);
			     r.add("tarikh_Lapor", r.unquote(tarikhLaporan));
			     r.add("id_Masuk", id_Masuk);
			     r.add("perihal_Syit", bilSyit);
			     r.add("lokasi_Tanah", lokasiTanah);
			     r.add("flag_Jenis_Tanah", flag_Jenis_Tanah);
			     r.add("flag_Luar_Simpanan", flag_Luar_Simpanan);
			     r.add("flag_Dlm_Simpanan", flag_Dlm_Simpanan);
			     r.add("flag_Luar_Majlis", flag_Luar_Majlis);
			     r.add("flag_Dlm_Majlis", flag_Dlm_Majlis);
			     r.add("rupabumi_Seluruh_Lot", rupabumi_Seluruh_Lot);
			     r.add("rupabumi_Kwsn_Terlibat", rupabumi_Kwsn_Terlibat);
			     r.add("lot_Seluruh_Lot", lot_Seluruh_Lot);
			     r.add("lot_Jenis_Tanaman", lot_Jenis_Tanaman);
			     r.add("lot_Keadaan_Tanaman", lot_Keadaan_Tanaman);
			     r.add("lot_Kwsn_Terlibat", lot_Kwsn_Terlibat);
			     r.add("tarikh_Masuk", r.unquote("sysdate"));
			     
			     sql = r.getSQLInsert("tblppttanahumum");
			     stmt.executeUpdate(sql);
			     
			     id = ""+ idTanahUmum;
			    
			      
		    }finally {
			      if (db != null) db.close();
		    }
		    
		    return id;
	}
	public static void updateLaporanTanah(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 
		    try
		    {
		    	String idTanahUmum = (String)data.get("idTanahUmum");
		    	String idHakmilik = (String)data.get("idHakmilik");
		    	String idPermohonan = (String)data.get("idPermohonan");
		    	String tkhMulaChart = (String)data.get("tkhMulaChart");
		    	String tarikhMulaChart = "to_date('" + tkhMulaChart + "','dd/MM/yyyy')";
		    	String tkhAkhirChart = (String)data.get("tkhAkhirChart");
		    	String tarikhAkhirChart= "to_date('" + tkhAkhirChart + "','dd/MM/yyyy')";
		    	String tkhLawatTapak = (String)data.get("tkhLawatTapak");
		    	String tarikhLawatTapak= "to_date('" + tkhLawatTapak + "','dd/MM/yyyy')";
		    	String id_Kemaskini = (String)data.get("id_Kemaskini");
		    	String bilSyit = (String)data.get("bilSyit");
		    	String lokasiTanah = (String)data.get("lokasiTanah");
		    	String flag_Jenis_Tanah = (String)data.get("flag_Jenis_Tanah");
		    	String flag_Luar_Simpanan = (String)data.get("flag_Luar_Simpanan");
		    	String flag_Dlm_Simpanan = (String)data.get("flag_Dlm_Simpanan");
		    	String flag_Luar_Majlis = (String)data.get("flag_Luar_Majlis");
		    	String flag_Dlm_Majlis = (String)data.get("flag_Dlm_Majlis");
		    	String rupabumi_Seluruh_Lot = (String)data.get("rupabumi_Seluruh_Lot");
		    	String rupabumi_Kwsn_Terlibat = (String)data.get("rupabumi_Kwsn_Terlibat");
		    	String lot_Seluruh_Lot = (String)data.get("lot_Seluruh_Lot");
		    	String lot_Jenis_Tanaman = (String)data.get("lot_Jenis_Tanaman");
		    	String lot_Keadaan_Tanaman = (String)data.get("lot_Keadaan_Tanaman");
		    	String lot_Kwsn_Terlibat = (String)data.get("lot_Kwsn_Terlibat");
		    	String nama_Pelapor = (String)data.get("nama_Pelapor");
		    	String tkhLapor = (String)data.get("tkhLapor");
		    	String tarikhLaporan= "to_date('" + tkhLapor + "','dd/MM/yyyy')";
		    
		    	 db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.update("id_Tanahumum",idTanahUmum);
			     r.add("id_Hakmilik",idHakmilik);
			     r.add("id_Permohonan", idPermohonan);
			     r.add("tarikh_Mula_Chart", r.unquote(tarikhMulaChart));
			     r.add("tarikh_Akhir_Chart", r.unquote(tarikhAkhirChart));
			     r.add("tarikh_Lawatan", r.unquote(tarikhLawatTapak));
			     r.add("nama_Pelapor",nama_Pelapor);
			     r.add("tarikh_Lapor", r.unquote(tarikhLaporan));
			     r.add("id_Kemaskini", id_Kemaskini);
			     r.add("perihal_Syit", bilSyit);
			     r.add("lokasi_Tanah", lokasiTanah);
			     r.add("flag_Jenis_Tanah", flag_Jenis_Tanah);
			     r.add("flag_Luar_Simpanan", flag_Luar_Simpanan);
			     r.add("flag_Dlm_Simpanan", flag_Dlm_Simpanan);
			     r.add("flag_Luar_Majlis", flag_Luar_Majlis);
			     r.add("flag_Dlm_Majlis", flag_Dlm_Majlis);
			     r.add("rupabumi_Seluruh_Lot", rupabumi_Seluruh_Lot);
			     r.add("rupabumi_Kwsn_Terlibat", rupabumi_Kwsn_Terlibat);
			     r.add("lot_Seluruh_Lot", lot_Seluruh_Lot);
			     r.add("lot_Jenis_Tanaman", lot_Jenis_Tanaman);
			     r.add("lot_Keadaan_Tanaman", lot_Keadaan_Tanaman);
			     r.add("lot_Kwsn_Terlibat", lot_Kwsn_Terlibat);
			     r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			     
			     sql = r.getSQLUpdate("tblppttanahumum");
			     stmt.executeUpdate(sql);
			     
			      
		    }finally {
			      if (db != null) db.close();
		    }
		    
		    
	}
	 public static void edit_status147(Hashtable data) throws Exception {
			Db db = null;
			String sql3 = "";
			
				try
				{						
					String id_permohonan = (String)data.get("id_permohonan");
					String id_status = "147" ;			        				
					  
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
	 
	 public static void edit_status149(Hashtable data) throws Exception {
			Db db = null;
			String sql3 = "";
			
				try
				{						
					String id_permohonan = (String)data.get("id_permohonan");
					String id_status = "149" ;			        				
					  
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
	 
	 public void check_idPermohonan(String idPermohonan) throws Exception {
		 
		 Db db = null;
		 String sql = "";
		 
		 try{
			 
			 id_Permohonan = new Vector();
			 db = new Db();
			 Statement stmt = db.getStatement();
			 
			 sql = "SELECT A.ID_PERMOHONAN FROM TBLPPTTANAHUMUM A WHERE A.ID_PERMOHONAN ='" +idPermohonan+"'";
			 
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
	 
	 public void setMaklumatLaporan (String idPermohonan) throws Exception{
		 
		 Db db = null;
		 String sql = "";
		 
		 try{
			 
			 maklumatLaporan = new Vector();
			 db = new Db();
			 Statement stmt = db.getStatement();
			 
			 sql = "SELECT B.ID_HAKMILIK,B.NO_LOT,B.LUAS_LOT, D.KETERANGAN AS UNIT_LUAS_LOT, G.NAMA_MUKIM, F.LUAS_SEWA, E.KETERANGAN AS UNIT_LUAS_SEWA,"+
				   " H.KETERANGAN AS KATEGORI_TANAH, B.NO_PELAN, B.NO_SYIT,C.TARIKH_MULA_CHART, C.TARIKH_AKHIR_CHART,C.TARIKH_LAWATAN, I.USER_NAME,"+
				   " C.PERIHAL_SYIT,C.FLAG_JENIS_TANAH,C.LOKASI_TANAH,C.FLAG_LUAR_SIMPANAN,C.FLAG_DLM_SIMPANAN,C.FLAG_LUAR_MAJLIS,C.FLAG_DLM_MAJLIS,"+
				   " C.RUPABUMI_SELURUH_LOT, C.RUPABUMI_KWSN_TERLIBAT, C.LOT_SELURUH_LOT, C.LOT_KEADAAN_TANAMAN,C.LOT_JENIS_TANAMAN,C.LOT_KWSN_TERLIBAT,"+
				   " C.ID_TANAHUMUM,C.TARIKH_LAPOR"+


				   " FROM TBLPPTPERMOHONAN A,"+
				   " TBLPPTHAKMILIK B,"+
				   " TBLPPTTANAHUMUM C,"+
				   " TBLRUJLUAS D,"+
				   " TBLRUJLUAS E,"+
				   " TBLPPTBORANGQ F,"+
				   " TBLRUJMUKIM G,"+
				   " TBLRUJKATEGORI H,"+
				   " USERS I"+
     
				   " WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN"+
				   " AND A.ID_PERMOHONAN = B.ID_PERMOHONAN"+
				   " AND B.ID_HAKMILIK = C.ID_HAKMILIK"+
				   " AND B.ID_HAKMILIK = F.ID_HAKMILIK"+
				   " AND G.ID_MUKIM = B.ID_MUKIM"+
				   " AND H.ID_KATEGORI = B.ID_KATEGORITANAH"+
				   " AND A.ID_PERMOHONAN = F.ID_PERMOHONAN"+
				   " AND D.ID_LUAS = B.ID_UNITLUASLOT"+
				   " AND E.ID_LUAS = F.ID_UNITLUAS"+
				   " AND I.USER_ID = C.ID_MASUK"+
				   " AND C.ID_PERMOHONAN = '"+idPermohonan+"'";
			 
			 ResultSet rs = stmt.executeQuery(sql);  
		     Hashtable h;
		     
		     while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("NO_LOT",rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
		    	  h.put("LUAS_LOT",rs.getString("LUAS_LOT")== null?"":rs.getString("LUAS_LOT"));
		    	  h.put("UNIT_LUAS_LOT",rs.getString("UNIT_LUAS_LOT")== null?"":rs.getString("UNIT_LUAS_LOT"));
		    	  h.put("NAMA_MUKIM",rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));
		    	  h.put("LUAS_SEWA",rs.getString("LUAS_SEWA")== null?"":rs.getString("LUAS_SEWA"));
		    	  h.put("UNIT_LUAS_SEWA",rs.getString("UNIT_LUAS_SEWA")== null?"":rs.getString("UNIT_LUAS_SEWA"));
		    	  h.put("KATEGORI_TANAH",rs.getString("KATEGORI_TANAH")== null?"":rs.getString("KATEGORI_TANAH"));
		    	  h.put("NO_PELAN",rs.getString("NO_PELAN")== null?"":rs.getString("NO_PELAN"));
		    	  h.put("NO_SYIT",rs.getString("NO_SYIT")== null?"":rs.getString("NO_SYIT"));
		    	  h.put("TARIKH_MULA_CHART",rs.getString("TARIKH_MULA_CHART")== null?"":Format.format(rs.getDate("TARIKH_MULA_CHART")));
		    	  h.put("TARIKH_AKHIR_CHART",rs.getString("TARIKH_AKHIR_CHART")== null?"":Format.format(rs.getDate("TARIKH_AKHIR_CHART")));
		    	  h.put("TARIKH_LAWATAN",rs.getString("TARIKH_LAWATAN")== null?"":Format.format(rs.getDate("TARIKH_LAWATAN")));
		    	  h.put("USER_NAME",rs.getString("USER_NAME")== null?"":rs.getString("USER_NAME"));
		    	  h.put("PERIHAL_SYIT",rs.getString("PERIHAL_SYIT")== null?"":rs.getString("PERIHAL_SYIT"));
		    	  h.put("FLAG_JENIS_TANAH",rs.getString("FLAG_JENIS_TANAH")== null?"":rs.getString("FLAG_JENIS_TANAH"));
		    	  h.put("LOKASI_TANAH",rs.getString("LOKASI_TANAH")== null?"":rs.getString("LOKASI_TANAH"));
		    	  h.put("FLAG_LUAR_SIMPANAN",rs.getString("FLAG_LUAR_SIMPANAN")== null?"":rs.getString("FLAG_LUAR_SIMPANAN"));
		    	  h.put("FLAG_DLM_SIMPANAN",rs.getString("FLAG_DLM_SIMPANAN")== null?"":rs.getString("FLAG_DLM_SIMPANAN"));
		    	  h.put("FLAG_LUAR_MAJLIS",rs.getString("FLAG_LUAR_MAJLIS")== null?"":rs.getString("FLAG_LUAR_MAJLIS"));
		    	  h.put("FLAG_DLM_MAJLIS",rs.getString("FLAG_DLM_MAJLIS")== null?"":rs.getString("FLAG_DLM_MAJLIS"));
		    	  h.put("RUPABUMI_SELURUH_LOT",rs.getString("RUPABUMI_SELURUH_LOT")== null?"":rs.getString("RUPABUMI_SELURUH_LOT"));
		    	  h.put("RUPABUMI_KWSN_TERLIBAT",rs.getString("RUPABUMI_KWSN_TERLIBAT")== null?"":rs.getString("RUPABUMI_KWSN_TERLIBAT"));
		    	  h.put("LOT_SELURUH_LOT",rs.getString("LOT_SELURUH_LOT")== null?"":rs.getString("LOT_SELURUH_LOT"));
		    	  h.put("LOT_KEADAAN_TANAMAN",rs.getString("LOT_KEADAAN_TANAMAN")== null?"":rs.getString("LOT_KEADAAN_TANAMAN"));
		    	  h.put("LOT_JENIS_TANAMAN",rs.getString("LOT_JENIS_TANAMAN")== null?"":rs.getString("LOT_JENIS_TANAMAN"));
		    	  h.put("LOT_KWSN_TERLIBAT",rs.getString("LOT_KWSN_TERLIBAT")== null?"":rs.getString("LOT_KWSN_TERLIBAT"));
		    	  h.put("ID_TANAHUMUM",rs.getString("ID_TANAHUMUM")== null?"":rs.getString("ID_TANAHUMUM"));
		    	  h.put("TARIKH_LAPOR",rs.getString("TARIKH_LAPOR")== null?"":Format.format(rs.getDate("TARIKH_LAPOR")));

		    	  maklumatLaporan.addElement(h);
		     }
			 
		 }finally {
				if(db != null) db.close();
			}
 
	 }
	 
	 public Vector getMaklumatLaporan(){
		 return maklumatLaporan;
	 }
	 
	 public static void deleteLaporanTanah(String id) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmtQ = db.getStatement();
		      SQLRenderer rQ = new SQLRenderer();
		      rQ.add("id_Tanahumum", id);
		      sql = rQ.getSQLDelete("tblppttanahumum");	      
		      stmtQ.executeUpdate(sql);
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close delete


}
