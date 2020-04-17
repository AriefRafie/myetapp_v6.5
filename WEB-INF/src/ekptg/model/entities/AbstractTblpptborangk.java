package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborangk entity provides the base persistence definition of the
 * Tblpptborangk entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborangk implements java.io.Serializable {

	// Fields

	private Long idBorangk;
	private String flagSegera;
	private Date tarikhBorangk;
	private Date tarikhCetak;
	private Long idBorangi;
	private Long idBorangg;
	private Long idPermohonan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborangk() {
	}

	/** full constructor */
	public AbstractTblpptborangk(String flagSegera, Date tarikhBorangk,
			Date tarikhCetak, Long idBorangi, Long idBorangg,
			Long idPermohonan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.flagSegera = flagSegera;
		this.tarikhBorangk = tarikhBorangk;
		this.tarikhCetak = tarikhCetak;
		this.idBorangi = idBorangi;
		this.idBorangg = idBorangg;
		this.idPermohonan = idPermohonan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorangk() {
		return this.idBorangk;
	}

	public void setIdBorangk(Long idBorangk) {
		this.idBorangk = idBorangk;
	}

	public String getFlagSegera() {
		return this.flagSegera;
	}

	public void setFlagSegera(String flagSegera) {
		this.flagSegera = flagSegera;
	}

	public Date getTarikhBorangk() {
		return this.tarikhBorangk;
	}

	public void setTarikhBorangk(Date tarikhBorangk) {
		this.tarikhBorangk = tarikhBorangk;
	}

	public Date getTarikhCetak() {
		return this.tarikhCetak;
	}

	public void setTarikhCetak(Date tarikhCetak) {
		this.tarikhCetak = tarikhCetak;
	}

	public Long getIdBorangi() {
		return this.idBorangi;
	}

	public void setIdBorangi(Long idBorangi) {
		this.idBorangi = idBorangi;
	}

	public Long getIdBorangg() {
		return this.idBorangg;
	}

	public void setIdBorangg(Long idBorangg) {
		this.idBorangg = idBorangg;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
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