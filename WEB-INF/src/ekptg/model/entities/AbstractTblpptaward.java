package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptaward entity provides the base persistence definition of the
 * Tblpptaward entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptaward implements java.io.Serializable {

	// Fields

	private Long idAward;
	private Long jenisAward;
	private Double bayarFee;
	private Double bayarPampasan;
	private Double bayarBangunan;
	private Double bayarBetterment;
	private Double bayarPecahpisah;
	private Double bayarPenjejasan;
	private Double bayarPendapatan;
	private Double bayarPindah;
	private Double bayarSewa;
	private Double bayarLain2;
	private Double luasAmbil;
	private Long idUnitluasAmbil;
	private Double nilaiSeunitAmbil;
	private String unitNilaiAmbil;
	private Long statusPenerima;
	private Double dendaLewat;
	private Date tarikhSediaAward;
	private Date tarikhTerimaAgensi;
	private String ulasan;
	private Date tarikhAkhirAgensi;
	private Double faedahSelepasPu;
	private Double faedahSebelumPu;
	private String flagSerahCek;
	private Long keputusanTawaran;
	private Long tempohPampasan;
	private Long unitTempoh;
	private Date tarikhSurat;
	private Long idPihakberkepentingan;
	private Long idSiasatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptaward() {
	}

	/** full constructor */
	public AbstractTblpptaward(Long jenisAward, Double bayarFee,
			Double bayarPampasan, Double bayarBangunan, Double bayarBetterment,
			Double bayarPecahpisah, Double bayarPenjejasan,
			Double bayarPendapatan, Double bayarPindah, Double bayarSewa,
			Double bayarLain2, Double luasAmbil, Long idUnitluasAmbil,
			Double nilaiSeunitAmbil, String unitNilaiAmbil,
			Long statusPenerima, Double dendaLewat, Date tarikhSediaAward,
			Date tarikhTerimaAgensi, String ulasan, Date tarikhAkhirAgensi,
			Double faedahSelepasPu, Double faedahSebelumPu,
			String flagSerahCek, Long keputusanTawaran, Long tempohPampasan,
			Long unitTempoh, Date tarikhSurat, Long idPihakberkepentingan,
			Long idSiasatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.jenisAward = jenisAward;
		this.bayarFee = bayarFee;
		this.bayarPampasan = bayarPampasan;
		this.bayarBangunan = bayarBangunan;
		this.bayarBetterment = bayarBetterment;
		this.bayarPecahpisah = bayarPecahpisah;
		this.bayarPenjejasan = bayarPenjejasan;
		this.bayarPendapatan = bayarPendapatan;
		this.bayarPindah = bayarPindah;
		this.bayarSewa = bayarSewa;
		this.bayarLain2 = bayarLain2;
		this.luasAmbil = luasAmbil;
		this.idUnitluasAmbil = idUnitluasAmbil;
		this.nilaiSeunitAmbil = nilaiSeunitAmbil;
		this.unitNilaiAmbil = unitNilaiAmbil;
		this.statusPenerima = statusPenerima;
		this.dendaLewat = dendaLewat;
		this.tarikhSediaAward = tarikhSediaAward;
		this.tarikhTerimaAgensi = tarikhTerimaAgensi;
		this.ulasan = ulasan;
		this.tarikhAkhirAgensi = tarikhAkhirAgensi;
		this.faedahSelepasPu = faedahSelepasPu;
		this.faedahSebelumPu = faedahSebelumPu;
		this.flagSerahCek = flagSerahCek;
		this.keputusanTawaran = keputusanTawaran;
		this.tempohPampasan = tempohPampasan;
		this.unitTempoh = unitTempoh;
		this.tarikhSurat = tarikhSurat;
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.idSiasatan = idSiasatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdAward() {
		return this.idAward;
	}

	public void setIdAward(Long idAward) {
		this.idAward = idAward;
	}

	public Long getJenisAward() {
		return this.jenisAward;
	}

	public void setJenisAward(Long jenisAward) {
		this.jenisAward = jenisAward;
	}

	public Double getBayarFee() {
		return this.bayarFee;
	}

	public void setBayarFee(Double bayarFee) {
		this.bayarFee = bayarFee;
	}

	public Double getBayarPampasan() {
		return this.bayarPampasan;
	}

	public void setBayarPampasan(Double bayarPampasan) {
		this.bayarPampasan = bayarPampasan;
	}

	public Double getBayarBangunan() {
		return this.bayarBangunan;
	}

	public void setBayarBangunan(Double bayarBangunan) {
		this.bayarBangunan = bayarBangunan;
	}

	public Double getBayarBetterment() {
		return this.bayarBetterment;
	}

	public void setBayarBetterment(Double bayarBetterment) {
		this.bayarBetterment = bayarBetterment;
	}

	public Double getBayarPecahpisah() {
		return this.bayarPecahpisah;
	}

	public void setBayarPecahpisah(Double bayarPecahpisah) {
		this.bayarPecahpisah = bayarPecahpisah;
	}

	public Double getBayarPenjejasan() {
		return this.bayarPenjejasan;
	}

	public void setBayarPenjejasan(Double bayarPenjejasan) {
		this.bayarPenjejasan = bayarPenjejasan;
	}

	public Double getBayarPendapatan() {
		return this.bayarPendapatan;
	}

	public void setBayarPendapatan(Double bayarPendapatan) {
		this.bayarPendapatan = bayarPendapatan;
	}

	public Double getBayarPindah() {
		return this.bayarPindah;
	}

	public void setBayarPindah(Double bayarPindah) {
		this.bayarPindah = bayarPindah;
	}

	public Double getBayarSewa() {
		return this.bayarSewa;
	}

	public void setBayarSewa(Double bayarSewa) {
		this.bayarSewa = bayarSewa;
	}

	public Double getBayarLain2() {
		return this.bayarLain2;
	}

	public void setBayarLain2(Double bayarLain2) {
		this.bayarLain2 = bayarLain2;
	}

	public Double getLuasAmbil() {
		return this.luasAmbil;
	}

	public void setLuasAmbil(Double luasAmbil) {
		this.luasAmbil = luasAmbil;
	}

	public Long getIdUnitluasAmbil() {
		return this.idUnitluasAmbil;
	}

	public void setIdUnitluasAmbil(Long idUnitluasAmbil) {
		this.idUnitluasAmbil = idUnitluasAmbil;
	}

	public Double getNilaiSeunitAmbil() {
		return this.nilaiSeunitAmbil;
	}

	public void setNilaiSeunitAmbil(Double nilaiSeunitAmbil) {
		this.nilaiSeunitAmbil = nilaiSeunitAmbil;
	}

	public String getUnitNilaiAmbil() {
		return this.unitNilaiAmbil;
	}

	public void setUnitNilaiAmbil(String unitNilaiAmbil) {
		this.unitNilaiAmbil = unitNilaiAmbil;
	}

	public Long getStatusPenerima() {
		return this.statusPenerima;
	}

	public void setStatusPenerima(Long statusPenerima) {
		this.statusPenerima = statusPenerima;
	}

	public Double getDendaLewat() {
		return this.dendaLewat;
	}

	public void setDendaLewat(Double dendaLewat) {
		this.dendaLewat = dendaLewat;
	}

	public Date getTarikhSediaAward() {
		return this.tarikhSediaAward;
	}

	public void setTarikhSediaAward(Date tarikhSediaAward) {
		this.tarikhSediaAward = tarikhSediaAward;
	}

	public Date getTarikhTerimaAgensi() {
		return this.tarikhTerimaAgensi;
	}

	public void setTarikhTerimaAgensi(Date tarikhTerimaAgensi) {
		this.tarikhTerimaAgensi = tarikhTerimaAgensi;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public Date getTarikhAkhirAgensi() {
		return this.tarikhAkhirAgensi;
	}

	public void setTarikhAkhirAgensi(Date tarikhAkhirAgensi) {
		this.tarikhAkhirAgensi = tarikhAkhirAgensi;
	}

	public Double getFaedahSelepasPu() {
		return this.faedahSelepasPu;
	}

	public void setFaedahSelepasPu(Double faedahSelepasPu) {
		this.faedahSelepasPu = faedahSelepasPu;
	}

	public Double getFaedahSebelumPu() {
		return this.faedahSebelumPu;
	}

	public void setFaedahSebelumPu(Double faedahSebelumPu) {
		this.faedahSebelumPu = faedahSebelumPu;
	}

	public String getFlagSerahCek() {
		return this.flagSerahCek;
	}

	public void setFlagSerahCek(String flagSerahCek) {
		this.flagSerahCek = flagSerahCek;
	}

	public Long getKeputusanTawaran() {
		return this.keputusanTawaran;
	}

	public void setKeputusanTawaran(Long keputusanTawaran) {
		this.keputusanTawaran = keputusanTawaran;
	}

	public Long getTempohPampasan() {
		return this.tempohPampasan;
	}

	public void setTempohPampasan(Long tempohPampasan) {
		this.tempohPampasan = tempohPampasan;
	}

	public Long getUnitTempoh() {
		return this.unitTempoh;
	}

	public void setUnitTempoh(Long unitTempoh) {
		this.unitTempoh = unitTempoh;
	}

	public Date getTarikhSurat() {
		return this.tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
	}

	public Long getIdSiasatan() {
		return this.idSiasatan;
	}

	public void setIdSiasatan(Long idSiasatan) {
		this.idSiasatan = idSiasatan;
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