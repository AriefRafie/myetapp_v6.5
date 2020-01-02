package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphppermohonanpenyewaan entity provides the base persistence
 * definition of the Tblphppermohonanpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppermohonanpenyewaan implements
		java.io.Serializable {

	// Fields

	private Long idPermohonanpenyewaan;
	private Long idFail;
	private String tujuan;
	private Long idJenissewa;
	private String keteranganAduan;
	private Date tarikhSurat;
	private String lokasi;
	private String tajukPermohonan;
	private Long idUnitluasmhn;
	private Double luasMhn;
	private Long idUnitluasbaki;
	private Double luasBaki;
	private String flagGuna;
	private Date tarikhMulaMhn;
	private Date tarikhTamatMhn;
	private Long idStatus;
	private String noPermohonanOnline;
	private String flagLbhCeroboh;
	private Long idJenisceroboh;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idUrusan;
	private Set tblphpulasanteknikalpenyewaans = new HashSet(0);
	private Set tblphppencerobohs = new HashSet(0);
	private Set tblphppemohonpenyewaans = new HashSet(0);
	private Set tblphpmohontanahpnywns = new HashSet(0);
	private Set tblphpperjanjianpenyewaans = new HashSet(0);
	private Set tblphpkertaskerjapenyewaans = new HashSet(0);
	private Set tblphplawatantapakpenyewaans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphppermohonanpenyewaan() {
	}

	/** minimal constructor */
	public AbstractTblphppermohonanpenyewaan(Long idPermohonanpenyewaan) {
		this.idPermohonanpenyewaan = idPermohonanpenyewaan;
	}

	/** full constructor */
	public AbstractTblphppermohonanpenyewaan(Long idPermohonanpenyewaan,
			Long idFail, String tujuan, Long idJenissewa,
			String keteranganAduan, Date tarikhSurat, String lokasi,
			String tajukPermohonan, Long idUnitluasmhn, Double luasMhn,
			Long idUnitluasbaki, Double luasBaki, String flagGuna,
			Date tarikhMulaMhn, Date tarikhTamatMhn, Long idStatus,
			String noPermohonanOnline, String flagLbhCeroboh,
			Long idJenisceroboh, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idUrusan,
			Set tblphpulasanteknikalpenyewaans, Set tblphppencerobohs,
			Set tblphppemohonpenyewaans, Set tblphpmohontanahpnywns,
			Set tblphpperjanjianpenyewaans, Set tblphpkertaskerjapenyewaans,
			Set tblphplawatantapakpenyewaans) {
		this.idPermohonanpenyewaan = idPermohonanpenyewaan;
		this.idFail = idFail;
		this.tujuan = tujuan;
		this.idJenissewa = idJenissewa;
		this.keteranganAduan = keteranganAduan;
		this.tarikhSurat = tarikhSurat;
		this.lokasi = lokasi;
		this.tajukPermohonan = tajukPermohonan;
		this.idUnitluasmhn = idUnitluasmhn;
		this.luasMhn = luasMhn;
		this.idUnitluasbaki = idUnitluasbaki;
		this.luasBaki = luasBaki;
		this.flagGuna = flagGuna;
		this.tarikhMulaMhn = tarikhMulaMhn;
		this.tarikhTamatMhn = tarikhTamatMhn;
		this.idStatus = idStatus;
		this.noPermohonanOnline = noPermohonanOnline;
		this.flagLbhCeroboh = flagLbhCeroboh;
		this.idJenisceroboh = idJenisceroboh;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idUrusan = idUrusan;
		this.tblphpulasanteknikalpenyewaans = tblphpulasanteknikalpenyewaans;
		this.tblphppencerobohs = tblphppencerobohs;
		this.tblphppemohonpenyewaans = tblphppemohonpenyewaans;
		this.tblphpmohontanahpnywns = tblphpmohontanahpnywns;
		this.tblphpperjanjianpenyewaans = tblphpperjanjianpenyewaans;
		this.tblphpkertaskerjapenyewaans = tblphpkertaskerjapenyewaans;
		this.tblphplawatantapakpenyewaans = tblphplawatantapakpenyewaans;
	}

	// Property accessors

	public Long getIdPermohonanpenyewaan() {
		return this.idPermohonanpenyewaan;
	}

	public void setIdPermohonanpenyewaan(Long idPermohonanpenyewaan) {
		this.idPermohonanpenyewaan = idPermohonanpenyewaan;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public String getTujuan() {
		return this.tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public Long getIdJenissewa() {
		return this.idJenissewa;
	}

	public void setIdJenissewa(Long idJenissewa) {
		this.idJenissewa = idJenissewa;
	}

	public String getKeteranganAduan() {
		return this.keteranganAduan;
	}

	public void setKeteranganAduan(String keteranganAduan) {
		this.keteranganAduan = keteranganAduan;
	}

	public Date getTarikhSurat() {
		return this.tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}

	public String getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
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

	public String getFlagGuna() {
		return this.flagGuna;
	}

	public void setFlagGuna(String flagGuna) {
		this.flagGuna = flagGuna;
	}

	public Date getTarikhMulaMhn() {
		return this.tarikhMulaMhn;
	}

	public void setTarikhMulaMhn(Date tarikhMulaMhn) {
		this.tarikhMulaMhn = tarikhMulaMhn;
	}

	public Date getTarikhTamatMhn() {
		return this.tarikhTamatMhn;
	}

	public void setTarikhTamatMhn(Date tarikhTamatMhn) {
		this.tarikhTamatMhn = tarikhTamatMhn;
	}

	public Long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getNoPermohonanOnline() {
		return this.noPermohonanOnline;
	}

	public void setNoPermohonanOnline(String noPermohonanOnline) {
		this.noPermohonanOnline = noPermohonanOnline;
	}

	public String getFlagLbhCeroboh() {
		return this.flagLbhCeroboh;
	}

	public void setFlagLbhCeroboh(String flagLbhCeroboh) {
		this.flagLbhCeroboh = flagLbhCeroboh;
	}

	public Long getIdJenisceroboh() {
		return this.idJenisceroboh;
	}

	public void setIdJenisceroboh(Long idJenisceroboh) {
		this.idJenisceroboh = idJenisceroboh;
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

	public Long getIdUrusan() {
		return this.idUrusan;
	}

	public void setIdUrusan(Long idUrusan) {
		this.idUrusan = idUrusan;
	}

	public Set getTblphpulasanteknikalpenyewaans() {
		return this.tblphpulasanteknikalpenyewaans;
	}

	public void setTblphpulasanteknikalpenyewaans(
			Set tblphpulasanteknikalpenyewaans) {
		this.tblphpulasanteknikalpenyewaans = tblphpulasanteknikalpenyewaans;
	}

	public Set getTblphppencerobohs() {
		return this.tblphppencerobohs;
	}

	public void setTblphppencerobohs(Set tblphppencerobohs) {
		this.tblphppencerobohs = tblphppencerobohs;
	}

	public Set getTblphppemohonpenyewaans() {
		return this.tblphppemohonpenyewaans;
	}

	public void setTblphppemohonpenyewaans(Set tblphppemohonpenyewaans) {
		this.tblphppemohonpenyewaans = tblphppemohonpenyewaans;
	}

	public Set getTblphpmohontanahpnywns() {
		return this.tblphpmohontanahpnywns;
	}

	public void setTblphpmohontanahpnywns(Set tblphpmohontanahpnywns) {
		this.tblphpmohontanahpnywns = tblphpmohontanahpnywns;
	}

	public Set getTblphpperjanjianpenyewaans() {
		return this.tblphpperjanjianpenyewaans;
	}

	public void setTblphpperjanjianpenyewaans(Set tblphpperjanjianpenyewaans) {
		this.tblphpperjanjianpenyewaans = tblphpperjanjianpenyewaans;
	}

	public Set getTblphpkertaskerjapenyewaans() {
		return this.tblphpkertaskerjapenyewaans;
	}

	public void setTblphpkertaskerjapenyewaans(Set tblphpkertaskerjapenyewaans) {
		this.tblphpkertaskerjapenyewaans = tblphpkertaskerjapenyewaans;
	}

	public Set getTblphplawatantapakpenyewaans() {
		return this.tblphplawatantapakpenyewaans;
	}

	public void setTblphplawatantapakpenyewaans(Set tblphplawatantapakpenyewaans) {
		this.tblphplawatantapakpenyewaans = tblphplawatantapakpenyewaans;
	}

}