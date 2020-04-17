package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpdtrujlokasimesyuarat entity provides the base persistence
 * definition of the Tblpdtrujlokasimesyuarat entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractTblpdtrujlokasimesyuarat implements
		java.io.Serializable {

	// Fields

	private Long idLokasi;
	private String lokasi;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idNegeri;
	private Long idSeksyen;

	// Constructors

	/** default constructor */
	public AbstractTblpdtrujlokasimesyuarat() {
	}

	/** full constructor */
	public AbstractTblpdtrujlokasimesyuarat(String lokasi, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idNegeri, Long idSeksyen) {
		this.lokasi = lokasi;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idNegeri = idNegeri;
		this.idSeksyen = idSeksyen;
	}

	// Property accessors

	public Long getIdLokasi() {
		return this.idLokasi;
	}

	public void setIdLokasi(Long idLokasi) {
		this.idLokasi = idLokasi;
	}

	public String getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
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

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

}