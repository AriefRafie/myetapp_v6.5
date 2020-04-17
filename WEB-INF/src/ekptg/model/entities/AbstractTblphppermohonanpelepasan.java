package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppermohonanpelepasan entity provides the base persistence
 * definition of the Tblphppermohonanpelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppermohonanpelepasan implements
		java.io.Serializable {

	// Fields

	private Long idPhppermohonanpelepasan;
	private Long idPermohonan;
	private String keterangan;
	private String noRujukanSurat;
	private String lokasi;
	private String namaProjek;
	private String flagJenisProjek;
	private String tajukPermohonan;
	private Long idUnitluasmhn;
	private Double luasMhn;
	private Long idUnitluasbaki;
	private Double luasBaki;
	private String kemajuanTanah;
	private String flagGuna;
	private Long idUnitluasdiluluskan;
	private Double luasDiluluskan;
	private Long idUnitluasbakidiluluskan;
	private Double luasBakiDiluluskan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblphppermohonanpelepasan() {
	}

	/** full constructor */
	public AbstractTblphppermohonanpelepasan(Long idPermohonan,
			String keterangan, String noRujukanSurat, String lokasi,
			String namaProjek, String flagJenisProjek, String tajukPermohonan,
			Long idUnitluasmhn, Double luasMhn, Long idUnitluasbaki,
			Double luasBaki, String kemajuanTanah, String flagGuna,
			Long idUnitluasdiluluskan, Double luasDiluluskan,
			Long idUnitluasbakidiluluskan, Double luasBakiDiluluskan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.idPermohonan = idPermohonan;
		this.keterangan = keterangan;
		this.noRujukanSurat = noRujukanSurat;
		this.lokasi = lokasi;
		this.namaProjek = namaProjek;
		this.flagJenisProjek = flagJenisProjek;
		this.tajukPermohonan = tajukPermohonan;
		this.idUnitluasmhn = idUnitluasmhn;
		this.luasMhn = luasMhn;
		this.idUnitluasbaki = idUnitluasbaki;
		this.luasBaki = luasBaki;
		this.kemajuanTanah = kemajuanTanah;
		this.flagGuna = flagGuna;
		this.idUnitluasdiluluskan = idUnitluasdiluluskan;
		this.luasDiluluskan = luasDiluluskan;
		this.idUnitluasbakidiluluskan = idUnitluasbakidiluluskan;
		this.luasBakiDiluluskan = luasBakiDiluluskan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdPhppermohonanpelepasan() {
		return this.idPhppermohonanpelepasan;
	}

	public void setIdPhppermohonanpelepasan(Long idPhppermohonanpelepasan) {
		this.idPhppermohonanpelepasan = idPhppermohonanpelepasan;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getNoRujukanSurat() {
		return this.noRujukanSurat;
	}

	public void setNoRujukanSurat(String noRujukanSurat) {
		this.noRujukanSurat = noRujukanSurat;
	}

	public String getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getNamaProjek() {
		return this.namaProjek;
	}

	public void setNamaProjek(String namaProjek) {
		this.namaProjek = namaProjek;
	}

	public String getFlagJenisProjek() {
		return this.flagJenisProjek;
	}

	public void setFlagJenisProjek(String flagJenisProjek) {
		this.flagJenisProjek = flagJenisProjek;
	}

	public String getTajukPermohonan() {
		return this.tajukPermohonan;
	}

	public void setTajukPermohonan(String tajukPermohonan) {
		this.tajukPermohonan = tajukPermohonan;
	}

	public Long getIdUnitluasmhn() {
		return this.idUnitluasmhn;
	}

	public void setIdUnitluasmhn(Long idUnitluasmhn) {
		this.idUnitluasmhn = idUnitluasmhn;
	}

	public Double getLuasMhn() {
		return this.luasMhn;
	}

	public void setLuasMhn(Double luasMhn) {
		this.luasMhn = luasMhn;
	}

	public Long getIdUnitluasbaki() {
		return this.idUnitluasbaki;
	}

	public void setIdUnitluasbaki(Long idUnitluasbaki) {
		this.idUnitluasbaki = idUnitluasbaki;
	}

	public Double getLuasBaki() {
		return this.luasBaki;
	}

	public void setLuasBaki(Double luasBaki) {
		this.luasBaki = luasBaki;
	}

	public String getKemajuanTanah() {
		return this.kemajuanTanah;
	}

	public void setKemajuanTanah(String kemajuanTanah) {
		this.kemajuanTanah = kemajuanTanah;
	}

	public String getFlagGuna() {
		return this.flagGuna;
	}

	public void setFlagGuna(String flagGuna) {
		this.flagGuna = flagGuna;
	}

	public Long getIdUnitluasdiluluskan() {
		return this.idUnitluasdiluluskan;
	}

	public void setIdUnitluasdiluluskan(Long idUnitluasdiluluskan) {
		this.idUnitluasdiluluskan = idUnitluasdiluluskan;
	}

	public Double getLuasDiluluskan() {
		return this.luasDiluluskan;
	}

	public void setLuasDiluluskan(Double luasDiluluskan) {
		this.luasDiluluskan = luasDiluluskan;
	}

	public Long getIdUnitluasbakidiluluskan() {
		return this.idUnitluasbakidiluluskan;
	}

	public void setIdUnitluasbakidiluluskan(Long idUnitluasbakidiluluskan) {
		this.idUnitluasbakidiluluskan = idUnitluasbakidiluluskan;
	}

	public Double getLuasBakiDiluluskan() {
		return this.luasBakiDiluluskan;
	}

	public void setLuasBakiDiluluskan(Double luasBakiDiluluskan) {
		this.luasBakiDiluluskan = luasBakiDiluluskan;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}