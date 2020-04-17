package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmHakmilikSementaraMaklumatPerundinganData {
	
	Vector paparSet = null;
	Vector listRundingan = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setDataRundingan (String idSiasatan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
			db = new Db();
			paparSet = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.NO_KES,A.NO_KES_SEBELUM,A.NO_SIASATAN,A.ALAMAT1,A.ALAMAT2,A.ALAMAT3,A.POSKOD,A.ID_NEGERI,"+
				  " A.ID_BANDAR,A.STATUS_SIASATAN,A.TARIKH_SIASATAN,A.TEMPAT,A.TARIKH_AKHIR_TAMPAL,A.ALASAN_TANGGUH,A.MASA_SIASATAN,A.JENIS_WAKTU_SIASATAN,A.ID_HAKMILIK"+

				  " FROM TBLPPTSIASATAN A"+
				  " WHERE A.ID_SIASATAN = '" +idSiasatan+"'";
			
			ResultSet rs = stmt.executeQuery(sql);  
		    Hashtable h;
		    while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NO_KES",rs.getString("NO_KES")==null?"":rs.getString("NO_KES"));
		    	  h.put("NO_KES_SEBELUM",rs.getString("NO_KES_SEBELUM")==null?"":rs.getString("NO_KES_SEBELUM"));
		    	  h.put("NO_SIASATAN",rs.getString("NO_SIASATAN")==null?"":rs.getString("NO_SIASATAN"));
		    	  h.put("ALAMAT1",rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
		    	  h.put("ALAMAT2",rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
		    	  h.put("ALAMAT3",rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	  h.put("POSKOD",rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
		    	  h.put("ID_BANDAR",rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
		    	  h.put("STATUS_SIASATAN",rs.getString("STATUS_SIASATAN")==null?"":rs.getString("STATUS_SIASATAN"));
		    	  h.put("TARIKH_SIASATAN",rs.getString("TARIKH_SIASATAN")==null?"":sdf.format(rs.getDate("TARIKH_SIASATAN")));
		    	  h.put("TEMPAT",rs.getString("TEMPAT")==null?"":rs.getString("TEMPAT"));
		    	  h.put("TARIKH_AKHIR_TAMPAL",rs.getString("TARIKH_AKHIR_TAMPAL")==null?"":sdf.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
		    	  h.put("ALASAN_TANGGUH",rs.getString("ALASAN_TANGGUH")==null?"":rs.getString("ALASAN_TANGGUH"));
		    	  h.put("MASA_SIASATAN",rs.getString("MASA_SIASATAN")==null?"":rs.getString("MASA_SIASATAN"));
		    	  h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("JENIS_WAKTU_SIASATAN",rs.getString("JENIS_WAKTU_SIASATAN")==null?"":rs.getString("JENIS_WAKTU_SIASATAN"));

		    	  paparSet.addElement(h);
		    }
			
		}
		 finally {
		      if (db != null) db.close();
	    }
	}
	
public void setRundingan (String idPermohonan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
			db = new Db();
			listRundingan = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_SIASATAN,A.NO_KES,A.NO_SIASATAN,A.TARIKH_SIASATAN,A.TEMPAT,A.STATUS_SIASATAN,C.NO_LOT," +
					" C.ID_HAKMILIK"+

				  " FROM TBLPPTSIASATAN A,"+
				  " TBLPPTPERMOHONAN B," +
				  " TBLPPTHAKMILIK C"+
     
				  " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN" +
				  " AND C.ID_HAKMILIK = A.ID_HAKMILIK"+
				  " AND A.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			  ResultSet rs = stmt.executeQuery(sql);  
			  
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("ID_SIASATAN",rs.getString("ID_SIASATAN")== null?"":rs.getString("ID_SIASATAN"));
		    	  h.put("NO_KES", rs.getString("NO_KES")== null?"":rs.getString("NO_KES"));
		    	  h.put("NO_SIASATAN", rs.getString("NO_SIASATAN")== null?"":rs.getString("NO_SIASATAN"));
		    	  h.put("TARIKH_SIASATAN", rs.getString("TARIKH_SIASATAN")== null?"":sdf.format(rs.getDate("TARIKH_SIASATAN")));
		    	  h.put("TEMPAT", rs.getString("TEMPAT")== null?"":rs.getString("TEMPAT"));
		    	  h.put("NO_LOT", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));

		    	  if(rs.getString("STATUS_SIASATAN").equals("1")){
		    		  h.put("STATUS_SIASATAN","DALAM PROSES");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("2")){
		    		  h.put("STATUS_SIASATAN","DITUNDA SEBELUM SIASATAN");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("3")){
		    		  h.put("STATUS_SIASATAN","DIBATALKAN");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("4")){
		    		  h.put("STATUS_SIASATAN","ULANG SIASATAN");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("5")){
		    		  h.put("STATUS_SIASATAN","SAMBUNG SIASATAN");
		    	  }
		    	  else if(rs.getString("STATUS_SIASATAN").equals("6")){
		    		  h.put("STATUS_SIASATAN","SELESAI");
		    	  }
		    	  else{
		    		  h.put("STATUS_SIASATAN","");
		    	  }
		    	  bil++;
		    	  count++;
		    	  listRundingan.addElement(h);
		      }
		      
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	  h.put("ID_HAKMILIK","");
		    	  h.put("ID_SIASATAN", "");
		    	  h.put("NO_KES","Tiada rekod.");
		    	  h.put("NO_SIASATAN", "");
		    	  h.put("TARIKH_SIASATAN", "");
		    	  h.put("TEMPAT", "");
		    	  h.put("NO_LOT", "");
		    	  h.put("STATUS_SIASATAN", "");
		    	  listRundingan.addElement(h); 
		      }
		}
		finally {
		      if (db != null) db.close();
		    }
		
	}

	public Vector getListRundingan(){
		return listRundingan;
	}
	
	public Vector getDataRundingan(){
		return paparSet;
	}
	
	

}
