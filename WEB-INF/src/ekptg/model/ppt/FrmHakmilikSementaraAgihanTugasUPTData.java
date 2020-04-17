package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmHakmilikSementaraAgihanTugasUPTData {
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraAgihanTugasUPTData.class);
	Vector listA = null;
	Vector listB = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public Vector getRecord(String id_fail,String id_permohonan) throws Exception {	

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Db db = null;
	    String sql = "";
	    try {
	      listA = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("a.ID_PERMOHONAN");
	      r.add("a.NO_PERMOHONAN");
	      r.add("a.ID_FAIL");
	      r.add("d.NO_FAIL");
	      r.add("c.KETERANGAN");
	      r.add("a.ID_STATUS");
	      r.add("b.id_tugas");
	      r.add("a.id_status",r.unquote("c.id_status"));
	      r.add("d.id_fail",r.unquote("a.id_fail"));	
	      r.add("a.id_permohonan",r.unquote("b.id_permohonan(+)"));
      
	      r.add("a.id_fail", r.unquote(id_fail));
	      r.add("a.id_permohonan", r.unquote(id_permohonan));
	      
	      sql = r.getSQLSelect("Tblpptpermohonan a, Tblppttugas b, Tblrujstatus c, Tblpfdfail d");
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN"));
	    	h.put("no_permohonan", rs.getString("NO_PERMOHONAN"));
	    	h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
	    	h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
	    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
	    	h.put("id_tugas", rs.getString("id_tugas")==null?"":rs.getString("id_tugas"));
	    	//h.put("nama_pegawaipengagih", rs.getString("nama_pegawaipengagih")==null?"":rs.getString("nama_pegawaipengagih"));
	    	h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
    	  
	    	listA.addElement(h);
	    	bil++;
	      }
	      return listA;
	    }
	    finally {
	      if (db != null) db.close();
	    }		
	  }	
	 public static Vector getNamaJawatan(String idK)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT k.jawatan ";
		      sql +="FROM Tblrujpegawai k ";
		      sql +="WHERE k.id_pegawai = "+idK+" ";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = null;
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  list = new Vector();
		    	  h = new Hashtable();
		    	  //h.put("id_pegawai", rs.getString("id_pegawai"));
		    	  h.put("jawatan", rs.getString("jawatan")==null?"":rs.getString("jawatan"));
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//find nama jawatan
	 
	 public static void edit_status(Hashtable data) throws Exception {
			Db db = null;
			String sql3 = "";
			
				try
				{						
					String id_permohonan = (String)data.get("id_permohonan");
					String id_status = "148" ;			        				
					  
					//**UNTUK UPDATE ID_STATUS = 149 [SEMAKAN CAWANGAN]
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
	 
	 public static void add_agihan_tugas(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";			
				try
				{			

					String id_permohonan = (String)data.get("id_permohonan");
					String id_fail = (String)data.get("id_fail");
					String ekptg_user_id = (String)data.get("ekptg_user_id");
					String txdTarikhSerahTugas = (String)data.get("txdTarikhSerahTugas");
					String pegawaiPenerima = (String)data.get("pegawaiPenerima");
					String txtCatatan = (String)data.get("txtCatatan");	
					//String txtNamaPegawai = (String)data.get("txtNamaPegawai");						
					String TST = "to_date('" + txdTarikhSerahTugas + "','dd/MM/yyyy')";					
			        				
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("id_fail",id_fail);
					r.add("id_pegawai",ekptg_user_id);
					r.add("id_pegawaipenerima",pegawaiPenerima);
					r.add("id_permohonan",id_permohonan);
					r.add("tarikh_agih", r.unquote(TST));
					r.add("perihal_agih",txtCatatan);
					//r.add("nama_pegawaipengagih",txtNamaPegawai);

					sql = r.getSQLInsert("tblppttugas");
					stmt.executeUpdate(sql);
					myLogger.info("insert agihan tugas :: "+sql);
										
				}finally {
					if(db != null) db.close();
				}
				
		}
	 public Vector getRecordEdit(String id_permohonan, String id_fail, String id_tugas) throws Exception {	

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Db db = null;
		    String sql = "";
		    try {
		      listB = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("a.id_permohonan");
		      r.add("a.no_permohonan");
		      r.add("a.id_fail");
		      r.add("d.no_fail");
		      r.add("b.id_tugas"); 
		      r.add("b.tarikh_agih"); 
		      r.add("b.perihal_agih");
		      r.add("c.keterangan");
		      r.add("su.nama_suburusan");
		      r.add("k.nama_kementerian");
		      r.add("b.id_pegawai");
		      r.add("e.nama_pegawai");
		      r.add("e.jawatan");	
		      r.add("u.user_name");
		      r.add("b.id_pegawaipenerima");
		      r.add("a.id_status");
	    
		      r.add("a.id_permohonan",r.unquote("b.id_permohonan"));
		      r.add("b.id_pegawaipenerima",r.unquote("e.id_pegawai"));
		      r.add("a.id_status",r.unquote("c.id_status"));
		      r.add("d.id_suburusan",r.unquote("su.id_suburusan"));
		      r.add("d.id_kementerian",r.unquote("k.id_kementerian"));
		      r.add("d.id_fail",r.unquote("a.id_fail"));
		      r.add("b.id_pegawai",r.unquote("u.user_id"));
		      
		      r.add("a.id_permohonan", id_permohonan, "=");
		      r.add("a.id_fail", id_fail, "=");			      
		      r.add("b.id_tugas", id_tugas, "=");

		      sql = r.getSQLSelect("Tblpptpermohonan a, Tblppttugas b, Tblrujstatus c, Tblpfdfail d, Tblrujsuburusan su, Tblrujkementerian k, Tblrujpegawai e, Users u ");
		      System.out.println("sql qgihan--"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      //Vector v = new Vector();
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    	h.put("id_permohonan", rs.getString("id_permohonan"));
		    	h.put("no_permohonan", rs.getString("no_permohonan"));
		    	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
		    	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
		    	h.put("id_tugas", rs.getString("id_tugas")==null?"":rs.getString("id_tugas"));
		    	h.put("tarikh_agih", rs.getDate("tarikh_agih")==null?"Belum DiAgihkan":Format.format(rs.getDate("tarikh_agih")));
		    	h.put("perihal_agih", rs.getString("perihal_agih")==null?"":rs.getString("perihal_agih"));
		    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
		    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
		    	h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
		    	h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
		    	h.put("jawatan", rs.getString("jawatan")==null?"":rs.getString("jawatan"));
		    	h.put("user_name", rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	h.put("id_pegawaipenerima", rs.getString("id_pegawaipenerima")==null?"":rs.getString("id_pegawaipenerima"));
		    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		    	
		    	listB.addElement(h);
		    	bil++;
		      }
		      return listB;
		    }
		    finally {
		      if (db != null) db.close();
		    }		
		  }

}
