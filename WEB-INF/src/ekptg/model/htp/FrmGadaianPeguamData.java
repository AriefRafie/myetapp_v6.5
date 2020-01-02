package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmGadaianPeguamData {
	private static Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
	private static Logger log = Logger.getLogger(ekptg.model.htp.FrmGadaianPeguamData.class);
	private static Db db = null;
	private static String sql = "";
	
	//*** query data from database
	public static void setListPeguam(String id)throws Exception {
	    list.clear();
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("peg.id_Peguam");
	      r.add("peg.id_Permohonan");
	      r.add("peg.Nama");
	      r.add("peg.Alamat1");
	      r.add("peg.Alamat2");
	      r.add("peg.Alamat3");
	      r.add("peg.Poskod");
	      r.add("peg.id_Daerah");
	      r.add("peg.ID_BANDAR");
	      r.add("peg.id_Negeri");
	      r.add("peg.No_Tel");
	      r.add("peg.No_Fax");
	      r.set("peg.id_Permohonan", id);     
	      sql = r.getSQLSelect("Tblhtppeguam peg");
	      log.info("setListPeguam::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable<String,String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("IdPeguam", Utils.isNull(rs.getString("ID_PEGUAM")));
	    	  h.put("Nama", Utils.isNull(rs.getString("NAMA")));
	    	  h.put("Alamat1", Utils.isNull(rs.getString("Alamat1")));
	    	  h.put("Alamat2", Utils.isNull(rs.getString("Alamat2")));
	    	  h.put("Alamat3", Utils.isNull(rs.getString("Alamat3")));	    	  
	    	  h.put("IdDaerah", Utils.isNull(rs.getString("ID_DAERAH")));	    	  
	    	  h.put("idBandar", Utils.isNull(rs.getString("ID_BANDAR")));	    	  
	    	  h.put("IdNegeri", Utils.isNull(rs.getString("ID_NEGERI")));	    	  
	    	  h.put("Poskod", Utils.isNull(rs.getString("POSKOD")));	    	  
    		  h.put("NoTel", Utils.isNull(rs.getString("NO_TEL")));
    		  h.put("NoFax", Utils.isNull(rs.getString("No_FAX")));
	    	  list.addElement(h);
	      }
	      //return list;
	    
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally {
	      if (db != null) db.close();
	    }
	  
	}
	  
	public static Vector<Hashtable<String,String>> getListPeguam(){
		  //log.info("FrmGadaianPeguamData: getListPeguam :" +list.size());
		  return list;
	} 	  
	//*** update data in database
	public static void update(Hashtable<String,String> data) throws Exception {
		try{
			String idPeguam = String.valueOf(data.get("idPeguam"));
			String nama = (String)data.get("nama");
			String alamat1 = (String)data.get("alamat1");
			String idBandarStr = String.valueOf(data.get("idBandar"));
			String idNegeriStr = String.valueOf(data.get("idNegeri"));

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
			int idDaerah = Integer.parseInt(FrmGadaianHakmilikData.getDaerahMengikutBandar(idBandarStr));
			int idBandar = Integer.parseInt(idBandarStr);
			int idNegeri = Integer.parseInt(idNegeriStr);
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

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Peguam", idPeguam);
			r.add("nama",nama);
			r.add("alamat1",alamat1);
			r.add("alamat2",alamat2);
			r.add("alamat3",alamat3);
			r.add("poskod",poskod);
			r.add("id_Daerah",idDaerah);
			r.add("ID_BANDAR",idBandar);
			r.add("id_Negeri",idNegeri);
			r.add("no_Tel",noTelefon);
			r.add("no_Fax",noFax);
			sql = r.getSQLUpdate("Tblhtppeguam");
			log.info("FrmGadaianPeguamData::Update::Tblhtppeguam = "+sql);
			stmt.executeUpdate(sql);
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (db != null) db.close();
		}
		  
	}
	//*** save data in database
	public static void simpan(Hashtable<String,String> data) throws Exception {
		try{
			String idPeguam = String.valueOf(DB.getNextID("TBLHTPPEGUAM_SEQ"));
			String idPermohonan = String.valueOf(data.get("idPermohonan"));
			String nama = (String)data.get("nama");
			String alamat1 = (String)data.get("alamat1");
			String alamat2;
			String idBandarStr = String.valueOf(data.get("idBandar"));
			String idNegeriStr = String.valueOf(data.get("idNegeri"));
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
			int idDaerah = Integer.parseInt(FrmGadaianHakmilikData.getDaerahMengikutBandar(idBandarStr));
			int idBandar = Integer.parseInt(idBandarStr);
			int idNegeri = Integer.parseInt(idNegeriStr);
		      
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

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Peguam", idPeguam);
			r.add("id_Permohonan", idPermohonan);
			r.add("nama",nama);
			r.add("alamat1",alamat1);
			r.add("alamat2",alamat2);
			r.add("alamat3",alamat3);
			r.add("poskod",poskod);
			r.add("id_Daerah",idDaerah);
			r.add("ID_BANDAR",idBandar);
			r.add("id_Negeri",idNegeri);
			r.add("no_Tel",noTelefon);
			r.add("no_Fax",noFax);
			sql = r.getSQLInsert("Tblhtppeguam");
			log.info("FrmGadaianPeguamData::Insert::Tblhtppeguam = "+sql);
			stmt.executeUpdate(sql);
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (db != null) db.close();
		}
		    
	}
	  
	  
}
