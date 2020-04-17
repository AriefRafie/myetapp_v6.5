package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborango entity provides the base persistence definition of the
 * Tblpptborango entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborango implements java.io.Serializable {

	// Fields

	private Long idBorango;
	private String noBorango;
	private Date tarikhBorango;
	private Date tarikhCetak;
	private Date tarikhCetakSemula;
	private String namaMahkamah;
	private String alamatMahkamah;
	private Long idBantahan;
	private String ringkasanBantahan;
	private String perihalTanaman;
	private Date tarikhTerimaPerintah;
	private Date tarikhTerimaSuratmahkamah;
	private String flagPulangDeposit;
	private Date tarikhPerintah;
	private Long idBank;
	private String noAkaun;
	private Long keputusanMahkamah;
	private String perintahMahkamah;
	private Double amaunDenda;
	private Long tarikhAkhirBayar;
	private Long tempohBayarAward;
	private String noRujukanTanah;
	private Long caraSelesai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Double kosPengapitHakim;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborango() {
	}

	/** full constructor */
	public AbstractTblpptborango(String noBorango, Date tarikhBorango,
			Date tarikhCetak, Date tarikhCetakSemula, String namaMahkamah,
			String alamatMahkamah, Long idBantahan, String ringkasanBantahan,
			String perihalTanaman, Date tarikhTerimaPerintah,
			Date tarikhTerimaSuratmahkamah, String flagPulangDeposit,
			Date tarikhPerintah, Long idBank, String noAkaun,
			Long keputusanMahkamah, String perintahMahkamah, Double amaunDenda,
			Long tarikhAkhirBayar, Long tempohBayarAward,
			String noRujukanTanah, Long caraSelesai, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Double kosPengapitHakim, Long idDb) {
		this.noBorango = noBorango;
		this.tarikhBorango = tarikhBorango;
		this.tarikhCetak = tarikhCetak;
		this.tarikhCetakSemula = tarikhCetakSemula;
		this.namaMahkamah = namaMahkamah;
		this.alamatMahkamah = alamatMahkamah;
		this.idBantahan = idBantahan;
		this.ringkasanBantahan = ringkasanBantahan;
		this.perihalTanaman = perihalTanaman;
		this.tarikhTerimaPerintah = tarikhTerimaPerintah;
		this.tarikhTerimaSuratmahkamah = tarikhTerimaSuratmahkamah;
		this.flagPulangDeposit = flagPulangDeposit;
		this.tarikhPerintah = tarikhPerintah;
		this.idBank = idBank;
		this.noAkaun = noAkaun;
		this.keputusanMahkamah = keputusanMahkamah;
		this.perintahMahkamah = perintahMahkamah;
		this.amaunDenda = amaunDenda;
		this.tarikhAkhirBayar = tarikhAkhirBayar;
		this.tempohBayarAward = tempohBayarAward;
		this.noRujukanTanah = noRujukanTanah;
		this.caraSelesai = caraSelesai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.kosPengapitHakim = kosPengapitHakim;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorango() {
		return this.idBorango;
	}

	public void setIdBorango(Long idBorango) {
		this.idBorango = idBorango;
	}

	public String getNoBorango() {
		return this.noBorango;
	}

	public void setNoBorango(String noBorango) {
		this.noBorango = noBorango;
	}

	public Date getTarikhBorango() {
		return this.tarikhBorango;
	}

	public void setTarikhBorango(Date tarikhBorango) {
		this.tarikhBorango = tarikhBorango;
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

	public String getNamaMahkamah() {
		return this.namaMahkamah;
	}

	public void setNamaMahkamah(String namaMahkamah) {
		this.namaMahkamah = namaMahkamah;
	}

	public String getAlamatMahkamah() {
		return this.alamatMahkamah;
	}

	public void setAlamatMahkamah(String alamatMahkamah) {
		this.alamatMahkamah = alamatMahkamah;
	}

	public Long getIdBantahan() {
		return this.idBantahan;
	}

	public void setIdBantahan(Long idBantahan) {
		this.idBantahan = idBantahan;
	}

	public String getRingkasanBantahan() {
		return this.ringkasanBantahan;
	}

	public void setRingkasanBantahan(String ringkasanBantahan) {
		this.ringkasanBantahan = ringkasanBantahan;
	}

	public String getPerihalTanaman() {
		return this.perihalTanaman;
	}

	public void setPerihalTanaman(String perihalTanaman) {
		this.perihalTanaman = perihalTanaman;
	}

	public Date getTarikhTerimaPerintah() {
		return this.tarikhTerimaPerintah;
	}

	public void setTarikhTerimaPerintah(Date tarikhTerimaPerintah) {
		this.tarikhTerimaPerintah = tarikhTerimaPerintah;
	}

	public Date getTarikhTerimaSuratmahkamah() {
		return this.tarikhTerimaSuratmahkamah;
	}

	public void setTarikhTerimaSuratmahkamah(Date tarikhTerimaSuratmahkamah) {
		this.tarikhTerimaSuratmahkamah = tarikhTerimaSuratmahkamah;
	}

	public String getFlagPulangDeposit() {
		return this.flagPulangDeposit;
	}

	public void setFlagPulangDeposit(String flagPulangDeposit) {
		this.flagPulangDeposit = flagPulangDeposit;
	}

	public Date getTarikhPerintah() {
		return this.tarikhPerintah;
	}

	public void setTarikhPerintah(Date tarikhPerintah) {
		this.tarikhPerintah = tarikhPerintah;
	}

	public Long getIdBank() {
		return this.idBank;
	}

	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	public String getNoAkaun() {
		return this.noAkaun;
	}

	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
	}

	public Long getKeputusanMahkamah() {
		return this.keputusanMahkamah;
	}

	public void setKeputusanMahkamah(Long keputusanMahkamah) {
		this.keputusanMahkamah = keputusanMahkamah;
	}

	public String getPerintahMahkamah() {
		return this.perintahMahkamah;
	}

	public void setPerintahMahkamah(String perintahMahkamah) {
		this.perintahMahkamah = perintahMahkamah;
	}

	public Double getAmaunDenda() {
		return this.amaunDenda;
	}

	public void setAmaunDenda(Double amaunDenda) {
		this.amaunDenda = amaunDenda;
	}

	public Long getTarikhAkhirBayar() {
		return this.tarikhAkhirBayar;
	}

	public void setTarikhAkhirBayar(Long tarikhAkhirBayar) {
		this.tarikhAkhirBayar = tarikhAkhirBayar;
	}

	public Long getTempohBayarAward() {
		return this.tempohBayarAward;
	}

	public void setTempohBayarAward(Long tempohBayarAward) {
		this.tempohBayarAward = tempohBayarAward;
	}

	public String getNoRujukanTanah() {
		return this.noRujukanTanah;
	}

	public void setNoRujukanTanah(String noRujukanTanah) {
		this.noRujukanTanah = noRujukanTanah;
	}

	public Long getCaraSelesai() {
		return this.caraSelesai;
	}

	public void setCaraSelesai(Long caraSelesai) {
		this.caraSelesai = caraSelesai;
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

	public Double getKosPengapitHakim() {
		return this.kosPengapitHakim;
	}

	public void setKosPengapitHakim(Double kosPengapitHakim) {
		this.kosPengapitHakim = kosPengapitHakim;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}