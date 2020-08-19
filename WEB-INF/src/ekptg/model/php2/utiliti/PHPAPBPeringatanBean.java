package ekptg.model.php2.utiliti;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pajakan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.utils.IPeringatan;

public class PHPAPBPeringatanBean implements IPeringatan {
	
	static Logger myLog = Logger.getLogger(PHPAPBPeringatanBean.class);
    private Db db = null;
    private String sql = "";
    private String sqlExt = "";

	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	Pajakan pajakan = null;
	Pemohon pemohon = null;

	@Override
	public Vector getSenaraiPeringatanHari(String search
		,String idUrusan,String tbl, String tarikh,int bilHari) throws Exception {
		 Vector list = null;
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 String tblBaru = tbl.equals("")?"TBLHTPULASANKJP":tbl;
			 if(tblBaru.equalsIgnoreCase("TBLPHPPMOHONNJDUALPERTAMA")) {
				 r.add("PA.TARIKH_KEPUTUSAN TARIKH"); 
			 }else {
				 r.add("PA.TARIKH_HANTAR TARIKH");				 
			 }
			 r.add("F.NO_FAIL");
			 r.add("F.TAJUK_FAIL TUJUAN");
//			 r.add("PE.NAMA_PEMOHON NAMA");
//			 r.add("PE.EMEL");
			 r.add("P.ID_PERMOHONAN");
			 r.add("F.ID_FAIL",r.unquote("P.ID_FAIL"));
			 r.add("P.ID_PERMOHONAN",r.unquote("PA.ID_PERMOHONAN"));
			 //r.add("F.ID_URUSAN",idUrusan);
			 if(tblBaru.equalsIgnoreCase("TBLPHPPMOHONNJDUALPERTAMA")) {
				 r.add("TO_CHAR((PA.TARIKH_KEPUTUSAN + "+bilHari+"),'dd/MM')",tarikh);
				 r.add("PA.FLAG_KEPUTUSAN","D"); 
//				 r.add("PA.TARIKH_KEPUTUSAN",r.unquote("NULL"),"IS NOT"); 
		 
				 sqlExt =  ","+tblBaru+" PA";
			 }else {
				 r.add("TO_CHAR((PA.TARIKH_HANTAR + PA.JANGKAMASA),'dd/MM')",tarikh);
				 sqlExt =  ",TBLPHPULASANTEKNIKAL PA";
			 }
			 sql = r.getSQLSelect("TBLPERMOHONAN P, TBLPFDFAIL F"+sqlExt);
			 myLog.info(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 list = new Vector();
			 while (rs.next()) {
				 Hashtable<String,String> hash = new Hashtable<String,String>();
				 hash.put("noFail",Utils.isNull(rs.getString("NO_FAIL")));
				 hash.put("tujuan",Utils.isNull(rs.getString("TUJUAN")));
//				 hash.put("nama",rs.getString("NAMA"));
//				 hash.put("emel",rs.getString("EMEL"));
				 hash.put("idPermohonan",rs.getString("ID_PERMOHONAN"));
				 list.addElement(hash);
				
			 }
	      
		 } catch (Exception ex) {
			ex.printStackTrace();
	    } finally {
	      if (db != null) db.close();
	    }
	      return list;

	}	
	
	
}
