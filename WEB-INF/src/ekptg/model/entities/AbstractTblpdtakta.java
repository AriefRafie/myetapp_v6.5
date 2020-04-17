package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtakta entity provides the base persistence definition of the
 * Tblpdtakta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtakta implements java.io.Serializable {

	// Fields

	private Long idAkta;
	private String noAkta;
	private String namaAkta;
	private Date tarikhKuatkuasa;
	private Date tarikhDaftar;
	private Date tarikhMansuh;
	private Long bil;
	private String dirFail;
	private Long idFail;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private String catatan;
	private Set tblpdtpindaanaktas = new HashSet(0);
	private Set tblpdtjaduals = new HashSet(0);
	private Set tblpdtaktabahagians = new HashSet(0);
	private Set tblpdtaktapenggals = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtakta() {
	}

	/** minimal constructor */
	public AbstractTblpdtakta(Long idAkta, String noAkta, String namaAkta,
			Long idFail) {
		this.idAkta = idAkta;
		this.noAkta = noAkta;
		this.namaAkta = namaAkta;
		this.idFail = idFail;
	}

	/** full constructor */
	public AbstractTblpdtakta(Long idAkta, String noAkta, String namaAkta,
			Date tarikhKuatkuasa, Date tarikhDaftar, Date tarikhMansuh,
			Long bil, String dirFail, Long idFail, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			String catatan, Set tblpdtpindaanaktas, Set tblpdtjaduals,
			Set tblpdtaktabahagians, Set tblpdtaktapenggals) {
		this.idAkta = idAkta;
		this.noAkta = noAkta;
		this.namaAkta = namaAkta;
		this.tarikhKuatkuasa = tarikhKuatkuasa;
		this.tarikhDaftar = tarikhDaftar;
		this.tarikhMansuh = tarikhMansuh;
		this.bil = bil;
		this.dirFail = dirFail;
		this.idFail = idFail;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.catatan = catatan;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
		this.tblpdtjaduals = tblpdtjaduals;
		this.tblpdtaktabahagians = tblpdtaktabahagians;
		this.tblpdtaktapenggals = tblpdtaktapenggals;
	}

	// Property accessors

	public Long getIdAkta() {
		return this.idAkta;
	}

	public void setIdAkta(Long idAkta) {
		this.idAkta = idAkta;
	}

	public String getNoAkta() {
		return this.noAkta;
	}

	public void setNoAkta(String noAkta) {
		this.noAkta = noAkta;
	}

	public String getNamaAkta() {
		return this.namaAkta;
	}

	public void setNamaAkta(String namaAkta) {
		this.namaAkta = namaAkta;
	}

	public Date getTarikhKuatkuasa() {
		return this.tarikhKuatkuasa;
	}

	public void setTarikhKuatkuasa(Date tarikhKuatkuasa) {
		this.tarikhKuatkuasa = tarikhKuatkuasa;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public Date getTarikhMansuh() {
		return this.tarikhMansuh;
	}

	public void setTarikhMansuh(Date tarikhMansuh) {
		this.tarikhMansuh = tarikhMansuh;
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

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Set getTblpdtpindaanaktas() {
		return this.tblpdtpindaanaktas;
	}

	public void setTblpdtpindaanaktas(Set tblpdtpindaanaktas) {
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

	public Set getTblpdtjaduals() {
		return this.tblpdtjaduals;
	}

	public void setTblpdtjaduals(Set tblpdtjaduals) {
		this.tblpdtjaduals = tblpdtjaduals;
	}

	public Set getTblpdtaktabahagians() {
		return this.tblpdtaktabahagians;
	}

	public void setTblpdtaktabahagians(Set tblpdtaktabahagians) {
		this.tblpdtaktabahagians = tblpdtaktabahagians;
	}

	public Set getTblpdtaktapenggals() {
		return this.tblpdtaktapenggals;
	}

	public void setTblpdtaktapenggals(Set tblpdtaktapenggals) {
		this.tblpdtaktapenggals = tblpdtaktapenggals;
	}

}