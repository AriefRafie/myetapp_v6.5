package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmGadaianPeguamMOCData {
	private static Vector listHeader = new Vector();
	private static Vector listContent = new Vector();
	private static Vector listFooter = new Vector();
	private static Vector listHakmilik = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static Logger log = Logger.getLogger(FrmGadaianPeguamMOCData.class);
	
	//*** query data from database
	public static void setListHeader(int idPermohonan)throws Exception {
		Db db = null;
		listHeader.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("htpP.no_Rujukan_Lain");
	      r.add("f.no_Fail");
	      r.add("peg.Nama");
	      r.add("peg.Alamat1");
	      r.add("peg.Alamat2");
	      r.add("peg.Alamat3");
	      r.add("peg.Poskod");
	      r.add("d.nama_Daerah");
	      r.add("n.nama_Negeri");
	      
	      r.add("P.id_Permohonan",r.unquote("htpP.id_Permohonan"));
	      r.add("P.id_Fail",r.unquote("f.id_Fail"));
	      r.add("P.id_Permohonan",r.unquote("peg.id_Permohonan"));
	      r.add("Peg.id_Daerah",r.unquote("d.id_Daerah"));
	      r.add("Peg.id_Negeri",r.unquote("n.id_Negeri"));

	      r.add("P.id_Permohonan", idPermohonan);
	      
	      sql = r.getSQLSelect("Tblhtppermohonan htpP, Tblpermohonan P, Tblpfdfail f, Tblhtppeguam peg, Tblrujdaerah d, Tblrujnegeri n");
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("FrmGadaianPeguamMOCData::setListHeader = "+sql);
	      //Vector list = new Vector();
	      Hashtable h;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("noRujukanLain", rs.getString("no_Rujukan_Lain"));
	    	  h.put("noFail", rs.getString("no_Fail"));
	    	  h.put("Tarikh", Format.format(new Date()));
	    	  h.put("Nama", rs.getString("Nama"));
	    	  if(rs.getString("Alamat1") != null){
	    		  h.put("Alamat1", rs.getString("Alamat1"));
	    	  }else{
	    		  h.put("Alamat1", "");
	    	  }
	    	  if(rs.getString("Alamat2") != null){
	    		  h.put("Alamat2", rs.getString("Alamat2"));
	    	  }else{
	    		  h.put("Alamat2", "");
	    	  }
	    	  if(rs.getString("Alamat3") != null){
	    		  h.put("Alamat3", rs.getString("Alamat3"));
	    	  }else{
	    		  h.put("Alamat3", "");
	    	  }	    	  
	    	  h.put("Poskod", rs.getString("Poskod"));	    	  
	    	  if(rs.getString("nama_Daerah") != null){
	    		  h.put("namaDaerah", rs.getString("nama_Daerah"));
	    	  }else{
	    		  h.put("namaDaerah", "");
	    	  }
	    	  if(rs.getString("nama_Negeri") != null){
	    		  h.put("namaNegeri", rs.getString("nama_Negeri"));
	    	  }else{
	    		  h.put("namaNegeri", "");
	    	  }
	    	  
	    	  listHeader.addElement(h);
	      }
	    }catch(Exception ex){
	    	//System.out.println("FrmGadaianPeguamMOCData::setListHeader::Exception = "+ex);
	    	//fir
	    	ex.printStackTrace();
	    	
	    } finally {
	      if (db != null){
	    	  db.close();
	      }
	    }
	}
	
	public static Vector getListHeader(){
		  log.info("listHeader size : " + listHeader.size());
		  return listHeader;
	  }
	
	//*** query data from database
	public static void setListContent(int idPermohonan)throws Exception {
		Db db = null;
		listContent.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("f.tajuk_Fail");
	      r.add("pihak.nama");
	      r.add("kem.jawatan");
	      r.add("kem.nama_Kementerian");
	      r.add("kem.alamat1");
	      r.add("kem.alamat2");
	      r.add("kem.alamat3");
	      r.add("kem.poskod");
	      r.add("n.nama_Negeri");
	      
	      r.add("P.id_Fail",r.unquote("f.id_Fail"));
	      r.add("P.id_Permohonan",r.unquote("urus.id_Permohonan"));
	      r.add("urus.id_Hakmilikurusan",r.unquote("pihak.id_Hakmilikurusan"));
	      r.add("f.id_Kementerian",r.unquote("kem.id_Kementerian"));
	      r.add("kem.id_Negeri",r.unquote("n.id_Negeri"));

	      r.add("P.id_Permohonan", idPermohonan);
	      
	      sql = r.getSQLSelect("Tblpermohonan P, Tblpfdfail f, Tblhtphakmilikurusan urus, Tblhtppihakberkepentingan pihak, Tblrujkementerian kem, Tblrujnegeri n");
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("FrmGadaianPeguamMOCData::setListContent = "+sql);
	      //Vector list = new Vector();
	      Hashtable h;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("TajukFail", rs.getString("tajuk_Fail"));
	    	  h.put("Nama", rs.getString("nama"));
	    	  h.put("Jawatan", rs.getString("jawatan"));
	    	  h.put("NamaKementerian", rs.getString("nama_Kementerian"));
	    	  if(rs.getString("Alamat1") != null){
	    		  h.put("Alamat1", rs.getString("Alamat1"));
	    	  }else{
	    		  h.put("Alamat1", "");
	    	  }
	    	  if(rs.getString("Alamat2") != null){
	    		  h.put("Alamat2", rs.getString("Alamat2"));
	    	  }else{
	    		  h.put("Alamat2", "");
	    	  }
	    	  if(rs.getString("Alamat3") != null){
	    		  h.put("Alamat3", rs.getString("Alamat3"));
	    	  }else{
	    		  h.put("Alamat3", "");
	    	  }	    	  
	    	  h.put("Poskod", rs.getString("poskod"));	    	  
	    	  if(rs.getString("nama_Negeri") != null){
	    		  h.put("NamaNegeri", rs.getString("nama_Negeri"));
	    	  }else{
	    		  h.put("NamaNegeri", "");
	    	  }
	    	  
	    	  listContent.addElement(h);
	      }
	    }
	    catch(Exception ex){
	    	//System.out.println("FrmGadaianPeguamMOCData::setListContent::Exception = "+ex);
	    	//fir
	    	ex.printStackTrace();
	    } finally {
	      if (db != null){
	    	  db.close();
	      }
	    }
	}
	
	public static Vector getListContent(){
		  log.info("FrmGadaianPeguamMOCData::getListContent:: " + listContent.size());
		  return listContent;
	  }
	
	//*** query data from database
	public static void setListHakmilik(int idPermohonan)throws Exception {
		Db db = null;
		listHakmilik.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("urus.no_Hakmilik");
	      r.add("m.nama_Mukim");
	      r.add("d.nama_Daerah");	      
	      
	      r.add("urus.id_Mukim",r.unquote("m.id_Mukim"));
	      r.add("urus.id_Daerah",r.unquote("d.id_Daerah"));

	      r.add("urus.id_Permohonan", idPermohonan);
	      
	      sql = r.getSQLSelect("Tblhtphakmilikurusan urus, Tblrujmukim m, Tblrujdaerah d");
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("FrmGadaianPeguamMOCData::setListHakmilik = "+sql);
	      //Vector list = new Vector();
	      Hashtable h;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("NoHakmilik", rs.getString("no_Hakmilik"));
	    	  h.put("NamaMukim", rs.getString("nama_Mukim"));
	    	  h.put("NamaDaerah", rs.getString("nama_Daerah"));
	    	  
	    	  listHakmilik.addElement(h);
	      }
	    }catch(Exception ex){
	    	//System.out.println("FrmGadaianPeguamMOCData::setListHakmilik::Exception = "+ex);
	    	//fir
	    	ex.printStackTrace();
	    } finally {
	      if (db != null){
	    	  db.close();
	      }
	    }
	}
	
	public static Vector getListHakmilik(){
		  log.info("FrmGadaianPeguamMOCData::getListHakMilik :" +listHakmilik.size());
		  return listHakmilik;
	  }
	
	//*** query data from database
	public static void setListFooter(int idPermohonan)throws Exception {
		Db db = null;
		listFooter.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("pihak.nama");
	      r.add("pihak.alamat1");
	      r.add("pihak.alamat2");
	      r.add("pihak.alamat3");
	      r.add("pihak.poskod");
	      r.add("d.nama_Daerah");
	      r.add("n.nama_Negeri");
	      
	      r.add("P.id_Permohonan",r.unquote("urus.id_Permohonan"));
	      r.add("urus.id_Hakmilikurusan",r.unquote("pihak.id_Hakmilikurusan"));
	      r.add("pihak.id_Daerah",r.unquote("d.id_Daerah"));
	      r.add("pihak.id_Negeri",r.unquote("n.id_Negeri"));

	      r.add("P.id_Permohonan", idPermohonan);
	      
	      sql = r.getSQLSelect("Tblpermohonan P, Tblhtphakmilikurusan urus, Tblhtppihakberkepentingan pihak, Tblrujdaerah d, Tblrujnegeri n");
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("FrmGadaianPeguamMOCData::setListContent = "+sql);
	      //Vector list = new Vector();
	      Hashtable h;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("Nama", rs.getString("nama"));
	    	  if(rs.getString("Alamat1") != null){
	    		  h.put("Alamat1", rs.getString("Alamat1"));
	    	  }else{
	    		  h.put("Alamat1", "");
	    	  }
	    	  if(rs.getString("Alamat2") != null){
	    		  h.put("Alamat2", rs.getString("Alamat2"));
	    	  }else{
	    		  h.put("Alamat2", "");
	    	  }
	    	  if(rs.getString("Alamat3") != null){
	    		  h.put("Alamat3", rs.getString("Alamat3"));
	    	  }else{
	    		  h.put("Alamat3", "");
	    	  }	    	  
	    	  h.put("Poskod", rs.getString("poskod"));
	    	  if(rs.getString("nama_Daerah") != null){
	    		  h.put("NamaDaerah", rs.getString("nama_Daerah"));	
	    	  }else{
	    		  h.put("NamaDaerah", "");	
	    	  }	    	  
	    	  if(rs.getString("nama_Negeri") != null){
	    		  h.put("NamaNegeri", rs.getString("nama_Negeri"));
	    	  }else{
	    		  h.put("NamaNegeri", "");
	    	  }
	    	  
	    	  listFooter.addElement(h);
	      }
	    }catch(Exception ex){
	    	//System.out.println("FrmGadaianPeguamMOCData::setListContent::Exception = "+ex);
	    	//fir
	    	ex.printStackTrace();
	    } finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	}
	
	public static Vector getListFooter(){
		  log.info("FrmGadaianPeguamMOCData::getListFooter :" +listFooter.size());
		  return listFooter;
	  }
}
