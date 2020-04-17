package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujlot entity provides the base persistence definition of the
 * Tblrujlot entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujlot implements java.io.Serializable {

	// Fields

	private Long idLot;
	private String kodLot;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujlot() {
	}

	/** minimal constructor */
	public AbstractTblrujlot(Long idLot) {
		this.idLot = idLot;
	}

	/** full constructor */
	public AbstractTblrujlot(Long idLot, String kodLot, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idLot = idLot;
		this.kodLot = kodLot;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdLot() {
		return this.idLot;
	}

	public void setIdLot(Long idLot) {
		this.idLot = idLot;
	}

	public String getKodLot() {
		return this.kodLot;
	}

	public void setKodLot(String kodLot) {
		this.kodLot = kodLot;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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

}