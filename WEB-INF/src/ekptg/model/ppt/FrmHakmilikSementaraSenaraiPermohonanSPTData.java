package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmHakmilikSementaraSenaraiPermohonanSPTData {
	
	Vector list = null;
	public void setListPemohon()throws Exception {
	    Db db = null;
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try{
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	     
	      sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status ";
	      sql +="AND p.id_status != 113 ";
	      sql +="AND f.id_suburusan = 53";
	      sql +="ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";
	     
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      
	      Hashtable h = null;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_fail", rs.getString("id_fail"));
	    	  h.put("id_status", rs.getString("id_status"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
	    	  h.put("status", rs.getString("keterangan"));
	    	  if(rs.getString("no_fail") == null){
		    		h.put("no_fail","Belum Diluluskan");
		    	}else{
		    		h.put("no_fail",rs.getString("no_fail"));
		    	}
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	      }//close while
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("id_fail", "");
	    	  h.put("id_status", "");
	    	  h.put("id_permohonan", "");
	    	  h.put("no_permohonan", "Tiada rekod.");
	    	  h.put("tarikh_permohonan","");
	    	  h.put("nama_suburusan", "");
	    	  h.put("nama_kementerian", "");
	    	  h.put("status", "");
	    	  h.put("no_fail","");
	    	  list.addElement(h);
	    	  
	      }
	      
	    }//close try 
	    finally{
	      if (db != null) db.close();
	    }//close finally
	 }//close getListPemohon
	 public Vector getList(){
		  return list;
	  }
	
}
