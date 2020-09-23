package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmJRPSenaraiPermohonanData {

	static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmJRPSenaraiPermohonanData.class);
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Vector getList(String fail)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("p.id_Permohonan");
	      r.add("f.no_Fail");
	      r.add("s.keterangan");
	      r.add("p.id_Fail = f.id_Fail");
	      r.add("p.id_Permohonan = sf.id_Permohonan");
	      r.add("p.id_Fail=sf.id_Fail");
	      /*r.add("p.id_Permohonan");
	      r.add("f.no_Fail");
	      r.add("p.tujuan");
	      r.add("s.keterangan");
	      
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
	      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
	      r.add("us.id_Status",r.unquote("s.id_Status"));

	      r.add("f.id_Urusan","309");
	      r.add("p.id_Fail",fail);
	      r.add("sf.aktif","IN (1,2)",,);
	      sql = r.getSQLSelect("p.id_Permohonanp, f.no_Fail, p.tujuan, s.keterangan" +
	      		"Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s");
		  */
	      sql= "SELECT p.id_Permohonan, f.no_Fail, UPPER(p.tujuan) TUJUAN, s.keterangan,RN.NAMA_NEGERI "+
	      	" FROM Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s,TBLRUJNEGERI RN "+
	      	" WHERE p.id_Fail = f.id_Fail  AND p.id_Permohonan = sf.id_Permohonan  AND "+
	      	" p.id_Fail=sf.id_Fail AND sf.id_Suburusanstatus = us.id_Suburusanstatus AND us.id_Status = s.id_Status  "+
	      	" AND f.id_Urusan =14 AND p.id_Fail ="+fail+" " +
	      	" AND F.ID_NEGERI=RN.ID_NEGERI " +
	      	" AND sf.aktif IN (1,2)";
	      //System.out.println("FrmPajakanKecilSenaraiPermohonanData:sql::"+sql);   
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("id", Utils.isNull(rs.getString("id_Permohonan")));
	    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public static Vector getListOnline(String fail)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("p.id_Permohonan");
	      r.add("f.no_Fail");
	      r.add("s.keterangan");
	      r.add("p.id_Fail = f.id_Fail");
	      r.add("p.id_Permohonan = sf.id_Permohonan");
	      r.add("p.id_Fail=sf.id_Fail");
	      r.add("P.no_Permohonan ");
	      /*r.add("f.no_Fail");
	      r.add("p.tujuan");
	      r.add("s.keterangan");
	      
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
	      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
	      r.add("us.id_Status",r.unquote("s.id_Status"));

	      r.add("f.id_Urusan","309");
	      r.add("p.id_Fail",fail);
	      r.add("sf.aktif","IN (1,2)",,);
	      sql = r.getSQLSelect("p.id_Permohonanp, f.no_Fail, p.tujuan, s.keterangan" +
	      		"Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s");
		  */
	      sql= "SELECT p.id_Permohonan, f.no_Fail, p.tujuan, s.keterangan,RN.NAMA_NEGERI,P.no_Permohonan  "+
	      	" FROM Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s,TBLRUJNEGERI RN "+
	      	" WHERE p.id_Fail = f.id_Fail  AND p.id_Permohonan = sf.id_Permohonan  AND "+
	      	" p.id_Fail=sf.id_Fail AND sf.id_Suburusanstatus = us.id_Suburusanstatus AND us.id_Status = s.id_Status  "+
	      	" AND f.id_Urusan =14 AND p.id_Fail ="+fail+" " +
	      			" AND F.ID_NEGERI=RN.ID_NEGERI " +
	      			" AND sf.aktif IN (1,2)";
		  
	      
	      System.out.println("FrmPajakanKecilSenaraiPermohonanData:sql::"+sql);
     
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("id", Utils.isNull(rs.getString("id_Permohonan")));
	    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    	  /*h.put("noP", Utils.isNull(rs.getString("no_Permohonan")));*/
	    	  h.put("tajuk", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	 
	 public static Vector getPermohonanHTP(String fail)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      /*r.add("p.id_Permohonan");
		      r.add("f.no_Fail");
		      r.add("p.tujuan");
		      r.add("s.keterangan");
		      
		      r.add("p.id_Fail",r.unquote("f.id_Fail"));
		      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
		      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
		      r.add("us.id_Status",r.unquote("s.id_Status"));

		      r.add("f.id_Urusan","309");
		      r.add("p.id_Fail",fail);
		      r.add("sf.aktif","IN (1,2)",,);
		      sql = r.getSQLSelect("p.id_Permohonanp, f.no_Fail, p.tujuan, s.keterangan" +
		      		"Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s");
			  */
		      sql= " SELECT p.id_Permohonan, f.no_Fail, p.tujuan,UPPER(F.TAJUK_FAIL) TAJUK_FAIL, s.keterangan,h.id_agensi  "+
		      	" FROM Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us," +
		      	" Tblrujstatus s, tblhtppermohonan h "+
		      	" WHERE h.id_permohonan=p.id_permohonan and " +
		      	" p.id_Fail = f.id_Fail  AND p.id_Permohonan = sf.id_Permohonan  AND "+
		      	" sf.id_Suburusanstatus = us.id_Suburusanstatus AND us.id_Status = s.id_Status  "+
		      	" AND f.id_Urusan = 14  AND p.id_Fail = "+fail+"  AND sf.aktif IN (1,2)";
			  //System.out.println("FrmPajakanKecilSenaraiPermohonanData:sql::"+sql);
	     
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id", rs.getString("id_Permohonan"));
		    	  h.put("no", rs.getString("no_Fail"));
		    	  h.put("tajuk", rs.getString("TAJUK_FAIL"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  h.put("idagensi", rs.getString("id_Agensi"));
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Hashtable getPermohonanInfo(String idpermohonan)throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
		     Statement stmt = db.getStatement();		      
		     sql= " " +
		     	"SELECT s.id_suburusan, p.no_permohonan,trm.nama_mukim, trd.id_daerah, trd.kod_daerah, trd.NAMA_DAERAH, n.nama_negeri,k.nama_kementerian,a.nama_agensi, "+
			    " S.nama_suburusan,f.no_fail,to_char(NVL(f.tarikh_daftar_fail,sysdate),'dd/mm/yyyy') tarikh_daftar_fail ,h.no_rujukan_kjp, "+
			    " H.no_rujukan_lain,to_char(NVL(p.tarikh_surat,sysdate),'dd/mm/yyyy') tarikh_surat,to_char(NVL(p.tarikh_terima,sysdate),'dd/mm/yyyy') tarikh_terima,p.tujuan," +
			    " P.ID_PERMOHONAN,F.id_fail,K.id_kementerian,A.id_agensi, "+
				" N.id_negeri,F.id_tarafkeselamatan, UPPER(F.TAJUK_FAIL) TAJUK_FAIL " +
				" FROM TBLPERMOHONAN P,TBLHTPPERMOHONAN H,tblrujnegeri n,Tblpfdfail f" +
				" ,tblrujkementerian K,tblrujdaerah TRD,tblrujmukim TRM"+
				" ,tblrujagensi A,tblrujsuburusan S " +
				" WHERE trm.id_daerah=trd.id_daerah and trd.id_negeri=n.ID_NEGERI " +
				" AND F.ID_NEGERI=N.ID_NEGERI AND P.ID_FAIL=f.ID_FAIL AND p.id_permohonan=h.ID_PERMOHONAN " +
				" AND H.ID_AGENSI=A.id_agensi AND F.ID_kementerian=K.id_kementerian " +
				" AND f.ID_SUBURUSAN=s.ID_SUBURUSAN " +
				" AND P.ID_PERMOHONAN='"+idpermohonan+"'";
		      myLog.info("getPermohonanInfo("+idpermohonan+"):SQL test = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      while (rs.next()) {
		    	  if(rs.getString("nama_negeri")==null){
		    		  h.put("negeri", "");
		      		}else{
		      			h.put("negeri", rs.getString("nama_negeri")); }
		    	  if(rs.getString("nama_kementerian")==null){
		    		  h.put("kementerian", "");
		    	  }else{
		    		  h.put("kementerian", rs.getString("nama_kementerian"));}
		    	  if(rs.getString("nama_agensi")==null){
		    		  h.put("agensi", "");
		    	  }else {
		    		  h.put("agensi", rs.getString("nama_agensi"));
		    	  }
		    	  if(rs.getString("nama_suburusan")==null){
		    		  h.put("suburusan", "");
		    	  }else{
		    		  h.put("suburusan", rs.getString("nama_suburusan"));
		    	  }
		    	  if(rs.getString("no_fail")==null){
		    		  h.put("fail", "");
		    	  }else{
		    		  h.put("fail", rs.getString("no_fail"));
		    	  }
		    	 /* if(rs.getString("no_permohonan")==null){
		    		  h.put("noP",new Date());
		    	  }else{
		    		  h.put("noP", rs.getString("no_permohonan"));
		    	  }*/
		    	  if(rs.getString("tarikh_daftar_fail")==null){
		    		  h.put("tdaftar",new Date());
		    	  }else{
		    		  h.put("tdaftar", rs.getString("tarikh_daftar_fail"));
		    	  }
		    	  /*if(rs.getString("no_rujukan_kjp")==null){
		    		  h.put("rujukankjp", "");
		    	  }else{
		    		  h.put("rujukankjp", rs.getString("no_rujukan_kjp"));
		    	  }*/
		    	  if(rs.getString("no_rujukan_lain")==null){
		    		  h.put("rujukanlain", "");
		    	  }else{
		    		  h.put("rujukanlain", rs.getString("no_rujukan_lain"));
		    	  }
		    	  if(rs.getString("tarikh_surat")==null){
		    		  h.put("tsurat",new Date());
		    	  }else{
		    		  h.put("tsurat", rs.getString("tarikh_surat"));
		    	  }
		    	  if(rs.getString("tarikh_terima")==null){
		    		  h.put("rtterima","");
		    	  }else{
		    		  h.put("rtterima", rs.getString("tarikh_terima"));
		    	  }
		    	  if(rs.getString("tujuan")==null){
		    		  h.put("tujuan", "");
		    	  }else{
		    		  h.put("tujuan", rs.getString("TAJUK_FAIL"));
		    	  }
		    	  if(rs.getString("id_permohonan")==null){
		    		  h.put("idpermohonan", "");
		    	  }else{
		    		  h.put("idpermohonan", rs.getString("id_permohonan"));
		    	  }
		    	  if(rs.getString("id_suburusan")==null){
		    		  h.put("idsuburusan", "");
		    	  }else{
		    		  h.put("idsuburusan", rs.getString("id_suburusan"));
		    	  }
		    	  if(rs.getString("id_fail")==null){
		    		  h.put("idfail", "");
		    	  }else{
		    		  h.put("idfail", rs.getString("id_fail"));
		    	  }	 
	    		  h.put("idagensi", Utils.isNull(rs.getString("id_agensi")));
		    	  if(rs.getString("id_agensi")==null){
		    		  h.put("idagensi", "0");
		    	  }else{
		    		  h.put("idagensi", rs.getString("id_agensi"));
		    	  }
	    		  h.put("idkementerian", Utils.isNull(rs.getString("id_kementerian")));
	    		  h.put("idnegeri", Utils.isNull(rs.getString("id_negeri")));
	    		  h.put("iddaerah", Utils.isNull(rs.getString("id_daerah")));
	    		  h.put("idtaraf", Utils.isNull(rs.getString("id_tarafkeselamatan")));
		    	  if(rs.getString("TAJUK_FAIL")==null){
		    		  h.put("tajukfail", "");
		    	  }else{
		    		  h.put("tajukfail", rs.getString("TAJUK_FAIL"));
		    	  }
	    		 // h.put("namamukim", rs.getString("nama_mukim"));
		    	  //list.addElement(h);
		    }
		    return h;
		      
		 }catch(SQLException sqlE){
		    	throw new Exception("[002] ERROR IN RETRIEVING DATA "+sqlE.getMessage());
		 
		 }finally {
		      if (db != null) db.close();
		 }
	 
	 }
	 
	 public Vector getIdNegeriKJPByUserId(String userId) throws Exception {
			Db db = null;
			String sql = "";
			Hashtable h;
			Vector listDetailKJP = new Vector();

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT A.USER_ID, A.USER_NAME, C.ID_NEGERI, B.ID_KEMENTERIAN, B.ID_AGENSI FROM USERS A, USERS_KEMENTERIAN B, TBLRUJAGENSI C, TBLRUJKEMENTERIAN D "
						+ " WHERE A.USER_ID = B.USER_ID AND B.ID_AGENSI = C.ID_AGENSI AND B.ID_KEMENTERIAN = D.ID_KEMENTERIAN AND A.USER_ID = '"
						+ userId + "'";

				ResultSet rs = stmt.executeQuery(sql);
				myLog.info("listDetailKJP:: "+sql);

				if (rs.next()) {
					h = new Hashtable();
					h.put("userId", rs.getString("USER_ID").toString());
					h.put("idNegeri", rs.getString("ID_NEGERI").toString());
					h.put("idKementerian", rs.getString("ID_KEMENTERIAN").toString());
					h.put("idAgensi", rs.getString("ID_AGENSI").toString());
					h.put("namaPemohon", rs.getString("USER_NAME").toString());
					listDetailKJP.addElement(h);

					return listDetailKJP;
				} else {
					return listDetailKJP;
				}

			} finally {
				if (db != null)
					db.close();
			}
		}


	  public static void add(String kod_cara_bayar, String keterangan, Long id_masuk, 
			  Date tarikh_masuk) throws Exception {
	    Db db = null;
	    String sql = "";
	    if(tarikh_masuk==null){
	    	tarikh_masuk = new Date(); }
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      //r.add("id_carabayar", UniqueStringId.get());
	      r.add("kod_cara_bayar", kod_cara_bayar);
	      r.add("keterangan", keterangan);
	      r.add("id_masuk", id_masuk);
	      r.add("tarikh_masuk", tarikh_masuk);
	      r.add("id_kemaskini", id_masuk);
	      r.add("tarikh_kemaskini", tarikh_masuk);
	      sql = r.getSQLInsert("tblrujcarabayar");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }


	  public static void update(Long id_carabayar, String kod_cara_bayar, 
			  String keterangan, Long id_kemaskini, Date tarikhKemaskini) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.clear();

	      r.update("id_carabayar", ""+id_carabayar);
	      r.add("kod_cara_bayar", kod_cara_bayar);
	      r.add("keterangan", keterangan);
	      r.add("id_kemaskini", id_kemaskini);
	      r.add("tarikh_kemaskini", tarikhKemaskini);
	      sql = r.getSQLUpdate("tblrujcarabayar");
	      stmt.executeUpdate(sql);
	    
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String id_carabayar)
	    throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      //boolean found = false;
	      //sql = "select id_carabayar from faculty_subject where faculty_id = '" + id_carabayar + "'";
	      //ResultSet rs = stmt.executeQuery(sql);
	      //if (rs.next()) found = true;
	      //if (found)
	      sql = "delete from tblrujcarabayar where id_carabayar = " + id_carabayar;
	      stmt.executeUpdate(sql);
	    }
	    finally
	    {
	      if (db != null) db.close();
	    }
	  }
	 
	  public static Hashtable getFailInfo(String idFail) throws Exception{
		  Db db = null;
		  String sql = null;
		  Hashtable h = new Hashtable();		  		  
		  try{
			  db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, C.ID_HTPPERMOHONAN, A.NO_FAIL, ";
		      sql += "UPPER(A.TAJUK_FAIL) TAJUK_FAIL, B.TARIKH_SURAT, C.NO_RUJUKAN_LAIN, A.TARIKH_DAFTAR_FAIL,  ";
		      sql += "A.ID_KEMENTERIAN,  A.ID_NEGERI, C.NO_RUJUKAN_KJP, C.ID_AGENSI, ";
		      sql += "A.ID_SUBURUSAN , B.TARIKH_TERIMA, B.TUJUAN  ";
		      sql += "FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C ";
		      sql += "WHERE A.ID_FAIL = B.ID_FAIL ";
		      sql += "AND A.ID_FAIL = '" + idFail + "'";
		      sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
		      myLog.info("getFailInfo : sql=" + sql);	      
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  h.put("idfail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
		    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
		    	  h.put("idHTPPermohonan", rs.getString("ID_HTPPERMOHONAN") == null ? "" : rs.getString("ID_HTPPERMOHONAN"));
		    	  h.put("fail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
		    	  h.put("tajukfail", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
		    	  h.put("tsurat", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
		    	  h.put("rujukanlain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN"));
		    	  h.put("tdaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
		    	  h.put("idkementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
		    	  h.put("idnegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
/*		    	  h.put("rujukankjp", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP"));
*/		    	  h.put("idagensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));		    	 
		    	  h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
		    	  h.put("rtterima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
		    	  h.put("tujuan", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
	  
		      }
			  
		  }catch(Exception e){
			  e.printStackTrace();
		  } finally{
			  db.close();
		  }
		  return h;
		  
	  }
	  
	  
	  
	}