package ekptg.model.htp.pembelian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Negeri;
import ekptg.model.htp.entity.Peguam;
import ekptg.model.htp.entity.Permohonan;

public class PembelianPeguamBean implements IPembelianPeguam {

	//@Override
	public Peguam findByIdPermohonan(String idPermohonan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		Peguam peguam = null;
		Negeri negeri = null;
		Daerah daerah = null;
		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("peg.id_Peguam");
		    r.add("peg.id_Permohonan");
		    r.add("peg.Nama");
		    r.add("peg.no_Rujukan");
		    r.add("peg.Alamat1");
		    r.add("peg.Alamat2");
		    r.add("peg.Alamat3");
		    r.add("peg.Poskod");
		    r.add("peg.id_Daerah");
		    r.add("peg.id_Negeri");
		    r.add("peg.No_Tel");
		    r.add("peg.No_Fax");
		    r.add("RN.NAMA_NEGERI");
		    r.add("RD.NAMA_DAERAH");
		    r.set("peg.id_Permohonan", idPermohonan);
		    r.add("PEG.ID_DAERAH",r.unquote("RD.ID_DAERAH(+)"));
		    r.add("PEG.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
		    sql = r.getSQLSelect("Tblhtppeguam peg,TBLRUJNEGERI RN,TBLRUJDAERAH RD");
		    //
		    ResultSet rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	peguam = new Peguam();
		    	negeri = new Negeri();
		    	daerah = new Daerah();
		    	
		    	Permohonan permohonan = new Permohonan();
		    	peguam.setIdpeguam(rs.getLong("id_Peguam"));
		    	peguam.setNama(rs.getString("Nama"));
		    	peguam.setNoRujukan(rs.getString("no_Rujukan"));
		    	peguam.setAlamat1(rs.getString("Alamat1"));
		    	peguam.setAlamat2(rs.getString("Alamat2"));
		    	peguam.setAlamat3(rs.getString("Alamat3"));
		    	peguam.setPoskod(rs.getString("Poskod"));
		    	peguam.setIdDaerah(rs.getLong("id_Daerah"));
		    	peguam.setIdNegeri(rs.getLong("id_Negeri"));
		    	peguam.setNoTel(rs.getString("No_Tel"));
		    	peguam.setNoFax(rs.getString("No_Fax"));
		    	negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
		    	daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
		    	peguam.setNegeri(negeri);
		    	peguam.setDaerah(daerah);
		    	peguam.setPermohonan(permohonan);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return peguam;
	}

	//@Override
	public Peguam simpanPeguam(Peguam peguam) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		
		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			long idPeguam = DB.getNextID("TBLHTPPEGUAM_SEQ");
			peguam.setIdpeguam(idPeguam);
			r = new SQLRenderer();
			r.add("id_Peguam", peguam.getIdpeguam());
			r.add("id_Permohonan", peguam.getPermohonan().getIdPermohonan());
			r.add("nama",peguam.getNama());
			r.add("no_Rujukan",peguam.getNoRujukan());
			r.add("alamat1",peguam.getAlamat1());
			r.add("alamat2",peguam.getAlamat2());
			r.add("alamat3",peguam.getAlamat3());
			r.add("poskod",peguam.getPoskod());
			r.add("id_Daerah",peguam.getIdDaerah());
			r.add("id_Negeri",peguam.getIdNegeri());
			r.add("no_Tel",peguam.getNoTel());
			r.add("no_Fax",peguam.getNoFax());
			sql = r.getSQLInsert("Tblhtppeguam");
			
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
		return peguam;
	}

	//@Override
	public Peguam updatePeguam(Peguam peguam) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			r = new SQLRenderer();
			r.update("id_peguam", peguam.getIdpeguam());
			r.add("nama",peguam.getNama());
			r.add("no_Rujukan",peguam.getNoRujukan());
			r.add("alamat1",peguam.getAlamat1());
			r.add("alamat2",peguam.getAlamat2());
			r.add("alamat3",peguam.getAlamat3());
			r.add("poskod",peguam.getPoskod());
			r.add("id_Daerah",peguam.getIdDaerah());
			r.add("id_Negeri",peguam.getIdNegeri());
			r.add("no_Tel",peguam.getNoTel());
			r.add("no_Fax",peguam.getNoFax());
			sql = r.getSQLUpdate("Tblhtppeguam");
			
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
		return peguam;
	}

}
