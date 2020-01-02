package ekptg.model.ppk.perintah;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;

public class FrmPerintahBean implements IPerintah{
	
	private static Logger myLog = Logger.getLogger(ekptg.model.ppk.perintah.FrmPerintahBean.class);
	Connection conn = null;
	Db db = null;
	private IHtp iHTP = null;  
	SQLRenderer r = null;
	String sql = "";

	public boolean isJenisPerintah(String noFail) throws Exception {
		boolean returnValue = false;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "SELECT F.NO_FAIL, PER.ID_JENISPERINTAH " +
			" FROM " +
			" TBLPPKPERINTAH PER " +
			" ,TBLPPKPERBICARAAN PERB " +
			" ,TBLPPKKEPUTUSANPERMOHONAN KEPP " +
			" ,TBLPPKPERMOHONAN P " +
			" ,TBLPFDFAIL F " +
			" WHERE " +
			" PER.ID_PERBICARAAN                  = PERB.ID_PERBICARAAN " +
			" AND PER.ID_JENISPERINTAH = 6 " + 
			" AND PERB.ID_KEPUTUSANPERMOHONAN     = KEPP.ID_KEPUTUSANPERMOHONAN " +
			" AND KEPP.ID_PERMOHONAN              = P.ID_PERMOHONAN " +
			" AND P.ID_FAIL                       = F.ID_FAIL " +
			" AND F.NO_FAIL = '"+noFail+"'"+
			"";
			myLog.info("isJenisPerintah:sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				returnValue = true;				
			}
			
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();

	 	}
		return returnValue;
	
	}
	
	public boolean isPerintahBatalWasiatPermohonan(String noFail) throws Exception {
		boolean returnValue = false;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "SELECT F.NO_FAIL, PER.ID_JENISPERINTAH " +
			" FROM " +
			" TBLPPKPERINTAH PER " +
			" ,TBLPPKPERBICARAAN PERB " +
			" ,TBLPPKKEPUTUSANPERMOHONAN KEPP " +
			" ,TBLPPKPERMOHONAN P " +
			" ,TBLPFDFAIL F " +
			" WHERE " +
			" PER.ID_PERBICARAAN                  = PERB.ID_PERBICARAAN " +
			" AND PER.ID_JENISPERINTAH = 6 " + 
			" AND PERB.ID_KEPUTUSANPERMOHONAN     = KEPP.ID_KEPUTUSANPERMOHONAN " +
			" AND KEPP.ID_PERMOHONAN              = P.ID_PERMOHONAN " +
			" AND P.ID_FAIL                       = F.ID_FAIL " +
			" AND (KEPP.FLAG_SEBABPINDAHMAHKAMAH is null or KEPP.FLAG_SEBABPINDAHMAHKAMAH = 1) " +
			" AND F.NO_FAIL = '"+noFail+"'"+
			"";
			myLog.info("isJenisPerintah:sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				returnValue = true;				
			}
			
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();

	 	}
		return returnValue;
	
	}
	
	public boolean isPerintahBatalWasiatPerbicaraan(String noFail) throws Exception {
		boolean returnValue = false;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "SELECT F.NO_FAIL, PER.ID_JENISPERINTAH " +
			" FROM " +
			" TBLPPKPERINTAH PER " +
			" ,TBLPPKPERBICARAAN PERB " +
			" ,TBLPPKKEPUTUSANPERMOHONAN KEPP " +
			" ,TBLPPKPERMOHONAN P " +
			" ,TBLPFDFAIL F " +
			" WHERE " +
			" PER.ID_PERBICARAAN                  = PERB.ID_PERBICARAAN " +
			" AND PER.FLAG_BATAL = '1' " + 
			" AND PERB.ID_KEPUTUSANPERMOHONAN     = KEPP.ID_KEPUTUSANPERMOHONAN " +
			" AND KEPP.ID_PERMOHONAN              = P.ID_PERMOHONAN " +
			" AND P.ID_FAIL                       = F.ID_FAIL " +
			" AND (KEPP.FLAG_SEBABPINDAHMAHKAMAH is null or KEPP.FLAG_SEBABPINDAHMAHKAMAH = 1) " +
			" AND F.NO_FAIL = '"+noFail+"'"+
			"";
			myLog.info("isJenisPerintah:sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				returnValue = true;				
			}
			
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();

	 	}
		return returnValue;
	
	}
	
	public boolean isPindahWasiat(String noFail) throws Exception {
		boolean returnValue = false;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "SELECT F.NO_FAIL " +
					" FROM " +
					" TBLPPKKEPUTUSANPERMOHONAN KEPP " +
					" ,TBLPPKPERMOHONAN P " +
					" ,TBLPFDFAIL F " +
					" WHERE " +
					" KEPP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL " +
					" AND KEPP.KEPUTUSAN_PERMOHONAN = 50 " +
					" AND KEPP.FLAG_SEBABPINDAHMAHKAMAH = 2 " +
					" AND F.NO_FAIL = '"+noFail+"' ";
			
			myLog.info("isJenisPerintah:sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				returnValue = true;				
			}
			
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();

	 	}
		return returnValue;
	
	}


	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
	
		
}
