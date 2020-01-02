package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkperbicaraan entity provides the base persistence definition of
 * the Tblppkperbicaraan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkperbicaraan implements java.io.Serializable {

	// Fields

	private Long idPerbicaraan;
//	private Tblppkkeputusanpermohonan tblppkkeputusanpermohonan;
        private Long idKeputusanpermohonan;
	private Long idNegeri;
	private Long idUnitpsk;
	private Date tarikhNotis;
	private Date tarikhBicara;
	private Date masaBicara;
	private String tempatBicara;
	private Long bilBicara;
	private String alamatBicara1;
	private String alamatBicara2;
	private String alamatBicara3;
	private String bandar;
	private String poskod;
	private Long idNegeribicara;
	private String pegPengendali;
	private String jenisKeputusan;
	private String catatan;
	private String sebabTangguh;
	private String sebabBatal;
	private String keputusanMahkamah;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppknotabicaras = new HashSet(0);
	private Set tblppkperintahs = new HashSet(0);
	private Set tblppkborangjs = new HashSet(0);
	private Set tblppknotisperbicaraans = new HashSet(0);
	private Set tblppkkolateralmsts = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkperbicaraan() {
	}

	/** minimal constructor */
	public AbstractTblppkperbicaraan(Long idPerbicaraan) {
		this.idPerbicaraan = idPerbicaraan;
	//	this.tblppkkeputusanpermohonan = tblppkkeputusanpermohonan;
	}

	/** full constructor */
	public AbstractTblppkperbicaraan(Long idPerbicaraan,
			Long idKeputusanpermohonan, Long idNegeri,
			Long idUnitpsk, Date tarikhNotis, Date tarikhBicara,
			Date masaBicara, String tempatBicara, Long bilBicara,
			String alamatBicara1, String alamatBicara2, String alamatBicara3,
			String bandar, String poskod, Long idNegeribicara,
			String pegPengendali, String jenisKeputusan, String catatan,
			String sebabTangguh, String sebabBatal, String keputusanMahkamah,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppknotabicaras, Set tblppkperintahs,
			Set tblppkborangjs, Set tblppknotisperbicaraans,
			Set tblppkkolateralmsts) {
		this.idPerbicaraan = idPerbicaraan;
	//	this.tblppkkeputusanpermohonan = tblppkkeputusanpermohonan;
                this.idKeputusanpermohonan = idKeputusanpermohonan;
		this.idNegeri = idNegeri;
		this.idUnitpsk = idUnitpsk;
		this.tarikhNotis = tarikhNotis;
		this.tarikhBicara = tarikhBicara;
		this.masaBicara = masaBicara;
		this.tempatBicara = tempatBicara;
		this.bilBicara = bilBicara;
		this.alamatBicara1 = alamatBicara1;
		this.alamatBicara2 = alamatBicara2;
		this.alamatBicara3 = alamatBicara3;
		this.bandar = bandar;
		this.poskod = poskod;
		this.idNegeribicara = idNegeribicara;
		this.pegPengendali = pegPengendali;
		this.jenisKeputusan = jenisKeputusan;
		this.catatan = catatan;
		this.sebabTangguh = sebabTangguh;
		this.sebabBatal = sebabBatal;
		this.keputusanMahkamah = keputusanMahkamah;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppknotabicaras = tblppknotabicaras;
		this.tblppkperintahs = tblppkperintahs;
		this.tblppkborangjs = tblppkborangjs;
		this.tblppknotisperbicaraans = tblppknotisperbicaraans;
		this.tblppkkolateralmsts = tblppkkolateralmsts;
	}

	// Property accessors

	public Long getIdPerbicaraan() {
		return this.idPerbicaraan;
	}

	public void setIdPerbicaraan(Long idPerbicaraan) {
		this.idPerbicaraan = idPerbicaraan;
	}

/*	public Tblppkkeputusanpermohonan getTblppkkeputusanpermohonan() {
		return this.tblppkkeputusanpermohonan;
	}

	public void setTblppkkeputusanpermohonan(
			Tblppkkeputusanpermohonan tblppkkeputusanpermohonan) {
		this.tblppkkeputusanpermohonan = tblppkkeputusanpermohonan;
	}
*/
	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdUnitpsk() {
		return this.idUnitpsk;
	}

	public void setIdUnitpsk(Long idUnitpsk) {
		this.idUnitpsk = idUnitpsk;
	}

	public Date getTarikhNotis() {
		return this.tarikhNotis;
	}

	public void setTarikhNotis(Date tarikhNotis) {
		this.tarikhNotis = tarikhNotis;
	}

	public Date getTarikhBicara() {
		return this.tarikhBicara;
	}

	public void setTarikhBicara(Date tarikhBicara) {
		this.tarikhBicara = tarikhBicara;
	}

	public Date getMasaBicara() {
		return this.masaBicara;
	}

	public void setMasaBicara(Date masaBicara) {
		this.masaBicara = masaBicara;
	}

	public String getTempatBicara() {
		return this.tempatBicara;
	}

	public void setTempatBicara(String tempatBicara) {
		this.tempatBicara = tempatBicara;
	}

	public Long getBilBicara() {
		return this.bilBicara;
	}

	public void setBilBicara(Long bilBicara) {
		this.bilBicara = bilBicara;
	}

	public String getAlamatBicara1() {
		return this.alamatBicara1;
	}

	public void setAlamatBicara1(String alamatBicara1) {
		this.alamatBicara1 = alamatBicara1;
	}

	public String getAlamatBicara2() {
		return this.alamatBicara2;
	}

	public void setAlamatBicara2(String alamatBicara2) {
		this.alamatBicara2 = alamatBicara2;
	}

	public String getAlamatBicara3() {
		return this.alamatBicara3;
	}

	public void setAlamatBicara3(String alamatBicara3) {
		this.alamatBicara3 = alamatBicara3;
	}

	public String getBandar() {
		return this.bandar;
	}

	public void setBandar(String bandar) {
		this.bandar = bandar;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Long getIdNegeribicara() {
		return this.idNegeribicara;
	}

	public void setIdNegeribicara(Long idNegeribicara) {
		this.idNegeribicara = idNegeribicara;
	}

	public String getPegPengendali() {
		return this.pegPengendali;
	}

	public void setPegPengendali(String pegPengendali) {
		this.pegPengendali = pegPengendali;
	}

	public String getJenisKeputusan() {
		return this.jenisKeputusan;
	}

	public void setJenisKeputusan(String jenisKeputusan) {
		this.jenisKeputusan = jenisKeputusan;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getSebabTangguh() {
		return this.sebabTangguh;
	}

	public void setSebabTangguh(String sebabTangguh) {
		this.sebabTangguh = sebabTangguh;
	}

	public String getSebabBatal() {
		return this.sebabBatal;
	}

	public void setSebabBatal(String sebabBatal) {
		this.sebabBatal = sebabBatal;
	}

	public String getKeputusanMahkamah() {
		return this.keputusanMahkamah;
	}

	public void setKeputusanMahkamah(String keputusanMahkamah) {
		this.keputusanMahkamah = keputusanMahkamah;
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

	public Set getTblppknotabicaras() {
		return this.tblppknotabicaras;
	}

	public void setTblppknotabicaras(Set tblppknotabicaras) {
		this.tblppknotabicaras = tblppknotabicaras;
	}

	public Set getTblppkperintahs() {
		return this.tblppkperintahs;
	}

	public void setTblppkperintahs(Set tblppkperintahs) {
		this.tblppkperintahs = tblppkperintahs;
	}

	public Set getTblppkborangjs() {
		return this.tblppkborangjs;
	}

	public void setTblppkborangjs(Set tblppkborangjs) {
		this.tblppkborangjs = tblppkborangjs;
	}

	public Set getTblppknotisperbicaraans() {
		return this.tblppknotisperbicaraans;
	}

	public void setTblppknotisperbicaraans(Set tblppknotisperbicaraans) {
		this.tblppknotisperbicaraans = tblppknotisperbicaraans;
	}

	public Set getTblppkkolateralmsts() {
		return this.tblppkkolateralmsts;
	}

	public void setTblppkkolateralmsts(Set tblppkkolateralmsts) {
		this.tblppkkolateralmsts = tblppkkolateralmsts;
	}

    public void setIdKeputusanpermohonan(Long idKeputusanpermohonan) {
        this.idKeputusanpermohonan = idKeputusanpermohonan;
    }

    public Long getIdKeputusanpermohonan() {
        return idKeputusanpermohonan;
    }
}
