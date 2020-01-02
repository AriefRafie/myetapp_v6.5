package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmRekodBatalHakmilikData {
	
	private static Logger log = Logger.getLogger(FrmRekodBatalHakmilikData.class);
	
	private static Vector listCarianHakmilikBatal = null;
	private static Vector listHakmilikBatal = null;
	private static Vector listMaklumatFail = null;
	private static Vector listHakmilikSambunganById = null;
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	public static Vector getCarianSenaraiHakmilikBatalById(String idJenisTanah,String idNegeri, String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi)throws Exception {
	    Db db = null;
	    listCarianHakmilikBatal = new Vector();
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
//          "CASE "+
//          "WHEN A.STATUS_SAH = 'H' "+
//              "THEN 'HAKMILIK ASAL TIADA' "+
//           "WHEN A.STATUS_SAH = 'D' "+
//              "THEN 'DAFTAR' "+
//           "WHEN A.STATUS_SAH = 'P' "+
//              "THEN 'BATAL PELEPASAN' "+
//           "WHEN A.STATUS_SAH = 'T' "+
//              "THEN 'BATAL TUKAR GUNA' "+ 
//           "WHEN A.STATUS_SAH = 'S' "+
//              "THEN 'BATAL SAMBUNGAN' "+
//           "ELSE 'TIADA' "+
          "CASE "+
          "WHEN A.STATUS_SAH = 'H' "+
              "THEN 'HAKMILIK ASAL TIADA' "+
           "WHEN A.STATUS_SAH = 'D' "+
              "THEN 'DAFTAR' "+
           "WHEN A.STATUS_SAH = 'P' "+
              "THEN 'BATAL' "+
           "WHEN A.STATUS_SAH = 'T' "+
              "THEN 'BATAL' "+ 
           "WHEN A.STATUS_SAH = 'S' "+
              "THEN 'BATAL' "+
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
           "AND (A.STATUS_SAH IN ('P')) ";
        
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
		sql = sql +"ORDER BY A.TARIKH_TERIMA DESC ";
		log.info("carian "+sql);
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

  	  listCarianHakmilikBatal.addElement(h);
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
  	  
  	listCarianHakmilikBatal.addElement(h);
    }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }		
	    return listCarianHakmilikBatal;
	}	
	// PAPAR SENARAI FAIL HAKMILIK SEMENTARA
	public static Vector getSenaraiHakmilikSambungan(String noHakmilikAsal) throws Exception {

		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		

		try {
			db = new Db();
			listHakmilikSambunganById = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLHTPHAKMILIK.ID_HAKMILIK,TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK, TBLHTPHAKMILIK.NO_HAKMILIK, "+
				  "(SELECT ID_HAKMILIK FROM TBLHTPHAKMILIK WHERE NO_HAKMILIK LIKE '%20000003%') AS ID_HAKMILIK_ASAL "+
				  "FROM TBLHTPHAKMILIK,TBLRUJJENISHAKMILIK "+
				  "WHERE TBLHTPHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK "+
				  "AND TBLHTPHAKMILIK.HAKMILIK_ASAL LIKE '%"+noHakmilikAsal+"%' ";			
			
			log.info("gaga :" +sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				h.put("idHakmilikAsal", rs.getString("ID_HAKMILIK_ASAL")==null ? "" :rs.getString("ID_HAKMILIK_ASAL"));
				h.put("idHakmilikBaru", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("kodJenisBaru", rs.getString("KOD_JENIS_HAKMILIK")==null ? "" :rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("noHakmilikBaru", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));			
				listHakmilikSambunganById.addElement(h);
		    	bil++;
		    	count++;
			} 		
			return listHakmilikSambunganById;
		} finally {
			if (db != null)
				db.close();
		}
	}			
	// RETURN SENARI FAIL
	public static Vector getPaparSenaraiHakmilikBatal() throws Exception {
		//return listSenaraiFail;
		return listCarianHakmilikBatal;
	}
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
	// PAPAR HAKMILIK DAN RIZAB BY ID
	public static Vector getPaparBatalHakmilikById(int id) throws Exception {

		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listHakmilikBatal = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_HAKMILIK, A.TARIKH_TERIMA, A.TARIKH_DAFTAR, A.NO_HAKMILIK, "+
		       	  "A.ID_NEGERI, A.ID_DAERAH, A.ID_MUKIM, A.ID_JENISHAKMILIK, A.CUKAI, "+
		       	  "A.LOKASI,A.LUAS_LAMA,A.ID_LUASLAMA,A.LUAS,A.ID_LUAS, " +
		       	  "A.NO_PELAN, A.ID_LOT, A.ID_RIZAB, A.ID_KATEGORI, "+
		       	  "A.TEMPOH, A.SYARAT, A.HAKMILIK_ASAL, A.NO_FAIL_JOPA, A.TARAF_HAKMILIK, "+
		       	  "A.TARIKH_LUPUT, A.CUKAI_TERKINI, B.KEGUNAAN_TANAH, A.NO_PU, "+
		       	  "A.TARIKH_WARTA, A.KAWASAN_RIZAB, A.NO_SYIT, A.NO_WARTA, A.SEKATAN, "+	
		       	  "A.HAKMILIK_BERIKUT, A.STATUS_SAH, A.TARIKH_KEMASKINI, A.STATUS_RIZAB, "+
		       	  "A.NO_RIZAB,A.TARIKH_RIZAB,A.NO_BANGUNAN,NO_TINGKAT,NO_PETAK,A.ID_KEMASKINI, "+
		       	  "S.USER_NAME, A.CATATAN, A.HAKMILIK_ASAL "+
				  "FROM TBLHTPHAKMILIK A, TBLHTPHAKMILIKPERIHAL B,USERS S "+
				  "WHERE A.ID_HAKMILIK = ' "+id +"' " +
				  "AND A.ID_KEMASKINI = S.USER_ID(+) "+
				  "AND A.ID_HAKMILIK = B.ID_HAKMILIK(+)";
			
			ResultSet rs = stmt.executeQuery(sql);
			log.info("maklumat detail hakmilik xxx :"+sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idHakmilik", rs.getString("id_Hakmilik"));
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
				h.put("idLuasLama", rs.getString("ID_LUASLAMA")==null ? "":rs.getString("ID_LUASLAMA"));
				h.put("luasLama", rs.getString("LUAS_LAMA")==null ? "":rs.getString("LUAS_LAMA"));
				//LUAS CONVERT
				h.put("luasConvert", rs.getString("LUAS")==null ? "":rs.getString("LUAS"));
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
				h.put("socRizab", rs.getString("STATUS_RIZAB")==null ? "":rs.getString("STATUS_RIZAB"));
				h.put("userName", rs.getString("USER_NAME")==null ? "":rs.getString("USER_NAME"));
				h.put("txtNoHakmilikAsal", rs.getString("HAKMILIK_ASAL")==null ? "":rs.getString("HAKMILIK_ASAL"));
				h.put("txtKemAgenTerkini", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
				listHakmilikBatal.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return listHakmilikBatal;
	}

}
