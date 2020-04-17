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

public class FrmRekodPendaftaranHakmilikSementaraData {
	private static Logger log = Logger.getLogger(ekptg.model.htp.FrmRekodPendaftaranHakmilikSementaraData.class);
	private static Vector<Hashtable<String,String>> listHakmilikSementara = null;
	private static Vector<Hashtable<String,String>> listSenaraiFail = null;
	private static Vector<Hashtable<String,String>> listMaklumatFail = null;
	private static Vector<Hashtable<String,String>> listCarianHakmilikSementara = null;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static String sql = "";
    private static Db db = null;

	//PAPAR CARIAN HAKMILIK DAN RIZAB
	public static Vector<Hashtable<String,String>> getCarianSenaraiHakmilikSementaraById(String idJenisTanah,String idNegeri, String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi)throws Exception {
	    listCarianHakmilikSementara = new Vector<Hashtable<String,String>>();
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
	      		"THEN 'BATAL PELEPASAN' "+
	      		"WHEN A.STATUS_SAH = 'T' "+
	      		"THEN 'BATAL TUKAR GUNA' "+ 
	      		"WHEN A.STATUS_SAH = 'S' "+
	      		"THEN 'BATAL SAMBUNGAN' "+
	      		"ELSE 'TIADA' "+
	      		"END AS STATUS_HAKMILIK, "+
	      		"A.STATUS_RIZAB, A.TARIKH_TERIMA,I.KEGUNAAN_TANAH "+
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
           		"AND A.STATUS_SAH = 'D' " +
           		"AND A.STATUS_RIZAB IS NOT NULL ";
	      
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
	      log.info("carian "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
  
	      Hashtable<String,String> h;
	      int bil = 1;
	      int count = 0;
    		while (rs.next()) {
    			h = new Hashtable<String,String>();
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

    			listCarianHakmilikSementara.addElement(h);
    			bil++;
    			count++;
  	  
    		}
    		if (count == 0){
    			h = new Hashtable<String,String>();
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
  	  
    			listCarianHakmilikSementara.addElement(h);
    		}
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }		
	    return listCarianHakmilikSementara;
	}	
	// PAPAR SENARAI FAIL HAKMILIK SEMENTARA
	public static Vector<Hashtable<String,String>> getSenaraiHakmilikSambungan(String noHakmilikAsal) throws Exception {
		try {
			db = new Db();
			listSenaraiFail = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();		
			sql = "SELECT TBLHTPHAKMILIK.ID_HAKMILIK,TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK, TBLHTPHAKMILIK.NO_HAKMILIK, "+
				  "(SELECT ID_HAKMILIK FROM TBLHTPHAKMILIK WHERE NO_HAKMILIK LIKE '%20000003%') AS ID_HAKMILIK_ASAL "+
		            ",CASE "+
		            "WHEN TBLHTPHAKMILIK.STATUS_SAH = 'H' "+
		                "THEN 'HAKMILIK ASAL TIADA' "+
		             "WHEN TBLHTPHAKMILIK.STATUS_SAH = 'D' "+
		                "THEN 'DAFTAR' "+
		             "WHEN TBLHTPHAKMILIK.STATUS_SAH = 'P' "+
		                "THEN 'BATAL (PELEPASAN)' "+
		             "WHEN TBLHTPHAKMILIK.STATUS_SAH = 'T' "+
		                "THEN 'TUKAR GUNA' "+ 
		             "WHEN TBLHTPHAKMILIK.STATUS_SAH = 'S' "+
		                "THEN 'BATAL (SAMBUNGAN)' "+
		             "WHEN TBLHTPHAKMILIK.STATUS_SAH = 'B' "+
		                "THEN 'BATAL' "+
		             "WHEN TBLHTPHAKMILIK.STATUS_SAH = 'M' "+
			         	"THEN 'BATAL (PINDAHMILIK)' "+
		             "ELSE 'TIADA' "+
		             "END AS STATUS_HAKMILIK "+				  
				  "FROM TBLHTPHAKMILIK,TBLRUJJENISHAKMILIK "+
				  "WHERE TBLHTPHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK "+
				  "AND TBLHTPHAKMILIK.HAKMILIK_ASAL= '"+noHakmilikAsal+"'";			
//			  "AND TBLHTPHAKMILIK.HAKMILIK_ASAL LIKE '%"+noHakmilikAsal+"%' ";			
			
			log.info("getSenaraiHakmilikSambungan :" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", bil+".");
				h.put("idHakmilikAsal", rs.getString("ID_HAKMILIK_ASAL")==null ? "" :rs.getString("ID_HAKMILIK_ASAL"));
				h.put("idHakmilikBaru", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("kodJenisBaru", rs.getString("KOD_JENIS_HAKMILIK")==null ? "" :rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("noHakmilikBaru", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));			
		    	h.put("statusSah", rs.getString("STATUS_HAKMILIK")== null?"":rs.getString("STATUS_HAKMILIK"));
				listSenaraiFail.addElement(h);
		    	bil++;
		    	count++;
		    	
			} 		
			if(count==0){
				h = new Hashtable<String,String>();
				h.put("bil","");
				h.put("idHakmilikAsal","");
				h.put("idHakmilikBaru","");
				//h.put("kodJenisBaru", "Tiada Rekod");
				h.put("kodJenisBaru", "");
				h.put("noHakmilikBaru","");			
				h.put("statusSah","TIADA");
				listSenaraiFail.addElement(h);
				
			}
			return listSenaraiFail;
			
		} finally {
			if (db != null)	db.close();
		}
	}		
	// RETURN SENARI FAIL
	public static Vector<Hashtable<String,String>> getPaparSenaraiHakmilikSementara() throws Exception {
		return listCarianHakmilikSementara;
	}
	// PAPAR MAKLUMAT FAIL (MASTER) BY ID
	public static Vector<Hashtable<String,String>> getPaparMaklumatFailById(String idHakmilik) throws Exception {
		try {
			db = new Db();
			listMaklumatFail = new Vector<Hashtable<String,String>>();
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
		  	  	"AND TBLHTPHAKMILIK.ID_HAKMILIK ='"+idHakmilik+"'";		   
			log.info("maklumat fail hakmilik "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
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
	// PAPAR HAKMILIK SEMENTARA BY ID
	public static Vector<Hashtable<String,String>> getPaparHakmilikSementaraById(String idHakmilik) throws Exception {
		try {
			db = new Db();
			listHakmilikSementara = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();			
			sql = "SELECT A.ID_HAKMILIK, A.TARIKH_TERIMA, A.TARIKH_DAFTAR,  "+
	       	  "A.ID_NEGERI, A.ID_DAERAH, A.ID_MUKIM, A.ID_JENISHAKMILIK, A.CUKAI, "+
	       	  "A.LOKASI,A.ID_LUAS,A.LUAS, " +
	       	  "A.LUAS_BERSAMAAN,A.NO_PELAN, A.ID_LOT, A.ID_RIZAB, A.ID_KATEGORI, "+
	       	  "A.TEMPOH, A.SYARAT, A.HAKMILIK_ASAL, A.NO_FAIL_JOPA, A.TARAF_HAKMILIK, "+
	       	  "A.TARIKH_LUPUT, A.CUKAI_TERKINI, A.KEGUNAAN_TANAH, A.NO_PU, "+
	       	  "A.TARIKH_WARTA, A.KAWASAN_RIZAB, A.NO_SYIT, A.NO_RIZAB NO_WARTA, A.SEKATAN, "+	
	       	  "A.HAKMILIK_BERIKUT, A.STATUS_SAH, A.TARIKH_KEMASKINI, A.STATUS_RIZAB, "+
	       	  "A.NO_RIZAB,A.TARIKH_RIZAB,A.NO_BANGUNAN,NO_TINGKAT,NO_PETAK,A.ID_KEMASKINI, "+
	       	  "S.USER_NAME, A.CATATAN,LTRIM(A.NO_HAKMILIK,'0') NO_HAKMILIK  "+
	       	  ",(SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH " +
	       	  " WHERE RJH.ID_JENISHAKMILIK=A.ID_JENISHAKMILIK ) KOD_JENIS_HAKMILIK "+
			  "FROM TBLHTPHAKMILIK A, USERS S "+
			  "WHERE A.ID_HAKMILIK = '"+idHakmilik +"' " +
			  "AND A.ID_KEMASKINI = S.USER_ID(+) ";			  		
		log.info("maklumat detail hakmilik xxx :"+sql);
		ResultSet rs = stmt.executeQuery(sql);		
		Hashtable<String,String> h;
		while (rs.next()) {
			h = new Hashtable<String,String>();

			h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
			h.put("idNegeriHR", rs.getString("ID_NEGERI")==null ? "" :rs.getString("ID_NEGERI"));
			h.put("idDaerahHR", rs.getString("ID_DAERAH")==null ? "":rs.getString("ID_DAERAH"));
			h.put("idMukimHR", rs.getString("ID_MUKIM")==null ? "":rs.getString("ID_MUKIM"));
			h.put("idLot", rs.getString("ID_LOT")==null ? "":rs.getString("ID_LOT"));
			h.put("idJenisHakmilikHR", rs.getString("ID_JENISHAKMILIK")==null ? "":rs.getString("ID_JENISHAKMILIK"));
			h.put("tarikhTerima", rs.getString("TARIKH_TERIMA")==null ? "	" :sdf.format(rs.getDate("TARIKH_TERIMA")));
			h.put("tarikhDaftar", rs.getString("TARIKH_DAFTAR")==null ? "" :sdf.format(rs.getDate("TARIKH_DAFTAR")));
			h.put("cukai", rs.getString("CUKAI")==null ? "" :rs.getString("CUKAI"));
			h.put("lokasi", rs.getString("LOKASI")==null ? "":rs.getString("LOKASI"));
			//LUAS LAMA
			h.put("idLuasLama", rs.getString("ID_LUAS")==null ? "":rs.getString("ID_LUAS"));
			h.put("luasLama", rs.getString("LUAS")==null ? "":rs.getString("LUAS"));
			//LUAS CONVERT
			h.put("luasConvert", rs.getString("LUAS_BERSAMAAN")==null ? "":rs.getString("LUAS_BERSAMAAN"));
			h.put("noPelan", rs.getString("NO_PELAN")==null ? "":rs.getString("NO_PELAN"));
			h.put("idJenisRizab", rs.getString("ID_RIZAB")==null ? "":rs.getString("ID_RIZAB"));
			h.put("idKategori", rs.getString("id_Kategori")==null ? "" :rs.getString("ID_KATEGORI"));
			h.put("tempoh", rs.getString("TEMPOH")==null ? "" :rs.getString("TEMPOH"));
			h.put("syarat", rs.getString("SYARAT")==null ? "":rs.getString("SYARAT"));
			h.put("noBangunan", rs.getString("NO_BANGUNAN")==null ? "":rs.getString("NO_BANGUNAN"));
			h.put("noTingkat", rs.getString("NO_TINGKAT")==null ? "":rs.getString("NO_TINGKAT"));
			h.put("noPetak", rs.getString("NO_PETAK")==null ? "":rs.getString("NO_PETAK"));
			h.put("socTaraf", rs.getString("TARAF_HAKMILIK")==null ? "":rs.getString("TARAF_HAKMILIK"));
			h.put("tarikhLuput", rs.getString("TARIKH_LUPUT")==null ? "":sdf.format(rs.getDate("TARIKH_LUPUT")));
			h.put("cukaiTerkini", rs.getString("CUKAI_TERKINI")==null ? "":rs.getString("CUKAI_TERKINI"));
			h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")==null ? "":rs.getString("KEGUNAAN_TANAH"));				
			h.put("noPu", rs.getString("NO_PU")==null ? "":rs.getString("NO_PU"));
			h.put("tarikhWarta", rs.getString("TARIKH_WARTA")==null ? "":sdf.format(rs.getDate("TARIKH_WARTA")));
			h.put("noWarta", rs.getString("NO_WARTA")==null? "":rs.getString("NO_WARTA"));
			h.put("tarikhRizab", rs.getString("TARIKH_RIZAB")==null ? "":sdf.format(rs.getDate("TARIKH_RIZAB")));
			h.put("noRizab", rs.getString("NO_RIZAB")==null? "":rs.getString("NO_RIZAB"));
			h.put("kawasanRizab", rs.getString("KAWASAN_RIZAB")==null ? "":rs.getString("KAWASAN_RIZAB"));
			h.put("noSyit", rs.getString("NO_SYIT")==null ? "":rs.getString("NO_SYIT"));
			h.put("sekatan", rs.getString("SEKATAN")==null ? "":rs.getString("SEKATAN"));
			h.put("socStatus", rs.getString("STATUS_SAH")==null ? "":rs.getString("STATUS_SAH"));
			h.put("tarikhKemaskini", rs.getString("TARIKH_KEMASKINI")==null ? "":sdf.format(rs.getDate("TARIKH_KEMASKINI")));
			
			//FIX BUG. OPEN THIS LINE BACK BECAUSE SOME LABEL DIDNT DISPLAY. 21112014. SYAZ
			h.put("socRizab", rs.getString("STATUS_RIZAB")==null ? "":rs.getString("STATUS_RIZAB"));
	    	  
			/*
			if(rs.getString("NO_HAKMILIK")!= null){
	    		  h.put("socRizab","M");
	    	  }else{
	    		  h.put("socRizab","R");
	    	  }
	    	  if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
	    		  h.put("socRizab","X");
	    	  }
	    	*/
			
	    	h.put("userName", rs.getString("USER_NAME")==null ? "":rs.getString("USER_NAME").toUpperCase());
			h.put("hakmilikAsal", rs.getString("HAKMILIK_ASAL")==null ? "":rs.getString("HAKMILIK_ASAL"));
			h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
			h.put("txtKemAgenTerkini", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
			h.put("noHakmilik", rs.getString("NO_HAKMILIK")==null ? "":rs.getString("NO_HAKMILIK"));
			h.put("kodJenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null ? "":rs.getString("KOD_JENIS_HAKMILIK"));			
			listHakmilikSementara.addElement(h);
			
			}
		} finally {
			if (db != null)	db.close();
		}
		return listHakmilikSementara;
		
	}
	// KEMASKINI HAKMILIK BY ID
//	public static void updateHakmilikById(Hashtable data) throws Exception {
//
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = new Date(); 
//		String currentDate = sdf.format(date);
//		String idHakmilikAsal =  (String)data.get("idHakmilik");
//	    //UPDATE FAIL LAME
//	    Db db = null;
//	    String sql = "";
//	    
//	    try
//	    {
//			  db = new Db();
//			  Statement stmt = db.getStatement();
//			  SQLRenderer r = new SQLRenderer();
//	    	  r.update("ID_HAKMILIK", data.get("idHakmilik"));
//	    	  r.add("STATUS_SAH", "B");
//	    	  r.add("HAKMILIK_ASAL",data.get("txtHakmilikAsal"));
//	    	  r.add("HAKMILIK_BERIKUT",data.get("txtHakmilikBerikut"));
//			  sql = r.getSQLUpdate("TBLHTPHAKMILIK");
//			  log.info("sql update :"+sql);
//		      stmt.executeUpdate(sql);
//		}
//	    finally {
//		   if (db != null) db.close();
//		}	
//	    Db dbHakmilikLama = null;
//	    String sqlHakmilikLama = "";
//	    try {
//		      dbHakmilikLama = new Db();
//		      Statement stmt = db.getStatement();
//	    
//		      sqlHakmilikLama = "SELECT A.ID_PERMOHONAN, A.ID_LUAS, A.PEGANGAN_HAKMILIK, A.ID_SUBKATEGORI, A.ID_KATEGORI, " +
//		      		"A.ID_DAERAH, A.ID_DAERAH, A.ID_MUKIM, A.ID_JENISHAKMILIK, A.ID_LOT, A.ID_RIZAB FROM TBLHTPHAKMILIK " +
//		      		"WHERE A.ID_HAKMILIK = ' "+idHakmilikAsal +"'";
//	    }
//	    finally {
//			   if (dbHakmilikLama != null) db.close();
//		}		
//	    Db db2 = null;
//	    String sql2 = "";
//		try
//		{
//		    db2 = new Db();
//		    Statement stmt2 = db2.getStatement();
//		    SQLRenderer r2 = new SQLRenderer();
//		    
//		    long idHakmilik = DB.getNextID("TBLHTPHAKMILIK_SEQ"); 
//		    r2.add("ID_HAKMILIK",idHakmilik);
//		    //convert date before add
//			String tarikhTerima = (String)(data.get("txdTarikhTerima"));
//			String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
//			r2.add("TARIKH_TERIMA",r2.unquote(txdTarikhTerima));  	
//			//convert date before add
//			String tarikhDaftar = (String)data.get("txdTarikhDaftar");
//			String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
//		    r2.add("TARIKH_DAFTAR", r2.unquote(txdTarikhDaftar));
//		    r2.add("CUKAI", data.get("txtCukaiTahun"));
//		    r2.add("LOKASI", data.get("txtLokasi"));
//		    r2.add("ID_LUAS", data.get("socLuas"));
//		    r2.add("TARAF_HAKMILIK", data.get("socTaraf"));
//		    r2.add("ID_KATEGORI", data.get("socKategori"));
//		    r2.add("NO_PELAN", data.get("txtNoPelan"));
//		    r2.add("LUAS", data.get("txtLuas"));
//		    r2.add("TEMPOH", data.get("txtTempoh"));
//		    r2.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	    	  
//		    r2.add("CUKAI_TERKINI", data.get("txtCukaiTerkini"));
//		    //convert date before add
//			String tarikhLuput = (String)data.get("txdTarikhLuput");
//			String txdTarikhLuput = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";	  			  			 
//		    r2.add("TARIKH_LUPUT", r2.unquote(txdTarikhLuput));
//		    r2.add("NO_PU", data.get("txtNoPu"));
//		    //convert date before add
//			String tarikhWarta = (String)data.get("txdTarikhWarta");
//			String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";		    	  
//		    r2.add("TARIKH_WARTA", r2.unquote(txdTarikhWarta));
//		    r2.add("KAWASAN_RIZAB", data.get("txtKawasanRizab"));
//		    r2.add("NO_SYIT", data.get("txtNoSyit"));
//		    r2.add("NO_WARTA", data.get("txtNoWarta"));
//		    r2.add("SEKATAN", data.get("txtSekatan"));	  
//		    r2.add("SYARAT", data.get("txtSyarat"));	  
//		    r2.add("HAKMILIK_ASAL", data.get("txtHakmilikAsal"));
//		    r2.add("HAKMILIK_BERIKUT", data.get("txtHakmilikBerikut"));
//		    r2.add("STATUS_SAH", "");
//		    //convert date before add
//			String tarikhKemaskini = currentDate;
//			String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
//		    r2.add("TARIKH_KEMASKINI", r2.unquote(txdTarikhKemaskini));
//		    r2.add("ID_RIZAB", data.get("socJenisRizab"));    
//		    r2.add("STATUS_RIZAB",data.get("socRizab"));
//
//		    sql2 = r2.getSQLInsert("TBLHTPHAKMILIK");
//		    log.info("insert baru "+sql2);
//		    stmt2.executeUpdate(sql2);
//		    }
//		    finally {
//		    	if (db2 != null) db2.close();
//		    }
//    }
	/////
	public static void updateHakmilikById(Hashtable<String,String> data) throws Exception {
		Connection conn = null;
		String idHakmilikAsal =  (String)data.get("idHakmilik");
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String status = (String)data.get("socStatus");
		    //UPDATE FAIL LAME
			
			/*if (status != null && status.trim().length() != 0){
			    r.update("ID_HAKMILIK", data.get("idHakmilik"));
		    	r.add("STATUS_SAH",data.get("socStatus"));
		    	r.add("ID_KEMASKINI",data.get("idKemaskini"));
		    	r.add("CATATAN",data.get("txtKemAgenTerkini"));	    	
		    	r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLHTPHAKMILIK");
				stmt.executeUpdate(sql);
			}*/
			
			if (status.equals("S") || status.equals("")){
				//GET DATA LAMA
				sql = "SELECT A.ID_PERMOHONAN, A.PEGANGAN_HAKMILIK, A.ID_SUBKATEGORI, " +
			      		"A.ID_NEGERI, A.ID_DAERAH, A.ID_MUKIM, A.ID_JENISHAKMILIK, " +
			      		"A.ID_LOT,A.TARIKH_TERIMA,A.TARIKH_DAFTAR, A.CUKAI, " +
			      		"A.LOKASI, A.ID_LUAS,A.TARAF_HAKMILIK, A.ID_KATEGORI, " +
			      		"A.NO_PELAN, A.LUAS, A.TEMPOH, A.CUKAI_TERKINI, A.TARIKH_LUPUT, " +
			      		"A.NO_PU, A.KAWASAN_RIZAB,A.NO_SYIT, A.TARIKH_RIZAB, " +
			      		"A.NO_RIZAB ,A.SEKATAN, A.SYARAT, A.NO_HAKMILIK, " +
			      		"A.STATUS_RIZAB, A.ID_RIZAB,A.NO_LOT " +
			      		",A.ID_LUAS " +
			      		"FROM TBLHTPHAKMILIK A " +
			      		"WHERE A.ID_HAKMILIK = '"+idHakmilikAsal +"'";
				log.info("get data lama hello"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					//CREATE REKOD BARU 
					r = new SQLRenderer();
				    long idHakmilikBaru = DB.getNextID("TBLHTPHAKMILIK_SEQ"); 
				    r.add("ID_HAKMILIK",idHakmilikBaru);
				    r.add("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN"));
				    r.add("PEGANGAN_HAKMILIK",rs.getString("PEGANGAN_HAKMILIK"));
				    r.add("ID_SUBKATEGORI",rs.getString("ID_SUBKATEGORI"));
				    r.add("ID_NEGERI",rs.getString("ID_NEGERI"));
				    r.add("ID_DAERAH",rs.getString("ID_DAERAH"));
				    r.add("ID_MUKIM",rs.getString("ID_MUKIM"));
				    r.add("ID_JENISHAKMILIK",data.get("socJenisHakmilikBaru"));
				    r.add("NO_HAKMILIK",data.get("txtHakmilikBerikut"));	    	  
				    if(rs.getString("TARIKH_DAFTAR")!=null){
				    	r.add("TARIKH_DAFTAR", r.unquote("to_date('" + sdf.format(rs.getDate("TARIKH_DAFTAR")) + "','dd/MM/yyyy')"));
				    }		  			  
				    r.add("CUKAI",rs.getString("CUKAI")== null ? "":rs.getString("CUKAI"));  	
				    r.add("LOKASI",rs.getString("LOKASI")== null ? "":rs.getString("LOKASI")); 	 	
				    r.add("TARAF_HAKMILIK",rs.getString("TARAF_HAKMILIK")== null ? "":rs.getString("TARAF_HAKMILIK"));	
				    r.add("ID_KATEGORI", rs.getString("ID_KATEGORI")== null ? "":rs.getString("ID_KATEGORI"));	
				    r.add("NO_PELAN",rs.getString("NO_PELAN")== null ? "":rs.getString("NO_PELAN"));
				    r.add("ID_LUAS", rs.getString("ID_LUAS")== null ? "":rs.getString("ID_LUAS"));
				    r.add("LUAS", rs.getString("LUAS")== null ? "":rs.getString("LUAS"));	  	
				    r.add("ID_LOT", rs.getString("ID_LOT")== null ? "":rs.getString("ID_LOT"));
				    r.add("NO_LOT", rs.getString("NO_LOT")== null ? "":rs.getString("NO_LOT"));			    
				    r.add("TEMPOH",rs.getString("TEMPOH")== null ? "":rs.getString("TEMPOH"));    	  
				    r.add("CUKAI_TERKINI",rs.getString("CUKAI_TERKINI")== null ? "":rs.getString("CUKAI_TERKINI"));  				  			 
				    if(rs.getString("TARIKH_LUPUT")!=null){
				    	r.add("TARIKH_LUPUT", r.unquote("to_date('" + sdf.format(rs.getDate("TARIKH_LUPUT")) + "','dd/MM/yyyy')"));
				    }
				    r.add("NO_PU",rs.getString("NO_PU")== null ? "":rs.getString("NO_PU"));    	  
				    r.add("KAWASAN_RIZAB", rs.getString("KAWASAN_RIZAB")== null ? "":rs.getString("KAWASAN_RIZAB"));   	
				    r.add("NO_SYIT", rs.getString("NO_SYIT")== null ? "":rs.getString("NO_SYIT"));
				    if(rs.getString("TARIKH_RIZAB")!=null){
				    	r.add("TARIKH_RIZAB", r.unquote("to_date('" + sdf.format(rs.getDate("TARIKH_RIZAB")) + "','dd/MM/yyyy')"));
				    }				    
				    r.add("NO_RIZAB", rs.getString("NO_RIZAB")== null ? "":rs.getString("NO_RIZAB")); 	
				    r.add("SEKATAN",rs.getString("SEKATAN")== null ? "":rs.getString("SEKATAN"));   
				    r.add("SYARAT", rs.getString("SYARAT")== null ? "":rs.getString("SYARAT"));     
				    r.add("HAKMILIK_ASAL", rs.getString("NO_HAKMILIK")== null ? "":rs.getString("NO_HAKMILIK"));  
				    r.add("STATUS_SAH", "D");    	  
				    r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				    r.add("ID_RIZAB", rs.getString("ID_RIZAB")== null ? "":rs.getString("ID_RIZAB"));   
				    r.add("STATUS_RIZAB",rs.getString("STATUS_RIZAB")== null ? "":rs.getString("STATUS_RIZAB"));    
				    sql = r.getSQLInsert("TBLHTPHAKMILIK");
				    log.info("sql insert baru "+sql);
					stmt.executeUpdate(sql);
				}
			}
			conn.commit();
			
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	public static void addHakmilikBaruById(Hashtable<String,String> data) throws Exception {
		Connection conn = null;
		Date date = new Date(); 
		String currentDate = sdf.format(date);
		String idHakmilikAsal =  (String)data.get("idHakmilik");
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long idHakmilikBaru = DB.getNextID(db,"TBLHTPHAKMILIK_SEQ"); 
			
			//GET DATA LAMA FROM TBLHTPHAKMILIK
			sql = "SELECT A.ID_PERMOHONAN, A.PEGANGAN_HAKMILIK, A.ID_SUBKATEGORI, " +
					"A.NO_HAKMILIK,LTRIM(A.NO_HAKMILIK,0) NO_HAKMILIKX0,A.ID_LOT, " +
		      		" A.ID_LUAS_BERSAMAAN,A.LUAS,A.ID_NEGERI,NVL(A.STATUS_RIZAB,'T') STATUS_RIZAB, " +
		      		" A.ID_DAERAH, A.ID_MUKIM, A.ID_JENISHAKMILIK, A.ID_LOT, A.ID_LUAS, " +
		      		" A.ID_KATEGORI,RJH.KOD_JENIS_HAKMILIK" +
		      		",A.ID_AGENSI, A.ID_KEMENTERIAN,A.KEGUNAAN_TANAH, A.CATATAN " + //modified on 2010/08/16
		      		" FROM TBLHTPHAKMILIK A,TBLRUJJENISHAKMILIK RJH " +
		      		" WHERE A.ID_JENISHAKMILIK=RJH.ID_JENISHAKMILIK " +
		      		" AND A.ID_HAKMILIK = '"+idHakmilikAsal +"'";
			log.info("get data lama "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				//CREATE REKOD BARU 			    
			    r.add("ID_HAKMILIK",idHakmilikBaru);
			    r.add("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN"));
			    //r.add("PEGANGAN_HAKMILIK",FrmUtilData.getKodNegeri(rs.getString("ID_NEGERI"))+FrmUtilData.getKodDaerah(rs.getString("ID_DAERAH"))+FrmUtilData.getKodMukim(rs.getString("ID_MUKIM"))+data.get("txtKodSocJenisHakmilikBaru")+String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtHakmilikBerikut")))));
			    r.add("PEGANGAN_HAKMILIK",FrmUtilData.getKodNegeri(rs.getString("ID_NEGERI"))+FrmUtilData.getKodDaerah(rs.getString("ID_DAERAH"))+FrmUtilData.getKodMukim(rs.getString("ID_MUKIM"))+data.get("txtKodSocJenisHakmilikBaru")+data.get("txtHakmilikBerikut"));
			    r.add("ID_SUBKATEGORI",rs.getString("ID_SUBKATEGORI"));
			    r.add("ID_NEGERI",rs.getString("ID_NEGERI"));
			    r.add("ID_DAERAH",rs.getString("ID_DAERAH"));
			    r.add("ID_MUKIM",rs.getString("ID_MUKIM"));
			    r.add("ID_JENISHAKMILIK",(String)data.get("socJenisHakmilikBaru"));
			    // By rosli add format 00000000,diguna semula pada 07/06/2010
			    r.add("NO_HAKMILIK", data.get("txtHakmilikBerikut"));
			    // tidak boleh format, kerana ada data yang dimasukkan adalah abjad 
			    //r.add("NO_HAKMILIK", String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtHakmilikBerikut")))));
			    r.add("NO_LOT",(String)data.get("txtNoLot"));
			    //convert date before add
				String tarikhTerima = (String)(data.get("txdTarikhTerima"));
				String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
				r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
				//convert date before add
				String tarikhDaftar = (String)data.get("txdTarikhDaftar");
				String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
			    r.add("TARIKH_DAFTAR", r.unquote(txdTarikhDaftar));
			    r.add("CUKAI", data.get("txtCukaiTahun"));
			    r.add("LOKASI", data.get("txtLokasi"));
			   
			    r.add("TARAF_HAKMILIK", data.get("socTaraf")); 
			    r.add("ID_LOT", rs.getString("ID_LOT"));
			    r.add("ID_KATEGORI", rs.getString("ID_KATEGORI"));
			    r.add("NO_PELAN", data.get("txtNoPelan"));
			    
			    r.add("ID_LUAS_BERSAMAAN",2);
			    r.add("LUAS_BERSAMAAN", data.get("txtLuas"));
		    	  
			    r.add("ID_LUAS",data.get("socLuas"));
			    r.add("LUAS", data.get("txtLuasLama"));

			    
			    r.add("TEMPOH", data.get("txtTempoh"));
			    r.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	    	  
			    r.add("CUKAI_TERKINI", data.get("txtCukaiTerkini"));
			    //convert date before add
				String tarikhLuput = (String)data.get("txdTarikhLuput");
				String txdTarikhLuput = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";	  			  			 
			    r.add("TARIKH_LUPUT", r.unquote(txdTarikhLuput));
			    r.add("NO_PU", data.get("txtNoPu"));
			    //convert date before add
				String tarikhWarta = (String)data.get("txdTarikhWarta");
				String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";		    	  
			    r.add("TARIKH_WARTA", r.unquote(txdTarikhWarta));
			    r.add("KAWASAN_RIZAB", data.get("txtKawasanRizab"));
			    r.add("NO_SYIT", data.get("txtNoSyit"));
			    //r.add("NO_WARTA", data.get("txtNoWarta"));
			    r.add("NO_RIZAB", data.get("txtNoWarta"));
			    r.add("SEKATAN", data.get("txtSekatan"));	  
			    r.add("SYARAT", data.get("txtSyarat"));	  
			    r.add("HAKMILIK_ASAL", rs.getString("KOD_JENIS_HAKMILIK")+rs.getString("NO_HAKMILIKX0"));
			    //r.add("STATUS_SAH", "");
			    // by Rosli 03/05/2010
			    // r.add("STATUS_SAH", data.get("socStatus"));
			    // Azam change on 20/05/2010
			    //Default to D instead of ambil dari form, 
			    //sebab dalam form ialah S (Batal Sambungan
			    //Need to check if got any problems
			    r.add("STATUS_SAH","D");
			    //convert date before add
				String tarikhKemaskini = currentDate;
				String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
			    r.add("TARIKH_MASUK", r.unquote(txdTarikhKemaskini));
			    r.add("ID_MASUK",data.get("idKemaskini"));
			    r.add("ID_RIZAB", data.get("socJenisRizab"));    
			    r.add("STATUS_RIZAB", Utils.isNull(rs.getString("STATUS_RIZAB")));
			    // Tambahan field untuk simpan by Rosli 16/08/2010
			    //A.ID_AGENSI, A.ID_KEMENTERIAN,A.KEGUNAAN_TANAH, A.CATATAN 
			    r.add("ID_AGENSI", Utils.isNull(rs.getString("ID_AGENSI")));
			    r.add("ID_KEMENTERIAN", Utils.isNull(rs.getString("ID_KEMENTERIAN")));
			    r.add("KEGUNAAN_TANAH", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
			    r.add("CATATAN", Utils.isNull(rs.getString("CATATAN")));
			    sql = r.getSQLInsert("TBLHTPHAKMILIK");
			    log.info("addHakmilikBaruById:sql insert baru::"+sql);
				stmt.executeUpdate(sql);
			}
			//GET DATA LAMA FROM TBLHTPHAKMILIKAGENSI
			sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_AGENSI, TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN " +
					"FROM TBLHTPHAKMILIKAGENSI "+
		      		"WHERE TBLHTPHAKMILIKAGENSI.ID_HAKMILIK = ' "+idHakmilikAsal +"'";
			
			ResultSet rs2 = stmt.executeQuery(sql);
			//KALO ADA ROW BRU INSERT
			if (rs2.next()){
				//CREATE REKOD BARU 
				r = new SQLRenderer();
			    long idHakmilikAgensi = DB.getNextID("TBLHTPHAKMILIKAGENSI_SEQ"); 
			    r.add("ID_HAKMILIKAGENSI",idHakmilikAgensi);
			    r.add("ID_HAKMILIK",idHakmilikBaru);
			    r.add("ID_MASUK",data.get("idKemaskini"));
			    r.add("ID_AGENSI",rs2.getString("ID_AGENSI"));
			    r.add("ID_KEMENTERIAN",rs2.getString("ID_KEMENTERIAN"));
			    r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
			    sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
			    log.info("insert hakmilikagensi :"+sql);
			    stmt.executeUpdate(sql);
			}
			//rs2.next();
			    
		    
			//GET DATA LAMA FROM TBLHTPHAKMILIKCUKAI
			sql = "SELECT NVL(TPC.CUKAI,0)CUKAI,NVL(TPC.CUKAI_TERKINI,0)CUKAI_TERKINI, " +
					"NVL(TPC.KOD_BAYARAN_CUKAI,'P') KOD_BAYARAN_CUKAI " +
					"FROM TBLHTPHAKMILIKCUKAI TPC "+
		      		"WHERE TPC.ID_HAKMILIK = ' "+idHakmilikAsal +"'";
			ResultSet rs3 = stmt.executeQuery(sql);
			//KALO ADA ROW BARU INSERT
			if(rs3.next()){
				//CREATE REKOD BARU 
				sql = "INSERT INTO TBLHTPHAKMILIKCUKAI (ID_HAKMILIK, ID_MASUK, " +
						"CUKAI_TERKINI, CUKAI, KOD_BAYARAN_CUKAI, STATUS, TARIKH_MASUK)  " +
						"VALUES ('"+idHakmilikBaru+"',"+data.get("idKemaskini")+", " +
								"'"+rs3.getString("CUKAI_TERKINI")+"', " +
								"'"+rs3.getString("CUKAI")+"', " +
								"'"+rs3.getString("KOD_BAYARAN_CUKAI")+"', 'S',  SYSDATE )";
				
				/*r = new SQLRenderer();
			    long idHakmilikCukai = DB.getNextID("TBLHTPHAKMILIKCUKAI_SEQ"); 
			    r.add("ID_HAKMILIKCUKAI",idHakmilikCukai);
			    r.add("ID_HAKMILIK",idHakmilikBaru);
			    r.add("ID_MASUK",data.get("idKemaskini"));
			    r.add("CUKAI_TERKINI",rs3.getString("CUKAI_TERKINI"));
			    r.add("CUKAI",rs3.getString("CUKAI"));
			    r.add("KOD_BAYARAN_CUKAI",rs3.getString("KOD_BAYARAN_CUKAI"));
			    r.add("STATUS","S");
			    r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
			    sql = r.getSQLInsert("TBLHTPHAKMILIKCUKAI");
			    */
			    log.info("insert hakmilikcukai :"+sql);
			    stmt.executeUpdate(sql);
			}	
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
	//HAPUS HAKMILIK BARU BY ID
	public static void hapusHakmilikBaruById(String idHakmilikBaru) throws Exception {
		Connection conn = null;
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPHAKMILIKPERIHAL
			r.add("ID_HAKMILIK", idHakmilikBaru);

			sql = r.getSQLDelete("TBLHTPHAKMILIK");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}
}
