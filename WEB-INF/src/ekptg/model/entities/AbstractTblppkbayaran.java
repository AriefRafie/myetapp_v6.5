package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkbayaran entity provides the base persistence definition of the
 * Tblppkbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkbayaran implements java.io.Serializable {

	// Fields

	private Long idBayaran;
//	private Tblppkpermohonan tblppkpermohonan;
        private Long idPermohonan;
	private Long idJenisbayar;
	private String noResit;
	private Date tarikhBayaran;
	private Double jumlahBayaran;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangphas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkbayaran() {
	}

	/** minimal constructor */
	public AbstractTblppkbayaran(Long idBayaran) {
		this.idBayaran = idBayaran;
	//	this.tblppkpermohonan = tblppkpermohonan;
	}

	/** full constructor */
	public AbstractTblppkbayaran(Long idBayaran,
			Long idPermohonan, Long idJenisbayar,
			String noResit, Date tarikhBayaran, Double jumlahBayaran,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkborangphas) {
		this.idBayaran = idBayaran;
		//this.tblppkpermohonan = tblppkpermohonan;
                this.idPermohonan = idPermohonan;
		this.idJenisbayar = idJenisbayar;
		this.noResit = noResit;
		this.tarikhBayaran = tarikhBayaran;
		this.jumlahBayaran = jumlahBayaran;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangphas = tblppkborangphas;
	}

	// Property accessors

	public Long getIdBayaran() {
		return this.idBayaran;
	}

	public void setIdBayaran(Long idBayaran) {
		this.idBayaran = idBayaran;
	}

/*	public Tblppkpermohonan getTblppkpermohonan() {
		return this.tblppkpermohonan;
	}

	public void setTblppkpermohonan(Tblppkpermohonan tblppkpermohonan) {
		this.tblppkpermohonan = tblppkpermohonan;
	}
*/
	public Long getIdJenisbayar() {
		return this.idJenisbayar;
	}

	public void setIdJenisbayar(Long idJenisbayar) {
		this.idJenisbayar = idJenisbayar;
	}

	public String getNoResit() {
		return this.noResit;
	}

	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}

	public Date getTarikhBayaran() {
		return this.tarikhBayaran;
	}

	public void setTarikhBayaran(Date tarikhBayaran) {
		this.tarikhBayaran = tarikhBayaran;
	}

	public Double getJumlahBayaran() {
		return this.jumlahBayaran;
	}

	public void setJumlahBayaran(Double jumlahBayaran) {
		this.jumlahBayaran = jumlahBayaran;
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

	public Set getTblppkborangphas() {
		return this.tblppkborangphas;
	}

	public void setTblppkborangphas(Set tblppkborangphas) {
		this.tblppkborangphas = tblppkborangphas;
	}

    public void setIdPermohonan(Long idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdPermohonan() {
        return idPermohonan;
    }
}
