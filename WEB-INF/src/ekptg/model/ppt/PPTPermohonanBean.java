package ekptg.model.ppt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.entities.Kementerian;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.permohonan.IPermohonan;

public class PPTPermohonanBean implements IPermohonan {
	
	static Logger myLog = Logger.getLogger(ekptg.model.ppt.PPTPermohonanBean.class);
	PfdFail fail = null;
	Permohonan permohonan = null;
	//HtpPermohonan htpPermohonan = null;
	
	@Override
	public Permohonan getMaklumatPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
			sql = "SELECT P.NO_PERMOHONAN_ONLINE,RA.NAMA_AGENSI,RK.NAMA_KEMENTERIAN ";
			sql += " FROM TBLPPTPERMOHONAN P, TBLRUJAGENSI  RA, TBLRUJKEMENTERIAN RK ";
			sql += " WHERE P.ID_AGENSI = RA.ID_AGENSI ";
			sql += " AND RA.ID_KEMENTERIAN = RK.ID_KEMENTERIAN ";
			sql += " AND P.ID_PERMOHONAN = '"+ idPermohonan +"'";
			//myLog.info("Permohonan:::findPermohonan::sql::"+sql);
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);		
			if(rs.next()){
				fail = new PfdFail();
				permohonan = new Permohonan();											     
				//permohonan.setIdAgensi(rs.getLong("ID_AGENSI"));
				permohonan.setNamaAgensi(rs.getString("NAMA_AGENSI"));
			    permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN_ONLINE"));
			    permohonan.setIdPermohonan(idPermohonan);
				//permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				//fail.setIdFail(rs.getLong("ID_FAIL"));
				//fail.setNoFail(rs.getString("NO_FAIL"));
				//fail.setIdKementerian(rs.getString("ID_KEMENTERIAN"));
				fail.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				Kementerian kementerian = new Kementerian();
				fail.setKementerian(kementerian);
				//fail.setIdNegeri(rs.getString("ID_NEGERI"));
	 			//fail.setTajukFail(rs.getString("TAJUK_FAIL"));
	 			permohonan.setPfdFail(fail);
	 			conn.commit();
	 			
			}
										    	
		 } finally {
		      if (db != null) db.close();
		    }
		

		return permohonan;
	}


	
}
