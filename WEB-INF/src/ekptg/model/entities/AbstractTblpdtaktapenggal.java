package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktapenggal entity provides the base persistence definition of
 * the Tblpdtaktapenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktapenggal implements java.io.Serializable {

	// Fields

	private Long idAktapenggal;
	private Tblpdtakta tblpdtakta;
	private String tajukPenggal;
	private String noPenggal;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtaktabahagians = new HashSet(0);
	private Set tblpdtpindaanaktapenggals = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktapenggal() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktapenggal(Long idAktapenggal) {
		this.idAktapenggal = idAktapenggal;
	}

	/** full constructor */
	public AbstractTblpdtaktapenggal(Long idAktapenggal, Tblpdtakta tblpdtakta,
			String tajukPenggal,String noPenggal, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpdtaktabahagians,
			Set tblpdtpindaanaktapenggals) {
		this.idAktapenggal = idAktapenggal;
		this.tblpdtakta = tblpdtakta;
		this.tajukPenggal = tajukPenggal;
		this.noPenggal = noPenggal;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtaktabahagians = tblpdtaktabahagians;
		this.tblpdtpindaanaktapenggals = tblpdtpindaanaktapenggals;
	}

	// Property accessors

	public Long getIdAktapenggal() {
		return this.idAktapenggal;
	}

	public void setIdAktapenggal(Long idAktapenggal) {
		this.idAktapenggal = idAktapenggal;
	}

	public Tblpdtakta getTblpdtakta() {
		return this.tblpdtakta;
	}

	public void setTblpdtakta(Tblpdtakta tblpdtakta) {
		this.tblpdtakta = tblpdtakta;
	}
	public String getNoPenggal() {
		return this.noPenggal;
	}

	public void setNoPenggal(String noPenggal) {
		this.noPenggal = noPenggal;
	}
	public String getTajukPenggal() {
		return this.tajukPenggal;
	}

	public void setTajukPenggal(String tajukPenggal) {
		this.tajukPenggal = tajukPenggal;
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

	public Set getTblpdtaktabahagians() {
		return this.tblpdtaktabahagians;
	}

	public void setTblpdtaktabahagians(Set tblpdtaktabahagians) {
		this.tblpdtaktabahagians = tblpdtaktabahagians;
	}

	public Set getTblpdtpindaanaktapenggals() {
		return this.tblpdtpindaanaktapenggals;
	}

	public void setTblpdtpindaanaktapenggals(Set tblpdtpindaanaktapenggals) {
		this.tblpdtpindaanaktapenggals = tblpdtpindaanaktapenggals;
	}

}