package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenpinda entity provides the base persistence definition of
 * the Tblpdtenakmenpinda entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenpinda implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenpinda;
	private Long noEnakmenAsal;
	private Long noEnakmenPindaan;
	private Long noEnakmenBaru;
	private String namaEnakmenBaru;
	private String justifikasiPindaan;
	private String kandunganPindaan;
	private Date tarikhKuatkuasa;
	private Date tarikhDaftarPindaan;
	private Date tarikhKuatkuasaPindaan;
	private Date tarikhWarta;
	private Date tarikhPerkenaanDiraja;
	private Date tarikhPenyiaranDalamwarta;
	private Long bil;
	private String dirFail;
	private Long idFail;
	private String catatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtjadualpindas = new HashSet(0);
	private Set tblpdtenakmenpindabahagians = new HashSet(0);
	private Set tblpdtpindaanenakmens = new HashSet(0);
	private Set tblpdtenakmenpindapenggals = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenpinda() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenpinda(Long idEnakmenpinda, Long noEnakmenAsal,
			Long noEnakmenPindaan, Long noEnakmenBaru, String namaEnakmenBaru,
			Long idFail) {
		this.idEnakmenpinda = idEnakmenpinda;
		this.noEnakmenAsal = noEnakmenAsal;
		this.noEnakmenPindaan = noEnakmenPindaan;
		this.noEnakmenBaru = noEnakmenBaru;
		this.namaEnakmenBaru = namaEnakmenBaru;
		this.idFail = idFail;
	}

	/** full constructor */
	public AbstractTblpdtenakmenpinda(Long idEnakmenpinda, Long noEnakmenAsal,
			Long noEnakmenPindaan, Long noEnakmenBaru, String namaEnakmenBaru,
			String justifikasiPindaan, String kandunganPindaan,
			Date tarikhKuatkuasa, Date tarikhDaftarPindaan,
			Date tarikhKuatkuasaPindaan, Date tarikhWarta,
			Date tarikhPerkenaanDiraja, Date tarikhPenyiaranDalamwarta,
			Long bil, String dirFail, Long idFail, String catatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtjadualpindas,
			Set tblpdtenakmenpindabahagians, Set tblpdtpindaanenakmens,
			Set tblpdtenakmenpindapenggals) {
		this.idEnakmenpinda = idEnakmenpinda;
		this.noEnakmenAsal = noEnakmenAsal;
		this.noEnakmenPindaan = noEnakmenPindaan;
		this.noEnakmenBaru = noEnakmenBaru;
		this.namaEnakmenBaru = namaEnakmenBaru;
		this.justifikasiPindaan = justifikasiPindaan;
		this.kandunganPindaan = kandunganPindaan;
		this.tarikhKuatkuasa = tarikhKuatkuasa;
		this.tarikhDaftarPindaan = tarikhDaftarPindaan;
		this.tarikhKuatkuasaPindaan = tarikhKuatkuasaPindaan;
		this.tarikhWarta = tarikhWarta;
		this.tarikhPerkenaanDiraja = tarikhPerkenaanDiraja;
		this.tarikhPenyiaranDalamwarta = tarikhPenyiaranDalamwarta;
		this.bil = bil;
		this.dirFail = dirFail;
		this.idFail = idFail;
		this.catatan = catatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtjadualpindas = tblpdtjadualpindas;
		this.tblpdtenakmenpindabahagians = tblpdtenakmenpindabahagians;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
		this.tblpdtenakmenpindapenggals = tblpdtenakmenpindapenggals;
	}

	// Property accessors

	public Long getIdEnakmenpinda() {
		return this.idEnakmenpinda;
	}

	public void setIdEnakmenpinda(Long idEnakmenpinda) {
		this.idEnakmenpinda = idEnakmenpinda;
	}

	public Long getNoEnakmenAsal() {
		return this.noEnakmenAsal;
	}

	public void setNoEnakmenAsal(Long noEnakmenAsal) {
		this.noEnakmenAsal = noEnakmenAsal;
	}

	public Long getNoEnakmenPindaan() {
		return this.noEnakmenPindaan;
	}

	public void setNoEnakmenPindaan(Long noEnakmenPindaan) {
		this.noEnakmenPindaan = noEnakmenPindaan;
	}

	public Long getNoEnakmenBaru() {
		return this.noEnakmenBaru;
	}

	public void setNoEnakmenBaru(Long noEnakmenBaru) {
		this.noEnakmenBaru = noEnakmenBaru;
	}

	public String getNamaEnakmenBaru() {
		return this.namaEnakmenBaru;
	}

	public void setNamaEnakmenBaru(String namaEnakmenBaru) {
		this.namaEnakmenBaru = namaEnakmenBaru;
	}

	public String getJustifikasiPindaan() {
		return this.justifikasiPindaan;
	}

	public void setJustifikasiPindaan(String justifikasiPindaan) {
		this.justifikasiPindaan = justifikasiPindaan;
	}

	public String getKandunganPindaan() {
		return this.kandunganPindaan;
	}

	public void setKandunganPindaan(String kandunganPindaan) {
		this.kandunganPindaan = kandunganPindaan;
	}

	public Date getTarikhKuatkuasa() {
		return this.tarikhKuatkuasa;
	}

	public void setTarikhKuatkuasa(Date tarikhKuatkuasa) {
		this.tarikhKuatkuasa = tarikhKuatkuasa;
	}

	public Date getTarikhDaftarPindaan() {
		return this.tarikhDaftarPindaan;
	}

	public void setTarikhDaftarPindaan(Date tarikhDaftarPindaan) {
		this.tarikhDaftarPindaan = tarikhDaftarPindaan;
	}

	public Date getTarikhKuatkuasaPindaan() {
		return this.tarikhKuatkuasaPindaan;
	}

	public void setTarikhKuatkuasaPindaan(Date tarikhKuatkuasaPindaan) {
		this.tarikhKuatkuasaPindaan = tarikhKuatkuasaPindaan;
	}

	public Date getTarikhWarta() {
		return this.tarikhWarta;
	}

	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public Date getTarikhPerkenaanDiraja() {
		return this.tarikhPerkenaanDiraja;
	}

	public void setTarikhPerkenaanDiraja(Date tarikhPerkenaanDiraja) {
		this.tarikhPerkenaanDiraja = tarikhPerkenaanDiraja;
	}

	public Date getTarikhPenyiaranDalamwarta() {
		return this.tarikhPenyiaranDalamwarta;
	}

	public void setTarikhPenyiaranDalamwarta(Date tarikhPenyiaranDalamwarta) {
		this.tarikhPenyiaranDalamwarta = tarikhPenyiaranDalamwarta;
	}

	public Long getBil() {
		return this.bil;
	}

	public void setBil(Long bil) {
		this.bil = bil;
	}

	public String getDirFail() {
		return this.dirFail;
	}

	public void setDirFail(String dirFail) {
		this.dirFail = dirFail;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
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

	public Set getTblpdtjadualpindas() {
		return this.tblpdtjadualpindas;
	}

	public void setTblpdtjadualpindas(Set tblpdtjadualpindas) {
		this.tblpdtjadualpindas = tblpdtjadualpindas;
	}

	public Set getTblpdtenakmenpindabahagians() {
		return this.tblpdtenakmenpindabahagians;
	}

	public void setTblpdtenakmenpindabahagians(Set tblpdtenakmenpindabahagians) {
		this.tblpdtenakmenpindabahagians = tblpdtenakmenpindabahagians;
	}

	public Set getTblpdtpindaanenakmens() {
		return this.tblpdtpindaanenakmens;
	}

	public void setTblpdtpindaanenakmens(Set tblpdtpindaanenakmens) {
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	public Set getTblpdtenakmenpindapenggals() {
		return this.tblpdtenakmenpindapenggals;
	}

	public void setTblpdtenakmenpindapenggals(Set tblpdtenakmenpindapenggals) {
		this.tblpdtenakmenpindapenggals = tblpdtenakmenpindapenggals;
	}

}