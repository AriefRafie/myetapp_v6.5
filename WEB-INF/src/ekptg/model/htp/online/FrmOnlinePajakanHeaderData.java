package ekptg.model.htp.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;
/**
 * 
 *
 */
public class FrmOnlinePajakanHeaderData {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.online.FrmOnlinePajakanHeaderData.class);
	private Vector<Hashtable<String, String>> beanMaklumatPermohonan = null;
	private Vector<Hashtable<String,String>> beanMaklumatPemohon = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPermohonan = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable<String, String> h;
			sql = "SELECT B.FLAG_MOHON_FAIL, A.ID_FAIL, A.NO_FAIL, A.ID_SUBURUSAN, A.TAJUK_FAIL "
				+" ,B.ID_PERMOHONAN,B.NO_PERMOHONAN, B.TARIKH_SURAT,B.ID_STATUS "
				+" ,C.NO_RUJUKAN_KJP, C.NO_RUJUKAN_LAIN, C.TARIKH_AGIHAN, C.TARIKH_SURAT_RUJUKAN_LAIN "
				+" ,D.ID_NEGERI, D.NAMA_NEGERI "
				+" ,E.ID_KEMENTERIAN, E.NAMA_KEMENTERIAN "
				+" ,F.ID_AGENSI, F.NAMA_AGENSI " 
				+" ,G.NAMA_SUBURUSAN "
				+" ,H.ID_JENISTANAH, H.KETERANGAN "
				+" ,I.ID_TARAFKESELAMATAN, I.TARAF_KESELAMATAN "
				+" ,J.KETERANGAN AS STATUS "
				+" ,(SELECT COUNT(UL1.ID_ULASANKJP) FROM TBLHTPULASANKJP UL1 WHERE UL1.ID_PERMOHONAN = B.ID_PERMOHONAN)AS BIL_ULASANKJP "
				+" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C, TBLRUJNEGERI D "
				+" ,TBLRUJKEMENTERIAN E, TBLRUJAGENSI F, TBLRUJSUBURUSAN G, TBLRUJJENISTANAH H " 
				+" ,TBLPFDRUJTARAFKESELAMATAN I, TBLRUJSTATUS J"
				+" WHERE B.ID_PERMOHONAN = C.ID_PERMOHONAN AND B.ID_FAIL = A.ID_FAIL "
				+" AND A.ID_NEGERI = D.ID_NEGERI AND A.ID_KEMENTERIAN = E.ID_KEMENTERIAN "
				+" AND C.ID_AGENSI = F.ID_AGENSI(+) AND A.ID_SUBURUSAN = G.ID_SUBURUSAN"
				+" AND C.ID_JENISTANAH = H.ID_JENISTANAH(+) AND A.ID_TARAFKESELAMATAN = I.ID_TARAFKESELAMATAN(+) "
				+" AND B.ID_STATUS = J.ID_STATUS(+) AND A.ID_FAIL = '" + idFail + "'"
				+" ";		
			myLog.info("setMaklumatPermohonan : " + sql);
			ResultSet rs = stmt.executeQuery(sql);	
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "0" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("idNegeriTanah", rs.getString("ID_NEGERI"));
				
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "0" : rs.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "0" : rs.getString("ID_AGENSI").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("urusan", "PAJAKAN");
				h.put("subUrusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				h.put("namaSubUrusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("idStatusTanah", rs.getString("ID_JENISTANAH") == null ? "0" : rs.getString("ID_JENISTANAH").toUpperCase());
				h.put("statusTanah", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("idJenisFail", rs.getString("ID_TARAFKESELAMATAN") == null ? "0" : rs.getString("ID_TARAFKESELAMATAN").toUpperCase());
				h.put("jenisFail", rs.getString("TARAF_KESELAMATAN") == null ? "" : rs.getString("TARAF_KESELAMATAN").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noP", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("noFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("tarikhSuratKJP", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("tarikhAgihan", rs.getDate("TARIKH_AGIHAN") == null ? "" : sdf.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("tarikhSuratPemohon", rs.getDate("TARIKH_SURAT_RUJUKAN_LAIN") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT_RUJUKAN_LAIN")));
				h.put("tajuk", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				h.put("status", rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
				h.put("flagMohonFail", rs.getString("FLAG_MOHON_FAIL") == null ? "" : rs.getString("FLAG_MOHON_FAIL").toUpperCase());
				h.put("bil_ulasankjp", String.valueOf(rs.getInt("BIL_ULASANKJP") == 0 ? 0 : rs.getInt("BIL_ULASANKJP")));
				
				beanMaklumatPermohonan.addElement(h);
				bil++;
				
			}

		} finally {
			if (db != null)
			db.close();
		
		}
		
	}

	public Vector<Hashtable<String, String>> getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector<Hashtable<String, String>> beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}	
	
	//TAMBAH UTK v6.5
	public Vector<Hashtable<String, String>> setMaklumatPemohon(String idUser) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPemohon = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			Hashtable<String, String> h;
			
			sql = " SELECT U.USER_ID, U.USER_NAME, UPPER(UO.KATEGORI) AS KATEGORI, UO.NO_KP_BARU, UO.NO_FAX, UO.NO_HP, UO.EMEL"+
				", UO.ALAMAT1, UO.ALAMAT2, UO.ALAMAT3,NVL(UO.ID_NEGERI,0) ID_NEGERI,NVL(UO.ID_BANDAR,0) ID_BANDAR  " +
				", UO.POSKOD, RB.KETERANGAN AS NAMA_BANDAR, RN.NAMA_NEGERI  " +
				  " FROM USERS U,USERS_ONLINE UO, TBLRUJNEGERI RN, TBLRUJBANDAR RB " +
				  " WHERE U.USER_ID = UO.USER_ID "+
				  " AND UO.ID_BANDAR = RB.ID_BANDAR(+) "+
				  " AND UO.ID_NEGERI = RN.ID_NEGERI(+) " +
				  " AND U.USER_ID = '" + idUser + "'";
					
			myLog.info("setMaklumatPermohonan : " + sql);
			ResultSet rs = stmt.executeQuery(sql);	
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("iduser", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("idBandar", rs.getString("ID_BANDAR"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("USER_ID"));
				h.put("kategoriPemohon", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("namaPemohon", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				h.put("noPengenalan", rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTel", rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX").toUpperCase());
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
					
				beanMaklumatPemohon.addElement(h);
				
			}

		} finally {
			if (db != null)
			db.close();
		}
		return beanMaklumatPemohon;
	}

	public void setMaklumatPermohonan(String idFail, String initiateFlagBuka,
			HttpSession session) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPermohonan = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable<String, String> h;
			
			if ("".equals(idFail) && session.getAttribute("ID_FAIL") != null) {
				idFail = (String) session.getAttribute("ID_FAIL");
			}
			
			sql = "SELECT B.FLAG_MOHON_FAIL, A.ID_FAIL, A.NO_FAIL, A.ID_SUBURUSAN, A.TAJUK_FAIL "
					+" ,B.ID_PERMOHONAN,B.NO_PERMOHONAN, B.TARIKH_SURAT,B.ID_STATUS "
					+" ,C.NO_RUJUKAN_KJP, C.NO_RUJUKAN_LAIN, C.TARIKH_AGIHAN, C.TARIKH_SURAT_RUJUKAN_LAIN "
					+" ,D.ID_NEGERI, D.NAMA_NEGERI "
					+" ,E.ID_KEMENTERIAN, E.NAMA_KEMENTERIAN "
					+" ,F.ID_AGENSI, F.NAMA_AGENSI " 
					+" ,G.NAMA_SUBURUSAN "
					+" ,H.ID_JENISTANAH, H.KETERANGAN "
					+" ,I.ID_TARAFKESELAMATAN, I.TARAF_KESELAMATAN "
					+" ,J.KETERANGAN AS STATUS "
					+" ,(SELECT COUNT(UL1.ID_ULASANKJP) FROM TBLHTPULASANKJP UL1 WHERE UL1.ID_PERMOHONAN = B.ID_PERMOHONAN)AS BIL_ULASANKJP "
					+" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C, TBLRUJNEGERI D "
					+" ,TBLRUJKEMENTERIAN E, TBLRUJAGENSI F, TBLRUJSUBURUSAN G, TBLRUJJENISTANAH H " 
					+" ,TBLPFDRUJTARAFKESELAMATAN I, TBLRUJSTATUS J"
					+" WHERE B.ID_PERMOHONAN = C.ID_PERMOHONAN AND B.ID_FAIL = A.ID_FAIL "
					+" AND A.ID_NEGERI = D.ID_NEGERI AND A.ID_KEMENTERIAN = E.ID_KEMENTERIAN "
					+" AND C.ID_AGENSI = F.ID_AGENSI(+) AND A.ID_SUBURUSAN = G.ID_SUBURUSAN"
					+" AND C.ID_JENISTANAH = H.ID_JENISTANAH(+) AND A.ID_TARAFKESELAMATAN = I.ID_TARAFKESELAMATAN(+) "
					+" AND B.ID_STATUS = J.ID_STATUS(+) AND A.ID_FAIL = '" + idFail + "'"
					+" ";
			myLog.info("setMaklumatPermohonan : " + sql);
			ResultSet rs = stmt.executeQuery(sql);	
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
				//h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "0" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("idNegeriTanah", rs.getString("ID_NEGERI"));
				
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "0" : rs.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "0" : rs.getString("ID_AGENSI").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("urusan", "PAJAKAN");
				h.put("subUrusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				h.put("namaSubUrusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("idStatusTanah", rs.getString("ID_JENISTANAH") == null ? "0" : rs.getString("ID_JENISTANAH").toUpperCase());
				h.put("statusTanah", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("idJenisFail", rs.getString("ID_TARAFKESELAMATAN") == null ? "0" : rs.getString("ID_TARAFKESELAMATAN").toUpperCase());
				h.put("jenisFail", rs.getString("TARAF_KESELAMATAN") == null ? "" : rs.getString("TARAF_KESELAMATAN").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noP", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("noFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("tarikhSuratKJP", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("tarikhAgihan", rs.getDate("TARIKH_AGIHAN") == null ? "" : sdf.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("tarikhSuratPemohon", rs.getDate("TARIKH_SURAT_RUJUKAN_LAIN") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT_RUJUKAN_LAIN")));
				h.put("tajuk", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				h.put("status", rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
				h.put("flagMohonFail", rs.getString("FLAG_MOHON_FAIL") == null ? "" : rs.getString("FLAG_MOHON_FAIL").toUpperCase());
				h.put("bil_ulasankjp", String.valueOf(rs.getInt("BIL_ULASANKJP") == 0 ? 0 : rs.getInt("BIL_ULASANKJP")));
				
				beanMaklumatPermohonan.addElement(h);
				bil++;
				
			}

		} finally {
			if (db != null)
			db.close();
		
		}
		
	}
}
