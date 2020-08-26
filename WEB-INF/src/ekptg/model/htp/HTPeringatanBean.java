package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class HTPeringatanBean implements IHTPeringatan {
	
	static Logger myLog = Logger.getLogger(HTPeringatanBean.class);
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	
	@Override
	public Vector<HtpPermohonan> getSenaraiPeringatanBayaran(String search,String idUrusan,String tahunBayaran) 
		throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      r.add("F.ID_FAIL");
	      r.add("F.NO_FAIL");
	      r.add("F.TAJUK_FAIL TUJUAN");
	      r.add("P.ID_PERMOHONAN");
	      //r.add("fdma.ARAHAN");
	      //r.add("TO_CHAR(fdma.tarikh_arahan,'dd/mm/yyyy') TARIKH_TAMAT");
	      //r.add("p.id_Permohonan");
	      
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      //r.add("p.id_Permohonan",r.unquote("FDMA.ID_PERINGATAN"));
	      //r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
	      //r.add("us.id_Status",r.unquote("s.id_Status"));
	      //r.add("rn.id_Negeri",r.unquote("f.id_Negeri"));

	      r.add("F.ID_URUSAN",idUrusan);
	      //r.add("FDMA.STATUS_TINDAKAN","0");
	      //r.add("f.id_Masuk",idMasuk);

	      //r.add("f.no_Fail","%"+search+"%","like");
	      r.add("P.ID_STATUS",71);
	      sql = r.getSQLSelect("TBLPERMOHONAN P, TBLPFDFAIL F");
	      //sql +=" AND (FDMA.tarikh_arahan - sysdate)< 151 AND (FDMA.tarikh_arahan - sysdate)>0 ";
		  sql += " AND P.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPBAYARAN " +
		  		"WHERE TO_CHAR(TARIKH_MULA,'yyyy')='"+tahunBayaran+"')"+
		  		" AND F.ID_SUBURUSAN in (7,18)";
	      myLog.info(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<HtpPermohonan> list = new Vector<HtpPermohonan>();

	      while (rs.next()) {
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("ID_FAIL"));
				fail.setNoFail(rs.getString("NO_FAIL"));

				permohonan.setTujuan(rs.getString("TUJUAN"));
	    	//h.put("keterangan", rs.getString("arahan"));
	    	//h.put("tarikhtamat", rs.getString("TARIKH_TAMAT"));
				//permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				//htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				//htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
			
				list.addElement(htpPermohonan);
				
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	    
	  }
	
	@Override
	public Vector<HtpPermohonan> getSenaraiPeringatanBayaranPer(String search,String idUrusan,String tahunBayaran)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      r.add("F.ID_FAIL");
	      r.add("F.NO_FAIL");
	      r.add("F.TAJUK_FAIL TUJUAN");
	      r.add("P.ID_PERMOHONAN");
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("F.ID_URUSAN",idUrusan);
	      //r.add("FDMA.STATUS_TINDAKAN","0");
	      //r.add("f.id_Masuk",idMasuk);

	      //r.add("f.no_Fail","%"+search+"%","like");
	      sql = r.getSQLSelect("TBLPERMOHONAN P, TBLPFDFAIL F");
	      //sql +=" AND (FDMA.tarikh_arahan - sysdate)< 151 AND (FDMA.tarikh_arahan - sysdate)>0 ";
		  sql += " AND P.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPBAYARAN " +
		  		"WHERE TO_CHAR(TARIKH_MULA,'yyyy')='"+tahunBayaran+"')" +
		  		" AND P.ID_PERMOHONAN IN ( "+search+")" +
		  				"";
	      myLog.info(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<HtpPermohonan> list = new Vector<HtpPermohonan>();

	      while (rs.next()) {
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("ID_FAIL"));
				fail.setNoFail(rs.getString("NO_FAIL"));

				permohonan.setTujuan(rs.getString("TUJUAN"));
	    	//h.put("keterangan", rs.getString("arahan"));
	    	//h.put("tarikhtamat", rs.getString("TARIKH_TAMAT"));
				//permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				//htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				//htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
			
				list.addElement(htpPermohonan);
				
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }

	@Override
	public Vector<HtpPermohonan> getPeringatanJenisBayaran(String search,String idUrusan,String tahunBayaran, String jenisBayaran)
		throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      r.add("F.ID_FAIL");
	      r.add("F.NO_FAIL");
	      r.add("F.TAJUK_FAIL TUJUAN");
	      r.add("P.ID_PERMOHONAN");
	      r.add("P.ID_FAIL",r.unquote("F.ID_FAIL"));
	      r.add("F.ID_URUSAN",idUrusan);
	      r.add("P.ID_STATUS",71);
	      sql = r.getSQLSelect("TBLPERMOHONAN P, TBLPFDFAIL F");
		  sql += " AND P.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPBAYARAN " +
		  		" WHERE " +
		  		" ID_JENISBAYARAN = "+jenisBayaran+" AND " +
		  		" TO_CHAR(TARIKH_RESIT,'yyyy') = TO_CHAR(SYSDATE,'yyyy') ) ";
		  		
		  		/**Remove this line. filter by tarikh mula. BUT NO TARIKH MULA IN SCREEN! */
		  		//" TO_CHAR(TARIKH_MULA,'yyyy')='"+tahunBayaran+"') ";
	      myLog.info(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<HtpPermohonan> list = new Vector<HtpPermohonan>();
	      while (rs.next()) {
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("ID_FAIL"));
				fail.setNoFail(rs.getString("NO_FAIL"));

				permohonan.setTujuan(rs.getString("TUJUAN"));
	    	//h.put("keterangan", rs.getString("arahan"));
	    	//h.put("tarikhtamat", rs.getString("TARIKH_TAMAT"));
				//permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				//htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				//htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
			
				list.addElement(htpPermohonan);
				
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  
	}
	
	@Override
	public Vector<HtpPermohonan> getPeringatanJenisBayaranPer(String search,String idUrusan,String tahunBayaran, String jenisBayaran)
		throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      if(search==null || search=="")
	      search = "0";	  
	      
	      r.add("F.ID_FAIL");
	      r.add("F.NO_FAIL");
	      r.add("F.TAJUK_FAIL TUJUAN");
	      r.add("P.ID_PERMOHONAN");
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("F.ID_URUSAN",idUrusan);
	      r.add("P.ID_STATUS","7");
	      sql = r.getSQLSelect("TBLPERMOHONAN P, TBLPFDFAIL F");
		  sql += " AND P.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPBAYARAN " +
		  		" WHERE " +
		  		" ID_JENISBAYARAN = "+jenisBayaran+" " +
		  		" AND TO_CHAR(TARIKH_RESIT,'yyyy') = TO_CHAR(SYSDATE,'yyyy') ) "+
		  		" AND P.ID_PERMOHONAN IN ("+search+")";
		  		
		  
	      //myLog.info("getPeringatanJenisBayaranPer:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<HtpPermohonan> list = new Vector<HtpPermohonan>();
	      while (rs.next()) {
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("ID_FAIL"));
				fail.setNoFail(rs.getString("NO_FAIL"));

				permohonan.setTujuan(rs.getString("TUJUAN"));
	    	//h.put("keterangan", rs.getString("arahan"));
	    	//h.put("tarikhtamat", rs.getString("TARIKH_TAMAT"));
				//permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				//htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				//htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
			
				list.addElement(htpPermohonan);
				
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
//	@Override
//	public Vector<HtpPermohonan> getSenaraiPeringatanHari(String search,String idUrusan,String tbl, String hantar,String terima)
//		throws Exception {
//	    Db db = null;
//	    String sql = "";
//	    try {
//	      db = new Db();
//	      Statement stmt = db.getStatement();
//	      SQLRenderer r = new SQLRenderer();
//	      String tblBaru = tbl.equals("")?"TBLHTPULASANKJP":tbl;
//	      r.add("F.ID_FAIL");
//	      r.add("F.NO_FAIL");
//	      r.add("F.TAJUK_FAIL TUJUAN");
//	      r.add("P.ID_PERMOHONAN");
//	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
//	      r.add("F.ID_URUSAN",idUrusan);
//	      sql = r.getSQLSelect("TBLPERMOHONAN P, TBLPFDFAIL F");
//		  sql += " AND P.ID_PERMOHONAN IN " +
//		  		" (SELECT TPU.ID_PERMOHONAN FROM " +
//		  		""+tblBaru+" TPU " +
//		  		" WHERE " +
//		  		" TPU.TARIKH_TERIMA IS NULL " +
//		  		" AND (SYSDATE-TPU.TARIKH_HANTAR)>15) ";
//	      myLog.info(sql);
//	      ResultSet rs = stmt.executeQuery(sql);
//	      Vector<HtpPermohonan> list = new Vector<HtpPermohonan>();
//	      while (rs.next()) {
//				permohonan = new Permohonan();
//				fail = new PfdFail();
//				htpPermohonan = new HtpPermohonan();
//				
//				fail.setIdFail(rs.getLong("ID_FAIL"));
//				fail.setNoFail(rs.getString("NO_FAIL"));
//
//				permohonan.setTujuan(rs.getString("TUJUAN"));
//				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
//				permohonan.setPfdFail(fail);
//				htpPermohonan.setPermohonan(permohonan);
//				list.addElement(htpPermohonan);
//				
//	      }
//	      return list;
//	      
//	    } finally {
//	      if (db != null) db.close();
//	    }
//	    
//	}	
	@Override
	public Vector<HtpPermohonan> getSenaraiPeringatan15(String search,String idUrusan,String tbl, String hantar,String terima)
		throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      String tblBaru = tbl.equals("")?"TBLHTPULASANKJP":tbl;
	      r.add("F.ID_FAIL");
	      r.add("F.NO_FAIL");
	      r.add("F.TAJUK_FAIL TUJUAN");
	      r.add("P.ID_PERMOHONAN");
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("F.ID_URUSAN",idUrusan);
	      sql = r.getSQLSelect("TBLPERMOHONAN P, TBLPFDFAIL F");
		  sql += " AND P.ID_PERMOHONAN IN " +
		  		" (SELECT TPU.ID_PERMOHONAN FROM " +
		  		""+tblBaru+" TPU " +
		  		" WHERE " +
		  		" TPU.TARIKH_TERIMA IS NULL " +
		  		" AND (SYSDATE-TPU.TARIKH_HANTAR)>15) ";
	      myLog.info(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<HtpPermohonan> list = new Vector<HtpPermohonan>();
	      while (rs.next()) {
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("ID_FAIL"));
				fail.setNoFail(rs.getString("NO_FAIL"));

				permohonan.setTujuan(rs.getString("TUJUAN"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				list.addElement(htpPermohonan);
				
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }	
	
	
}
