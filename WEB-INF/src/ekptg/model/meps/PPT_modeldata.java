package ekptg.model.meps;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujnegeri;

public class PPT_modeldata extends EkptgCache implements Serializable {
	static Logger myLogger = Logger.getLogger(PPT_modeldata.class);
	
	// VECTOR
	public Vector getHakmilik = null;
	public Vector PenarikanBalik = null;
	public Vector BorangKKementerian = null;
	public Vector TotalBorangK = null;
	public Vector getWarta = null;
	public Vector getBantahan = null;
	public Vector getPermintaanUkur = null;
	public Vector getAfidavit = null;
	public Vector getPampasan = null;
	public Vector getStatus = null;
	public Vector getPampasanAmanahRaya = null;
	public Vector getCajBayaranLewat = null;
	public Vector getTotalTanahRizab = null;
	public Vector getStatusBantahan = null;
	public Vector getStatusPenggunaan = null;
	public Vector getStatusPenggunaanOnline = null;
	public Vector getStatusPenggunaanOnlineUpdated = null;
	public Vector getStatusHTP = null;
	public Vector getStatusRizab = null;
	public Vector getStatusPHP = null;
	public Vector getTotalPenggunaaneTaPP = null;
	public Vector getKodCawangan= null;
	public Vector getAbbrev = null;
	
	private String SQL;
	
	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}
	
	

	public Vector getListTotalStatusWartaNegeri(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String jenis_permohonan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		 System.out.println("txdTarikhMula :"+txdTarikhMula);
		 System.out.println("txdTarikhAkhir :"+txdTarikhAkhir);
		try{
			System.out.println("jenis_permohonan :"+jenis_permohonan);
			
			if(jenis_permohonan.equals("") || jenis_permohonan.equals(null))
			{
				jenis_permohonan = "tapak";
			}
			
			/*if(txdTarikhMula.equals("") || txdTarikhMula.equals(null) ||  txdTarikhAkhir.equals("") || txdTarikhAkhir.equals(null))
			{
				txdTarikhMula = "01/01/2010";
				txdTarikhAkhir = "31/12/2014";
			}*/
			
			
				getWarta = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
			
				
				sql = " SELECT ROW_NUMBER() OVER (ORDER BY NN.ID_NEGERI) AS BIL,NN.NAMA_NEGERI, NN.ID_NEGERI, "+
					" (SELECT COUNT(PPP.ID_PERMOHONAN) "+
					" FROM "+
					" ( "+
					" SELECT "+
					" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
					" abs(round(months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN   "+ 
					" FROM "+
					" ( "+
					" SELECT DISTINCT PP.ID_PERMOHONAN, "+
					" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
					" ELSE '1' END) JENIS_PERMOHONAN, "+
					" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
					" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
					" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
					" ELSE '' END) AS TARIKH_WARTA, "+
					" WWW.TARIKH_WARTA AS TARIKH_W "+
					" FROM TBLPPTPERMOHONAN PP,  "+
					" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
					" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
					" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN PPP "+
					" ) WWW "+
					" WHERE  "+
					" PP.ID_PERMOHONAN IS NOT NULL "+
					" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
					" AND WWW.TARIKH_WARTA IS NULL "+
					" ) "+
					" ) KKK, "+
					" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
					" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN  ";

				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
					if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
					{
					sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
					}
					
					sql += " AND PPP.ID_FAIL = FF.ID_FAIL AND FF.ID_NEGERI = NN.ID_NEGERI "+
					" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) > 6) OR ('"+jenis_permohonan+"' = 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) >= 4)))  "+
					" AS OVERDUE,  "+
					" ( "+
					" SELECT COUNT(PPP.ID_PERMOHONAN) "+
					" FROM "+
					" ( "+
					" SELECT "+
					" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
					" abs(round(months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN     "+
					" FROM "+
					" ( "+
					" SELECT DISTINCT PP.ID_PERMOHONAN, "+
					" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
					" ELSE '1' END) JENIS_PERMOHONAN, "+
					" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
					" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
					" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
					" ELSE '' END) AS TARIKH_WARTA, "+
					" WWW.TARIKH_WARTA AS TARIKH_W "+
					" FROM TBLPPTPERMOHONAN PP,  "+
					" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
					" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
					" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN PPP "+
					" ) WWW "+
					" WHERE  "+
					" PP.ID_PERMOHONAN IS NOT NULL "+
					" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
					" AND WWW.TARIKH_WARTA IS NULL "+
					" ) "+
					" ) KKK, "+
					" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
					" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN AND FF.ID_FAIL = PPP.ID_FAIL ";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
					
					if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
					{
					sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
					}
					
					sql += " AND FF.ID_NEGERI = NN.ID_NEGERI "+
					" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) <= 6) OR ('"+jenis_permohonan+"' = 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) <= 4)) "+
					" ) "+
					" AS ONGOING, "+
					" (SELECT COUNT(PPP.ID_PERMOHONAN) "+
					" FROM "+
					" ( "+
					" SELECT "+
					" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
					" abs(round(months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN  "+  
					" FROM "+
					" ( "+
					" SELECT DISTINCT PP.ID_PERMOHONAN, "+
					" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
					" ELSE '1' END) JENIS_PERMOHONAN, "+
					" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
					" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
					" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
					" ELSE '' END) AS TARIKH_WARTA, "+
					" WWW.TARIKH_WARTA AS TARIKH_W "+
					" FROM TBLPPTPERMOHONAN PP,  "+
					" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
					" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
					" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN PPP "+
					" ) WWW "+
					" WHERE  "+
					" PP.ID_PERMOHONAN IS NOT NULL "+
					" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
					" AND WWW.TARIKH_WARTA IS NOT NULL "+
					" ) "+
					" ) KKK, "+
					" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
					" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN  ";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
					
					if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
					{
					sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')   ";
					}
					
					sql += " AND PPP.ID_FAIL = FF.ID_FAIL  "+
					" AND FF.ID_NEGERI = NN.ID_NEGERI "+
					" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) <= 4) OR ('"+jenis_permohonan+"' = 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) <=  2))) "+
					" AS AHEAD, "+
					" (SELECT COUNT(PPP.ID_PERMOHONAN) "+
					" FROM "+
					" ( "+
					" SELECT "+
					" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
					" abs((months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN    "+
					" FROM "+
					" ( "+
					" SELECT DISTINCT PP.ID_PERMOHONAN, "+
					" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
					" ELSE '1' END) JENIS_PERMOHONAN, "+
					" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
					" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
					" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
					" ELSE '' END) AS TARIKH_WARTA, "+
					" WWW.TARIKH_WARTA AS TARIKH_W "+
					" FROM TBLPPTPERMOHONAN PP,  "+
					" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
					" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
					" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN PPP "+
					" ) WWW "+
					" WHERE  "+
					" PP.ID_PERMOHONAN IS NOT NULL "+
					" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
					" AND WWW.TARIKH_WARTA IS NOT NULL "+
					" ) "+
					" ) KKK, "+
					" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
					" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN  ";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
					
					if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
					{
					sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')  ";
					}
					
					sql += " AND PPP.ID_FAIL = FF.ID_FAIL  "+
					" AND FF.ID_NEGERI = NN.ID_NEGERI "+
					" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) > 4 AND TO_NUMBER(KKK.JARAK_BULAN) <= 6) OR  "+
					" ('"+jenis_permohonan+" '= 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) > 2 AND TO_NUMBER(KKK.JARAK_BULAN) <= 4))) "+
					" AS ONTIME "+
					" FROM TBLRUJNEGERI NN WHERE  "+
					" NN.ID_NEGERI NOT IN (1,12,13,15,16,17,0) ";

					setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL KESELURUHAN STATUS WARTA NEGERI :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("ongoing")));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("nama_negeri")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("overdue")));
		    	h.put("ahead", Utils.isNull(rs.getString("ahead")));
		    	h.put("ontime", Utils.isNull(rs.getString("ontime")));
		    	h.put("nama_negeri", rs.getString("nama_negeri"));	
		    	getWarta.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getWarta;					
 }
	
	public Vector getListTotalStatusWartaDaerah(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String jenis_permohonan, String idNegeri)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		 System.out.println("txdTarikhMula :"+txdTarikhMula);
		 System.out.println("txdTarikhAkhir :"+txdTarikhAkhir);
		try{
			System.out.println("jenis_permohonan :"+jenis_permohonan);
			
			if(jenis_permohonan.equals("") || jenis_permohonan.equals(null))
			{
				jenis_permohonan = "tapak";
			}
			
			/*if(txdTarikhMula.equals("") || txdTarikhMula.equals(null) ||  txdTarikhAkhir.equals("") || txdTarikhAkhir.equals(null))
			{
				txdTarikhMula = "01/01/2010";
				txdTarikhAkhir = "31/12/2014";
			}*/
			
			
				getWarta = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
			
				
				sql = " SELECT ROW_NUMBER() OVER (ORDER BY DD.NAMA_DAERAH) AS BIL,DD.NAMA_DAERAH, "+
					" (SELECT COUNT(PPP.ID_PERMOHONAN) "+
					" FROM "+
					" ( "+
					" SELECT "+
					" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
					" abs(round(months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN   "+ 
					" FROM "+
					" ( "+
					" SELECT DISTINCT PP.ID_PERMOHONAN, "+
					" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
					" ELSE '1' END) JENIS_PERMOHONAN, "+
					" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
					" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
					" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
					" ELSE '' END) AS TARIKH_WARTA, "+
					" WWW.TARIKH_WARTA AS TARIKH_W "+
					" FROM TBLPPTPERMOHONAN PP,  "+
					" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
					" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
					" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN PPP "+
					" ) WWW "+
					" WHERE  "+
					" PP.ID_PERMOHONAN IS NOT NULL "+
					" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
					" AND WWW.TARIKH_WARTA IS NULL "+
					" ) "+
					" ) KKK, "+
					" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
					" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN  ";
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
					if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
					{
					sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
					}
					
					sql += " AND PPP.ID_FAIL = FF.ID_FAIL AND FF.ID_NEGERI = NN.ID_NEGERI AND PPP.ID_DAERAH = DD.ID_DAERAH"+
					" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) > 6) OR ('"+jenis_permohonan+"' = 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) >= 4)))  "+
					" AS OVERDUE,  "+
					" ( "+
					" SELECT COUNT(PPP.ID_PERMOHONAN) "+
					" FROM "+
					" ( "+
					" SELECT "+
					" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
					" abs(round(months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN     "+
					" FROM "+
					" ( "+
					" SELECT DISTINCT PP.ID_PERMOHONAN, "+
					" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
					" ELSE '1' END) JENIS_PERMOHONAN, "+
					" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
					" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
					" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
					" ELSE '' END) AS TARIKH_WARTA, "+
					" WWW.TARIKH_WARTA AS TARIKH_W "+
					" FROM TBLPPTPERMOHONAN PP,  "+
					" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
					" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
					" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN PPP "+
					" ) WWW "+
					" WHERE  "+
					" PP.ID_PERMOHONAN IS NOT NULL "+
					" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
					" AND WWW.TARIKH_WARTA IS NULL "+
					" ) "+
					" ) KKK, "+
					" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
					" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN AND FF.ID_FAIL = PPP.ID_FAIL ";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
					
					if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
					{
					sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
					}
					
					sql += " AND FF.ID_NEGERI = NN.ID_NEGERI AND PPP.ID_DAERAH = DD.ID_DAERAH"+
					" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) <= 6) OR ('"+jenis_permohonan+"' = 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) <= 4)) "+
					" ) "+
					" AS ONGOING, "+
					" (SELECT COUNT(PPP.ID_PERMOHONAN) "+
					" FROM "+
					" ( "+
					" SELECT "+
					" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
					" abs(round(months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN  "+  
					" FROM "+
					" ( "+
					" SELECT DISTINCT PP.ID_PERMOHONAN, "+
					" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
					" ELSE '1' END) JENIS_PERMOHONAN, "+
					" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
					" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
					" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
					" ELSE '' END) AS TARIKH_WARTA, "+
					" WWW.TARIKH_WARTA AS TARIKH_W "+
					" FROM TBLPPTPERMOHONAN PP,  "+
					" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
					" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
					" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN PPP "+
					" ) WWW "+
					" WHERE  "+
					" PP.ID_PERMOHONAN IS NOT NULL "+
					" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
					" AND WWW.TARIKH_WARTA IS NOT NULL "+
					" ) "+
					" ) KKK, "+
					" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
					" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN  ";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
					
					if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
					{
					sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')   ";
					}
					
					sql += " AND PPP.ID_FAIL = FF.ID_FAIL AND PPP.ID_DAERAH = DD.ID_DAERAH"+
					" AND FF.ID_NEGERI = NN.ID_NEGERI "+
					" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) <= 4) OR ('"+jenis_permohonan+"' = 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) <=  2))) "+
					" AS AHEAD, "+
					" (SELECT COUNT(PPP.ID_PERMOHONAN) "+
					" FROM "+
					" ( "+
					" SELECT "+
					" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
					" abs((months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN    "+
					" FROM "+
					" ( "+
					" SELECT DISTINCT PP.ID_PERMOHONAN, "+
					" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
					" ELSE '1' END) JENIS_PERMOHONAN, "+
					" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
					" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
					" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
					" ELSE '' END) AS TARIKH_WARTA, "+
					" WWW.TARIKH_WARTA AS TARIKH_W "+
					" FROM TBLPPTPERMOHONAN PP,  "+
					" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
					" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
					" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN PPP "+
					" ) WWW "+
					" WHERE  "+
					" PP.ID_PERMOHONAN IS NOT NULL "+
					" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
					" AND WWW.TARIKH_WARTA IS NOT NULL "+
					" ) "+
					" ) KKK, "+
					" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
					" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN  ";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
					
					if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
					{
					sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')  ";
					}
					
					sql += " AND PPP.ID_FAIL = FF.ID_FAIL AND PPP.ID_DAERAH = DD.ID_DAERAH"+
					" AND FF.ID_NEGERI = NN.ID_NEGERI "+
					" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) > 4 AND TO_NUMBER(KKK.JARAK_BULAN) <= 6) OR  "+
					" ('"+jenis_permohonan+" '= 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) > 2 AND TO_NUMBER(KKK.JARAK_BULAN) <= 4))) "+
					" AS ONTIME "+
					" FROM TBLRUJNEGERI NN, TBLRUJDAERAH DD WHERE  "+
					" NN.ID_NEGERI NOT IN (1,12,13,15,16,17,0) "+
					" AND NN.ID_NEGERI = DD.ID_NEGERI AND NN.ID_NEGERI = " + idNegeri;

					setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL KESELURUHAN STATUS WARTA DAERAH :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("ongoing")));
		    	h.put("nama_daerah", Utils.NiceStateName(rs.getString("nama_daerah")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("overdue")));
		    	h.put("ahead", Utils.isNull(rs.getString("ahead")));
		    	h.put("ontime", Utils.isNull(rs.getString("ontime")));
		    	getWarta.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getWarta;					
}
	
	public Vector getListTotalStatusWartaKementerian(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir,String jenis_permohonan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				System.out.println("jenis_permohonan :"+jenis_permohonan);
				
				if(jenis_permohonan.equals("") || jenis_permohonan.equals(null))
				{
					jenis_permohonan = "tapak";
				}
				
//				if(txdTarikhMula.equals("") || txdTarikhMula.equals(null) ||  txdTarikhAkhir.equals("") || txdTarikhAkhir.equals(null))
//				{
//					txdTarikhMula = "01/01/2010";
//					txdTarikhAkhir = "31/12/2014";
//				}
				
				
				
					
					sql = " SELECT ROW_NUMBER() OVER (ORDER BY NN.NAMA_KEMENTERIAN) AS BIL,NN.NAMA_KEMENTERIAN, "+
						" (SELECT COUNT(PPP.ID_PERMOHONAN) "+
						" FROM "+
						" ( "+
						" SELECT "+
						" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
						" abs(round(months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN   "+ 
						" FROM "+
						" ( "+
						" SELECT DISTINCT PP.ID_PERMOHONAN, "+
						" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
						" ELSE '1' END) JENIS_PERMOHONAN, "+
						" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
						" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
						" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
						" ELSE '' END) AS TARIKH_WARTA, "+
						" WWW.TARIKH_WARTA AS TARIKH_W "+
						" FROM TBLPPTPERMOHONAN PP,  "+
						" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
						" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
						" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
						" FROM TBLPPTPERMOHONAN PPP "+
						" ) WWW "+
						" WHERE  "+
						" PP.ID_PERMOHONAN IS NOT NULL "+
						" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
						" AND WWW.TARIKH_WARTA IS NULL "+
						" ) "+
						" ) KKK, "+
						" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
						" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN  ";
					
					// TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
					
						if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
						{
						sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
						}
						
						sql += " AND PPP.ID_FAIL = FF.ID_FAIL AND FF.ID_KEMENTERIAN = NN.ID_KEMENTERIAN "+
						" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) > 6) OR ('"+jenis_permohonan+"' = 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) >= 4)))  "+
						" AS OVERDUE,  "+
						" ( "+
						" SELECT COUNT(PPP.ID_PERMOHONAN) "+
						" FROM "+
						" ( "+
						" SELECT "+
						" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
						" abs(round(months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN     "+
						" FROM "+
						" ( "+
						" SELECT DISTINCT PP.ID_PERMOHONAN, "+
						" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
						" ELSE '1' END) JENIS_PERMOHONAN, "+
						" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
						" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
						" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
						" ELSE '' END) AS TARIKH_WARTA, "+
						" WWW.TARIKH_WARTA AS TARIKH_W "+
						" FROM TBLPPTPERMOHONAN PP,  "+
						" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
						" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
						" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
						" FROM TBLPPTPERMOHONAN PPP "+
						" ) WWW "+
						" WHERE  "+
						" PP.ID_PERMOHONAN IS NOT NULL "+
						" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
						" AND WWW.TARIKH_WARTA IS NULL "+
						" ) "+
						" ) KKK, "+
						" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
						" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN AND FF.ID_FAIL = PPP.ID_FAIL ";
						
						// TAHUN 
						if (socTahun != null) {
							if (!socTahun.trim().equals("")) {
								sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
							}
						}
						
						if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
						{
						sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
						}
						
						sql += " AND FF.ID_KEMENTERIAN = NN.ID_KEMENTERIAN "+
						" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) <= 6) OR ('"+jenis_permohonan+"' = 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) <= 4)) "+
						" ) "+
						" AS ONGOING, "+
						" (SELECT COUNT(PPP.ID_PERMOHONAN) "+
						" FROM "+
						" ( "+
						" SELECT "+
						" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
						" abs(round(months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN  "+  
						" FROM "+
						" ( "+
						" SELECT DISTINCT PP.ID_PERMOHONAN, "+
						" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
						" ELSE '1' END) JENIS_PERMOHONAN, "+
						" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
						" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
						" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
						" ELSE '' END) AS TARIKH_WARTA, "+
						" WWW.TARIKH_WARTA AS TARIKH_W "+
						" FROM TBLPPTPERMOHONAN PP,  "+
						" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
						" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
						" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
						" FROM TBLPPTPERMOHONAN PPP "+
						" ) WWW "+
						" WHERE  "+
						" PP.ID_PERMOHONAN IS NOT NULL "+
						" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
						" AND WWW.TARIKH_WARTA IS NOT NULL "+
						" ) "+
						" ) KKK, "+
						" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
						" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN  ";
						
						// TAHUN 
						if (socTahun != null) {
							if (!socTahun.trim().equals("")) {
								sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
							}
						}
						
						if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
						{
						sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')   ";
						}
						
						sql += " AND PPP.ID_FAIL = FF.ID_FAIL  "+
						" AND FF.ID_KEMENTERIAN = NN.ID_KEMENTERIAN "+
						" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) <= 4) OR ('"+jenis_permohonan+"' = 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) <=  2))) "+
						" AS AHEAD, "+
						" (SELECT COUNT(PPP.ID_PERMOHONAN) "+
						" FROM "+
						" ( "+
						" SELECT "+
						" ID_PERMOHONAN, JENIS_PERMOHONAN, TARIKH_PERMOHONAN, TARIKH_WARTA, "+
						" abs((months_between(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY'), TO_DATE(TARIKH_PERMOHONAN,'DD/MM/YYYY')))) AS JARAK_BULAN    "+
						" FROM "+
						" ( "+
						" SELECT DISTINCT PP.ID_PERMOHONAN, "+
						" (CASE WHEN PP.FLAG_JENIS_PROJEK IS NOT NULL THEN TO_CHAR(PP.FLAG_JENIS_PROJEK) "+
						" ELSE '1' END) JENIS_PERMOHONAN, "+
						" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
						" (CASE WHEN WWW.TARIKH_WARTA IS NOT NULL THEN TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') "+
						" WHEN WWW.TARIKH_WARTA IS NULL THEN TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
						" ELSE '' END) AS TARIKH_WARTA, "+
						" WWW.TARIKH_WARTA AS TARIKH_W "+
						" FROM TBLPPTPERMOHONAN PP,  "+
						" (SELECT (SELECT MAX(WW.TARIKH_WARTA) FROM TBLPPTWARTA WW  "+
						" WHERE WW.ID_PERMOHONAN = PPP.ID_PERMOHONAN  "+
						" AND WW.TARIKH_WARTA IS NOT NULL) AS TARIKH_WARTA, PPP.ID_PERMOHONAN "+
						" FROM TBLPPTPERMOHONAN PPP "+
						" ) WWW "+
						" WHERE  "+
						" PP.ID_PERMOHONAN IS NOT NULL "+
						" AND  PP.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
						" AND WWW.TARIKH_WARTA IS NOT NULL "+
						" ) "+
						" ) KKK, "+
						" TBLPPTPERMOHONAN PPP,TBLPFDFAIL FF "+
						" WHERE KKK.ID_PERMOHONAN = PPP.ID_PERMOHONAN  ";
						
						// TAHUN 
						if (socTahun != null) {
							if (!socTahun.trim().equals("")) {
								sql = sql + " AND TO_CHAR(PPP.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
							}
						}
						
						if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
						{
						sql += " AND PPP.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')  ";
						}
						
						sql += " AND PPP.ID_FAIL = FF.ID_FAIL  "+
						" AND FF.ID_KEMENTERIAN = NN.ID_KEMENTERIAN "+
						" AND (('"+jenis_permohonan+"' = 'jajaran' AND TO_NUMBER(KKK.JARAK_BULAN) > 4 AND TO_NUMBER(KKK.JARAK_BULAN) <= 6) OR  "+
						" ('"+jenis_permohonan+" '= 'tapak' AND TO_NUMBER(KKK.JARAK_BULAN) > 2 AND TO_NUMBER(KKK.JARAK_BULAN) <= 4))) "+
						" AS ONTIME "+
						" FROM TBLRUJKEMENTERIAN NN  ";

					
					setSQL(sql);
					myLogger.info("LAPORAN TOTAL KESELURUHAN STATUS WARTA KEMENTERIAN :: "+sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					int bil = 1;
					
			     while (rs.next()) {
			    	h = new Hashtable();			    	
			    	h.put("bil", bil);		        
			    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("ongoing")));
			    	h.put("nama_kementerian", Utils.NiceStateName(rs.getString("nama_kementerian")));		    			
			    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("overdue")));
			    	h.put("ahead", Utils.isNull(rs.getString("ahead")));
			    	h.put("ontime", Utils.isNull(rs.getString("ontime")));
			    	getHakmilik.addElement(h);
			    	bil++;
			      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
				}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	
	public Vector getListTotalProjectKementerian(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
							
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z, ID_NEGERI FROM ( ";
				sql += " SELECT NVL(C.NAMA_NEGERI,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK, C.ID_NEGERI  ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, ";        
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) ";         
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1)";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x, ID_NEGERI ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTFAF :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	h.put("idNegeri", rs.getString("ID_NEGERI"));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	public Vector getListTotalPenarikanBalikNegeri(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			PenarikanBalik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
							
				sql="SELECT ROW_NUMBER() OVER (ORDER BY N.ID_NEGERI) AS BIL,"+
                   " N.NAMA_NEGERI, N.ID_NEGERI,TP.TOTAL_PERMOHONAN,"+ 
					" TH.TOTAL_HAKMILIK, THS.TOTAL_HAKMILIK_SELESAI,"+ 
					" (NVL(TH.TOTAL_HAKMILIK,0)-NVL(THS.TOTAL_HAKMILIK_SELESAI,0)) AS"+
				    " TOTAL_HAKMILIK_XSELESAI"+
					" ,(CASE WHEN NVL(THS.TOTAL_HAKMILIK_SELESAI,0) = 0 THEN 0"+
					" WHEN NVL(THS.TOTAL_HAKMILIK_SELESAI,0) > 0 THEN"+
				    " ROUND(((NVL(THS.TOTAL_HAKMILIK_SELESAI,0)/(NVL(TH.TOTAL_HAKMILIK,0)))*100),2"+
				    " )"+ 
					" ELSE 0 END) AS PERATUS_SELESAI"+
					" FROM TBLRUJNEGERI N,"+
					" (SELECT DISTINCT (SELECT COUNT(PB.ID_PERMOHONAN) AS JUMLAH_PERMOHONAN"+ 
					" FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTPENARIKANBALIK PB"+
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PB.ID_PERMOHONAN"+ 
					" AND F.ID_NEGERI = NN.ID_NEGERI";
				
				if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY') ";
				}
			               
			    sql+= " )" +
			        " AS TOTAL_PERMOHONAN,"+
					" NN.ID_NEGERI FROM TBLRUJNEGERI NN) TP,"+    
					" (SELECT DISTINCT (SELECT COUNT(PH.ID_PENARIKANHAKMILIK) AS"+
				    " JUMLAH_PERMOHONAN"+ 
                   " FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTPENARIKANBALIK"+
				    " PB,TBLPPTPENARIKANHAKMILIK PH"+
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PB.ID_PERMOHONAN"+
				    " AND PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK"+
					" AND F.ID_NEGERI = NN.ID_NEGERI"; 
			    if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY') ";
				}
			               
			    sql+= " )" +
			        "AS TOTAL_HAKMILIK,"+
                   " NN.ID_NEGERI FROM TBLRUJNEGERI NN) TH,"+     
					" (SELECT DISTINCT (SELECT COUNT(S.ID_SIASATAN) AS JUMLAH_PERMOHONAN "+
                   " FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTPENARIKANBALIK"+
				    " PB,TBLPPTPENARIKANHAKMILIK PH,TBLPPTSIASATAN S"+
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PB.ID_PERMOHONAN"+
		            " AND PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK AND S.ID_HAKMILIK =PH.ID_HAKMILIK "+
                   " AND S.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND S.STATUS_SIASATAN ='6'"+
					" AND F.ID_NEGERI = NN.ID_NEGERI";
			    
			    if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY') ";
				}
			               
			    sql+= " )" +
			        " AS TOTAL_HAKMILIK_SELESAI,"+
					" NN.ID_NEGERI FROM TBLRUJNEGERI NN) THS"+
					" WHERE N.ID_NEGERI NOT IN (1,12,13,15,16,17,0) "+
					" AND N.ID_NEGERI =  TP.ID_NEGERI  AND N.ID_NEGERI =  TH.ID_NEGERI AND N.ID_NEGERI =  THS.ID_NEGERI";

				      
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTAA :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("total_hakmilik_xselesai")));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("nama_negeri")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("total_hakmilik_selesai")));
		    	h.put("nama_negeri", rs.getString("nama_negeri"));	
		    	PenarikanBalik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return PenarikanBalik;					
}
	public Vector getListTotalPenarikanBalikDaerah(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir,String idNegeri)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			PenarikanBalik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
							
				sql="SELECT ROW_NUMBER() OVER (ORDER BY Z.NAMA_DAERAH) AS BIL,"+
                  " Z.NAMA_DAERAH, TP.TOTAL_PERMOHONAN,"+ 
					" TH.TOTAL_HAKMILIK, THS.TOTAL_HAKMILIK_SELESAI,"+ 
					" (NVL(TH.TOTAL_HAKMILIK,0)-NVL(THS.TOTAL_HAKMILIK_SELESAI,0)) AS"+
				    " TOTAL_HAKMILIK_XSELESAI"+
					" ,(CASE WHEN NVL(THS.TOTAL_HAKMILIK_SELESAI,0) = 0 THEN 0"+
					" WHEN NVL(THS.TOTAL_HAKMILIK_SELESAI,0) > 0 THEN"+
				    " ROUND(((NVL(THS.TOTAL_HAKMILIK_SELESAI,0)/(NVL(TH.TOTAL_HAKMILIK,0)))*100),2"+
				    " )"+ 
					" ELSE 0 END) AS PERATUS_SELESAI"+
					" FROM TBLRUJDAERAH Z,"+
					" (SELECT DISTINCT (SELECT COUNT(PB.ID_PERMOHONAN) AS JUMLAH_PERMOHONAN"+ 
					" FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTPENARIKANBALIK PB,TBLRUJNEGERI NN"+
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PB.ID_PERMOHONAN"+ 
					" AND F.ID_NEGERI = NN.ID_NEGERI AND ZZ.ID_DAERAH = P.ID_DAERAH";
				
				if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY') ";
				}
			               
			    sql+= " )" +
			        " AS TOTAL_PERMOHONAN, ZZ.ID_DAERAH"+
					" FROM TBLRUJDAERAH ZZ) TP,"+    
					" (SELECT DISTINCT (SELECT COUNT(PH.ID_PENARIKANHAKMILIK) AS"+
				    " JUMLAH_PERMOHONAN"+ 
                  " FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTPENARIKANBALIK"+
				    " PB,TBLPPTPENARIKANHAKMILIK PH, TBLRUJNEGERI NN"+
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PB.ID_PERMOHONAN"+
				    " AND PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK"+
					" AND F.ID_NEGERI = NN.ID_NEGERI AND ZZ.ID_DAERAH = P.ID_DAERAH"; 
			    if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY') ";
				}
			               
			    sql+= " )" +
			        "AS TOTAL_HAKMILIK,"+
                  " ZZ.ID_DAERAH FROM TBLRUJDAERAH ZZ) TH,"+     
					" (SELECT DISTINCT (SELECT COUNT(S.ID_SIASATAN) AS JUMLAH_PERMOHONAN "+
                  " FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTPENARIKANBALIK"+
				    " PB,TBLPPTPENARIKANHAKMILIK PH,TBLPPTSIASATAN S,TBLRUJNEGERI NN"+
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PB.ID_PERMOHONAN"+
		            " AND PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK AND S.ID_HAKMILIK =PH.ID_HAKMILIK "+
                  " AND S.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND S.STATUS_SIASATAN ='6'"+
					" AND F.ID_NEGERI = NN.ID_NEGERI AND ZZ.ID_DAERAH = P.ID_DAERAH";
			    
			    if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY') ";
				}
			               
			    sql+= " )" +
			        " AS TOTAL_HAKMILIK_SELESAI,"+
					" ZZ.ID_DAERAH FROM TBLRUJDAERAH ZZ) THS, TBLRUJNEGERI NN"+
					" WHERE Z.ID_NEGERI NOT IN (1,12,13,15,16,17,0) "+
					" AND Z.ID_DAERAH =  TP.ID_DAERAH  AND Z.ID_DAERAH =  TH.ID_DAERAH AND Z.ID_DAERAH =  THS.ID_DAERAH"+
					" AND NN.ID_NEGERI = Z.ID_NEGERI  AND Z.ID_NEGERI = " + idNegeri;
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTSS :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("total_hakmilik_xselesai")));
		    	h.put("nama_daerah", Utils.NiceStateName(rs.getString("nama_daerah")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("total_hakmilik_selesai")));
		    	PenarikanBalik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return PenarikanBalik;					
}
	
	public Vector getListTotalPenarikanBalikKementerian(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
							
				sql="SELECT ROW_NUMBER () OVER (ORDER BY n.nama_kementerian) AS bil,"+
				      " n.nama_kementerian, tp.total_permohonan, th.total_hakmilik,"+
				       " ths.total_hakmilik_selesai,"+
				       " (NVL (th.total_hakmilik, 0) - NVL (ths.total_hakmilik_selesai, 0)"+
				       " ) AS total_hakmilik_xselesai,"+
				       " (CASE"+
				           " WHEN NVL (ths.total_hakmilik_selesai, 0) = 0"+
				              " THEN 0"+
				           " WHEN NVL (ths.total_hakmilik_selesai, 0) > 0"+
				              " THEN ROUND ((  (  NVL (ths.total_hakmilik_selesai, 0)"+
				                              " / (NVL (th.total_hakmilik, 0))"+
				                             " )"+
				                           " * 100"+
				                          " ),"+
				                          " 2"+
				                         " )"+
				           " ELSE 0"+
				        " END"+
				       " ) AS peratus_selesai"+
				  " FROM tblrujkementerian n,"+
				       " (SELECT DISTINCT (SELECT COUNT (pb.id_permohonan)"+
				                           " AS jumlah_permohonanv"+
				                           " FROM tblpfdfail f,"+
				                           " tblpptpermohonan p,"+
				                           " tblpptpenarikanbalik pb"+
				                           " WHERE f.id_fail = p.id_fail"+
				                           " AND p.id_permohonan = pb.id_permohonan"+
				                           " AND f.id_kementerian = nn.id_kementerian";
				
				if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
				}
			      sql+= " )" +
				        " AS total_permohonan,"+
				                           " nn.id_kementerian"+
				                           " FROM tblrujkementerian nn) tp,"+
				                           " (SELECT DISTINCT (SELECT COUNT"+
				                           "(ph.id_penarikanhakmilik"+
				                           ") AS jumlah_permohonan"+
				                           " FROM tblpfdfail f,"+
				                           " tblpptpermohonan p,"+
				                           " tblpptpenarikanbalik pb,"+
				                           " tblpptpenarikanhakmilik ph"+
				                           " WHERE f.id_fail = p.id_fail"+
				                           " AND p.id_permohonan = pb.id_permohonan"+
				                           " AND pb.id_penarikanbalik = ph.id_penarikanbalik"+
				                           " AND f.id_kementerian = nn.id_kementerian";
			      
			  	if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
				}
			      sql+= " )" +
			      	" AS total_hakmilik,"+
				                           " nn.id_kementerian FROM tblrujkementerian nn) th,"+
				                           " (SELECT DISTINCT (SELECT COUNT (s.id_siasatan)"+
				                           " AS jumlah_permohonan"+
				                           " FROM tblpfdfail f,"+
				                           " tblpptpermohonan p,"+
				                           " tblpptpenarikanbalik pb,"+
				                           " tblpptpenarikanhakmilik ph,"+
				                           " tblpptsiasatan s"+
				                           " WHERE f.id_fail = p.id_fail"+
				                           " AND p.id_permohonan = pb.id_permohonan"+
				                           " AND pb.id_penarikanbalik = ph.id_penarikanbalik"+
				                           " AND s.id_hakmilik = ph.id_hakmilik"+
				                           " AND s.id_penarikanbalik = pb.id_penarikanbalik"+
				                           " AND s.status_siasatan = '6'"+
				                           " AND f.id_kementerian = nn.id_kementerian";
			      
			  	if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
				}
			      sql+= " )" +
				                           
				                           " AS total_hakmilik_selesai,nn.id_kementerian"+
				                           " FROM tblrujkementerian nn) ths"+
				                           " WHERE n.id_negeri NOT IN (1, 12, 13, 15, 16, 17, 0)"+
				                           " AND n.id_kementerian = tp.id_kementerian"+
				                           " AND n.id_kementerian = th.id_kementerian"+
				                           " AND n.id_kementerian = ths.id_kementerian";
				      
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTGG :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("total_hakmilik_xselesai")));
		    	h.put("nama_kementerian", Utils.NiceStateName(rs.getString("nama_kementerian")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("total_hakmilik_selesai")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	public Vector getListTotalBorangKNegeri(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			    TotalBorangK = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
							
				sql="SELECT ROW_NUMBER () OVER (ORDER BY dd.id_negeri) AS bil, dd.nama_negeri, dd.id_negeri, " +
			       " th.total_hakmilik, ths.total_hakmilik_endos," +
			       " (th.total_hakmilik - ths.total_hakmilik_endos) AS baki" +
			       " FROM tblrujnegeri dd," +
			       "(SELECT (SELECT TO_CHAR (COUNT (*)) AS total_hm " +
			                  " FROM tblpptpermohonan p," +
			                       " tblpfdfail f," +
			                       " tblppthakmilik hm" +
			                 " WHERE p.id_fail = f.id_fail" +
			                   " AND hm.id_permohonan = p.id_permohonan" +
			                   " AND hm.id_negeri = ddd.id_negeri" +
			                   " AND NVL (hm.flag_penarikan_keseluruhan, '0') <> 'Y'" +
			                   " AND NVL (hm.flag_pembatalan_keseluruhan, '0') <> 'Y'";
				
				
				
				
				if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
				}
			      sql+= " )" +
			           " AS total_hakmilik," +
			           " ddd.nama_negeri, ddd.id_negeri" +
			          " FROM tblrujnegeri ddd" +
			         " WHERE ddd.id_negeri NOT IN (1, 12, 13, 15, 16, 17, 0)) th," +
			       " (SELECT (SELECT TO_CHAR (COUNT (h.id_hakmilik))" +
			                                         " AS total_hm " +
			                  " FROM tblpptpermohonan p," +
			                       " tblpfdfail f," +
			                       " tblppthakmilik h," +
			                       " tblrujjenishakmilik hm," +
			                       " tblrujlot jl," +
			                       " tblppthakmilikborangk hbk," +
			                       " tblpptborangk bk," +
			                       " tblpptsiasatan s," +
			                       " tblpptendosanborangk ebk" +
			                 " WHERE p.id_fail = f.id_fail" +
			                   " AND ebk.id_borangk = bk.id_borangk" +
			                   " AND h.id_negeri = ddd.id_negeri" +
			                   " AND hbk.id_hakmilik = h.id_hakmilik" +
			                   " AND hbk.id_borangk = bk.id_borangk(+)" +
			                   " AND p.id_permohonan = h.id_permohonan" +
			                   " AND h.id_jenishakmilik = hm.id_jenishakmilik(+)" +
			                   " AND NVL (h.flag_penarikan_keseluruhan, '0') <> 'Y'" +
			                   " AND NVL (h.flag_pembatalan_keseluruhan, '0') <> 'Y'" ;
			      
			   // TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(p.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
			      
			                   if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
			   				{
			   				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
			   				}
			                  sql+= " AND h.id_lot = jl.id_lot(+)" +
			                   " AND h.id_hakmilik = s.id_hakmilik(+)" +
			                   " AND ebk.tarikh_terima IS NOT NULL" +
			                   " AND (   (s.id_siasatan IS NULL)" +
			                        " OR (    s.status_siasatan = '6'" +
			                            " AND s.id_siasatan IS NOT NULL" +
			                           " )" +
			                       " )) AS total_hakmilik_endos," +
			               " ddd.nama_negeri, ddd.id_negeri" +
			          " FROM tblrujnegeri ddd" +
			         " WHERE ddd.id_negeri NOT IN (1, 12, 13, 15, 16, 17, 0)) ths" +
			 " WHERE dd.id_negeri = th.id_negeri" +
			   " AND dd.id_negeri = ths.id_negeri" +
			   " AND dd.nama_negeri NOT LIKE '%TIADA%'";			
				
				setSQL(sql);
				//
			
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTEE :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("total_hakmilik_endos")));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("nama_negeri")));	
		    	h.put("nama_negeri", rs.getString("nama_negeri"));
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("baki")));
		    	TotalBorangK.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return TotalBorangK;					
}
	
	public Vector getListTotalBorangKDaerah(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String idNegeri)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			    TotalBorangK = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
							
				sql="SELECT ROW_NUMBER () OVER (ORDER BY dd.nama_daerah) AS bil, dd.nama_daerah," +
			       " th.total_hakmilik, ths.total_hakmilik_endos," +
			       " (th.total_hakmilik - ths.total_hakmilik_endos) AS baki" +
			       " FROM tblrujdaerah dd," +
			       "(SELECT (SELECT TO_CHAR (COUNT (*)) AS total_hm " +
			                  " FROM tblpptpermohonan p," +
			                       " tblpfdfail f," +
			                       " tblppthakmilik hm" +
			                 " WHERE p.id_fail = f.id_fail" +
			                   " AND hm.id_permohonan = p.id_permohonan" +
			                   " AND hm.id_negeri = nn.id_negeri" +
			                   " AND ddd.ID_DAERAH = hm.ID_DAERAH" +
			                   " AND NVL (hm.flag_penarikan_keseluruhan, '0') <> 'Y'" +
			                   " AND NVL (hm.flag_pembatalan_keseluruhan, '0') <> 'Y'";
				
				
				
				
				if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
				}
			      sql+= " )" +
			           " AS total_hakmilik," +
			           " ddd.nama_daerah, ddd.id_daerah" +
			          " FROM tblrujdaerah ddd, tblrujnegeri nn" +
			         " WHERE nn.id_negeri NOT IN (1, 12, 13, 15, 16, 17, 0)" +
			        " AND ddd.id_negeri = nn.id_negeri) th," +
			       " (SELECT (SELECT TO_CHAR (COUNT (h.id_hakmilik))" +
			                                         " AS total_hm " +
			                  " FROM tblpptpermohonan p," +
			                       " tblpfdfail f," +
			                       " tblppthakmilik h," +
			                       " tblrujjenishakmilik hm," +
			                       " tblrujlot jl," +
			                       " tblppthakmilikborangk hbk," +
			                       " tblpptborangk bk," +
			                       " tblpptsiasatan s," +
			                       " tblpptendosanborangk ebk" +
			                 " WHERE p.id_fail = f.id_fail" +
			                   " AND ebk.id_borangk = bk.id_borangk" +
			                   " AND h.id_negeri = nn.id_negeri" +
			                   " AND ddd.id_daerah = h.id_daerah" +
			                   " AND hbk.id_hakmilik = h.id_hakmilik" +
			                   " AND hbk.id_borangk = bk.id_borangk(+)" +
			                   " AND p.id_permohonan = h.id_permohonan" +
			                   " AND h.id_jenishakmilik = hm.id_jenishakmilik(+)" +
			                   " AND NVL (h.flag_penarikan_keseluruhan, '0') <> 'Y'" +
			                   " AND NVL (h.flag_pembatalan_keseluruhan, '0') <> 'Y'" ;
			      
			   // TAHUN 
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(p.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
			      
			                   if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
			   				{
			   				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
			   				}
			                  sql+= " AND h.id_lot = jl.id_lot(+)" +
			                   " AND h.id_hakmilik = s.id_hakmilik(+)" +
			                   " AND ebk.tarikh_terima IS NOT NULL" +
			                   " AND (   (s.id_siasatan IS NULL)" +
			                        " OR (    s.status_siasatan = '6'" +
			                            " AND s.id_siasatan IS NOT NULL" +
			                           " )" +
			                       " )) AS total_hakmilik_endos," +
			               " ddd.nama_daerah, ddd.id_daerah" +
			          " FROM tblrujdaerah ddd, tblrujnegeri nn" +
			         " WHERE nn.id_negeri NOT IN (1, 12, 13, 15, 16, 17, 0)" +
			        "AND ddd.id_negeri = nn.id_negeri) ths, tblrujnegeri nn" +
			 " WHERE dd.id_daerah = th.id_daerah" +
			   " AND dd.id_daerah = ths.id_daerah" +
			   " AND nn.id_negeri = dd.id_negeri" +
			   " AND dd.id_negeri = " + idNegeri;			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTZZ :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("total_hakmilik_endos")));
		    	h.put("nama_daerah", Utils.NiceStateName(rs.getString("nama_daerah")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("baki")));
		    	TotalBorangK.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return TotalBorangK;					
}
	
	public Vector getListTotalBorangKKementerian(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			BorangKKementerian = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
							
				sql="SELECT ROW_NUMBER () OVER (ORDER BY dd.nama_kementerian) AS bil," +
			       " dd.nama_kementerian, th.total_hakmilik, ths.total_hakmilik_endos," +
			       " (th.total_hakmilik - ths.total_hakmilik_endos) AS baki" +
			  " FROM tblrujkementerian dd," +
			       " (SELECT (SELECT TO_CHAR (COUNT (*)) AS total_hm" +
			                  " FROM tblpptpermohonan p," +
			                       " tblpfdfail f," +
			                       " tblppthakmilik hm" +
			                 " WHERE p.id_fail = f.id_fail" +
			                   " AND hm.id_permohonan = p.id_permohonan" +
			                   " AND f.id_kementerian = ddd.id_kementerian" +
			                   " AND NVL (hm.flag_penarikan_keseluruhan, '0') <> 'Y'" +
			                   " AND NVL (hm.flag_pembatalan_keseluruhan, '0') <> 'Y'";
				
				
				
				if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
				{
				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
				}
			               
			    sql+= " )" +
			           " AS total_hakmilik," +
			           " ddd.nama_kementerian, ddd.id_kementerian" +
			          " FROM tblrujkementerian ddd) th," +
			       " (SELECT (SELECT TO_CHAR (COUNT (h.id_hakmilik))" +
			                                         " AS total_hm" +
			                  " FROM tblpptpermohonan p," +
			                       " tblpfdfail f," +
			                       " tblppthakmilik h," +
			                       " tblrujjenishakmilik hm," +
			                       " tblrujlot jl," +
			                       " tblppthakmilikborangk hbk," +
			                       " tblpptborangk bk," +
			                       " tblpptsiasatan s," +
			                       " tblpptendosanborangk ebk" +
			                 " WHERE p.id_fail = f.id_fail" +
			                   " AND ebk.id_borangk = bk.id_borangk" +
			                   " AND f.id_kementerian = ddd.id_kementerian" +
			                   " AND hbk.id_hakmilik = h.id_hakmilik" +
			                   " AND hbk.id_borangk = bk.id_borangk(+)" +
			                   " AND p.id_permohonan = h.id_permohonan" +
			                   " AND h.id_jenishakmilik = hm.id_jenishakmilik(+)" +
			                   " AND NVL (h.flag_penarikan_keseluruhan, '0') <> 'Y'" +
			                   " AND NVL (h.flag_pembatalan_keseluruhan, '0') <> 'Y'" ;
			    
			 // TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(p.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
			    
			                   if(!txdTarikhMula.equals("") && !txdTarikhAkhir.equals(""))
			   				{
			   				sql += " AND p.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY')    ";
			   				}
			                 sql+=  " AND h.id_lot = jl.id_lot(+)" +
			                   " AND h.id_hakmilik = s.id_hakmilik(+)" +
			                   " AND ebk.tarikh_terima IS NOT NULL" +
			                   " AND (   (s.id_siasatan IS NULL)" +
			                        " OR (    s.status_siasatan = '6'" +
			                            " AND s.id_siasatan IS NOT NULL" +
			                           " )" +
			                       " )) AS total_hakmilik_endos," +
			               " ddd.nama_kementerian, ddd.id_kementerian" +
			          " FROM tblrujkementerian ddd) ths" +
			 " WHERE dd.id_kementerian = th.id_kementerian" +
			   " AND dd.id_kementerian = ths.id_kementerian" +
			   " AND dd.nama_kementerian NOT LIKE '%TIADA%'";	
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTJ :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("total_hakmilik_endos")));
		    	h.put("nama_kementerian", Utils.NiceStateName(rs.getString("nama_kementerian")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("baki")));
		    	BorangKKementerian.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return BorangKKementerian;					
}
	
	public Vector getListTotalProjectKementerian_add(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT ID_NEGERI,x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(C.NAMA_NEGERI,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK, C.ID_NEGERI ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, ";
				sql += " TBLRUJDAERAH H ";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                      
				sql += " AND A.ID_NEGERI = C.ID_NEGERI ";   
				sql += " AND H.ID_DAERAH = D.ID_DAERAH ";   
				sql += " AND B.ID_DAERAH = D.ID_DAERAH  ";  
				sql += " AND H.ID_DAERAH IS NOT NULL "; 
				sql += " AND S.ID_STATUS = B.ID_STATUS ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1,12,13) ";
				sql += " AND A.NO_FAIL IS NOT NULL " ;
				sql += " AND B.ID_STATUS NOT IN (4,5,11,113)" ;
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
					
				sql += " ORDER BY C.ID_NEGERI ";
				sql += " ) ";
				sql += " GROUP BY ID_NEGERI,x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", rs.getString("x"));		 
		    	//h.put("nama_negeri",rs.getString("NAMA_NEGERI"));
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	
	// Faresh ADDED 10.12.2012	
	public Vector getAbbrev() throws Exception {
		Db db = null;
		String sql = "";
		try{
			getAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			
			sql =  " SELECT distinct(RK.ABBREV) AS ABBREV,RK.ID_NEGERI, RK.NAMA_NEGERI AS NEGERI " +
					" FROM TBLRUJNEGERI RK WHERE RK.ABBREV not in ('PEL','TM')" +
					" AND RK.ID_NEGERI NOT IN (1)"+
					" ORDER BY RK.ID_NEGERI" +
					"";
	//		myLog.info(sql);
				setSQL(sql);				
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;				
			while (rs.next()) {
				h = new Hashtable();	        
		    	h.put("abbrev", Utils.isNull(rs.getString("ABBREV")));
		    	h.put("negeri", Utils.isNull(rs.getString("NEGERI")));		    	
		    	getAbbrev.addElement(h);
			}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		}finally{
			if(db != null)db.close();
		}	
		return getAbbrev;	
	
	}	
	
	
	public Vector getListTotalPermohonanUnit(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(E.NAMA_PEJABAT,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTK :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_pejabat", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	public Vector getListTotalPermohonanUnit_add(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT ID_NEGERI,x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(E.NAMA_PEJABAT,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK, C.ID_NEGERI ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1)	";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
					
				sql += " ORDER BY C.ID_NEGERI ";
				sql += " ) ";
				sql += " GROUP BY ID_NEGERI,x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTP :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	//h.put("nama_pejabat", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_pejabat", rs.getString("x"));
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}


	public Vector getListTotalPermohonanUnitKod_add(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(C.KOD_NEGERI,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTR :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("kod_negeri", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	

	
	public Vector getListTotalPermohonanUnitLot(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(E.NAMA_PEJABAT,'TIADA MAKLUMAT') AS x, B.ID_PERMOHONAN, D.ID_HAKMILIK ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTQ :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_pejabat", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	public Vector getListTotalPermohonanUnitLotKod(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NAMA_PEJABAT,x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT E.NAMA_PEJABAT, NVL(C.KOD_NEGERI,'TIADA MAKLUMAT') AS x, B.ID_PERMOHONAN, D.ID_HAKMILIK ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY NAMA_PEJABAT,x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTPU :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("kod_negeri", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	h.put("nama_pejabat",rs.getString("NAMA_PEJABAT"));

		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	public Vector getListTotalPermohonanUnitLotKod_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT ID_NEGERI,NAMA_PEJABAT,x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT E.NAMA_PEJABAT, NVL(C.KOD_NEGERI,'TIADA MAKLUMAT') AS x, B.ID_PERMOHONAN, D.ID_HAKMILIK, C.ID_NEGERI ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1)	";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}

					
				sql += " ORDER BY C.ID_NEGERI ";
				sql += " ) ";
				sql += " GROUP BY ID_NEGERI,NAMA_PEJABAT,x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTD :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("kod_negeri", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	h.put("nama_pejabat",rs.getString("NAMA_PEJABAT"));

		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	public Vector getKodCawangan() throws Exception {
		Db db = null;
		String sql = "";
		try{
			getKodCawangan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			
			sql =  " SELECT distinct(RKME.KOD_NEGERI) AS KOD, RK.NAMA_PEJABAT AS CAWANGAN " +
					" FROM TBLRUJPEJABATJKPTG RK,TBLRUJNEGERI RKME " +
					" WHERE " +
					" RK.ID_NEGERI = RKME.ID_NEGERI AND RK.ID_SEKSYEN = '1' AND RK.ID_JENISPEJABAT=22" +
					" ORDER BY RKME.KOD_NEGERI" +
					"";
			myLogger.info(sql);
//				setSQL(sql);				
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;				
			while (rs.next()) {
				h = new Hashtable();	        
		    	h.put("kod", Utils.isNull(rs.getString("KOD")));
		    	h.put("cawangan", Utils.isNull(rs.getString("CAWANGAN")));		    	
		    	getKodCawangan.addElement(h);
			}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		}finally{
			if(db != null)db.close();
		}	
		return getKodCawangan;	
	
	}	
	
	
	public Vector getListTotalProjectKementerianAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NAMA_NEGERI, x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT C.NAMA_NEGERI, NVL(C.ABBREV,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, ";        
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) ";         
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY NAMA_NEGERI,x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTAD :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));


		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	public Vector getListTotalProjectKementerianAbbrev_add(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{ //EDIT BY YATI 18/12/2017
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NAMA_NEGERI, x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT C.ID_NEGERI,C.NAMA_NEGERI, NVL(C.ABBREV,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, ";        
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) ";         
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1) " ;
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY NAMA_NEGERI,x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTX :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));


		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	public Vector getListTotalProjectKementerianAbbrev_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{ //EDIT BY YATI 18/12/2017
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT ID_NEGERI,NAMA_NEGERI, x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT C.ID_NEGERI,C.NAMA_NEGERI, NVL(C.ABBREV,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, ";    
				sql += " TBLRUJDAERAH H ";     
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI ";   
				sql += " AND H.ID_DAERAH = D.ID_DAERAH ";   
				sql += " AND B.ID_DAERAH = D.ID_DAERAH  ";  
				sql += " AND H.ID_DAERAH IS NOT NULL "; 
				sql += " AND S.ID_STATUS = B.ID_STATUS ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1,12,13) " ;
				sql += " AND A.NO_FAIL IS NOT NULL " ;
				sql += " AND B.ID_STATUS NOT IN (4,5,11,113)" ;
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}

				
				sql += " ) ";
				sql += " GROUP BY ID_NEGERI,NAMA_NEGERI,x ";
				sql += " ORDER BY ID_NEGERI ";
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTXX :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));


		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	public Vector getListTotalSiasatan(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(E.NAMA_NEGERI,'TIADA NEGERI') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTSIASATAN D,TBLRUJNEGERI E ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK ";
				sql += " AND D.ID_NEGERI = A.ID_NEGERI ";		
				sql += " AND D.ID_NEGERI = E.ID_NEGERI ";				
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + id_bulan +"' ";
					}
				}
					
				sql += " GROUP BY E.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}	
	

	
	
	public Vector getListTotalBantahan(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				/*sql =  " SELECT NVL(F.NAMA_NEGERI,'TIADA MAKLUMAT') AS x, COUNT (*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN D, ";					
				sql += " TBLPPTHAKMILIKPB E,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK(+) = D.ID_HAKMILIK ";
				sql += " AND E.ID_HAKMILIKPB(+) = D.ID_HAKMILIKPB ";
				sql += " AND D.ID_NEGERI = F.ID_NEGERI(+) ";	
				*/
				
				sql = " SELECT  NG.ID_NEGERI ,NVL(NG.NAMA_NEGERI,'TIADA MAKLUMAT') AS NAMA_NEGERI, COUNT(F.ID_FAIL) AS y " +
						"FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM, TBLPPTHAKMILIKPB HPB ,TBLPPTBANTAHAN B,TBLRUJNEGERI NG " +
						"WHERE F.ID_FAIL = P.ID_FAIL " +
						"AND P.ID_PERMOHONAN = HM.ID_PERMOHONAN " +
						"AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK " +
						"AND F.ID_SEKSYEN = '1' " +
						"AND HPB.ID_HAKMILIKPB = B.ID_HAKMILIKPB " +
						"AND F.ID_NEGERI = NG.ID_NEGERI " ;
					
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY NG.ID_NEGERI,NAMA_NEGERI ";	
				sql += " ORDER BY NG.ID_NEGERI";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL BANTAHAN1 :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_negeri", rs.getString("nama_negeri"));
		    	getBantahan.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getBantahan;					
}	

	public Vector getListTotalBantahanAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(F.ABBREV,'TM') AS x, NVL(F.NAMA_NEGERI,'TIADA MAKLUMAT')NAMA_NEGERI, COUNT (*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN D, ";					
				sql += " TBLPPTHAKMILIKPB E,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK(+) = D.ID_HAKMILIK ";
				sql += " AND E.ID_HAKMILIKPB(+) = D.ID_HAKMILIKPB ";
				sql += " AND D.ID_NEGERI = F.ID_NEGERI(+) ";	
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY F.ABBREV,F.NAMA_NEGERI ";			
				sql += " order BY F.ABBREV ";		
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL BANTAHAN2 :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getBantahan.addElement(h);
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));


		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getBantahan;					
	}
	
	public Vector getListTotalBantahanAbbrev_add(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(F.ABBREV,'TM') AS x, NVL(F.NAMA_NEGERI,'TIADA MAKLUMAT')NAMA_NEGERI, COUNT (*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN D, ";					
				sql += " TBLPPTHAKMILIKPB E,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK(+) = D.ID_HAKMILIK ";
				sql += " AND E.ID_HAKMILIKPB(+) = D.ID_HAKMILIKPB ";
				sql += " AND D.ID_NEGERI = F.ID_NEGERI(+) ";	
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}

					
				sql += " GROUP BY F.ABBREV,F.NAMA_NEGERI ";			
				sql += " order BY F.ABBREV ";		
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL BANTAHAN3 :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getBantahan.addElement(h);
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));


		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getBantahan;					
	}
	
	public Vector getListDaerahByNegeriTotalBantahanAbbrev(Long idNegeri, String socTahun, String socBulan, String txdTarikhMula, String txdTarikhAkhir) throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL (NG.ABBREV, 'TM') AS X, ";
				sql += " NVL (D.NAMA_DAERAH, 'TIADA MAKLUMAT') NAMA_DAERAH, COUNT (*) AS Y, D.ID_DAERAH ";
				sql += " FROM TBLPFDFAIL F, TBLPPTPERMOHONAN P, TBLPPTHAKMILIK HM, TBLPPTHAKMILIKPB HPB, ";
				sql += " TBLPPTBANTAHAN B, TBLRUJNEGERI NG, TBLRUJDAERAH D ";					
									
				sql += " WHERE F.ID_FAIL = P.ID_FAIL ";
				sql += " AND P.ID_PERMOHONAN = HM.ID_PERMOHONAN ";
				sql += " AND F.ID_NEGERI = NG.ID_NEGERI ";
				sql += " AND HM.ID_DAERAH = D.ID_DAERAH ";
				sql += " AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK ";
				sql += " AND HPB.ID_HAKMILIKPB = B.ID_HAKMILIKPB ";
				sql += " AND F.ID_SEKSYEN = 1 ";
				// NEGERI
				if (idNegeri == 00) {
						sql = sql + " AND (F.ID_NEGERI is null OR F.ID_NEGERI = 00) ";
					} else if (idNegeri != 00) {
						sql = sql + " AND F.ID_NEGERI = '"+idNegeri+"' ";
						//C.ID_NEGERI = "+idNegeri;
						
					}
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
						sql += " AND P.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
					}
				}

					
				sql += " GROUP BY NG.ABBREV, D.NAMA_DAERAH, D.ID_DAERAH ";			
				sql += " ORDER BY D.ID_DAERAH ";		
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL BANTAHAN4 :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getBantahan.addElement(h);
		    	h.put("nama_daerah",rs.getString("NAMA_DAERAH"));


		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getBantahan;					
	}
	public Vector getListTotalPU(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPermintaanUkur = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT E.ID_NEGERI,NVL(E.NAMA_NEGERI,'TIADA NEGERI') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, ";
				sql += " TBLPPTPERMINTAANUKUR D,TBLRUJNEGERI E, ";					
				sql += " TBLPPTHAKMILIKBORANGK F,TBLPPTBORANGK G ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND A.ID_NEGERI = E.ID_NEGERI(+) ";
				sql += " AND C.ID_HAKMILIK = F.ID_HAKMILIK AND F.ID_BORANGK = G.ID_BORANGK ";
				sql += " AND E.ID_NEGERI NOT IN (1) ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY E.ID_NEGERI,E.NAMA_NEGERI ";		
				sql += " ORDER BY E.ID_NEGERI ";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PU1 :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));	
		    	h.put("nama_negeri",rs.getString("x"));
		    	getPermintaanUkur.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPermintaanUkur;					
}

	public Vector getListDaerahByNegeriTotalPUAbbrev_add(Long idNegeri, String socTahun, String socBulan, String txdTarikhMula, String txdTarikhAkhir) throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPermintaanUkur = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(H.NAMA_DAERAH,'TIADA MAKLUMAT')NAMA_DAERAH,NVL(E.ABBREV,'TM') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, ";
				sql += " TBLPPTPERMINTAANUKUR D,TBLRUJNEGERI E, ";					
				sql += " TBLPPTHAKMILIKBORANGK F,TBLRUJDAERAH H,TBLPPTBORANGK G ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND H.ID_DAERAH(+) = C.ID_DAERAH";		
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND A.ID_NEGERI = E.ID_NEGERI(+) ";
				sql += " AND C.ID_HAKMILIK = F.ID_HAKMILIK AND F.ID_BORANGK = G.ID_BORANGK AND E.ID_NEGERI = "+idNegeri;
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
						sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
					}
				}

					
				sql += " GROUP BY NAMA_DAERAH, E.ABBREV ";			
				sql += " ORDER BY NAMA_DAERAH ";	
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PU2 :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getPermintaanUkur.addElement(h);
		    	 h.put("nama_daerah",rs.getString("NAMA_DAERAH"));

		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPermintaanUkur;					
}
	public Vector getListTotalPUAbbrev_add(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir
			)throws Exception {		 		 
					 Db db = null;
					 String sql = "";
					try{
							getPermintaanUkur = new Vector();
							db = new Db();
							Statement stmt = db.getStatement();
							SQLRenderer r = new SQLRenderer();									
							
							sql =  " SELECT NVL(E.NAMA_NEGERI,'TIADA MAKLUMAT')NAMA_NEGERI,NVL(E.ABBREV,'TM') AS x, COUNT (*) AS y ";					
							sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, ";
							sql += " TBLPPTPERMINTAANUKUR D,TBLRUJNEGERI E, ";					
							sql += " TBLPPTHAKMILIKBORANGK F,TBLPPTBORANGK G ";					
							sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
							sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND A.ID_NEGERI = E.ID_NEGERI(+) ";
							sql += " AND C.ID_HAKMILIK = F.ID_HAKMILIK AND F.ID_BORANGK = G.ID_BORANGK ";
							
				    		// TAHUN 
							if (id_tahun != null) {
								if (!id_tahun.trim().equals("")) {
									sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
								}
							}
							
				    		// BULAN
							if (id_bulan != null) {
								if (!id_bulan.trim().equals("")) {
									sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
								}
							}
							
							if (txdTarikhMula != null && txdTarikhAkhir != null) {
								if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
							
							sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
							" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
							" 'DD/MM/YYYY')";
							
								}
							}

								
							sql += " GROUP BY NAMA_NEGERI, E.ABBREV ";			
							sql += " ORDER BY NAMA_NEGERI ";	
							setSQL(sql);
							
							myLogger.info("LAPORAN TOTAL PU3 :: "+sql);
							
							ResultSet rs = stmt.executeQuery(sql);
							Hashtable h;	
							int bil = 1;
							
					     while (rs.next()) {
					    	h = new Hashtable();			    	
					    	h.put("bil", bil);
					    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
					    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
					    	getPermintaanUkur.addElement(h);
					    	 h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

					    	bil++;
					      	}      
					} catch (Exception er) {
						myLogger.error(er);
						throw er;
						}
							finally{
								if(db != null)db.close();
							}	
					return getPermintaanUkur;					
			}

	public Vector getListTotalAfidavit(String id_tahun,String id_bulan) throws Exception {
		 Db db = null;
		 String sql = "";
		try{
				getAfidavit = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(F.NAMA_NEGERI,'TIADA NEGERI') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";					
				sql += " TBLPPTAWARD E,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIKPB ";
				sql += " AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
				sql += " AND A.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND F.ID_NEGERI NOT IN (1)";

	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY F.NAMA_NEGERI ";			
				sql += " ORDER BY x ";	
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL AFIDAVITz :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    	
		    	getAfidavit.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getAfidavit;					
	}	
	
	public Vector getListTotalAfidavitAbbrev(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		 Db db = null;
		 String sql = "";
		try{
				getAfidavit = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT F.ID_NEGERI,NVL(F.NAMA_NEGERI,'TIADA MAKLUMAT')NAMA_NEGERI, NVL(F.ABBREV,'TM') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";					
				sql += " TBLPPTAWARD E,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIKPB ";
				sql += " AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
				sql += " AND A.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND F.ID_NEGERI NOT IN (1,12,13) ";

	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
					
				sql += " GROUP BY F.ID_NEGERI,NAMA_NEGERI, F.ABBREV ";	
				sql += " ORDER BY F.ID_NEGERI ";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL AFIDAVITy :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getAfidavit.addElement(h);
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getAfidavit;					
	}	
	 public Vector getListDaerahByNegeriTotalAfidavitAbbrev(Long idNegeri, String socTahun, String socBulan, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
		 Db db = null;
		 String sql = "";
		try{
				getAfidavit = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(H.NAMA_DAERAH,'TIADA MAKLUMAT')NAMA_DAERAH, NVL(F.ABBREV,'TM') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";					
				sql += " TBLPPTAWARD E,TBLRUJDAERAH H,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIKPB ";
				sql += " AND  H.ID_DAERAH(+) = C.ID_DAERAH";
				sql += " AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
				sql += " AND A.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND F.ID_NEGERI = "+idNegeri;
				

	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
						sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
					}
				}
				
					
				sql += " GROUP BY NAMA_DAERAH, F.ABBREV ";	
				sql += " ORDER BY x ";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL AFIDAVITa :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getAfidavit.addElement(h);
		    	h.put("nama_daerah",rs.getString("NAMA_DAERAH"));

		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getAfidavit;					
	}	
	

	public Vector getListTotalJumlahPampasan(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				sql += " AND N.ID_NEGERI NOT IN (1) ";
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.NAMA_NEGERI  ";	
				sql += " ORDER BY x ";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASANF :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_negeri",rs.getString("x"));
		    	
		    	getPampasan.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasan;					
}	
	

	public Vector getListTotalJumlahPampasanAbbrev(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI, N.ABBREV AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND P.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}

				
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASANH :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

		    	getPampasan.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasan;					
}	
	
	public Vector getListDaerahByNegeriTotalJumlahPampasanAbbrev(Long idNegeri, String socTahun, String socBulan, String txdTarikhMula, String txdTarikhAkhir) throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT E.NAMA_DAERAH, N.ABBREV AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJDAERAH E,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND E.ID_DAERAH(+) = H.ID_DAERAH ";
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				sql += " AND N.ID_NEGERI = "+idNegeri;
				
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
						sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
					}
				}
				
				
				sql += " GROUP BY E.NAMA_DAERAH,N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASANK :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_daerah",rs.getString("NAMA_DAERAH"));

		    	getPampasan.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasan;					
}
	public Vector getListStatusPengambilan(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatus = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  ID_NEGERI,NAMA_NEGERI, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),'0') SELESAI, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),'0') XSELESAI ";					
				sql += " FROM (   ";					
				sql += " SELECT NAMA_NEGERI, SELESAI , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN   ";		
				sql += " FROM ( ";
				sql += " SELECT N.NAMA_NEGERI,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";					
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";		
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";				
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";	
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";					
				sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";				
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL";				
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
						
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				sql += " GROUP BY N.NAMA_NEGERI ";
				sql += " UNION ";				
				sql += " SELECT N.NAMA_NEGERI,COUNT(DISTINCT P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,tblrujnegeri N ";
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";		
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";				
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";		
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
				
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				
				sql += " GROUP BY N.NAMA_NEGERI) ";		
				sql += " )GROUP BY ID_NEGERI,NAMA_NEGERI ";		
				//sql += " GROUP BY N.NAMA_NEGERI ORDER BY 1 ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS PENGAMBILANX :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("jumlah_permohonan_selesai", rs.getString("SELESAI")==null?"":rs.getString("SELESAI"));  
		    	h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI")==null?"":rs.getString("XSELESAI")); 
		    	
		    	getStatus.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatus;					
}	
	
	public Vector getListStatusPengambilan_add(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir
)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatus = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  ID_NEGERI,NAMA_NEGERI, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),'0') SELESAI, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),'0') XSELESAI ";					
				sql += " FROM (   ";					
				sql += " SELECT ID_NEGERI,NAMA_NEGERI, SELESAI , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN   ";		
				sql += " FROM ( ";
				sql += " SELECT N.ID_NEGERI,N.NAMA_NEGERI,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";					
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";		
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";				
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";	
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";					
				sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";				
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL";				
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16,1) ";
						
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND P.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
				
				sql += " GROUP BY N.ID_NEGERI,N.NAMA_NEGERI ";
				sql += " UNION ";				
				sql += " SELECT N.ID_NEGERI,N.NAMA_NEGERI,COUNT(DISTINCT P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,tblrujnegeri N ";
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";		
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";				
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";		
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16,1) ";
				sql += " AND N.ID_NEGERI NOT IN (1) ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				
				sql += " GROUP BY N.ID_NEGERI,N.NAMA_NEGERI) ";		
				sql += " )GROUP BY ID_NEGERI,NAMA_NEGERI ";		
				sql += " ORDER BY ID_NEGERI";
				//sql += " GROUP BY N.NAMA_NEGERI ORDER BY 1 ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS PENGAMBILANXZZ :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("jumlah_permohonan_selesai", rs.getString("SELESAI")==null?"":rs.getString("SELESAI"));  
		    	h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI")==null?"":rs.getString("XSELESAI")); 
		    	
		    	getStatus.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatus;					
}	
	
	public Vector getListStatusPengambilanAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatus = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI,ABBREV, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),'0') SELESAI, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),'0') XSELESAI ";					
				sql += " FROM (   ";					
				sql += " SELECT NAMA_NEGERI,ABBREV, SELESAI , ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN   ";		
				sql += " FROM ( ";
				sql += " SELECT N.NAMA_NEGERI,N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";					
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";		
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";				
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";	
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";					
				sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";				
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL";				
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
						
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV ";
				sql += " UNION ";				
				sql += " SELECT N.NAMA_NEGERI,N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,tblrujnegeri N ";
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";		
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";				
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";		
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
				
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV) ";		
				sql += " )GROUP BY NAMA_NEGERI,ABBREV ";		
				//sql += " GROUP BY N.NAMA_NEGERI ORDER BY 1 ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS PENGAMBILANXZ :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("ABBREV")));
		    	h.put("jumlah_permohonan_selesai", rs.getString("SELESAI")==null?"":rs.getString("SELESAI"));  
		    	h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI")==null?"":rs.getString("XSELESAI")); 
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

		    	getStatus.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatus;					
        }	
	
	
	public Vector getListStatusPengambilanAbbrev_baru(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatus = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI,ABBREV, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),'0') SELESAI, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),'0') XSELESAI ";					
				sql += " FROM (   ";					
				sql += " SELECT NAMA_NEGERI,ABBREV, SELESAI , ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN   ";		
				sql += " FROM ( ";
				sql += " SELECT N.NAMA_NEGERI,N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";					
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";		
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";				
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";	
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";					
				sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";				
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL";				
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
						
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND P.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV ";
				sql += " UNION ";				
				sql += " SELECT N.NAMA_NEGERI,N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,tblrujnegeri N ";
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";		
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";				
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";		
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
				
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV) ";		
				sql += " )GROUP BY NAMA_NEGERI,ABBREV ";		
				//sql += " GROUP BY N.NAMA_NEGERI ORDER BY 1 ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS PENGAMBILANXXX :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("ABBREV")));
		    	h.put("jumlah_permohonan_selesai", rs.getString("SELESAI")==null?"":rs.getString("SELESAI"));  
		    	h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI")==null?"":rs.getString("XSELESAI")); 
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

		    	getStatus.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatus;					
       }	
	
	public Vector getListStatusPengambilanAbbrev_add(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatus = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI,ABBREV, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),'0') SELESAI, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),'0') XSELESAI ";					
				sql += " FROM (   ";					
				sql += " SELECT NAMA_NEGERI,ABBREV, SELESAI , ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN   ";		
				sql += " FROM ( ";
				sql += " SELECT N.NAMA_NEGERI,N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";					
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";		
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";				
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";	
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";					
				sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";				
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL";				
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
						
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}	
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND P.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV ";
				sql += " UNION ";				
				sql += " SELECT N.NAMA_NEGERI,N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,tblrujnegeri N ";
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";		
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";				
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";		
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
				
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV) ";		
				sql += " )GROUP BY NAMA_NEGERI,ABBREV ";		
				//sql += " GROUP BY N.NAMA_NEGERI ORDER BY 1 ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS PENGAMBILANXX :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("ABBREV")));
		    	h.put("jumlah_permohonan_selesai", rs.getString("SELESAI")==null?"":rs.getString("SELESAI"));  
		    	h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI")==null?"":rs.getString("XSELESAI")); 
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

		    	getStatus.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatus;					
}

	public Vector getListCajBayaranLewat(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getCajBayaranLewat = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.ID_NEGERI,N.NAMA_NEGERI AS x, NVL(SUM (A.DENDA_LEWAT),0) AS y ";					
				sql += " FROM TBLPPTPERMOHONAN P,TBLPPTHAKMILIK H,  ";
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTBAYARAN A, ";					
				sql += " TBLPFDFAIL F,TBLRUJNEGERI N ";					
				sql += " WHERE F.ID_FAIL = P.ID_FAIL ";		
				sql += " AND P.ID_PERMOHONAN = H.ID_PERMOHONAN ";
				sql += " AND H.ID_HAKMILIK = HPB.ID_HAKMILIK ";					
				sql += " AND HPB.ID_HAKMILIKPB = A.ID_HAKMILIKPB ";					
				sql += " AND F.ID_NEGERI = N.ID_NEGERI ";	
				sql += " AND N.ID_NEGERI NOT IN (1) ";	
							
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.ID_NEGERI,N.NAMA_NEGERI  ";	
				sql += " ORDER BY N.ID_NEGERI  ";	
				
				setSQL(sql);
				
				myLogger.info("LAPORAN CAJ BYR LEWATS :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);			    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_negeri",rs.getString("x"));
		    	
		    	getCajBayaranLewat.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getCajBayaranLewat;					
}		
	
	public Vector getListCajBayaranLewat(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getCajBayaranLewat = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI AS x, NVL(SUM (A.DENDA_LEWAT),0) AS y ";					
				sql += " FROM TBLPPTPERMOHONAN P,TBLPPTHAKMILIK H,  ";
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTBAYARAN A, ";					
				sql += " TBLPFDFAIL F,TBLRUJNEGERI N ";					
				sql += " WHERE F.ID_FAIL = P.ID_FAIL ";		
				sql += " AND P.ID_PERMOHONAN = H.ID_PERMOHONAN ";
				sql += " AND H.ID_HAKMILIK = HPB.ID_HAKMILIK ";					
				sql += " AND HPB.ID_HAKMILIKPB = A.ID_HAKMILIKPB ";					
				sql += " AND F.ID_NEGERI = N.ID_NEGERI ";	
							
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}	
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND P.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
				
				
				sql += " GROUP BY N.NAMA_NEGERI  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN CAJ BYR LEWATX :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);			    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getCajBayaranLewat.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getCajBayaranLewat;					
}		
	
	public Vector getListCajBayaranLewatAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getCajBayaranLewat = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI,N.ABBREV AS x, NVL(SUM (A.DENDA_LEWAT),0) AS y ";					
				sql += " FROM TBLPPTPERMOHONAN P,TBLPPTHAKMILIK H,  ";
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTBAYARAN A, ";					
				sql += " TBLPFDFAIL F,TBLRUJNEGERI N ";					
				sql += " WHERE F.ID_FAIL = P.ID_FAIL ";		
				sql += " AND P.ID_PERMOHONAN = H.ID_PERMOHONAN ";
				sql += " AND H.ID_HAKMILIK = HPB.ID_HAKMILIK ";					
				sql += " AND HPB.ID_HAKMILIKPB = A.ID_HAKMILIKPB ";					
				sql += " AND F.ID_NEGERI = N.ID_NEGERI ";	
							
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN CAJ BYR LEWATV :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);			    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

		    	getCajBayaranLewat.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getCajBayaranLewat;					
}		
	
	public Vector getListCajBayaranLewatAbbrev_add(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getCajBayaranLewat = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI,N.ABBREV AS x, NVL(SUM (A.DENDA_LEWAT),0) AS y ";					
				sql += " FROM TBLPPTPERMOHONAN P,TBLPPTHAKMILIK H,  ";
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTBAYARAN A, ";					
				sql += " TBLPFDFAIL F,TBLRUJNEGERI N ";					
				sql += " WHERE F.ID_FAIL = P.ID_FAIL ";		
				sql += " AND P.ID_PERMOHONAN = H.ID_PERMOHONAN ";
				sql += " AND H.ID_HAKMILIK = HPB.ID_HAKMILIK ";					
				sql += " AND HPB.ID_HAKMILIKPB = A.ID_HAKMILIKPB ";					
				sql += " AND F.ID_NEGERI = N.ID_NEGERI ";	
							
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND P.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
				
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN CAJ BYR LEWATA :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);			    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

		    	getCajBayaranLewat.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getCajBayaranLewat;					
}		

	
	public Vector getListDaerahByNegeriCajBayaranLewatAbbrev_add(String idNegeri, String socTahun, String socBulan, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
		 Db db = null;
		 String sql = "";
		try{
			getCajBayaranLewat = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT C.NAMA_DAERAH,N.ABBREV AS x, NVL(SUM (A.DENDA_LEWAT),0) AS y ";					
				sql += " FROM TBLPPTPERMOHONAN P,TBLPPTHAKMILIK H,  ";
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTBAYARAN A, ";					
				sql += " TBLPFDFAIL F,TBLRUJNEGERI N, ";
				sql += " TBLRUJDAERAH C ";
				sql += " WHERE F.ID_FAIL = P.ID_FAIL ";		
				sql += " AND P.ID_PERMOHONAN = H.ID_PERMOHONAN ";
				sql += " AND H.ID_HAKMILIK = HPB.ID_HAKMILIK ";					
				sql += " AND HPB.ID_HAKMILIKPB = A.ID_HAKMILIKPB ";					
				sql += " AND F.ID_NEGERI = N.ID_NEGERI ";
				sql += " AND C.ID_DAERAH(+) = H.ID_DAERAH ";
				sql += " AND N.ID_NEGERI = "+idNegeri;
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
						sql += " AND P.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
					}
				}
				sql = sql + "GROUP BY C.NAMA_DAERAH, N.ABBREV";		
				
				setSQL(sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
				while (rs.next()) {
			    	h = new Hashtable();			    	
			    	h.put("bil", bil);			    	
			    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
			    	h.put("jumlah_permohonan",rs.getDouble("y"));
			    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
			    	h.put("nama_daerah", Utils.isNull(rs.getString("NAMA_DAERAH")));

			    	getCajBayaranLewat.addElement(h);
			    	bil++;
			      }      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getCajBayaranLewat;		
	}

	
	public Vector getListJumlahPampasanAmanahRayaBerhad(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasanAmanahRaya = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				sql += " AND AW.STATUS_PENERIMA = '3' ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.NAMA_NEGERI  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASANZ :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getPampasanAmanahRaya.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasanAmanahRaya;					
}			
	public Vector getListJumlahPampasanAmanahRayaBerhad_add(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{ //EDIT BY 18/12/2017
				getPampasanAmanahRaya = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.ID_NEGERI AS z, N.NAMA_NEGERI AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				sql += " AND AW.STATUS_PENERIMA = '3' ";
				sql += " AND N.ID_NEGERI NOT IN (1) " ;
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}	
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND P.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}

				
				
				sql += " GROUP BY N.ID_NEGERI,N.NAMA_NEGERI  ";			
				sql += " ORDER BY N.ID_NEGERI  ";		
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASANX :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_negeri",rs.getString("x"));
		    	
		    	getPampasanAmanahRaya.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasanAmanahRaya;					
}			
	public Vector getListJumlahPampasanAmanahRayaBerhadAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasanAmanahRaya = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI, N.ABBREV AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				sql += " AND AW.STATUS_PENERIMA = '3' ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASANY :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

		    	getPampasanAmanahRaya.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasanAmanahRaya;					
}	
	
	public Vector getListJumlahPampasanAmanahRayaBerhadAbbrev_add(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasanAmanahRaya = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI, N.ABBREV AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				sql += " AND AW.STATUS_PENERIMA = '3' ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND P.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
				
				
				sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASANA :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_negeri",rs.getString("NAMA_NEGERI"));

		    	getPampasanAmanahRaya.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasanAmanahRaya;					
}
	public Vector getListDaerahByNegeriJumlahPampasanAmanahRayaBerhadAbbrev_add(Long idNegeri, String socTahun, String socBulan, String txdTarikhMula, String txdTarikhAkhir) throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasanAmanahRaya = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT E.NAMA_DAERAH, N.ABBREV AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJDAERAH E,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				sql += " AND E.ID_DAERAH(+) = H.ID_DAERAH ";
				sql += " AND AW.STATUS_PENERIMA = '3' ";
				sql += " AND N.ID_NEGERI = "+idNegeri;
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
						sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
					}
				}
				sql += " GROUP BY E.NAMA_DAERAH,N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASANS :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	h.put("nama_daerah",rs.getString("NAMA_DAERAH"));

		    	getPampasanAmanahRaya.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasanAmanahRaya;					
}
	
	public Vector getListTanahRizab_add(String idNegeri,String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalTanahRizab = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT D.ID_NEGERI,D.NAMA_NEGERI AS x, count(*) AS y, SUM(C.LUAS_AMBIL) AS LUAS_RIZAB ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLRUJNEGERI D ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND A.ID_NEGERI = D.ID_NEGERI ";
				sql += " AND C.FLAG_JENIS_RIZAB  = '1' ";		
				sql += " AND D.ID_NEGERI NOT IN (1,12,13) ";

	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + id_bulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}

					
				sql += " GROUP BY D.ID_NEGERI,D.NAMA_NEGERI ";			
				sql += " ORDER BY D.ID_NEGERI ";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL TANAH RIZABY :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	//h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));	
		    	h.put("nama_negeri", rs.getString("x"));
		    	h.put("luas_rizab", rs.getString("LUAS_RIZAB"));
		    	
		    	getTotalTanahRizab.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalTanahRizab;					
}
	
	public Vector getListDaerahByNegeriTanahRizab_add(String idNegeri,String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalTanahRizab = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT E.NAMA_DAERAH AS x, count(*) AS y, SUM(C.LUAS_AMBIL) AS LUAS_RIZAB ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLRUJNEGERI D, ";	
				sql += " TBLRUJDAERAH E";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND A.ID_NEGERI = D.ID_NEGERI ";
				sql += " AND E.ID_DAERAH(+) = C.ID_DAERAH";
				sql += " AND D.ID_NEGERI = "+idNegeri;
				sql += " AND C.FLAG_JENIS_RIZAB = '1' ";
				sql += " AND D.ID_NEGERI NOT IN (1,12,13) ";

	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + id_bulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}

					
				sql += " GROUP BY E.NAMA_DAERAH ";			
				sql += " ORDER BY 1 ";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL TANAH RIZABM :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_daerah", Utils.NiceStateName(rs.getString("x")));	
		    	h.put("luas_rizab", rs.getString("LUAS_RIZAB"));
		    	
		    	getTotalTanahRizab.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalTanahRizab;					
}
	
	
	public Vector getListStatusBantahanMahkamah(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, BANTAHAN ))),'0') BANTAHAN_PB, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, BANTAHAN ))),'0') BANTAHAN_AP ";					
				sql += " FROM (  ";					
				sql += " SELECT NAMA_NEGERI, REPLACE(REPLACE(BANTAHAN,'A'),'B') BANTAHAN  , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN ";		
				sql += " FROM (  ";
				sql += " SELECT N.NAMA_NEGERI, 'A' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN  ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";		
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";				
				sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N ";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";	
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";					
				sql += " AND A.ID_NEGERI = N.ID_NEGERI ";									
				sql += " GROUP BY N.NAMA_NEGERI ";
				sql += " UNION  ";				
				sql += " SELECT N.NAMA_NEGERI, 'B' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,  ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN E,TBLRUJNEGERI N ";		
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";				
				sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK AND A.ID_NEGERI = N.ID_NEGERI ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}		
				
				sql += " GROUP BY N.NAMA_NEGERI)  ) ";
				sql += " GROUP BY NAMA_NEGERI ";				
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS BANTAHAN APZ :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("jumlah_permohonan_bantahanpb", rs.getString("BANTAHAN_PB")==null?"":rs.getString("BANTAHAN_PB"));  
		    	h.put("jumlah_permohonan_bantahanap", rs.getString("BANTAHAN_AP")==null?"":rs.getString("BANTAHAN_AP")); 
		    	
		    	getStatusBantahan.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusBantahan;					
}		
	
	public Vector getListStatusBantahanMahkamah_add(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, BANTAHAN ))),'0') BANTAHAN_PB, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, BANTAHAN ))),'0') BANTAHAN_AP ";					
				sql += " FROM (  ";					
				sql += " SELECT NAMA_NEGERI, REPLACE(REPLACE(BANTAHAN,'A'),'B') BANTAHAN  , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN ";		
				sql += " FROM (  ";
				sql += " SELECT N.NAMA_NEGERI, 'A' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN  ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";		
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";				
				sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N ";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";	
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";					
				sql += " AND A.ID_NEGERI = N.ID_NEGERI ";
				
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}	
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}
				
				sql += " GROUP BY N.NAMA_NEGERI ";
				sql += " UNION  ";				
				sql += " SELECT N.NAMA_NEGERI, 'B' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,  ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN E,TBLRUJNEGERI N ";		
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";				
				sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK AND A.ID_NEGERI = N.ID_NEGERI ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}	
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
				
				sql += " AND B.TARIKH_PERMOHONAN BETWEEN "+
				" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
				" 'DD/MM/YYYY')";
				
					}
				}

				
				sql += " GROUP BY N.NAMA_NEGERI)  ) ";
				sql += " GROUP BY NAMA_NEGERI ";				
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS BANTAHAN APA :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
		    	h.put("jumlah_permohonan_bantahanpb", rs.getString("BANTAHAN_PB")==null?"":rs.getString("BANTAHAN_PB"));  
		    	h.put("jumlah_permohonan_bantahanap", rs.getString("BANTAHAN_AP")==null?"":rs.getString("BANTAHAN_AP")); 
		    	
		    	getStatusBantahan.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusBantahan;					
}	
	
	public Vector getListDaerahByNegeriStatusBantahanMahkamah_add(Long idNegeri, String socTahun, String socBulan, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
		 Db db = null;
		 String sql = "";
		try{
			getStatusBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_DAERAH, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, BANTAHAN ))),'0') BANTAHAN_PB, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, BANTAHAN ))),'0') BANTAHAN_AP ";					
				sql += " FROM (  ";					
				sql += " SELECT NAMA_DAERAH, REPLACE(REPLACE(BANTAHAN,'A'),'B') BANTAHAN  , ROW_NUMBER() OVER ( PARTITION BY NAMA_DAERAH ORDER BY ROWNUM) RN ";		
				sql += " FROM (  ";
				sql += " SELECT F.NAMA_DAERAH, 'A' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN  ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";		
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";				
				sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N, ";
				sql += " TBLRUJDAERAH F";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";	
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";					
				sql += " AND A.ID_NEGERI = N.ID_NEGERI AND F.ID_DAERAH(+) = C.ID_DAERAH ";
				sql += " AND N.ID_NEGERI = "+idNegeri;
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
						sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
					}
				}
				
				sql += " GROUP BY F.NAMA_DAERAH ";
				sql += " UNION  ";				
				sql += " SELECT F.NAMA_DAERAH, 'B' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C,  ";
				sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N,TBLRUJDAERAH F ";		
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";				
				sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK AND A.ID_NEGERI = N.ID_NEGERI ";
				sql += " AND F.ID_DAERAH(+) = C.ID_DAERAH AND N.ID_NEGERI = "+idNegeri;
				
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
						sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
					}
				}
				sql += " GROUP BY F.NAMA_DAERAH)  ) ";
				sql += " GROUP BY NAMA_DAERAH ";			
				
				setSQL(sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
				while (rs.next()) {
			    	h = new Hashtable();			    	
			    	h.put("bil", bil);		        
//			    	h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
			    	h.put("nama_daerah", Utils.NiceStateName(rs.getString("NAMA_DAERAH")));
			    	h.put("jumlah_permohonan_bantahanpb", rs.getString("BANTAHAN_PB")==null?"":rs.getString("BANTAHAN_PB"));  
			    	h.put("jumlah_permohonan_bantahanap", rs.getString("BANTAHAN_AP")==null?"":rs.getString("BANTAHAN_AP")); 
			    	
			    	
			    	getStatusBantahan.addElement(h);
			    	bil++;
			      }      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusBantahan;		
	}
	
	
	public Vector getListStatusPenggunaaneTaPP(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			getStatusPenggunaan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI, ";					
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 1, BIL ))),0) SPT,   ";
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 2, BIL ))),0) SPPK,  ";
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 3, BIL ))),0) SHTP,  ";	
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 4, BIL ))),0) SPHP  ";	   
				sql += " FROM (  ";					
				sql += " SELECT NAMA_NEGERI, BIL , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN  ";	
				sql += " FROM (  ";				
				sql += " SELECT NN.NAMA_NEGERI, NVL(N.BIL,'0')BIL FROM (";
				sql += " SELECT E.ID_NEGERI,E.NAMA_NEGERI, NVL(COUNT(E.ID_NEGERI),'0') AS BIL ";
				sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
				sql += " WHERE A.USER_NAME = B.USER_LOGIN ";
				sql += " AND B.USER_ID = C.USER_ID ";
				sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";
				sql += " AND C.ID_NEGERI = E.ID_NEGERI "; 
				sql += " AND D.ID_SEKSYEN=1 ";
				
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI,E.ID_NEGERI) N,TBLRUJNEGERI NN";
				sql += " WHERE NN.ID_NEGERI = N.ID_NEGERI(+)  ";
				sql += " GROUP BY NN.NAMA_NEGERI,N.BIL  ";
				sql += " UNION  ";
				sql += " SELECT NN.NAMA_NEGERI, NVL(N.BIL,'0')BIL FROM (";
				sql += " SELECT E.ID_NEGERI,E.NAMA_NEGERI, NVL(COUNT(E.ID_NEGERI),'0') AS BIL  ";
				sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
				sql += " WHERE A.USER_NAME = B.USER_LOGIN ";
				sql += " AND B.USER_ID = C.USER_ID ";
				sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";
				sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
				sql += " AND D.ID_SEKSYEN=2 ";
				
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI,E.ID_NEGERI) N,TBLRUJNEGERI NN";
				sql += " WHERE NN.ID_NEGERI = N.ID_NEGERI(+)  ";
				sql += " GROUP BY NN.NAMA_NEGERI,N.BIL  ";	
				sql += " UNION  ";	
				sql += " SELECT NN.NAMA_NEGERI, NVL(N.BIL,'0')BIL FROM (";
				sql += " SELECT E.ID_NEGERI,E.NAMA_NEGERI, NVL(COUNT(E.ID_NEGERI),'0') AS BIL  ";	
				sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";	
				sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
				sql += " AND B.USER_ID = C.USER_ID "; 	
				sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
				sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
				sql += " AND D.ID_SEKSYEN=3 ";
				
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI,E.ID_NEGERI) N,TBLRUJNEGERI NN";
				sql += " WHERE NN.ID_NEGERI = N.ID_NEGERI(+)  ";
				sql += " GROUP BY NN.NAMA_NEGERI,N.BIL  ";	
				sql += " UNION  ";	
				sql += " SELECT NN.NAMA_NEGERI, NVL(N.BIL,'0')BIL FROM (";
				sql += " SELECT E.ID_NEGERI,E.NAMA_NEGERI, NVL(COUNT(E.ID_NEGERI),'0') AS BIL  ";	
				sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
				sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
				sql += " AND B.USER_ID = C.USER_ID ";	
				sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
				sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
				sql += " AND D.ID_SEKSYEN=4 ";
				
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI,E.ID_NEGERI) N,TBLRUJNEGERI NN";
				sql += " WHERE NN.ID_NEGERI = N.ID_NEGERI(+)  ";
				sql += " GROUP BY NN.NAMA_NEGERI,N.BIL 	)) ";
				sql += " GROUP BY NAMA_NEGERI ";				
				
				setSQL(sql);
				
				myLogger.info("LAPORAN PENGGUNAAN ETAPP :: "+sql);
				System.out.println("LAPORAN PENGGUNAAN ETAPP :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("dari_spt", rs.getString("SPT")==null?"":rs.getString("SPT"));  
		    	h.put("dari_sppk", rs.getString("SPPK")==null?"":rs.getString("SPPK")); 
		    	h.put("dari_shtp", rs.getString("SHTP")==null?"":rs.getString("SHTP")); 
		    	h.put("dari_sphp", rs.getString("SPHP")==null?"":rs.getString("SPHP")); 
	//	    	h.put("dari_spf", rs.getString("SPF")==null?"":rs.getString("SPF")); 
		    	
		    	getStatusPenggunaan.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusPenggunaan;					
}		

	public Vector getListStatusPenggunaaneTaPPOnline(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			getStatusPenggunaanOnline = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI, ";					
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 1, BIL ))),0) AduanCadangan, ";
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 2, BIL ))),0) StatusAduan,  ";
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 3, BIL ))),0) MaklumatPemohon,  ";	
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 4, BIL ))),0) StatusPermohonan    ";	    
				sql += " FROM (  ";					
				sql += " select NAMA_NEGERI ,BIL, ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN  ";	
				sql += " FROM (  ";
				sql += " SELECT  E.NAMA_NEGERI, A.MODULE_NAME, COUNT(*) BIL ";
				sql += " FROM USER_TRACKER A, USERS B , USERS_ONLINE C,  TBLRUJNEGERI E ";
				sql += " WHERE A.USER_LOGIN = B.USER_LOGIN ";
				sql += " AND B.USER_ID = C.USER_ID ";
				sql += " AND C.ID_NEGERI = E.ID_NEGERI  ";
				sql += " AND A.MODULE_NAME=   'Aduan & Cadangan' "; 
								
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI,A.MODULE_NAME ";
				sql += " UNION  ";
				sql += " SELECT  E.NAMA_NEGERI, A.MODULE_NAME, COUNT(*) BIL ";
				sql += " FROM USER_TRACKER A, USERS B , USERS_ONLINE C,  TBLRUJNEGERI E ";
				sql += " WHERE A.USER_LOGIN = B.USER_LOGIN ";
				sql += " AND B.USER_ID = C.USER_ID ";
				sql += " AND C.ID_NEGERI = E.ID_NEGERI  ";
				sql += " AND A.MODULE_NAME=   'Status Aduan' "; 
								
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI,A.MODULE_NAME ";
				sql += " UNION  ";	
				sql += " SELECT  E.NAMA_NEGERI, A.MODULE_NAME, COUNT(*) BIL ";
				sql += " FROM USER_TRACKER A, USERS B , USERS_ONLINE C,  TBLRUJNEGERI E ";
				sql += " WHERE A.USER_LOGIN = B.USER_LOGIN ";
				sql += " AND B.USER_ID = C.USER_ID ";
				sql += " AND C.ID_NEGERI = E.ID_NEGERI  ";
				sql += " AND A.MODULE_NAME=   'Maklumat Pemohon' "; 
								
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI,A.MODULE_NAME ";
				sql += " UNION  ";
				sql += " SELECT  E.NAMA_NEGERI, A.MODULE_NAME, COUNT(*) BIL ";
				sql += " FROM USER_TRACKER A, USERS B , USERS_ONLINE C,  TBLRUJNEGERI E ";
				sql += " WHERE A.USER_LOGIN = B.USER_LOGIN ";
				sql += " AND B.USER_ID = C.USER_ID ";
				sql += " AND C.ID_NEGERI = E.ID_NEGERI  ";
				sql += " AND A.MODULE_NAME=   'Status Permohonan' "; 
								
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI,A.MODULE_NAME ) )";
				sql += " GROUP BY NAMA_NEGERI ";				
				
				setSQL(sql);
				
				myLogger.info("LAPORAN PENGGUNAAN ETAPP ONLINE :: "+sql);
				System.out.println("LAPORAN PENGGUNAAN ETAPP ONLINE :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("dari_AduanCadangan", rs.getString("AduanCadangan")==null?"":rs.getString("AduanCadangan"));  
		    	h.put("dari_StatusAduan", rs.getString("StatusAduan")==null?"":rs.getString("StatusAduan")); 
		    	h.put("dari_MaklumatPemohon", rs.getString("MaklumatPemohon")==null?"":rs.getString("MaklumatPemohon")); 
		    	h.put("dari_StatusPermohonan", rs.getString("StatusPermohonan")==null?"":rs.getString("StatusPermohonan")); 

		    	
		    	getStatusPenggunaanOnline.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusPenggunaanOnline;					
}		

	public Vector getListStatusPenggunaaneTaPPOnlineUpdated(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			getStatusPenggunaanOnlineUpdated = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT * FROM ( ";					
				sql += " SELECT D.NAMA_NEGERI, A.MODULE_NAME ";
				sql += " FROM USER_TRACKER A , USERS B, USERS_ONLINE C, TBLRUJNEGERI D  ";
				sql += " WHERE  A.USER_LOGIN = B.USER_LOGIN  ";	
				sql += " AND B.USER_ID = C.USER_ID    ";	    
				sql += " AND C.ID_NEGERI= D.ID_NEGERI  ";
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}

				sql += " AND B.USER_TYPE='online')  ";	
				sql += " PIVOT (  ";
				sql += " COUNT(MODULE_NAME) ";
				sql += " FOR MODULE_NAME IN ( ";
				sql += " 'Permohonan Pusaka Kecil'\"S8\", ";
				sql += " 'Kemaskini Seksyen 17'\"S17\", ";
				sql += " 'Status Permohonan'\"StatusPermohonan\", ";
				sql += " 'Bantahan Mahkamah - Pihak Berkepentingan'\"Bantahan\", ";
				sql += " 'PYW Online Senarai Fail'\"Penyewaan\", ";
				sql += " 'FrmAPBOnlineSenaraiFailView'\"APB\", ";
				sql += " 'Bayaran Online'\"PembayaranOnline\",'Manual Pengguna'\"PanduanPengguna\", ";
				sql += " 'Aduan & Cadangan'\"AduanCadangan\",'Status Aduan'\"StatusAduan\", ";
				sql += "  'My Profile'\"ProfilPengguna\")) ";
							
				
				sql += " ORDER BY NAMA_NEGERI ";				
				
				setSQL(sql);
				
				myLogger.info("LAPORAN PENGGUNAAN ETAPP ONLINE :: "+sql);
				System.out.println("LAPORAN PENGGUNAAN ETAPP ONLINE :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("dari_S8", rs.getString("S8")==null?"":rs.getString("S8"));  
		    	h.put("dari_S17", rs.getString("S17")==null?"":rs.getString("S17")); 
		    	h.put("dari_StatusPermohonan", rs.getString("StatusPermohonan")==null?"":rs.getString("StatusPermohonan")); 
		    	h.put("dari_Bantahan", rs.getString("Bantahan")==null?"":rs.getString("Bantahan")); 
		    	h.put("dari_Penyewaan", rs.getString("Penyewaan")==null?"":rs.getString("Penyewaan"));
		    	h.put("dari_APB", rs.getString("APB")==null?"":rs.getString("APB"));
		    	h.put("dari_PembayaranOnline", rs.getString("PembayaranOnline")==null?"":rs.getString("PembayaranOnline"));
		    	h.put("dari_PanduanPengguna", rs.getString("PanduanPengguna")==null?"":rs.getString("PanduanPengguna"));
		    	h.put("dari_AduanCadangan", rs.getString("AduanCadangan")==null?"":rs.getString("AduanCadangan"));
		    	h.put("dari_ProfilPengguna", rs.getString("ProfilPengguna")==null?"":rs.getString("ProfilPengguna"));

		    	
		    	getStatusPenggunaanOnlineUpdated.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusPenggunaanOnlineUpdated;					
}		

	
	public Vector getListStatusBantahanMahkamahAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  ABBREV, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, BANTAHAN ))),'0') BANTAHAN_PB, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, BANTAHAN ))),'0') BANTAHAN_AP ";					
				sql += " FROM (  ";					
				sql += " SELECT ABBREV, REPLACE(REPLACE(BANTAHAN,'A'),'B') BANTAHAN  , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN ";		
				sql += " FROM (  ";
				sql += " SELECT N.ABBREV, 'A' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN  ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";		
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";				
				sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N ";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";	
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";					
				sql += " AND A.ID_NEGERI = N.ID_NEGERI ";									
				sql += " GROUP BY N.ABBREV ";
				sql += " UNION  ";				
				sql += " SELECT N.ABBREV, 'B' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,  ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN E,TBLRUJNEGERI N ";		
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";				
				sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK AND A.ID_NEGERI = N.ID_NEGERI ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}		
				
				sql += " GROUP BY N.ABBREV)  ) ";
				sql += " GROUP BY ABBREV ";				
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS BANTAHAN APX :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("ABBREV")));
		    	h.put("jumlah_permohonan_bantahanpb", rs.getString("BANTAHAN_PB")==null?"":rs.getString("BANTAHAN_PB"));  
		    	h.put("jumlah_permohonan_bantahanap", rs.getString("BANTAHAN_AP")==null?"":rs.getString("BANTAHAN_AP")); 
		    	
		    	getStatusBantahan.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusBantahan;					
}		
	
	
	
	// GENERATE BAR/PIE CHART
	
	public  String generateXML(String nama_laporan){
		
		System.out.println(getSQL());
		String xml="<chart caption='"+nama_laporan+"' subcaption='' xAxisName='Negeri' yAxisName='Jumlah' numberPrefix='' showLegend='1'>";
		Db db = null;
		//String xname = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"'  value='"+rs.getString("y")+"' />";				
			}
			xml =xml+"</chart>";
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}

		System.out.println(xml);
		return xml;
	}	
	
	public String generateXMLDaerah_add(String nama_laporan) {
		String xml="<chart caption='"+nama_laporan+"' subcaption='' xAxisName='Daerah' yAxisName='Jumlah Permohonan' numberPrefix='' showLegend='1'>";
		Db db = null;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){
				xml =xml+"<set label='"+rs.getString("NAMA_DAERAH")+"' value='"+ rs.getDouble("y")+"' />";
			}
			xml =xml+"</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}

		return xml;
	}

	
public  String generateXMLine(String nama_laporan){
		
		System.out.println(getSQL());
		String xml="<chart caption='"+nama_laporan+"' subcaption='' xAxisName='Negeri' yAxisName='Jumlah' numberPrefix='' showLegend='1'>";
		Db db = null;
		//String xname = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' color='3813F0' value='"+rs.getString("y")+"' />";				
			}
			xml =xml+"</chart>";
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}

		System.out.println(xml);
		return xml;
	}	

	public  String generateXMLCawanganLot(String nama_laporan){
		
		System.out.println(getSQL());
		String xml="<chart caption='"+nama_laporan+"' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan' numberPrefix='' showLegend='1'>";
		Db db = null;
		//String xname = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("z")+"' />";				
			}
			xml =xml+"</chart>";
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}

		System.out.println(xml);
		return xml;
	}	

	
	//add by faresh

	
public  String generateXMLBorangKNegeri(String nama_laporan){
		
	System.out.println(getSQL());
		//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
		//"useRoundEdges='1' legendBorderAlpha='0'>";
		
		String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
		Db db = null;
		String xname = "";
		String categories = "";
		String dataset1="" ;
		String dataset2="" ;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			categories = "<categories>";
			dataset1 = "<dataset seriesName='Telah Diendos' color='3813F0' showValues='1'>";
			dataset2 = "<dataset seriesName='Belum Diendos' color='BA1435' showValues='1'>";
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			
				categories = categories+"<category label='"+rs.getString("nama_negeri")+"'/>";
				dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("nama_negeri"))+"' value='"+rs.getString("total_hakmilik_endos")+"'/>";
				dataset2 = dataset2+"<set value='"+rs.getString("baki")+"'/>";
			}
			categories = categories+"</categories>";
			dataset1 = dataset1+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			xml =xml+categories+dataset1+dataset2+"</chart>";
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
        
		System.out.println(xml);
		return xml;
	}

public  String generateXMLBorangKDaerah(String nama_laporan){
	
	System.out.println(getSQL());
		//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
		//"useRoundEdges='1' legendBorderAlpha='0'>";
		
		String xml="<chart palette='2'  xAxisName='Daerah' caption='"+nama_laporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
		Db db = null;
		String xname = "";
		String categories = "";
		String dataset1="" ;
		String dataset2="" ;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			categories = "<categories>";
			dataset1 = "<dataset seriesName='Telah Diendos' color='3813F0' showValues='1'>";
			dataset2 = "<dataset seriesName='Belum Diendos' color='BA1435' showValues='1'>";
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			
				categories = categories+"<category label='"+rs.getString("nama_daerah")+"'/>";
				dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("nama_daerah"))+"' value='"+rs.getString("total_hakmilik_endos")+"'/>";
				dataset2 = dataset2+"<set value='"+rs.getString("baki")+"'/>";
			}
			categories = categories+"</categories>";
			dataset1 = dataset1+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			xml =xml+categories+dataset1+dataset2+"</chart>";
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
        
		System.out.println(xml);
		return xml;
	}

public  String generateXMLPenarikanBalikNegeri(String nama_laporan){
	
	System.out.println(getSQL());
		//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
		//"useRoundEdges='1' legendBorderAlpha='0'>";
		
		String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
		Db db = null;
		String xname = "";
		String categories = "";
		String dataset1="" ;
		String dataset2="" ;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			categories = "<categories>";
			dataset1 = "<dataset seriesName='Jumlah Lot Belum Selesai' color='3813F0' showValues='1'>";
			dataset2 = "<dataset seriesName='Jumlah Lot Telah Selesai' color='BA1435' showValues='1'>";
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			
				categories = categories+"<category label='"+rs.getString("nama_negeri")+"'/>";
				dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("nama_negeri"))+"' value='"+rs.getString("total_hakmilik_xselesai")+"'/>";
				dataset2 = dataset2+"<set value='"+rs.getString("total_hakmilik_selesai")+"'/>";
			}
			categories = categories+"</categories>";
			dataset1 = dataset1+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			xml =xml+categories+dataset1+dataset2+"</chart>";
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
        
		System.out.println(xml);
		return xml;
	}

public  String generateXMLPenarikanBalikDaerah(String nama_laporan){
	
	System.out.println(getSQL());
		//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
		//"useRoundEdges='1' legendBorderAlpha='0'>";
		
		String xml="<chart palette='2'  xAxisName='Daerah' caption='"+nama_laporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
		Db db = null;
		String xname = "";
		String categories = "";
		String dataset1="" ;
		String dataset2="" ;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			categories = "<categories>";
			dataset1 = "<dataset seriesName='Jumlah Lot Belum Selesai' color='3813F0' showValues='1'>";
			dataset2 = "<dataset seriesName='Jumlah Lot Telah Selesai' color='BA1435' showValues='1'>";
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			
				categories = categories+"<category label='"+rs.getString("nama_daerah")+"'/>";
				dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("nama_daerah"))+"' value='"+rs.getString("total_hakmilik_xselesai")+"'/>";
				dataset2 = dataset2+"<set value='"+rs.getString("total_hakmilik_selesai")+"'/>";
			}
			categories = categories+"</categories>";
			dataset1 = dataset1+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			xml =xml+categories+dataset1+dataset2+"</chart>";
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
        
		System.out.println(xml);
		return xml;
	}

public  String generateXMLSistemWartaNegeri(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='4'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	String dataset3="" ;
	String dataset4="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Lewat Jadual' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Dalam Proses' color='BA1435' showValues='1'>";
		dataset3 = "<dataset seriesName='Ikut Jadial' color='999966' showValues='1'>";
		dataset4 = "<dataset seriesName='Awal Jadual' color='660066' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("nama_negeri")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("nama_negeri")+"' value='"+rs.getString("overdue")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("ongoing")+"'/>";
			dataset3 = dataset3+"<set value='"+rs.getString("ontime")+"'/>";
			dataset4 = dataset4+"<set value='"+rs.getString("ahead")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		dataset3 = dataset3+"</dataset>";
		dataset4 = dataset4+"</dataset>";
		xml =xml+categories+dataset1+dataset2+dataset3+dataset4+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
	}	

public  String generateXMLSistemWartaDaerah(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='4'  xAxisName='Daerah' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	String dataset3="" ;
	String dataset4="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Lewat Jadual' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Dalam Proses' color='BA1435' showValues='1'>";
		dataset3 = "<dataset seriesName='Ikut Jadial' color='999966' showValues='1'>";
		dataset4 = "<dataset seriesName='Awal Jadual' color='660066' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("nama_daerah")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("nama_daerah")+"' value='"+rs.getString("overdue")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("ongoing")+"'/>";
			dataset3 = dataset3+"<set value='"+rs.getString("ontime")+"'/>";
			dataset4 = dataset4+"<set value='"+rs.getString("ahead")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		dataset3 = dataset3+"</dataset>";
		dataset4 = dataset4+"</dataset>";
		xml =xml+categories+dataset1+dataset2+dataset3+dataset4+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
	}

public  String generateXMLSistemWartaKementerian(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='4'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	String dataset3="" ;
	String dataset4="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Lewat Jadual' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Dalam Proses' color='BA1435' showValues='1'>";
		dataset3 = "<dataset seriesName='Ikut Jadial' color='999966' showValues='1'>";
		dataset4 = "<dataset seriesName='Awal Jadual' color='660066' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("nama_kementerian")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("nama_kementerian")+"' value='"+rs.getString("overdue")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("ongoing")+"'/>";
			dataset3 = dataset3+"<set value='"+rs.getString("ontime")+"'/>";
			dataset4 = dataset4+"<set value='"+rs.getString("ahead")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		dataset3 = dataset3+"</dataset>";
		dataset4 = dataset4+"</dataset>";
		xml =xml+categories+dataset1+dataset2+dataset3+dataset4+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
	}	

public  String generateXMLPenarikanBalikKementerian(String nama_laporan){
	
	System.out.println(getSQL());
		//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
		//"useRoundEdges='1' legendBorderAlpha='0'>";
		
		String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
		Db db = null;
		String xname = "";
		String categories = "";
		String dataset1="" ;
		String dataset2="" ;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			categories = "<categories>";
			dataset1 = "<dataset seriesName='Jumlah Lot Belum Selesai' color='3813F0' showValues='1'>";
			dataset2 = "<dataset seriesName='Jumlah Lot Telah Selesai' color='BA1435' showValues='1'>";
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			
				categories = categories+"<category label='"+rs.getString("nama_kementerian")+"'/>";
				dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("nama_kementerian"))+"' value='"+rs.getString("total_hakmilik_xselesai")+"'/>";
				dataset2 = dataset2+"<set value='"+rs.getString("total_hakmilik_selesai")+"'/>";
			}
			categories = categories+"</categories>";
			dataset1 = dataset1+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			xml =xml+categories+dataset1+dataset2+"</chart>";
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
        
		System.out.println(xml);
		return xml;
	}	



public  String generateXMLfaresh(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Jumlah Permohonan' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Jumlah Hakmilik' color='BA1435' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("x")+"'/>";
			dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("z")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	

public  String generateXMLBorangKKementerian(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Telah Diendos' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Belum Diendos' color='BA1435' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("nama_kementerian")+"'/>";
			dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("nama_kementerian"))+"' value='"+rs.getString("total_hakmilik_endos")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("baki")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	

public  String generateXMLfaresh2(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
				"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
				"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	     
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Jumlah Permohonan' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Jumlah Hakmilik' color='BA1435' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("x")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("x")+"' value='"+rs.getString("y")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("z")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	

public  String generateXMLPenarikanBalikLineChartNegeri(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
				"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
				"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	     
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Jumlah Lot Belum Selesai' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Jumlah Lot Telah Selesai' color='BA1435' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("nama_negeri")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("nama_negeri")+"' value='"+rs.getString("total_hakmilik_xselesai")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("total_hakmilik_selesai")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	


public  String generateXMLPenarikanBalikLineChartKementerian(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
				"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
				"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	     
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Jumlah Lot Belum Selesai' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Jumlah Lot Telah Selesai' color='BA1435' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("nama_kementerian")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("nama_kementerian")+"' value='"+rs.getString("total_hakmilik_xselesai")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("total_hakmilik_selesai")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	



public  String generateXMLlineNegeriBorangK(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
				"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
				"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	     
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Telah Diendos' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Belum Diendos' color='BA1435' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("nama_negeri")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("nama_negeri")+"' value='"+rs.getString("total_hakmilik_endos")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("baki")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}

public  String generateXMLlineNegeriSistemWarta(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='3'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
				"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
				"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	     
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	String dataset3="" ;
	String dataset4="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		System.out.println("--------------" + getSQL());
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Lewat Jadual' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Dalam Proses' color='BA1435' showValues='1'>";
		dataset3 = "<dataset seriesName='Ikut Jadial' color='999966' showValues='1'>";
		dataset4 = "<dataset seriesName='Awal Jadual' color='660066' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			System.out.println("------------" + rs.getString("nama_negeri"));
			categories = categories+"<category label='"+rs.getString("nama_negeri")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("nama_negeri")+"' value='"+rs.getString("overdue")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("ongoing")+"'/>";
			dataset3 = dataset3+"<set value='"+rs.getString("ontime")+"'/>";
			dataset4 = dataset4+"<set value='"+rs.getString("ahead")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		dataset3 = dataset3+"</dataset>";
		dataset4 = dataset4+"</dataset>";
		xml =xml+categories+dataset1+dataset2+dataset3+dataset4+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	

public  String generateXMLlineKementerianSistemWarta(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='3'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
				"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
				"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	     
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	String dataset3="" ;
	String dataset4="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		System.out.println("--------------" + getSQL());
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Lewat Jadual' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Dalam Proses' color='BA1435' showValues='1'>";
		dataset3 = "<dataset seriesName='Ikut Jadial' color='999966' showValues='1'>";
		dataset4 = "<dataset seriesName='Awal Jadual' color='660066' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			System.out.println("------------" + rs.getString("nama_kementerian"));
			categories = categories+"<category label='"+rs.getString("nama_negeri")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("nama_kementerian")+"' value='"+rs.getString("overdue")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("ongoing")+"'/>";
			dataset3 = dataset3+"<set value='"+rs.getString("ontime")+"'/>";
			dataset4 = dataset4+"<set value='"+rs.getString("ahead")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		dataset3 = dataset3+"</dataset>";
		dataset4 = dataset4+"</dataset>";
		xml =xml+categories+dataset1+dataset2+dataset3+dataset4+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	


public  String generateXMLlineKementerianBorangK(String nama_laporan){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
				"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
				"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	     
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Telah Diendos' color='3813F0' showValues='1'>";
		dataset2 = "<dataset seriesName='Belum Diendos' color='BA1435' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("nama_kementerian")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("nama_kementerian")+"' value='"+rs.getString("total_hakmilik_endos")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("baki")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	

public  String generateXMLfaresh3(){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart bgColor='E9E9E9' outCnvBaseFontColor='666666'  xAxisName='Negeri' caption='Status Jumlah Permohonan dan Jumlah Lot' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"plotFillAlpha='50' numVDivLines='10' showAlternateVGridColor='1' AlternateVGridColor='e1f5ff'  " +
				"divLineColor='e1f5ff' vdivLineColor='e1f5ff' baseFontColor='666666' canvasBorderThickness='1'  " +
				"showPlotBorder='1' plotBorderThickness='0' formatNumberScale='0' >";
	  
	
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Jumlah Permohonan' color='C8A1D1'  plotBorderColor='C8A1D1' showValues='1'>";
		dataset2 = "<dataset seriesName='Jumlah Hakmilik' color='B1D1DC' plotBorderColor='B1D1DC' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("x")+"'/>";
			dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("z")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	

public  String generateXMLJumlahHakmilik(String nama_laporan){
	
	System.out.println(getSQL());
	String xml="<chart caption='"+nama_laporan+"' subcaption='' xAxisName='Negeri' yAxisName='Jumlah ' numberPrefix=''>";
	Db db = null;
	//String xname = "";
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("z")+"' />";				
		}
		xml =xml+"</chart>";
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}

	System.out.println(xml);
	return xml;
}	
	//end add


	public String generateXML_MSColumn2D(String id_tahun,String id_bulan,String nama_laporan) {
	
		String xml="<chart palette='2' caption='"+nama_laporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0'>";
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			sql =  " SELECT N.ABBREV,NVL(COUNT(DISTINCT P.ID_PERMOHONAN),'0') AS SELESAI ";
			sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H, ";
			sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";
			sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";
			sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";
			sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";
			sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";
			sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
			sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";
			sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
			
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";			

			myLogger.info("SQL STATUS PENGAMBILAN(SELESAI) :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Selesai' color='3813F0' showValues='1'>";
			while(rs.next()){
				//xml =xml+"<set label='"+rs.getString("x")+"' value='"+rs.getString("y")+"' />";
				categories = categories+"<category label='"+rs.getString("abbrev")+"'/>";
				dataset = dataset+"<set value='"+rs.getString("SELESAI")+"'/>";
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			
			//TAK SELESAI
			
			sql  = " SELECT N.ABBREV,NVL(COUNT(DISTINCT P.ID_PERMOHONAN),'0') AS TAKSELESAI ";
			sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H, ";
			sql += " tblrujnegeri N ";
			sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";
			sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
			sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";
			sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";

    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";
			
			myLogger.info("SQL STATUS PENGAMBILAN(TAKSELESAI) :: "+sql);
			
			rs =stat.executeQuery(sql);
			dataset2 = "<dataset seriesName='Tidak Selesai' color='BA1435' showValues='1'>";
			while(rs.next()){
				dataset2 = dataset2+"<set value='"+rs.getString("TAKSELESAI")+"'/>";
			}	
			dataset2 = dataset2+"</dataset>";
			
			
			xml =xml+categories+dataset+dataset2+"</chart>";
			
			System.out.println(xml);
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}
	
	public String generateXML_Status_Pengambilan(String id_tahun,String id_bulan,String nama_laporan) {
		
			
		String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
		"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
		"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
		"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
		"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
		"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
		
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			sql =  " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";
			sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H, ";
			sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";
			sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";
			sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";
			sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";
			sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";
			sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
			sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";
			sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
			
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";			

			myLogger.info("SQL STATUS PENGAMBILAN(SELESAI) :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Selesai' color='3813F0' showValues='1'>";
			while(rs.next()){
				//xml =xml+"<set label='"+rs.getString("x")+"' value='"+rs.getString("y")+"' />";
				categories = categories+"<category label='"+rs.getString("abbrev")+"'/>";
				dataset = dataset+"<set value='"+rs.getString("SELESAI")+"'/>";
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			
			//TAK SELESAI
			
			sql  = " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS TAKSELESAI ";
			sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H, ";
			sql += " tblrujnegeri N ";
			sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";
			sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
			sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";
			sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";

    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";
			
			myLogger.info("SQL STATUS PENGAMBILAN(TAKSELESAI) :: "+sql);
			
			rs =stat.executeQuery(sql);
			dataset2 = "<dataset seriesName='Tidak Selesai' color='BA1435' showValues='1'>";
			while(rs.next()){
				dataset2 = dataset2+"<set value='"+rs.getString("TAKSELESAI")+"'/>";
			}	
			dataset2 = dataset2+"</dataset>";
			
			
			xml =xml+categories+dataset+dataset2+"</chart>";
			
			System.out.println(xml);
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}

	
	public String generateXML_MSColumn2D_Bantahan(String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir, String nama_laporan) {
		
		String xml="<chart palette='2' caption='"+nama_laporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0' showLegend='1'>";
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			
			//BANTAHAN 
			sql =  " SELECT NAMA_NEGERI, ";
			sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, BANTAHAN ))),'0') BANTAHAN_PB, ";
			sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, BANTAHAN ))),'0') BANTAHAN_AP  ";
			sql += " FROM (  ";
			sql += " SELECT NAMA_NEGERI, REPLACE(REPLACE(BANTAHAN,'A'),'B') BANTAHAN  , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN ";
			sql += " FROM (  ";
			sql += " SELECT N.NAMA_NEGERI, 'A' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
			sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
			sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";
			sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N  ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
			sql += " AND A.ID_NEGERI = N.ID_NEGERI ";
			sql += " AND N.ID_NEGERI NOT IN (1)";
			
			// TAHUN 
						if (id_tahun != null) {
							if (!id_tahun.trim().equals("")) {
								sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
							}
						}
						
			    		// BULAN
						if (id_bulan != null) {
							if (!id_bulan.trim().equals("")) {
								sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
							}
						}
						
						if (txdTarikhMula != null && txdTarikhAkhir != null) {
							if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
								sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
							}
						}
			
			sql += " GROUP BY N.NAMA_NEGERI ";
			sql += " UNION ";
			sql += " SELECT N.NAMA_NEGERI, 'B' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
			sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,  ";			
			sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN E,TBLRUJNEGERI N  ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK AND A.ID_NEGERI = N.ID_NEGERI ";
			sql += " AND N.ID_NEGERI NOT IN (1)";
			
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
				}
			}
			
			sql += " GROUP BY  N.NAMA_NEGERI)  ) ";
			sql += " GROUP BY  NAMA_NEGERI ";
			sql += " ORDER BY 1 ";			

			myLogger.info("SQL PRESTASI BANTAHANX :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Bantahan Pihak Berkepentingan' color='3813F0' showValues='1'>";
			dataset2 = "<dataset seriesName='Bantahan Agensi Pemohon' color='BA1435' showValues='1'>";
			
			while(rs.next()){
				
				//categories = categories+"<category label='"+Utils.NiceStateName(rs.getString("abbrev"))+"'/>";
				dataset = dataset+"<set value='"+Utils.isNull(rs.getString("BANTAHAN_PB"))+"'/>";
				dataset2 = dataset2+"<set value='"+Utils.isNull(rs.getString("BANTAHAN_AP"))+"'/>";
				categories = categories+"<category label='"+rs.getString("NAMA_NEGERI")+"'/>";
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			dataset2 = dataset2+"</dataset>";

			xml = xml+categories+dataset+dataset2+"</chart>";
			
			myLogger.info(xml);
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}
	
public String generateXML_MSColumn2D_Bantahan_add(String idNegeri,String id_tahun,String id_bulan,String txdTarikhMula,String txdTarikhAkhir,String nama_laporan) {
		
		String xml="<chart palette='2' caption='"+nama_laporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0' showLegend='1'>";
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			
			//BANTAHAN 
			sql =  " SELECT NAMA_DAERAH , ";
			sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, BANTAHAN ))),'0') BANTAHAN_PB, ";
			sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, BANTAHAN ))),'0') BANTAHAN_AP  ";
			sql += " FROM (  ";
			sql += " SELECT NAMA_DAERAH, REPLACE(REPLACE(BANTAHAN,'A'),'B') BANTAHAN  , ROW_NUMBER() OVER ( PARTITION BY NAMA_DAERAH ORDER BY ROWNUM) RN ";
			sql += " FROM (  ";
			sql += " SELECT F.NAMA_DAERAH, 'A' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
			sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
			sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";
			sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N,  ";
			sql += " TBLRUJDAERAH F";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
			sql += " AND A.ID_NEGERI = N.ID_NEGERI AND F.ID_DAERAH(+) = C.ID_DAERAH ";
			sql += " AND N.ID_NEGERI = "+idNegeri;
			
			// TAHUN 
						if (id_tahun != null) {
							if (!id_tahun.trim().equals("")) {
								sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
							}
						}
						
			    		// BULAN
						if (id_bulan != null) {
							if (!id_bulan.trim().equals("")) {
								sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
							}
						}
												
						if (txdTarikhMula != null && txdTarikhAkhir != null) {
							if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
								sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
							}
						}
			
			sql += " GROUP BY F.NAMA_DAERAH ";
			sql += " UNION ";
			sql += " SELECT F.NAMA_DAERAH, 'B' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
			sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,  ";			
			sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN E, ";
			sql += " TBLRUJNEGERI N, TBLRUJDAERAH F ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK AND A.ID_NEGERI = N.ID_NEGERI ";
			sql += " AND F.ID_DAERAH(+) = C.ID_DAERAH AND N.ID_NEGERI = "+idNegeri;
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
				}
			}
			
			sql += " GROUP BY F.NAMA_DAERAH)  ) ";
			sql += " GROUP BY NAMA_DAERAH ";
			sql += " ORDER BY 1 ";			

			myLogger.info("SQL PRESTASI BANTAHANXA :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Bantahan Pihak Berkepentingan' color='3813F0' showValues='1'>";
			dataset2 = "<dataset seriesName='Bantahan Agensi Pemohon' color='BA1435' showValues='1'>";
			
			while(rs.next()){
				
				categories = categories+"<category label='"+Utils.NiceStateName(rs.getString("nama_daerah"))+"'/>";
				dataset = dataset+"<set value='"+Utils.isNull(rs.getString("BANTAHAN_PB"))+"'/>";
				dataset2 = dataset2+"<set value='"+Utils.isNull(rs.getString("BANTAHAN_AP"))+"'/>";
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			dataset2 = dataset2+"</dataset>";

			xml = xml+categories+dataset+dataset2+"</chart>";
			
			System.out.println(xml);
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}


	public String generateXML_MSColumn2D_Penggunaan(String id_tahun,String id_bulan,String nama_laporan) {
		
		String xml="<chart palette='2' caption='"+nama_laporan+"' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0'>";
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		String dataset3 = "";
		String dataset4 = "";
		String dataset5 = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			
			//BANTAHAN 
			sql =  " SELECT  ABBREV, ";					
			sql += " NVL (MAX(INITCAP(DECODE ( RN , 1, BIL ))),0) SPT,   ";
			sql += " NVL (MAX(INITCAP(DECODE ( RN , 2, BIL ))),0) SPPK,  ";
			sql += " NVL (MAX(INITCAP(DECODE ( RN , 3, BIL ))),0) SHTP,  ";	
			sql += " NVL (MAX(INITCAP(DECODE ( RN , 4, BIL ))),0) SPHP  ";	
    
			sql += " FROM (  ";					
			sql += " SELECT ABBREV, BIL , ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN  ";	
			sql += " FROM (  ";
			sql += " SELECT E.ABBREV, COUNT(E.ID_NEGERI) AS BIL ";
			sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
			sql += " WHERE A.USER_NAME = B.USER_LOGIN ";
			sql += " AND B.USER_ID = C.USER_ID ";
			sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";
			sql += " AND C.ID_NEGERI = E.ID_NEGERI "; 
			sql += " AND D.ID_SEKSYEN=1 ";
			
			// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}
			sql += " GROUP BY E.ABBREV ";
			sql += " UNION  ";
			sql += " SELECT E.ABBREV, COUNT(E.ID_NEGERI) AS BIL ";
			sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
			sql += " WHERE A.USER_NAME = B.USER_LOGIN ";
			sql += " AND B.USER_ID = C.USER_ID ";
			sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";
			sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
			sql += " AND D.ID_SEKSYEN=2 ";	
			
			// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY E.ABBREV ";	
			sql += " UNION  ";	
			sql += " SELECT E.ABBREV, COUNT(E.ID_NEGERI) AS BIL ";	
			sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";	
			sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
			sql += " AND B.USER_ID = C.USER_ID "; 	
			sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
			sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
			sql += " AND D.ID_SEKSYEN=3 ";
			
			// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY E.ABBREV ";	
			sql += " UNION  ";	
			sql += " SELECT E.ABBREV, COUNT(E.ID_NEGERI) AS BIL ";	
			sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
			sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
			sql += " AND B.USER_ID = C.USER_ID ";	
			sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
			sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
			sql += " AND D.ID_SEKSYEN=4 ";	
			
			// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}
			
	
			
			sql += " GROUP BY e.ABBREV)  ) ";
			sql += " GROUP BY ABBREV ";
			sql += " ORDER BY 1 ";			

			myLogger.info("SQL PENGGUNAAN ETAPP :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='SPT' color='AFD8F8' showValues='0'>";
			dataset2 = "<dataset seriesName='SPK' color='F6BD0F' showValues='0'>";
			dataset3 = "<dataset seriesName='SHTP' color='99CC33' showValues='0'>";
			dataset4 = "<dataset seriesName='SPHP' color='FF33CC' showValues='0'>";
			
			
			while(rs.next()){
				
				categories = categories+"<category label='"+Utils.NiceStateName(rs.getString("abbrev"))+"'/>";
				dataset = dataset+"<set value='"+Utils.isNull(rs.getString("SPT"))+"'/>";
				dataset2 = dataset2+"<set value='"+Utils.isNull(rs.getString("SPPK"))+"'/>";
				dataset3 = dataset3+"<set value='"+Utils.isNull(rs.getString("SHTP"))+"'/>";
				dataset4 = dataset4+"<set value='"+Utils.isNull(rs.getString("SPHP"))+"'/>";
				
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			dataset3 = dataset3+"</dataset>";
			dataset4 = dataset4+"</dataset>";
			
			

			xml = xml+categories+dataset+dataset2+dataset3+dataset4+"</chart>";
			
			System.out.println(xml);
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}

public String generateXML_MSColumn2D_PenggunaanOnlineUpdated(String id_tahun,String id_bulan, String nama_laporan) {
		
		String xml="<chart palette='2' caption='"+nama_laporan+"'" +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0'>";
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		String dataset3 = "";
		String dataset4 = "";
		String dataset5 = "";
		String dataset6 = "";
		String dataset7 = "";
		String dataset8 = "";
		String dataset9 = "";
		String dataset10 = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			
			sql =  " SELECT * FROM ( ";					
			sql += " SELECT D.ABBREV, A.MODULE_NAME ";
			sql += " FROM USER_TRACKER A , USERS B, USERS_ONLINE C, TBLRUJNEGERI D  ";
			sql += " WHERE  A.USER_LOGIN = B.USER_LOGIN  ";	
			sql += " AND B.USER_ID = C.USER_ID    ";	    
			sql += " AND C.ID_NEGERI= D.ID_NEGERI  ";
			// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}

			sql += " AND B.USER_TYPE='online')  ";	
			sql += " PIVOT (  ";
			sql += " COUNT(MODULE_NAME) ";
			sql += " FOR MODULE_NAME IN ( ";
			sql += " 'Permohonan Pusaka Kecil'\"S8\", ";
			sql += " 'Kemaskini Seksyen 17'\"S17\", ";
			sql += " 'Status Permohonan'\"StatusPermohonan\", ";
			sql += " 'Bantahan Mahkamah - Pihak Berkepentingan'\"Bantahan\", ";
			sql += " 'PYW Online Senarai Fail'\"Penyewaan\", ";
			sql += " 'FrmAPBOnlineSenaraiFailView'\"APB\", ";
			sql += " 'Bayaran Online'\"PembayaranOnline\",'Manual Pengguna'\"PanduanPengguna\", ";
			sql += " 'Aduan & Cadangan'\"AduanCadangan\",'Status Aduan'\"StatusAduan\", ";
			sql += "  'My Profile'\"ProfilPengguna\")) ";
						
			
			sql += " ORDER BY ABBREV ";	

			myLogger.info("SQL PENGGUNAAN ETAPP :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='PPK-Sekyen 8' color='AFD8F8' showValues='1'>";
			dataset2 = "<dataset seriesName='PPK-Seksyen 17' color='F6BD0F' showValues='1'>";
			dataset3 = "<dataset seriesName='PPK-Status Permohonan' color='99CC33' showValues='1'>";
			dataset4 = "<dataset seriesName='PPK-Bantahan Mahkamah' color='FF33CC' showValues='1'>";
			dataset5 = "<dataset seriesName='PHP-Penyewaan' color='0000FF' showValues='1'>";
			dataset6 = "<dataset seriesName='PHP-APB' color='FF0000' showValues='1'>";
			dataset7 = "<dataset seriesName='Pembayaran Online' color='C0C0C0' showValues='1'>";
			dataset8 = "<dataset seriesName='Panduan Pengguna' color='000033' showValues='1'>";
			dataset9 = "<dataset seriesName='Aduan dan Cadangan' color='006633' showValues='1'>";
			dataset10 = "<dataset seriesName='ProfilPengguna' color='FF6600' showValues='1'>";
			
			while(rs.next()){
				
				categories = categories+"<category label='"+Utils.NiceStateName(rs.getString("abbrev"))+"'/>";
				dataset = dataset+"<set value='"+Utils.isNull(rs.getString("S8"))+"'/>";
				dataset2 = dataset2+"<set value='"+Utils.isNull(rs.getString("S17"))+"'/>";
				dataset3 = dataset3+"<set value='"+Utils.isNull(rs.getString("StatusPermohonan"))+"'/>";
				dataset4 = dataset4+"<set value='"+Utils.isNull(rs.getString("Bantahan"))+"'/>";
				dataset5 = dataset5+"<set value='"+Utils.isNull(rs.getString("Penyewaan"))+"'/>";
				dataset6 = dataset6+"<set value='"+Utils.isNull(rs.getString("APB"))+"'/>";
				dataset7 = dataset7+"<set value='"+Utils.isNull(rs.getString("PembayaranOnline"))+"'/>";
				dataset8 = dataset8+"<set value='"+Utils.isNull(rs.getString("PanduanPengguna"))+"'/>";
				dataset9 = dataset9+"<set value='"+Utils.isNull(rs.getString("AduanCadangan"))+"'/>";
				dataset10 = dataset10+"<set value='"+Utils.isNull(rs.getString("ProfilPengguna"))+"'/>";
				
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			dataset3 = dataset3+"</dataset>";
			dataset4 = dataset4+"</dataset>";
			dataset5 = dataset5+"</dataset>";
			dataset6 = dataset6+"</dataset>";
			dataset7 = dataset7+"</dataset>";
			dataset8 = dataset8+"</dataset>";
			dataset9 = dataset9+"</dataset>";
			dataset10 = dataset10+"</dataset>";
			

			xml = xml+categories+dataset+dataset2+dataset3+dataset4+dataset5+dataset6+dataset7+dataset8+dataset9+dataset10+"</chart>";
			
			System.out.println(xml);
			
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}

public String generateXML_MSColumn2D_PenggunaanOnline(String id_tahun,String id_bulan, String nama_laporan) {
	
	String xml="<chart palette='2' caption='"+nama_laporan+"'" +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"useRoundEdges='1' legendBorderAlpha='0'>";
	Db db = null;
	String sql = "";
	String categories = "";
	String dataset = "";
	String dataset2 = "";
	String dataset3 = "";
	String dataset4 = "";
	String dataset5 = "";
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		
		sql =  " SELECT  ABBREV, ";					
		sql += " NVL (MAX(INITCAP(DECODE ( RN , 1, BIL ))),0) AduanCadangan, ";
		sql += " NVL (MAX(INITCAP(DECODE ( RN , 2, BIL ))),0) StatusAduan,  ";
		sql += " NVL (MAX(INITCAP(DECODE ( RN , 3, BIL ))),0) MaklumatPemohon,  ";	
		sql += " NVL (MAX(INITCAP(DECODE ( RN , 4, BIL ))),0) StatusPermohonan    ";	    
		sql += " FROM (  ";					
		sql += " select ABBREV ,BIL, ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN  ";	
		sql += " FROM (  ";
		sql += " SELECT  E.ABBREV, A.MODULE_NAME, COUNT(*) BIL ";
		sql += " FROM USER_TRACKER A, USERS B , USERS_ONLINE C,  TBLRUJNEGERI E ";
		sql += " WHERE A.USER_LOGIN = B.USER_LOGIN ";
		sql += " AND B.USER_ID = C.USER_ID ";
		sql += " AND C.ID_NEGERI = E.ID_NEGERI  ";
		sql += " AND A.MODULE_NAME=   'Aduan & Cadangan' "; 
						
		// TAHUN 
		if (id_tahun != null) {
			if (!id_tahun.trim().equals("")) {
				sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
			}
		}
		
		// BULAN
		if (id_bulan != null) {
			if (!id_bulan.trim().equals("")) {
				sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
			}
		}
		
		sql += " GROUP BY E.ABBREV,A.MODULE_NAME ";
		sql += " UNION  ";
		sql += " SELECT  E.ABBREV, A.MODULE_NAME, COUNT(*) BIL ";
		sql += " FROM USER_TRACKER A, USERS B , USERS_ONLINE C,  TBLRUJNEGERI E ";
		sql += " WHERE A.USER_LOGIN = B.USER_LOGIN ";
		sql += " AND B.USER_ID = C.USER_ID ";
		sql += " AND C.ID_NEGERI = E.ID_NEGERI  ";
		sql += " AND A.MODULE_NAME=   'Status Aduan' "; 
						
		// TAHUN 
		if (id_tahun != null) {
			if (!id_tahun.trim().equals("")) {
				sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
			}
		}
		
		// BULAN
		if (id_bulan != null) {
			if (!id_bulan.trim().equals("")) {
				sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
			}
		}
		
		sql += " GROUP BY E.ABBREV,A.MODULE_NAME ";
		sql += " UNION  ";	
		sql += " SELECT  E.ABBREV, A.MODULE_NAME, COUNT(*) BIL ";
		sql += " FROM USER_TRACKER A, USERS B , USERS_ONLINE C,  TBLRUJNEGERI E ";
		sql += " WHERE A.USER_LOGIN = B.USER_LOGIN ";
		sql += " AND B.USER_ID = C.USER_ID ";
		sql += " AND C.ID_NEGERI = E.ID_NEGERI  ";
		sql += " AND A.MODULE_NAME=   'Maklumat Pemohon' "; 
						
		// TAHUN 
		if (id_tahun != null) {
			if (!id_tahun.trim().equals("")) {
				sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
			}
		}
		
		// BULAN
		if (id_bulan != null) {
			if (!id_bulan.trim().equals("")) {
				sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
			}
		}
		
		sql += " GROUP BY E.ABBREV,A.MODULE_NAME ";
		sql += " UNION  ";
		sql += " SELECT  E.ABBREV, A.MODULE_NAME, COUNT(*) BIL ";
		sql += " FROM USER_TRACKER A, USERS B , USERS_ONLINE C,  TBLRUJNEGERI E ";
		sql += " WHERE A.USER_LOGIN = B.USER_LOGIN ";
		sql += " AND B.USER_ID = C.USER_ID ";
		sql += " AND C.ID_NEGERI = E.ID_NEGERI  ";
		sql += " AND A.MODULE_NAME=   'Status Permohonan' "; 
						
		// TAHUN 
		if (id_tahun != null) {
			if (!id_tahun.trim().equals("")) {
				sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
			}
		}
		
		// BULAN
		if (id_bulan != null) {
			if (!id_bulan.trim().equals("")) {
				sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
			}
		}
		
		sql += " GROUP BY E.ABBREV,A.MODULE_NAME ) )";
		sql += " GROUP BY ABBREV ";		

		myLogger.info("SQL PENGGUNAAN ETAPP :: "+sql);
		
		ResultSet rs =stat.executeQuery(sql);
		categories = "<categories>";
		dataset = "<dataset seriesName='Aduan/Cadangan' color='AFD8F8' showValues='1'>";
		dataset2 = "<dataset seriesName='Status Aduan' color='F6BD0F' showValues='1'>";
		dataset3 = "<dataset seriesName='Maklumat Pemohon' color='99CC33' showValues='1'>";
		dataset4 = "<dataset seriesName='Status Permohonan' color='FF33CC' showValues='1'>";
		
		
		while(rs.next()){
			
			categories = categories+"<category label='"+Utils.NiceStateName(rs.getString("abbrev"))+"'/>";
			dataset = dataset+"<set value='"+Utils.isNull(rs.getString("AduanCadangan"))+"'/>";
			dataset2 = dataset2+"<set value='"+Utils.isNull(rs.getString("StatusAduan"))+"'/>";
			dataset3 = dataset3+"<set value='"+Utils.isNull(rs.getString("MaklumatPemohon"))+"'/>";
			dataset4 = dataset4+"<set value='"+Utils.isNull(rs.getString("StatusPermohonan"))+"'/>";
			
		}
		categories = categories+"</categories>";
		dataset = dataset+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		dataset3 = dataset3+"</dataset>";
		dataset4 = dataset4+"</dataset>";
		

		xml = xml+categories+dataset+dataset2+dataset3+dataset4+"</chart>";
		
		System.out.println(xml);
		
	} catch (Exception er) {
		myLogger.error(er);
	} finally {
		if (db!=null) db.close();
	}
	
	
	return xml;
}

	
	public Vector getListRumusan()throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatus = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT   nama_negeri, NVL ( MAX (INITCAP (DECODE (rn, 1, kutipan_data))), 0 ) kutipan_data, ";					
				sql += " NVL ( MAX (INITCAP (DECODE (rn, 2, kutipan_data))), 0 ) permohonan_baru ";
				sql += " FROM (SELECT nama_negeri, kutipan_data, ";					
				sql += " ROW_NUMBER () OVER (PARTITION BY nama_negeri ORDER BY ROWNUM)rn ";					
				sql += " FROM (SELECT   c.nama_negeri,COUNT (DISTINCT NVL(b.id_permohonan,0)) AS kutipan_data ";		
				sql += " FROM tblpfdfail a,tblpptpermohonan b,tblrujnegeri c,tblrujstatus s,users u,users_internal ui ";
				sql += " WHERE a.id_fail = b.id_fail ";					
				sql += " AND a.id_negeri = c.id_negeri(+) ";		
				sql += " AND b.id_masuk = u.user_id(+) ";				
				sql += " AND u.user_id = ui.user_id(+) ";
				sql += " AND s.id_status = b.id_status(+) ";	
				sql += " AND a.id_suburusan IN (51, 52, 53) ";					
				sql += " AND TO_CHAR(b.TARIKH_PERMOHONAN,'MM/DD/YYYY') <= '08/01/2010' ";
				sql += " GROUP BY c.nama_negeri ";				
				sql += " UNION ";
				sql += " SELECT   c.nama_negeri,COUNT (DISTINCT NVL(b.id_permohonan,0)) AS permohonan_baru ";				
				sql += " FROM tblpfdfail a,tblpptpermohonan b,tblrujnegeri c,tblrujstatus s,users u,users_internal ui ";													
				sql += " WHERE a.id_fail = b.id_fail ";
				sql += " AND a.id_negeri = c.id_negeri(+) ";				
				sql += " AND b.id_masuk = u.user_id(+) ";
				sql += " AND u.user_id = ui.user_id(+) ";
				sql += " AND s.id_status = b.id_status(+) ";		
				sql += " AND a.id_suburusan IN (51, 52, 53)   ";				
				sql += " AND TO_CHAR(b.TARIKH_PERMOHONAN,'MM/DD/YYYY') >= '08/02/2010' ";
				sql += " GROUP BY c.nama_negeri)) ";
				sql += " GROUP BY nama_negeri ";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN RUMUSAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("jumlah_kutipandata", rs.getString("KUTIPAN_DATA")==null?"":rs.getString("KUTIPAN_DATA"));  
		    	h.put("jumlah_permohonanbaru", rs.getString("PERMOHONAN_BARU")==null?"":rs.getString("PERMOHONAN_BARU")); 
		    	
		    	getStatus.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatus;					
	}		

	
	public Vector getListRumusanHTP()throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
				getStatusHTP = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql = " SELECT COUNT (*) AS JUMLAH_TANAHMILIK "+				
						" FROM " +
						"TBLPERMOHONAN A, "+					
						" TBLHTPHAKMILIK B "+		
						" WHERE " +
						"A.ID_PERMOHONAN = B.ID_PERMOHONAN AND "+			    				
						" B.STATUS_SAH IN (" +
						"	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
						" ) ";
				sql += " AND NVL(B.NO_HAKMILIK,' ')<>' ' ";	
				setSQL(sql);				
				myLogger.info("LAPORAN RUMUSAN :: "+sql);			
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
				while (rs.next()) {
					h = new Hashtable();			    	
					h.put("bil", bil);		         
					h.put("jumlah_tanahmilik", rs.getString("JUMLAH_TANAHMILIK")==null?"":rs.getString("JUMLAH_TANAHMILIK")); 		    	
					getStatusHTP.addElement(h);
					bil++;
		      	}   
				
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
			}
			finally{
				if(db != null)db.close();
			}	
		return getStatusHTP;
		
	}	
	
	public Vector getSenaraiRumusanTanah()throws Exception {		 		 
		Db db = null;
		try{
				getStatusHTP = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
				SQL="SELECT SUM(H.BIL) BIL_HAKMILIK,SUM(R.BIL) BIL_RIZAB FROM  "+ 
					" (SELECT COUNT(*) BIL FROM TBLHTPHAKMILIK MT  "+ 
					" WHERE NVL(MT.NO_HAKMILIK,' ')<>' '  "+ 
					" AND MT.STATUS_SAH IN (  "+ 
					"     SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1  "+ 
					"     )  "+ 
					" ) H  "+ 
					" ,(SELECT COUNT(*) BIL FROM TBLHTPHAKMILIK MT  "+ 
					" WHERE NVL(MT.NO_WARTA,' ')<>' '  "+ 
					" AND MT.STATUS_SAH IN (  "+ 
					" SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1  "+ 
					" )  "+  
					" ) R  " +
					"";
				
				ResultSet rs = stmt.executeQuery(SQL);
				Hashtable h;	
				int bil = 1;
				
				while (rs.next()) {
					h = new Hashtable();			    	
					h.put("bil", bil);		         
					h.put("jumlah_tanahmilik", rs.getString("BIL_HAKMILIK")==null?"":rs.getString("BIL_HAKMILIK")); 		    	
					h.put("jumlah_tanahrizab", rs.getString("BIL_RIZAB")==null?"":rs.getString("BIL_RIZAB")); 		    	
					getStatusHTP.addElement(h);
					bil++;
		      	}   
				
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
			}
			finally{
				if(db != null)db.close();
			}	
		return getStatusHTP;
		
	}
	
	
	public Vector getListRumusanTanahRizab()throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusRizab = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT COUNT (A.ID_PERMOHONAN) AS JUMLAH_TANAHRIZAB ";					
				sql += " FROM TBLPERMOHONAN A, ";					
				sql += " TBLHTPHAKMILIK B ";		
				sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";				
				sql += " AND B.NO_WARTA IS NOT NULL ";	

				setSQL(sql);				
				myLogger.info("LAPORAN RUMUSAN TANAH RIZABJ :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		         
		    	h.put("jumlah_tanahrizab", rs.getString("JUMLAH_TANAHRIZAB")==null?"":rs.getString("JUMLAH_TANAHRIZAB")); 
		    	
		    	getStatusRizab.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusRizab;					
	}		
	
	
	public Vector getListRumusanPHP()throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusPHP = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				/* UPDATED BY PEJE
				 * 
				sql =  " SELECT TBLRUJSUBURUSAN.NAMA_SUBURUSAN AS NAMA_URUSAN, TBLRUJSUBURUSAN.ID_URUSAN AS ID_URUSAN, ";					
				sql += " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN ";					
				sql += " AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU, ";		
				sql += " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN ";				
				sql += " AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_KUTIPAN ";	
				sql += " FROM TBLRUJSUBURUSAN WHERE TBLRUJSUBURUSAN.ID_URUSAN = 6 ";					
				sql += " UNION ";		
				sql += " SELECT TBLRUJURUSAN.NAMA_URUSAN AS NAMA_URUSAN, TBLRUJURUSAN.ID_URUSAN AS ID_URUSAN, ";				
				sql += " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN ";				
				sql += " AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU, ";					
				sql += " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN ";		
				sql += " AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3)) AS FAIL_KUTIPAN ";				
				sql += " FROM TBLRUJURUSAN WHERE TBLRUJURUSAN.ID_URUSAN IN (7,8,9,12,13) ORDER BY ID_URUSAN ";
				*/
				
				sql = "SELECT TBLRUJSUBURUSAN.ID_URUSAN AS ID_URUSAN, TBLRUJSUBURUSAN.NAMA_SUBURUSAN AS NAMA_URUSAN,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_KUTIPAN"
					+ " FROM TBLRUJSUBURUSAN WHERE TBLRUJSUBURUSAN.ID_URUSAN = 6"  //PELEPASAN
					+ " UNION"
					+ " SELECT TBLRUJURUSAN.ID_URUSAN AS ID_URUSAN, TBLRUJURUSAN.NAMA_URUSAN AS NAMA_URUSAN,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL AND TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL AND TBLPERMOHONAN.FLAG_PERJANJIAN = 'U' AND TBLPERMOHONAN.FLAG_AKTIF = 'Y') AS FAIL_BARU,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL AND TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL AND TBLPERMOHONAN.FLAG_PERJANJIAN = 'U' AND TBLPERMOHONAN.FLAG_AKTIF = 'Y') AS FAIL_KUTIPAN"
					+ " FROM TBLRUJURUSAN WHERE TBLRUJURUSAN.ID_URUSAN IN (7,12,13)"  //PENYEWAAN + PENGELUARAN HASIL + BAHAN BATUAN
					+ " UNION"
					+ " SELECT TBLRUJURUSAN.ID_URUSAN AS ID_URUSAN, TBLRUJURUSAN.NAMA_URUSAN AS NAMA_URUSAN,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_KUTIPAN"
					+ " FROM TBLRUJURUSAN WHERE TBLRUJURUSAN.ID_URUSAN = 8"  //PENGUATKUASAAN
					+ " UNION"
					+ " SELECT TBLRUJURUSAN.ID_URUSAN AS ID_URUSAN, TBLRUJURUSAN.NAMA_URUSAN AS NAMA_URUSAN,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_KUTIPAN"
					+ " FROM TBLRUJURUSAN WHERE TBLRUJURUSAN.ID_URUSAN = 9"  //APB
					+ " ORDER BY ID_URUSAN";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN RUMUSAN PHP :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		         
		    	h.put("nama_urusan", rs.getString("NAMA_URUSAN")==null?"":rs.getString("NAMA_URUSAN")); 
		    	h.put("id_urusan", rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
		    	h.put("fail_baru", rs.getString("FAIL_BARU")==null?"":rs.getString("FAIL_BARU"));
		    	h.put("fail_kutipan", rs.getString("FAIL_KUTIPAN")==null?"":rs.getString("FAIL_KUTIPAN"));
		    	getStatusPHP.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusPHP;					
	}

	public Vector getListDaerahByNegeri(Long idNegeri, String socTahun, String socBulan, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
		 Db db = null;
		 String sql = "";
		try{
			getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				 sql =  "SELECT NVL(H.NAMA_DAERAH,'TIADA MAKLUMAT') AS x, COUNT(DISTINCT B.ID_PERMOHONAN)as y, COUNT(ID_HAKMILIK) as z " +
						"FROM  TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLRUJNEGERI C, TBLPPTHAKMILIK D, TBLRUJSTATUS S, TBLRUJDAERAH H " +
						"WHERE A.ID_FAIL = B.ID_FAIL AND A.ID_NEGERI = C.ID_NEGERI AND S.ID_STATUS = B.ID_STATUS " +
						"AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN AND A.ID_SUBURUSAN IN (51, 52, 53) AND H.ID_DAERAH = D.ID_DAERAH " +
						"AND B.ID_DAERAH = D.ID_DAERAH AND H.ID_DAERAH IS NOT NULL AND C.ID_NEGERI = D.ID_NEGERI AND A.NO_FAIL IS NOT NULL AND B.ID_STATUS NOT IN (4,5,11,113) " +
						"AND C.ID_NEGERI = "+idNegeri;
						
						
				
				/*sql =   "SELECT NVL(C.NAMA_NEGERI,'TIADA MAKLUMAT') AS x, C.ID_NEGERI, COUNT(DISTINCT B.ID_PERMOHONAN)as y, COUNT(ID_HAKMILIK) as z " +
				"FROM  TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLRUJNEGERI C, TBLPPTHAKMILIK D, TBLRUJSTATUS S, TBLRUJDAERAH H, USERS U, USERS_INTERNAL UI " +
				"WHERE A.ID_FAIL = B.ID_FAIL AND A.ID_NEGERI = C.ID_NEGERI(+) AND B.ID_MASUK = U.USER_ID(+) AND U.USER_ID = UI.USER_ID(+) AND S.ID_STATUS = B.ID_STATUS(+) " +
				"AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN AND A.ID_SUBURUSAN IN (51, 52, 53) AND H.ID_DAERAH(+) = D.ID_DAERAH AND C.ID_NEGERI NOT IN (0) AND C.ID_NEGERI = "+idNegeri;
				*/
				// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
						sql += " AND B.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', 'DD/MM/YYYY')";
					}
				}
				sql = sql + "GROUP BY H.NAMA_DAERAH";		
				
				setSQL(sql);
				
				myLogger.info("SQL LIST CARIAN : "+sql);
				
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_daerah", Utils.isNull(rs.getString("x")));
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));

		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;		
	}

	
	//penambahan yati-MEP 10/1/2018
	public static Vector<Tblrujnegeri> getNegeri() throws Exception {
		String key = "DBgetNegeriPPT";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Negeri,kod_Negeri,nama_Negeri from tblrujnegeri where id_Negeri not in (1,12,13,17,0)";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong(1));
					s.setKodNegeri(rs.getString(2));
					s.setNamaNegeri(rs.getString(3));
					v.addElement(s);
					
				}
				myLogger.info("SQL NEGERI PPT: "+sql);
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}
	public String generateXMLDaerah(String nama_laporan) {
		String xml="<chart caption='"+nama_laporan+"' subcaption='' xAxisName='Daerah' yAxisName='Jumlah Permohonan' numberPrefix='' showLegend='1'>";
		Db db = null;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){
				xml =xml+"<set label='"+rs.getString("x")+"' value='"+rs.getString("y")+"' />";
			}
			xml =xml+"</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db!=null) db.close();
		}

		return xml;
	}

	public Vector getAbbrevDaerah(Long idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try{
			getAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			
			sql =  " SELECT distinct(RK.ID_NEGERI), RK.NAMA_DAERAH, RK.ID_DAERAH "+ 
                    	" FROM TBLRUJDAERAH RK "+
                    	" WHERE RK.ID_NEGERI = "+idNegeri+
                    	" AND RK.ID_NEGERI NOT IN (1) "+
                    	" ORDER BY RK.ID_DAERAH ";
			
				setSQL(sql);				
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;				
			while (rs.next()) {
				h = new Hashtable();	        
		    	h.put("abbrev", Utils.isNull(rs.getString("ID_DAERAH")));
		    	h.put("negeri", Utils.isNull(rs.getString("NAMA_DAERAH")));		    	
		    	getAbbrev.addElement(h);
			}      
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		}finally{
			if(db != null)db.close();
		}	
		return getAbbrev;	
	}
	
}	// CLOSE MAIN CLASS
