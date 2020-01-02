package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTbljejakaudit entity provides the base persistence definition of the
 * Tbljejakaudit entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTbljejakaudit implements java.io.Serializable {

	// Fields

	private Long idJejakAudit;
	private Date tarikhAktiviti;
	private String kodPengguna;
	private String kodSkrin;
	private String catatan;

	// Constructors

	/** default constructor */
	public AbstractTbljejakaudit() {
	}

	/** full constructor */
	public AbstractTbljejakaudit(Date tarikhAktiviti, String kodPengguna,
			String kodSkrin, String catatan) {
		this.tarikhAktiviti = tarikhAktiviti;
		this.kodPengguna = kodPengguna;
		this.kodSkrin = kodSkrin;
		this.catatan = catatan;
	}

	// Property accessors

	public Long getIdJejakAudit() {
		return this.idJejakAudit;
	}

	public void setIdJejakAudit(Long idJejakAudit) {
		this.idJejakAudit = idJejakAudit;
	}

	public Date getTarikhAktiviti() {
		return this.tarikhAktiviti;
	}

	public void setTarikhAktiviti(Date tarikhAktiviti) {
		this.tarikhAktiviti = tarikhAktiviti;
	}

	public String getKodPengguna() {
		return this.kodPengguna;
	}

	public void setKodPengguna(String kodPengguna) {
		this.kodPengguna = kodPengguna;
	}

	public String getKodSkrin() {
		return this.kodSkrin;
	}

	public void setKodSkrin(String kodSkrin) {
		this.kodSkrin = kodSkrin;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

}