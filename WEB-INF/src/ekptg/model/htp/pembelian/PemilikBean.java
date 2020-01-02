package ekptg.model.htp.pembelian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Negeri;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.PihakBerkepentingan;

public class PemilikBean implements IPemilik {

	//@Override
	public void deletePemilik(String idPihakBerkepentigan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            sql = "DELETE FROM Tblhtppihakberkepentingan WHERE id_Pihakberkepentingan="+idPihakBerkepentigan;
            
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

	//@Override
	public PihakBerkepentingan findPemilik(String idPihakBerkepentigan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		PihakBerkepentingan pihak = new PihakBerkepentingan();
		try{
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("A.id_Pihakberkepentingan");
			r.add("A.id_Hakmilikurusan");
			r.add("A.nama");
			r.add("A.no_Rujukan");		  
			r.add("A.alamat1");
			r.add("A.alamat2");
			r.add("A.alamat3");
			r.add("A.poskod");
			r.add("A.ID_JENISPB");
			r.add("A.id_Daerah");
			r.add("A.id_Negeri");
			r.add("A.no_Tel");
			r.add("A.no_Fax");
			r.set("A.id_Pihakberkepentingan", idPihakBerkepentigan);
			sql = r.getSQLSelect("Tblhtppihakberkepentingan A");
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				pihak = new PihakBerkepentingan();
				
            	pihak.setIdpihakberkepentingan(idPihakBerkepentigan);
            	pihak.setIdHakmilikurusanPB(rs.getString("id_Hakmilikurusan"));
            	pihak.setNama(rs.getString("nama"));
            	pihak.setNoRujukan(rs.getString("no_Rujukan"));
            	pihak.setAlamat1(rs.getString("alamat1"));
            	pihak.setAlamat2(rs.getString("alamat2"));
            	pihak.setAlamat3(rs.getString("alamat3"));
            	pihak.setJenisPB(rs.getString("id_jenispb"));
            	pihak.setPoskod(rs.getString("poskod"));
            	pihak.setTel(rs.getString("no_Tel"));
            	pihak.setFax(rs.getString("no_Fax"));
            	pihak.setIdNegeri(rs.getString("id_negeri"));
            	pihak.setIdDaerah(rs.getString("id_daerah"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return pihak;
	}

	//@Override
	public Vector<PihakBerkepentingan> findPemilikByPermohonan(String idPermohonan) {
		System.out.println("======findPemilikByPermohonan==========" + idPermohonan);
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		Negeri negeri = null;
		Daerah daerah = null;
		Vector<PihakBerkepentingan> v = new Vector<PihakBerkepentingan>();
		try{
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("B.ID_PERMOHONAN");
			r.add("A.id_Pihakberkepentingan");
			r.add("B.id_Hakmilikurusan");
			r.add("B.no_hakmilik");
			r.add("A.nama");
			r.add("A.no_Rujukan");		  
			r.add("A.alamat1");
			r.add("A.alamat2");
			r.add("A.alamat3");
			r.add("A.poskod");
			r.add("A.id_Daerah");
			r.add("A.id_Negeri");
			r.add("A.no_Tel");
			r.add("A.no_Fax");
		    r.add("RN.NAMA_NEGERI");
		    r.add("RD.NAMA_DAERAH");
		    r.add("(SELECT KOD_JENIS_HAKMILIK FROM tblrujjenishakmilik WHERE ID_JENISHAKMILIK=B.ID_JENISHAKMILIK) jenis_hakmilik");
			r.add("A.ID_HAKMILIKURUSAN",r.unquote("B.ID_HAKMILIKURUSAN"));
			r.set("B.ID_PERMOHONAN", idPermohonan);
		    r.add("A.ID_DAERAH",r.unquote("RD.ID_DAERAH(+)"));
		    r.add("A.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
			sql = r.getSQLSelect("Tblhtppihakberkepentingan A,TBLHTPHAKMILIKURUSAN B,TBLRUJNEGERI RN,TBLRUJDAERAH RD");
			
			ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
            	PihakBerkepentingan pihak = new PihakBerkepentingan();
		    	negeri = new Negeri();
		    	daerah = new Daerah();
               	HakmilikUrusan urusan = new HakmilikUrusan();
               	pihak.setIdpihakberkepentingan(rs.getString("id_Pihakberkepentingan"));
            	pihak.setIdHakmilikUrusan(rs.getString("id_Hakmilikurusan"));
            	pihak.setNama(rs.getString("nama"));
            	pihak.setNoRujukan(rs.getString("no_Rujukan"));
            	pihak.setAlamat1(rs.getString("alamat1"));
            	pihak.setAlamat2(rs.getString("alamat2"));
            	pihak.setAlamat3(rs.getString("alamat3"));
            	pihak.setPoskod(rs.getString("poskod"));
            	pihak.setTel(rs.getString("no_Tel"));
            	pihak.setFax(rs.getString("no_Fax"));
		    	negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
		    	daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
		    	pihak.setNegeri(negeri);
		    	pihak.setDaerah(daerah);
            	pihak.setNoHakmilik(rs.getString("no_hakmilik"));
            	
				urusan.setNohakmilik(rs.getString("no_Hakmilik"));
				urusan.setKodjenishakmilik(rs.getString("jenis_hakmilik"));
				pihak.setHakmilikUrusan(urusan);
            	
            	v.addElement(pihak);
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
	public PihakBerkepentingan savePemilik(PihakBerkepentingan pemilik) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			 long IdPihakberkepentingan = DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ");
			 r = new SQLRenderer();
			 r.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			 r.add("id_Hakmilikurusan", pemilik.getIdHakmilikUrusan());
			 r.add("nama", pemilik.getNama());
			 r.add("id_Jenisnopb", pemilik.getJenisPB());
			 r.add("id_Jenispb", pemilik.getJenisPB());
			 r.add("no_Rujukan",pemilik.getNoRujukan());		  
			 r.add("alamat1", pemilik.getAlamat1());
			 r.add("alamat2", pemilik.getAlamat2());
			 r.add("alamat3", pemilik.getAlamat3());
			 r.add("poskod", pemilik.getPoskod());
			 r.add("id_Daerah", pemilik.getIdDaerah());
			 r.add("id_Negeri", pemilik.getIdNegeri());
			 r.add("no_Tel", pemilik.getTel());
			 r.add("no_Fax", pemilik.getFax());
			 sql = r.getSQLInsert("Tblhtppihakberkepentingan");
			 
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
		return pemilik;
	}

	//@Override
	public PihakBerkepentingan updatePemilik(PihakBerkepentingan pemilik) {
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
			r.update("id_Pihakberkepentingan", pemilik.getIdpihakberkepentingan());
			r.add("id_Hakmilikurusan", pemilik.getIdHakmilikurusanPB());
			r.add("nama", pemilik.getNama());
			r.add("no_Rujukan",pemilik.getNoRujukan());		  
			r.add("alamat1", pemilik.getAlamat1());
			r.add("alamat2", pemilik.getAlamat2());
			r.add("alamat3", pemilik.getAlamat3());
			r.add("poskod", pemilik.getPoskod());
			r.add("id_Daerah", pemilik.getIdDaerah());
			r.add("id_Negeri", pemilik.getIdNegeri());
			r.add("no_Tel", pemilik.getTel());
			r.add("no_Fax", pemilik.getFax());
			sql = r.getSQLUpdate("Tblhtppihakberkepentingan");
			
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
		return pemilik;
	}

}
