package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblhtpbayarancukai entity provides the base persistence definition of
 * the Tblhtpbayarancukai entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpbayarancukai implements
		java.io.Serializable {

	// Fields

	private Long idBayarancukai;
	private Tblhtpperingkatbayaran tblhtpperingkatbayaran;
	private Tblhtpbaucer tblhtpbaucer;
	private String status;
	private Date tarikhBayaran;
	private String namaBank;
	private Double amaun;
	private Date tarikhTerimaBayaran;
	private Date tarikhHantarPtg;
	private String noRujbayaran;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblhtpbajetgunas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblhtpbayarancukai() {
	}

	/** minimal constructor */
	public AbstractTblhtpbayarancukai(Long idBayarancukai,
			Tblhtpperingkatbayaran tblhtpperingkatbayaran,
			Tblhtpbaucer tblhtpbaucer) {
		this.idBayarancukai = idBayarancukai;
		this.tblhtpperingkatbayaran = tblhtpperingkatbayaran;
		this.tblhtpbaucer = tblhtpbaucer;
	}

	/** full constructor */
	public AbstractTblhtpbayarancukai(Long idBayarancukai,
			Tblhtpperingkatbayaran tblhtpperingkatbayaran,
			Tblhtpbaucer tblhtpbaucer, String status, Date tarikhBayaran,
			String namaBank, Double amaun, Date tarikhTerimaBayaran,
			Date tarikhHantarPtg, String noRujbayaran, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblhtpbajetgunas) {
		this.idBayarancukai = idBayarancukai;
		this.tblhtpperingkatbayaran = tblhtpperingkatbayaran;
		this.tblhtpbaucer = tblhtpbaucer;
		this.status = status;
		this.tarikhBayaran = tarikhBayaran;
		this.namaBank = namaBank;
		this.amaun = amaun;
		this.tarikhTerimaBayaran = tarikhTerimaBayaran;
		this.tarikhHantarPtg = tarikhHantarPtg;
		this.noRujbayaran = noRujbayaran;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblhtpbajetgunas = tblhtpbajetgunas;
	}

	// Property accessors

	public Long getIdBayarancukai() {
		return this.idBayarancukai;
	}

	public void setIdBayarancukai(Long idBayarancukai) {
		this.idBayarancukai = idBayarancukai;
	}

	public Tblhtpperingkatbayaran getTblhtpperingkatbayaran() {
		return this.tblhtpperingkatbayaran;
	}

	public void setTblhtpperingkatbayaran(
			Tblhtpperingkatbayaran tblhtpperingkatbayaran) {
		this.tblhtpperingkatbayaran = tblhtpperingkatbayaran;
	}

	public Tblhtpbaucer getTblhtpbaucer() {
		return this.tblhtpbaucer;
	}

	public void setTblhtpbaucer(Tblhtpbaucer tblhtpbaucer) {
		this.tblhtpbaucer = tblhtpbaucer;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTarikhBayaran() {
		return this.tarikhBayaran;
	}

	public void setTarikhBayaran(Date tarikhBayaran) {
		this.tarikhBayaran = tarikhBayaran;
	}

	public String getNamaBank() {
		return this.namaBank;
	}

	public void setNamaBank(String namaBank) {
		this.namaBank = namaBank;
	}

	public Double getAmaun() {
		return this.amaun;
	}

	public void setAmaun(Double amaun) {
		this.amaun = amaun;
	}

	public Date getTarikhTerimaBayaran() {
		return this.tarikhTerimaBayaran;
	}

	public void setTarikhTerimaBayaran(Date tarikhTerimaBayaran) {
		this.tarikhTerimaBayaran = tarikhTerimaBayaran;
	}

	public Date getTarikhHantarPtg() {
		return this.tarikhHantarPtg;
	}

	public void setTarikhHantarPtg(Date tarikhHantarPtg) {
		this.tarikhHantarPtg = tarikhHantarPtg;
	}

	public String getNoRujbayaran() {
		return this.noRujbayaran;
	}

	public void setNoRujbayaran(String noRujbayaran) {
		this.noRujbayaran = noRujbayaran;
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

	public Set getTblhtpbajetgunas() {
		return this.tblhtpbajetgunas;
	}

	public void setTblhtpbajetgunas(Set tblhtpbajetgunas) {
		this.tblhtpbajetgunas = tblhtpbajetgunas;
	}

}