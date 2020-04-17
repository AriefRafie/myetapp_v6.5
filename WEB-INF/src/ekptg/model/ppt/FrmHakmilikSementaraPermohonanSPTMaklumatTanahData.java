package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmHakmilikSementaraPermohonanSPTMaklumatTanahData {
	Vector maklumatTanah = null;

	public static void add_maklumat_tanah(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    try
	    {
	      
	      String permohonan = (String)data.get("id_permohonan");
	      String negeri = (String)data.get("negeri");
	      String jenisHakMilik = (String)data.get("jenisHakMilik");
	      String daerah = (String)data.get("daerah");
	      String mukim = (String)data.get("mukim");
	      String luas = (String)data.get("luas");
	      String lot = (String)data.get("lot");
	      //String luas_diambil = (String)data.get("luas_diambil");
	      
	      String no_hakmilik = (String)data.get("no_hakmilik");
	      String no_lot = (String)data.get("no_lot");
	      String luas_lot = (String)data.get("luas_lot");
	      //String no_pt = (String)data.get("no_pt");
	      String anggaran_luas = (String)data.get("anggaran_luas");
	      
	      int id = Integer.parseInt(permohonan);
	      int id_negeri = Integer.parseInt(negeri);
	      int id_jenishakmilik = Integer.parseInt(jenisHakMilik);
	      int id_daerah = Integer.parseInt(daerah);
	      int id_mukim = Integer.parseInt(mukim);
	      int id_luas = Integer.parseInt(luas);
	      int id_lot = Integer.parseInt(lot);
	     // int id_luas_diambil = Integer.parseInt(luas_diambil);
	      int luas_ambil = Integer.parseInt(anggaran_luas);
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_permohonan", id);
	      r.add("id_negeri", id_negeri); 	
	      r.add("id_jenishakmilik", id_jenishakmilik);
	      r.add("id_daerah", id_daerah);
	      r.add("id_mukim", id_mukim);
	      r.add("id_unitluaslot", id_luas);
	      r.add("id_lot", id_lot);
	    //  r.add("id_unitluasambil", id_luas_diambil);
	      
	      r.add("luas_ambil", luas_ambil);
	      r.add("no_hakmilik", no_hakmilik);
	      r.add("no_lot", no_lot);
	      r.add("luas_lot", luas_lot);
	      //r.add("no_pt", no_pt);
	     
	
	      sql = r.getSQLInsert("tblppthakmilik");
	      stmt.executeUpdate(sql);
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close add_maklumat_tanah
	public void setMaklumatTanah(String id) throws Exception {
		Db db = null;
		String sql = "";
		int one = 1;
		
		try{
			maklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("hk.id_hakmilik");
		r.add("ls.id_luas");
		//r.add("ls2.id_luas");
		r.add("p.id_permohonan");
		r.add("jhk.id_jenishakmilik");
		r.add("n.id_negeri");
		r.add("hk.id_lot");
		r.add("d.id_daerah");
		r.add("mk.id_mukim");
		r.add("hk.luas_ambil");
		r.add("hk.no_hakmilik");
		r.add("hk.no_lot");
		r.add("hk.luas_lot");
		r.add("hk.no_pt");
		r.add("n.nama_negeri");
		r.add("d.nama_daerah");
		r.add("ls.keterangan");
		r.add("mk.nama_mukim");
		r.add("jhk.keterangan");
		
		

		r.add("n.id_negeri",r.unquote("hk.id_negeri"));
		r.add("d.id_daerah",r.unquote("hk.id_daerah"));
		r.add("ls.id_luas",r.unquote("hk.id_unitluaslot"));
		//r.add("ls2.id_luas",r.unquote("hk.id_unitluasambil"));
		r.add("mk.id_mukim",r.unquote("hk.id_mukim"));
		r.add("jhk.id_jenishakmilik",r.unquote("hk.id_jenishakmilik"));
		
		
		r.add("p.id_permohonan",r.unquote("hk.id_permohonan"));
		
		
		r.add("hk.id_hakmilik",id);
		
		sql = r.getSQLSelect("Tblppthakmilik hk, Tblrujjenishakmilik jhk, Tblrujmukim mk, Tblrujluas ls, Tblrujdaerah d, Tblrujnegeri n, Tblpptpermohonan p");
		
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		
		while(rs.next()) {
			h = new Hashtable();
			h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
			h.put("id_luasLot", rs.getString(2)==null?"":rs.getString(2));
			h.put("id_lot", rs.getString("id_lot")==null?"":rs.getString("id_lot"));
			//h.put("id_luasAmbil", rs.getString(3)==null?"":rs.getString(3));
			h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
			h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
			h.put("luas_ambil", rs.getString("luas_ambil")==null?"":rs.getString("luas_ambil"));
			h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getString("luas_lot"));
			h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
			h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
			h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
			
			maklumatTanah.addElement(h);
		}
		}
		finally {
			if(db != null) db.close();
		}
		
	}//close maklumat tanah
	
	public static void updateMaklumatTanah(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    	
	   //   int idPermohonan = (Integer)data.get("id_permohonan");
	      String idHakmilik = (String)data.get("id_hakmilik");
	      
	      
		  String negeri = (String)data.get("editNegeri");
		  String jenishakmilik = (String)data.get("editJenisHakmilik");
		  String daerah = (String)data.get("editDaerah");
		  String mukim = (String)data.get("editMukim");
		  String luas = (String)data.get("editLuas");
		  String lot = (String)data.get("editLot");
		  //String luasambil = (String)data.get("editLuasAmbil");
	
		  
		  String edit_no_hakmilik = (String)data.get("edit_no_hakmilik");  
		  String edit_no_lot = (String)data.get("edit_no_lot");
		  String edit_luas_lot = (String)data.get("edit_luas_lot");
		  //String edit_no_pt = (String)data.get("edit_no_pt");
		  String edit_anggaran_luas = (String)data.get("edit_anggaran_luas");
		  
		  int idNegeri = Integer.parseInt(negeri);
		  int idJenisHakMilik = Integer.parseInt(jenishakmilik);
		  int idDaerah = Integer.parseInt(daerah);
		  int idMukim = Integer.parseInt(mukim);
		  int idLuas = Integer.parseInt(luas);
		  int idLot = Integer.parseInt(lot);
		  //int idLuasAmbil = Integer.parseInt(luasambil);
		  
		  int luasAmbil = Integer.parseInt(edit_anggaran_luas);
		  
		  db = new Db();
		  
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_hakmilik", idHakmilik);
		  r.add("id_negeri", idNegeri);
		  r.add("id_jenishakmilik", idJenisHakMilik);
		  r.add("id_daerah", idDaerah);
		  r.add("id_mukim", idMukim);
		  r.add("id_unitluaslot", idLuas);
		  r.add("id_lot", idLot);
		  
		  r.add("no_hakmilik", edit_no_hakmilik);
		  r.add("no_lot", edit_no_lot);
		  r.add("luas_lot", edit_luas_lot);
		  //r.add("no_pt", edit_no_pt);
		  r.add("luas_ambil", luasAmbil);
		  sql = r.getSQLUpdate("tblppthakmilik");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close updateMaklumatTanah
	
	public static void deleteMaklumatTanah(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
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
	public Vector getMaklumatTanah(){
		return maklumatTanah;
	}
	
}
