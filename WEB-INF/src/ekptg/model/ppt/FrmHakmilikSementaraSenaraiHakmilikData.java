package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmHakmilikSementaraSenaraiHakmilikData {
	
	Vector list = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public void  setSenaraiHakmilik(String idFail,String idPermohonan)throws Exception {
		Db db = null;
	    String sql = "";
	    
	    try {
	    	list = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT  B.ID_HAKMILIK,B.NO_LOT, B.NO_PT, B.NO_HAKMILIK, B.LUAS_LOT, B.LUAS_AMBIL, C.NAMA_DAERAH, D.NAMA_MUKIM " +
		      		" FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLRUJDAERAH C, TBLRUJMUKIM D"+
		      		" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN" +
		      		" AND C.ID_DAERAH = B.ID_DAERAH" +
		      		" AND D.ID_MUKIM = B.ID_MUKIM" +
		      		" AND A.ID_PERMOHONAN = '" + idPermohonan + "' " +
		      		" AND A.ID_FAIL = '" + idFail + "'";
		      
		      ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("BIL", bil);
		    	  if (rs.getString("NO_PT") != null){
			    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT") + "/" + rs.getString("NO_LOT"));
 
		    	  }
		    	  else{
		    		  h.put("NO_LOT",rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
		    	  }
		    	  h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("NO_PT",rs.getString("NO_PT")==null?"":rs.getString("NO_PT"));
		    	  h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
		    	  h.put("LUAS_LOT",rs.getString("LUAS_LOT")==null?"":rs.getString("LUAS_LOT"));
		    	  h.put("LUAS_AMBIL",rs.getString("LUAS_AMBIL")==null?"":rs.getString("LUAS_AMBIL"));
		    	  h.put("NAMA_DAERAH",rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
		    	  h.put("NAMA_MUKIM",rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("BIL", "");
		    	  h.put("NO_LOT","Tiada rekod.");
		    	  h.put("NO_PT","");
		    	  h.put("NO_HAKMILIK","");
		    	  h.put("LUAS_LOT","");
		    	  h.put("LUAS_AMBIL","");
		    	  h.put("NAMA_DAERAH","");
		    	  h.put("NAMA_MUKIM","");
		    	  list.addElement(h);
		      }
		      		

		      
	    }finally {
	      if (db != null) db.close();
	    }
	}
	
	public Vector getSenaraiHakmilik(){
		return list;
	}

}
