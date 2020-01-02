package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblhtpcukaiutama entity provides the base persistence definition of
 * the Tblhtpcukaiutama entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpcukaiutama implements java.io.Serializable {

	// Fields

	private Long idCukaiutama;
	private Tblhtpperingkatbayaran tblhtpperingkatbayaran;
	private Double jumlagCukai;
	private Long jumlahHakmilik;
	private Double amaunBayaranCukai;
	private String tahun;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblhtpbaucers = new HashSet(0);
	private Set tblhtpcukaiterperincis = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblhtpcukaiutama() {
	}

	/** minimal constructor */
	public AbstractTblhtpcukaiutama(Long idCukaiutama,
			Tblhtpperingkatbayaran tblhtpperingkatbayaran, Long idNegeri,
			Long idDaerah) {
		this.idCukaiutama = idCukaiutama;
		this.tblhtpperingkatbayaran = tblhtpperingkatbayaran;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
	}

	/** full constructor */
	public AbstractTblhtpcukaiutama(Long idCukaiutama,
			Tblhtpperingkatbayaran tblhtpperingkatbayaran, Double jumlagCukai,
			Long jumlahHakmilik, Double amaunBayaranCukai, String tahun,
			Long idNegeri, Long idDaerah, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblhtpbaucers,
			Set tblhtpcukaiterperincis) {
		this.idCukaiutama = idCukaiutama;
		this.tblhtpperingkatbayaran = tblhtpperingkatbayaran;
		this.jumlagCukai = jumlagCukai;
		this.jumlahHakmilik = jumlahHakmilik;
		this.amaunBayaranCukai = amaunBayaranCukai;
		this.tahun = tahun;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblhtpbaucers = tblhtpbaucers;
		this.tblhtpcukaiterperincis = tblhtpcukaiterperincis;
	}

	// Property accessors

	public Long getIdCukaiutama() {
		return this.idCukaiutama;
	}

	public void setIdCukaiutama(Long idCukaiutama) {
		this.idCukaiutama = idCukaiutama;
	}

	public Tblhtpperingkatbayaran getTblhtpperingkatbayaran() {
		return this.tblhtpperingkatbayaran;
	}

	public void setTblhtpperingkatbayaran(
			Tblhtpperingkatbayaran tblhtpperingkatbayaran) {
		this.tblhtpperingkatbayaran = tblhtpperingkatbayaran;
	}

	public Double getJumlagCukai() {
		return this.jumlagCukai;
	}

	public void setJumlagCukai(Double jumlagCukai) {
		this.jumlagCukai = jumlagCukai;
	}

	public Long getJumlahHakmilik() {
		return this.jumlahHakmilik;
	}

	public void setJumlahHakmilik(Long jumlahHakmilik) {
		this.jumlahHakmilik = jumlahHakmilik;
	}

	public Double getAmaunBayaranCukai() {
		return this.amaunBayaranCukai;
	}

	public void setAmaunBayaranCukai(Double amaunBayaranCukai) {
		this.amaunBayaranCukai = amaunBayaranCukai;
	}

	public String getTahun() {
		return this.tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
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

	public Set getTblhtpbaucers() {
		return this.tblhtpbaucers;
	}

	public void setTblhtpbaucers(Set tblhtpbaucers) {
		this.tblhtpbaucers = tblhtpbaucers;
	}

	public Set getTblhtpcukaiterperincis() {
		return this.tblhtpcukaiterperincis;
	}

	public void setTblhtpcukaiterperincis(Set tblhtpcukaiterperincis) {
		this.tblhtpcukaiterperincis = tblhtpcukaiterperincis;
	}

}