package ekptg.model.ppt;

import java.sql.Statement;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmHakmilikSementaraMaklumatPampasanPBSerahCekData {
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraMaklumatPampasanPBSerahCekData.class);
public static void updateSerahCek(Hashtable data)throws Exception{
	
		Db db = null;
		String sql = "";
//		String sql2 = "";
		
		try{
			String idBayaran = (String)data.get("idBayaran");
			String tarikhSerahCek = (String)data.get("tarikhSerahCek");
			String flagSerahCek = (String)data.get("flagSerahCek");
			String id_permohonan = (String)data.get("id_permohonan");
			myLogger.info("ID PERMOHONAN >> "+id_permohonan);
			String idKemaskini = (String)data.get("idKemaskini");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("id_Bayaran",idBayaran);
			r.add("tarikh_Serah_Cek",r.unquote("to_date('" + tarikhSerahCek + "','dd/MM/yyyy')"));
			r.add("flag_Serah_Cek",flagSerahCek);
			r.add("id_Kemaskini",idKemaskini);
			r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptbayaran");
			stmt.executeUpdate(sql);
			
			myLogger.info("UPDATE tblpptbayaran :: "+sql);
			
//			Statement stmtB = db.getStatement();
//			SQLRenderer rB = new SQLRenderer();
			
			r.clear();
			
			r.update("id_Permohonan",id_permohonan);
			r.add("id_Status","72");
			sql = r.getSQLUpdate("tblpptpermohonan");
			stmt.executeUpdate(sql);
		
			myLogger.info("UPDATE tblpptpermohonan :: "+sql);
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}

}
