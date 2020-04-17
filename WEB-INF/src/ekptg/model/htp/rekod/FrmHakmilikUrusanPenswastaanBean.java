package ekptg.model.htp.rekod;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;

public class FrmHakmilikUrusanPenswastaanBean implements IHakmilikUrusan {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmHakmilikUrusanPenswastaanBean.class);
	private static Vector<Hashtable<String,String>> listCarianHakmilikDanRizab = null;
 	private IHtp iHTP = null;  
	private String sql = "";
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	public Vector<Hashtable<String,String>> getMaklumat(String idHakmilik)throws Exception {
	    Db db = null;
	    listCarianHakmilikDanRizab = new Vector<Hashtable<String,String>>();
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();  
	    	sql = "SELECT TPH.NO_HAKMILIK,TPH.NO_WARTA " +
	 			" ,( SELECT CASE "+
	 			" 	WHEN RJH.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	 			"		ELSE RJH.KOD_JENIS_HAKMILIK "+
	 			" 	END AS JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH " +
	 			" 	WHERE RJH.ID_JENISHAKMILIK = TPH.ID_JENISHAKMILIK " +
	    		" ) JENIS_HAKMILIK "+
	    		" ,NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=TPH.ID_LOT),'') JENIS_LOT "+
	            " , TPH.NO_LOT "+
	            " , F.NO_FAIL, F.TAJUK_FAIL "+
	            " , NVL(( SELECT RT.KETERANGAN FROM TBLHTPRUJTINDAKAN RT " +
	            " WHERE RT.STATUS_SAH = TPHU.TINDAKAN_LANJUT),'TIADA MAKLUMAT') TINDAKAN "+
	             " FROM TBLHTPHAKMILIKURUSAN TPHU, TBLHTPHAKMILIK TPH, TBLPFDFAIL F, TBLPERMOHONAN P " +
	             " WHERE TPHU.PEGANGAN_HAKMILIK = TPH.PEGANGAN_HAKMILIK "+
	             " AND TPHU.ID_PERMOHONAN = P.ID_PERMOHONAN "+
	             " AND P.ID_FAIL = F.ID_FAIL "+
	             " AND F.ID_URUSAN = '4' " +
	             " AND TPH.ID_HAKMILIK = '"+idHakmilik+"'"+
	             "";	          
			sql = sql +" ORDER BY F.NO_FAIL DESC ";
			//myLog.debug("getMaklumat:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			Hashtable<String,String> h;
	      	int bil = 1;
	      	while (rs.next()) {
	      		h = new Hashtable<String,String>();
	      		h.put("bil", String.valueOf(bil));
	      		h.put("noFail",rs.getString("NO_FAIL"));
	      		h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
	      		h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	      		h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
	      		h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
	      		h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	      		h.put("tindakan", rs.getString("TINDAKAN"));
	      		if(rs.getString("NO_HAKMILIK")!= null){
	      			h.put("jenisTanah","M");
	      		}else{
	      			h.put("jenisTanah","R");
	      		}
	      		if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
	      			h.put("jenisTanah","X");
	      		}
	      		listCarianHakmilikDanRizab.addElement(h);
	      		bil++;
	    	  
	      	}
	      	
	    } catch (Exception e) {
	    	getIHTP().getErrorHTML(e.toString());

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return listCarianHakmilikDanRizab;
	    
	}	
	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
		
	}
	
	
}
