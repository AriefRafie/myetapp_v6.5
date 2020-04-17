package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktabahagian entity provides the base persistence definition of
 * the Tblpdtaktabahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktabahagian implements
		java.io.Serializable {

	// Fields

	private Long idAktabahagian;
	private Tblpdtaktapenggal tblpdtaktapenggal;
	private Tblpdtakta tblpdtakta;
	private String noBahagian;
	private String tajukBahagian;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtaktababs = new HashSet(0);
	private Set tblpdtpindaanaktabahagians = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktabahagian() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktabahagian(Long idAktabahagian) {
		this.idAktabahagian = idAktabahagian;
	}

	/** full constructor */
	public AbstractTblpdtaktabahagian(Long idAktabahagian,
			Tblpdtaktapenggal tblpdtaktapenggal, Tblpdtakta tblpdtakta,
			String tajukBahagian,String noBahagian, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpdtaktababs,
			Set tblpdtpindaanaktabahagians) {
		this.idAktabahagian = idAktabahagian;
		this.tblpdtaktapenggal = tblpdtaktapenggal;
		this.tblpdtakta = tblpdtakta;
		this.tajukBahagian = tajukBahagian;
		this.noBahagian = noBahagian;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtaktababs = tblpdtaktababs;
		this.tblpdtpindaanaktabahagians = tblpdtpindaanaktabahagians;
	}

	// Property accessors

	public Long getIdAktabahagian() {
		return this.idAktabahagian;
	}

	public void setIdAktabahagian(Long idAktabahagian) {
		this.idAktabahagian = idAktabahagian;
	}

	public Tblpdtaktapenggal getTblpdtaktapenggal() {
		return this.tblpdtaktapenggal;
	}

	public void setTblpdtaktapenggal(Tblpdtaktapenggal tblpdtaktapenggal) {
		this.tblpdtaktapenggal = tblpdtaktapenggal;
	}

	public Tblpdtakta getTblpdtakta() {
		return this.tblpdtakta;
	}

	public void setTblpdtakta(Tblpdtakta tblpdtakta) {
		this.tblpdtakta = tblpdtakta;
	}

	public String getTajukBahagian() {
		return this.tajukBahagian;
	}

	public void setTajukBahagian(String tajukBahagian) {
		this.tajukBahagian = tajukBahagian;
	}
	public String getNoBahagian() {
		return this.noBahagian;
	}

	public void setNoBahagian(String noBahagian) {
		this.noBahagian = noBahagian;
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

	public Set getTblpdtaktababs() {
		return this.tblpdtaktababs;
	}

	public void setTblpdtaktababs(Set tblpdtaktababs) {
		this.tblpdtaktababs = tblpdtaktababs;
	}

	public Set getTblpdtpindaanaktabahagians() {
		return this.tblpdtpindaanaktabahagians;
	}

	public void setTblpdtpindaanaktabahagians(Set tblpdtpindaanaktabahagians) {
		this.tblpdtpindaanaktabahagians = tblpdtpindaanaktabahagians;
	}

}