package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmDaftarMinitMesyuaratData {
	

	private static Vector list = null;
	public static void  setCarianMesyuarat(String tajukMesyuarat, String tarikhMesyuarat, String seksyen, String lokasi)throws Exception {
		
		Db db = null;
	    list = new Vector();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT A.ID_MESYUARAT, A.BIL_MESYUARAT,A.TAJUK_MESYUARAT, A.TARIKH_MESYUARAT,"+
	      		" A.MASA_MESYUARAT_DARI,A.WAKTU_MESYUARAT_DARI, B.LOKASI,C.KOD_SEKSYEN, D.KETERANGAN" +
	      		" FROM TBLPFDMESYUARAT A, TBLPFDRUJLOKASIMESYUARAT B, TBLRUJSEKSYEN C, TBLRUJSTATUS D" +
	      		" WHERE A.ID_LOKASI = B.ID_LOKASI" +
	      		" AND A.ID_SEKSYEN = C.ID_SEKSYEN" +
	      		" AND A.ID_STATUS = D.ID_STATUS" +
	      		" AND A.KATEGORI_MESYUARAT = '1'";
      

	     
	      //TAJUK MESYUARAT
	      if (tajukMesyuarat != null) {
				if (!tajukMesyuarat.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_MESYUARAT) LIKE '%' ||'" + tajukMesyuarat.toUpperCase() + "'|| '%'";
				}
			}
	      
	      //TARIKH MESYUARAT    
	      SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
	      if (tarikhMesyuarat != null) {
				if (!tarikhMesyuarat.toString().trim().equals("")) {			 
					sql = sql + " AND A.TARIKH_MESYUARAL = '" + sdf1.format(sdf.parse(tarikhMesyuarat)).toUpperCase() +"'";
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
	      
	      //LOKASI
	      if (lokasi != null) {
				if (!lokasi.trim().equals("")) {
					if (!lokasi.trim().equals("0")) {
						sql = sql + " AND A.ID_LOKASI = '" + lokasi + "'";
					}
				}
			}
	      
	      sql = sql + " ORDER BY A.ID_MESYUARAT ASC";
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      System.out.println("------"+sql);
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      String waktu = "";
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("bil_Mesyuarat", rs.getString("BIL_MESYUARAT"));
	    	  h.put("id_Mesyuarat",rs.getString("ID_MESYUARAT"));
	    	  h.put("tajuk_Mesyuarat", rs.getString("TAJUK_MESYUARAT"));
	    	  h.put("tarikh_Mesyuarat",rs.getDate("TARIKH_MESYUARAT")== null?"":sdf.format(rs.getDate("TARIKH_MESYUARAT")));
	    	  
	    	  String flagDate = rs.getString("WAKTU_MESYUARAT_DARI");
	    	  if (flagDate != null){
		    	  
		    	  if (Integer.parseInt(rs.getString("WAKTU_MESYUARAT_DARI")) == 1){
		    		  waktu = "PAGI";
		    	  }
		    	  else if (Integer.parseInt(rs.getString("WAKTU_MESYUARAT_DARI")) == 2){
		    		  waktu = "PETANG";
		    	  }
		    	  else if (Integer.parseInt(rs.getString("WAKTU_MESYUARAT_DARI")) == 3){
		    		  waktu = "MALAM";
		    	  }	    		  
	    	  }

	    	  h.put("masa_Mesyuarat_Dari", rs.getString("MASA_MESYUARAT_DARI")+ " " + waktu );
	    	  h.put("kod_Seksyen",rs.getString("KOD_SEKSYEN"));
	    	  h.put("keterangan", rs.getString("KETERANGAN"));
	    	  h.put("lokasi",rs.getString("LOKASI"));
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	    	  System.out.println("hash h :: "+h);
	      
	    }
	      
	    if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("bil_Mesyuarat", "");
	    	  h.put("id_Mesyuarat",0);
	    	  h.put("tajuk_Mesyuarat", "");
	    	  h.put("tarikh_Mesyuarat","");
	    	  h.put("masa_Mesyuarat_Dari", "");
	    	  h.put("kod_Seksyen","Tiada rekod.");
	    	  h.put("keterangan", "");
	    	  h.put("lokasi","");
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
