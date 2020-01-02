package ekptg.report.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import ekptg.report.EkptgReportServlet;

public class NotaPerbicaraan17 extends EkptgReportServlet
{
  Vector bayaran = null;
  Vector bayaranPusaka = null;

  public NotaPerbicaraan17()
  {
    super.setReportName("NotaPerbicaraan17");
    super.setFolderName("ppk");
  }

  public Vector getBayaran(String idfail) throws Exception
  {
    String sql = "";
    Db db = null;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    try {
      Vector localVector;
      db = new Db();
      this.bayaran = new Vector();

      sql = " SELECT ID_PERMOHONAN, (MAX(NO_RESIT_PERINTAH)) AS NO_RESIT_PERINTAH, (MAX(NO_RESIT_PERINTAHTAMB)) AS NO_RESIT_PERINTAHTAMB, (MAX(TARIKH_BAYARAN_PERINTAH)) AS TARIKH_BAYARAN_PERINTAH, (MAX(TARIKH_BAYARAN_PERINTAHTAMB)) AS TARIKH_BAYARAN_PERINTAHTAMB, (MAX(JUMLAH_BAYARAN_PERINTAH))  AS JUMLAH_BAYARAN_PERINTAH, (MAX(JUMLAH_BAYARAN_PERINTAHTAMB))  AS JUMLAH_BAYARAN_PERINTAHTAMB, (MAX(JUMLAH_BAYARAN_CUKAIPUSAKA))  AS JUMLAH_BAYARAN_CUKAIPUSAKA FROM (SELECT TBLPPKBAYARAN.ID_JENISBAYARAN,TBLPPKPERMOHONAN.ID_PERMOHONAN , CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 24 THEN TBLPPKBAYARAN.NO_RESIT END NO_RESIT_PERINTAH, CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 25 THEN TBLPPKBAYARAN.NO_RESIT END AS NO_RESIT_PERINTAHTAMB, CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 24 THEN TO_CHAR(TBLPPKBAYARAN.TARIKH_BAYARAN,'DD/MM/YYYY') ELSE '' END AS TARIKH_BAYARAN_PERINTAH, CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 25 THEN TO_CHAR(TBLPPKBAYARAN.TARIKH_BAYARAN,'DD/MM/YYYY') ELSE '' END AS TARIKH_BAYARAN_PERINTAHTAMB, CASE WHEN TBLPPKPERMOHONAN.BATAL_KUASA_PENTADBIR = 'Y' AND TBLPPKPERMOHONAN.HARTA_TINGGAL IN ('Y','T') THEN CASE WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON <= 1000 THEN replace(TO_CHAR(10+30,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON BETWEEN 1001 AND 50000 THEN replace(TO_CHAR(30+30,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON >= 50001 THEN replace(to_char((0.2/100)*(TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON+30),'999,999,999.99'),' ') END WHEN TBLPPKPERMOHONAN.BATAL_P_AMANAH = 'Y' AND TBLPPKPERMOHONAN.HARTA_TINGGAL IN ('Y','T') THEN CASE WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON <= 1000 THEN replace(TO_CHAR(10+30,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON BETWEEN 1001 AND 50000 THEN replace(TO_CHAR(30+30,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON >= 50001 THEN replace(to_char((0.2/100)*(TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON+30),'999,999,999.99'),' ') END WHEN TBLPPKPERMOHONAN.BATAL_P_AMANAH = 'T' AND TBLPPKPERMOHONAN.HARTA_TINGGAL ='Y' THEN CASE WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON <= 1000 THEN replace(TO_CHAR(10,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON BETWEEN 1001 AND 50000 THEN replace(TO_CHAR(30,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON >= 50001 THEN replace(to_char((0.2/100)*(TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON),'999,999,999.99'),' ') END WHEN TBLPPKPERMOHONAN.BATAL_KUASA_PENTADBIR = 'T' AND TBLPPKPERMOHONAN.HARTA_TINGGAL ='Y' THEN CASE WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON <= 1000 THEN replace(TO_CHAR(10,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON BETWEEN 1001 AND 50000 THEN replace(TO_CHAR(30,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON >= 50001 THEN replace(to_char((0.2/100)*(TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON),'999,999,999.99'),' ') END  WHEN TBLPPKPERMOHONAN.BATAL_KUASA_PENTADBIR = 'Y' AND TBLPPKPERMOHONAN.BATAL_P_AMANAH = 'Y' AND TBLPPKPERMOHONAN.HARTA_TINGGAL ='Y' THEN CASE WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON <= 1000 THEN replace(TO_CHAR(10+60,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON BETWEEN 1001 AND 50000 THEN replace(TO_CHAR(30+60,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON >= 50001 THEN replace(to_char((0.2/100)*(TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON+60),'999,999,999.99'),' ') END WHEN TBLPPKPERMOHONAN.BATAL_KUASA_PENTADBIR = 'T' AND TBLPPKPERMOHONAN.BATAL_P_AMANAH = 'T' AND TBLPPKPERMOHONAN.HARTA_TINGGAL ='Y' THEN CASE WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON <= 1000 THEN replace(TO_CHAR(10,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON BETWEEN 1001 AND 50000 THEN replace(TO_CHAR(30,'999,999,999.99'),' ') WHEN TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON >= 50001 THEN replace(to_char((0.2/100)*(TBLPPKPERMOHONAN.JUM_HARTA_TAMBAHAN_TKHMOHON),'999,999,999.99'),' ') END END AS JUMLAH_BAYARAN_PERINTAH, CASE WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 25 AND TBLPPKBAYARAN.JUMLAH_BAYARAN >0 THEN (replace(to_char(TBLPPKBAYARAN.JUMLAH_BAYARAN,'999,999,999.99'),' ', '')) ELSE '' END AS JUMLAH_BAYARAN_PERINTAHTAMB, CASE  WHEN TBLPPKBAYARAN.ID_JENISBAYARAN = 26 AND TBLPPKBAYARAN.JUMLAH_BAYARAN >0 THEN (replace(to_char(TBLPPKBAYARAN.JUMLAH_BAYARAN,'999,999,999.99'),' ', '')) ELSE '' END AS JUMLAH_BAYARAN_CUKAIPUSAKA, ROW_NUMBER() OVER ( PARTITION BY TBLPPKPERMOHONAN.ID_PERMOHONAN  ORDER BY ROWNUM) RN FROM TBLPPKBAYARAN, TBLPPKPERMOHONAN, TBLPFDFAIL WHERE TBLPPKBAYARAN.ID_PERMOHONAN(+) = TBLPPKPERMOHONAN.ID_PERMOHONAN AND TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL AND TBLPPKPERMOHONAN.ID_FAIL= '" + 
        idfail + "')" + 
        " CONNECT  BY  ID_PERMOHONAN = PRIOR ID_PERMOHONAN" + 
        " AND  RN = PRIOR RN+1" + 
        " START WITH RN = 1" + 
        " GROUP BY ID_PERMOHONAN";
      
      System.out.println("sqlNotaBicara---:"+sql);
      
      Statement stmt = db.getStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        Hashtable h = new Hashtable();
        h.put("noResitPerintah", (rs.getString("NO_RESIT_PERINTAH") == null) ? "" : rs.getString("NO_RESIT_PERINTAH"));
        h.put("noResitPerintahTmbh", (rs.getString("NO_RESIT_PERINTAHTAMB") == null) ? "" : rs.getString("NO_RESIT_PERINTAHTAMB"));
        h.put("tkhByrnPerintah", (rs.getString("TARIKH_BAYARAN_PERINTAH") != null) ? rs.getString("TARIKH_BAYARAN_PERINTAH"):"");
        h.put("tkhByrnPerintahTmbh", (rs.getString("TARIKH_BAYARAN_PERINTAHTAMB") != null) ? rs.getString("TARIKH_BAYARAN_PERINTAHTAMB"):"");
        h.put("jumByrnPerintah", (rs.getString("JUMLAH_BAYARAN_PERINTAH") == null) ? "" : rs.getString("JUMLAH_BAYARAN_PERINTAH"));
        h.put("jumByrnPerintahTmbh", (rs.getString("JUMLAH_BAYARAN_PERINTAHTAMB") == null) ? "" : rs.getString("JUMLAH_BAYARAN_PERINTAHTAMB"));
        h.put("jumByrnCukai", (rs.getString("JUMLAH_BAYARAN_CUKAIPUSAKA") == null) ? "" : rs.getString("JUMLAH_BAYARAN_CUKAIPUSAKA"));
        this.bayaran.addElement(h);
      }

      return this.bayaran;
    } finally {
      if (db != null)
        db.close();
    }
  }

  public void doProcessing(HttpServletRequest request, HttpServletResponse response, ServletContext context, Map parameters)
    throws Exception
  {
    String idfail = "";
    if (parameters.get("idfail") != null) {
      idfail = parameters.get("idfail").toString();
    }

    this.bayaranPusaka = new Vector();
    this.bayaranPusaka = getBayaran(idfail);
    Hashtable hB = (Hashtable)this.bayaranPusaka.get(0);

    System.out.println("jumlah_Byrn_perintah " + ((String)hB.get("jumByrnPerintahTmbh")));

    parameters.put("no_Resit_perintah", (String)hB.get("noResitPerintah"));
    parameters.put("no_Resit_perintahTmbh", (String)hB.get("noResitPerintahTmbh"));
    parameters.put("tarikh_Byrn_perintah", (String)hB.get("tkhByrnPerintah"));
    parameters.put("tarikh_Byrn_perintahTmbh", (String)hB.get("tkhByrnPerintahTmbh"));
    parameters.put("jumlah_Byrn_perintah", (String)hB.get("jumByrnPerintah"));
    parameters.put("jumlah_Byrn_perintahTmbh", (String)hB.get("jumByrnPerintahTmbh"));
    parameters.put("jumlah_Byrn_Cukai", (String)hB.get("jumByrnCukai"));
  }
}