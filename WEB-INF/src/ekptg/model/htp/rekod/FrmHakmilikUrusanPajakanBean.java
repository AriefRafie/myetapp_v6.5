package ekptg.model.htp.rekod;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;

public class FrmHakmilikUrusanPajakanBean implements IHakmilikUrusan {
	
	//private HakmilikInterface iHakmilik = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmHakmilikUrusanPajakanBean.class);
	private static Vector<Hashtable<String,String>> listCarianHakmilikDanRizab = null;
	//private HakMilik hakmilik = null;
 	private IHtp iHTP = null;  
 	//private PfdFail pfdFail = null;
 	//private Permohonan permohonan = null;
	private String sql = "";
	//private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private	Db db = null;
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	public Vector<Hashtable<String,String>> getMaklumat(String idHakmilik)throws Exception {
	//public Pajakan getMaklumat(String idHakmilik)throws Exception {
	    listCarianHakmilikDanRizab = new Vector<Hashtable<String,String>>();
	    //Pajakan	pajakan = new Pajakan();
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
	    		" ,NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=TPH.ID_LOT)" +
	    		" ,'') JENIS_LOT "+
	            " ,TPH.NO_LOT "+
	            " ,F.NO_FAIL, F.TAJUK_FAIL "+
	            " ,( SELECT HPP.NAMA_PEMOHON FROM TBLHTPPEMOHON HPP WHERE HPP.ID_PERMOHONAN = P.ID_PERMOHONAN" +
	            " ) PEMOHON "+
	            //" ,NVL(HPP.NO_SIRI,'') NO_SIRI "+
	            " ,NVL(TO_CHAR(TPP.TARIKH_MULA_PAJAKAN,'dd/mm/yyyy'),'01/01/1900') TARIKH_MULA_PERJANJIAN "+
	            " ,NVL(TO_CHAR(TPP.TARIKH_TAMATPAJAKAN,'dd/mm/yyyy'),'01/01/1900') TARIKH_TAMAT_PERJANJIAN "+
	            " ,NVL(TPP.KADAR_PAJAKAN,'0.00') KADAR "+
	            //" ,NVL(HPP.CAGARAN,'0.00') CAGARAN "+
	            " ,NVL(TPP.TEMPOH_PAJAKAN,'0') TEMPOH "+
	             " FROM TBLHTPHAKMILIKURUSAN TPHU, TBLHTPHAKMILIK TPH, TBLPFDFAIL F, TBLPERMOHONAN P " +
	             " , TBLHTPPAJAKAN TPP "+
	             " WHERE TPHU.PEGANGAN_HAKMILIK = TPH.PEGANGAN_HAKMILIK "+
	             " AND TPHU.ID_PERMOHONAN = P.ID_PERMOHONAN "+
	             " AND P.ID_FAIL = F.ID_FAIL "+
	             " AND F.ID_URUSAN = '3' " +
	             " AND P.ID_PERMOHONAN = TPP.ID_PERMOHONAN(+) " +
	             " AND TPH.ID_HAKMILIK = '"+idHakmilik+"'"+
	             "";     		
			sql = sql +" ORDER BY F.NO_FAIL DESC ";
			myLog.debug("Pajakan getMaklumat:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			Hashtable<String,String> h;
	      	int bil = 1;
	      	while (rs.next()) {
	      		myLog.info("bil="+bil);
	      		h = new Hashtable<String,String>();
	      		h.put("bil", bil+".");
	      		h.put("idHakmilik",idHakmilik);
	      		h.put("noFail",rs.getString("NO_FAIL"));
	      		h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
	      		h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	      		h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
	      		h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
	      		h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	      		h.put("pemohon", rs.getString("PEMOHON")== null?"":rs.getString("PEMOHON"));
	      		//h.put("noRujukan", Utils.isNull(rs.getString("NO_SIRI")));
	      		h.put("tarikhMula",rs.getString("TARIKH_MULA_PERJANJIAN"));
	      		h.put("tarikhTamat",rs.getString("TARIKH_TAMAT_PERJANJIAN"));
	      		h.put("kadar", String.valueOf(rs.getString("KADAR")== null?0.00:rs.getDouble("KADAR")));
	      		h.put("tempoh", rs.getString("TEMPOH"));	    	  
	      		listCarianHakmilikDanRizab.addElement(h);
	      		bil++;
	    	  
	      	}
	    } catch (Exception e) {
	    	getIHTP().getErrorHTML(e.toString());

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    //return listCarianHakmilikDanRizab;
	    return listCarianHakmilikDanRizab;
	    
	}	
	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
		
	}
	
	
	
}
