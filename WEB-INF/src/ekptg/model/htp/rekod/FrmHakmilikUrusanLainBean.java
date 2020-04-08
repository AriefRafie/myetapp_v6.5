package ekptg.model.htp.rekod;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;

public class FrmHakmilikUrusanLainBean implements IHakmilikUrusan {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmHakmilikUrusanLainBean.class);
	private static Vector<Hashtable<String,String>> senaraiTanahBerurusan = null;
 	private IHtp iHTP = null;  
	private String sql = "";
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	public Vector<Hashtable<String,String>> getMaklumat(String idHakmilik) throws Exception {
	    Db db = null;
	    senaraiTanahBerurusan = new Vector<Hashtable<String,String>>();
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();  
	    	sql = "SELECT DISTINCT " +
	    			" F.NO_FAIL, F.TAJUK_FAIL,RU.NAMA_URUSAN,F.TARIKH_DAFTAR_FAIL, RS.KETERANGAN "+
	    			" FROM TBLPFDFAIL F, TBLPERMOHONAN P,TBLRUJURUSAN RU " +
	    			" , TBLPHPHAKMILIKPERMOHONAN HPH, TBLHTPHAKMILIK MT,TBLHTPHAKMILIKAGENSI MTA,TBLRUJSTATUS RS " +
	    			" WHERE F.ID_FAIL = P.ID_FAIL " +
	    			" AND F.ID_URUSAN = RU.ID_URUSAN " +
	    			" AND P.ID_PERMOHONAN = HPH.ID_PERMOHONAN " +
	    			" AND HPH.ID_HAKMILIKAGENSI = MTA.ID_HAKMILIKAGENSI "+
	    			" AND MTA.ID_HAKMILIK = MT.ID_HAKMILIK " +
	    			" AND P.ID_STATUS=RS.ID_STATUS " +
	    			" AND MT.ID_HAKMILIK = '"+idHakmilik+"'"+
	             	"";	          
			sql = sql +" ORDER BY F.TARIKH_DAFTAR_FAIL DESC ";
			myLog.info("getMaklumat:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			Hashtable<String,String> h;
	      	int bil = 1;
	      	while (rs.next()) {
	      		h = new Hashtable<String,String>();
	      		h.put("bil", String.valueOf(bil)+".");
	      		h.put("noFail",rs.getString("NO_FAIL"));
	      		h.put("tujuan", rs.getString("TAJUK_FAIL"));
	      		h.put("urusan", rs.getString("NAMA_URUSAN"));
	      		h.put("tindakan", rs.getString("KETERANGAN"));
	      		senaraiTanahBerurusan.addElement(h);
	      		bil++;
	    	  
	      	}
	      	
	    } catch (Exception e) {
	    	getIHTP().getErrorHTML(e.toString());

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return senaraiTanahBerurusan;
	    
	}	
	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
		
	}
	
	
}
