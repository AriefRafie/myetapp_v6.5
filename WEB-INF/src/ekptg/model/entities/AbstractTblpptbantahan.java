package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptbantahan entity provides the base persistence definition of the
 * Tblpptbantahan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptbantahan implements java.io.Serializable {

	// Fields

	private Long idBantahan;
	private String noBantahan;
	private Long jenisPembantah;
	private String namaPembantah;
	private Date tarikhTerima;
	private Date tarikhBorangn;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private String flagPenerimaPampasan;
	private String flagBahagianPampasan;
	private String flagUkurLuas;
	private String flagPampasan;
	private String flagSyarat;
	private Long idPihakberkepentingan;
	private Long idKementerian;
	private Long idAgensi;
	private Double amaunAward;
	private Date tarikhTerimaAward;
	private Long idAward;
	private String alasan;
	private Long statusBantahan;
	private Long caraBayar;
	private String noRujukanBayaran;
	private Date tarikhSuratBayardeposit;
	private String tempohBayar;
	private Long unitTempoh;
	private Date tarikhAkhirBayardeposit;
	private Date tarikhTerimaResit;
	private Date tarikhResit;
	private String noResit;
	private String flagTerimadeposit;
	private Double amaunDeposit;
	private String noRujukanSurattarikbalik;
	private Date tarikhTerimaTarikbalik;
	private Date tarikhSuratTarikbalik;
	private String flagTarikbalik;
	private Date tarikhCetak;
	private Date tarikhCetakSemula;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptbantahan() {
	}

	/** minimal constructor */
	public AbstractTblpptbantahan(Long idKementerian) {
		this.idKementerian = idKementerian;
	}

	/** full constructor */
	public AbstractTblpptbantahan(String noBantahan, Long jenisPembantah,
			String namaPembantah, Date tarikhTerima, Date tarikhBorangn,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, String flagPenerimaPampasan,
			String flagBahagianPampasan, String flagUkurLuas,
			String flagPampasan, String flagSyarat, Long idPihakberkepentingan,
			Long idKementerian, Long idAgensi, Double amaunAward,
			Date tarikhTerimaAward, Long idAward, String alasan,
			Long statusBantahan, Long caraBayar, String noRujukanBayaran,
			Date tarikhSuratBayardeposit, String tempohBayar, Long unitTempoh,
			Date tarikhAkhirBayardeposit, Date tarikhTerimaResit,
			Date tarikhResit, String noResit, String flagTerimadeposit,
			Double amaunDeposit, String noRujukanSurattarikbalik,
			Date tarikhTerimaTarikbalik, Date tarikhSuratTarikbalik,
			String flagTarikbalik, Date tarikhCetak, Date tarikhCetakSemula,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.noBantahan = noBantahan;
		this.jenisPembantah = jenisPembantah;
		this.namaPembantah = namaPembantah;
		this.tarikhTerima = tarikhTerima;
		this.tarikhBorangn = tarikhBorangn;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.flagPenerimaPampasan = flagPenerimaPampasan;
		this.flagBahagianPampasan = flagBahagianPampasan;
		this.flagUkurLuas = flagUkurLuas;
		this.flagPampasan = flagPampasan;
		this.flagSyarat = flagSyarat;
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.idKementerian = idKementerian;
		this.idAgensi = idAgensi;
		this.amaunAward = amaunAward;
		this.tarikhTerimaAward = tarikhTerimaAward;
		this.idAward = idAward;
		this.alasan = alasan;
		this.statusBantahan = statusBantahan;
		this.caraBayar = caraBayar;
		this.noRujukanBayaran = noRujukanBayaran;
		this.tarikhSuratBayardeposit = tarikhSuratBayardeposit;
		this.tempohBayar = tempohBayar;
		this.unitTempoh = unitTempoh;
		this.tarikhAkhirBayardeposit = tarikhAkhirBayardeposit;
		this.tarikhTerimaResit = tarikhTerimaResit;
		this.tarikhResit = tarikhResit;
		this.noResit = noResit;
		this.flagTerimadeposit = flagTerimadeposit;
		this.amaunDeposit = amaunDeposit;
		this.noRujukanSurattarikbalik = noRujukanSurattarikbalik;
		this.tarikhTerimaTarikbalik = tarikhTerimaTarikbalik;
		this.tarikhSuratTarikbalik = tarikhSuratTarikbalik;
		this.flagTarikbalik = flagTarikbalik;
		this.tarikhCetak = tarikhCetak;
		this.tarikhCetakSemula = tarikhCetakSemula;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBantahan() {
		return this.idBantahan;
	}

	public void setIdBantahan(Long idBantahan) {
		this.idBantahan = idBantahan;
	}

	public String getNoBantahan() {
		return this.noBantahan;
	}

	public void setNoBantahan(String noBantahan) {
		this.noBantahan = noBantahan;
	}

	public Long getJenisPembantah() {
		return this.jenisPembantah;
	}

	public void setJenisPembantah(Long jenisPembantah) {
		this.jenisPembantah = jenisPembantah;
	}

	public String getNamaPembantah() {
		return this.namaPembantah;
	}

	public void setNamaPembantah(String namaPembantah) {
		this.namaPembantah = namaPembantah;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhBorangn() {
		return this.tarikhBorangn;
	}

	public void setTarikhBorangn(Date tarikhBorangn) {
		this.tarikhBorangn = tarikhBorangn;
	}

	public String getAlamat1() {
		return this.alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return this.alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return this.alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getFlagPenerimaPampasan() {
		return this.flagPenerimaPampasan;
	}

	public void setFlagPenerimaPampasan(String flagPenerimaPampasan) {
		this.flagPenerimaPampasan = flagPenerimaPampasan;
	}

	public String getFlagBahagianPampasan() {
		return this.flagBahagianPampasan;
	}

	public void setFlagBahagianPampasan(String flagBahagianPampasan) {
		this.flagBahagianPampasan = flagBahagianPampasan;
	}

	public String getFlagUkurLuas() {
		return this.flagUkurLuas;
	}

	public void setFlagUkurLuas(String flagUkurLuas) {
		this.flagUkurLuas = flagUkurLuas;
	}

	public String getFlagPampasan() {
		return this.flagPampasan;
	}

	public void setFlagPampasan(String flagPampasan) {
		this.flagPampasan = flagPampasan;
	}

	public String getFlagSyarat() {
		return this.flagSyarat;
	}

	public void setFlagSyarat(String flagSyarat) {
		this.flagSyarat = flagSyarat;
	}

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
	}

	public Long getIdKementerian() {
		return this.idKementerian;
	}

	public void setIdKementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
	}

	public Long getIdAgensi() {
		return this.idAgensi;
	}

	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}

	public Double getAmaunAward() {
		return this.amaunAward;
	}

	public void setAmaunAward(Double amaunAward) {
		this.amaunAward = amaunAward;
	}

	public Date getTarikhTerimaAward() {
		return this.tarikhTerimaAward;
	}

	public void setTarikhTerimaAward(Date tarikhTerimaAward) {
		this.tarikhTerimaAward = tarikhTerimaAward;
	}

	public Long getIdAward() {
		return this.idAward;
	}

	public void setIdAward(Long idAward) {
		this.idAward = idAward;
	}

	public String getAlasan() {
		return this.alasan;
	}

	public void setAlasan(String alasan) {
		this.alasan = alasan;
	}

	public Long getStatusBantahan() {
		return this.statusBantahan;
	}

	public void setStatusBantahan(Long statusBantahan) {
		this.statusBantahan = statusBantahan;
	}

	public Long getCaraBayar() {
		return this.caraBayar;
	}

	public void setCaraBayar(Long caraBayar) {
		this.caraBayar = caraBayar;
	}

	public String getNoRujukanBayaran() {
		return this.noRujukanBayaran;
	}

	public void setNoRujukanBayaran(String noRujukanBayaran) {
		this.noRujukanBayaran = noRujukanBayaran;
	}

	public Date getTarikhSuratBayardeposit() {
		return this.tarikhSuratBayardeposit;
	}

	public void setTarikhSuratBayardeposit(Date tarikhSuratBayardeposit) {
		this.tarikhSuratBayardeposit = tarikhSuratBayardeposit;
	}

	public String getTempohBayar() {
		return this.tempohBayar;
	}

	public void setTempohBayar(String tempohBayar) {
		this.tempohBayar = tempohBayar;
	}

	public Long getUnitTempoh() {
		return this.unitTempoh;
	}

	public void setUnitTempoh(Long unitTempoh) {
		this.unitTempoh = unitTempoh;
	}

	public Date getTarikhAkhirBayardeposit() {
		return this.tarikhAkhirBayardeposit;
	}

	public void setTarikhAkhirBayardeposit(Date tarikhAkhirBayardeposit) {
		this.tarikhAkhirBayardeposit = tarikhAkhirBayardeposit;
	}

	public Date getTarikhTerimaResit() {
		return this.tarikhTerimaResit;
	}

	public void setTarikhTerimaResit(Date tarikhTerimaResit) {
		this.tarikhTerimaResit = tarikhTerimaResit;
	}

	public Date getTarikhResit() {
		return this.tarikhResit;
	}

	public void setTarikhResit(Date tarikhResit) {
		this.tarikhResit = tarikhResit;
	}

	public String getNoResit() {
		return this.noResit;
	}

	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}

	public String getFlagTerimadeposit() {
		return this.flagTerimadeposit;
	}

	public void setFlagTerimadeposit(String flagTerimadeposit) {
		this.flagTerimadeposit = flagTerimadeposit;
	}

	public Double getAmaunDeposit() {
		return this.amaunDeposit;
	}

	public void setAmaunDeposit(Double amaunDeposit) {
		this.amaunDeposit = amaunDeposit;
	}

	public String getNoRujukanSurattarikbalik() {
		return this.noRujukanSurattarikbalik;
	}

	public void setNoRujukanSurattarikbalik(String noRujukanSurattarikbalik) {
		this.noRujukanSurattarikbalik = noRujukanSurattarikbalik;
	}

	public Date getTarikhTerimaTarikbalik() {
		return this.tarikhTerimaTarikbalik;
	}

	public void setTarikhTerimaTarikbalik(Date tarikhTerimaTarikbalik) {
		this.tarikhTerimaTarikbalik = tarikhTerimaTarikbalik;
	}

	public Date getTarikhSuratTarikbalik() {
		return this.tarikhSuratTarikbalik;
	}

	public void setTarikhSuratTarikbalik(Date tarikhSuratTarikbalik) {
		this.tarikhSuratTarikbalik = tarikhSuratTarikbalik;
	}

	public String getFlagTarikbalik() {
		return this.flagTarikbalik;
	}

	public void setFlagTarikbalik(String flagTarikbalik) {
		this.flagTarikbalik = flagTarikbalik;
	}

	public Date getTarikhCetak() {
		return this.tarikhCetak;
	}

	public void setTarikhCetak(Date tarikhCetak) {
		this.tarikhCetak = tarikhCetak;
	}

	public Date getTarikhCetakSemula() {
		return this.tarikhCetakSemula;
	}

	public void setTarikhCetakSemula(Date tarikhCetakSemula) {
		this.tarikhCetakSemula = tarikhCetakSemula;
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