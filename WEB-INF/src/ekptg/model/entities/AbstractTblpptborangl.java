package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborangl entity provides the base persistence definition of the
 * Tblpptborangl entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborangl implements java.io.Serializable {

	// Fields

	private Long idBorangl;
	private Date tarikhBorangl;
	private Date tarikhCetak;
	private Long jenisPilih;
	private Long unitTempoh;
	private Date tarikhTempoh;
	private Long tempoh;
	private Long idPermohonan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborangl() {
	}

	/** full constructor */
	public AbstractTblpptborangl(Date tarikhBorangl, Date tarikhCetak,
			Long jenisPilih, Long unitTempoh, Date tarikhTempoh, Long tempoh,
			Long idPermohonan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.tarikhBorangl = tarikhBorangl;
		this.tarikhCetak = tarikhCetak;
		this.jenisPilih = jenisPilih;
		this.unitTempoh = unitTempoh;
		this.tarikhTempoh = tarikhTempoh;
		this.tempoh = tempoh;
		this.idPermohonan = idPermohonan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorangl() {
		return this.idBorangl;
	}

	public void setIdBorangl(Long idBorangl) {
		this.idBorangl = idBorangl;
	}

	public Date getTarikhBorangl() {
		return this.tarikhBorangl;
	}

	public void setTarikhBorangl(Date tarikhBorangl) {
		this.tarikhBorangl = tarikhBorangl;
	}

	public Date getTarikhCetak() {
		return this.tarikhCetak;
	}

	public void setTarikhCetak(Date tarikhCetak) {
		this.tarikhCetak = tarikhCetak;
	}

	public Long getJenisPilih() {
		return this.jenisPilih;
	}

	public void setJenisPilih(Long jenisPilih) {
		this.jenisPilih = jenisPilih;
	}

	public Long getUnitTempoh() {
		return this.unitTempoh;
	}

	public void setUnitTempoh(Long unitTempoh) {
		this.unitTempoh = unitTempoh;
	}

	public Date getTarikhTempoh() {
		return this.tarikhTempoh;
	}

	public void setTarikhTempoh(Date tarikhTempoh) {
		this.tarikhTempoh = tarikhTempoh;
	}

	public Long getTempoh() {
		return this.tempoh;
	}

	public void setTempoh(Long tempoh) {
		this.tempoh = tempoh;
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