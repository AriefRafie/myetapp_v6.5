package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborangg entity provides the base persistence definition of the
 * Tblpptborangg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborangg implements java.io.Serializable {

	// Fields

	private Long idBorangg;
	private Date tarikhBorangg;
	private Date tarikhCetak;
	private Date tarikhCetakSemula;
	private Date tarikhTerimaAp;
	private Date tarikhAkhirAp;
	private Date tarikhBorangh;
	private Long idSiasatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborangg() {
	}

	/** full constructor */
	public AbstractTblpptborangg(Date tarikhBorangg, Date tarikhCetak,
			Date tarikhCetakSemula, Date tarikhTerimaAp, Date tarikhAkhirAp,
			Date tarikhBorangh, Long idSiasatan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.tarikhBorangg = tarikhBorangg;
		this.tarikhCetak = tarikhCetak;
		this.tarikhCetakSemula = tarikhCetakSemula;
		this.tarikhTerimaAp = tarikhTerimaAp;
		this.tarikhAkhirAp = tarikhAkhirAp;
		this.tarikhBorangh = tarikhBorangh;
		this.idSiasatan = idSiasatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorangg() {
		return this.idBorangg;
	}

	public void setIdBorangg(Long idBorangg) {
		this.idBorangg = idBorangg;
	}

	public Date getTarikhBorangg() {
		return this.tarikhBorangg;
	}

	public void setTarikhBorangg(Date tarikhBorangg) {
		this.tarikhBorangg = tarikhBorangg;
	}

	public Date getTarikhCetak() {
		return this.tarikhCetak;
	}

	public void setTarikhCetak(Date tarikhCetak) {
		this.tarikhCetak = tarikhCetak;
	}

	public Date getTarikhCetakSemula() {
		return this.tarikhCetakSemula;
	}

	public void setTarikhCetakSemula(Date tarikhCetakSemula) {
		this.tarikhCetakSemula = tarikhCetakSemula;
	}

	public Date getTarikhTerimaAp() {
		return this.tarikhTerimaAp;
	}

	public void setTarikhTerimaAp(Date tarikhTerimaAp) {
		this.tarikhTerimaAp = tarikhTerimaAp;
	}

	public Date getTarikhAkhirAp() {
		return this.tarikhAkhirAp;
	}

	public void setTarikhAkhirAp(Date tarikhAkhirAp) {
		this.tarikhAkhirAp = tarikhAkhirAp;
	}

	public Date getTarikhBorangh() {
		return this.tarikhBorangh;
	}

	public void setTarikhBorangh(Date tarikhBorangh) {
		this.tarikhBorangh = tarikhBorangh;
	}

	public Long getIdSiasatan() {
		return this.idSiasatan;
	}

	public void setIdSiasatan(Long idSiasatan) {
		this.idSiasatan = idSiasatan;
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