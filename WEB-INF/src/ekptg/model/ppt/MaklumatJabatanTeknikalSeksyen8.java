package ekptg.model.ppt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class MaklumatJabatanTeknikalSeksyen8 {
	private static Vector list = new Vector();
	private static Vector listA = new Vector();
	private static Vector listB = new Vector();
	private static Vector listC = new Vector();
	private static Vector listD = new Vector();
	//private  Vector listPohon = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static void setList(String carianFail, String CarianTarikhMohon, String selectStatusLaporanAwalTanah)throws Exception {
		Db db = null;
	    list.clear();
	    String sql = "";
	    String chkData = carianFail.trim();
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct f.id_fail, f.no_fail, p.id_permohonan, p.no_permohonan, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan, p.id_status, t.id_tanahumum, f.id_suburusan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblppttanahumum t  ";
	      sql +="WHERE f.id_fail = p.id_fail(+) AND f.id_suburusan = su.id_suburusan(+) AND f.id_kementerian = k.id_kementerian(+) AND p.id_permohonan = t.id_permohonan(+) ";
	      sql +="AND p.id_status = s.id_status(+) AND s.id_status IN (22,147) AND f.id_suburusan = 52";
	      if(carianFail != null){
	    	  sql = sql + " AND UPPER(f.no_fail) LIKE '%'||'" + carianFail.toUpperCase() + "'||'%'";  
	      }
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
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);	    	  
	    	  h.put("id_fail", rs.getString("id_fail")==null?"-":rs.getString("id_fail"));
	    	  h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
	    	  h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
	    	  h.put("id_status", rs.getString("id_status")==null?"-":rs.getString("id_status"));
	    	  h.put("id_tanahumum", rs.getString("id_tanahumum")==null?"-":rs.getString("id_tanahumum"));
	    	  h.put("id_suburusan", rs.getString("id_suburusan")==null?"-":rs.getString("id_suburusan"));
	    	  
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

	public static Vector getRecord(int id_fail, int id_permohonan, int id_status) throws Exception {
		
	    Db db = null;
	    listA.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("p.id_permohonan");
	      r.add("p.no_permohonan");
	      r.add("p.id_fail");
	      r.add("f.no_fail");
	      r.add("f.id_suburusan");
	      r.add("p.tarikh_permohonan");
	      r.add("p.id_status");
	      r.add("f.id_kementerian");
	      r.add("p.id_agensi");
	      r.add("a.nama_agensi");
	      r.add("p.flag_peruntukan");
	      r.add("p.flag_segera");
	      r.add("f.id_negeri");
	      r.add("p.id_daerah"); 
	      r.add("p.tujuan");
	      r.add("p.no_rujukan_surat");
	      r.add("p.tarikh_kehendaki");
	      r.add("p.alamat1");
	      r.add("p.alamat2");
	      r.add("p.alamat3");
	      r.add("p.poskod");
	      r.add("p.id_negeri");
	      r.add("p.id_mukim");
	      r.add("k.nama_kementerian");
	      r.add("b.nama_daerah");
	      r.add("p.no_rujukan_ptd");
	      r.add("p.no_rujukan_ptg");
	      r.add("p.no_rujukan_upt");
	      r.add("su.nama_suburusan");
	      r.add("s.keterangan");
	      r.add("a.nama_agensi");
	      r.add("c.nama_negeri");
	      r.add("p.tarikh_terima");	      
	      
	      r.add("f.id_kementerian",r.unquote("k.id_kementerian"));
	      r.add("f.id_fail",r.unquote("p.id_fail"));
	      r.add("b.id_daerah",r.unquote("p.id_daerah"));
	      r.add("f.id_suburusan",r.unquote("su.id_suburusan"));
	      r.add("p.id_status",r.unquote("s.id_status"));
	      r.add("p.id_agensi",r.unquote("a.id_agensi"));
	      r.add("p.id_negeri",r.unquote("c.id_negeri"));
	      r.add("p.id_fail", id_fail, "=");
	      r.add("p.id_permohonan", id_permohonan, "=");

	      sql = r.getSQLSelect("Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujagensi a, Tblrujnegeri c, Tblrujdaerah b, Tblrujsuburusan su, Tblrujstatus s");
	      ResultSet rs = stmt.executeQuery(sql);
	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
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
	    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
	    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
	    	if(rs.getString("id_suburusan") == null){
	    		h.put("id_suburusan","");
	    	}else{
	    		h.put("id_suburusan",rs.getString("id_suburusan"));
	    	}
	    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"":Format.format(rs.getDate("tarikh_terima")));
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
	    	h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"":rs.getString("no_rujukan_surat"));		    	
	    	h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":Format.format(rs.getDate("tarikh_kehendaki")));
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

	 //--Get alamat Kementerian--//
	 public static Vector getAlamatKementerian(int idK)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT k.alamat1, k.alamat2, k.alamat3, k.poskod, n.id_negeri, n.nama_negeri ";
		      sql +="FROM Tblrujkementerian k, Tblrujnegeri n ";
		      sql +="WHERE k.id_negeri = n.id_negeri ";
		      sql +="AND k.id_kementerian = "+idK+" ";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_negeri", rs.getString("id_negeri"));
		    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	  h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
		    	  
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//find alamat kementerian

	 //--Get alamat Kementerian--//
	 public static Vector getNamaNegeri(int idNM)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT n.id_negeri, n.nama_negeri ";
		      sql +="FROM Tblrujnegeri n ";
		      sql +="WHERE n.id_negeri = "+idNM+" ";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_negeri", rs.getString("id_negeri"));
		    	  h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
		    	  
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//find alamat kementerian
	 
		public static void add_ulasanteknikal(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";
			String sql2 = "";
			
				try
				{			
					long id_ulasanteknikal = DB.getNextID("TBLPPTULASANTEKNIKAL_SEQ");
					long id_jabatanteknikal = DB.getNextID("TBLPPTJABATANTEKNIKAL_SEQ");
						
					int id_permohonan = (Integer)data.get("id_permohonan");
					String agensi = (String)data.get("agensi");
					String txdTarikhSurat = (String)data.get("txdTarikhSurat");
					String txdTarikhHantar = (String)data.get("txdTarikhHantar");
					String txtTempoh = (String)data.get("txtTempoh");
					String txtPerihal = (String)data.get("txtPerihal");
					String flag_terima = (String)data.get("flag_terima");	
					String alamat1 = (String)data.get("alamat1");
					String alamat2 = (String)data.get("alamat2");
				    String alamat3 = (String)data.get("alamat3");
				    String poskod = (String)data.get("poskod");
				    String negeri = (String)data.get("negeri");	
				    int bil_surat = (Integer)data.get("bil_surat");
				    String txtNoRujukan = (String)data.get("txtNoRujukan");				    
				    
				    String txtNoRujSuratAdd = (String)data.get("txtNoRujSuratAdd");
				    String txdTkhTerimaAdd = (String)data.get("txdTkhTerimaAdd");
				    String txdTkhSuratAdd = (String)data.get("txdTkhSuratAdd");
				    String socKeputusanAdd = (String)data.get("socKeputusanAdd");
				    String txtUlasanAdd = (String)data.get("txtUlasanAdd");
				    
					String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
					String TH = "to_date('" + txdTarikhHantar + "','dd/MM/yyyy')";
					
					String TTS = "to_date('" + txdTkhTerimaAdd + "','dd/MM/yyyy')";
					String TSA = "to_date('" + txdTkhSuratAdd + "','dd/MM/yyyy')";
					
				  	
				    
			        db = new Db();
					
					//--------------- insert tblpptpejabatteknikal --------------------------------
	
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					
					r2.add("id_jabatanteknikal",id_jabatanteknikal);
					r2.add("nama_jabatan",agensi);
					r2.add("alamat1",alamat1);					
					r2.add("alamat2",alamat2);
					r2.add("alamat3", alamat3);
					r2.add("poskod", poskod);
					r2.add("id_negeri",negeri);
					//r2.add("no_tel","");
					//r2.add("no_fax","");

					sql2 = r2.getSQLInsert("tblpptjabatanteknikal");
					
					stmt2.executeUpdate(sql2);
										
					//end
					
				  //--------------- insert tblpptulasanteknikal --------------------------------
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_ulasanteknikal",id_ulasanteknikal);
					r.add("id_jabatanteknikal",id_jabatanteknikal);
					r.add("id_permohonan",id_permohonan);					
					r.add("tarikh_surat", r.unquote(TS));
					r.add("tarikh_hantar", r.unquote(TH));
					r.add("bil_surat",bil_surat);
					r.add("tempoh",txtTempoh);
					r.add("perihal",txtPerihal);
					r.add("flag_terima",flag_terima); 		
					r.add("no_rujukansurat",txtNoRujukan); 
					r.add("no_rujukansuratjt",txtNoRujSuratAdd);
					r.add("tarikh_suratjt", r.unquote(TSA));
					r.add("tarikh_terimajt", r.unquote(TTS));
					r.add("keputusanjt",socKeputusanAdd);
					r.add("ulasanjt",txtUlasanAdd);
				    
					sql = r.getSQLInsert("tblpptulasanteknikal");
					
					stmt.executeUpdate(sql);

					//end
									
				}finally {
					if(db != null) db.close();
				}				
		}

		public static Vector getRecordJabatanTeknikal(int id_ulasanteknikal, int id_permohonan, int id_status) throws Exception {
		
		    Db db = null;
		    listB.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_ulasanteknikal");
		      r.add("a.id_jabatanteknikal");
		      r.add("a.id_permohonan");
		      r.add("a.no_rujukansuratjt");
		      r.add("a.tarikh_surat");
		      r.add("a.tarikh_suratjt");
		      r.add("a.tarikh_hantar");
		      r.add("a.tarikh_terimajt");
		      r.add("a.bil_surat");
		      r.add("a.bil_surat_jt");
		      r.add("a.tempoh");
		      r.add("a.perihal");
		      r.add("a.keputusanjt");
		      r.add("a.ulasanjt");
		      r.add("a.flag_terima");
		      r.add("b.nama_jabatan");
		      r.add("b.alamat1");
		      r.add("b.alamat2");
		      r.add("b.alamat3");
		      r.add("b.poskod");
		      r.add("b.id_negeri");
		      r.add("b.nama_negeri");   
		      r.add("d.id_status");
		      r.add("e.keterangan");
		      
		      r.add("a.id_jabatanteknikal",r.unquote("b.id_jabatanteknikal"));
		      r.add("b.id_negeri",r.unquote("c.id_negeri"));
		      r.add("a.id_permohonan",r.unquote("d.id_permohonan")); 	
		      r.add("d.id_status",r.unquote("e.id_status"));
	      
		      r.add("a.id_ulasanteknikal", id_ulasanteknikal, "=");
		      r.add("a.id_permohonan", id_permohonan, "=");
	
		      sql = r.getSQLSelect("Tblpptulasanteknikal a, Tblpptjabatanteknikal b, Tblrujnegeri c, Tblpptpermohonan d, Tblrujstatus e");
		      ResultSet rs = stmt.executeQuery(sql);
		      //Vector v = new Vector();
		      Hashtable h;
		      int bil = 1;
	
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_ulasanteknikal", rs.getString("id_ulasanteknikal")==null?"-":rs.getString("id_ulasanteknikal"));
		    	h.put("id_jabatanteknikal", rs.getString("id_jabatanteknikal")==null?"-":rs.getString("id_jabatanteknikal"));
		    	h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
		    	h.put("no_rujukansuratjt", rs.getString("no_rujukansuratjt")==null?"-":rs.getString("no_rujukansuratjt"));
		    	h.put("tarikh_surat", rs.getString("tarikh_surat")==null?"-":rs.getString("tarikh_surat"));
		    	h.put("tarikh_suratjt", rs.getString("tarikh_suratjt")==null?"-":rs.getString("tarikh_suratjt"));
		    	h.put("tarikh_hantar", rs.getString("tarikh_hantar")==null?"-":rs.getString("tarikh_hantar"));
		    	h.put("tarikh_terimajt", rs.getString("tarikh_terimajt")==null?"-":rs.getString("tarikh_terimajt"));
		    	h.put("bil_surat", rs.getString("bil_surat")==null?"-":rs.getString("bil_surat"));
		    	h.put("bil_surat_jt", rs.getString("bil_surat_jt")==null?"-":rs.getString("bil_surat_jt"));
		    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
		    	h.put("tempoh", rs.getString("tempoh")==null?"-":rs.getString("tempoh"));
		    	h.put("perihal", rs.getString("perihal")==null?"-":rs.getString("perihal"));
		    	h.put("keputusanjt", rs.getString("keputusanjt")==null?"-":rs.getString("keputusanjt"));
		    	h.put("ulasanjt", rs.getString("ulasanjt")==null?"-":rs.getString("ulasanjt"));
		    	h.put("flag_terima", rs.getString("flag_terima")==null?"-":rs.getString("flag_terima"));
		    	h.put("nama_jabatan", rs.getString("nama_jabatan")==null?"-":rs.getString("nama_jabatan"));
		    	h.put("alamat1", rs.getString("alamat1")==null?"-":rs.getString("alamat1"));
		    	h.put("alamat2", rs.getString("alamat2")==null?"-":rs.getString("alamat2"));
		    	h.put("alamat3", rs.getString("alamat3")==null?"-":rs.getString("alamat3"));
		    	h.put("poskod", rs.getString("poskod")==null?"-":rs.getString("poskod"));
		    	h.put("id_negeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
		    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));  
		    	h.put("id_status", rs.getString("id_status")==null?"-":rs.getString("id_status"));  
		    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));  
		    	
		    	listB.addElement(h);
		    	bil++;
		      }
		      return listB;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}

			public static Vector getRecordListJabatanTeknikal(int id_permohonan) throws Exception {
				
			    Db db = null;
			    listC.clear();
			    String sql = "";
			    try {
			      Vector localVector1;
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("a.id_ulasanteknikal");
			      r.add("a.id_jabatanteknikal");
			      r.add("a.id_permohonan");
			      r.add("b.nama_jabatan");
			      r.add("a.perihal");
			      r.add("a.tarikh_surat");
			      r.add("a.tempoh"); 
			      r.add("c.id_status");
			      r.add("d.keterangan"); 
			      r.add("a.bil_surat"); //tanda "" tu dah mewakili order by
			      r.add("e.nama_agensi"); 
			      r.add("a.no_rujukansurat"); 

			      r.add("a.id_jabatanteknikal",r.unquote("b.id_jabatanteknikal(+)"));
			      r.add("a.id_permohonan ",r.unquote("c.id_permohonan(+)"));
			      r.add("c.id_status ",r.unquote("d.id_status(+)"));
			      r.add("b.nama_jabatan",r.unquote("e.id_agensi(+)"));

			      r.add("a.id_permohonan", id_permohonan, "=");

			      sql = r.getSQLSelect("Tblpptulasanteknikal a, Tblpptjabatanteknikal b, Tblpptpermohonan c, Tblrujstatus d, Tblrujagensi e", "a.bil_surat");
			      ResultSet rs = stmt.executeQuery(sql);
			      //Vector v = new Vector();
			      Hashtable h;
			      int bil = 1;

			      while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("bil", bil);
			        h.put("id_ulasanteknikal", rs.getString("id_ulasanteknikal")==null?"-":rs.getString("id_ulasanteknikal"));
			    	h.put("id_jabatanteknikal", rs.getString("id_jabatanteknikal")==null?"-":rs.getString("id_jabatanteknikal"));
			    	h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
			    	h.put("nama_jabatan", rs.getString("nama_jabatan")==null?"-":rs.getString("nama_jabatan"));
			    	h.put("perihal", rs.getString("perihal")==null?"-":rs.getString("perihal"));
			    	h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
			    	h.put("tempoh", rs.getString("tempoh")==null?"-":rs.getString("tempoh"));
			    	h.put("id_status", rs.getString("id_status")==null?"-":rs.getString("id_status"));
			    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
			    	h.put("bil_surat", rs.getString("bil_surat")==null?"-":rs.getString("bil_surat"));
			    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
			    	h.put("no_rujukansurat", rs.getString("no_rujukansurat")==null?"-":rs.getString("no_rujukansurat"));
			    	
			    	listC.addElement(h);
			    	bil++;
			      }
			      return listC;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			}

			public static void edit_status(Hashtable data) throws Exception {
				Db db = null;
				String sql3 = "";			
					try
					{			
						String id_permohonan = (String)data.get("id_permohonan");
						String id_status = "22" ;			        				
						  
						//**UNTUK UPDATE ID_STATUS = 22 [MAKLUMAT JABATAN TEKNIKAL]
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

				public static Vector getRecordSurat(int id_permohonan, int id_ulasanteknikal, int id_jabatanteknikal) throws Exception {
					
				    Db db = null;
				    listD.clear();
				    String sql = "";
				    try {
				      Vector localVector1;
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("a.id_ulasanteknikal");
				      r.add("a.id_jabatanteknikal");
				      r.add("a.id_permohonan");
				      r.add("a.no_rujukansuratjt");
				      r.add("a.tarikh_surat");
				      r.add("a.tarikh_suratjt");
				      r.add("a.tarikh_hantar");
				      r.add("a.tarikh_terimajt");
				      r.add("a.bil_surat_jt");
				      r.add("a.keputusanjt");
				      r.add("a.ulasanjt");
				      r.add("a.flag_terima");
				      r.add("a.perihal");
				      r.add("a.tempoh");
				      r.add("a.bil_surat");
				      r.add("b.nama_jabatan");
				      r.add("b.alamat1");
				      r.add("b.alamat2");
				      r.add("b.alamat3");
				      r.add("b.poskod");
				      r.add("b.id_negeri");
				      r.add("c.id_status");
				      r.add("d.keterangan");
				      r.add("e.nama_agensi ");
				      r.add("f.nama_negeri");
				      r.add("a.no_rujukansurat");
				      
				      r.add("a.id_jabatanteknikal",r.unquote("b.id_jabatanteknikal"));
				      r.add("a.id_permohonan",r.unquote("c.id_permohonan"));
				      r.add("c.id_status",r.unquote("d.id_status"));
				      r.add("b.nama_jabatan",r.unquote("e.id_agensi"));
				      r.add("b.id_negeri",r.unquote("f.id_negeri"));

				      r.add("a.id_permohonan", id_permohonan, "=");
				      r.add("a.id_ulasanteknikal", id_ulasanteknikal, "=");
				      r.add("a.id_jabatanteknikal", id_jabatanteknikal, "=");

				      sql = r.getSQLSelect("Tblpptulasanteknikal a, Tblpptjabatanteknikal b, Tblpptpermohonan c, Tblrujstatus d, Tblrujagensi e, Tblrujnegeri f ");
				      ResultSet rs = stmt.executeQuery(sql);
				      //Vector v = new Vector();
				      Hashtable h;
				      int bil = 1;

				      while (rs.next()) {
				    	h = new Hashtable();
				    	h.put("bil", bil);
				        h.put("id_ulasanteknikal", rs.getString("id_ulasanteknikal")==null?"-":rs.getString("id_ulasanteknikal"));
				    	h.put("id_jabatanteknikal", rs.getString("id_jabatanteknikal")==null?"-":rs.getString("id_jabatanteknikal"));
				    	h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
				    	h.put("no_rujukansuratjt", rs.getString("no_rujukansuratjt")==null?"-":rs.getString("no_rujukansuratjt"));
				    	h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"-":Format.format(rs.getDate("tarikh_surat")));
				    	h.put("tarikh_suratjt", rs.getDate("tarikh_suratjt")==null?"-":Format.format(rs.getDate("tarikh_suratjt")));
				    	h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"-":Format.format(rs.getDate("tarikh_hantar")));
				    	h.put("tarikh_terimajt", rs.getDate("tarikh_terimajt")==null?"-":Format.format(rs.getDate("tarikh_terimajt")));
				    	h.put("bil_surat_jt", rs.getString("bil_surat_jt")==null?"-":rs.getString("bil_surat_jt"));
				    	h.put("keputusanjt", rs.getString("keputusanjt")==null?"-":rs.getString("keputusanjt"));
				    	h.put("ulasanjt", rs.getString("ulasanjt")==null?"-":rs.getString("ulasanjt"));
				    	h.put("flag_terima", rs.getString("flag_terima")==null?"-":rs.getString("flag_terima"));
				    	h.put("perihal", rs.getString("perihal")==null?"-":rs.getString("perihal"));
				    	h.put("tempoh", rs.getString("tempoh")==null?"-":rs.getString("tempoh"));
				    	h.put("bil_surat", rs.getString("bil_surat")==null?"-":rs.getString("bil_surat"));
				    	h.put("nama_jabatan", rs.getString("nama_jabatan")==null?"-":rs.getString("nama_jabatan"));
				    	h.put("alamat1", rs.getString("alamat1")==null?"-":rs.getString("alamat1"));
				    	h.put("alamat2", rs.getString("alamat2")==null?"-":rs.getString("alamat2"));
				    	h.put("alamat3", rs.getString("alamat3")==null?"-":rs.getString("alamat3"));
				    	h.put("poskod", rs.getString("poskod")==null?"-":rs.getString("poskod"));
				    	h.put("id_negeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
				    	h.put("id_status", rs.getString("id_status")==null?"-":rs.getString("id_status"));
				    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
				    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
				    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
				    	h.put("no_rujukansurat", rs.getString("no_rujukansurat")==null?"-":rs.getString("no_rujukansurat"));
				    	
				    	listD.addElement(h);
				    	bil++;
				      }
				      return listD;
				    }
				    finally {
				      if (db != null) db.close();
				    }
				}
}
