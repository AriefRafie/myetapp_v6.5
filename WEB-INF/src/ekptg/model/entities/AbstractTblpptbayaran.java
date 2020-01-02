package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptbayaran entity provides the base persistence definition of the
 * Tblpptbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptbayaran implements java.io.Serializable {

	// Fields

	private Long idBayaran;
	private String noBayaran;
	private Double amaunBayaran;
	private Date tarikhTerima;
	private Long idBank;
	private Long caraBayar;
	private Date tarikhCek;
	private Long jenisAward;
	private Date tarikhSerahCek;
	private String flagSerahCek;
	private String namaWakil;
	private String noWakil;
	private Long idJenisnopb;
	private String flagTerimaCek;
	private String noRujukanSurat;
	private Date tarikhSurat;
	private Date tarikhAmbilCek;
	private String tempatAmbil;
	private String masaAmbilCek;
	private String penerimaCek;
	private Double dendaLewat;
	private String noPb;
	private String noKpLama;
	private String noEft;
	private String noBaucer;
	private String noRujukanSurateft;
	private Date tarikhTerimaEft;
	private Date tarikhSuratEft;
	private Long idTerimabayaran;
	private Long idPihakberkepentingan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptbayaran() {
	}

	/** full constructor */
	public AbstractTblpptbayaran(String noBayaran, Double amaunBayaran,
			Date tarikhTerima, Long idBank, Long caraBayar, Date tarikhCek,
			Long jenisAward, Date tarikhSerahCek, String flagSerahCek,
			String namaWakil, String noWakil, Long idJenisnopb,
			String flagTerimaCek, String noRujukanSurat, Date tarikhSurat,
			Date tarikhAmbilCek, String tempatAmbil, String masaAmbilCek,
			String penerimaCek, Double dendaLewat, String noPb,
			String noKpLama, String noEft, String noBaucer,
			String noRujukanSurateft, Date tarikhTerimaEft,
			Date tarikhSuratEft, Long idTerimabayaran,
			Long idPihakberkepentingan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.noBayaran = noBayaran;
		this.amaunBayaran = amaunBayaran;
		this.tarikhTerima = tarikhTerima;
		this.idBank = idBank;
		this.caraBayar = caraBayar;
		this.tarikhCek = tarikhCek;
		this.jenisAward = jenisAward;
		this.tarikhSerahCek = tarikhSerahCek;
		this.flagSerahCek = flagSerahCek;
		this.namaWakil = namaWakil;
		this.noWakil = noWakil;
		this.idJenisnopb = idJenisnopb;
		this.flagTerimaCek = flagTerimaCek;
		this.noRujukanSurat = noRujukanSurat;
		this.tarikhSurat = tarikhSurat;
		this.tarikhAmbilCek = tarikhAmbilCek;
		this.tempatAmbil = tempatAmbil;
		this.masaAmbilCek = masaAmbilCek;
		this.penerimaCek = penerimaCek;
		this.dendaLewat = dendaLewat;
		this.noPb = noPb;
		this.noKpLama = noKpLama;
		this.noEft = noEft;
		this.noBaucer = noBaucer;
		this.noRujukanSurateft = noRujukanSurateft;
		this.tarikhTerimaEft = tarikhTerimaEft;
		this.tarikhSuratEft = tarikhSuratEft;
		this.idTerimabayaran = idTerimabayaran;
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBayaran() {
		return this.idBayaran;
	}

	public void setIdBayaran(Long idBayaran) {
		this.idBayaran = idBayaran;
	}

	public String getNoBayaran() {
		return this.noBayaran;
	}

	public void setNoBayaran(String noBayaran) {
		this.noBayaran = noBayaran;
	}

	public Double getAmaunBayaran() {
		return this.amaunBayaran;
	}

	public void setAmaunBayaran(Double amaunBayaran) {
		this.amaunBayaran = amaunBayaran;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Long getIdBank() {
		return this.idBank;
	}

	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	public Long getCaraBayar() {
		return this.caraBayar;
	}

	public void setCaraBayar(Long caraBayar) {
		this.caraBayar = caraBayar;
	}

	public Date getTarikhCek() {
		return this.tarikhCek;
	}

	public void setTarikhCek(Date tarikhCek) {
		this.tarikhCek = tarikhCek;
	}

	public Long getJenisAward() {
		return this.jenisAward;
	}

	public void setJenisAward(Long jenisAward) {
		this.jenisAward = jenisAward;
	}

	public Date getTarikhSerahCek() {
		return this.tarikhSerahCek;
	}

	public void setTarikhSerahCek(Date tarikhSerahCek) {
		this.tarikhSerahCek = tarikhSerahCek;
	}

	public String getFlagSerahCek() {
		return this.flagSerahCek;
	}

	public void setFlagSerahCek(String flagSerahCek) {
		this.flagSerahCek = flagSerahCek;
	}

	public String getNamaWakil() {
		return this.namaWakil;
	}

	public void setNamaWakil(String namaWakil) {
		this.namaWakil = namaWakil;
	}

	public String getNoWakil() {
		return this.noWakil;
	}

	public void setNoWakil(String noWakil) {
		this.noWakil = noWakil;
	}

	public Long getIdJenisnopb() {
		return this.idJenisnopb;
	}

	public void setIdJenisnopb(Long idJenisnopb) {
		this.idJenisnopb = idJenisnopb;
	}

	public String getFlagTerimaCek() {
		return this.flagTerimaCek;
	}

	public void setFlagTerimaCek(String flagTerimaCek) {
		this.flagTerimaCek = flagTerimaCek;
	}

	public String getNoRujukanSurat() {
		return this.noRujukanSurat;
	}

	public void setNoRujukanSurat(String noRujukanSurat) {
		this.noRujukanSurat = noRujukanSurat;
	}

	public Date getTarikhSurat() {
		return this.tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}

	public Date getTarikhAmbilCek() {
		return this.tarikhAmbilCek;
	}

	public void setTarikhAmbilCek(Date tarikhAmbilCek) {
		this.tarikhAmbilCek = tarikhAmbilCek;
	}

	public String getTempatAmbil() {
		return this.tempatAmbil;
	}

	public void setTempatAmbil(String tempatAmbil) {
		this.tempatAmbil = tempatAmbil;
	}

	public String getMasaAmbilCek() {
		return this.masaAmbilCek;
	}

	public void setMasaAmbilCek(String masaAmbilCek) {
		this.masaAmbilCek = masaAmbilCek;
	}

	public String getPenerimaCek() {
		return this.penerimaCek;
	}

	public void setPenerimaCek(String penerimaCek) {
		this.penerimaCek = penerimaCek;
	}

	public Double getDendaLewat() {
		return this.dendaLewat;
	}

	public void setDendaLewat(Double dendaLewat) {
		this.dendaLewat = dendaLewat;
	}

	public String getNoPb() {
		return this.noPb;
	}

	public void setNoPb(String noPb) {
		this.noPb = noPb;
	}

	public String getNoKpLama() {
		return this.noKpLama;
	}

	public void setNoKpLama(String noKpLama) {
		this.noKpLama = noKpLama;
	}

	public String getNoEft() {
		return this.noEft;
	}

	public void setNoEft(String noEft) {
		this.noEft = noEft;
	}

	public String getNoBaucer() {
		return this.noBaucer;
	}

	public void setNoBaucer(String noBaucer) {
		this.noBaucer = noBaucer;
	}

	public String getNoRujukanSurateft() {
		return this.noRujukanSurateft;
	}

	public void setNoRujukanSurateft(String noRujukanSurateft) {
		this.noRujukanSurateft = noRujukanSurateft;
	}

	public Date getTarikhTerimaEft() {
		return this.tarikhTerimaEft;
	}

	public void setTarikhTerimaEft(Date tarikhTerimaEft) {
		this.tarikhTerimaEft = tarikhTerimaEft;
	}

	public Date getTarikhSuratEft() {
		return this.tarikhSuratEft;
	}

	public void setTarikhSuratEft(Date tarikhSuratEft) {
		this.tarikhSuratEft = tarikhSuratEft;
	}

	public Long getIdTerimabayaran() {
		return this.idTerimabayaran;
	}

	public void setIdTerimabayaran(Long idTerimabayaran) {
		this.idTerimabayaran = idTerimabayaran;
	}

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
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