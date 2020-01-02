package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblhtpbaucer entity provides the base persistence definition of the
 * Tblhtpbaucer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpbaucer implements java.io.Serializable {

	// Fields

	private Long idBaucer;
	private Tblhtpcukaiutama tblhtpcukaiutama;
	private String tahun;
	private String noBaucer;
	private Double amaunBaucer;
	private Date tarikhBaucer;
	private Long idBank;
	private Date tarikhBayaran;
	private String noResit;
	private Date tarikhResit;
	private Date tarikhTerima;
	private Date tarikhTerimaResit;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblhtpbayarancukais = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblhtpbaucer() {
	}

	/** minimal constructor */
	public AbstractTblhtpbaucer(Long idBaucer,
			Tblhtpcukaiutama tblhtpcukaiutama, Long idNegeri, Long idDaerah) {
		this.idBaucer = idBaucer;
		this.tblhtpcukaiutama = tblhtpcukaiutama;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
	}

	/** full constructor */
	public AbstractTblhtpbaucer(Long idBaucer,
			Tblhtpcukaiutama tblhtpcukaiutama, String tahun, String noBaucer,
			Double amaunBaucer, Date tarikhBaucer, Long idBank,
			Date tarikhBayaran, String noResit, Date tarikhResit,
			Date tarikhTerima, Date tarikhTerimaResit, Long idNegeri,
			Long idDaerah, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblhtpbayarancukais) {
		this.idBaucer = idBaucer;
		this.tblhtpcukaiutama = tblhtpcukaiutama;
		this.tahun = tahun;
		this.noBaucer = noBaucer;
		this.amaunBaucer = amaunBaucer;
		this.tarikhBaucer = tarikhBaucer;
		this.idBank = idBank;
		this.tarikhBayaran = tarikhBayaran;
		this.noResit = noResit;
		this.tarikhResit = tarikhResit;
		this.tarikhTerima = tarikhTerima;
		this.tarikhTerimaResit = tarikhTerimaResit;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblhtpbayarancukais = tblhtpbayarancukais;
	}

	// Property accessors

	public Long getIdBaucer() {
		return this.idBaucer;
	}

	public void setIdBaucer(Long idBaucer) {
		this.idBaucer = idBaucer;
	}

	public Tblhtpcukaiutama getTblhtpcukaiutama() {
		return this.tblhtpcukaiutama;
	}

	public void setTblhtpcukaiutama(Tblhtpcukaiutama tblhtpcukaiutama) {
		this.tblhtpcukaiutama = tblhtpcukaiutama;
	}

	public String getTahun() {
		return this.tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	public String getNoBaucer() {
		return this.noBaucer;
	}

	public void setNoBaucer(String noBaucer) {
		this.noBaucer = noBaucer;
	}

	public Double getAmaunBaucer() {
		return this.amaunBaucer;
	}

	public void setAmaunBaucer(Double amaunBaucer) {
		this.amaunBaucer = amaunBaucer;
	}

	public Date getTarikhBaucer() {
		return this.tarikhBaucer;
	}

	public void setTarikhBaucer(Date tarikhBaucer) {
		this.tarikhBaucer = tarikhBaucer;
	}

	public Long getIdBank() {
		return this.idBank;
	}

	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	public Date getTarikhBayaran() {
		return this.tarikhBayaran;
	}

	public void setTarikhBayaran(Date tarikhBayaran) {
		this.tarikhBayaran = tarikhBayaran;
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

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhTerimaResit() {
		return this.tarikhTerimaResit;
	}

	public void setTarikhTerimaResit(Date tarikhTerimaResit) {
		this.tarikhTerimaResit = tarikhTerimaResit;
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

	public Set getTblhtpbayarancukais() {
		return this.tblhtpbayarancukais;
	}

	public void setTblhtpbayarancukais(Set tblhtpbayarancukais) {
		this.tblhtpbayarancukais = tblhtpbayarancukais;
	}

}