package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptpermintaanukur entity provides the base persistence definition
 * of the Tblpptpermintaanukur entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptpermintaanukur implements
		java.io.Serializable {

	// Fields

	private Long idPermintaanukur;
	private String noRujukanPtg;
	private Date tarikhSuratPtg;
	private String noPelan;
	private String noPu;
	private Date tarikhPu;
	private Date tarikhTerimaSp;
	private String noSp;
	private Date tarikhTerimaSa;
	private Date tarikhTerimaPa;
	private String noPa;
	private Date tarikhSelesai;
	private Date tarikhKembaliUkur;
	private String catatan;
	private String namaPegawai;
	private Date tarikhSedia;
	private Date tarikhBayar;
	private Date tarikhHakmilikSambung;
	private Long jenisPelarasan;
	private Double amaunPu;
	private Long idUnitluaspu;
	private Long luasPu;
	private Double faedahSebelum;
	private Double faedahSelepas;
	private Date tarikhBayarAward;
	private Date tarikhBorangk;
	private Date tarikhTerimaAgensi;
	private Date tarikhAkhirAgensi;
	private Date tarikhHantarJupem;
	private Long idHakmilik;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptpermintaanukur() {
	}

	/** full constructor */
	public AbstractTblpptpermintaanukur(String noRujukanPtg,
			Date tarikhSuratPtg, String noPelan, String noPu, Date tarikhPu,
			Date tarikhTerimaSp, String noSp, Date tarikhTerimaSa,
			Date tarikhTerimaPa, String noPa, Date tarikhSelesai,
			Date tarikhKembaliUkur, String catatan, String namaPegawai,
			Date tarikhSedia, Date tarikhBayar, Date tarikhHakmilikSambung,
			Long jenisPelarasan, Double amaunPu, Long idUnitluaspu,
			Long luasPu, Double faedahSebelum, Double faedahSelepas,
			Date tarikhBayarAward, Date tarikhBorangk, Date tarikhTerimaAgensi,
			Date tarikhAkhirAgensi, Date tarikhHantarJupem, Long idHakmilik,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.noRujukanPtg = noRujukanPtg;
		this.tarikhSuratPtg = tarikhSuratPtg;
		this.noPelan = noPelan;
		this.noPu = noPu;
		this.tarikhPu = tarikhPu;
		this.tarikhTerimaSp = tarikhTerimaSp;
		this.noSp = noSp;
		this.tarikhTerimaSa = tarikhTerimaSa;
		this.tarikhTerimaPa = tarikhTerimaPa;
		this.noPa = noPa;
		this.tarikhSelesai = tarikhSelesai;
		this.tarikhKembaliUkur = tarikhKembaliUkur;
		this.catatan = catatan;
		this.namaPegawai = namaPegawai;
		this.tarikhSedia = tarikhSedia;
		this.tarikhBayar = tarikhBayar;
		this.tarikhHakmilikSambung = tarikhHakmilikSambung;
		this.jenisPelarasan = jenisPelarasan;
		this.amaunPu = amaunPu;
		this.idUnitluaspu = idUnitluaspu;
		this.luasPu = luasPu;
		this.faedahSebelum = faedahSebelum;
		this.faedahSelepas = faedahSelepas;
		this.tarikhBayarAward = tarikhBayarAward;
		this.tarikhBorangk = tarikhBorangk;
		this.tarikhTerimaAgensi = tarikhTerimaAgensi;
		this.tarikhAkhirAgensi = tarikhAkhirAgensi;
		this.tarikhHantarJupem = tarikhHantarJupem;
		this.idHakmilik = idHakmilik;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdPermintaanukur() {
		return this.idPermintaanukur;
	}

	public void setIdPermintaanukur(Long idPermintaanukur) {
		this.idPermintaanukur = idPermintaanukur;
	}

	public String getNoRujukanPtg() {
		return this.noRujukanPtg;
	}

	public void setNoRujukanPtg(String noRujukanPtg) {
		this.noRujukanPtg = noRujukanPtg;
	}

	public Date getTarikhSuratPtg() {
		return this.tarikhSuratPtg;
	}

	public void setTarikhSuratPtg(Date tarikhSuratPtg) {
		this.tarikhSuratPtg = tarikhSuratPtg;
	}

	public String getNoPelan() {
		return this.noPelan;
	}

	public void setNoPelan(String noPelan) {
		this.noPelan = noPelan;
	}

	public String getNoPu() {
		return this.noPu;
	}

	public void setNoPu(String noPu) {
		this.noPu = noPu;
	}

	public Date getTarikhPu() {
		return this.tarikhPu;
	}

	public void setTarikhPu(Date tarikhPu) {
		this.tarikhPu = tarikhPu;
	}

	public Date getTarikhTerimaSp() {
		return this.tarikhTerimaSp;
	}

	public void setTarikhTerimaSp(Date tarikhTerimaSp) {
		this.tarikhTerimaSp = tarikhTerimaSp;
	}

	public String getNoSp() {
		return this.noSp;
	}

	public void setNoSp(String noSp) {
		this.noSp = noSp;
	}

	public Date getTarikhTerimaSa() {
		return this.tarikhTerimaSa;
	}

	public void setTarikhTerimaSa(Date tarikhTerimaSa) {
		this.tarikhTerimaSa = tarikhTerimaSa;
	}

	public Date getTarikhTerimaPa() {
		return this.tarikhTerimaPa;
	}

	public void setTarikhTerimaPa(Date tarikhTerimaPa) {
		this.tarikhTerimaPa = tarikhTerimaPa;
	}

	public String getNoPa() {
		return this.noPa;
	}

	public void setNoPa(String noPa) {
		this.noPa = noPa;
	}

	public Date getTarikhSelesai() {
		return this.tarikhSelesai;
	}

	public void setTarikhSelesai(Date tarikhSelesai) {
		this.tarikhSelesai = tarikhSelesai;
	}

	public Date getTarikhKembaliUkur() {
		return this.tarikhKembaliUkur;
	}

	public void setTarikhKembaliUkur(Date tarikhKembaliUkur) {
		this.tarikhKembaliUkur = tarikhKembaliUkur;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getNamaPegawai() {
		return this.namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}

	public Date getTarikhSedia() {
		return this.tarikhSedia;
	}

	public void setTarikhSedia(Date tarikhSedia) {
		this.tarikhSedia = tarikhSedia;
	}

	public Date getTarikhBayar() {
		return this.tarikhBayar;
	}

	public void setTarikhBayar(Date tarikhBayar) {
		this.tarikhBayar = tarikhBayar;
	}

	public Date getTarikhHakmilikSambung() {
		return this.tarikhHakmilikSambung;
	}

	public void setTarikhHakmilikSambung(Date tarikhHakmilikSambung) {
		this.tarikhHakmilikSambung = tarikhHakmilikSambung;
	}

	public Long getJenisPelarasan() {
		return this.jenisPelarasan;
	}

	public void setJenisPelarasan(Long jenisPelarasan) {
		this.jenisPelarasan = jenisPelarasan;
	}

	public Double getAmaunPu() {
		return this.amaunPu;
	}

	public void setAmaunPu(Double amaunPu) {
		this.amaunPu = amaunPu;
	}

	public Long getIdUnitluaspu() {
		return this.idUnitluaspu;
	}

	public void setIdUnitluaspu(Long idUnitluaspu) {
		this.idUnitluaspu = idUnitluaspu;
	}

	public Long getLuasPu() {
		return this.luasPu;
	}

	public void setLuasPu(Long luasPu) {
		this.luasPu = luasPu;
	}

	public Double getFaedahSebelum() {
		return this.faedahSebelum;
	}

	public void setFaedahSebelum(Double faedahSebelum) {
		this.faedahSebelum = faedahSebelum;
	}

	public Double getFaedahSelepas() {
		return this.faedahSelepas;
	}

	public void setFaedahSelepas(Double faedahSelepas) {
		this.faedahSelepas = faedahSelepas;
	}

	public Date getTarikhBayarAward() {
		return this.tarikhBayarAward;
	}

	public void setTarikhBayarAward(Date tarikhBayarAward) {
		this.tarikhBayarAward = tarikhBayarAward;
	}

	public Date getTarikhBorangk() {
		return this.tarikhBorangk;
	}

	public void setTarikhBorangk(Date tarikhBorangk) {
		this.tarikhBorangk = tarikhBorangk;
	}

	public Date getTarikhTerimaAgensi() {
		return this.tarikhTerimaAgensi;
	}

	public void setTarikhTerimaAgensi(Date tarikhTerimaAgensi) {
		this.tarikhTerimaAgensi = tarikhTerimaAgensi;
	}

	public Date getTarikhAkhirAgensi() {
		return this.tarikhAkhirAgensi;
	}

	public void setTarikhAkhirAgensi(Date tarikhAkhirAgensi) {
		this.tarikhAkhirAgensi = tarikhAkhirAgensi;
	}

	public Date getTarikhHantarJupem() {
		return this.tarikhHantarJupem;
	}

	public void setTarikhHantarJupem(Date tarikhHantarJupem) {
		this.tarikhHantarJupem = tarikhHantarJupem;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
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