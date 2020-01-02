package ekptg.report.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class SlipPendengaran extends EkptgReportServlet{
	static Logger myLogger = Logger.getLogger(SlipPendengaran.class);
	
	
	
	public SlipPendengaran() {
		
		
		super.setReportName("SlipPendengaran");
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		Db db = null;
		try {
			db = new Db();
			String sql = "SELECT (SELECT NAMA_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH = '"+parameters.get("idDaerah")+"') AS NAMA_DAERAH," +
					" (SELECT NAMA_PEGAWAI FROM TBLPPKRUJUNIT WHERE ID_UNITPSK = '"+parameters.get("id_pegawai")+"') AS NAMA_PEGAWAI FROM DUAL";
			myLogger.info("CHECK JASPER SLIP"+sql);
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				parameters.put("daerahMohon",rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				parameters.put("namaPegawai",rs.getString("NAMA_PEGAWAI"));
			}
			
			
			/*
			String sql1 = "SELECT NAMA_PEGAWAI FROM TBLPPKRUJUNIT WHERE ID_UNITPSK = '"+parameters.get("id_pegawai")+"' ";
			Statement stmt1 = db.getStatement();
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			if (rs1.next()) {
				parameters.put("namaPegawai",rs1.getString("NAMA_PEGAWAI") == null ? "" : rs1.getString("NAMA_PEGAWAI"));			
			}
			*/
			
		}finally {
			if (db != null)
				db.close();
		}
		
		
		
		
	}

}
