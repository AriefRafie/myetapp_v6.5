package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptwarta entity provides the base persistence definition of the
 * Tblpptwarta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptwarta implements java.io.Serializable {

	// Fields

	private Long idWarta;
	private Long prosesWarta;
	private String noWarta;
	private Long jenisWarta;
	private Date tarikhWarta;
	private Date tarikhTerimaWarta;
	private Long idPermohonan;
	private Long idPenarikanbalik;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptwarta() {
	}

	/** full constructor */
	public AbstractTblpptwarta(Long prosesWarta, String noWarta,
			Long jenisWarta, Date tarikhWarta, Date tarikhTerimaWarta,
			Long idPermohonan, Long idPenarikanbalik, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.prosesWarta = prosesWarta;
		this.noWarta = noWarta;
		this.jenisWarta = jenisWarta;
		this.tarikhWarta = tarikhWarta;
		this.tarikhTerimaWarta = tarikhTerimaWarta;
		this.idPermohonan = idPermohonan;
		this.idPenarikanbalik = idPenarikanbalik;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdWarta() {
		return this.idWarta;
	}

	public void setIdWarta(Long idWarta) {
		this.idWarta = idWarta;
	}

	public Long getProsesWarta() {
		return this.prosesWarta;
	}

	public void setProsesWarta(Long prosesWarta) {
		this.prosesWarta = prosesWarta;
	}

	public String getNoWarta() {
		return this.noWarta;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public Long getJenisWarta() {
		return this.jenisWarta;
	}

	public void setJenisWarta(Long jenisWarta) {
		this.jenisWarta = jenisWarta;
	}

	public Date getTarikhWarta() {
		return this.tarikhWarta;
	}

	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public Date getTarikhTerimaWarta() {
		return this.tarikhTerimaWarta;
	}

	public void setTarikhTerimaWarta(Date tarikhTerimaWarta) {
		this.tarikhTerimaWarta = tarikhTerimaWarta;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdPenarikanbalik() {
		return this.idPenarikanbalik;
	}

	public void setIdPenarikanbalik(Long idPenarikanbalik) {
		this.idPenarikanbalik = idPenarikanbalik;
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

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}