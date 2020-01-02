package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.Utils;

public class FrmHakmilikSementaraPerundinganKeputusanData {
	
	Vector paparHakmilik = null;
	Vector listPb = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setDataHakmilik(String idHakmilikPB)throws Exception{
		Db db = null;
		String sql = "";
		
		try{
			paparHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.ID_BORANGQ,C.ID_HAKMILIK,B.TAWARAN_PAMPASAN,B.LUAS_SEWA,D.KETERANGAN AS LUAS,B.TEMPOH_PENDUDUKAN,B.UNIT_TEMPOH," +
					"B.TARIKH_MULA,B.TARIKH_AKHIR,E.NAMA_PB,G.NO_AKAUN,G.NILAI_SEWA_BULANAN, G.KEPUTUSAN_PERUNDINGAN, G.ULASAN_KEPUTUSAN, G.NILAI_SEWA_KESELURUHAN, " +
					"G.NAMA_BANK " +
					"FROM TBLPPTHAKMILIK A, TBLPPTBORANGQ B,TBLPPTSIASATAN C, TBLRUJLUAS D,TBLPPTPIHAKBERKEPENTINGAN E, TBLRUJBANK F, TBLPPTHAKMILIKPB G " +
					"WHERE A.ID_HAKMILIK = B.ID_HAKMILIK " +
					"AND A.ID_HAKMILIK = C.ID_HAKMILIK " +
					"AND D.ID_LUAS = B.ID_UNITLUAS " +
					"AND A.ID_HAKMILIK = G.ID_HAKMILIK " +
					"AND E.ID_PIHAKBERKEPENTINGAN = G.ID_PIHAKBERKEPENTINGAN " +
					"AND G.ID_HAKMILIKPB = '"+idHakmilikPB+"'";
			
			 ResultSet rs = stmt.executeQuery(sql);  
		     Hashtable h;
		     
		     while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_BORANGQ",rs.getString("ID_BORANGQ")==null?"":rs.getString("ID_BORANGQ"));
		    	  h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("TAWARAN_PAMPASAN",rs.getString("TAWARAN_PAMPASAN")==null?" ":rs.getString("TAWARAN_PAMPASAN"));
		    	  h.put("LUAS_SEWA",rs.getString("LUAS_SEWA")==null?"":rs.getString("LUAS_SEWA")+" " +rs.getString("LUAS"));
		    	  if(rs.getString("UNIT_TEMPOH").equals("1")){
			    	  h.put("TEMPOH_PENDUDUKAN",rs.getString("TEMPOH_PENDUDUKAN")==null?"":rs.getString("TEMPOH_PENDUDUKAN")+" BULAN");
		    	  }
		    	  else{
			    	  h.put("TEMPOH_PENDUDUKAN",rs.getString("TEMPOH_PENDUDUKAN")==null?"":rs.getString("TEMPOH_PENDUDUKAN")+" TAHUN");

		    	  }
		    	  h.put("TARIKH_MULA",rs.getString("TARIKH_MULA")==null?"":sdf.format(rs.getDate("TARIKH_MULA")));
		    	  h.put("TARIKH_AKHIR",rs.getString("TARIKH_AKHIR")==null?"":sdf.format(rs.getDate("TARIKH_AKHIR")));
		    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	  h.put("NO_AKAUN",rs.getString("NO_AKAUN")==null?"":rs.getString("NO_AKAUN"));
		    	  h.put("NAMA_BANK",rs.getString("NAMA_BANK")==null?"":rs.getString("NAMA_BANK"));
		    	  h.put("NILAI_SEWA_BULANAN",rs.getString("NILAI_SEWA_BULANAN")==null?" ":rs.getString("NILAI_SEWA_BULANAN"));
		    	  h.put("KEPUTUSAN_PERUNDINGAN",rs.getString("KEPUTUSAN_PERUNDINGAN")==null?"":rs.getString("KEPUTUSAN_PERUNDINGAN"));
		    	  h.put("ULASAN_KEPUTUSAN",rs.getString("ULASAN_KEPUTUSAN")==null?"":rs.getString("ULASAN_KEPUTUSAN"));
		    	  h.put("NILAI_SEWA_KESELURUHAN",rs.getString("NILAI_SEWA_KESELURUHAN")== null?"":rs.getString("NILAI_SEWA_KESELURUHAN"));
		    	  
		    	  paparHakmilik.addElement(h);
		    	  
		     }
		}
		finally {
		      if (db != null) db.close();
		    }
		
	}
	public void setListPb(String idSiasatan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			listPb = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT C.ID_HAKMILIKPB,A.NAMA_PB,A.NO_PB,B.NO_HAKMILIK,B.NO_LOT,E.KETERANGAN AS LOT,H.TAWARAN_PAMPASAN, F.KOD_JENIS_HAKMILIK " +
					"FROM TBLPPTPIHAKBERKEPENTINGAN A,TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLPPTSIASATAN D,TBLRUJLOT E,TBLRUJJENISHAKMILIK F,TBLRUJJENISPB G,TBLPPTBORANGQ H " +
					"WHERE A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN " +
					"AND B.ID_HAKMILIK = C.ID_HAKMILIK " +
					"AND B.ID_HAKMILIK = D.ID_HAKMILIK " +
					"AND B.ID_HAKMILIK = H.ID_HAKMILIK " +
					"AND E.ID_LOT = B.ID_LOT " +
					"AND F.ID_JENISHAKMILIK = B.ID_JENISHAKMILIK " +
					"AND G.ID_JENISPB = A.ID_JENISPB " +
					"AND G.KOD_JENIS_PB NOT IN ('WJ','PS','PG') " +
					"AND D.ID_SIASATAN = '"+idSiasatan+"'";
			
			 ResultSet rs = stmt.executeQuery(sql);  
		     Hashtable h;
		     int bil = 1;
		     int count = 0;
		     while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("BIL",bil);
		    	  h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	  h.put("NAMA_PB",rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	  h.put("NO_PB",rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK")==null?"":rs.getString("KOD_JENIS_HAKMILIK") + " " + rs.getString("NO_HAKMILIK"));
		    	  h.put("NO_LOT",rs.getString("NO_LOT")==null?"":rs.getString("LOT")+ " " + rs.getString("NO_LOT"));
		    	  h.put("TAWARAN_PAMPASAN",rs.getString("TAWARAN_PAMPASAN")==null?"":Utils.format2Decimal(Double.parseDouble(rs.getString("TAWARAN_PAMPASAN").toLowerCase())));
		    	  bil++;
		    	  count++;
		    	  listPb.addElement(h);
		    	  
		     }
		     if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("BIL","");
		    	  h.put("ID_HAKMILIKPB","");
		    	  h.put("NAMA_PB","Tiada rekod.");
		    	  h.put("NO_PB","");
		    	  h.put("NO_HAKMILIK","");
		    	  h.put("NO_LOT","");
		    	  h.put("NILAI_SEWA_BULANAN","");
		    	  listPb.addElement(h);
		     }

			
		}
		
		finally {
		      if (db != null) db.close();
		    }
	}
	public static void updateKeputusan(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			String idSiasatan = (String)data.get("idSiasatan");
			String idHakmilikPB = (String)data.get("idHakmilikPB");
			String idHakmilik = (String)data.get("idHakmilik");
			String idBorangQ = (String)data.get("idBorangQ");
			String nilaiSewaBulanan = (String)data.get("nilaiSewaBulanan");
			String keputusan = (String)data.get("keputusan");
			String ulasanKeputusan = (String)data.get("ulasanKeputusan");
			String nilaiSewaSeluruh = (String)data.get("nilaiSewaSeluruh");
			String amaunPampasan = (String)data.get("amaunPampasan");
			String idKemaskini = (String)data.get("idKemaskini");
			
			db = new Db();
			Statement stmtH = db.getStatement();
			SQLRenderer rH = new SQLRenderer();
			
			rH.update("id_Hakmilikpb",idHakmilikPB);
			rH.add("NILAI_SEWA_BULANAN",nilaiSewaBulanan);
			rH.add("NILAI_SEWA_KESELURUHAN",nilaiSewaSeluruh);
			rH.add("KEPUTUSAN_PERUNDINGAN",keputusan);
			rH.add("ULASAN_KEPUTUSAN",ulasanKeputusan);
			rH.add("id_Kemaskini",idKemaskini);
			rH.add("tarikh_Kemaskini",rH.unquote("sysdate"));
			
			sql = rH.getSQLUpdate("tblppthakmilikpb");
			stmtH.executeUpdate(sql);
			
			Statement stmtQ = db.getStatement();
			SQLRenderer rQ = new SQLRenderer();
			
			rQ.update("id_Borangq",idBorangQ);
			rQ.add("TAWARAN_PAMPASAN",amaunPampasan);
			rQ.add("id_Kemaskini",idKemaskini);
			rQ.add("tarikh_Kemaskini",rH.unquote("sysdate"));
			
			sql = rQ.getSQLUpdate("tblpptborangq");
			stmtQ.executeUpdate(sql);
			
		}
		finally {
		      if (db != null) db.close();
		    }
		
	}
	public Vector getListPb(){
		return listPb;
	}
	public Vector getDataHakmilik(){
		return paparHakmilik;
	}

}
