package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmHakmilikSementaraSenaraiAgihanTugasSPTData {
	Vector list = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public void setList(String carianFail, String CarianTarikhMohon, String cStatus)throws Exception {
		
		Db db = null;		
	    String sql = "";
	    String chkData = carianFail.trim();
	    
	    try {
	    	list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql = "SELECT a.id_permohonan, c.id_status, a.no_permohonan, a.id_fail, d.no_fail, b.id_tugas, b.tarikh_agih, b.perihal_agih, c.keterangan, su.nama_suburusan, k.nama_kementerian, b.id_pegawai, e.nama_pegawai, e.jawatan, a.tarikh_permohonan ";
	      sql +="FROM Tblpptpermohonan a, Tblppttugas b, Tblrujstatus c, Tblpfdfail d, Tblrujsuburusan su, Tblrujkementerian k, Tblrujpegawai e ";
	      sql +="WHERE a.id_permohonan = b.id_permohonan(+) AND b.id_pegawaipenerima = e.id_pegawai(+)  ";
	      sql +="AND a.id_status = c.id_status(+) AND d.id_suburusan = su.id_suburusan(+) AND d.id_kementerian = k.id_kementerian(+) AND d.id_fail = a.id_fail(+) AND c.kod_status IN ('28','32') AND c.id_seksyen = 1 AND d.id_suburusan = 53 ";
	      
	      if(carianFail != null){
	    	  sql = sql + " AND UPPER(d.no_fail) LIKE '%'||'" + carianFail.toUpperCase() + "'||'%'";  
	      }
	      
	      if(CarianTarikhMohon != null){
	    	  if (!CarianTarikhMohon.toString().trim().equals("")){
	    	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	    	  sql +="AND b.tarikh_agih = '" + sdf.format(Format.parse(CarianTarikhMohon)).toUpperCase()+ "' ";
	    	  }
	      }
	      if(cStatus != null){
	    	  if (!cStatus.trim().equals("")){
	    		  if (!cStatus.trim().equals("0")){
	    			  sql +="AND c.id_status = '" + cStatus + "' ";
	    		  }
	    	  }	  
	      }
	      ResultSet rs = stmt.executeQuery(sql); 
	      
	      Hashtable h; 
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan"));
	    	  h.put("id_status", rs.getString("id_status"));
	    	  h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	  h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    	  h.put("id_tugas", rs.getString("id_tugas")==null?"":rs.getString("id_tugas"));
	    	  h.put("tarikh_agih", rs.getDate("tarikh_agih")==null?"":Format.format(rs.getDate("tarikh_agih")));
	    	  h.put("perihal_agih", rs.getString("perihal_agih")==null?"BELUM DIAGIHKAN":rs.getString("perihal_agih"));
	    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
	    	  h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
	    	  h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
	    	  h.put("jawatan", rs.getString("jawatan")==null?"":rs.getString("jawatan"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if(count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("id_permohonan", "");
	    	  h.put("no_permohonan", "");
	    	  h.put("id_status", "");
	    	  h.put("id_fail", "");
	    	  h.put("no_fail", "Tiada rekod.");
	    	  h.put("id_tugas", "");
	    	  h.put("tarikh_agih", "");
	    	  h.put("perihal_agih", "");
	    	  h.put("keterangan", "");
	    	  h.put("nama_suburusan", "");
	    	  h.put("nama_kementerian", "");
	    	  h.put("id_pegawai", "");
	    	  h.put("nama_pegawai", "");
	    	  h.put("jawatan", "");
	    	  h.put("tarikh_permohonan", "");
	    
	    	  list.addElement(h);
	      }
	      
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public Vector getList(){
		  return list;
	  }	

}
