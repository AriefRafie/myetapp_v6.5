package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmHakmilikSementaraMaklumatPampasanPBData {
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraMaklumatPampasanPBData.class);
	
	Vector list = null;
	Vector listDetails = null;
	
	public Vector getRecord(String id_pb)throws Exception {
		
		Db db = null;
	    String sql = "";
	    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT C.ID_HAKMILIKPB,A.NAMA_PB, A.NO_PB, B.NO_LOT, B.NO_PT,D.NAMA_MUKIM,"+
	    	    " C.SYER_ATAS,C.SYER_BAWAH,G.TAWARAN_PAMPASAN,B.LUAS_AMBIL,E.KETERANGAN AS UNIT_LOT,"+
	    	    " F.KETERANGAN AS UNIT_LUAS"+


	    	    " FROM TBLPPTPIHAKBERKEPENTINGAN A,"+
	    	    " TBLPPTHAKMILIK B,"+
	    	    " TBLPPTHAKMILIKPB C,"+
	    	    " TBLRUJMUKIM D,"+
	    	    " TBLRUJLOT E,"+
	    	    " TBLRUJLUAS F," +
	    	    " TBLPPTBORANGQ G"+
     
	    	    " WHERE A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN"+
	    	    " AND B.ID_HAKMILIK = C.ID_HAKMILIK" +
	    	    " AND B.ID_HAKMILIK = G.ID_HAKMILIK"+
	    	    " AND D.ID_MUKIM = B.ID_MUKIM"+
	    	    " AND E.ID_LOT(+) = B.ID_LOT"+
	    	    " AND F.ID_LUAS(+) = B.ID_UNITLUASAMBIL"+
	    	    " AND A.ID_PIHAKBERKEPENTINGAN = '"+id_pb+"'";
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      myLogger.info("GETRECORD PENAMA CEK >> "+sql);
	      Hashtable h;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB")==null?"-":rs.getString("ID_HAKMILIKPB"));
	    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"-":rs.getString("NAMA_PB"));
	    	  h.put("NO_PB",rs.getString("NO_PB")==null?"-":rs.getString("NO_PB"));
	    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"-":rs.getString("UNIT_LOT") + " " + rs.getString("NO_LOT"));
	    	  h.put("NO_PT",rs.getString("NO_PT")==null?"-":rs.getString("NO_PT"));
	    	  h.put("NAMA_MUKIM",rs.getString("NAMA_MUKIM")==null?"-":rs.getString("NAMA_MUKIM"));
	    	  h.put("SYER_ATAS",rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS")+ "/" + rs.getString("SYER_BAWAH"));
	    	  h.put("TAWARAN_PAMPASAN",rs.getString("TAWARAN_PAMPASAN")==null?"-":Utils.format2Decimal(Double.parseDouble(rs.getString("TAWARAN_PAMPASAN").toString())));
	    	  h.put("LUAS_AMBIL",rs.getString("LUAS_AMBIL")==null?"":rs.getString("LUAS_AMBIL") + " " + rs.getString("UNIT_LUAS"));
	    	  list.addElement(h);
	    	  
	      }
	      
	      
	      return list;
	    }
	    finally {
		      if (db != null) db.close();
		    }
		
	}


	public Vector getRecordDetails(String id_permohonan,String id_pb)throws Exception {
		
		Db db = null;
	    String sql = "";
	    try {
	      listDetails = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = " SELECT c.id_hakmilikpb, b.id_hakmilik, d.id_pihakberkepentingan, d.nama_pb, "+
	    	    " d.no_pb, b.no_lot, b.no_pt, f.nama_mukim, c.syer_atas, c.syer_bawah,e.tawaran_pampasan,  "+
	    	    " a.id_permohonan, g.keterangan, i.bayar_pampasan,e.luas_sewa,k.keterangan AS unit_luas,l.keterangan AS unit_lot "+
	    	    " FROM tblpptpermohonan a,tblppthakmilik b, "+
	    	    " tblppthakmilikpb c,tblpptpihakberkepentingan d,tblpptborangq e,tblrujmukim f, "+
	    	    " tblrujlot g,tblpptaward i,tblrujluas k,tblrujlot l "+
	    	    " WHERE a.id_permohonan = b.id_permohonan AND b.id_hakmilik = c.id_hakmilik "+
	    	    " AND c.id_pihakberkepentingan = d.id_pihakberkepentingan AND a.id_permohonan = e.id_permohonan "+
	    	    " AND b.id_hakmilik = e.id_hakmilik AND b.id_mukim = f.id_mukim(+) "+
	    	    " AND b.id_lot = g.id_lot(+) AND c.id_hakmilikpb = i.id_hakmilikpb "+     
	    	    " AND e.id_unitluas = k.id_luas(+) AND b.id_lot = l.id_lot(+) "+
	    	    " AND a.id_permohonan = '"+id_permohonan+"' AND d.id_pihakberkepentingan = '"+id_pb+"' ";

	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB")==null?"-":rs.getString("ID_HAKMILIKPB"));
	    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"-":rs.getString("NAMA_PB"));
	    	  h.put("NO_PB",rs.getString("NO_PB")==null?"-":rs.getString("NO_PB"));
	    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"-": rs.getString("NO_LOT"));
	    	  h.put("NO_PT",rs.getString("NO_PT")==null?"-":rs.getString("NO_PT"));
	    	  h.put("NAMA_MUKIM",rs.getString("NAMA_MUKIM")==null?"-":rs.getString("NAMA_MUKIM"));
	    	  h.put("SYER_ATAS",rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS")+ "/" + rs.getString("SYER_BAWAH"));
	    	  h.put("BAYAR_PAMPASAN",rs.getString("BAYAR_PAMPASAN")==null?"-":Utils.format2Decimal(Double.parseDouble(rs.getString("BAYAR_PAMPASAN").toString())));
	    	  h.put("LUAS_AMBIL",rs.getString("LUAS_SEWA")==null?"":rs.getString("LUAS_SEWA") + " " + rs.getString("UNIT_LUAS"));
	    	  listDetails.addElement(h);
	    	  
	      }
	      
	      
	      return listDetails;
	    }
	    finally {
		      if (db != null) db.close();
		    }
		
	}	

}
