package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmHakmilikSementaraSenaraiBorangMPBData {
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraSenaraiBorangMPBData.class);
	Vector list = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public void  setSenaraiPB(String idPermohonan)throws Exception {
		Db db = null;
	    String sql = "";
	    
	    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT D.ID_HAKMILIKPB,B.ID_HAKMILIK,A.ID_PIHAKBERKEPENTINGAN,A.NAMA_PB, A.NO_PB, B.NO_LOT, B.NO_PT,"+
	      		" C.NAMA_MUKIM, D.SYER_ATAS, D.SYER_BAWAH, D.JUMLAH_AWARD, B.ID_PERMOHONAN," +
	      		" F.KETERANGAN"+

	      		" FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
	      		" TBLPPTHAKMILIK B,"+
	      		" TBLRUJMUKIM C,"+
	      		" TBLPPTHAKMILIKPB D,"+
	      		" TBLPPTPERMOHONAN E," +
	      		" TBLRUJLOT F " +
//	      		" TBLRUJJENISPB G"+
     
	      		" WHERE A.ID_PIHAKBERKEPENTINGAN = D.ID_PIHAKBERKEPENTINGAN"+
	      		" AND B.ID_HAKMILIK = D.ID_HAKMILIK"+
	      		" AND C.ID_MUKIM = B.ID_MUKIM"+
	      		" AND E.ID_PERMOHONAN = B.ID_PERMOHONAN" +
	      		" AND F.ID_LOT(+) = B.ID_LOT" +
//	      		" AND G.ID_JENISPB = A.ID_JENISPB" +
//	      		" AND G.KOD_JENIS_PB NOT IN('WJ','PG','PS')"+
	      		" AND B.ID_PERMOHONAN = '"+idPermohonan+"'";

//	      sql = "SELECT D.ID_HAKMILIKPB,B.ID_HAKMILIK,A.ID_PIHAKBERKEPENTINGAN,A.NAMA_PB, A.NO_PB, B.NO_LOT, B.NO_PT,"+
//    		" C.NAMA_MUKIM, D.SYER_ATAS, D.SYER_BAWAH, D.JUMLAH_AWARD, B.ID_PERMOHONAN," +
//    		" F.KETERANGAN"+
//
//    		" FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
//    		" TBLPPTHAKMILIK B,"+
//    		" TBLRUJMUKIM C,"+
//    		" TBLPPTHAKMILIKPB D,"+
//    		" TBLPPTPERMOHONAN E," +
//    		" TBLRUJLOT F," +
//    		" TBLRUJJENISPB G"+
//
//    		" WHERE A.ID_PIHAKBERKEPENTINGAN = D.ID_PIHAKBERKEPENTINGAN"+
//    		" AND B.ID_HAKMILIK = D.ID_HAKMILIK"+
//    		" AND C.ID_MUKIM = B.ID_MUKIM"+
//    		" AND E.ID_PERMOHONAN = B.ID_PERMOHONAN" +
//    		" AND F.ID_LOT = B.ID_LOT" +
//    		" AND G.ID_JENISPB = A.ID_JENISPB" +
//    		" AND G.KOD_JENIS_PB NOT IN('WJ','PG','PS')"+
//    		" AND B.ID_PERMOHONAN = '"+idPermohonan+"'";	      
	      
	      
	      
	      myLogger.info("SQL LIST PB DAYAH :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("ID_PIHAKBERKEPENTINGAN", rs.getString("ID_PIHAKBERKEPENTINGAN"));
	    	  h.put("ID_HAKMILIKPB", rs.getString("ID_HAKMILIKPB"));
	    	  h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK"));
	    	  h.put("NAMA_PB", rs.getString("NAMA_PB"));
	    	  h.put("NO_PB", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
	      	  h.put("NO_LOT", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
	    	  h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
	    	  h.put("SYER_ATAS", rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS")+"/"+rs.getString("SYER_BAWAH"));
	    	  h.put("SYER_BAWAH", rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_BAWAH"));
	    	  h.put("JUMLAH_AWARD", rs.getString("JUMLAH_AWARD")==null?"":"RM " + rs.getString("JUMLAH_AWARD"));
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if(count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("ID_PIHAKBERKEPENTINGAN","");
	    	  h.put("NAMA_PB", "Tiada rekod.");
	    	  h.put("NO_PB", "");
	    	  h.put("NO_LOT", "");
	    	  h.put("NO_PT", "");
	    	  h.put("NAMA_MUKIM", "");
	    	  h.put("SYER_ATAS", "");
	    	  h.put("SYER_BAWAH", "");
	    	  h.put("JUMLAH_AWARD","");
	    	  list.addElement(h);
	      }
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public Vector getSenaraiPB(){
		return list;
	}


}
