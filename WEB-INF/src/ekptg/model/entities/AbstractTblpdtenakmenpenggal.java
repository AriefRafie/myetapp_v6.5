package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenpenggal entity provides the base persistence definition
 * of the Tblpdtenakmenpenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenpenggal implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenpenggal;
	private Tblpdtenakmen tblpdtenakmen;
	private String tajukPenggal;
	private String noPenggal;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanenakmenpenggals = new HashSet(0);
	private Set tblpdtenakmenbahagians = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenpenggal() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenpenggal(Long idEnakmenpenggal) {
		this.idEnakmenpenggal = idEnakmenpenggal;
	}

	/** full constructor */
	public AbstractTblpdtenakmenpenggal(Long idEnakmenpenggal,
			Tblpdtenakmen tblpdtenakmen, String tajukPenggal, String noPenggal, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanenakmenpenggals, Set tblpdtenakmenbahagians) {
		this.idEnakmenpenggal = idEnakmenpenggal;
		this.tblpdtenakmen = tblpdtenakmen;
		this.tajukPenggal = tajukPenggal;
		this.noPenggal = noPenggal;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanenakmenpenggals = tblpdtpindaanenakmenpenggals;
		this.tblpdtenakmenbahagians = tblpdtenakmenbahagians;
	}

	// Property accessors

	public Long getIdEnakmenpenggal() {
		return this.idEnakmenpenggal;
	}

	public void setIdEnakmenpenggal(Long idEnakmenpenggal) {
		this.idEnakmenpenggal = idEnakmenpenggal;
	}

	public Tblpdtenakmen getTblpdtenakmen() {
		return this.tblpdtenakmen;
	}

	public void setTblpdtenakmen(Tblpdtenakmen tblpdtenakmen) {
		this.tblpdtenakmen = tblpdtenakmen;
	}

	public String getTajukPenggal() {
		return this.tajukPenggal;
	}

	public void setTajukPenggal(String tajukPenggal) {
		this.tajukPenggal = tajukPenggal;
	}
	public String getNoPenggal() {
		return this.noPenggal;
	}

	public void setNoPenggal(String noPenggal) {
		this.noPenggal = noPenggal;
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

	public Set getTblpdtenakmenbahagians() {
		return this.tblpdtenakmenbahagians;
	}

	public void setTblpdtenakmenbahagians(Set tblpdtenakmenbahagians) {
		this.tblpdtenakmenbahagians = tblpdtenakmenbahagians;
	}

}