package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkboranga entity provides the base persistence definition of the
 * Tblppkboranga entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkboranga implements java.io.Serializable {

	// Fields

	private Long idBoranga;
	private Tblppkpermohonan tblppkpermohonan;
	private Date tarikhMohon;
	private Double jumlahHtaTarikhmohon;
	private Double jumlahHtaTarikhmati;
	private Double jumlahHaTarikhmohon;
	private Double jumlahHaTarikhmati;
	private Double jumlahHartaTarikhmohon;
	private Double jumlahHartaTarikhmati;
	private String catatan;
	private Long idNegerimhn;
	private Long idDaerahmhn;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangapemohons = new HashSet(0);
	private Set tblppkborangasimatis = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkboranga() {
	}

	/** minimal constructor */
	public AbstractTblppkboranga(Long idBoranga,
			Tblppkpermohonan tblppkpermohonan) {
		this.idBoranga = idBoranga;
		this.tblppkpermohonan = tblppkpermohonan;
	}

	/** full constructor */
	public AbstractTblppkboranga(Long idBoranga,
			Tblppkpermohonan tblppkpermohonan, Date tarikhMohon,
			Double jumlahHtaTarikhmohon, Double jumlahHtaTarikhmati,
			Double jumlahHaTarikhmohon, Double jumlahHaTarikhmati,
			Double jumlahHartaTarikhmohon, Double jumlahHartaTarikhmati,
			String catatan, Long idNegerimhn, Long idDaerahmhn, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkborangapemohons, Set tblppkborangasimatis) {
		this.idBoranga = idBoranga;
		this.tblppkpermohonan = tblppkpermohonan;
		this.tarikhMohon = tarikhMohon;
		this.jumlahHtaTarikhmohon = jumlahHtaTarikhmohon;
		this.jumlahHtaTarikhmati = jumlahHtaTarikhmati;
		this.jumlahHaTarikhmohon = jumlahHaTarikhmohon;
		this.jumlahHaTarikhmati = jumlahHaTarikhmati;
		this.jumlahHartaTarikhmohon = jumlahHartaTarikhmohon;
		this.jumlahHartaTarikhmati = jumlahHartaTarikhmati;
		this.catatan = catatan;
		this.idNegerimhn = idNegerimhn;
		this.idDaerahmhn = idDaerahmhn;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangapemohons = tblppkborangapemohons;
		this.tblppkborangasimatis = tblppkborangasimatis;
	}

	// Property accessors

	public Long getIdBoranga() {
		return this.idBoranga;
	}

	public void setIdBoranga(Long idBoranga) {
		this.idBoranga = idBoranga;
	}

	public Tblppkpermohonan getTblppkpermohonan() {
		return this.tblppkpermohonan;
	}

	public void setTblppkpermohonan(Tblppkpermohonan tblppkpermohonan) {
		this.tblppkpermohonan = tblppkpermohonan;
	}

	public Date getTarikhMohon() {
		return this.tarikhMohon;
	}

	public void setTarikhMohon(Date tarikhMohon) {
		this.tarikhMohon = tarikhMohon;
	}

	public Double getJumlahHtaTarikhmohon() {
		return this.jumlahHtaTarikhmohon;
	}

	public void setJumlahHtaTarikhmohon(Double jumlahHtaTarikhmohon) {
		this.jumlahHtaTarikhmohon = jumlahHtaTarikhmohon;
	}

	public Double getJumlahHtaTarikhmati() {
		return this.jumlahHtaTarikhmati;
	}

	public void setJumlahHtaTarikhmati(Double jumlahHtaTarikhmati) {
		this.jumlahHtaTarikhmati = jumlahHtaTarikhmati;
	}

	public Double getJumlahHaTarikhmohon() {
		return this.jumlahHaTarikhmohon;
	}

	public void setJumlahHaTarikhmohon(Double jumlahHaTarikhmohon) {
		this.jumlahHaTarikhmohon = jumlahHaTarikhmohon;
	}

	public Double getJumlahHaTarikhmati() {
		return this.jumlahHaTarikhmati;
	}

	public void setJumlahHaTarikhmati(Double jumlahHaTarikhmati) {
		this.jumlahHaTarikhmati = jumlahHaTarikhmati;
	}

	public Double getJumlahHartaTarikhmohon() {
		return this.jumlahHartaTarikhmohon;
	}

	public void setJumlahHartaTarikhmohon(Double jumlahHartaTarikhmohon) {
		this.jumlahHartaTarikhmohon = jumlahHartaTarikhmohon;
	}

	public Double getJumlahHartaTarikhmati() {
		return this.jumlahHartaTarikhmati;
	}

	public void setJumlahHartaTarikhmati(Double jumlahHartaTarikhmati) {
		this.jumlahHartaTarikhmati = jumlahHartaTarikhmati;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Long getIdNegerimhn() {
		return this.idNegerimhn;
	}

	public void setIdNegerimhn(Long idNegerimhn) {
		this.idNegerimhn = idNegerimhn;
	}

	public Long getIdDaerahmhn() {
		return this.idDaerahmhn;
	}

	public void setIdDaerahmhn(Long idDaerahmhn) {
		this.idDaerahmhn = idDaerahmhn;
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

	public Set getTblppkborangapemohons() {
		return this.tblppkborangapemohons;
	}

	public void setTblppkborangapemohons(Set tblppkborangapemohons) {
		this.tblppkborangapemohons = tblppkborangapemohons;
	}

	public Set getTblppkborangasimatis() {
		return this.tblppkborangasimatis;
	}

	public void setTblppkborangasimatis(Set tblppkborangasimatis) {
		this.tblppkborangasimatis = tblppkborangasimatis;
	}

}