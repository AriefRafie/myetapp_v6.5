package ekptg.model.php2.modelTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class TblPhpMesyuaratModel {
	
	//DB columns
	private Long idMesyuarat;
	private Long idPermohonan;
	private String tajuk;
	private String bilMesyuarat;
	private String tarikhMesyuarat;
	private String jamDari;
	private String minitDari;
	private String jamHIngga;
	private String minitHingga;
	private Long idLokasi;
	private String catatan;
	private String flagSyor;
	private String flagMesyuarat; 
	private String jamMesyuaratDari;
	private String jamMesyuaratHingga;
	private String minitMesyuaratDari;
	private String minitMesyuaratHingga;
	private Long idFail;
	private String tujuan;
	private String ulasanKeputusan;
	private String tindakanMesyuarat;
	private String waktuMesyuaratDari;
	private String waktuMesyuaratHingga;
	private Long idOld;
	private Long idRujmesyuarat;
	private String pengerusiMesyuarat;
	
	//non-DB columns
	private String flagPost;
	
	public String save(TblPhpMesyuaratModel model, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		
		Long idMesyuarat = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(model.getIdPermohonan()!=null){
				r.add("ID_PERMOHONAN", model.getIdPermohonan());
			}
			if(model.getTajuk()!=null){
				r.add("TAJUK", model.getTajuk());
			}
			if(model.getBilMesyuarat()!=null){
				r.add("BIL_MESYUARAT", model.getBilMesyuarat());
			}
			if(model.getTarikhMesyuarat()!=null){
				r.add("TARIKH_MESYUARAT", r.unquote(StringToDate(model.getTarikhMesyuarat())));
			}
			if(model.getJamDari()!=null){
				r.add("JAM_DARI", model.getJamDari());
			}
			if(model.getMinitDari()!=null){
				r.add("MINIT_DARI", model.getMinitDari());
			}
			if(model.getJamHIngga()!=null){
				r.add("JAM_HINGGA", model.getJamHIngga());
			}
			if(model.getMinitHingga()!=null){
				r.add("MINIT_HINGGA", model.getMinitHingga());
			}
			if(model.getIdLokasi()!=null){
				r.add("ID_LOKASI", model.getIdLokasi());
			}
			if(model.getCatatan()!=null){
				r.add("CATATAN", model.getCatatan());
			}
			if(model.getFlagSyor()!=null){
				r.add("FLAG_SYOR", model.getFlagSyor());
			}
			if(model.getFlagMesyuarat()!=null){
				r.add("FLAG_MESYUARAT", model.getFlagMesyuarat());
			}
			if(model.getJamMesyuaratDari()!=null){
				r.add("JAM_MESYUARAT_DARI", model.getJamMesyuaratDari());
			}
			if(model.getMinitMesyuaratDari()!=null){
				r.add("MINIT_MESYUARAT_DARI", model.getMinitMesyuaratDari());
			}
			if(model.getJamMesyuaratHingga()!=null){
				r.add("JAM_MESYUARAT_HINGGA", model.getJamMesyuaratHingga());
			}
			if(model.getMinitMesyuaratHingga()!=null){
				r.add("MINIT_MESYUARAT_HINGGA", model.getMinitMesyuaratHingga());
			}
			if(model.getIdFail()!=null){
				r.add("ID_FAIL", model.getIdFail());
			}
			if(model.getTujuan()!=null){
				r.add("TUJUAN", model.getTujuan());
			}
			if(model.getUlasanKeputusan()!=null){
				r.add("ULASAN_KEPUTUSAN", model.getUlasanKeputusan());
			}
			if(model.getTindakanMesyuarat()!=null){
				r.add("TINDAKAN_MESYUARAT", model.getTindakanMesyuarat());
			}
			if(model.getWaktuMesyuaratDari()!=null){
				r.add("WAKTU_MESYUARAT_DARI", model.getWaktuMesyuaratDari());
			}
			if(model.getWaktuMesyuaratHingga()!=null){
				r.add("WAKTU_MESYUARAT_HINGGA", model.getWaktuMesyuaratHingga());
			}
			if(model.getIdOld()!=null){
				r.add("ID_OLD", model.getIdOld());
			}
			if(model.getIdMesyuarat()!=null){
				r.add("ID_RUJMESYUARAT", model.getIdMesyuarat());
			}
			if(model.getPengerusiMesyuarat()!=null){
				r.add("PENGERUSI_MESYUARAT", model.getPengerusiMesyuarat());
			}
			
			if(model.getFlagPost().equals("insert")){
				idMesyuarat = DB.getNextID("TBLPHPMESYUARAT_SEQ");
				r.add("ID_MESYUARAT", idMesyuarat);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				
				sql = r.getSQLInsert("TBLPHPMESYUARAT");
			} else if(model.getFlagPost().equals("update")){
				idMesyuarat = model.getIdMesyuarat();
				r.update("ID_MESYUARAT", idMesyuarat);
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				
				sql = r.getSQLUpdate("TBLPHPMESYUARAT");
			}
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		
		return idMesyuarat.toString();
	}
	
	private String StringToDate(String tarikh){
		return "to_date('" + tarikh + "','dd/MM/yyyy')";
	}

	public Long getIdPermohonan() {
		return idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public String getTajuk() {
		return tajuk;
	}

	public void setTajuk(String tajuk) {
		this.tajuk = tajuk;
	}

	public String getBilMesyuarat() {
		return bilMesyuarat;
	}

	public void setBilMesyuarat(String bilMesyuarat) {
		this.bilMesyuarat = bilMesyuarat;
	}

	public String getTarikhMesyuarat() {
		return tarikhMesyuarat;
	}

	public void setTarikhMesyuarat(String tarikhMesyuarat) {
		this.tarikhMesyuarat = tarikhMesyuarat;
	}

	public String getJamDari() {
		return jamDari;
	}

	public void setJamDari(String jamDari) {
		this.jamDari = jamDari;
	}

	public String getMinitDari() {
		return minitDari;
	}

	public void setMinitDari(String minitDari) {
		this.minitDari = minitDari;
	}

	public String getJamHIngga() {
		return jamHIngga;
	}

	public void setJamHIngga(String jamHIngga) {
		this.jamHIngga = jamHIngga;
	}

	public String getMinitHingga() {
		return minitHingga;
	}

	public void setMinitHingga(String minitHingga) {
		this.minitHingga = minitHingga;
	}

	public Long getIdLokasi() {
		return idLokasi;
	}

	public void setIdLokasi(Long idLokasi) {
		this.idLokasi = idLokasi;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getFlagSyor() {
		return flagSyor;
	}

	public void setFlagSyor(String flagSyor) {
		this.flagSyor = flagSyor;
	}

	public String getFlagMesyuarat() {
		return flagMesyuarat;
	}

	public void setFlagMesyuarat(String flagMesyuarat) {
		this.flagMesyuarat = flagMesyuarat;
	}

	public String getJamMesyuaratDari() {
		return jamMesyuaratDari;
	}

	public void setJamMesyuaratDari(String jamMesyuaratDari) {
		this.jamMesyuaratDari = jamMesyuaratDari;
	}

	public String getJamMesyuaratHingga() {
		return jamMesyuaratHingga;
	}

	public void setJamMesyuaratHingga(String jamMesyuaratHingga) {
		this.jamMesyuaratHingga = jamMesyuaratHingga;
	}

	public String getMinitMesyuaratDari() {
		return minitMesyuaratDari;
	}

	public void setMinitMesyuaratDari(String minitMesyuaratDari) {
		this.minitMesyuaratDari = minitMesyuaratDari;
	}

	public String getMinitMesyuaratHingga() {
		return minitMesyuaratHingga;
	}

	public void setMinitMesyuaratHingga(String minitMesyuaratHingga) {
		this.minitMesyuaratHingga = minitMesyuaratHingga;
	}

	public Long getIdFail() {
		return idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public String getTujuan() {
		return tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getUlasanKeputusan() {
		return ulasanKeputusan;
	}

	public void setUlasanKeputusan(String ulasanKeputusan) {
		this.ulasanKeputusan = ulasanKeputusan;
	}

	public String getTindakanMesyuarat() {
		return tindakanMesyuarat;
	}

	public void setTindakanMesyuarat(String tindakanMesyuarat) {
		this.tindakanMesyuarat = tindakanMesyuarat;
	}

	public String getWaktuMesyuaratDari() {
		return waktuMesyuaratDari;
	}

	public void setWaktuMesyuaratDari(String waktuMesyuaratDari) {
		this.waktuMesyuaratDari = waktuMesyuaratDari;
	}

	public String getWaktuMesyuaratHingga() {
		return waktuMesyuaratHingga;
	}

	public void setWaktuMesyuaratHingga(String waktuMesyuaratHingga) {
		this.waktuMesyuaratHingga = waktuMesyuaratHingga;
	}

	public Long getIdOld() {
		return idOld;
	}

	public void setIdOld(Long idOld) {
		this.idOld = idOld;
	}

	public Long getIdRujmesyuarat() {
		return idRujmesyuarat;
	}

	public void setIdRujmesyuarat(Long idRujmesyuarat) {
		this.idRujmesyuarat = idRujmesyuarat;
	}

	public String getPengerusiMesyuarat() {
		return pengerusiMesyuarat;
	}

	public void setPengerusiMesyuarat(String pengerusiMesyuarat) {
		this.pengerusiMesyuarat = pengerusiMesyuarat;
	}

	public String getFlagPost() {
		return flagPost;
	}

	public void setFlagPost(String flagPost) {
		this.flagPost = flagPost;
	}

	public Long getIdMesyuarat() {
		return idMesyuarat;
	}

	public void setIdMesyuarat(Long idMesyuarat) {
		this.idMesyuarat = idMesyuarat;
	}

}
