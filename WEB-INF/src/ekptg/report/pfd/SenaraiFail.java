package ekptg.report.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import ekptg.report.EkptgReportServlet;


public class SenaraiFail extends EkptgReportServlet{
	
	public SenaraiFail() {
		
		super.setReportName("PFD_SenaraiFail");
		super.setFolderName("pfd");
	    Map parameters = new HashMap();
		parameters.put("ReportTitle","Senarai Fail eKPTG");
		super.setParameters(parameters);
//		super.setSQL("SELECT NO_FAIL AS TBLPFDFAIL_NO_FAIL," +
//				"TAJUK_FAIL AS TBLPFDFAIL_TAJUK_FAIL,"+
//				"TARIKH_DAFTAR_FAIL AS TBLPFDFAIL_TARIKH_DAFTAR_FAIL "+
//				"FROM TBLPFDFAIL ");
//		        //"WHERE TAJUK_FAIL like '%JKPTG/PK/01/16/0030/2009%' ");
//		super.setSQL("SELECT DISTINCT A.ID_FAIL, A.TARIKH_DAFTAR_FAIL,A.TAJUK_FAIL, A.NO_FAIL, B.NAMA_NEGERI,C.KOD_SEKSYEN, D.KETERANGAN AS STATUS_FAIL," +
//  		" E.KETERANGAN AS STATUS_PERGERAKAN_FAIL" +
//  		" FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F" +    		
//  		" WHERE " +
//  		" A.ID_FAIL = F.ID_FAIL(+)" +
//  		" AND A.ID_NEGERI = B.ID_NEGERI" +
//  		" AND A.ID_SEKSYEN = C.ID_SEKSYEN " +
//  		" AND A.ID_STATUS = D.ID_STATUS(+)" +
//  		" AND F.ID_STATUS = E.ID_STATUS(+)" +
//  		" AND A.ID_URUSAN NOT IN (382,6,108,11,10,3,4,2,5,12,7,8,9,13,17,1,309,108)");

		
	}
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String id_negeri = getid_negeri(user_id);
		String id_seksyen = getid_seksyen(user_id);
		
		
		String nofailSRC = (String) parameters.get("nofail");
		String tajukfailSRC = (String) parameters.get("tajukfail");
		String idstatusSRC = (String) parameters.get("idstatus");
//		String idnegeriSRC = (String) parameters.get("idnegeri");
//		String idseksyenSRC = (String) parameters.get("idseksyen");
		String tarikhdaftarSRC = (String) parameters.get("tarikhdaftar");
//		String SQLnofailSRCBCK ="";
//		String SQLnofailSRC="";
		String sql = "";
//		if(!"".equals(nofailSRC)){
//			SQLnofailSRC = " AND UPPER(A.NO_FAIL) LIKE  '%' ||'"; 
//			SQLnofailSRCBCK = "'|| '%' ";
//		}
		

//---------------------------------------------------------------------------------------------
		
  	  sql = "SELECT DISTINCT A.ID_FAIL, A.TARIKH_DAFTAR_FAIL,A.TAJUK_FAIL, A.NO_FAIL, B.NAMA_NEGERI,C.KOD_SEKSYEN, D.KETERANGAN AS STATUS_FAIL," +
		" E.KETERANGAN AS STATUS_PERGERAKAN_FAIL" +
		" FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F" + 
		" WHERE " +
		" A.ID_FAIL = F.ID_FAIL(+)" +
		" AND A.ID_NEGERI = B.ID_NEGERI" +
		" AND A.ID_SEKSYEN = C.ID_SEKSYEN " +
		" AND A.ID_STATUS = D.ID_STATUS(+)" +
		" AND F.ID_STATUS = E.ID_STATUS(+)" +
		" AND A.ID_TARAFKESELAMATAN in (1)" +
		" and a.id_seksyen ='"+id_seksyen+"'" +
		" and a.id_negeri ='"+id_negeri+"'";

//NO FAIL  tarikhdaftar
  	  if (nofailSRC != null) {
  		  if (!nofailSRC.trim().equals("")) {
  			  sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'" + nofailSRC.toUpperCase() + "'|| '%'";
  		  }
  	  }

//TAJUK FAIL
  	  if (tajukfailSRC != null) {
		if (!tajukfailSRC.trim().equals("")) {
			sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'" + tajukfailSRC.toUpperCase() + "'|| '%'";
		}
  	  }

//NEGERI
  	  if (id_negeri != null) {
		if (!id_negeri.trim().equals("")) {
			if (!id_negeri.trim().equals("0")) {
				sql = sql + " AND A.ID_NEGERI = '" + id_negeri + "'";
			}
		}
  	  }


//SEKSYEN
		if (id_seksyen != null) {
			if (!id_seksyen.trim().equals("")) {
				if (!id_seksyen.trim().equals("0")) {
				sql = sql + " AND A.ID_SEKSYEN = '" + id_seksyen + "'";
				}
			}
		}


//STATUS
if (idstatusSRC != null) {
		if (!idstatusSRC.trim().equals("")) {
			if (!idstatusSRC.trim().equals("0")) {
				sql = sql + " AND A.ID_STATUS = '" + idstatusSRC + "'";
			}
		}
	}

//TARIKH DAFTAR FAIL	      
// SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
if (tarikhdaftarSRC != null) {
		if (!tarikhdaftarSRC.toString().trim().equals("")) {			 
			sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') = '" + tarikhdaftarSRC +"'";
			
		}
	}

sql = sql + " ORDER BY A.ID_FAIL DESC";

		super.setSQL(sql);
//		-----------------------------		
		parameters.put("nofailSRC",nofailSRC);
//		parameters.put("SQLnofailSRC",SQLnofailSRC);
//		parameters.put("SQLnofailSRCBCK",SQLnofailSRCBCK);
		
		
		parameters.put("id_negeri",id_negeri);
		parameters.put("id_seksyen",id_seksyen);
		 
	}
	
	 public static String  getid_seksyen(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String id_seksyen = "";
		    
		    try {

				db = new Db();
				Statement stmt = db.getStatement();

		      sql = "select id_seksyen from users_internal where user_id=" + id;
	      		
						      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  id_seksyen = rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen");

		      }
		      
		     
		      
		   
		      return id_seksyen;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	 
	 public static String  getid_negeri(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String id_negeri = "";
		    
		    try {

				db = new Db();
				Statement stmt = db.getStatement();

		      sql = "select id_negeri from users_internal where user_id=" + id;
	      		
						      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  id_negeri = rs.getString("id_negeri")==null?"":rs.getString("id_negeri");

		      }
		      
		     
		      
		   
		      return id_negeri;
		    } finally {
		      if (db != null) db.close();
		    }
		}
}
