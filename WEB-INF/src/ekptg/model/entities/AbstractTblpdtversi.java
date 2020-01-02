package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpdtversi entity provides the base persistence definition of the
 * Tblpdtversi entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtversi implements java.io.Serializable {

	// Fields

	private Long idVersi;
	private Tblpdtmesyuarat tblpdtmesyuarat;
	private Date tarikhVersi;

	// Constructors

	/** default constructor */
	public AbstractTblpdtversi() {
	}

	/** minimal constructor */
	public AbstractTblpdtversi(Long idVersi) {
		this.idVersi = idVersi;
	}

	/** full constructor */
	public AbstractTblpdtversi(Long idVersi, Tblpdtmesyuarat tblpdtmesyuarat,
			Date tarikhVersi) {
		this.idVersi = idVersi;
		this.tblpdtmesyuarat = tblpdtmesyuarat;
		this.tarikhVersi = tarikhVersi;
	}

	// Property accessors

	public Long getIdVersi() {
		return this.idVersi;
	}

	public void setIdVersi(Long idVersi) {
		this.idVersi = idVersi;
	}

	public Tblpdtmesyuarat getTblpdtmesyuarat() {
		return this.tblpdtmesyuarat;
	}

	public void setTblpdtmesyuarat(Tblpdtmesyuarat tblpdtmesyuarat) {
		this.tblpdtmesyuarat = tblpdtmesyuarat;
	}

	public Date getTarikhVersi() {
		return this.tarikhVersi;
	}

	public void setTarikhVersi(Date tarikhVersi) {
		this.tarikhVersi = tarikhVersi;
	}

}