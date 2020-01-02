package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraNotisIndividuData {
	
	Vector list = null;
	Vector dataPb = null;
	Vector listPenyampaianNotis = null;
	Vector listPBPilihan = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");

	
	public void  setCarianFail(String noFail,String tarikh,String status)throws Exception {
		Db db = null;
	    String sql = "";
	    String chkData = noFail.trim();
	    
	    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
//	      
	      sql = "SELECT distinct s.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, c.nama_negeri, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, f.no_fail, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f, Tblrujsuburusan su, Tblrujnegeri c, Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND c.id_negeri = p.id_negeri AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status AND s.kod_status NOT IN ('32','27') AND s.id_seksyen = 1 AND f.id_suburusan = 53";
	      
	      if(noFail != null){
	    	  sql = sql + " AND UPPER(f.no_fail) LIKE '%'||'" + noFail.toUpperCase() + "'||'%'";  
	      }
	     
	      if(tarikh != null){
	    	  if (!tarikh.toString().trim().equals("")){
	    	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	    	  sql +="AND p.tarikh_permohonan = '" + sdf.format(Format.parse(tarikh)).toUpperCase()+ "' ";
	    	  }
	      }
	      if(status != null){
	    	  if (!status.trim().equals("")){
	    		  if (!status.trim().equals("0")){
	    			  sql +="AND s.id_status = '" + status + "' ";
	    		  }
	    	  }	  
	      }
	    
	      sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
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
	    	  count++;
	      }
	      
	      if(count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("id_fail","");
	    	  h.put("id_status", "");
	    	  h.put("id_permohonan", "");
	    	  h.put("no_permohonan", "");
	    	  h.put("no_rujukan_ptd", "");
	    	  h.put("no_rujukan_ptg", "");
	    	  h.put("no_rujukan_upt", "");
	    	  h.put("tarikh_permohonan", "");
	    	  h.put("nama_suburusan", "");
	    	 // h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));
	    	  h.put("nama_negeri", "");
	    	  h.put("nama_kementerian", "");
	    	  h.put("keterangan", "");
	    	  h.put("no_fail","Tiada rekod.");
	    	  list.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	

	public static Object addPenyampaiNotis(Hashtable data,long id_buktipenyampaian) throws Exception {
		// Masukan data dalam table - Bukti Penyampaian
		Db db = null;
		 String sql = "";
		 String id = "";
		 
		    try
		    {
		    	String idPermohonan = (String)data.get("idPermohonan");
		    	String namaPenghantarNotis = (String)data.get("namaPenghantarNotis");
		    	String namaPB = (String)data.get("namaPB");
		    	String tarikhTampalNotis = (String)data.get("tarikhTampalNotis");
		    	String tkhTampalNotis = "to_date('" + tarikhTampalNotis + "','dd/MM/yyyy')";
		    	String statusSerahan = (String)data.get("statusSerahan");
		    	String jenisSerahan = (String)data.get("jenisSerahan");
		    	String noKp = (String)data.get("noKp");
		    	String catatan = (String)data.get("catatan");
	    		String id_user = (String)data.get("id_user");
	    		String namaWakil = (String)data.get("namaWakil");
	    	 	String tarikhSerahan = (String)data.get("tarikhSerahan");
	    	 	String tkhSerahan = "to_date('" + tarikhSerahan + "','dd/MM/yyyy')";
		    			    	
		    	 db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.add("id_buktiPenyampaian",id_buktipenyampaian);
			     r.add("id_permohonan", idPermohonan);
			     r.add("nama_penghantar", namaPenghantarNotis);
			     r.add("id_pihakberkepentingan", namaPB);
			     r.add("nama_penerima",namaWakil);
			     r.add("tarikh_Tampal_Notis", r.unquote(tkhTampalNotis));
			     r.add("flag_Serah", statusSerahan);
			     r.add("jenis_Serahan", jenisSerahan);
			     r.add("no_Kp_Penerima", noKp);
			     r.add("catatan", catatan);
			     r.add("tarikh_Hantar", r.unquote(tkhSerahan));
			     r.add("tarikh_masuk",r.unquote("sysdate"));
		         r.add("id_masuk",id_user);  
			     			     
			     sql = r.getSQLInsert("tblpptbuktipenyampaian");
			     stmt.executeUpdate(sql);
			     
			     Statement stmtQ = db.getStatement();
			     SQLRenderer rQ = new SQLRenderer();
			     rQ.update("id_permohonan", idPermohonan);
			     rQ.add("id_status", "58");
			    
			     sql = rQ.getSQLUpdate("tblpptpermohonan");
			     stmtQ.executeUpdate(sql);
			     
			     id = ""+ id_buktipenyampaian;
			     //System.out.println(id);
			      
		    }finally {
			      if (db != null) db.close();
		    }
		    
		    return id;
	}
	public static void simpanPilihanPB(Hashtable data,String id_pihakberkepentingan,long id_buktipenyampaian) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_buktipenyampaianpb",DB.getNextID("TBLPPTBUKTIPENYAMPAIANPB_SEQ"));
	    		r.add("id_pihakberkepentingan",id_pihakberkepentingan);
	    		r.add("id_buktipenyampaian",id_buktipenyampaian);	    			    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblpptbuktipenyampaianpb");
	    		stmt.executeUpdate(sql);
  	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanPilihanPB
	public void listPB(String idPermohonan) throws Exception {
		// List maklumat penyampaian notis
		Db db = null;
	    String sql = "";
	    String chkData = idPermohonan.trim();
	    
	    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      	sql = "SELECT DISTINCT c.id_hakmilikpb, a.id_pihakberkepentingan, a.nama_pb, a.no_pb,";
    		sql += " (select count(*) from Tblpptbuktipenyampaianpb where id_pihakberkepentingan(+) = a.id_pihakberkepentingan)as flag ";
	  		sql += " FROM Tblpptpermohonan p, Tblpptpihakberkepentingan a, Tblppthakmilik b, Tblppthakmilikpb c ";
	  		sql += " WHERE c.id_hakmilik = b.id_hakmilik(+)";
	  		sql += " AND b.id_permohonan = p.id_permohonan ";
	  		sql += " AND c.id_pihakberkepentingan = a.id_pihakberkepentingan(+)";
	  		sql += " AND a.id_jenispb not in (40,41,42)";
	  		sql += " AND p.id_permohonan = '"+idPermohonan+"'";    
	  	  
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("no", bil);
	    	  h.put("id_hakmilikpb", rs.getString("id_hakmilikpb"));
	    	  h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan"));
	    	  h.put("nama_pb", rs.getString("nama_pb"));
	    	  h.put("no_pb", rs.getString("no_pb"));

	    	  list.addElement(h);
	    	  bil++;
	      }
	      
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
		
	}
	public void listPBPilihan(String id_buktipenyampaian)throws Exception {
		
		 listPBPilihan = new Vector();
			
		    Db db = null;
		    listPBPilihan.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT c.id_hakmilikpb, a.id_pihakberkepentingan, a.nama_pb, a.id_jenisnopb, c.id_jenispb,a.no_pb, ";
		    		sql += " (select count(*) from Tblpptbuktipenyampaianpb where id_pihakberkepentingan(+) = a.id_pihakberkepentingan and id_buktipenyampaian = '"+id_buktipenyampaian+"' )as flag ";
		    		sql += " FROM Tblpptpermohonan p, Tblpptpihakberkepentingan a, Tblppthakmilik b, Tblppthakmilikpb c, Tblpptbuktipenyampaian d ";
		    		sql += " WHERE c.id_hakmilik = b.id_hakmilik(+) ";
		    		sql += " AND b.id_permohonan = p.id_permohonan  ";
		    		sql += " AND d.id_permohonan(+) = p.id_permohonan ";
					sql += " AND c.id_pihakberkepentingan = a.id_pihakberkepentingan(+) "; 
					sql += " AND a.id_jenispb not in (40,41,42)";
					sql += " AND d.id_buktipenyampaian = '"+id_buktipenyampaian+"'";
					sql += " ORDER BY nama_pb asc ";
					
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		int bil = 1;

		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("no", bil);		    			
		    			h.put("flag", rs.getString("flag")== null?"":rs.getString("flag"));
		    			h.put("id_hakmilikpb", rs.getString("id_hakmilikpb")== null?"":rs.getString("id_hakmilikpb"));
		    			h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan")== null?"":rs.getString("id_pihakberkepentingan"));
		    			h.put("nama_pb", rs.getString("nama_pb")== null?"":rs.getString("nama_pb"));
		    			h.put("id_jenisnopb", rs.getString("id_jenisnopb")== null?"":rs.getString("id_jenisnopb"));
		    			h.put("id_jenispb", rs.getString("id_jenispb")== null?"":rs.getString("id_jenispb"));
		    			h.put("no_pb", rs.getString("no_pb")== null?"":rs.getString("no_pb"));
		    			listPBPilihan.addElement(h);
		    			bil++;
		    			
		      }//close while
		      
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }
	public void listPenyampaianNotis(String idPermohonan) throws Exception{
		
		
		Db db = null;
		String sql = "";

		
		try {
				listPenyampaianNotis = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT a.id_buktipenyampaian,a.nama_penerima,a.nama_penghantar,a.catatan,a.jenis_serahan,a.no_kp_penerima, ";
				sql += " a.tarikh_hantar,a.flag_serah,b.nama_pb,a.tarikh_tampal_notis ";
				sql += " FROM tblpptbuktipenyampaian a,tblpptpihakberkepentingan b ";
				sql += " WHERE b.id_pihakberkepentingan(+) = a.id_pihakberkepentingan";
				sql += " AND a.id_permohonan = '"+idPermohonan+"'";
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				int count = 0;
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_buktipenyampaian", rs.getString("id_buktipenyampaian")==null?"":rs.getString("id_buktipenyampaian"));
					h.put("nama_penerima", rs.getString("nama_penerima")==null?rs.getString("nama_pb"):rs.getString("nama_penerima"));
					h.put("nama_penghantar", rs.getString("nama_penghantar")==null?"":rs.getString("nama_penghantar"));			
					h.put("tarikh_hantar", rs.getString("tarikh_hantar")==null?Format.format(rs.getDate("tarikh_tampal_notis")):Format.format(rs.getDate("tarikh_hantar")));
					listPenyampaianNotis.addElement(h);
					bil++;	
					count++;
				}
				
				if(count == 0){
					h = new Hashtable();
					h.put("bil", "");
					h.put("id_buktipenyampaian", "");
					h.put("nama_penerima", "Tiada rekod.");
					h.put("nama_penghantar", "");			
					h.put("tarikh_hantar", "");
					listPenyampaianNotis.addElement(h);
				
				}
		
			}finally {
				if(db != null) db.close();
			}
			
	}
	public void paparPenerimaNotis(String id_buktipenyampaian) throws Exception {
		// papar penerima notis
		Db db = null;
	    String sql = "";
	    
	    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT b.id_buktipenyampaian, b.nama_penghantar, b.flag_serah, b.jenis_serahan, b.tarikh_hantar, b.id_pihakberkepentingan, b.no_kp_penerima, b.catatan, b.nama_penerima FROM Tblpptbuktipenyampaian b"+
	      		" WHERE b.id_buktipenyampaian = '"+id_buktipenyampaian+"'";
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("id_buktipenyampaian", rs.getString("id_buktipenyampaian"));
	    	  h.put("nama_penghantar", rs.getString("nama_penghantar")!=null?rs.getString("nama_penghantar"):"");
	    	  h.put("nama_penerima", rs.getString("nama_penerima")!=null?rs.getString("nama_penerima"):"");
	    	  h.put("flag_serah", rs.getString("flag_serah")!=null?rs.getString("flag_serah"):"");
	    	  h.put("jenis_serahan", rs.getString("jenis_serahan")!=null?rs.getString("jenis_serahan"):"");
	       	  h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
	       	  h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan")!=null?rs.getString("id_pihakberkepentingan"):"0");
	       	  h.put("no_kp_penerima", rs.getString("no_kp_penerima")!=null?rs.getString("no_kp_penerima"):"");
	       	  h.put("catatan", rs.getString("catatan")!=null?rs.getString("catatan"):"");

	    	  list.addElement(h);
	    	 
	      }
	      
	    
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}

	public static void updatePenerimaNotis(Hashtable data,long id_buktipenyampaian) throws Exception {
		// update 
		Db db = null;
		 String sql = "";
		 String id = "";
		 
		    try
		    {
		    	String idPermohonan = (String)data.get("idPermohonan");
		    	String namaPenghantarNotis = (String)data.get("namaPenghantarNotis");
		    	String namaPB = (String)data.get("namaPB");
		    	String tarikhTampalNotis = (String)data.get("tarikhTampalNotis");
		    	String tkhTampalNotis = "to_date('" + tarikhTampalNotis + "','dd/MM/yyyy')";
		    	String statusSerahan = (String)data.get("statusSerahan");
		    	String jenisSerahan = (String)data.get("jenisSerahan");
		    	String noKp = (String)data.get("noKp");
		    	String catatan = (String)data.get("catatan");
	    		String id_user = (String)data.get("id_user");
	    	 	String tarikhSerahan = (String)data.get("tarikhSerahan");
	    	 	String tkhSerahan = "to_date('" + tarikhSerahan + "','dd/MM/yyyy')";
	    		String namaWakil = (String)data.get("namaWakil");
		    	
		    	 db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.update("id_buktiPenyampaian",id_buktipenyampaian);
			     r.add("id_permohonan", idPermohonan);
			     r.add("nama_penghantar", namaPenghantarNotis);
			     r.add("id_pihakberkepentingan", namaPB);
			     r.add("nama_penerima",namaWakil);
			     r.add("tarikh_Tampal_Notis", r.unquote(tkhTampalNotis));
			     r.add("flag_Serah", statusSerahan);
			     r.add("jenis_Serahan", jenisSerahan);
			     r.add("no_Kp_Penerima", noKp);
			     r.add("catatan", catatan);
			     r.add("tarikh_Hantar", r.unquote(tkhSerahan));
			     r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    	 r.add("id_kemaskini",id_user);  
			     			     
			     sql = r.getSQLUpdate("tblpptbuktipenyampaian");
			     stmt.executeUpdate(sql);
			     
			      
		    }finally {
			      if (db != null) db.close();
		    }
		
		
		
	}

	public static void deleteMaklumat(String id_buktipenyampaian)  throws Exception {
		// delete row
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmtQ = db.getStatement();
	      SQLRenderer rQ = new SQLRenderer();
	      rQ.add("id_buktipenyampaian", id_buktipenyampaian);
	      sql = rQ.getSQLDelete("tblpptbuktipenyampaian");	      
	      stmtQ.executeUpdate(sql);
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
		
		
	}
	public static void deletePilihanPB(long id_buktipenyampaian) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	  
	    		db = new Db();
	    		Statement stmt = db.getStatement();	 
  		
	    		sql = "DELETE FROM Tblpptbuktipenyampaianpb WHERE id_buktipenyampaian = '"+id_buktipenyampaian+"'";
	    		stmt.executeUpdate(sql);
  	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}
	public void setDataPB (String idPB)throws Exception{
		Db db = null;
	    String sql = "";
	    
	    try{
	    	dataPb = new Vector();
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT A.NO_PB"+ 

		    	" FROM TBLPPTPIHAKBERKEPENTINGAN A"+

     			" WHERE A.ID_PIHAKBERKEPENTINGAN = '"+idPB+"'";
		    ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	 
		    	  dataPb.addElement(h);
		      }
		      
	    }
	    finally {
		      if (db != null) db.close();
		    }
		
		
	}
	
	public Vector getDataPb(){
		return dataPb;
	}
	
	public Vector getList(){
		return list;
	}
	public Vector getListPBPilihan(){
		return listPBPilihan;
	}
	public Vector getListPenyampaianNotis(){
		return listPenyampaianNotis;
	}


}
