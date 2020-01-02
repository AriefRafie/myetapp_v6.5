package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class PermohonanUPTSek4 {
	static Logger myLogger = Logger.getLogger(PermohonanUPTSek4.class);
	private static Vector list = new Vector();	
	private static Vector listAG = new Vector();	
	private static Vector listA = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");

	
	public static void setList(String carianFail, String CarianTarikhMohon, String cStatus)throws Exception {
		Db db = null;
	    list.clear();
	    String sql = "";
	    String chkData = carianFail.trim();
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct s.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, c.nama_negeri, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, f.no_fail, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f, Tblrujsuburusan su, Tblrujnegeri c, Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND c.id_negeri = p.id_negeri AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status AND s.id_status = ANY(148,149)";
	      sql +=" AND su.id_suburusan = '51' ";
	      
	      if(carianFail != null){
	    	  sql = sql + " AND UPPER(f.no_fail) LIKE '%'||'" + chkData.toUpperCase() + "'||'%'";  
	      }
//	      if(carianSuburusan != null){
//	    	  if (!carianSuburusan.trim().equals("")){
//	    		  if (!carianSuburusan.trim().equals("0")){
//	    			  sql +="AND f.id_suburusan = '" + carianSuburusan + "' ";
//	    		  }
//	    	  }	  
//	      }
	      if(CarianTarikhMohon != null){
	    	  if (!CarianTarikhMohon.toString().trim().equals("")){
	    	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	    	  sql +="AND p.tarikh_permohonan = '" + sdf.format(Format.parse(CarianTarikhMohon)).toUpperCase()+ "' ";
	    	  }
	      }
	      if(cStatus != null){
	    	  if (!cStatus.trim().equals("")){
	    		  if (!cStatus.trim().equals("0")){
	    			  sql +="AND s.id_status = '" + cStatus + "' ";
	    		  }
	    	  }	  
	      }
	      sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_fail", rs.getString("id_fail"));
	    	  h.put("id_status", rs.getString("id_status"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan"));
	    	  h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
	    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
	    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
	    	 // h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));
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

	public static Vector getRecord(String id_fail,String id_permohonan) throws Exception {	

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Db db = null;
	    listA.clear();
	    String sql = "";
	    try {
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT p.id_permohonan, p.no_permohonan, p.id_fail, f.no_fail, d.nama_mukim, s.keterangan, f.id_suburusan, ";
	    		sql += " p.tarikh_permohonan, p.id_status, f.id_kementerian, p.id_agensi, p.flag_peruntukan, p.flag_segera, f.id_negeri, np.nama_negeri as projek_negeri, ";  
	    		sql += " p.id_daerah, p.tujuan, p.no_rujukan_surat, p.tarikh_kehendaki, p.alamat1, p.alamat2, p.alamat3, p.poskod, "; 
	    		sql += " p.id_negeri, p.id_mukim, k.nama_kementerian, b.nama_daerah, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, "; 
	    		sql += " su.nama_suburusan, c.nama_negeri, e.id_senaraisemak, e.semak1, e.semak2, e.semak3, e.semak4, e.semak5, e.semak6, e.semak7 ";  
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujnegeri c, Tblrujdaerah b, Tblrujsuburusan su, "; 
				sql += " Tblrujstatus s, Tblrujmukim d, Tblpptsenaraisemak e, Tblrujnegeri np ";  
				sql += " WHERE f.id_kementerian = k.id_kementerian ";  
				sql += " AND f.id_negeri = np.id_negeri ";
				sql += " AND f.id_fail = p.id_fail "; 
				sql += " AND b.id_daerah = p.id_daerah "; 
				sql += " AND f.id_suburusan = su.id_suburusan ";  
				sql += " AND p.id_status = s.id_status "; 
				sql += " AND p.id_negeri = c.id_negeri "; 
				sql += " AND p.id_mukim = d.id_mukim "; 
				sql += " AND p.id_permohonan = e.id_permohonan "; 
				sql += " AND p.id_fail = '"+id_fail+"' ";
				sql += " AND p.id_permohonan = '"+id_permohonan+"'";
	    		
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;
	      
	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	    	h.put("projek_negeri", rs.getString("projek_negeri")==null?"-":rs.getString("projek_negeri"));
	        h.put("id_permohonan",rs.getString("id_permohonan"));
	    	h.put("no_permohonan",rs.getString("no_permohonan"));
	    	h.put("no_fail",rs.getString("no_fail"));
	    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	h.put("nama_bandar", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
	    	//h.put("nama_status", rs.getString(7)==null?"":rs.getString(7));
	    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
	    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
	    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
	    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
	    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
	    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
	    	//h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));	    
	    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));  
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
	    	h.put("tujuan", rs.getString("tujuan")==null?"-":rs.getString("tujuan"));
	    	h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat"));	
	    	h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
	    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"-":Format.format(rs.getDate("tarikh_permohonan")));
	    	h.put("alamat1", rs.getString("alamat1")==null?"-":rs.getString("alamat1"));	    	
	    	h.put("alamat2", rs.getString("alamat2")==null?"-":rs.getString("alamat2"));	    
	    	h.put("alamat3", rs.getString("alamat3")==null?"-":rs.getString("alamat3"));	    
	    	h.put("poskod", rs.getString("poskod")==null?"-":rs.getString("poskod"));
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
		    	String id_fail = (String)data.get("id_fail");
			    String norujukanptd = (String)data.get("no_rujukan_ptd");
			    String norujukanptg = (String)data.get("no_rujukan_ptg");
			    String norujukanupt = (String)data.get("no_rujukan_upt");
			    
			    String id_user = (String)data.get("id_user");
			    
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_Fail", id_fail);
			    r.add("no_rujukan_upt", norujukanptd);
			    r.add("no_rujukan_ptg", norujukanptg);
			    r.add("no_rujukan_upt", norujukanupt);
			    r.add("id_kemaskini",id_user);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));
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
						 
				String id_user = (String)data.get("id_user");
				
		        db = new Db();
				Statement stmt = db.getStatement();
				//sql = "UPDATE TBLPPTPERMOHONAN SET no_rujukan_ptg = '"+ no_rujukan_ptg +"', " +
				//"no_rujukan_ptd = '"+ no_rujukan_ptd +"', no_rujukan_upt = '"+ no_rujukan_upt +"' where id_fail = '"+ id_fail +"' ";
				stmt.executeUpdate(sql);
				
				
			}finally {
				if(db != null) db.close();
			}
	}
	public static void updateStatus(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    
	    try
	    {
	    	String id_permohonan = (String)data.get("id_permohonan");
	    	int idP = Integer.parseInt(id_permohonan);
	    	
	    	String id_user = (String)data.get("id_user");
	    	
	    	String statusPCawangan = "149"; //SEMAKAN CAWANGAN
			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", idP);
		    r.add("id_status", statusPCawangan);
		    r.add("id_kemaskini",id_user);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("tblpptpermohonan");
		    stmt.executeUpdate(sql);
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}
    	}
	
	public static Vector getNamaAgensi(String idAgensi)throws Exception {
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
	
	
	/*
	 * 
	 * SEKSYEN 8
	 * 
	 * 
	 */
	
	
	private static Vector listSeksyen8 = new Vector();	
	
	public static Vector getListSeksyen8(){
		 return listSeksyen8;
	}	
	
	public static void setListSeksyen8(String carianFail, String carianSuburusan, String CarianTarikhMohon, String cStatus)throws Exception {
		Db db = null;
		listSeksyen8.clear();
	    String sql = "";
	    String chkData = carianFail.trim();
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct s.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, c.nama_negeri, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, f.no_fail, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f, Tblrujsuburusan su, Tblrujnegeri c, Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND c.id_negeri = p.id_negeri AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status AND s.id_status = ANY(148,149)";
	      sql +=" AND su.id_suburusan = '52' ";
	      
	      if(carianFail != null){
	    	  sql = sql + " AND UPPER(f.no_fail) LIKE '%'||'" + chkData.toUpperCase() + "'||'%'";  
	      }
	      if(carianSuburusan != null){
	    	  if (!carianSuburusan.trim().equals("")){
	    		  if (!carianSuburusan.trim().equals("0")){
	    			  sql +="AND f.id_suburusan = '" + carianSuburusan + "' ";
	    		  }
	    	  }	  
	      }
	      if(CarianTarikhMohon != null){
	    	  if (!CarianTarikhMohon.toString().trim().equals("")){
	    	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	    	  sql +="AND p.tarikh_permohonan = '" + sdf.format(Format.parse(CarianTarikhMohon)).toUpperCase()+ "' ";
	    	  }
	      }
	      if(cStatus != null){
	    	  if (!cStatus.trim().equals("")){
	    		  if (!cStatus.trim().equals("0")){
	    			  sql +="AND s.id_status = '" + cStatus + "' ";
	    		  }
	    	  }	  
	      }
	      sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_fail", rs.getString("id_fail"));
	    	  h.put("id_status", rs.getString("id_status"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan"));
	    	  h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
	    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
	    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
	    	 // h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));
	    	  h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
	    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  if(rs.getString("no_fail") == null){
		    		h.put("no_fail","Belum Diluluskan");
		    	}else{
		    		h.put("no_fail",rs.getString("no_fail"));
		    	}
	    	  listSeksyen8.addElement(h);
	    	  bil++;
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	  
	 
	
}//close class
