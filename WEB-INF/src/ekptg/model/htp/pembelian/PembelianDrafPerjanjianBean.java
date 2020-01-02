package ekptg.model.htp.pembelian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.model.htp.entity.DrafPerjanjian;
import ekptg.model.htp.entity.Permohonan;

public class PembelianDrafPerjanjianBean implements IPembelianDrafPerjanjian {

	//@Override
	public Vector<DrafPerjanjian> getDrafList(String idPermohonan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		Vector<DrafPerjanjian> v = new Vector<DrafPerjanjian>();
		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("id_derafperjanjian");
	        r.add("id_permohonan");
	      	r.add("TO_CHAR(tarikh_terima_perjanjian,'DD/MM/YYYY') AS tarikh_terima_perjanjian");
	       	r.add("TO_CHAR(tarikh_hantar_deraf,'DD/MM/YYYY') AS tarikh_hantar_deraf");
	        r.add("id_masuk");
	        r.add("ulasan_seksyen");
	        r.set("id_permohonan", idPermohonan);
		    sql = r.getSQLSelect("TBLHTPDERAFPERJANJIAN");
		    
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()){
		    	Permohonan permohonan = new Permohonan();
		    	DrafPerjanjian draf = new DrafPerjanjian();
		    	draf.setIdDrafPerjanjian(rs.getLong("id_derafperjanjian"));
		    	draf.setTarikhHantar(rs.getString("tarikh_hantar_deraf"));
		    	draf.setTarikhTerima(rs.getString("tarikh_terima_perjanjian"));
		    	draf.setUlasan(rs.getString("ulasan_seksyen"));
		    	draf.setPermohonan(permohonan);
		    	v.addElement(draf);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return v;
	}

	//@Override
	public DrafPerjanjian getDrafPerjanjian(String idDraf) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		DrafPerjanjian draf = null;
		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("id_derafperjanjian");
	        r.add("id_permohonan");
	      	r.add("TO_CHAR(tarikh_terima_perjanjian,'DD/MM/YYYY') AS tarikh_terima_perjanjian");
	       	r.add("TO_CHAR(tarikh_hantar_deraf,'DD/MM/YYYY') AS tarikh_hantar_deraf");
	        r.add("id_masuk");
	        r.add("ulasan_seksyen");
	        r.add("id_derafperjanjian");
	        r.set("id_derafperjanjian", idDraf);
		    sql = r.getSQLSelect("TBLHTPDERAFPERJANJIAN");
		    
		    ResultSet rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	Permohonan permohonan = new Permohonan();
		    	draf = new DrafPerjanjian();
		    	draf.setIdDrafPerjanjian(rs.getLong("id_derafperjanjian"));
		    	draf.setTarikhHantar(rs.getString("tarikh_hantar_deraf"));
		    	draf.setTarikhTerima(rs.getString("tarikh_terima_perjanjian"));
		    	draf.setUlasan(rs.getString("ulasan_seksyen"));
		    	draf.setPermohonan(permohonan);
		    }
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return draf;
	}

	//@Override
	public DrafPerjanjian saveDrafPerjanjian(DrafPerjanjian draf) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
            String tarikhTerima = draf.getTarikhTerima();
            String tarikhHantar = draf.getTarikhHantar();

            String TTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
            String THantar = "to_date('" + tarikhHantar + "','dd/MM/yyyy')";

            long idDrafPerjanjian = DB.getNextID("TBLHTPDERAFPERJANJIAN_SEQ");
            draf.setIdDrafPerjanjian(idDrafPerjanjian);
            r = new SQLRenderer();
            r.add("id_derafperjanjian", draf.getIdDrafPerjanjian());
            r.add("id_permohonan", draf.getPermohonan().getIdPermohonan());
            if(!tarikhTerima.equals(""))
            	r.add("tarikh_terima_perjanjian", r.unquote(TTerima));
            else
            	r.add("tarikh_terima_perjanjian",tarikhTerima);
            if(!tarikhHantar.equals(""))
            	r.add("tarikh_hantar_deraf", r.unquote(THantar));
            else
            	r.add("tarikh_hantar_deraf", tarikhHantar);
            //r.add("id_masuk", draf.getIdMasuk());
            r.add("ulasan_seksyen", draf.getUlasan());
            String sql = r.getSQLInsert("TBLHTPDERAFPERJANJIAN");
            
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
		return draf;
	}

	//@Override
	public DrafPerjanjian updateDrafPerjanjian(DrafPerjanjian draf) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
            String tarikhTerima = draf.getTarikhTerima();
            String tarikhHantar = draf.getTarikhHantar();

            String TTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
            String THantar = "to_date('" + tarikhHantar + "','dd/MM/yyyy')";

            
            r = new SQLRenderer();
            r.update("id_derafperjanjian", draf.getIdDrafPerjanjian());
            if(!tarikhTerima.equals(""))
            	r.add("tarikh_terima_perjanjian", r.unquote(TTerima));
            else
            	r.add("tarikh_terima_perjanjian",tarikhTerima);
            if(!tarikhHantar.equals(""))
            	r.add("tarikh_hantar_deraf", r.unquote(THantar));
            else
            	r.add("tarikh_hantar_deraf", tarikhHantar);
            //r.add("id_masuk", draf.getIdMasuk());
            r.add("ulasan_seksyen", draf.getUlasan());
            String sql = r.getSQLUpdate("TBLHTPDERAFPERJANJIAN");
            
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
		return draf;
	}

	//@Override
	public void deleteDrafPerjanjian(String idDraf) {
		Db db = null;
		Connection conn = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            String sql ="DELETE FROM TBLHTPDERAFPERJANJIAN WHERE ID_DERAFPERJANJIAN ="+idDraf;
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
	}

}
