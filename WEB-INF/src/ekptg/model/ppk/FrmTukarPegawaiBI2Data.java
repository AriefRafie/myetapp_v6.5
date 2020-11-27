package ekptg.model.ppk;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.pm.entity.ActivityEvent;
import lebah.pm.entity.UserActivityEvent;
import lebah.template.DbPersistence;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;

public class FrmTukarPegawaiBI2Data {
	static Logger myLogger = Logger.getLogger(ekptg.model.ppk.FrmTukarPegawaiBI2Data.class);
	
	public Map getDetailUsers(HttpSession session, String nama, String user_id, String id_negeri, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();	
			sql += " SELECT * FROM USERS U,USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID " +
					//" AND NVL(UI.FLAG_AKTIF,' ') IN (' ','1') " +
					//" AND UI.ID_SEKSYEN = '2' " +
					" ";
			if(!user_id.equals(""))
			{
				sql += " AND U.USER_ID = '"+user_id+"' ";
			}
			else
			{
				if(!nama.equals(""))
				{
					sql += " AND UPPER(U.USER_NAME) = '"+nama.toUpperCase()+"' ";
				}
			}
			
			if(!id_negeri.equals(""))
			{
				sql += " AND UI.ID_NEGERI = '"+id_negeri+"' ";
			}
			
			myLogger.info(" Tukar Pegawa Pengendali 2 : SQL getDetailUsers :"+ sql);			
			rs = stmt.executeQuery(sql);
			Map h = null;		
			while (rs.next()) {	
				h = Collections.synchronizedMap(new HashMap());		
				h.put("USER_ID",rs == null ? "" :rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("USER_NAME",rs == null ? "" :rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("ID_JAWATAN",rs == null ? "" :rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
				h.put("ID_NEGERI",rs == null ? "" :rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));		
				h.put("ID_SEKSYEN",rs == null ? "" :rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));		
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}
	
	public String getDetailPegawaiList(HttpSession session, String username, Db db)throws Exception {
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();			
			sql += " SELECT LIST_ID_UNITPSK || (SELECT ',' || (rtrim (xmlagg (xmlelement (e, ID_UNITPSK || ','  )).extract ('//text()'))|| 0) FROM TBLPPKRUJUNIT  WHERE USER_ID = '"+USER_ID_SYSTEM+"'  GROUP BY USER_ID) AS LIST_ID_UNITPSK FROM ( " +
				   " SELECT (rtrim (xmlagg (xmlelement (e, ID_UNITPSK || ','  )).extract ('//text()')) || 0) " +
				   " AS LIST_ID_UNITPSK FROM ( "+
				   " SELECT ID_UNITPSK,NAMA_PEGAWAI, UTL_MATCH.edit_distance(UPPER(NAMA_PEGAWAI),UPPER('"+username+"')) TOT_BEZA "+
				   " FROM TBLPPKRUJUNIT ) WHERE  TOT_BEZA < 4 ) ";			
			myLogger.info(" BICARA INTERAKTIF : SQL getDetailPegawaiList :"+ sql);			
			rs = stmt.executeQuery(sql);
			
			String LIST_ID_UNITPSK = "0";
			while (rs.next()) {	
				LIST_ID_UNITPSK = rs == null ? "" :rs.getString("LIST_ID_UNITPSK") == null ? "" : rs.getString("LIST_ID_UNITPSK");
			}
			return LIST_ID_UNITPSK;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}
	
}


/*
 * List fail tukar pegawai 2:
 * 1. ekptg.view.ppk.FrmTukarPegawaiBI2.java
 * 2. app/ppk/TukarPegawai2/FrmPrmhnnTukarPegawai2.jsp
 * 3. app/ppk/TukarPegawai2/historyTukarPegawai2.jsp
 * 4. app/ppk/TukarPegawai2/listTukarPegawai2.jsp
 * 5. ekptg.model.ppk.FrmTukarPegawaiBI2Data.java
 * */