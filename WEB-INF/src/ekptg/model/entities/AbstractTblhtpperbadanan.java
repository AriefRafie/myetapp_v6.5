package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpperbadanan entity provides the base persistence definition of
 * the Tblhtpperbadanan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpperbadanan implements java.io.Serializable {

	// Fields

	private TblhtpperbadananId id;
	private Date tarikhKuasaPbdn;
	private Date tarikhMsyrtJemaah;
	private String noMemorandum;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idPerbadanan;

	// Constructors

	/** default constructor */
	public AbstractTblhtpperbadanan() {
	}

	/** minimal constructor */
	public AbstractTblhtpperbadanan(TblhtpperbadananId id, Long idPerbadanan) {
		this.id = id;
		this.idPerbadanan = idPerbadanan;
	}

	/** full constructor */
	public AbstractTblhtpperbadanan(TblhtpperbadananId id,
			Date tarikhKuasaPbdn, Date tarikhMsyrtJemaah, String noMemorandum,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idPerbadanan) {
		this.id = id;
		this.tarikhKuasaPbdn = tarikhKuasaPbdn;
		this.tarikhMsyrtJemaah = tarikhMsyrtJemaah;
		this.noMemorandum = noMemorandum;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idPerbadanan = idPerbadanan;
	}

	// Property accessors

	public TblhtpperbadananId getId() {
		return this.id;
	}

	public void setId(TblhtpperbadananId id) {
		this.id = id;
	}

	public Date getTarikhKuasaPbdn() {
		return this.tarikhKuasaPbdn;
	}

	public void setTarikhKuasaPbdn(Date tarikhKuasaPbdn) {
		this.tarikhKuasaPbdn = tarikhKuasaPbdn;
	}

	public Date getTarikhMsyrtJemaah() {
		return this.tarikhMsyrtJemaah;
	}

	public void setTarikhMsyrtJemaah(Date tarikhMsyrtJemaah) {
		this.tarikhMsyrtJemaah = tarikhMsyrtJemaah;
	}

	public String getNoMemorandum() {
		return this.noMemorandum;
	}

	public void setNoMemorandum(String noMemorandum) {
		this.noMemorandum = noMemorandum;
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

	public Long getIdPerbadanan() {
		return this.idPerbadanan;
	}

	public void setIdPerbadanan(Long idPerbadanan) {
		this.idPerbadanan = idPerbadanan;
	}

}