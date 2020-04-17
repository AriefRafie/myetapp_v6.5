package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphplawatantapak entity provides the base persistence definition of
 * the Tblphplawatantapak entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphplawatantapak implements
		java.io.Serializable {

	// Fields

	private Long idLawatantapak;
	private Date tarikhLawatan;
	private String tujuanLawatan;
	private String perkembanganTapak;
	private String jalanHubungan;
	private String kawasanBerhampiran;
	private String jarakDrbandar;
	private String keadaanRupabumi;
	private String keadaanTanah;
	private String kemudahanAsas;
	private String pembangunanSekitar;
	private String sempUtara;
	private String sempBarat;
	private String sempTimur;
	private String sempSelatan;
	private String ulasan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphpimejantanahs = new HashSet(0);
	private Set tblphpjabatanlaporantanahs = new HashSet(0);
	private Set tblphppegawailaporantanahs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphplawatantapak() {
	}

	/** minimal constructor */
	public AbstractTblphplawatantapak(Long idLawatantapak) {
		this.idLawatantapak = idLawatantapak;
	}

	/** full constructor */
	public AbstractTblphplawatantapak(Long idLawatantapak, Date tarikhLawatan,
			String tujuanLawatan, String perkembanganTapak,
			String jalanHubungan, String kawasanBerhampiran,
			String jarakDrbandar, String keadaanRupabumi, String keadaanTanah,
			String kemudahanAsas, String pembangunanSekitar, String sempUtara,
			String sempBarat, String sempTimur, String sempSelatan,
			String ulasan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphpimejantanahs,
			Set tblphpjabatanlaporantanahs, Set tblphppegawailaporantanahs) {
		this.idLawatantapak = idLawatantapak;
		this.tarikhLawatan = tarikhLawatan;
		this.tujuanLawatan = tujuanLawatan;
		this.perkembanganTapak = perkembanganTapak;
		this.jalanHubungan = jalanHubungan;
		this.kawasanBerhampiran = kawasanBerhampiran;
		this.jarakDrbandar = jarakDrbandar;
		this.keadaanRupabumi = keadaanRupabumi;
		this.keadaanTanah = keadaanTanah;
		this.kemudahanAsas = kemudahanAsas;
		this.pembangunanSekitar = pembangunanSekitar;
		this.sempUtara = sempUtara;
		this.sempBarat = sempBarat;
		this.sempTimur = sempTimur;
		this.sempSelatan = sempSelatan;
		this.ulasan = ulasan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphpimejantanahs = tblphpimejantanahs;
		this.tblphpjabatanlaporantanahs = tblphpjabatanlaporantanahs;
		this.tblphppegawailaporantanahs = tblphppegawailaporantanahs;
	}

	// Property accessors

	public Long getIdLawatantapak() {
		return this.idLawatantapak;
	}

	public void setIdLawatantapak(Long idLawatantapak) {
		this.idLawatantapak = idLawatantapak;
	}

	public Date getTarikhLawatan() {
		return this.tarikhLawatan;
	}

	public void setTarikhLawatan(Date tarikhLawatan) {
		this.tarikhLawatan = tarikhLawatan;
	}

	public String getTujuanLawatan() {
		return this.tujuanLawatan;
	}

	public void setTujuanLawatan(String tujuanLawatan) {
		this.tujuanLawatan = tujuanLawatan;
	}

	public String getPerkembanganTapak() {
		return this.perkembanganTapak;
	}

	public void setPerkembanganTapak(String perkembanganTapak) {
		this.perkembanganTapak = perkembanganTapak;
	}

	public String getJalanHubungan() {
		return this.jalanHubungan;
	}

	public void setJalanHubungan(String jalanHubungan) {
		this.jalanHubungan = jalanHubungan;
	}

	public String getKawasanBerhampiran() {
		return this.kawasanBerhampiran;
	}

	public void setKawasanBerhampiran(String kawasanBerhampiran) {
		this.kawasanBerhampiran = kawasanBerhampiran;
	}

	public String getJarakDrbandar() {
		return this.jarakDrbandar;
	}

	public void setJarakDrbandar(String jarakDrbandar) {
		this.jarakDrbandar = jarakDrbandar;
	}

	public String getKeadaanRupabumi() {
		return this.keadaanRupabumi;
	}

	public void setKeadaanRupabumi(String keadaanRupabumi) {
		this.keadaanRupabumi = keadaanRupabumi;
	}

	public String getKeadaanTanah() {
		return this.keadaanTanah;
	}

	public void setKeadaanTanah(String keadaanTanah) {
		this.keadaanTanah = keadaanTanah;
	}

	public String getKemudahanAsas() {
		return this.kemudahanAsas;
	}

	public void setKemudahanAsas(String kemudahanAsas) {
		this.kemudahanAsas = kemudahanAsas;
	}

	public String getPembangunanSekitar() {
		return this.pembangunanSekitar;
	}

	public void setPembangunanSekitar(String pembangunanSekitar) {
		this.pembangunanSekitar = pembangunanSekitar;
	}

	public String getSempUtara() {
		return this.sempUtara;
	}

	public void setSempUtara(String sempUtara) {
		this.sempUtara = sempUtara;
	}

	public String getSempBarat() {
		return this.sempBarat;
	}

	public void setSempBarat(String sempBarat) {
		this.sempBarat = sempBarat;
	}

	public String getSempTimur() {
		return this.sempTimur;
	}

	public void setSempTimur(String sempTimur) {
		this.sempTimur = sempTimur;
	}

	public String getSempSelatan() {
		return this.sempSelatan;
	}

	public void setSempSelatan(String sempSelatan) {
		this.sempSelatan = sempSelatan;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
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

	public Set getTblphpimejantanahs() {
		return this.tblphpimejantanahs;
	}

	public void setTblphpimejantanahs(Set tblphpimejantanahs) {
		this.tblphpimejantanahs = tblphpimejantanahs;
	}

	public Set getTblphpjabatanlaporantanahs() {
		return this.tblphpjabatanlaporantanahs;
	}

	public void setTblphpjabatanlaporantanahs(Set tblphpjabatanlaporantanahs) {
		this.tblphpjabatanlaporantanahs = tblphpjabatanlaporantanahs;
	}

	public Set getTblphppegawailaporantanahs() {
		return this.tblphppegawailaporantanahs;
	}

	public void setTblphppegawailaporantanahs(Set tblphppegawailaporantanahs) {
		this.tblphppegawailaporantanahs = tblphppegawailaporantanahs;
	}

}