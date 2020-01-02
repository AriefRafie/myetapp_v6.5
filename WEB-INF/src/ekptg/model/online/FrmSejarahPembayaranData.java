package ekptg.model.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmSejarahPembayaranData {
	
	private static Vector list = new Vector();
	public static void  setCarianBayaran(String dariBulan,String keBulan,String userId)throws Exception {
		
		Db db = null;
	    list.clear();
	    String sql = "";
	    
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT A.ID_PEMBAYARANONLINE, A.TARIKH_PEMBAYARAN, B.KETERANGAN, A.NO_AKAUN, A.NO_AKAUN_PENERIMA, A.AMAUN_BAYARAN, C.KETERANGAN AS STATUS_BAYARAN, A.NO_TRANSAKSI_FPX, A.NO_RUJUKAN" +
    		" FROM TBLPEMBAYARANONLINE A, TBLRUJJENISBAYARAN B, TBLRUJSTATUS C" +    		
    		" WHERE " +
    		" A.ID_JENISBAYARAN = B.ID_JENISBAYARAN(+)" +
    		" AND A.ID_STATUS = C.ID_STATUS(+)" +
    		" AND A.ID_PENGGUNA = " + userId;
	      
	      
	      SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
	      if (dariBulan != null) {
				if (!dariBulan.toString().trim().equals("")) {			 
					sql = sql + " AND A.TARIKH_PEMBAYARAN = '" + sdf1.format(sdf.parse(dariBulan)).toUpperCase() +"'";
				}
		  }
	      if (keBulan != null) {
				if (!keBulan.toString().trim().equals("")) {			 
					sql = sql + " AND A.TARIKH_PEMBAYARAN = '" + sdf1.format(sdf.parse(keBulan)).toUpperCase() +"'";
				}
		  }
	      
	      sql = sql + " ORDER BY A.ID_PEMBAYARANONLINE DESC";
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPembayaranOnline",rs.getString("ID_PEMBAYARANONLINE"));
	    	  h.put("tarikhBayaran",rs.getString("TARIKH_PEMBAYARAN")== null?"":rs.getString("TARIKH_PEMBAYARAN"));
	    	  h.put("jenisBayaran", rs.getString("KETERANGAN")== null? "":rs.getString("KETERANGAN"));
	    	  h.put("noAkaun", rs.getString("NO_AKAUN")== null?"":rs.getString("NO_AKAUN")+ "/" + rs.getString("NO_AKAUN_PENERIMA"));
	    	  h.put("noAkaunPenerima", rs.getString("NO_AKAUN_PENERIMA")== null? "":rs.getString("NO_AKAUN_PENERIMA"));
	    	  h.put("amaunBayaran", rs.getString("AMAUN_BAYARAN")== null?"":rs.getString("AMAUN_BAYARAN"));
	    	  if (rs.getString("NO_RUJUKAN")!= null && rs.getString("NO_RUJUKAN")!= "" ){
	    		  System.out.println("Sayang1");
	    		  h.put("statusBayaran",rs.getString("STATUS_BAYARAN")+ "/" +rs.getString("NO_RUJUKAN"));
	    	  
	    	  }
	    	  else{
	    		  System.out.println("Sayang2");
	    		  h.put("statusBayaran",rs.getString("STATUS_BAYARAN")+ "/" +rs.getString("NO_TRANSAKSI_FPX"));
	    	  }
	    	  
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	      }
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("idPembayaranOnline", "");
	    	  h.put("tarikhBayaran","Tiada rekod.");
	    	  h.put("jenisBayaran", "");
	    	  h.put("noAkaun", "");
	    	  h.put("noAkaunPenerima", "");
	    	  h.put("amaunBayaran", "");
	    	  h.put("statusBayaran","");
	    	  h.put("noRujukan","");
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
