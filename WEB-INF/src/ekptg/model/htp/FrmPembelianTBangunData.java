package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmPembelianTBangunData {
	private static Vector list = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static Logger log = Logger.getLogger(FrmPembelianTBangunData.class);
	private static Connection conn = null;
	private static Db db = null;

	//*** query data from database
	@Deprecated
	public static void setTBangun(long idPermohonan, long idDaerah, long idMukim)throws Exception {

	    list.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("distinct u.Id_Hakmilikurusan");
	      r.add("u.Id_Negeri");	      
	      r.add("u.Id_Daerah");
	      r.add("u.Id_Mukim");
//	      r.add("u.Id_Jenishakmilik");
	      r.add("h.Keterangan as HakKeterangan");
	      r.add("u.No_Hakmilik");
	      r.add("u.No_Lot");
//	      r.add("u.Id_Lot");
	      r.add("lot.Keterangan as LotKeterangan");
	      r.add("u.Tarikh_Mula");
	      r.add("u.Tarikh_Luput");
	      r.add("u.Luas");
	      r.add("u.No_Bangunan");
	      r.add("u.No_Tingkat");
	      r.add("u.No_Petak");
//	      r.add("u.Id_Luas");
	      r.add("luas.Keterangan as LuasKeterangan");
	      r.add("u.No_Pelan");
//	      r.add("u.Status_Tanah");
//	      r.add("j.Keterangan");
	      r.add("u.Id_Jenisrizab");
	      r.add("u.Id_Kategori");
	      
	      r.add("u.Id_Permohonan", idPermohonan);
	      r.add("u.Id_Jenishakmilik",r.unquote("h.Id_Jenishakmilik"));
	      r.add("u.Id_Lot",r.unquote("lot.Id_Lot"));
	      r.add("u.Id_Luas",r.unquote("luas.Id_Luas"));
//	      r.add("u.No_Bangunan","%TIADA%","NOT LIKE");
//	      r.add("u.No_Tingkat","%TIADA%","NOT LIKE");
	      r.add("u.No_Petak","%TIADA%","NOT LIKE");
	      
	      if(idDaerah != 0){
	    	  r.add("u.Id_Daerah",idDaerah);
	      }
	      
	      if(idMukim != 0){
	    	  r.add("u.Id_Mukim",idMukim);
	      }
	      
	      sql = r.getSQLSelect("Tblhtphakmilikurusan u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, Tblrujjenistanah j");
	      ResultSet rs = stmt.executeQuery(sql);
	      log.info("FrmPembelianTBangunanData ::"+sql);
	      //Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  	    	  
	    	  h.put("IdHakmilikurusan",rs.getString("Id_Hakmilikurusan"));
	    	  if(rs.getString("Id_Negeri") == null){
	    		  h.put("IdNegeri","");
	    	  }else{
	    		  h.put("IdNegeri",rs.getString("Id_Negeri"));
	    	  }
	    	  if(rs.getString("Id_Daerah") == null){
	    		  h.put("IdDaerah","");
	    	  }else{
	    		  h.put("IdDaerah",rs.getString("Id_Daerah"));
	    	  }
	    	  if(rs.getString("Id_Mukim") == null){
	    		  h.put("IdMukim","");
	    	  }else{
	    		  h.put("IdMukim",rs.getString("Id_Mukim"));
	    	  }
//	    	  if(rs.getString("Id_Jenishakmilik") == null){
//	    		  h.put("IdJenishakmilik","");
//	    	  }else{
//	    		  h.put("IdJenishakmilik",rs.getString("Id_Jenishakmilik"));
//	    	  }
	    	  if(rs.getString("HakKeterangan") == null){
	    		  h.put("HakKeterangan","");
	    	  }else{
	    		  h.put("HakKeterangan",rs.getString("HakKeterangan"));
	    	  }
	    	  if(rs.getString("No_Hakmilik") == null){
	    		  h.put("NoHakmilik","");
	    	  }else{
	    		  h.put("NoHakmilik",rs.getString("No_Hakmilik"));
	    	  }
	    	  if(rs.getString("No_Lot") == null){
	    		  h.put("NoLot","");
	    	  }else{
	    		  h.put("NoLot",rs.getString("No_Lot"));
	    	  }
//	    	  if(rs.getString("Id_Lot") == null){
//	    		  h.put("IdLot","");
//	    	  }else{
//	    		  h.put("IdLot",rs.getString("Id_Lot"));
//	    	  }
	    	  if(rs.getString("LotKeterangan") == null){
	    		  h.put("LotKeterangan","");
	    	  }else{
	    		  h.put("LotKeterangan",rs.getString("LotKeterangan"));
	    	  }
	    	  if(rs.getString("Tarikh_Mula") == null){
	    		  h.put("TarikhMula","");
	    	  }else{
	    		  h.put("TarikhMula",Format.format(rs.getDate("Tarikh_Mula")));
	    	  }
	    	  if(rs.getString("Tarikh_Luput") == null){
	    		  h.put("TarikhLuput","");
	    	  }else{
	    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
	    	  }
	    	  if(rs.getString("Luas") == null){
	    		  h.put("Luas","");
	    	  }else{
	    		  h.put("Luas",rs.getString("Luas"));
	    	  }
	    	  h.put("NoBangunan",rs.getString("No_Bangunan"));
	    	  h.put("NoTingkat",rs.getString("No_Tingkat"));
	    	  h.put("NoPetak",rs.getString("No_Petak"));
	    	  if(rs.getString("LuasKeterangan") == null){
	    		  h.put("LuasKeterangan","");
	    	  }else{
	    		  h.put("LuasKeterangan",rs.getString("LuasKeterangan"));
	    	  }
//	    	  if(rs.getString("Status_Tanah") == null){
//	    		  h.put("StatusTanah","");
//	    	  }else{
//	    		  h.put("StatusTanah",rs.getString("Status_Tanah"));
//	    	  }
	    	  if(rs.getString("No_Pelan") == null){
	    		  h.put("NoPelan","");
	    	  }else{
	    		  h.put("NoPelan",rs.getString("No_Pelan"));
	    	  }
//	    	  if(rs.getString("j.Keterangan") == null){
//	    		  h.put("StatusTanah","");
//	    	  }else{
//	    		  h.put("StatusTanah",rs.getString("j.Keterangan"));
//	    	  }
	    	  if(rs.getString("Id_Jenisrizab") == null){
	    		  h.put("IdRizab","");
	    	  }else{
	    		  h.put("IdRizab",rs.getString("Id_Jenisrizab"));
	    	  }
	    	  if(rs.getString("Id_Kategori") == null){
	    		  h.put("IdKategori","");
	    	  }else{
	    		  h.put("IdKategori",rs.getString("Id_Kategori"));
	    	  }	    	  
	    	  list.addElement(h);
	      }
	      
	    } 
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getTBangun(){
		  log.info("getTBangunSize : "+list.size());
		  log.info("getTBangunList :"+list);
		  return list;
	  }
	  
	// *** Kementerian
	public static String SelectNoHakmilik(long idPermohonan, String selectName) throws Exception {
		return SelectNoHakmilik(idPermohonan,selectName, null, null);
	}

	public static String SelectNoHakmilik(long idPermohonan, String selectName,
			String selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>Sila Pilih No. Hakmilik</option>\n");
			Vector v = getHakmilik(idPermohonan);
			Hashtable h = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = (Hashtable) v.get(i);
				if (h.get("NoHakmilik").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("NoHakmilik")
						+ ">" + h.get("NoHakmilik")+ "</option>\n");
			}
			sb.append("</select>");
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return sb.toString();
	}
	
	//*** query data from database TBLHTPHAKMILIKURUSAN to get No_Hakmilik only
	public static Vector getHakmilik(long idPermohonan) throws Exception {

	    String sql = "Select distinct no_Hakmilik from Tblhtphakmilikurusan where id_Permohonan ="+idPermohonan;
	    Vector listHak = null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      log.info("FrmPembelianTBangunan::getHakmilik::sql = "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      listHak = new Vector();
	      Hashtable h = null;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("NoHakmilik", rs.getString("No_Hakmilik"));
	    	  listHak.addElement(h);
	      }
	      
	    } 
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    finally {
	      if (db != null) db.close();
	    }
	    
	    return listHak;
	}
	  
	//*** query data from database for popup
	  public static void setTBangunPop(long idPermohonan, long idHakmilikurusan)throws Exception {

		    list.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("u.Id_Hakmilikurusan");
		      r.add("u.No_Hakmilik");
		      r.add("u.No_Bangunan");
		      r.add("u.No_Tingkat");
		      r.add("u.No_Petak");
		      
		      r.add("u.Id_Permohonan", idPermohonan);
		      r.add("u.Id_Hakmilikurusan", idHakmilikurusan);
		      
		      sql = r.getSQLSelect("Tblhtphakmilikurusan u");
		      ResultSet rs = stmt.executeQuery(sql);
		      log.info("FrmPembelianTBangunanData ::"+sql);
		      //Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  	    	  
		    	  h.put("IdHakmilikurusan",rs.getString("Id_Hakmilikurusan"));
		    	  if(rs.getString("No_Hakmilik") == null){
		    		  h.put("NoHakmilik","");
		    	  }else{
		    		  h.put("NoHakmilik",rs.getString("No_Hakmilik"));
		    	  }
		    	  h.put("NoBangunan",rs.getString("No_Bangunan"));
		    	  h.put("NoTingkat",rs.getString("No_Tingkat"));
		    	  h.put("NoPetak",rs.getString("No_Petak"));
		    	  
		    	  list.addElement(h);
		      }
		      //return list;
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		   
		    finally {
		      if (db != null) db.close();
		    }
		  }
		  
		  public static Vector getTBangunPop(){
			  log.info("FrmPembelianTBangunData : getTbangunPop : " +list.size());
			  return list;
		  }
		  
		//*** update data in database
		  public static void update(Hashtable data) throws Exception {
		 
		    String sql = "";
		    long idPermohonan = 0;
		    long idHakmilikurusan = 0;
		    
		    try
		    {
		      idPermohonan = Long.parseLong(data.get("idPermohonan").toString());
		      idHakmilikurusan = Long.parseLong(data.get("idHakmilikurusan").toString());
		      String NoBangunan;
		      if(data.get("NoBangunan") != "")
		    	  NoBangunan = (String)data.get("NoBangunan");
		      else
		    	  NoBangunan = "TIADA";
		      String NoTingkat;
		      if(data.get("NoTingkat") != "")
		    	  NoTingkat = (String)data.get("NoTingkat");
		      else
		    	  NoTingkat = "TIADA";
		      String NoPetak;
		      if(data.get("NoPetak") != "")
		    	  NoPetak = (String)data.get("NoPetak");
		      else
		    	  NoPetak = "TIADA";
			  			  
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
			  
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Hakmilikurusan", idHakmilikurusan);
			  r.update("id_Permohonan", idPermohonan);
			  r.add("no_Bangunan", NoBangunan);
		      r.add("no_Tingkat", NoTingkat);
		      r.add("no_Petak", NoPetak);
			  
		      sql = r.getSQLUpdate("Tblhtphakmilikurusan");
		      log.info("FrmPembelianTBangunanData::Update::Tblhtphakmilikurusan = "+sql);
		      stmt.executeUpdate(sql);
		      
		      conn.commit();
		    }
		    catch(Exception e){
		    	conn.rollback();
		    	e.printStackTrace();
		    }
		    		    
		    finally {
		    	if (conn != null)conn.close();
		      if (db != null) db.close();
		    }
		  }
		  
		//*** Simpan data in database
		  public static long simpan(Hashtable data) throws Exception {
		
		    String sql = "";
		    long idPermohonan = 0;
		    long idHakmilikurusan = 0;
		    try
		    {
		       idPermohonan = Long.parseLong((String)data.get("idPermohonan"));
		      idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
		      String NoHakmilik = (String)data.get("NoHakmilik");
		      String NoBangunan;
		      if(data.get("NoBangunan") != ""){
		    	  NoBangunan = (String)data.get("NoBangunan");
		      }  
		      else{
		    	  NoBangunan = "TIADA";
		      }
		    	  
		      String NoTingkat;
		      if(data.get("NoTingkat") != ""){
		    	  NoTingkat = (String)data.get("NoTingkat");
		      }
		      else{
		    	  NoTingkat = "TIADA";
		      }
		      String NoPetak;
		      if(data.get("NoPetak") != ""){
		    	  NoPetak = (String)data.get("NoPetak");
		      }
		      else{
		    	  NoPetak = "TIADA";
		      }
		    	  
		      db = new Db();
		      conn = db.getConnection();
		      conn.setAutoCommit(false);
		      
			  //Query Record
		      Statement stmtQ = db.getStatement();
		      SQLRenderer rQ = new SQLRenderer();
		 
		      rQ.add("u.Id_Permohonan");
		      rQ.add("u.Pegangan_Hakmilik");
		      rQ.add("u.Id_Negeri");	      
		      rQ.add("u.Id_Daerah");
		      rQ.add("u.Id_Mukim");
		      rQ.add("u.Id_Jenishakmilik");
		      rQ.add("u.No_Hakmilik");
		      rQ.add("u.No_Lot");
		      rQ.add("u.Id_Lot");
		      rQ.add("u.Tarikh_Mula");
		      rQ.add("u.Tarikh_Luput");
		      rQ.add("u.Luas");
		      rQ.add("u.Id_Luas");
		      rQ.add("u.No_Pelan");
		      rQ.add("u.Id_Jenisrizab");
		      rQ.add("u.Id_Kategori");
		      rQ.add("u.Id_Subkategori");	
		      
		      rQ.add("u.Id_Permohonan", idPermohonan);
		      rQ.add("u.No_Hakmilik", NoHakmilik);
		      
		      sql = rQ.getSQLSelect("Tblhtphakmilikurusan u");
		      ResultSet rs = stmtQ.executeQuery(sql);

		      int idNegeri = 0;
		      int socDaerah = 0;
		      int socMukim = 0;
		      int socJenisHakmilik = 0;
		      String NoLot = "";
		      int socLot = 0;
			  String TarikhTerima = new String();
			  String TarikhLuput = new String();
			  String peganganHakmilik = "";
			  String Luas = "";
			  int socLuas = 0;
			  String NoPelan = "";
			  int socRizab = 0;
			  int socKategori = 0;
			  int idSubkategori = 0;
			  
			  while (rs.next()) {
				  idNegeri = rs.getInt("Id_Negeri");
				  socDaerah = rs.getInt("Id_Daerah");
				  socMukim = rs.getInt("Id_Mukim");
				  socJenisHakmilik = rs.getInt("Id_Jenishakmilik");
				  NoLot = rs.getString("No_Lot");
				  socLot = rs.getInt("Id_Lot");
				  TarikhTerima = Format.format(rs.getDate("Tarikh_Mula"));
				  TarikhLuput = Format.format(rs.getDate("Tarikh_Luput"));
				  peganganHakmilik = rs.getString("pegangan_Hakmilik");
				  Luas = rs.getString("Luas");
				  socLuas = rs.getInt("Id_Luas");
				  NoPelan = rs.getString("No_Pelan");
				  socRizab = rs.getInt("Id_Jenisrizab");
				  socKategori = rs.getInt("Id_Kategori");
				  idSubkategori = rs.getInt("Id_Subkategori");
			  }
			  
			  String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
			  String TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
		      
		      //Create Record			  
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_Hakmilikurusan", idHakmilikurusan);
			  r.add("id_Permohonan", idPermohonan);	
			  r.add("pegangan_Hakmilik",peganganHakmilik);
			  r.add("id_Negeri", idNegeri);
			  r.add("id_Daerah", socDaerah);
			  r.add("id_Mukim", socMukim);
			  r.add("id_JenisHakmilik", socJenisHakmilik);
			  r.add("no_Hakmilik", NoHakmilik);
			  r.add("no_Lot", NoLot);
			  r.add("id_Lot", socLot);
			  r.add("no_Bangunan", NoBangunan);
		      r.add("no_Tingkat", NoTingkat);
		      r.add("no_Petak", NoPetak);
			  r.add("Tarikh_Mula", r.unquote(TT));
			  r.add("Tarikh_Luput", r.unquote(TL));
			  r.add("Luas", Luas);
			  r.add("id_Luas", socLuas);
			  r.add("no_Pelan", NoPelan);
			  r.add("id_Jenisrizab", socRizab);
			  r.add("id_Kategori", socKategori);
			  r.add("id_Subkategori", idSubkategori);		  
			  
		      sql = r.getSQLInsert("Tblhtphakmilikurusan");
		      log.info("FrmPembelianTBangunanData::Insert::Tblhtphakmilikurusan = "+sql);
		      stmt.executeUpdate(sql);
		      
		      conn.commit();
		      
		    }
		    catch(Exception e){
		    	conn.rollback();
		    	e.printStackTrace();
		    }
		    finally {
		    	if (conn != null)conn.close();
		      if (db != null) db.close();
		    }
		    
		    return idHakmilikurusan;
		  }
}
