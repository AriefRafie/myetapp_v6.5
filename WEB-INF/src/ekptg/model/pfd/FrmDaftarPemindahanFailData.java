package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmDaftarPemindahanFailData {
	private static Vector list = new Vector();
	public static void  setCarianFail(String noFail,String tajukFail,String negeri,String seksyen,String status,String tarikhDaftar)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT A.ID_FAIL, A.TARIKH_DAFTAR_FAIL,A.TAJUK_FAIL, A.NO_FAIL, B.NAMA_NEGERI,C.KOD_SEKSYEN, D.KETERANGAN AS STATUS_FAIL," +
	      		" E.KETERANGAN AS STATUS_PERGERAKAN_FAIL" +
	      		" FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F" +    		
	      		" WHERE " +
	      		" A.ID_FAIL = F.ID_FAIL(+)" +
	      		" AND A.ID_NEGERI = B.ID_NEGERI" +
	      		" AND A.ID_SEKSYEN = C.ID_SEKSYEN " +
	      		" AND A.ID_STATUS = D.ID_STATUS(+)" +
	      		" AND F.ID_STATUS = E.ID_STATUS(+)";
	      
	      //NO FAIL
	      if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
				}
			}
	      
	      //TAJUK FAIL
	      if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'" + tajukFail.toUpperCase() + "'|| '%'";
				}
			}
	      
	      //NEGERI
	      if (negeri != null) {
				if (!negeri.trim().equals("")) {
					if (!negeri.trim().equals("0")) {
						sql = sql + " AND A.ID_NEGERI = '" + negeri + "'";
					}
				}
			}
	      
	      //SEKSYEN
	      if (seksyen != null) {
				if (!seksyen.trim().equals("")) {
					if (!seksyen.trim().equals("0")) {
						sql = sql + " AND A.ID_SEKSYEN = '" + seksyen + "'";
					}
				}
			}
	      
	      //STATUS
	      if (status != null) {
				if (!status.trim().equals("")) {
					if (!status.trim().equals("0")) {
						sql = sql + " AND A.ID_STATUS = '" + status + "'";
					}
				}
			}
	      
	      //TARIKH DAFTAR FAIL
	      if (tarikhDaftar != null) {
				if (!tarikhDaftar.trim().equals("")) {
					sql = sql + " AND A.TARIKH_DAFTAR_FAIL LIKE '%' ||'" + tarikhDaftar + "'|| '%'";
				}
			}
	      
	      sql = sql + " ORDER BY A.ID_FAIL DESC";
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_Fail",rs.getString("ID_FAIL"));
	    	  h.put("no_Fail", rs.getString("NO_FAIL"));
	    	  h.put("tajuk_Fail", rs.getString("TAJUK_FAIL")== null?"":rs.getString("TAJUK_FAIL"));
	    	  h.put("tarikh_Daftar_Fail",sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL"))== null? "":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
	    	  h.put("nama_Negeri", rs.getString("NAMA_NEGERI"));
	    	  h.put("kod_Seksyen",rs.getString("kod_Seksyen"));
	    	  h.put("keterangan1", rs.getString("STATUS_FAIL"));
	    	  h.put("keterangan2", rs.getString("STATUS_PERGERAKAN_FAIL")== null? "":rs.getString("STATUS_PERGERAKAN_FAIL"));
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	      }
	     
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil","");
	    	  h.put("id_Fail",0);
	    	  h.put("no_Fail", "");
	    	  h.put("tajuk_Fail", "");
	    	  h.put("tarikh_Daftar_Fail", "");
	    	  h.put("nama_Negeri", "Tiada rekod.");
	    	  h.put("kod_Seksyen","");
	    	  h.put("keterangan1", "");
	    	  h.put("keterangan2", "");
	    	  list.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	 public static Vector getList(){
		 
		  return list;
	  }
}