package ekptg.model.php2.modelTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class TblPhpHakmilikModel {
	
	//DB columns
	private Long idHakmilik;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMukim;
	private Long idJenisHakmilik;
	private String noHakmilik;
	private Long idLot;
	private String noLot;
	private Long idUnitluasambil;
	private Double luasAmbil;
	private Long idKategori;
	private String syaratNyata;
	private String sekatanKepentingan;
	private Long idKementerian;
	private Long idAgensi;
	private String peganganHakmilik;
	private String kegunaanTanah;
	private Long idSubkategori;
	private String syarat;
	private String sekatan;
	private Long idLuas;
	private Double luas;
	private Long idHakmilikPermohonan;
	private String tarikhBorangK;
	private String catatan;
	private String noPerserahan;
	private String tarikhCatatan;
	private String tarikhTerima;
	private String noWarta;
	private String tarikhWarta;
	
	//non-DB columns
	private String flagPost;

	public void save(TblPhpHakmilikModel model, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		
		Long idHakmilik = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(model.getIdNegeri()!=null){
				r.add("ID_NEGERI", model.getIdNegeri());
			}
			if(model.getIdDaerah()!=null){
				r.add("ID_DAERAH", model.getIdDaerah());
			}
			if(model.getIdMukim()!=null){
				r.add("ID_MUKIM", model.getIdMukim());
			}
			if(model.getIdJenisHakmilik()!=null){
				r.add("ID_JENISHAKMILIK", model.getIdJenisHakmilik());
			}
			if(model.getNoHakmilik()!=null){
				r.add("NO_HAKMILIK", model.getNoHakmilik());
			}
			if(model.getIdLot()!=null){
				r.add("ID_LOT", model.getIdLot());
			}
			if(model.getNoLot()!=null){
				r.add("NO_LOT", model.getNoLot());
			}
			if(model.getIdUnitluasambil()!=null){
				r.add("ID_UNITLUASAMBIL", model.getIdUnitluasambil());
			}
			if(model.getLuasAmbil()!=null){
				r.add("LUAS_AMBIL", model.getLuasAmbil());
			}
			if(model.getIdKategori()!=null){
				r.add("ID_KATEGORI", model.getIdKategori());
			}
			if(model.getSyaratNyata()!=null){
				r.add("SYARAT_NYATA", model.getSyaratNyata());
			}
			if(model.getSekatanKepentingan()!=null){
				r.add("SEKATAN_KEPENTINGAN", model.getSekatanKepentingan());
			}
			if(model.getIdKementerian()!=null){
				r.add("ID_KEMENTERIAN", model.getIdKementerian());
			}
			if(model.getIdAgensi()!=null){
				r.add("ID_AGENSI", model.getIdAgensi());
			}
			if(model.getPeganganHakmilik()!=null){
				r.add("PEGANGAN_HAKMILIK", model.getPeganganHakmilik());
			}
			if(model.getKegunaanTanah()!=null){
				r.add("KEGUNAAN_TANAH", model.getKegunaanTanah());
			}
			if(model.getIdSubkategori()!=null){
				r.add("ID_SUBKATEGORI", model.getIdSubkategori());
			}
			if(model.getSyarat()!=null){
				r.add("SYARAT", model.getSyarat());
			}
			if(model.getSekatan()!=null){
				r.add("SEKATAN", model.getSekatan());
			}
			if(model.getIdLuas()!=null){
				r.add("ID_LUAS", model.getIdLuas());
			}
			if(model.getLuas()!=null){
				r.add("LUAS", model.getLuas());
			}
			if(model.getTarikhBorangK()!=null){
				r.add("TARIKH_BORANGK", r.unquote(StringToDate(model.getTarikhBorangK())));
			}
			if(model.getIdHakmilikPermohonan()!=null){
				r.add("ID_HAKMILIKPERMOHONAN", model.getIdHakmilikPermohonan());
			}
			if(model.getCatatan()!=null){
				r.add("CATATAN", model.getCatatan());
			}
			if(model.getNoPerserahan()!=null){
				r.add("NO_PERSERAHAN", model.getNoPerserahan());
			}
			if(model.getTarikhCatatan()!=null){
				r.add("TARIKH_CATATAN", r.unquote(StringToDate(model.getTarikhCatatan())));
			}
			if(model.getTarikhTerima()!=null){
				r.add("TARIKH_TERIMA", r.unquote(StringToDate(model.getTarikhTerima())));
			}
			if(model.getNoWarta()!=null){
				r.add("NO_WARTA", model.getNoWarta());
			}
			if(model.getTarikhWarta()!=null){
				r.add("TARIKH_WARTA", r.unquote(StringToDate(model.getTarikhWarta())));
			}
			
			if(model.getFlagPost().equals("insert")){
				idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
				r.add("ID_HAKMILIK", idHakmilik);
//				r.add("ID_MASUK", userId);
//				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				
				sql = r.getSQLInsert("TBLPHPHAKMILIK");
			} else if(model.getFlagPost().equals("update")){
				idHakmilik = model.getIdHakmilik();
				r.update("ID_HAKMILIK", idHakmilik);
//				r.add("ID_KEMASKINI", userId);
//				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				
				sql = r.getSQLUpdate("TBLPHPHAKMILIK");
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
	}
	
	private String StringToDate(String tarikh){
		return "to_date('" + tarikh + "','dd/MM/yyyy')";
	}
	
	public Long getIdHakmilik() {
		return idHakmilik;
	}
	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}
	public Long getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}
	public Long getIdDaerah() {
		return idDaerah;
	}
	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}
	public Long getIdMukim() {
		return idMukim;
	}
	public void setIdMukim(Long idMukim) {
		this.idMukim = idMukim;
	}
	public Long getIdJenisHakmilik() {
		return idJenisHakmilik;
	}
	public void setIdJenisHakmilik(Long idJenisHakmilik) {
		this.idJenisHakmilik = idJenisHakmilik;
	}
	public String getNoHakmilik() {
		return noHakmilik;
	}
	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public Long getIdLot() {
		return idLot;
	}
	public void setIdLot(Long idLot) {
		this.idLot = idLot;
	}
	public String getNoLot() {
		return noLot;
	}
	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}
	public Long getIdUnitluasambil() {
		return idUnitluasambil;
	}
	public void setIdUnitluasambil(Long idUnitluasambil) {
		this.idUnitluasambil = idUnitluasambil;
	}
	public Double getLuasAmbil() {
		return luasAmbil;
	}
	public void setLuasAmbil(Double luasAmbil) {
		this.luasAmbil = luasAmbil;
	}
	public Long getIdKategori() {
		return idKategori;
	}
	public void setIdKategori(Long idKategori) {
		this.idKategori = idKategori;
	}
	public String getSyaratNyata() {
		return syaratNyata;
	}
	public void setSyaratNyata(String syaratNyata) {
		this.syaratNyata = syaratNyata;
	}
	public String getSekatanKepentingan() {
		return sekatanKepentingan;
	}
	public void setSekatanKepentingan(String sekatanKepentingan) {
		this.sekatanKepentingan = sekatanKepentingan;
	}
	public Long getIdKementerian() {
		return idKementerian;
	}
	public void setIdKementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
	}
	public Long getIdAgensi() {
		return idAgensi;
	}
	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}
	public String getPeganganHakmilik() {
		return peganganHakmilik;
	}
	public void setPeganganHakmilik(String peganganHakmilik) {
		this.peganganHakmilik = peganganHakmilik;
	}
	public String getKegunaanTanah() {
		return kegunaanTanah;
	}
	public void setKegunaanTanah(String kegunaanTanah) {
		this.kegunaanTanah = kegunaanTanah;
	}
	public Long getIdSubkategori() {
		return idSubkategori;
	}
	public void setIdSubkategori(Long idSubkategori) {
		this.idSubkategori = idSubkategori;
	}
	public String getSyarat() {
		return syarat;
	}
	public void setSyarat(String syarat) {
		this.syarat = syarat;
	}
	public String getSekatan() {
		return sekatan;
	}
	public void setSekatan(String sekatan) {
		this.sekatan = sekatan;
	}
	public Long getIdLuas() {
		return idLuas;
	}
	public void setIdLuas(Long idLuas) {
		this.idLuas = idLuas;
	}
	public Double getLuas() {
		return luas;
	}
	public void setLuas(Double luas) {
		this.luas = luas;
	}
	public Long getIdHakmilikPermohonan() {
		return idHakmilikPermohonan;
	}
	public void setIdHakmilikPermohonan(Long idHakmilikPermohonan) {
		this.idHakmilikPermohonan = idHakmilikPermohonan;
	}
	public String getTarikhBorangK() {
		return tarikhBorangK;
	}
	public void setTarikhBorangK(String tarikhBorangK) {
		this.tarikhBorangK = tarikhBorangK;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public String getNoPerserahan() {
		return noPerserahan;
	}
	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}
	public String getTarikhCatatan() {
		return tarikhCatatan;
	}
	public void setTarikhCatatan(String tarikhCatatan) {
		this.tarikhCatatan = tarikhCatatan;
	}
	public String getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(String tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public String getNoWarta() {
		return noWarta;
	}
	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}
	public String getTarikhWarta() {
		return tarikhWarta;
	}
	public void setTarikhWarta(String tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}
	public String getFlagPost() {
		return flagPost;
	}
	public void setFlagPost(String flagPost) {
		this.flagPost = flagPost;
	}
	
	

}
