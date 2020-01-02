package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.entities.Agensi;
import ekptg.model.entities.Kementerian;
import ekptg.model.entities.UserKementerian;

public class PenggunaKementerianBean implements IPenggunaKementerian {
	
	static Logger myLog = Logger.getLogger(ekptg.model.htp.PenggunaKementerianBean.class);
	
	@Override
	public UserKementerian getKementerian(String idUser){
		Connection conn = null;
		Db db = null;
		UserKementerian uk = new UserKementerian();
		SQLRenderer r = null;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("RK.ID_KEMENTERIAN");
			r.add("RK.NAMA_KEMENTERIAN");
			r.add("RA.ID_AGENSI");
			r.add("RA.NAMA_AGENSI");
			//r.add("RK.ID_KEMENTERIAN");
			r.add("U.USER_ID");
			r.add("U.USER_ID",r.unquote(idUser));
			r.add("U.USER_ID",r.unquote("UK.USER_ID"));
			r.add("UK.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
			r.add("RA.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));

			sql = r.getSQLSelect("USERS U,USERS_KEMENTERIAN UK,TBLRUJAGENSI RA,TBLRUJKEMENTERIAN RK" +
				"" +
				"");

			//myLog.info("getOnline:"+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();
				
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));				
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));				
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				agensi.setKementerian(kem);
				uk.setAgensi(agensi);
				uk.setIdUser(rs.getLong("USER_ID"));
				
			}
			
		}catch(Exception e){
				e.printStackTrace();
			
	 	} finally {
	 		if (db != null) db.close();
	 	}

		return uk;
	}


}
