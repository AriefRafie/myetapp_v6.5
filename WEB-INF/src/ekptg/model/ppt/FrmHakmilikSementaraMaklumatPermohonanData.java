package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmHakmilikSementaraMaklumatPermohonanData {
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraMaklumatPermohonanData.class);
	Vector listA = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public Vector getNoSiasatan = null;
	
	public Vector getRecord(String id_permohonan) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    try {
	     listA = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql += " SELECT p.id_permohonan, f.id_negeri, p.id_negeri, p.no_permohonan, p.id_fail, f.no_fail, " +
	    	  " f.id_suburusan, p.tarikh_permohonan, p.id_status, f.id_kementerian, p.id_agensi, p.flag_peruntukan, "+
	    	  " p.flag_segera, p.id_daerah, p.tujuan, p.no_rujukan_surat, p.tarikh_kehendaki, p.alamat1, p.alamat2, p.alamat3, "+
	    	  " p.poskod, p.id_mukim, k.nama_kementerian, b.nama_daerah, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, "+
	    	  " su.nama_suburusan, s.keterangan, c.nama_negeri, p.tarikh_terima  "+
	    	  " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujnegeri c, Tblrujdaerah b, Tblrujsuburusan su, Tblrujstatus s "+
	    	  " WHERE f.id_kementerian = k.id_kementerian  "+
	    	  " AND f.id_fail = p.id_fail  "+
	    	  " AND b.id_daerah(+) = p.id_daerah  "+
	    	  " AND f.id_suburusan = su.id_suburusan  "+
	    	  " AND p.id_status = s.id_status  "+ 
	    	  " AND f.id_negeri = c.id_negeri(+)  "+ 
	    	  " AND p.id_permohonan = '"+id_permohonan+"'"; 

	      myLogger.info("SQL PB BORANG M :: "+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
	    	h.put("no_permohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
	    	h.put("id_fail", rs.getString("id_fail")==null?"-":rs.getString("id_fail"));
	    	h.put("id_status", rs.getString("id_status"));
	    	h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
	    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
	    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
	    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd").toUpperCase());
	    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg").toUpperCase());
	    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt").toUpperCase());
	    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
	    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
	    	//h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
	    	h.put("projek_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
	    	if(rs.getString("id_suburusan") == null){
	    		h.put("id_suburusan","");
	    	}else{
	    		h.put("id_suburusan",rs.getString("id_suburusan"));
	    	}
	    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"-":Format.format(rs.getDate("tarikh_terima")));
	    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
	    	if(rs.getString("id_kementerian") == null){
	    		h.put("id_kementerian","");
	    	}else{
	    		h.put("id_kementerian",rs.getString("id_kementerian"));
	    	}
	    	if(rs.getString("id_agensi") == null){
	    		h.put("id_agensi","");
	    	}else{
	    		h.put("id_agensi",rs.getString("id_agensi"));
	    	}
	    	h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
	    	h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
	    	if(rs.getString(3) == null){
	    		h.put("id_negeri_projek","");
	    	}else{
	    		h.put("id_negeri_projek",rs.getString(2));
	    	}
	    	if(rs.getString("id_daerah") == null){
	    		h.put("id_daerah","");
	    	}else{
	    		h.put("id_daerah",rs.getString("id_daerah"));
	    	}
	    	h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
	    	h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat"));
	    	h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
	    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
	    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
	    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
	    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	    	if(rs.getString("id_negeri") == null){
	    		h.put("id_negeri","");
	    	}else{
	    		h.put("id_negeri",rs.getString("id_negeri"));
	    	}
	    	if(rs.getString("id_mukim") == null){
	    		h.put("id_mukim","");
	    	}else{
	    		h.put("id_mukim",rs.getString("id_mukim"));
	    	}
	    	listA.addElement(h);
	    	bil++;
	      }
	      return listA;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}
	public static Vector getNamaAgensi(String idAgensi)throws Exception {
    Db db = null;
    String sql = "";
    try {
      db = new Db();
      Statement stmt = db.getStatement();
      SQLRenderer r = new SQLRenderer();
      r.add("a.nama_agensi");	 
     
      r.add("a.id_agensi",idAgensi);
      
      sql = r.getSQLSelect("tblrujagensi a");
      ResultSet rs = stmt.executeQuery(sql);
      Vector list = null;
      
      Hashtable h = null;

      while (rs.next()) {
    	  list = new Vector();
    	  h = new Hashtable();
    	  h.put("nama_agensi",rs.getString("nama_agensi"));	    	 
    	  list.addElement(h);
      }
      return list;
    } finally {
      if (db != null) db.close();
    }
  }

	
	 public Vector getNoSiasatan(String id_permohonan,String id_hakmilik)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		 try{
			 	getNoSiasatan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT NO_SIASATAN,ID_SIASATAN FROM TBLPPTSIASATAN A WHERE ID_PERMOHONAN = '"+id_permohonan+"' "; 
				sql += " AND ID_SIASATAN = (SELECT MAX(ID_SIASATAN) FROM TBLPPTSIASATAN WHERE ID_PERMOHONAN = A.ID_PERMOHONAN AND ID_HAKMILIK = '"+id_hakmilik+"') ";
				myLogger.info("getIDSiasatan  :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
		     while (rs.next()) {
		    	h = new Hashtable();		    	 
		    	h.put("no_siasatan", rs.getString("NO_SIASATAN")==null?"":rs.getString("NO_SIASATAN"));		    	
		    	h.put("id_siasatan", rs.getString("ID_SIASATAN")==null?"":rs.getString("ID_SIASATAN"));
		    	getNoSiasatan.addElement(h);		    	
		     	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getNoSiasatan;					
	 }	
	
}
