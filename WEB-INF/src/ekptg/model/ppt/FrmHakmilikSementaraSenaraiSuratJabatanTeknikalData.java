package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmHakmilikSementaraSenaraiSuratJabatanTeknikalData {
	
	Vector listSuratJabatan = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setListSuratJabatan(String idPermohonan)throws Exception {
		
		Db db = null;
	    String sql = "";
	    
	    try{
	    	  listSuratJabatan = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_ULASANTEKNIKAL,C.NAMA_JABATAN,A.PERIHAL,A.BIL_SURAT, A.TARIKH_SURAT,A.TEMPOH"+

		    	    " FROM TBLPPTULASANTEKNIKAL A,"+
		    	    " TBLPPTPERMOHONAN B,"+
		    	    " TBLPPTJABATANTEKNIKAL C"+
     
		    	    " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN"+
		    	    " AND C.ID_JABATANTEKNIKAL = A.ID_JABATANTEKNIKAL"+
		    	    " AND A.ID_PERMOHONAN = '"+idPermohonan+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("idUlasanTeknikal", rs.getString("ID_ULASANTEKNIKAL")== null?"":rs.getString("ID_ULASANTEKNIKAL"));
		    	  h.put("namaJabatan",rs.getString("NAMA_JABATAN")== null?"":rs.getString("NAMA_JABATAN"));
		    	  h.put("perihal",rs.getString("PERIHAL")==null?"":rs.getString("PERIHAL"));
		    	  h.put("bilSurat", rs.getString("BIL_SURAT")== null?"":rs.getString("BIL_SURAT"));
		    	  h.put("tarikhSurat", rs.getString("TARIKH_SURAT")== null?"":sdf.format(rs.getDate("TARIKH_SURAT")));
		    	  h.put("tempoh", rs.getString("TEMPOH")== null?"":rs.getString("TEMPOH"));
		    	  listSuratJabatan.addElement(h);
		    	  
		    	  bil++;
		    	  count++;
		    	  
		      }
		      if (count == 0){
		    	  
		    	  h = new Hashtable();
		    	  h.put("bil","");
		    	  h.put("idUlasanTeknikal", "");
		    	  h.put("namaJabatan","Tiada rekod.");
		    	  h.put("perihal","");
		    	  h.put("bilSurat", "");
		    	  h.put("tarikhSurat", "");
		    	  h.put("tempoh", "");
		    	  listSuratJabatan.addElement(h);
		    	  
		    	  
		      }
		      
	    } finally{
	      if (db != null) db.close();
	    }//close finally
		
	}
	public Vector getListSuratJabatan(){
		  return listSuratJabatan;
	  }
	

}
