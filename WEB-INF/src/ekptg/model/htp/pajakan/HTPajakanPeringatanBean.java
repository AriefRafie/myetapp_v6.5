package ekptg.model.htp.pajakan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pajakan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.utils.IPeringatan;

public class HTPajakanPeringatanBean implements IPeringatan {
	
	static Logger myLog = Logger.getLogger(HTPajakanPeringatanBean.class);
    private Db db = null;
    private String sql = "";

	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	Pajakan pajakan = null;
	Pemohon pemohon = null;
	
	@Override
	public Vector getSenaraiPeringatanHari(String search
		,String idUrusan,String tbl, String tarikh,int bilHari) throws Exception {
		 Vector list = null;
	    //SELECT PA.ID_PERMOHONAN,PA.TARIKH_PATUT_BAYAR,TO_CHAR((PA.TARIKH_PATUT_BAYAR - 20),'dd/MM') PATUT_BAYARF
	    //,PE.NAMA_PEMOHON NAMA,PE.EMEL
	    //FROM TBLHTPPAJAKAN PA,TBLHTPPEMOHON PE
	    //WHERE  
	    //PA.ID_PERMOHONAN = PE.ID_PERMOHONAN
	    //AND PA.TARIKH_PATUT_BAYAR IS NOT NULL
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      String tblBaru = tbl.equals("")?"TBLHTPULASANKJP":tbl;
	      r.add("PA.TARIKH_PATUT_BAYAR");
	      r.add("F.NO_FAIL");
	      r.add("F.TAJUK_FAIL TUJUAN");
	      r.add("PE.NAMA_PEMOHON NAMA");
	      r.add("PE.EMEL");
//	      r.add("PE.EMEL");
	      r.add("P.ID_PERMOHONAN");
	      r.add("F.ID_FAIL",r.unquote("P.ID_FAIL"));
	      r.add("P.ID_PERMOHONAN",r.unquote("PA.ID_PERMOHONAN"));
	      r.add("P.ID_PERMOHONAN",r.unquote("PE.ID_PERMOHONAN"));
	      r.add("F.ID_URUSAN",idUrusan);
	      r.add("TO_CHAR((PA.TARIKH_PATUT_BAYAR - "+bilHari+"),'dd/MM')",tarikh);
	      //r.add("PA.TARIKH_PATUT_BAYAR",r.unquote(NOT NULL),"IS");
	      sql = r.getSQLSelect("TBLPERMOHONAN P, TBLPFDFAIL F,TBLHTPPAJAKAN PA,TBLHTPPEMOHON PE");
//		  sql += " AND P.ID_PERMOHONAN IN " +
//		  		" (SELECT TPU.ID_PERMOHONAN FROM " +
//		  		""+tblBaru+" TPU " +
//		  		" WHERE " +
//		  		" TPU.TARIKH_TERIMA IS NULL " +
//		  		" AND (SYSDATE-TPU.TARIKH_HANTAR)>15) ";
	      myLog.info(sql);
	      ResultSet rs = stmt.executeQuery(sql);
//	      list = new Vector<Permohonan>();
	      list = new Vector();
	      while (rs.next()) {
	    	  Hashtable hash = new Hashtable();
//	    	  pajakan = new Pajakan();
//	    	  permohonan = new Permohonan();
//	    	  fail = new PfdFail();	    	  
//	    	  fail.setNoFail(rs.getString("NO_FAIL"));
//	    	  permohonan.setTujuan(rs.getString("TUJUAN"));
//	    	  permohonan.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
//	    	  permohonan.setPfdFail(fail);    	  
//	    	  pemohon = new Pemohon();
//	    	  pemohon.setNama(rs.getString("NAMA"));
//	    	  pemohon.setEmel(rs.getString("EMEL"));		
//	    	  pajakan.setTarikhBayarString(rs.getString("TARIKH_PATUT_BAYAR"));
//	    	  pajakan.setPemohon(pemohon);
//	    	  pajakan.setPermohonan(permohonan);
//				htpPermohonan.setPermohonan(permohonan);
	    	  hash.put("noFail",rs.getString("NO_FAIL"));
	    	  hash.put("tujuan",rs.getString("TUJUAN"));
	    	  hash.put("nama",rs.getString("NAMA"));
	    	  hash.put("emel",rs.getString("EMEL"));
	    	  hash.put("tarikhBayar",rs.getString("TARIKH_PATUT_BAYAR"));
	    	  list.addElement(hash);
				
	      }
//	      return list;
	      
	    } catch (Exception ex) {
			ex.printStackTrace();
	    } finally {
	      if (db != null) db.close();
	    }
	      return list;

	}	
	
	
}
