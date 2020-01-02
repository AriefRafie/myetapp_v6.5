package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraSetPerundinganData {
	
	Vector listRundingan = null;
	Vector paparSet = null;
	Vector listHakmilik = null;
	Vector listPb = null;
	Vector dataPb = null;
	Vector paparPb = null;
	Vector listTurutHadir = null;
	Vector paparTurutHadir = null;
	static Vector getStatus = null;
	static Vector idStatus = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setRundingan (String idPermohonan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
			db = new Db();
			listRundingan = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_SIASATAN,A.NO_KES,A.NO_SIASATAN,A.TARIKH_SIASATAN,A.TEMPAT,A.STATUS_SIASATAN,C.NO_LOT"+

				  " FROM TBLPPTSIASATAN A,"+
				  " TBLPPTPERMOHONAN B," +
				  " TBLPPTHAKMILIK C"+
     
				  " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN" +
				  " AND C.ID_HAKMILIK = A.ID_HAKMILIK"+
				  " AND A.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			  ResultSet rs = stmt.executeQuery(sql);  
			  
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("ID_SIASATAN",rs.getString("ID_SIASATAN")== null?"":rs.getString("ID_SIASATAN"));
		    	  h.put("NO_KES", rs.getString("NO_KES")== null?"":rs.getString("NO_KES"));
		    	  h.put("NO_SIASATAN", rs.getString("NO_SIASATAN")== null?"":rs.getString("NO_SIASATAN"));
		    	  h.put("TARIKH_SIASATAN", rs.getString("TARIKH_SIASATAN")== null?"":sdf.format(rs.getDate("TARIKH_SIASATAN")));
		    	  h.put("TEMPAT", rs.getString("TEMPAT")== null?"":rs.getString("TEMPAT"));
		    	  h.put("NO_LOT", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));

		    	  if(rs.getString("STATUS_SIASATAN").equals("1")){
		    		  h.put("STATUS_SIASATAN","DALAM PROSES");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("2")){
		    		  h.put("STATUS_SIASATAN","DITUNDA SEBELUM SIASATAN");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("3")){
		    		  h.put("STATUS_SIASATAN","DIBATALKAN");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("4")){
		    		  h.put("STATUS_SIASATAN","ULANG SIASATAN");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("5")){
		    		  h.put("STATUS_SIASATAN","SAMBUNG SIASATAN");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("6")){
		    		  h.put("STATUS_SIASATAN","SELESAI");
		    	  }
		    	  else{
		    		  h.put("STATUS_SIASATAN","");
		    	  }
		    	  bil++;
		    	  count++;
		    	  listRundingan.addElement(h);
		      }
		      
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	  h.put("ID_SIASATAN", "");
		    	  h.put("NO_KES","Tiada rekod.");
		    	  h.put("NO_SIASATAN", "");
		    	  h.put("TARIKH_SIASATAN", "");
		    	  h.put("TEMPAT", "");
		    	  h.put("NO_LOT", "");
		    	  h.put("STATUS_SIASATAN", "");
		    	  listRundingan.addElement(h); 
		      }
		}
		finally {
		      if (db != null) db.close();
		    }
		
	}
	public static String addSet(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		
		 
		    try
		    {
		    	long idSiasatan = DB.getNextID("TBLPPTSIASATAN_SEQ");
		    	String idPermohonan= (String)data.get("idPermohonan");
		    	String idHakmilik = (String)data.get("idHakmilik");
		    	String bilRundingan= (String)data.get("bilRundingan");
		    	String bilRundinganSblm= (String)data.get("bilRundinganSblm");
		    	String noPerundingan= (String)data.get("noPerundingan");
		    	String pegawaiPerundingan = (String)data.get("pegawaiPerundingan");
		    	String alamat1= (String)data.get("alamat1");
		    	String alamat2= (String)data.get("alamat2");
		    	String alamat3= (String)data.get("alamat3");
		    	String poskod= (String)data.get("poskod");
		    	String negeri= (String)data.get("negeri");
		    	String bandar= (String)data.get("bandar");
		    	String statusRundingan= (String)data.get("statusRundingan");
		    	String tarikhRundingan= (String)data.get("tarikhRundingan");
		    	String tkhRundingan= "to_date('" + tarikhRundingan + "','dd/MM/yyyy')";
		    	String masaRundingan= (String)data.get("masaRundingan");
		    	String waktuRundingan= (String)data.get("waktuRundingan");
		    	String tempat= (String)data.get("tempat");
		    	String tarikhAkhirNotis= (String)data.get("tarikhAkhirNotis");
		    	String tkhAkhirNotis= "to_date('" + tarikhAkhirNotis + "','dd/MM/yyyy')";
		    	String alasan= (String)data.get("alasan");
		    	String idMasuk= (String)data.get("idMasuk");
		    
		    	db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    
			    r.add("id_Siasatan",idSiasatan);
			    r.add("id_Permohonan",idPermohonan);
			    r.add("id_Hakmilik",idHakmilik);
			    r.add("no_Kes",bilRundingan);
			    r.add("no_Kes_Sebelum",bilRundinganSblm);
			    r.add("no_Siasatan",noPerundingan);
			    r.add("id_Pegawai_Siasatan",pegawaiPerundingan);
			    r.add("alamat1",alamat1);
			    r.add("alamat2",alamat2);
			    r.add("alamat3",alamat3);
			    r.add("poskod",poskod);
			    r.add("id_Negeri",negeri);
			    r.add("id_Bandar",bandar);
			    r.add("status_Siasatan",statusRundingan);
			    r.add("tarikh_Siasatan",r.unquote(tkhRundingan));
			    r.add("masa_Siasatan",masaRundingan);
			    r.add("jenis_Waktu_Siasatan",waktuRundingan);
			    r.add("tempat",tempat);
			    r.add("tarikh_Akhir_Tampal",r.unquote(tkhAkhirNotis));
			    r.add("alasan_Tangguh",alasan);
			    r.add("id_Masuk",idMasuk);
			    r.add("tarikh_Masuk",r.unquote("sysdate"));
			    
			    sql = r.getSQLInsert("tblpptsiasatan");
			    stmt.executeUpdate(sql);
			    
//			    idStatus = new Vector();
//			    idStatus = getStatus(idPermohonan);
//			    Hashtable hS = (Hashtable)idStatus.get(0);
			    
			    
			    idStatus = new Vector();
			    idStatus = getStatus(idPermohonan);
			    Hashtable hS = (Hashtable)idStatus.get(0);
			    
				Statement stmtQ = db.getStatement();
			    SQLRenderer rQ = new SQLRenderer();
				rQ.update("ID_PERMOHONAN", idPermohonan);
				rQ.add("ID_STATUS",hS.get("ID_STATUS"));
				sql = rQ.getSQLUpdate("TBLPPTPERMOHONAN");	
				stmtQ.executeUpdate(sql);
			  
			    return ""+idSiasatan;
			}
		    finally {
			      if (db != null) db.close();
		    }
	}
	
	public static Vector getStatus(String idPermohonan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			 getStatus = new Vector();
			 db = new Db();
			 Statement stmtS = db.getStatement();

			    sql = "SELECT ID_STATUS FROM TBLRUJSTATUS WHERE KOD_STATUS = '44' AND ID_SEKSYEN = 1";
			    ResultSet rs = stmtS.executeQuery(sql);  
				  
			    Hashtable h;
			    while(rs.next()){
			    	h = new Hashtable();
			    	h.put("ID_STATUS",rs.getString("ID_STATUS")); 
				    getStatus.addElement(h);
			    	
			    }
			return getStatus;
		}
		finally {
		      if (db != null) db.close();
	    }
		
		
	}
	public static void updateSet(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		
		 
		    try
		    {
		    	String idSiasatan = (String)data.get("idSiasatan");
		    	String bilRundingan= (String)data.get("bilRundingan");
		    	String bilRundinganSblm= (String)data.get("bilRundinganSblm");
		    	String noPerundingan= (String)data.get("noPerundingan");
		    	String pegawaiPerundingan = (String)data.get("pegawaiPerundingan");
		    	String alamat1= (String)data.get("alamat1");
		    	String alamat2= (String)data.get("alamat2");
		    	String alamat3= (String)data.get("alamat3");
		    	String poskod= (String)data.get("poskod");
		    	String negeri= (String)data.get("negeri");
		    	String bandar= (String)data.get("bandar");
		    	String statusRundingan= (String)data.get("statusRundingan");
		    	String tarikhRundingan= (String)data.get("tarikhRundingan");
		    	String tkhRundingan= "to_date('" + tarikhRundingan + "','dd/MM/yyyy')";
		    	String masaRundingan= (String)data.get("masaRundingan");
		    	String waktuRundingan= (String)data.get("waktuRundingan");
		    	String tempat= (String)data.get("tempat");
		    	String tarikhAkhirNotis= (String)data.get("tarikhAkhirNotis");
		    	String tkhAkhirNotis= "to_date('" + tarikhAkhirNotis + "','dd/MM/yyyy')";
		    	String alasan= (String)data.get("alasan");
		    	String idKemaskini= (String)data.get("idKemaskini");
		    
		    	db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    
			    r.update("id_Siasatan",idSiasatan);
			    r.add("no_Kes",bilRundingan);
			    r.add("no_Kes_Sebelum",bilRundinganSblm);
			    r.add("no_Siasatan",noPerundingan);
			    r.add("id_Pegawai_Siasatan",pegawaiPerundingan);
			    r.add("alamat1",alamat1);
			    r.add("alamat2",alamat2);
			    r.add("alamat3",alamat3);
			    r.add("poskod",poskod);
			    r.add("id_Negeri",negeri);
			    r.add("id_Bandar",bandar);
			    r.add("status_Siasatan",statusRundingan);
			    r.add("tarikh_Siasatan",r.unquote(tkhRundingan));
			    r.add("masa_Siasatan",masaRundingan);
			    r.add("jenis_Waktu_Siasatan",waktuRundingan);
			    r.add("tempat",tempat);
			    r.add("tarikh_Akhir_Tampal",r.unquote(tkhAkhirNotis));
			    r.add("alasan_Tangguh",alasan);
			    r.add("id_Kemaskini",idKemaskini);
			    r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			    
			    sql = r.getSQLUpdate("tblpptsiasatan");		
			    stmt.executeUpdate(sql);
			  
			}
		    finally {
			      if (db != null) db.close();
		    }
	}
	public void setDataRundingan (String idSiasatan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
			db = new Db();
			paparSet = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.NO_KES,A.NO_KES_SEBELUM,A.NO_SIASATAN,A.ALAMAT1,A.ALAMAT2,A.ALAMAT3,A.POSKOD,A.ID_NEGERI,"+
				  " A.ID_BANDAR,A.STATUS_SIASATAN,A.TARIKH_SIASATAN,A.TEMPAT,A.TARIKH_AKHIR_TAMPAL,A.ALASAN_TANGGUH,A.MASA_SIASATAN,A.JENIS_WAKTU_SIASATAN," +
				  " A.ID_HAKMILIK, A.ID_PEGAWAI_SIASATAN"+

				  " FROM TBLPPTSIASATAN A"+
				  " WHERE A.ID_SIASATAN = '" +idSiasatan+"'";
			
			ResultSet rs = stmt.executeQuery(sql);  
		    Hashtable h;
		    while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NO_KES",rs.getString("NO_KES")==null?"":rs.getString("NO_KES"));
		    	  h.put("NO_KES_SEBELUM",rs.getString("NO_KES_SEBELUM")==null?"":rs.getString("NO_KES_SEBELUM"));
		    	  h.put("NO_SIASATAN",rs.getString("NO_SIASATAN")==null?"":rs.getString("NO_SIASATAN"));
		    	  h.put("ALAMAT1",rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
		    	  h.put("ALAMAT2",rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
		    	  h.put("ALAMAT3",rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	  h.put("POSKOD",rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
		    	  h.put("ID_BANDAR",rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
		    	  h.put("STATUS_SIASATAN",rs.getString("STATUS_SIASATAN")==null?"":rs.getString("STATUS_SIASATAN"));
		    	  h.put("TARIKH_SIASATAN",rs.getString("TARIKH_SIASATAN")==null?"":sdf.format(rs.getDate("TARIKH_SIASATAN")));
		    	  h.put("TEMPAT",rs.getString("TEMPAT")==null?"":rs.getString("TEMPAT"));
		    	  h.put("TARIKH_AKHIR_TAMPAL",rs.getString("TARIKH_AKHIR_TAMPAL")==null?"":sdf.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
		    	  h.put("ALASAN_TANGGUH",rs.getString("ALASAN_TANGGUH")==null?"":rs.getString("ALASAN_TANGGUH"));
		    	  h.put("MASA_SIASATAN",rs.getString("MASA_SIASATAN")==null?"":rs.getString("MASA_SIASATAN"));
		    	  h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("JENIS_WAKTU_SIASATAN",rs.getString("JENIS_WAKTU_SIASATAN")==null?"":rs.getString("JENIS_WAKTU_SIASATAN"));
		    	  h.put("ID_PEGAWAI_SIASATAN",rs.getString("ID_PEGAWAI_SIASATAN")==null?"0":rs.getString("ID_PEGAWAI_SIASATAN"));

		    	  paparSet.addElement(h);
		    }
			
		}
		 finally {
		      if (db != null) db.close();
	    }
	}
	public static void hapusSet(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      
	      //DELETE FROM TBLPPTKEHADIRAN
	      Statement stmtK = db.getStatement();
	      SQLRenderer rK = new SQLRenderer();
	      rK.add("id_Siasatan", id);
	      sql = rK.getSQLDelete("tblpptkehadiran");	      
	      stmtK.executeUpdate(sql);
	      
	      //DELETE FROMT TBLPPTSIASATAN
	      
	      Statement stmtQ = db.getStatement();
	      SQLRenderer rQ = new SQLRenderer();
	      rQ.add("id_Siasatan", id);
	      sql = rQ.getSQLDelete("tblpptsiasatan");	      
	      stmtQ.executeUpdate(sql);
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close delete

	public void  setSenaraiHakmilik(String idFail,String idPermohonan)throws Exception {
		Db db = null;
	    String sql = "";
	    
	    try {
	    	listHakmilik = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT  B.ID_HAKMILIK,B.NO_LOT, B.NO_PT, B.NO_HAKMILIK, B.LUAS_LOT, B.LUAS_AMBIL, C.NAMA_DAERAH, D.NAMA_MUKIM " +
		      		" FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLRUJDAERAH C, TBLRUJMUKIM D"+
		      		" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN" +
		      		" AND C.ID_DAERAH = B.ID_DAERAH" +
		      		" AND D.ID_MUKIM = B.ID_MUKIM" +
		      		" AND A.ID_PERMOHONAN = '" + idPermohonan + "' " +
		      		" AND A.ID_FAIL = '" + idFail + "'";
		      
		      ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("BIL", bil);
		    	  if (rs.getString("NO_PT") != null){
			    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT") + "/" + rs.getString("NO_LOT"));
 
		    	  }
		    	  else{
		    		  h.put("NO_LOT",rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
		    	  }
		    	  h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("NO_PT",rs.getString("NO_PT")==null?"":rs.getString("NO_PT"));
		    	  h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
		    	  h.put("LUAS_LOT",rs.getString("LUAS_LOT")==null?"":rs.getString("LUAS_LOT"));
		    	  h.put("LUAS_AMBIL",rs.getString("LUAS_AMBIL")==null?"":rs.getString("LUAS_AMBIL"));
		    	  h.put("NAMA_DAERAH",rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
		    	  h.put("NAMA_MUKIM",rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
		    	  listHakmilik.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("BIL", "");
		    	  h.put("NO_LOT","Tiada rekod.");
		    	  h.put("NO_PT","");
		    	  h.put("NO_HAKMILIK","");
		    	  h.put("LUAS_LOT","");
		    	  h.put("LUAS_AMBIL","");
		    	  h.put("NAMA_DAERAH","");
		    	  h.put("NAMA_MUKIM","");
		    	  listHakmilik.addElement(h);
		      }
		      		

		      
	    }finally {
	      if (db != null) db.close();
	    }
	}
	public void setListPB (String idHakmilik)throws Exception{
		Db db = null;
	    String sql = "";
	    
	    try{
	    	listPb = new Vector();
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT E.ID_KEHADIRAN,C.ID_HAKMILIKPB,A.ID_PIHAKBERKEPENTINGAN,A.NO_PB,A.NAMA_PB,B.NO_LOT,D.KETERANGAN AS JENIS_PB,E.FLAG_HADIR"+ 

		    	" FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
     			" TBLPPTHAKMILIK B,"+
     			" TBLPPTHAKMILIKPB C,"+
     			" TBLRUJJENISPB D," +
     			" TBLPPTKEHADIRAN E"+

     			" WHERE A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN"+
				" AND B.ID_HAKMILIK = C.ID_HAKMILIK"+
				" AND D.ID_JENISPB = A.ID_JENISPB" +
				" AND C.ID_HAKMILIKPB = E.ID_HAKMILIKPB " +
				" AND D.KOD_JENIS_PB NOT IN('WJ','PG','PS')"+
				" AND C.ID_HAKMILIK = '"+idHakmilik+"'";
		    ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("BIL", bil);
		    	  h.put("ID_PIHAKBERKEPENTINGAN",rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
		    	  h.put("JENIS_PB",rs.getString("JENIS_PB")==null?"":rs.getString("JENIS_PB"));
		    	  h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	  h.put("ID_KEHADIRAN",rs.getString("ID_KEHADIRAN")==null?"":rs.getString("ID_KEHADIRAN"));

		    	  if(rs.getString("FLAG_HADIR").equals("1")){
		    		  h.put("FLAG_HADIR","HADIR");
		    		  
		    	  }
		    	  else if(rs.getString("FLAG_HADIR").equals("2")){
		    		  h.put("FLAG_HADIR","TIDAK HADIR");
		    	  }
		    	  else{
		    		  h.put("FLAG_HADIR","");
		    	  }
		    	  

		    	  bil++;
		    	  count++;
		    	  listPb.addElement(h);
		      }
		      if(count == 0){ 
		    	  h = new Hashtable();
		    	  h.put("BIL", "");
		    	  h.put("ID_PIHAKBERKEPENTINGAN","");
		    	  h.put("NO_PB","");
		    	  h.put("NAMA_PB","Tiada rekod.");
		    	  h.put("NO_LOT","");
		    	  h.put("JENIS_PB","");
		    	  h.put("ID_HAKMILIKPB","");
		    	  h.put("ID_KEHADIRAN","");
		    	  h.put("FLAG_HADIR","");
		    	  listPb.addElement(h);
		    	  
		      }
		      
	    }
	    finally {
		      if (db != null) db.close();
		    }
		
		
	}
	public void setDataPB (String idHakmilikPB)throws Exception{
		Db db = null;
	    String sql = "";
	    
	    try{
	    	dataPb = new Vector();
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT A.NO_PB,B.NO_LOT,B.NO_PT,D.KETERANGAN AS JENIS_PB,C.ALAMAT1, C.ALAMAT2, C.ALAMAT3,C.POSKOD,C.ID_NEGERI,C.ID_BANDAR,E.KETERANGAN AS LOT," +
		    		" C.NO_HANDPHONE,C.NO_TEL_RUMAH"+ 

		    	" FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
     			" TBLPPTHAKMILIK B,"+
     			" TBLPPTHAKMILIKPB C,"+
     			" TBLRUJJENISPB D," +
     			" TBLRUJLOT E" +
     			

     			" WHERE A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN"+
				" AND B.ID_HAKMILIK = C.ID_HAKMILIK"+
				" AND D.ID_JENISPB = A.ID_JENISPB" +
				" AND E.ID_LOT = B.ID_LOT" +
				" AND C.ID_HAKMILIKPB = '"+idHakmilikPB+"'";
		    ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"-":rs.getString("NO_LOT"));
		    	  h.put("NO_PT",rs.getString("NO_PT")==null?"-":rs.getString("NO_PT"));
		    	  h.put("JENIS_PB",rs.getString("JENIS_PB")==null?"":rs.getString("JENIS_PB"));
		    	  h.put("ALAMAT1",rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
		    	  h.put("ALAMAT2",rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
		    	  h.put("ALAMAT3",rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	  h.put("POSKOD",rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
		    	  h.put("ID_BANDAR",rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
		    	  h.put("LOT",rs.getString("LOT")==null?"":rs.getString("LOT"));
		    	  h.put("NO_HANDPHONE",rs.getString("NO_HANDPHONE")==null?"":rs.getString("NO_HANDPHONE"));
		    	  h.put("NO_TEL_RUMAH",rs.getString("NO_TEL_RUMAH")==null?"":rs.getString("NO_TEL_RUMAH"));

		    	  dataPb.addElement(h);
		      }
		      
	    }
	    finally {
		      if (db != null) db.close();
		    }
		
		
	}
	public static String addPb(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		
		 
		    try
		    {
		    	long idKehadiran = DB.getNextID("TBLPPTKEHADIRAN_SEQ");
		    	String idHakmilikPb= (String)data.get("idHakmilikPb");
		    	String flagHadir= (String)data.get("flagHadir");
		    	String namaBank= (String)data.get("namaBank");
		    	String noAkaun= (String)data.get("noAkaun");
		    	String idMasuk= (String)data.get("idMasuk");
		    	String idSiasatan = (String)data.get("idSiasatan");
		    
		    	db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    
			    r.add("id_Kehadiran",idKehadiran);
			    r.add("id_Siasatan",idSiasatan);
			    r.add("id_Hakmilikpb",idHakmilikPb);
			    r.add("flag_Hadir",flagHadir);
			    r.add("id_Masuk",idMasuk);
			    r.add("tarikh_Masuk",r.unquote("sysdate"));
			    
			    sql = r.getSQLInsert("tblpptkehadiran");
			    stmt.executeUpdate(sql);
			    
			    Statement stmtH = db.getStatement();
			    SQLRenderer rH = new SQLRenderer();
			    rH.update("id_Hakmilikpb",idHakmilikPb);
			    rH.add("nama_Bank",namaBank);
			    rH.add("no_Akaun",noAkaun);
			    rH.add("id_Kemaskini",idMasuk);
			    rH.add("tarikh_Kemaskini",r.unquote("sysdate"));
			    sql = rH.getSQLUpdate("tblppthakmilikpb");
			    stmtH.executeUpdate(sql);
			    
			    
			    return ""+idKehadiran;
			}
		    finally {
			      if (db != null) db.close();
		    }
	}
	public static void updatePb(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		
		 
		    try
		    {
		    	String idHakmilikPb= (String)data.get("idHakmilikPb");
		    	String namaBank= (String)data.get("namaBank");
		    	String noAkaun= (String)data.get("noAkaun");
		    	String idKemaskini= (String)data.get("idKemaskini");
		    
		    	db = new Db();

			    Statement stmtH = db.getStatement();
			    SQLRenderer rH = new SQLRenderer();
			    rH.update("id_Hakmilikpb",idHakmilikPb);
			    rH.add("nama_Bank",namaBank);
			    rH.add("no_Akaun",noAkaun);
			    rH.add("id_Kemaskini",idKemaskini);
			    rH.add("tarikh_Kemaskini",rH.unquote("sysdate"));
			    sql = rH.getSQLUpdate("tblppthakmilikpb");
			    stmtH.executeUpdate(sql);
			   
			}
		    finally {
			      if (db != null) db.close();
		    }
	}
	public void setPaparPB (String idKehadiran)throws Exception{
		Db db = null;
	    String sql = "";
	    
	    try{
	    	paparPb = new Vector();
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT A.NO_PB,B.NO_LOT,B.NO_PT,D.KETERANGAN AS JENIS_PB,C.ALAMAT1, C.ALAMAT2, C.ALAMAT3,C.POSKOD,C.ID_NEGERI,C.ID_BANDAR,E.KETERANGAN AS LOT," +
		    		" C.NAMA_BANK,C.NO_AKAUN,F.FLAG_HADIR,F.ID_HAKMILIKPB,C.NO_HANDPHONE,C.NO_TEL_RUMAH"+ 

		    	" FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
     			" TBLPPTHAKMILIK B,"+
     			" TBLPPTHAKMILIKPB C,"+
     			" TBLRUJJENISPB D," +
     			" TBLRUJLOT E," +
     			" TBLPPTKEHADIRAN F" +
     			

     			" WHERE A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN"+
				" AND B.ID_HAKMILIK = C.ID_HAKMILIK"+
				" AND D.ID_JENISPB = A.ID_JENISPB" +
				" AND E.ID_LOT = B.ID_LOT" +
				" AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB" +
				" AND F.ID_KEHADIRAN= '"+idKehadiran+"'";
		    ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"-":rs.getString("NO_LOT"));
		    	  h.put("NO_PT",rs.getString("NO_PT")==null?"-":rs.getString("NO_PT"));
		    	  h.put("JENIS_PB",rs.getString("JENIS_PB")==null?"":rs.getString("JENIS_PB"));
		    	  h.put("ALAMAT1",rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
		    	  h.put("ALAMAT2",rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
		    	  h.put("ALAMAT3",rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	  h.put("POSKOD",rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
		    	  h.put("ID_BANDAR",rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
		    	  h.put("LOT",rs.getString("LOT")==null?"":rs.getString("LOT"));
		    	  h.put("NAMA_BANK",rs.getString("NAMA_BANK")==null?"":rs.getString("NAMA_BANK"));
		    	  h.put("NO_AKAUN",rs.getString("NO_AKAUN")==null?"":rs.getString("NO_AKAUN"));
		    	  h.put("FLAG_HADIR",rs.getString("FLAG_HADIR")==null?"":rs.getString("FLAG_HADIR"));
		    	  h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	  h.put("NO_HANDPHONE",rs.getString("NO_HANDPHONE")==null?"":rs.getString("NO_HANDPHONE"));
		    	  h.put("NO_TEL_RUMAH",rs.getString("NO_TEL_RUMAH")==null?"":rs.getString("NO_TEL_RUMAH"));

		    	  paparPb.addElement(h);
		      }
		      
	    }
	    finally {
		      if (db != null) db.close();
		    }
		
		
	}
	public static void hapusKehadiran(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      
	      //DELETE FROM TBLPPTKEHADIRAN
	      Statement stmtK = db.getStatement();
	      SQLRenderer rK = new SQLRenderer();
	      rK.add("id_Kehadiran", id);
	      sql = rK.getSQLDelete("tblpptkehadiran");	      
	      stmtK.executeUpdate(sql);
	      
	     
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public void setListTurutHadir (String idHakmilik)throws Exception{
		Db db = null;
	    String sql = "";
	    
	    try{
	    	listTurutHadir = new Vector();
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT E.ID_KEHADIRAN,C.ID_HAKMILIKPB,A.ID_PIHAKBERKEPENTINGAN,A.NO_PB,A.NAMA_PB,B.NO_LOT,D.KETERANGAN AS JENIS_PB,E.FLAG_HADIR"+ 

		    	" FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
     			" TBLPPTHAKMILIK B,"+
     			" TBLPPTHAKMILIKPB C,"+
     			" TBLRUJJENISPB D," +
     			" TBLPPTKEHADIRAN E"+

     			" WHERE A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN"+
				" AND B.ID_HAKMILIK = C.ID_HAKMILIK"+
				" AND D.ID_JENISPB = A.ID_JENISPB" +
				" AND D.KOD_JENIS_PB IN ('WJ','PG','PS')" +
				" AND C.ID_HAKMILIKPB = E.ID_HAKMILIKPB "+
				" AND C.ID_HAKMILIK = '"+idHakmilik+"'";
		    ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("BIL", bil);
		    	  h.put("ID_PIHAKBERKEPENTINGAN",rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
		    	  h.put("JENIS_PB",rs.getString("JENIS_PB")==null?"":rs.getString("JENIS_PB"));
		    	  h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	  h.put("ID_KEHADIRAN",rs.getString("ID_KEHADIRAN")==null?"":rs.getString("ID_KEHADIRAN"));

		    	  if(rs.getString("FLAG_HADIR").equals("1")){
		    		  h.put("FLAG_HADIR","HADIR");
		    		  
		    	  }
		    	  else if(rs.getString("FLAG_HADIR").equals("2")){
		    		  h.put("FLAG_HADIR","TIDAK HADIR");
		    	  }
		    	  else{
		    		  h.put("FLAG_HADIR","");
		    	  }
		    	  

		    	  bil++;
		    	  count++;
		    	  listTurutHadir.addElement(h);
		      }
		      if(count == 0){ 
		    	  h = new Hashtable();
		    	  h.put("BIL", "");
		    	  h.put("ID_PIHAKBERKEPENTINGAN","");
		    	  h.put("NO_PB","");
		    	  h.put("NAMA_PB","Tiada rekod.");
		    	  h.put("NO_LOT","");
		    	  h.put("JENIS_PB","");
		    	  h.put("ID_HAKMILIKPB","");
		    	  h.put("ID_KEHADIRAN","");
		    	  h.put("FLAG_HADIR","");
		    	  listTurutHadir.addElement(h);
		    	  
		      }
		      
	    }
	    finally {
		      if (db != null) db.close();
		    }
		
		
	}
	public static String addTurutHadir(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		
		 
		    try
		    {
		    	long idPihakBerkepentingan = DB.getNextID("TBLPPTPIHAKBERKEPENTINGAN_SEQ");
		    	long idHakmilikpb = DB.getNextID("TBLPPTHAKMILIKPB_SEQ");
		    	long idKehadiran = DB.getNextID("TBLPPTKEHADIRAN_SEQ");
		    	String idHakmilik = (String)data.get("idHakmilik");
		    	String idSiasatan = (String)data.get("idSiasatan");
		    	String namaPB = (String)data.get("namaPB");
		    	String kodNoPB= (String)data.get("kodNoPB");
		    	String kodJenisPB= (String)data.get("kodJenisPB");
		    	String noPB= (String)data.get("noPB");
		    	String alamat4= (String)data.get("alamat4");
		    	String alamat5= (String)data.get("alamat5");
		    	String alamat6= (String)data.get("alamat6");
		    	String poskod= (String)data.get("poskod");
		    	String negeri= (String)data.get("negeri");
		    	String bandar= (String)data.get("bandar");
		    	String noTelPjbt= (String)data.get("noTelPjbt");
		    	String noHp= (String)data.get("noHp");
		    	String noFaks= (String)data.get("noFaks");
		    	String idMasuk= (String)data.get("idMasuk");
		    	
		    	db = new Db();
			    Statement stmt1 = db.getStatement();
			    SQLRenderer r1 = new SQLRenderer();
			    
			    r1.add("id_Pihakberkepentingan",idPihakBerkepentingan);
			    r1.add("nama_Pb",namaPB);
			    r1.add("id_Jenisnopb",kodNoPB);
			    r1.add("id_Jenispb",kodJenisPB);
			    r1.add("no_Pb",noPB);
			    r1.add("id_Masuk",idMasuk);
			    r1.add("tarikh_Masuk",r1.unquote("sysdate"));
			    
			    sql = r1.getSQLInsert("tblpptpihakberkepentingan");
			    stmt1.executeUpdate(sql);
			    
			    Statement stmt2 = db.getStatement();
			    SQLRenderer r2 = new SQLRenderer();
			    
			    r2.add("id_Hakmilikpb",idHakmilikpb);
			    r2.add("id_Hakmilik",idHakmilik);
			    r2.add("id_Pihakberkepentingan",idPihakBerkepentingan);
			    r2.add("alamat1",alamat4);
			    r2.add("alamat2",alamat5);
			    r2.add("alamat3",alamat6);
			    r2.add("poskod",poskod);
			    r2.add("id_Negeri",negeri);
			    r2.add("id_Bandar",bandar);
			    r2.add("no_Tel_Rumah",noTelPjbt);
			    r2.add("no_Handphone",noHp);
			    r2.add("no_Fax",noFaks);
			    r2.add("id_Masuk",idMasuk);
			    r2.add("tarikh_Masuk",r2.unquote("sysdate"));
			    
			    sql = r2.getSQLInsert("tblppthakmilikpb");
			    stmt2.executeUpdate(sql);
			    
			    db = new Db();
			    Statement stmt3 = db.getStatement();
			    SQLRenderer r3 = new SQLRenderer();
			    
			    r3.add("id_Kehadiran",idKehadiran);
			    r3.add("id_Hakmilikpb",idHakmilikpb);
			    r3.add("id_Siasatan",idSiasatan);
			    r3.add("flag_Hadir","1");
			    r3.add("id_Masuk",idMasuk);
			    r3.add("tarikh_Masuk",r1.unquote("sysdate"));
			    
			    sql = r3.getSQLInsert("tblpptkehadiran");
			    stmt3.executeUpdate(sql);
		    	
		    	 return ""+idKehadiran;
		    }
		   
		    finally {
			      if (db != null) db.close();
			    }
	}
	public static void kemaskiniTurutHadir(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		
		 
		    try
		    {
		    	String idPihakBerkepentingan = (String)data.get("idPB");
		    	String idHakmilikpb = (String)data.get("idHakmilikPb");
		    	String namaPB = (String)data.get("namaPB");
		    	String kodNoPB= (String)data.get("kodNoPB");
		    	String kodJenisPB= (String)data.get("kodJenisPB");
		    	String noPB= (String)data.get("noPB");
		    	String alamat4= (String)data.get("alamat4");
		    	String alamat5= (String)data.get("alamat5");
		    	String alamat6= (String)data.get("alamat6");
		    	String poskod= (String)data.get("poskod");
		    	String negeri= (String)data.get("negeri");
		    	String bandar= (String)data.get("bandar");
		    	String noTelPjbt= (String)data.get("noTelPjbt");
		    	String noHp= (String)data.get("noHp");
		    	String noFaks= (String)data.get("noFaks");
		    	String idKemaskini= (String)data.get("idKemaskini");
		    	
		    	db = new Db();
			    Statement stmt1 = db.getStatement();
			    SQLRenderer r1 = new SQLRenderer();
			    
			    r1.update("id_Pihakberkepentingan",idPihakBerkepentingan);
			    r1.add("nama_Pb",namaPB);
			    r1.add("id_Jenisnopb",kodNoPB);
			    r1.add("id_Jenispb",kodJenisPB);
			    r1.add("no_Pb",noPB);
			    r1.add("id_Kemaskini",idKemaskini);
			    r1.add("tarikh_Kemaskini",r1.unquote("sysdate"));
			    
			    sql = r1.getSQLUpdate("tblpptpihakberkepentingan");
			    stmt1.executeUpdate(sql);
			    
			    Statement stmt2 = db.getStatement();
			    SQLRenderer r2 = new SQLRenderer();
			    
			    r2.update("id_Hakmilikpb",idHakmilikpb);
			    r2.add("alamat1",alamat4);
			    r2.add("alamat2",alamat5);
			    r2.add("alamat3",alamat6);
			    r2.add("poskod",poskod);
			    r2.add("id_Negeri",negeri);
			    r2.add("id_Bandar",bandar);
			    r2.add("no_Tel_Rumah",noTelPjbt);
			    r2.add("no_Handphone",noHp);
			    r2.add("no_Fax",noFaks);
			    r2.add("id_Kemaskini",idKemaskini);
			    r2.add("tarikh_Kemaskini",r2.unquote("sysdate"));
			    
			    sql = r2.getSQLUpdate("tblppthakmilikpb");
			    stmt2.executeUpdate(sql);
			    
			    
		    }
		   
		    finally {
			      if (db != null) db.close();
			    }
	}
	public static void hapusTurutHadir(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      
	      //DELETE FROM TBLPPTKEHADIRAN
	      Statement stmtK = db.getStatement();
	      SQLRenderer rK = new SQLRenderer();
	      rK.add("id_Kehadiran", id);
	      sql = rK.getSQLDelete("tblpptkehadiran");	      
	      stmtK.executeUpdate(sql);
	      
	     
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	public void setPaparTurutHadir (String idKehadiran)throws Exception{
		Db db = null;
	    String sql = "";
	    
	    try{
	    	paparTurutHadir = new Vector();
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT A.ID_PIHAKBERKEPENTINGAN,F.ID_KEHADIRAN,A.NAMA_PB,A.ID_JENISNOPB,A.ID_JENISPB,A.NO_PB,C.ALAMAT1, C.ALAMAT2, C.ALAMAT3,C.POSKOD,C.ID_NEGERI,C.ID_BANDAR," +
		    		" F.ID_HAKMILIKPB,C.NO_HANDPHONE,C.NO_TEL_RUMAH,C.NO_FAX"+ 

		    	" FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
     			" TBLPPTHAKMILIK B,"+
     			" TBLPPTHAKMILIKPB C,"+
     			" TBLPPTKEHADIRAN F" +
     			

     			" WHERE A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN"+
				" AND B.ID_HAKMILIK = C.ID_HAKMILIK"+
				" AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB" +
				" AND F.ID_KEHADIRAN= '"+idKehadiran+"'";
		    ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	  h.put("ID_JENISNOPB",rs.getString("ID_JENISNOPB")==null?"":rs.getString("ID_JENISNOPB"));
		    	  h.put("ID_JENISPB",rs.getString("ID_JENISPB")==null?"":rs.getString("ID_JENISPB"));
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("ALAMAT1",rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
		    	  h.put("ALAMAT2",rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
		    	  h.put("ALAMAT3",rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	  h.put("POSKOD",rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
		    	  h.put("ID_BANDAR",rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
		    	  h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	  h.put("NO_HANDPHONE",rs.getString("NO_HANDPHONE")==null?"":rs.getString("NO_HANDPHONE"));
		    	  h.put("NO_TEL_RUMAH",rs.getString("NO_TEL_RUMAH")==null?"":rs.getString("NO_TEL_RUMAH"));
		    	  h.put("NO_FAX",rs.getString("NO_FAX")==null?"":rs.getString("NO_FAX"));
		    	  h.put("ID_KEHADIRAN",rs.getString("ID_KEHADIRAN")==null?"":rs.getString("ID_KEHADIRAN"));
		    	  h.put("ID_PIHAKBERKEPENTINGAN",rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));

		    	  paparTurutHadir.addElement(h);
		      }
		      
	    }
	    finally {
		      if (db != null) db.close();
		    }
		
		
	}
	public Vector getPaparTurutHadir(){
		return paparTurutHadir;
	}
	public Vector getListTurutHadir(){
		return listTurutHadir;
	}
	public Vector getDataPb(){
		return dataPb;
	}
	public Vector getPaparPb(){
		return paparPb;
	}
	public Vector getListPb(){
		return listPb;
	}
	public Vector getSenaraiHakmilik(){
		return listHakmilik;
	}
	public Vector getListRundingan(){
		return listRundingan;
	}
	
	public Vector getDataRundingan(){
		return paparSet;
	}


}
