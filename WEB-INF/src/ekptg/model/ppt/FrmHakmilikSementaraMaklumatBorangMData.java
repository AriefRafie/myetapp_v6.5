package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmHakmilikSementaraMaklumatBorangMData {
	
	Vector list = null;
	Vector paparMahkamah = null;
	Vector papar = null;
	static Vector getStatus = null;
	static Vector idStatus = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraSenaraiJPPHData.class);
	
	public void  setCarianFail(String noFail,String tarikh,String status, String noRujJKPTG, String userIdNegeri)throws Exception {
		Db db = null;
	    String sql = "";
	    String chkData = noFail.trim();
	    
	    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct s.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, c.nama_negeri, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, f.no_fail, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f, Tblrujsuburusan su, Tblrujnegeri c, Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND c.id_negeri(+) = p.id_negeri AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status AND s.id_status IN ('72','1610193') AND s.id_seksyen = 1 AND f.id_suburusan = 53";
	      
  		// ADDED BY ELLY 14 JUNE 2010
  		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
  			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
  		}	      
	      
  		if(noFail != null){
			sql += " AND (UPPER(F.NO_FAIL) LIKE '%" + noFail.toUpperCase().trim() + "%' OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%" + noFail.toUpperCase().trim() + "%' OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%" + noFail.toUpperCase().trim() + "%')";
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
	      if(noRujJKPTG != null){
	    	  if (!noRujJKPTG.trim().equals("")){
	    		  if (!noRujJKPTG.trim().equals("0")){
	    			  sql +="AND UPPER(p.no_rujukan_upt) LIKE '%'||'" + noRujJKPTG.toUpperCase() + "'||'%'";  
	    		  }
	    	  }	  
	      }
	      sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      myLogger.info("SQL DEFAULT :: "+sql);
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
	
	public Vector getList(){
		return list;
	}
	
	public static String tambah(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 String output = "";
		    try
		    {
		    	long idBorangM = DB.getNextID("TBLPPTBORANGM_SEQ");
		    	String idPermohonan = (String)data.get("idPermohonan");
		    	String idMahkamah = (String)data.get("idMahkamah");
		    	String tujuan = (String)data.get("tujuan");
		    	String idMasuk = (String)data.get("idMasuk");
		    	String perkaraRujuk = (String)data.get("perkaraRujuk");
		    	String idHakmilikPB = (String)data.get("idHakmilikPB");

		    	
		    	db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.add("ID_BORANGM",idBorangM);
			    r.add("ID_PERMOHONAN",idPermohonan);
			    r.add("ID_HAKMILIKPB",idHakmilikPB);
			    r.add("ID_MAHKAMAH",idMahkamah);
			    r.add("TARIKH_BORANGM",r.unquote("sysdate"));
			    r.add("TUJUAN",tujuan);
			    r.add("PERKARA_RUJUKAN",perkaraRujuk);
			    r.add("ID_MASUK",idMasuk);
			    r.add("TARIKH_MASUK",r.unquote("sysdate"));
			    sql = r.getSQLInsert("TBLPPTBORANGM");
			    stmt.executeUpdate(sql);
			    
			    idStatus = new Vector();
				idStatus = getStatus(idPermohonan);
				Hashtable hS = (Hashtable)idStatus.get(0);
				    
				Statement stmtQ = db.getStatement();
				SQLRenderer rQ = new SQLRenderer();
				rQ.update("ID_PERMOHONAN", idPermohonan);
				rQ.add("ID_STATUS",hS.get("ID_STATUS"));
				sql = rQ.getSQLUpdate("TBLPPTPERMOHONAN");	
				stmtQ.executeUpdate(sql); 
			    
			    output = ""+idBorangM;
		    	
		    }finally {
			      if (db != null) db.close();
		    }
		    
		    return output;
	}
	
	public static Vector getStatus(String idPermohonan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			 getStatus = new Vector();
			 db = new Db();
			 Statement stmtS = db.getStatement();

			    sql = "SELECT ID_STATUS FROM TBLRUJSTATUS WHERE KOD_STATUS = '46' AND ID_SEKSYEN = 1";
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
	public Vector papar(String idHakmilikPB) throws Exception {
		
		String sql = "";
		Db db = null;
			
			try {
				papar = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT A.ID_BORANGM,A.ID_MAHKAMAH,A.TUJUAN, A.PERKARA_RUJUKAN, " +
						"A.NO_RUJUKAN_PROSIDING, A.PERINTAH_MAHKAMAH,A.KEPUTUSAN_MAHKAMAH, A.TARIKH_PERINTAH, A.TARIKH_TERIMA_PERINTAH, A.PAMPASAN_MAHKAMAH," +
						"A.PAMPASAN_ASAL, A.PAMPASAN_BANTAHAN, A.BEZA_PAMPASAN, " +
						"C.ALAMAT1,C.ALAMAT2,C.ALAMAT3,C.POSKOD,C.ID_NEGERI "+
						"FROM TBLPPTBORANGM A, " +
						"TBLPPTHAKMILIKPB B, " +
						"TBLRUJPEJABAT C " +
						"WHERE B.ID_HAKMILIKPB = A.ID_HAKMILIKPB(+) " +
						"AND C.ID_PEJABAT(+) = A.ID_MAHKAMAH " +
						"AND A.ID_HAKMILIKPB = '"+idHakmilikPB+"'";
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
			
				while (rs.next()) {
					h = new Hashtable();	
					h.put("ID_BORANGM", rs.getString("ID_BORANGM")==null?"":rs.getString("ID_BORANGM"));
					h.put("ID_MAHKAMAH", rs.getString("ID_MAHKAMAH")==null?"":rs.getString("ID_MAHKAMAH"));
					h.put("TUJUAN", rs.getString("TUJUAN")==null?"":rs.getString("TUJUAN"));
					h.put("PERKARA_RUJUKAN", rs.getString("PERKARA_RUJUKAN")==null?"":rs.getString("PERKARA_RUJUKAN"));
					h.put("KEPUTUSAN_MAHKAMAH", rs.getString("KEPUTUSAN_MAHKAMAH")==null?"":rs.getString("KEPUTUSAN_MAHKAMAH"));
					h.put("NO_RUJUKAN_PROSIDING", rs.getString("NO_RUJUKAN_PROSIDING")==null?"":rs.getString("NO_RUJUKAN_PROSIDING"));
					h.put("PERINTAH_MAHKAMAH", rs.getString("PERINTAH_MAHKAMAH")==null?"":rs.getString("PERINTAH_MAHKAMAH"));
					h.put("TARIKH_PERINTAH", rs.getString("TARIKH_PERINTAH")==null?"":Format.format(rs.getDate("TARIKH_PERINTAH")));
					h.put("TARIKH_TERIMA_PERINTAH", rs.getString("TARIKH_TERIMA_PERINTAH")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_PERINTAH")));				
					h.put("PAMPASAN_MAHKAMAH", rs.getString("PAMPASAN_MAHKAMAH")==null?"":rs.getString("PAMPASAN_MAHKAMAH"));
					
					h.put("ALAMAT1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1").toUpperCase());
					h.put("ALAMAT2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2").toUpperCase());				
					h.put("ALAMAT3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3").toUpperCase());
					h.put("POSKOD", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					
					papar.addElement(h);								
				}
			}
			finally {
				if (db != null)
					db.close();
			}	
		return papar;
	}
	
	public void kemaskini(Hashtable data) throws Exception {
		
		String sql = "";
		String idBorangM = (String)data.get("idBorangM");
		String idMahkamah = (String)data.get("idMahkamah");
		String tujuan = (String)data.get("tujuan");
		String perkaraRujuk = (String)data.get("perkaraRujuk");
		String idKemaskini = (String)data.get("idKemaskini");
		
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("ID_BORANGM", idBorangM);
			r.add("ID_MAHKAMAH", idMahkamah);
		    r.add("TUJUAN", tujuan);
		    r.add("PERKARA_RUJUKAN", perkaraRujuk);
		    r.add("ID_KEMASKINI", idKemaskini);
		    r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		    
		    sql = r.getSQLUpdate("TBLPPTBORANGM");
		    stmt.executeUpdate(sql);
		    
		}
		
		finally {
			if (db != null)
				db.close();
		}
	}
	public static void kemaskiniPerintah(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		    try
		    {
		    	String idBorangM = (String)data.get("idBorangM");
		    	String noRujProsiding = (String)data.get("noRujProsiding");
		    	String perintahMahkamah = (String)data.get("perintahMahkamah");
		    	String kptsnPerintah = (String)data.get("keputusanPerintah");
		    	String tkhPerintah = (String)data.get("tarikhPerintah");
		    	String tkhTerimaPerintah = (String)data.get("tarikhTerimaPerintah");
		    	String amaunPampasanMahkamah = (String)data.get("amaunPampasanMahkamah");
		    	String idKemaskini = (String)data.get("idKemaskini");
		    	
		    	db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("ID_BORANGM",idBorangM);
			    r.add("NO_RUJUKAN_PROSIDING",noRujProsiding);
			    r.add("PERINTAH_MAHKAMAH",perintahMahkamah);
			    r.add("KEPUTUSAN_MAHKAMAH",kptsnPerintah);
			    r.add("TARIKH_PERINTAH",r.unquote("to_date('" + tkhPerintah + "','dd/MM/yyyy')"));
			    r.add("TARIKH_TERIMA_PERINTAH",r.unquote("to_date('" + tkhTerimaPerintah + "','dd/MM/yyyy')"));
			    r.add("PAMPASAN_MAHKAMAH",amaunPampasanMahkamah);
			    r.add("ID_KEMASKINI",idKemaskini);
			    r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			    sql = r.getSQLUpdate("TBLPPTBORANGM");
			    stmt.executeUpdate(sql);
			    
		    	
		    }finally {
			      if (db != null) db.close();
		    }
		    
	}
	
	public Vector paparMahkamah(String idMahkamah)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparMahkamah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();		
			
			sql = "SELECT A.ALAMAT1,A.ALAMAT2,A.ALAMAT3,A.POSKOD,A.ID_NEGERI " +
					"FROM TBLRUJPEJABAT A " +
					"WHERE A.ID_PEJABAT = '"+idMahkamah+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
		
			while (rs.next()) {
				h = new Hashtable();	
				h.put("ALAMAT1",rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1").toUpperCase());
				h.put("ALAMAT2",rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2").toUpperCase());
				h.put("ALAMAT3",rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3").toUpperCase());
				h.put("POSKOD",rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				
				paparMahkamah.addElement(h);
	
			}
			
			return paparMahkamah;
			
		}
		finally {
			if(db != null)
				db.close();
		}

	}
	
}
