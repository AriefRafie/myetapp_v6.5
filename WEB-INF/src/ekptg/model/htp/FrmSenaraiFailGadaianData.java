 package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmSenaraiFailGadaianData {
	private static Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
	private static Logger log = Logger.getLogger(FrmSenaraiFailGadaianData.class);
	
	
	public static Vector<Hashtable<String,String>> getSenarai(String carian, String noFail
		, String idNegeri, String keyNo_upt, String keyNo_ptg, String keyNo_ptd
		, String keyNo_pemilik, String keyNo_rujukan) throws Exception {		
	    Db db = null;
	    list.clear();
	    String sql = "";
	    if (idNegeri.equals("20")){
	    	idNegeri = null;
	    }
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql =   " SELECT F.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL, S.KETERANGAN, N.NAMA_NEGERI, N.KOD_MAMPU "+ 
	    		  ",PB.NAMA NAMAPB"+
	    		  " FROM TBLPFDFAIL F, TBLRUJSTATUS S, TBLRUJNEGERI n "+ 
	    		  " ,TBLPERMOHONAN P"+
	    		  " ,TBLHTPHAKMILIKURUSAN T, TBLHTPPIHAKBERKEPENTINGAN PB "+
	    		  " WHERE F.ID_STATUS=S.ID_STATUS "+
	    		  " AND F.ID_FAIL = P.ID_FAIL"+
	    		  " AND P.ID_PERMOHONAN = T.ID_PERMOHONAN"+
	    		  " AND T.ID_HAKMILIKURUSAN = PB.ID_HAKMILIKURUSAN"+
	    		  " AND F.ID_URUSAN = 108 "+ 
	    		  " AND N.ID_NEGERI = F.ID_NEGERI "+  
	    		  " AND F.ID_STATUS <> 999 "+
	    		  " AND F.TAJUK_FAIL LIKE '%"+carian+"%' "+  
	    		  " AND F.NO_FAIL LIKE '%"+noFail+"%' ";
	      
	      if(keyNo_upt!="" || keyNo_ptg!="" || keyNo_ptd!=""){
		      sql +=  " AND F.ID_FAIL IN (SELECT DISTINCT F1.ID_FAIL FROM TBLPFDFAIL F1, TBLPERMOHONAN P1, TBLHTPPERMOHONAN HP1 "+
		    		  					" WHERE F1.ID_FAIL = P1.ID_FAIL AND P1.ID_PERMOHONAN = HP1.ID_PERMOHONAN ";
		    		  					
										if(keyNo_upt!="")
		    		  					sql += " AND UPPER(HP1.NO_RUJUKAN_UPT) LIKE '%"+keyNo_upt.trim().toUpperCase()+"%') ";
										
										if(keyNo_ptg!="")
			    		  				sql += " AND UPPER(HP1.NO_RUJUKAN_PTG) LIKE '%"+keyNo_ptg.trim().toUpperCase()+"%') ";
										
										if(keyNo_ptd!="")
			    		  				sql += " AND UPPER(HP1.NO_RUJUKAN_PTD) LIKE '%"+keyNo_ptd.trim().toUpperCase()+"%') ";
	      }
	      
	      if(keyNo_pemilik!="" || keyNo_rujukan!=""){
	    	  sql +=  " AND F.ID_FAIL IN (SELECT DISTINCT F2.ID_FAIL FROM TBLPFDFAIL F2, TBLPERMOHONAN P2, TBLHTPHAKMILIKURUSAN U2, TBLHTPPIHAKBERKEPENTINGAN HPB2 "+
	    			  					" WHERE F2.ID_FAIL = P2.ID_FAIL AND P2.ID_PERMOHONAN = U2.ID_PERMOHONAN AND U2.ID_HAKMILIKURUSAN = HPB2.ID_HAKMILIKURUSAN ";
	    	  
	    	  							if(keyNo_pemilik!="")
	    	  							sql += " AND UPPER(HPB2.NAMA) LIKE '%"+keyNo_pemilik.trim().toUpperCase()+"%' ) ";
	    	  							
	    	  							if(keyNo_rujukan!="")
	    	  							sql += " AND UPPER(HPB2.NO_RUJUKAN) LIKE '%"+keyNo_pemilik.trim().toUpperCase()+"%' ) ";    	  								
	      }
	      if(idNegeri != null)
	    	  sql +=" AND F.ID_NEGERI = "+idNegeri;
    		  					
	      sql +=  " ORDER BY F.TARIKH_KEMASKINI ";	      
	      log.info("senarai : sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable<String,String> h;
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", rs.getString("no_Fail"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("nama", rs.getString("namapb"));
	    	  h.put("negeri", rs.getString("nama_Negeri"));
	    	  h.put("keterangan", rs.getString("keterangan"));
	    	  h.put("kodMampu", rs.getString("kod_Mampu"));
	    	  list.addElement(h);
	    	  bil++;
	    	  
	      }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
		return list;
    
	  }

	//public static void setList(String carian, String noFail, Long idNegeri, String keyNo_upt, String keyNo_ptg, 
	//			String keyNo_ptd, String keyNo_pemilik, String keyNo_rujukan) throws Exception {
	public static Vector<Hashtable<String,String>> setList(String carian, String noFail, Long idNegeri, String keyNo_upt, String keyNo_ptg, 
			String keyNo_ptd, String keyNo_pemilik, String keyNo_rujukan) throws Exception {		
	    Db db = null;
	    list.clear();
	    String sql = "";
	    if (idNegeri == 20){
	    	idNegeri = null;
	    }
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      /*sql = " SELECT F.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL, S.KETERANGAN, N.NAMA_NEGERI, N.KOD_MAMPU "+ 
	      	" FROM TBLPFDFAIL F, TBLRUJSTATUS S, TBLRUJNEGERI n "+
	      	" , TBLPERMOHONAN P, TBLHTPPERMOHONAN HP "+
	      	" WHERE F.ID_STATUS=S.ID_STATUS AND F.ID_URUSAN = 108 "+
	      	" AND P.ID_FAIL = F.ID_FAIL AND HP.ID_PERMOHONAN = P.ID_PERMOHONAN "+
	      	" AND N.ID_NEGERI = F.ID_NEGERI  AND F.ID_STATUS <> 999 ";
	      sql +=" AND F.TAJUK_FAIL LIKE '%"+carian+"%' ";
	      sql +=" AND F.NO_FAIL LIKE '%"+noFail+"%' ";
	      
	      if(keyNo_upt!=null&&keyNo_upt!="")
	      sql +=" AND HP.NO_RUJUKAN_UPT LIKE '%"+keyNo_upt+"%' ";
	      
	      if(keyNo_ptg!=null&&keyNo_ptg!="")
	      sql +=" AND HP.NO_RUJUKAN_PTG LIKE '%"+keyNo_ptg+"%' ";
	      
	      if(keyNo_ptd!=null&&keyNo_ptd!="")
	      sql +=" AND HP.NO_RUJUKAN_PTD LIKE '%"+keyNo_ptd+"%' ";
	      
	      if(idNegeri != null)
	    	  sql +="AND F.ID_NEGERI = "+idNegeri;
	      sql +=" ORDER BY F.TARIKH_KEMASKINI";*/
	      
	      sql =   " SELECT F.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL, S.KETERANGAN, N.NAMA_NEGERI, N.KOD_MAMPU "+  
	    		  " FROM TBLPFDFAIL F, TBLRUJSTATUS S, TBLRUJNEGERI n "+ 
	    		  " WHERE F.ID_STATUS=S.ID_STATUS "+
	    		  " AND F.ID_URUSAN = 108 "+ 
	    		  " AND N.ID_NEGERI = F.ID_NEGERI "+  
	    		  " AND F.ID_STATUS <> 999 "+
	    		  " AND F.TAJUK_FAIL LIKE '%"+carian+"%' "+  
	    		  " AND F.NO_FAIL LIKE '%"+noFail+"%' ";
	      
	      if(keyNo_upt!="" || keyNo_ptg!="" || keyNo_ptd!=""){
		      sql +=  " AND F.ID_FAIL IN (SELECT DISTINCT F1.ID_FAIL FROM TBLPFDFAIL F1, TBLPERMOHONAN P1, TBLHTPPERMOHONAN HP1 "+
		    		  					" WHERE F1.ID_FAIL = P1.ID_FAIL AND P1.ID_PERMOHONAN = HP1.ID_PERMOHONAN ";
		    		  					
										if(keyNo_upt!="")
		    		  					sql += " AND UPPER(HP1.NO_RUJUKAN_UPT) LIKE '%"+keyNo_upt.trim().toUpperCase()+"%') ";
										
										if(keyNo_ptg!="")
			    		  				sql += " AND UPPER(HP1.NO_RUJUKAN_PTG) LIKE '%"+keyNo_ptg.trim().toUpperCase()+"%') ";
										
										if(keyNo_ptd!="")
			    		  				sql += " AND UPPER(HP1.NO_RUJUKAN_PTD) LIKE '%"+keyNo_ptd.trim().toUpperCase()+"%') ";
	      }
	      
	      if(keyNo_pemilik!="" || keyNo_rujukan!=""){
	    	  sql +=  " AND F.ID_FAIL IN (SELECT DISTINCT F2.ID_FAIL FROM TBLPFDFAIL F2, TBLPERMOHONAN P2, TBLHTPHAKMILIKURUSAN U2, TBLHTPPIHAKBERKEPENTINGAN HPB2 "+
	    			  					" WHERE F2.ID_FAIL = P2.ID_FAIL AND P2.ID_PERMOHONAN = U2.ID_PERMOHONAN AND U2.ID_HAKMILIKURUSAN = HPB2.ID_HAKMILIKURUSAN ";
	    	  
	    	  							if(keyNo_pemilik!="")
	    	  							sql += " AND UPPER(HPB2.NAMA) LIKE '%"+keyNo_pemilik.trim().toUpperCase()+"%' ) ";
	    	  							
	    	  							if(keyNo_rujukan!="")
	    	  							sql += " AND UPPER(HPB2.NO_RUJUKAN) LIKE '%"+keyNo_pemilik.trim().toUpperCase()+"%' ) ";    	  								
	      }
	      if(idNegeri != null)
	    	  sql +=" AND F.ID_NEGERI = "+idNegeri;
    		  					
	      sql +=  " ORDER BY F.TARIKH_KEMASKINI ";	      
	      log.info("LIST : "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable<String,String> h;
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", rs.getString("no_Fail"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("negeri", rs.getString("nama_Negeri"));
	    	  h.put("keterangan", rs.getString("keterangan"));
	    	  h.put("kodMampu", rs.getString("kod_Mampu"));
	    	  list.addElement(h);
	    	  bil++;
	    	  
	      }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
		return list;
    
	  }
	

	public static void setListOnline(String carian, String noFail, Long idNegeri,String idKementerian)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    String Like = "";
	    if (idNegeri == 20){
	    	idNegeri = null;
	    }
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql = " SELECT F.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL, S.KETERANGAN, N.NAMA_NEGERI, N.KOD_MAMPU "+ 
	      	" FROM TBLPFDFAIL F, TBLRUJSTATUS S, TBLRUJNEGERI n "+
	      	" WHERE F.ID_STATUS=S.ID_STATUS AND F.ID_URUSAN = 108 "+
	      	" AND N.ID_NEGERI = F.ID_NEGERI  AND F.ID_STATUS <> 999 and F.ID_KEMENTERIAN ="+idKementerian ;
	      sql +=" AND F.TAJUK_FAIL LIKE '%"+carian+"%' ";
	      
	      if(idNegeri != null)
	    	  sql +="AND F.ID_NEGERI = "+idNegeri;
	      sql +=" ORDER BY F.TARIKH_KEMASKINI";
	      	      
	      log.info(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", rs.getString("no_Fail"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("negeri", rs.getString("nama_Negeri"));
	    	  h.put("keterangan", rs.getString("keterangan"));
	    	  h.put("kodMampu", rs.getString("kod_Mampu"));
	    	  list.addElement(h);
	    	  bil++;
	      }
	      //return list;
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	  }	
	public static void SenaraiFailGadaian(String urusan, String negeri)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    String Like = "";

	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql = "SELECT PFDF.ID_FAIL, S.NAMA_SUBURUSAN, PFDF.TAJUK_FAIL,";
	      sql+= "PFDF.NO_FAIL, NEG.NAMA_NEGERI ";
	      sql +="FROM Tblpfdfail pfdf, tblrujnegeri NEG, tblrujsuburusan S";
	      sql +="WHERE pfdf.id_suburusan = " + urusan;
	      sql +=" AND PFDF.ID_SUBURUSAN = S.ID_SUBURUSAN ";
	      sql +=" AND pfdf.id_negeri = " + negeri;
	      sql +=" AND pfdf.id_negeri = NEG.ID_NEGERI " ;

	      ResultSet rs = stmt.executeQuery(sql);
	      log.info(sql);
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idFail", rs.getString("id_Fail"));
	    	  h.put("noFail", rs.getString("no_Fail"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("negeri", rs.getString("nama_Negeri"));
	    	  h.put("urusan", rs.getString("nama_suburusan"));

	    	  list.addElement(h);
	    	  bil++;
	      }
	      //return list;
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	  }

	  public static Vector<Hashtable<String,String>> getList(){
		  log.info("FrmSenaraiFailGadaianData list : "+ list.size());
		  return list;
	  }
}
