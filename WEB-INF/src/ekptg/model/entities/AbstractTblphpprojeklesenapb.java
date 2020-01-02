package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpprojeklesenapb entity provides the base persistence definition
 * of the Tblphpprojeklesenapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpprojeklesenapb implements
		java.io.Serializable {

	// Fields

	private Long idProjeklesenapb;
	private Tblphppmohonnjdualpertama tblphppmohonnjdualpertama;
	private String namaProjek;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpprojeklesenapb() {
	}

	/** minimal constructor */
	public AbstractTblphpprojeklesenapb(Long idProjeklesenapb) {
		this.idProjeklesenapb = idProjeklesenapb;
	}

	/** full constructor */
	public AbstractTblphpprojeklesenapb(Long idProjeklesenapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama,
			String namaProjek, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idProjeklesenapb = idProjeklesenapb;
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
		this.namaProjek = namaProjek;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdProjeklesenapb() {
		return this.idProjeklesenapb;
	}

	public void setIdProjeklesenapb(Long idProjeklesenapb) {
		this.idProjeklesenapb = idProjeklesenapb;
	}

	public Tblphppmohonnjdualpertama getTblphppmohonnjdualpertama() {
		return this.tblphppmohonnjdualpertama;
	}

	public void setTblphppmohonnjdualpertama(
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama) {
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
	}

	public String getNamaProjek() {
		return this.namaProjek;
	}

	public void setNamaProjek(String namaProjek) {
		this.namaProjek = namaProjek;
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