package ekptg.model.php2.modelTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class TblPhpPermohonanSewaModel {
	
	//DB columns
	private Long idPhppermohonansewa;
	private Long idPermohonan;
	private String tujuan;
	private Long idLuasasal;
	private Double luasAsal;
	private Double luasMhn1;
	private Double luasMhn2;
	private Double luasMhn3;
	private Long idLuasmhnbersamaan;
	private Double luasMhnbersamaan;
	private Long idLuasbaki;
	private Double luasBaki;
	private String flagPermohonandari;
	private Long idLuasmhn;
	private String flagGuna;
	private String flagTempohsewa;
	private String keteranganAduan;
	private String lokasi;
	private Long idUnitluasmhn;
	private Double luasMhn;
	private Long idUnitluasbaki;
	private String tarikhmUlaMhn;
	private String tarikhTamatMhn;
	private String flagLbhCeroboh;
	private Long idJenisceroboh;
	private String lainJnsCeroboh;
	private String tarikhSewaBaru;
	private String tarikhHantarSrt;
	private String tarikhKelulusanRayuan;
	private String tarikhKeputusan;
	private String flagKeputusan;
	private String flagRayuankadarsewa;
	private String sebabRayuan;
	private String tajukSewa;
	private Double tempohSewa;
	private String flagKeputusanRayuan;
	private Double permohonanDari;
	private String flagKeputusanTujuan;
	private String flagKeputusanTarikh;
	private String tarikhKelulusanTarikh;
	private String tarikhKelulusanTujuan;
	private String kelulusan;
	private String keputusan;
	private String tarikhHantarkeputusan;
	private String flagProsesfail;
	private String catatan;
	private String flagJenisperjanjian;
	private String tarikhMulaperjanjian;
	private String tarikhTerimatamat;
	private String tarikhSurattamat;
	private String noRujSuratTamat;
	private String flagSebabTamat;
	private String catatanTamat;
	private String sebabTolakRingkas;
	private String tarikhSuratTolakRingkas;
	private String idSebabtamat;
	
	//non-DB columns
	private String flagPost;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private static final Log log = LogFactory.getLog(TblPhpPermohonanSewaModel.class);

	public Vector getTable(String idPermohonanSewa) throws Exception {
		Db db = null;
		String sql = "";
		Vector beanMaklumatPenamatanSewa = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERMOHONAN, TUJUAN, ID_LUASASAL, LUAS_ASAL, LUAS_MHN1, LUAS_MHN2, LUAS_MHN3, ID_LUASMHNBERSAMAAN, LUAS_MHNBERSAMAAN, ID_LUASBAKI, " +
					"LUAS_BAKI, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, FLAG_PERMOHONANDARI, ID_LUASMHN, FLAG_GUNA, FLAG_TEMPOHSEWA, KETERANGAN_ADUAN, LOKASI, ID_UNITLUASMHN, " +
					"LUAS_MHN, ID_UNITLUASBAKI, TARIKH_MULA_MHN, TARIKH_TAMAT_MHN, FLAG_LBH_CEROBOH, ID_JENISCEROBOH, LAIN_JNS_CEROBOH, TARIKH_SEWA_BARU, TARIKH_HANTAR_SRT, " +
					"TARIKH_KELULUSAN_RAYUAN, TARIKH_KEPUTUSAN, FLAG_KEPUTUSAN, FLAG_RAYUANKADARSEWA, SEBAB_RAYUAN, TAJUK_SEWA, TEMPOH_SEWA, FLAG_KEPUTUSAN_RAYUAN, PERMOHONAN_DARI, " +
					"FLAG_KEPUTUSAN_TUJUAN, FLAG_KEPUTUSAN_TARIKH, TARIKH_KELULUSAN_TARIKH, TARIKH_KELULUSAN_TUJUAN, KEPUTUSAN, TARIKH_HANTARKEPUTUSAN, FLAG_PROSESFAIL, CATATAN, " +
					"FLAG_JENISPERJANJIAN, TARIKH_MULAPERJANJIAN, TARIKH_TERIMA_TAMAT, TARIKH_SURAT_TAMAT, NO_RUJ_SURAT_TAMAT, FLAG_SEBAB_TAMAT, CATATAN_TAMAT, SEBAB_TOLAK_RINGKAS, " +
					"TARIKH_SURAT_TOLAK_RINGKAS, ID_SEBABTAMAT "
					+ " FROM TBLPHPPERMOHONANSEWA "
					+ " WHERE ID_PHPPERMOHONANSEWA = "+idPermohonanSewa;

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				
				h.put("idPermohonan", rs
						.getString("ID_PERMOHONAN") == null ? "" : rs
						.getString("ID_PERMOHONAN").toUpperCase());
				h.put("tujuan", rs
						.getString("TUJUAN") == null ? "" : rs
						.getString("TUJUAN").toUpperCase());
				h.put("idLuasasal", rs
						.getString("ID_LUASASAL") == null ? "" : rs
						.getString("ID_LUASASAL").toUpperCase());
				h.put("luasAsal", rs
						.getString("LUAS_ASAL") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_ASAL")));
				h.put("luasMhn1", rs
						.getString("LUAS_MHN1") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN1")));
				h.put("luasMhn2", rs
						.getString("LUAS_MHN2") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN2")));
				h.put("luasMhn3", rs
						.getString("LUAS_MHN3") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN3")));
				h.put("idLuasmhnbersamaan", rs
						.getString("ID_LUASMHNBERSAMAAN") == null ? "" : rs
						.getString("ID_LUASMHNBERSAMAAN").toUpperCase());
				h.put("luasMhnbersamaan", rs
						.getString("LUAS_MHNBERSAMAAN") == null ? "" : rs
						.getString("LUAS_MHNBERSAMAAN").toUpperCase());
				h.put("idLuasbaki", rs
						.getString("ID_LUASBAKI") == null ? "" : rs
						.getString("ID_LUASBAKI").toUpperCase());
				h.put("luasBaki", rs
						.getString("LUAS_BAKI") == null ? "" : rs
						.getString("LUAS_BAKI").toUpperCase());
				h.put("flagPermohonandari", rs
						.getString("FLAG_PERMOHONANDARI") == null ? "" : rs
						.getString("FLAG_PERMOHONANDARI").toUpperCase());
				h.put("idLuasmhn", rs
						.getString("ID_LUASMHN") == null ? "" : rs
						.getString("ID_LUASMHN").toUpperCase());
				h.put("flagGuna", rs
						.getString("FLAG_GUNA") == null ? "" : rs
						.getString("FLAG_GUNA").toUpperCase());
				h.put("flagTempohsewa", rs
						.getString("FLAG_TEMPOHSEWA") == null ? "" : rs
						.getString("FLAG_TEMPOHSEWA").toUpperCase());
				h.put("keteranganAduan", rs
						.getString("KETERANGAN_ADUAN") == null ? "" : rs
						.getString("KETERANGAN_ADUAN").toUpperCase());
				h.put("lokasi", rs
						.getString("LOKASI") == null ? "" : rs
						.getString("LOKASI").toUpperCase());
				h.put("idUnitluasmhn", rs
						.getString("ID_UNITLUASMHN") == null ? "" : rs
						.getString("ID_UNITLUASMHN").toUpperCase());
				h.put("luasMhn", rs
						.getString("LUAS_MHN") == null ? "" : rs
						.getString("LUAS_MHN").toUpperCase());
				h.put("idUnitluasbaki", rs
						.getString("ID_UNITLUASBAKI") == null ? "" : rs
						.getString("ID_UNITLUASBAKI").toUpperCase());
				h.put("tarikhMulaMhn", rs
						.getString("TARIKH_MULA_MHN") == null ? "" :sdf
								.format(rs.getDate("TARIKH_MULA_MHN")));
				h.put("tarikhTamatMhn", rs
						.getString("TARIKH_TAMAT_MHN") == null ? "" :sdf
								.format(rs.getDate("TARIKH_TAMAT_MHN")));
				h.put("flagLbhCeroboh", rs
						.getString("FLAG_LBH_CEROBOH") == null ? "" : rs
						.getString("FLAG_LBH_CEROBOH").toUpperCase());
				h.put("idJenisceroboh", rs
						.getString("ID_JENISCEROBOH") == null ? "" : rs
						.getString("ID_JENISCEROBOH").toUpperCase());
				h.put("lainJnsCeroboh", rs
						.getString("LAIN_JNS_CEROBOH") == null ? "" : rs
						.getString("LAIN_JNS_CEROBOH").toUpperCase());
				h.put("tarikhSewaBaru", rs
						.getString("TARIKH_SEWA_BARU") == null ? "" :sdf
								.format(rs.getDate("TARIKH_SEWA_BARU")));
				h.put("tarikhHantarSrt", rs
						.getString("TARIKH_HANTAR_SRT") == null ? "" :sdf
								.format(rs.getDate("TARIKH_HANTAR_SRT")));
				h.put("tarikhKelulusanRayuan", rs
						.getString("TARIKH_KELULUSAN_RAYUAN") == null ? "" :sdf
								.format(rs.getDate("TARIKH_KELULUSAN_RAYUAN")));
				h.put("tarikhKeputusan", rs
						.getString("TARIKH_KEPUTUSAN") == null ? "" :sdf
								.format(rs.getDate("TARIKH_KEPUTUSAN")));
				h.put("flagKeputusan", rs
						.getString("FLAG_KEPUTUSAN") == null ? "" : rs
						.getString("FLAG_KEPUTUSAN").toUpperCase());
				h.put("flagRayuankadarsewa", rs
						.getString("FLAG_RAYUANKADARSEWA") == null ? "" : rs
						.getString("FLAG_RAYUANKADARSEWA").toUpperCase());
				h.put("sebabRayuan", rs
						.getString("SEBAB_RAYUAN") == null ? "" : rs
						.getString("SEBAB_RAYUAN").toUpperCase());
				h.put("tajukSewa", rs
						.getString("TAJUK_SEWA") == null ? "" : rs
						.getString("TAJUK_SEWA").toUpperCase());
				h.put("tempohSewa", rs
						.getString("TEMPOH_SEWA") == null ? "" : rs
						.getString("TEMPOH_SEWA").toUpperCase());
				h.put("flagKeputusanRayuan", rs
						.getString("FLAG_KEPUTUSAN_RAYUAN") == null ? "" : rs
						.getString("FLAG_KEPUTUSAN_RAYUAN").toUpperCase());
				h.put("permohonanDari", rs
						.getString("PERMOHONAN_DARI") == null ? "" : rs
						.getString("PERMOHONAN_DARI").toUpperCase());
				h.put("flagKeputusanTujuan", rs
						.getString("FLAG_KEPUTUSAN_TUJUAN") == null ? "" : rs
						.getString("FLAG_KEPUTUSAN_TUJUAN").toUpperCase());
				h.put("flagKeputusanTarikh", rs
						.getString("FLAG_KEPUTUSAN_TARIKH") == null ? "" : rs
						.getString("FLAG_KEPUTUSAN_TARIKH").toUpperCase());
				h.put("tarikhKelulusanTarikh", rs
						.getString("TARIKH_KELULUSAN_TARIKH") == null ? "" :sdf
								.format(rs.getDate("TARIKH_KELULUSAN_TARIKH")));
				h.put("tarikhKelulusanTujuan", rs
						.getString("TARIKH_KELULUSAN_TUJUAN") == null ? "" :sdf
								.format(rs.getDate("TARIKH_KELULUSAN_TUJUAN")));
//				h.put("kelulusan", rs
//						.getString("KELULUSAN") == null ? "" : rs
//						.getString("KELULUSAN").toUpperCase());
				h.put("keputusan", rs
						.getString("KEPUTUSAN") == null ? "" : rs
						.getString("KEPUTUSAN").toUpperCase());
				h.put("tarikhHantarkeputusan", rs
						.getString("TARIKH_HANTARKEPUTUSAN") == null ? "" :sdf
								.format(rs.getDate("TARIKH_HANTARKEPUTUSAN")));
				h.put("flagProsesfail", rs
						.getString("FLAG_PROSESFAIL") == null ? "" : rs
						.getString("FLAG_PROSESFAIL").toUpperCase());
				h.put("catatan", rs
						.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("flagJenisperjanjian", rs
						.getString("FLAG_JENISPERJANJIAN") == null ? "" : rs
						.getString("FLAG_JENISPERJANJIAN").toUpperCase());
				h.put("tarikhMulaperjanjian", rs
						.getString("TARIKH_MULAPERJANJIAN") == null ? "" :sdf
								.format(rs.getDate("TARIKH_MULAPERJANJIAN")));
				h.put("tarikhTerimatamat", rs
						.getString("TARIKH_TERIMA_TAMAT") == null ? "" :sdf
								.format(rs.getDate("TARIKH_TERIMA_TAMAT")));
				h.put("tarikhSuratTamat", rs
						.getString("TARIKH_SURAT_TAMAT") == null ? "" :sdf
								.format(rs.getDate("TARIKH_SURAT_TAMAT")));
				h.put("noRujSuratTamat", rs
						.getString("NO_RUJ_SURAT_TAMAT") == null ? "" : rs
						.getString("NO_RUJ_SURAT_TAMAT").toUpperCase());
				h.put("flagSebabTamat", rs
						.getString("FLAG_SEBAB_TAMAT") == null ? "" : rs
						.getString("FLAG_SEBAB_TAMAT").toUpperCase());
				h.put("catatanTamat", rs
						.getString("CATATAN_TAMAT") == null ? "" : rs
						.getString("CATATAN_TAMAT").toUpperCase());
				h.put("sebabTolakRingkas", rs
						.getString("SEBAB_TOLAK_RINGKAS") == null ? "" : rs
						.getString("SEBAB_TOLAK_RINGKAS").toUpperCase());
				h.put("tarikhSuratTolakRingkas", rs
						.getString("TARIKH_SURAT_TOLAK_RINGKAS") == null ? "" :sdf
								.format(rs.getDate("TARIKH_SURAT_TOLAK_RINGKAS")));
				h.put("socSebabTamat", rs
						.getString("ID_SEBABTAMAT") == null ? "" : rs
						.getString("ID_SEBABTAMAT").toUpperCase());		//idSebabtamat 
				beanMaklumatPenamatanSewa.addElement(h);
			}	
				
		}catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		
		return beanMaklumatPenamatanSewa;
	}
	
	public void save(TblPhpPermohonanSewaModel model, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		
		Long idPermohonanSewa = null;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(model.getIdPermohonan()!=null){
				r.add("ID_PERMOHONAN", model.getIdPermohonan());
			}
			if(model.getTujuan()!=null){
				r.add("TUJUAN", model.getTujuan());
			}
			if(model.getIdLuasasal()!=null){
				r.add("ID_LUASASAL", model.getIdLuasasal());
			}
			if(model.getLuasAsal()!=null){
				r.add("LUAS_ASAL", model.getLuasAsal());
			}
			if(model.getLuasMhn1()!=null){
				r.add("LUAS_MHN1", model.getLuasMhn1());
			}
			if(model.getLuasMhn2()!=null){
				r.add("LUAS_MHN2", model.getLuasMhn2());
			}
			if(model.getLuasMhn3()!=null){
				r.add("LUAS_MHN3", model.getLuasMhn3());
			}
			if(model.getIdLuasmhnbersamaan()!=null){
				r.add("ID_LUASMHNBERSAMAAN", model.getIdLuasmhnbersamaan());
			}
			if(model.getLuasMhnbersamaan()!=null){
				r.add("LUAS_MHNBERSAMAAN", model.getLuasMhnbersamaan());
			}
			if(model.getIdLuasbaki()!=null){
				r.add("ID_LUASBAKI", model.getIdLuasbaki());
			}
			if(model.getLuasBaki()!=null){
				r.add("LUAS_BAKI", model.getLuasBaki());
			}
			if(model.getFlagPermohonandari()!=null){
				r.add("FLAG_PERMOHONANDARI", model.getFlagPermohonandari());
			}
			if(model.getIdLuasmhn()!=null){
				r.add("ID_LUASMHN", model.getIdLuasmhn());
			}
			if(model.getFlagGuna()!=null){
				r.add("FLAG_GUNA", model.getFlagGuna());
			}
			if(model.getFlagTempohsewa()!=null){
				r.add("FLAG_TEMPOHSEWA", model.getFlagTempohsewa());
			}
			if(model.getKeteranganAduan()!=null){
				r.add("KETERANGAN_ADUAN", model.getKeteranganAduan());
			}
			if(model.getLokasi()!=null){
				r.add("LOKASI", model.getLokasi());
			}
			if(model.getIdUnitluasbaki()!=null){
				r.add("ID_UNITLUASMHN", model.getIdUnitluasbaki());
			}
			if(model.getLuasMhn()!=null){
				r.add("LUAS_MHN", model.getLuasMhn());
			}
			if(model.getIdUnitluasbaki()!=null){
				r.add("ID_UNITLUASBAKI", model.getIdUnitluasbaki());
			}
			if(model.getTarikhmUlaMhn()!=null){
				r.add("TARIKH_MULA_MHN", r.unquote(StringToDate(model.getTarikhmUlaMhn())));
			}
			if(model.getTarikhTamatMhn()!=null){
				r.add("TARIKH_TAMAT_MHN", r.unquote(StringToDate(model.getTarikhTamatMhn())));
			}
			if(model.getFlagLbhCeroboh()!=null){
				r.add("FLAG_LBH_CEROBOH", model.getFlagLbhCeroboh());
			}
			if(model.getIdJenisceroboh()!=null){
				r.add("ID_JENISCEROBOH", model.getIdJenisceroboh());
			}
			if(model.getLainJnsCeroboh()!=null){
				r.add("LAIN_JNS_CEROBOH", model.getLainJnsCeroboh());
			}
			if(model.getTarikhSewaBaru()!=null){
				r.add("TARIKH_SEWA_BARU", r.unquote(StringToDate(model.getTarikhSewaBaru())));
			}
			if(model.getTarikhHantarSrt()!=null){
				r.add("TARIKH_HANTAR_SRT", r.unquote(StringToDate(model.getTarikhHantarSrt())));
			}
			if(model.getTarikhKelulusanRayuan()!=null){
				r.add("TARIKH_KELULUSAN_RAYUAN", r.unquote(StringToDate(model.getTarikhKelulusanRayuan())));
			}
			if(model.getTarikhKeputusan()!=null){
				r.add("TARIKH_KEPUTUSAN", r.unquote(StringToDate(model.getTarikhKeputusan())));
			}
			if(model.getFlagKeputusan()!=null){
				r.add("FLAG_KEPUTUSAN", model.getFlagKeputusan());
			}
			if(model.getFlagRayuankadarsewa()!=null){
				r.add("FLAG_RAYUANKADARSEWA", model.getFlagRayuankadarsewa());
			}
			if(model.getSebabRayuan()!=null){
				r.add("SEBAB_RAYUAN", model.getSebabRayuan());
			}
			if(model.getTajukSewa()!=null){
				r.add("TAJUK_SEWA", model.getTajukSewa());
			}
			if(model.getTempohSewa()!=null){
				r.add("TEMPOH_SEWA", model.getTempohSewa());
			}
			if(model.getFlagKeputusanRayuan()!=null){
				r.add("FLAG_KEPUTUSAN_RAYUAN", model.getFlagKeputusanRayuan());
			}
			if(model.getPermohonanDari()!=null){
				r.add("PERMOHONAN_DARI", model.getPermohonanDari());
			}
			if(model.getFlagKeputusanRayuan()!=null){
				r.add("FLAG_KEPUTUSAN_TUJUAN", model.getFlagKeputusanRayuan());
			}
			if(model.getFlagKeputusanTarikh()!=null){
				r.add("FLAG_KEPUTUSAN_TARIKH", model.getFlagKeputusanTarikh());
			}
			if(model.getTarikhKelulusanTarikh()!=null){
				r.add("TARIKH_KELULUSAN_TARIKH", r.unquote(StringToDate(model.getTarikhKelulusanTarikh())));
			}
			if(model.getTarikhKelulusanTujuan()!=null){
				r.add("TARIKH_KELULUSAN_TUJUAN", r.unquote(StringToDate(model.getTarikhKelulusanTujuan())));
			}
			if(model.getKeputusan()!=null){
				r.add("KEPUTUSAN", model.getKeputusan());
			}
			if(model.getTarikhHantarkeputusan()!=null){
				r.add("TARIKH_HANTARKEPUTUSAN", r.unquote(StringToDate(model.getTarikhHantarkeputusan())));
			}
			if(model.getFlagProsesfail()!=null){
				r.add("FLAG_PROSESFAIL", model.getFlagProsesfail());
			}
			if(model.getCatatan()!=null){
				r.add("CATATAN", model.getCatatan());
			}
			if(model.getFlagJenisperjanjian()!=null){
				r.add("FLAG_JENISPERJANJIAN", model.getFlagJenisperjanjian());
			}
			if(model.getTarikhMulaperjanjian()!=null){
				r.add("TARIKH_MULAPERJANJIAN", r.unquote(StringToDate(model.getTarikhMulaperjanjian())));
			}
			if(model.getTarikhTerimatamat()!=null){
				r.add("TARIKH_TERIMA_TAMAT", r.unquote(StringToDate(model.getTarikhTerimatamat())));
			}
			if(model.getTarikhSurattamat()!=null){
				r.add("TARIKH_SURAT_TAMAT", r.unquote(StringToDate(model.getTarikhSurattamat())));
			}
			if(model.getNoRujSuratTamat()!=null){
				r.add("NO_RUJ_SURAT_TAMAT", model.getNoRujSuratTamat());
			}
			if(model.getFlagSebabTamat()!=null){
				r.add("FLAG_SEBAB_TAMAT", model.getFlagSebabTamat());
			}
			if(model.getCatatanTamat()!=null){
				r.add("CATATAN_TAMAT", model.getCatatanTamat());
			}
			if(model.getSebabTolakRingkas()!=null){
				r.add("SEBAB_TOLAK_RINGKAS", model.getSebabTolakRingkas());
			}
			if(model.getTarikhSuratTolakRingkas()!=null){
				r.add("TARIKH_SURAT_TOLAK_RINGKAS", r.unquote(StringToDate(model.getTarikhSuratTolakRingkas())));
			}
			if(model.getIdSebabtamat()!=null){
				r.add("ID_SEBABTAMAT", model.getIdSebabtamat());
			}
			
			if(model.getFlagPost().equals("insert")){
				idPermohonanSewa = DB.getNextID("TBLPHPPERMOHONANSEWA_SEQ");
				r.add("ID_PHPPERMOHONANSEWA", idPermohonanSewa);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				
				sql = r.getSQLInsert("TBLPHPPERMOHONANSEWA");
			} else if(model.getFlagPost().equals("update")){
				idPermohonanSewa = model.getIdPhppermohonansewa();
				r.update("ID_PHPPERMOHONANSEWA", idPermohonanSewa);
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				
				sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
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

	public Long getIdPhppermohonansewa() {
		return idPhppermohonansewa;
	}

	public void setIdPhppermohonansewa(Long idPhppermohonansewa) {
		this.idPhppermohonansewa = idPhppermohonansewa;
	}

	public Long getIdPermohonan() {
		return idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public String getTujuan() {
		return tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public Long getIdLuasasal() {
		return idLuasasal;
	}

	public void setIdLuasasal(Long idLuasasal) {
		this.idLuasasal = idLuasasal;
	}

	public Double getLuasAsal() {
		return luasAsal;
	}

	public void setLuasAsal(Double luasAsal) {
		this.luasAsal = luasAsal;
	}

	public Double getLuasMhn1() {
		return luasMhn1;
	}

	public void setLuasMhn1(Double luasMhn1) {
		this.luasMhn1 = luasMhn1;
	}

	public Double getLuasMhn2() {
		return luasMhn2;
	}

	public void setLuasMhn2(Double luasMhn2) {
		this.luasMhn2 = luasMhn2;
	}

	public Double getLuasMhn3() {
		return luasMhn3;
	}

	public void setLuasMhn3(Double luasMhn3) {
		this.luasMhn3 = luasMhn3;
	}

	public Long getIdLuasmhnbersamaan() {
		return idLuasmhnbersamaan;
	}

	public void setIdLuasmhnbersamaan(Long idLuasmhnbersamaan) {
		this.idLuasmhnbersamaan = idLuasmhnbersamaan;
	}

	public Double getLuasMhnbersamaan() {
		return luasMhnbersamaan;
	}

	public void setLuasMhnbersamaan(Double luasMhnbersamaan) {
		this.luasMhnbersamaan = luasMhnbersamaan;
	}

	public Long getIdLuasbaki() {
		return idLuasbaki;
	}

	public void setIdLuasbaki(Long idLuasbaki) {
		this.idLuasbaki = idLuasbaki;
	}

	public Double getLuasBaki() {
		return luasBaki;
	}

	public void setLuasBaki(Double luasBaki) {
		this.luasBaki = luasBaki;
	}

	public String getFlagPermohonandari() {
		return flagPermohonandari;
	}

	public void setFlagPermohonandari(String flagPermohonandari) {
		this.flagPermohonandari = flagPermohonandari;
	}

	public Long getIdLuasmhn() {
		return idLuasmhn;
	}

	public void setIdLuasmhn(Long idLuasmhn) {
		this.idLuasmhn = idLuasmhn;
	}

	public String getFlagGuna() {
		return flagGuna;
	}

	public void setFlagGuna(String flagGuna) {
		this.flagGuna = flagGuna;
	}

	public String getFlagTempohsewa() {
		return flagTempohsewa;
	}

	public void setFlagTempohsewa(String flagTempohsewa) {
		this.flagTempohsewa = flagTempohsewa;
	}

	public String getKeteranganAduan() {
		return keteranganAduan;
	}

	public void setKeteranganAduan(String keteranganAduan) {
		this.keteranganAduan = keteranganAduan;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public Long getIdUnitluasmhn() {
		return idUnitluasmhn;
	}

	public void setIdUnitluasmhn(Long idUnitluasmhn) {
		this.idUnitluasmhn = idUnitluasmhn;
	}

	public Double getLuasMhn() {
		return luasMhn;
	}

	public void setLuasMhn(Double luasMhn) {
		this.luasMhn = luasMhn;
	}

	public Long getIdUnitluasbaki() {
		return idUnitluasbaki;
	}

	public void setIdUnitluasbaki(Long idUnitluasbaki) {
		this.idUnitluasbaki = idUnitluasbaki;
	}

	public String getTarikhmUlaMhn() {
		return tarikhmUlaMhn;
	}

	public void setTarikhmUlaMhn(String tarikhmUlaMhn) {
		this.tarikhmUlaMhn = tarikhmUlaMhn;
	}

	public String getTarikhTamatMhn() {
		return tarikhTamatMhn;
	}

	public void setTarikhTamatMhn(String tarikhTamatMhn) {
		this.tarikhTamatMhn = tarikhTamatMhn;
	}

	public String getFlagLbhCeroboh() {
		return flagLbhCeroboh;
	}

	public void setFlagLbhCeroboh(String flagLbhCeroboh) {
		this.flagLbhCeroboh = flagLbhCeroboh;
	}

	public Long getIdJenisceroboh() {
		return idJenisceroboh;
	}

	public void setIdJenisceroboh(Long idJenisceroboh) {
		this.idJenisceroboh = idJenisceroboh;
	}

	public String getLainJnsCeroboh() {
		return lainJnsCeroboh;
	}

	public void setLainJnsCeroboh(String lainJnsCeroboh) {
		this.lainJnsCeroboh = lainJnsCeroboh;
	}

	public String getTarikhSewaBaru() {
		return tarikhSewaBaru;
	}

	public void setTarikhSewaBaru(String tarikhSewaBaru) {
		this.tarikhSewaBaru = tarikhSewaBaru;
	}

	public String getTarikhHantarSrt() {
		return tarikhHantarSrt;
	}

	public void setTarikhHantarSrt(String tarikhHantarSrt) {
		this.tarikhHantarSrt = tarikhHantarSrt;
	}

	public String getTarikhKelulusanRayuan() {
		return tarikhKelulusanRayuan;
	}

	public void setTarikhKelulusanRayuan(String tarikhKelulusanRayuan) {
		this.tarikhKelulusanRayuan = tarikhKelulusanRayuan;
	}

	public String getTarikhKeputusan() {
		return tarikhKeputusan;
	}

	public void setTarikhKeputusan(String tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}

	public String getFlagKeputusan() {
		return flagKeputusan;
	}

	public void setFlagKeputusan(String flagKeputusan) {
		this.flagKeputusan = flagKeputusan;
	}

	public String getFlagRayuankadarsewa() {
		return flagRayuankadarsewa;
	}

	public void setFlagRayuankadarsewa(String flagRayuankadarsewa) {
		this.flagRayuankadarsewa = flagRayuankadarsewa;
	}

	public String getSebabRayuan() {
		return sebabRayuan;
	}

	public void setSebabRayuan(String sebabRayuan) {
		this.sebabRayuan = sebabRayuan;
	}

	public String getTajukSewa() {
		return tajukSewa;
	}

	public void setTajukSewa(String tajukSewa) {
		this.tajukSewa = tajukSewa;
	}

	public Double getTempohSewa() {
		return tempohSewa;
	}

	public void setTempohSewa(Double tempohSewa) {
		this.tempohSewa = tempohSewa;
	}

	public String getFlagKeputusanRayuan() {
		return flagKeputusanRayuan;
	}

	public void setFlagKeputusanRayuan(String flagKeputusanRayuan) {
		this.flagKeputusanRayuan = flagKeputusanRayuan;
	}

	public Double getPermohonanDari() {
		return permohonanDari;
	}

	public void setPermohonanDari(Double permohonanDari) {
		this.permohonanDari = permohonanDari;
	}

	public String getFlagKeputusanTujuan() {
		return flagKeputusanTujuan;
	}

	public void setFlagKeputusanTujuan(String flagKeputusanTujuan) {
		this.flagKeputusanTujuan = flagKeputusanTujuan;
	}

	public String getFlagKeputusanTarikh() {
		return flagKeputusanTarikh;
	}

	public void setFlagKeputusanTarikh(String flagKeputusanTarikh) {
		this.flagKeputusanTarikh = flagKeputusanTarikh;
	}

	public String getTarikhKelulusanTarikh() {
		return tarikhKelulusanTarikh;
	}

	public void setTarikhKelulusanTarikh(String tarikhKelulusanTarikh) {
		this.tarikhKelulusanTarikh = tarikhKelulusanTarikh;
	}

	public String getTarikhKelulusanTujuan() {
		return tarikhKelulusanTujuan;
	}

	public void setTarikhKelulusanTujuan(String tarikhKelulusanTujuan) {
		this.tarikhKelulusanTujuan = tarikhKelulusanTujuan;
	}

	public String getKelulusan() {
		return kelulusan;
	}

	public void setKelulusan(String kelulusan) {
		this.kelulusan = kelulusan;
	}

	public String getTarikhHantarkeputusan() {
		return tarikhHantarkeputusan;
	}

	public void setTarikhHantarkeputusan(String tarikhHantarkeputusan) {
		this.tarikhHantarkeputusan = tarikhHantarkeputusan;
	}

	public String getFlagProsesfail() {
		return flagProsesfail;
	}

	public void setFlagProsesfail(String flagProsesfail) {
		this.flagProsesfail = flagProsesfail;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getFlagJenisperjanjian() {
		return flagJenisperjanjian;
	}

	public void setFlagJenisperjanjian(String flagJenisperjanjian) {
		this.flagJenisperjanjian = flagJenisperjanjian;
	}

	public String getTarikhMulaperjanjian() {
		return tarikhMulaperjanjian;
	}

	public void setTarikhMulaperjanjian(String tarikhMulaperjanjian) {
		this.tarikhMulaperjanjian = tarikhMulaperjanjian;
	}

	public String getTarikhTerimatamat() {
		return tarikhTerimatamat;
	}

	public void setTarikhTerimatamat(String tarikhTerimatamat) {
		this.tarikhTerimatamat = tarikhTerimatamat;
	}

	public String getTarikhSurattamat() {
		return tarikhSurattamat;
	}

	public void setTarikhSurattamat(String tarikhSurattamat) {
		this.tarikhSurattamat = tarikhSurattamat;
	}

	public String getNoRujSuratTamat() {
		return noRujSuratTamat;
	}

	public void setNoRujSuratTamat(String noRujSuratTamat) {
		this.noRujSuratTamat = noRujSuratTamat;
	}

	public String getFlagSebabTamat() {
		return flagSebabTamat;
	}

	public void setFlagSebabTamat(String flagSebabTamat) {
		this.flagSebabTamat = flagSebabTamat;
	}

	public String getCatatanTamat() {
		return catatanTamat;
	}

	public void setCatatanTamat(String catatanTamat) {
		this.catatanTamat = catatanTamat;
	}

	public String getSebabTolakRingkas() {
		return sebabTolakRingkas;
	}

	public void setSebabTolakRingkas(String sebabTolakRingkas) {
		this.sebabTolakRingkas = sebabTolakRingkas;
	}

	public String getTarikhSuratTolakRingkas() {
		return tarikhSuratTolakRingkas;
	}

	public void setTarikhSuratTolakRingkas(String tarikhSuratTolakRingkas) {
		this.tarikhSuratTolakRingkas = tarikhSuratTolakRingkas;
	}

	public String getFlagPost() {
		return flagPost;
	}

	public void setFlagPost(String flagPost) {
		this.flagPost = flagPost;
	}

	public String getKeputusan() {
		return keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}

	public String getIdSebabtamat() {
		return idSebabtamat;
	}

	public void setIdSebabtamat(String idSebabtamat) {
		this.idSebabtamat = idSebabtamat;
	}

}
