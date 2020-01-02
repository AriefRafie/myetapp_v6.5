package ekptg.report.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class NotaPerbicaraan extends EkptgReportServlet
{
  Vector bayaran = null;
  Vector bayaranPusaka = null;
  FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	private static Logger myLog = Logger.getLogger(NotaPerbicaraan.class);

  public NotaPerbicaraan()
  {
    super.setReportName("NotaPerbicaraan");
    super.setFolderName("ppk");
  }

  public Vector getBayaran(String idfail) throws Exception
  {
	  String sql = "";
	  Db db = null;
	  SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
	  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	  try {
		  Vector localVector;
		  db = new Db();
		  this.bayaran = new Vector();

          sql = "SELECT ID_PERMOHONAN, (MAX(NO_RESIT_PERINTAH)) AS NO_RESIT_PERINTAH" +
                  ", (MAX(NO_RESIT_PERINTAHTAMB)) AS NO_RESIT_PERINTAHTAMB" +
                  ", (MAX(TO_DATE(TARIKH_BAYARAN_PERINTAH,'dd/MM/yyyy'))) AS TARIKH_BAYARAN_PERINTAH" +
                  ", (MAX(TO_DATE(TARIKH_BAYARAN_PERINTAHTAMB,'dd/MM/yyyy'))) AS TARIKH_BAYARAN_PERINTAHTAMB" +
                  ", (MAX(JUMLAH_BAYARAN_PERINTAH))  AS JUMLAH_BAYARAN_PERINTAH" +
                  ", (MAX(JUMLAH_BAYARAN_PERINTAHTAMB))  AS JUMLAH_BAYARAN_PERINTAHTAMB" +
                  ", (MAX(JUMLAH_BAYARAN_CUKAIPUSAKA))  AS JUMLAH_BAYARAN_CUKAIPUSAKA " +
                  "    FROM (SELECT TBLPPKBAYARAN.ID_JENISBAYARAN,TBLPPKPERMOHONAN.ID_PERMOHONAN " +
                  "    , CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 24 THEN TBLPPKBAYARAN.NO_RESIT " +
                  "    END NO_RESIT_PERINTAH" +
                  ", CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 25 THEN TBLPPKBAYARAN.NO_RESIT " +
                  "    END AS NO_RESIT_PERINTAHTAMB" +
                  ", CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 24 THEN TO_CHAR(TBLPPKBAYARAN.TARIKH_BAYARAN,'DD/MM/YYYY') " +
                  "    ELSE '' " +
                  "END AS TARIKH_BAYARAN_PERINTAH" +
                  ", CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 25 THEN TO_CHAR(TBLPPKBAYARAN.TARIKH_BAYARAN,'DD/MM/YYYY') " +
                  "ELSE '' " +
                  "END AS TARIKH_BAYARAN_PERINTAHTAMB," +
                  " CASE " +                                        
                  " WHEN TBLPPKPERMOHONAN.seksyen=8 and TBLPPKPERMOHONAN.JUMLAH_HARTA_TARIKHMOHON <= 1000 THEN 'RM '||replace(TO_CHAR(ROUND(10,1),'999,999,999.99'),' ') " +
                  " WHEN TBLPPKPERMOHONAN.seksyen=8 and TBLPPKPERMOHONAN.JUMLAH_HARTA_TARIKHMOHON BETWEEN 1001 AND 50000 THEN 'RM '||replace(TO_CHAR(ROUND(30,1),'999,999,999.99'),' ') " +
                  " WHEN TBLPPKPERMOHONAN.seksyen=8 and TBLPPKPERMOHONAN.JUMLAH_HARTA_TARIKHMOHON >= 50001 THEN 'RM '||TRIM(TO_CHAR(((0.2/100)* ((TBLPPKPERMOHONAN.JUMLAH_HARTA_TARIKHMOHON) - (replace((select nvl(sum(tblppkha.nilai_ha_tarikhmohon),0) from tblppkha, tblppkpermohonansimati " +
                  " where TBLPPKPERMOHONAN.id_permohonan = tblppkpermohonansimati.id_permohonan " +
                  " and tblppkpermohonansimati.id_simati = tblppkha.id_simati " +
                  " and tblppkha.id_jenisha = '98' and rownum<=1),'')) )),'999,999,999.99')) " +                        
                  " WHEN (TBLPPKPERMOHONAN.batal_kuasa_pentadbir ='Y' or TBLPPKPERMOHONAN.batal_p_amanah ='Y') and TBLPPKPERMOHONAN.harta_tinggal = 'Y' and TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON <= 1000 THEN 'RM '||replace(TO_CHAR(ROUND(40,1),'999,999,999.99'),' ')" +
                  " WHEN (TBLPPKPERMOHONAN.batal_kuasa_pentadbir ='Y' or TBLPPKPERMOHONAN.batal_p_amanah ='Y') and TBLPPKPERMOHONAN.harta_tinggal = 'Y' and TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON BETWEEN 1001 AND 50000 THEN 'RM '||replace(TO_CHAR(ROUND(60,1),'999,999,999.99'),' ')" +
                  " WHEN (TBLPPKPERMOHONAN.batal_kuasa_pentadbir ='Y' or TBLPPKPERMOHONAN.batal_p_amanah ='Y') and TBLPPKPERMOHONAN.harta_tinggal = 'Y' and TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON >= 50001 THEN 'RM '|| ROUND((((0.2/100) * TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON) + 30),1)   " +                                            
                  " WHEN (TBLPPKPERMOHONAN.batal_kuasa_pentadbir ='Y' or TBLPPKPERMOHONAN.batal_p_amanah ='Y') and TBLPPKPERMOHONAN.harta_tinggal = 'T' THEN 'RM '||replace(TO_CHAR(ROUND(30,1),'999,999,999.99'),' ')" +
                  " WHEN (TBLPPKPERMOHONAN.batal_kuasa_pentadbir ='Y' or TBLPPKPERMOHONAN.batal_p_amanah ='Y') and TBLPPKPERMOHONAN.harta_tinggal is null THEN 'RM '||replace(TO_CHAR(ROUND(30,1),'999,999,999.99'),' ')" +              
                  " WHEN (TBLPPKPERMOHONAN.batal_kuasa_pentadbir ='T' or TBLPPKPERMOHONAN.batal_kuasa_pentadbir is null) and TBLPPKPERMOHONAN.harta_tinggal ='Y' and TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON <= 1000 THEN 'RM '||replace(TO_CHAR(ROUND(10,1),'999,999,999.99'),' ')" +
                  " WHEN (TBLPPKPERMOHONAN.batal_kuasa_pentadbir ='T' or TBLPPKPERMOHONAN.batal_kuasa_pentadbir is null) and TBLPPKPERMOHONAN.harta_tinggal ='Y' and TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON BETWEEN 1001 AND 50000 THEN 'RM '||replace(TO_CHAR(ROUND(30,1),'999,999,999.99'),' ')" +
                  " WHEN (TBLPPKPERMOHONAN.batal_kuasa_pentadbir ='T' or TBLPPKPERMOHONAN.batal_kuasa_pentadbir is null) and TBLPPKPERMOHONAN.harta_tinggal ='Y' and TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON >= 50001 THEN 'RM '||replace(to_char(ROUND((0.2/100)*TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON,1),'999,999,999.99'),' ')" +
                  " WHEN (TBLPPKPERMOHONAN.batal_p_amanah ='T' or TBLPPKPERMOHONAN.batal_p_amanah is null) and TBLPPKPERMOHONAN.harta_tinggal ='Y' and TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON <= 1000 THEN 'RM '||replace(TO_CHAR(ROUND(10,1),'999,999,999.99'),' ')" +
                  " WHEN (TBLPPKPERMOHONAN.batal_p_amanah ='T' or TBLPPKPERMOHONAN.batal_p_amanah is null) and TBLPPKPERMOHONAN.harta_tinggal ='Y' and TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON BETWEEN 1001 AND 50000 THEN 'RM '||replace(TO_CHAR(ROUND(30,1),'999,999,999.99'),' ')" +
                  " WHEN (TBLPPKPERMOHONAN.batal_p_amanah ='T' or TBLPPKPERMOHONAN.batal_p_amanah is null) and TBLPPKPERMOHONAN.harta_tinggal ='Y' and TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON >= 50001 THEN 'RM '||replace(to_char(ROUND((0.2/100)*TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON,1),'999,999,999.99'),' ')" +
                  " END AS JUMLAH_BAYARAN_PERINTAH, " +
                  " replace((select nilai_ha_tarikhmohon from tblppkha, tblppkpermohonansimati " +
                  " where TBLPPKPERMOHONAN.id_permohonan = tblppkpermohonansimati.id_permohonan" +
                  " and tblppkpermohonansimati.id_simati = tblppkha.id_simati" +
                  " and tblppkha.id_jenisha = '98' and rownum<=1),'')*(0.2/100) as ARBbersih" +
                  ", CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 25 AND TBLPPKBAYARAN.JUMLAH_BAYARAN >0 THEN (replace(to_char(TBLPPKBAYARAN.JUMLAH_BAYARAN,'999,999,999.99'),' ', '')) " +
                  "ELSE '' " +
                  "END AS JUMLAH_BAYARAN_PERINTAHTAMB" +
                  ", CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 26 AND TBLPPKBAYARAN.JUMLAH_BAYARAN >0 THEN (replace(to_char(TBLPPKBAYARAN.JUMLAH_BAYARAN,'999,999,999.99'),' ', '')) " +
                  "ELSE '' " +
                  "END AS JUMLAH_BAYARAN_CUKAIPUSAKA, ROW_NUMBER() " +
                  "OVER ( PARTITION BY TBLPPKPERMOHONAN.ID_PERMOHONAN  ORDER BY ROWNUM) RN " +
                  "FROM TBLPPKBAYARAN, TBLPPKPERMOHONAN, TBLPFDFAIL  " +
                  "WHERE TBLPPKBAYARAN.ID_PERMOHONAN(+) = TBLPPKPERMOHONAN.ID_PERMOHONAN  " +
                  "AND TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL  " +
                  "AND TBLPPKPERMOHONAN.ID_FAIL= '" + 
                  idfail + "')" + 
                  " CONNECT  BY  ID_PERMOHONAN = PRIOR ID_PERMOHONAN" + 
                  " AND  RN = PRIOR RN+1" + 
                  " START WITH RN = 1" + 
                  " GROUP BY ID_PERMOHONAN";
      
		  Statement stmt = db.getStatement();
		//  myLog.info("sql bayarannnnnnnnnnnnn:"+sql);
		  ResultSet rs = stmt.executeQuery(sql);
		
		  while (rs.next()) {
			  Hashtable h = new Hashtable();     
			  h.put("noResitPerintah", getValue(rs.getString("NO_RESIT_PERINTAH")));
			  h.put("noResitPerintahTmbh", getValue(rs.getString("NO_RESIT_PERINTAHTAMB")));
			
			  h.put("tkhByrnPerintah", getValue(rs.getString("TARIKH_BAYARAN_PERINTAH")==null?"":rs.getString("TARIKH_BAYARAN_PERINTAH")));
			  h.put("tkhByrnPerintahTmbh", getValue(rs.getString("TARIKH_BAYARAN_PERINTAHTAMB")==null?"":rs.getString("TARIKH_BAYARAN_PERINTAHTAMB")));
			  h.put("jumByrnPerintah", getValue(rs.getString("JUMLAH_BAYARAN_PERINTAH")));
			  h.put("jumByrnPerintahTmbh", getValue(rs.getString("JUMLAH_BAYARAN_PERINTAHTAMB")));
			  h.put("jumByrnCukai", getValue(rs.getString("JUMLAH_BAYARAN_CUKAIPUSAKA")));
			
			  this.bayaran.addElement(h);
			  
			  
			/*  
			  System.out.print("NO_RESIT_PERINTAH"+rs.getString("NO_RESIT_PERINTAH"));
			  System.out.print("NO_RESIT_PERINTAHTAMB"+rs.getString("NO_RESIT_PERINTAHTAMB"));
			  System.out.print("TARIKH_BAYARAN_PERINTAH"+rs.getString("TARIKH_BAYARAN_PERINTAH"));
			  System.out.print("TARIKH_BAYARAN_PERINTAHTAMB"+rs.getString("TARIKH_BAYARAN_PERINTAHTAMB"));
			  System.out.print("JUMLAH_BAYARAN_PERINTAH"+rs.getString("JUMLAH_BAYARAN_PERINTAH"));
			  System.out.print("JUMLAH_BAYARAN_PERINTAHTAMB"+rs.getString("JUMLAH_BAYARAN_PERINTAHTAMB"));
			  System.out.print("JUMLAH_BAYARAN_CUKAIPUSAKA"+rs.getString("JUMLAH_BAYARAN_CUKAIPUSAKA"));*/
        
		  }

		  return this.bayaran;
	  } finally {
		  if (db != null)
			  db.close();
	  }
  
  }

  public String getValue(String txt) {
	  if (txt == null || txt.trim() == "" || txt.equals(" ")){
		  
		  return "";
		  
	  } else return txt;
  }
  
  public void doProcessing(HttpServletRequest request, HttpServletResponse response
		  , ServletContext context, Map parameters) throws Exception {
    String idfail = "";
    if (parameters.get("idfail") != null) {
      idfail = parameters.get("idfail").toString();
    }
    
    parameters.put("idfail", idfail);
    parameters.put("idperbicaraan", getIdBicara(idfail));
	//  myLog.info("idfail:"+idfail);
//
    this.bayaranPusaka = new Vector();
    this.bayaranPusaka = getBayaran(idfail);
    Hashtable hB = (Hashtable)this.bayaranPusaka.get(0);
//    myLog.info(hB);
    if (!hB.get("noResitPerintah").equals(null)){
    	parameters.put("no_Resit_perintah", (String)hB.get("noResitPerintah"));
    }else{
    	parameters.put("no_Resit_perintah", "");
    }
   // myLog.info("noResitPerintah:"+hB.get("noResitPerintah"));
   
//    
    if(!hB.get("noResitPerintahTmbh").equals(null)){
        parameters.put("no_Resit_perintahTmbh", (String)hB.get("noResitPerintahTmbh"));
    }else{
        parameters.put("no_Resit_perintahTmbh", "");
    }
   // myLog.info("noResitPerintahTmbh:"+hB.get("noResitPerintahTmbh"));
   
//    
    if(!hB.get("tkhByrnPerintah").equals(null)){
        parameters.put("tarikh_Byrn_perintah", (String)hB.get("tkhByrnPerintah"));
    }else{
        parameters.put("tarikh_Byrn_perintah","");
    }
   // myLog.info("tkhByrnPerintah:"+hB.get("tkhByrnPerintah"));
    
//    
    if(!hB.get("tkhByrnPerintahTmbh").equals(null)){
        parameters.put("tarikh_Byrn_perintahTmbh", (String)hB.get("tkhByrnPerintahTmbh"));
    }else{
        parameters.put("tarikh_Byrn_perintahTmbh", "");
    }
  //  myLog.info("tkhByrnPerintahTmbh:"+hB.get("tkhByrnPerintahTmbh"));
    
//    
    if(!hB.get("jumByrnPerintah").equals(null)){
        parameters.put("jumlah_Byrn_perintah", (String)hB.get("jumByrnPerintah"));
    }else{
        parameters.put("jumlah_Byrn_perintah", "");
    }
   // myLog.info("jumByrnPerintah:"+hB.get("jumByrnPerintah"));
    
//    
    if(!hB.get("jumByrnPerintahTmbh").equals(null)){
        parameters.put("jumlah_Byrn_perintahTmbh", (String)hB.get("jumByrnPerintahTmbh"));
    }else{
        parameters.put("jumlah_Byrn_perintahTmbh", "");
    }
  //  myLog.info("jumByrnPerintahTmbh:"+hB.get("jumByrnPerintahTmbh"));
    
    
    if(!hB.get("jumByrnCukai").equals(null)){
    	 parameters.put("jumlah_Byrn_Cukai", (String)hB.get("jumByrnCukai"));
    }else{
    	 parameters.put("jumlah_Byrn_Cukai", "");
    }
   // myLog.info("jumByrnCukai:"+hB.get("jumByrnCukai"));
    
    //myLog.info(":::::::::::::::::END:::::::::::::::");
    
	
    
    
    
  }
  
  public String getIdBicara(String idfail) throws Exception{
	  String sql = "";
	  String returnValue = "";
	  Db db = null;
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  try {
		  db = new Db();
		  sql = "SELECT PKP.ID_PERBICARAAN " +
		  		" from " +
		  		" Tblpfdfail f,Tblppkpermohonan p,Tblppkperbicaraan pkp,Tblppkkeputusanpermohonan pkkp " +
		  		" where p.id_fail = f.id_fail " +
		  		" and PKKP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
		  		" and PKKP.ID_KEPUTUSANPERMOHONAN = PKP.ID_KEPUTUSANPERMOHONAN  " +
		  		" and f.id_fail= '" + 
		  		idfail + "'" + 
		  		" order by PKP.TARIKH_BICARA ";    
		  Statement stmt = db.getStatement();
		 // myLog.info("sql:"+sql);
		  ResultSet rs = stmt.executeQuery(sql);
		  while (rs.next()) {
			  returnValue = rs.getString("ID_PERBICARAAN");     
		  }

		  return returnValue;
	  } finally {
		  if (db != null)
			  db.close();
	  }
  
  }
  
  
}