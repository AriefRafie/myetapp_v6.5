package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmHakmilikSementaraPerundinganTuanTanahData {
	
	static Vector paparTuanTanah = null;
	static Vector getStatus = null;
	static Vector idStatus = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static void updateTuanTanah(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
			String idSiasatan = (String)data.get("idSiasatan");
			String tarikhMilikTanah = (String)data.get("tarikhMilikTanah");
			String caraMilikTanah = (String)data.get("caraMilikTanah");
			String hargaTanah = (String)data.get("hargaTanah");
			String bebananTanah = (String)data.get("bebananTanah");
			String keteranganTuanTanah = (String)data.get("keteranganTuanTanah");
			String jenisTanaman = (String)data.get("jenisTanaman");
			String jenisBangunan = (String)data.get("jenisBangunan");
			String statusPecahSempadan = (String)data.get("statusPecahSempadan");
			String tarikhPecahSempadan = (String)data.get("tarikhPecahSempadan");
			String statusTukarSyarat = (String)data.get("statusTukarSyarat");
			String tarikhTukarSyarat = (String)data.get("tarikhTukarSyarat");
			String idKemaskini = (String)data.get("idKemaskini");
			String idPermohonan = (String)data.get("idPermohonan");
			
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("id_Siasatan",idSiasatan);
			r.add("tempoh_Milik_Tanah",tarikhMilikTanah);
			r.add("cara_Milik",caraMilikTanah);
			r.add("harga_Beli",hargaTanah);
			r.add("bebanan",bebananTanah);
			r.add("keterangan_Tuan_Tanah",keteranganTuanTanah);
			r.add("jenis_Tanaman",jenisTanaman);
			r.add("jenis_Bangunan",jenisBangunan);
			r.add("flag_Pecah_Sempadan",statusPecahSempadan);
			r.add("tarikh_Pecah_Sempadan",r.unquote("to_date('" + tarikhPecahSempadan + "','dd/MM/yyyy')"));
			r.add("flag_Tukar_Syarat",statusTukarSyarat);
			r.add("tarikh_Tukar_Syarat",r.unquote("to_date('" + tarikhTukarSyarat + "','dd/MM/yyyy')"));
			r.add("id_Kemaskini",idKemaskini);
			r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			
			sql = r.getSQLUpdate("tblpptsiasatan");
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
			
			
			
		}
		finally {
			if(db != null) db.close();
		}
	}
	public static Vector getStatus(String idPermohonan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			 getStatus = new Vector();
			 db = new Db();
			 Statement stmtS = db.getStatement();

			    sql = "SELECT ID_STATUS FROM TBLRUJSTATUS WHERE KOD_STATUS = '45' AND ID_SEKSYEN = 1";
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
	public static void setDataTuanTanah(String idSiasatan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparTuanTanah = new Vector();
			 db = new Db();
		     Statement stmt = db.getStatement();
		     
		     sql = "SELECT TEMPOH_MILIK_TANAH,CARA_MILIK,HARGA_BELI,BEBANAN,KETERANGAN_TUAN_TANAH," +
		     		" JENIS_TANAMAN,JENIS_BANGUNAN,FLAG_PECAH_SEMPADAN,TARIKH_PECAH_SEMPADAN,FLAG_TUKAR_SYARAT," +
		     		" TARIKH_TUKAR_SYARAT FROM TBLPPTSIASATAN WHERE ID_SIASATAN = '"+idSiasatan+"'";
		     
		     ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("TEMPOH_MILIK_TANAH", rs.getString("TEMPOH_MILIK_TANAH")==null?"":rs.getString("TEMPOH_MILIK_TANAH"));
		    	  h.put("CARA_MILIK", rs.getString("CARA_MILIK")==null?"":rs.getString("CARA_MILIK"));
		    	  h.put("HARGA_BELI", rs.getString("HARGA_BELI")==null?"":rs.getString("HARGA_BELI"));
		    	  h.put("BEBANAN", rs.getString("BEBANAN")==null?"":rs.getString("BEBANAN"));
		    	  h.put("KETERANGAN_TUAN_TANAH", rs.getString("KETERANGAN_TUAN_TANAH")==null?"":rs.getString("KETERANGAN_TUAN_TANAH"));
		    	  h.put("JENIS_TANAMAN", rs.getString("JENIS_TANAMAN")==null?"":rs.getString("JENIS_TANAMAN"));
		    	  h.put("JENIS_BANGUNAN", rs.getString("JENIS_BANGUNAN")==null?"":rs.getString("JENIS_BANGUNAN"));
		    	  h.put("FLAG_PECAH_SEMPADAN", rs.getString("FLAG_PECAH_SEMPADAN")==null?"":rs.getString("FLAG_PECAH_SEMPADAN"));
		    	  h.put("TARIKH_PECAH_SEMPADAN", rs.getString("TARIKH_PECAH_SEMPADAN")==null?"":sdf.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
		    	  h.put("FLAG_TUKAR_SYARAT", rs.getString("FLAG_TUKAR_SYARAT")==null?"":rs.getString("FLAG_TUKAR_SYARAT"));
		    	  h.put("TARIKH_TUKAR_SYARAT", rs.getString("TARIKH_TUKAR_SYARAT")==null?"":sdf.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
		    	  paparTuanTanah.addElement(h);
		      }

		}
		finally {
			if(db != null) db.close();
		}

	}
	
	public static Vector getDataTuanTanah(){
		return paparTuanTanah;
	}

}
