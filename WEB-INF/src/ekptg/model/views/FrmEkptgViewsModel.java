package ekptg.model.views;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FrmEkptgViewsModel {
	
	private static final Log log = LogFactory.getLog(FrmEkptgViewsModel.class);
	private String kodLot;
	private String noLot;
	private Long idPermohonan;
	private Long idFail;
	private String noFail;
	private String noHakmilik;
	private String tajukFail;
	private String status;
	private String namaUrusan;
	private String kodUrusan;
	private String keputusan;
	private String tarikhKeputusan;
	private Long idLaporantanah;
	private String namaPelapor;
	private String moduleClassDaftarphmn;
	private String moduleClassKeputusan;
	private String kodNoLot;
	private String idNegeri;
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Vector getViews() throws Exception {
		Vector beanMaklumatTanahDanLaporanTanah = beanMaklumatTanahDanLaporanTanahDB();
		return beanMaklumatTanahDanLaporanTanah;
	}

	public Vector getViewsByParam(FrmEkptgViewsModel param) throws Exception {
		Vector listMaklumatTanahDanLaporanTanahFiltered = new Vector();
		Vector listMaklumatTanahDanLaporanTanah = beanMaklumatTanahDanLaporanTanahDB();
		
		for(int i=0; i<listMaklumatTanahDanLaporanTanah.size(); i++){
			boolean whereClause = false;
			Hashtable hashMaklumatTanahDanLaporanTanah = (Hashtable) listMaklumatTanahDanLaporanTanah.get(i);
			
			String kodLotDB = "";
			String noLotDB = "";
			Long idPermohonanDB = null;
			Long idFailDB = null;
			String noFailDB = "";
			String noHakmilikDB = "";
			String tajukFailDB = "";
			String statusDB = "";
			String namaUrusanDB = "";
			String kodUrusanDB = "";
			String keputusanDB = "";
			String tarikhKeputusanDB = "";
			Long idLaporantanahDB = null;
			String namaPelaporDB = "";
			String moduleClassDaftarpmhnDB = "";
			String moduleClassKeputusanDB = "";
			String idNegeriDB = ""; 
			
			String kodNoLotDB = "";
			if(hashMaklumatTanahDanLaporanTanah.get("kodLot")!=null){
				kodLotDB = (String)hashMaklumatTanahDanLaporanTanah.get("kodLot");
				kodNoLotDB = kodLotDB;
			}
			if(hashMaklumatTanahDanLaporanTanah.get("noLot")!=null){
				noLotDB = (String)hashMaklumatTanahDanLaporanTanah.get("noLot");
				kodNoLotDB = kodNoLotDB + " " +noLotDB;
			}
			if(hashMaklumatTanahDanLaporanTanah.get("idPermohonan")!=null)
				idPermohonanDB = Long.parseLong(hashMaklumatTanahDanLaporanTanah.get("idPermohonan").toString());
			if(hashMaklumatTanahDanLaporanTanah.get("idFail")!=null)
				idFailDB = Long.parseLong(hashMaklumatTanahDanLaporanTanah.get("idFail").toString());
			if(hashMaklumatTanahDanLaporanTanah.get("tajukFail")!=null)
				tajukFailDB = (String)hashMaklumatTanahDanLaporanTanah.get("tajukFail");
			if(hashMaklumatTanahDanLaporanTanah.get("status")!=null)
				statusDB = (String)hashMaklumatTanahDanLaporanTanah.get("status");
			if(hashMaklumatTanahDanLaporanTanah.get("namaUrusan")!=null)
				namaUrusanDB = (String)hashMaklumatTanahDanLaporanTanah.get("namaUrusan");
			if(hashMaklumatTanahDanLaporanTanah.get("kodUrusan")!=null)
				kodUrusanDB = (String)hashMaklumatTanahDanLaporanTanah.get("kodUrusan");
			if(hashMaklumatTanahDanLaporanTanah.get("keputusan")!=null)
				keputusanDB = (String)hashMaklumatTanahDanLaporanTanah.get("keputusan");
			if(hashMaklumatTanahDanLaporanTanah.get("tarikhKeputusan")!=null)
				tarikhKeputusanDB = (String)hashMaklumatTanahDanLaporanTanah.get("tarikhKeputusan");
			if(hashMaklumatTanahDanLaporanTanah.get("idLaporantanah")!=null)
				idLaporantanahDB = Long.parseLong(hashMaklumatTanahDanLaporanTanah.get("idLaporantanah").toString());
			if(hashMaklumatTanahDanLaporanTanah.get("noFail")!=null)
				noFailDB = (String)hashMaklumatTanahDanLaporanTanah.get("noFail");
			if(hashMaklumatTanahDanLaporanTanah.get("noHakmilik")!=null)
				noHakmilikDB = (String)hashMaklumatTanahDanLaporanTanah.get("noHakmilik");
			if(hashMaklumatTanahDanLaporanTanah.get("idNegeri")!=null)
				idNegeriDB = (String)hashMaklumatTanahDanLaporanTanah.get("idNegeri");
			if(hashMaklumatTanahDanLaporanTanah.get("namaPelapor")!=null)
				namaPelaporDB = (String)hashMaklumatTanahDanLaporanTanah.get("namaPelapor");
			if(hashMaklumatTanahDanLaporanTanah.get("moduleClassDaftarpmhn")!=null)
				moduleClassDaftarpmhnDB = (String)hashMaklumatTanahDanLaporanTanah.get("moduleClassDaftarpmhn");
			if(hashMaklumatTanahDanLaporanTanah.get("moduleClassKeputusan")!=null)
				moduleClassKeputusanDB = (String)hashMaklumatTanahDanLaporanTanah.get("moduleClassKeputusan");
			

//			if(param.getKodLot()!=null && param.getKodLot().length()>0){
//				kodNoLot = param.getKodLot();
//				if(param.getKodLot().equals(kodLotDB) || kodLotDB.indexOf(param.getKodLot())>0){
//					whereClause = true;
//				}
//			}
//			if(param.getNoLot()!=null && param.getNoLot().length()>0){
//				kodNoLot = kodNoLot + " " +param.getNoLot();
//				if(param.getNoLot().equals(noLotDB) || kodLotDB.indexOf(param.getNoLot())>0)
//					whereClause = true;
//			}
			if(param.getKodNoLot()!=null && param.getKodNoLot().length()>0){
				if(param.getKodNoLot().equals(kodNoLotDB))
					whereClause = true;
			}
//			if(param.getIdPermohonan()!=null){
//				if(param.getIdPermohonan()==idPermohonanDB)
//					whereClause = true;
//			}
//			if(param.getIdFail()!=null){
//				if(param.getIdFail()==idFailDB)
//					whereClause = true;
//			}
			if(param.getNoFail()!=null && param.getNoFail().length()>0){
				if(param.getNoFail().equals(noFailDB) || noFailDB.indexOf(param.getNoFail())>0)
					whereClause = true;
			}
			
			if(param.getNoHakmilik()!=null && param.getNoHakmilik().length()>0){
				if(param.getNoHakmilik().equals(noHakmilikDB) || noHakmilikDB.indexOf(param.getNoHakmilik())>0)
					whereClause = true;
			}
			
			if(param.getIdNegeri()!=null && param.getIdNegeri().length()>0){
				if(param.getIdNegeri().equals(idNegeriDB) || idNegeriDB.indexOf(param.getIdNegeri())>0)
					whereClause = true;
			}
			
//			if(param.getTajukFail()!=null && param.getTajukFail().length()>0){
//				if(param.getTajukFail().equals(tajukFailDB) || tajukFailDB.indexOf(param.getTajukFail())>0)
//					whereClause = true;
//			}
//			if(param.getStatus()!=null && param.getStatus().length()>0){
//				if(param.getStatus().equals(statusDB) || statusDB.indexOf(param.getStatus())>0)
//					whereClause = true;
//			}
//			if(param.getNamaUrusan()!=null && param.getNamaUrusan().length()>0){
//				if(param.getNamaUrusan().equals(namaUrusanDB) || namaUrusanDB.indexOf(param.getNamaUrusan())>0)
//					whereClause = true;
//			}
//			if(param.getKodUrusan()!=null && param.getKodUrusan().length()>0){
//				if(param.getKodUrusan().equals(kodUrusanDB))
//					whereClause = true;
//			}
//			if(param.getKeputusan()!=null && param.getKeputusan().length()>0){
//				if(param.getKeputusan().equals(keputusanDB) || keputusanDB.indexOf(param.getKeputusan())>0)
//					whereClause = true;
//			}
//			if(param.getTarikhKeputusan()!=null && param.getTarikhKeputusan().length()>0){
//				if(param.getTarikhKeputusan().equals(tarikhKeputusanDB))
//					whereClause = true;
//			}
//			if(param.getIdLaporantanah()!=null){
//				if(param.getIdLaporantanah().equals(idLaporantanahDB))
//					whereClause = true;
//			}
//			if(param.getNamaPelapor()!=null && param.getNamaPelapor().length()>0){
//				if(param.getNamaPelapor().equals(namaPelaporDB))
//					whereClause = true;
//			}
//			if(param.getModuleClassDaftarphmn()!=null && param.getModuleClassDaftarphmn().length()>0){
//				if(param.getModuleClassDaftarphmn().equals(moduleClassDaftarpmhnDB))
//					whereClause = true;
//			}
//			if(param.getModuleClassKeputusan()!=null && param.getModuleClassKeputusan().length()>0){
//				if(param.getModuleClassKeputusan().equals(moduleClassKeputusanDB))
//					whereClause = true;
//			}
			
			if(whereClause){
				listMaklumatTanahDanLaporanTanahFiltered.addElement(hashMaklumatTanahDanLaporanTanah);
			}
		}
		
		return listMaklumatTanahDanLaporanTanahFiltered;
	}
	
	private Vector beanMaklumatTanahDanLaporanTanahDB() throws Exception{
		Db db = null;
		String sql = "";
		Vector beanMaklumatTanahDanLaporanTanah = new Vector();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.KOD_LOT, A.NO_LOT, A.ID_PERMOHONAN, A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL, A.STATUS,"
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
				beanMaklumatTanahDanLaporanTanah.addElement(h);
				bil++;
			}
			
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		
		return beanMaklumatTanahDanLaporanTanah;
	}

	public String getKodLot() {
		return kodLot;
	}

	public void setKodLot(String kodLot) {
		this.kodLot = kodLot;
	}

	public String getNoLot() {
		return noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public Long getIdPermohonan() {
		return idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdFail() {
		return idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public String getNoFail() {
		return noFail;
	}

	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}

	public String getTajukFail() {
		return tajukFail;
	}

	public void setTajukFail(String tajukFail) {
		this.tajukFail = tajukFail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNamaUrusan() {
		return namaUrusan;
	}

	public void setNamaUrusan(String namaUrusan) {
		this.namaUrusan = namaUrusan;
	}

	public String getKodUrusan() {
		return kodUrusan;
	}

	public void setKodUrusan(String kodUrusan) {
		this.kodUrusan = kodUrusan;
	}

	public String getKeputusan() {
		return keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}

	public String getTarikhKeputusan() {
		return tarikhKeputusan;
	}

	public void setTarikhKeputusan(String tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}

	public Long getIdLaporantanah() {
		return idLaporantanah;
	}

	public void setIdLaporantanah(Long idLaporantanah) {
		this.idLaporantanah = idLaporantanah;
	}

	public String getNamaPelapor() {
		return namaPelapor;
	}

	public void setNamaPelapor(String namaPelapor) {
		this.namaPelapor = namaPelapor;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public String getModuleClassDaftarphmn() {
		return moduleClassDaftarphmn;
	}

	public void setModuleClassDaftarphmn(String moduleClassDaftarphmn) {
		this.moduleClassDaftarphmn = moduleClassDaftarphmn;
	}

	public String getModuleClassKeputusan() {
		return moduleClassKeputusan;
	}

	public void setModuleClassKeputusan(String moduleClassKeputusan) {
		this.moduleClassKeputusan = moduleClassKeputusan;
	}

	public String getKodNoLot() {
		return kodNoLot;
	}

	public void setKodNoLot(String kodNoLot) {
		this.kodNoLot = kodNoLot;
	}

	public String getNoHakmilik() {
		return noHakmilik;
	}

	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}

	public String getIdNegeri() {
		return idNegeri;
	}

	public void setIdNegeri(String idNegeri) {
		this.idNegeri = idNegeri;
	}

}
