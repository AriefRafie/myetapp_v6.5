package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraPerundinganNilaianJPPHData {
	
	
	
	static Vector paparNilaian = null;
	
	public static String simpanNilaian(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			long idTanah = DB.getNextID("TBLPPTTANAH_SEQ");
			String idHakmilik = (String)data.get("idHakmilik");
			String hargaSeunitSO = (String)data.get("hargaSeunitSO");
			String unitHargaSO = (String)data.get("unitHargaSO");
			String hargaPasaranSO = (String)data.get("hargaPasaranSO");
			String penjejasanSO = (String)data.get("penjejasanSO");
			String pecahpisahSO = (String)data.get("pecahpisahSO");
			String naikNilaiSO = (String)data.get("naikNilaiSO");
			String hargaSeunitJPPH = (String)data.get("hargaSeunitJPPH");
			String unitHargaJPPH = (String)data.get("unitHargaJPPH");
			String hargaPasaranJPPH = (String)data.get("hargaPasaranJPPH");
			String penjejasanJPPH = (String)data.get("penjejasanJPPH");
			String pecahpisahJPPH = (String)data.get("pecahpisahJPPH");
			String naikNilaiJPPH = (String)data.get("naikNilaiJPPH");
			String idMasuk = (String)data.get("idMasuk");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("id_Tanah",idTanah);
			r.add("id_Hakmilik",idHakmilik);
			r.add("harga_Seunit_So",hargaSeunitSO);
			r.add("unit_Harga_So",unitHargaSO);
			r.add("harga_Pasaran_So",hargaPasaranSO);
			r.add("bayar_Penjejasan",penjejasanSO);
			r.add("bayar_Pecahpisah",pecahpisahSO);
			r.add("bayar_Naik_Nilaiso",naikNilaiSO);
			r.add("harga_Seunit_Jpph",hargaSeunitJPPH);
			r.add("unit_Harga",unitHargaJPPH);
			r.add("harga_Pasaran",hargaPasaranJPPH);
			r.add("amaun_Penjejasan_Jpph",penjejasanJPPH);
			r.add("amaun_Pecahpisah_Jpph",pecahpisahJPPH);
			r.add("naik_Nilai_Jpph",naikNilaiJPPH);
			r.add("id_Masuk",idMasuk);
			r.add("tarikh_Masuk",r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppttanah");
			stmt.executeUpdate(sql);
			
			return ""+idTanah;
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public static void updateNilaian(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			String idTanah = (String)data.get("idTanah");
			String idHakmilik = (String)data.get("idHakmilik");
			String hargaSeunitSO = (String)data.get("hargaSeunitSO");
			String unitHargaSO = (String)data.get("unitHargaSO");
			String hargaPasaranSO = (String)data.get("hargaPasaranSO");
			String penjejasanSO = (String)data.get("penjejasanSO");
			String pecahpisahSO = (String)data.get("pecahpisahSO");
			String naikNilaiSO = (String)data.get("naikNilaiSO");
			String hargaSeunitJPPH = (String)data.get("hargaSeunitJPPH");
			String unitHargaJPPH = (String)data.get("unitHargaJPPH");
			String hargaPasaranJPPH = (String)data.get("hargaPasaranJPPH");
			String penjejasanJPPH = (String)data.get("penjejasanJPPH");
			String pecahpisahJPPH = (String)data.get("pecahpisahJPPH");
			String naikNilaiJPPH = (String)data.get("naikNilaiJPPH");
			String idKemaskini = (String)data.get("idKemaskini");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("id_Tanah",idTanah);
			r.add("harga_Seunit_So",hargaSeunitSO);
			r.add("unit_Harga_So",unitHargaSO);
			r.add("harga_Pasaran_So",hargaPasaranSO);
			r.add("bayar_Penjejasan",penjejasanSO);
			r.add("bayar_Pecahpisah",pecahpisahSO);
			r.add("bayar_Naik_Nilaiso",naikNilaiSO);
			r.add("harga_Seunit_Jpph",hargaSeunitJPPH);
			r.add("unit_Harga",unitHargaJPPH);
			r.add("harga_Pasaran",hargaPasaranJPPH);
			r.add("amaun_Penjejasan_Jpph",penjejasanJPPH);
			r.add("amaun_Pecahpisah_Jpph",pecahpisahJPPH);
			r.add("naik_Nilai_Jpph",naikNilaiJPPH);
			r.add("id_Kemaskini",idKemaskini);
			r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppttanah");
			stmt.executeUpdate(sql);
			
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public static void setDataNilaian(String id_siasatan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
			paparNilaian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_TANAH,A.HARGA_SEUNIT_SO,A.UNIT_HARGA_SO,A.HARGA_PASARAN_SO,A.BAYAR_PENJEJASAN,A.BAYAR_PECAHPISAH," +
					" A.BAYAR_NAIK_NILAISO,A.HARGA_SEUNIT_JPPH,A.UNIT_HARGA,A.HARGA_PASARAN,A.AMAUN_PENJEJASAN_JPPH,A.AMAUN_PECAHPISAH_JPPH," +
					" A.NAIK_NILAI_JPPH FROM TBLPPTTANAH A,TBLPPTSIASATAN B WHERE A.ID_HAKMILIK = B.ID_HAKMILIK AND B.ID_SIASATAN = '"+id_siasatan+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_TANAH", rs.getString("ID_TANAH")==null?"":rs.getString("ID_TANAH"));
		    	  h.put("HARGA_SEUNIT_SO", rs.getString("HARGA_SEUNIT_SO")==null?"":rs.getString("HARGA_SEUNIT_SO"));
		    	  h.put("UNIT_HARGA_SO", rs.getString("UNIT_HARGA_SO")==null?"":rs.getString("UNIT_HARGA_SO"));
		    	  h.put("HARGA_PASARAN_SO", rs.getString("HARGA_PASARAN_SO")==null?"":rs.getString("HARGA_PASARAN_SO"));
		    	  h.put("BAYAR_PENJEJASAN", rs.getString("BAYAR_PENJEJASAN")==null?"":rs.getString("BAYAR_PENJEJASAN"));
		    	  h.put("BAYAR_PECAHPISAH", rs.getString("BAYAR_PECAHPISAH")==null?"":rs.getString("BAYAR_PECAHPISAH"));
		    	  h.put("BAYAR_NAIK_NILAISO", rs.getString("BAYAR_NAIK_NILAISO")==null?"":rs.getString("BAYAR_NAIK_NILAISO"));
		    	  h.put("HARGA_SEUNIT_JPPH", rs.getString("HARGA_SEUNIT_JPPH")==null?"":rs.getString("HARGA_SEUNIT_JPPH"));
		    	  h.put("UNIT_HARGA", rs.getString("UNIT_HARGA")==null?"":rs.getString("UNIT_HARGA"));
		    	  h.put("HARGA_PASARAN", rs.getString("HARGA_PASARAN")==null?"":rs.getString("HARGA_PASARAN"));
		    	  h.put("AMAUN_PENJEJASAN_JPPH", rs.getString("AMAUN_PENJEJASAN_JPPH")==null?"":rs.getString("AMAUN_PENJEJASAN_JPPH"));
		    	  h.put("AMAUN_PECAHPISAH_JPPH", rs.getString("AMAUN_PECAHPISAH_JPPH")==null?"":rs.getString("AMAUN_PECAHPISAH_JPPH"));
		    	  h.put("NAIK_NILAI_JPPH", rs.getString("NAIK_NILAI_JPPH")==null?"":rs.getString("NAIK_NILAI_JPPH"));

		    	  paparNilaian.addElement(h);
		    	  
		      }
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	public static Vector getDataNilaian(){
		return paparNilaian;
	}
	
	
	

}
