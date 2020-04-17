package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmCukaiSenaraiPelarasanData {
	 	
	 	public static Vector getList(Long noHakmilik, Long idJenisHakmilik, Long idNegeri, Long idDaerah, Long idMukim)throws Exception {
	 	    Db db = null;
	 	    String sql = "";
	 	    
	 	    if (noHakmilik == 0){
	 	    	noHakmilik = null;
	 	    }
	 	    if (idJenisHakmilik == 0){
	 	    	idJenisHakmilik = null;
	 	    }
	 	    if (idNegeri == 0){
	 	    	idNegeri = null;
	 	    }
	 	    if (idDaerah == 0){
	 	    	idDaerah = null;
	 	    }
	 	    if (idMukim == 0){
	 	    	idMukim = null;
	 	    }
	 	   
	 	    try {
	 	      db = new Db();
	 	      Statement stmt = db.getStatement();

	 	      	sql = " SELECT DISTINCT thh.id_negeri, trn.nama_negeri, thh.id_daerah, trd.nama_daerah, thh.id_jenishakmilik, ";
	 	      	sql +=" thh.id_mukim, trm.nama_mukim, thh.id_hakmilik, thh.no_hakmilik, thha.id_kementerian, ";
	 	      	sql +=" trk.nama_kementerian, trjh.kod_jenis_hakmilik  ";//, thhc.status_terkini
	 	      	sql +=" FROM tblhtphakmilik thh, tblhtphakmilikcukai thhc, tblrujjenishakmilik trjh, tblrujnegeri trn, tblrujdaerah trd, ";
	 	      	sql +=" tblrujmukim trm, tblrujkementerian trk, tblhtphakmilikagensi thha ";
	 	      	sql +=" WHERE thh.id_jenishakmilik = trjh.id_jenishakmilik ";
	 	      	sql +=" AND trn.id_negeri = thh.id_negeri ";
	 	      	sql +=" AND trd.id_daerah = thh.id_daerah ";
	 	      	sql +=" AND trm.id_mukim = thh.id_mukim ";
	 	      	sql +=" AND thha.id_kementerian = trk.id_kementerian ";
	 	      	sql +=" AND thh.id_hakmilik = thha.id_hakmilik"; 
	 	      	sql +=" AND thh.id_hakmilik = thhc.id_hakmilik ";
	 	      		 	      	
	 	     if(noHakmilik != null){
	 	    	sql +=" AND thh.no_hakmilik = "+noHakmilik;
	 	     }
	 	     if(idJenisHakmilik != null){
	 	    	sql += " AND thh.id_jenishakmilik = "+idJenisHakmilik;
	 	     }
	 	     if(idNegeri != null){
	 	    	sql += " AND thh.id_negeri = "+idNegeri;
	 	     }
	 	     if(idDaerah != null){
	 	    	sql += " AND thh.id_daerah = "+idDaerah;
	 	     }
	 	     if(idMukim != null){
	 	    	sql += " AND thh.id_mukim = "+idMukim;
	 	     }
	 	     	
	 	     	//sql +=" AND thhc.status_terkini = 'Y' ";
 	      		sql +=" ORDER BY thh.id_negeri ";
 	      		
	 	     System.out.println("FrmCukaiSenaraiPelarasanData::getList::sql::"+sql);	
	 	     ResultSet rs = stmt.executeQuery(sql);
	 	      
	 	     Vector list = new Vector();
	 	     Hashtable h;
	 	     int bil = 1;
	 	      
	 	     while (rs.next()) {
	 	    	  h = new Hashtable();
	 	    	  h.put("bil", bil);
	 	    	  h.put("idNegeri", rs.getString("id_negeri"));
	 	    	  h.put("idDaerah", rs.getString("id_daerah"));
	 	    	  h.put("idMukim", rs.getString("id_mukim"));
	 	    	  h.put("idHakmilik", rs.getString("id_hakmilik"));
	 	    	  h.put("no_hakmilik", rs.getInt("no_hakmilik"));
	 	    	  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
	 	    	  h.put("idKementerian", rs.getString("id_kementerian"));
	 	    	  h.put("nama_negeri", rs.getString("nama_negeri"));
	 	    	  h.put("nama_daerah", rs.getString("nama_daerah"));
	 	    	  h.put("nama_mukim", rs.getString("nama_mukim"));
	 	    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
	 	    	  h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik"));
	 	    	  
	 	    	  list.addElement(h);
	 	    	  bil++;
	 	      }
	 	     return list;
	 	    } finally {
	 	      if (db != null) db.close();
	 	    }
	 	}
}