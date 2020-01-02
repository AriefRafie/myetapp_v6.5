package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;


public class Sek4MMKSenarai {

	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private  Vector list = new Vector();
	private  Vector listSek4MMK = new Vector();
	private  Vector listKertas = new Vector();
	private  Vector listKeputusan = new Vector();
	
	public  Vector getData(){
		return list;
	}
	
	public Vector getSek4MMK(){
		return listSek4MMK;
	}
	
	public Vector getListKertas(){
		return listKertas;
	}
	
	public Vector getListKeputusan(){
		return listKeputusan;
	}
	
	
	public void setDataSek4MMK(int id) throws Exception {
		Db db = null;
		listSek4MMK.clear();
		String sql = "";
		
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("p.id_permohonan");
		r.add("p.no_permohonan");
		r.add("f.no_fail");
		r.add("n.nama_negeri");
		r.add("p.tarikh_terima");
		r.add("p.tujuan");
		r.add("k.nama_kementerian");
		r.add("k.kod_kementerian");
		r.add("s.keterangan");
		r.add("d.nama_daerah");
		r.add("p.tarikh_kehendaki");
		r.add("a.nama_agensi");
		r.add("p.no_rujukan_ptd");
		r.add("p.no_rujukan_ptg");
		r.add("p.no_rujukan_surat");
		
		
		r.add("f.id_fail",r.unquote("p.id_fail"));
		r.add("k.id_kementerian",r.unquote("f.id_kementerian"));
		r.add("n.id_negeri",r.unquote("p.id_negeri"));
		r.add("s.id_status",r.unquote("p.id_status"));
		r.add("d.id_daerah",r.unquote("p.id_daerah"));
		r.add("a.id_agensi",r.unquote("p.id_agensi"));
		
		r.add("p.id_Permohonan",id);
		
		sql = r.getSQLSelect("Tblpfdfail f, Tblrujagensi a, Tblrujdaerah d, Tblrujnegeri n, Tblrujkementerian k, Tblrujstatus s, Tblpptpermohonan p");
		
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		
		while(rs.next()) {
			h = new Hashtable();
			h.put("idPermohonan", rs.getString("id_Permohonan")==null?"nodata":rs.getString("id_Permohonan"));
			h.put("tujuan", rs.getString("tujuan")==null?"nodata":rs.getString("tujuan"));
			h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
			h.put("status", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
			h.put("daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
			h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
			h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
			h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
			h.put("kod_kementerian", rs.getString("kod_kementerian")==null?"-":rs.getString("kod_kementerian"));
			h.put("namaNegeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
			h.put("tarikhTerima", rs.getDate("tarikh_terima")==null?"-":Format.format(rs.getDate("tarikh_terima")));
			h.put("noPermohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
			h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd"));
			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg"));
			h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat"));
			listSek4MMK.addElement(h);
		}
		}
		finally {
			if(db != null) db.close();
		}
		
	}//close datasek4mmk

	public void setDataListKertas(int id) throws Exception{
		Db db = null;
		listKertas.clear();
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("p.id_permohonan");
			r.add("m.id_mmk");
			//r.add("mk.id_mmkkeputusan");
			r.add("m.no_rujmmk");
			r.add("m.tujuan");
			r.add("m.latarbelakang");
			r.add("m.asas_pertimbangan");
			r.add("m.implikasi");
			r.add("m.kesimpulan");
			r.add("m.syor");
			r.add("m.tarikh_masuk");
			
			//r.add("mk.tarikh_hantar");
			//r.add("mk.tarikh_terima");
			//r.add("mk.tarikh_keputusan");
			//r.add("mk.status_keputusan");
			//r.add("mk.ulasan");
			
			
			r.add("m.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
			//r.add("mk.id_mmk",r.unquote("m.id_mmk(+)"));
			
			r.add("p.id_Permohonan",id);
			
			
			sql = r.getSQLSelect("Tblpptpermohonan p, Tblpptmmk m ");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_permohonan", rs.getString("id_Permohonan")==null?"-":rs.getString("id_Permohonan"));
				h.put("id_mmk", rs.getString("id_mmk")==null?"-":rs.getString("id_mmk"));
				//h.put("status_keputusan", rs.getString("status_keputusan")==null?"-":rs.getString("status_keputusan"));
				h.put("no_rujmmk", rs.getString("no_rujmmk")==null?"-":rs.getString("no_rujmmk"));
				h.put("tujuan", rs.getString("tujuan")==null?"-":rs.getString("tujuan"));
				h.put("tarikh_masuk", rs.getDate("tarikh_masuk")==null?"-":Format.format(rs.getDate("tarikh_masuk")));
				h.put("latarbelakang", rs.getString("latarbelakang")==null?"-":rs.getString("latarbelakang"));
				h.put("asas_pertimbangan", rs.getString("asas_pertimbangan")==null?"-":rs.getString("asas_pertimbangan"));
				h.put("implikasi", rs.getString("implikasi")==null?"-":rs.getString("implikasi"));
				h.put("kesimpulan", rs.getString("kesimpulan")==null?"-":rs.getString("kesimpulan"));
				h.put("syor", rs.getString("syor")==null?"-":rs.getString("syor"));
				listKertas.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas
	

	//private static Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void add(Hashtable data) throws Exception
	  {
		
		
	    Db db = null;
	    String sql = "";
	    try
	    {
	      String tujuan = (String)data.get("tujuan");
	      String latarBelakang = (String)data.get("latarBelakang");
	      String implikasi = (String)data.get("implikasi");
	      String kesimpulan = (String)data.get("kesimpulan");
	      String asasPertimbangan = (String)data.get("asasPertimbangan");
	      String syor = (String)data.get("syor");
	      String id_permohonan = (String)data.get("id_permohonan");
	      //Date tarikhMasuk = (Date)formatter.parseObject((String)data.get("tarikh_masuk"));
	      String tarikhMasuk = (String)data.get("tarikh_masuk");
	      
	      String TL = "to_date('" + tarikhMasuk + "','dd/MM/yyyy')";
	      int idPermohonan = Integer.parseInt(id_permohonan);
	      
	      String flag_penyediaan = "1";
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_permohonan", idPermohonan);
	      r.add("flag_semak", flag_penyediaan);
	      r.add("tujuan", tujuan); 	
	      r.add("latarbelakang", latarBelakang);
	      r.add("implikasi", implikasi);
	      r.add("kesimpulan", kesimpulan);
	      r.add("asas_pertimbangan", asasPertimbangan);
	      r.add("syor", syor);
	      r.add("tarikh_masuk", r.unquote(TL));
	      //r.add("tarikh_masuk", r.unquote("sysdate")); for current date n time
	      
	      
	      sql = r.getSQLInsert("tblpptmmk");
	      stmt.executeUpdate(sql);
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close add
	
	public static void delete(int id) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_mmk", id);
	      sql = r.getSQLDelete("tblpptmmk");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close delete
	
	 public static void update_semakan(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		      int id_mmk = (Integer)data.get("id_mmk");
		      String tarikh_semak = (String)data.get("tarikh_semak");
			  String keputusan_semak = (String)data.get("keputusan_semak");
			  String ulasan = (String)data.get("ulasan");
		      
			  String TD = "to_date('" + tarikh_semak + "','dd/MM/yyyy')";
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_mmk", id_mmk);
			  r.add("tarikh_semak", r.unquote(TD));
			  r.add("flag_semak", keputusan_semak);
			  r.add("ulasan", ulasan);
		     
		      sql = r.getSQLUpdate("tblpptmmk");
		      stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close update
	
	 public static Hashtable getViewKertas(int id_mmk) throws Exception {
		    Db db = null;

		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      	r.add("id_mmk");
				r.add("no_rujmmk");
				r.add("tujuan");
				r.add("latarbelakang");
				r.add("asas_pertimbangan");
				r.add("implikasi");
				r.add("kesimpulan");
				r.add("syor");
				r.add("tarikh_mmk");
				r.add("tarikh_semak");
				r.add("flag_semak");
				r.add("ulasan");
			
				r.add("id_mmk", id_mmk);
				
		      sql = r.getSQLSelect("Tblpptmmk", "tarikh_mmk");
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      
		      while (rs.next()) {
		    	  
			        h.put("id_mmk", rs.getInt("id_mmk"));  
			        
			        h.put("tarikh_mmk", rs.getDate("tarikh_mmk")==null?"-":Format.format(rs.getDate("tarikh_mmk")));
					h.put("flag_semak", rs.getString("flag_semak")==null?"-":rs.getString("flag_semak"));
					h.put("ulasan", rs.getString("ulasan")==null?"-":rs.getString("ulasan"));
			        if(rs.getString("no_rujmmk") == null){
			        	h.put("no_rujmmk", "");
			        }else{
			        	h.put("no_rujmmk", rs.getString("no_rujmmk"));
			        }
			    /*  if(rs.getString("ulasan") == null){
			        	h.put("ulasan", "");
			        }else{
			        	h.put("ulasan", rs.getString("ulasan"));
			        }
			        if(rs.getString("flag_semak") == null){
			        	h.put("flag_semak", "");
			        }else{
			        	h.put("flag_semak", rs.getString("flag_semak"));
			        }*/
			        if(rs.getString("tujuan") == null){
			        	h.put("tujuan", "");
			        }else{
			        	h.put("tujuan", rs.getString("tujuan"));
			        }
			        
			        if(rs.getString("latarbelakang") == null){
			        	h.put("latarbelakang", "");
			        }else{
			        	h.put("latarbelakang", rs.getString("latarbelakang"));
			        }
			        
			        if(rs.getString("asas_pertimbangan") == null){
			        	h.put("asas_pertimbangan", "");
			        }else{
			        	h.put("asas_pertimbangan", rs.getString("asas_pertimbangan"));
			        }
			        
			        if(rs.getString("implikasi") == null){
			        	h.put("implikasi", "");
			        }else{
			        	h.put("implikasi", rs.getString("implikasi"));
			        }
			        
			        if(rs.getString("kesimpulan") == null){
			        	h.put("kesimpulan", "");
			        }else{
			        	h.put("kesimpulan", rs.getString("kesimpulan"));
			        }
			        
			        if(rs.getString("syor") == null){
			        	h.put("syor", "");
			        }else{
			        	h.put("syor", rs.getString("syor"));
			        }
			     /*   if(Format.format(rs.getDate("tarikh_masuk")) == null){
			        	h.put("tarikh_masuk", "");
			        }else{
			        	h.put("tarikh_masuk", Format.format(rs.getDate("tarikh_masuk")));
			        }
			     */   
			      }
			      return h;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			    
			 }//close getList
	 
	 public static void add_keputusan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    try
	    {
	      String id_mmk = (String)data.get("id_mmk");
	      String tarikh_hantar = (String)data.get("tarikh_hantar");
	      String tarikh_keputusan = (String)data.get("tarikh_keputusan");
	      String tarikh_terima = (String)data.get("tarikh_terima");
	      String ulasan = (String)data.get("ulasan");
	      String keputusan = (String)data.get("keputusan");
	      
	      String TA = "to_date('" + tarikh_hantar + "','dd/MM/yyyy')";
	      String TB = "to_date('" + tarikh_keputusan + "','dd/MM/yyyy')";
	      String TC = "to_date('" + tarikh_terima + "','dd/MM/yyyy')";
	      
	      int idMMK = Integer.parseInt(id_mmk);
	      int int_keputusan = Integer.parseInt(keputusan);
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_mmk", idMMK);
	      r.add("status_keputusan", int_keputusan); 	
	      r.add("ulasan", ulasan);
	      r.add("tarikh_hantar", r.unquote(TA));
	      r.add("tarikh_keputusan", r.unquote(TB));
	      r.add("tarikh_terima", r.unquote(TC));
	      
	      //r.add("tarikh_masuk", r.unquote("sysdate")); for current date n time
	      
	      sql = r.getSQLInsert("tblpptmmkkeputusan");
	      stmt.executeUpdate(sql);
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close add_keputusan
	 
}//close class
