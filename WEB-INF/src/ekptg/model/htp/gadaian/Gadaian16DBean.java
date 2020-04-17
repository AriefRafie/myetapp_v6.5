package ekptg.model.htp.gadaian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.entities.Bebanan;
import ekptg.model.entities.MaklumatGadaian;
import ekptg.model.htp.entity.HtpPermohonan;

public class Gadaian16DBean implements IGadaian{
	private static final String KOD_JABATAN ="JKPTG";
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.gadaian.Gadaian16DBean.class);
	private static final long IDTARAFKESELAMTAN = 1;
	private static final int IDSEKSYEN = 3;
	private static final int IDURUSAN = 2;
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	MaklumatGadaian mg = null;
	Bebanan beban = null;	

	//@Override
	public MaklumatGadaian cariMaklumat(String idPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		
		Date now = new Date();
		 SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		 String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "select TPMG.TARIKH_LEPASGADAI,TPMG.id_Permohonan ";
			sql += " from TBLHTPMAKLUMATGADAIAN TPMG ";
			sql += " where ";
			sql += " TPMG.ID_PERMOHONAN = '"+ idPermohonan +"'";
			sql +=" ORDER BY TPMG.id_Permohonan desc";
			System.out.println("MaklumatGadaian:::cariMaklumat::sql::"+sql);
											
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);		
			mg = new MaklumatGadaian();
			mg.setTarikhLepasgadai(now);							     
										    
			if(rs.next()){
			
			mg.setTarikhLepasgadai(rs.getDate("TARIKH_LEPASGADAI"));

			conn.commit();
										     }									    	
		 } finally {
		      if (db != null) db.close();
		    }
		

		return mg;
	}
	//@Override

	
	//@Override
	public Bebanan cariBebanan(String idPermohonan) {
		Db db = null;
		Vector<HtpPermohonan> v = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();

		      String sql = "SELECT f.id_fail, P.ID_PERMOHONAN , PK.NAMA , PK.ALAMAT1,PK.ALAMAT2,PK.ALAMAT3, ";
		      sql += " PK.POSKOD, PK.NO_TEL, PK.NO_FAX, HTPP.NO_RUJUKAN_LAIN, ";
		      sql += " N.NAMA_NEGERI, D.NAMA_DAERAH, HU.NO_HAKMILIK, HU.NO_LOT, L.KETERANGAN as nama_lot,";
		      sql += " HK.KETERANGAN as jenis_hakmilik, B.NO_PERSERAHAN ";
		      sql += " from tblpfdfail f,TBLHTPFAILMAPPING FP,TBLPERMOHONAN P,TBLHTPHAKMILIKURUSAN HU,TBLHTPPIHAKBERKEPENTINGAN PK,";
		      sql += "  tblhtppermohonan htpp, tblrujdaerah d, tblrujnegeri n, tblrujlot l, tblrujjenishakmilik hk,  tblhtpbebanan b";
		      sql += " where " ;
		      //		"f.id_fail=" + idFail;
		      sql += " AND FP.ID_FAIL=F.ID_FAIL ";
		      sql += " AND FP.ID_FAILLAMA =P.ID_FAIL ";
		      sql += " AND P.ID_PERMOHONAN="+idPermohonan;
		      sql += " AND P.ID_PERMOHONAN=HU.ID_PERMOHONAN ";
		      sql += " AND P.ID_PERMOHONAN=htpp.ID_PERMOHONAN ";
		      sql += " AND PK.ID_HAKMILIKURUSAN=HU.ID_HAKMILIKURUSAN ";
		      sql += " AND PK.ID_DAERAH = D.ID_DAERAH ";
		      sql += " AND PK.ID_NEGERI = N.ID_NEGERI ";
		      sql += " AND HU.ID_LOT = L.ID_LOT ";
		      sql += " AND HU.ID_JENISHAKMILIK = HK.ID_JENISHAKMILIK ";
		      sql += " AND PK.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN ";
			
			beban = new Bebanan();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				beban.setNamaPihakBerkepentingan(rs.getString("NAMA"));
				
				//v.addElement(htpPermohonan);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return beban;
	}
	
	@Override
	public Vector getSenaraiFailMengikutSubUrusan(
			String idSubUrusan, String tajuk, String noFail, String idNegeri)
		throws Exception {
/*
 * 		69 - BORANG 16O KTN
 * 		68 - BORANG 16H KTN
 * 		67 - BORANG 16G KTN
 * 		66 - BORANG 16D KTN
 */

	    Db db = null;
	    Vector fails = new Vector();
	    String sql = "";
	    String Like = "";
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = " SELECT F.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL, S.KETERANGAN, N.NAMA_NEGERI, N.KOD_MAMPU "+ 
	      	" FROM TBLPFDFAIL F, TBLRUJSTATUS S, TBLRUJNEGERI N "+
	      	" WHERE F.ID_STATUS=S.ID_STATUS AND F.ID_URUSAN = 108 "+
	      	" AND N.ID_NEGERI = F.ID_NEGERI  AND F.ID_STATUS <> 999 ";
	      sql +=" AND F.TAJUK_FAIL LIKE '%"+tajuk+"%' ";
	      sql +=" AND F.NO_FAIL LIKE '%"+noFail+"%' ";
	      if(!idSubUrusan.equals("")){
	    	  sql +=" AND F.ID_SUBURUSAN = "+idSubUrusan;  	  
	      }
	      if(!idNegeri.equals("")){
	    	  sql +=" AND F.ID_NEGERI = "+idNegeri;
	      }
	      sql +=" ORDER BY F.TARIKH_KEMASKINI";
	      	      
	      myLog.info(sql);
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
	    	  fails.addElement(h);
	    	  bil++;
	      }
	      return fails;
	    
	    }catch(Exception e){
	    	e.printStackTrace();
	    
	    }finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	      return fails;
 
	  }
	
	@Override
	public Vector getSemak(String idFail)throws Exception {
	    Db db = null;
	    Vector fails = new Vector();
	    String sql = "";
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("f.id_Fail");
	      r.add("f.id_Negeri");
	      r.add("f.id_Kementerian");
	      r.add("f.id_Suburusan");
	      r.add("f.tajuk_Fail");
	      r.add("f.no_Fail");
	      r.add("f.tarikh_Daftar_Fail");
	      r.add("f.id_Fail",idFail);	      
	      sql = r.getSQLSelect("Tblpfdfail f");
	      ResultSet rs = stmt.executeQuery(sql);
	      myLog.info(sql);
	      
	      Hashtable h;
	      while (rs.next()) {
	    	  h = new Hashtable();
		      h.put("idFail", rs.getString("id_Fail"));
	    	  h.put("idNegeri", rs.getString("id_Negeri"));
	    	  h.put("idKementerian", rs.getString("id_Kementerian"));
	    	  h.put("idSuburusan", rs.getString("id_Suburusan"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("noFail", rs.getString("no_Fail"));
	    	  h.put("tarikhDaftarFail", Format.format(rs.getDate("tarikh_Daftar_Fail")));
	    	  fails.addElement(h);
	      }
	      return fails;

	    }catch(Exception e){
	    	  e.printStackTrace();
	    	  
	    }finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	      return fails;
	    
	  }
	
	@Override
	public Vector getPermohonans(String idFail,String noFail, String carian)throws Exception {
	    Db db = null;
	    Vector permohonans = new Vector();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String Select = " " +
	      		"SELECT P.ID_PERMOHONAN" +
	      		" ,F.ID_NEGERI,PP.NO_RUJUKAN_LAIN,F.ID_FAIL " +
	      		" ,NVL((SELECT ID_FAILLAMA FROM TBLHTPFAILMAPPING WHERE ID_FAIL=F.ID_FAIL ),'') FAIL_LAMA" +
	      		" ,NVL(TO_CHAR(P.TARIKH_TERIMA,'dd/mm/yyyy'),'01/01/1900') TARIKH_TERIMA, S.KETERANGAN  ";
	      String From = "" +
	      		" FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLHTPPERMOHONAN PP" +
	      		" ,Tblrujsuburusanstatusfail SF, Tblrujsuburusanstatus SS, Tblrujstatus S ";
	      String Where = "" +
	      		" WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN" +
	      		" AND SF.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
	      		" AND SF.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS "+
	      		" AND SS.ID_STATUS = S.ID_STATUS AND SF.AKTIF = '1' " +
	      		" AND ( P.ID_STATUS is null OR P.ID_STATUS <> 999) "+
	      		"  ";

	      		 if(noFail.equalsIgnoreCase("")){
	      			 Where += " AND PP.NO_RUJUKAN_LAIN LIKE '%"+noFail+"%' ";
	      		 }
	      		 Where +=" AND F.ID_FAIL = "+idFail;
	      		 //Where +=" AND p.nama LIKE '%"+carian+"%'";
	      sql = Select + From + Where +" ORDER BY P.TARIKH_TERIMA DESC,P.ID_PERMOHONAN DESC ";
	      myLog.info("getPermohonans : sql=" + sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPermohonan", rs.getString("id_Permohonan"));
	    	  //h.put("nama", Utils.isNull(rs.getString("nama")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("idNegeri", rs.getString("id_Negeri"));
	    	  h.put("noFailLain", Utils.isNull(rs.getString("NO_RUJUKAN_LAIN")));
	       	  h.put("idFail", Utils.isNull(rs.getString("ID_FAIL")));
	       	  h.put("idFailLama", Utils.isNull(rs.getString("FAIL_LAMA")));
	       	  h.put("tarikhTerima", rs.getString("TARIKH_TERIMA").equals("01/01/1900")?"":rs.getString("TARIKH_TERIMA"));
	       	  
	       	  permohonans.addElement(h);
	    	  bil++;
	      }
	    
	    } catch(Exception e){
	    	e.printStackTrace();
	    
	    } finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	    return permohonans;
	  
	}
	
	
}
