package ekptg.model.ppt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class PermohonanUPTSek4PengagihanTugas {
	private static Vector list = new Vector();	
	private static Vector listA = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static void setList(String carianFail, String carianSuburusan)throws Exception {
		Db db = null;
	    list.clear();
	    String sql = "";
	    String chkData = carianFail.trim();
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct p.id_permohonan, p.no_permohonan, f.id_fail, a.nama_agensi, c.nama_negeri, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, f.no_fail, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f, Tblrujsuburusan su, Tblrujnegeri c, Tblrujagensi a, Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND a.id_agensi = p.id_agensi AND c.id_negeri = p.id_negeri AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status AND s.id_status = 128";
	      if(carianFail != null){
	    	  sql = sql + " AND UPPER(f.no_fail) LIKE '%'||'" + carianFail.toUpperCase() + "'||'%'";  
	      }
	      if(carianSuburusan != null){
	    	  if (!carianSuburusan.trim().equals("")){
	    		  if (!carianSuburusan.trim().equals("0")){
	    			  sql +="AND f.id_suburusan = '" + carianSuburusan + "' ";
	    		  }
	    	  }	  
	      }
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_fail", rs.getString("id_fail"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan"));
	    	  h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
	    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
	    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
	    	  h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));
	    	  h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
	    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  if(rs.getString("no_fail") == null){
		    		h.put("no_fail","Belum Diluluskan");
		    	}else{
		    		h.put("no_fail",rs.getString("no_fail"));
		    	}
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

	public static Vector getRecord(int id_fail,int id_permohonan) throws Exception {	

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
	      r.add("d.keterangan"); //keterangan bandar
	      r.add("s.keterangan"); //keterangan id_status
	      r.add("f.id_suburusan");
	      r.add("p.tarikh_permohonan");
	      r.add("p.id_status");
	      r.add("f.id_kementerian");
	      r.add("p.id_agensi");
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
	      r.add("a.nama_agensi");
	      r.add("c.nama_negeri");
	      r.add("e.id_senaraisemak");
	      r.add("e.semak1");
	      r.add("e.semak2");
	      r.add("e.semak3");
	      r.add("e.semak4");
	      r.add("e.semak5");
	      r.add("e.semak6");
	      r.add("e.semak7"); 	      
	      
	      r.add("f.id_kementerian",r.unquote("k.id_kementerian"));
	      r.add("f.id_fail",r.unquote("p.id_fail"));
	      r.add("b.id_daerah",r.unquote("p.id_daerah"));
	      r.add("f.id_suburusan",r.unquote("su.id_suburusan"));
	      r.add("p.id_status",r.unquote("s.id_status"));
	      r.add("p.id_agensi",r.unquote("a.id_agensi"));
	      r.add("p.id_negeri",r.unquote("c.id_negeri"));
	      r.add("p.id_mukim",r.unquote("d.id_bandar"));
	      r.add("p.id_permohonan",r.unquote("e.id_permohonan"));
      
	      r.add("p.id_fail", id_fail, "=");
	      r.add("p.id_permohonan", id_permohonan, "=");

	      sql = r.getSQLSelect("Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujagensi a, Tblrujnegeri c, Tblrujdaerah b, Tblrujsuburusan su, Tblrujstatus s, Tblrujbandar d, Tblpptsenaraisemak e ");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_permohonan",rs.getLong("id_permohonan"));
	    	h.put("no_permohonan",rs.getString("no_permohonan"));
	    	h.put("no_fail",rs.getString("no_fail"));
	    	h.put("nama_bandar", rs.getString(5)==null?"":rs.getString(5));
	    	h.put("nama_status", rs.getString(6)==null?"":rs.getString(6));
	    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
	    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
	    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
	    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
	    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
	    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
	    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));	    
	    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));  
	    	if(rs.getString("id_suburusan") == null){
	    		h.put("id_suburusan","");
	    	}else{
	    		h.put("id_suburusan",rs.getString("id_suburusan"));
	    	}
	    	//h.put("tarikh_permohonan",rs.getDate("tarikh_permohonan")==null?"":rs.getDate("tarikh_permohonan"));
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
	    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
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
	    	h.put("id_senaraisemak", rs.getString("id_senaraisemak")==null?"":rs.getString("id_senaraisemak"));
	    	  h.put("semak1", rs.getString("semak1")==null?"0":rs.getString("semak1"));
	    	  h.put("semak2", rs.getString("semak2")==null?"0":rs.getString("semak2"));
	    	  h.put("semak3", rs.getString("semak3")==null?"0":rs.getString("semak3"));
	    	  h.put("semak4", rs.getString("semak4")==null?"0":rs.getString("semak4"));
	    	  h.put("semak5", rs.getString("semak5")==null?"0":rs.getString("semak5"));
	    	  h.put("semak6", rs.getString("semak6")==null?"0":rs.getString("semak6"));
	    	  h.put("semak7", rs.getString("semak7")==null?"0":rs.getString("semak7"));
	    	listA.addElement(h);
	    	bil++;
	      }
	      return listA;
	    }
	    finally {
	      if (db != null) db.close();
	    }		
	  }	
	
	public static void update(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try
		    {
		    	int id_fail = (Integer)data.get("id_fail");
			    String norujukanptd = (String)data.get("no_rujukan_ptd");
			    String norujukanptg = (String)data.get("no_rujukan_ptg");
			    String norujukanupt = (String)data.get("no_rujukan_upt");
			    
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_Fail", id_fail);
			    r.add("no_rujukan_upt", norujukanptd);
			    r.add("no_rujukan_ptg", norujukanptg);
			    r.add("no_rujukan_upt", norujukanupt);
			
			    sql = r.getSQLUpdate("tblpptpermohonan");
			    stmt.executeUpdate(sql);
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}
	
	public static void updatePermohonan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			try
			{	
				String id_fail = (String)data.get("id_fail");
			    String no_rujukan_ptg = (String)data.get("no_rujukan_ptg");
				String no_rujukan_ptd = (String)data.get("no_rujukan_ptd");
				String no_rujukan_upt = (String)data.get("no_rujukan_upt");
				
						        					
		        db = new Db();
				Statement stmt = db.getStatement();
				//sql = "UPDATE TBLPPTPERMOHONAN SET no_rujukan_ptg = '"+ no_rujukan_ptg +"', " +
				//"no_rujukan_ptd = '"+ no_rujukan_ptd +"', no_rujukan_upt = '"+ no_rujukan_upt +"' where id_fail = '"+ id_fail +"' ";
				stmt.executeUpdate(sql);
				
				/*db = new Db();
				Statement stmtA = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("id_simati", IdSimati);
				r.add("nama_simati",nama_simati);
				r.add("no_kp_baru",no_kpbaru_simati);
				r.add("no_kp_lama",no_kplama_simati);
				r.add("jenis_kp",sel_jeniskp_simati);
				r.add("n0_kp_lain",no_kplain_simati);
				sql1 = r.getSQLUpdate("tblppksimati");
			    stmtA.executeUpdate(sql1);
				
			    db = new Db();
				Statement stmtB = db.getStatement();
				sql2 = "UPDATE TBLPPKSIMATI SET nama_simati='"+ nama_simati +"', no_kp_baru='"+ no_kpbaru_simati +"', " + 
				"no_kp_lama='"+ no_kplama_simati +"', "+
				"jenis_kp='"+ sel_jeniskp_simati +"', "+
				"n0_kp_lain='"+ no_kplain_simati +"' "+ 
				"where id_simati="+ IdSimati +"";
				stmtB.executeUpdate(sql2);
			
				db = new Db();
				Statement stmtc = db.getStatement();
				sql3 = "UPDATE TBLPPKPEMOHON SET no_kp_baru='"+ no_kpbaru_pemohon +"', no_kp_lama='"+ no_kplama_pemohon +"',jenis_kp='"+ sel_jeniskp_pemohon +"', no_kp_lain='"+ no_kplain_pemohon +"', nama_pemohon='"+ nama_pemohon +"', alamat_1='"+ alamat1 +"', alamat_2='"+ alamat2 +"', alamat_3='"+ alamat3 +"', poskod='"+ poskod +"', bandar='"+ bandar +"',id_negeri="+negeri+" where id_pemohon = "+ IdPemohon +"";
				//System.out.println("sql2-->"+sql2);
				stmtc.executeUpdate(sql3);
				
				db = new Db();
				Statement stmtd = db.getStatement();
				sql4 = "UPDATE TBLPPKPERMOHONAN set id_daerahmhn = "+ no_daerah +" where " +
				"id_permohonan = "+ IdPermohonan +" and id_fail="+ IdFail +"";
				//System.out.println("sql3-->"+sql3);
				stmtd.executeUpdate(sql4);	*/
			}finally {
				if(db != null) db.close();
			}
	}
}
