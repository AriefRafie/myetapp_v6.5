package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmHakmilikSementaraSenaraiPampasanPBData {
	
	Vector list = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraSenaraiJPPHData.class);
	
	@SuppressWarnings("unchecked")
	public void  setSenaraiPB(String idPermohonan,String id_hakmilik )throws Exception {
		Db db = null;
	    String sql = "";
	    
	    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = " SELECT C.ID_HAKMILIKPB, B.ID_HAKMILIK, D.ID_PIHAKBERKEPENTINGAN, D.NAMA_PB, "+
	      		" D.NO_PB, B.NO_LOT, B.NO_PT, F.NAMA_MUKIM, C.SYER_ATAS, C.SYER_BAWAH, "+
	      		" E.TAWARAN_PAMPASAN, A.ID_PERMOHONAN, G.KETERANGAN, I.BAYAR_PAMPASAN, "+
	      		
	      		" CASE "+
			    " WHEN B.no_lot IS NOT NULL AND B.no_pt IS NULL THEN B.no_lot "+
				" WHEN B.no_lot IS NULL AND B.no_pt IS NULL THEN G.keterangan || B.no_pt "+
				" WHEN B.no_lot IS NULL AND B.no_pt IS NOT NULL THEN  G.keterangan || B.no_pt "+   
				" WHEN B.no_lot IS NOT NULL AND B.no_pt IS NOT NULL THEN G.keterangan || B.no_pt || CHR(32) || CHR(40) || B.no_lot || CHR(41) "+
				" ELSE '' "+
				" END AS NO_LOTPT "+
	      		
				" FROM TBLPPTPERMOHONAN A,TBLPPTHAKMILIK B, "+
	      		" TBLPPTHAKMILIKPB C,TBLPPTPIHAKBERKEPENTINGAN D, "+
	      		" TBLPPTBORANGQ E,TBLRUJMUKIM F,TBLRUJLOT G,TBLPPTAWARD I "+
	      		" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	      		" AND B.ID_HAKMILIK = C.ID_HAKMILIK AND C.ID_PIHAKBERKEPENTINGAN = D.ID_PIHAKBERKEPENTINGAN "+
	      		" AND A.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_HAKMILIK = E.ID_HAKMILIK "+
	      		" AND B.ID_MUKIM = F.ID_MUKIM(+) AND B.ID_LOT = G.ID_LOT(+) AND C.ID_HAKMILIKPB = I.ID_HAKMILIKPB "+ 
	      		//" AND J.ID_SIASATAN = (SELECT MAX(ID_SIASATAN) FROM TBLPPTSIASATAN WHERE ID_PERMOHONAN = A.ID_PERMOHONAN AND ID_HAKMILIK = '"+id_hakmilik+"') "+
	      		" AND A.ID_PERMOHONAN = '"+idPermohonan+"' AND B.ID_HAKMILIK = '"+id_hakmilik+"' ";	
	      
	      myLogger.info("SQL setSenaraiPB >> "+sql);  
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
	    	  h.put("NAMA_PB", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
	    	  h.put("NO_PB", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
	    	  h.put("NO_LOT", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
	      	  //h.put("NO_LOT", rs.getString("NO_LOT")==null?rs.getString("KETERANGAN") + " " + rs.getString("NO_PT").equals(null):rs.getString("KETERANGAN") + " " + rs.getString("NO_LOT") + "/-");
	    	  h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
	    	  h.put("SYER_ATAS", rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS")+"/"+rs.getString("SYER_BAWAH"));
	    	  h.put("SYER_BAWAH", rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_BAWAH"));
	    	  h.put("TAWARAN_PAMPASAN", rs.getString("TAWARAN_PAMPASAN")==null?"":"RM " + Utils.format2Decimal(Double.parseDouble(rs.getString("TAWARAN_PAMPASAN").toString())));
	    	  h.put("BAYAR_PAMPASAN", rs.getString("BAYAR_PAMPASAN")==null?"":"RM " + Utils.format2Decimal(Double.parseDouble(rs.getString("BAYAR_PAMPASAN").toString())));
	    	  h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
//	    	  h.put("ID_SIASATAN", rs.getString("ID_SIASATAN")==null?"":rs.getString("ID_SIASATAN"));
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
	    	  h.put("BAYAR_PAMPASAN","");
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
