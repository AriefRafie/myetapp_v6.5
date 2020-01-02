package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmPopupSenaraiFailArkibData {

	private static Vector list = new Vector();
	
	public static void  setCarianFail(String noFail,String idUrusan,String idSuburusan,String idSubSuburusan,String idSubSubSuburusan)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT A.ID_FAILARKIB, A.NO_FAIL_ARKIB" +
	      		" FROM TBLRUJFAILARKIB A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E"+    		
	      		" WHERE " +
	      		" A.ID_URUSAN = B.ID_URUSAN" +
	      		" AND A.ID_SUBURUSAN = C.ID_SUBURUSAN " +
	      		" AND B.ID_URUSAN = C.ID_URUSAN " +
	      		" AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN " +
	      		" AND C.ID_SUBURUSAN = D.ID_SUBURUSAN " +
	      		" AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN "+
	      		" AND D.ID_SUBSUBURUSAN = E.ID_SUBSUBURUSAN ";
	    
	      
	  		//dapat flag
			boolean setLimit = true;
	      
	      //NO FAIL
	      if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.NO_FAIL_ARKIB) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
				}
			}
	      
	      //URUSAN
	      if (idUrusan != null) {
				if (!idUrusan.trim().equals("")) {
					setLimit = false;
					if (!idUrusan.trim().equals("0")) {
						sql = sql + " AND A.ID_URUSAN LIKE = '"+ idUrusan + "'";
					}
				}
			}
	      
	      //SUBURUSAN
	      if (idSuburusan != null) {
				if (!idSuburusan.trim().equals("")) {
					setLimit = false;
					if (!idSuburusan.trim().equals("0")) {
						sql = sql + " AND A.ID_SUBURUSAN = '" + idSuburusan + "'";
					}
				}
			}
	      
	      //SUB SUBURUSAN
	      if (idSubSuburusan != null) {
				if (!idSubSuburusan.trim().equals("")) {
					setLimit = false;
					if (!idSubSuburusan.trim().equals("0")) {
						sql = sql + " AND A.ID_SUBSUBURUSAN = '" + idSubSuburusan + "'";
					}
				}
			}
	      
	      //SUB SUB SUBURUSAN
	      if (idSubSubSuburusan != null) {
				if (!idSubSubSuburusan.trim().equals("")) {
					setLimit = false;
					if (!idSubSubSuburusan.trim().equals("0")) {
						sql = sql + " AND A.ID_SUBSUBSUBURUSAN = '" + idSubSubSuburusan + "'";
					}
				}
			}
	      
	     

	      
			
	      sql = sql + " ORDER BY A.ID_FAILARKIB DESC";
	      
	      System.out.println("sql output-----" +sql);	
	     
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      
	    
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_FailArkib", rs.getString("ID_FAILARKIB")== null? "":rs.getString("ID_FAILARKIB"));
	    	  h.put("no_Fail_Arkib", rs.getString("NO_FAIL_ARKIB")== null? "":rs.getString("NO_FAIL_ARKIB"));
	    	 
	    	  
	    	  bil++;
	    	  count++;
	    	  
	      }
	     
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil","");
	    	  h.put("id_FailArkib",0);
	    	  h.put("no_Fail_Arkib", "Tiada rekod.");
	    	 
	    	  list.addElement(h);
	      }
	     
	    } finally {
	      if (db != null) db.close();
	    }
	}
	 public static Vector getList(){
		 
		  return list;
	  }
}
