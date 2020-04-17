package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborangm entity provides the base persistence definition of the
 * Tblpptborangm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborangm implements java.io.Serializable {

	// Fields

	private Long idBorangm;
	private String namaMahkamah;
	private String alamatMahkamah1;
	private String alamatMahkamah2;
	private String alamatMahkamah3;
	private String tujuan;
	private String perkaraRujukan;
	private Date tarikhBorangm;
	private Long keputusanMahkamah;
	private String perintahMahkamah;
	private Date tarikhPerintah;
	private Date tarikhTerimaPerintah;
	private Double pampasanAsal;
	private Double pampasanBantahan;
	private Double bezaPampasan;
	private String noRujukanProsiding;
	private Long idPermohonan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborangm() {
	}

	/** full constructor */
	public AbstractTblpptborangm(String namaMahkamah, String alamatMahkamah1,
			String alamatMahkamah2, String alamatMahkamah3, String tujuan,
			String perkaraRujukan, Date tarikhBorangm, Long keputusanMahkamah,
			String perintahMahkamah, Date tarikhPerintah,
			Date tarikhTerimaPerintah, Double pampasanAsal,
			Double pampasanBantahan, Double bezaPampasan,
			String noRujukanProsiding, Long idPermohonan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.namaMahkamah = namaMahkamah;
		this.alamatMahkamah1 = alamatMahkamah1;
		this.alamatMahkamah2 = alamatMahkamah2;
		this.alamatMahkamah3 = alamatMahkamah3;
		this.tujuan = tujuan;
		this.perkaraRujukan = perkaraRujukan;
		this.tarikhBorangm = tarikhBorangm;
		this.keputusanMahkamah = keputusanMahkamah;
		this.perintahMahkamah = perintahMahkamah;
		this.tarikhPerintah = tarikhPerintah;
		this.tarikhTerimaPerintah = tarikhTerimaPerintah;
		this.pampasanAsal = pampasanAsal;
		this.pampasanBantahan = pampasanBantahan;
		this.bezaPampasan = bezaPampasan;
		this.noRujukanProsiding = noRujukanProsiding;
		this.idPermohonan = idPermohonan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorangm() {
		return this.idBorangm;
	}

	public void setIdBorangm(Long idBorangm) {
		this.idBorangm = idBorangm;
	}

	public String getNamaMahkamah() {
		return this.namaMahkamah;
	}

	public void setNamaMahkamah(String namaMahkamah) {
		this.namaMahkamah = namaMahkamah;
	}

	public String getAlamatMahkamah1() {
		return this.alamatMahkamah1;
	}

	public void setAlamatMahkamah1(String alamatMahkamah1) {
		this.alamatMahkamah1 = alamatMahkamah1;
	}

	public String getAlamatMahkamah2() {
		return this.alamatMahkamah2;
	}

	public void setAlamatMahkamah2(String alamatMahkamah2) {
		this.alamatMahkamah2 = alamatMahkamah2;
	}

	public String getAlamatMahkamah3() {
		return this.alamatMahkamah3;
	}

	public void setAlamatMahkamah3(String alamatMahkamah3) {
		this.alamatMahkamah3 = alamatMahkamah3;
	}

	public String getTujuan() {
		return this.tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getPerkaraRujukan() {
		return this.perkaraRujukan;
	}

	public void setPerkaraRujukan(String perkaraRujukan) {
		this.perkaraRujukan = perkaraRujukan;
	}

	public Date getTarikhBorangm() {
		return this.tarikhBorangm;
	}

	public void setTarikhBorangm(Date tarikhBorangm) {
		this.tarikhBorangm = tarikhBorangm;
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

	public Date getTarikhPerintah() {
		return this.tarikhPerintah;
	}

	public void setTarikhPerintah(Date tarikhPerintah) {
		this.tarikhPerintah = tarikhPerintah;
	}

	public Date getTarikhTerimaPerintah() {
		return this.tarikhTerimaPerintah;
	}

	public void setTarikhTerimaPerintah(Date tarikhTerimaPerintah) {
		this.tarikhTerimaPerintah = tarikhTerimaPerintah;
	}

	public Double getPampasanAsal() {
		return this.pampasanAsal;
	}

	public void setPampasanAsal(Double pampasanAsal) {
		this.pampasanAsal = pampasanAsal;
	}

	public Double getPampasanBantahan() {
		return this.pampasanBantahan;
	}

	public void setPampasanBantahan(Double pampasanBantahan) {
		this.pampasanBantahan = pampasanBantahan;
	}

	public Double getBezaPampasan() {
		return this.bezaPampasan;
	}

	public void setBezaPampasan(Double bezaPampasan) {
		this.bezaPampasan = bezaPampasan;
	}

	public String getNoRujukanProsiding() {
		return this.noRujukanProsiding;
	}

	public void setNoRujukanProsiding(String noRujukanProsiding) {
		this.noRujukanProsiding = noRujukanProsiding;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
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