package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpjabatanlaporantanah entity provides the base persistence
 * definition of the Tblphpjabatanlaporantanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpjabatanlaporantanah implements
		java.io.Serializable {

	// Fields

	private Long idJabatanlaporantanah;
	private Tblphplawatantapak tblphplawatantapak;
	private Long idJabatan;
	private Long idNegerijabatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpjabatanlaporantanah() {
	}

	/** minimal constructor */
	public AbstractTblphpjabatanlaporantanah(Long idJabatanlaporantanah) {
		this.idJabatanlaporantanah = idJabatanlaporantanah;
	}

	/** full constructor */
	public AbstractTblphpjabatanlaporantanah(Long idJabatanlaporantanah,
			Tblphplawatantapak tblphplawatantapak, Long idJabatan,
			Long idNegerijabatan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idJabatanlaporantanah = idJabatanlaporantanah;
		this.tblphplawatantapak = tblphplawatantapak;
		this.idJabatan = idJabatan;
		this.idNegerijabatan = idNegerijabatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJabatanlaporantanah() {
		return this.idJabatanlaporantanah;
	}

	public void setIdJabatanlaporantanah(Long idJabatanlaporantanah) {
		this.idJabatanlaporantanah = idJabatanlaporantanah;
	}

	public Tblphplawatantapak getTblphplawatantapak() {
		return this.tblphplawatantapak;
	}

	public void setTblphplawatantapak(Tblphplawatantapak tblphplawatantapak) {
		this.tblphplawatantapak = tblphplawatantapak;
	}

	public Long getIdJabatan() {
		return this.idJabatan;
	}

	public void setIdJabatan(Long idJabatan) {
		this.idJabatan = idJabatan;
	}

	public Long getIdNegerijabatan() {
		return this.idNegerijabatan;
	}

	public void setIdNegerijabatan(Long idNegerijabatan) {
		this.idNegerijabatan = idNegerijabatan;
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