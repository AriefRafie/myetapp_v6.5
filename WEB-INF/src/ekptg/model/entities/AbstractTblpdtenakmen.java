package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmen entity provides the base persistence definition of the
 * Tblpdtenakmen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmen implements java.io.Serializable {

	// Fields

	private Long idEnakmen;
	private Long noEnakmen;
	private String namaEnakmen;
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
	private Date tarikhWarta;
	private Set tblpdtenakmenbahagians = new HashSet(0);
	private Set tblpdtenakmenpenggals = new HashSet(0);
	private Set tblpdtjaduals = new HashSet(0);
	private Set tblpdtpindaanenakmens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmen() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmen(Long idEnakmen, Long noEnakmen,
			String namaEnakmen, Long idFail) {
		this.idEnakmen = idEnakmen;
		this.noEnakmen = noEnakmen;
		this.namaEnakmen = namaEnakmen;
		this.idFail = idFail;
	}

	/** full constructor */
	public AbstractTblpdtenakmen(Long idEnakmen, Long noEnakmen,
			String namaEnakmen, Date tarikhKuatkuasa, Date tarikhDaftar,
			Date tarikhMansuh, Long bil, String dirFail, Long idFail,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, String catatan, Date tarikhWarta,
			Set tblpdtenakmenbahagians, Set tblpdtenakmenpenggals,
			Set tblpdtjaduals, Set tblpdtpindaanenakmens) {
		this.idEnakmen = idEnakmen;
		this.noEnakmen = noEnakmen;
		this.namaEnakmen = namaEnakmen;
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
		this.tarikhWarta = tarikhWarta;
		this.tblpdtenakmenbahagians = tblpdtenakmenbahagians;
		this.tblpdtenakmenpenggals = tblpdtenakmenpenggals;
		this.tblpdtjaduals = tblpdtjaduals;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	// Property accessors

	public Long getIdEnakmen() {
		return this.idEnakmen;
	}

	public void setIdEnakmen(Long idEnakmen) {
		this.idEnakmen = idEnakmen;
	}

	public Long getNoEnakmen() {
		return this.noEnakmen;
	}

	public void setNoEnakmen(Long noEnakmen) {
		this.noEnakmen = noEnakmen;
	}

	public String getNamaEnakmen() {
		return this.namaEnakmen;
	}

	public void setNamaEnakmen(String namaEnakmen) {
		this.namaEnakmen = namaEnakmen;
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

	public Date getTarikhWarta() {
		return this.tarikhWarta;
	}

	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public Set getTblpdtenakmenbahagians() {
		return this.tblpdtenakmenbahagians;
	}

	public void setTblpdtenakmenbahagians(Set tblpdtenakmenbahagians) {
		this.tblpdtenakmenbahagians = tblpdtenakmenbahagians;
	}

	public Set getTblpdtenakmenpenggals() {
		return this.tblpdtenakmenpenggals;
	}

	public void setTblpdtenakmenpenggals(Set tblpdtenakmenpenggals) {
		this.tblpdtenakmenpenggals = tblpdtenakmenpenggals;
	}

	public Set getTblpdtjaduals() {
		return this.tblpdtjaduals;
	}

	public void setTblpdtjaduals(Set tblpdtjaduals) {
		this.tblpdtjaduals = tblpdtjaduals;
	}

	public Set getTblpdtpindaanenakmens() {
		return this.tblpdtpindaanenakmens;
	}

	public void setTblpdtpindaanenakmens(Set tblpdtpindaanenakmens) {
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

}