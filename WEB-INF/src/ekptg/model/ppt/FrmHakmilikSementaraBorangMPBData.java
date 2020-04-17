package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmHakmilikSementaraBorangMPBData {
	
	Vector list = null;
	
	public void setDataPB(String idHakmilikPB)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			list = new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT A.NAMA_PB,A.NO_PB,D.KETERANGAN AS JENIS_PB, B.NO_HAKMILIK,B.NO_LOT,B.LUAS_LOT," +
		    		" E.KETERANGAN AS LOT,F.KETERANGAN AS LUAS " +
		    		" FROM TBLPPTPIHAKBERKEPENTINGAN A,TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLRUJJENISPB D," +
		    		" TBLRUJLOT E, TBLRUJLUAS F" +
		    		" WHERE A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN" +
		    		" AND B.ID_HAKMILIK = C.ID_HAKMILIK" +
		    		" AND D.ID_JENISPB(+) = A.ID_JENISPB" +
		    		" AND E.ID_LOT(+) = B.ID_LOT" +
		    		" AND F.ID_LUAS = B.ID_UNITLUASLOT" +
		    		" AND C.ID_HAKMILIKPB = '"+idHakmilikPB+"'";
		    System.out.println("SQL :: "+sql);
		    ResultSet rs = stmt.executeQuery(sql);  
		    Hashtable h;
		    
		    while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"-":rs.getString("NAMA_PB"));
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"-":rs.getString("NO_PB"));
		    	  h.put("JENIS_PB",rs.getString("JENIS_PB")==null?"-":rs.getString("JENIS_PB").toUpperCase());
		    	  h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK")==null?"-":rs.getString("NO_HAKMILIK"));
		    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"-":rs.getString("NO_LOT"));
		    	  h.put("LUAS_LOT",rs.getString("LUAS_LOT")==null?"-":rs.getString("LUAS_LOT")+" "+rs.getString("LUAS"));

		    	  list.addElement(h);
		    	  
		    }

			
		}
		finally {
		      if (db != null) db.close();
		    }
		
	}
	public Vector getDataPB(){
		return list;
	}

}
