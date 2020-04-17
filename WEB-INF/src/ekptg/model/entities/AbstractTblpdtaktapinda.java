package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktapinda entity provides the base persistence definition of
 * the Tblpdtaktapinda entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktapinda implements java.io.Serializable {

	// Fields

	private Long idAktapinda;
	private Long noAktaAsal;
	private Long noAktaPindaan;
	private Long noAktaBaru;
	private String namaAktaBaru;
	private String justifikasiPindaan;
	private Long noMemorandumMenteri;
	private String keteranganJemaahMenteri;
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
	private Set tblpdtaktapindapenggals = new HashSet(0);
	private Set tblpdtpindaanaktas = new HashSet(0);
	private Set tblpdtjadualpindas = new HashSet(0);
	private Set tblpdtaktapindabahagians = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktapinda() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktapinda(Long idAktapinda, Long noAktaAsal,
			Long noAktaPindaan, Long noAktaBaru, String namaAktaBaru,
			Long idFail) {
		this.idAktapinda = idAktapinda;
		this.noAktaAsal = noAktaAsal;
		this.noAktaPindaan = noAktaPindaan;
		this.noAktaBaru = noAktaBaru;
		this.namaAktaBaru = namaAktaBaru;
		this.idFail = idFail;
	}

	/** full constructor */
	public AbstractTblpdtaktapinda(Long idAktapinda, Long noAktaAsal,
			Long noAktaPindaan, Long noAktaBaru, String namaAktaBaru,
			String justifikasiPindaan, Long noMemorandumMenteri,
			String keteranganJemaahMenteri, Date tarikhKuatkuasa,
			Date tarikhDaftarPindaan, Date tarikhKuatkuasaPindaan,
			Date tarikhWarta, Date tarikhPerkenaanDiraja,
			Date tarikhPenyiaranDalamwarta, Long bil, String dirFail,
			Long idFail, String catatan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtaktapindapenggals, Set tblpdtpindaanaktas,
			Set tblpdtjadualpindas, Set tblpdtaktapindabahagians) {
		this.idAktapinda = idAktapinda;
		this.noAktaAsal = noAktaAsal;
		this.noAktaPindaan = noAktaPindaan;
		this.noAktaBaru = noAktaBaru;
		this.namaAktaBaru = namaAktaBaru;
		this.justifikasiPindaan = justifikasiPindaan;
		this.noMemorandumMenteri = noMemorandumMenteri;
		this.keteranganJemaahMenteri = keteranganJemaahMenteri;
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
		this.tblpdtaktapindapenggals = tblpdtaktapindapenggals;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
		this.tblpdtjadualpindas = tblpdtjadualpindas;
		this.tblpdtaktapindabahagians = tblpdtaktapindabahagians;
	}

	// Property accessors

	public Long getIdAktapinda() {
		return this.idAktapinda;
	}

	public void setIdAktapinda(Long idAktapinda) {
		this.idAktapinda = idAktapinda;
	}

	public Long getNoAktaAsal() {
		return this.noAktaAsal;
	}

	public void setNoAktaAsal(Long noAktaAsal) {
		this.noAktaAsal = noAktaAsal;
	}

	public Long getNoAktaPindaan() {
		return this.noAktaPindaan;
	}

	public void setNoAktaPindaan(Long noAktaPindaan) {
		this.noAktaPindaan = noAktaPindaan;
	}

	public Long getNoAktaBaru() {
		return this.noAktaBaru;
	}

	public void setNoAktaBaru(Long noAktaBaru) {
		this.noAktaBaru = noAktaBaru;
	}

	public String getNamaAktaBaru() {
		return this.namaAktaBaru;
	}

	public void setNamaAktaBaru(String namaAktaBaru) {
		this.namaAktaBaru = namaAktaBaru;
	}

	public String getJustifikasiPindaan() {
		return this.justifikasiPindaan;
	}

	public void setJustifikasiPindaan(String justifikasiPindaan) {
		this.justifikasiPindaan = justifikasiPindaan;
	}

	public Long getNoMemorandumMenteri() {
		return this.noMemorandumMenteri;
	}

	public void setNoMemorandumMenteri(Long noMemorandumMenteri) {
		this.noMemorandumMenteri = noMemorandumMenteri;
	}

	public String getKeteranganJemaahMenteri() {
		return this.keteranganJemaahMenteri;
	}

	public void setKeteranganJemaahMenteri(String keteranganJemaahMenteri) {
		this.keteranganJemaahMenteri = keteranganJemaahMenteri;
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

	public Set getTblpdtaktapindapenggals() {
		return this.tblpdtaktapindapenggals;
	}

	public void setTblpdtaktapindapenggals(Set tblpdtaktapindapenggals) {
		this.tblpdtaktapindapenggals = tblpdtaktapindapenggals;
	}

	public Set getTblpdtpindaanaktas() {
		return this.tblpdtpindaanaktas;
	}

	public void setTblpdtpindaanaktas(Set tblpdtpindaanaktas) {
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

	public Set getTblpdtjadualpindas() {
		return this.tblpdtjadualpindas;
	}

	public void setTblpdtjadualpindas(Set tblpdtjadualpindas) {
		this.tblpdtjadualpindas = tblpdtjadualpindas;
	}

	public Set getTblpdtaktapindabahagians() {
		return this.tblpdtaktapindabahagians;
	}

	public void setTblpdtaktapindabahagians(Set tblpdtaktapindabahagians) {
		this.tblpdtaktapindabahagians = tblpdtaktapindabahagians;
	}

}