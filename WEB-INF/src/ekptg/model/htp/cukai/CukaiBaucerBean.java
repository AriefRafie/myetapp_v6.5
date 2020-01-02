package ekptg.model.htp.cukai;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.entities.Daerah;
import ekptg.model.htp.cukai.entity.BaucerCukai;
import ekptg.model.htp.cukai.entity.CukaiPenyata;
import ekptg.model.htp.entity.Baucer;
import ekptg.model.htp.entity.Resit;

public class CukaiBaucerBean implements ICukaiBaucer {
	
	static Logger myLog = Logger.getLogger(ekptg.model.htp.cukai.CukaiPenyataBean.class);
	private static SimpleDateFormat format =  new SimpleDateFormat("dd/MM/yyyy");
	private String sql = "";
	private CukaiPenyata cPenyata = null;
	private Vector list = null;
	private Double jumlahBaucer = 0.00;

	public BaucerCukai getSenaraiBaucer(String idNegeri, String idPeringkatBayaran
			, String peringkatBayaran,String tahun,String idBaucer) throws Exception {		
		Connection conn = null;
		Db db = null;
		String sql = "";
		BaucerCukai bCukai = null;		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			if(peringkatBayaran.equals("1")){
				sql = "SELECT "+ 
				" RN.NAMA_NEGERI nama_daerah,RN.ID_NEGERI id_daerah "+
			  	" ,THB.id_baucer, thb.no_baucer, thb.tarikh_baucer" +
			  	" , NVL(TO_CHAR(THB.TARIKH_TERIMA,'dd/mm/yyyy'),'01/01/1900') tarikh_terima "+
				" ,thb.no_resit, NVL(TO_CHAR(THB.TARIKH_RESIT,'dd/mm/yyyy'),'01/01/1900') tarikh_resit" +
				" , thb.amaun_baucer,thb.id_peringkatbayaran" +
				", (select count(*) from tblhtpbayarancukai where id_baucer=thb.id_baucer ) bil_bayaran "+
				" FROM tblhtpbaucer THB,TBLRUJNEGERI RN "+
				" WHERE "+
				" THB.ID_NEGERI = RN.ID_NEGERI "+
			  	//" AND THB.id_peringkatbayaran = '"+ idPeringkatBayaran +"' " +
			  			"";
			}else{
				sql = "SELECT "+
					" DISTINCT rd.nama_daerah, rd.id_daerah "+
					" , thb.id_baucer, thb.no_baucer, thb.tarikh_baucer" +
					" , NVL(TO_CHAR(THB.TARIKH_TERIMA,'dd/mm/yyyy'),'01/01/1900') tarikh_terima " +
					" ,thb.no_resit, NVL(TO_CHAR(THB.TARIKH_RESIT,'dd/mm/yyyy'),'01/01/1900') tarikh_resit" +
					" , thb.amaun_baucer, thb.id_peringkatbayaran "+
					", (select count(*) from tblhtpbayarancukai where id_baucer=thb.id_baucer ) bil_bayaran "+
					" FROM tblhtpbaucer thb, tblrujdaerah rd" +
					" WHERE THB.id_daerah = RD.id_daerah " +
					//" AND thb.id_peringkatbayaran = '"+ idPeringkatBayaran +"' "+
					//" AND thb.id_peringkatbayaran =TPCU.id_peringkatbayaran " +
					"";					
			}
			if(idPeringkatBayaran != null)	sql += " AND thb.id_peringkatbayaran = '"+idPeringkatBayaran+"'  ";
			if(tahun != null)	sql += " AND THB.TAHUN = '"+tahun+"'  ";
			if(idBaucer != null)	sql += " AND THB.ID_BAUCER = '"+idBaucer+"'  ";
			myLog.info("CukaiBaucer:::getSenaraiBaucer::sql::"+sql);
			rs = stmt.executeQuery(sql);			     
			while (rs.next()) {
				bCukai = new BaucerCukai();
				Daerah daerah= new Daerah();
				Resit resit = new Resit();
				Baucer baucer = new Baucer();
				
				daerah.setIdDaerah(rs.getLong("id_daerah"));
				daerah.setNamaDaerah(rs.getString("nama_daerah"));
				bCukai.setDaerah(daerah);
				bCukai.setId( rs.getLong("id_baucer"));
				bCukai.setNoRujukan(rs.getString("no_baucer"));
				bCukai.setIdPeringkat(rs.getLong("id_peringkatbayaran"));
	   		  	//h.put("peringkat_bayaran",idPeringkatBayaran);
				bCukai.setTarikh(rs.getDate("tarikh_baucer"));
				resit.setNoRujukan(rs.getString("no_resit"));
				resit.setTarikh(new Date(rs.getString("tarikh_resit")));
				bCukai.setResit(resit);
				bCukai.setJumlah(rs.getDouble("amaun_baucer"));
			    bCukai.setTarikhTerima(new Date(rs.getString("tarikh_terima")));
				baucer.setBil(rs.getInt("bil_bayaran"));
			    bCukai.setBaucer(baucer);
	
			}
			return bCukai;
			      
		} finally {
			if (db != null) db.close();
			      
		}
			
	}	

	@Override
	public void hapusBaucerCukai(String pk) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			//ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();	
			
			r.add("ID_BAUCER", r.unquote(pk));
			sql = r.getSQLDelete("tblhtpbaucer");
			//myLog.info(sql);
			stmt.executeUpdate(sql);

			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah Hapus Data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	    //return pk;
	    
	}
	
	public boolean isBaucer(String idBaucer)throws Exception {
		Db db = null;
		String sql = "";
		boolean returnValue= false;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_BAUCER,AMAUN_BAUCER "
				+ " FROM tblhtpbaucer "
				+ " WHERE ID_BAUCER ='" +idBaucer+"'"
				+ " ";			
			ResultSet rs = stmt.executeQuery(sql);
			returnValue = rs.next();
			jumlahBaucer = rs.getDouble("AMAUN_BAUCER");
		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}
	
	public Double getJumlahBaucer()throws Exception {	
		return jumlahBaucer;
	}	

}

