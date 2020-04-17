package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmNotaBicaraData {

	private static Vector list = new Vector();
	private static Vector dataNotaBicara = new Vector();
	
	public static Vector getList(){
		return list;
	}
	public static Vector getDataNotaBicara(){
		return dataNotaBicara;
	}
	
	
	//semak 
	public static void setList(String nofail, String usid)throws Exception {
		
	    Db db = null;
	    list.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		String chkDataFail = nofail.trim();
	      
	    		//SQL
	    		sql = "SELECT NVL(MAX(A.ID_PERBICARAAN),0) AS ID_PERBICARAAN";
	    		sql += " FROM TBLPPKPERBICARAAN A, TBLPPKKEPUTUSANPERMOHONAN B, TBLPPKPERMOHONAN C, TBLPFDFAIL D, TBLRUJDAERAH F,";
	    		sql += " TBLRUJSTATUS G, TBLRUJSUBURUSANSTATUS H, TBLRUJSUBURUSANSTATUSFAIL I";
	    		sql += " WHERE F.ID_DAERAH";
	    		sql += " IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID= '"+usid+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
					
				sql += " )";
	    		sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN";
	    		sql += " AND C.ID_DAERAHMHN = F.ID_DAERAH";
	    		sql += " AND A.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN";
				sql += " AND C.ID_FAIL = D.ID_FAIL"; 
				sql += " AND C.ID_STATUS = G.ID_STATUS";
				sql += " AND H.ID_STATUS = G.ID_STATUS";
				sql += " AND I.ID_SUBURUSANSTATUS = H.ID_SUBURUSANSTATUS";
				sql += " AND I.AKTIF = '1'";
				sql += " AND C.ID_STATUS NOT IN ('999','56')";
				sql += " AND D.NO_FAIL = '"+chkDataFail+"'";
	    		
				ResultSet rs = stmt.executeQuery(sql);
	      	
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();      		  
	    			h.put("id_perbicaraan", rs.getString("ID_PERBICARAAN")==null?"":rs.getString("ID_PERBICARAAN"));
	    			list.addElement(h);
	    		}
	      
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }	    
	  }//close list
	
	
	
	//semak data nota bicara
	public static void setDataNotaBicara(String id_perbicaraan)throws Exception {
		
	    Db db = null;
	    dataNotaBicara.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		sql = "SELECT ID_NOTABICARA, NOTA_BICARA FROM TBLPPKNOTABICARA";
	    		sql += " WHERE ID_PERBICARAAN = '"+id_perbicaraan+"'";
	    		
				ResultSet rs = stmt.executeQuery(sql);
	      	
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();      		  
	    			h.put("id_notabicara", rs.getString("ID_NOTABICARA")==null?"":rs.getString("ID_NOTABICARA"));
	    			h.put("nota_bicara", rs.getString("NOTA_BICARA")==null?"":rs.getString("NOTA_BICARA"));
	    			dataNotaBicara.addElement(h);
	    		}
	      
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }	    
	  }//close data nota bicara
	
	
	public static void simpanNotaBicara(Hashtable data) throws Exception{
	    
		Db db = null;
		String sql = "";
		    
		try
		{
		   db = new Db();
		    	 
		   String notaBicara = (String)data.get("notaBicara");
		   String id_masuk = (String)data.get("id_masuk");
		   String id_perbicaraan = (String)data.get("id_perbicaraan");
		   
		   Statement stmt = db.getStatement();
		   SQLRenderer r = new SQLRenderer();
		   r.add("nota_bicara", notaBicara); 	
		   r.add("id_perbicaraan", id_perbicaraan); 
		   r.add("tarikh_masuk", r.unquote("sysdate"));
		   r.add("id_masuk",id_masuk);
		    	
		   sql = r.getSQLInsert("Tblppknotabicara");
		   stmt.executeUpdate(sql);
		    
		}//close try 
		    
		finally {
		    if (db != null) db.close();
		}//close finally
		   
	}//close simpan nota bicara
	
	
	public static void updateNotaBicara(Hashtable data) throws Exception{
	    
		Db db = null;
		String sql = "";
		    
		try
		{
		   db = new Db();
		    	 
		   String notaBicara = (String)data.get("notaBicara");
		   String id_kemaskini = (String)data.get("id_kemaskini");
		   String id_notabicara = (String)data.get("id_notabicara");
		   
		   Statement stmt = db.getStatement();
		   SQLRenderer r = new SQLRenderer();
		   r.update("id_notabicara", id_notabicara); 	
		   r.add("nota_bicara", notaBicara); 
		   r.add("tarikh_kemaskini", r.unquote("sysdate"));
		   r.add("id_kemaskini",id_kemaskini);
		    	
		   sql = r.getSQLUpdate("Tblppknotabicara");
		   stmt.executeUpdate(sql);
		    
		}//close try 
		    
		finally {
		    if (db != null) db.close();
		}//close finally
		   
	}//close simpan nota bicara
	
}//close class
