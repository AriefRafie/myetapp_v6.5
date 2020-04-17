package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraSenaraiTandaKwsnData {

	Vector listTandaKawasan = null;
	Vector listCarianFail = null;
	Vector paparTandaKawasan = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");

	
	public void  setCarianFail(String noFail,String tarikh,String status)throws Exception {
		Db db = null;
	    String sql = "";
	    String chkData = noFail.trim();
	    
	    try {
	      listCarianFail = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      // 
	      sql = "SELECT distinct s.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, c.nama_negeri, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, f.no_fail, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f, Tblrujsuburusan su, Tblrujnegeri c, Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND c.id_negeri = p.id_negeri AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status AND s.kod_status NOT IN ('27','32') AND s.id_seksyen = 1  AND f.id_suburusan = 53";
	      
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
	    	  listCarianFail.addElement(h);
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
	    	  listCarianFail.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	
	public static void deleteTanda(String id_tandakawasan) throws Exception {
		// delete row berdasarkan idNotisAwam
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmtQ = db.getStatement();
	      SQLRenderer rQ = new SQLRenderer();
	      rQ.add("id_tandakawasan", id_tandakawasan);
	      sql = rQ.getSQLDelete("tblppttandakawasan");	      
	      stmtQ.executeUpdate(sql);
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
		
		
	}

	public static void updateTanda(Hashtable data) throws Exception{
		// update table Tanda Kwsn
		Db db = null;
		 String sql = "";
		 String id = "";
		 
		    try
		    {	
		    	String idTandaKawasan = (String)data.get("idTandaKawasan");
		    	String idPermohonan = (String)data.get("idPermohonan");
		    	String StatusTanda = (String)data.get("StatusTanda");
		    	String StatusLaksana = (String)data.get("StatusLaksana");
		    	String TarikhTandaDari = (String)data.get("TarikhTandaDari");
		    	String tkhTandaDari = "to_date('" + TarikhTandaDari + "','dd/MM/yyyy')";
	    	 	String TarikhTandaHingga = (String)data.get("TarikhTandaHingga");
	    	 	String tkhTandaHingga = "to_date('" + TarikhTandaHingga + "','dd/MM/yyyy')";
	    	 	String TarikhLawatPeriksa = (String)data.get("TarikhLawatPeriksa");
	    	 	String tkhLawatPeriksa = "to_date('" + TarikhLawatPeriksa + "','dd/MM/yyyy')";
	    	 	String TarikhLulus = (String)data.get("TarikhLulus");
	    	 	String tkhLulus = "to_date('" + TarikhLulus + "','dd/MM/yyyy')";
	    	 	String rujAgensi = (String)data.get("rujAgensi");
	    	 	String namaPegawai = (String)data.get("namaPegawai");
	    	 	String Alamat1 = (String)data.get("Alamat1");
	    	 	String Alamat2 = (String)data.get("Alamat2");
	    	 	String Alamat3 = (String)data.get("Alamat3");
	    	 	String Poskod = (String)data.get("Poskod");
	    	 	String Negeri = (String)data.get("Negeri");
	    	 	String Bandar = (String)data.get("Bandar");
		    	String idKemaskini = (String)data.get("idKemaskini");
		    	
		    	 db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.update("id_tandakawasan",idTandaKawasan);
			     r.add("id_permohonan", idPermohonan);
			     r.add("flag_Tanda", StatusTanda);
			     r.add("cara_Laksana", StatusLaksana);
			     r.add("tarikh_mula", r.unquote(tkhTandaDari));
			     r.add("tarikh_akhir", r.unquote(tkhTandaHingga));
			     r.add("tarikh_lawat", r.unquote(tkhLawatPeriksa));
			     r.add("tarikh_lulus",r.unquote(tkhLulus));
			     r.add("no_rujagensi", rujAgensi);
			     r.add("nama_pegawai", namaPegawai);
			     r.add("alamat1_juruukur", Alamat1);
			     r.add("alamat2_juruukur", Alamat2);
			     r.add("alamat3_juruukur", Alamat3);
			     r.add("poskod", Poskod);
			     r.add("id_negeri", Negeri);
			     r.add("id_bandar", Bandar);
			     r.add("id_Kemaskini",idKemaskini);
			     r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			     			     
			     sql = r.getSQLUpdate("tblppttandakawasan");
			     stmt.executeUpdate(sql);
			     
			     
			     id = ""+ idTandaKawasan;
			    
			      
		    }finally {
			      if (db != null) db.close();
		    }
		
	}

	public static void addTanda(Hashtable data) throws Exception {
		// Masukan data dalam table - PTG
		Db db = null;
		 String sql = "";
		 
		    try
		    {
		    	long idTandaKawasan = DB.getNextID("TBLPPTTANDAKAWASAN_SEQ");
		    	String idPermohonan = (String)data.get("idPermohonan");
		    	String StatusTanda = (String)data.get("StatusTanda");
		    	String StatusLaksana = (String)data.get("StatusLaksana");
		    	String TarikhTandaDari = (String)data.get("TarikhTandaDari");
		    	String tkhTandaDari = "to_date('" + TarikhTandaDari + "','dd/MM/yyyy')";
	    	 	String TarikhTandaHingga = (String)data.get("TarikhTandaHingga");
	    	 	String tkhTandaHingga = "to_date('" + TarikhTandaHingga + "','dd/MM/yyyy')";
	    	 	String TarikhLawatPeriksa = (String)data.get("TarikhLawatPeriksa");
	    	 	String tkhLawatPeriksa = "to_date('" + TarikhLawatPeriksa + "','dd/MM/yyyy')";
	    	 	String TarikhLulus = (String)data.get("TarikhLulus");
	    	 	String tkhLulus = "to_date('" + TarikhLulus + "','dd/MM/yyyy')";
	    	 	String rujAgensi = (String)data.get("rujAgensi");
	    	 	String namaPegawai = (String)data.get("namaPegawai");
	    	 	String Alamat1 = (String)data.get("Alamat1");
	    	 	String Alamat2 = (String)data.get("Alamat2");
	    	 	String Alamat3 = (String)data.get("Alamat3");
	    	 	String Poskod = (String)data.get("Poskod");
	    	 	String Negeri = (String)data.get("Negeri");
	    	 	String Bandar = (String)data.get("Bandar");
		        String idMasuk = (String)data.get("idMasuk");	
		    	 
			     db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.add("id_tandakawasan",idTandaKawasan);
			     r.add("id_permohonan", idPermohonan);
			     r.add("flag_Tanda", StatusTanda);
			     r.add("cara_Laksana", StatusLaksana);
			     r.add("tarikh_mula", r.unquote(tkhTandaDari));
			     r.add("tarikh_akhir", r.unquote(tkhTandaHingga));
			     r.add("tarikh_lawat", r.unquote(tkhLawatPeriksa));
			     r.add("tarikh_lulus",r.unquote(tkhLulus));
			     r.add("no_rujagensi", rujAgensi);
			     r.add("nama_pegawai", namaPegawai);
			     r.add("alamat1_juruukur",Alamat1);
			     r.add("alamat2_juruukur", Alamat2);
			     r.add("alamat3_juruukur", Alamat3);
			     r.add("poskod", Poskod);
			     r.add("id_negeri", Negeri);
			     r.add("id_bandar", Bandar);
			     r.add("id_Masuk",idMasuk);
			     r.add("tarikh_Masuk",r.unquote("sysdate"));
			     sql = r.getSQLInsert("tblppttandakawasan");
			     stmt.executeUpdate(sql);
			     
			     Statement stmtQ = db.getStatement();
			     SQLRenderer rQ = new SQLRenderer();
			     rQ.update("id_permohonan", idPermohonan);
			     rQ.add("id_status", "38");
			     sql = rQ.getSQLUpdate("tblpptpermohonan");
			     stmtQ.executeUpdate(sql);
			     
			    
			    
			      
		    }finally {
			      if (db != null) db.close();
		    }
		    
		    
	}
	


	public void listTandaKwsn(String idPermohonan) throws Exception {
		
		//listkan tanda kawasan
		Db db = null;
	    String sql = "";
	    String chkData = idPermohonan.trim();
	    
	    try {
	      listTandaKawasan = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT T.NO_RUJAGENSI, T.NAMA_PEGAWAI, T.FLAG_TANDA, T.CARA_LAKSANA, T.TARIKH_MULA, T.TARIKH_AKHIR, T.TARIKH_LAWAT,T.ID_TANDAKAWASAN FROM TBLPPTTANDAKAWASAN T, TBLPPTPERMOHONAN P "+
	      		"WHERE P.ID_PERMOHONAN = T.ID_PERMOHONAN AND T.ID_PERMOHONAN = '"+idPermohonan+"'";


	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_tandakawasan", rs.getString("ID_TANDAKAWASAN"));
	    	  h.put("norujagensi", rs.getString("NO_RUJAGENSI"));
	    	  h.put("namapegawai", rs.getString("NAMA_PEGAWAI"));
	    	  
	    	  if(rs.getString("FLAG_TANDA").equals("1")){
	    		  h.put("statustanda","DIBUAT");
	    	  }
	    	  else if(rs.getString("FLAG_TANDA").equals("2")){
	    		  h.put("statustanda","TIDAK DIBUAT");
	    	  }
	    	  
	    	  if(rs.getString("CARA_LAKSANA").equals("1")){
	    		  h.put("statuslaksana","DILAKSANA SENDIRI");
	    	  }
	    	  else if(rs.getString("CARA_LAKSANA").equals("2")){
	    		  h.put("statuslaksana","BANTUAN AGENSI");
	    	  }
	    	
	       	  h.put("tarikhmula", rs.getDate("TARIKH_MULA")==null?"":Format.format(rs.getDate("TARIKH_MULA")));
	       	  h.put("tarikhakhir", rs.getDate("TARIKH_AKHIR")==null?"":Format.format(rs.getDate("TARIKH_AKHIR")));
	       	  h.put("tarikhlawat", rs.getDate("TARIKH_LAWAT")==null?"":Format.format(rs.getDate("TARIKH_LAWAT")));
	       	  bil++;
	    	  count++;
	    	  listTandaKawasan.addElement(h);
	    	
	      }
	      
	      if(count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("norujagensi", "Tiada rekod.");
	    	  h.put("namapegawai", "");
	    	  h.put("statustanda", "");
	    	  h.put("statuslaksana", "");
	       	  h.put("tarikhmula", "");
	       	  h.put("tarikhakhir", "");
	       	  h.put("tarikhlawat", "");
	       	  listTandaKawasan.addElement(h);
	      }
	    } finally {
	      if (db != null) db.close();
	    }
	}

	public void paparTandaKwsn(String id_TandaKawasan) throws Exception {
		//papar Tanda kwsn
		Db db = null;
	    String sql = "";
	    
	    try {
	      paparTandaKawasan = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT NO_RUJAGENSI, NAMA_PEGAWAI, FLAG_TANDA, CARA_LAKSANA, TARIKH_MULA, TARIKH_AKHIR, TARIKH_LAWAT,TARIKH_LULUS, ALAMAT1_JURUUKUR, ALAMAT2_JURUUKUR, ALAMAT3_JURUUKUR, POSKOD, ID_NEGERI, ID_BANDAR,ID_TANDAKAWASAN FROM TBLPPTTANDAKAWASAN "+

    		" WHERE ID_TANDAKAWASAN = '"+id_TandaKawasan+"'";
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	     
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("norujagensi", rs.getString("NO_RUJAGENSI"));
	    	  h.put("namapegawai", rs.getString("NAMA_PEGAWAI"));
	    	  h.put("statustanda", rs.getString("FLAG_TANDA"));
	    	  h.put("statuslaksana", rs.getString("CARA_LAKSANA"));
	       	  h.put("tarikhmula", rs.getString("TARIKH_MULA")==null?"":Format.format(rs.getDate("TARIKH_MULA")));
	       	  h.put("tarikhakhir", rs.getString("TARIKH_AKHIR")==null?"":Format.format(rs.getDate("TARIKH_AKHIR")));
	       	  h.put("tarikhlawat", rs.getString("TARIKH_LAWAT")==null?"":Format.format(rs.getDate("TARIKH_LAWAT")));
	       	  h.put("tarikhlulus", rs.getString("TARIKH_LULUS")==null?"":Format.format(rs.getDate("TARIKH_LULUS")));
	       	  h.put("alamat1", rs.getString("ALAMAT1_JURUUKUR")==null?"":rs.getString("ALAMAT1_JURUUKUR"));
	       	  h.put("alamat2", rs.getString("ALAMAT2_JURUUKUR")==null?"":rs.getString("ALAMAT2_JURUUKUR"));
	       	  h.put("alamat3", rs.getString("ALAMAT3_JURUUKUR")==null?"":rs.getString("ALAMAT3_JURUUKUR"));
	       	  h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
	       	  h.put("idnegeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
	       	  h.put("idbandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
	       	  h.put("id_tandakawasan", rs.getString("ID_TANDAKAWASAN")==null?"":rs.getString("ID_TANDAKAWASAN"));


	       	  paparTandaKawasan.addElement(h);
	    	 
	      }
	      
	    
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
		
	public Vector getListCarianFail(){
		return listCarianFail;
	}
	public Vector getListTandaKawasan(){
		return listTandaKawasan;
	}
	public Vector getPaparTandaKawasan(){
		return paparTandaKawasan;
	}
	




	
	
}
