package ekptg.model.ppt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class PermohonanSek4Hakmilik {
	
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector list = new Vector();
	private static Vector listDokumen = new Vector();
	private static Vector listA = new Vector();
	private static Vector listB = new Vector();
	private static Vector listC = new Vector();
	private static Vector listD = new Vector();
	private static Vector listE = new Vector();
	private static Vector listF = new Vector();
	
	
	public Vector getListDokumen(){
		return listDokumen;
	}
	
	public static void setList(String carianFail, String CarianTarikhMohon, String carianStatusHakmilik)throws Exception {
		Db db = null;
	    list.clear();
	    String sql = "";
	    String chkData = carianFail.trim();
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f, Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status AND s.id_status IN (149,16)";
	      sql +=" AND su.id_suburusan = '51' ";
	      if(carianFail != null){
	    	  sql = sql + " AND UPPER(f.no_fail) LIKE '%'||'" + chkData.toUpperCase() + "'||'%'";  
	      }
//	      if(selectSubUrusanUPT != null){
//	    	  if (!selectSubUrusanUPT.trim().equals("")){
//	    		  if (!selectSubUrusanUPT.trim().equals("0")){
//	    			  sql +="AND f.id_suburusan = '" + selectSubUrusanUPT + "' ";
//	    		  }
//	    	  }	  
//	      }
	      if(carianStatusHakmilik != null){
	    	  if (!carianStatusHakmilik.trim().equals("")){
	    		  if (!carianStatusHakmilik.trim().equals("0")){
	    			  sql +="AND p.id_status = '" + carianStatusHakmilik + "' ";
	    		  }
	    	  }	  
	      }
	      if(CarianTarikhMohon != null){
	    	  if (!CarianTarikhMohon.toString().trim().equals("")){
	    	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	    	  sql +="AND p.tarikh_permohonan = '" + sdf.format(Format.parse(CarianTarikhMohon)).toUpperCase()+ "' ";
	    	  }
	      }	 
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
	    	 // h.put("id_hakmilik", rs.getString("id_hakmilik"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
	    	  h.put("id_fail", rs.getString("id_fail")==null?"-":rs.getString("id_fail"));
	    	  h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));    	
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
	    	  h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
	    	  if(rs.getString("no_fail") == null){
		    		h.put("no_fail","Belum Diluluskan");
		    	}else{
		    		h.put("no_fail",rs.getString("no_fail"));
		    	}
	    	  list.addElement(h);
	    	  bil++;
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getList(){
		  return list;
	  }	
	  
	  public static Vector getJenisKp()throws Exception {
		    Db db = null;
		    String sql = "Select id_jenisnopb,keterangan" +
			" from tblrujjenisnopb where id_jenisnopb in(4,5,6) ";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_jenisnopb");
		      r.add("keterangan");
		
		      //sql = r.getSQLSelect(sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id", rs.getString("id_jenisnopb"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }

	public static Vector getRecord(String id_fail, String id_permohonan) throws Exception {
		
	    Db db = null;
	    listA.clear();
	    String sql = "";
	    try {
	     
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT p.id_permohonan, f.id_negeri as id_projeknegeri, p.id_negeri, pn.nama_negeri as projek_negeri,p.no_permohonan, p.id_fail, f.no_fail, f.id_suburusan, ";
	    		sql += " p.tarikh_permohonan, p.id_status, f.id_kementerian, p.id_agensi, p.flag_peruntukan, p.flag_segera, ";
	    		sql += " p.id_daerah, p.tujuan, p.no_rujukan_surat, p.tarikh_kehendaki, p.alamat1, p.alamat2, p.alamat3, ";
	    		sql += " p.poskod, p.id_mukim, k.nama_kementerian, b.nama_daerah, p.no_rujukan_ptd, p.no_rujukan_ptg, ";
	    		sql += " p.no_rujukan_upt, su.nama_suburusan, s.keterangan, c.nama_negeri, p.tarikh_terima  ";
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujnegeri c, Tblrujdaerah b, Tblrujsuburusan su, Tblrujstatus s, ";
				sql += " Tblrujnegeri pn ";
				sql += " WHERE f.id_kementerian = k.id_kementerian ";  
				sql += " AND f.id_negeri = pn.id_negeri ";
				sql += " AND f.id_fail = p.id_fail "; 
				sql += " AND b.id_daerah = p.id_daerah ";  
				sql += " AND f.id_suburusan = su.id_suburusan ";  
				sql += " AND p.id_status = s.id_status "; 
				sql += " AND p.id_negeri = c.id_negeri "; 
				sql += " AND p.id_fail = '"+id_fail+"'" ; 
				sql += " AND p.id_permohonan = '"+id_permohonan+"'" ; 
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	      
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
	    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd"));
	    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg"));
	    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt"));
	    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
	    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
	    	h.put("projek_negeri", rs.getString("projek_negeri")==null?"-":rs.getString("projek_negeri"));    	
	    	h.put("id_suburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));	    	
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
	    	if(rs.getString("id_projeknegeri") == null){
	    		h.put("id_negeri_projek","");
	    	}else{
	    		h.put("id_negeri_projek",rs.getString("id_projeknegeri"));
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

	public static Vector getRecordHakmilik(int idhakmilik, int id_permohonanv) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Db db = null;
	    listB.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("A.ID_PERMOHONAN");
	      r.add("A.ID_NEGERI");
	      r.add("A.ID_DAERAH");
	      r.add("A.ID_MUKIM");
	      r.add("ID_JENISHAKMILIK");
	      r.add("A.NO_HAKMILIK");
	      r.add("A.NO_PT");
	      r.add("A.NO_LOT");
	      r.add("A.LUAS_LOT");
	      r.add("A.ID_UNITLUASAMBIL");
	      r.add("A.LUAS_AMBIL");
	      r.add("A.ID_UNITLUASBARU");       
	      
	      r.add("A.ID_HAKMILIK",r.unquote("B.ID_HAKMILIK"));
	      r.add("B.ID_HAKMILIK",r.unquote("C.ID_HAKMILIK"));
	      r.add("D.ID_PERMOHONAN",r.unquote("A.ID_PERMOHONAN"));
	      //r.add("b.id_daerah",r.unquote("p.id_daerah"));
	      //r.add("p.id_negeri",r.unquote("c.id_negeri"));
	      r.add("A.ID_HAKMILIK", idhakmilik, "=");
	      r.add("A.ID_PERMOHONAN", id_permohonanv, "=");

	      sql = r.getSQLSelect("TBLPPTPERMOHONAN D, TBLPPTHAKMILIK A, TBLPPTHAKMILIKPB B, TBLPPTPIHAKBERKEPENTINGAN C");
	      ResultSet rs = stmt.executeQuery(sql);

	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
	    	h.put("id_negeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
	    	h.put("id_daerah", rs.getString("id_daerah")==null?"-":rs.getString("id_daerah"));
	    	h.put("id_mukim", rs.getString("id_mukim")==null?"-":rs.getString("id_mukim"));
	    	h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"-":rs.getString("id_jenishakmilik"));
	    	h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
	    	h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
	    	h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
	    	h.put("luas_lot", rs.getString("luas_lot")==null?"-":rs.getString("luas_lot"));
	    	h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"-":rs.getString("id_unitluasambil"));
	    	h.put("luas_ambil", rs.getString("luas_ambil")==null?"-":rs.getString("luas_ambil"));
	    	h.put("id_unitluasbaru", rs.getString("id_unitluasbaru")==null?"-":rs.getString("id_unitluasbaru"));

	    	listB.addElement(h);
	    	bil++;
	      }
	      return listB;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}
	


	public static Vector getListHakmilik(String id_permohonan) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	     
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("a.id_hakmilik");
	      r.add("ls.keterangan");
	      r.add("a.no_hakmilik");
	      r.add("lt.keterangan");
	      r.add("a.no_lot");
	      r.add("a.no_pt");
	      r.add("a.id_mukim");
	      r.add("c.nama_mukim");
	      r.add("a.id_daerah");
	      r.add("d.nama_daerah");
	      r.add("a.luas_ambil");
	      r.add("a.luas_lot");
	      r.add("jn.id_jenishakmilik");
	      r.add("ne.id_negeri");
	      
	      r.add("a.id_permohonan",r.unquote("b.id_permohonan"));
	      r.add("a.id_mukim",r.unquote("c.id_mukim"));
	      r.add("a.id_lot",r.unquote("lt.id_lot"));
	      r.add("ls.id_luas",r.unquote("a.id_unitluaslot"));
	      r.add("a.id_daerah",r.unquote("d.id_daerah"));
	      r.add("a.id_negeri",r.unquote("ne.id_negeri"));
	      r.add("a.id_jenishakmilik",r.unquote("jn.id_jenishakmilik"));
	      //r.add("a.id_permohonan", id_permohonan, "=");

	      r.add("a.id_permohonan", id_permohonan);
	      
	      sql = r.getSQLSelect("Tblppthakmilik a, Tblrujlot lt, Tblrujluas ls, Tblrujnegeri ne, Tblpptpermohonan b, Tblrujjenishakmilik jn, Tblrujmukim c, Tblrujdaerah d ");
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      
	      Vector listC = new Vector();
	      Hashtable h= null;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);    
	    	h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"-":rs.getString("id_hakmilik"));
	    	h.put("unit_luas", rs.getString(2)==null?"-":rs.getString(2));
	    	h.put("kod_lot", rs.getString(4)==null?"-":rs.getString(4));
	    	h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
	    	h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
	    	h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
	    	h.put("id_mukim", rs.getString("id_mukim")==null?"-":rs.getString("id_mukim"));
	    	h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
	    	h.put("id_daerah", rs.getString("id_daerah")==null?"-":rs.getString("id_daerah"));
	    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
	    	h.put("luas_ambil", rs.getString("luas_ambil")==null?"-":rs.getString("luas_ambil"));
	    	h.put("luas_lot", rs.getString("luas_lot")==null?"-":rs.getString("luas_lot"));
	    	h.put("id_jenishakmilik", rs.getString("id_jenishakmilik"));
	    	h.put("id_negeri", rs.getString("id_negeri"));
	    	
	    	listC.addElement(h);
	    	bil++;
	      }
	      return listC;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}

	public static Vector getRecordHM(int id_hakmilik) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Db db = null;
	    listD.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("A.ID_HAKMILIK");
	      r.add("D.id_status");
	      r.add("A.ID_PERMOHONAN");
	      r.add("A.ID_NEGERI");
	      r.add("E.NAMA_NEGERI");
	      r.add("A.ID_DAERAH"); 
	      r.add("F.NAMA_DAERAH");
	      r.add("A.ID_MUKIM");
	      r.add("G.NAMA_MUKIM");
	      r.add("A.ID_JENISHAKMILIK");
	      r.add("A.NO_HAKMILIK");
	      r.add("A.NO_PT");
	      r.add("A.NO_LOT");
	      r.add("A.LUAS_LOT");
	      r.add("A.ID_UNITLUASLOT");   
	      r.add("H.KETERANGAN");
	      r.add("A.LUAS_AMBIL");
	      //r.add("A.ID_UNITLUASBARU");
	      r.add("B.KETERANGAN");
	      r.add("A.id_lot");
	      
	      r.add("D.ID_PERMOHONAN",r.unquote("A.ID_PERMOHONAN"));
	      r.add("A.ID_NEGERI",r.unquote("E.ID_NEGERI"));
	      r.add("A.ID_DAERAH",r.unquote("F.ID_DAERAH"));
	      r.add("A.ID_MUKIM",r.unquote("G.ID_MUKIM"));
	      r.add("A.ID_UNITLUASLOT",r.unquote("H.ID_LUAS"));
	      r.add("A.ID_JENISHAKMILIK",r.unquote("B.ID_JENISHAKMILIK"));
	      r.add("A.ID_HAKMILIK", id_hakmilik, "=");	  
	      
	      sql = r.getSQLSelect("TBLPPTPERMOHONAN D, TBLPPTHAKMILIK A, TBLRUJNEGERI E, TBLRUJDAERAH F, TBLRUJMUKIM G, TBLRUJLUAS H, TBLRUJJENISHAKMILIK B ");
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
	        h.put("id_lot", rs.getString("id_lot")==null?"":rs.getString("id_lot"));
	        h.put("id_status", rs.getString("id_status"));
	        h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
	        h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
	    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
	    	h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
	    	h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
	    	h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
	    	h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
	    	h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
	    	h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
	    	h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
	    	h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getString("luas_lot"));
	    	h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
	    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	h.put("luas_ambil", rs.getString("luas_ambil")==null?"":rs.getString("luas_ambil"));
	    	//h.put("id_unitluasbaru", rs.getString("id_unitluasbaru")==null?"":rs.getString("id_unitluasbaru"));
	    	h.put("keteranganHM", rs.getString(17)==null?"":rs.getString(17));

	    	listD.addElement(h);
	    	bil++;
	      }
	      return listD;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}

	public static Vector getRecordPB(int id_hakmilik) throws Exception {
	    Db db = null;	   
	    String sql = "";
	    try {

	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("a.id_pihakberkepentingan");
	      r.add("a.id_hakmilik");
	      r.add("a.id_jenispb");
	      r.add("a.id_jenisnopb");
	      r.add("a.no_pb");
	      r.add("a.id_bangsa"); 
	      r.add("a.id_warganegara");
	      r.add("a.nama_pb");  
	      r.add("a.syer_atas"); 
	      r.add("c.keterangan"); //tblrujjenispb
	      r.add("d.keterangan"); //tblrujjenisnopb
	      r.add("a.syer_bawah");

	      r.add("a.id_hakmilik",r.unquote("b.id_hakmilik"));
	      r.add("a.id_jenispb",r.unquote("c.id_jenispb"));
	      r.add("a.id_jenisnopb",r.unquote("d.id_jenisnopb"));
	      r.add("a.id_hakmilik", id_hakmilik, "=");	  
	      
	      sql = r.getSQLSelect("tblpptpihakberkepentingan a, tblppthakmilik b, tblrujjenispb c, tblrujjenisnopb d");
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();

	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan")==null?"-":rs.getString("id_pihakberkepentingan"));
	        h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"-":rs.getString("id_hakmilik"));
	        h.put("id_jenispb", rs.getString("id_jenispb")==null?"-":rs.getString("id_jenispb"));
	        h.put("id_jenisnopb", rs.getString("id_jenisnopb")==null?"-":rs.getString("id_jenisnopb"));
	    	h.put("no_pb", rs.getString("no_pb")==null?"-":rs.getString("no_pb"));
	    	h.put("id_bangsa", rs.getString("id_bangsa")==null?"-":rs.getString("id_bangsa"));
	    	h.put("id_warganegara", rs.getString("id_warganegara")==null?"-":rs.getString("id_warganegara"));
	    	h.put("nama_pb", rs.getString("nama_pb")==null?"-":rs.getString("nama_pb"));
	    	h.put("syer_atas", rs.getString("syer_atas")==null?"-":rs.getString("syer_atas"));
	    	h.put("keteranganjenispb", rs.getString(10)==null?"-":rs.getString(10));
	    	h.put("keteranganjenisnopb", rs.getString(11)==null?"-":rs.getString(11));
	    	h.put("syer_bawah", rs.getString("syer_bawah")==null?"-":rs.getString("syer_bawah"));

	    	list.addElement(h);
	    	bil++;
	      }
	      return list;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}

	public static Vector getRecordDetailPB(int id_pihakberkepentingan) throws Exception  {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Db db = null;
	    listF.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("a.id_pihakberkepentingan");
	      r.add("a.id_hakmilik");
	      r.add("a.id_jenispb");
	      r.add("a.id_jenisnopb");
	      r.add("a.no_pb");
	      r.add("a.id_bangsa"); 
	      r.add("a.id_warganegara");
	      r.add("b.keterangan");
	      r.add("c.keterangan");
	      r.add("d.keterangan");
	      r.add("a.nama_pb");
	      r.add("a.syer_atas");
	      r.add("a.syer_bawah");
	      r.add("e.keterangan");
	      r.add("a.alamat1");
	      r.add("a.alamat2");
	      r.add("a.alamat3");
	      r.add("a.poskod");
	      r.add("a.id_negeri");
       
	      r.add("a.id_jenispb ",r.unquote("c.id_jenispb"));
	      r.add("a.id_jenisnopb ",r.unquote("d.id_jenisnopb"));
	      r.add("a.id_warganegara ",r.unquote("b.id_warganegara"));
	      r.add("a.id_bangsa ",r.unquote("e.id_bangsa"));
	      r.add("a.id_pihakberkepentingan", id_pihakberkepentingan, "=");	  
	      
	      sql = r.getSQLSelect("Tblpptpihakberkepentingan a, Tblrujwarganegara b, Tblrujjenispb c, Tblrujjenisnopb d, Tblrujbangsa e");
	      ResultSet rs = stmt.executeQuery(sql);
	      

	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan")==null?"-":rs.getString("id_pihakberkepentingan"));
	        h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"-":rs.getString("id_hakmilik"));
	        h.put("id_negeri", rs.getString("id_negeri")==null?0:rs.getString("id_negeri"));
	        h.put("alamat1", rs.getString("alamat1")==null?"-":rs.getString("alamat1"));
	        h.put("alamat2", rs.getString("alamat2")==null?"-":rs.getString("alamat2"));
	        h.put("alamat3", rs.getString("alamat3")==null?"-":rs.getString("alamat3"));
	        h.put("poskod", rs.getString("poskod")==null?"-":rs.getString("poskod"));
	        h.put("id_jenispb", rs.getString("id_jenispb")==null?"-":rs.getString("id_jenispb"));
	        h.put("id_jenisnopb", rs.getString("id_jenisnopb")==null?"-":rs.getString("id_jenisnopb"));
	    	h.put("no_pb", rs.getString("no_pb")==null?"-":rs.getString("no_pb"));
	    	h.put("id_bangsa", rs.getString("id_bangsa")==null?"-":rs.getString("id_bangsa"));
	    	h.put("id_warganegara", rs.getString("id_warganegara")==null?"-":rs.getString("id_warganegara"));
	    	h.put("keterangan_warga", rs.getString(8)==null?"-":rs.getString(8));
	    	h.put("keterangan_jenispb", rs.getString(9)==null?"-":rs.getString(9));
	    	h.put("keterangan_jenisnopb", rs.getString(10)==null?"-":rs.getString(10));
	    	h.put("nama_pb", rs.getString("nama_pb")==null?"-":rs.getString("nama_pb"));
	    	h.put("syer_atas", rs.getString("syer_atas")==null?"-":rs.getString("syer_atas"));
	    	h.put("syer_bawah", rs.getString("syer_bawah")==null?"-":rs.getString("syer_bawah"));
	    	h.put("keterangan_bangsa", rs.getString(14)==null?"-":rs.getString(14));
	    	
	    	listF.addElement(h);
	    	bil++;
	      }
	      return listF;
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
	      Vector list = new Vector();
	      
	      Hashtable h = null;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("nama_agensi",rs.getString("nama_agensi"));	    	 
	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	//view list dokumen by id
	 public static void  setListDokumen(String id)throws Exception {
		    Db db = null;
		    listDokumen.clear();
		    String sql = "";
		    
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_permohonan");
		      r.add("a.id_Dokumen");
		      r.add("a.nama_Fail");
		      r.add("a.jenis_Mime");
		      r.add("a.tajuk");
		      r.add("a.keterangan");
		      r.add("a.content");
		      
		      r.add("p.id_permohonan",id);
		      r.add("p.id_permohonan",r.unquote("a.id_permohonan"));
		
		      sql = r.getSQLSelect("Tblpptdokumen a, tblpptpermohonan p");
		     
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      int bil = 1;
		    
		      while (rs.next()) {
		    	  
		    	  h = new Hashtable();
		    	 
		    	  h.put("bil", bil);
		    	  h.put("id_permohonan",rs.getInt("id_permohonan"));
		    	  h.put("id_Dokumen", rs.getString("id_Dokumen")== null?"":rs.getString("id_Dokumen"));
		    	  h.put("nama_Fail", rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime",rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  h.put("tajuk",rs.getString("tajuk")== null?"":rs.getString("tajuk"));
		    	  h.put("keterangan",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		          
		    	  listDokumen.addElement(h);
		    	  bil++;	    	
		      }			    
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	
}//close main class
