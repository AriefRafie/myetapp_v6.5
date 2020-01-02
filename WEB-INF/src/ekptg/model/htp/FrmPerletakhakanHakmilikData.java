package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmPerletakhakanHakmilikData {
	private static Vector list = new Vector();
	
	//*** query data from database
	public static void setListHakmilik(int idHakmilikurusan)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("pihak.id_Pihakberkepentingan");
	      r.add("pihak.id_hakmilikurusan");
	      r.add("pihak.Nama");
	      r.add("pihak.Alamat1");
	      r.add("pihak.Alamat2");
	      r.add("pihak.Alamat3");
	      r.add("pihak.Poskod");
	      r.add("pihak.id_Daerah");
	      r.add("pihak.id_Negeri");
	      r.add("pihak.No_Tel");
	      r.add("pihak.No_Fax");
	      r.add("beban.id_Bebanan");
	      r.add("beban.No_Perserahan");
	      
	      r.add("pihak.id_Pihakberkepentingan",r.unquote("beban.id_Pihakberkepentingan"));

	      r.add("pihak.id_hakmilikurusan", idHakmilikurusan,"=");
	      
	      sql = r.getSQLSelect("Tblhtppihakberkepentingan pihak, Tblhtpbebanan beban");
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      //Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("IdPihakberkepentingan", rs.getString("id_Pihakberkepentingan"));
	    	  h.put("Nama", rs.getString("Nama"));
	    	  if(rs.getString("Alamat1") != null){
	    		  h.put("Alamat1", rs.getString("Alamat1"));
	    	  }else{
	    		  h.put("Alamat1", "");
	    	  }
	    	  if(rs.getString("Alamat2") != null){
	    		  h.put("Alamat2", rs.getString("Alamat2"));
	    	  }else{
	    		  h.put("Alamat2", "");
	    	  }
	    	  if(rs.getString("Alamat3") != null){
	    		  h.put("Alamat3", rs.getString("Alamat3"));
	    	  }else{
	    		  h.put("Alamat3", "");
	    	  }	    	  
	    	  h.put("Poskod", rs.getString("Poskod"));
	    	  if(rs.getString("id_Daerah") != null){
	    		  h.put("IdDaerah", rs.getString("id_Daerah"));
	    	  }else{
	    		  h.put("IdDaerah", "");
	    	  }	    	  
	    	  h.put("IdNegeri", rs.getString("id_Negeri"));
	    	  if(rs.getString("No_Tel") != null){
	    		  h.put("NoTel", rs.getString("No_Tel"));
	    	  }else{
	    		  h.put("NoTel", "");
	    	  }
	    	  if(rs.getString("No_Fax") != null){
	    		  h.put("NoFax", rs.getString("No_Fax"));
	    	  }else{
	    		  h.put("NoFax", "");
	    	  }	    	  
	    	  h.put("IdBebanan", rs.getString("id_Bebanan"));
	    	  h.put("NoPerserahan", rs.getString("No_Perserahan"));
	    	  list.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getListHakmilik(){
		  System.out.println(list.size());
		  return list;
	  } 
	  
	  //*** update data in database
	  public static void update(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		      int IdPihakberkepentingan = (Integer)data.get("idPihakberkepentingan");
		      String nama = (String)data.get("nama");
		      String alamat1 = (String)data.get("alamat1");
		      String alamat2;
		      if(data.get("alamat2") != null)
		    	  alamat2 = (String)data.get("alamat2");
		      else
		    	  alamat2 = "";
		      String alamat3;
		      if(data.get("alamat3") != null)
		    	  alamat3 = (String)data.get("alamat3");
		      else
		    	  alamat3 = "";		      
		      String poskod = (String)data.get("poskod");
		      int idDaerah = (Integer)data.get("idDaerah");
		      int idNegeri = (Integer)data.get("idNegeri");
		      String noTelefon;
		      if(data.get("noTelefon") != null)
		    	  noTelefon = (String)data.get("noTelefon");
		      else
		    	  noTelefon = "";
		      String noFax;
		      if(data.get("noFax") != null)
		    	  noFax = (String)data.get("noFax");
		      else
		    	  noFax = "";
		      
		      int idBebanan = (Integer)data.get("idBebanan");
		      String noPerserahan = (String)data.get("noPerserahan");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Pihakberkepentingan", IdPihakberkepentingan);
			  r.add("nama",nama);
			  r.add("alamat1",alamat1);
			  r.add("alamat2",alamat2);
			  r.add("alamat3",alamat3);
			  r.add("poskod",poskod);
			  r.add("id_Daerah",idDaerah);
			  r.add("id_Negeri",idNegeri);
			  r.add("no_Tel",noTelefon);
			  r.add("no_Fax",noFax);
			  sql = r.getSQLUpdate("Tblhtppihakberkepentingan");
			  System.out.println("FrmGadaianHakmilikData::Update::Tblhtppihakberkepentingan = "+sql);
			  stmt.executeUpdate(sql);
		      
		      Statement stmtP = db.getStatement();
			  SQLRenderer rP = new SQLRenderer();
			  rP.update("id_Bebanan", idBebanan);
			  rP.add("no_Perserahan",noPerserahan);
		      sql = rP.getSQLUpdate("Tblhtpbebanan");
		      System.out.println("FrmGadaianHakmilikData::Update::Tblhtpbebanan = "+sql);
		      stmtP.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	  
	//*** save data in database
	  public static void simpan(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		      long IdPihakberkepentingan = DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ");
		      int idHakmilikurusan = (Integer)data.get("idHakmilikurusan");
		      int idJenisnopb = Integer.parseInt("1");
		      int idJenispb = Integer.parseInt("1");
		      String nama = (String)data.get("nama");
		      String alamat1 = (String)data.get("alamat1");
		      String alamat2;
		      if(data.get("alamat2") != null)
		    	  alamat2 = (String)data.get("alamat2");
		      else
		    	  alamat2 = "";
		      String alamat3;
		      if(data.get("alamat3") != null)
		    	  alamat3 = (String)data.get("alamat3");
		      else
		    	  alamat3 = "";		      
		      String poskod = (String)data.get("poskod");
		      int idDaerah = (Integer)data.get("idDaerah");
		      int idNegeri = (Integer)data.get("idNegeri");
		      String noTelefon;
		      if(data.get("noTelefon") != null)
		    	  noTelefon = (String)data.get("noTelefon");
		      else
		    	  noTelefon = "";
		      String noFax;
		      if(data.get("noFax") != null)
		    	  noFax = (String)data.get("noFax");
		      else
		    	  noFax = "";
		      
		      long idBebanan = DB.getNextID("TBLHTPBEBANAN_SEQ");
		      String noPerserahan = (String)data.get("noPerserahan");
		      String jilid = "1";
		      String folio = "1";
		      Date now = new Date();
		      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      int IdRujbebanan = Integer.parseInt("224");
		      int IdRujpihakberkepentingan = Integer.parseInt("1");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  r.add("id_Hakmilikurusan", idHakmilikurusan);
			  r.add("id_Jenisnopb", idJenisnopb);
			  r.add("id_Jenispb", idJenispb);
			  r.add("nama", nama);
			  r.add("alamat1", alamat1);
			  r.add("alamat2", alamat2);
			  r.add("alamat3", alamat3);
			  r.add("poskod", poskod);
			  r.add("id_Daerah", idDaerah);
			  r.add("id_Negeri", idNegeri);
			  r.add("no_Tel", noTelefon);
			  r.add("no_Fax", noFax);
			  sql = r.getSQLInsert("Tblhtppihakberkepentingan");
			  System.out.println("FrmGadaianHakmilikData::Insert::Tblhtppihakberkepentingan = "+sql);
			  stmt.executeUpdate(sql);
		      
		      Statement stmtP = db.getStatement();
			  SQLRenderer rP = new SQLRenderer();
			  rP.add("id_Bebanan", idBebanan);
			  rP.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  rP.add("nama_Pihak_Berkepentingan", nama);
			  rP.add("no_Perserahan", noPerserahan);
			  rP.add("jilid", jilid);
			  rP.add("folio", folio);
			  rP.add("tarikh_Daftar", rP.unquote(sekarang));
			  rP.add("alamat1", alamat1);
			  rP.add("alamat2", alamat2);
			  rP.add("alamat3", alamat3);
			  rP.add("poskod", poskod);
			  rP.add("id_Daerah", idDaerah);
			  rP.add("id_Negeri", idNegeri);
			  rP.add("no_Tel", noTelefon);
			  rP.add("no_Fax", noFax);
			  rP.add("id_Rujbebanan", IdRujbebanan);
			  rP.add("Id_Rujpihakberkepentingan", IdRujpihakberkepentingan);
		      sql = rP.getSQLInsert("Tblhtpbebanan");
		      System.out.println("FrmGadaianHakmilikData::Insert::Tblhtpbebanan = "+sql);
		      stmtP.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
}
