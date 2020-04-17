package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpboranga entity provides the base persistence definition of the
 * Tblphpboranga entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpboranga implements java.io.Serializable {

	// Fields

	private Long idBoranga;
	private Tblphpjadualkedualesenapb tblphpjadualkedualesenapb;
	private String tujuan;
	private String destinasi;
	private Long idIsipadu;
	private Double isipadu;
	private Double anggaranRoyalti;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphpbarges = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpboranga() {
	}

	/** minimal constructor */
	public AbstractTblphpboranga(Long idBoranga) {
		this.idBoranga = idBoranga;
	}

	/** full constructor */
	public AbstractTblphpboranga(Long idBoranga,
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb, String tujuan,
			String destinasi, Long idIsipadu, Double isipadu,
			Double anggaranRoyalti, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblphpbarges) {
		this.idBoranga = idBoranga;
		this.tblphpjadualkedualesenapb = tblphpjadualkedualesenapb;
		this.tujuan = tujuan;
		this.destinasi = destinasi;
		this.idIsipadu = idIsipadu;
		this.isipadu = isipadu;
		this.anggaranRoyalti = anggaranRoyalti;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphpbarges = tblphpbarges;
	}

	// Property accessors

	public Long getIdBoranga() {
		return this.idBoranga;
	}

	public void setIdBoranga(Long idBoranga) {
		this.idBoranga = idBoranga;
	}

	public Tblphpjadualkedualesenapb getTblphpjadualkedualesenapb() {
		return this.tblphpjadualkedualesenapb;
	}

	public void setTblphpjadualkedualesenapb(
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb) {
		this.tblphpjadualkedualesenapb = tblphpjadualkedualesenapb;
	}

	public String getTujuan() {
		return this.tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getDestinasi() {
		return this.destinasi;
	}

	public void setDestinasi(String destinasi) {
		this.destinasi = destinasi;
	}

	public Long getIdIsipadu() {
		return this.idIsipadu;
	}

	public void setIdIsipadu(Long idIsipadu) {
		this.idIsipadu = idIsipadu;
	}

	public Double getIsipadu() {
		return this.isipadu;
	}

	public void setIsipadu(Double isipadu) {
		this.isipadu = isipadu;
	}

	public Double getAnggaranRoyalti() {
		return this.anggaranRoyalti;
	}

	public void setAnggaranRoyalti(Double anggaranRoyalti) {
		this.anggaranRoyalti = anggaranRoyalti;
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

	public Set getTblphpbarges() {
		return this.tblphpbarges;
	}

	public void setTblphpbarges(Set tblphpbarges) {
		this.tblphpbarges = tblphpbarges;
	}

}