package ekptg.view.ppt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmTukaranStatus;

/*
 * @author
 * Razman
 */

public class KronologiHakmilik extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(KronologiHakmilik.class);	
	FrmTukaranStatus model = new FrmTukaranStatus();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	HttpSession session = null;
	String action = null;
	private final String PATH="app/ppt/Kronologi_Hakmilik/";
	private String vm = PATH +"index.jsp";
	
	//String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");	
		
	public String doTemplate2()  throws Exception{
		session = request.getSession();
		String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");	
		
		defaultVarible();
		
		String command = getParam("command");
		action = getParam("action");
    	myLogger.info("[submit] :: " +command);
		String doPost = (String) session.getAttribute("doPost");
		myLogger.info("check doPost :"+doPost);   
		String usid=(String)session.getAttribute("_ekptg_user_id");		
		context.put("ScrollX",getParam("ScrollX"));
    	context.put("ScrollY",getParam("ScrollY"));
    	context.put("command",command); 
    	
    	if(command.equals("carianHakmilik")){
    		displaySenaraiHakmilik(getParam("ID_JENISHAKMILIK"),getParam("NO_HAKMILIK"),getParam("NO_FAIL"),getParam("NAMA_PROJEK"),getParam("NO_LOT_PT"),userIdNeg);
    		displayListJenisHakmilik();	
    		context.put("carian","yes");
    		context.put("ID_JENISHAKMILIK",getParam("ID_JENISHAKMILIK")); 
    		context.put("NO_HAKMILIK",getParam("NO_HAKMILIK")); 
    		context.put("NO_FAIL",getParam("NO_FAIL"));
    		context.put("NAMA_PROJEK",getParam("NAMA_PROJEK")); 
    		context.put("NO_LOT_PT",getParam("NO_LOT_PT"));
		}
    	else if(command.equals("paparHakmilik")){
    		context.put("skrinHakmilik","yes");
    		Hashtable dataHakmilik = null;
    		dataHakmilik = dataHakmilik(getParam("ID_HAKMILIK"));
    		context.put("dataHakmilik",dataHakmilik);
    		displayKronologi(getParam("ID_HAKMILIK"));
    		context.put("ID_HAKMILIK",dataHakmilik.get("ID_HAKMILIK").toString());
		}
    	else
    	{
    		
    		displayListJenisHakmilik();
    		context.put("carian","yes");
    		displaySenaraiHakmilik(getParam("ID_JENISHAKMILIK"),getParam("NO_HAKMILIK"),getParam("NO_FAIL"),getParam("NAMA_PROJEK"),getParam("NO_LOT_PT"),userIdNeg);
    		
    	}
    	
    	
		
	return vm;
	}
	
	private void defaultVarible() throws Exception{
		context.put("TUJUAN",""); 
		context.put("ID_JENISHAKMILIK",""); 
		context.put("ID_HAKMILIK",""); 
		context.put("NO_HAKMILIK",""); 
		context.put("NO_FAIL","");
		context.put("NAMA_PROJEK",""); 
		context.put("NO_LOT_PT","");
		context.put("command",""); 
		context.put("carian",""); 
		context.put("skrinHakmilik","");
	}
	
	private void displayListJenisHakmilik()throws Exception{
		List<Hashtable>  listJenisHakmilik = new ArrayList();
		listJenisHakmilik = listJenisHakmilik();		
		context.put("listJenisHakmilik",listJenisHakmilik);
	}
	
	
	List<Hashtable>  listJenisHakmilik = null;
	public List<Hashtable>  listJenisHakmilik() throws Exception {
		listJenisHakmilik = new ArrayList();
		Db db = null;
		listJenisHakmilik.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT JH.ID_JENISHAKMILIK,UPPER(JH.KOD_JENIS_HAKMILIK) AS KOD_JENIS_HAKMILIK,UPPER(JH.KETERANGAN) AS JENISHAKMILIK FROM TBLRUJJENISHAKMILIK JH ORDER BY JH.KOD_JENIS_HAKMILIK ";
			myLogger.info("LIST ID_JENISHAKMILIK:" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_JENISHAKMILIK",rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("JENISHAKMILIK",rs.getString("JENISHAKMILIK") == null ? "" : rs.getString("JENISHAKMILIK").toUpperCase());		
				listJenisHakmilik.add(h);
			}
			return listJenisHakmilik;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	private void displayKronologi(String ID_HAKMILIK) throws Exception{
		List<Hashtable>  list_kronologi = null;	
		list_kronologi = getKronologi(ID_HAKMILIK);	
		context.put("list_kronologi", list_kronologi);
	}
	
	private void displaySenaraiHakmilik(String ID_JENISHAKMILIK,String NO_HAKMILIK,String NO_FAIL,String NAMA_PROJEK,String NO_LOT_PT,String userIdNeg) throws Exception{
		List<Hashtable>  list_hakmilik = null;	
		list_hakmilik = getHakmilik(ID_JENISHAKMILIK,NO_HAKMILIK,NO_FAIL,NAMA_PROJEK,NO_LOT_PT,userIdNeg);	
		context.put("SenaraiFail", list_hakmilik);	
		List<Hashtable> list = list_hakmilik;
		setupPage(session,action,list);	

	}
	
	Vector list_kronologi = null;
	
	//backup 11-07-2013
	
	/*
	public Vector getKronologi(String ID_HAKMILIK) throws Exception {
		list_kronologi = new Vector();
		list_kronologi.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();		
			sql +="";
			sql +=" SELECT ROWNUM AS TURUTAN,UPPER(KRONOLOGI) AS PEKARA,TO_CHAR(TARIKH,'DD/MM/YYYY') AS TARIKH FROM ( ";
			  //pendaftaran hakmilik
			  sql +=" SELECT "+
					" (CASE WHEN (SELECT TO_CHAR(NO_HAKMILIK) FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL "+
					" THEN 'Hakmilik ' ||  (SELECT (SELECT JHM.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK JHM WHERE JHM.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK) FROM TBLPPTHAKMILIK H WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' ' "+
					" || (SELECT TO_CHAR(NO_HAKMILIK) FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') || '' "+
					" ELSE ' Hakmilik ini' END) "+
					" || "+
					" (CASE WHEN (SELECT TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY') FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL "+
					" THEN ' telah didaftarkan melalui sistem pada ' || (SELECT TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY') FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') || '' "+
					" ELSE ' telah didaftarkan melalui sistem' END) "+
					" || "+
					" (CASE WHEN (SELECT F.ID_SUBURUSAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" = 51 "+
					" THEN ' dibawah permohonan Seksyen 4' "+
					" WHEN (SELECT F.ID_SUBURUSAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" = 52 "+
					" THEN ' dibawah permohonan Seksyen 8' "+
					" WHEN (SELECT F.ID_SUBURUSAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" = 52 "+
					" THEN ' dibawah permohonan Pendudukan Sementara' "+
					" ELSE '' END) "+
					" || "+
					" (CASE WHEN (SELECT F.NO_FAIL FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" IS NOT NULL "+
					" THEN ' bernombor fail ' || (SELECT F.NO_FAIL FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '.' "+
					" ELSE '' END) "+
					" || "+
					" (CASE WHEN (SELECT F.ID_KEMENTERIAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" IS NOT NULL "+
					" THEN ' Permohonan ini dibuat oleh ' || (SELECT (SELECT UPPER(M.NAMA_KEMENTERIAN) FROM TBLRUJKEMENTERIAN M WHERE M.ID_KEMENTERIAN = F.ID_KEMENTERIAN) "+
					" FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' bertujuan untuk ' || "+
					" (SELECT P.TUJUAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"')  || '.'    "+       
					" ELSE '' END) "+ 				
					" || "+
					" (CASE WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') = 1 "+
					" THEN ' Seorang Pemilik/Tuan Tanah didaftarkan dibawah hakmilik ini.'   "+
					" WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') > 1 "+
					" THEN ' Seramai ' || (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' orang Pemilik/Tuan Tanah didaftarkan dibawah hakmilik ini.'   "+				
					" ELSE '' END) "+
					" || "+
					" (CASE WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') > 0  "+
					" THEN ' Pemilik/Tuan Tanah yang dimaksudkan adalah ' || (SELECT LTRIM(MAX( SYS_CONNECT_BY_PATH ( UPPER(NAMA_PB), ', ')),',') NAMA_PB FROM "+
					" (SELECT  HPB.ID_HAKMILIKPB , HPB.ID_HAKMILIK, PB.NAMA_PB, "+
					" row_number() OVER ( PARTITION BY HPB.ID_HAKMILIK  ORDER BY rownum) rn FROM  TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB  "+
					" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"' "+
					" )  CONNECT  BY  ID_HAKMILIK = PRIOR ID_HAKMILIK  AND  rn     = PRIOR rn+1 START WITH rn =1 GROUP BY ID_HAKMILIK) || '.' "+
					" ELSE '' END) "+
					" || "+
					" (CASE WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB != '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') = 1 "+
					" THEN ' Bagi pihak berkepentingan selain Pemilik/Tuan Tanah, seorang telah didaftarkan dibawah hakmilik ini.'    "+ 
					" WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB != '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') > 1 "+
					" THEN ' Bagi pihak berkepentingan selain daripada Pemilik/Tuan Tanah, seramai ' || (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB != '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' orang telah didaftarkan dibawah hakmilik ini.'    "+ 
				    " ELSE '' END)     "+	
				    " || "+
				    " (CASE WHEN ((SELECT DISTINCT P.FLAG_SEGERA FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND H.ID_PERMOHONAN = P.ID_PERMOHONAN) = '1') "+
				    " OR ((SELECT H.FLAG_SEGERA_SEBAHAGIAN FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND H.ID_PERMOHONAN = P.ID_PERMOHONAN) = 'Y') "+
				    " THEN ' Permohonan pengambilan tanah bagi hakmilik ini adalah pengambilan segera.' "+
				    " ELSE '' END) "+				
					" AS KRONOLOGI,TO_NUMBER('1') AS TURUTAN,(SELECT TO_DATE(TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH FROM DUAL  ";                     
			  sql +=" UNION ";
			  //laporan awal tanah
			  sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_LAWATAN FROM TBLPPTTANAHUMUM WHERE ID_PERMOHONAN IN ";
			  sql +=" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL "+
					" THEN    'Laporan Awal Tanah telah dilakukan pada ' ||  "+
					" (SELECT DISTINCT TO_CHAR(TARIKH_LAWATAN,'DD/MM/YYYY') FROM TBLPPTTANAHUMUM WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) || '.'                  "+          
					"  ELSE ''  "+
					" END )          "+
					" AS KRONOLOGI, TO_NUMBER('2') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_LAWATAN,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTTANAHUMUM WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH  "+
					" FROM DUAL  ";
			  sql +=" UNION ";	
			  //ulasan teknikal jpbd
			  sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_SURAT FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') IS NOT NULL "+
					" THEN    'Permohonan maklumat perancangan kepada JPBD telah dibuat pada ' ||  "+
					" (SELECT DISTINCT TO_CHAR(TARIKH_SURAT,'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') || '. '  "+                          
					" ELSE ''  "+
					" END ) "+
					" AS KRONOLOGI, TO_NUMBER('3') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') AS TARIKH  "+
					" FROM DUAL ";
			  sql +=" UNION     ";
			  //terima ulasan teknikal jpbd
			  sql +=" SELECT  (CASE WHEN (SELECT DISTINCT TARIKH_AKHIR FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') IS NOT NULL "+
					" THEN    'Menerima maklumbalas dari JPBD pada ' ||  "+
					" (SELECT DISTINCT TO_CHAR(TARIKH_AKHIR,'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') || '. '  "+                          
					" ELSE ''  "+
					" END )         "+
					" AS KRONOLOGI, TO_NUMBER('4') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') AS TARIKH  "+
					" FROM DUAL ";
			  sql +=" UNION     ";
			  //ulasan teknikal jpph
			  sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_SURAT FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') IS NOT NULL "+
					" THEN    'Permohonan maklumat perancangan kepada JPPH telah dibuat pada ' ||  "+
					" (SELECT DISTINCT TO_CHAR(TARIKH_SURAT,'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') || '. '     "+                       
					" ELSE ''  "+
					" END ) "+
					" AS KRONOLOGI, TO_NUMBER('5') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') AS TARIKH  "+
					" FROM DUAL "+
					" UNION ";
			  //terima ulasan teknikal jpph
			  sql +=" SELECT  (CASE WHEN (SELECT DISTINCT TARIKH_AKHIR FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') IS NOT NULL "+
					" THEN    'Menerima maklumbalas dari JPPH pada ' ||  "+
					" (SELECT DISTINCT TO_CHAR(TARIKH_AKHIR,'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') || '. ' "+                           
					" ELSE ''  "+
					" END )         "+
					" AS KRONOLOGI, TO_NUMBER('6') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') AS TARIKH  "+
					" FROM DUAL "+
					" UNION ";
			  //laporan tanah terperinci
			  sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_MULA_LAWAT FROM TBLPPTTANAH WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL "+
					" THEN    'Lawatan tapak dilakukan pada ' ||   "+
					" (SELECT DISTINCT TO_CHAR(TARIKH_MULA_LAWAT,'DD/MM/YYYY') FROM TBLPPTTANAH WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' untuk penyediaan laporan terperinci tanah. '      "+                       
					" ELSE ''  "+ 
					" END )    "+                          
					" AS KRONOLOGI, TO_NUMBER('7') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_MULA_LAWAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTTANAH WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
					" FROM DUAL " +
					" UNION ";
			  //jabatan teknikal
			  sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_SURAT AS TARIKH FROM " +
					" (SELECT TO_DATE(TO_CHAR(A.TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_SURAT, " +
					" (CASE WHEN A.TARIKH_SURAT IS NOT NULL " +
					" THEN    'Permohonan maklumat Jabatan Teknikal ' ||    " +                 
					" (CASE WHEN B.NAMA_JABATAN IS NOT NULL THEN 'kepada ' || UPPER(B.NAMA_JABATAN) || ' '  " +
					" ELSE '' " +
					" END)  " +
					" ||'telah dibuat pada ' ||  " +
					" TO_CHAR(A.TARIKH_SURAT,'DD/MM/YYYY') || '. '  " +                          
					" ELSE ''  " +
					" END ) " +
					" AS KRONOLOGI, " +
					" TO_NUMBER('8') AS TURUTAN " +
					" FROM TBLPPTULASANTEKNIKAL A, TBLPPTJABATANTEKNIKAL B " +
					" WHERE A.ID_JABATANTEKNIKAL = B.ID_JABATANTEKNIKAL(+) " +
					" AND NVL(A.FLAG_JENIS_ULASAN,0) NOT IN (1,2) " +
					" AND A.ID_PERMOHONAN IN  " +
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
					" ORDER BY A.TARIKH_SURAT ASC) " ;
		      sql +=" UNION ";
		      //terima jabatan teknikal
			  sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_SURAT AS TARIKH FROM " +
					" (SELECT TO_DATE(TO_CHAR(A.TARIKH_TERIMAJT,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_SURAT, " +
					" (CASE WHEN A.TARIKH_TERIMAJT IS NOT NULL " +
					" THEN    'Menerima maklumbalas ' ||   " +                  
					" (CASE WHEN B.NAMA_JABATAN IS NOT NULL THEN 'dari ' || UPPER(B.NAMA_JABATAN) || ' ' " +
					" ELSE '' " +
					" END)  " +
					" ||'pada ' ||  " +
					" TO_CHAR(A.TARIKH_TERIMAJT,'DD/MM/YYYY') || '. '     " +                       
					" ELSE ''  " +
					" END ) || " +
					" (CASE WHEN A.KEPUTUSANJT = '1' THEN    'Keputusan maklumbalas jabatan teknikal adalah disokong. ' " +
					" WHEN A.KEPUTUSANJT = '2' THEN    'Keputusan maklumbalas jabatan teknikal adalah tidak disokong. '  " +                         
					" ELSE ''  " +
					" END ) " +
					" AS KRONOLOGI, " +
					" TO_NUMBER('9') AS TURUTAN " +
					" FROM TBLPPTULASANTEKNIKAL A, TBLPPTJABATANTEKNIKAL B " +
					" WHERE A.ID_JABATANTEKNIKAL = B.ID_JABATANTEKNIKAL(+) " +
					" AND NVL(A.FLAG_JENIS_ULASAN,0) NOT IN (1,2) " +
					" AND A.ID_PERMOHONAN IN  " +
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
					" ORDER BY A.TARIKH_SURAT ASC) " ;
		      sql +=" UNION ";
		      //KERTAS MMK
		      sql +=" SELECT (CASE WHEN (SELECT A.TARIKH_HANTAR FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL " +
		    		" THEN    'Kertas MMK/MB/KM/LC telah disediakan dan dihantar pada ' ||  " +
		    		" (SELECT TO_CHAR(A.TARIKH_HANTAR,'DD/MM/YYYY') AS TARIKH_HANTAR FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) || ' untuk mendapat kelulusan PBN. '    " +                        
		    		" ELSE ''  " +
		    		" END ) " +		    		               
		    		" AS KRONOLOGI, TO_NUMBER('10') AS TURUTAN, (SELECT TO_DATE(TO_CHAR(A.TARIKH_HANTAR,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_HANTAR FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH  " +
		    		" FROM DUAL   " ;                                    
		      sql +=" UNION ";
		      //terima keputusan pbn
		      sql +=" SELECT (CASE WHEN (SELECT A.TARIKH_MMK FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL " +
		    		" THEN    'Menerima keputusan dari PBN pada ' ||  " +
		    		" (SELECT TO_CHAR(A.TARIKH_MMK,'DD/MM/YYYY') AS TARIKH_MMK FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) || ''  " +                          
		    		" ELSE ''  " +
		    		" END ) " +
		    		" || " +
		    		" (CASE WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 1  " +
		    		" THEN ' dan keputusanya adalah diluluskan.' " +
		    		" WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 2 " +
		    		" THEN ' dan keputusanya adalah ditolak.' " +
		    		" WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 3 " +
		    		" THEN ' dan keputusanya adalah ditangguh.'  " +                          
		    		" ELSE '.'  " +
		    		" END )          " +                  
		    		" AS KRONOLOGI, TO_NUMBER('11') AS TURUTAN, (SELECT TO_DATE(TO_CHAR(A.TARIKH_MMK,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_MMK FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH  " +
		    		" FROM DUAL   " ;                                    
		      sql +=" UNION ";
		      //warta
		      sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_WARTA FROM TBLPPTWARTA " + 
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) " +
		    		" AND TARIKH_WARTA IS NOT NULL " +
		    		" AND ROWNUM = 1) IS NOT NULL  " +
		    		" THEN    'Warta dikeluarkan pada ' ||   " +
		    		" (SELECT DISTINCT TO_CHAR(TARIKH_WARTA,'DD/MM/YYYY') AS TARIKH_WARTA FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) " +
		    		" AND TARIKH_WARTA IS NOT NULL " +
		    		" AND ROWNUM = 1) || '. '                        " +
		    		" ELSE ''   " +
		    		" END )  ||     " +
		    		" (CASE WHEN (SELECT DISTINCT NO_WARTA FROM TBLPPTWARTA  " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) " +
		    		" AND TARIKH_WARTA IS NOT NULL " +
		    		" AND ROWNUM = 1) IS NOT NULL  " +
		    		" THEN    'No Warta adalah ' ||   " +
		    		" (SELECT DISTINCT NO_WARTA FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) " +
		    		" AND TARIKH_WARTA IS NOT NULL " +
		    		" AND ROWNUM = 1) || '. '                        " +
		    		" ELSE ''   " +
		    		" END )                       " +
		    		" AS KRONOLOGI, TO_NUMBER('12') AS TURUTAN,  (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_WARTA,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_WARTA FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) " +
		    		" AND TARIKH_WARTA IS NOT NULL " +
		    		" AND ROWNUM = 1)  AS TARIKH   " +
		    		" FROM DUAL ";	
		      sql +=" UNION ";
		      //borang e
		      sql +=" SELECT (CASE WHEN (SELECT DISTINCT B.TARIKH_BORANGE FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		      		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL " +
		    		" THEN    'Borang E dikeluarkan pada ' ||  " +
		    		" (SELECT DISTINCT TO_CHAR(B.TARIKH_BORANGE,'DD/MM/YYYY') AS TARIKH_BORANGE FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '   " +                         
		    		" ELSE ''  " +
		    		" END ) " +
		    		" || " +
		    		" (CASE WHEN (SELECT DISTINCT B.TARIKH_AKHIR_TAMPAL FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL " +
		    		" THEN    'Tarikh akhir tampal adalah pada ' || " + 
		    		" (SELECT DISTINCT TO_CHAR(B.TARIKH_AKHIR_TAMPAL,'DD/MM/YYYY') AS TARIKH_AKHIR_TAMPAL FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '   " +                         
		    		" ELSE '' " + 
		    		" END ) " +
		    		" || " +
		    		" (CASE WHEN (SELECT DISTINCT B.TARIKH_SIASATAN FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL " +
		    		" THEN    'Tarikh ditetapkan pada ' ||  " +
		    		" (SELECT DISTINCT TO_CHAR(B.TARIKH_SIASATAN,'DD/MM/YYYY') AS TARIKH_SIASATAN FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '       " +                     
		    		" ELSE ''  " +
		    		" END )     " +     
		    		" AS KRONOLOGI, TO_NUMBER('13') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(B.TARIKH_BORANGE,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGE FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH  " +
		    		" FROM DUAL  " ;      
		      sql +=" UNION ";
               //borang F
		      sql +=" SELECT (CASE WHEN (SELECT DISTINCT B.TARIKH_BORANGF FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL " +
		    		" THEN    'Borang F dikeluarkan pada ' ||  " +
		    		" (SELECT DISTINCT TO_CHAR(B.TARIKH_BORANGF,'DD/MM/YYYY') AS TARIKH_BORANGF FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '           " +                 
		    		" ELSE ''  " +
		    		" END )        " +                 
		    		" AS KRONOLOGI, TO_NUMBER('14') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(B.TARIKH_BORANGE,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGE FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH  " +
		    		" FROM DUAL		" ; 
		      sql +=" UNION ";
		      //notis awam
		      sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_TAMPAL AS TARIKH FROM ( " +
		    		" SELECT (CASE WHEN B.JENIS_TEMPAT_TAMPAL IS NOT NULL THEN 'Notis Awan telah ditampal di ' || " +
		    		" (CASE " +
		    		" WHEN B.JENIS_TEMPAT_TAMPAL = '1' THEN 'PTD / PTG' " +
		    		" WHEN B.JENIS_TEMPAT_TAMPAL = '2' THEN 'PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR' " +
		    		" WHEN B.JENIS_TEMPAT_TAMPAL = '3' THEN 'TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH' " +
		    		" ELSE '' END) " +
		    		" ELSE '' END ) || " +
		    		" (CASE WHEN TEMPAT IS NOT NULL THEN ' (' || TEMPAT || ')' ELSE '' END )|| " +
		    		" (CASE WHEN B.TARIKH_TAMPAL IS NOT NULL THEN ' pada ' || TO_CHAR(B.TARIKH_TAMPAL,'DD/MM/YYYY') ELSE '' END )  " +            
		    		" AS KRONOLOGI,TO_NUMBER('15') AS TURUTAN,TO_DATE(TO_CHAR(B.TARIKH_TAMPAL,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_TAMPAL " +
		    		" FROM TBLPPTNOTISAWAMHAKMILIK A, TBLPPTNOTISAWAM B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_NOTISAWAM = B.ID_NOTISAWAM " +
		    		" AND A.ID_HAKMILIK = C.ID_HAKMILIK " +
		    		" AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"' " +
		    		" AND B.TARIKH_TAMPAL IS NOT NULL)  " ;
		      sql +=" UNION ";
		      //penandaan kawasan
		      sql +=" SELECT (CASE WHEN (SELECT TARIKH_LAWAT FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL "+
		    		" THEN    'Penandaan Kawasan telah dibuat pada ' ||  "+
		    		" (SELECT TO_CHAR(TARIKH_LAWAT,'DD/MM/YYYY') AS TARIKH_LAWAT FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) || '. ' "+                           
		    		" ELSE '' "+ 
		    		" END )  "+
		    		" || "+
		    		" (CASE WHEN (SELECT TARIKH_MULA FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL AND "+
		    		" (SELECT TARIKH_AKHIR FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL  "+
		    		" THEN    'Proses penandaan bermula dari ' ||   "+
		    		" (SELECT TO_CHAR(TARIKH_MULA,'DD/MM/YYYY') AS TARIKH_LAWAT FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) || ' sehingga '   "+
		    		" || (SELECT TO_CHAR(TARIKH_AKHIR,'DD/MM/YYYY') AS TARIKH_LAWAT FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) || '. '    "+                       
		    		" ELSE ''   "+
		    		" END )      "+                        
		    		" AS KRONOLOGI, TO_NUMBER('16') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_MULA_LAWAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTTANAH WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
		    		" FROM DUAL ";
		      sql +=" UNION ";
		      //siasatan
		      sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_SIASATAN AS TARIKH FROM ( "+
		    		" SELECT (CASE WHEN S.TARIKH_SIASATAN IS NOT NULL THEN 'Urusan siasatan (' "+
		    		" || "+
		    		" (CASE WHEN S.NO_KES IS NOT NULL THEN 'No. Kes : ' || S.NO_KES || ', ' ELSE '' END) "+ 
		    		" || "+
		    		" (CASE WHEN S.BIL_SIASATAN IS NOT NULL THEN 'Bil. Siasatan : ' || S.BIL_SIASATAN || '' ELSE '' END ) "+                 
		    		" || "+                  
		    		" ') telah dibuat ' "+
		    		" ELSE '' END ) ||  "+                 
		    		" (CASE WHEN S.TEMPAT IS NOT NULL THEN 'di ' || TEMPAT || ' ' ELSE '' END ) "+
		    		" || "+
		    		" (CASE WHEN S.TARIKH_SIASATAN IS NOT NULL THEN 'pada ' || TO_CHAR(S.TARIKH_SIASATAN,'DD/MM/YYYY') || '. ' ELSE '' END ) "+
		    		" || "+
		    		" (CASE WHEN S.STATUS_SIASATAN IS NOT NULL THEN  "+
		    		" 'Status semasa siasatan adalah ' || "+
		    		" CASE WHEN S.STATUS_SIASATAN = '1'  THEN 'Siasatan Permulaan. '  "+
		    		" WHEN S.STATUS_SIASATAN = '2'  THEN 'Ditunda. ' "+ 
		    		" WHEN S.STATUS_SIASATAN = '3'  THEN 'Dibatalkan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '4'  THEN 'Siasatan Ulangan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '5'  THEN 'Sambung Siasatan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '6'  THEN 'Selesai. ' "+
		    		" WHEN S.STATUS_SIASATAN = '7'  THEN 'Tangguh. ' "+
		    		" ELSE '' END  "+
		    		" ELSE '' END )  "+
		    		" || "+
		    		" CASE WHEN (SELECT NVL(SUM(BAYAR_PAMPASAN),0) FROM TBLPPTAWARD WHERE ID_SIASATAN =  "+
		    		" (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6' AND ID_PENARIKANBALIK IS NULL)) > 0  "+
		    		" THEN 'Sejumlah pampasan sebanyak RM ' || "+
		    		" (SELECT TRIM(TO_CHAR(SUM(BAYAR_PAMPASAN),'999,999,999,990.99')) AS BAYAR_PAMPASAN FROM TBLPPTAWARD WHERE ID_SIASATAN = "+ 
		    		" (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6' AND ID_PENARIKANBALIK IS NULL)) "+
		    		" ELSE '' END || ' ' || 'telah diawardkan kepada pihak-pihak perkepentingan yang terlibat.' "+                  
		    		" AS KRONOLOGI,TO_NUMBER('17') AS TURUTAN,TO_DATE(TO_CHAR(S.TARIKH_SIASATAN,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_SIASATAN "+
		    		" FROM TBLPPTSIASATAN S WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND ID_PENARIKANBALIK IS NULL"+
		    		" AND S.TARIKH_SIASATAN IS NOT NULL) ";
		      sql +=" UNION ";
		      //borang g
		      sql +=" SELECT (CASE WHEN (SELECT G.TARIKH_BORANGG FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
		    		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) IS NOT NULL "+
		    		" THEN    'Borang G telah dikeluarkan pada ' || "+
		    		" (SELECT TO_CHAR(G.TARIKH_BORANGG,'DD/MM/YYYY') AS TARIKH_BORANGG FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
		    		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) || '. '  "+             
		    		" ELSE '' "+ 
		    		" END )   "+                          
		    		" AS KRONOLOGI, TO_NUMBER('18') AS TURUTAN, "+
		    		" (SELECT TO_DATE(TO_CHAR(G.TARIKH_BORANGG,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGG FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
		    		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) AS TARIKH  "+
		    		" FROM DUAL ";
	          sql +=" UNION ";
	          //borang h
	          sql +=" SELECT (CASE WHEN (SELECT G.TARIKH_BORANGH FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
	        		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) IS NOT NULL "+ 
	        		" THEN    'Borang H telah dikeluarkan pada ' || "+ 
	        		" (SELECT TO_CHAR(G.TARIKH_BORANGH,'DD/MM/YYYY') AS TARIKH_BORANGH FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
	        		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) || '. '    "+           
	        		" ELSE '' "+ 
	        		" END )    "+                         
	        		" AS KRONOLOGI, TO_NUMBER('19') AS TURUTAN, "+
	        		" (SELECT TO_DATE(TO_CHAR(G.TARIKH_BORANGH,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGH FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
	        		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) AS TARIKH  "+
	        		" FROM DUAL  ";
	          sql +=" UNION ";
	          //borang k
	          sql +=" SELECT (CASE WHEN (SELECT K.TARIKH_BORANGK FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
		            " WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
		            " AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
		            " THEN    'Borang K telah dikeluarkan pada ' ||  "+
		            " (SELECT TO_CHAR(K.TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
		            " WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
		            " AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '   "+             
		            " ELSE ''   "+
		            " END )  "+                            
		            " AS KRONOLOGI, TO_NUMBER('20') AS TURUTAN,  "+
		            " (SELECT TO_DATE(TO_CHAR(K.TARIKH_BORANGK,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGK FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
		            " WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
		            " AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH  "+ 
		            " FROM DUAL ";
              sql +=" UNION ";
              //borang L
              sql +=" SELECT (CASE WHEN (SELECT TARIKH_BORANGL FROM TBLPPTBORANGL WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Borang L telah dikeluarkan pada ' ||  "+
            		" (SELECT TO_CHAR(TARIKH_BORANGL,'DD/MM/YYYY') AS TARIKH_BORANGL FROM TBLPPTBORANGL WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '  "+              
            		" ELSE ''   "+
            		" END )  "+                            
            		" AS KRONOLOGI, TO_NUMBER('21') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(TARIKH_BORANGL,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGL FROM TBLPPTBORANGL WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
              //hantar endorsan borang k
              sql +=" SELECT (CASE WHEN (SELECT H.TARIKH_CATATAN FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Maklumat Endorsan Borang K dihantar pada ' || "+ 
            		" (SELECT TO_CHAR(H.TARIKH_CATATAN,'DD/MM/YYYY') AS TARIKH_CATATAN FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '     "+           
            		" ELSE ''   "+
            		" END )   "+                           
            		" AS KRONOLOGI, TO_NUMBER('22') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(H.TARIKH_CATATAN,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_CATATAN FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
              //terima endorsan borang k
              sql +=" SELECT (CASE WHEN (SELECT H.TARIKH_TERIMA FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Endorsan Borang K diterima pada ' ||  "+
            		" (SELECT TO_CHAR(H.TARIKH_TERIMA,'DD/MM/YYYY') AS TARIKH_TERIMA  FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '   "+             
            		" ELSE ''   "+
            		" END )    "+                          
            		" AS KRONOLOGI, TO_NUMBER('23') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(H.TARIKH_TERIMA,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_TERIMA FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
              //BORANG I         
              sql +=" SELECT (CASE WHEN (((SELECT DISTINCT P.FLAG_SEGERA FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND H.ID_PERMOHONAN = P.ID_PERMOHONAN) = '1') "+
            		" OR ((SELECT H.FLAG_SEGERA_SEBAHAGIAN FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND H.ID_PERMOHONAN = P.ID_PERMOHONAN) = 'Y')) AND  "+
            		" (SELECT DISTINCT A.TARIKH_BORANGI FROM TBLPPTBORANGI A,  TBLPPTPERMOHONAN C  "+
            		" WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"'))  IS NOT NULL "+
            		" THEN    'Borang I telah dikeluarkan pada ' ||                     "+
            		" (SELECT TO_CHAR(TARIKH_BORANGI,'DD/MM/YYYY') FROM TBLPPTBORANGI A,  TBLPPTPERMOHONAN C  "+
            		" WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+            
            		" || '. '    "+            
            		" ELSE ''   "+
            		" END )     "+                        
            		"  AS KRONOLOGI, TO_NUMBER('24') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(TARIKH_BORANGI,'DD/MM/YYYY'),'DD/MM/YYYY')  FROM TBLPPTBORANGI A,  TBLPPTPERMOHONAN C  "+
            		" WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"'))   AS TARIKH   "+
            		" FROM DUAL  ";
                    sql +=" UNION ";
              //PU             
              sql +=" SELECT (CASE WHEN (SELECT DISTINCT B.TARIKH_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Permohonan Permintaan Ukur ' || "+
            		" (SELECT DISTINCT '(No. PU: ' || B.NO_PU || ')' FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND B.NO_PU IS NOT NULL) "+
            		" || ' telah dibuat pada ' ||  "+
            		" (SELECT DISTINCT TO_CHAR(B.TARIKH_PU,'DD/MM/YYYY') AS TARIKH_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '   "+             
            		" ELSE ''   "+
            		" END )    "+
            		" ||   "+
            		" (CASE WHEN (SELECT DISTINCT B.LUAS_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Luas Pelan Akui adalah ' || "+
            		" (SELECT DISTINCT TRIM(TO_CHAR(B.LUAS_PU,'999999999990.9999')) || ' Hektar' AS LUAS_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. ' "+               
            		" ELSE ''  "+ 
            		" END )  "+
            		" ||   "+
            		" (CASE WHEN (SELECT DISTINCT B.BEZA_LUAS FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Perbezaan luas adalah ' || "+
            		" (SELECT DISTINCT TRIM(TO_CHAR(B.BEZA_LUAS,'999999999990.9999')) || ' Hektar' AS LUAS_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B "+ 
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '     "+           
            		" ELSE ''   "+
            		" END )    "+                       
            		" AS KRONOLOGI, TO_NUMBER('25') AS TURUTAN,  "+
            		" (SELECT DISTINCT TO_DATE(TO_CHAR(B.TARIKH_PU,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
              //PENARIKAN BALIK DAFTAR            
              sql +=" SELECT (CASE WHEN (SELECT PB.TARIKH_SURAT FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Permohonan untuk Penarikan Balik bagi hakmilik ini dibuat pada ' ||                  "+   
            		" (SELECT TO_CHAR(PB.TARIKH_SURAT,'DD/MM/YYYY') FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')             "+  
            		" || '. '      "+          
            		" ELSE ''   "+
            		" END )  "+
            		" || "+
            		" (CASE WHEN (SELECT PB.NO_RUJUKAN_SURAT FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'No. Rujukan Surat permohonan penarikan balik adalah ' ||                 "+    
            		" (SELECT PB.NO_RUJUKAN_SURAT FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')    "+           
            		" || '. '  "+              
            		" ELSE ''  "+ 
            		" END )    "+                          
            		" AS KRONOLOGI, TO_NUMBER('26') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(PB.TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";       
              //PENARIKAN BALIK LAPOPTAN TANAH              
              sql +=" SELECT (CASE WHEN (SELECT TARIKH_PEMERIKSAAN FROM TBLPPTTANAHUMUM T "+
            		" WHERE T.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Lawatan tapak dilakukan bagi pemeriksaan hakmilik yang ditarik balik pada ' ||     "+                
            		" (SELECT TO_CHAR(TARIKH_PEMERIKSAAN,'DD/MM/YYYY')  FROM TBLPPTTANAHUMUM T "+
            		" WHERE T.ID_HAKMILIK = '"+ID_HAKMILIK+"')         "+      
            		" || '. '                "+
            		" ELSE ''   "+
            		" END )               "+                                   
            		" AS KRONOLOGI, TO_NUMBER('27') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(PB.TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
		      //penarikan balik KERTAS MMK             
              sql +=" SELECT (CASE WHEN (SELECT C.TARIKH_HANTAR FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
            		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL  "+
            		" THEN    'Kertas MMK/MB/KM/LC bagi urusan penarikan balik telah disediakan dan dihantar pada ' ||   "+
            		" (SELECT TO_CHAR(C.TARIKH_HANTAR,'DD/MM/YYYY') FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
            		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) || ' untuk mendapat kelulusan PBN. '      "+                       
            		" ELSE ''  "+ 
            		" END ) 	 "+	    		               
            		" AS KRONOLOGI, TO_NUMBER('28') AS TURUTAN, (SELECT TO_DATE(TO_CHAR(C.TARIKH_HANTAR,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_HANTAR FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  "+ 
            		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH   "+
            		" FROM DUAL  ";                                                        
		      sql +=" UNION ";
		      //terima keputusan pbn penarikan balik          
		      sql +=" SELECT (CASE WHEN (SELECT C.TARIKH_TERIMA FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL  "+
		    		" THEN    'Menerima keputusan dari PBN bagi urusan Penarikan Balik pada ' ||   "+
		    		" (SELECT TO_CHAR(C.TARIKH_TERIMA,'DD/MM/YYYY') AS TARIKH_MMK FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND   A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) || ''   "+                          
		    		" ELSE ''   "+
		    		" END )  "+
		    		" ||  "+
		    		" (CASE WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND   A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 1   "+
		    		" THEN ' dan keputusanya adalah diluluskan.'  "+
		    		" WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND   A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 2  "+
		    		" THEN ' dan keputusanya adalah ditolak.'  "+
		    		" WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND   A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 3  "+
		    		" THEN ' dan keputusanya adalah ditangguh.'       "+                      
		    		" ELSE '.'   "+
		    		" END )         "+                    
		    		" AS KRONOLOGI, TO_NUMBER('29') AS TURUTAN, (SELECT TO_DATE(TO_CHAR(C.TARIKH_TERIMA,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_MMK FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND   A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		"  WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH   "+
		    		" FROM DUAL  ";
		      sql +=" UNION ";      
                    //warta penarikan balik
		      sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_WARTA FROM TBLPPTWARTA   "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')  "+
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+
		    		" AND TARIKH_WARTA IS NOT NULL  "+
		    		" AND ROWNUM = 1) IS NOT NULL   "+
		    		" THEN    'Warta untuk permohonan penarikan balik dikeluarkan pada ' ||   "+ 
		    		" (SELECT DISTINCT TO_CHAR(TARIKH_WARTA,'DD/MM/YYYY') AS TARIKH_WARTA FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')  "+
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+
		    		" AND TARIKH_WARTA IS NOT NULL  "+
		    		" AND ROWNUM = 1) || '. '        "+                 
		    		" ELSE ''    "+
		    		" END )  ||      "+
		    		" (CASE WHEN (SELECT DISTINCT NO_WARTA FROM TBLPPTWARTA   "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')  "+
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+
		    		" AND TARIKH_WARTA IS NOT NULL  "+
		    		" AND ROWNUM = 1) IS NOT NULL   "+
		    		" THEN    'No Warta adalah ' ||   "+ 
		    		" (SELECT DISTINCT NO_WARTA FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')  "+
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+
		    		" AND TARIKH_WARTA IS NOT NULL  "+
		    		" AND ROWNUM = 1) || '. '          "+               
		    		" ELSE ''    "+
		    		" END )           "+             
		    		" AS KRONOLOGI, TO_NUMBER('30') AS TURUTAN,  (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_WARTA,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_WARTA FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')  "+
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+
		    		" AND TARIKH_WARTA IS NOT NULL  "+
		    		" AND ROWNUM = 1)  AS TARIKH    "+
		    		" FROM DUAL ";
		      sql +=" UNION ";
               //siasatan penarikan balik
		      sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_SIASATAN AS TARIKH FROM ( "+
		    		" SELECT (CASE WHEN S.TARIKH_SIASATAN IS NOT NULL THEN 'Urusan siasatan bagi hakmilik yang ditarik balik (' "+
		    		" || "+
		    		" (CASE WHEN S.NO_KES IS NOT NULL THEN 'No. Kes : ' || S.NO_KES || ', ' ELSE '' END)  "+
		    		" || "+
		    		" (CASE WHEN S.BIL_SIASATAN IS NOT NULL THEN 'Bil. Siasatan : ' || S.BIL_SIASATAN || '' ELSE '' END )         "+         
		    		" ||    "+               
		    		" ') telah dibuat ' "+
		    		" ELSE '' END ) ||     "+              
		    		" (CASE WHEN S.TEMPAT IS NOT NULL THEN 'di ' || TEMPAT || ' ' ELSE '' END ) "+
		    		" || "+
		    		" (CASE WHEN S.TARIKH_SIASATAN IS NOT NULL THEN 'pada ' || TO_CHAR(S.TARIKH_SIASATAN,'DD/MM/YYYY') || '. ' ELSE '' END ) "+
		    		" || "+
		    		" (CASE WHEN S.STATUS_SIASATAN IS NOT NULL THEN  "+
		    		" 'Status semasa siasatan adalah ' || "+
		    		" CASE WHEN S.STATUS_SIASATAN = '1'  THEN 'Siasatan Permulaan. '  "+
		    		" WHEN S.STATUS_SIASATAN = '2'  THEN 'Ditunda. '  "+
		    		" WHEN S.STATUS_SIASATAN = '3'  THEN 'Dibatalkan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '4'  THEN 'Siasatan Ulangan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '5'  THEN 'Sambung Siasatan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '6'  THEN 'Selesai. ' "+
		    		" WHEN S.STATUS_SIASATAN = '7'  THEN 'Tangguh. ' "+
		    		" ELSE '' END  "+
		    		" ELSE '' END )  "+
		    		" || "+
		    		" CASE WHEN (SELECT NVL(SUM(BAYAR_PAMPASAN),0) FROM TBLPPTAWARD WHERE ID_SIASATAN =  "+
		    		" (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6' AND ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB  "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))) > 0  "+
		    		" THEN 'Sejumlah pampasan sebanyak RM ' || "+
		    		" (SELECT TRIM(TO_CHAR(SUM(BAYAR_PAMPASAN),'999,999,999,990.99')) AS BAYAR_PAMPASAN FROM TBLPPTAWARD WHERE ID_SIASATAN =  "+
		    		" (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6' AND ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') )  "+
		    		" ) "+
		    		" ELSE '' END || ' ' || 'telah diawardkan kepada pihak-pihak perkepentingan yang terlibat.'  "+                 
		    		" AS KRONOLOGI,TO_NUMBER('31') AS TURUTAN,TO_DATE(TO_CHAR(S.TARIKH_SIASATAN,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_SIASATAN "+
		    		" FROM TBLPPTSIASATAN S WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' "+
		    		" AND ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
		    		" AND S.TARIKH_SIASATAN IS NOT NULL) ";
		      sql +=" UNION ";              
              //PEMBATALAN DAFTAR             
		      sql +=" SELECT (CASE WHEN (SELECT TARIKH_PEMBATALAN FROM TBLPPTPEMBATALANHAKMILIK PH, TBLPPTPEMBATALAN PB "+
		    		  " WHERE PH.ID_PEMBATALAN = PB.ID_PEMBATALAN AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
		    		  " THEN    'Permohonan untuk Pembatalan bagi hakmilik ini telah dibuat pada ' ||                 "+    
		    		  " (SELECT TO_CHAR(TARIKH_PEMBATALAN,'DD/MM/YYYY') FROM TBLPPTPEMBATALANHAKMILIK PH, TBLPPTPEMBATALAN PB "+
		    		  " WHERE PH.ID_PEMBATALAN = PB.ID_PEMBATALAN AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')              "+
		    		  " || '. '       "+         
		    		  " ELSE ''   "+
		    		  " END )         "+                    
		    		  " AS KRONOLOGI, TO_NUMBER('32') AS TURUTAN,  "+
		    		  " (SELECT TO_DATE(TO_CHAR(TARIKH_PEMBATALAN,'DD/MM/YYYY'),'DD/MM/YYYY')  FROM TBLPPTPEMBATALANHAKMILIK PH, TBLPPTPEMBATALAN PB "+
		    		  " WHERE PH.ID_PEMBATALAN = PB.ID_PEMBATALAN AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')   AS TARIKH   "+
		    		  " FROM DUAL ";
			  sql +=" ORDER BY TARIKH,TURUTAN ";
			  sql +=" ) WHERE KRONOLOGI IS NOT NULL AND TARIKH IS NOT NULL ";
			
			
			myLogger.info("LIST KRONOLOGI :"+sql);
			//stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {				
				Hashtable h = new Hashtable();		
				if (rs.getString("TURUTAN") == null) {	h.put("TURUTAN", ""); } else { h.put("TURUTAN", rs.getString("TURUTAN").toUpperCase()); }				
				if (rs.getString("PEKARA") == null) {	h.put("PEKARA", ""); } else { h.put("PEKARA", rs.getString("PEKARA").toUpperCase()); }
				if (rs.getString("TARIKH") == null) {	h.put("TARIKH", ""); } else { h.put("TARIKH", rs.getString("TARIKH").toUpperCase()); }
				list_kronologi.addElement(h);
			}
			return list_kronologi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	*/
	
	
	public Vector getKronologi(String ID_HAKMILIK) throws Exception {
		list_kronologi = new Vector();
		list_kronologi.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();		
			sql +="";
			sql +=" SELECT ROWNUM AS RN,TO_CHAR(ROWNUM) AS TURUTAN,UPPER(KRONOLOGI) AS PEKARA,TO_CHAR(TARIKH,'DD/MM/YYYY') AS TARIKH FROM ( ";
			  //pendaftaran hakmilik
				sql +=" SELECT "+					
					" (CASE WHEN (SELECT F.ID_KEMENTERIAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" IS NOT NULL "+
					" THEN 'Permohonan ' || " +
					
					" (CASE WHEN (SELECT F.ID_SUBURUSAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" = 51 "+
					" THEN ' Pengambilan Tanah DiBawah Seksyen 4' "+
					" WHEN (SELECT F.ID_SUBURUSAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" = 52 "+
					" THEN ' Pengambilan Tanah DiBawah Seksyen 8' "+
					" WHEN (SELECT F.ID_SUBURUSAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" = 52 "+
					" THEN ' Pendudukan Sementara' "+
					" ELSE '' END) "+
					" || "+
					" (CASE WHEN (SELECT F.NO_FAIL FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" IS NOT NULL "+
					" THEN ' bernombor fail ' || (SELECT F.NO_FAIL FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '' "+
					" ELSE '' END) "+
					" || ' telah dibuat oleh ' || "+
										
					
					"(SELECT (SELECT UPPER(M.NAMA_KEMENTERIAN) FROM TBLRUJKEMENTERIAN M WHERE M.ID_KEMENTERIAN = F.ID_KEMENTERIAN) "+
					" FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' bertujuan untuk ' || "+
					" (SELECT P.TUJUAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"')  || '.'    "+       
					" ELSE '' END) "+
					" AS KRONOLOGI,TO_NUMBER('1') AS TURUTAN," +
					//"(SELECT TO_DATE(TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
					" (SELECT DISTINCT TO_DATE(TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"')"+
							" AS TARIKH FROM DUAL  ";                     
			  sql +=" UNION ";
			  sql +=" SELECT "+
					" (CASE WHEN (SELECT TO_CHAR(NO_HAKMILIK) FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL "+
					" THEN 'Hakmilik ' ||  (SELECT (SELECT JHM.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK JHM WHERE JHM.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK) FROM TBLPPTHAKMILIK H WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' ' "+
					" || (SELECT TO_CHAR(NO_HAKMILIK) FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') || '' "+
					" ELSE ' Hakmilik ini' END) "+
					" || "+
					" (CASE WHEN (SELECT TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY') FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL "+
					//" THEN ' telah didaftarkan melalui sistem pada ' || (SELECT TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY') FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') || '' "+
					" THEN ' telah didaftarkan melalui sistem.' "+
					" ELSE ' telah didaftarkan melalui sistem.' END) "+
					" || "+/*
					" (CASE WHEN (SELECT F.ID_SUBURUSAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" = 51 "+
					" THEN ' dibawah permohonan Seksyen 4' "+
					" WHEN (SELECT F.ID_SUBURUSAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" = 52 "+
					" THEN ' dibawah permohonan Seksyen 8' "+
					" WHEN (SELECT F.ID_SUBURUSAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" = 52 "+
					" THEN ' dibawah permohonan Pendudukan Sementara' "+
					" ELSE '' END) "+
					" || "+
					" (CASE WHEN (SELECT F.NO_FAIL FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" IS NOT NULL "+
					" THEN ' bernombor fail ' || (SELECT F.NO_FAIL FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '.' "+
					" ELSE '' END) "+
					" || "+
					" (CASE WHEN (SELECT F.ID_KEMENTERIAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
					" IS NOT NULL "+
					" THEN ' Permohonan ini dibuat oleh ' || (SELECT (SELECT UPPER(M.NAMA_KEMENTERIAN) FROM TBLRUJKEMENTERIAN M WHERE M.ID_KEMENTERIAN = F.ID_KEMENTERIAN) "+
					" FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' bertujuan untuk ' || "+
					" (SELECT P.TUJUAN FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLPPTHAKMILIK HM WHERE F.ID_FAIL = P.ID_FAIL AND HM.ID_PERMOHONAN = P.ID_PERMOHONAN AND HM.ID_HAKMILIK = '"+ID_HAKMILIK+"')  || '.'    "+       
					" ELSE '' END) "+ 				
					" || "+*/
					" (CASE WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') = 1 "+
					" THEN ' Seorang Pemilik/Tuan Tanah didaftarkan dibawah hakmilik ini.'   "+
					" WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') > 1 "+
					" THEN ' Seramai ' || (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' orang Pemilik/Tuan Tanah didaftarkan dibawah hakmilik ini.'   "+				
					" ELSE '' END) "+
					" || "+
					" (CASE WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') > 0  "+
					" THEN ' Pemilik/Tuan Tanah yang dimaksudkan adalah ' || (SELECT LTRIM(MAX( SYS_CONNECT_BY_PATH ( UPPER(NAMA_PB), ', ')),',') NAMA_PB FROM "+
					" (SELECT  HPB.ID_HAKMILIKPB , HPB.ID_HAKMILIK, PB.NAMA_PB, "+
					" row_number() OVER ( PARTITION BY HPB.ID_HAKMILIK  ORDER BY rownum) rn FROM  TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB  "+
					" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN AND HPB.ID_JENISPB = '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"' "+
					" )  CONNECT  BY  ID_HAKMILIK = PRIOR ID_HAKMILIK  AND  rn     = PRIOR rn+1 START WITH rn =1 GROUP BY ID_HAKMILIK) || '.' "+
					" ELSE '' END) "+
					" || "+
					" (CASE WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB != '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') = 1 "+
					" THEN ' Bagi pihak berkepentingan selain Pemilik/Tuan Tanah, seorang telah didaftarkan dibawah hakmilik ini.'    "+ 
					" WHEN (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB != '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') > 1 "+
					" THEN ' Bagi pihak berkepentingan selain daripada Pemilik/Tuan Tanah, seramai ' || (SELECT COUNT(*) AS TOTAL FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIKPB HPB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
					" AND HPB.ID_JENISPB != '1' AND HPB.ID_HAKMILIK = '"+ID_HAKMILIK+"') || ' orang telah didaftarkan dibawah hakmilik ini.'    "+ 
				    " ELSE '' END)     "+	
				    " || "+
				    " (CASE WHEN ((SELECT DISTINCT P.FLAG_SEGERA FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND H.ID_PERMOHONAN = P.ID_PERMOHONAN) = '1') "+
				    " OR ((SELECT H.FLAG_SEGERA_SEBAHAGIAN FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND H.ID_PERMOHONAN = P.ID_PERMOHONAN) = 'Y') "+
				    " THEN ' Permohonan pengambilan tanah bagi hakmilik ini adalah pengambilan segera.' "+
				    " ELSE '' END) "+				
					" AS KRONOLOGI,TO_NUMBER('2') AS TURUTAN,(SELECT TO_DATE(TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH FROM DUAL  ";                     
			  sql +=" UNION ";
			  //laporan awal tanah
			  sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_LAWATAN FROM TBLPPTTANAHUMUM WHERE ID_PERMOHONAN IN ";
			  sql +=" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL "+
					" THEN    'Menyediakan Laporan Awal Tanah.'                  "+          
					"  ELSE ''  "+
					" END )          "+
					" AS KRONOLOGI, TO_NUMBER('3') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_LAWATAN,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTTANAHUMUM WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH  "+
					" FROM DUAL  ";
			  sql +=" UNION ";	
			  //ulasan teknikal jpbd
			  sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_SURAT FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') IS NOT NULL "+
					" THEN    'Permohonan maklumat perancangan kepada JPBD. '  "+                          
					" ELSE ''  "+
					" END ) "+
					" AS KRONOLOGI, TO_NUMBER('4') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') AS TARIKH  "+
					" FROM DUAL ";
			  sql +=" UNION     ";
			  //terima ulasan teknikal jpbd
			  sql +=" SELECT  (CASE WHEN (SELECT DISTINCT TARIKH_AKHIR FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') IS NOT NULL "+
					" THEN    'Menerima maklumbalas dari JPBD. '  "+                          
					" ELSE ''  "+
					" END )         "+
					" AS KRONOLOGI, TO_NUMBER('5') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '1') AS TARIKH  "+
					" FROM DUAL ";
			  sql +=" UNION     ";
			  //ulasan teknikal jpph
			  sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_SURAT FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') IS NOT NULL "+
					" THEN    'Permohonan maklumat perancangan kepada JPPH. '     "+                       
					" ELSE ''  "+
					" END ) "+
					" AS KRONOLOGI, TO_NUMBER('6') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') AS TARIKH  "+
					" FROM DUAL "+
					" UNION ";
			  //terima ulasan teknikal jpph
			  sql +=" SELECT  (CASE WHEN (SELECT DISTINCT TARIKH_AKHIR FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') IS NOT NULL "+
					" THEN    'Menerima maklumbalas dari JPPH. ' "+                           
					" ELSE ''  "+
					" END )         "+
					" AS KRONOLOGI, TO_NUMBER('7') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN  "+
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AND FLAG_JENIS_ULASAN = '2') AS TARIKH  "+
					" FROM DUAL "+
					" UNION ";
			  //laporan tanah terperinci
			  sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_MULA_LAWAT FROM TBLPPTTANAH WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL "+
					" THEN    'Lawatan tapak dilakukan untuk penyediaan laporan terperinci tanah. '      "+                       
					" ELSE ''  "+ 
					" END )    "+                          
					" AS KRONOLOGI, TO_NUMBER('8') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_MULA_LAWAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTTANAH WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
					" FROM DUAL " +
					" UNION ";
			  //jabatan teknikal
			  sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_SURAT AS TARIKH FROM " +
					" (SELECT TO_DATE(TO_CHAR(A.TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_SURAT, " +
					" (CASE WHEN A.TARIKH_SURAT IS NOT NULL " +
					" THEN    'Permohonan maklumat Jabatan Teknikal ' ||    " +                 
					" (CASE WHEN B.NAMA_JABATAN IS NOT NULL THEN 'kepada ' || UPPER(B.NAMA_JABATAN) || ''  " +
					" ELSE '' " +
					" END)  " +
					" ||'. '  " +                          
					" ELSE ''  " +
					" END ) " +
					" AS KRONOLOGI, " +
					" TO_NUMBER('9') AS TURUTAN " +
					" FROM TBLPPTULASANTEKNIKAL A, TBLPPTJABATANTEKNIKAL B " +
					" WHERE A.ID_JABATANTEKNIKAL = B.ID_JABATANTEKNIKAL(+) " +
					" AND NVL(A.FLAG_JENIS_ULASAN,0) NOT IN (1,2) " +
					" AND A.ID_PERMOHONAN IN  " +
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
					" ORDER BY A.TARIKH_SURAT ASC) " ;
		      sql +=" UNION ";
		      //terima jabatan teknikal
			  sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_SURAT AS TARIKH FROM " +
					" (SELECT TO_DATE(TO_CHAR(A.TARIKH_TERIMAJT,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_SURAT, " +
					" (CASE WHEN A.TARIKH_TERIMAJT IS NOT NULL " +
					" THEN    'Menerima maklumbalas ' ||   " +                  
					" (CASE WHEN B.NAMA_JABATAN IS NOT NULL THEN 'dari ' || UPPER(B.NAMA_JABATAN) || '' " +
					" ELSE '' " +
					" END)  " +
					" ||'. '     " +                       
					" ELSE ''  " +
					" END ) || " +
					" (CASE WHEN A.KEPUTUSANJT = '1' THEN    'Keputusan maklumbalas jabatan teknikal adalah disokong. ' " +
					" WHEN A.KEPUTUSANJT = '2' THEN    'Keputusan maklumbalas jabatan teknikal adalah tidak disokong. '  " +                         
					" ELSE ''  " +
					" END ) " +
					" AS KRONOLOGI, " +
					" TO_NUMBER('10') AS TURUTAN " +
					" FROM TBLPPTULASANTEKNIKAL A, TBLPPTJABATANTEKNIKAL B " +
					" WHERE A.ID_JABATANTEKNIKAL = B.ID_JABATANTEKNIKAL(+) " +
					" AND NVL(A.FLAG_JENIS_ULASAN,0) NOT IN (1,2) " +
					" AND A.ID_PERMOHONAN IN  " +
					" (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
					" ORDER BY A.TARIKH_SURAT ASC) " ;
		      sql +=" UNION ";
		      //KERTAS MMK
		      sql +=" SELECT (CASE WHEN (SELECT A.TARIKH_HANTAR FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL " +
		    		" THEN    'Kertas MMK/MB/KM/LC telah disediakan dan dihantar' || ' untuk mendapat kelulusan PBN. '    " +                        
		    		" ELSE ''  " +
		    		" END ) " +		    		               
		    		" AS KRONOLOGI, TO_NUMBER('11') AS TURUTAN, (SELECT TO_DATE(TO_CHAR(A.TARIKH_HANTAR,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_HANTAR FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH  " +
		    		" FROM DUAL   " ;                                    
		      sql +=" UNION ";
		      //terima keputusan pbn
		      sql +=" SELECT (CASE WHEN (SELECT A.TARIKH_MMK FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL " +
		    		" THEN    'Menerima keputusan dari PBN' ||  " +
		    		" ''  " +                          
		    		" ELSE ''  " +
		    		" END ) " +
		    		" || " +
		    		" (CASE WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 1  " +
		    		" THEN ' dan keputusanya adalah diluluskan.' " +
		    		" WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 2 " +
		    		" THEN ' dan keputusanya adalah ditolak.' " +
		    		" WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 3 " +
		    		" THEN ' dan keputusanya adalah ditangguh.'  " +                          
		    		" ELSE '.'  " +
		    		" END )          " +                  
		    		" AS KRONOLOGI, TO_NUMBER('12') AS TURUTAN, (SELECT TO_DATE(TO_CHAR(A.TARIKH_MMK,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_MMK FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  " +
		    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH  " +
		    		" FROM DUAL   " ;                                    
		      sql +=" UNION ";
		      //warta
		      sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_WARTA FROM TBLPPTWARTA " + 
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) " +
		    		" AND TARIKH_WARTA IS NOT NULL " +
		    		" AND ROWNUM = 1) IS NOT NULL  " +
		    		" THEN    'Warta dikeluarkan. '                        " +
		    		" ELSE ''   " +
		    		" END )  ||     " +
		    		" (CASE WHEN (SELECT DISTINCT NO_WARTA FROM TBLPPTWARTA  " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) " +
		    		" AND TARIKH_WARTA IS NOT NULL " +
		    		" AND ROWNUM = 1) IS NOT NULL  " +
		    		" THEN    'No Warta adalah ' ||   " +
		    		" (SELECT DISTINCT NO_WARTA FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) " +
		    		" AND TARIKH_WARTA IS NOT NULL " +
		    		" AND ROWNUM = 1) || '. '                        " +
		    		" ELSE ''   " +
		    		" END )                       " +
		    		" AS KRONOLOGI, TO_NUMBER('13') AS TURUTAN,  (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_WARTA,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_WARTA FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') " +
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA " +
		    		" WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) " +
		    		" AND TARIKH_WARTA IS NOT NULL " +
		    		" AND ROWNUM = 1)  AS TARIKH   " +
		    		" FROM DUAL ";	
		      sql +=" UNION ";
		      //borang e
		      sql +=" SELECT (CASE WHEN (SELECT DISTINCT B.TARIKH_BORANGE FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		      		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL " +
		    		" THEN    'Borang E dikeluarkan. '   " +                         
		    		" ELSE ''  " +
		    		" END ) " +
		    		" || " +
		    		" (CASE WHEN (SELECT DISTINCT B.TARIKH_AKHIR_TAMPAL FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL " +
		    		" THEN    'Tarikh akhir tampal adalah pada ' || " + 
		    		" (SELECT DISTINCT TO_CHAR(B.TARIKH_AKHIR_TAMPAL,'DD/MM/YYYY') AS TARIKH_AKHIR_TAMPAL FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '   " +                         
		    		" ELSE '' " + 
		    		" END ) " +
		    		" || " +
		    		" (CASE WHEN (SELECT DISTINCT B.TARIKH_SIASATAN FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL " +
		    		" THEN    'Tarikh ditetapkan pada ' ||  " +
		    		" (SELECT DISTINCT TO_CHAR(B.TARIKH_SIASATAN,'DD/MM/YYYY') AS TARIKH_SIASATAN FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '       " +                     
		    		" ELSE ''  " +
		    		" END )     " +     
		    		" AS KRONOLOGI, TO_NUMBER('14') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(B.TARIKH_BORANGE,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGE FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH  " +
		    		" FROM DUAL  " ;      
		      sql +=" UNION ";
               //borang F
		      sql +=" SELECT (CASE WHEN (SELECT DISTINCT B.TARIKH_BORANGF FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL " +
		    		" THEN    'Borang F dikeluarkan. '           " +                 
		    		" ELSE ''  " +
		    		" END )        " +                 
		    		" AS KRONOLOGI, TO_NUMBER('15') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(B.TARIKH_BORANGE,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGE FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_BORANGE = B.ID_BORANGE AND A.ID_HAKMILIK = C.ID_HAKMILIK   AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH  " +
		    		" FROM DUAL		" ; 
		      sql +=" UNION ";
		      //notis awam
		      sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_TAMPAL AS TARIKH FROM ( " +
		    		" SELECT (CASE WHEN B.JENIS_TEMPAT_TAMPAL IS NOT NULL THEN 'Notis Awan telah ditampal di ' || " +
		    		" (CASE " +
		    		" WHEN B.JENIS_TEMPAT_TAMPAL = '1' THEN 'PTD / PTG.' " +
		    		" WHEN B.JENIS_TEMPAT_TAMPAL = '2' THEN 'PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR.' " +
		    		" WHEN B.JENIS_TEMPAT_TAMPAL = '3' THEN 'TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH.' " +
		    		" ELSE '' END) " +
		    		" ELSE '' END ) || " +
		    		" (CASE WHEN TEMPAT IS NOT NULL THEN ' (' || TEMPAT || ')' ELSE '' END )" +
		    		//" (CASE WHEN B.TARIKH_TAMPAL IS NOT NULL THEN ' pada ' || TO_CHAR(B.TARIKH_TAMPAL,'DD/MM/YYYY') ELSE '' END )  " +            
		    		" AS KRONOLOGI,TO_NUMBER('16') AS TURUTAN,TO_DATE(TO_CHAR(B.TARIKH_TAMPAL,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_TAMPAL " +
		    		" FROM TBLPPTNOTISAWAMHAKMILIK A, TBLPPTNOTISAWAM B, TBLPPTHAKMILIK C " +
		    		" WHERE A.ID_NOTISAWAM = B.ID_NOTISAWAM " +
		    		" AND A.ID_HAKMILIK = C.ID_HAKMILIK " +
		    		" AND C.ID_HAKMILIK = '"+ID_HAKMILIK+"' " +
		    		" AND B.TARIKH_TAMPAL IS NOT NULL)  " ;
		      sql +=" UNION ";
		      //penandaan kawasan
		      sql +=" SELECT (CASE WHEN (SELECT TARIKH_LAWAT FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL "+
		    		" THEN    'Penandaan Kawasan telah dilakukan. ' "+                           
		    		" ELSE '' "+ 
		    		" END )  "+
		    		" || "+
		    		" (CASE WHEN (SELECT TARIKH_MULA FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL AND "+
		    		" (SELECT TARIKH_AKHIR FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL  "+
		    		" THEN    'Proses penandaan bermula dari ' ||   "+
		    		" (SELECT TO_CHAR(TARIKH_MULA,'DD/MM/YYYY') AS TARIKH_LAWAT FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) || ' sehingga '   "+
		    		" || (SELECT TO_CHAR(TARIKH_AKHIR,'DD/MM/YYYY') AS TARIKH_LAWAT FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"')) || '. '    "+                       
		    		" ELSE ''   "+
		    		" END )      "+                        
		    		" AS KRONOLOGI, TO_NUMBER('17') AS TURUTAN, (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_MULA_LAWAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTTANAH WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
		    		" FROM DUAL ";
		      sql +=" UNION ";
		      //siasatan
		      sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_SIASATAN AS TARIKH FROM ( "+
		    		" SELECT (CASE WHEN S.TARIKH_SIASATAN IS NOT NULL THEN 'Urusan siasatan (' "+
		    		" || "+
		    		" (CASE WHEN S.NO_KES IS NOT NULL THEN 'No. Kes : ' || S.NO_KES || ', ' ELSE '' END) "+ 
		    		" || "+
		    		" (CASE WHEN S.BIL_SIASATAN IS NOT NULL THEN 'Bil. Siasatan : ' || S.BIL_SIASATAN || '' ELSE '' END ) "+                 
		    		" || "+                  
		    		" ') telah dilakukan. ' "+
		    		" ELSE '' END ) ||  "+                 
		    		" (CASE WHEN S.TEMPAT IS NOT NULL THEN 'di ' || TEMPAT || ' ' ELSE '' END ) "+
		    		" || "+
		    		//" (CASE WHEN S.TARIKH_SIASATAN IS NOT NULL THEN 'pada ' || TO_CHAR(S.TARIKH_SIASATAN,'DD/MM/YYYY') || '. ' ELSE '' END ) "+
		    		//" || "+
		    		" (CASE WHEN S.STATUS_SIASATAN IS NOT NULL THEN  "+
		    		" 'Status semasa siasatan adalah ' || "+
		    		" CASE WHEN S.STATUS_SIASATAN = '1'  THEN 'Siasatan Permulaan. '  "+
		    		" WHEN S.STATUS_SIASATAN = '2'  THEN 'Ditunda. ' "+ 
		    		" WHEN S.STATUS_SIASATAN = '3'  THEN 'Dibatalkan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '4'  THEN 'Siasatan Ulangan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '5'  THEN 'Sambung Siasatan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '6'  THEN 'Selesai. ' "+
		    		" WHEN S.STATUS_SIASATAN = '7'  THEN 'Tangguh. ' "+
		    		" ELSE '' END  "+
		    		" ELSE '' END )  "+
		    		" || "+
		    		" CASE WHEN (SELECT NVL(SUM(BAYAR_PAMPASAN),0) FROM TBLPPTAWARD WHERE ID_SIASATAN =  "+
		    		" (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6' AND ID_PENARIKANBALIK IS NULL)) > 0  "+
		    		" THEN 'Sejumlah pampasan sebanyak RM ' || "+
		    		" (SELECT TRIM(TO_CHAR(SUM(BAYAR_PAMPASAN),'999,999,999,990.99')) AS BAYAR_PAMPASAN FROM TBLPPTAWARD WHERE ID_SIASATAN = "+ 
		    		" (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6' AND ID_PENARIKANBALIK IS NULL)) "+
		    		" ELSE '' END || ' ' || 'telah diawardkan kepada pihak-pihak perkepentingan yang terlibat.' "+                  
		    		" AS KRONOLOGI,TO_NUMBER('18') AS TURUTAN,TO_DATE(TO_CHAR(S.TARIKH_SIASATAN,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_SIASATAN "+
		    		" FROM TBLPPTSIASATAN S WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND ID_PENARIKANBALIK IS NULL"+
		    		" AND S.TARIKH_SIASATAN IS NOT NULL) ";
		      sql +=" UNION ";
		      //borang g
		      sql +=" SELECT (CASE WHEN (SELECT G.TARIKH_BORANGG FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
		    		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) IS NOT NULL "+
		    		" THEN    'Borang G telah dikeluarkan. '  "+             
		    		" ELSE '' "+ 
		    		" END )   "+                          
		    		" AS KRONOLOGI, TO_NUMBER('19') AS TURUTAN, "+
		    		" (SELECT TO_DATE(TO_CHAR(G.TARIKH_BORANGG,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGG FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
		    		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) AS TARIKH  "+
		    		" FROM DUAL ";
	          sql +=" UNION ";
	          //borang h
	          sql +=" SELECT (CASE WHEN (SELECT G.TARIKH_BORANGH FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
	        		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) IS NOT NULL "+ 
	        		" THEN    'Borang H telah dikeluarkan. '    "+           
	        		" ELSE '' "+ 
	        		" END )    "+                         
	        		" AS KRONOLOGI, TO_NUMBER('20') AS TURUTAN, "+
	        		" (SELECT TO_DATE(TO_CHAR(G.TARIKH_BORANGH,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGH FROM TBLPPTBORANGG G WHERE G.ID_SIASATAN "+
	        		" IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6')) AS TARIKH  "+
	        		" FROM DUAL  ";
	          sql +=" UNION ";
	          //borang k
	          sql +=" SELECT (CASE WHEN (SELECT K.TARIKH_BORANGK FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
		            " WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
		            " AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
		            " THEN    'Borang K telah dikeluarkan. '   "+             
		            " ELSE ''   "+
		            " END )  "+                            
		            " AS KRONOLOGI, TO_NUMBER('21') AS TURUTAN,  "+
		            " (SELECT TO_DATE(TO_CHAR(K.TARIKH_BORANGK,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGK FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
		            " WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
		            " AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH  "+ 
		            " FROM DUAL ";
              sql +=" UNION ";
              //borang L
              sql +=" SELECT (CASE WHEN (SELECT TARIKH_BORANGL FROM TBLPPTBORANGL WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Borang L telah dikeluarkan. '  "+              
            		" ELSE ''   "+
            		" END )  "+                            
            		" AS KRONOLOGI, TO_NUMBER('22') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(TARIKH_BORANGL,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_BORANGL FROM TBLPPTBORANGL WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
              //hantar endorsan borang k
              sql +=" SELECT (CASE WHEN (SELECT H.TARIKH_CATATAN FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Maklumat Endorsan Borang K dihantar. '     "+           
            		" ELSE ''   "+
            		" END )   "+                           
            		" AS KRONOLOGI, TO_NUMBER('23') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(H.TARIKH_CATATAN,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_CATATAN FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
              //terima endorsan borang k
              sql +=" SELECT (CASE WHEN (SELECT H.TARIKH_TERIMA FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Endorsan Borang K diterima. '   "+             
            		" ELSE ''   "+
            		" END )    "+                          
            		" AS KRONOLOGI, TO_NUMBER('24') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(H.TARIKH_TERIMA,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_TERIMA FROM TBLPPTENDOSANBORANGK H,TBLPPTBORANGK K,TBLPPTHAKMILIKBORANGK HK "+
            		" WHERE H.ID_BORANGK = K.ID_BORANGK AND HK.ID_BORANGK = K.ID_BORANGK "+
            		" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
              //BORANG I         
              sql +=" SELECT (CASE WHEN (((SELECT DISTINCT P.FLAG_SEGERA FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND H.ID_PERMOHONAN = P.ID_PERMOHONAN) = '1') "+
            		" OR ((SELECT H.FLAG_SEGERA_SEBAHAGIAN FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P WHERE H.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND H.ID_PERMOHONAN = P.ID_PERMOHONAN) = 'Y')) AND  "+
            		" (SELECT DISTINCT A.TARIKH_BORANGI FROM TBLPPTBORANGI A,  TBLPPTPERMOHONAN C  "+
            		" WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"'))  IS NOT NULL "+
            		" THEN    'Borang I telah dikeluarkan. '    "+            
            		" ELSE ''   "+
            		" END )     "+                        
            		"  AS KRONOLOGI, TO_NUMBER('25') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(TARIKH_BORANGI,'DD/MM/YYYY'),'DD/MM/YYYY')  FROM TBLPPTBORANGI A,  TBLPPTPERMOHONAN C  "+
            		" WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"'))   AS TARIKH   "+
            		" FROM DUAL  ";
                    sql +=" UNION ";
              //PU             
              sql +=" SELECT (CASE WHEN (SELECT DISTINCT B.TARIKH_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Permohonan Permintaan Ukur ' || "+
            		" (SELECT DISTINCT '(No. PU: ' || B.NO_PU || ')' FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"' AND B.NO_PU IS NOT NULL) "+
            		" || ' telah dilakukan. '   "+             
            		" ELSE ''   "+
            		" END )    "+
            		" ||   "+
            		" (CASE WHEN (SELECT DISTINCT B.LUAS_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Luas Pelan Akui adalah ' || "+
            		" (SELECT DISTINCT TRIM(TO_CHAR(B.LUAS_PU,'999999999990.9999')) || ' Hektar' AS LUAS_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. ' "+               
            		" ELSE ''  "+ 
            		" END )  "+
            		" ||   "+
            		" (CASE WHEN (SELECT DISTINCT B.BEZA_LUAS FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Perbezaan luas adalah ' || "+
            		" (SELECT DISTINCT TRIM(TO_CHAR(B.BEZA_LUAS,'999999999990.9999')) || ' Hektar' AS LUAS_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B "+ 
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') || '. '     "+           
            		" ELSE ''   "+
            		" END )    "+                       
            		" AS KRONOLOGI, TO_NUMBER('26') AS TURUTAN,  "+
            		" (SELECT DISTINCT TO_DATE(TO_CHAR(B.TARIKH_PU,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_PU FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B  "+
            		" WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
              //PENARIKAN BALIK DAFTAR            
              sql +=" SELECT (CASE WHEN (SELECT PB.TARIKH_SURAT FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Permohonan untuk Penarikan Balik bagi hakmilik ini dibuat. '      "+          
            		" ELSE ''   "+
            		" END )  "+
            		" || "+
            		" (CASE WHEN (SELECT PB.NO_RUJUKAN_SURAT FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'No. Rujukan Surat permohonan penarikan balik adalah ' ||                 "+    
            		" (SELECT PB.NO_RUJUKAN_SURAT FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')    "+           
            		" || '. '  "+              
            		" ELSE ''  "+ 
            		" END )    "+                          
            		" AS KRONOLOGI, TO_NUMBER('27') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(PB.TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";       
              //PENARIKAN BALIK LAPOPTAN TANAH              
              sql +=" SELECT (CASE WHEN (SELECT TARIKH_PEMERIKSAAN FROM TBLPPTTANAHUMUM T "+
            		" WHERE T.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
            		" THEN    'Lawatan tapak dilakukan bagi pemeriksaan hakmilik yang ditarik balik. '                "+
            		" ELSE ''   "+
            		" END )               "+                                   
            		" AS KRONOLOGI, TO_NUMBER('28') AS TURUTAN,  "+
            		" (SELECT TO_DATE(TO_CHAR(PB.TARIKH_SURAT,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') AS TARIKH   "+
            		" FROM DUAL ";
              sql +=" UNION ";
		      //penarikan balik KERTAS MMK             
              sql +=" SELECT (CASE WHEN (SELECT C.TARIKH_HANTAR FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
            		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL  "+
            		" THEN    'Kertas MMK/MB/KM/LC bagi urusan penarikan balik telah disediakan untuk mendapat kelulusan PBN. '      "+                       
            		" ELSE ''  "+ 
            		" END ) 	 "+	    		               
            		" AS KRONOLOGI, TO_NUMBER('29') AS TURUTAN, (SELECT TO_DATE(TO_CHAR(C.TARIKH_HANTAR,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_HANTAR FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C  "+ 
            		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
            		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH   "+
            		" FROM DUAL  ";                                                        
		      sql +=" UNION ";
		      //terima keputusan pbn penarikan balik        
		      sql +=" SELECT (CASE WHEN (SELECT C.TARIKH_TERIMA FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
			    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND  A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
			    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) IS NOT NULL  "+
			    		" THEN    'Menerima keputusan dari PBN bagi urusan Penarikan Balik'  "+			    		                  
			    		" ELSE ''   "+
			    		" END )  "+
			    		" ||  "+
			    		" (CASE WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
			    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND   A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
			    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 1   "+
			    		" THEN ' dan keputusanya adalah diluluskan.'  "+
			    		" WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
			    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND   A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
			    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 2  "+
			    		" THEN ' dan keputusanya adalah ditolak.'  "+
			    		" WHEN (SELECT C.STATUS_KEPUTUSAN FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
			    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND   A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
			    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) = 3  "+
			    		" THEN ' dan keputusanya adalah ditangguh.'       "+                      
			    		" ELSE '.'   "+
			    		" END )         "+                    
			    		" AS KRONOLOGI, TO_NUMBER('30') AS TURUTAN, (SELECT TO_DATE(TO_CHAR(C.TARIKH_TERIMA,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_MMK FROM TBLPPTMMK A, TBLPPTMMKKEPUTUSAN C   "+
			    		" WHERE  C.ID_MMK(+) = A.ID_MMK AND   A.ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
			    		"  WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')) AS TARIKH   "+
			    		" FROM DUAL  ";
		      sql +=" UNION ";    
                    //warta penarikan balik
		      sql +=" SELECT (CASE WHEN (SELECT DISTINCT TARIKH_WARTA FROM TBLPPTWARTA   "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')  "+
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+
		    		" AND TARIKH_WARTA IS NOT NULL  "+
		    		" AND ROWNUM = 1) IS NOT NULL   "+
		    		" THEN    'Warta untuk permohonan penarikan balik dikeluarkan. '        "+                 
		    		" ELSE ''    "+
		    		" END )  ||      "+
		    		" (CASE WHEN (SELECT DISTINCT NO_WARTA FROM TBLPPTWARTA   "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')  "+
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+
		    		" AND TARIKH_WARTA IS NOT NULL  "+
		    		" AND ROWNUM = 1) IS NOT NULL   "+
		    		" THEN    'No Warta adalah ' ||   "+ 
		    		" (SELECT DISTINCT NO_WARTA FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')  "+
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+
		    		" AND TARIKH_WARTA IS NOT NULL  "+
		    		" AND ROWNUM = 1) || '. '          "+               
		    		" ELSE ''    "+
		    		" END )           "+             
		    		" AS KRONOLOGI, TO_NUMBER('31') AS TURUTAN,  (SELECT DISTINCT TO_DATE(TO_CHAR(TARIKH_WARTA,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_WARTA FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')  "+
		    		" AND TARIKH_WARTA =  (SELECT MAX(TARIKH_WARTA)FROM TBLPPTWARTA  "+
		    		" WHERE ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))  "+
		    		" AND TARIKH_WARTA IS NOT NULL  "+
		    		" AND ROWNUM = 1)  AS TARIKH    "+
		    		" FROM DUAL ";
		      sql +=" UNION ";
               //siasatan penarikan balik
		      sql +=" SELECT KRONOLOGI,TURUTAN,TARIKH_SIASATAN AS TARIKH FROM ( "+
		    		" SELECT (CASE WHEN S.TARIKH_SIASATAN IS NOT NULL THEN 'Urusan siasatan bagi hakmilik yang ditarik balik (' "+
		    		" || "+
		    		" (CASE WHEN S.NO_KES IS NOT NULL THEN 'No. Kes : ' || S.NO_KES || ', ' ELSE '' END)  "+
		    		" || "+
		    		" (CASE WHEN S.BIL_SIASATAN IS NOT NULL THEN 'Bil. Siasatan : ' || S.BIL_SIASATAN || '' ELSE '' END )         "+         
		    		" ||    "+               
		    		" ') telah dilakukan. ' "+
		    		" ELSE '' END ) ||     "+              
		    		" (CASE WHEN S.TEMPAT IS NOT NULL THEN 'di ' || TEMPAT || ' ' ELSE '' END ) "+
		    		" || "+
		    		//" (CASE WHEN S.TARIKH_SIASATAN IS NOT NULL THEN 'pada ' || TO_CHAR(S.TARIKH_SIASATAN,'DD/MM/YYYY') || '. ' ELSE '' END ) "+
		    		//" || "+
		    		" (CASE WHEN S.STATUS_SIASATAN IS NOT NULL THEN  "+
		    		" 'Status semasa siasatan adalah ' || "+
		    		" CASE WHEN S.STATUS_SIASATAN = '1'  THEN 'Siasatan Permulaan. '  "+
		    		" WHEN S.STATUS_SIASATAN = '2'  THEN 'Ditunda. '  "+
		    		" WHEN S.STATUS_SIASATAN = '3'  THEN 'Dibatalkan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '4'  THEN 'Siasatan Ulangan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '5'  THEN 'Sambung Siasatan. ' "+
		    		" WHEN S.STATUS_SIASATAN = '6'  THEN 'Selesai. ' "+
		    		" WHEN S.STATUS_SIASATAN = '7'  THEN 'Tangguh. ' "+
		    		" ELSE '' END  "+
		    		" ELSE '' END )  "+
		    		" || "+
		    		" CASE WHEN (SELECT NVL(SUM(BAYAR_PAMPASAN),0) FROM TBLPPTAWARD WHERE ID_SIASATAN =  "+
		    		" (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6' AND ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB  "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"'))) > 0  "+
		    		" THEN 'Sejumlah pampasan sebanyak RM ' || "+
		    		" (SELECT TRIM(TO_CHAR(SUM(BAYAR_PAMPASAN),'999,999,999,990.99')) AS BAYAR_PAMPASAN FROM TBLPPTAWARD WHERE ID_SIASATAN =  "+
		    		" (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' AND STATUS_SIASATAN = '6' AND ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') )  "+
		    		" ) "+
		    		" ELSE '' END || ' ' || 'telah diawardkan kepada pihak-pihak perkepentingan yang terlibat.'  "+                 
		    		" AS KRONOLOGI,TO_NUMBER('32') AS TURUTAN,TO_DATE(TO_CHAR(S.TARIKH_SIASATAN,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_SIASATAN "+
		    		" FROM TBLPPTSIASATAN S WHERE ID_HAKMILIK = '"+ID_HAKMILIK+"' "+
		    		" AND ID_PENARIKANBALIK IN (SELECT PB.ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK PH, TBLPPTPENARIKANBALIK PB "+
		    		" WHERE PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') "+
		    		" AND S.TARIKH_SIASATAN IS NOT NULL) ";
		      sql +=" UNION ";              
              //PEMBATALAN DAFTAR             
		      sql +=" SELECT (CASE WHEN (SELECT TARIKH_PEMBATALAN FROM TBLPPTPEMBATALANHAKMILIK PH, TBLPPTPEMBATALAN PB "+
		    		  " WHERE PH.ID_PEMBATALAN = PB.ID_PEMBATALAN AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"') IS NOT NULL  "+
		    		  " THEN    'Permohonan untuk Pembatalan bagi hakmilik ini telah dilakukan. '       "+         
		    		  " ELSE ''   "+
		    		  " END )         "+                    
		    		  " AS KRONOLOGI, TO_NUMBER('33') AS TURUTAN,  "+
		    		  " (SELECT TO_DATE(TO_CHAR(TARIKH_PEMBATALAN,'DD/MM/YYYY'),'DD/MM/YYYY')  FROM TBLPPTPEMBATALANHAKMILIK PH, TBLPPTPEMBATALAN PB "+
		    		  " WHERE PH.ID_PEMBATALAN = PB.ID_PEMBATALAN AND PH.ID_HAKMILIK = '"+ID_HAKMILIK+"')   AS TARIKH   "+
		    		  " FROM DUAL ";
			  sql +=" ORDER BY TURUTAN,TARIKH ";
			  sql +=" ) WHERE KRONOLOGI IS NOT NULL AND TARIKH IS NOT NULL ";
			
			
			myLogger.info("LIST KRONOLOGI :"+sql);
			//stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {				
				Hashtable h = new Hashtable();		
				if (rs.getString("TURUTAN") == null) {	h.put("TURUTAN", ""); } else { h.put("TURUTAN", rs.getString("TURUTAN").toUpperCase()); }				
				if (rs.getString("PEKARA") == null) {	h.put("PEKARA", ""); } else { h.put("PEKARA", rs.getString("PEKARA").toUpperCase()); }
				if (rs.getString("TARIKH") == null) {	h.put("TARIKH", ""); } else { h.put("TARIKH", rs.getString("TARIKH").toUpperCase()); }
				list_kronologi.addElement(h);
			}
			return list_kronologi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector list_hakmilik = null;
	public Vector getHakmilik(String ID_JENISHAKMILIK,String NO_HAKMILIK,String NO_FAIL,String NAMA_PROJEK,String NO_LOT_PT,String userIdNeg) throws Exception {
		list_hakmilik = new Vector();
		list_hakmilik.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			String sql_negeri ="";
			
			if(!userIdNeg.equals("16") && !userIdNeg.isEmpty()){
    			if(userIdNeg.equals("14")){
    				sql_negeri += "AND F.ID_NEGERI in (14,15,16) ";
    			}else{
    				sql_negeri += "AND F.ID_NEGERI ='"+userIdNeg+"'";
    			}		
    		}
			
			sql = "  SELECT H.ID_HAKMILIK,UPPER(P.TUJUAN) AS TUJUAN,H.NO_LOT,NO_PT,H.ID_JENISHAKMILIK,JH.KOD_JENIS_HAKMILIK," +
					" H.NO_HAKMILIK,H.ID_PERMOHONAN,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_PTD,P.NO_RUJUKAN_UPT,F.NO_FAIL "+
					"  FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLRUJJENISHAKMILIK JH,TBLPFDFAIL F "+
					"  WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND P.ID_PERMOHONAN = H.ID_PERMOHONAN "+
					"  AND F.ID_FAIL = P.ID_FAIL     "+sql_negeri+" ";
			
			if (NO_FAIL != null) {
				if (!NO_FAIL.trim().equals("")) {
						sql += " AND (UPPER(F.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase().trim() + "%' " +
						" OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%" + NO_FAIL.toUpperCase().trim() + "%' " +
						" OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%" + NO_FAIL.toUpperCase().trim() + "%' " +
						" OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%" + NO_FAIL.toUpperCase().trim() + "%')";
				}
			}
			
			if (ID_JENISHAKMILIK != null) {
				if (!ID_JENISHAKMILIK.trim().equals("")) {				
					sql += " AND H.ID_JENISHAKMILIK = '" + ID_JENISHAKMILIK.toUpperCase().trim() + "' ";
				}
			}

			if (NO_HAKMILIK != null) {
				if (!NO_HAKMILIK.trim().equals("")) {
					sql += "  AND UPPER(H.NO_HAKMILIK) LIKE '%" + NO_HAKMILIK.toUpperCase().trim() + "%' ";					
				}
			}			
			
			if (NAMA_PROJEK != null) {
				if (!NAMA_PROJEK.trim().equals("")) {
					sql = sql + " AND  lower(P.TUJUAN)  LIKE lower('%' ||'"+NAMA_PROJEK.trim()+"'|| '%')  ";					
				}
			}			
			
			if (NO_LOT_PT != null) {
		    	if (!NO_LOT_PT.equals("")) {
		    		sql += "  AND (UPPER(H.NO_LOT) LIKE '%" + NO_LOT_PT.toUpperCase().trim() + "%'  OR UPPER(H.NO_PT) LIKE '%" + NO_LOT_PT.toUpperCase().trim() + "%') ";  
			    }
		     }			
			
			sql +=  " AND ROWNUM <= 100 ORDER BY H.TARIKH_KEMASKINI DESC";
			
			myLogger.info("LIST HAKMILIK CARIAN :"+sql.toUpperCase());
			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {				
				Hashtable h = new Hashtable();		
				if (rs.getString("ID_HAKMILIK") == null) {	h.put("ID_HAKMILIK", ""); } else { h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK").toUpperCase()); }				
				if (rs.getString("TUJUAN") == null) {	h.put("TUJUAN", ""); } else { h.put("TUJUAN", rs.getString("TUJUAN").toUpperCase()); }
				if (rs.getString("NO_LOT") == null) {	h.put("NO_LOT", ""); } else { h.put("NO_LOT", rs.getString("NO_LOT").toUpperCase()); }
				if (rs.getString("NO_PT") == null) {	h.put("NO_PT", ""); } else { h.put("NO_PT", rs.getString("NO_PT").toUpperCase()); }
				if (rs.getString("ID_JENISHAKMILIK") == null) {	h.put("ID_JENISHAKMILIK", ""); } else { h.put("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK").toUpperCase()); }
				if (rs.getString("KOD_JENIS_HAKMILIK") == null) {	h.put("KOD_JENIS_HAKMILIK", ""); } else { h.put("KOD_JENIS_HAKMILIK", rs.getString("KOD_JENIS_HAKMILIK").toUpperCase()); }
				if (rs.getString("NO_HAKMILIK") == null) {	h.put("NO_HAKMILIK", ""); } else { h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK").toUpperCase()); }
				if (rs.getString("ID_PERMOHONAN") == null) {	h.put("ID_PERMOHONAN", ""); } else { h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN").toUpperCase()); }
				if (rs.getString("NO_RUJUKAN_PTG") == null) {	h.put("NO_RUJUKAN_PTG", ""); } else { h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG").toUpperCase()); }
				if (rs.getString("NO_RUJUKAN_UPT") == null) {	h.put("NO_RUJUKAN_UPT", ""); } else { h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT").toUpperCase()); }
				if (rs.getString("NO_RUJUKAN_PTD") == null) {	h.put("NO_RUJUKAN_PTD", ""); } else { h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD").toUpperCase()); }
				if (rs.getString("NO_FAIL") == null) {	h.put("NO_FAIL", ""); } else { h.put("NO_FAIL", rs.getString("NO_FAIL").toUpperCase()); }						
				list_hakmilik.addElement(h);
			}
			return list_hakmilik;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	Hashtable dataHakmilik = null;
	public Hashtable dataHakmilik(String ID_HAKMILIK) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "  "+			
					" SELECT DISTINCT HK.ID_HAKMILIK,HK.FLAG_SEGERA_SEBAHAGIAN, F.ID_FAIL,F.ID_SUBURUSAN, JHK.KOD_JENIS_HAKMILIK, "+
					" HK.ID_HAKMILIK, LS.ID_LUAS AS ID_UNITLUASLOT, "+
					" LAM.ID_LUAS AS ID_UNITLUASAMBIL, P.ID_PERMOHONAN, "+ 
					" JHK.ID_JENISHAKMILIK, N.ID_NEGERI, "+
					" HK.ID_LOT, D.ID_DAERAH, MK.ID_MUKIM, HK.LUAS_AMBIL, HK.NO_HAKMILIK, HK.NO_LOT, "+ 
					" HK.LUAS_LOT, HK.NO_PT, LS.KETERANGAN,  UPPER(JHK.KETERANGAN) AS JENIS_HAKMILIK, HK.CATATAN, HK.SEKSYEN, "+   
					" TO_CHAR(HK.TARIKH_DAFTAR,'DD/MM/YYYY') AS TARIKH_DAFTAR, TO_CHAR(HK.TARIKH_LUPUT,'DD/MM/YYYY') AS TARIKH_LUPUT, HK.TEMPOH_LUPUT,  "+
					" HK.LOKASI,HK.SYARAT_NYATA,HK.SYARAT_KHAS,HK.SEKATAN_HAK,HK.SEKATAN_KEPENTINGAN,HK.NO_SYIT,HK.ID_KATEGORITANAH,  "+
					" MK.NAMA_MUKIM, D.NAMA_DAERAH, N.NAMA_NEGERI, KT.KETERANGAN AS KATEGORI_TANAH, HK.ID_DAERAHPENGGAWA, " +
					" (SELECT UPPER(NAMA_DAERAHPENGGAWA) FROM TBLRUJDAERAHPENGGAWA PGG WHERE PGG.ID_DAERAHPENGGAWA = HK.ID_DAERAHPENGGAWA) AS DAERAHPENGGAWA, "+
					" HK.STATUS_BORANGL, TO_CHAR(HK.TARIKH_TERIMA_HM,'DD/MM/YYYY') AS TARIKH_TERIMA_HM, LT.KETERANGAN AS UNITQT,  "+
					" HK.FLAG_JENIS_RIZAB, HK.NAMA_LAIN_RIZAB, HK.NO_WARTA_RIZAB, HK.TARIKH_WARTA_RIZAB, HK.ID_PEGAWAI,  "+
					" HK.ID_UNITLUASLOT_CONVERT, HK.ID_UNITLUASAMBIL_CONVERT, HK.NAMA_LUAS_ASAL, HK.NAMA_LUAS_AMBIL,     "+        
					" CASE  "+
					" WHEN HK.NO_LOT IS NOT NULL AND HK.NO_PT IS NULL THEN HK.NO_LOT   "+
					" WHEN HK.NO_LOT IS NULL AND HK.NO_PT IS NULL THEN LT.KETERANGAN || HK.NO_PT  "+
					" WHEN HK.NO_LOT IS NULL AND HK.NO_PT IS NOT NULL THEN  LT.KETERANGAN || HK.NO_PT   "+
					" WHEN HK.NO_LOT IS NOT NULL AND HK.NO_PT IS NOT NULL THEN LT.KETERANGAN || HK.NO_PT || CHR(32) || CHR(40) || HK.NO_LOT || CHR(41)  "+
					" ELSE ''  "+
					" END AS NO_LOTPT,       "+     
					" CASE  "+
					" WHEN HK.FLAG_JENIS_RIZAB = '1' THEN 'KAWASAN RIZAB MELAYU'   "+
					" WHEN HK.FLAG_JENIS_RIZAB = '2' THEN 'KAWASAN ORANG ASLI'   "+
					" WHEN HK.FLAG_JENIS_RIZAB = '3' THEN 'RIZAB ORANG ASLI'   "+
					" WHEN HK.FLAG_JENIS_RIZAB = '4' THEN 'KAWASAN PENEMPATAN BERKELOMPOK'   "+
					" WHEN HK.FLAG_JENIS_RIZAB = '5' THEN 'LAIN-LAIN'   "+
					" ELSE ''  "+
					" END AS JENIS_RIZAB,       "+  
					" UPPER(P.TUJUAN) AS TUJUAN,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_PTD,P.NO_RUJUKAN_UPT,F.NO_FAIL, "+	
					" (SELECT UPPER(NAMA_AGENSI) FROM TBLRUJAGENSI A WHERE A.ID_AGENSI = P.ID_AGENSI) AS AGENSI, " +
					" (SELECT UPPER(NAMA_KEMENTERIAN) FROM TBLRUJKEMENTERIAN K WHERE K.ID_KEMENTERIAN = F.ID_KEMENTERIAN) AS KEMENTERIAN " +
					" FROM TBLPFDFAIL F,TBLPPTHAKMILIK HK, TBLRUJJENISHAKMILIK JHK, TBLRUJMUKIM MK, TBLRUJLUAS LS, TBLRUJDAERAH D,   "+
					" TBLRUJNEGERI N, TBLPPTPERMOHONAN P, TBLRUJKATEGORI KT, TBLRUJLUAS LAM, TBLRUJLOT LT  "+
					" WHERE N.ID_NEGERI(+) = HK.ID_NEGERI   "+
					" AND D.ID_DAERAH(+) = P.ID_DAERAH   "+
					" AND P.ID_FAIL = F.ID_FAIL(+) "+
					" AND LS.ID_LUAS(+) = HK.ID_UNITLUASLOT    "+
					" AND LAM.ID_LUAS(+) = HK.ID_UNITLUASAMBIL    "+
					" AND MK.ID_MUKIM(+) = HK.ID_MUKIM   "+
					" AND KT.ID_KATEGORI(+) = HK.ID_KATEGORITANAH   "+
					" AND HK.ID_LOT = LT.ID_LOT(+) "+
					" AND JHK.ID_JENISHAKMILIK(+) = HK.ID_JENISHAKMILIK  "+  
					" AND P.ID_PERMOHONAN = HK.ID_PERMOHONAN    "+
					" AND HK.ID_HAKMILIK = '"+ID_HAKMILIK+"'";
			
			myLogger.info("HASH dataHakmilik :"+sql);	
			ResultSet rs = stmt.executeQuery(sql);
			
			dataHakmilik = new Hashtable();
			
			while (rs.next()) {
				if (rs.getString("DAERAHPENGGAWA") == null) {dataHakmilik.put("DAERAHPENGGAWA", "");} else {dataHakmilik.put("DAERAHPENGGAWA", rs.getString("DAERAHPENGGAWA"));}
				if (rs.getString("KEMENTERIAN") == null) {dataHakmilik.put("KEMENTERIAN", "");} else {dataHakmilik.put("KEMENTERIAN", rs.getString("KEMENTERIAN"));}
				if (rs.getString("AGENSI") == null) {dataHakmilik.put("AGENSI", "");} else {dataHakmilik.put("AGENSI", rs.getString("AGENSI"));}
				if (rs.getString("TUJUAN") == null) {dataHakmilik.put("TUJUAN", "");} else {dataHakmilik.put("TUJUAN", rs.getString("TUJUAN"));}				
				if (rs.getString("NO_RUJUKAN_PTG") == null) {dataHakmilik.put("NO_RUJUKAN_PTG", "");} else {dataHakmilik.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG"));}				
				if (rs.getString("NO_RUJUKAN_PTD") == null) {dataHakmilik.put("NO_RUJUKAN_PTD", "");} else {dataHakmilik.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD"));}				
				if (rs.getString("NO_RUJUKAN_UPT") == null) {dataHakmilik.put("NO_RUJUKAN_UPT", "");} else {dataHakmilik.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT"));}				
				if (rs.getString("NO_FAIL") == null) {dataHakmilik.put("NO_FAIL", "");} else {dataHakmilik.put("NO_FAIL", rs.getString("NO_FAIL"));}					
				if (rs.getString("ID_HAKMILIK") == null) {dataHakmilik.put("ID_HAKMILIK", "");} else {dataHakmilik.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK"));}				
				if (rs.getString("FLAG_SEGERA_SEBAHAGIAN") == null) {dataHakmilik.put("FLAG_SEGERA_SEBAHAGIAN", "");} else {dataHakmilik.put("FLAG_SEGERA_SEBAHAGIAN", rs.getString("FLAG_SEGERA_SEBAHAGIAN"));}
				if (rs.getString("ID_FAIL") == null) {dataHakmilik.put("ID_FAIL", "");} else {dataHakmilik.put("ID_FAIL", rs.getString("ID_FAIL"));}
				if (rs.getString("ID_SUBURUSAN") == null) {dataHakmilik.put("ID_SUBURUSAN", "");} else {dataHakmilik.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN"));}
				if (rs.getString("KOD_JENIS_HAKMILIK") == null) {dataHakmilik.put("KOD_JENIS_HAKMILIK", "");} else {dataHakmilik.put("KOD_JENIS_HAKMILIK", rs.getString("KOD_JENIS_HAKMILIK"));}
				if (rs.getString("ID_HAKMILIK") == null) {dataHakmilik.put("ID_HAKMILIK", "");} else {dataHakmilik.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK"));}
				if (rs.getString("ID_UNITLUASLOT") == null) {dataHakmilik.put("ID_UNITLUASLOT", "");} else {dataHakmilik.put("ID_UNITLUASLOT", rs.getString("ID_UNITLUASLOT"));}
				if (rs.getString("ID_UNITLUASAMBIL") == null) {dataHakmilik.put("ID_UNITLUASAMBIL", "");} else {dataHakmilik.put("ID_UNITLUASAMBIL", rs.getString("ID_UNITLUASAMBIL"));}
				if (rs.getString("ID_PERMOHONAN") == null) {dataHakmilik.put("ID_PERMOHONAN", "");} else {dataHakmilik.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));}
				if (rs.getString("ID_JENISHAKMILIK") == null) {dataHakmilik.put("ID_JENISHAKMILIK", "");} else {dataHakmilik.put("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK"));}
				if (rs.getString("ID_NEGERI") == null) {dataHakmilik.put("ID_NEGERI", "");} else {dataHakmilik.put("ID_NEGERI", rs.getString("ID_NEGERI"));}
				if (rs.getString("ID_LOT") == null) {dataHakmilik.put("ID_LOT", "");} else {dataHakmilik.put("ID_LOT", rs.getString("ID_LOT"));}
				if (rs.getString("ID_DAERAH") == null) {dataHakmilik.put("ID_DAERAH", "");} else {dataHakmilik.put("ID_DAERAH", rs.getString("ID_DAERAH"));}
				if (rs.getString("ID_MUKIM") == null) {dataHakmilik.put("ID_MUKIM", "");} else {dataHakmilik.put("ID_MUKIM", rs.getString("ID_MUKIM"));}
				if (rs.getString("LUAS_AMBIL") == null) {dataHakmilik.put("LUAS_AMBIL", "");} else {dataHakmilik.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL"));}
				if (rs.getString("NO_HAKMILIK") == null) {dataHakmilik.put("NO_HAKMILIK", "");} else {dataHakmilik.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK"));}
				if (rs.getString("NO_LOT") == null) {dataHakmilik.put("NO_LOT", "");} else {dataHakmilik.put("NO_LOT", rs.getString("NO_LOT"));}
				if (rs.getString("LUAS_LOT") == null) {dataHakmilik.put("LUAS_LOT", "");} else {dataHakmilik.put("LUAS_LOT", rs.getString("LUAS_LOT"));}
				if (rs.getString("NO_PT") == null) {dataHakmilik.put("NO_PT", "");} else {dataHakmilik.put("NO_PT", rs.getString("NO_PT"));}
				if (rs.getString("KETERANGAN") == null) {dataHakmilik.put("KETERANGAN", "");} else {dataHakmilik.put("KETERANGAN", rs.getString("KETERANGAN"));}
				if (rs.getString("JENIS_HAKMILIK") == null) {dataHakmilik.put("JENIS_HAKMILIK", "");} else {dataHakmilik.put("JENIS_HAKMILIK", rs.getString("JENIS_HAKMILIK"));}
				if (rs.getString("CATATAN") == null) {dataHakmilik.put("CATATAN", "");} else {dataHakmilik.put("CATATAN", rs.getString("CATATAN"));}
				if (rs.getString("SEKSYEN") == null) {dataHakmilik.put("SEKSYEN", "");} else {dataHakmilik.put("SEKSYEN", rs.getString("SEKSYEN"));}
				if (rs.getString("TARIKH_DAFTAR") == null) {dataHakmilik.put("TARIKH_DAFTAR", "");} else {dataHakmilik.put("TARIKH_DAFTAR", rs.getString("TARIKH_DAFTAR"));}
				if (rs.getString("TARIKH_LUPUT") == null) {dataHakmilik.put("TARIKH_LUPUT", "");} else {dataHakmilik.put("TARIKH_LUPUT", rs.getString("TARIKH_LUPUT"));}
				if (rs.getString("TEMPOH_LUPUT") == null) {dataHakmilik.put("TEMPOH_LUPUT", "");} else {dataHakmilik.put("TEMPOH_LUPUT", rs.getString("TEMPOH_LUPUT"));}
				if (rs.getString("LOKASI") == null) {dataHakmilik.put("LOKASI", "");} else {dataHakmilik.put("LOKASI", rs.getString("LOKASI"));}
				if (rs.getString("SYARAT_NYATA") == null) {dataHakmilik.put("SYARAT_NYATA", "");} else {dataHakmilik.put("SYARAT_NYATA", rs.getString("SYARAT_NYATA"));}
				if (rs.getString("SYARAT_KHAS") == null) {dataHakmilik.put("SYARAT_KHAS", "");} else {dataHakmilik.put("SYARAT_KHAS", rs.getString("SYARAT_KHAS"));}
				if (rs.getString("SEKATAN_HAK") == null) {dataHakmilik.put("SEKATAN_HAK", "");} else {dataHakmilik.put("SEKATAN_HAK", rs.getString("SEKATAN_HAK"));}
				if (rs.getString("SEKATAN_KEPENTINGAN") == null) {dataHakmilik.put("SEKATAN_KEPENTINGAN", "");} else {dataHakmilik.put("SEKATAN_KEPENTINGAN", rs.getString("SEKATAN_KEPENTINGAN"));}
				if (rs.getString("NO_SYIT") == null) {dataHakmilik.put("NO_SYIT", "");} else {dataHakmilik.put("NO_SYIT", rs.getString("NO_SYIT"));}
				if (rs.getString("ID_KATEGORITANAH") == null) {dataHakmilik.put("ID_KATEGORITANAH", "");} else {dataHakmilik.put("ID_KATEGORITANAH", rs.getString("ID_KATEGORITANAH"));}
				if (rs.getString("NAMA_MUKIM") == null) {dataHakmilik.put("NAMA_MUKIM", "");} else {dataHakmilik.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));}
				if (rs.getString("NAMA_DAERAH") == null) {dataHakmilik.put("NAMA_DAERAH", "");} else {dataHakmilik.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));}
				if (rs.getString("NAMA_NEGERI") == null) {dataHakmilik.put("NAMA_NEGERI", "");} else {dataHakmilik.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));}
				if (rs.getString("KATEGORI_TANAH") == null) {dataHakmilik.put("KATEGORI_TANAH", "");} else {dataHakmilik.put("KATEGORI_TANAH", rs.getString("KATEGORI_TANAH"));}
				if (rs.getString("ID_DAERAHPENGGAWA") == null) {dataHakmilik.put("ID_DAERAHPENGGAWA", "");} else {dataHakmilik.put("ID_DAERAHPENGGAWA", rs.getString("ID_DAERAHPENGGAWA"));}
				if (rs.getString("STATUS_BORANGL") == null) {dataHakmilik.put("STATUS_BORANGL", "");} else {dataHakmilik.put("STATUS_BORANGL", rs.getString("STATUS_BORANGL"));}
				if (rs.getString("TARIKH_TERIMA_HM") == null) {dataHakmilik.put("TARIKH_TERIMA_HM", "");} else {dataHakmilik.put("TARIKH_TERIMA_HM", rs.getString("TARIKH_TERIMA_HM"));}
				if (rs.getString("UNITQT") == null) {dataHakmilik.put("UNITQT", "");} else {dataHakmilik.put("UNITQT", rs.getString("UNITQT"));}
				if (rs.getString("FLAG_JENIS_RIZAB") == null) {dataHakmilik.put("FLAG_JENIS_RIZAB", "");} else {dataHakmilik.put("FLAG_JENIS_RIZAB", rs.getString("FLAG_JENIS_RIZAB"));}
				if (rs.getString("JENIS_RIZAB") == null) {dataHakmilik.put("JENIS_RIZAB", "");} else {dataHakmilik.put("JENIS_RIZAB", rs.getString("JENIS_RIZAB"));}
				if (rs.getString("NAMA_LAIN_RIZAB") == null) {dataHakmilik.put("NAMA_LAIN_RIZAB", "");} else {dataHakmilik.put("NAMA_LAIN_RIZAB", rs.getString("NAMA_LAIN_RIZAB"));}
				if (rs.getString("NO_WARTA_RIZAB") == null) {dataHakmilik.put("NO_WARTA_RIZAB", "");} else {dataHakmilik.put("NO_WARTA_RIZAB", rs.getString("NO_WARTA_RIZAB"));}
				if (rs.getString("TARIKH_WARTA_RIZAB") == null) {dataHakmilik.put("TARIKH_WARTA_RIZAB", "");} else {dataHakmilik.put("TARIKH_WARTA_RIZAB", rs.getString("TARIKH_WARTA_RIZAB"));}
				if (rs.getString("ID_PEGAWAI") == null) {dataHakmilik.put("ID_PEGAWAI", "");} else {dataHakmilik.put("ID_PEGAWAI", rs.getString("ID_PEGAWAI"));}
				if (rs.getString("ID_UNITLUASLOT_CONVERT") == null) {dataHakmilik.put("ID_UNITLUASLOT_CONVERT", "");} else {dataHakmilik.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT"));}
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT") == null) {dataHakmilik.put("ID_UNITLUASAMBIL_CONVERT", "");} else {dataHakmilik.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT"));}
				if (rs.getString("NAMA_LUAS_ASAL") == null) {dataHakmilik.put("NAMA_LUAS_ASAL", "");} else {dataHakmilik.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL"));}
				if (rs.getString("NAMA_LUAS_AMBIL") == null) {dataHakmilik.put("NAMA_LUAS_AMBIL", "");} else {dataHakmilik.put("NAMA_LUAS_AMBIL", rs.getString("NAMA_LUAS_AMBIL"));}
				if (rs.getString("NO_LOTPT") == null) {dataHakmilik.put("NO_LOTPT", "");} else {dataHakmilik.put("NO_LOTPT", rs.getString("NO_LOTPT"));}	
				
			}
			return dataHakmilik;
		} finally {
			if (db != null)
				db.close();
		}
	
	}
	
	
}
