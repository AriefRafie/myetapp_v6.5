package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import ekptg.model.views.FrmEkptgViewsModel;

public class FrmMaklumatTanahDanLaporanTanahData {

	FrmEkptgViewsModel model = new FrmEkptgViewsModel();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Vector beanMaklumatTanahDanLaporanTanah = null;
	private Vector senarai = null;
	
	public Vector setSenaraiMaklumatTanahDanLaporanTanah() throws Exception {
		Vector beanMaklumatTanahDanLaporanTanah = model.getViews();
		return beanMaklumatTanahDanLaporanTanah;
	}

	public Vector setSenaraiMaklumatTanahDanLaporanTanahByParam(FrmEkptgViewsModel param) throws Exception {
		Vector beanMaklumatTanahDanLaporanTanah = model.getViewsByParam(param);
		return beanMaklumatTanahDanLaporanTanah;
	}
	
	
	
public void carian(String noFail,String idNegeri,String idDaerah,String noHakmilik,String kodLot, String idKementerian , String idMukim) throws Exception{
		
		Db db = null;
		String sql = "";
		
		
		try {
			senarai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.KOD_LOT, A.NO_LOT , A.ID_PERMOHONAN, A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL, A.STATUS,"
					+ " A.NAMA_URUSAN, A.KOD_URUSAN, A.KEPUTUSAN, A.TARIKH_KEPUTUSAN, A.ID_LAPORANTANAH, A.NAMA_PELAPOR, A.MODULE_CLASS_DAFTARPMHNN,  " 
					+ " A.MODULE_CLASS_KEPUTUSAN ,b.ID_DAERAH ,b.ID_NEGERI ,b.ID_KEMENTERIAN , A.ID_MUKIM" 
					+ " FROM V_MAKLUMATTNH_DAN_LAPORANTNH A,tblpfdfail b, tblrujdaerah c, tblrujnegeri d,tblrujkementerian e ,  tblrujmukim f"
					+ " where a.ID_FAIL = b.ID_FAIL and"
					+ " b.ID_DAERAH = c.ID_DAERAH(+) and"
					+ " b.ID_NEGERI = d.ID_NEGERI(+) and A.ID_MUKIM = f.id_mukim and"
					+ " b.ID_KEMENTERIAN = e.ID_KEMENTERIAN(+)";
			
					// noFail
						if (noFail != null) {
							if (!noFail.trim().equals("")) {
								sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
										+ noFail.trim().toUpperCase() + "'|| '%'";
							}
						}

			
						// idNegeri
						if (idNegeri != null) {
							if (!idNegeri.trim().equals("")
									&& !idNegeri.trim().equals("99999")) {
								sql = sql + " AND B.ID_NEGERI = '" + idNegeri.trim() + "'";
							}
						}

						// idDaerah
						if (idDaerah != null) {
							if (!idDaerah.trim().equals("")
									&& !idDaerah.trim().equals("99999")) {
								sql = sql + " AND B.ID_DAERAH = '" + idDaerah.trim() + "'";
							}
						}
						
						// idKementerian
						if (idKementerian != null) {
							if (!idKementerian.trim().equals("")
									&& !idKementerian.trim().equals("99999")) {
								sql = sql + " AND B.ID_KEMENTERIAN = '"
										+ idKementerian.trim() + "'";
							}
						}
						
						// idmukim
						if (idMukim != null) {
							if (!idMukim.trim().equals("")
									&& !idMukim.trim().equals("99999")) {
								sql = sql + " AND A.ID_MUKIM = '"
										+ idMukim.trim() + "'";
							}
						}
						
						// noHakmilik
						if (noHakmilik != null) {
							if (!noHakmilik.trim().equals("")) {
								sql = sql + " AND UPPER( A.NO_LOT) LIKE '%' ||'"
										+ noHakmilik.trim().toUpperCase() + "'|| '%'";
							}
						}
						
						// noHakmilik
						if (kodLot != null) {
							if (!kodLot.trim().equals("")) {
								sql = sql + " AND UPPER( A.KOD_LOT) LIKE '%' ||'"
										+ kodLot.trim().toUpperCase() + "'|| '%'";
							}
						}



						// idLot
						if (kodLot != null) {
							if (!kodLot.trim().equals("") && !kodLot.trim().equals("99999")) {
								sql = sql + " AND A.KOD_LOT = '" + kodLot.trim() + "'";
							}
						}
			
			ResultSet rs = stmt.executeQuery(sql);
			
			int bil = 1;
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDaerahC", rs
						.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("idNegeriC", rs
						.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("idKementerianC", rs
						.getString("ID_KEMENTERIAN") == null ? "" : rs
						.getString("ID_KEMENTERIAN").toUpperCase());		
				h.put("idMukimC", rs
						.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());		
				h.put("kodLot", rs
						.getString("KOD_LOT") == null ? "" : rs
						.getString("KOD_LOT").toUpperCase());
				h.put("noLot", rs
						.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("idPermohonan", rs
						.getString("ID_PERMOHONAN") == null ? "" : rs
						.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idFail", rs
						.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("noFail", rs
						.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("tajukFail", rs
						.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("status", rs
						.getString("STATUS") == null ? "" : rs
						.getString("STATUS").toUpperCase());
				h.put("namaUrusan", rs
						.getString("NAMA_URUSAN") == null ? "" : rs
						.getString("NAMA_URUSAN").toUpperCase());
				h.put("kodUrusan", rs
						.getString("KOD_URUSAN") == null ? "" : rs
						.getString("KOD_URUSAN").toUpperCase());
				h.put("keputusan", rs
						.getString("KEPUTUSAN") == null ? "" : rs
						.getString("KEPUTUSAN").toUpperCase());
				h.put("tarikhKeputusan", rs
						.getString("TARIKH_KEPUTUSAN") == null ? "" :sdf
								.format(rs.getDate("TARIKH_KEPUTUSAN")));				
				h.put("idLaporantanah", rs
						.getString("ID_LAPORANTANAH") == null ? "" : rs
						.getString("ID_LAPORANTANAH").toUpperCase());
				h.put("namaPelapor", rs
						.getString("NAMA_PELAPOR") == null ? "" : rs
						.getString("NAMA_PELAPOR").toUpperCase());
				h.put("moduleClassDaftarpmhn", rs
						.getString("MODULE_CLASS_DAFTARPMHNN") == null ? "" : rs
						.getString("MODULE_CLASS_DAFTARPMHNN"));
				h.put("moduleClassKeputusan", rs
						.getString("MODULE_CLASS_KEPUTUSAN") == null ? "" : rs
						.getString("MODULE_CLASS_KEPUTUSAN"));
				h.put("bil", bil);
				senarai.addElement(h);
				bil++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
public void carian1() throws Exception{
		
		Db db = null;
		String sql = "";
		
		
		try {
			senarai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.KOD_LOT, A.NO_LOT , A.ID_PERMOHONAN, A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL, A.STATUS,"
					+ " A.NAMA_URUSAN, A.KOD_URUSAN, A.KEPUTUSAN, A.TARIKH_KEPUTUSAN, A.ID_LAPORANTANAH, A.NAMA_PELAPOR, A.MODULE_CLASS_DAFTARPMHNN,  " 
					+ " A.MODULE_CLASS_KEPUTUSAN ,b.ID_DAERAH ,b.ID_NEGERI ,b.ID_KEMENTERIAN" 
					+ " FROM V_MAKLUMATTNH_DAN_LAPORANTNH A,tblpfdfail b, tblrujdaerah c, tblrujnegeri d,tblrujkementerian e "
					+ " where a.ID_FAIL = b.ID_FAIL and"
					+ " b.ID_DAERAH = c.ID_DAERAH(+) and"
					+ " b.ID_NEGERI = d.ID_NEGERI(+) and"
					+ " b.ID_KEMENTERIAN = e.ID_KEMENTERIAN(+)";
			
				
			
			ResultSet rs = stmt.executeQuery(sql);
			
			int bil = 1;
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDaerahC", rs
						.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("idNegeriC", rs
						.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("idKementerianC", rs
						.getString("ID_KEMENTERIAN") == null ? "" : rs
						.getString("ID_KEMENTERIAN").toUpperCase());				
				h.put("kodLot", rs
						.getString("KOD_LOT") == null ? "" : rs
						.getString("KOD_LOT").toUpperCase());
				h.put("noLot", rs
						.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("idPermohonan", rs
						.getString("ID_PERMOHONAN") == null ? "" : rs
						.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idFail", rs
						.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("noFail", rs
						.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("tajukFail", rs
						.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("status", rs
						.getString("STATUS") == null ? "" : rs
						.getString("STATUS").toUpperCase());
				h.put("namaUrusan", rs
						.getString("NAMA_URUSAN") == null ? "" : rs
						.getString("NAMA_URUSAN").toUpperCase());
				h.put("kodUrusan", rs
						.getString("KOD_URUSAN") == null ? "" : rs
						.getString("KOD_URUSAN").toUpperCase());
				h.put("keputusan", rs
						.getString("KEPUTUSAN") == null ? "" : rs
						.getString("KEPUTUSAN").toUpperCase());
				h.put("tarikhKeputusan", rs
						.getString("TARIKH_KEPUTUSAN") == null ? "" :sdf
								.format(rs.getDate("TARIKH_KEPUTUSAN")));				
				h.put("idLaporantanah", rs
						.getString("ID_LAPORANTANAH") == null ? "" : rs
						.getString("ID_LAPORANTANAH").toUpperCase());
				h.put("namaPelapor", rs
						.getString("NAMA_PELAPOR") == null ? "" : rs
						.getString("NAMA_PELAPOR").toUpperCase());
				h.put("moduleClassDaftarpmhn", rs
						.getString("MODULE_CLASS_DAFTARPMHNN") == null ? "" : rs
						.getString("MODULE_CLASS_DAFTARPMHNN"));
				h.put("moduleClassKeputusan", rs
						.getString("MODULE_CLASS_KEPUTUSAN") == null ? "" : rs
						.getString("MODULE_CLASS_KEPUTUSAN"));
				h.put("bil", bil);
				senarai.addElement(h);
				bil++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}

public Vector getSenarai() {
	return senarai;
}

public void setSenarai(Vector senarai) {
	this.senarai = senarai;
}


	
	
	
	

}
