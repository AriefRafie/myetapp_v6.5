package ekptg.model.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmEAduanListData {
	
	private static Vector list = new Vector();
	public static void  setCarianAduan(String noAduan ,String jenisAduan,String tkhAduan,String statusAduan)throws Exception {
		
	    Db db = null;
	    list.clear();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT A.ID_ADUAN,A.NO_ADUAN_ONLINE, A.TARIKH_ADUAN, C.KETERANGAN,B.JENIS_ADUAN,A.TINDAKAN_SUSULAN" +
	      		" FROM TBLONLINEADUAN A, TBLRUJJENISADUAN B, TBLRUJSTATUS C" +
	      		" WHERE A.ID_JENISADUAN = B.ID_JENISADUAN(+)" +
	      		" AND A.ID_STATUS = C.ID_STATUS(+)";
	      	
	    	 
	      
	      //NO ADUAN ONLINE
	      if (noAduan != null) {
				if (!noAduan.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_ADUAN_ONLINE) LIKE '%' ||'" + noAduan.toUpperCase() + "'|| '%'";
				}
			}
	      
	      //JENIS ADUAN
	      if (jenisAduan != null) {
				if (!jenisAduan.trim().equals("")) {
					if (!jenisAduan.trim().equals("0")) {
						sql = sql + " AND A.ID_JENISADUAN = '" + jenisAduan + "'";
					}
				}
			}
	      
	      //TARIKH ADUAN
	      SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
	      if (tkhAduan != null) {
				if (!tkhAduan.toString().trim().equals("")) {			 
					sql = sql + " AND A.TARIKH_ADUAN = '" + sdf1.format(sdf.parse(tkhAduan)).toUpperCase() +"'";
				}
			}
	      
	      //STATUS ADUAN
	      if (statusAduan != null) {
				if (!statusAduan.trim().equals("")) {
					if (!statusAduan.trim().equals("0")) {
						sql = sql + " AND A.ID_STATUS = '" + statusAduan + "'";
					}
				}
			}
	      
	      sql = sql + " ORDER BY A.ID_ADUAN ASC";
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_Aduan",rs.getString("ID_ADUAN"));
	    	  h.put("no_Aduan_Online", rs.getString("NO_ADUAN_ONLINE")== null?"":rs.getString("NO_ADUAN_ONLINE"));
	    	  h.put("jenis_Aduan", rs.getString("JENIS_ADUAN")== null?"":rs.getString("JENIS_ADUAN"));
	    	  h.put("tarikh_Aduan",rs.getDate("TARIKH_ADUAN")== null? "":sdf.format(rs.getDate("TARIKH_ADUAN")));
	    	  h.put("status_Aduan", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
	    	  h.put("tindakan_Susulan", rs.getString("TINDAKAN_SUSULAN")== null?"":rs.getString("TINDAKAN_SUSULAN"));
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil","");
	    	  h.put("id_Aduan",0);
	    	  h.put("no_Aduan_Online", "Tiada rekod.");
	    	  h.put("jenis_Aduan", "");
	    	  h.put("tarikh_Aduan", "");
	    	  h.put("status_Aduan","");
	    	  h.put("tindakan_Susulan","");
	    	  list.addElement(h);
	      }
	    }finally {
	      if (db != null) db.close();
	    }
	}
	 public static Vector getList(){
		 
		  return list;
	  }

}
