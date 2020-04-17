package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraMaklumatHakmilikPBData {
	
	Vector list = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public Vector  getHakmilik(String idHakmilik)throws Exception {
		Db db = null;
	    String sql = "";
	    
	    try {
	    	  list = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_JENISHAKMILIK,A.NO_HAKMILIK,A.TARIKH_DAFTAR,A.ID_NEGERI,A.ID_DAERAH,A.ID_MUKIM,"+
		      		" A.SYARAT_NYATA,A.SYARAT_KHAS,A.ID_LOT,A.NO_LOT,A.LUAS_LOT,A.ID_UNITLUASLOT,A.LUAS_AMBIL,A.ID_UNITLUASAMBIL,A.ID_KATEGORITANAH,A.NO_SYIT,"+
		      		" A.NO_PELAN,A.SEKATAN_KEPENTINGAN,B.TARIKH_MULA,B.TARIKH_AKHIR,B.LUAS_SEWA,B.ID_UNITLUAS,B.TEMPOH_PENDUDUKAN," +
		      		" B.UNIT_TEMPOH"+

		      		" FROM TBLPPTHAKMILIK A," +
		      		" TBLPPTBORANGQ B"+

		      		" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN(+)" +
		      		" AND A.ID_HAKMILIK = B.ID_HAKMILIK(+)" +
		      		" AND A.ID_HAKMILIK = '" + idHakmilik + "'";
		      		
		      ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		     
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_JENISHAKMILIK",rs.getString("ID_JENISHAKMILIK")==null?"":rs.getString("ID_JENISHAKMILIK"));
		    	  h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK").toUpperCase());
		    	  h.put("TARIKH_DAFTAR",rs.getString("TARIKH_DAFTAR")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR")));
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
		    	  h.put("ID_DAERAH",rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
		    	  h.put("ID_MUKIM",rs.getString("ID_MUKIM")==null?"":rs.getString("ID_MUKIM"));
		    	  h.put("SYARAT_NYATA",rs.getString("SYARAT_NYATA")==null?"":rs.getString("SYARAT_NYATA"));
		    	  h.put("SYARAT_KHAS",rs.getString("SYARAT_KHAS")==null?"":rs.getString("SYARAT_KHAS"));
		    	  h.put("ID_LOT",rs.getString("ID_LOT")==null?"":rs.getString("ID_LOT"));
		    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
		    	  h.put("LUAS_LOT",rs.getString("LUAS_LOT")==null?"":rs.getString("LUAS_LOT"));
		    	  h.put("ID_UNITLUASLOT",rs.getString("ID_UNITLUASLOT")==null?"":rs.getString("ID_UNITLUASLOT"));
		    	  h.put("LUAS_AMBIL",rs.getString("LUAS_AMBIL")==null?"":rs.getString("LUAS_AMBIL"));
		    	  h.put("ID_UNITLUASAMBIL",rs.getString("ID_UNITLUASAMBIL")==null?"":rs.getString("ID_UNITLUASAMBIL"));
		    	  h.put("ID_KATEGORITANAH",rs.getString("ID_KATEGORITANAH")==null?"":rs.getString("ID_KATEGORITANAH"));
		    	  h.put("NO_SYIT",rs.getString("NO_SYIT")==null?"":rs.getString("NO_SYIT"));
		    	  h.put("NO_PELAN",rs.getString("NO_PELAN")==null?"":rs.getString("NO_PELAN"));
		    	  h.put("SEKATAN_KEPENTINGAN",rs.getString("SEKATAN_KEPENTINGAN")==null?"":rs.getString("SEKATAN_KEPENTINGAN"));
		    	  h.put("TARIKH_MULA",rs.getString("TARIKH_MULA")==null?"":Format.format(rs.getDate("TARIKH_MULA")));
		    	  h.put("TARIKH_AKHIR",rs.getString("TARIKH_AKHIR")==null?"":Format.format(rs.getDate("TARIKH_AKHIR")));
		    	  h.put("LUAS_SEWA",rs.getString("LUAS_SEWA")==null?rs.getString("LUAS_AMBIL"):rs.getString("LUAS_SEWA"));
		    	  h.put("ID_UNITLUAS",rs.getString("ID_UNITLUAS")==null?"":rs.getString("ID_UNITLUAS"));
		    	  h.put("TEMPOH_PENDUDUKAN",rs.getString("TEMPOH_PENDUDUKAN")==null?"":rs.getString("TEMPOH_PENDUDUKAN"));
		    	  h.put("UNIT_TEMPOH",rs.getString("UNIT_TEMPOH")==null?"":rs.getString("UNIT_TEMPOH"));

		    	  list.addElement(h);
		    	  
		      }
		      return list;
		      
	    }finally {
	      if (db != null) db.close();
	    }
	}
	

	public static void addHakmilik(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 
		    try
		    {
		    	 String permohonan = (String)data.get("id_permohonan");
		    	 String idJenisHakmilik = (String)data.get("idJenisHakmilik");
		    	 String noHakmilik = (String)data.get("noHakmilik");
		    	 String tkhDaftar = (String)data.get("tkhDaftar");
		    	 String tarikhDaftar = "to_date('" + tkhDaftar + "','dd/MM/yyyy')";
		    	 String idNegeri = (String)data.get("idNegeri");
		    	 String idDaerah = (String)data.get("idDaerah");
		    	 String idMukim = (String)data.get("idMukim");
		    	 String syaratNyata = (String)data.get("syaratNyata");
		    	 String syaratKhas = (String)data.get("syaratKhas");
		    	 String idLot = (String)data.get("idLot");
		    	 String noLot = (String)data.get("noLot");
		     	 String luasLot = (String)data.get("luasLot");
		     	 String idLuasLot = (String)data.get("idLuasLot");
		     	 String idKategoriTanah = (String)data.get("idKategoriTanah");
		    	 String noPiawai = (String)data.get("noPiawai");
		    	 String noPA = (String)data.get("noPA");
		    	 String luasSewa = (String)data.get("luasSewa");
		    	 String idLuasSewa = (String)data.get("idLuasSewa");
		    	 String sekatan = (String)data.get("sekatan");
		    	 String tempoh = (String)data.get("tempoh");
		    	 String unitTempoh = (String)data.get("unitTempoh");
		    	 String tkhMula = (String)data.get("tkhMula");
		    	 String tarikhMula = "to_date('" + tkhMula + "','dd/MM/yyyy')";
		    	 String tkhAkhir = (String)data.get("tkhAkhir");
		    	 String tarikhAkhir = "to_date('" + tkhAkhir + "','dd/MM/yyyy')";
			     long idHakmilik = DB.getNextID("TBLPPTHAKMILIK_SEQ");

			     String idMasuk = (String)data.get("id_Masuk");
			     
		    	  db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("id_Hakmilik",idHakmilik);
			      r.add("id_permohonan", permohonan);
			      r.add("id_Jenishakmilik", idJenisHakmilik);
			      r.add("no_Hakmilik", noHakmilik);
			      r.add("tarikh_Daftar", r.unquote(tarikhDaftar));
			      r.add("id_Negeri", idNegeri);
			      r.add("id_Daerah", idDaerah);
			      r.add("id_Mukim", idMukim);
			      r.add("syarat_Nyata", syaratNyata);
			      r.add("syarat_Khas",syaratKhas);
			      r.add("id_Lot", idLot);
			      r.add("no_Lot", noLot);
			      r.add("luas_Lot", luasLot);
			      r.add("id_Unitluaslot", idLuasLot);
			      r.add("luas_Ambil", luasSewa);
			      r.add("id_Unitluasambil", idLuasSewa);
			      r.add("id_Kategoritanah", idKategoriTanah);
			      r.add("no_Syit", noPiawai);
			      r.add("no_Pelan", noPA);
			      r.add("sekatan_Kepentingan", sekatan);
			      r.add("id_Masuk",idMasuk);
			      r.add("tarikh_Masuk",r.unquote("sysdate")); 
			      
			      sql = r.getSQLInsert("tblppthakmilik");
			      stmt.executeUpdate(sql);
			      
			      Statement stmtQ = db.getStatement();
			      SQLRenderer rQ = new SQLRenderer();
			      rQ.add("id_permohonan", permohonan);
			      rQ.add("tempoh_Pendudukan", tempoh);
			      rQ.add("unit_Tempoh", unitTempoh);
			      rQ.add("id_Hakmilik",idHakmilik);
			      rQ.add("tarikh_Mula", r.unquote(tarikhMula));
			      rQ.add("tarikh_Akhir", r.unquote(tarikhAkhir));
			      rQ.add("luas_Sewa", luasSewa);
			      rQ.add("id_Unitluas", idLuasSewa);
			      rQ.add("id_Masuk",idMasuk);
			      rQ.add("tarikh_Masuk",r.unquote("sysdate")); 
			      sql = rQ.getSQLInsert("tblpptborangq");
			      stmtQ.executeUpdate(sql);
			      
			      Statement stmtS = db.getStatement();
			      SQLRenderer rS = new SQLRenderer();
			      rS.update("id_permohonan", permohonan);
			      rS.add("id_status", "16");
			      sql = rS.getSQLUpdate("tblpptpermohonan");
			      stmtS.executeUpdate(sql);
			      

		    }
		    finally {
			      if (db != null) db.close();
			    }
	}
	public static void updateHakmilik(Hashtable data) throws Exception {
		
		 Db db = null;
		    String sql = "";
		   
		    try
		    {	
		    	 String idHakmilik = (String)data.get("idHakmilik");
		    	 String permohonan = (String)data.get("id_permohonan");
		    	 String idJenisHakmilik = (String)data.get("idJenisHakmilik");
		    	 String noHakmilik = (String)data.get("noHakmilik");
		    	 String tkhDaftar = (String)data.get("tkhDaftar");
		    	 String tarikhDaftar = "to_date('" + tkhDaftar + "','dd/MM/yyyy')";
		    	 String idNegeri = (String)data.get("idNegeri");
		    	 String idDaerah = (String)data.get("idDaerah");
		    	 String idMukim = (String)data.get("idMukim");
		    	 String syaratNyata = (String)data.get("syaratNyata");
		    	 String syaratKhas = (String)data.get("syaratKhas");
		    	 String idLot = (String)data.get("idLot");
		    	 String noLot = (String)data.get("noLot");
		     	 String luasLot = (String)data.get("luasLot");
		     	 String idLuasLot = (String)data.get("idLuasLot");
		     	 String idKategoriTanah = (String)data.get("idKategoriTanah");
		    	 String noPiawai = (String)data.get("noPiawai");
		    	 String noPA = (String)data.get("noPA");
		    	 String luasSewa = (String)data.get("luasSewa");
		    	 String idLuasSewa = (String)data.get("idLuasSewa");
		    	 String sekatan = (String)data.get("sekatan");
		    	 String tempoh = (String)data.get("tempoh");
		    	 String unitTempoh = (String)data.get("unitTempoh");
		    	 String tkhMula = (String)data.get("tkhMula");
		    	 String tarikhMula = "to_date('" + tkhMula + "','dd/MM/yyyy')";
		    	 String tkhAkhir = (String)data.get("tkhAkhir");
		    	 String tarikhAkhir = "to_date('" + tkhAkhir + "','dd/MM/yyyy')";
		    	 String idKemaskini= (String)data.get("id_Kemaskini");
		    	 
		    	 db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.update("id_Hakmilik",idHakmilik);
			      r.add("id_permohonan", permohonan);
			      r.add("id_Jenishakmilik", idJenisHakmilik);
			      r.add("no_Hakmilik", noHakmilik);
			      r.add("tarikh_Daftar", r.unquote(tarikhDaftar));
			      r.add("id_Negeri", idNegeri);
			      r.add("id_Daerah", idDaerah);
			      r.add("id_Mukim", idMukim);
			      r.add("syarat_Nyata", syaratNyata);
			      r.add("syarat_Khas",syaratKhas);
			      r.add("id_Lot", idLot);
			      r.add("no_Lot", noLot);
			      r.add("luas_Lot", luasLot);
			      r.add("id_Unitluaslot", idLuasLot);
			      r.add("luas_Ambil", luasSewa);
			      r.add("id_Unitluasambil", idLuasSewa);
			      r.add("id_Kategoritanah", idKategoriTanah);
			      r.add("no_Syit", noPiawai);
			      r.add("no_Pelan", noPA);
			      r.add("sekatan_Kepentingan", sekatan);
			      r.add("id_Kemaskini",idKemaskini);
			      r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
			      
			      sql = r.getSQLUpdate("tblppthakmilik");
			      stmt.executeUpdate(sql);
			      
			      Statement stmtS = db.getStatement();
			      SQLRenderer rS = new SQLRenderer();
			      rS.update("id_permohonan", permohonan);
			      rS.add("id_status", "16");
			      sql = rS.getSQLUpdate("tblpptpermohonan");
			      stmtS.executeUpdate(sql);
			      
			      Statement stmtCheckData = db.getStatement();
			      String sqlCheckData = "SELECT ID_HAKMILIK FROM TBLPPTBORANGQ WHERE ID_HAKMILIK = '" + idHakmilik + "'";

			      ResultSet rsCheckData = stmtCheckData.executeQuery(sqlCheckData);  
			      
			      
			      if(rsCheckData.next()){
				      if(rsCheckData.getString("ID_HAKMILIK")!=" "){
				      
				      Statement stmtQ = db.getStatement();
				      SQLRenderer rQ = new SQLRenderer();
				      rQ.add("id_permohonan", permohonan);
				      rQ.add("tempoh_Pendudukan", tempoh);
				      rQ.add("unit_Tempoh", unitTempoh);
				      rQ.update("id_Hakmilik",idHakmilik);
				      rQ.add("tarikh_Mula", r.unquote(tarikhMula));
				      rQ.add("tarikh_Akhir", r.unquote(tarikhAkhir));
				      rQ.add("luas_Sewa", luasSewa);
				      rQ.add("id_Unitluas", idLuasSewa);
				      rQ.add("id_Kemaskini",idKemaskini);
				      rQ.add("tarikh_Kemaskini",r.unquote("sysdate")); 
				      sql = rQ.getSQLUpdate("tblpptborangq");
				      stmtQ.executeUpdate(sql);
				     
				      }
			      }
			      else{
			    	  Statement stmtQ = db.getStatement();
				      SQLRenderer rQ = new SQLRenderer();
				      rQ.add("id_permohonan", permohonan);
				      rQ.add("tempoh_Pendudukan", tempoh);
				      rQ.add("unit_Tempoh", unitTempoh);
				      rQ.add("id_Hakmilik",idHakmilik);
				      rQ.add("tarikh_Mula", r.unquote(tarikhMula));
				      rQ.add("tarikh_Akhir", r.unquote(tarikhAkhir));
				      rQ.add("luas_Sewa", luasSewa);
				      rQ.add("id_Unitluas", idLuasSewa);
				      rQ.add("id_Masuk",idKemaskini);
				      rQ.add("tarikh_Masuk",r.unquote("sysdate")); 
				      sql = rQ.getSQLInsert("tblpptborangq");
				      stmtQ.executeUpdate(sql);

			      }
			      
			      
		    	 
		    } finally {
			      if (db != null) db.close();
		    }

	}
	public static void deleteMaklumatTanah(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmtQ = db.getStatement();
	      SQLRenderer rQ = new SQLRenderer();
	      rQ.add("id_hakmilik", id);
	      sql = rQ.getSQLDelete("tblpptborangq");	      
	      stmtQ.executeUpdate(sql);
	      
	   
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_hakmilik", id);
	      sql = r.getSQLDelete("tblppthakmilik");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close delete

	public Vector  getSenaraiPB(String idHakmilik)throws Exception {
		
		Db db = null;
	    String sql = "";
	    
	    try {
	    	 list = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_PIHAKBERKEPENTINGAN,A.NO_PB, A.NAMA_PB, B.SYER_ATAS, B.SYER_BAWAH"+

		    	    " FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
		    	    " TBLPPTHAKMILIKPB B,"+
		    	    " TBLPPTHAKMILIK C"+

		    	    " WHERE C.ID_HAKMILIK = B.ID_HAKMILIK"+
		    	    " AND A.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN"+
		    	    " AND B.ID_HAKMILIK = '" + idHakmilik + "'";
		      
		      ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("BIL",bil);
		    	  h.put("ID_PIHAKBERKEPENTINGAN", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	  h.put("BAHAGIAN",rs.getString("SYER_ATAS")==null && rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_ATAS")+" / "+ rs.getString("SYER_BAWAH"));
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		    	  
		      }
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("BIL","");
		    	  h.put("ID_PIHAKBERKEPENTINGAN", "");
		    	  h.put("NO_PB","");
		    	  h.put("NAMA_PB","Tiada rekod.");
		    	  h.put("BAHAGIAN","");
		    	  list.addElement(h);
		      }
		      return list;  
	    }
	    
	    finally {
	      if (db != null) db.close();
	    }
	   
		
	}
	public static void addPB(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 
		    try
		    {
		    	 long id_Pihakberkepentingan = DB.getNextID("TBLPPTPIHAKBERKEPENTINGAN_SEQ");
		    	 long idHakmilikPB = DB.getNextID("TBLPPTHAKMILIKPB_SEQ");
		    	 String idHakmilik = (String)data.get("idHakmilik");
		    	 String namaPB = (String)data.get("namaPB");
		    	 String kodJenisPB = (String)data.get("kodJenisPB");
		    	 String kodNoPB = (String)data.get("kodNoPB");
		    	 String noPB   = (String)data.get("noPB");
		    	 String bangsa  = (String)data.get("bangsa");
		    	 String warganegara  = (String)data.get("warganegara");
		    	 String bhgnAtas  = (String)data.get("bhgnAtas");
		    	 String bhgnBwh  = (String)data.get("bhgnBwh");
		    	 String alamat1  = (String)data.get("alamat1");
		    	 String alamat2  = (String)data.get("alamat2");
		    	 String alamat3  = (String)data.get("alamat3");
		    	 String poskod  = (String)data.get("poskod");
		    	 String negeri  = (String)data.get("negeri");
		    	 String bandar  = (String)data.get("bandar");
		    	 String noTel  = (String)data.get("noTel");
		    	 String noHP  = (String)data.get("noHP");
		    	 String noFaks  = (String)data.get("noFaks");
		    	 String id_Masuk = (String)data.get("id_Masuk");
		    	 
		    	 db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.add("id_Hakmilikpb",idHakmilikPB);
			     r.add("id_Hakmilik",idHakmilik);
			     r.add("id_Pihakberkepentingan",id_Pihakberkepentingan);
			     r.add("syer_Atas",bhgnAtas);
			     r.add("syer_Bawah",bhgnBwh);
			     r.add("alamat1",alamat1);
			     r.add("alamat2",alamat2);
			     r.add("alamat3",alamat3);
			     r.add("poskod",poskod);
			     r.add("id_Negeri",negeri);
			     r.add("id_Bandar",bandar);
			     r.add("no_Tel_Rumah",noTel);
			     r.add("no_Handphone",noHP);
			     r.add("no_Fax",noFaks);
			     r.add("id_Masuk",id_Masuk);
			     r.add("tarikh_Masuk",r.unquote("sysdate"));
			     sql = r.getSQLInsert("tblppthakmilikpb");
			     stmt.executeUpdate(sql);
			     
			     Statement stmtPB = db.getStatement();
			     SQLRenderer rPB = new SQLRenderer();
			     rPB.add("id_Pihakberkepentingan",id_Pihakberkepentingan);
			     rPB.add("nama_PB",namaPB);
			     rPB.add("id_Jenispb",kodJenisPB);
			     rPB.add("id_Jenisnopb",kodNoPB);
			     rPB.add("no_PB",noPB);
			     rPB.add("id_Bangsa",bangsa);
			     rPB.add("id_Warganegara",warganegara);
			     rPB.add("id_Masuk",id_Masuk);
			     rPB.add("tarikh_Masuk",rPB.unquote("sysdate"));
			     sql = rPB.getSQLInsert("tblpptpihakberkepentingan");
			     stmtPB.executeUpdate(sql);
		    	 
		    }
		    finally {
			      if (db != null) db.close();
			}
			   
		    
	}
	public Vector getPihakBerkepentingan(String idHakmilik, String idPihakBerkepentingan)throws Exception {
		Db db = null;
	    String sql = "";
	    
	    try {
	    	list = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.NAMA_PB,A.ID_JENISPB, A.ID_JENISNOPB, A.NO_PB,A.ID_BANGSA,"+
		    	    " A.ID_WARGANEGARA, C.SYER_ATAS, C.SYER_BAWAH, C.ALAMAT1, C.ALAMAT2, C.ALAMAT3, C.POSKOD, C.ID_NEGERI, C.ID_BANDAR,"+
		    	    " C.NO_TEL_RUMAH, C.NO_HANDPHONE, C.NO_FAX"+

		    	    " FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
		    	    " TBLPPTHAKMILIK B,"+
		    	    " TBLPPTHAKMILIKPB C"+
     
		    	    " WHERE A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN"+
		    	    " AND B.ID_HAKMILIK = C.ID_HAKMILIK"+
		    	    " AND B.ID_HAKMILIK ='" + idHakmilik + "'" +
		    	    " AND A.ID_PIHAKBERKEPENTINGAN = '" + idPihakBerkepentingan + "'";
		      
		      ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		     
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	  h.put("ID_JENISPB",rs.getString("ID_JENISPB")==null?"":rs.getString("ID_JENISPB"));
		    	  h.put("ID_JENISNOPB",rs.getString("ID_JENISNOPB")==null?"":rs.getString("ID_JENISNOPB"));
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("ID_BANGSA",rs.getString("ID_BANGSA")==null?"":rs.getString("ID_BANGSA"));
		    	  h.put("ID_WARGANEGARA",rs.getString("ID_WARGANEGARA")==null?"":rs.getString("ID_WARGANEGARA"));
		    	  h.put("SYER_ATAS",rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS"));
		    	  h.put("SYER_BAWAH",rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_BAWAH"));
		    	  h.put("ALAMAT1",rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1").toUpperCase());
		    	  h.put("ALAMAT2",rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2").toUpperCase());
		    	  h.put("ALAMAT3",rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3").toUpperCase());
		    	  h.put("POSKOD",rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
		    	  h.put("ID_BANDAR",rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
		    	  h.put("NO_TEL_RUMAH",rs.getString("NO_TEL_RUMAH")==null?"":rs.getString("NO_TEL_RUMAH"));
		    	  h.put("NO_HP",rs.getString("NO_HANDPHONE")==null?"":rs.getString("NO_HANDPHONE"));
		    	  h.put("NO_FAX",rs.getString("NO_FAX")==null?"":rs.getString("NO_FAX"));
		    	
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		      
		      
	    }finally {
		      if (db != null) db.close();
		}
	}
	
	public static void updatePB(Hashtable data) throws Exception
	{
		 Db db = null;
		 String sql = "";
		 
		    try
		    {
		    	 String id_Pihakberkepentingan = (String)data.get("idPB");
		    	 String namaPB = (String)data.get("namaPB");
		    	 String kodJenisPB = (String)data.get("kodJenisPB");
		    	 String kodNoPB = (String)data.get("kodNoPB");
		    	 String noPB   = (String)data.get("noPB");
		    	 String bangsa  = (String)data.get("bangsa");
		    	 String warganegara  = (String)data.get("warganegara");
		    	 String bhgnAtas  = (String)data.get("bhgnAtas");
		    	 String bhgnBwh  = (String)data.get("bhgnBwh");
		    	 String alamat1  = (String)data.get("alamat1");
		    	 String alamat2  = (String)data.get("alamat2");
		    	 String alamat3  = (String)data.get("alamat3");
		    	 String poskod  = (String)data.get("poskod");
		    	 String negeri  = (String)data.get("negeri");
		    	 String bandar  = (String)data.get("bandar");
		    	 String noTel  = (String)data.get("noTel");
		    	 String noHP  = (String)data.get("noHP");
		    	 String noFaks  = (String)data.get("noFaks");
		    	 String id_Kemaskini = (String)data.get("id_Kemaskini");
		    	 
		    	 db = new Db();
			     Statement stmtPB = db.getStatement();
			     SQLRenderer rPB = new SQLRenderer();
			     rPB.update("id_Pihakberkepentingan",id_Pihakberkepentingan);
			     rPB.add("nama_PB",namaPB);
			     rPB.add("id_Jenispb",kodJenisPB);
			     rPB.add("id_Jenisnopb",kodNoPB);
			     rPB.add("no_PB",noPB);
			     rPB.add("id_Bangsa",bangsa);
			     rPB.add("id_Warganegara",warganegara);
			     rPB.add("id_Kemaskini",id_Kemaskini);
			     rPB.add("tarikh_Kemaskini",rPB.unquote("sysdate"));
			     sql = rPB.getSQLUpdate("tblpptpihakberkepentingan");
			     stmtPB.executeUpdate(sql);
			     
			     db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.update("id_Pihakberkepentingan",id_Pihakberkepentingan);
			     r.add("syer_Atas",bhgnAtas);
			     r.add("syer_Bawah",bhgnBwh);
			     r.add("alamat1",alamat1);
			     r.add("alamat2",alamat2);
			     r.add("alamat3",alamat3);
			     r.add("poskod",poskod);
			     r.add("id_Negeri",negeri);
			     r.add("id_Bandar",bandar);
			     r.add("no_Tel_Rumah",noTel);
			     r.add("no_Handphone",noHP);
			     r.add("no_Fax",noFaks);
			     r.add("id_Kemaskini",id_Kemaskini);
			     r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			     sql = r.getSQLUpdate("tblppthakmilikpb");
			     stmt.executeUpdate(sql);
		    	 
		    }
		    finally {
			      if (db != null) db.close();
			}
			   
		    
	}
	
	public static void deleteMaklumatPB( String idPB) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmtQ = db.getStatement();
	      SQLRenderer rQ = new SQLRenderer();
	      rQ.add("id_Pihakberkepentingan", idPB);
	      sql = rQ.getSQLDelete("tblppthakmilikpb");	      
	      stmtQ.executeUpdate(sql);
	      
	   
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Pihakberkepentingan", idPB);
	      sql = r.getSQLDelete("tblpptpihakberkepentingan");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close delete
}
