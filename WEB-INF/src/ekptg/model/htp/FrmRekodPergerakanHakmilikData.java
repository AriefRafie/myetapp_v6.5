package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmRekodPergerakanHakmilikData {
	
	private static Logger log = Logger.getLogger(FrmRekodPergerakanHakmilikData.class);
	private static Vector listMaklumatFail = null;
	private static Vector listSenaraiFail = null;		
	private static Vector listPergerakanHakmilik = null;
	private static Vector listCarianHakmilikDanRizab = null;
	private static Vector listPergerakanById = null;
	
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	public static Vector getCarianSenaraiHakmilikRizabById(String idJenisTanah,String idNegeri, String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi)throws Exception {
	    Db db = null;
	    listCarianHakmilikDanRizab = new Vector();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
    
	      sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
          		"CASE "+
          		"WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
          		"ELSE E.KOD_JENIS_HAKMILIK "+
          		"END AS JENIS_HAKMILIK, A.NO_HAKMILIK, A.NO_WARTA, "+
          		"H.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN, "+
          		"CASE "+
          		"WHEN A.STATUS_SAH = 'H' "+
          		"THEN 'HAKMILIK ASAL TIADA' "+
          		"WHEN A.STATUS_SAH = 'D' "+
          		"THEN 'DAFTAR' "+
          		"WHEN A.STATUS_SAH = 'P' "+
          		"THEN 'BATAL (PELEPASAN)' "+
          		"WHEN A.STATUS_SAH = 'T' "+
             	"THEN 'TUKAR GUNA' "+ 
             	"WHEN A.STATUS_SAH = 'S' "+
             	"THEN 'BATAL (SAMBUNGAN)' "+
	            "WHEN A.STATUS_SAH = 'B' "+
	                "THEN 'BATAL' "+
	             "WHEN A.STATUS_SAH = 'M' "+
		         	"THEN 'BATAL (PINDAHMILIK)' "+
             	"ELSE 'TIADA' "+
             	"END AS STATUS_HAKMILIK, "+
             	"A.STATUS_RIZAB, A.TARIKH_TERIMA,A.KEGUNAAN_TANAH "+
             	"FROM TBLHTPHAKMILIK A, "+
             	"TBLPERMOHONAN B, "+
             	"TBLPFDFAIL C, "+
             	"TBLRUJURUSAN D, "+
             	"TBLRUJJENISHAKMILIK E, "+
             	"TBLRUJLOT F, "+
             	"TBLRUJLOT H, "+
             	"TBLHTPHAKMILIKPERIHAL I, "+
             	"TBLHTPHAKMILIKAGENSI J "+
             	"WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
             	"AND B.ID_FAIL = C.ID_FAIL "+
             	"AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
             	"AND A.ID_LOT = F.ID_LOT "+
             	"AND C.ID_URUSAN = D.ID_URUSAN "+
             	"AND A.ID_LOT = H.ID_LOT "+
             	"AND A.ID_HAKMILIK = J.ID_HAKMILIK(+) "+
             	"AND A.ID_HAKMILIK = I.ID_HAKMILIK(+) "+
	      		//Azam removed
             	//Rosli removed 2010/05/28 
             	//"AND ( A.STATUS_RIZAB IS NULL OR A.STATUS_RIZAB=0 )";
	      		"";
	      //idJenisTanah
	      if (idJenisTanah != null) {
			if (!idJenisTanah.trim().equals("")) {
				if (Integer.parseInt(idJenisTanah) == 1){
					sql = sql + " AND A.NO_HAKMILIK IS NOT NULL AND A.STATUS_RIZAB IS NOT NULL ";
				} else if (Integer.parseInt(idJenisTanah) == 2){
					sql = sql + " AND A.NO_HAKMILIK IS NULL AND A.STATUS_RIZAB IS NULL ";
				}
			}
	      }
         
	      //id negeri
	      if (idNegeri != null) {
			if (!idNegeri.trim().equals("")) {
				if (!idNegeri.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeri + "'";
				}
			}
	      }
	      //id daerah
	      if (idDaerah != null) {
			if (!idDaerah.trim().equals("")) {
				if (!idDaerah.trim().equals("99999")) {
					sql = sql + " AND A.ID_DAERAH = '" + idDaerah + "'";
				}
			}
	      }
		//id mukim
		if (idMukim != null) {
			if (!idMukim.trim().equals("")) {
				if (!idMukim.trim().equals("99999")) {
					sql = sql + " AND A.ID_MUKIM = '" + idMukim + "'";
				}
			}
		}	      
		//no fail
		if (noFail != null) {
			if (!noFail.trim().equals("")) {
				sql = sql + " AND UPPER(C.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
			}
		}
		//id jenishakmilik
		if (idJenisHakmilik != null) {
			if (!idJenisHakmilik.trim().equals("")) {
				if (!idJenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND A.ID_JENISHAKMILIK = '" + idJenisHakmilik + "'";
				}
			}
		} 
		//no hakmilik
		if (noHakmilik != null) {
			if (!noHakmilik.trim().equals("")) {
				sql = sql + " AND UPPER(A.NO_HAKMILIK) LIKE '%' ||'" + noHakmilik.toUpperCase() + "'|| '%'";
			}
		}       
		//no warta
		if (noWarta != null) {
			if (!noWarta.trim().equals("")) {
				sql = sql + " AND UPPER(A.NO_WARTA) LIKE '%' ||'" + noWarta.toUpperCase() + "'|| '%'";
			}
		}   
		//id lot
		if (idLot != null) {
			if (!idLot.trim().equals("")) {
				if (!idLot.trim().equals("99999")) {
					sql = sql + " AND A.ID_LOT = '" + idLot + "'";
				}
			}
		} 
		//no lot
		if (noLot != null) {
			if (!noLot.trim().equals("")) {
				sql = sql + " AND UPPER(A.NO_LOT) LIKE '%' ||'" + noLot.toUpperCase() + "'|| '%'";
			}
		} 
		//no agensi
		if (idAgensi != null) {
			if (!idAgensi.trim().equals("")) {
				if (!idAgensi.trim().equals("99999")) {
					sql = sql + " AND J.ID_AGENSI = '" + idAgensi + "'";
				}
			}
		}
			sql = sql +" ORDER BY A.TARIKH_TERIMA DESC ";
			//log.info("carian "+sql);
			ResultSet rs = stmt.executeQuery(sql);
  
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				h.put("idHakmilik",rs.getString("ID_HAKMILIK"));
				h.put("noFail",rs.getString("NO_FAIL"));
				h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
				h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
				h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
				h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
				h.put("namaUrusan", rs.getString("NAMA_URUSAN")== null?"":rs.getString("NAMA_URUSAN"));
				h.put("statusSah", rs.getString("STATUS_HAKMILIK")== null?"":rs.getString("STATUS_HAKMILIK"));
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")== null?"":rs.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
				h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));

				listCarianHakmilikDanRizab.addElement(h);
				bil++;
				count++;
  	  
			}
			if (count == 0){
				h = new Hashtable();
				h.put("bil","");
				h.put("idHakmilik","");
				h.put("noFail","");
				h.put("kodJenis", "");
				h.put("noHakmilik", "Tiada Rekod.");
				h.put("noWarta", "");
				h.put("kodLot", "");
				h.put("noLot", "");
				h.put("namaUrusan", "");
				h.put("statusSah", "");
				h.put("kegunaanTanah", "");
				h.put("statusRizab", "");
				h.put("tarikhTerima","");
  	  
  	  		listCarianHakmilikDanRizab.addElement(h);
			}
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }		
	    return listCarianHakmilikDanRizab;
	}	
//	// PAPAR SENARAI FAIL HAKMILIK DAN RIZAB
//	public static Vector getPaparSenaraiHakmilikRizab() throws Exception {
//
//		Db db = null;
//		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		
//
//		try {
//			db = new Db();
//			listSenaraiFail = new Vector();
//			Statement stmt = db.getStatement();
//		
//			
//			sql = "SELECT A.ID_HAKMILIK,A.ID_NEGERI,A.ID_DAERAH,ID_MUKIM,A.NO_HAKMILIK, A.NO_LOT, D.NAMA_URUSAN,"+
//    	    		"CASE "+
//    	    		"WHEN A.STATUS_SAH='H' THEN 'HAKMILIK ASAL TIADA' "+
//    	    		"WHEN A.STATUS_SAH='D' THEN 'DAFTAR' "+
//    	    		"WHEN A.STATUS_SAH='B' THEN 'BATAL' "+
//    	    		"ELSE 'TIADA' "+
//    	    		"END AS STATUS_HAKMILIK, "+
//    	    		"A.STATUS_RIZAB, "+
//    	    		"A.TARIKH_TERIMA, "+
//    	    		"E.KOD_JENIS_HAKMILIK, "+
//    	    		"F.KETERANGAN, "+
//    	    		"A.PEGANGAN_HAKMILIK, "+
//    	    		"A.NO_WARTA "+
//    	    		"FROM "+
//    	    		"TBLHTPHAKMILIK A, "+
//    	    		"TBLPERMOHONAN B, "+
//    	    		"TBLPFDFAIL C, "+
//    	    		"TBLRUJURUSAN D ,"+
//    	    		"TBLRUJJENISHAKMILIK E, "+
//    	    		"TBLRUJLOT F "+
//    	    		"WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
//		          	"AND B.ID_FAIL = C.ID_FAIL "+
//		          	"AND C.ID_URUSAN = D.ID_URUSAN "+
//		          	"AND E.ID_JENISHAKMILIK = A.ID_JENISHAKMILIK "+
//		          	"AND F.ID_LOT = A.ID_LOT "+
//		          	"AND A.STATUS_RIZAB IS NOT NULL "+
//		          	//"AND A.STATUS_SAH <> 'B' "+
//					"ORDER BY A.TARIKH_TERIMA DESC";
//			
//			log.info("senarai pergerakan :" +sql);
//			ResultSet rs = stmt.executeQuery(sql);
//
//			Hashtable h;
//		    int bil = 1;
//		    int count = 0;
//			while (rs.next()) {
//				h = new Hashtable();
//				h.put("bil", bil);
//		    	h.put("idHakmilik",rs.getString("ID_HAKMILIK"));
//		    	h.put("peganganHakmilik",rs.getString("PEGANGAN_HAKMILIK"));
//		    	h.put("idNegeri",rs.getString("ID_NEGERI"));
//		    	h.put("idDaerah",rs.getString("ID_DAERAH"));
//		    	h.put("idMukim",rs.getString("ID_MUKIM")); 
//		    	h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
//		        h.put("kodJenis", rs.getString("KOD_JENIS_HAKMILIK")== null?"":rs.getString("KOD_JENIS_HAKMILIK"));
//		    	h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
//		    	h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
//		    	h.put("kodLot", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
//		    	h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
//		    	h.put("namaUrusan", rs.getString("NAMA_URUSAN")== null?"":rs.getString("NAMA_URUSAN"));
//		    	h.put("statusSah", rs.getString("STATUS_HAKMILIK")== null?"":rs.getString("STATUS_HAKMILIK"));
//		        h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));				
//				listSenaraiFail.addElement(h);
//		    	bil++;
//		    	count++;
//			} 	
//		    if (count == 0){
//		        h = new Hashtable();
//		    	h.put("bil","");
//		    	h.put("idHakmilik","");
//		    	h.put("peganganHakmilik","Tiada rekod.");
//		    	h.put("idNegeri", "");
//		    	h.put("idDaerah", "");
//		    	h.put("idMukim", "");
//		    	h.put("statusRizab","");
//		    	h.put("kodJenis", "");
//		    	h.put("noHakmilik", "");
//		    	h.put("noWarta","");
//		    	h.put("kodLot", "");
//		    	h.put("noLot", "");
//		        h.put("namaUrusan", "");
//		        h.put("statusSah", "");
//		    	h.put("tarikhTerima", "");
//
//		      listCarianHakmilikDanRizab.addElement(h);
//		   }			
//		} finally {
//			if (db != null)
//				db.close();
//		}
//		return listSenaraiFail;
//	}		
	// PAPAR MAKLUMAT FAIL (MASTER) BY ID
	public static Vector getPaparMaklumatFailById(int id) throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			listMaklumatFail = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLHTPHAKMILIK.ID_HAKMILIK, TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN,TBLRUJAGENSI.NAMA_AGENSI, "+
					"TBLRUJNEGERI.NAMA_NEGERI, TBLRUJDAERAH.NAMA_DAERAH, TBLRUJMUKIM.NAMA_MUKIM, "+
					"TBLHTPHAKMILIK.NO_FAIL_PTG, TBLPFDFAIL.NO_FAIL,TBLHTPHAKMILIK.NO_FAIL_KJP, "+
					"TBLPERMOHONAN.TUJUAN, TBLRUJJENISHAKMILIK.KETERANGAN, "+
					"TBLHTPHAKMILIK.NO_HAKMILIK, TBLHTPHAKMILIK.NO_LOT, TBLHTPHAKMILIK.NO_WARTA, "+
					"TBLRUJURUSAN.NAMA_URUSAN "+
					"FROM TBLHTPHAKMILIK , "+
					"TBLRUJKEMENTERIAN, "+
					"TBLRUJDAERAH, "+
					"TBLRUJMUKIM, "+
					"TBLRUJJENISHAKMILIK, "+
					"TBLRUJAGENSI, "+
					"TBLRUJNEGERI, "+
					"TBLPERMOHONAN, "+
					"TBLPFDFAIL, "+
					"TBLHTPHAKMILIKAGENSI, "+
					"TBLRUJURUSAN "+
					"WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "+
					"AND TBLPERMOHONAN.ID_PERMOHONAN = TBLHTPHAKMILIK.ID_PERMOHONAN "+
					"AND TBLRUJURUSAN.ID_URUSAN = TBLPFDFAIL.ID_URUSAN "+
					"AND TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK(+) "+
					"AND TBLHTPHAKMILIK.ID_KEMENTERIAN = TBLRUJKEMENTERIAN.ID_KEMENTERIAN(+) "+
					"AND TBLHTPHAKMILIKAGENSI.ID_AGENSI = TBLRUJAGENSI.ID_AGENSI(+) "+
					"AND TBLHTPHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) "+
					"AND TBLHTPHAKMILIK.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI(+) "+
					"AND TBLHTPHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) "+
					"AND TBLHTPHAKMILIK.ID_DAERAH = TBLRUJDAERAH.ID_DAERAH(+) "+ 
					"AND TBLHTPHAKMILIK.ID_HAKMILIK ="+id;
		   
			log.info("maklumat fail hakmilik "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idHakmilik", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("namaKementerian", rs.getString("NAMA_KEMENTERIAN")==null ? "" :rs.getString("NAMA_KEMENTERIAN"));
				h.put("noFailSeksyen", rs.getString("NO_FAIL")==null ? "" :rs.getString("NO_FAIL"));
				h.put("noFailPtg", rs.getString("NO_FAIL_PTG")==null ? "" :rs.getString("NO_FAIL_PTG"));
				h.put("tajukFail", rs.getString("TUJUAN")==null ? "" :rs.getString("TUJUAN"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI")==null ? "" :rs.getString("NAMA_NEGERI"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH")==null ? "" :rs.getString("NAMA_DAERAH"));
				h.put("jenisHakmilik", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN").toUpperCase());
				h.put("namaMukim", rs.getString("NAMA_MUKIM")==null ? "" :rs.getString("NAMA_MUKIM"));	
				h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
				h.put("noFailKjp", rs.getString("NO_FAIL_KJP")==null ? "":rs.getString("NO_FAIL_KJP"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));
				h.put("noLot", rs.getString("NO_LOT")==null ? "" :rs.getString("NO_LOT"));
				h.put("noWarta", rs.getString("NO_WARTA")==null ? "" :rs.getString("NO_WARTA"));
				h.put("caraPerolehan", rs.getString("NAMA_URUSAN")==null ? "" :rs.getString("NAMA_URUSAN"));
				listMaklumatFail.addElement(h);

			}
		} finally {
			if (db != null)
				db.close();
		}
		return listMaklumatFail;
	}
	
	
	public static Vector getPaparMaklumatFailById(String id) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			listMaklumatFail = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLHTPHAKMILIK.ID_HAKMILIK, TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN,TBLRUJAGENSI.NAMA_AGENSI, "+
					"TBLRUJNEGERI.NAMA_NEGERI, TBLRUJDAERAH.NAMA_DAERAH, TBLRUJMUKIM.NAMA_MUKIM, "+
					"TBLHTPHAKMILIK.NO_FAIL_PTG, TBLPFDFAIL.NO_FAIL,TBLHTPHAKMILIK.NO_FAIL_KJP, "+
					"TBLPERMOHONAN.TUJUAN, TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK KETERANGAN, "+
					"TBLHTPHAKMILIK.NO_HAKMILIK, TBLHTPHAKMILIK.NO_LOT, TBLHTPHAKMILIK.NO_WARTA, "+
					"TBLRUJURUSAN.NAMA_URUSAN " +
					",(SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT = TBLHTPHAKMILIK.ID_LOT ) JENIS_LOT "+
					"FROM TBLHTPHAKMILIK , "+
					"TBLRUJKEMENTERIAN, "+
					"TBLRUJDAERAH, "+
					"TBLRUJMUKIM, "+
					"TBLRUJJENISHAKMILIK, "+
					"TBLRUJAGENSI, "+
					"TBLRUJNEGERI, "+
					"TBLPERMOHONAN, "+
					"TBLPFDFAIL, "+
					"TBLHTPHAKMILIKAGENSI, "+
					"TBLRUJURUSAN "+
					"WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "+
					"AND TBLPERMOHONAN.ID_PERMOHONAN = TBLHTPHAKMILIK.ID_PERMOHONAN "+
					"AND TBLRUJURUSAN.ID_URUSAN = TBLPFDFAIL.ID_URUSAN "+
					"AND TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK(+) "+
					"AND TBLHTPHAKMILIK.ID_KEMENTERIAN = TBLRUJKEMENTERIAN.ID_KEMENTERIAN(+) "+
					"AND TBLHTPHAKMILIKAGENSI.ID_AGENSI = TBLRUJAGENSI.ID_AGENSI(+) "+
					"AND TBLHTPHAKMILIKAGENSI.STATUS = 'Y' "+
					"AND TBLHTPHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) "+
					"AND TBLHTPHAKMILIK.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI(+) "+
					"AND TBLHTPHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) "+
					"AND TBLHTPHAKMILIK.ID_DAERAH = TBLRUJDAERAH.ID_DAERAH(+) "+ 
					"AND TBLHTPHAKMILIK.ID_HAKMILIK ='"+id+"'";
		   
			log.info("getPaparMaklumatFailById:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilik", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("namaKementerian", rs.getString("NAMA_KEMENTERIAN")==null ? "" :rs.getString("NAMA_KEMENTERIAN"));
				h.put("noFailSeksyen", rs.getString("NO_FAIL")==null ? "" :rs.getString("NO_FAIL"));
				h.put("noFailPtg", rs.getString("NO_FAIL_PTG")==null ? "" :rs.getString("NO_FAIL_PTG"));
				h.put("tajukFail", rs.getString("TUJUAN")==null ? "" :rs.getString("TUJUAN"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI")==null ? "" :rs.getString("NAMA_NEGERI"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH")==null ? "" :rs.getString("NAMA_DAERAH"));
				h.put("jenisHakmilik", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN").toUpperCase());
				h.put("namaMukim", rs.getString("NAMA_MUKIM")==null ? "" :rs.getString("NAMA_MUKIM"));	
				h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
				h.put("noFailKjp", rs.getString("NO_FAIL_KJP")==null ? "":rs.getString("NO_FAIL_KJP"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));
				h.put("jenisLot", rs.getString("JENIS_LOT")==null ? "" :rs.getString("JENIS_LOT"));
				h.put("noLot", rs.getString("NO_LOT")==null ? "" :rs.getString("NO_LOT"));
				h.put("noWarta", rs.getString("NO_WARTA")==null ? "" :rs.getString("NO_WARTA"));
				h.put("caraPerolehan", rs.getString("NAMA_URUSAN")==null ? "" :rs.getString("NAMA_URUSAN"));
				listMaklumatFail.addElement(h);

			}
		} finally {
			if (db != null)
				db.close();
		}
		return listMaklumatFail;
	}

	// PAPAR PERGERAKAN HAKMILIK BY ID
	public static Vector getPaparSenaraiPergerakanHakmilikById(int id) throws Exception {

		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			listPergerakanHakmilik = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PERGERAKAN, A.TARIKH, UPPER(A.KETERANGAN) AS KETERANGAN" +
					", A.BIL_SALINAN, A.STATUS, UPPER(A.KEPADA) AS KEPADA, A.TARIKH_KEMBALI, "+
			  	  "CASE "+
			  	  	"WHEN A.STATUS = 'D' THEN 'DALAMAN' "+
			  	  	"WHEN A.STATUS = 'L' THEN 'LUARAN' "+
			  	  "END AS STATUS_PINJAMAN "+
			  	  "FROM TBLHTPPERGERAKAN A, TBLHTPHAKMILIK B "+
			  	  "WHERE B.ID_HAKMILIK = A.ID_HAKMILIK "+
			  	  "AND A.ID_HAKMILIK = ' "+id +"'"+
			  	  "ORDER BY A.TARIKH DESC";
			
			log.info("senarai pergerakan hakmilik : "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				h.put("idPergerakan", rs.getString("ID_PERGERAKAN"));
				h.put("tarikh", rs.getString("TARIKH")==null ? "" :sdf.format(rs.getDate("TARIKH")));
				h.put("keterangan", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN"));
				h.put("bilSalinan", rs.getString("BIL_SALINAN")==null ? "":rs.getString("BIL_SALINAN"));
				h.put("status", rs.getString("STATUS_PINJAMAN")==null ? "":rs.getString("STATUS_PINJAMAN"));
				h.put("kepada", rs.getString("KEPADA")==null ? "":rs.getString("KEPADA"));
				h.put("tarikhKembali", rs.getString("TARIKH_KEMBALI")==null ? "" :sdf.format(rs.getDate("TARIKH_KEMBALI")));
				listPergerakanHakmilik.addElement(h);
		        bil++;
		    	count++;
		   }
		   if (count == 0){
		        h = new Hashtable();
		    	h.put("bil","");
		    	h.put("idPergerakan",0);
		        h.put("tarikh", "");
		    	h.put("keterangan", "Tiada rekod.");
		        h.put("bilSalinan", "");
		    	h.put("status", "");
		    	h.put("kepada", "");
		    	h.put("tarikhKembali", "");
		        listPergerakanHakmilik.addElement(h);
		  }
		}
		finally {
			if (db != null)
				db.close();
	    }
		return listPergerakanHakmilik;
	}
	
	// PAPAR PERGERAKAN HAKMILIK BY ID
	public static Vector getPaparSenaraiPergerakanHakmilikById(String id) throws Exception {

		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			listPergerakanHakmilik = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PERGERAKAN, A.TARIKH, UPPER(A.KETERANGAN) AS KETERANGAN" +
					", A.BIL_SALINAN, A.STATUS, UPPER(A.KEPADA) AS KEPADA, A.TARIKH_KEMBALI, "+
			  	  "CASE "+
			  	  	"WHEN A.STATUS = 'D' THEN 'DALAMAN' "+
			  	  	"WHEN A.STATUS = 'L' THEN 'LUARAN' "+
			  	  "END AS STATUS_PINJAMAN "+
			  	  "FROM TBLHTPPERGERAKAN A, TBLHTPHAKMILIK B "+
			  	  "WHERE B.ID_HAKMILIK = A.ID_HAKMILIK "+
			  	  "AND A.ID_HAKMILIK = ' "+id +"'"+
			  	  "ORDER BY A.TARIKH DESC";
			
			log.info("senarai pergerakan hakmilik : "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				h.put("idPergerakan", rs.getString("ID_PERGERAKAN"));
				h.put("tarikh", rs.getString("TARIKH")==null ? "" :sdf.format(rs.getDate("TARIKH")));
				h.put("keterangan", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN"));
				h.put("bilSalinan", rs.getString("BIL_SALINAN")==null ? "":rs.getString("BIL_SALINAN"));
				h.put("status", rs.getString("STATUS_PINJAMAN")==null ? "":rs.getString("STATUS_PINJAMAN"));
				h.put("kepada", rs.getString("KEPADA")==null ? "":rs.getString("KEPADA"));
				h.put("tarikhKembali", rs.getString("TARIKH_KEMBALI")==null ? "" :sdf.format(rs.getDate("TARIKH_KEMBALI")));
				listPergerakanHakmilik.addElement(h);
		        bil++;
		    	count++;
		   }
		   if (count == 0){
		        h = new Hashtable();
		    	h.put("bil","");
		    	h.put("idPergerakan",0);
		        h.put("tarikh", "");
		    	h.put("keterangan", "Tiada rekod.");
		        h.put("bilSalinan", "");
		    	h.put("status", "");
		    	h.put("kepada", "");
		    	h.put("tarikhKembali", "");
		        listPergerakanHakmilik.addElement(h);
		  }
		}
		finally {
			if (db != null)
				db.close();
	    }
		return listPergerakanHakmilik;
	}

	// ADD PERGERAKAN
	public static String addPergerakan(Hashtable data) throws Exception {
		Db db = null;
		Connection conn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
		String idHakmilikAsal =  (String)data.get("idHakmilik");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
		    long idPergerakan = DB.getNextID("TBLHTPPERGERAKAN_SEQ"); 
		    r.add("ID_PERGERAKAN",idPergerakan);
		    r.add("ID_HAKMILIK",data.get("idHakmilik"));
		    r.add("KETERANGAN",data.get("keterangan"));
		    r.add("KEPADA",data.get("kepada"));
			//convert date before add
			String tarikhSerahan = (String)data.get("tarikhSerah");
			String txdTarikhSerahan = "to_date('" + tarikhSerahan + "','dd/MM/yyyy')";
		    r.add("TARIKH", r.unquote(txdTarikhSerahan));
		    r.add("STATUS",data.get("socStatusS"));
		    r.add("BIL_SALINAN",data.get("bilSalinan")); 
			//convert date before add
			String tarikhKembali = (String)data.get("txdTarikhKembali");
			String txdTarikhKembali = "to_date('" + tarikhKembali + "','dd/MM/yyyy')";
	        if (tarikhKembali !=null && !tarikhKembali.equals("")){
				r.add("TARIKH_KEMBALI", r.unquote(txdTarikhKembali));	
			}
		    r.add("CATATAN",data.get("txtCatatan")); 
		    r.add("NO_FAIL",data.get("failRujukanPer")); 
		    r.add("TAJUK_FAIL",data.get("txTajukPer")); 
		    r.add("FLAG_PILIHAN",data.get("sorDokumen")); 
		    r.add("ID_MASUK",data.get("idKemaskini")); 
		    r.add("TARIKH_MASUK",r.unquote("SYSDATE")); 
		    sql = r.getSQLInsert("TBLHTPPERGERAKAN");
		    log.info("sql insert baru "+sql);
			stmt.executeUpdate(sql);
			
			
			conn.commit();
		    return String.valueOf(idPergerakan);
		    
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
	    }
	}
	
	// UPDATE PERGERAKAN
	public static void updatePergerakan(Hashtable data) throws Exception {
		Db db = null;
		Connection conn = null;
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Date date = new Date(); 
		//String currentDate = sdf.format(date);
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
		    r.update("ID_PERGERAKAN",data.get("idPergerakan"));
		    r.add("ID_HAKMILIK",data.get("idHakmilik"));
		    r.add("KETERANGAN",data.get("keterangan"));
		    r.add("KEPADA",data.get("kepada"));
			//convert date before add
			String tarikhSerahan = (String)data.get("tarikhSerah");
			String txdTarikhSerahan = "to_date('" + tarikhSerahan + "','dd/MM/yyyy')";
		    r.add("TARIKH", r.unquote(txdTarikhSerahan));
		    r.add("STATUS",data.get("socStatus"));
		    r.add("BIL_SALINAN",data.get("bilSalinan"));
			//convert date before add
			String tarikhKembali = (String)data.get("txdTarikhKembali");
		    log.info("tarikhKembali : "+tarikhKembali);
			String txdTarikhKembali = "to_date('" + tarikhKembali + "','dd/MM/yyyy')";
	        if (tarikhKembali !=null && !tarikhKembali.equals("")){
				r.add("TARIKH_KEMBALI", r.unquote(txdTarikhKembali));	
			}else{
				r.add("TARIKH_KEMBALI", tarikhKembali);				
			}
	        
		    r.add("CATATAN",data.get("txtCatatan")); 
		    r.add("FLAG_PILIHAN",data.get("sorDokumen"));
		    r.add("ID_KEMASKINI",data.get("idKemaskini"));
		    r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE")); 
		    r.add("NO_FAIL",data.get("failRujukanPer")); 
		    r.add("TAJUK_FAIL",data.get("txTajukPer")); 		    
		    sql = r.getSQLUpdate("TBLHTPPERGERAKAN");
		    log.info("sql update baru "+sql);
			stmt.executeUpdate(sql);
			
			conn.commit();
		    
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	    

	}
	// PAPAR MAKLUMAT PERGERAKAN BY ID
	public static Vector getMaklumatPergerakanById(String id) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			listPergerakanById = new Vector();
			Statement stmt = db.getStatement();			
			sql ="SELECT A.ID_PERGERAKAN, A.ID_HAKMILIK, A.FLAG_PILIHAN, A.KEPADA, A.KETERANGAN" +
					" , A.STATUS, A.BIL_SALINAN, A.TARIKH, A.TARIKH_KEMBALI, A.CATATAN " +
					" ,A.NO_FAIL,  A.TAJUK_FAIL " +
					" FROM TBLHTPPERGERAKAN A " +
					" WHERE A.ID_PERGERAKAN = '"+id +"'";		
			log.info("papar detail pergerakan "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPergerakan", rs.getString("ID_PERGERAKAN"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("sorDokumen", rs.getString("FLAG_PILIHAN")==null ? "":rs.getString("FLAG_PILIHAN"));
				h.put("kepada", rs.getString("KEPADA")==null ? "":rs.getString("KEPADA"));
				h.put("tarikhSerahan", rs.getString("TARIKH")==null ? "	" :sdf.format(rs.getDate("TARIKH")));
				h.put("bilSalinan", rs.getString("BIL_SALINAN")==null ? "" :rs.getString("BIL_SALINAN"));
				h.put("keterangan", rs.getString("KETERANGAN")==null ? "":rs.getString("KETERANGAN"));
				h.put("socStatusS", rs.getString("STATUS")==null ? "":rs.getString("STATUS"));
				h.put("tarikhKembali", rs.getString("TARIKH_KEMBALI")==null ? "" :sdf.format(rs.getDate("TARIKH_KEMBALI")));
				h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
				h.put("failRujukanPer", Utils.isNull(rs.getString("NO_FAIL")));
				h.put("txTajukPer", Utils.isNull(rs.getString("TAJUK_FAIL")));			
				listPergerakanById.addElement(h);
				
			}
		} finally {
			if (db != null)
				db.close();
		
		}
		return listPergerakanById;
		
	}
	
	public static void hapus(String id) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PERGERAKAN",id);
			sql = r.getSQLDelete("TBLHTPPERGERAKAN");
			stmt.executeUpdate(sql);
			/// DELETE MAKLUMAT PERGERAKAN

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
}
