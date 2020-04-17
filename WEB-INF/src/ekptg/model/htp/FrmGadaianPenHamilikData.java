package ekptg.model.htp;

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
import ekptg.helpers.Utils;

public class FrmGadaianPenHamilikData {
	private static Logger log = Logger.getLogger(ekptg.model.htp.FrmGadaianPenHamilikData.class);
	private static NumberFormat NF = new DecimalFormat("#,###,##0.00");
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
    private Db db = null;
    private String sql = "";
	
	//*** query data lama from database
	public static void setPenHakmilik(int id)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("p.Id_Permohonan");
	      r.add("f.Id_Kementerian");
	      r.add("f.No_Fail");
	      r.add("hp.Id_Agensi");
	      r.add("f.Tajuk_Fail");
	      r.add("hp.No_Rujukan_Lain");
	      r.add("f.Id_Negeri");
	      r.add("u.Id_Hakmilikurusan");
	      r.add("u.Id_Daerah");
	      r.add("u.Id_Mukim");
	      r.add("u.Id_Jenishakmilik");
	      r.add("u.No_Hakmilik");
	      r.add("u.No_Lot");
	      r.add("u.Id_Lot");
	      r.add("u.Tarikh_Mula");
	      r.add("u.Cukai");
	      r.add("u.Lokasi");
	      r.add("U.ID_LUAS");
	      r.add("U.LUAS");	      
	      r.add("U.ID_LUAS_BERSAMAAN");
	      r.add("U.LUAS_BERSAMAAN");	      
	      r.add("u.No_Pelan");
	      r.add("u.Id_Jenisrizab");
	      r.add("u.Id_Kategori");
	      r.add("u.Syarat");
	      r.add("u.Tarikh_Luput");	      
	      r.add("u.Cukai_Terkini");	      
	      r.add("u.Tarikh_Rizab");	      
	      r.add("u.No_Rizab");	      
	      r.add("u.No_Petak");
	      r.add("u.No_Syit");
	      r.add("u.Sekatan");
	      
	      r.add("p.Id_Permohonan", id);
	      r.add("f.Id_Fail",r.unquote("p.Id_Fail"));
	      r.add("hp.Id_Permohonan",r.unquote("p.Id_Permohonan"));
	      r.add("u.Id_Permohonan",r.unquote("p.Id_Permohonan"));
	      
	      sql = r.getSQLSelect("Tblpfdfail f, Tblpermohonan p, Tblhtppermohonan hp, Tblhtphakmilikurusan u");
	      ResultSet rs = stmt.executeQuery(sql);
	      log.info("setPenHakmilik("+id+") ::"+sql);
	      //Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  
	    	  h.put("IdPermohonan",rs.getString("Id_Permohonan"));
	    	  h.put("IdKementerian",rs.getString("Id_Kementerian"));
	    	  h.put("NoFail",rs.getString("No_Fail"));
	    	  h.put("IdAgensi",rs.getString("Id_Agensi"));
	    	  h.put("TajukFail",rs.getString("Tajuk_Fail"));
	    	  h.put("NoRujukanLain",rs.getString("No_Rujukan_Lain"));
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
	    	  if(rs.getString("Cukai") == null){
	    		  h.put("Cukai","");
	    	  }else{
	    		  h.put("Cukai",NF.format(rs.getDouble("Cukai")));
	    	  }
	    	  if(rs.getString("Lokasi") == null){
	    		  h.put("Lokasi","");
	    	  }else{
	    		  h.put("Lokasi",rs.getString("Lokasi"));
	    	  }
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
	    		  h.put("IdKategori","0");
	    	  }else{
	    		  h.put("IdKategori",rs.getString("Id_Kategori"));
	    	  }
	    	  if(rs.getString("Syarat") == null){
	    		  h.put("Syarat","");
	    	  }else{
	    		  h.put("Syarat",rs.getString("Syarat"));
	    	  }
	    	  if(rs.getString("Tarikh_Luput") == null){
	    		  h.put("TarikhLuput","");
	    	  }else{
	    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
	    	  }
	    	  if(rs.getString("Cukai_Terkini") == null){
	    		  h.put("CukaiTerkini","");
	    	  }else{
	    		  h.put("CukaiTerkini",NF.format(rs.getDouble("Cukai_Terkini")));
	    	  }
	    	  h.put("idLuas",rs.getString("ID_LUAS"));
	    	  if(rs.getString("LUAS") == null){
	    		  h.put("luas","");
	    	  }else{
	    		  h.put("luas",rs.getString("LUAS"));
	    	  }
	    	  h.put("idLuasBersamaan",rs.getString("ID_LUAS_BERSAMAAN"));
	    	  if(rs.getString("LUAS_BERSAMAAN") == null){
	    		  h.put("luasBersamaan","");
	    	  }else{
	    		  h.put("luasBersamaan",rs.getString("LUAS_BERSAMAAN"));
	    	  }	    	 
	    	  if(rs.getString("Tarikh_Rizab") == null){
	    		  h.put("TarikhRizab","");
	    	  }else{
	    		  h.put("TarikhRizab",Format.format(rs.getDate("Tarikh_Rizab")));
	    	  }
	    	  if(rs.getString("No_Rizab") == null){
	    		  h.put("NoRizab","");
	    	  }else{
	    		  h.put("NoRizab",rs.getString("No_Rizab"));
	    	  }
	    	  if(rs.getString("No_petak") == null){
	    		  h.put("Noptk","");
	    	  }else{
	    		  h.put("Noptk",rs.getString("No_petak"));
	    	  }
	    	  if(rs.getString("No_Syit") == null){
	    		  h.put("NoSyit","");
	    	  }else{
	    		  h.put("NoSyit",rs.getString("No_Syit"));
	    	  }
	    	  if(rs.getString("Sekatan") == null){
	    		  h.put("Sekatan","");
	    	  }else{
	    		  h.put("Sekatan",rs.getString("Sekatan"));
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
	
	//** query data lama from database
	// Created on 10/08/2010 by Mohamad Rosli */
	//public Hashtable getPendaftaranHakmilik(String idPermohonan)throws Exception {
	public Vector<Hashtable<String,String>> getPendaftaranHakmilik(String idPermohonan)throws Exception {
	    Db db = null;
	    String sql = "";
	    Hashtable<String,String> h =  null;
	    list = new Vector<Hashtable<String,String>>();
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("p.Id_Permohonan");
	      r.add("f.Id_Kementerian");
	      r.add("f.No_Fail");
	      r.add("hp.Id_Agensi");
	      r.add("f.Tajuk_Fail");
	      r.add("hp.No_Rujukan_Lain");
	      r.add("u.Id_Negeri");
	      r.add("u.Id_Hakmilikurusan");
	      r.add("u.Id_Daerah");
	      r.add("u.Id_Mukim");
	      r.add("u.Id_Jenishakmilik");
	      r.add("u.No_Hakmilik");
	      r.add("u.No_Lot");
	      r.add("u.Id_Lot");
	      r.add("u.Tarikh_Mula");
	      r.add("u.Cukai");
	      r.add("u.Lokasi");
	      r.add("U.ID_LUAS");
	      r.add("U.LUAS");	      
	      r.add("U.ID_LUAS_BERSAMAAN");
	      r.add("U.LUAS_BERSAMAAN");	      
	      r.add("u.No_Pelan");
	      r.add("u.Id_Jenisrizab");
	      r.add("u.Id_Kategori");
	      r.add("u.Syarat");
	      r.add("u.Tarikh_Luput");	      
	      r.add("u.Cukai_Terkini");	      
	      r.add("u.Tarikh_Rizab");	      
	      r.add("u.No_Rizab");	      
	      r.add("u.No_Petak");
	      r.add("u.No_Syit");
	      r.add("u.Sekatan");	    
	      r.add("u.no_fail_jofa");
	      r.add("p.Id_Permohonan", idPermohonan);
	      r.add("f.Id_Fail",r.unquote("p.Id_Fail"));
	      r.add("hp.Id_Permohonan",r.unquote("p.Id_Permohonan"));
	      r.add("u.Id_Permohonan",r.unquote("p.Id_Permohonan"));	      
	      sql = r.getSQLSelect("Tblpfdfail f, Tblpermohonan p, Tblhtppermohonan hp, Tblhtphakmilikurusan u");
	      log.info("getPendaftaranHakmilik("+idPermohonan+") ::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      //Vector list = new Vector();
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("IdPermohonan",rs.getString("Id_Permohonan"));
	    	  h.put("IdKementerian",rs.getString("Id_Kementerian"));
	    	  h.put("NoFail",rs.getString("No_Fail"));
	    	  h.put("IdAgensi",rs.getString("Id_Agensi"));
	    	  h.put("TajukFail",rs.getString("Tajuk_Fail"));
	    	  h.put("NoRujukanLain",rs.getString("No_Rujukan_Lain"));
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
	    	  if(rs.getString("Cukai") == null){
	    		  h.put("Cukai","");
	    	  }else{
	    		  h.put("Cukai",NF.format(rs.getDouble("Cukai")));
	    	  }
	    	  if(rs.getString("no_fail_jofa") == null){
	    		  h.put("NoJofa","");
	    	  }else{
	    		  h.put("NoJofa",rs.getString("no_fail_jofa"));
	    	  }
	    	  if(rs.getString("Lokasi") == null){
	    		  h.put("Lokasi","");
	    	  }else{
	    		  h.put("Lokasi",rs.getString("Lokasi"));
	    	  }
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
	    		  h.put("IdKategori","0");
	    	  }else{
	    		  h.put("IdKategori",rs.getString("Id_Kategori"));
	    	  }
	    	  if(rs.getString("Syarat") == null){
	    		  h.put("Syarat","");
	    	  }else{
	    		  h.put("Syarat",rs.getString("Syarat"));
	    	  }
	    	  if(rs.getString("Tarikh_Luput") == null){
	    		  h.put("TarikhLuput","");
	    	  }else{
	    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
	    	  }
	    	  if(rs.getString("Cukai_Terkini") == null){
	    		  h.put("CukaiTerkini","");
	    	  }else{
	    		  h.put("CukaiTerkini",NF.format(rs.getDouble("Cukai_Terkini")));
	    	  }
	    	  h.put("idLuas",rs.getString("ID_LUAS"));
	    	  if(rs.getString("LUAS") == null){
	    		  h.put("luas","");
	    	  }else{
	    		  h.put("luas",rs.getString("LUAS"));
	    	  }
	    	  h.put("idLuasBersamaan",rs.getString("ID_LUAS_BERSAMAAN"));
	    	  if(rs.getString("LUAS_BERSAMAAN") == null){
	    		  h.put("luasBersamaan","");
	    	  }else{
	    		  h.put("luasBersamaan",rs.getString("LUAS_BERSAMAAN"));
	    	  }	    	 
	    	  if(rs.getString("Tarikh_Rizab") == null){
	    		  h.put("TarikhRizab","");
	    	  }else{
	    		  h.put("TarikhRizab",Format.format(rs.getDate("Tarikh_Rizab")));
	    	  }
	    	  if(rs.getString("No_Rizab") == null){
	    		  h.put("NoRizab","");
	    	  }else{
	    		  h.put("NoRizab",rs.getString("No_Rizab"));
	    	  }
	    	  if(rs.getString("No_petak") == null){
	    		  h.put("Noptk","");
	    	  }else{
	    		  h.put("Noptk",rs.getString("No_petak"));
	    	  }
	    	  if(rs.getString("No_Syit") == null){
	    		  h.put("NoSyit","");
	    	  }else{
	    		  h.put("NoSyit",rs.getString("No_Syit"));
	    	  }
	    	  if(rs.getString("Sekatan") == null){
	    		  h.put("Sekatan","");
	    	  }else{
	    		  h.put("Sekatan",rs.getString("Sekatan"));
	    	  }
	    	  list.addElement(h);
	    	  
	      }
	    
	    }catch(Exception e){
	    	e.printStackTrace();
	    
	    }finally {
	      if (db != null) db.close();
	    
	    }
	    //return h;
	    return list;
	    
	}
	/** query data lama from database
	 Dibuat pada 10/08/2010 by Mohamad Rosli 
	 Dikemaskini pada 2017/12/25
	*/
	public Hashtable<String,String> getHakmilik(String idPermohonan) throws Exception {
	    Hashtable<String,String> h =  null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("p.id_permohonan");
	      r.add("f.id_kementerian");
	      r.add("f.no_fail");
	      r.add("hp.id_agensi");
	      r.add("f.tajuk_fail");
	      r.add("hp.no_rujukan_lain");
	      r.add("u.id_negeri");
	      r.add("u.id_hakmilikurusan");
	      r.add("u.id_daerah");
	      r.add("u.id_mukim");
	      r.add("u.id_jenishakmilik");
	      r.add("u.no_hakmilik");
	      r.add("u.no_lot");
	      r.add("u.id_lot");
	      r.add("u.Tarikh_Mula");
	      r.add("u.Cukai");
	      r.add("u.Lokasi");
	      r.add("U.ID_LUAS");
	      r.add("U.LUAS");	      
	      r.add("U.ID_LUAS_BERSAMAAN");
	      r.add("U.LUAS_BERSAMAAN");	      
	      r.add("u.No_Pelan");
	      r.add("u.Id_Jenisrizab");
	      r.add("u.Id_Kategori");
	      r.add("u.Syarat");
	      r.add("u.Tarikh_Luput");	      
	      r.add("u.Cukai_Terkini");	      
	      r.add("u.Tarikh_Rizab");	      
	      r.add("u.No_Rizab");	      
	      r.add("u.No_Petak");
	      r.add("u.No_Syit");
	      r.add("u.Sekatan");	    
	      r.add("u.no_fail_jofa");
	      r.add("p.id_permohonan", idPermohonan);
	      r.add("f.id_fail",r.unquote("p.id_fail"));
	      r.add("hp.id_permohonan",r.unquote("p.id_permohonan"));
	      r.add("u.id_permohonan",r.unquote("p.id_permohonan"));	      
	      sql = r.getSQLSelect("tblpfdfail f, tblpermohonan p, tblhtppermohonan hp, tblhtphakmilikurusan u");
	      log.info("getHakmilik("+idPermohonan+") ::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("IdPermohonan",rs.getString("Id_Permohonan"));
	    	  h.put("IdKementerian",rs.getString("Id_Kementerian"));
	    	  h.put("NoFail",rs.getString("No_Fail"));
	    	  h.put("IdAgensi",rs.getString("Id_Agensi"));
	    	  h.put("TajukFail",rs.getString("Tajuk_Fail"));
	    	  h.put("NoRujukanLain",rs.getString("No_Rujukan_Lain"));
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
	    	  if(rs.getString("Cukai") == null){
	    		  h.put("Cukai","");
	    	  }else{
	    		  h.put("Cukai",NF.format(rs.getDouble("Cukai")));
	    	  }
	    	  if(rs.getString("no_fail_jofa") == null){
	    		  h.put("NoJofa","");
	    	  }else{
	    		  h.put("NoJofa",rs.getString("no_fail_jofa"));
	    	  }
	    	  if(rs.getString("Lokasi") == null){
	    		  h.put("Lokasi","");
	    	  }else{
	    		  h.put("Lokasi",rs.getString("Lokasi"));
	    	  }
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
	    		  h.put("IdKategori","0");
	    	  }else{
	    		  h.put("IdKategori",rs.getString("Id_Kategori"));
	    	  }
	    	  if(rs.getString("Syarat") == null){
	    		  h.put("Syarat","");
	    	  }else{
	    		  h.put("Syarat",rs.getString("Syarat"));
	    	  }
	    	  if(rs.getString("Tarikh_Luput") == null){
	    		  h.put("TarikhLuput","");
	    	  }else{
	    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
	    	  }
	    	  if(rs.getString("Cukai_Terkini") == null){
	    		  h.put("CukaiTerkini","");
	    	  }else{
	    		  h.put("CukaiTerkini",NF.format(rs.getDouble("Cukai_Terkini")));
	    	  }
	    	  h.put("idLuas",rs.getString("ID_LUAS"));
	    	  if(rs.getString("LUAS") == null){
	    		  h.put("luas","0.0");
	    	  }else{
	    		  h.put("luas",rs.getString("LUAS"));
	    	  }
	    	  h.put("idLuasBersamaan",rs.getString("ID_LUAS_BERSAMAAN"));
	    	  if(rs.getString("LUAS_BERSAMAAN") == null){
	    		  h.put("luasBersamaan","0.0");
	    	  }else{
	    		  h.put("luasBersamaan",rs.getString("LUAS_BERSAMAAN"));
	    	  }	    	 
	    	  if(rs.getString("Tarikh_Rizab") == null){
	    		  h.put("TarikhRizab","");
	    	  }else{
	    		  h.put("TarikhRizab",Format.format(rs.getDate("Tarikh_Rizab")));
	    	  }
	    	  if(rs.getString("No_Rizab") == null){
	    		  h.put("NoRizab","");
	    	  }else{
	    		  h.put("NoRizab",rs.getString("No_Rizab"));
	    	  }
	    	  if(rs.getString("No_petak") == null){
	    		  h.put("Noptk","");
	    	  }else{
	    		  h.put("Noptk",rs.getString("No_petak"));
	    	  }
	    	  if(rs.getString("No_Syit") == null){
	    		  h.put("NoSyit","");
	    	  }else{
	    		  h.put("NoSyit",rs.getString("No_Syit"));
	    	  }
	    	  if(rs.getString("Sekatan") == null){
	    		  h.put("Sekatan","");
	    	  }else{
	    		  h.put("Sekatan",rs.getString("Sekatan"));
	    	  }
	    	  list.addElement(h);
	    	  
	      }
	    
	    }catch(Exception e){
	    	e.printStackTrace();
	    
	    }finally {
	      if (db != null) db.close();
	    
	    }
	    return h;
	    //return list;
	    
	}

	  
	  public static Vector getPenHakmilik(){
		  log.info("getPenHakmilik:: "+list.size());
		  return list;
	  }
	  
	//*** query data baru from database
		public static void setPenHakmilikBaru(int id)throws Exception {
		    Db db = null;
		    list.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("p.Id_Permohonan");
		      r.add("f.Id_Kementerian");
		      r.add("f.No_Fail");
		      r.add("hp.Id_Agensi");
		      r.add("f.Tajuk_Fail");
		      r.add("hp.No_Rujukan_Lain");
		      r.add("f.Id_Negeri");
		      
		      r.add("p.Id_Permohonan", id);
		      r.add("f.Id_Fail",r.unquote("p.Id_Fail"));
		      r.add("hp.Id_Permohonan",r.unquote("p.Id_Permohonan"));
		      
		      sql = r.getSQLSelect("Tblpfdfail f, Tblpermohonan p, Tblhtppermohonan hp");
		      ResultSet rs = stmt.executeQuery(sql);
		      log.info("FrmGadaianPenHakmilikData ::"+sql);
		      //Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("IdPermohonan",rs.getString("Id_Permohonan"));
		    	  h.put("IdKementerian",rs.getString("Id_Kementerian"));
		    	  h.put("NoFail",rs.getString("No_Fail"));
		    	  h.put("IdAgensi",rs.getString("Id_Agensi"));
		    	  h.put("TajukFail",rs.getString("Tajuk_Fail"));
		    	  h.put("NoRujukanLain",rs.getString("No_Rujukan_Lain"));
		    	  if(rs.getString("Id_Negeri") == null){
		    		  h.put("IdNegeri","");
		    	  }else{
		    		  h.put("IdNegeri",rs.getString("Id_Negeri"));
		    	  }
		    	  h.put("IdDaerah","");
		    	  h.put("IdMukim","");
		    	  h.put("IdJenishakmilik","");
		    	  h.put("NoHakmilik","");
		    	  h.put("NoLot","");
		    	  h.put("IdLot","");
		    	  h.put("TarikhMula","");
		    	  h.put("Cukai","");
		    	  h.put("Lokasi","");
		    	  h.put("IdLuas","");
		    	  h.put("NoPelan","");
		    	  h.put("IdRizab","");
		    	  h.put("IdKategori","");
		    	  h.put("Syarat","");
		    	  h.put("TarikhLuput","");
		    	  h.put("CukaiTerkini","");
		    	  h.put("Luas","");
		    	  h.put("TarikhRizab","");
		    	  h.put("NoRizab","");
		    	  h.put("Noptk","");
		    	  h.put("NoSyit","");
		    	  h.put("Sekatan","");
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
		  
		public static Vector<Hashtable<String,String>> getPenHakmilikBaru(){
			//log.info("getPenHakmilikBaru:: "+list.size());
			return list;
		}
		  
		//*** query data baru from database
		public Vector<Hashtable<String,String>> getPendaftaranHakmilikBaru(String idPermohonan)throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			 
				r.add("p.Id_Permohonan");
			    r.add("f.Id_Kementerian");
			    r.add("f.No_Fail");
			    r.add("hp.Id_Agensi");
			    r.add("f.Tajuk_Fail");
			    r.add("hp.No_Rujukan_Lain");
			    r.add("f.Id_Negeri");
			    r.add("p.Id_Permohonan", idPermohonan);
			    r.add("f.Id_Fail",r.unquote("p.Id_Fail"));
			    r.add("hp.Id_Permohonan",r.unquote("p.Id_Permohonan"));
			    sql = r.getSQLSelect("Tblpfdfail f, Tblpermohonan p, Tblhtppermohonan hp");
			    log.info("getPendaftaranHakmilikBaru ::sql="+sql);
			    ResultSet rs = stmt.executeQuery(sql);
			      //Vector list = new Vector();
			    Hashtable<String,String> h;
			    while (rs.next()) {
			    	h = new Hashtable<String,String>();
			    	h.put("IdPermohonan",rs.getString("Id_Permohonan"));
			    	h.put("IdKementerian",rs.getString("Id_Kementerian"));
			    	h.put("NoFail",rs.getString("No_Fail"));
			    	h.put("IdAgensi",rs.getString("Id_Agensi"));
			    	h.put("TajukFail",rs.getString("Tajuk_Fail"));
			    	h.put("NoRujukanLain",rs.getString("No_Rujukan_Lain"));
			    	if(rs.getString("Id_Negeri") == null){
			    		  h.put("IdNegeri","0");
			    	}else{
			    		h.put("IdNegeri",rs.getString("Id_Negeri"));
			    	}
			    	h.put("IdDaerah","");
			    	h.put("IdMukim","");
			    	h.put("IdJenishakmilik","");
			    	h.put("NoHakmilik","");
			    	h.put("NoLot","");
			    	h.put("IdLot","");
			    	h.put("TarikhMula","");
			    	h.put("Cukai","");
			    	h.put("Lokasi","");
			    	h.put("IdLuas","");
			    	h.put("NoPelan","");
			    	h.put("IdRizab","");
			    	h.put("IdKategori","");
			    	h.put("Syarat","");
			    	h.put("TarikhLuput","");
			    	h.put("CukaiTerkini","");
			    	h.put("Luas","");
			    	h.put("TarikhRizab","");
			    	h.put("NoRizab","");
			    	h.put("Noptk","");
			    	h.put("NoSyit","");
			    	h.put("Sekatan","");
			    	list.addElement(h);
			    	
			    }
			    
			}catch(Exception e){
			  	e.printStackTrace();
			    
			}finally {
			     if (db != null) db.close();
			    
			}
			
			return list;
			 
		}
	  
	  
	//*** update data in database
	public static void update(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try{
			String idPermohonan = (String)data.get("idPermohonan");
			String idHakmilikurusan = (String)data.get("idHakmilikurusan");
		      int socNegeri = Integer.parseInt(data.get("idNegeri").toString());
		      log.info("FrmGadaianPenHamilikData::socDaerah = "+data.get("socDaerah"));
		      int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
		      int socMukim = Integer.parseInt(data.get("socMukim").toString());
		      int socJenisHakmilik = Integer.parseInt(data.get("socJenisHakmilik").toString());
		      String NoHakmilik = (String)data.get("NoHakmilik");
		      String NoLot = (String)data.get("NoLot");
		      String NoJofa = (String)data.get("NoJofa");
		      int socLot = Integer.parseInt(data.get("socLot").toString());
			  String TarikhTerima = (String)data.get("TarikhTerima");
			  String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
			  double CukaiPertama;
			  if(data.get("CukaiPertama") != "")
				  CukaiPertama = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiPertama").toString()));
			  else
				  CukaiPertama = 0.0;
			  String Lokasi;
			  if(data.get("Lokasi") != null)
				  Lokasi = (String)data.get("Lokasi");
			  else
				  Lokasi = "";
			  String NoPelan;
			  if(data.get("NoPelan") != null)
				  NoPelan = (String)data.get("NoPelan");
			  else
				  NoPelan = "";
			  int socRizab = Integer.parseInt(data.get("socRizab").toString());
			  int socKategori = Integer.parseInt(data.get("socKategori").toString());
			  String Syarat;
			  if(data.get("Syarat") != null)
				  Syarat = (String)data.get("Syarat");
			  else
				  Syarat = "";
			  String TarikhLuput = (String)data.get("TarikhLuput");
			  String TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
			  double CukaiSemasa;
			  if(data.get("CukaiSemasa") != "")
				  CukaiSemasa = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiSemasa").toString()));
			  else
				  CukaiSemasa = 0.0;
			  int socLuas = Integer.parseInt(data.get("socLuas").toString());
			  String Luas;
			  if(data.get("Luas") != null)
				  Luas = (String)data.get("Luas");
			  else
				  Luas = "";
			  String TarikhWarta = (String)data.get("TarikhWarta");
			  String TW = "to_date('" + TarikhWarta + "','dd/MM/yyyy')";
			  String NoWarta = (String)data.get("NoWarta");
			  String NoPU;
			  if(data.get("NoPU") != null)
				  NoPU = (String)data.get("NoPU");
			  else
				  NoPU = "";
			  String NoSyit;
			  if(data.get("NoSyit") != null)
				  NoSyit = (String)data.get("NoSyit");
			  else
				  NoSyit = "";
			  String Sekatan;
			  if(data.get("Sekatan") != null)
				  Sekatan = (String)data.get("Sekatan");
			  else
				  Sekatan = "";
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Hakmilikurusan", idHakmilikurusan);
			  //r.update("id_Permohonan", idPermohonan);
			  r.add("id_Negeri", socNegeri);
			  r.add("id_Daerah", socDaerah);
			  r.add("id_Mukim", socMukim);
			  r.add("id_JenisHakmilik", socJenisHakmilik);
			  r.add("no_Hakmilik", NoHakmilik);
			  r.add("no_Lot", NoLot);
			  r.add("no_fail_jofa", NoJofa);
			  r.add("id_Lot", socLot);
			  r.add("tarikh_Mula",r.unquote(TT));
			  r.add("Cukai", CukaiPertama);
			  r.add("Lokasi", Lokasi);
			  r.add("id_Luas", socLuas);
			  r.add("Luas", Luas);
			  r.add("ID_LUAS_BERSAMAAN", r.unquote((String)data.get("idLuasBersamaan")));
			  r.add("LUAS_BERSAMAAN", (String)data.get("luasBersamaan"));
			  r.add("no_Pelan", NoPelan);
			  r.add("id_Jenisrizab", socRizab);
			  r.add("id_Kategori", socKategori);
			  r.add("Syarat", Syarat);
			  r.add("tarikh_Luput",r.unquote(TL));
			  r.add("cukai_Terkini", CukaiSemasa);
			  r.add("tarikh_Rizab",r.unquote(TW));
			  r.add("no_Rizab", NoWarta);
			  r.add("NO_PETAK", NoPU);
			  r.add("no_Syit", NoSyit);
			  r.add("Sekatan", Sekatan);
			  r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			  
		      sql = r.getSQLUpdate("Tblhtphakmilikurusan");
		      log.info("update:Tblhtphakmilikurusan ::sql= "+sql);
		      stmt.executeUpdate(sql);
		      
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    finally {
		      if (db != null) db.close();
		      
		    }
		  }	  
	/**
	 * Simpan data in database
	 */	
	  public static void simpan(Hashtable data) throws Exception {
		  double CukaiPertama = 0.0;
		  double CukaiSemasa = 0.0;
		  String Lokasi = "";
		  String peganganHakmilik = "JKPTG";
		  String socLuas = "0";
		  String NoPelan = "";
		  String socRizab = "0";
		  String socKategori = "1";
		  String idSubkategori = "96";
		  String Syarat = "";
		  String TL = "";
		  String Luas = "";
		  String TW = "";
		  String NoWarta = "";
		  String NoPU = "";
		  String NoSyit = "";
		  String Sekatan = "";
		  Db db = null;
		  String sql = "";

		    try {
		      String idPermohonan = (String)data.get("idPermohonan");
		      long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
		      String idNegeri = (String)data.get("idNegeri");
		      String socDaerah = (String)data.get("socDaerah");
		      String socMukim = (String)data.get("socMukim");
		      String socJenisHakmilik = (String)data.get("socJenisHakmilik");
		      String NoHakmilik = (String)data.get("NoHakmilik");
		      String NoLot = (String)data.get("NoLot");
		      String NoJofa = (String)data.get("NoJofa");
		      String socLot = (String)data.get("socLot");
			  String TarikhTerima = (String)data.get("TarikhTerima");
			  String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
			  String luas = (String)data.get("socLuas");
			  String TarikhLuput = (String)data.get("TarikhLuput");
			  String TarikhWarta = (String)data.get("TarikhWarta");
			  
			  if(data.get("CukaiPertama") != ""){
				  CukaiPertama = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiPertama").toString()));
			  }

			  if(data.get("Lokasi") != null && data.get("Lokasi") != ""){
				  Lokasi = (String)data.get("Lokasi");
			  }

			  if (luas != null && luas != ""){
				  socLuas = data.get("socLuas").toString();
			  }

			  if(data.get("NoPelan") != null && data.get("NoPelan") != ""){
				  NoPelan = (String)data.get("NoPelan");
			  }

			  if (data.get("socRizab") != null && data.get("socRizab") != ""){
				  socRizab = data.get("socRizab").toString();
			  }
			  
			  log.info("kat : " + data.get("socKategori").toString() );
			  if (data.get("socKategori") != null && data.get("socKategori") != ""){
				  socKategori = data.get("socKategori").toString();
			  }
	 
			  if(data.get("Syarat") != null && data.get("Syarat") != ""){
				  Syarat = (String)data.get("Syarat");
			  }

			  if(TarikhLuput != null && TarikhLuput != ""){
				  TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
			  }

			  if(data.get("CukaiSemasa") != "" && data.get("CukaiSemasa") != null){
				  CukaiSemasa = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiSemasa").toString()));
			  }
			  
			  if(data.get("Luas") != null && data.get("Luas") != ""){
				  Luas = (String)data.get("Luas");
			  }
			  if (TarikhWarta != null && TarikhWarta != ""){
				  TW = "to_date('" + TarikhWarta + "','dd/MM/yyyy')";
			  }

			  if(data.get("NoWarta") != null && data.get("NoWarta") != ""){
				  NoWarta = (String)data.get("NoWarta");
			  }
			  
			  if(data.get("NoPU") != null && data.get("NoPU") != ""){
				  NoPU = (String)data.get("NoPU");
			  }

			  if(data.get("NoSyit") != null && data.get("NoSyit") != ""){
				  NoSyit = (String)data.get("NoSyit");
			  }

			  if(data.get("Sekatan") != null && data.get("Sekatan") != ""){
				  Sekatan = (String)data.get("Sekatan");
			  }
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_Hakmilikurusan", idHakmilikurusan);
			  r.add("id_Permohonan", idPermohonan);
			  r.add("id_Negeri", idNegeri);
			  r.add("id_Daerah", socDaerah);
			  r.add("id_Mukim", socMukim);
			  r.add("id_JenisHakmilik", socJenisHakmilik);
			  r.add("no_Hakmilik", NoHakmilik);
			  r.add("no_Lot", NoLot);
			  r.add("id_Lot", socLot);
			  r.add("NO_FAIL_JOFA", NoJofa);
			  r.add("tarikh_Mula", r.unquote(TT));
			  if(CukaiPertama != 0.0)
				  r.add("Cukai", CukaiPertama);
			  r.add("Lokasi", Lokasi);
			  r.add("pegangan_Hakmilik",peganganHakmilik);
			  r.add("id_Luas", socLuas);
			  r.add("no_Pelan", NoPelan);
			  r.add("id_Jenisrizab", socRizab);
			  r.add("id_Kategori", socKategori);
			  
			  r.add("id_Subkategori", idSubkategori);
			  r.add("Syarat", Syarat);
			  
			  if(TL != "" && TL!= null){
				  r.add("tarikh_Luput", r.unquote(TL));
			  }else{
				  r.add("tarikh_Luput", TL);
			  }
 
			  if(CukaiSemasa != 0.0)
				  r.add("cukai_Terkini", CukaiSemasa);
			  r.add("Luas", Luas);
			  r.add("ID_LUAS_BERSAMAAN", data.get("idLuasBersamaan"));
			  r.add("LUAS_BERSAMAAN", data.get("luasBersamaan"));

			  if(TW != "" && TW != null){
				  r.add("tarikh_Rizab", r.unquote(TW));
			  } else{
				  r.add("tarikh_Rizab", TW);
			  }
			  
			  r.add("no_Rizab", NoWarta);
			  r.add("NO_Petak", NoPU);
			  r.add("no_Syit", NoSyit);
			  r.add("Sekatan", Sekatan);
		      sql = r.getSQLInsert("Tblhtphakmilikurusan");		      
		      log.info("FrmGadaianPenHakMilikData:sql= " + sql);
		      stmt.executeUpdate(sql);
		      
		      // add tarikh melepas gadai ke dlm db bg kes soc
		      String tarikhLepasGadai = (String)data.get("tarikhLepasGadai");
		      String idFail = (String)data.get("idFail");
		      if(tarikhLepasGadai != null && tarikhLepasGadai != ""){
		    	  SQLRenderer rLepas = new SQLRenderer();
		    	  Statement stmtLG = db.getStatement();
		    	  
		    	  String TLG = "to_date('" + tarikhLepasGadai + "','dd/MM/yyyy')";
		    	  long idFailMG = DB.getNextID("TBLHTPMAKLUMATGADAIAN_SEQ");
		    	  rLepas.add("id_htpmaklumatgadaian", idFailMG);
		    	  rLepas.add("id_permohonan", r.unquote(idPermohonan));
//					rLepas.add("tarikh_agihan", "");
		    	  rLepas.add("id_masuk", idFailMG);
		    	  rLepas.add("tarikh_masuk", "");
		    	  rLepas.add("id_kemaskini", idFailMG);
		    	  rLepas.add("tarikh_kemaskini", "");
		    	  rLepas.add("tarikh_lepasgadai", r.unquote(TLG));
		    	  rLepas.add("id_fail", r.unquote(idFail));					
		    	  String sqlLepasGadai = rLepas.getSQLInsert("TBLHTPMAKLUMATGADAIAN");				
		    	  log.info("FrmGadaianPenHakmilkData:SOC::sqlLepasGadai = "+ sqlLepasGadai);				
		    	  stmtLG.executeUpdate(sqlLepasGadai);				
				
		      }
		      
		    }catch(Exception e){
		    	e.printStackTrace();
		    
		    }finally {
		      if (db != null) db.close();
		    
		    }
		  
	  }

	  
}
