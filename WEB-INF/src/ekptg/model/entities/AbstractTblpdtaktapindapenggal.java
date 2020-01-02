package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktapindapenggal entity provides the base persistence
 * definition of the Tblpdtaktapindapenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktapindapenggal implements
		java.io.Serializable {

	// Fields

	private Long idAktapindapenggal;
	private Tblpdtaktapinda tblpdtaktapinda;
	private String namaPenggal;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanaktapenggals = new HashSet(0);
	private Set tblpdtaktapindabahagians = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktapindapenggal() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktapindapenggal(Long idAktapindapenggal) {
		this.idAktapindapenggal = idAktapindapenggal;
	}

	/** full constructor */
	public AbstractTblpdtaktapindapenggal(Long idAktapindapenggal,
			Tblpdtaktapinda tblpdtaktapinda, String namaPenggal, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanaktapenggals, Set tblpdtaktapindabahagians) {
		this.idAktapindapenggal = idAktapindapenggal;
		this.tblpdtaktapinda = tblpdtaktapinda;
		this.namaPenggal = namaPenggal;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanaktapenggals = tblpdtpindaanaktapenggals;
		this.tblpdtaktapindabahagians = tblpdtaktapindabahagians;
	}

	// Property accessors

	public Long getIdAktapindapenggal() {
		return this.idAktapindapenggal;
	}

	public void setIdAktapindapenggal(Long idAktapindapenggal) {
		this.idAktapindapenggal = idAktapindapenggal;
	}

	public Tblpdtaktapinda getTblpdtaktapinda() {
		return this.tblpdtaktapinda;
	}

	public void setTblpdtaktapinda(Tblpdtaktapinda tblpdtaktapinda) {
		this.tblpdtaktapinda = tblpdtaktapinda;
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

	public Set getTblpdtpindaanaktapenggals() {
		return this.tblpdtpindaanaktapenggals;
	}

	public void setTblpdtpindaanaktapenggals(Set tblpdtpindaanaktapenggals) {
		this.tblpdtpindaanaktapenggals = tblpdtpindaanaktapenggals;
	}

	public Set getTblpdtaktapindabahagians() {
		return this.tblpdtaktapindabahagians;
	}

	public void setTblpdtaktapindabahagians(Set tblpdtaktapindabahagians) {
		this.tblpdtaktapindabahagians = tblpdtaktapindabahagians;
	}

}