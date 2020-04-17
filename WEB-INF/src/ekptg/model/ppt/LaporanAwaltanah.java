package ekptg.model.ppt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class LaporanAwaltanah {
	private static Vector list = new Vector();
	private static Vector listA = new Vector();
	private static Vector listB = new Vector();
	private static Vector listC = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static void setList(String carianFail, String CarianTarikhMohon, String selectStatusLaporanAwalTanah)throws Exception {
		Db db = null;
	    list.clear();
	    listA.clear();
	    listB.clear();
	    listC.clear();
	    String sql = "";
	    String chkData = carianFail.trim();
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct f.id_fail, f.no_fail, p.id_permohonan, p.no_permohonan, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan, p.id_status, t.id_tanahumum, f.id_suburusan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblppttanahumum t  ";
	      sql +="WHERE f.id_fail = p.id_fail(+) AND f.id_suburusan = su.id_suburusan(+) AND f.id_kementerian = k.id_kementerian(+) AND p.id_permohonan = t.id_permohonan(+) ";
	      sql +="AND p.id_status = s.id_status(+) AND s.id_status IN (16,147,149)";
	      sql +=" AND su.id_suburusan = '51'";
	      if(carianFail != null){
	    	  sql = sql + " AND UPPER(f.no_fail) LIKE '%'||'" + chkData.toUpperCase() + "'||'%'";  
	      }
//	      if(selectSubUrusanUPT != null){
//	    	  if (!selectSubUrusanUPT.trim().equals("")){
//	    		  if (!selectSubUrusanUPT.trim().equals("0")){
//	    			  sql +="AND f.id_suburusan = '" + selectSubUrusanUPT + "' ";
//	    		  }
//	    	  }	  
//	      }
	      if(selectStatusLaporanAwalTanah != null){
	    	  if (!selectStatusLaporanAwalTanah.trim().equals("")){
	    		  if (!selectStatusLaporanAwalTanah.trim().equals("0")){
	    			  sql +="AND p.id_status = '" + selectStatusLaporanAwalTanah + "' ";
	    		  }
	    	  }	  
	      }
	      if(CarianTarikhMohon != null){
	    	  if (!CarianTarikhMohon.toString().trim().equals("")){
	    	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	    	  sql +="AND p.tarikh_permohonan = '" + sdf.format(Format.parse(CarianTarikhMohon)).toUpperCase()+ "' ";
	    	  }
	      }	 
	      sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);	    	  
	    	  h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	  h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
	    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
	    	  h.put("id_tanahumum", rs.getString("id_tanahumum")==null?"":rs.getString("id_tanahumum"));
	    	  h.put("id_suburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
	    	  
	    	  list.addElement(h);
	    	  bil++;
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getList(){
		  return list;
	  }

		public static Vector getRecord(String id_fail, String id_permohonan, String id_status) throws Exception {
			
		    Db db = null;
		    listA.clear();
		    String sql = "";
		    try {
		
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT DISTINCT ag.nama_agensi,p.id_permohonan, p.no_permohonan, p.id_fail, f.no_fail, f.id_suburusan, p.tarikh_permohonan, p.id_status, ";
		      sql += " f.id_kementerian, p.id_agensi, p.flag_peruntukan, p.flag_segera, f.id_negeri as id_projeknegeri, p.id_daerah, p.tujuan, p.no_rujukan_surat, "; 
		      sql += " p.tarikh_kehendaki, p.alamat1, p.alamat2, p.alamat3, p.poskod, p.id_negeri, p.id_mukim, k.nama_kementerian, b.nama_daerah, "; 
		      sql += " p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, su.nama_suburusan, s.keterangan, c.nama_negeri, p.tarikh_terima, c2.nama_negeri as nama_projeknegeri ";  
			  sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujnegeri c, Tblrujnegeri c2,Tblrujdaerah b, Tblrujsuburusan su, Tblrujstatus s, Tblrujagensi ag "; 
			  sql += " WHERE f.id_kementerian = k.id_kementerian ";  
			  sql += " AND f.id_fail = p.id_fail "; 
			  sql += " AND b.id_daerah = p.id_daerah ";  
			  sql += " AND f.id_suburusan = su.id_suburusan ";  
			  sql += " AND p.id_status = s.id_status ";  
			  sql += " AND p.id_negeri = c.id_negeri ";  
			  sql += " AND f.id_negeri = c2.id_negeri ";
			  sql += " AND p.id_agensi = ag.id_agensi(+) ";
		      sql += " AND p.id_fail = '"+id_fail+"'  AND p.id_permohonan = '"+id_permohonan+"'";

		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
		    	h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
		    	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
		    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
		    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
		    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
		    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd"));
		    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg"));
		    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt"));
		    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
		    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
		    	h.put("nama_projeknegeri", rs.getString("nama_projeknegeri")==null?"-":rs.getString("nama_projeknegeri"));
		    	h.put("id_suburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
		    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    	h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"-":Format.format(rs.getDate("tarikh_terima")));
		    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		    	h.put("id_kementerian", rs.getString("id_kementerian")==null?"":rs.getString("id_kementerian"));
		    	h.put("id_agensi", rs.getString("id_agensi")==null?"":rs.getString("id_agensi"));
		    	h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
		    	h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
		    	h.put("id_negeri_projek", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
		    	h.put("tujuan", rs.getString("tujuan")==null?"-":rs.getString("tujuan"));
		    	h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat"));		    	
		    	h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
		    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
		    	listA.addElement(h);
		    	bil++;
		      }
		      return listA;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

		public static void add_tanah_umum(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";
			
				try
				{			
					long id_tanahumum = DB.getNextID("TBLPPTTANAHUMUM_SEQ");

					String id_user = (String)data.get("id_user");
					
					String id_permohonan = (String)data.get("id_permohonan");					
					String perihal_syit = (String)data.get("perihal_syit");
					String tarikh_mula_chart = (String)data.get("tarikh_mula_chart");
					String tarikh_akhir_chart = (String)data.get("tarikh_akhir_chart");
					String tarikh_lawatan = (String)data.get("tarikh_lawatan");
					String jenis_tanah = (String)data.get("jenis_tanah");
					String bil_hakmilik = (String)data.get("bil_hakmilik");
					String luas_terlibat = (String)data.get("luas_terlibat");
					String id_unitluas = (String)data.get("id_unitluas");					
					String perihal_kawasan_majlis = (String)data.get("perihal_kawasan_majlis");
					String perihal_kawasan_simpan = (String)data.get("perihal_kawasan_simpan");
					String perihal_kawasan_lain2 = (String)data.get("perihal_kawasan_lain2");
					String ulasan_syor = (String)data.get("ulasan_syor");
					String lokasi_tanah = (String)data.get("lokasi_tanah");
					String keadaan_rupabumi = (String)data.get("keadaan_rupabumi");
					String perihal_bangunan = (String)data.get("perihal_bangunan");
					String kemudahan_awam = (String)data.get("kemudahan_awam");
					
					String TMC = "to_date('" + tarikh_mula_chart + "','dd/MM/yyyy')";
					String TTC = "to_date('" + tarikh_akhir_chart + "','dd/MM/yyyy')";
					String TLT = "to_date('" + tarikh_lawatan + "','dd/MM/yyyy')";					
			        				
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("id_tanahumum",id_tanahumum);
					r.add("id_permohonan",id_permohonan);
					r.add("perihal_syit",perihal_syit);					
					r.add("tarikh_mula_chart", r.unquote(TMC));
					r.add("tarikh_akhir_chart", r.unquote(TTC));
					r.add("tarikh_lawatan", r.unquote(TLT));
					r.add("jenis_tanah",jenis_tanah);
					r.add("bil_hakmilik",bil_hakmilik);
					r.add("luas_terlibat",luas_terlibat);
					r.add("id_unitluas",id_unitluas);
					r.add("perihal_kawasan_majlis",perihal_kawasan_majlis);
					r.add("perihal_kawasan_simpan",perihal_kawasan_simpan);
					r.add("perihal_kawasan_lain2",perihal_kawasan_lain2);
					r.add("ulasan_syor",ulasan_syor);
					r.add("lokasi_tanah",lokasi_tanah);
					r.add("keadaan_rupabumi",keadaan_rupabumi);
					r.add("perihal_bangunan",perihal_bangunan);
					r.add("kemudahan_awam",kemudahan_awam);
					r.add("id_masuk",id_user);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					sql = r.getSQLInsert("tblppttanahumum");
					
					stmt.executeUpdate(sql);
									
				}finally {
					if(db != null) db.close();
				}				
		}

		public static void edit_status(Hashtable data) throws Exception {
			Db db = null;
			String sql3 = "";			
				try
				{			
					String id_permohonan = (String)data.get("id_permohonan");
					String id_status = "147" ;			        				
					  
					String id_user = (String)data.get("id_user");
					
					//**UNTUK UPDATE ID_STATUS = 147 [PENYEDIAAN LAPORAN AWAL]
					  db = new Db();					  
					  Statement stmt3 = db.getStatement();
					  SQLRenderer r3 = new SQLRenderer();
					
					  r3.update("id_permohonan", id_permohonan);

					  r3.add("id_status", id_status);
					  r3.add("id_kemaskini",id_user);
					  r3.add("tarikh_kemaskini",r3.unquote("sysdate"));
					  sql3 = r3.getSQLUpdate("tblpptpermohonan");
					  stmt3.executeUpdate(sql3);
					 			
				}finally {
					if(db != null) db.close();
				}			
		}

	

		public static Vector getRecordLaporan(String id_fail, String id_permohonan, String id_status, String id_tanahumum) throws Exception {
			
		    Db db = null;
		    listA.clear();
		    String sql = "";
		    try {
		    		Vector localVector1;
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT p.id_permohonan, p.no_permohonan, p.id_fail, f.no_fail, f.id_suburusan, p.tarikh_permohonan, p.id_status, f.id_kementerian, "; 
		    		sql += " p.id_agensi, p.flag_peruntukan, p.flag_segera, f.id_negeri, p.id_daerah, p.tujuan, p.no_rujukan_surat, p.tarikh_kehendaki, "; 
		    		sql += " p.alamat1, p.alamat2, p.alamat3, p.poskod, p.id_negeri, p.id_mukim, k.nama_kementerian, b.nama_daerah, ";
		    		sql += " p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, su.nama_suburusan, s.keterangan, c.nama_negeri, p.tarikh_terima, pn.nama_negeri as nama_projeknegeri ";  
		    		sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujnegeri c, Tblrujdaerah b, Tblrujsuburusan su, Tblrujstatus s, Tblrujnegeri pn ";
		    		sql += " WHERE f.id_kementerian = k.id_kementerian ";  
		    		sql += " AND f.id_fail = p.id_fail "; 
		    		sql += " AND f.id_negeri = pn.id_negeri ";
		    		sql += " AND b.id_daerah = p.id_daerah ";  
		    		sql += " AND f.id_suburusan = su.id_suburusan ";  
		    		sql += " AND p.id_status = s.id_status "; 
		    		sql += " AND p.id_negeri = c.id_negeri "; 
		    		sql += " AND p.id_fail = '"+id_fail+"'";  
		    		sql += " AND p.id_permohonan = '"+id_permohonan+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);

		    		Hashtable h;
		    		int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    	h.put("nama_projeknegeri", rs.getString("nama_projeknegeri")==null?"-":rs.getString("nama_projeknegeri"));
		        h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
		    	h.put("no_permohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
		    	h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
		    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
		    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
		    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd"));
		    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg"));
		    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt"));
		    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
		    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
		    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
		    	if(rs.getString("id_suburusan") == null){
		    		h.put("id_suburusan","");
		    	}else{
		    		h.put("id_suburusan",rs.getString("id_suburusan"));
		    	}
		    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    	h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"-":Format.format(rs.getDate("tarikh_terima")));
		    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		    	if(rs.getString("id_kementerian") == null){
		    		h.put("id_kementerian","");
		    	}else{
		    		h.put("id_kementerian",rs.getString("id_kementerian"));
		    	}
		    	if(rs.getString("id_agensi") == null){
		    		h.put("id_agensi","");
		    	}else{
		    		h.put("id_agensi",rs.getString("id_agensi"));
		    	}
		    	h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
		    	h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
		    	if(rs.getString("id_negeri") == null){
		    		h.put("id_negeri_projek","");
		    	}else{
		    		h.put("id_negeri_projek",rs.getString("id_negeri"));
		    	}
		    	if(rs.getString("id_daerah") == null){
		    		h.put("id_daerah","");
		    	}else{
		    		h.put("id_daerah",rs.getString("id_daerah"));
		    	}
		    	h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
		    	h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat"));		    	
		    	h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
		    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	if(rs.getString("id_negeri") == null){
		    		h.put("id_negeri","");
		    	}else{
		    		h.put("id_negeri",rs.getString("id_negeri"));
		    	}
		    	if(rs.getString("id_mukim") == null){
		    		h.put("id_mukim","");
		    	}else{
		    		h.put("id_mukim",rs.getString("id_mukim"));
		    	}
		    	listA.addElement(h);
		    	bil++;
		      }
		      return listA;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

			public static Vector getRecordLaporanTU(String id_permohonan, String id_tanahumum) throws Exception {
				
			    Db db = null;
			    listC.clear();
			    String sql = "";
			    try {
			      Vector localVector1;
			      db = new Db();
			      Statement stmt = db.getStatement();
			     
			      sql = "SELECT DISTINCT a.id_tanahumum, a.id_permohonan, a.tarikh_lawatan, ";
			      sql += " a.tarikh_mula_chart, a.tarikh_akhir_chart, a.perihal_syit, a.jenis_tanah, ";
			      sql += " c.keterangan, a.perihal_kawasan_simpan, a.perihal_kawasan_majlis, a.perihal_kawasan_lain2, ";
			      sql += " a.lokasi_tanah, a.keadaan_rupabumi, a.kemudahan_awam, a.perihal_bangunan, a.ulasan_syor, "; 
			      sql += " a.bil_hakmilik, d.keterangan, a.luas_terlibat, a.id_unitluas "; 
				  sql += " FROM  Tblppttanahumum a, Tblpptpermohonan b, Tblrujjenistanah c, Tblrujluas d "; 
				  sql += " WHERE a.id_permohonan = b.id_permohonan "; 
				  sql += " AND a.jenis_tanah = c.id_jenistanah(+) ";  
				  sql += " AND a.id_unitluas = d.id_luas(+) ";  
				  sql += " AND a.id_permohonan = '"+id_permohonan+"'"; 
				  sql += " AND a.id_tanahumum = '"+id_tanahumum+"'"; 
			      
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      //Vector v = new Vector();
			      Hashtable h;
			      int bil = 1;

			      while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("bil", bil);
			        h.put("id_tanahumum", rs.getString("id_tanahumum")==null?"":rs.getString("id_tanahumum"));
			    	h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
			    	h.put("tarikh_lawatan", rs.getDate("tarikh_lawatan")==null?"":Format.format(rs.getDate("tarikh_lawatan")));
			    	h.put("tarikh_mula_chart", rs.getDate("tarikh_mula_chart")==null?"":Format.format(rs.getDate("tarikh_mula_chart")));
			    	h.put("tarikh_akhir_chart", rs.getDate("tarikh_akhir_chart")==null?"":Format.format(rs.getDate("tarikh_akhir_chart")));
			    	h.put("perihal_syit", rs.getString("perihal_syit")==null?"":rs.getString("perihal_syit"));
			    	h.put("jenis_tanah", rs.getString("jenis_tanah")==null?"":rs.getString("jenis_tanah"));
			    	h.put("keterangan_jenistanah", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	h.put("perihal_kawasan_simpan", rs.getString("perihal_kawasan_simpan")==null?"":rs.getString("perihal_kawasan_simpan"));
			    	h.put("perihal_kawasan_majlis", rs.getString("perihal_kawasan_majlis")==null?"":rs.getString("perihal_kawasan_majlis"));
			    	h.put("perihal_kawasan_lain2", rs.getString("perihal_kawasan_lain2")==null?"":rs.getString("perihal_kawasan_lain2"));
			    	h.put("lokasi_tanah", rs.getString("lokasi_tanah")==null?"":rs.getString("lokasi_tanah"));
			    	h.put("keadaan_rupabumi", rs.getString("keadaan_rupabumi")==null?"":rs.getString("keadaan_rupabumi"));
			    	h.put("kemudahan_awam", rs.getString("kemudahan_awam")==null?"":rs.getString("kemudahan_awam"));
			    	h.put("perihal_bangunan", rs.getString("perihal_bangunan")==null?"":rs.getString("perihal_bangunan"));
			    	h.put("ulasan_syor", rs.getString("ulasan_syor")==null?"":rs.getString("ulasan_syor"));
			    	h.put("bil_hakmilik", rs.getString("bil_hakmilik")==null?"":rs.getString("bil_hakmilik"));
			    	h.put("keterangan_unitluas", rs.getString(18)==null?"":rs.getString(18));
			    	h.put("luas_terlibat", rs.getString("luas_terlibat")==null?"":rs.getString("luas_terlibat"));		
			    	h.put("id_unitluas", rs.getString("id_unitluas")==null?"":rs.getString("id_unitluas"));	
			    	
			    	listC.addElement(h);
			    	bil++;
			      }
			      return listC;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			}

				public static void updateMaklumatTanahUmum(Hashtable data) throws Exception {
				    Db db = null;
				    String sql = "";		   
				    try
				    {		    	
				    	String id_permohonan = (String)data.get("id_permohonan");
				    	String id_tanahumum = (String)data.get("id_tanahumum");		      		      
					  String txtNoSyitEdit = (String)data.get("txtNoSyitEdit");
					  String txdTarikhMulaChartingEdit = (String)data.get("txdTarikhMulaChartingEdit");
					  String txtKeseluruhanLotEdit = (String)data.get("txtKeseluruhanLotEdit");
					  String txdTarikhTamatChartingEdit = (String)data.get("txdTarikhTamatChartingEdit");
					  String txdTarikhLawatanEdit = (String)data.get("txdTarikhLawatanEdit");
					  String txtKeluasanEdit = (String)data.get("txtKeluasanEdit");			  
					  String id_existjenistanah = (String)data.get("id_existjenistanah");  
					  String id_unitluas = (String)data.get("id_unitluas");
					  String txtKwsMajlisEdit = (String)data.get("txtKwsMajlisEdit");
					  String txtLokasiTanahEdit = (String)data.get("txtLokasiTanahEdit"); 	
					  String txtKwsSmpMelayuEdit = (String)data.get("txtKwsSmpMelayuEdit"); 
					  String txtKeadaanRupabumiEdit = (String)data.get("txtKeadaanRupabumiEdit"); 
					  String txtLainLainEdit = (String)data.get("txtLainLainEdit"); 
					  String txtPerihalBgnanEdit = (String)data.get("txtPerihalBgnanEdit");
					  String txtUlasanEdit = (String)data.get("txtUlasanEdit");
					  String txtKemudahanAwamEdit = (String)data.get("txtKemudahanAwamEdit");
					  
					  String TMC = "to_date('" + txdTarikhMulaChartingEdit + "','dd/MM/yyyy')";
					  String TTC = "to_date('" + txdTarikhTamatChartingEdit + "','dd/MM/yyyy')";
					  String TL = "to_date('" + txdTarikhLawatanEdit + "','dd/MM/yyyy')";				  
					  
					  //int idUnitLuas = Integer.parseInt(id_unitluas);
					  
					  String id_user = (String)data.get("id_user");
					  
					  db = new Db();			  
					  Statement stmt = db.getStatement();
					  SQLRenderer r = new SQLRenderer();
					  r.update("id_tanahumum", id_tanahumum);
					  r.update("id_permohonan", id_permohonan);
					  
					  r.add("perihal_syit", txtNoSyitEdit);
					  //r.add("tarikh_mula_chart", txdTarikhMulaChartingEdit);
					  r.add("tarikh_mula_chart", r.unquote(TMC));
					  r.add("bil_hakmilik", txtKeseluruhanLotEdit);
					  //r.add("tarikh_akhir_chart", txdTarikhTamatChartingEdit);
					  r.add("tarikh_akhir_chart", r.unquote(TTC));
					  //r.add("tarikh_lawatan", txdTarikhLawatanEdit);
					  r.add("tarikh_lawatan", r.unquote(TL));
					  r.add("luas_terlibat", txtKeluasanEdit);
					  r.add("jenis_tanah", id_existjenistanah);
					  r.add("id_unitluas", id_unitluas);
					  r.add("perihal_kawasan_majlis", txtKwsMajlisEdit);
					  r.add("lokasi_tanah", txtLokasiTanahEdit);
					  r.add("perihal_kawasan_simpan", txtKwsSmpMelayuEdit);
					  r.add("keadaan_rupabumi", txtKeadaanRupabumiEdit);
					  r.add("perihal_kawasan_lain2", txtLainLainEdit);
					  r.add("perihal_bangunan", txtPerihalBgnanEdit);
					  r.add("ulasan_syor", txtUlasanEdit);
					  r.add("kemudahan_awam", txtKemudahanAwamEdit);
					  r.add("id_kemaskini",id_user);
					  r.add("tarikh_kemaskini",r.unquote("sysdate"));
					  sql = r.getSQLUpdate("tblppttanahumum");
				      stmt.executeUpdate(sql);
				    }
				    finally {
				      if (db != null) db.close();
				    }
				  }//close updateMaklumatTanahUmum

					public static void add_tanah_umum_seksyen8(Hashtable data) throws Exception {
						Db db = null;
						String sql = "";
						
							try
							{			
								long id_tanahumum = DB.getNextID("TBLPPTTANAHUMUM_SEQ");
								
								String id_user = (String)data.get("id_user");
								
								String id_permohonan = (String)data.get("id_permohonan");					
								String txdTarikhMulaCharting = (String)data.get("txdTarikhMulaCharting");
								String txdTarikhTamatCharting = (String)data.get("txdTarikhTamatCharting");
								String txdTarikhLawatan = (String)data.get("txdTarikhLawatan");
								String txtPerihalBgnan = (String)data.get("txtPerihalBgnan");
								String txtKeseluruhanLot = (String)data.get("txtKeseluruhanLot");
								String txtKeluasan = (String)data.get("txtKeluasan");
								String socLuas = (String)data.get("socLuas");
								String txtAnggaranKosTanah = (String)data.get("txtAnggaranKosTanah");					
								String txtAnggaranKosBangunan = (String)data.get("txtAnggaranKosBangunan");
								String txtTanahPersendirian = (String)data.get("txtTanahPersendirian");
								String txtTanahRizab = (String)data.get("txtTanahRizab");
								String txtKawRizab = (String)data.get("txtKawRizab");
								String txtTanahPersekutuan = (String)data.get("txtTanahPersekutuan");
								String txtKeadaanTanah = (String)data.get("txtKeadaanTanah");
								String txtLokasiTanah = (String)data.get("txtLokasiTanah");
								String txtTanahKerajaan = (String)data.get("txtTanahKerajaan");
								String txtSyor = (String)data.get("txtSyor");
								
								String TMC = "to_date('" + txdTarikhMulaCharting + "','dd/MM/yyyy')";
								String TTC = "to_date('" + txdTarikhTamatCharting + "','dd/MM/yyyy')";
								String TLT = "to_date('" + txdTarikhLawatan + "','dd/MM/yyyy')";					
								
								db = new Db();
								Statement stmt = db.getStatement();
								SQLRenderer r = new SQLRenderer();
								r.add("id_tanahumum",id_tanahumum);
								r.add("id_permohonan",id_permohonan);					
								r.add("tarikh_mula_chart", r.unquote(TMC));
								r.add("tarikh_akhir_chart", r.unquote(TTC));
								r.add("tarikh_lawatan", r.unquote(TLT));
								r.add("perihal_bangunan",txtPerihalBgnan);
								r.add("bil_hakmilik",txtKeseluruhanLot);
								r.add("luas_terlibat",txtKeluasan);
								r.add("id_unitluas",socLuas);
								r.add("harga_anggar",txtAnggaranKosTanah);
								r.add("harga_anggar_bangunan",txtAnggaranKosBangunan);
								r.add("perihal_tm_sendiri",txtTanahPersendirian);
								r.add("perihal_tr_negeri",txtTanahRizab);
								r.add("perihal_kawasan_simpan",txtKawRizab);
								r.add("perihal_tmtr_sekutuan",txtTanahPersekutuan);
								r.add("keadaan_rupabumi",txtKeadaanTanah);
								r.add("lokasi_tanah",txtLokasiTanah);
								r.add("perihal_tnh_kerajaan",txtTanahKerajaan);
								r.add("ulasan_syor",txtSyor);
								r.add("id_masuk",id_user);
								r.add("tarikh_masuk",r.unquote("sysdate"));
								sql = r.getSQLInsert("tblppttanahumum");
								stmt.executeUpdate(sql);
												
							}finally {
								if(db != null) db.close();
							}				
					}	//close add_tanah_umum_seksyen8

	
						public static void edit_status_seksyen8(Hashtable data) throws Exception {
							Db db = null;
							String sql3 = "";			
								try
								{			
									String id_permohonan = (String)data.get("id_permohonan");
									String id_status = "147" ;			        				
									  
									String id_user = (String)data.get("id_user");
									
									//**UNTUK UPDATE ID_STATUS = 147 [PENYEDIAAN LAPORAN AWAL]
									  db = new Db();					  
									  Statement stmt3 = db.getStatement();
									  SQLRenderer r3 = new SQLRenderer();
									
									  r3.update("id_permohonan", id_permohonan);

									  r3.add("id_status", id_status);
									  r3.add("id_kemaskini",id_user);
									  r3.add("tarikh_kemaskini",r3.unquote("sysdate"));
									  sql3 = r3.getSQLUpdate("tblpptpermohonan");
									  stmt3.executeUpdate(sql3);
									  			
								}finally {
									if(db != null) db.close();
								}			
						}
						
						public static Vector getNamaAgensi(int idAgensi)throws Exception {
						    Db db = null;
						    String sql = "";
						    try {
						      db = new Db();
						      Statement stmt = db.getStatement();
						      SQLRenderer r = new SQLRenderer();
						      r.add("a.nama_agensi");	 
						     
						      r.add("a.id_agensi",idAgensi);
						      
						      sql = r.getSQLSelect("tblrujagensi a");
						      ResultSet rs = stmt.executeQuery(sql);
						      Vector list = new Vector();
						      
						      Hashtable h = null;

						      while (rs.next()) {
						    	  h = new Hashtable();
						    	  h.put("nama_agensi",rs.getString("nama_agensi"));	    	 
						    	  list.addElement(h);
						      }
						      return list;
						    } finally {
						      if (db != null) db.close();
						    }
						  }		
						
		public static Vector getListHakmilik(String id_permohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		     
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_hakmilik");
		      r.add("ls.keterangan");
		      r.add("a.no_hakmilik");
		      r.add("lt.keterangan");
		      r.add("a.no_lot");
		      r.add("a.no_pt");
		      r.add("a.id_mukim");
		      r.add("c.nama_mukim");
		      r.add("a.id_daerah");
		      r.add("d.nama_daerah");
		      r.add("a.luas_ambil");
		      r.add("a.luas_lot");
		      r.add("jn.id_jenishakmilik");
		      r.add("ne.id_negeri");
		      
		      r.add("a.id_permohonan",r.unquote("b.id_permohonan"));
		      r.add("a.id_mukim",r.unquote("c.id_mukim"));
		      r.add("a.id_lot",r.unquote("lt.id_lot"));
		      r.add("ls.id_luas",r.unquote("a.id_unitluaslot"));
		      r.add("a.id_daerah",r.unquote("d.id_daerah"));
		      r.add("a.id_negeri",r.unquote("ne.id_negeri"));
		      r.add("a.id_jenishakmilik",r.unquote("jn.id_jenishakmilik"));
		      //r.add("a.id_permohonan", id_permohonan, "=");

		      r.add("a.id_permohonan", id_permohonan);
		      
		      sql = r.getSQLSelect("Tblppthakmilik a, Tblrujlot lt, Tblrujluas ls, Tblrujnegeri ne, Tblpptpermohonan b, Tblrujjenishakmilik jn, Tblrujmukim c, Tblrujdaerah d ");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      
		      Vector listC = new Vector();
		      Hashtable h= null;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);    
		    	h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"-":rs.getString("id_hakmilik"));
		    	h.put("unit_luas", rs.getString(2)==null?"-":rs.getString(2));
		    	h.put("kod_lot", rs.getString(4)==null?"-":rs.getString(4));
		    	h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
		    	h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
		    	h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
		    	h.put("id_mukim", rs.getString("id_mukim")==null?"-":rs.getString("id_mukim"));
		    	h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
		    	h.put("id_daerah", rs.getString("id_daerah")==null?"-":rs.getString("id_daerah"));
		    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
		    	h.put("luas_ambil", rs.getString("luas_ambil")==null?"-":rs.getString("luas_ambil"));
		    	h.put("luas_lot", rs.getString("luas_lot")==null?"-":rs.getString("luas_lot"));
		    	h.put("id_jenishakmilik", rs.getString("id_jenishakmilik"));
		    	h.put("id_negeri", rs.getString("id_negeri"));
		    	
		    	listC.addElement(h);
		    	bil++;
		      }
		      return listC;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}
					
}
