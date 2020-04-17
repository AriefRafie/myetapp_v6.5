package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblhtphakmilikurusan;
import ekptg.model.entities.Tblhtppihakberkepentingan;

public class FrmPajakanKecilHakmilikData {
	
	private static Logger log = Logger.getLogger(ekptg.model.htp.FrmPajakanKecilHakmilikData.class);

	public static Vector getSenaraiHakmilik(Long idpermohonan)throws Exception {
		Db db = null;
	    String sql = "";
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();
	    	sql = "SELECT tmu.id_Hakmilikurusan,trn.nama_negeri,trd.nama_daerah,trm.nama_mukim,trjhm.KOD_JENIS_HAKMILIK,tmu.NO_HAKMILIK,trl.KETERANGAN, tmu.no_lot "+
	      		" FROM tblHTPHakMilikurusan tmu, tblrujnegeri trn,tblrujdaerah trd,tblrujmukim trm, tblrujjenishakmilik trjhm,tblrujlot trl "+
	      		" WHERE trn.ID_NEGERI = tmu.ID_NEGERI "+
	      		" AND tmu.ID_DAERAH=trd.ID_DAERAH "+
	      		" AND tmu.ID_MUKIM = trm.ID_MUKIM "+
	      		" AND tmu.ID_JENISHAKMILIK = trjhm.ID_JENISHAKMILIK "+
	      		" AND TMU.ID_LOT=trl.ID_LOT "+
	      		" AND tmu.id_permohonan ="+idpermohonan;	      
	      //System.out.println("FrmPajakanKecilHakmilikData:getSenaraiHakmilik("+idpermohonan+")::sql::"+sql);    
	      log.info("senarai hakmilik sql : " + sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("nolot", rs.getString("no_Lot"));
	    	  h.put("nohakmilik", rs.getString("no_Hakmilik"));
	    	  h.put("idhakmilikurusan", rs.getString("id_Hakmilikurusan"));
	    	  h.put("namanegeri", rs.getString("nama_negeri"));
	    	  h.put("namadaerah", rs.getString("nama_daerah"));
	    	  h.put("namamukim", rs.getString("nama_mukim"));
	    	  h.put("kodjenishakmilik", rs.getString("KOD_JENIS_HAKMILIK"));
	    	  h.put("keterangan", rs.getString("KETERANGAN"));
	    	  list.addElement(h);

	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    
	    }
	    
	}
	 
	public static boolean isHakmilikUrusan(Long idhakmilik)throws Exception {
		boolean isHakmilik = false;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " select a.no_Lot,a.no_Hakmilik, a.id_Permohonan,a.id_Hakmilikurusan " +
			" from Tblhtphakmilikurusan a " +
			" where a.id_Hakmilikurusan = "+idhakmilik;
		     
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			while (rs.next()) {
			    	  isHakmilik = true;
			
			}
			
		} finally {
			if (db != null) db.close();
		
		}
		return isHakmilik;
		
	}
	
	public static Tblhtphakmilikurusan getHakmilikInfo(Long idhakmilik)throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "select h.id_Hakmilikurusan,h.no_hakmilik,h.no_lot " +
				" from Tblhtphakmilikurusan h " +
			    " where h.id_Hakmilikurusan = "+idhakmilik;
			//System.out.println("FrmPajakanKecilHakmilikData:getHakmilikInfo("+idhakmilik+")::sql::"+sql);
		     
			ResultSet rs = stmt.executeQuery(sql);
			Tblhtphakmilikurusan h=null;
			while (rs.next()) {
				h = new Tblhtphakmilikurusan();
			    h.setIdHakmilikurusan(rs.getLong("id_Hakmilikurusan"));
			    h.setNoLot(rs.getString("no_lot"));
			    h.setNoHakmilik(rs.getString("no_hakmilik"));
			     //h.put("keterangan", rs.getString("keterangan"));
			    	  //list.addElement(h);
			}
			return h;
			
		} finally {
			if (db != null) db.close();
		
		}
	}		 
	 
	 public static Vector getSenaraiPemilik(Long idpermohonan)throws Exception {
		    Db db = null;
		    String sql = "";
		    Vector list = new Vector();
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      sql = "SELECT C.NO_RUJUKAN, C.NAMA, B.ID_HAKMILIKURUSAN, B.ID_PIHAKBERKEPENTINGAN, B.ID_HAKMILIKURUSANPB ";
		      sql += "FROM TBLHTPHAKMILIKURUSAN A ,TBLHTPHAKMILIKURUSANPB B, TBLHTPPIHAKBERKEPENTINGAN C  ";
		      sql += "WHERE A.ID_HAKMILIKURUSAN = B.ID_HAKMILIKURUSAN ";
		      sql += "AND B.ID_HAKMILIKURUSAN = C.ID_HAKMILIKURUSAN ";
		      sql += "AND B.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
		      sql += "AND A.ID_PERMOHONAN = '" + idpermohonan + "'";	      
		      log.info("FrmPajakanKecilHakmilikData:getSenaraiPemilik("+idpermohonan+")::sql::"+sql);
			     
		      ResultSet rs = stmt.executeQuery(sql);		     
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("norujukan", rs.getString("NO_RUJUKAN")== null? "" : rs.getString("NO_RUJUKAN"));
		    	  h.put("nama",rs.getString("NAMA")== null? "" :rs.getString("NAMA"));	
		    	  h.put("idpihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	  h.put("idHakmilikurusan", rs.getString("ID_HAKMILIKURUSAN"));
		    	  h.put("idHakmilikurusanPB", rs.getString("ID_HAKMILIKURUSANPB"));
		    	  list.addElement(h);
		    	  
		      }	      
		      
		    } finally {
		      if (db != null) db.close();
		    }		    
		    return list;
	 
	 }
	 
	 public static Vector getSenaraiPemilik(Long idpermohonan,Long idHakmilik)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      sql = "select distinct a.no_Rujukan,a.nama, a.id_Pihakberkepentingan " +
		              "from Tblhtppihakberkepentingan a,Tblhtphakmilikurusan h " +
		              "where a.id_Hakmilikurusan=h.id_Hakmilikurusan " +
		              "and h.id_Permohonan = "+idpermohonan+ " and a.id_Hakmilikurusan="+idHakmilik ;
			  System.out.println("FrmPajakanKecilHakmilikData:getSenaraiPemilik("+idpermohonan+")::sql::"+sql);
	     
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("norujukan", rs.getString("no_Rujukan")== null?"":rs.getString("no_Rujukan"));
		    	  h.put("nama",rs.getString("nama")== null?"":rs.getString("nama"));	
		    	  h.put("idpihakberkepentingan", rs.getString("id_Pihakberkepentingan"));
		    	  //h.put("keterangan", rs.getString("keterangan"));
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		    
		  }
	 
	 public static Tblhtppihakberkepentingan getPemilikInfo(Long idpemilik)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      sql = "SELECT ID_PIHAKBERKEPENTINGAN,ID_HAKMILIKURUSAN,"+
		      " ID_JENISNOPB,ID_JENISPB,"+
		      " NO_RUJUKAN,NAMA,ALAMAT1,ALAMAT2,ALAMAT3,POSKOD,"+
		      " ID_DAERAH,ID_NEGERI,"+
		      " NO_TEL,NO_FAX,"+
		      " ID_MASUK,TARIKH_MASUK ,"+
		      " ID_KEMASKINI ,TARIKH_KEMASKINI,ID_DB FROM "+
		      " Tblhtppihakberkepentingan where ID_PIHAKBERKEPENTINGAN="+idpemilik;
		      //sql = "select distinct a.no_Rujukan,a.nama, a.id_Pihakberkepentingan " +
		        //      "from Tblhtppihakberkepentingan a,Tblhtphakmilikurusan h " +
		          //    "where a.id_Hakmilikurusan=h.id_Hakmilikurusan " +
		            //  "and h.id_Permohonan = "+idpermohonan;
			  System.out.println("FrmPajakanKecilHakmilikData:getPemilikInfo("+idpemilik+")::sql::"+sql);
	     
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      //Hashtable h;
		      Tblhtppihakberkepentingan h=null;
		      while (rs.next()) {
		    	  h = new Tblhtppihakberkepentingan();
		    	  h.setIdHakmilikurusan(rs.getLong("id_hakmilikurusan"));
		    	  h.setNoRujukan(rs.getString("no_Rujukan")== null?"":rs.getString("no_Rujukan"));
		    	  h.setNama(rs.getString("nama")== null?"":rs.getString("nama"));
		    	  h.setAlamat1(rs.getString("alamat1")== null?"":rs.getString("alamat1"));
		    	  h.setAlamat2(rs.getString("alamat2")== null?"":rs.getString("alamat2"));
		    	  h.setAlamat3(rs.getString("alamat3")== null?"":rs.getString("alamat3"));
		    	  h.setPoskod(rs.getString("poskod"));
		    	  h.setIdDaerah(rs.getLong("id_daerah"));
		    	  //list.addElement(h);
		      }
		      return h;
		    } finally {
		      if (db != null) db.close();
		    }
		    
		  }	 
	 
	 public static Tblhtppihakberkepentingan getPemilikInfo(String idpemilik)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      sql = "SELECT ID_PIHAKBERKEPENTINGAN,ID_HAKMILIKURUSAN,"+
		      " ID_JENISNOPB,ID_JENISPB,"+
		      " NO_RUJUKAN,NAMA,ALAMAT1,ALAMAT2,ALAMAT3,POSKOD,"+
		      " ID_DAERAH,ID_NEGERI,"+
		      " NO_TEL,NO_FAX,"+
		      " ID_MASUK,TARIKH_MASUK ,"+
		      " ID_KEMASKINI ,TARIKH_KEMASKINI,ID_DB FROM "+
		      " Tblhtppihakberkepentingan where ID_PIHAKBERKEPENTINGAN="+idpemilik;
		      //sql = "select distinct a.no_Rujukan,a.nama, a.id_Pihakberkepentingan " +
		        //      "from Tblhtppihakberkepentingan a,Tblhtphakmilikurusan h " +
		          //    "where a.id_Hakmilikurusan=h.id_Hakmilikurusan " +
		            //  "and h.id_Permohonan = "+idpermohonan;
			  System.out.println("FrmPajakanKecilHakmilikData:getPemilikInfo("+idpemilik+")::sql::"+sql);
	     
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      //Hashtable h;
		      Tblhtppihakberkepentingan h=null;
		      while (rs.next()) {
		    	  h = new Tblhtppihakberkepentingan();
		    	  h.setIdHakmilikurusan(rs.getLong("id_hakmilikurusan"));
		    	  h.setNoRujukan(rs.getString("no_Rujukan")== null?"":rs.getString("no_Rujukan"));
		    	  h.setNama(rs.getString("nama")== null?"":rs.getString("nama"));
		    	  h.setAlamat1(rs.getString("alamat1")== null?"":rs.getString("alamat1"));
		    	  h.setAlamat2(rs.getString("alamat2")== null?"":rs.getString("alamat2"));
		    	  h.setAlamat3(rs.getString("alamat3")== null?"":rs.getString("alamat3"));
		    	  h.setPoskod(rs.getString("poskod"));
		    	  h.setIdNegeri(rs.getLong("id_negeri"));
		    	  h.setIdDaerah(rs.getLong("id_daerah"));
		    	  //list.addElement(h);
		      }
		      return h;
		    } finally {
		      if (db != null) db.close();
		    }
		    
		  }
		  
	//*** Simpan data in database TBLHTPHAKMILIKURUSAN
	  public static Long simpan(Hashtable data) throws Exception {
		    Db db = null;
		    Long i = 0L;
		    String sql = "";
		    try	{
		      String idPermohonan = String.valueOf(data.get("idPermohonan"));
		      long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
		      //long idHakmilikurusan = (Long)data.get("idHakmilikurusan");;
		      int idNegeri = Integer.parseInt(data.get("idNegeri").toString());
		      System.out.println("FrmGadaianPenHamilikData::socDaerah = "+data.get("socDaerah1"));
		      int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
		      int socDaerah1 = Integer.parseInt(data.get("socDaerah1").toString());
		      int socMukim = Integer.parseInt(data.get("socMukim").toString());
		      int socJenisHakmilik = Integer.parseInt(data.get("socHakmilik").toString());
		      String NoHakmilik = (String)data.get("NoHakmilik");
		      String NoLot = (String)data.get("NoLot");
		      int socLot = Integer.parseInt(data.get("socLot").toString());
		      String noBangunan = (String)data.get("noBangunan");
		      String noTingkat = (String)data.get("noTingkat");
		      String noPetak = (String)data.get("noPetak");
			  String TarikhTerima = (String)data.get("TarikhTerima");
			  String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
			  double CukaiPertama;
			  if(data.get("CukaiPertama") != null)
				  CukaiPertama = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiPertama").toString()));
			  else
				  CukaiPertama = 0.0;
			  String Lokasi;
			  if(data.get("Lokasi") != null)
				  Lokasi = (String)data.get("Lokasi");
			  else
				  Lokasi = "";
			  String peganganHakmilik = "JKPTG";
			  int socLuas = Integer.parseInt(data.get("socLuas").toString());
			  String NoPelan;
			  if(data.get("NoPelan") != null)
				  NoPelan = (String)data.get("NoPelan");
			  else
				  NoPelan = "";
			  int socRizab = Integer.parseInt(data.get("socRizab").toString());
			  int socKategori = Integer.parseInt(data.get("socKategori").toString());
			  int idSubkategori = Integer.parseInt("96");
			  String Syarat;
			  if(data.get("Syarat") != null)
				  Syarat = (String)data.get("Syarat");
			  else
				  Syarat = "";
			  String TarikhLuput = (String)data.get("TarikhLuput");
			  String TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
			  double CukaiSemasa;
			  if(data.get("CukaiSemasa") != null)
				  CukaiSemasa = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiSemasa").toString()));
			  else
				  CukaiSemasa = 0.0;
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
			  r.add("id_Hakmilikurusan", idHakmilikurusan);
			  r.add("id_Permohonan", idPermohonan);
			  r.add("id_Negeri", idNegeri);
			  r.add("id_Daerah", socDaerah);
			  r.add("id_Mukim", socMukim);
			  r.add("id_JenisHakmilik", socJenisHakmilik);
			  r.add("no_Hakmilik", NoHakmilik);
			  r.add("no_Lot", NoLot);
			  r.add("id_Lot", socLot);
			  r.add("NO_BANGUNAN",noBangunan);
			  r.add("NO_TINGKAT",noTingkat);
			  r.add("NO_PETAK",noPetak);
			  r.add("tarikh_Mula", r.unquote(TT));
			  r.add("Cukai", CukaiPertama);
			  r.add("Lokasi", Lokasi);
			  r.add("pegangan_Hakmilik",peganganHakmilik);
			  r.add("id_Luas", socLuas);
			  r.add("no_Pelan", NoPelan);
			  r.add("id_Jenisrizab", socRizab);
			  r.add("id_Kategori", socKategori);
			  r.add("id_Subkategori", idSubkategori);
			  r.add("Syarat", Syarat);
			  r.add("tarikh_Luput", r.unquote(TL));
			  r.add("cukai_Terkini", CukaiSemasa);
			  r.add("Luas", Luas);
			  r.add("tarikh_Rizab", r.unquote(TW));
			  r.add("no_Rizab", NoWarta);
			 // r.add("no_petak", NoPU);
			  r.add("no_Syit", NoSyit);
			  r.add("Sekatan", Sekatan);
			  
		      sql = r.getSQLInsert("Tblhtphakmilikurusan");
		      System.out.println("FrmMukimData::Insert::Tblhtphakmilikurusan = "+sql);
		      if(stmt.executeUpdate(sql) !=0)
		    	  i = idHakmilikurusan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
			return i;
		  }
	  public static Long simpanOnline(Hashtable data) throws Exception {
		    Db db = null;
		    Long i = 0L;
		    String sql = "";
		    try	{
		      int idPermohonan = (Integer)data.get("idPermohonan");
		      long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
		      //long idHakmilikurusan = (Long)data.get("idHakmilikurusan");;
		      int idNegeri = Integer.parseInt(data.get("idNegeri").toString());
		      System.out.println("FrmGadaianPenHamilikData::socDaerah = "+data.get("socDaerah1"));
		      int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
		     // int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
		      int socMukim = Integer.parseInt(data.get("socMukim").toString());
		      int socJenisHakmilik = Integer.parseInt(data.get("socHakmilik").toString());
		      String NoHakmilik = (String)data.get("NoHakmilik");
		      String NoLot = (String)data.get("NoLot");
		      int socLot = Integer.parseInt(data.get("socLot").toString());
			  String TarikhTerima = (String)data.get("TarikhTerima");
			  String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
			  double CukaiPertama;
			  if(data.get("CukaiPertama") != null)
				  CukaiPertama = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiPertama").toString()));
			  else
				  CukaiPertama = 0.0;
			  String Lokasi;
			  if(data.get("Lokasi") != null)
				  Lokasi = (String)data.get("Lokasi");
			  else
				  Lokasi = "";
			  String peganganHakmilik = "JKPTG";
			  int socLuas = Integer.parseInt(data.get("socLuas").toString());
			  String NoPelan;
			  if(data.get("NoPelan") != null)
				  NoPelan = (String)data.get("NoPelan");
			  else
				  NoPelan = "";
			  int socRizab = Integer.parseInt(data.get("socRizab").toString());
			  int socKategori = Integer.parseInt(data.get("socKategori").toString());
			  int idSubkategori = Integer.parseInt("96");
			  String Syarat;
			  if(data.get("Syarat") != null)
				  Syarat = (String)data.get("Syarat");
			  else
				  Syarat = "";
			  String TarikhLuput = (String)data.get("TarikhLuput");
			  String TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
			  double CukaiSemasa;
			  if(data.get("CukaiSemasa") != null)
				  CukaiSemasa = Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiSemasa").toString()));
			  else
				  CukaiSemasa = 0.0;
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
			  r.add("id_Hakmilikurusan", idHakmilikurusan);
			  r.add("id_Permohonan", idPermohonan);
			  r.add("id_Negeri", idNegeri);
			  r.add("id_Daerah", socDaerah);
			  r.add("id_Mukim", socMukim);
			  r.add("id_JenisHakmilik", socJenisHakmilik);
			  r.add("no_Hakmilik", NoHakmilik);
			  r.add("no_Lot", NoLot);
			  r.add("id_Lot", socLot);
			  r.add("tarikh_Mula", r.unquote(TT));
			  r.add("Cukai", CukaiPertama);
			  r.add("Lokasi", Lokasi);
			  r.add("pegangan_Hakmilik",peganganHakmilik);
			  r.add("id_Luas", socLuas);
			  r.add("no_Pelan", NoPelan);
			  r.add("id_Jenisrizab", socRizab);
			  r.add("id_Kategori", socKategori);
			  r.add("id_Subkategori", idSubkategori);
			  r.add("Syarat", Syarat);
			  r.add("tarikh_Luput", r.unquote(TL));
			  r.add("cukai_Terkini", CukaiSemasa);
			  r.add("Luas", Luas);
			  r.add("tarikh_Rizab", r.unquote(TW));
			  r.add("no_Rizab", NoWarta);
			  r.add("no_petak", NoPU);
			  r.add("no_Syit", NoSyit);
			  r.add("Sekatan", Sekatan);
			  
		      sql = r.getSQLInsert("Tblhtphakmilikurusan");
		      System.out.println("FrmMukimData::Insert::Tblhtphakmilikurusan = "+sql);
		      if(stmt.executeUpdate(sql) !=0)
		    	  i = idHakmilikurusan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
			return i;
		  }
	  public static void simpanTajuk(String tajuk,String id) throws Exception{
		  Db db = null;
		  String sql = "";
		  try {
			  db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      System.out.println("FrmGadaianSemakanData::Update::tajuk = "+tajuk);
			  
		      r.update("id_fail", id);
			  r.add("tajuk_fail", tajuk);

		      sql = r.getSQLUpdate("TBLPFDFAIL");
		      System.out.println("FrmGadaianSemakanData::Update::TBLPFDFAIL = "+sql);
		      stmt.executeUpdate(sql);			  
			  
		} finally {
			if (db != null) db.close();
		}
	  }
	//*** save data in database TBLHTPPIHAKBERKEPENTINGAN
	  
	  //fir modified to insert it at tblhtphakmilikurusanpb also..
	  public static void simpanPemilik(Hashtable data) throws Exception {
		  Db db = null;
		  String sql = "";
		  String userID = data.get("userID").toString();
		  try{
			  String IdPihakberkepentingan = String.valueOf(DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ"));
		      String idHakmilikurusan = String.valueOf(data.get("idHakmilikurusan"));
		      int idJenisnopb = Integer.parseInt("1");
		      int idJenispb = Integer.parseInt("1");
		      String noRujukan = (String)data.get("noRujukan");
		      String nama = (String)data.get("nama");
		      String alamat1 = (String)data.get("alamat1");
		      String alamat2;
		      if(data.get("alamat2") != null)
		    	  alamat2 = (String)data.get("alamat2");
		      else
		    	  alamat2 = "";
		      String alamat3;
		      if(data.get("alamat3") != null)
		    	  alamat3 = (String)data.get("alamat3");
		      else
		    	  alamat3 = "";		      
		      String poskod = (String)data.get("poskod");
		      int idDaerah = (Integer)data.get("idDaerah");
		      int idNegeri = (Integer)data.get("idNegeri");
		      String noTelefon;
		      if(data.get("noTelefon") != null)
		    	  noTelefon = (String)data.get("noTelefon");
		      else
		    	  noTelefon = "";
		      String noFax;
		      if(data.get("noFax") != null)
		    	  noFax = (String)data.get("noFax");
		      else
		    	  noFax = "";
		      
		      //long idBebanan = DB.getNextID("TBLHTPBEBANAN_SEQ");
		      //String noPerserahan = (String)data.get("noPerserahan");
		      //String jilid = "1";
		      //String folio = "1";
		      Date now = new Date();
		      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      //int IdRujbebanan = Integer.parseInt("224");
		      //int IdRujpihakberkepentingan = Integer.parseInt("1");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  r.add("id_Hakmilikurusan", idHakmilikurusan);
			  r.add("id_Jenisnopb", idJenisnopb);
			  r.add("id_Jenispb", idJenispb);
			  r.add("no_rujukan", noRujukan);
			  r.add("nama", nama);
			  r.add("alamat1", alamat1);
			  r.add("alamat2", alamat2);
			  r.add("alamat3", alamat3);
			  r.add("poskod", poskod);
			  r.add("id_Daerah", idDaerah);
			  r.add("id_Negeri", idNegeri);
			  r.add("no_Tel", noTelefon);
			  r.add("no_Fax", noFax);
			  sql = r.getSQLInsert("Tblhtppihakberkepentingan");
			  System.out.println("FrmpajakanKecilHakmilikData::Insert::Tblhtppihakberkepentingan = "+sql);
			  stmt.executeUpdate(sql);
			  
			  
			  //fir
			  //TBLHTPHAKMILIKURUSANPB
			  r = new SQLRenderer();
			  long idHakmilikurusanPB = DB.getNextID("TBLHTPHAKMILIKURUSANPB_SEQ");
			  
			  r.add("ID_HAKMILIKURUSANPB",idHakmilikurusanPB);
			  r.add("ID_PIHAKBERKEPENTINGAN",IdPihakberkepentingan);
			  r.add("ID_HAKMILIKURUSAN",idHakmilikurusan);
			  r.add("ID_MASUK",userID);
			  r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
			  r.add("ID_KEMASKINI",userID);
			  r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
			  r.add("ID_DB","99");
			  
			  
			  sql = r.getSQLInsert("TBLHTPHAKMILIKURUSANPB");
			  
			  log.info("sql simpanHakMilikUrusanPB : " + sql);
			  
			  stmt.executeUpdate(sql);
			  
			  
		      
		      /*Statement stmtP = db.getStatement();
			  SQLRenderer rP = new SQLRenderer();
			  rP.add("id_Bebanan", idBebanan);
			  rP.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  rP.add("nama_Pihak_Berkepentingan", nama);
			  rP.add("no_Perserahan", noPerserahan);
			  rP.add("jilid", jilid);
			  rP.add("folio", folio);
			  rP.add("tarikh_Daftar", rP.unquote(sekarang));
			  rP.add("alamat1", alamat1);
			  rP.add("alamat2", alamat2);
			  rP.add("alamat3", alamat3);
			  rP.add("poskod", poskod);
			  rP.add("id_Daerah", idDaerah);
			  rP.add("id_Negeri", idNegeri);
			  rP.add("no_Tel", noTelefon);
			  rP.add("no_Fax", noFax);
			  rP.add("id_Rujbebanan", IdRujbebanan);
			  rP.add("Id_Rujpihakberkepentingan", IdRujpihakberkepentingan);
		      sql = rP.getSQLInsert("Tblhtpbebanan");
		      System.out.println("FrmGadaianHakmilikData::Insert::Tblhtpbebanan = "+sql);
		      stmtP.executeUpdate(sql);*/
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	  
		//*** save data in database TBLHTPPIHAKBERKEPENTINGAN
	  public static int simpanPB(Hashtable data) throws Exception {
		  Db db = null;
		  String sql = "";
		  int returnValue=0;
		  try{
			  long IdPihakberkepentingan = DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ");
		      int idHakmilikurusan = (Integer)data.get("idHakmilikurusan");
		      int idJenisnopb = Integer.parseInt("1");
		      int idJenispb = Integer.parseInt("1");
		      String noRujukan = (String)data.get("noRujukan");
		      String nama = (String)data.get("nama");
		      String alamat1 = (String)data.get("alamat1");
		      String alamat2;
		      if(data.get("alamat2") != null)
		    	  alamat2 = (String)data.get("alamat2");
		      else
		    	  alamat2 = "";
		      String alamat3;
		      if(data.get("alamat3") != null)
		    	  alamat3 = (String)data.get("alamat3");
		      else
		    	  alamat3 = "";		      
		      String poskod = (String)data.get("poskod");
		      int idDaerah = (Integer)data.get("idDaerah");
		      int idNegeri = (Integer)data.get("idNegeri");
		      String noTelefon;
		      if(data.get("noTelefon") != null)
		    	  noTelefon = (String)data.get("noTelefon");
		      else
		    	  noTelefon = "";
		      String noFax;
		      if(data.get("noFax") != null)
		    	  noFax = (String)data.get("noFax");
		      else
		    	  noFax = "";
		      
		      //long idBebanan = DB.getNextID("TBLHTPBEBANAN_SEQ");
		      //String noPerserahan = (String)data.get("noPerserahan");
		      //String jilid = "1";
		      //String folio = "1";
		      Date now = new Date();
		      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      //int IdRujbebanan = Integer.parseInt("224");
		      //int IdRujpihakberkepentingan = Integer.parseInt("1");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  r.add("id_Hakmilikurusan", idHakmilikurusan);
			  r.add("id_Jenisnopb", idJenisnopb);
			  r.add("id_Jenispb", idJenispb);
			  r.add("no_rujukan", noRujukan);
			  r.add("nama", nama);
			  r.add("alamat1", alamat1);
			  r.add("alamat2", alamat2);
			  r.add("alamat3", alamat3);
			  r.add("poskod", poskod);
			  r.add("id_Daerah", idDaerah);
			  r.add("id_Negeri", idNegeri);
			  r.add("no_Tel", noTelefon);
			  r.add("no_Fax", noFax);
			  sql = r.getSQLInsert("Tblhtppihakberkepentingan");
			  System.out.println("FrmpajakanKecilHakmilikData::Insert::Tblhtppihakberkepentingan = "+sql);
			  stmt.executeUpdate(sql);
		      
		      /*Statement stmtP = db.getStatement();
			  SQLRenderer rP = new SQLRenderer();
			  rP.add("id_Bebanan", idBebanan);
			  rP.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  rP.add("nama_Pihak_Berkepentingan", nama);
			  rP.add("no_Perserahan", noPerserahan);
			  rP.add("jilid", jilid);
			  rP.add("folio", folio);
			  rP.add("tarikh_Daftar", rP.unquote(sekarang));
			  rP.add("alamat1", alamat1);
			  rP.add("alamat2", alamat2);
			  rP.add("alamat3", alamat3);
			  rP.add("poskod", poskod);
			  rP.add("id_Daerah", idDaerah);
			  rP.add("id_Negeri", idNegeri);
			  rP.add("no_Tel", noTelefon);
			  rP.add("no_Fax", noFax);
			  rP.add("id_Rujbebanan", IdRujbebanan);
			  rP.add("Id_Rujpihakberkepentingan", IdRujpihakberkepentingan);
		      sql = rP.getSQLInsert("Tblhtpbebanan");
		      System.out.println("FrmGadaianHakmilikData::Insert::Tblhtpbebanan = "+sql);
		      stmtP.executeUpdate(sql);*/
			  returnValue = Integer.parseInt(""+IdPihakberkepentingan);
		    }
		    finally {
		      if (db != null) db.close();
		    }
				  return returnValue ;
	    
		  }
	  
	  
	  public static void simpanHakMilikUrusanPB(String idPihakBerkepentingan, String idHakmilikUrusan, HttpSession session) throws Exception{
		  Connection conn = null;
		  Db db = null;
		  
		  try{
			  String userID = session.getAttribute("_ekptg_user_id").toString();
			  String sql = null;
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
			  Statement stmt = conn.createStatement();
			  
			  SQLRenderer r  = new SQLRenderer();
			  
			  long idHakmilikurusanPB = DB.getNextID("TBLHTPHAKMILIKURUSANPB_SEQ");
			  
			  r.add("ID_HAKMILIKURUSANPB",idHakmilikurusanPB);
			  r.add("ID_PIHAKBERKEPENTINGAN",idPihakBerkepentingan);
			  r.add("ID_HAKMILIKURUSAN",idHakmilikUrusan);
			  r.add("ID_MASUK",userID);
			  r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
			  r.add("ID_KEMASKINI",userID);
			  r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
			  r.add("ID_DB","99");
			  
			  
			  sql = r.getSQLInsert("TBLHTPHAKMILIKURUSANPB");
			  
			  log.info("sql simpanHakMilikUrusanPB : " + sql);
			  
			  stmt.executeUpdate(sql);
			  
			  conn.commit();
			  
			  
			  
		  }catch(Exception e){
			  conn.rollback();
			  e.printStackTrace();
		  }
		  
		  finally{
			  conn.close();
			  db.close();
		  }

	  }
	  public static void deletePemilik(String idPemilikPB){
		  Db db = null;
		  try{
			  String sql ="DELETE TBLHTPHAKMILIKURUSANPB WHERE ID_HAKMILIKURUSANPB="+idPemilikPB;
			  db = new Db();
		      Statement stmt = db.getStatement();
		      stmt.executeUpdate(sql);
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
		  finally{
			  if (db != null) db.close();
		  }
	  }
	  public static void deleteHakmilik(String idHakmilik){
		  Db db = null;
		  Connection conn = null;
		  try{
			  String sql ="DELETE TBLHTPHAKMILIKURUSANPB WHERE ID_HAKMILIKURUSAN="+idHakmilik;
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
		      stmt.executeUpdate(sql);
		      
		      sql ="DELETE TBLHTPHAKMILIKURUSAN WHERE ID_HAKMILIKURUSAN="+idHakmilik;
		      stmt.executeUpdate(sql);
		      
		      conn.commit();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
		  finally{
			 
			  if (db != null) db.close();
		  }
	  }
	  
	  public static HakmilikUrusan getSenaraiPemilikbyIdHakmilikUrusan(HakmilikUrusan hu,String idHakmilikUrusan)throws Exception {
		    Db db = null;
		    String sql = "";
		   
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT A.ID_HAKMILIKURUSAN, A.ID_PIHAKBERKEPENTINGAN, B.NAMA, B.NO_RUJUKAN, A.ID_HAKMILIKURUSANPB ";
		      sql += "FROM TBLHTPHAKMILIKURUSANPB A ";
		      sql += "LEFT JOIN TBLHTPPIHAKBERKEPENTINGAN B ";
		      sql += "on  A.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN  ";
		      sql += "WHERE A.ID_HAKMILIKURUSAN = '" + idHakmilikUrusan + "'";		      
		      //log.info("getSenaraiPemilikbyIdHakmilikUrusan : " + sql);
			     
		      ResultSet rs = stmt.executeQuery(sql);		     
		      while (rs.next()) {
		    	  PihakBerkepentingan pb = new PihakBerkepentingan();
		    	  pb.setNama(rs.getString("NAMA")== null? "" :rs.getString("NAMA"));
		    	  pb.setNoRujukan(rs.getString("NO_RUJUKAN")== null? "" : rs.getString("NO_RUJUKAN"));
		    	  pb.setIdpihakberkepentingan(rs.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	  pb.setIdHakmilikurusanPB(rs.getString("ID_HAKMILIKURUSANPB"));
		    	  hu.addPihakBerkepentingan(pb);
		    	  
		      }
		      
		    } finally {
		      if (db != null) db.close();
		      
		    }	    
		    return hu;
		    
	  }
	  
	  public static Vector getSenaraiPemilikbyIdHakmilikUrusan(String idHakmilikUrusan)throws Exception {
		    Db db = null;
		    String sql = "";
		    Vector list = new Vector();
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();	      
		      sql = "SELECT A.ID_HAKMILIKURUSAN, A.ID_PIHAKBERKEPENTINGAN, B.NAMA, B.NO_RUJUKAN, A.ID_HAKMILIKURUSANPB ";
		      sql += "FROM TBLHTPHAKMILIKURUSANPB A ";
		      sql += "LEFT JOIN TBLHTPPIHAKBERKEPENTINGAN B ";
		      sql += "on  A.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN  ";
		      sql += "WHERE A.ID_HAKMILIKURUSAN = '" + idHakmilikUrusan + "'";		      
		      log.info("getSenaraiPemilikbyIdHakmilikUrusan : " + sql);
			     
		      ResultSet rs = stmt.executeQuery(sql);		     
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("norujukan", rs.getString("NO_RUJUKAN")== null? "" : rs.getString("NO_RUJUKAN"));
		    	  h.put("nama",rs.getString("NAMA")== null? "" :rs.getString("NAMA"));	
		    	  h.put("idpihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	  h.put("idHakmilikurusan", rs.getString("ID_HAKMILIKURUSAN"));
		    	  h.put("idHakmilikurusanPB", rs.getString("ID_HAKMILIKURUSANPB"));
		    	  list.addElement(h);
		    	  
		      }
		      
		    } finally {
		      if (db != null) db.close();
		      
		    }
		    
		    return list;
		    
	  }
	  
	  public static Hashtable getHakmilikDetails(String idPermohonan)throws Exception{		  
		  Hashtable hakmilikInfo = null;
		  String sql = null;
		  Db db = null;
		  
		  try{
			  db = new Db();
			  Statement stmt = db.getStatement();			  
			  hakmilikInfo = new Hashtable();			  
			  sql = "SELECT A.ID_HAKMILIKURUSAN, A.ID_PERMOHONAN, A.ID_LUAS, A.NO_HAKMILIK,A.NO_BANGUNAN,A.NO_TINGKAT,A.NO_PETAK, ";
			  sql += "A.NO_LOT, A.LUAS, A.ID_SUBKATEGORI, A.ID_KATEGORI, A.ID_DAERAH, A.ID_NEGERI, ";
			  sql += "A.ID_MUKIM, A.ID_LOT, A.ID_JENISHAKMILIK ";
			  sql += "FROM TBLHTPHAKMILIKURUSAN A ";
			  sql += "WHERE A.ID_PERMOHONAN = '" + idPermohonan + "'";		  
			  //log.info("sql getHakmilikDetails : " + sql);			  
			  
			  ResultSet rs = stmt.executeQuery(sql);
			  while(rs.next()){
				  hakmilikInfo.put("idHakmilikUrusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN") );
				  hakmilikInfo.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN") );
				  hakmilikInfo.put("idLuas", rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS") );
				  hakmilikInfo.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK") );
				  hakmilikInfo.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT") );
				  hakmilikInfo.put("luas", rs.getString("LUAS") == null ? "" : rs.getString("LUAS") );
				  hakmilikInfo.put("idSubkategori", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI") );
				  hakmilikInfo.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI") );
				  hakmilikInfo.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH") );
				  hakmilikInfo.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI") );
				  hakmilikInfo.put("idMukim", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM") );
				  hakmilikInfo.put("idLot", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT") );
				  hakmilikInfo.put("noBangunan", rs.getString("NO_BANGUNAN") == null ? "" : rs.getString("NO_BANGUNAN") );
				  hakmilikInfo.put("noTingkat", rs.getString("NO_TINGKAT") == null ? "" : rs.getString("NO_TINGKAT") );
				  hakmilikInfo.put("noPetak", rs.getString("NO_PETAK") == null ? "" : rs.getString("NO_PETAK") );
				  hakmilikInfo.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK") );
			 
			  }
	  
		  }catch(Exception e){
			  e.printStackTrace();
			  
		  }
		  
		  finally{
			  
		  }
		  
		  return hakmilikInfo;
	  }
	  
	}