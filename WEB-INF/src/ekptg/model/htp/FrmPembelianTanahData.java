package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmPembelianTanahData {
	private static Vector list = new Vector();
	private static Vector listUlasan = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static NumberFormat NF = new DecimalFormat("#,###,##0.00");
	private static Logger log = Logger.getLogger(FrmPembelianTanahData.class);
	private static Connection conn = null;
	private static Db db = null;
	private static Vector hakMilik = new Vector();
	
	//*** query data from database
	public static void setTanah(long idPermohonan,long idDaerah, long idMukim)throws Exception {
//	    Db db = null;
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
//	      r.add("(u.No_Bangunan","'%TIADA%')","LIKE '%TIADA%' OR u.No_Tingkat LIKE '%TIADA%' OR u.No_Petak LIKE");
//	      r.add("u.No_Bangunan","%TIADA%","LIKE");
//	      r.add("u.No_Tingkat","%TIADA%","LIKE");
	      r.add("u.No_Petak","%TIADA%","LIKE");
//	      r.add("u.Status_Tanah",r.unquote("j.Id_Jenistanah"));
	      
	      if(idDaerah != 0)
	    	  r.add("u.Id_Daerah",idDaerah);
	      
	      if(idMukim != 0)
	    	  r.add("u.Id_Mukim",idMukim);
	      
	      sql = r.getSQLSelect("Tblhtphakmilikurusan u, Tblrujjenishakmilik h, Tblrujlot lot, Tblrujluas luas, Tblrujjenistanah j");
	      log.info("FrmPembelianTanahData ::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      //Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  	    	  
	    	  h.put("IdHakmilikurusan",rs.getString("Id_Hakmilikurusan") == null ? "" : rs.getString("Id_Hakmilikurusan"));
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
//	    	  h.put("IdLuas",rs.getString("Id_Luas"));
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
	      //return list;
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getTanah(){
		  log.info("FrmPembelianTanahData : getTanahSize : "+list.size());
		  log.info("FrmPembelianTanahData : getTanahList :"+list);
		  return list;
	  }
	  
	  public static void setUlasan(long idPermohonan)throws Exception {
		    Db db = null;
		    listUlasan.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("id_Ulasankptg");
		      r.add("tarikh_Hantar");	      
		      r.add("status");
		      r.add("ulasan");
		      
		      r.add("Id_Permohonan", idPermohonan);
		      
		      sql = r.getSQLSelect("Tblhtpulasankptg");
		      ResultSet rs = stmt.executeQuery(sql);
		      log.info("FrmPembelianTanahData ::"+sql);
		      //Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  	    	  
		    	  h.put("IdUlasankptg",rs.getString("id_Ulasankptg") == null ? "" : rs.getString("id_Ulasankptg"));
		    	  if(rs.getString("tarikh_Hantar") == null){
		    		  h.put("TarikhHantar","");
		    	  }else{
		    		  h.put("TarikhHantar",Format.format(rs.getDate("tarikh_Hantar")));
		    	  }
		    	  if(rs.getString("status") == null){
		    		  h.put("Syarat","");
		    	  }else{
		    		  h.put("Syarat",rs.getString("status"));
		    	  }
		    	  if(rs.getString("ulasan") == null){
		    		  h.put("Ulasan","");
		    	  }else{
		    		  h.put("Ulasan",rs.getString("ulasan"));
		    	  }   	  
		    	  listUlasan.addElement(h);
		      }

		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    
		    finally {
		      if (db != null) db.close();
		    }
		  }
		  
		  public static Vector getUlasan(){
			  log.info("FrmPembelianTanahData : getUlasan Size : "+listUlasan.size());
			  log.info(" FrmPembelianTanahData getUlasan List : "+listUlasan);
			  return listUlasan;
		  }
	  
	//*** query data from database for popup
	  public static void setTanahPop(long idPermohonan, long idHakmilikurusan)throws Exception {

		    list.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("u.Id_Hakmilikurusan");
		      r.add("u.Id_Negeri");	      
		      r.add("u.Id_Daerah");
		      r.add("u.Id_Mukim");
		      r.add("u.Id_Jenishakmilik");
		      r.add("u.No_Hakmilik");
		      r.add("u.No_Lot");
		      r.add("u.Id_Lot");
		      r.add("u.Tarikh_Mula");
		      r.add("u.Tarikh_Luput");
		      r.add("u.Luas");
		      r.add("u.Id_Luas");
		      r.add("u.No_Pelan");
//		      r.add("u.Status_Tanah");
		      r.add("u.Id_Jenisrizab");
		      r.add("u.Id_Kategori");
		      
		      r.add("u.Id_Permohonan", idPermohonan);
		      r.add("u.Id_Hakmilikurusan", idHakmilikurusan);
		      
		      sql = r.getSQLSelect("Tblhtphakmilikurusan u");
		      ResultSet rs = stmt.executeQuery(sql);
		      log.info("FrmPembelianTanahData ::"+sql);
		      //Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  	    	  
		    	  h.put("IdHakmilikurusan",rs.getString("Id_Hakmilikurusan") == null ? "" : rs.getString("Id_Hakmilikurusan"));
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
		    	  if(rs.getString("Id_Jenishakmilik") == null){
		    		  h.put("IdJenishakmilik","");
		    	  }else{
		    		  h.put("IdJenishakmilik",rs.getString("Id_Jenishakmilik"));
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
		    	  if(rs.getString("Id_Lot") == null){
		    		  h.put("IdLot","");
		    	  }else{
		    		  h.put("IdLot",rs.getString("Id_Lot"));
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
		    	  h.put("IdLuas",rs.getString("Id_Luas") == null ? "" : rs.getString("Id_Luas"));
//		    	  if(rs.getString("Status_Tanah") == null){
//		    		  h.put("StatusTanah","");
//		    	  }else{
//		    		  h.put("StatusTanah",rs.getString("Status_Tanah"));
//		    	  }
		    	  if(rs.getString("No_Pelan") == null){
		    		  h.put("NoPelan","");
		    	  }else{
		    		  h.put("NoPelan",rs.getString("No_Pelan"));
		    	  }
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
		      //return list;
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    
		    
		    finally {
		      if (db != null) db.close();
		    }
		  }
		  
		  public static Vector getTanahPop(){
			  log.info("FrmPembelianTanahData : getTanahPop : list size " + list.size());
			  return list;
		  }
	  
	  //*** update data in database
	  public static void update(Hashtable data) throws Exception {

		    String sql = "";
		    try
		    {
		      long idPermohonan = Long.parseLong(data.get("idPermohonan").toString());
		      long idHakmilikurusan = Long.parseLong(data.get("idHakmilikurusan").toString());
		      int socNegeri = (Integer)data.get("socNegeri");
		      log.info("FrmPembelianTanahData::socDaerah = "+data.get("socDaerah"));
		      int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
		      int socMukim = Integer.parseInt(data.get("socMukim").toString());
		      int socJenisHakmilik = Integer.parseInt(data.get("socJenisHakmilik").toString());
		      String NoHakmilik = (String)data.get("NoHakmilik");
		      String NoLot = (String)data.get("NoLot");
		      int socLot = Integer.parseInt(data.get("socLot").toString());
			  String TarikhTerima = (String)data.get("TarikhTerima");
			  String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
			  String TarikhLuput = (String)data.get("TarikhLuput");
			  String TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
			  String Luas;
			  if(data.get("Luas") != null)
				  Luas = (String)data.get("Luas");
			  else
				  Luas = "";
			  int socLuas = Integer.parseInt(data.get("socLuas").toString());
			  String NoPelan;
			  if(data.get("NoPelan") != null)
				  NoPelan = (String)data.get("NoPelan");
			  else
				  NoPelan = "";
//			  String status = (String)data.get("statusTanah");
			  int socRizab = Integer.parseInt(data.get("socRizab").toString());
			  int socKategori = Integer.parseInt(data.get("socKategori").toString());
			  			  
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
			  
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Hakmilikurusan", idHakmilikurusan);
			  r.update("id_Permohonan", idPermohonan);
			  r.add("id_Daerah", socDaerah);
			  r.add("id_Mukim", socMukim);
			  r.add("id_JenisHakmilik", socJenisHakmilik);
			  r.add("no_Hakmilik", NoHakmilik);
			  r.add("no_Lot", NoLot);
			  r.add("id_Lot", socLot);
			  r.add("tarikh_Mula",r.unquote(TT));
			  r.add("tarikh_Luput",r.unquote(TL));			  
			  r.add("Luas", Luas);
			  r.add("id_Luas", socLuas);
			  r.add("no_Pelan", NoPelan);
//			  r.add("Status_Tanah", status);
			  r.add("id_Jenisrizab", socRizab);
			  r.add("id_Kategori", socKategori);
			  
		      sql = r.getSQLUpdate("Tblhtphakmilikurusan");
		      log.info("FrmPembelianTanahData::Update::Tblhtphakmilikurusan = "+sql);
		      stmt.executeUpdate(sql);
		    }
		    
		    catch(Exception e){
		    	conn.rollback();
		    	e.printStackTrace();
		    }
		    
		    finally {
		    	if(conn != null)conn.close();
		      if (db != null) db.close();
		    }
		  }
	  
	  public static void updateUlasan(Hashtable data) throws Exception {

		    String sql = "";
		    try
		    {
		    	
		    	log.info("updateUlasan hash : " + data);
		    	
		      String idPermohonan = (String)data.get("idPermohonan");
		      String idUlasankptg = (String)data.get("idUlasankptg");
		      String status = (String)data.get("syarat");
//		      	if (data.get("syarat") != "")
//		      		status = (String)data.get("syarat");
//		      	else
//		      		status = "";
		      String ulasan = (String)data.get("ulasan");
//		      	if (data.get("ulasan") != "" )
//		      		ulasan = (String)data.get("ulasan");
//		      	else
//		      		ulasan = "";
//			  String tarikhHantar = (String)data.get("tarikhHantar");
//			  String TH = "to_date('" + tarikhHantar + "','dd/MM/yyyy')";
			  
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
			  Statement stmt = db.getStatement();
			  
			  
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Ulasankptg", idUlasankptg);
			  r.update("id_Permohonan", idPermohonan);
			  
			  if(!data.get("tarikhHantar").toString().equalsIgnoreCase("")){
				  r.add("tarikh_Hantar", r.unquote("to_date('" + data.get("tarikhHantar").toString() + "','dd/MM/yyyy')"));  
			  }
			  
			  r.add("status", status);
			  r.add("ulasan", ulasan);
			  
		      sql = r.getSQLUpdate("Tblhtpulasankptg");
		      log.info("FrmPembelianTanahData::UpdateUlasan::Tblhtpulasankptg = "+sql);
		      
		      
		      stmt.executeUpdate(sql);
		      
		      conn.commit();
		    }
		    catch(Exception e){
		    	conn.rollback();
		    	e.printStackTrace();
		    }
		    
		    
		    finally {
		    	if (db != null) db.close();
		    	if(conn != null)conn.close();
		      
		    }
		  }
	  
	//*** Simpan data in database
	  public static long simpan(Hashtable data) throws Exception {
	    
//		  Connection conn = null;
//		  Db db = null;
	    String sql = "";
	    long idPermohonan = 0L;
	    long idHakmilikurusan = 0L;

	    try
	    {
	    	log.info("FrmPerbelianTanahData : " + data);

	      idPermohonan = Long.parseLong(data.get("idPermohonan").toString());
	      idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
	      int idNegeri = Integer.parseInt(data.get("socNegeri").toString());
	      log.info("FrmPembelianTanahData::socDaerah = "+data.get("socDaerah"));
	      int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
	      int socMukim = Integer.parseInt(data.get("socMukim").toString());
	      int socJenisHakmilik = Integer.parseInt(data.get("socJenisHakmilik").toString());
	      String NoHakmilik = (String)data.get("NoHakmilik");
	      String NoLot = (String)data.get("NoLot");
	      int socLot = Integer.parseInt(data.get("socLot").toString());
		  String TarikhTerima = (String)data.get("TarikhTerima");
		  String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
		  String TarikhLuput = (String)data.get("TarikhLuput");
		  String TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
		  String peganganHakmilik = "JKPTG";
		  String Luas;
		  if(data.get("Luas") != null)
			  Luas = (String)data.get("Luas");
		  else
			  Luas = "";
		  int socLuas = Integer.parseInt(data.get("socLuas").toString());
		  String NoPelan;
		  if(data.get("NoPelan") != null)
			  NoPelan = (String)data.get("NoPelan");
		  else
			  NoPelan = "";
//			  String status = (String)data.get("statusTanah");
		  int socRizab = Integer.parseInt(data.get("socRizab").toString());
		  int socKategori = Integer.parseInt(data.get("socKategori").toString());
		  int idSubkategori = Integer.parseInt("96");
		  String tiada = "TIADA";
		  
		  db = new Db();
		  conn = db.getConnection();
		  conn.setAutoCommit(false);
		  
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
		  r.add("no_Bangunan", tiada);
		  r.add("no_Tingkat", tiada);
		  r.add("no_Petak", tiada);
		  r.add("no_Lot", NoLot);
		  r.add("id_Lot", socLot);
		  r.add("tarikh_Mula", r.unquote(TT));
		  r.add("tarikh_Luput", r.unquote(TL));
		  r.add("Luas", Luas);
		  r.add("id_Luas", socLuas);
		  r.add("no_Pelan", NoPelan);
//			  r.add("Status_Tanah", status);
		  r.add("id_Jenisrizab", socRizab);
		  r.add("id_Kategori", socKategori);
		  r.add("id_Subkategori", idSubkategori);		  
		  
	      sql = r.getSQLInsert("Tblhtphakmilikurusan");
	      log.info("FrmPembelianTanahData::Insert::Tblhtphakmilikurusan = "+sql);
	      stmt.executeUpdate(sql);
	      
	      conn.commit();

	    }
	    
	    catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    }
	    
	    finally {
	    	if(conn !=null) conn.close();
	    	if (db != null) db.close();
	    }
	    
	    log.info("FrmPembelianTanahData : Simpan : " + idHakmilikurusan);
	    return idHakmilikurusan;
	  }
	  
	  public static void simpanUlasan(Hashtable data) throws Exception {

	    String sql = "";
	    try
	    {
	      int idPermohonan = Integer.parseInt(data.get("idPermohonan").toString());
	      long idUlasankptg = DB.getNextID("TBLHTPULASANKPTG_SEQ");
	      String status;
	      	if (data.get("syarat") != "")
	      		status = (String)data.get("syarat");
	      	else
	      		status = "";
	      String ulasan;
	      	if (data.get("ulasan") != "" )
	      		ulasan = (String)data.get("ulasan");
	      	else
	      		ulasan = "";
		  String tarikhHantar = (String)data.get("tarikhHantar");
		  String TH = "to_date('" + tarikhHantar + "','dd/MM/yyyy')";
		  
		  db = new Db();
		  conn = db.getConnection();
		  conn.setAutoCommit(false);
		  
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.add("id_Ulasankptg", idUlasankptg);
		  r.add("id_Permohonan", idPermohonan);
		  r.add("tarikh_Hantar", r.unquote(TH));
		  r.add("status", status);
		  r.add("ulasan", ulasan);
		  
	      sql = r.getSQLInsert("Tblhtpulasankptg");
	      log.info("FrmPembelianTanahData::SimpanUlasan::Tblhtpulasankptg = "+sql);
	      stmt.executeUpdate(sql);
	      
	      conn.commit();
	    }
	    catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    }
	    finally {
	    	if(conn != null)conn.close();
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getListHakmilik(){
		  
		return hakMilik;
		  
	  }
	  
	  public static void setListHakmilik(String idPermohonan){
		  
		  hakMilik.clear();
		    String sql;
		    try {
		    	
		      db = new Db();

		     Statement stmt = db.getStatement();
		     sql = " SELECT U.ID_HAKMILIKURUSAN, U.NO_HAKMILIK ";
		     sql += " FROM Tblhtphakmilikurusan U ";
		     sql += " WHERE U.Id_Permohonan = " + idPermohonan  ;
		      
		     log.info("FrmPembelianTanahData :: setListHakmilik :: "+sql);
		     ResultSet rs = stmt.executeQuery(sql);

		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NoHakmilik",rs.getString("No_Hakmilik") == null ? "" : rs.getString("No_Hakmilik"));
		    	  h.put("idHakMilikUrusan",rs.getString("id_hakmilikurusan") == null ? "" : rs.getString("id_hakmilikurusan"));
		    	  
		    	  hakMilik.addElement(h);
		      }

		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    
		    finally {
		      if (db != null) db.close();
		    }
		  
		  
	  }
}
