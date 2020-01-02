package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenpindapenggal entity provides the base persistence
 * definition of the Tblpdtenakmenpindapenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenpindapenggal implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenpindapenggal;
	private Tblpdtenakmenpinda tblpdtenakmenpinda;
	private String namaPenggal;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanenakmenpenggals = new HashSet(0);
	private Set tblpdtenakmenpindabahagians = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenpindapenggal() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenpindapenggal(Long idEnakmenpindapenggal) {
		this.idEnakmenpindapenggal = idEnakmenpindapenggal;
	}

	/** full constructor */
	public AbstractTblpdtenakmenpindapenggal(Long idEnakmenpindapenggal,
			Tblpdtenakmenpinda tblpdtenakmenpinda, String namaPenggal,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmenpenggals,
			Set tblpdtenakmenpindabahagians) {
		this.idEnakmenpindapenggal = idEnakmenpindapenggal;
		this.tblpdtenakmenpinda = tblpdtenakmenpinda;
		this.namaPenggal = namaPenggal;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanenakmenpenggals = tblpdtpindaanenakmenpenggals;
		this.tblpdtenakmenpindabahagians = tblpdtenakmenpindabahagians;
	}

	// Property accessors

	public Long getIdEnakmenpindapenggal() {
		return this.idEnakmenpindapenggal;
	}

	public void setIdEnakmenpindapenggal(Long idEnakmenpindapenggal) {
		this.idEnakmenpindapenggal = idEnakmenpindapenggal;
	}

	public Tblpdtenakmenpinda getTblpdtenakmenpinda() {
		return this.tblpdtenakmenpinda;
	}

	public void setTblpdtenakmenpinda(Tblpdtenakmenpinda tblpdtenakmenpinda) {
		this.tblpdtenakmenpinda = tblpdtenakmenpinda;
	}

	public String getNamaPenggal() {
		return this.namaPenggal;
	}

	public void setNamaPenggal(String namaPenggal) {
		this.namaPenggal = namaPenggal;
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

	public Set getTblpdtpindaanenakmenpenggals() {
		return this.tblpdtpindaanenakmenpenggals;
	}

	public void setTblpdtpindaanenakmenpenggals(Set tblpdtpindaanenakmenpenggals) {
		this.tblpdtpindaanenakmenpenggals = tblpdtpindaanenakmenpenggals;
	}

	public Set getTblpdtenakmenpindabahagians() {
		return this.tblpdtenakmenpindabahagians;
	}

	public void setTblpdtenakmenpindabahagians(Set tblpdtenakmenpindabahagians) {
		this.tblpdtenakmenpindabahagians = tblpdtenakmenpindabahagians;
	}

}