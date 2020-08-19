
package ekptg.report.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import ekptg.report.EkptgReportServlet;

public class RingkasanLampiranA extends EkptgReportServlet{
	
	public RingkasanLampiranA() {
		super.setReportName("Ringkasan_Laporan_All");
		super.setFolderName("ppt");
		
	}
	
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		Hashtable viewPejabat = null;
		Hashtable getDATE = null;
		String BULAN = (String) parameters.get("BULAN");
		String BULANSE = (String) parameters.get("BULANSE");
		String TAHUN = (String) parameters.get("TAHUN");
		String TAHUNSE = (String) parameters.get("TAHUNSE");		
		String ID_PEJABAT = (String) parameters.get("ID_PEJABAT");
		String ID_NEGERI = getID_NEGERIbyPEJABAT(ID_PEJABAT,null);
		String bulantahun = (String) parameters.get("bulantahun");
		String bulantahunSE = (String) parameters.get("bulantahunSE");
		String projek = (String) parameters.get("projek");
		String ID_DAERAH = (String) parameters.get("ID_DAERAH");
		String type = (String) parameters.get("type");
		
		System.out.println("ID_NEGERI : "+ID_NEGERI+" ID_PEJABAT : "+ID_PEJABAT+" bulantahun : "+bulantahun+" bulantahunSE : "+bulantahunSE+" projek : "+projek+" ID_DAERAH : "+ID_DAERAH+" type : "+type);
		viewPejabat = getPejabat(ID_PEJABAT,TAHUN,BULAN,TAHUNSE,BULANSE,type,null);
		getDATE = getDATE(null);
		/*
		h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
					h.put("BULAN",rs.getString("BULAN") == null ? "" : rs.getString("BULAN"));
					h.put("BULANSE",rs.getString("BULANSE") == null ? "" : rs.getString("BULANSE"));
					h.put("TAHUN",rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN"));
					h.put("TAHUNSE",rs.getString("TAHUNSE") == null ? "" : rs.getString("TAHUNSE"));
					h.put("TYPEINT",rs.getString("TYPEINT") == null ? "" : rs.getString("TYPEINT"));	
		*/
		
		String NAMA_PEJABAT = "";
		String NAMA_NEGERI = "";
		String TYPEINT = "";
		if(viewPejabat!=null)
		{
			NAMA_PEJABAT = (String) viewPejabat.get("NAMA_PEJABAT");
			NAMA_NEGERI = (String) viewPejabat.get("NAMA_NEGERI");
			BULAN = (String) viewPejabat.get("BULAN");
			BULANSE = (String) viewPejabat.get("BULANSE");
			TAHUN = (String) viewPejabat.get("TAHUN");
			TAHUNSE = (String) viewPejabat.get("TAHUNSE");
			TYPEINT = (String) viewPejabat.get("TYPEINT");
			
		}
		
		
		
		if(bulantahun.equals("/"))
		{
			bulantahun = "";
		}
		if(bulantahunSE.equals("/"))
		{
			bulantahunSE = "";
		}
		if(ID_DAERAH.equals("000"))
		{
			ID_DAERAH = " ";
		}
		if(ID_NEGERI.equals("") || ID_NEGERI.equals("16"))
		{
			ID_NEGERI = " ";
		}
		if(projek.equals(""))
		{
			projek = " ";
		}
		
		parameters.put("type",type);
		parameters.put("bulantahun",bulantahun);
		parameters.put("bulantahunSE",bulantahunSE);
		parameters.put("ID_DAERAH",ID_DAERAH);
		parameters.put("ID_NEGERI",ID_NEGERI);
		parameters.put("projek",projek);
		parameters.put("NAMA_PEJABAT",NAMA_PEJABAT);
		parameters.put("NAMA_NEGERI",NAMA_NEGERI);
		parameters.put("BULAN",BULAN);
		parameters.put("BULANSE",BULANSE);
		parameters.put("TAHUN",TAHUN);
		parameters.put("TAHUNSE",TAHUNSE);
		parameters.put("TYPEINT",TYPEINT);
		parameters.put("HARI_SYSDATE",(String) getDATE.get("HARI_SYSDATE"));
		parameters.put("BULAN_SYSDATE",(String) getDATE.get("BULAN_SYSDATE"));
		parameters.put("TAHUN_SYSDATE",(String) getDATE.get("TAHUN_SYSDATE"));
		
		
		/*
		
		//String template = String.valueOf(parameters.get("template"));
		//super.setReportName(template);	 
		if(String.valueOf(parameters.get("type")).equals("3")){			
//	    	String strTahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
	    	String strBulan = lebah.util.Util.getDateTime(new Date(), "MM");
//	    	System.out.println(strBulan+":"+strTahun);
//			parameters.put("TAHUN",strTahun);
//			parameters.put("TAHUNSE",strTahun);
			parameters.put("BULAN","01");
//			parameters.put("BULANSE",strBulan);

		}
		*/
	}
	
	
	public String getID_NEGERIbyPEJABAT(String ID_PEJABAT,Db db) throws Exception {
		Db db1 = null;
		String ID_NEGERI = "";
		String ID_JENISPEJABAT = "";
		ResultSet rs = null;
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			
			Statement stmt = db1.getStatement();			
			String sql = " SELECT ID_NEGERI,ID_JENISPEJABAT  ";
			sql += " FROM TBLRUJPEJABATJKPTG";
			sql += " WHERE ID_PEJABATJKPTG = '"+ID_PEJABAT+"' ";
			rs = stmt.executeQuery(sql);					
			while (rs.next()) {	
				ID_JENISPEJABAT = (String) (rs.getString("ID_JENISPEJABAT") == null ? "": rs.getString("ID_JENISPEJABAT").toUpperCase());
				if(ID_JENISPEJABAT.equals("21"))
				{
					ID_NEGERI = "16";
				}
				else
				{
					ID_NEGERI = (String) (rs.getString("ID_NEGERI") == null ? "": rs.getString("ID_NEGERI").toUpperCase());
				}
			}
			
		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
		return ID_NEGERI;
	}
	
	
	public Hashtable getPejabat(String ID_PEJABAT,String TAHUN,String BULAN,
			String TAHUNSE,String BULANSE,String TYPE,
			Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			Hashtable h;
			h = new Hashtable();			
			sql = " SELECT UPPER(TBLRUJPEJABATJKPTG.NAMA_PEJABAT) AS NAMA_PEJABAT, TBLRUJNEGERI.NAMA_NEGERI,  "+
					" CASE  "+
					" WHEN '"+BULAN+"'='1' OR '"+BULAN+"'='01' THEN 'JANUARI' "+
					" WHEN '"+BULAN+"'='2' OR '"+BULAN+"'='02' THEN 'FEBRUARI'  "+
					" WHEN '"+BULAN+"'='3' OR '"+BULAN+"'='03' THEN 'MAC' "+
					" WHEN '"+BULAN+"'='4' OR '"+BULAN+"'='04' THEN 'APRIL' "+
					" WHEN '"+BULAN+"'='5' OR '"+BULAN+"'='05' THEN 'MEI' "+
					" WHEN '"+BULAN+"'='6' OR '"+BULAN+"'='06' THEN 'JUN' "+
					" WHEN '"+BULAN+"'='7' OR '"+BULAN+"'='07' THEN 'JULAI' "+
					" WHEN '"+BULAN+"'='8' OR '"+BULAN+"'='08' THEN 'OGOS' "+
					" WHEN '"+BULAN+"'='9' OR '"+BULAN+"'='09' THEN 'SEPTEMBER' "+
					" WHEN '"+BULAN+"'='10' THEN 'OKTOBER' "+
					" WHEN '"+BULAN+"'='11' THEN 'NOVEMBER' "+
					" WHEN '"+BULAN+"'='12' THEN 'DISEMBER' "+
					" END AS BULAN, "+
					" CASE  "+
					" WHEN '"+BULANSE+"'='1' OR '"+BULANSE+"'='01' THEN 'JANUARI' "+
					" WHEN '"+BULANSE+"'='2' OR '"+BULANSE+"'='02' THEN 'FEBRUARI'  "+
					" WHEN '"+BULANSE+"'='3' OR '"+BULANSE+"'='03' THEN 'MAC' "+
					" WHEN '"+BULANSE+"'='4' OR '"+BULANSE+"'='04' THEN 'APRIL' "+
					" WHEN '"+BULANSE+"'='5' OR '"+BULANSE+"'='05' THEN 'MEI' "+
					" WHEN '"+BULANSE+"'='6' OR '"+BULANSE+"'='06' THEN 'JUN' "+
					" WHEN '"+BULANSE+"'='7' OR '"+BULANSE+"'='07' THEN 'JULAI' "+
					" WHEN '"+BULANSE+"'='8' OR '"+BULANSE+"'='08' THEN 'OGOS' "+
					" WHEN '"+BULANSE+"'='9' OR '"+BULANSE+"'='09' THEN 'SEPTEMBER' "+
					" WHEN '"+BULANSE+"'='10' THEN 'OKTOBER' "+
					" WHEN '"+BULANSE+"'='11' THEN 'NOVEMBER' "+
					" WHEN '"+BULANSE+"'='12' THEN 'DISEMBER' "+
					" END AS BULANSE,       "+
					" '"+TAHUN+"' AS TAHUN,'"+TAHUNSE+"' AS TAHUNSE, CAST('"+TYPE+"' AS INT) AS TYPEINT "+
					" FROM TBLRUJPEJABATJKPTG , TBLRUJNEGERI "+
					" WHERE TBLRUJPEJABATJKPTG.ID_SEKSYEN = '1' "+
					" AND TBLRUJPEJABATJKPTG.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI "+
					" AND TBLRUJPEJABATJKPTG.ID_NEGERI = '"+ID_PEJABAT+"' ";
				//System.out.println(" get pejabat :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);	
				
				//System.out.println("SQL GET PEJABAT : "+sql);
				while (rs.next()) {
					h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
					h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
					h.put("BULAN",rs.getString("BULAN") == null ? "" : rs.getString("BULAN"));
					h.put("BULANSE",rs.getString("BULANSE") == null ? "" : rs.getString("BULANSE"));
					h.put("TAHUN",rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN"));
					h.put("TAHUNSE",rs.getString("TAHUNSE") == null ? "" : rs.getString("TAHUNSE"));
					h.put("TYPEINT",rs.getString("TYPEINT") == null ? "" : rs.getString("TYPEINT"));					
				}
			
			return h;
		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	
	public Hashtable getDATE(Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			Hashtable h;
			h = new Hashtable();			
			sql = " SELECT TO_CHAR(SYSDATE,'DD') AS HARI_SYSDATE, "+
					" CASE  "+
					" WHEN TO_CHAR(SYSDATE,'MM')='01' THEN 'Januari' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='02' THEN 'Februari'  "+
					" WHEN TO_CHAR(SYSDATE,'MM')='03' THEN 'Mac' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='04' THEN 'April' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='05' THEN 'Mei' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='06' THEN 'Jun' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='07' THEN 'Julai' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='08' THEN 'Ogos' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='09' THEN 'September' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='10' THEN 'Oktober' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='11' THEN 'November' "+
					" WHEN TO_CHAR(SYSDATE,'MM')='12' THEN 'Disember' "+
					" END AS BULAN_SYSDATE, TO_CHAR(SYSDATE,'yyyy') AS TAHUN_SYSDATE FROM DUAL";
				System.out.println(" getDATE :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					h.put("HARI_SYSDATE",rs.getString("HARI_SYSDATE") == null ? "" : rs.getString("HARI_SYSDATE"));
					h.put("BULAN_SYSDATE",rs.getString("BULAN_SYSDATE") == null ? "" : rs.getString("BULAN_SYSDATE"));
					h.put("TAHUN_SYSDATE",rs.getString("TAHUN_SYSDATE") == null ? "" : rs.getString("TAHUN_SYSDATE"));
				}
			
			return h;
		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	
	
}
