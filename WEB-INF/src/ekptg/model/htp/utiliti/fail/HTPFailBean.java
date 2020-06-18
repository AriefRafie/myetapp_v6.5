package ekptg.model.htp.utiliti.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class HTPFailBean implements IHTPFail{
	
 	private IHtp iHTP = null;  
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.fail.HTPFailBean.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String sql = "";
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;

	@Override
	public  Vector<Hashtable<String, String>> getSenaraiFail(String idUser
		,String nofail,String tajukfail
		,String id_kementerian,String id_agensi
		,String id_negeri,String id_daerah,String id_mukim
		,String id_urusan
		,String tarikhBukaFail) throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    boolean isSearch = false;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "SELECT f.id_masuk,p.id_Fail, f.no_Fail, f.tajuk_Fail,F.tarikh_daftar_fail" +
	      		" ,TO_CHAR(F.tarikh_daftar_fail,'dd/MM/yyyy') tarikh_daftar,f.id_kemaskini,F.tarikh_masuk  " +
	      		" ,P.id_permohonan,P.no_permohonan, P.tujuan " +
	      		" ,rn.nama_negeri negeri"+
	      		" ,S.keterangan "+
	      		" FROM tblpermohonan P,tblhtppermohonan PP, "+
			    " tblpfdfail F,tblrujsuburusanstatusfail SF," +
			    //"Tblhtphakmilikurusan THMU," +
			    " tblrujsuburusanstatus US,tblrujstatus S,tblrujnegeri rn "+
			    " WHERE P.id_Fail = F.id_Fail  " +
			    " AND F.id_negeri=rn.id_negeri " +
			    " AND P.id_permohonan = PP.id_permohonan "+
			    " AND P.id_permohonan = SF.id_permohonan  "+
			    //" AND P.id_Permohonan = THMU.id_Permohonan(+)  "+
			    " AND SF.id_suburusanstatus = US.id_suburusanstatus  "+
			    " AND SF.id_fail = F.id_fail "+
			    " AND US.id_status = S.id_status  " +
			    //"AND F.id_Urusan IN (1,10)  "
			    " AND ( F.id_status <> '999' OR F.id_status is null) " +
			    "AND sf.aktif = '1'  ";
	      
	      if (idUser != null) {
	    	  sql = sql + " AND f.id_masuk='"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + " AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + "AND f.id_urusan = "+id_urusan+" ";
	      }
	      
	      if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + " AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      }
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }
	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }
	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  //by rosli 24/02/2011
	    	  //sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	    	  sql = sql + " AND P.ID_PERMOHONAN IN ( "+
	    	  	" SELECT THMUI.ID_PERMOHONAN FROM Tblhtphakmilikurusan THMUI "+
	    		" WHERE THMUI.ID_MUKIM = '"+id_mukim+"')"; 
	      }

	      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
	    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
	      }
	      
	      if (!isSearch) {
	    	  //sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      //sql = sql + "ORDER BY F.TARIKH_DAFTAR_FAIL DESC";
	      sql = sql + "ORDER BY P.ID_FAIL DESC" +
	      "" ;
	     // 		"--,TARIKH_DAFTAR DESC";

	      myLog.info("getSenaraiFail:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String, String>>();
	      int bil = 1;
	      Hashtable<String, String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("id", rs.getString("id_fail"));
	    	  h.put("no", Utils.isNull(rs.getString("no_fail")));
	    	  h.put("noP", Utils.isNull(rs.getString("no_permohonan")));
	    	  h.put("idPermohonan", Utils.isNull(rs.getString("id_permohonan")));
	    	  h.put("negeri", Utils.isNull(rs.getString("negeri")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("tarikh_daftar")));
	    	  list.addElement(h);
	    	  bil++;
	    	  
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	      
	    }
	    
	  }	
	@Override
	public  Vector<Hashtable<String, String>> getSenaraiFail(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhBukaFail,boolean isSearch)throws Exception {
	    Db db = null;
	    Vector<Hashtable<String, String>> list = null;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "SELECT F.ID_MASUK, F.ID_FAIL, F.NO_FAIL, UPPER(F.TAJUK_FAIL) TAJUK_FAIL, F.TARIKH_DAFTAR_FAIL" +
	      		" ,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR,F.ID_KEMASKINI,F.TARIKH_MASUK  " +
	      		" ,P.NO_PERMOHONAN, P.TUJUAN, " +
	      		" (select nama_negeri from tblrujnegeri where id_negeri = f.id_negeri) NEGERI "+
	      		" ,NVL(( "+
	      		" 	    SELECT SI.KETERANGAN  "+
	      		" 	    FROM TBLRUJSTATUS SI, TBLRUJSUBURUSANSTATUS RSUSI, TBLRUJSUBURUSANSTATUSFAIL RSUSFI "+
	      		" 	    WHERE SI.ID_STATUS = RSUSI.ID_STATUS "+
	      		" 	    AND RSUSI.ID_SUBURUSANSTATUS = RSUSFI.ID_SUBURUSANSTATUS "+
	      		" 	    AND RSUSFI.AKTIF = '1' "+
	      		" 	    AND RSUSFI.ID_FAIL =P.ID_FAIL  AND RSUSFI.ID_PERMOHONAN=P.ID_PERMOHONAN "+
	      		" 	),'TIADA MAKLUMAT') KETERANGAN "+
	      		" FROM TBLPFDFAIL F, TBLPERMOHONAN P,TBLHTPPERMOHONAN PP "+
			    " WHERE P.ID_FAIL = F.ID_FAIL  " +
			    " AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
			    " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) " +
			    "";
	      
	      if (idUser != null) {
	    	  sql = sql + " AND F.ID_MASUK = '"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + " AND LOWER(F.NO_FAIL) like '%"+nofail.toLowerCase()+"%' ";
	    	  //isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + " AND F.ID_URUSAN = "+id_urusan+" ";
	      }
	      
	      //if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + " AND LOWER(F.TAJUK_FAIL) like '%"+tajukfail.toLowerCase()+"%' ";
	      //}
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + " AND F.ID_KEMENTERIAN = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + " AND PP.ID_AGENSI = '"+id_agensi+"' ";
	      }
	      
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + " AND F.ID_NEGERI = '"+id_negeri+"' ";
	      }	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + " AND PP.ID_DAERAH = '"+id_daerah+"' ";
	      }	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  //sql = sql + " AND thmu.id_mukim = '"+id_mukim+"' ";
	    	  sql = sql + " AND P.ID_PERMOHONAN IN ( "+
	    	  	" SELECT THMUI.ID_PERMOHONAN FROM TBLHTPHAKMILIKURUSAN THMUI "+
	    		" WHERE THMUI.ID_MUKIM = '"+id_mukim+"')"; 
	      }

	      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
	    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
	      }
	      
	      if (!isSearch) {
	    	  sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      //sql = sql + "ORDER BY F.TARIKH_DAFTAR_FAIL DESC";
	      sql = sql + " ORDER BY P.ID_FAIL DESC" +
	      "" ;
	     // 		"--,TARIKH_DAFTAR DESC";

	      myLog.info("getSenaraiFail:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String, String>>();
	      int bil = 1;
	      Hashtable<String, String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("id", rs.getString("id_Fail"));
	    	  //options Penswastaan
	    	  h.put("idFail", rs.getString("id_Fail"));
	    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
	    	  //options Penswastaan
	    	  h.put("noFail", Utils.isNull(rs.getString("NO_FAIL")));	    	  
	    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
	    	  //options Penswastaan
	    	  h.put("status", Utils.isNull(rs.getString("KETERANGAN")));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  list.addElement(h);
	    	  bil++;
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	      
	    }
	  }	
	
	@Override
	public  Vector<Hashtable<String, String>> getSenaraiFail(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhTerima,String tarikhBukaFail
			,String status, String pemohon, boolean isSearch )
			throws Exception {
	    Db db = null;
	    Vector<Hashtable<String, String>> list = null;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "SELECT F.ID_MASUK, F.ID_FAIL, F.NO_FAIL, UPPER(F.TAJUK_FAIL) TAJUK_FAIL, F.TARIKH_DAFTAR_FAIL" +
	      		" ,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR,F.ID_KEMASKINI,F.TARIKH_MASUK  " +
	      		" ,P.ID_PERMOHONAN, P.NO_PERMOHONAN, P.TUJUAN, P.TARIKH_TERIMA, P.ID_STATUS " +
	      		" ,(select nama_negeri from tblrujnegeri where id_negeri = f.id_negeri) NEGERI "+
	      		" ,NVL( SI.KETERANGAN ,'TIADA MAKLUMAT') KETERANGAN "+
	      		" FROM TBLPFDFAIL F, TBLPERMOHONAN P,TBLHTPPERMOHONAN PP, "+
	      	    " TBLRUJSTATUS SI, TBLRUJSUBURUSANSTATUS RSUSI, TBLRUJSUBURUSANSTATUSFAIL RSUSFI "+
			    " WHERE P.ID_FAIL = F.ID_FAIL  " +
			    " AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
			    " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) " +
			 
	      		" AND SI.ID_STATUS = RSUSI.ID_STATUS "+
	      		" AND RSUSI.ID_SUBURUSANSTATUS = RSUSFI.ID_SUBURUSANSTATUS "+
	      		" AND RSUSFI.AKTIF = '1' "+
	      		" AND RSUSFI.ID_FAIL =P.ID_FAIL  AND RSUSFI.ID_PERMOHONAN=P.ID_PERMOHONAN "+
			    "";
	      
	      if (idUser != null) {
	    	  sql = sql + " AND F.ID_MASUK = '"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + " AND LOWER(F.NO_FAIL) like '%"+nofail.toLowerCase()+"%' ";
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + " AND F.ID_URUSAN = "+id_urusan+" ";
	      }
	      
	      //if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + " AND LOWER(F.TAJUK_FAIL) like '%"+tajukfail.toLowerCase()+"%' ";
	      //}
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) 
	    		  && !"".equals(id_kementerian) && !id_kementerian.equals("99999")) {
	    	  sql = sql + " AND F.ID_KEMENTERIAN = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) 
	    		  && !"".equals(id_agensi) && !id_agensi.equals("99999")) {
	    	  sql = sql + " AND PP.ID_AGENSI = '"+id_agensi+"' ";
	      }	      
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + " AND F.ID_NEGERI = '"+id_negeri+"' ";
	      }	      	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + " AND PP.ID_DAERAH = '"+id_daerah+"' ";
	      }	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  //sql = sql + " AND thmu.id_mukim = '"+id_mukim+"' ";
	    	  sql = sql + "AND B.ID_PERMOHONAN IN "+ 
	    	  	" ( SELECT TPHUI.ID_PERMOHONAN FROM TBLHTPHAKMILIKURUSAN TPHUI "+
	    	  	" WHERE TPHUI.ID_MUKIM='"+id_mukim+"'"+
	    	  	" ) ";
	      }
	      //tarikhTerima
	      if (tarikhTerima != null) {
	    	  if (!tarikhTerima.toString().trim().equals("")) {
	    		  sql = sql + " AND TO_CHAR(P.TARIKH_TERIMA,'dd/MM/yyyy') = '" +tarikhTerima+"'";
	    	  }
	      }

	      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
	    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
	      }	   
	      if (status != null && !"-1".equals(status) && !"".equals(status)) {
	    	  sql = sql + "AND B.ID_PERMOHONAN IN "+ 
	    	  	" ( SELECT  "+ 
	    	  	" RSUSFI.ID_PERMOHONAN "+ 
	    	  	"       FROM TBLRUJSTATUS RSI, TBLRUJSUBURUSANSTATUS RSUSI, TBLRUJSUBURUSANSTATUSFAIL RSUSFI "+ 
	    	  	"       WHERE RSUSI.ID_STATUS = RSI.ID_STATUS  "+ 
	    	  	"       AND RSUSI.ID_SUBURUSANSTATUS = RSUSFI.ID_SUBURUSANSTATUS  "+ 
	    	  	"       AND RSUSFI.AKTIF ='1' "+ 
	    	  	"       AND RSI.ID_STATUS = '"+status+"'"+
	    		"    ) "+
	    		"";     
	      }
	      if (pemohon != null && !"-1".equals(pemohon) && !"".equals(pemohon)) {
	    	  sql = sql + "AND B.ID_PERMOHONAN IN "+ 
	    	  	" ( SELECT TPPI.ID_PERMOHONAN "+ 
	    	  	"       FROM TBLHTPPEMOHON TPPI "+ 
	    	  	"       WHERE "+ 
	    	  	"       TPPI.NAMA_PEMOHON LIKE '%"+pemohon+"%'"+
	    		"    ) "+
	    		"";     
	      }
	      if (!isSearch) {
	    	  sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      //sql = sql + "ORDER BY F.TARIKH_DAFTAR_FAIL DESC";
	      sql = sql + " ORDER BY P.ID_FAIL DESC" +
	      "" ;
	     // 		"--,TARIKH_DAFTAR DESC";

	      myLog.info("getSenaraiFail:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String, String>>();
	      int bil = 1;
	      Hashtable<String, String> h;
	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("id", rs.getString("id_Fail"));
	    	  //options Penswastaan,Pajakan
	    	  h.put("idFail", rs.getString("id_Fail"));
	    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
	    	  //options Penswastaan,Pajakan
	    	  h.put("noFail", Utils.isNull(rs.getString("NO_FAIL")));	
	    	  //options Pajakan
	    	  h.put("idPermohonan", Utils.isNull(rs.getString("ID_PERMOHONAN")));
	    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
	    	  //options Penswastaan
	    	  h.put("status", Utils.isNull(rs.getString("KETERANGAN")));
	    	  //options Pajakan
			  h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
	    	  //options Pajakan
			  h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  list.addElement(h);
	    	  bil++;
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	      
	    }
	  }	
	
	@Override
	public void XkemaskiniMaklumatAsasTanah(Hashtable<String, String> data) throws Exception{			
		Connection conn = null;
		Db db = null;
	    try {
	    	String idUser = (String)data.get("idUser");
	    	String idhakmilikurusan = (String)data.get("idhakmilikurusan");
	    	String idPermohonan = (String)data.get("idpermohonan");
			String id_negeri = (String)data.get("socNegeri");
			String id_daerah = (String)data.get("socDaerah");
			String id_mukim = (String)data.get("socMukim");
			String id_lot = (String)data.get("socLot");
			String infonolot = (String)data.get("txtNoLot");
			String infonosyit = (String)data.get("noSyit");
			String infoPelan = (String)data.get("noPelan");
			//String infoIdLot = (String)data.get("socLot");
			String id_luaslama = (String)data.get("socLuas");
			String Lokasi = (String)data.get("Lokasi");				
			String luas = (String)data.get("Luas");				
			String luasBersamaan = (String)data.get("LuasH");				
			//Date now = new Date();
		    //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		    //String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			//r.add("Luas",Luas);
			//r.add("Luas_bersamaan",Luas);

		    db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "UPDATE TBLHTPHAKMILIKURUSAN SET " +
				"id_negeri='"+id_negeri+"',id_daerah='"+id_daerah+"',id_mukim='"+id_mukim+"'," +
			  	"id_lot='"+id_lot+"',no_lot='"+infonolot+"',no_syit='"+infonosyit+"'," +
			  	"no_pelan='"+infoPelan+"',id_luas='"+id_luaslama+"',lokasi='"+Lokasi+"'," +
			  	"id_kemaskini='"+idUser+"',tarikh_kemaskini=sysdate "+
			  	",luas='"+luas+"',luas_bersamaan= "+luasBersamaan+""+
			  	" WHERE id_hakmilikurusan='"+idhakmilikurusan+"'";
			myLog.info(sql);
		      stmt.executeUpdate(sql);
		      
		      SQLRenderer r = new SQLRenderer();
		      String idDaerahTemp ="";
		      r.add("ID_PERMOHONAN",idPermohonan);				      
		      r.add("ID_DAERAH");
		      sql = r.getSQLSelect("TBLHTPPERMOHONAN");
		      rs = stmt.executeQuery(sql);
		      if(rs.next()){
		    	  idDaerahTemp = rs.getString("ID_DAERAH");
		    	  if(idDaerahTemp!=id_daerah){
						r = new SQLRenderer();
						r.update("ID_PERMOHONAN",idPermohonan);
						r.add("ID_DAERAH",id_daerah);
						r.add("ID_KEMASKINI", idUser);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
						sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
						stmt.executeUpdate(sql);    		  
		    	  }
		      }else{
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN",idPermohonan);
				r.add("ID_DAERAH",id_daerah);
				r.add("ID_KEMASKINI", idUser);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
				stmt.executeUpdate(sql);		    
				
		      }
		      conn.commit();
	      
		}catch (SQLException se) { 
	
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Update Hakmilik:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}	
	    
	}
	
	@Override
	public String XkemaskiniMaklumatTanah(Hashtable<String, String> data) throws Exception{			
		Connection conn = null;
		Db db = null;
	    String returnValue = "";
	    try {
	    	String idUser = (String)data.get("idUser");
	    	String idhakmilikurusan = (String)data.get("idhakmilikurusan");
	    	returnValue = idhakmilikurusan;
	    	String idPermohonan = (String)data.get("idpermohonan");
			String id_negeri = (String)data.get("socNegeri");
			String id_daerah = (String)data.get("socDaerah");
			String id_mukim = (String)data.get("socMukim");
			String id_lot = (String)data.get("socLot");
			String infonolot = (String)data.get("txtNoLot");
			String infonosyit = (String)data.get("noSyit");
			String infoPelan = (String)data.get("noPelan");
			//String infoIdLot = (String)data.get("socLot");
			String id_luaslama = (String)data.get("socLuas");
			String Lokasi = (String)data.get("Lokasi");				
			String luas = (String)data.get("Luas");				
			String luasBersamaan = (String)data.get("LuasH");				
			//Date now = new Date();
		    //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		    //String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			//r.add("Luas",Luas);
			//r.add("Luas_bersamaan",Luas);

		    db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "UPDATE TBLHTPHAKMILIKURUSAN SET " +
				"id_negeri='"+id_negeri+"',id_daerah='"+id_daerah+"',id_mukim='"+id_mukim+"'," +
			  	"id_lot='"+id_lot+"',no_lot='"+infonolot+"',no_syit='"+infonosyit+"'," +
			  	"no_pelan='"+infoPelan+"',id_luas='"+id_luaslama+"',lokasi='"+Lokasi+"'," +
			  	"id_kemaskini='"+idUser+"',tarikh_kemaskini=sysdate "+
			  	",luas='"+luas+"',luas_bersamaan= "+luasBersamaan+""+
			  	" WHERE id_hakmilikurusan='"+idhakmilikurusan+"'";
			myLog.info(sql);
		      stmt.executeUpdate(sql);
		      
		      SQLRenderer r = new SQLRenderer();
		      String idDaerahTemp ="";
		      r.add("ID_PERMOHONAN",idPermohonan);				      
		      r.add("ID_DAERAH");
		      sql = r.getSQLSelect("TBLHTPPERMOHONAN");
		      rs = stmt.executeQuery(sql);
		      if(rs.next()){
		    	  idDaerahTemp = rs.getString("ID_DAERAH");
		    	  if(idDaerahTemp!=id_daerah){
						r = new SQLRenderer();
						r.update("ID_PERMOHONAN",idPermohonan);
						r.add("ID_DAERAH",id_daerah);
						r.add("ID_KEMASKINI", idUser);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
						sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
						stmt.executeUpdate(sql);    		  
		    	  }
		      }else{
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN",idPermohonan);
				r.add("ID_DAERAH",id_daerah);
				r.add("ID_KEMASKINI", idUser);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
				stmt.executeUpdate(sql);		    
				
		      }
		      conn.commit();
	      
		}catch (SQLException se) { 
	
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Update Hakmilik:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}	
		return returnValue ;
	}
	
	@Override
	public void XsimpanMaklumatAsasTanah(Hashtable<String, String> data) throws Exception{	
		Connection conn = null;
		Db db = null;
		ResultSet rs = null;
	    try{
	    	String peganganHakmilik = "";
	    	int idsubketegori = 96;
	    	int idketegori = 1;
	    	int idjenishakmilik = 0;
	    	long idPermohonan = Utils.parseLong((String)data.get("idpermohonan"));
	    	String idUser = (String)data.get("idUser");
			int idNegeri = Utils.parseInt((String)data.get("socNegeri"));
			int idDaerah = Utils.parseInt((String)data.get("socDaerah"));
			int idMukim = Utils.parseInt((String)data.get("socMukim"));
			int KodLot = Utils.parseInt((String)data.get("socLot"));
			String NoLot = (String)data.get("txtNoLot");
			String noSyit = (String)data.get("noSyit");
			String noPelan = (String)data.get("noPelan");
			if ("".equals(data.get("kodluas"))) {
				data.put("kodluas", "0");
			}
			int kodluas = Utils.parseInt((String)data.get("socLuas"));
			String Luas = (String)data.get("Luas");
			//String LuasH = (String)(data.get("LuasH"));	
			String Lokasi = (String)data.get("Lokasi");
			//int jenistanah = Utils.parseInt((String)data.get("jenistanah"));			
			//myLog.debug("simpanMaklumatAsasTanah :: " + data);
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_masuk", idUser);
			r.add("id_kemaskini",idUser);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Negeri",idNegeri);
			r.add("id_daerah",idDaerah);
			r.add("id_mukim",idMukim);
			r.add("id_lot",KodLot);
			r.add("no_lot",NoLot);
			r.add("no_syit",noSyit);
			r.add("no_pelan",noPelan);
			r.add("id_luas","2");
			r.add("Luas",Luas);
			r.add("id_luas_bersamaan",kodluas);
			r.add("Luas_bersamaan",Luas);
			r.add("Lokasi",Lokasi);
			r.add("tarikh_masuk",r.unquote("sysdate"));
			
			peganganHakmilik = FrmUtilData.getKodNegeri(String.valueOf(idNegeri));
			peganganHakmilik += FrmUtilData.getKodDaerah(String.valueOf(idDaerah));
			peganganHakmilik += FrmUtilData.getKodMukim(String.valueOf(idMukim));
			r.add("pegangan_hakmilik",peganganHakmilik);
			r.add("id_subkategori",idsubketegori);
			r.add("id_kategori",idketegori);
			r.add("id_jenishakmilik",idjenishakmilik);
			sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
			myLog.debug("Insert::TBLHTPHAKMILIKURUSAN = sql:"+sql);
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
		    String idDaerahTemp ="";
		    r.add("ID_PERMOHONAN",idPermohonan);				      
			r.add("ID_DAERAH");
		    sql = r.getSQLSelect("TBLHTPPERMOHONAN");
			myLog.debug("SELECT::TBLHTPHAKMILIKURUSAN = sql:"+sql);
		    rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	idDaerahTemp = rs.getString("ID_DAERAH");
		    	if(idDaerahTemp!=String.valueOf(idDaerah)){
		    		r = new SQLRenderer();
					r.update("ID_PERMOHONAN",idPermohonan);
					r.add("ID_DAERAH",idDaerah);
					r.add("ID_KEMASKINI", idUser);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
					sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
					myLog.debug("KEMASKINI X::TBLHTPHAKMILIKURUSAN = sql:"+sql);
					stmt.executeUpdate(sql);    		  
		    	}
		    }else{
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN",idPermohonan);
				r.add("ID_DAERAH",idDaerah);
				r.add("ID_KEMASKINI", idUser);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
				myLog.debug("KEMASKINI ELSE::TBLHTPHAKMILIKURUSAN = sql:"+sql);
				stmt.executeUpdate(sql);		    
				
		      }

			conn.commit();
		      
		}catch (SQLException se) { 
	
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Insert Hakmilik:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}
	}
	
	@Override
	public String XsimpanMaklumatTanah(Hashtable<String, String> data) throws Exception{	
		Connection conn = null;
		Db db = null;
		ResultSet rs = null;
		String returnValue = "";
	    try{
	    	String peganganHakmilik = "";
	    	int idsubketegori = 96;
	    	int idkategori = 1;
	    	int idjenishakmilik = 0;
	    	long idPermohonan = Utils.parseLong((String)data.get("idpermohonan"));
	    	String idUser = (String)data.get("idUser");
			int idNegeri = Utils.parseInt((String)data.get("socNegeri"));
			int idDaerah = Utils.parseInt((String)data.get("socDaerah"));
			int idMukim = Utils.parseInt((String)data.get("socMukim"));
			int kodLot = Utils.parseInt((String)data.get("socLot"));
			String noLot = (String)data.get("txtNoLot");
			String noSyit = (String)data.get("noSyit");
			String noPelan = (String)data.get("noPelan");
			if ("".equals(data.get("kodluas"))) {
				data.put("kodluas", "0");
			}
			int kodLuas = Utils.parseInt((String)data.get("socLuas"));
			String Luas = (String)data.get("Luas");
			String LuasH = (String)(data.get("LuasH"));	
			String Lokasi = (String)data.get("Lokasi");
			//int jenistanah = Utils.parseInt((String)data.get("jenistanah"));			
			long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
			returnValue = String.valueOf(idHakmilikurusan);
			//myLog.debug("simpanMaklumatAsasTanah :: " + data);

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_hakmilikurusan", idHakmilikurusan);
			r.add("id_masuk", idUser);
			r.add("id_kemaskini",idUser);
			r.add("id_permohonan", idPermohonan);
			r.add("id_negeri",idNegeri);
			r.add("id_daerah",idDaerah);
			r.add("id_mukim",idMukim);
			r.add("id_lot",kodLot);
			r.add("no_lot",noLot);
			r.add("no_syit",noSyit);
			r.add("no_pelan",noPelan);
			r.add("id_luas",kodLuas);
			r.add("luas",Luas);
			r.add("id_luas_bersamaan","2");
			r.add("luas_bersamaan",LuasH);
			r.add("lokasi",Lokasi);
			r.add("tarikh_masuk",r.unquote("sysdate"));
			
			peganganHakmilik = FrmUtilData.getKodNegeri(String.valueOf(idNegeri));
			peganganHakmilik += FrmUtilData.getKodDaerah(String.valueOf(idDaerah));
			peganganHakmilik += FrmUtilData.getKodMukim(String.valueOf(idMukim));
			r.add("pegangan_hakmilik",peganganHakmilik);
			r.add("id_subkategori",idsubketegori);
			r.add("id_kategori",idkategori);
			r.add("id_jenishakmilik",idjenishakmilik);
			sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
			myLog.debug("Insert::TBLHTPHAKMILIKURUSAN = sql:"+sql);
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
		    String idDaerahTemp ="";
		    r.add("ID_PERMOHONAN",idPermohonan);				      
			r.add("ID_DAERAH");
		    sql = r.getSQLSelect("TBLHTPPERMOHONAN");
			myLog.debug("SELECT::TBLHTPHAKMILIKURUSAN = sql:"+sql);
		    rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	idDaerahTemp = rs.getString("ID_DAERAH");
		    	if(idDaerahTemp!=String.valueOf(idDaerah)){
		    		r = new SQLRenderer();
					r.update("ID_PERMOHONAN",idPermohonan);
					r.add("ID_DAERAH",idDaerah);
					r.add("ID_KEMASKINI", idUser);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
					sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
					myLog.debug("KEMASKINI X::TBLHTPHAKMILIKURUSAN = sql:"+sql);
					stmt.executeUpdate(sql);    		  
		    	}
		    }else{
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN",idPermohonan);
				r.add("ID_DAERAH",idDaerah);
				r.add("ID_KEMASKINI", idUser);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
				myLog.debug("KEMASKINI ELSE::TBLHTPHAKMILIKURUSAN = sql:"+sql);
				stmt.executeUpdate(sql);		    
				
		      }

			conn.commit();
		      
		}catch (SQLException se) { 
	
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Insert Hakmilik:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}
    	return returnValue ;

	}	
	
	@Override
	public Hashtable<String, String> XgetMaklumatAsasTanahKemaskini(String idhakmilikurusan)throws Exception {
		Db db = null;
		Hashtable<String, String> h = null;
		try{
			//list = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			sql ="select ID_HAKMILIKURUSAN,ID_NEGERI,ID_DAERAH,ID_MUKIM, " +
					"NO_LOT,NO_SYIT,NO_PELAN,ID_LOT,ID_LUAS,ID_LUAS_BERSAMAAN, " +
					"LUAS,LUAS_BERSAMAAN,LOKASI " +
					"from tblhtphakmilikurusan where id_hakmilikurusan = '"+idhakmilikurusan+"'";		  
		  myLog.info("getMaklumatAsasTanahKemaskini: sql::"+sql);
		  
		  ResultSet rs1 = stmt.executeQuery(sql);
		  int bil = 1;		  
		  while(rs1.next()){
			  h = new Hashtable<String, String>();
			  h.put("bil", String.valueOf(bil));
			  h.put("idhakmilikurusan", Utils.isNull(rs1.getString("ID_HAKMILIKURUSAN"))); 
			  h.put("idnegeri", Utils.isNull(rs1.getString("ID_NEGERI"))); 
			  h.put("iddaerah", Utils.isNull(rs1.getString("ID_DAERAH"))); 
			  h.put("idmukim", Utils.isNull(rs1.getString("ID_MUKIM"))); 
			  h.put("nolot", Utils.isNull(rs1.getString("NO_LOT"))); 
			  h.put("nosyit", Utils.isNull(rs1.getString("NO_SYIT"))); 
			  h.put("nopelan", Utils.isNull(rs1.getString("NO_PELAN"))); 
			  h.put("idlot", Utils.isNull(rs1.getString("ID_LOT"))); 
			  h.put("idluas", Utils.isNull(rs1.getString("ID_LUAS"))); 
			  h.put("idluasbersamaan", Utils.isNull(rs1.getString("ID_LUAS_BERSAMAAN"))); 
			  h.put("luas", Utils.isNull(rs1.getString("LUAS"))); 
			  h.put("luasLama", Utils.isNull(rs1.getString("LUAS_BERSAMAAN"))); 
			  h.put("luasH", Utils.isNull(rs1.getString("LUAS_BERSAMAAN"))); 
			  h.put("lokasi", Utils.isNull(rs1.getString("LOKASI"))); 
			  bil++;
			  //list.addElement(h);
		  }
			return h;
		}finally{
			if (db != null) db.close();
		}	
	}
	
	@Override
	public Hashtable<String,String> getMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		Hashtable<String,String> h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL, B.ID_PERMOHONAN,B.TARIKH_SURAT, B.ID_STATUS" +
					" ,PP.NO_RUJUKAN_KJP, PP.NO_RUJUKAN_LAIN, PP.TARIKH_AGIHAN "+
					" ,D.ID_NEGERI, D.NAMA_NEGERI, E.ID_KEMENTERIAN, E.NAMA_KEMENTERIAN" +
					//", F.ID_AGENSI, F.NAMA_AGENSI" +
					" ,NVL((SELECT RAI.nama_agensi FROM TBLRUJAGENSI RAI "+
					" 		WHERE RAI.ID_AGENSI = PP.ID_AGENSI "+
					" 	),'TIADA MAKLUMAT AGENSI') NAMA_AGENSI "+
					" ,NVL((SELECT RAI.ID_AGENSI FROM TBLRUJAGENSI RAI "+
					" 		WHERE RAI.ID_AGENSI = PP.ID_AGENSI "+
					" 	),'0') ID_AGENSI "+
					" ,NVL((SELECT RUI.NAMA_URUSAN FROM TBLRUJURUSAN RUI "+ 
					"  		WHERE RUI.ID_URUSAN = A.ID_URUSAN "+
					"  ),'TIADA MAKLUMAT URUSAN') NAMA_URUSAN "+ 
					//" ,A.ID_SUBURUSAN, G.NAMA_SUBURUSAN" +
					" ,NVL((SELECT RSUI.ID_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
					" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
					" 	),'0') ID_SUBURUSAN "+
					" ,NVL((SELECT RSUI.NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
					" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
					" 	),'TIADA MAKLUMAT URUSAN') NAMA_SUBURUSAN "+
					//" ,H.ID_JENISTANAH, H.KETERANGAN" +
					" ,NVL((SELECT RJJI.ID_JENISTANAH FROM TBLRUJJENISTANAH RJJI "+ 
					" 		WHERE RJJI.id_jenistanah = PP.id_jenistanah "+
					" 	),'0') ID_JENISTANAH "+
					" ,NVL((SELECT RJJI.KETERANGAN FROM TBLRUJJENISTANAH RJJI "+ 
					" 		WHERE RJJI.id_jenistanah = PP.id_jenistanah "+
					" 	),'TIADA MAKLUMAT JENIS TANAH') KETERANGAN "+
					//" ,I.ID_TARAFKESELAMATAN, I.TARAF_KESELAMATAN "+
					" ,NVL((SELECT RTKI.ID_TARAFKESELAMATAN FROM TBLPFDRUJTARAFKESELAMATAN RTKI "+ 
					" 		WHERE A.ID_TARAFKESELAMATAN = RTKI.ID_TARAFKESELAMATAN "+
					" 	),'0') ID_TARAFKESELAMATAN "+
					" ,NVL((SELECT RTKI.TARAF_KESELAMATAN FROM TBLPFDRUJTARAFKESELAMATAN RTKI "+ 
					" 		WHERE A.ID_TARAFKESELAMATAN = RTKI.ID_TARAFKESELAMATAN "+
					" 	),'TIADA MAKLUMAT STATUS KESELAMATAN FAIL') TARAF_KESELAMATAN "+
				    " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN PP "+
					" , TBLRUJNEGERI D, TBLRUJKEMENTERIAN E "+
					//" , TBLRUJAGENSI F, TBLRUJSUBURUSAN G, TBLRUJJENISTANAH H, TBLPFDRUJTARAFKESELAMATAN I "+
					" WHERE B.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
					" AND B.ID_FAIL = A.ID_FAIL "+
					" AND A.ID_NEGERI = D.ID_NEGERI "+
					" AND A.ID_KEMENTERIAN = E.ID_KEMENTERIAN "+
					//"AND C.ID_AGENSI = F.ID_AGENSI AND"
					//" A.ID_SUBURUSAN = G.ID_SUBURUSAN " +
					//"AND C.ID_JENISTANAH = H.ID_JENISTANAH " +
					//"AND A.ID_TARAFKESELAMATAN = I.ID_TARAFKESELAMATAN"
					" AND A.ID_FAIL = '" + idFail + "'" +
					"";

			ResultSet rs = stmt.executeQuery(sql);	
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				// Header 
				h.put("urusan", rs.getString("NAMA_URUSAN") == null ? "" : rs.getString("NAMA_URUSAN").toUpperCase());
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				// Header 
				h.put("subUrusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				h.put("namaSubUrusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("idStatusTanah", rs.getString("ID_JENISTANAH") == null ? "" : rs.getString("ID_JENISTANAH").toUpperCase());
				h.put("statusTanah", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("idJenisFail", rs.getString("ID_TARAFKESELAMATAN") == null ? "" : rs.getString("ID_TARAFKESELAMATAN").toUpperCase());
				h.put("jenisFail", rs.getString("TARAF_KESELAMATAN") == null ? "" : rs.getString("TARAF_KESELAMATAN").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("tarikhSuratKJP", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("tarikhAgihan", rs.getDate("TARIKH_AGIHAN") == null ? "" : sdf.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("tajuk", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				
			}
		}catch(Exception e){	
			e.printStackTrace();
			throw new Exception(getIHTP().getErrorHTML("[HTP FAIL] GET FILE"));

		} finally {
			if (db != null)
				db.close();
		}
		return h;
	}
	
	@Override
	public Vector<Hashtable<String, String>> getMaklumatPermohonans(String idFail) throws Exception {
		Db db = null;
		Hashtable<String, String> h = null;
		Vector<Hashtable<String, String>> beanMaklumatPermohonan = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//int bil = 1;
			sql = "SELECT B.FLAG_MOHON_FAIL, A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL, B.ID_PERMOHONAN,B.TARIKH_SURAT, B.ID_STATUS "+
					" ,NVL(PP.NO_RUJUKAN_KJP,'') NO_RUJUKAN_KJP, NVL(PP.NO_RUJUKAN_LAIN,'') NO_RUJUKAN_LAIN, PP.TARIKH_AGIHAN "+
					" ,D.ID_NEGERI, D.NAMA_NEGERI, E.ID_KEMENTERIAN, E.NAMA_KEMENTERIAN" +
					//", F.ID_AGENSI, F.NAMA_AGENSI" +
					" ,NVL((SELECT RAI.nama_agensi FROM TBLRUJAGENSI RAI "+
					" 		WHERE RAI.ID_AGENSI = PP.ID_AGENSI "+
					" 	),'TIADA MAKLUMAT AGENSI') NAMA_AGENSI "+
					" ,NVL((SELECT RAI.ID_AGENSI FROM TBLRUJAGENSI RAI "+
					" 		WHERE RAI.ID_AGENSI = PP.ID_AGENSI "+
					" 	),'0') ID_AGENSI "+
					" ,NVL((SELECT RUI.NAMA_URUSAN FROM TBLRUJURUSAN RUI "+ 
					"  		WHERE RUI.ID_URUSAN = A.ID_URUSAN "+
					"  ),'TIADA MAKLUMAT URUSAN') NAMA_URUSAN "+ 
					//" ,A.ID_SUBURUSAN, G.NAMA_SUBURUSAN" +
					" ,NVL((SELECT RSUI.ID_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
					" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
					" 	),'0') ID_SUBURUSAN "+
					" ,NVL((SELECT RSUI.NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
					" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
					" 	),'TIADA MAKLUMAT URUSAN') NAMA_SUBURUSAN "+
					//" ,H.ID_JENISTANAH, H.KETERANGAN" +
					" ,NVL((SELECT RJJI.ID_JENISTANAH FROM TBLRUJJENISTANAH RJJI "+ 
					" 		WHERE RJJI.id_jenistanah = PP.id_jenistanah "+
					" 	),'0') ID_JENISTANAH "+
					" ,NVL((SELECT RJJI.KETERANGAN FROM TBLRUJJENISTANAH RJJI "+ 
					" 		WHERE RJJI.id_jenistanah = PP.id_jenistanah "+
					" 	),'TIADA MAKLUMAT JENIS TANAH') KETERANGAN "+
					//" ,I.ID_TARAFKESELAMATAN, I.TARAF_KESELAMATAN "+
					" ,NVL((SELECT RTKI.ID_TARAFKESELAMATAN FROM TBLPFDRUJTARAFKESELAMATAN RTKI "+ 
					" 		WHERE A.ID_TARAFKESELAMATAN = RTKI.ID_TARAFKESELAMATAN "+
					" 	),'0') ID_TARAFKESELAMATAN "+
					" ,NVL((SELECT RTKI.TARAF_KESELAMATAN FROM TBLPFDRUJTARAFKESELAMATAN RTKI "+ 
					" 		WHERE A.ID_TARAFKESELAMATAN = RTKI.ID_TARAFKESELAMATAN "+
					" 	),'TIADA MAKLUMAT STATUS KESELAMATAN FAIL') TARAF_KESELAMATAN "+
				    " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN PP "+
					" , TBLRUJNEGERI D, TBLRUJKEMENTERIAN E "+
					//" , TBLRUJAGENSI F, TBLRUJSUBURUSAN G, TBLRUJJENISTANAH H, TBLPFDRUJTARAFKESELAMATAN I "+
					" WHERE B.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
					" AND B.ID_FAIL = A.ID_FAIL "+
					" AND A.ID_NEGERI = D.ID_NEGERI "+
					" AND A.ID_KEMENTERIAN = E.ID_KEMENTERIAN "+
					//"AND C.ID_AGENSI = F.ID_AGENSI AND"
					//" A.ID_SUBURUSAN = G.ID_SUBURUSAN " +
					//"AND C.ID_JENISTANAH = H.ID_JENISTANAH " +
					//"AND A.ID_TARAFKESELAMATAN = I.ID_TARAFKESELAMATAN"
					" AND A.ID_FAIL = '" + idFail + "'" +
					"";
			myLog.info("sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	
			beanMaklumatPermohonan = new Vector<Hashtable<String, String>>();
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				// Header 
				h.put("urusan", rs.getString("NAMA_URUSAN") == null ? "" : rs.getString("NAMA_URUSAN").toUpperCase());
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				// Header 
				h.put("subUrusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				h.put("namaSubUrusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("idStatusTanah", rs.getString("ID_JENISTANAH") == null ? "" : rs.getString("ID_JENISTANAH").toUpperCase());
				h.put("statusTanah", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("idJenisFail", rs.getString("ID_TARAFKESELAMATAN") == null ? "" : rs.getString("ID_TARAFKESELAMATAN").toUpperCase());
				h.put("jenisFail", rs.getString("TARAF_KESELAMATAN") == null ? "" : rs.getString("TARAF_KESELAMATAN").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("tarikhSuratKJP", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("tarikhAgihan", rs.getDate("TARIKH_AGIHAN") == null ? "" : sdf.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("tajuk", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				h.put("flagMohonFail", rs.getString("FLAG_MOHON_FAIL") == null ? "" : rs.getString("FLAG_MOHON_FAIL"));
				beanMaklumatPermohonan.addElement(h);
				//bil++;
				
			}
		}catch(Exception e){	
			e.printStackTrace();
			throw new Exception(getIHTP().getErrorHTML("[HTP FAIL] GET FILES"));
			
		} finally {
			if (db != null)
				db.close();
		}
		return beanMaklumatPermohonan;
		
	}
	
	@Override
	public HtpPermohonan findPermohonan(String idFail, String idPermohonan,String idHtpPermohonan) 
		throws Exception {
		Db db = null;
		Connection conn = null;
		//Date now = new Date();
		//SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		//String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL, A.ID_URUSAN" +
				",TO_CHAR(A.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') AS OPEN_FILE_DATE" +
				",B.TARIKH_TERIMA, B.ID_PERMOHONAN,TO_CHAR(B.TARIKH_SURAT,'DD/MM/YYYY') TARIKH_SURAT" +
				", B.ID_STATUS,B.NO_PERMOHONAN,B.TUJUAN, B.FLAG_MOHON_FAIL "+
			" ,NVL(PP.NO_RUJUKAN_KJP,'') NO_RUJUKAN_KJP, NVL(PP.NO_RUJUKAN_LAIN,'') NO_RUJUKAN_LAIN" +
			", PP.TARIKH_AGIHAN,PP.ID_HTPPERMOHONAN "+
			" ,D.ID_NEGERI, D.NAMA_NEGERI, E.ID_KEMENTERIAN, E.NAMA_KEMENTERIAN" +
			//", F.ID_AGENSI, F.NAMA_AGENSI" +
			" ,NVL((SELECT RAI.nama_agensi FROM TBLRUJAGENSI RAI "+
			" 		WHERE RAI.ID_AGENSI = PP.ID_AGENSI "+
			" 	),'TIADA MAKLUMAT AGENSI') NAMA_AGENSI "+
			" ,NVL((SELECT RAI.ID_AGENSI FROM TBLRUJAGENSI RAI "+
			" 		WHERE RAI.ID_AGENSI = PP.ID_AGENSI "+
			" 	),'0') ID_AGENSI "+
			" ,NVL((SELECT RUI.NAMA_URUSAN FROM TBLRUJURUSAN RUI "+ 
			"  		WHERE RUI.ID_URUSAN = A.ID_URUSAN "+
			"  ),'TIADA MAKLUMAT URUSAN') NAMA_URUSAN "+ 
			//" ,A.ID_SUBURUSAN, G.NAMA_SUBURUSAN" +
			" ,NVL((SELECT RSUI.ID_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
			" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
			" 	),'0') ID_SUBURUSAN "+
			" ,NVL((SELECT RSUI.NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
			" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
			" 	),'TIADA MAKLUMAT URUSAN') NAMA_SUBURUSAN "+
			" ,NVL((SELECT RSUI.KOD_SUBURUSAN FROM TBLRUJSUBURUSAN RSUI "+ 
			" 		WHERE RSUI.ID_SUBURUSAN = A.ID_SUBURUSAN "+
			" 	),'TIADA MAKLUMAT URUSAN') KOD_SUBURUSAN "+
			//" ,H.ID_JENISTANAH, H.KETERANGAN" +
			" ,NVL((SELECT RJJI.ID_JENISTANAH FROM TBLRUJJENISTANAH RJJI "+ 
			" 		WHERE RJJI.id_jenistanah = PP.id_jenistanah "+
			" 	),'0') ID_JENISTANAH "+
			" ,NVL((SELECT RJJI.KETERANGAN FROM TBLRUJJENISTANAH RJJI "+ 
			" 		WHERE RJJI.id_jenistanah = PP.id_jenistanah "+
			" 	),'TIADA MAKLUMAT JENIS TANAH') KETERANGAN "+
			//" ,I.ID_TARAFKESELAMATAN, I.TARAF_KESELAMATAN "+
			" ,NVL((SELECT RTKI.ID_TARAFKESELAMATAN FROM TBLPFDRUJTARAFKESELAMATAN RTKI "+ 
			" 		WHERE A.ID_TARAFKESELAMATAN = RTKI.ID_TARAFKESELAMATAN "+
			" 	),'0') ID_TARAFKESELAMATAN "+
			" ,NVL((SELECT RTKI.TARAF_KESELAMATAN FROM TBLPFDRUJTARAFKESELAMATAN RTKI "+ 
			" 		WHERE A.ID_TARAFKESELAMATAN = RTKI.ID_TARAFKESELAMATAN "+
			" 	),'TIADA MAKLUMAT STATUS KESELAMATAN FAIL') TARAF_KESELAMATAN "+
		    " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN PP "+
			" , TBLRUJNEGERI D, TBLRUJKEMENTERIAN E "+
			//" , TBLRUJAGENSI F, TBLRUJSUBURUSAN G, TBLRUJJENISTANAH H, TBLPFDRUJTARAFKESELAMATAN I "+
			" WHERE B.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
			" AND B.ID_FAIL = A.ID_FAIL "+
			" AND A.ID_NEGERI = D.ID_NEGERI "+
			" AND A.ID_KEMENTERIAN = E.ID_KEMENTERIAN "+
			//"AND C.ID_AGENSI = F.ID_AGENSI AND"
			//" A.ID_SUBURUSAN = G.ID_SUBURUSAN " +
			//"AND C.ID_JENISTANAH = H.ID_JENISTANAH " +
			//"AND A.ID_TARAFKESELAMATAN = I.ID_TARAFKESELAMATAN"
			" AND A.ID_FAIL = '" + idFail + "'" +
			"";
			if(!idPermohonan.equals("")){
				sql += " AND B.ID_PERMOHONAN = '"+ idPermohonan +"'";
			}
			if(!idHtpPermohonan.equals("")){
				sql += " AND PP.ID_HTPPERMOHONAN = '"+ idHtpPermohonan +"'";
			}
			sql +=" ORDER BY B.id_Permohonan desc";
//			myLog.info("Permohonan:::findPermohonan::sql::"+sql);
											
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);		
			if(rs.next()){
				fail = new PfdFail();
				permohonan = new Permohonan();
				htpPermohonan = new HtpPermohonan();									 		
//										 		 String TarikhSurKJP = rs.getString("TARIKH_SURAT");
//											     String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";	
//											     String TarikhPermohonan = rs.getString("TARIKH_TERIMA");
//											     String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
											     
				htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
				htpPermohonan.setIdAgensi(rs.getLong("ID_AGENSI"));
				htpPermohonan.setIdJenisTanah(rs.getString("ID_JENISTANAH"));
				htpPermohonan.setNoRujukanKJP(rs.getString("NO_RUJUKAN_KJP"));
				htpPermohonan.setNoRujukanLain(rs.getString("NO_RUJUKAN_LAIN"));
				htpPermohonan.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
				permohonan.setIdPermohonan(rs.getString("ID_PERMOHONAN"));
				permohonan.setTarikhSurat(rs.getString("TARIKH_SURAT"));
				permohonan.setTarikhTerima(rs.getString("TARIKH_TERIMA"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				permohonan.setFlagMohonFail(rs.getString("FLAG_MOHON_FAIL")==null?"":rs.getString("FLAG_MOHON_FAIL"));
				fail.setNoFail(rs.getString("NO_FAIL"));
				fail.setIdKementerian(rs.getString("ID_KEMENTERIAN"));
				fail.setIdNegeri(rs.getString("ID_NEGERI"));
				fail.setIdUrusan(rs.getLong("ID_URUSAN"));
				fail.setIdSubUrusan(rs.getString("ID_SUBURUSAN"));
				fail.setIdTarafKeselamatan(rs.getString("ID_TARAFKESELAMATAN")==null?"1":rs.getString("ID_TARAFKESELAMATAN"));
				fail.setTarikhDaftarFail(rs.getString("OPEN_FILE_DATE"));
				fail.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				fail.setNamaSuburusan(rs.getString("NAMA_SUBURUSAN"));
				fail.setKodSuburusan(rs.getString("KOD_SUBURUSAN"));
				fail.setTajukFail(rs.getString("TAJUK_FAIL"));
				fail.setIdFail(rs.getLong("ID_FAIL"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				conn.commit();
			
			}
										    	
		 } finally {
		      if (db != null) db.close();
		    }
		
		return htpPermohonan;
		
	}	
	
	@Override
	public  Vector<Hashtable<String, String>> getSenaraiFailMengikutUrusans(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhTerima,String tarikhBukaFail
			,String status,boolean isSearch)throws Exception {
	    Db db = null;
	    Vector<Hashtable<String, String>> list = null;
	    //boolean isSearch = false;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "SELECT F.ID_MASUK,P.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL,F.TARIKH_DAFTAR_FAIL,F.ID_URUSAN" +
	      		" ,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR,F.ID_KEMASKINI,F.TARIKH_MASUK  " +
	      		" ,P.NO_PERMOHONAN, P.TUJUAN " +
	      		" ,P.ID_PERMOHONAN "+
	      		" ,PP.NO_RUJUKAN_PTD,PP.NO_RUJUKAN_PTG " +      		
	      		" ,(select nama_negeri from tblrujnegeri where id_negeri = f.id_negeri) NEGERI"+
	      		" ,S.KETERANGAN "+
	      		" FROM Tblpermohonan P,TblHTPPermohonan PP, "+
			    " Tblpfdfail F,Tblrujsuburusanstatusfail SF," +
			    " Tblrujsuburusanstatus US,Tblrujstatus S "+
			    " WHERE P.ID_FAIL = F.ID_FAIL  " +
			    " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS is null) " +
			    " AND ( F.NO_FAIL IS NOT NULL AND F.NO_FAIL <> ' ') " +
			    " AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
			    " AND P.ID_PERMOHONAN = SF.ID_PERMOHONAN  "+
			    " AND SF.ID_SUBURUSANSTATUS= US.ID_SUBURUSANSTATUS  "+
			    " AND SF.ID_FAIL = F.ID_FAIL "+
			    " AND US.ID_STATUS = S.ID_STATUS  " +
			    " AND SF.AKTIF = '1' ";	      
	      if (idUser != null) {
	    	  sql = sql + " AND F.ID_MASUK ='"+idUser+"'";
	      }	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + " AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + " AND F.ID_URUSAN IN ("+id_urusan+") ";
	      }	      
	      //if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + " AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      //}	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  //by rosli 24/02/2011
	    	  //sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	    	  sql = sql + " AND P.ID_PERMOHONAN IN ( "+
	    	  	" SELECT THMUI.ID_PERMOHONAN FROM Tblhtphakmilikurusan THMUI "+
	    		" WHERE THMUI.ID_MUKIM = '"+id_mukim+"')"; 
	      }
	      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
	    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
	      }
	      myLog.info("isSearch="+isSearch);
	      if (!isSearch) {
	    	  sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      //sql = sql + "ORDER BY F.TARIKH_DAFTAR_FAIL DESC";
	      sql = sql + " ORDER BY P.ID_FAIL DESC" +
	      "" ;
	     // 		"--,TARIKH_DAFTAR DESC";
	      myLog.info("getSenaraiFailMengikutUrusans::sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String, String>>();
	      int bil = 1;
	      Hashtable<String, String> h;
	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("bil", String.valueOf(bil));    	  
	    	  h.put("id", rs.getString("ID_FAIL"));
	    	  h.put("idFail", rs.getString("ID_FAIL"));
	    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
	    	  h.put("noPermohonan", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
	     	  h.put("ptg", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
	    	  h.put("ptd", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
	    	  h.put("flagTanah", Utils.isNull(rs.getString("ID_URUSAN")));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
	    	  h.put("idPermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
	    	  list.addElement(h);
	    	  bil++;
	    	  
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	      
	    }
	  }	
	
	@Override
	public  Vector<Hashtable<String, String>> getSenaraiFailMengikutUrusans(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhTerima,String tarikhBukaFail
			,String status,boolean isSearch, String nofaillain)throws Exception {
	    Db db = null;
	    Vector<Hashtable<String, String>> list = null;
	    //boolean isSearch = false;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "SELECT F.ID_MASUK,P.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL,F.TARIKH_DAFTAR_FAIL,F.ID_URUSAN" +
	      		" ,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR,F.ID_KEMASKINI,F.TARIKH_MASUK  " +
	      		" ,P.NO_PERMOHONAN, P.TUJUAN " +
	      		" ,P.ID_PERMOHONAN "+
	      		" ,PP.NO_RUJUKAN_PTD,PP.NO_RUJUKAN_PTG " +      		
	      		" ,(select nama_negeri from tblrujnegeri where id_negeri = f.id_negeri) NEGERI"+
	      		" ,S.KETERANGAN "+
	      		" FROM Tblpermohonan P,TblHTPPermohonan PP, "+
			    " Tblpfdfail F,Tblrujsuburusanstatusfail SF," +
			    " Tblrujsuburusanstatus US,Tblrujstatus S "+
			    " WHERE P.ID_FAIL = F.ID_FAIL  " +
			    " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS is null) " +
			    " AND ( F.NO_FAIL IS NOT NULL AND F.NO_FAIL <> ' ') " +
			    " AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
			    " AND P.ID_PERMOHONAN = SF.ID_PERMOHONAN  "+
			    " AND SF.ID_SUBURUSANSTATUS= US.ID_SUBURUSANSTATUS  "+
			    " AND SF.ID_FAIL = F.ID_FAIL "+
			    " AND US.ID_STATUS = S.ID_STATUS  " +
			    " AND SF.AKTIF = '1' ";	      
	      if (idUser != null) {
	    	  sql = sql + " AND F.ID_MASUK ='"+idUser+"'";
	      }	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + " AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }      
	      if (nofaillain != null && !"".equals(nofaillain)) {
	    	  sql = sql + " AND lower(PP.no_rujukan_upt) like '%"+nofaillain.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + " AND F.ID_URUSAN IN ("+id_urusan+") ";
	      }	      
	      //if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + " AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      //}	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  //by rosli 24/02/2011
	    	  //sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	    	  sql = sql + " AND P.ID_PERMOHONAN IN ( "+
	    	  	" SELECT THMUI.ID_PERMOHONAN FROM Tblhtphakmilikurusan THMUI "+
	    		" WHERE THMUI.ID_MUKIM = '"+id_mukim+"')"; 
	      }
	      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
	    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
	      }
	      myLog.info("isSearch="+isSearch);
	      if (!isSearch) {
	    	  sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      //sql = sql + "ORDER BY F.TARIKH_DAFTAR_FAIL DESC";
	      sql = sql + " ORDER BY P.ID_FAIL DESC" +
	      "" ;
	     // 		"--,TARIKH_DAFTAR DESC";
	      myLog.info("getSenaraiFailMengikutUrusans::sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String, String>>();
	      int bil = 1;
	      Hashtable<String, String> h;
	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("bil", String.valueOf(bil));    	  
	    	  h.put("id", rs.getString("ID_FAIL"));
	    	  h.put("idFail", rs.getString("ID_FAIL"));
	    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
	    	  h.put("noPermohonan", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
	     	  h.put("ptg", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
	    	  h.put("ptd", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
	    	  h.put("flagTanah", Utils.isNull(rs.getString("ID_URUSAN")));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
	    	  h.put("idPermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
	    	  list.addElement(h);
	    	  bil++;
	    	  
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	      
	    }
	  }	
	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
	
	
}
