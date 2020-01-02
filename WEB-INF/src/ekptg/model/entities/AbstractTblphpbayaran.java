package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpbayaran entity provides the base persistence definition of the
 * Tblphpbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpbayaran implements java.io.Serializable {

	// Fields

	private Long idBayaran;
	private Tblphpbayaranperludibayar tblphpbayaranperludibayar;
	private Double amaunDibayar;
	private Long idCarabayar;
	private String noResit;
	private Date tarikhResit;
	private String noCek;
	private Date tarikhCek;
	private Date tarikhLuput;
	private String statusBayar;
	private String namaBank;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphppenyelarasanbayarans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpbayaran() {
	}

	/** minimal constructor */
	public AbstractTblphpbayaran(Long idBayaran) {
		this.idBayaran = idBayaran;
	}

	/** full constructor */
	public AbstractTblphpbayaran(Long idBayaran,
			Tblphpbayaranperludibayar tblphpbayaranperludibayar,
			Double amaunDibayar, Long idCarabayar, String noResit,
			Date tarikhResit, String noCek, Date tarikhCek, Date tarikhLuput,
			String statusBayar, String namaBank, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppenyelarasanbayarans) {
		this.idBayaran = idBayaran;
		this.tblphpbayaranperludibayar = tblphpbayaranperludibayar;
		this.amaunDibayar = amaunDibayar;
		this.idCarabayar = idCarabayar;
		this.noResit = noResit;
		this.tarikhResit = tarikhResit;
		this.noCek = noCek;
		this.tarikhCek = tarikhCek;
		this.tarikhLuput = tarikhLuput;
		this.statusBayar = statusBayar;
		this.namaBank = namaBank;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphppenyelarasanbayarans = tblphppenyelarasanbayarans;
	}

	// Property accessors

	public Long getIdBayaran() {
		return this.idBayaran;
	}

	public void setIdBayaran(Long idBayaran) {
		this.idBayaran = idBayaran;
	}

	public Tblphpbayaranperludibayar getTblphpbayaranperludibayar() {
		return this.tblphpbayaranperludibayar;
	}

	public void setTblphpbayaranperludibayar(
			Tblphpbayaranperludibayar tblphpbayaranperludibayar) {
		this.tblphpbayaranperludibayar = tblphpbayaranperludibayar;
	}

	public Double getAmaunDibayar() {
		return this.amaunDibayar;
	}

	public void setAmaunDibayar(Double amaunDibayar) {
		this.amaunDibayar = amaunDibayar;
	}

	public Long getIdCarabayar() {
		return this.idCarabayar;
	}

	public void setIdCarabayar(Long idCarabayar) {
		this.idCarabayar = idCarabayar;
	}

	public String getNoResit() {
		return this.noResit;
	}

	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}

	public Date getTarikhResit() {
		return this.tarikhResit;
	}

	public void setTarikhResit(Date tarikhResit) {
		this.tarikhResit = tarikhResit;
	}

	public String getNoCek() {
		return this.noCek;
	}

	public void setNoCek(String noCek) {
		this.noCek = noCek;
	}

	public Date getTarikhCek() {
		return this.tarikhCek;
	}

	public void setTarikhCek(Date tarikhCek) {
		this.tarikhCek = tarikhCek;
	}

	public Date getTarikhLuput() {
		return this.tarikhLuput;
	}

	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}

	public String getStatusBayar() {
		return this.statusBayar;
	}

	public void setStatusBayar(String statusBayar) {
		this.statusBayar = statusBayar;
	}

	public String getNamaBank() {
		return this.namaBank;
	}

	public void setNamaBank(String namaBank) {
		this.namaBank = namaBank;
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

	public Set getTblphppenyelarasanbayarans() {
		return this.tblphppenyelarasanbayarans;
	}

	public void setTblphppenyelarasanbayarans(Set tblphppenyelarasanbayarans) {
		this.tblphppenyelarasanbayarans = tblphppenyelarasanbayarans;
	}

}