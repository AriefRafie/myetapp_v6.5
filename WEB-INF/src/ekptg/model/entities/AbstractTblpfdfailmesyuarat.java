package ekptg.model.entities;


import java.util.Date;

/**
 * AbstractTblpfdfailmesyuarat entity provides the base persistence definition
 * of the Tblpfdfailmesyuarat entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfdfailmesyuarat implements
		java.io.Serializable {

	// Fields

	private Long idFailmesyuarat;
	private Long idFail;
	private Long idMesyuarat;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpfdfailmesyuarat() {
	}

	/** full constructor */
	public AbstractTblpfdfailmesyuarat(Long idFail,
			Long idMesyuarat, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idFail = idFail;
		this.idMesyuarat = idMesyuarat;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdFailmesyuarat() {
		return this.idFailmesyuarat;
	}

	public void setIdFailmesyuarat(Long idFailmesyuarat) {
		this.idFailmesyuarat = idFailmesyuarat;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public Long getIdMesyuarat() {
		return this.idMesyuarat;
	}

	public void setIdMesyuarat(Long idMesyuarat) {
		this.idMesyuarat = idMesyuarat;
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