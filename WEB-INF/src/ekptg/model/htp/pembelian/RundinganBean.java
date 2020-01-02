package ekptg.model.htp.pembelian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.model.htp.entity.Rundingan;

public class RundinganBean implements IRundingan {

	@Override
	public Rundingan findByPermohonan(String idPermohonan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		Rundingan rundingan = null;
		try{
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			
			r = new SQLRenderer();
			r.add("ID_PERMOHONAN");
			r.add("ID_MAKLUMATMSYRT");
			r.add("UNIT_BERSAMAAN");
			r.add("HARGA_BERSAMAAN");
			r.add("NILAI_TNH");
			r.add("NILAI_BGN");
			r.add("HARGA_BELI");
			r.add("HARGA_SETUJU");
			r.add("TEMPOH_SERAH");
			r.add("KEPUTUSAN");
			r.add("ULASAN");
			r.set("ID_PERMOHONAN",idPermohonan);
			sql = r.getSQLSelect("TBLHTPMAKLUMATMSYRT");
			
			ResultSet rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	rundingan = new Rundingan();
		    	rundingan.setIdMaklumatMysrt(rs.getString("ID_MAKLUMATMSYRT"));
		    	rundingan.setUnitBersamaan(rs.getString("UNIT_BERSAMAAN"));
		    	rundingan.setHargaBersamaan(rs.getDouble("HARGA_BERSAMAAN"));
		    	rundingan.setNilaiTanah(rs.getDouble("NILAI_TNH"));
		    	rundingan.setNilaiBangunan(rs.getDouble("NILAI_BGN"));
		    	rundingan.setHargaTawaran(rs.getDouble("HARGA_BELI"));
		    	rundingan.setHargaSetuju(rs.getDouble("HARGA_SETUJU"));
		    	rundingan.setTempohSerahan(rs.getInt("TEMPOH_SERAH"));
		    	rundingan.setUlasan(rs.getString("ULASAN"));
		    	rundingan.setKeputusan(rs.getString("KEPUTUSAN"));
		    	
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return rundingan;
	}

	@Override
	public Rundingan saveRundingan(Rundingan rundingan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			long id = DB.getNextID("TBLHTPMAKLUMATMSYRT_SEQ");
			rundingan.setIdMaklumatMysrt(id);
			
			r = new SQLRenderer();
			r.add("ID_MAKLUMATMSYRT",rundingan.getIdMaklumatMysrt());
			r.add("ID_PERMOHONAN",rundingan.getPermohonan().getIdPermohonan());
			r.add("UNIT_BERSAMAAN",rundingan.getUnitBersamaan());
			r.add("HARGA_BERSAMAAN",rundingan.getHargaBersamaan());
			r.add("NILAI_TNH",rundingan.getNilaiTanah());
			r.add("NILAI_BGN",rundingan.getNilaiBangunan());
			r.add("HARGA_BELI",rundingan.getHargaTawaran());
			r.add("HARGA_SETUJU",rundingan.getHargaSetuju());
			r.add("TEMPOH_SERAH",rundingan.getTempohSerahan());
			r.add("KEPUTUSAN",rundingan.getKeputusan());
			r.add("ULASAN",rundingan.getUlasan());
			sql = r.getSQLInsert("TBLHTPMAKLUMATMSYRT");
			System.out.println();
			stmt.executeUpdate(sql);	
			conn.commit();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return rundingan;
	}

	@Override
	public Rundingan updateRundingan(Rundingan rundingan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			r = new SQLRenderer();
			r.update("ID_MAKLUMATMSYRT",rundingan.getIdMaklumatMysrt());
			r.add("UNIT_BERSAMAAN",rundingan.getUnitBersamaan());
			r.add("HARGA_BERSAMAAN",rundingan.getHargaBersamaan());
			r.add("NILAI_TNH",rundingan.getNilaiTanah());
			r.add("NILAI_BGN",rundingan.getNilaiBangunan());
			r.add("HARGA_BELI",rundingan.getHargaTawaran());
			r.add("HARGA_SETUJU",rundingan.getHargaSetuju());
			r.add("TEMPOH_SERAH",rundingan.getTempohSerahan());
			r.add("KEPUTUSAN",rundingan.getKeputusan());
			r.add("ULASAN",rundingan.getUlasan());
			sql = r.getSQLUpdate("TBLHTPMAKLUMATMSYRT");
			
			stmt.executeUpdate(sql);	
			conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
			
		return rundingan;
	}

}
