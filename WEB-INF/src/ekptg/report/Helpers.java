package ekptg.report;

import java.sql.ResultSet;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.object.Person;

public class Helpers {
	private static Helpers instance = null;
	static Logger myLogger = Logger.getLogger(Helpers.class);
	
	public static void main (String args[]) {
		Helpers w = Helpers.getInstance();
	}
	
	public static Helpers getInstance()
	{
	    if (instance == null) instance = new Helpers();
	    return instance;
	}
	  
	public Person getInfo(String no_fail,String user_id) {
		Db db = null;
		String sql = "";
		Person person=null;
		try {
			db = new Db();
			sql = "select D.ID_PERMOHONANSIMATI,A.NO_FAIL,B.ID_PERMOHONAN,E.NAMA_SIMATI,A.ID_FAIL," +
					"C.NAMA_PEMOHON," +
					"C.ID_PEMOHON,D.ID_SIMATI," +
					"(select KETERANGAN from tblrujstatus where id_status=B.ID_STATUS) AS STATUS_TERKINI, "+
					"(select no_permohonan_online from Tblppkpermohonan where id_fail=A.ID_FAIL) AS NO_ONLINE "+
			"from "+
			"TBLPFDFAIL A LEFT OUTER JOIN Tblppkpermohonan B ON A.ID_FAIL = B.ID_FAIL "+
			"JOIN Tblppkpemohon C ON B.ID_PEMOHON = C.ID_PEMOHON "+
			"JOIN Tblppkpermohonansimati D ON B.ID_PERMOHONAN = D.ID_PERMOHONAN "+
			"JOIN Tblppksimati E ON D.ID_SIMATI = E.ID_SIMATI "+
			"WHERE "+
			"NVL(A.ID_STATUS,0) NOT IN ('999') "+
			"AND B.id_daerahmhn IN ( "+
			"select distinct u.id_daerahurus from  TBLRUJPEJABATURUSAN u, users_internal ur  "+
			"where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+user_id+"' "+
			") "+
			"AND ( UPPER(A.NO_FAIL) = '"+no_fail.toUpperCase().trim()+"' " +
					"OR " +
					"UPPER(B.NO_PERMOHONAN_ONLINE) = '"+no_fail.toUpperCase().trim()+"' "+
					")"+
			"order by B.TARIKH_KEMASKINI";
			myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql); 
			if (rs.next()){	
				person = new Person();
				person.setIdFile(rs.getString("ID_FAIL"));
				person.setNamaSimati(rs.getString("NAMA_SIMATI"));
				person.setNamaPemohon(rs.getString("NAMA_PEMOHON"));
				person.setId_pemohon(rs.getString("ID_PEMOHON"));
				person.setId_permohonan(rs.getString("ID_PERMOHONAN"));
				person.setId_permohonansimati(rs.getString("ID_PERMOHONANSIMATI"));
				person.setId_simati(rs.getString("ID_SIMATI"));
				person.setNoFile(no_fail);
				person.setStatusTerkini(rs.getString("STATUS_TERKINI"));
				person.setNoPermohonanOnline(rs.getString("NO_ONLINE"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return person;
		
	}
}
